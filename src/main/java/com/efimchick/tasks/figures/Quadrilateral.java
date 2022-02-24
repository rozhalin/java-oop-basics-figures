package com.efimchick.tasks.figures;

import java.util.Arrays;

//TODO
class Quadrilateral extends Figure implements Convex{

    private Point[] points = new Point[4];

    public Point[] getPoints() {
        return this.points;
    }

    public Quadrilateral(Point pointA, Point pointB, Point pointC, Point pointD) {
        this.points[0] = pointA;
        this.points[1] = pointB;
        this.points[2] = pointC;
        this.points[3] = pointD;
        if (!isConvex(this.points)) throw new RuntimeException("This quadrilateral isn't convex");
        this.points = convexHull(this.points);
        if (this.degenerationCheck()) {
            throw new RuntimeException(String.format("This %s is degenerative", this));
        }
    }

    @Override
    public boolean degenerationCheck() {
        return new Triangle(this.points[0], this.points[1], this.points[2]).degenerationCheck() ||
                new Triangle(this.points[0], this.points[1], this.points[3]).degenerationCheck() ||
                new Triangle(this.points[0], this.points[2], this.points[3]).degenerationCheck() ||
                new Triangle(this.points[1], this.points[2], this.points[3]).degenerationCheck();
    }

    @Override
    public double area() {
        return new Triangle(points[0],points[1],points[2]).area() +
                new Triangle(points[0],points[2],points[3]).area();
    }

    @Override
    public Point centroid()
    {
        double x = 0;
        double y = 0;

        int n = this.points.length;
        double signedArea = 0;

        // For all vertices
        for (int i = 0; i < n; i++)
        {

            double x0 = this.points[i].getX();
            double y0 = this.points[i].getY();
            double x1 = this.points[(i + 1) % n].getX();
            double y1 = this.points[(i + 1) % n].getY();

            // Calculate value of A
            // using shoelace formula
            double A = (x0 * y1) - (x1 * y0);
            signedArea += A;

            // Calculating coordinates of
            // centroid of polygon
            x += (x0 + x1) * A;
            y += (y0 + y1) * A;
        }

        signedArea *= 0.5;
        x = (x) / (6 * signedArea);
        y= (y) / (6 * signedArea);

        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Quadrilateral)) return false;
        return Arrays.equals(this.points, ((Quadrilateral) figure).getPoints());
    }

    @Override
    public String toString() {
        return String.format("Quadrilateral[%s,%s,%s,%s]",
                points[0].toString(),
                points[1].toString(),
                points[2].toString(),
                points[3].toString());
    }

    @Override
    public Point leftMostPoint() {
        return points[0];
    }
}
