package functional.consumer;

import java.util.StringJoiner;

public class RightTriangle {
    private double sideA;
    private double sideB;
    public RightTriangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }
    public double getSideA() {
        return sideA;
    }
    public void setSideA(double sideA) {
        this.sideA = sideA;
    }
    public double getSideB() {
        return sideB;
    }
    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RightTriangle.class.getSimpleName() + "[", "]")
                .add("sideA=" + sideA).add("sideB=" + sideB).toString();
    }
}
