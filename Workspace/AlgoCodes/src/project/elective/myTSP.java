package project.elective;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * @author: Freeze Francis
 * */

public class myTSP {
	public static final double M_PI = 3.14159265358979323846264;

	public static void computeEuclidean2DDistance(double[] x, double[] y,
			double[][] distMatrix) {
		for (int i = 0; i < distMatrix.length; i++) {
			distMatrix[i][i] = Long.MAX_VALUE;
			for (int j = i + 1; j < distMatrix.length; j++) {
				double dx=x[i] - x[j];
				double dy=y[i] - y[j];
				distMatrix[i][j] =Math.sqrt(dx*dx
						+dy*dy);

				distMatrix[j][i] = distMatrix[i][j];
			}
		}
	}

	public static void computeGeoDistance(double[] latitude,
			double[] longitude, long[][] distMatrix) {
		double lati, latj, longi, longj;
		double q1, q2, q3, q4, q5;
		for (int i = 0; i < distMatrix.length; i++) {
			distMatrix[i][i] = Long.MAX_VALUE;
			for (int j = i + 1; j < distMatrix.length; j++) {
				lati = M_PI * latitude[i] / 180.0;
				latj = M_PI * latitude[j] / 180.0;

				longi = M_PI * longitude[i] / 180.0;
				longj = M_PI * longitude[j] / 180.0;

				q1 = Math.cos(latj) * Math.sin(longi - longj);
				q3 = Math.sin((longi - longj) / 2.0);
				q4 = Math.cos((longi - longj) / 2.0);
				q2 = Math.sin(lati + latj) * q3 * q3 - Math.sin(lati - latj)
						* q4 * q4;
				q5 = Math.cos(lati - latj) * q4 * q4 - Math.cos(lati + latj)
						* q3 * q3;
				distMatrix[i][j] = (long) (6378388.0 * Math.atan2(
						Math.sqrt(q1 * q1 + q2 * q2), q5) + 1.0);
				distMatrix[j][i] = distMatrix[i][j];
			}
		}

	}

	public static void main(String args[]) {

		try {
			// distance matrix for 5-city TSP
			// distance[i][i]=INF to avoid self loops
			Scanner sc = null;
			File file;
			try {
				file = new File("tsp_dijbouti.txt");
				sc = new Scanner(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int nCities = sc.nextInt();
			double[] x_cord = new double[nCities];
			double[] y_cord = new double[nCities];

			for (int i = 0; i < nCities; i++) {
				sc.next();
				x_cord[i] = sc.nextDouble();
				y_cord[i] = sc.nextDouble();
			}

			double distance[][] = new double[nCities][nCities];

			computeEuclidean2DDistance(x_cord, y_cord, distance);
			// GlobalUtils.printArray(distance);
			/*int[] path = {0,1,3,2,4,5,6,7,8,11,10,18,17,16,15,12,14,19,22,25,24,21,23,27,26,30,35,33,32,37,36,34,31,29,28,20,13,9};
			double sum = 0;
			for (int i = 0; i < path.length-1; i++) {
				System.out.println(path[i]+"->"+path[i+1]);
				sum += distance[path[i]][path[i + 1]];
			}
			sum+=distance[path[path.length-1]][path[0]];
			System.out.println(sum);
			System.exit(0);*/
			
			GRBEnv env = new GRBEnv();
			GRBModel model = new GRBModel(env);
			model.set(GRB.StringAttr.ModelName, "5-city TSP");

			GRBVar[][] x = new GRBVar[nCities][nCities];
			for (int i = 0; i < nCities; i++) {
				for (int j = 0; j < nCities; ++j) {
					// create decision variables and define objective function
					x[i][j] = model.addVar(0.0, 1.0, distance[i][j],
							GRB.BINARY, "x" + i + "" + j);

				}

			}

			// 1-minimize objective function
			model.set(GRB.IntAttr.ModelSense, 1);

			// Update model with new variables
			model.update();

			// one incoming-edge to node and one outgoing-edge from node
			// constraints
			for (int i = 0; i < nCities; i++) {
				GRBLinExpr expr = new GRBLinExpr();
				GRBLinExpr expr1 = new GRBLinExpr();
				for (int j = 0; j < nCities; j++) {
					if (i != j) {
						expr.addTerm(1.0, x[i][j]);
						expr1.addTerm(1.0, x[j][i]);
					}
				}
				model.addConstr(expr, GRB.EQUAL, 1.0, "outgoing_city" + i);
				model.addConstr(expr1, GRB.EQUAL, 1.0, "incoming_city" + i);
			}

			model.update();

			// subtour elimination using MTZ formulation
			GRBVar[] u = new GRBVar[nCities];
			u[0] = model.addVar(1, 1, 0, GRB.INTEGER, "u0");
			for (int i = 1; i < nCities; i++) {

				u[i] = model.addVar(2, nCities, 0, GRB.INTEGER, "u" + i);
			}
			// Update model with new variables
			model.update();

			GRBLinExpr expr = new GRBLinExpr();
			for (int i = 1; i < nCities; i++) {

				for (int j = 1; j < nCities; j++) {
					if (i != j) {
						expr.addTerm(1.0, u[i]);
						expr.addTerm(-1.0, u[j]);
						expr.addTerm(nCities, x[i][j]);
						model.addConstr(expr, GRB.LESS_EQUAL, nCities - 1,
								"subtour_elim" + i + "" + j);
						expr = new GRBLinExpr();
					}

				}

			}
			model.update();
			model.write("model.lp");

			model.optimize();
			// PrintStream out = new PrintStream(
			// new FileOutputStream("output.txt"));
			// System.setOut(out);
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < nCities; i++) {
				for (int j = 0; j < nCities; j++) {

					// System.out.println("x"+i+""+j+":"+x[i][j].get(GRB.DoubleAttr.X)+" ");
					Double val = x[i][j].get(GRB.DoubleAttr.X);
					if (val.equals(1.0)) {
						// System.out.println(i + "->" + j);
						map.put(i, j);
					}
				}
			}
			int city = 0;
			while (nCities > 0) {
				System.out.print(city + "->");
				city = map.get(city);

				nCities--;
			}

		} catch (GRBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		 * catch (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}
}
