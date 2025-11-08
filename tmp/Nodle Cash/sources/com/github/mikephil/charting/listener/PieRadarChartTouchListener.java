package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.compose.ui.autofill.a;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;

public class PieRadarChartTouchListener extends ChartTouchListener<PieRadarChartBase<?>> {
    private ArrayList<AngularVelocitySample> _velocitySamples = new ArrayList<>();
    private float mDecelerationAngularVelocity = 0.0f;
    private long mDecelerationLastTime = 0;
    private float mStartAngle = 0.0f;
    private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);

    public class AngularVelocitySample {
        public float angle;
        public long time;

        public AngularVelocitySample(long j2, float f2) {
            this.time = j2;
            this.angle = f2;
        }
    }

    public PieRadarChartTouchListener(PieRadarChartBase<?> pieRadarChartBase) {
        super(pieRadarChartBase);
    }

    private float calculateVelocity() {
        if (this._velocitySamples.isEmpty()) {
            return 0.0f;
        }
        boolean z2 = false;
        AngularVelocitySample angularVelocitySample = this._velocitySamples.get(0);
        AngularVelocitySample angularVelocitySample2 = (AngularVelocitySample) a.h(this._velocitySamples, 1);
        AngularVelocitySample angularVelocitySample3 = angularVelocitySample;
        for (int size = this._velocitySamples.size() - 1; size >= 0; size--) {
            angularVelocitySample3 = this._velocitySamples.get(size);
            if (angularVelocitySample3.angle != angularVelocitySample2.angle) {
                break;
            }
        }
        float f2 = ((float) (angularVelocitySample2.time - angularVelocitySample.time)) / 1000.0f;
        if (f2 == 0.0f) {
            f2 = 0.1f;
        }
        float f3 = angularVelocitySample2.angle;
        float f4 = angularVelocitySample3.angle;
        if (f3 >= f4) {
            z2 = true;
        }
        if (((double) Math.abs(f3 - f4)) > 270.0d) {
            z2 = !z2;
        }
        float f5 = angularVelocitySample2.angle;
        float f6 = angularVelocitySample.angle;
        if (((double) (f5 - f6)) > 180.0d) {
            angularVelocitySample.angle = (float) (((double) f6) + 360.0d);
        } else if (((double) (f6 - f5)) > 180.0d) {
            angularVelocitySample2.angle = (float) (((double) f5) + 360.0d);
        }
        float abs = Math.abs((angularVelocitySample2.angle - angularVelocitySample.angle) / f2);
        return !z2 ? -abs : abs;
    }

    private void resetVelocity() {
        this._velocitySamples.clear();
    }

    private void sampleVelocity(float f2, float f3) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this._velocitySamples.add(new AngularVelocitySample(currentAnimationTimeMillis, ((PieRadarChartBase) this.mChart).getAngleForPoint(f2, f3)));
        for (int size = this._velocitySamples.size(); size - 2 > 0 && currentAnimationTimeMillis - this._velocitySamples.get(0).time > 1000; size--) {
            this._velocitySamples.remove(0);
        }
    }

    public void computeScroll() {
        if (this.mDecelerationAngularVelocity != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mDecelerationAngularVelocity = ((PieRadarChartBase) this.mChart).getDragDecelerationFrictionCoef() * this.mDecelerationAngularVelocity;
            T t2 = this.mChart;
            ((PieRadarChartBase) t2).setRotationAngle((this.mDecelerationAngularVelocity * (((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f)) + ((PieRadarChartBase) t2).getRotationAngle());
            this.mDecelerationLastTime = currentAnimationTimeMillis;
            if (((double) Math.abs(this.mDecelerationAngularVelocity)) >= 0.001d) {
                Utils.postInvalidateOnAnimation(this.mChart);
            } else {
                stopDeceleration();
            }
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (!((PieRadarChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((PieRadarChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return true;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && ((PieRadarChartBase) this.mChart).isRotationEnabled()) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                startAction(motionEvent);
                stopDeceleration();
                resetVelocity();
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    sampleVelocity(x2, y2);
                }
                setGestureStartAngle(x2, y2);
                MPPointF mPPointF = this.mTouchStartPoint;
                mPPointF.f6574x = x2;
                mPPointF.f6575y = y2;
            } else if (action == 1) {
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    stopDeceleration();
                    sampleVelocity(x2, y2);
                    float calculateVelocity = calculateVelocity();
                    this.mDecelerationAngularVelocity = calculateVelocity;
                    if (calculateVelocity != 0.0f) {
                        this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                        Utils.postInvalidateOnAnimation(this.mChart);
                    }
                }
                ((PieRadarChartBase) this.mChart).enableScroll();
                this.mTouchMode = 0;
                endAction(motionEvent);
            } else if (action == 2) {
                if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                    sampleVelocity(x2, y2);
                }
                if (this.mTouchMode == 0) {
                    MPPointF mPPointF2 = this.mTouchStartPoint;
                    if (ChartTouchListener.distance(x2, mPPointF2.f6574x, y2, mPPointF2.f6575y) > Utils.convertDpToPixel(8.0f)) {
                        this.mLastGesture = ChartTouchListener.ChartGesture.ROTATE;
                        this.mTouchMode = 6;
                        ((PieRadarChartBase) this.mChart).disableScroll();
                        endAction(motionEvent);
                    }
                }
                if (this.mTouchMode == 6) {
                    updateGestureRotation(x2, y2);
                    ((PieRadarChartBase) this.mChart).invalidate();
                }
                endAction(motionEvent);
            }
        }
        return true;
    }

    public void setGestureStartAngle(float f2, float f3) {
        this.mStartAngle = ((PieRadarChartBase) this.mChart).getAngleForPoint(f2, f3) - ((PieRadarChartBase) this.mChart).getRawRotationAngle();
    }

    public void stopDeceleration() {
        this.mDecelerationAngularVelocity = 0.0f;
    }

    public void updateGestureRotation(float f2, float f3) {
        T t2 = this.mChart;
        ((PieRadarChartBase) t2).setRotationAngle(((PieRadarChartBase) t2).getAngleForPoint(f2, f3) - this.mStartAngle);
    }
}
