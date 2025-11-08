package com.google.zxing;

import android.support.v4.media.session.a;
import com.google.zxing.common.detector.MathUtils;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class ResultPoint {

    /* renamed from: x  reason: collision with root package name */
    private final float f7190x;

    /* renamed from: y  reason: collision with root package name */
    private final float f7191y;

    public ResultPoint(float f2, float f3) {
        this.f7190x = f2;
        this.f7191y = f3;
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f2 = resultPoint2.f7190x;
        float f3 = resultPoint2.f7191y;
        return ((resultPoint.f7191y - f3) * (resultPoint3.f7190x - f2)) - ((resultPoint.f7190x - f2) * (resultPoint3.f7191y - f3));
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f7190x, resultPoint.f7191y, resultPoint2.f7190x, resultPoint2.f7191y);
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint3 = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint3 = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[1];
        } else {
            resultPoint3 = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint3, resultPoint) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint;
            resultPoint = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint3;
        resultPointArr[2] = resultPoint;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        return this.f7190x == resultPoint.f7190x && this.f7191y == resultPoint.f7191y;
    }

    public final float getX() {
        return this.f7190x;
    }

    public final float getY() {
        return this.f7191y;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f7191y) + (Float.floatToIntBits(this.f7190x) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.f7190x);
        sb.append(AbstractJsonLexerKt.COMMA);
        return a.o(sb, this.f7191y, ')');
    }
}
