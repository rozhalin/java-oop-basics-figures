package com.efimchick.tasks.figures;

class Circle extends Figure{

    final private Point center;
    final private Double radius;

    public Point getCenter() {
        return center;
    }

    public Double getRadius() {
        return radius;
    }

    public Circle(Point center, double radius){
        if (center == null) throw new RuntimeException("Center is null");
        this.center = center;
        this.radius = radius;
        if (radius <= 0.0d) {
            throw new RuntimeException("Incorrect radius");
        }
        if (degenerationCheck()) {
            throw new RuntimeException(String.format("This %s is degenerative", this));
        }
    }

    @Override
    public boolean degenerationCheck() {
        return DoubleComparator.doubleCompare(radius, 0.0d) == 0;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Circle)) return false;
        return this.center.compareTo(((Circle) figure).getCenter()) == 0
                && DoubleComparator.doubleCompare(this.radius, ((Circle) figure).getRadius()) == 0;
    }

    @Override
    public String toString() {
        return "Circle[" + center + radius.toString() + "]";
    }

    @Override
    public Point leftMostPoint() {
        return new Point(center.getX() - radius, center.getY());
    }
}
