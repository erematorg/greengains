package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import androidx.annotation.RequiresApi;
import com.github.mikephil.charting.animation.Easing;

public class ChartAnimator {
    private ValueAnimator.AnimatorUpdateListener mListener;
    protected float mPhaseX = 1.0f;
    protected float mPhaseY = 1.0f;

    public ChartAnimator() {
    }

    @RequiresApi(11)
    private ObjectAnimator xAnimator(int i3, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration((long) i3);
        return ofFloat;
    }

    @RequiresApi(11)
    private ObjectAnimator yAnimator(int i3, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration((long) i3);
        return ofFloat;
    }

    @RequiresApi(11)
    public void animateX(int i3) {
        animateX(i3, Easing.Linear);
    }

    @RequiresApi(11)
    public void animateXY(int i3, int i4) {
        Easing.EasingFunction easingFunction = Easing.Linear;
        animateXY(i3, i4, easingFunction, easingFunction);
    }

    @RequiresApi(11)
    public void animateY(int i3) {
        animateY(i3, Easing.Linear);
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        if (r3 < 0.0f) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPhaseX(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0008
        L_0x0006:
            r3 = r0
            goto L_0x000e
        L_0x0008:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x000e
            goto L_0x0006
        L_0x000e:
            r2.mPhaseX = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.animation.ChartAnimator.setPhaseX(float):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        if (r3 < 0.0f) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPhaseY(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0008
        L_0x0006:
            r3 = r0
            goto L_0x000e
        L_0x0008:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x000e
            goto L_0x0006
        L_0x000e:
            r2.mPhaseY = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.animation.ChartAnimator.setPhaseY(float):void");
    }

    @RequiresApi(11)
    public void animateX(int i3, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i3, easingFunction);
        xAnimator.addUpdateListener(this.mListener);
        xAnimator.start();
    }

    @RequiresApi(11)
    public void animateXY(int i3, int i4, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i3, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i4, easingFunction);
        if (i3 > i4) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }

    @RequiresApi(11)
    public void animateY(int i3, Easing.EasingFunction easingFunction) {
        ObjectAnimator yAnimator = yAnimator(i3, easingFunction);
        yAnimator.addUpdateListener(this.mListener);
        yAnimator.start();
    }

    @RequiresApi(11)
    public ChartAnimator(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mListener = animatorUpdateListener;
    }

    @RequiresApi(11)
    public void animateXY(int i3, int i4, Easing.EasingFunction easingFunction, Easing.EasingFunction easingFunction2) {
        ObjectAnimator xAnimator = xAnimator(i3, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i4, easingFunction2);
        if (i3 > i4) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }
}
