public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point p) {
        return Math.sqrt((p.y - y) * (p.y - y) + (p.x - x) * (p.x - x));
    }

}
