package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    @Nullable
    private ShapeData shapeData;

    public RoundedCornersContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, RoundedCorners roundedCorners2) {
        this.lottieDrawable = lottieDrawable2;
        this.name = roundedCorners2.getName();
        BaseKeyframeAnimation<Float, Float> createAnimation = roundedCorners2.getCornerRadius().createAnimation();
        this.roundedCorners = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    private static int floorDiv(int i3, int i4) {
        int i5 = i3 / i4;
        return ((i3 ^ i4) >= 0 || i4 * i5 == i3) ? i5 : i5 - 1;
    }

    private static int floorMod(int i3, int i4) {
        return i3 - (floorDiv(i3, i4) * i4);
    }

    @NonNull
    private ShapeData getShapeData(ShapeData shapeData2) {
        List<CubicCurveData> curves = shapeData2.getCurves();
        boolean isClosed = shapeData2.isClosed();
        int size = curves.size() - 1;
        int i3 = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = curves.get(size);
            CubicCurveData cubicCurveData2 = curves.get(floorMod(size - 1, curves.size()));
            PointF vertex = (size != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData2.getInitialPoint();
            i3 = (!((size != 0 || isClosed) ? cubicCurveData2.getControlPoint2() : vertex).equals(vertex) || !cubicCurveData.getControlPoint1().equals(vertex) || (!shapeData2.isClosed() && (size == 0 || size == curves.size() - 1))) ? i3 + 1 : i3 + 2;
            size--;
        }
        ShapeData shapeData3 = this.shapeData;
        if (shapeData3 == null || shapeData3.getCurves().size() != i3) {
            ArrayList arrayList = new ArrayList(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    public String getName() {
        return this.name;
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009b, code lost:
        if (r6 != (r0.size() - 1)) goto L_0x009e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.content.ShapeData modifyShape(com.airbnb.lottie.model.content.ShapeData r18) {
        /*
            r17 = this;
            java.util.List r0 = r18.getCurves()
            int r1 = r0.size()
            r2 = 2
            if (r1 > r2) goto L_0x000c
            return r18
        L_0x000c:
            r1 = r17
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r2 = r1.roundedCorners
            java.lang.Object r2 = r2.getValue()
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            r3 = 0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0020
            return r18
        L_0x0020:
            com.airbnb.lottie.model.content.ShapeData r1 = r17.getShapeData(r18)
            android.graphics.PointF r3 = r18.getInitialPoint()
            float r3 = r3.x
            android.graphics.PointF r4 = r18.getInitialPoint()
            float r4 = r4.y
            r1.setInitialPoint(r3, r4)
            java.util.List r3 = r1.getCurves()
            boolean r4 = r18.isClosed()
            r6 = 0
            r7 = 0
        L_0x003d:
            int r8 = r0.size()
            if (r6 >= r8) goto L_0x0198
            java.lang.Object r8 = r0.get(r6)
            com.airbnb.lottie.model.CubicCurveData r8 = (com.airbnb.lottie.model.CubicCurveData) r8
            int r9 = r6 + -1
            int r10 = r0.size()
            int r9 = floorMod(r9, r10)
            java.lang.Object r9 = r0.get(r9)
            com.airbnb.lottie.model.CubicCurveData r9 = (com.airbnb.lottie.model.CubicCurveData) r9
            int r10 = r6 + -2
            int r11 = r0.size()
            int r10 = floorMod(r10, r11)
            java.lang.Object r10 = r0.get(r10)
            com.airbnb.lottie.model.CubicCurveData r10 = (com.airbnb.lottie.model.CubicCurveData) r10
            if (r6 != 0) goto L_0x0072
            if (r4 != 0) goto L_0x0072
            android.graphics.PointF r11 = r18.getInitialPoint()
            goto L_0x0076
        L_0x0072:
            android.graphics.PointF r11 = r9.getVertex()
        L_0x0076:
            if (r6 != 0) goto L_0x007c
            if (r4 != 0) goto L_0x007c
            r12 = r11
            goto L_0x0080
        L_0x007c:
            android.graphics.PointF r12 = r9.getControlPoint2()
        L_0x0080:
            android.graphics.PointF r13 = r8.getControlPoint1()
            android.graphics.PointF r10 = r10.getVertex()
            android.graphics.PointF r14 = r8.getVertex()
            boolean r15 = r18.isClosed()
            if (r15 != 0) goto L_0x009e
            r15 = 1
            if (r6 == 0) goto L_0x009f
            int r16 = r0.size()
            int r5 = r16 + -1
            if (r6 != r5) goto L_0x009e
            goto L_0x009f
        L_0x009e:
            r15 = 0
        L_0x009f:
            boolean r5 = r12.equals(r11)
            if (r5 == 0) goto L_0x014b
            boolean r5 = r13.equals(r11)
            if (r5 == 0) goto L_0x014b
            if (r15 != 0) goto L_0x014b
            float r5 = r11.x
            float r8 = r10.x
            float r8 = r5 - r8
            float r9 = r11.y
            float r12 = r10.y
            float r12 = r9 - r12
            float r13 = r14.x
            float r13 = r13 - r5
            float r5 = r14.y
            float r5 = r5 - r9
            double r8 = (double) r8
            r15 = r0
            r16 = r1
            double r0 = (double) r12
            double r0 = java.lang.Math.hypot(r8, r0)
            float r0 = (float) r0
            double r8 = (double) r13
            double r12 = (double) r5
            double r8 = java.lang.Math.hypot(r8, r12)
            float r1 = (float) r8
            float r0 = r2 / r0
            r5 = 1056964608(0x3f000000, float:0.5)
            float r0 = java.lang.Math.min(r0, r5)
            float r1 = r2 / r1
            float r1 = java.lang.Math.min(r1, r5)
            float r5 = r11.x
            float r8 = r10.x
            float r8 = android.support.v4.media.session.a.b(r8, r5, r0, r5)
            float r9 = r11.y
            float r10 = r10.y
            float r0 = android.support.v4.media.session.a.b(r10, r9, r0, r9)
            float r10 = r14.x
            float r10 = android.support.v4.media.session.a.b(r10, r5, r1, r5)
            float r11 = r14.y
            float r1 = android.support.v4.media.session.a.b(r11, r9, r1, r9)
            float r11 = r8 - r5
            r12 = 1057835346(0x3f0d4952, float:0.5519)
            float r11 = r11 * r12
            float r11 = r8 - r11
            float r13 = r0 - r9
            float r13 = r13 * r12
            float r13 = r0 - r13
            float r5 = r10 - r5
            float r5 = r5 * r12
            float r5 = r10 - r5
            float r9 = r1 - r9
            float r9 = r9 * r12
            float r9 = r1 - r9
            int r12 = r7 + -1
            int r14 = r3.size()
            int r12 = floorMod(r12, r14)
            java.lang.Object r12 = r3.get(r12)
            com.airbnb.lottie.model.CubicCurveData r12 = (com.airbnb.lottie.model.CubicCurveData) r12
            java.lang.Object r14 = r3.get(r7)
            com.airbnb.lottie.model.CubicCurveData r14 = (com.airbnb.lottie.model.CubicCurveData) r14
            r12.setControlPoint2(r8, r0)
            r12.setVertex(r8, r0)
            r12 = r16
            if (r6 != 0) goto L_0x0134
            r12.setInitialPoint(r8, r0)
        L_0x0134:
            r14.setControlPoint1(r11, r13)
            int r0 = r7 + 1
            java.lang.Object r0 = r3.get(r0)
            com.airbnb.lottie.model.CubicCurveData r0 = (com.airbnb.lottie.model.CubicCurveData) r0
            r14.setControlPoint2(r5, r9)
            r14.setVertex(r10, r1)
            r0.setControlPoint1(r10, r1)
            int r7 = r7 + 2
            goto L_0x0192
        L_0x014b:
            r15 = r0
            r12 = r1
            int r0 = r7 + -1
            int r1 = r3.size()
            int r0 = floorMod(r0, r1)
            java.lang.Object r0 = r3.get(r0)
            com.airbnb.lottie.model.CubicCurveData r0 = (com.airbnb.lottie.model.CubicCurveData) r0
            java.lang.Object r1 = r3.get(r7)
            com.airbnb.lottie.model.CubicCurveData r1 = (com.airbnb.lottie.model.CubicCurveData) r1
            android.graphics.PointF r5 = r9.getControlPoint2()
            float r5 = r5.x
            android.graphics.PointF r10 = r9.getControlPoint2()
            float r10 = r10.y
            r0.setControlPoint2(r5, r10)
            android.graphics.PointF r5 = r9.getVertex()
            float r5 = r5.x
            android.graphics.PointF r9 = r9.getVertex()
            float r9 = r9.y
            r0.setVertex(r5, r9)
            android.graphics.PointF r0 = r8.getControlPoint1()
            float r0 = r0.x
            android.graphics.PointF r5 = r8.getControlPoint1()
            float r5 = r5.y
            r1.setControlPoint1(r0, r5)
            int r7 = r7 + 1
        L_0x0192:
            int r6 = r6 + 1
            r1 = r12
            r0 = r15
            goto L_0x003d
        L_0x0198:
            r12 = r1
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RoundedCornersContent.modifyShape(com.airbnb.lottie.model.content.ShapeData):com.airbnb.lottie.model.content.ShapeData");
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }
}
