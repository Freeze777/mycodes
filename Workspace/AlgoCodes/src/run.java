import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class run {
	static long mod = 1000000007;

	public static Ellipse2D getEllipseFromCenter(double x, double y,
			double width, double height) {
		double newX = x - width / 2.0;
		double newY = y - height / 2.0;

		Ellipse2D ellipse = new Ellipse2D.Double(newX, newY, width, height);

		return ellipse;
	}

	public static Shape makeEllipse(int x1, int y1, int x2, int y2,
			double majorAxis) {
		// Create foci points
		Point2D f1 = new Point2D.Double(x1, y1);
		Point2D f2 = new Point2D.Double(x2, y2);

		// Calculate ellipse characteristics
		double a = majorAxis / 2.0;
		double c = f1.distance(f2) / 2.0;
		double b = Math.sqrt(a * a - c * c);
		//System.out.println(a + " " + b);

		Point2D centre = new Point2D.Double((x1 + x2) / 2.0, (y1 + y2) / 2.0);

		/*double newX = centre.getX() - a;
		double newY = centre.getY() - b;

		Ellipse2D ellipse = new Ellipse2D.Double(newX, newY, 2 * a, 2 * b);
		return ellipse;*/
		

		 // Create a transform to rotate and translate the ellipse
        double theta = Math.atan2 (y2 - y1, x2 - x1);
        AffineTransform trans = new AffineTransform();
        trans.translate (centre.getX(), centre.getY());
        trans.rotate(theta);

        // Create an ellipse with correct size but origin at centre 
        Ellipse2D tmpEllipse = new Ellipse2D.Double (-a, -b, 2 * a, 2 * b);

        // Translate and rotate it to where it should be
        Shape ellipse = trans.createTransformedShape (tmpEllipse);

        return ellipse;
	}

	private static void solve(FastScanner sc, PrintStream out) {
		int n = sc.nextInt();
		int v = sc.nextInt();
		int x[]=new int[v];
		int y[]=new int[v];
		
		for (int i = v-1; i>=0; i--) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}
		Polygon p = new Polygon(x,y,v);
		Area commonArea = new Area(p);
		n--;
		while (n-- > 0) {
			 v = sc.nextInt();
			 x=new int[v];
			 y=new int[v];
			
			for (int i = v-1; i>=0; i--) {
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			p = new Polygon(x,y,v);
			Area pArea = new Area(p);
			commonArea.intersect(pArea);
		}
		// returns the rectangle-- (bottom left corner(x,y),width, hieght)
		//System.out.println(commonArea.getBounds());

		int m = sc.nextInt();
		while (m-- > 0) {
			int f1x = sc.nextInt();
			int f1y = sc.nextInt();
			int f2x = sc.nextInt();
			int f2y = sc.nextInt();
			int majorAxis = sc.nextInt() * 2;
			Area eArea = new Area(makeEllipse(f1x, f1y, f2x, f2y, majorAxis*1.0000000001));
			commonArea.intersect(eArea);
		}
		// Ellipse2D e=new Ellipse2D.Double(x, y, w, h)
		Rectangle2D ans=commonArea.getBounds2D();
		out.printf("%.9f\n",ans.getCenterX());
		out.printf("%.9f\n",ans.getCenterY());
		//System.out.println(1.000343253456346455540001);

	}

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintStream out = System.out;
		solve(in, out);
		in.close();
		out.close();
	}

	public static long gcd(long x, long y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	public static long pow(long n, long p, long m) {
		long result = 1;
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		while (p != 0) {
			if (p % 2 == 1)
				result *= n;
			if (result >= m)
				result %= m;
			p >>= 1;
			n *= n;
			if (n >= m)
				n %= m;
		}
		return result;
	}

	static class Pair implements Comparable<Pair> {
		int a, b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.a != o.a)
				return Integer.compare(this.a, o.a);
			else
				return Integer.compare(this.b, o.b);
			// return 0;
		}

		public boolean equals(Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return p.a == a && p.b == b;
			}
			return false;
		}

		public int hashCode() {
			return new Integer(a).hashCode() * 31 + new Integer(b).hashCode();
		}
	}

	static class FastScanner {
		BufferedReader reader;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
			st = null;
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = reader.readLine();
					if (line == null) {
						return null;
					}
					st = new StringTokenizer(line);
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}
			return st.nextToken();
		}

		String nextLine() {
			String s = null;
			try {
				s = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return next().charAt(0);
		}

		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			int i = 0;
			while (i < n) {
				arr[i++] = nextInt();
			}
			return arr;
		}

		long[] nextLongArray(int n) {
			long[] arr = new long[n];
			int i = 0;
			while (i < n) {
				arr[i++] = nextLong();
			}
			return arr;
		}

		int[] nextIntArrayOneBased(int n) {
			int[] arr = new int[n + 1];
			int i = 1;
			while (i <= n) {
				arr[i++] = nextInt();
			}
			return arr;
		}

		long[] nextLongArrayOneBased(int n) {
			long[] arr = new long[n + 1];
			int i = 1;
			while (i <= n) {
				arr[i++] = nextLong();
			}
			return arr;
		}

		void close() {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}