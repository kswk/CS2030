public class SolidSphere extends Sphere implements SolidShapes {
	
	private final double density;

	public SolidSphere(double radius, double density) {
		super(radius);
		this.density = density;
	}

	@Override
	public String toString() {
		return "Solid" + super.toString() + " with a mass of " + String.format("%.2f", this.getMass());
	}

	public SolidSphere setRadius(double radius) {
		return new SolidSphere(radius, density);
	}

	public double getDensity() {
		return density;
	}

	public double getMass() {
		return this.getDensity() * this.getVolume();
	}
}
