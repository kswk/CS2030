import java.util.Scanner;

public class Main {
    public static Circle createCircle(Point p, Point q, double radius) {
        if (p.equals(q)) {
            return null;
        } else if (radius <= 0) {
            return null;
        } else {
            Point m = p.midPoint(q);
            double theta = p.angleTo(q) + Math.PI / 2;
            double distanceToMid = p.distanceTo(m);
            double d = Math.sqrt(Math.pow(radius, 2) -
                    Math.pow(distanceToMid, 2));
            Point centre = m.moveTo(theta, d);
            return Circle.getCircle(centre, radius);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point[] arr = new Point[n];
		for (int i = 0; i < n; i++) {
			Point p = new Point(sc.nextDouble(), sc.nextDouble());
			arr[i] = p;
		}
		int max = 0;
		int coverage = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Circle c = createCircle(arr[i], arr[j], 1);
				if (max < coverage) {
					max = coverage;
				}
				coverage = 0;
				for (int k = 0; k < n; k++) {
					if (c != null) {
						if (c.contains(arr[k])) {
						coverage += 1;
						}
					}
				}
			}
		}
		System.out.println("Maximum Disc Coverage: " + max);
		sc.close();
    }
}
