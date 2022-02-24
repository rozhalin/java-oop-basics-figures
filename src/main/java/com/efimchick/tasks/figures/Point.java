package com.efimchick.tasks.figures;

import java.util.Objects;

import static com.efimchick.tasks.figures.MathConstant.EPSILON;

class Point implements Comparable<Point>{
    final private double x;
    final private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return this.compareTo(point) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Point p) {
        if (Math.abs(this.x - p.getX()) < EPSILON && Math.abs(this.y - p.getY()) < EPSILON) {
            return 0;
        } else if (this.x > p.getX()) {
            return 1;
        } else {
            return -1;
        }
    }
}
