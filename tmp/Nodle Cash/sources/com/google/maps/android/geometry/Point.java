package com.google.maps.android.geometry;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class Point {

    /* renamed from: x  reason: collision with root package name */
    public final double f7188x;

    /* renamed from: y  reason: collision with root package name */
    public final double f7189y;

    public Point(double d2, double d3) {
        this.f7188x = d2;
        this.f7189y = d3;
    }

    public String toString() {
        return "Point{x=" + this.f7188x + ", y=" + this.f7189y + AbstractJsonLexerKt.END_OBJ;
    }
}
