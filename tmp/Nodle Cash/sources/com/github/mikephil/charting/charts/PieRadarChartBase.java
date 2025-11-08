package com.github.mikephil.charting.charts;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>> extends Chart<T> {
    protected float mMinOffset = 0.0f;
    private float mRawRotationAngle = 270.0f;
    protected boolean mRotateEnabled = true;
    private float mRotationAngle = 270.0f;

    /* renamed from: com.github.mikephil.charting.charts.PieRadarChartBase$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(2:13|14)|15|17|18|19|20|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
        static {
            /*
                com.github.mikephil.charting.components.Legend$LegendOrientation[] r0 = com.github.mikephil.charting.components.Legend.LegendOrientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = r0
                r1 = 1
                com.github.mikephil.charting.components.Legend$LegendOrientation r2 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation     // Catch:{ NoSuchFieldError -> 0x001d }
                com.github.mikephil.charting.components.Legend$LegendOrientation r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment[] r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment = r2
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r2 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment[] r2 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment = r2
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r1 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment     // Catch:{ NoSuchFieldError -> 0x005e }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r2 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x005e }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.charts.PieRadarChartBase.AnonymousClass2.<clinit>():void");
        }
    }

    public PieRadarChartBase(Context context) {
        super(context);
    }

    public void calcMinMax() {
    }

    public void calculateOffsets() {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        Legend legend = this.mLegend;
        float f12 = 0.0f;
        if (legend == null || !legend.isEnabled() || this.mLegend.isDrawInsideEnabled()) {
            f4 = 0.0f;
            f3 = 0.0f;
            f2 = 0.0f;
        } else {
            float min = Math.min(this.mLegend.mNeededWidth, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartWidth());
            int i3 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[this.mLegend.getOrientation().ordinal()];
            if (i3 != 1) {
                if (i3 == 2 && (this.mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.TOP || this.mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.BOTTOM)) {
                    f7 = Math.min(this.mLegend.mNeededHeight + getRequiredLegendOffset(), this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight());
                    int i4 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
                    if (i4 == 1) {
                        f6 = 0.0f;
                        f5 = f6;
                    } else if (i4 == 2) {
                        f5 = f7;
                        f7 = 0.0f;
                        f6 = 0.0f;
                    }
                }
                f7 = 0.0f;
                f6 = 0.0f;
                f5 = f6;
            } else {
                if (this.mLegend.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.LEFT && this.mLegend.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.RIGHT) {
                    f8 = 0.0f;
                } else if (this.mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.CENTER) {
                    f8 = Utils.convertDpToPixel(13.0f) + min;
                } else {
                    f8 = Utils.convertDpToPixel(8.0f) + min;
                    Legend legend2 = this.mLegend;
                    float f13 = legend2.mNeededHeight + legend2.mTextHeightMax;
                    MPPointF center = getCenter();
                    float width = this.mLegend.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.RIGHT ? (((float) getWidth()) - f8) + 15.0f : f8 - 15.0f;
                    float f14 = f13 + 15.0f;
                    float distanceToCenter = distanceToCenter(width, f14);
                    MPPointF position = getPosition(center, getRadius(), getAngleForPoint(width, f14));
                    float distanceToCenter2 = distanceToCenter(position.f6574x, position.f6575y);
                    float convertDpToPixel = Utils.convertDpToPixel(5.0f);
                    if (f14 < center.f6575y || ((float) getHeight()) - f8 <= ((float) getWidth())) {
                        f8 = distanceToCenter < distanceToCenter2 ? (distanceToCenter2 - distanceToCenter) + convertDpToPixel : 0.0f;
                    }
                    MPPointF.recycleInstance(center);
                    MPPointF.recycleInstance(position);
                }
                int i5 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[this.mLegend.getHorizontalAlignment().ordinal()];
                if (i5 == 1) {
                    f10 = 0.0f;
                    f9 = 0.0f;
                    f12 = f8;
                    f8 = 0.0f;
                } else if (i5 != 2) {
                    if (i5 == 3) {
                        int i6 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
                        if (i6 == 1) {
                            f9 = Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight());
                            f10 = 0.0f;
                            f8 = 0.0f;
                        } else if (i6 == 2) {
                            f10 = Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight());
                            f11 = 0.0f;
                            f9 = f8;
                        }
                    }
                    f10 = 0.0f;
                    f11 = 0.0f;
                    f9 = f8;
                } else {
                    f10 = 0.0f;
                    f9 = 0.0f;
                }
                float f15 = f9;
                f5 = f10;
                f7 = f15;
            }
            f12 += getRequiredBaseOffset();
            f3 = f6 + getRequiredBaseOffset();
            f4 = f7 + getRequiredBaseOffset();
            f2 = f5 + getRequiredBaseOffset();
        }
        float convertDpToPixel2 = Utils.convertDpToPixel(this.mMinOffset);
        if (this instanceof RadarChart) {
            XAxis xAxis = getXAxis();
            if (xAxis.isEnabled() && xAxis.isDrawLabelsEnabled()) {
                convertDpToPixel2 = Math.max(convertDpToPixel2, (float) xAxis.mLabelRotatedWidth);
            }
        }
        float extraTopOffset = getExtraTopOffset() + f4;
        float extraRightOffset = getExtraRightOffset() + f3;
        float extraBottomOffset = getExtraBottomOffset() + f2;
        float max = Math.max(convertDpToPixel2, getExtraLeftOffset() + f12);
        float max2 = Math.max(convertDpToPixel2, extraTopOffset);
        float max3 = Math.max(convertDpToPixel2, extraRightOffset);
        float max4 = Math.max(convertDpToPixel2, Math.max(getRequiredBaseOffset(), extraBottomOffset));
        this.mViewPortHandler.restrainViewPort(max, max2, max3, max4);
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "offsetLeft: " + max + ", offsetTop: " + max2 + ", offsetRight: " + max3 + ", offsetBottom: " + max4);
        }
    }

    public void computeScroll() {
        ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if (chartTouchListener instanceof PieRadarChartTouchListener) {
            ((PieRadarChartTouchListener) chartTouchListener).computeScroll();
        }
    }

    public float distanceToCenter(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        float f4 = centerOffsets.f6574x;
        float f5 = f2 > f4 ? f2 - f4 : f4 - f2;
        float f6 = centerOffsets.f6575y;
        float sqrt = (float) Math.sqrt(Math.pow((double) (f3 > f6 ? f3 - f6 : f6 - f3), 2.0d) + Math.pow((double) f5, 2.0d));
        MPPointF.recycleInstance(centerOffsets);
        return sqrt;
    }

    public float getAngleForPoint(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        double d2 = (double) (f2 - centerOffsets.f6574x);
        double d3 = (double) (f3 - centerOffsets.f6575y);
        float degrees = (float) Math.toDegrees(Math.acos(d3 / Math.sqrt((d3 * d3) + (d2 * d2))));
        if (f2 > centerOffsets.f6574x) {
            degrees = 360.0f - degrees;
        }
        float f4 = degrees + 90.0f;
        if (f4 > 360.0f) {
            f4 -= 360.0f;
        }
        MPPointF.recycleInstance(centerOffsets);
        return f4;
    }

    public float getDiameter() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        contentRect.left = getExtraLeftOffset() + contentRect.left;
        contentRect.top = getExtraTopOffset() + contentRect.top;
        contentRect.right -= getExtraRightOffset();
        contentRect.bottom -= getExtraBottomOffset();
        return Math.min(contentRect.width(), contentRect.height());
    }

    public abstract int getIndexForAngle(float f2);

    public int getMaxVisibleCount() {
        return this.mData.getEntryCount();
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public MPPointF getPosition(MPPointF mPPointF, float f2, float f3) {
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(mPPointF, f2, f3, instance);
        return instance;
    }

    public abstract float getRadius();

    public float getRawRotationAngle() {
        return this.mRawRotationAngle;
    }

    public abstract float getRequiredBaseOffset();

    public abstract float getRequiredLegendOffset();

    public float getRotationAngle() {
        return this.mRotationAngle;
    }

    public float getYChartMax() {
        return 0.0f;
    }

    public float getYChartMin() {
        return 0.0f;
    }

    public void init() {
        super.init();
        this.mChartTouchListener = new PieRadarChartTouchListener(this);
    }

    public boolean isRotationEnabled() {
        return this.mRotateEnabled;
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            calcMinMax();
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mChartTouchListener;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r2) {
        /*
            r1 = this;
            boolean r0 = r1.mTouchEnabled
            if (r0 == 0) goto L_0x000d
            com.github.mikephil.charting.listener.ChartTouchListener r0 = r1.mChartTouchListener
            if (r0 == 0) goto L_0x000d
            boolean r1 = r0.onTouch(r1, r2)
            return r1
        L_0x000d:
            boolean r1 = super.onTouchEvent(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.charts.PieRadarChartBase.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setMinOffset(float f2) {
        this.mMinOffset = f2;
    }

    public void setRotationAngle(float f2) {
        this.mRawRotationAngle = f2;
        this.mRotationAngle = Utils.getNormalizedAngle(f2);
    }

    public void setRotationEnabled(boolean z2) {
        this.mRotateEnabled = z2;
    }

    @SuppressLint({"NewApi"})
    public void spin(int i3, float f2, float f3, Easing.EasingFunction easingFunction) {
        setRotationAngle(f2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotationAngle", new float[]{f2, f3});
        ofFloat.setDuration((long) i3);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PieRadarChartBase.this.postInvalidate();
            }
        });
        ofFloat.start();
    }

    public void getPosition(MPPointF mPPointF, float f2, float f3, MPPointF mPPointF2) {
        double d2 = (double) f2;
        double d3 = (double) f3;
        mPPointF2.f6574x = (float) ((Math.cos(Math.toRadians(d3)) * d2) + ((double) mPPointF.f6574x));
        mPPointF2.f6575y = (float) ((Math.sin(Math.toRadians(d3)) * d2) + ((double) mPPointF.f6575y));
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
