public class Sphere implements Shapes3D {
	
	private final double radius;

	public Sphere(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Sphere [" + String.format("%.2f", this.radius) + "]";
	}

	public Sphere setRadius(double radius) {
		return new Sphere(radius);
	}

	public double getVolume() {
		return 4.0 / 3.0 * Math.PI * this.radius * this.radius * this.radius;
	}

	public double getSurfaceArea() {
		return 4 * Math.PI * this.radius * this.radius;
	}
}
