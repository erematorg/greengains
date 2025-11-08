package com.google.maps.android.geometry;

public class Bounds {
    public final double maxX;
    public final double maxY;
    public final double midX;
    public final double midY;
    public final double minX;
    public final double minY;

    public Bounds(double d2, double d3, double d4, double d5) {
        this.minX = d2;
        this.minY = d4;
        this.maxX = d3;
        this.maxY = d5;
        this.midX = (d2 + d3) / 2.0d;
        this.midY = (d4 + d5) / 2.0d;
    }

    public boolean contains(double d2, double d3) {
        return this.minX <= d2 && d2 <= this.maxX && this.minY <= d3 && d3 <= this.maxY;
    }

    public boolean intersects(double d2, double d3, double d4, double d5) {
        return d2 < this.maxX && this.minX < d3 && d4 < this.maxY && this.minY < d5;
    }

    public boolean contains(Point point) {
        return contains(point.f7188x, point.f7189y);
    }

    public boolean intersects(Bounds bounds) {
        return intersects(bounds.minX, bounds.maxX, bounds.minY, bounds.maxY);
    }

    public boolean contains(Bounds bounds) {
        return bounds.minX >= this.minX && bounds.maxX <= this.maxX && bounds.minY >= this.minY && bounds.maxY <= this.maxY;
    }
}
