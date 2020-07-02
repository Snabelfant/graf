package function;

public abstract class Function {
    private Double lastX = null;
    private Double lastY = null;
    private Double lastT = null;
    public XY compute(double t) {
        double x = computeX(t);
        double y = computeY(t);

        lastX = x;
        lastY = y;
        lastT = t;

        return new XY(x, y);
    }

    abstract double computeX(double t);

    abstract double computeY(double t);
}
