public class Sample {
	private int a, b, c;

	Sample(int b, int c) {
		this.b = b;
		this.c = c;
	}

	public void func() {
		System.out.println(this.a);

	}
	public static void main(String[] args) {
		//int a;
		//System.out.println(a);
		new Sample(1,2).func();
	}
}
