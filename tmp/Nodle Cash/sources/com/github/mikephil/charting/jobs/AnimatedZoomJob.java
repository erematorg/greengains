package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.support.v4.media.session.a;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public class AnimatedZoomJob extends AnimatedViewPortJob implements Animator.AnimatorListener {
    private static ObjectPool<AnimatedZoomJob> pool;
    protected Matrix mOnAnimationUpdateMatrixBuffer = new Matrix();
    protected float xAxisRange;
    protected YAxis yAxis;
    protected float zoomCenterX;
    protected float zoomCenterY;
    protected float zoomOriginX;
    protected float zoomOriginY;

    static {
        AnimatedZoomJob animatedZoomJob = r0;
        AnimatedZoomJob animatedZoomJob2 = new AnimatedZoomJob((ViewPortHandler) null, (View) null, (Transformer) null, (YAxis) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
        pool = ObjectPool.create(8, animatedZoomJob);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @SuppressLint({"NewApi"})
    public AnimatedZoomJob(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j2) {
        super(viewPortHandler, f3, f4, transformer, view, f5, f6, j2);
        this.zoomCenterX = f7;
        this.zoomCenterY = f8;
        this.zoomOriginX = f9;
        this.zoomOriginY = f10;
        this.animator.addListener(this);
        this.yAxis = yAxis2;
        this.xAxisRange = f2;
    }

    public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j2) {
        AnimatedZoomJob animatedZoomJob = pool.get();
        animatedZoomJob.mViewPortHandler = viewPortHandler;
        animatedZoomJob.xValue = f3;
        animatedZoomJob.yValue = f4;
        animatedZoomJob.mTrans = transformer;
        animatedZoomJob.view = view;
        animatedZoomJob.xOrigin = f5;
        animatedZoomJob.yOrigin = f6;
        animatedZoomJob.yAxis = yAxis2;
        animatedZoomJob.xAxisRange = f2;
        animatedZoomJob.resetAnimator();
        animatedZoomJob.animator.setDuration(j2);
        return animatedZoomJob;
    }

    public ObjectPool.Poolable instantiate() {
        return new AnimatedZoomJob((ViewPortHandler) null, (View) null, (Transformer) null, (YAxis) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f2 = this.xOrigin;
        float f3 = this.phase;
        float f4 = ((this.xValue - f2) * f3) + f2;
        float f5 = this.yOrigin;
        float b3 = a.b(this.yValue, f5, f3, f5);
        Matrix matrix = this.mOnAnimationUpdateMatrixBuffer;
        this.mViewPortHandler.setZoom(f4, b3, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        float scaleY = this.yAxis.mAxisRange / this.mViewPortHandler.getScaleY();
        float scaleX = this.xAxisRange / this.mViewPortHandler.getScaleX();
        float[] fArr = this.pts;
        float f6 = this.zoomOriginX;
        float f7 = this.phase;
        fArr[0] = (((this.zoomCenterX - (scaleX / 2.0f)) - f6) * f7) + f6;
        float f8 = this.zoomOriginY;
        fArr[1] = ((((scaleY / 2.0f) + this.zoomCenterY) - f8) * f7) + f8;
        this.mTrans.pointValuesToPixel(fArr);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, true);
    }

    public void recycleSelf() {
    }
}
