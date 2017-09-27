package com.java.lab5;

import java.util.Objects;

/**
 * Created by apavliuchenkova on 09/06/2017.
 */
public class Point {
    private int x;
    private int y;

    public Point() {

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object object) {

        return ((this.x == ((Point) object).x) && this.y == ((Point) object).y);
    }

    @Override
    public int hashCode() {
        return x+y;
    }
}
