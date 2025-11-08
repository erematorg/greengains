package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    protected static final int DRAG = 1;
    protected static final int NONE = 0;
    protected static final int PINCH_ZOOM = 4;
    protected static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;
    protected static final int X_ZOOM = 2;
    protected static final int Y_ZOOM = 3;
    protected T mChart;
    protected GestureDetector mGestureDetector;
    protected ChartGesture mLastGesture = ChartGesture.NONE;
    protected Highlight mLastHighlighted;
    protected int mTouchMode = 0;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T t2) {
        this.mChart = t2;
        this.mGestureDetector = new GestureDetector(t2.getContext(), this);
    }

    public static float distance(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f3;
        float f7 = f4 - f5;
        return (float) Math.sqrt((double) ((f7 * f7) + (f6 * f6)));
    }

    public void endAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureEnd(motionEvent, this.mLastGesture);
        }
    }

    public ChartGesture getLastGesture() {
        return this.mLastGesture;
    }

    public int getTouchMode() {
        return this.mTouchMode;
    }

    public void performHighlight(Highlight highlight, MotionEvent motionEvent) {
        if (highlight == null || highlight.equalTo(this.mLastHighlighted)) {
            this.mChart.highlightValue((Highlight) null, true);
            this.mLastHighlighted = null;
            return;
        }
        this.mChart.highlightValue(highlight, true);
        this.mLastHighlighted = highlight;
    }

    public void setLastHighlighted(Highlight highlight) {
        this.mLastHighlighted = highlight;
    }

    public void startAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureStart(motionEvent, this.mLastGesture);
        }
    }
}
