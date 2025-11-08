package com.google.maps.android;

import androidx.compose.ui.autofill.a;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PolyUtil {
    public static final double DEFAULT_TOLERANCE = 0.1d;

    private PolyUtil() {
    }

    public static boolean containsLocation(LatLng latLng, List<LatLng> list, boolean z2) {
        return containsLocation(latLng.latitude, latLng.longitude, list, z2);
    }

    public static List<LatLng> decode(String str) {
        int i3;
        int i4;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < length) {
            int i8 = 1;
            int i9 = 0;
            int i10 = 1;
            while (true) {
                i3 = i5 + 1;
                int charAt = str.charAt(i5) - '@';
                i10 += charAt << i9;
                i9 += 5;
                if (charAt < 31) {
                    break;
                }
                i5 = i3;
            }
            int i11 = ((i10 & 1) != 0 ? ~(i10 >> 1) : i10 >> 1) + i6;
            int i12 = 0;
            while (true) {
                i4 = i3 + 1;
                int charAt2 = str.charAt(i3) - '@';
                i8 += charAt2 << i12;
                i12 += 5;
                if (charAt2 < 31) {
                    break;
                }
                i3 = i4;
            }
            i7 += (i8 & 1) != 0 ? ~(i8 >> 1) : i8 >> 1;
            arrayList.add(new LatLng(((double) i11) * 1.0E-5d, ((double) i7) * 1.0E-5d));
            i6 = i11;
            i5 = i4;
        }
        return arrayList;
    }

    public static double distanceToLine(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        LatLng latLng4 = latLng;
        LatLng latLng5 = latLng2;
        LatLng latLng6 = latLng3;
        if (latLng2.equals(latLng3)) {
            return SphericalUtil.computeDistanceBetween(latLng6, latLng4);
        }
        double radians = Math.toRadians(latLng4.latitude);
        double radians2 = Math.toRadians(latLng4.longitude);
        double radians3 = Math.toRadians(latLng5.latitude);
        double radians4 = Math.toRadians(latLng5.longitude);
        double radians5 = Math.toRadians(latLng6.latitude) - radians3;
        double radians6 = Math.toRadians(latLng6.longitude) - radians4;
        double a2 = a.a(radians2, radians4, radians6, (radians - radians3) * radians5) / ((radians6 * radians6) + (radians5 * radians5));
        if (a2 <= 0.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng2);
        }
        if (a2 >= 1.0d) {
            return SphericalUtil.computeDistanceBetween(latLng4, latLng6);
        }
        double d2 = latLng5.latitude;
        double d3 = a2;
        double a3 = a.a(latLng6.latitude, d2, d3, d2);
        double d4 = latLng5.longitude;
        return SphericalUtil.computeDistanceBetween(latLng4, new LatLng(a3, a.a(latLng6.longitude, d4, d3, d4)));
    }

    public static String encode(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = 0;
        long j3 = 0;
        for (LatLng next : list) {
            long round = Math.round(next.latitude * 100000.0d);
            long round2 = Math.round(next.longitude * 100000.0d);
            encode(round - j2, stringBuffer);
            encode(round2 - j3, stringBuffer);
            j2 = round;
            j3 = round2;
        }
        return stringBuffer.toString();
    }

    private static boolean intersects(double d2, double d3, double d4, double d5, double d6, boolean z2) {
        if ((d6 >= 0.0d && d6 >= d4) || ((d6 < 0.0d && d6 < d4) || d5 <= -1.5707963267948966d || d2 <= -1.5707963267948966d || d3 <= -1.5707963267948966d || d2 >= 1.5707963267948966d || d3 >= 1.5707963267948966d || d4 <= -3.141592653589793d)) {
            return false;
        }
        double d7 = ((d3 * d6) + ((d4 - d6) * d2)) / d4;
        if (d2 >= 0.0d && d3 >= 0.0d && d5 < d7) {
            return false;
        }
        if ((d2 <= 0.0d && d3 <= 0.0d && d5 >= d7) || d5 >= 1.5707963267948966d) {
            return true;
        }
        if (z2) {
            if (Math.tan(d5) < tanLatGC(d2, d3, d4, d6)) {
                return false;
            }
        } else if (MathUtil.mercator(d5) < mercatorLatRhumb(d2, d3, d4, d6)) {
            return false;
        }
        return true;
    }

    public static boolean isClosedPolygon(List<LatLng> list) {
        return list.get(0).equals((LatLng) android.support.v4.media.session.a.h(list, 1));
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z2, double d2) {
        return isLocationOnEdgeOrPath(latLng, list, true, z2, d2);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z2, boolean z3, double d2) {
        return locationIndexOnEdgeOrPath(latLng, list, z2, z3, d2) >= 0;
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z2, double d2) {
        return isLocationOnEdgeOrPath(latLng, list, false, z2, d2);
    }

    private static boolean isOnSegmentGC(double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double havDistance = MathUtil.havDistance(d2, d6, d3 - d7);
        if (havDistance <= d8) {
            return true;
        }
        double havDistance2 = MathUtil.havDistance(d4, d6, d5 - d7);
        if (havDistance2 <= d8) {
            return true;
        }
        double havFromSin = MathUtil.havFromSin(MathUtil.sinFromHav(havDistance) * sinDeltaBearing(d2, d3, d4, d5, d6, d7));
        if (havFromSin > d8) {
            return false;
        }
        double havDistance3 = MathUtil.havDistance(d2, d4, d3 - d5);
        double d9 = ((1.0d - (havDistance3 * 2.0d)) * havFromSin) + havDistance3;
        if (havDistance > d9 || havDistance2 > d9) {
            return false;
        }
        if (havDistance3 < 0.74d) {
            return true;
        }
        double d10 = 1.0d - (2.0d * havFromSin);
        return MathUtil.sinSumFromHav((havDistance - havFromSin) / d10, (havDistance2 - havFromSin) / d10) > 0.0d;
    }

    public static int locationIndexOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z2, boolean z3, double d2) {
        int i3;
        List<LatLng> list2;
        Iterator<LatLng> it;
        LatLng latLng2 = latLng;
        int size = list.size();
        int i4 = -1;
        if (size == 0) {
            return -1;
        }
        double d3 = d2 / 6371009.0d;
        double hav = MathUtil.hav(d3);
        double radians = Math.toRadians(latLng2.latitude);
        double radians2 = Math.toRadians(latLng2.longitude);
        if (z2) {
            i3 = size - 1;
            list2 = list;
        } else {
            list2 = list;
            i3 = 0;
        }
        LatLng latLng3 = list2.get(i3);
        double radians3 = Math.toRadians(latLng3.latitude);
        double radians4 = Math.toRadians(latLng3.longitude);
        if (z3) {
            int i5 = 0;
            for (LatLng next : list) {
                double radians5 = Math.toRadians(next.latitude);
                double radians6 = Math.toRadians(next.longitude);
                if (isOnSegmentGC(radians3, radians4, radians5, radians6, radians, radians2, hav)) {
                    return Math.max(0, i5 - 1);
                }
                i5++;
                radians3 = radians5;
                radians4 = radians6;
            }
        } else {
            double d4 = radians - d3;
            double d5 = radians + d3;
            double mercator = MathUtil.mercator(radians3);
            double mercator2 = MathUtil.mercator(radians);
            int i6 = 0;
            for (Iterator<LatLng> it2 = list.iterator(); it2.hasNext(); it2 = it) {
                LatLng next2 = it2.next();
                int i7 = i6;
                double radians7 = Math.toRadians(next2.latitude);
                double mercator3 = MathUtil.mercator(radians7);
                double radians8 = Math.toRadians(next2.longitude);
                if (Math.max(radians3, radians7) < d4 || Math.min(radians3, radians7) > d5) {
                    it = it2;
                } else {
                    double wrap = MathUtil.wrap(radians8 - radians4, -3.141592653589793d, 3.141592653589793d);
                    double wrap2 = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
                    it = it2;
                    double[] dArr = {wrap2, wrap2 + 6.283185307179586d, wrap2 - 6.283185307179586d};
                    for (int i8 = 0; i8 < 3; i8++) {
                        double d6 = dArr[i8];
                        double d7 = mercator3 - mercator;
                        double d8 = (d7 * d7) + (wrap * wrap);
                        double d9 = 0.0d;
                        if (d8 > 0.0d) {
                            d9 = MathUtil.clamp((((mercator2 - mercator) * d7) + (d6 * wrap)) / d8, 0.0d, 1.0d);
                        }
                        if (MathUtil.havDistance(radians, MathUtil.inverseMercator((d9 * d7) + mercator), d6 - (d9 * wrap)) < hav) {
                            return Math.max(0, i7 - 1);
                        }
                    }
                }
                radians3 = radians7;
                mercator = mercator3;
                i4 = -1;
                i6 = i7 + 1;
                radians4 = radians8;
            }
        }
        return i4;
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z2, double d2) {
        return locationIndexOnEdgeOrPath(latLng, list, false, z2, d2);
    }

    private static double mercatorLatRhumb(double d2, double d3, double d4, double d5) {
        return ((MathUtil.mercator(d3) * d5) + ((d4 - d5) * MathUtil.mercator(d2))) / d4;
    }

    public static List<LatLng> simplify(List<LatLng> list, double d2) {
        LatLng latLng;
        List<LatLng> list2 = list;
        int size = list.size();
        if (size >= 1) {
            double d3 = 0.0d;
            if (d2 > 0.0d) {
                boolean isClosedPolygon = isClosedPolygon(list);
                if (isClosedPolygon) {
                    latLng = (LatLng) android.support.v4.media.session.a.h(list2, 1);
                    list2.remove(list.size() - 1);
                    list2.add(new LatLng(latLng.latitude + 1.0E-11d, latLng.longitude + 1.0E-11d));
                } else {
                    latLng = null;
                }
                Stack stack = new Stack();
                double[] dArr = new double[size];
                int i3 = 0;
                dArr[0] = 1.0d;
                int i4 = size - 1;
                dArr[i4] = 1.0d;
                if (size > 2) {
                    stack.push(new int[]{0, i4});
                    int i5 = 0;
                    while (stack.size() > 0) {
                        int[] iArr = (int[]) stack.pop();
                        double d4 = d3;
                        for (int i6 = iArr[0] + 1; i6 < iArr[1]; i6++) {
                            double distanceToLine = distanceToLine(list2.get(i6), list2.get(iArr[0]), list2.get(iArr[1]));
                            if (distanceToLine > d4) {
                                d4 = distanceToLine;
                                i5 = i6;
                            }
                        }
                        if (d4 > d2) {
                            dArr[i5] = d4;
                            stack.push(new int[]{iArr[0], i5});
                            stack.push(new int[]{i5, iArr[1]});
                        }
                        d3 = 0.0d;
                    }
                }
                if (isClosedPolygon) {
                    list2.remove(list.size() - 1);
                    list2.add(latLng);
                }
                ArrayList arrayList = new ArrayList();
                for (LatLng next : list) {
                    if (dArr[i3] != 0.0d) {
                        arrayList.add(next);
                    }
                    i3++;
                }
                return arrayList;
            }
            throw new IllegalArgumentException("Tolerance must be greater than zero");
        }
        throw new IllegalArgumentException("Polyline must have at least 1 point");
    }

    private static double sinDeltaBearing(double d2, double d3, double d4, double d5, double d6, double d7) {
        double sin = Math.sin(d2);
        double cos = Math.cos(d4);
        double cos2 = Math.cos(d6);
        double d8 = d7 - d3;
        double d9 = d5 - d3;
        double sin2 = Math.sin(d8) * cos2;
        double sin3 = Math.sin(d9) * cos;
        double d10 = sin * 2.0d;
        double hav = (cos2 * d10 * MathUtil.hav(d8)) + Math.sin(d6 - d2);
        double hav2 = (d10 * cos * MathUtil.hav(d9)) + Math.sin(d4 - d2);
        double d11 = ((hav2 * hav2) + (sin3 * sin3)) * ((hav * hav) + (sin2 * sin2));
        if (d11 <= 0.0d) {
            return 1.0d;
        }
        return ((sin2 * hav2) - (hav * sin3)) / Math.sqrt(d11);
    }

    private static double tanLatGC(double d2, double d3, double d4, double d5) {
        double tan = Math.tan(d2);
        return android.support.v4.media.session.a.a(d5, Math.tan(d3), Math.sin(d4 - d5) * tan) / Math.sin(d4);
    }

    public static boolean containsLocation(double d2, double d3, List<LatLng> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double radians = Math.toRadians(d2);
        double radians2 = Math.toRadians(d3);
        LatLng latLng = list.get(size - 1);
        double radians3 = Math.toRadians(latLng.latitude);
        double radians4 = Math.toRadians(latLng.longitude);
        int i3 = 0;
        double d4 = radians3;
        for (LatLng next : list) {
            double wrap = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
            if (radians == d4 && wrap == 0.0d) {
                return true;
            }
            double radians5 = Math.toRadians(next.latitude);
            double radians6 = Math.toRadians(next.longitude);
            if (intersects(d4, radians5, MathUtil.wrap(radians6 - radians4, -3.141592653589793d, 3.141592653589793d), radians, wrap, z2)) {
                i3++;
            }
            d4 = radians5;
            radians4 = radians6;
        }
        if ((i3 & 1) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z2) {
        return isLocationOnEdge(latLng, list, z2, 0.1d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z2) {
        return isLocationOnPath(latLng, list, z2, 0.1d);
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z2) {
        return locationIndexOnPath(latLng, list, z2, 0.1d);
    }

    private static void encode(long j2, StringBuffer stringBuffer) {
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        long j3 = j2 << 1;
        if (i3 < 0) {
            j3 = ~j3;
        }
        while (j3 >= 32) {
            stringBuffer.append(Character.toChars((int) ((32 | (31 & j3)) + 63)));
            j3 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j3 + 63)));
    }
}
