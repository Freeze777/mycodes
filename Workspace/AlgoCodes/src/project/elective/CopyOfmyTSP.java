package project.elective;

import gurobi.*;

public class CopyOfmyTSP {

	public static void main(String args[]) {

		try {
			int distance[][] = { { Integer.MAX_VALUE, 10, 8, 9, 7 },
					{ 10, Integer.MAX_VALUE, 10, 5, 6 },
					{ 8, 10, Integer.MAX_VALUE, 8, 9 },
					{ 9, 5, 8, Integer.MAX_VALUE, 6 },
					{ 7, 6, 9, 6, Integer.MAX_VALUE } };
			int nCities = distance.length;

			GRBEnv env = new GRBEnv();
			GRBModel model = new GRBModel(env);
			model.set(GRB.StringAttr.ModelName, "5-city TSP");

			GRBVar[][] x = new GRBVar[nCities][nCities];
			for (int i = 0; i < nCities; i++) {
				for (int j = 0; j < nCities; ++j) {

					x[i][j] = model.addVar(0.0, 1.0, distance[i][j],
							GRB.BINARY, "x" + i + "" + j);

					//x[j][i] = x[i][j];// assuming the symmetric distance
										// matrix
				}

			}

			// minimize
			model.set(GRB.IntAttr.ModelSense, 1);

			// Update model with new variables
			model.update();

			// one incoming node and one outgoing node constraint
			for (int i = 0; i < nCities; i++) {
				GRBLinExpr expr = new GRBLinExpr();
				GRBLinExpr expr1 = new GRBLinExpr();
				for (int j = 0; j < nCities; j++)
				{	if(i!=j){
					expr.addTerm(1.0, x[i][j]);
					expr1.addTerm(1.0, x[j][i]);
					}
				}
				model.addConstr(expr, GRB.EQUAL, 1.0, "outgoing_city" + i);
				model.addConstr(expr1, GRB.EQUAL, 1.0, "incoming_city" + i);
			}
			model.update();
			// subtour elimination

			GRBVar[] u = new GRBVar[nCities];
			for (int i = 0; i < nCities; i++) {
				u[i] = model.addVar(0,GRB.INFINITY,0,
						GRB.INTEGER, "u" + i);
			}
			// Update model with new variables
			model.update();
			
			GRBLinExpr expr = new GRBLinExpr();
			for (int i = 0; i < nCities-1; i++) {
			
				for (int j = 1; j < nCities; j++) {
					if (i != j) {
						expr.addTerm(1.0, u[i]);
						expr.addTerm(-1.0, u[j]);
						expr.addTerm(nCities, x[i][j]);
						model.addConstr(expr, GRB.LESS_EQUAL, nCities-1, "subtour_elim"+i+""+j);
						expr=new GRBLinExpr();
					}
					
				}
				
			}
			model.update();
			model.write("model.lp");
			
		model.optimize();
		} catch (GRBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
