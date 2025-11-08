package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.BinderThread;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.OrientationListener;

final class TouchTracker extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, OrientationListener.Listener {
    static final float MAX_PITCH_DEGREES = 45.0f;
    private final PointF accumulatedTouchOffsetDegrees = new PointF();
    private final GestureDetector gestureDetector;
    private final Listener listener;
    private final PointF previousTouchPointPx = new PointF();
    private final float pxPerDegrees;
    private volatile float roll;

    public interface Listener {
        void onScrollChange(PointF pointF);

        boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    public TouchTracker(Context context, Listener listener2, float f2) {
        this.listener = listener2;
        this.pxPerDegrees = f2;
        this.gestureDetector = new GestureDetector(context, this);
        this.roll = 3.1415927f;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.previousTouchPointPx.set(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    @BinderThread
    public void onOrientationChange(float[] fArr, float f2) {
        this.roll = -f2;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        float x2 = (motionEvent2.getX() - this.previousTouchPointPx.x) / this.pxPerDegrees;
        float y2 = motionEvent2.getY();
        PointF pointF = this.previousTouchPointPx;
        float f4 = (y2 - pointF.y) / this.pxPerDegrees;
        pointF.set(motionEvent2.getX(), motionEvent2.getY());
        double d2 = (double) this.roll;
        float cos = (float) Math.cos(d2);
        float sin = (float) Math.sin(d2);
        PointF pointF2 = this.accumulatedTouchOffsetDegrees;
        pointF2.x -= (cos * x2) - (sin * f4);
        float f5 = (cos * f4) + (sin * x2) + pointF2.y;
        pointF2.y = f5;
        pointF2.y = Math.max(-45.0f, Math.min(MAX_PITCH_DEGREES, f5));
        this.listener.onScrollChange(this.accumulatedTouchOffsetDegrees);
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.listener.onSingleTapUp(motionEvent);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }
}
