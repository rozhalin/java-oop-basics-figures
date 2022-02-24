package com.efimchick.tasks.figures;

import java.util.Arrays;

//TODO
class Triangle extends Figure implements Degenerate{

    final private Point[] points = new Point[3];

    public Triangle(Point pointA, Point pointB, Point pointC){
        this.points[0] = pointA;
        this.points[1] = pointB;
        this.points[2] = pointC;
        Arrays.sort(points);

        if (this.degenerationCheck()) {
            throw new RuntimeException(String.format("This %s is degenerative", this));
        }
    }
    public Point[] getPoints() {
        return this.points;
    }

    @Override
    public double area() {
        double area;
        area = 0.5 * Math.abs(
                                (points[0].getX() - points[2].getX()) *
                                (points[1].getY() - points[2].getY()) -
                                (points[1].getX() - points[2].getX()) *
                                (points[0].getY() - points[2].getY())
                            );
        return area;
    }

    @Override
    public Point centroid() {
        double sumX = 0.0;
        double sumY = 0.0;
        for (Point p : points) {
            sumX = sumX + p.getX();
            sumY = sumY + p.getY();
        }
        return new Point(sumX / 3.0, sumY / 3.0);
    }

    @Override
    public String toString() {
        return String.format("Triangle[%s,%s,%s]",
                points[0].toString(), points[1].toString(), points[2].toString());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Triangle)) return false;
        return Arrays.equals(this.points, ((Triangle) figure).getPoints());
    }

    @Override
    public boolean degenerationCheck() {
        return DoubleComparator.doubleCompare(this.area(), 0.0d) == 0;
    }

    @Override
    public Point leftMostPoint() {
        return points[0];
    }
}