public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "point (" + String.format("%.3f", this.x) + ", " + 
                String.format("%.3f", this.y) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return Math.abs(this.x - p.x) < 1E-15 && Math.abs(this.y - p.y) < 1E-15;
    }

    public Point midPoint(Point otherPoint) {
        double midX = (this.x + otherPoint.x) / 2;
        double midY = (this.y + otherPoint.y) / 2;
        return new Point(midX, midY);
    }

    public double angleTo(Point otherPoint) {
        return Math.atan2(otherPoint.y - this.y, otherPoint.x - this.x);
    }

    public double distanceTo(Point otherPoint) {
        double dx = this.x - otherPoint.x;
        double dy = this.y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point moveTo(double theta, double d) {
        return new Point(this.x + d * Math.cos(theta), this.y +
                    d * Math.sin(theta));
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
