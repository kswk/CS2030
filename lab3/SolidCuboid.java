public class SolidCuboid extends Cuboid implements SolidShapes {
	
	private final double density;

	public SolidCuboid(double height, double width, double length, double density) {
		super(height, width, length);
		this.density = density;
	}

	@Override
	public String toString() {
		return "Solid" + super.toString() + " with a mass of " + 
				String.format("%.2f", this.getMass());
	}

	public SolidCuboid setHeight(double height) {
		return new SolidCuboid(height, width, length, density);
	}

	public SolidCuboid setWidth(double width) {
		return new SolidCuboid(height, width, length, density);
	}

	public SolidCuboid setLength(double length) {
		return new SolidCuboid(height, width, length, density);
	}

	public double getDensity() {
		return density;
	}

	public double getMass() {
		return this.getDensity() * this.getVolume();
	}
}
