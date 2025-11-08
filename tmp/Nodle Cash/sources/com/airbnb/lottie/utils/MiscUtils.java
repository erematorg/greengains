package com.airbnb.lottie.utils;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.media.session.a;
import androidx.annotation.FloatRange;
import com.airbnb.lottie.animation.content.KeyPathElementContent;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

public class MiscUtils {
    private static final PointF pathFromDataCurrentPoint = new PointF();

    public static PointF addPoints(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static int clamp(int i3, int i4, int i5) {
        return Math.max(i4, Math.min(i5, i3));
    }

    public static boolean contains(float f2, float f3, float f4) {
        return f2 >= f3 && f2 <= f4;
    }

    private static int floorDiv(int i3, int i4) {
        int i5 = i3 / i4;
        return (((i3 ^ i4) >= 0) || i3 % i4 == 0) ? i5 : i5 - 1;
    }

    public static int floorMod(float f2, float f3) {
        return floorMod((int) f2, (int) f3);
    }

    public static void getPathFromData(ShapeData shapeData, Path path) {
        path.reset();
        PointF initialPoint = shapeData.getInitialPoint();
        path.moveTo(initialPoint.x, initialPoint.y);
        pathFromDataCurrentPoint.set(initialPoint.x, initialPoint.y);
        for (int i3 = 0; i3 < shapeData.getCurves().size(); i3++) {
            CubicCurveData cubicCurveData = shapeData.getCurves().get(i3);
            PointF controlPoint1 = cubicCurveData.getControlPoint1();
            PointF controlPoint2 = cubicCurveData.getControlPoint2();
            PointF vertex = cubicCurveData.getVertex();
            PointF pointF = pathFromDataCurrentPoint;
            if (!controlPoint1.equals(pointF) || !controlPoint2.equals(vertex)) {
                path.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, vertex.x, vertex.y);
            } else {
                path.lineTo(vertex.x, vertex.y);
            }
            pointF.set(vertex.x, vertex.y);
        }
        if (shapeData.isClosed()) {
            path.close();
        }
    }

    public static int lerp(int i3, int i4, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return (int) ((f2 * ((float) (i4 - i3))) + ((float) i3));
    }

    public static void resolveKeyPath(KeyPath keyPath, int i3, List<KeyPath> list, KeyPath keyPath2, KeyPathElementContent keyPathElementContent) {
        if (keyPath.fullyResolvesTo(keyPathElementContent.getName(), i3)) {
            list.add(keyPath2.addKey(keyPathElementContent.getName()).resolve(keyPathElementContent));
        }
    }

    public static float clamp(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f4, f2));
    }

    private static int floorMod(int i3, int i4) {
        return i3 - (i4 * floorDiv(i3, i4));
    }

    public static float lerp(float f2, float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4) {
        return a.b(f3, f2, f4, f2);
    }

    public static double clamp(double d2, double d3, double d4) {
        return Math.max(d3, Math.min(d4, d2));
    }

    public static double lerp(double d2, double d3, @FloatRange(from = 0.0d, to = 1.0d) double d4) {
        return androidx.compose.ui.autofill.a.a(d3, d2, d4, d2);
    }
}
