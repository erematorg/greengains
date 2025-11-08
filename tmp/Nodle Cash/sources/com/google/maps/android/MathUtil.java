package com.google.maps.android;

class MathUtil {
    static final double EARTH_RADIUS = 6371009.0d;

    public static double arcHav(double d2) {
        return Math.asin(Math.sqrt(d2)) * 2.0d;
    }

    public static double clamp(double d2, double d3, double d4) {
        return d2 < d3 ? d3 : d2 > d4 ? d4 : d2;
    }

    public static double hav(double d2) {
        double sin = Math.sin(d2 * 0.5d);
        return sin * sin;
    }

    public static double havDistance(double d2, double d3, double d4) {
        double hav = hav(d2 - d3);
        double hav2 = hav(d4);
        return (Math.cos(d3) * Math.cos(d2) * hav2) + hav;
    }

    public static double havFromSin(double d2) {
        double d3 = d2 * d2;
        return (d3 / (Math.sqrt(1.0d - d3) + 1.0d)) * 0.5d;
    }

    public static double inverseMercator(double d2) {
        return (Math.atan(Math.exp(d2)) * 2.0d) - 1.5707963267948966d;
    }

    public static double mercator(double d2) {
        return Math.log(Math.tan((d2 * 0.5d) + 0.7853981633974483d));
    }

    public static double mod(double d2, double d3) {
        return ((d2 % d3) + d3) % d3;
    }

    public static double sinFromHav(double d2) {
        return Math.sqrt((1.0d - d2) * d2) * 2.0d;
    }

    public static double sinSumFromHav(double d2, double d3) {
        double sqrt = Math.sqrt((1.0d - d2) * d2);
        double sqrt2 = Math.sqrt((1.0d - d3) * d3);
        return ((sqrt + sqrt2) - (((sqrt2 * d2) + (sqrt * d3)) * 2.0d)) * 2.0d;
    }

    public static double wrap(double d2, double d3, double d4) {
        return (d2 < d3 || d2 >= d4) ? mod(d2 - d3, d4 - d3) + d3 : d2;
    }
}
