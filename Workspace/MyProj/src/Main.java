import java.util.LinkedList;
import java.util.Scanner;
class Graph
    {
    int vertex,weight;
    int color;
    LinkedList<Graph> edge;
    Graph(int vertex,int weight)
        {
        this.vertex=vertex;
        this.weight=weight;
        this.edge=new LinkedList<Graph>();
        this.color=0;
    }
}
public class Main{
public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int i,j,k,n,sum=0,num;
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        Graph v[]=new Graph[n];
        for(i=0;i<n;i++)
        {
            num=in.nextInt();
            v[i]=new Graph(i+1,num);
            sum=sum+num;
        }
        for(i=0;i<n-1;i++)    
            v[in.nextInt()-1].edge.add(v[in.nextInt()-1]);
            
        
        //dfs(v,)
        
}
}