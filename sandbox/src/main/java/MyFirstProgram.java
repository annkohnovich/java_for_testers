public class MyFirstProgram {

    public static void main (String[] args) {

        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);

        System.out.print("Distance between point : (" + p1.x + ", " + p1.y + ")" + " and (" + p2.x + ", " + p2.y + ") ");
        System.out.println("equals "+ p1.distanceTo(p2));
    }

}