public class Circle extends Shape{

    @Override
    public double getArea(double d) {
        return Math.PI*d*d;
    }

    @Override
    public double getPerimeter(double d) {
        return 2*Math.PI*d;
    }
}
