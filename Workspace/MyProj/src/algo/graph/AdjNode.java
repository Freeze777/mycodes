package algo.graph;

public class AdjNode implements Comparable<AdjNode> {

	int dest;
	int cost;

	public AdjNode(int dest, int cost) {
		super();
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + dest;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdjNode other = (AdjNode) obj;
		if (dest != other.dest)
			return false;
		return true;
	}
	
	public int compareTo(AdjNode o) {
		// TODO Auto-generated method stub
		return this.dest-o.dest;
	}

	@Override
	public String toString() {
		return "AdjNode [dest=" + dest + ", cost=" + cost + "]\n";
	}

}
