package com.efimchick.tasks.figures;

public class ComparatorsCollection {

    //TODO
    public static int compareByArea(Figure lhs, Figure rhs) {
        return DoubleComparator.doubleCompare(lhs.area(), rhs.area());
    }

    //TODO
    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs) {
        return DoubleComparator.doubleCompare(lhs.leftMostPoint().getX(), rhs.leftMostPoint().getX());
    }

    //TODO
    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs) {
        return DoubleComparator.doubleCompare(lhs.centroid().getX(), rhs.centroid().getX());
    }
}
