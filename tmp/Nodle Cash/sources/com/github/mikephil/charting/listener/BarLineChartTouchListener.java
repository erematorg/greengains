package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private IDataSet mClosestDataSetToTouch;
    private MPPointF mDecelerationCurrentPoint = MPPointF.getInstance(0.0f, 0.0f);
    private long mDecelerationLastTime = 0;
    private MPPointF mDecelerationVelocity = MPPointF.getInstance(0.0f, 0.0f);
    private float mDragTriggerDist;
    private Matrix mMatrix = new Matrix();
    private float mMinScalePointerDistance;
    private float mSavedDist = 1.0f;
    private Matrix mSavedMatrix = new Matrix();
    private float mSavedXDist = 1.0f;
    private float mSavedYDist = 1.0f;
    private MPPointF mTouchPointCenter = MPPointF.getInstance(0.0f, 0.0f);
    private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
    private VelocityTracker mVelocityTracker;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, Matrix matrix, float f2) {
        super(barLineChartBase);
        this.mMatrix = matrix;
        this.mDragTriggerDist = Utils.convertDpToPixel(f2);
        this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5f);
    }

    private static float getXDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    private static float getYDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = r1.mClosestDataSetToTouch;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean inverted() {
        /*
            r1 = this;
            com.github.mikephil.charting.interfaces.datasets.IDataSet r0 = r1.mClosestDataSetToTouch
            if (r0 != 0) goto L_0x000e
            T r0 = r1.mChart
            com.github.mikephil.charting.charts.BarLineChartBase r0 = (com.github.mikephil.charting.charts.BarLineChartBase) r0
            boolean r0 = r0.isAnyAxisInverted()
            if (r0 != 0) goto L_0x0020
        L_0x000e:
            com.github.mikephil.charting.interfaces.datasets.IDataSet r0 = r1.mClosestDataSetToTouch
            if (r0 == 0) goto L_0x0022
            T r1 = r1.mChart
            com.github.mikephil.charting.charts.BarLineChartBase r1 = (com.github.mikephil.charting.charts.BarLineChartBase) r1
            com.github.mikephil.charting.components.YAxis$AxisDependency r0 = r0.getAxisDependency()
            boolean r1 = r1.isInverted(r0)
            if (r1 == 0) goto L_0x0022
        L_0x0020:
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.listener.BarLineChartTouchListener.inverted():boolean");
    }

    private static void midPoint(MPPointF mPPointF, MotionEvent motionEvent) {
        float x2 = motionEvent.getX(0);
        float y2 = motionEvent.getY(0);
        mPPointF.f6574x = (motionEvent.getX(1) + x2) / 2.0f;
        mPPointF.f6575y = (motionEvent.getY(1) + y2) / 2.0f;
    }

    private void performDrag(MotionEvent motionEvent, float f2, float f3) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
        this.mMatrix.set(this.mSavedMatrix);
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (inverted()) {
            if (this.mChart instanceof HorizontalBarChart) {
                f2 = -f2;
            } else {
                f3 = -f3;
            }
        }
        this.mMatrix.postTranslate(f2, f3);
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartTranslate(motionEvent, f2, f3);
        }
    }

    private void performHighlightDrag(MotionEvent motionEvent) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY());
        if (highlightByTouchPoint != null && !highlightByTouchPoint.equalTo(this.mLastHighlighted)) {
            this.mLastHighlighted = highlightByTouchPoint;
            ((BarLineChartBase) this.mChart).highlightValue(highlightByTouchPoint, true);
        }
    }

    private void performZoom(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
            float spacing = spacing(motionEvent);
            if (spacing > this.mMinScalePointerDistance) {
                MPPointF mPPointF = this.mTouchPointCenter;
                MPPointF trans = getTrans(mPPointF.f6574x, mPPointF.f6575y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
                int i3 = this.mTouchMode;
                boolean z2 = false;
                float f2 = 1.0f;
                if (i3 == 4) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
                    float f3 = spacing / this.mSavedDist;
                    if (f3 < 1.0f) {
                        z2 = true;
                    }
                    boolean canZoomOutMoreX = z2 ? viewPortHandler.canZoomOutMoreX() : viewPortHandler.canZoomInMoreX();
                    boolean canZoomOutMoreY = z2 ? viewPortHandler.canZoomOutMoreY() : viewPortHandler.canZoomInMoreY();
                    float f4 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? f3 : 1.0f;
                    if (((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                        f2 = f3;
                    }
                    if (canZoomOutMoreY || canZoomOutMoreX) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(f4, f2, trans.f6574x, trans.f6575y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, f4, f2);
                        }
                    }
                } else if (i3 == 2 && ((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
                    float xDist = getXDist(motionEvent) / this.mSavedXDist;
                    if (xDist < 1.0f ? viewPortHandler.canZoomOutMoreX() : viewPortHandler.canZoomInMoreX()) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(xDist, 1.0f, trans.f6574x, trans.f6575y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, xDist, 1.0f);
                        }
                    }
                } else if (this.mTouchMode == 3 && ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
                    float yDist = getYDist(motionEvent) / this.mSavedYDist;
                    if (yDist < 1.0f) {
                        z2 = true;
                    }
                    if (z2 ? viewPortHandler.canZoomOutMoreY() : viewPortHandler.canZoomInMoreY()) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(1.0f, yDist, trans.f6574x, trans.f6575y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, 1.0f, yDist);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    private void saveTouchStart(MotionEvent motionEvent) {
        this.mSavedMatrix.set(this.mMatrix);
        this.mTouchStartPoint.f6574x = motionEvent.getX();
        this.mTouchStartPoint.f6575y = motionEvent.getY();
        this.mClosestDataSetToTouch = ((BarLineChartBase) this.mChart).getDataSetByTouchPoint(motionEvent.getX(), motionEvent.getY());
    }

    private static float spacing(MotionEvent motionEvent) {
        float x2 = motionEvent.getX(0) - motionEvent.getX(1);
        float y2 = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((y2 * y2) + (x2 * x2)));
    }

    public void computeScroll() {
        MPPointF mPPointF = this.mDecelerationVelocity;
        float f2 = 0.0f;
        if (mPPointF.f6574x != 0.0f || mPPointF.f6575y != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            MPPointF mPPointF2 = this.mDecelerationVelocity;
            mPPointF2.f6574x = ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef() * mPPointF2.f6574x;
            MPPointF mPPointF3 = this.mDecelerationVelocity;
            mPPointF3.f6575y = ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef() * mPPointF3.f6575y;
            float f3 = ((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f;
            MPPointF mPPointF4 = this.mDecelerationVelocity;
            float f4 = mPPointF4.f6574x * f3;
            float f5 = mPPointF4.f6575y * f3;
            MPPointF mPPointF5 = this.mDecelerationCurrentPoint;
            float f6 = mPPointF5.f6574x + f4;
            mPPointF5.f6574x = f6;
            float f7 = mPPointF5.f6575y + f5;
            mPPointF5.f6575y = f7;
            MotionEvent obtain = MotionEvent.obtain(currentAnimationTimeMillis, currentAnimationTimeMillis, 2, f6, f7, 0);
            float f8 = ((BarLineChartBase) this.mChart).isDragXEnabled() ? this.mDecelerationCurrentPoint.f6574x - this.mTouchStartPoint.f6574x : 0.0f;
            if (((BarLineChartBase) this.mChart).isDragYEnabled()) {
                f2 = this.mDecelerationCurrentPoint.f6575y - this.mTouchStartPoint.f6575y;
            }
            performDrag(obtain, f8, f2);
            obtain.recycle();
            this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, false);
            this.mDecelerationLastTime = currentAnimationTimeMillis;
            if (((double) Math.abs(this.mDecelerationVelocity.f6574x)) >= 0.01d || ((double) Math.abs(this.mDecelerationVelocity.f6575y)) >= 0.01d) {
                Utils.postInvalidateOnAnimation(this.mChart);
                return;
            }
            ((BarLineChartBase) this.mChart).calculateOffsets();
            ((BarLineChartBase) this.mChart).postInvalidate();
            stopDeceleration();
        }
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public MPPointF getTrans(float f2, float f3) {
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
        return MPPointF.getInstance(f2 - viewPortHandler.offsetLeft(), inverted() ? -(f3 - viewPortHandler.offsetTop()) : -((((float) ((BarLineChartBase) this.mChart).getMeasuredHeight()) - f3) - viewPortHandler.offsetBottom()));
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.mChart).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(motionEvent.getX(), motionEvent.getY());
            T t2 = this.mChart;
            BarLineChartBase barLineChartBase = (BarLineChartBase) t2;
            float f2 = 1.0f;
            float f3 = ((BarLineChartBase) t2).isScaleXEnabled() ? 1.4f : 1.0f;
            if (((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                f2 = 1.4f;
            }
            barLineChartBase.zoom(f3, f2, trans.f6574x, trans.f6575y);
            if (((BarLineChartBase) this.mChart).isLogEnabled()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.f6574x + ", y: " + trans.f6575y);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.mLastGesture = ChartTouchListener.ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(motionEvent, motionEvent2, f2, f3);
        }
        return super.onFling(motionEvent, motionEvent2, f2, f3);
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (!((BarLineChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return super.onSingleTapUp(motionEvent);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i3 = 3;
        if (motionEvent.getActionMasked() == 3 && (velocityTracker = this.mVelocityTracker) != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.mTouchMode == 0) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if (!((BarLineChartBase) this.mChart).isDragEnabled() && !((BarLineChartBase) this.mChart).isScaleXEnabled() && !((BarLineChartBase) this.mChart).isScaleYEnabled()) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            startAction(motionEvent);
            stopDeceleration();
            saveTouchStart(motionEvent);
        } else if (action == 1) {
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            int pointerId = motionEvent.getPointerId(0);
            velocityTracker2.computeCurrentVelocity(1000, (float) Utils.getMaximumFlingVelocity());
            float yVelocity = velocityTracker2.getYVelocity(pointerId);
            float xVelocity = velocityTracker2.getXVelocity(pointerId);
            if ((Math.abs(xVelocity) > ((float) Utils.getMinimumFlingVelocity()) || Math.abs(yVelocity) > ((float) Utils.getMinimumFlingVelocity())) && this.mTouchMode == 1 && ((BarLineChartBase) this.mChart).isDragDecelerationEnabled()) {
                stopDeceleration();
                this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                this.mDecelerationCurrentPoint.f6574x = motionEvent.getX();
                this.mDecelerationCurrentPoint.f6575y = motionEvent.getY();
                MPPointF mPPointF = this.mDecelerationVelocity;
                mPPointF.f6574x = xVelocity;
                mPPointF.f6575y = yVelocity;
                Utils.postInvalidateOnAnimation(this.mChart);
            }
            int i4 = this.mTouchMode;
            if (i4 == 2 || i4 == 3 || i4 == 4 || i4 == 5) {
                ((BarLineChartBase) this.mChart).calculateOffsets();
                ((BarLineChartBase) this.mChart).postInvalidate();
            }
            this.mTouchMode = 0;
            ((BarLineChartBase) this.mChart).enableScroll();
            VelocityTracker velocityTracker3 = this.mVelocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.mVelocityTracker = null;
            }
            endAction(motionEvent);
        } else if (action == 2) {
            int i5 = this.mTouchMode;
            if (i5 == 1) {
                ((BarLineChartBase) this.mChart).disableScroll();
                float f2 = 0.0f;
                float x2 = ((BarLineChartBase) this.mChart).isDragXEnabled() ? motionEvent.getX() - this.mTouchStartPoint.f6574x : 0.0f;
                if (((BarLineChartBase) this.mChart).isDragYEnabled()) {
                    f2 = motionEvent.getY() - this.mTouchStartPoint.f6575y;
                }
                performDrag(motionEvent, x2, f2);
            } else if (i5 == 2 || i5 == 3 || i5 == 4) {
                ((BarLineChartBase) this.mChart).disableScroll();
                if (((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    performZoom(motionEvent);
                }
            } else if (i5 == 0 && Math.abs(ChartTouchListener.distance(motionEvent.getX(), this.mTouchStartPoint.f6574x, motionEvent.getY(), this.mTouchStartPoint.f6575y)) > this.mDragTriggerDist && ((BarLineChartBase) this.mChart).isDragEnabled()) {
                if (!((BarLineChartBase) this.mChart).isFullyZoomedOut() || !((BarLineChartBase) this.mChart).hasNoDragOffset()) {
                    float abs = Math.abs(motionEvent.getX() - this.mTouchStartPoint.f6574x);
                    float abs2 = Math.abs(motionEvent.getY() - this.mTouchStartPoint.f6575y);
                    if ((((BarLineChartBase) this.mChart).isDragXEnabled() || abs2 >= abs) && (((BarLineChartBase) this.mChart).isDragYEnabled() || abs2 <= abs)) {
                        this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                        this.mTouchMode = 1;
                    }
                } else if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                    if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                        performHighlightDrag(motionEvent);
                    }
                }
            }
        } else if (action == 3) {
            this.mTouchMode = 0;
            endAction(motionEvent);
        } else if (action != 5) {
            if (action == 6) {
                Utils.velocityTrackerPointerUpCleanUpIfNecessary(motionEvent, this.mVelocityTracker);
                this.mTouchMode = 5;
            }
        } else if (motionEvent.getPointerCount() >= 2) {
            ((BarLineChartBase) this.mChart).disableScroll();
            saveTouchStart(motionEvent);
            this.mSavedXDist = getXDist(motionEvent);
            this.mSavedYDist = getYDist(motionEvent);
            float spacing = spacing(motionEvent);
            this.mSavedDist = spacing;
            if (spacing > 10.0f) {
                if (((BarLineChartBase) this.mChart).isPinchZoomEnabled()) {
                    this.mTouchMode = 4;
                } else if (((BarLineChartBase) this.mChart).isScaleXEnabled() != ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    if (((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                        i3 = 2;
                    }
                    this.mTouchMode = i3;
                } else {
                    if (this.mSavedXDist > this.mSavedYDist) {
                        i3 = 2;
                    }
                    this.mTouchMode = i3;
                }
            }
            midPoint(this.mTouchPointCenter, motionEvent);
        }
        this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, true);
        return true;
    }

    public void setDragTriggerDist(float f2) {
        this.mDragTriggerDist = Utils.convertDpToPixel(f2);
    }

    public void stopDeceleration() {
        MPPointF mPPointF = this.mDecelerationVelocity;
        mPPointF.f6574x = 0.0f;
        mPPointF.f6575y = 0.0f;
    }
}
