package com.google.maps.android;

import androidx.compose.ui.autofill.a;
import com.google.android.gms.maps.model.LatLng;
import java.util.Iterator;
import java.util.List;

public class SphericalUtil {
    private SphericalUtil() {
    }

    public static double computeAngleBetween(LatLng latLng, LatLng latLng2) {
        return distanceRadians(Math.toRadians(latLng.latitude), Math.toRadians(latLng.longitude), Math.toRadians(latLng2.latitude), Math.toRadians(latLng2.longitude));
    }

    public static double computeArea(List<LatLng> list) {
        return Math.abs(computeSignedArea(list));
    }

    public static double computeDistanceBetween(LatLng latLng, LatLng latLng2) {
        return computeAngleBetween(latLng, latLng2) * 6371009.0d;
    }

    public static double computeHeading(LatLng latLng, LatLng latLng2) {
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude) - radians2;
        double cos = Math.cos(radians3) * Math.sin(radians4);
        double cos2 = Math.cos(radians);
        double sin = Math.sin(radians);
        return MathUtil.wrap(Math.toDegrees(Math.atan2(cos, (Math.sin(radians3) * cos2) - (Math.cos(radians4) * (Math.cos(radians3) * sin)))), -180.0d, 180.0d);
    }

    public static double computeLength(List<LatLng> list) {
        double d2 = 0.0d;
        if (list.size() < 2) {
            return 0.0d;
        }
        LatLng latLng = list.get(0);
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        Iterator<LatLng> it = list.iterator();
        while (true) {
            double d3 = radians2;
            double d4 = radians;
            if (!it.hasNext()) {
                return d2 * 6371009.0d;
            }
            LatLng next = it.next();
            radians = Math.toRadians(next.latitude);
            radians2 = Math.toRadians(next.longitude);
            d2 += distanceRadians(d4, d3, radians, radians2);
        }
    }

    public static LatLng computeOffset(LatLng latLng, double d2, double d3) {
        double d4 = d2 / 6371009.0d;
        double radians = Math.toRadians(d3);
        double radians2 = Math.toRadians(latLng.latitude);
        double radians3 = Math.toRadians(latLng.longitude);
        double cos = Math.cos(d4);
        double sin = Math.sin(d4);
        double sin2 = Math.sin(radians2);
        double cos2 = sin * Math.cos(radians2);
        double cos3 = (Math.cos(radians) * cos2) + (cos * sin2);
        return new LatLng(Math.toDegrees(Math.asin(cos3)), Math.toDegrees(radians3 + Math.atan2(Math.sin(radians) * cos2, cos - (sin2 * cos3))));
    }

    public static LatLng computeOffsetOrigin(LatLng latLng, double d2, double d3) {
        LatLng latLng2 = latLng;
        double radians = Math.toRadians(d3);
        double d4 = d2 / 6371009.0d;
        double cos = Math.cos(d4);
        double cos2 = Math.cos(radians) * Math.sin(d4);
        double sin = Math.sin(radians) * Math.sin(d4);
        double sin2 = Math.sin(Math.toRadians(latLng2.latitude));
        double d5 = cos * cos;
        double d6 = cos2 * cos2;
        double d7 = ((d5 * d5) + (d6 * d5)) - ((d5 * sin2) * sin2);
        if (d7 < 0.0d) {
            return null;
        }
        double d8 = cos2 * sin2;
        double d9 = d5 + d6;
        double sqrt = (Math.sqrt(d7) + d8) / d9;
        double d10 = (sin2 - (cos2 * sqrt)) / cos;
        double atan2 = Math.atan2(d10, sqrt);
        if (atan2 < -1.5707963267948966d || atan2 > 1.5707963267948966d) {
            atan2 = Math.atan2(d10, (d8 - Math.sqrt(d7)) / d9);
        }
        if (atan2 < -1.5707963267948966d || atan2 > 1.5707963267948966d) {
            return null;
        }
        return new LatLng(Math.toDegrees(atan2), Math.toDegrees(Math.toRadians(latLng2.longitude) - Math.atan2(sin, (Math.cos(atan2) * cos) - (Math.sin(atan2) * cos2))));
    }

    public static double computeSignedArea(List<LatLng> list) {
        return computeSignedArea(list, 6371009.0d);
    }

    private static double distanceRadians(double d2, double d3, double d4, double d5) {
        return MathUtil.arcHav(MathUtil.havDistance(d2, d4, d3 - d5));
    }

    public static LatLng interpolate(LatLng latLng, LatLng latLng2, double d2) {
        LatLng latLng3 = latLng;
        LatLng latLng4 = latLng2;
        double radians = Math.toRadians(latLng3.latitude);
        double radians2 = Math.toRadians(latLng3.longitude);
        double radians3 = Math.toRadians(latLng4.latitude);
        double radians4 = Math.toRadians(latLng4.longitude);
        double cos = Math.cos(radians);
        double cos2 = Math.cos(radians3);
        double computeAngleBetween = computeAngleBetween(latLng, latLng2);
        double sin = Math.sin(computeAngleBetween);
        if (sin < 1.0E-6d) {
            double d3 = latLng3.latitude;
            double a2 = a.a(latLng4.latitude, d3, d2, d3);
            double d4 = latLng3.longitude;
            return new LatLng(a2, a.a(latLng4.longitude, d4, d2, d4));
        }
        double sin2 = Math.sin((1.0d - d2) * computeAngleBetween) / sin;
        double sin3 = Math.sin(computeAngleBetween * d2) / sin;
        double d5 = cos * sin2;
        double d6 = cos2 * sin3;
        double cos3 = Math.cos(radians4) * d6;
        double sin4 = Math.sin(radians2) * d5;
        double cos4 = cos3 + (Math.cos(radians2) * d5);
        double a3 = android.support.v4.media.session.a.a(radians4, d6, sin4);
        return new LatLng(Math.toDegrees(Math.atan2(android.support.v4.media.session.a.a(radians3, sin3, Math.sin(radians) * sin2), Math.sqrt((a3 * a3) + (cos4 * cos4)))), Math.toDegrees(Math.atan2(a3, cos4)));
    }

    private static double polarTriangleArea(double d2, double d3, double d4, double d5) {
        double d6 = d3 - d5;
        double d7 = d2 * d4;
        return Math.atan2(Math.sin(d6) * d7, (Math.cos(d6) * d7) + 1.0d) * 2.0d;
    }

    public static double computeSignedArea(List<LatLng> list, double d2) {
        int size = list.size();
        double d3 = 0.0d;
        if (size < 3) {
            return 0.0d;
        }
        LatLng latLng = list.get(size - 1);
        double tan = Math.tan((1.5707963267948966d - Math.toRadians(latLng.latitude)) / 2.0d);
        double radians = Math.toRadians(latLng.longitude);
        double d4 = tan;
        double d5 = radians;
        for (LatLng next : list) {
            double tan2 = Math.tan((1.5707963267948966d - Math.toRadians(next.latitude)) / 2.0d);
            double radians2 = Math.toRadians(next.longitude);
            d3 += polarTriangleArea(tan2, radians2, d4, d5);
            d4 = tan2;
            d5 = radians2;
        }
        return d2 * d2 * d3;
    }
}
