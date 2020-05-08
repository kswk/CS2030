public class Circle {
    Point centre;
    double radius;

    private Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public static Circle getCircle(Point centre, double radius) {
        if (Double.isNaN(centre.getX()) || Double.isNaN(centre.getY())) {
            return null;
        }
        if (radius <= 0) {
            return null;
        } else {
            return new Circle(centre, radius);
        }
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centered at " 
                + this.centre;
    }

	public boolean contains(Point p) {
		return this.centre.distanceTo(p) <= radius;
	}
}
