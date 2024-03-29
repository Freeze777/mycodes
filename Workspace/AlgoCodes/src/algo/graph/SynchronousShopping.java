package algo.graph;

// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.lang.*;
import java.io.*;

public class SynchronousShopping {
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	static int V ;

	static int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 1; v <= V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	// A utility function to print the constructed distance array
	static void printSolution(int dist[], int n) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 1; i <= V; i++)
			System.out.println(i + " \t\t " + dist[i]);
	}

	// Funtion that implements Dijkstra's single source shortest path
	// algorithm for a graph represented using adjacency matrix
	// representation
	static void dijkstra(int graph[][], int src) {
		Set<Integer> set=new HashSet<Integer>();
		int dist[] = new int[V+1]; // The output array. dist[i] will hold
									// the shortest distance from src to i

		// sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		Boolean sptSet[] = new Boolean[V+1];

		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u1 = minDistance(dist, sptSet);
			// Mark the picked vertex as processed
			sptSet[u1] = true;
			
			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 1; v <= V; v++)

				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && graph[u1][v] != 0
						&& dist[u1] != Integer.MAX_VALUE
						&& dist[u1] + graph[u1][v] < dist[v])
					dist[v] = dist[u1] + graph[u1][v];
		}

		// print the constructed distance array
		printSolution(dist, V);
	}

	// Driver method
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		List<Set<Integer>> fish = new ArrayList<Set<Integer>>();
		fish.add(null);

		for (int i = 1; i <= V; i++) {
			Set<Integer> s=new HashSet<Integer>();
			int nfish=sc.nextInt();
			for (int j = 0; j < nfish; j++) 
				s.add(sc.nextInt());
			fish.add(s);
			
		}
		int graph[][] = new int[V + 1][V + 1];
		for (int i = 0; i < m; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int z=sc.nextInt();
			graph[x][y]=z;
			graph[y][x]=z;
		}
		

		dijkstra(graph, 1);
		System.out.println(fish);
	}
}