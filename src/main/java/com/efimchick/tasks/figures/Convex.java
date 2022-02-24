package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.List;

public interface Convex {

    default int getLeftMostPointIndex(Point[] points) {
        int mostLeftPointIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getX() < points[mostLeftPointIndex].getX()) {
                mostLeftPointIndex = i;
            }
        }
        return mostLeftPointIndex;
    }

    default int orientation(Point p, Point q, Point r)
    {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (DoubleComparator.doubleCompare(val, 0.0d) == 0) return 0;  // collinear
        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    // Jarvis March Algorithm
    default Point[] convexHull(Point[] points)
    {
        int n = points.length;
        List<Point> hull = new ArrayList<>();

        // There must be at least 3 points
        if (n < 3) return points;

        // Initialize Result
        //Vector<Point> hull = new Vector<Point>();

        // Find the leftmost point
        int l = getLeftMostPointIndex(points);
        // Start from leftmost point, keep moving
        // counterclockwise until reach the start point
        // again. This loop runs O(h) times where h is
        // number of points in result or output.
        int p = l, q;
        do
        {
            // Add current point to result
            hull.add(points[p]);

            // Search for a point 'q' such that
            // orientation(p, q, x) is counterclockwise
            // for all points 'x'. The idea is to keep
            // track of last visited most counterclock-
            // wise point in q. If any point 'i' is more
            // counterclock-wise than q, then update q.
            q = (p + 1) % n;

            for (int i = 0; i < n; i++)
            {
                // If i is more counterclockwise than
                // current q, then update q
                if (orientation(points[p], points[i], points[q])
                        == 2)
                    q = i;
            }

            // Now q is the most counterclockwise with
            // respect to p. Set p as q for next iteration,
            // so that q is added to result 'hull'
            p = q;

        } while (p != l);  // While we don't come to first

        Point[] result = new Point[hull.size()];
        for (int i = 0; i < hull.size(); i++) {
            result[i] = hull.get(i);
        }
        return result;
    }

    default boolean isConvex(Point[] vertices) {
        boolean sign = false;
        int n = vertices.length;
        for (int i = 0; i < n; i++) {
            double dx1 = vertices[(i + 2) % n].getX() - vertices[(i + 1) % n].getX();
            double dy1 = vertices[(i + 2) % n].getY() - vertices[(i + 1) % n].getY();
            double dx2 = vertices[i].getX() - vertices[(i + 1) % n].getX();
            double dy2 = vertices[i].getY() - vertices[(i + 1) % n].getY();
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;
            if (i == 0) {
                sign = zcrossproduct > 0;
            } else if (sign != (zcrossproduct > 0)) {
                return false;
            }
        }
        return true;
    }
}