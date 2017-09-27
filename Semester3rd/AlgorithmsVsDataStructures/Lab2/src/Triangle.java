import java.text.Format;

/**
 * Created by alesia on 9/26/17.
 * 10 - Трикутник: координати вершин, конструктор, методи обчислення
 * площини, периметру, виведення об'єкта
 */
public class Triangle {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle (double x1, double y1, double x2, double y2, double x3, double y3) throws TriangleException {
        if (Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) == 0) throw new TriangleException();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double calculateArea () {
        double area = Math.abs((double) (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) ) / 2);

        return area;
    }

    public double calculatePerimeter () {
        double side1 = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double side2 = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        double side3 = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
        double perimeter = side1 + side2 + side3;

        return perimeter;
    }

    @Override
    public String toString() {
        String result = String.format("Area = %1$-10f Perimeter = %2$-10f " +
                "Triangle { x1 = %3$-10f y1 = %4$-10f x2 = %5$-10f y2 = %6$-10f" +
                " x3 = %7$-10f y3 = %8$-10f}"
                , calculateArea()
                , calculatePerimeter()
                , x1, y1, x2, y2, x3, y3);

        return result;
    }
}
