public class SolidShape3D implements SolidShapes {
	
	private final Shapes3D shape;
	private final Material material;

	public SolidShape3D(Shapes3D shape, Material material) {
		this.shape = shape;
		this.material = material;
	}

	public double getDensity() {
		return material.getDensity();
	}

	public double getMass() {
		return shape.getVolume() * material.getDensity();
	}

	@Override
	public String toString() {
		return "Solid" + shape.toString() + " with a mass of " + String.format("%.2f", getMass());
	}
}
