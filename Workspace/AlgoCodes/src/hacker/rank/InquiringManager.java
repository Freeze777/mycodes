package hacker.rank;
import java.util.PriorityQueue;
import java.util.Scanner;

class Order implements Comparable<Order> {

	long amount;
	long time;

	public Order(long amount, long time) {
		super();
		this.amount = amount;
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + (int) (time ^ (time >>> 32));
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
		Order other = (Order) obj;
		if (amount != other.amount)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		return (int) -(this.amount - o.amount);
	}

}

public class InquiringManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		PriorityQueue<Order> pq = new PriorityQueue<Order>();

		for (int i = 0; i < n; i++) {
			int c = sc.nextInt();
			if (c == 1) {
				Order odr = new Order(sc.nextLong(), sc.nextLong());
				pq.add(odr);
			} else {
				long t = sc.nextLong();
				long et = t - 59;
				while (!pq.isEmpty()) {
					Order odr = pq.peek();

					if (odr.time >= et) {
						System.out.println(odr.amount);
						break;
					} else
						pq.remove();
				}
				if(pq.isEmpty())
					System.out.println(-1);

			}

		}
	}
}