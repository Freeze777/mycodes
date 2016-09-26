import java.util.Scanner;
class MySample implements Runnable{

	@Override
	public void run() {
		System.out.println("hi");
		
	}

	public void start() {
		System.out.println("afsdg");
		
	}
	
}
public class T {

	public static void main(String[] args) {
		MySample s=new MySample();
		System.out.println(Long.MAX_VALUE);
		Thread t=new Thread();
	}
}