package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public abstract class AnimatedViewPortJob extends ViewPortJob implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    protected ObjectAnimator animator;
    protected float phase;
    protected float xOrigin;
    protected float yOrigin;

    public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float f2, float f3, Transformer transformer, View view, float f4, float f5, long j2) {
        super(viewPortHandler, f2, f3, transformer, view);
        this.xOrigin = f4;
        this.yOrigin = f5;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, TypedValues.CycleType.S_WAVE_PHASE, new float[]{0.0f, 1.0f});
        this.animator = ofFloat;
        ofFloat.setDuration(j2);
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    public float getPhase() {
        return this.phase;
    }

    public float getXOrigin() {
        return this.xOrigin;
    }

    public float getYOrigin() {
        return this.yOrigin;
    }

    public void onAnimationCancel(Animator animator2) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    public void onAnimationEnd(Animator animator2) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    public void onAnimationRepeat(Animator animator2) {
    }

    public void onAnimationStart(Animator animator2) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
    }

    public abstract void recycleSelf();

    public void resetAnimator() {
        this.animator.removeAllListeners();
        this.animator.removeAllUpdateListeners();
        this.animator.reverse();
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    @SuppressLint({"NewApi"})
    public void run() {
        this.animator.start();
    }

    public void setPhase(float f2) {
        this.phase = f2;
    }
}
