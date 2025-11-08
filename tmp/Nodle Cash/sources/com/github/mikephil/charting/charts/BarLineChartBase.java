package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.compose.animation.core.a;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"RtlHardcoded"})
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    private long drawCycles = 0;
    protected boolean mAutoScaleMinMaxEnabled = false;
    protected YAxis mAxisLeft;
    protected YAxisRenderer mAxisRendererLeft;
    protected YAxisRenderer mAxisRendererRight;
    protected YAxis mAxisRight;
    protected Paint mBorderPaint;
    protected boolean mClipValuesToContent = false;
    private boolean mCustomViewPortEnabled = false;
    protected boolean mDoubleTapToZoomEnabled = true;
    private boolean mDragXEnabled = true;
    private boolean mDragYEnabled = true;
    protected boolean mDrawBorders = false;
    protected boolean mDrawGridBackground = false;
    protected OnDrawListener mDrawListener;
    protected Matrix mFitScreenMatrixBuffer = new Matrix();
    protected float[] mGetPositionBuffer = new float[2];
    protected Paint mGridBackgroundPaint;
    protected boolean mHighlightPerDragEnabled = true;
    protected boolean mKeepPositionOnRotation = false;
    protected Transformer mLeftAxisTransformer;
    protected int mMaxVisibleCount = 100;
    protected float mMinOffset = 15.0f;
    private RectF mOffsetsBuffer = new RectF();
    protected float[] mOnSizeChangedBuffer = new float[2];
    protected boolean mPinchZoomEnabled = false;
    protected Transformer mRightAxisTransformer;
    private boolean mScaleXEnabled = true;
    private boolean mScaleYEnabled = true;
    protected XAxisRenderer mXAxisRenderer;
    protected Matrix mZoomMatrixBuffer = new Matrix();
    protected MPPointD posForGetHighestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
    protected MPPointD posForGetLowestVisibleX = MPPointD.getInstance(0.0d, 0.0d);
    private long totalTime = 0;

    /* renamed from: com.github.mikephil.charting.charts.BarLineChartBase$2  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.charts.BarLineChartBase.AnonymousClass2.<clinit>():void");
        }
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }

    public void autoScale() {
        ((BarLineScatterCandleBubbleData) this.mData).calcMinMaxY(getLowestVisibleX(), getHighestVisibleX());
        this.mXAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getXMin(), ((BarLineScatterCandleBubbleData) this.mData).getXMax());
        if (this.mAxisLeft.isEnabled()) {
            YAxis yAxis = this.mAxisLeft;
            YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
            yAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(axisDependency), ((BarLineScatterCandleBubbleData) this.mData).getYMax(axisDependency));
        }
        if (this.mAxisRight.isEnabled()) {
            YAxis yAxis2 = this.mAxisRight;
            YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
            yAxis2.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(axisDependency2), ((BarLineScatterCandleBubbleData) this.mData).getYMax(axisDependency2));
        }
        calculateOffsets();
    }

    public void calcMinMax() {
        this.mXAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getXMin(), ((BarLineScatterCandleBubbleData) this.mData).getXMax());
        YAxis yAxis = this.mAxisLeft;
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(axisDependency), ((BarLineScatterCandleBubbleData) this.mData).getYMax(axisDependency));
        YAxis yAxis2 = this.mAxisRight;
        YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        yAxis2.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(axisDependency2), ((BarLineScatterCandleBubbleData) this.mData).getYMax(axisDependency2));
    }

    public void calculateLegendOffsets(RectF rectF) {
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        rectF.top = 0.0f;
        rectF.bottom = 0.0f;
        Legend legend = this.mLegend;
        if (legend != null && legend.isEnabled() && !this.mLegend.isDrawInsideEnabled()) {
            int i3 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[this.mLegend.getOrientation().ordinal()];
            if (i3 == 1) {
                int i4 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[this.mLegend.getHorizontalAlignment().ordinal()];
                if (i4 == 1) {
                    rectF.left = this.mLegend.getXOffset() + Math.min(this.mLegend.mNeededWidth, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartWidth()) + rectF.left;
                } else if (i4 == 2) {
                    rectF.right = this.mLegend.getXOffset() + Math.min(this.mLegend.mNeededWidth, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartWidth()) + rectF.right;
                } else if (i4 == 3) {
                    int i5 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
                    if (i5 == 1) {
                        rectF.top = this.mLegend.getYOffset() + Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight()) + rectF.top;
                    } else if (i5 == 2) {
                        rectF.bottom = this.mLegend.getYOffset() + Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight()) + rectF.bottom;
                    }
                }
            } else if (i3 == 2) {
                int i6 = AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
                if (i6 == 1) {
                    rectF.top = this.mLegend.getYOffset() + Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight()) + rectF.top;
                } else if (i6 == 2) {
                    rectF.bottom = this.mLegend.getYOffset() + Math.min(this.mLegend.mNeededHeight, this.mLegend.getMaxSizePercent() * this.mViewPortHandler.getChartHeight()) + rectF.bottom;
                }
            }
        }
    }

    public void calculateOffsets() {
        if (!this.mCustomViewPortEnabled) {
            calculateLegendOffsets(this.mOffsetsBuffer);
            RectF rectF = this.mOffsetsBuffer;
            float f2 = rectF.left + 0.0f;
            float f3 = rectF.top + 0.0f;
            float f4 = rectF.right + 0.0f;
            float f5 = rectF.bottom + 0.0f;
            if (this.mAxisLeft.needsOffset()) {
                f2 += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
            }
            if (this.mAxisRight.needsOffset()) {
                f4 += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
            }
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
                XAxis xAxis = this.mXAxis;
                float yOffset = xAxis.getYOffset() + ((float) xAxis.mLabelRotatedHeight);
                if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                    f5 += yOffset;
                } else {
                    if (this.mXAxis.getPosition() != XAxis.XAxisPosition.TOP) {
                        if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                            f5 += yOffset;
                        }
                    }
                    f3 += yOffset;
                }
            }
            float extraTopOffset = getExtraTopOffset() + f3;
            float extraRightOffset = getExtraRightOffset() + f4;
            float extraBottomOffset = getExtraBottomOffset() + f5;
            float extraLeftOffset = getExtraLeftOffset() + f2;
            float convertDpToPixel = Utils.convertDpToPixel(this.mMinOffset);
            this.mViewPortHandler.restrainViewPort(Math.max(convertDpToPixel, extraLeftOffset), Math.max(convertDpToPixel, extraTopOffset), Math.max(convertDpToPixel, extraRightOffset), Math.max(convertDpToPixel, extraBottomOffset));
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "offsetLeft: " + extraLeftOffset + ", offsetTop: " + extraTopOffset + ", offsetRight: " + extraRightOffset + ", offsetBottom: " + extraBottomOffset);
                StringBuilder sb = new StringBuilder("Content: ");
                sb.append(this.mViewPortHandler.getContentRect().toString());
                Log.i(Chart.LOG_TAG, sb.toString());
            }
        }
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    public void centerViewTo(float f2, float f3, YAxis.AxisDependency axisDependency) {
        float axisRange = getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY();
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f2 - ((getXAxis().mAxisRange / this.mViewPortHandler.getScaleX()) / 2.0f), (axisRange / 2.0f) + f3, getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void centerViewToAnimated(float f2, float f3, YAxis.AxisDependency axisDependency, long j2) {
        YAxis.AxisDependency axisDependency2 = axisDependency;
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency);
        float axisRange = getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY();
        float scaleX = getXAxis().mAxisRange / this.mViewPortHandler.getScaleX();
        addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, f2 - (scaleX / 2.0f), (axisRange / 2.0f) + f3, getTransformer(axisDependency), this, (float) valuesByTouchPoint.f6572x, (float) valuesByTouchPoint.f6573y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void centerViewToY(float f2, YAxis.AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, 0.0f, ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f2, getTransformer(axisDependency), this));
    }

    public void computeScroll() {
        ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if (chartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) chartTouchListener).computeScroll();
        }
    }

    public void drawGridBackground(Canvas canvas) {
        if (this.mDrawGridBackground) {
            canvas.drawRect(this.mViewPortHandler.getContentRect(), this.mGridBackgroundPaint);
        }
        if (this.mDrawBorders) {
            canvas.drawRect(this.mViewPortHandler.getContentRect(), this.mBorderPaint);
        }
    }

    public void fitScreen() {
        Matrix matrix = this.mFitScreenMatrixBuffer;
        this.mViewPortHandler.fitScreen(matrix);
        this.mViewPortHandler.refresh(matrix, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public YAxis getAxis(YAxis.AxisDependency axisDependency) {
        return axisDependency == YAxis.AxisDependency.LEFT ? this.mAxisLeft : this.mAxisRight;
    }

    public YAxis getAxisLeft() {
        return this.mAxisLeft;
    }

    public float getAxisRange(YAxis.AxisDependency axisDependency) {
        return axisDependency == YAxis.AxisDependency.LEFT ? this.mAxisLeft.mAxisRange : this.mAxisRight.mAxisRange;
    }

    public YAxis getAxisRight() {
        return this.mAxisRight;
    }

    public /* bridge */ /* synthetic */ BarLineScatterCandleBubbleData getData() {
        return (BarLineScatterCandleBubbleData) super.getData();
    }

    public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float f2, float f3) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f2, f3);
        if (highlightByTouchPoint != null) {
            return (IBarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex(highlightByTouchPoint.getDataSetIndex());
        }
        return null;
    }

    public OnDrawListener getDrawListener() {
        return this.mDrawListener;
    }

    public Entry getEntryByTouchPoint(float f2, float f3) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f2, f3);
        if (highlightByTouchPoint != null) {
            return ((BarLineScatterCandleBubbleData) this.mData).getEntryForHighlight(highlightByTouchPoint);
        }
        return null;
    }

    public float getHighestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.posForGetHighestVisibleX);
        return (float) Math.min((double) this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.f6572x);
    }

    public float getLowestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.posForGetLowestVisibleX);
        return (float) Math.max((double) this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.f6572x);
    }

    public int getMaxVisibleCount() {
        return this.mMaxVisibleCount;
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public Paint getPaint(int i3) {
        Paint paint = super.getPaint(i3);
        if (paint != null) {
            return paint;
        }
        if (i3 != 4) {
            return null;
        }
        return this.mGridBackgroundPaint;
    }

    public MPPointD getPixelForValues(float f2, float f3, YAxis.AxisDependency axisDependency) {
        return getTransformer(axisDependency).getPixelForValues(f2, f3);
    }

    public MPPointF getPosition(Entry entry, YAxis.AxisDependency axisDependency) {
        if (entry == null) {
            return null;
        }
        this.mGetPositionBuffer[0] = entry.getX();
        this.mGetPositionBuffer[1] = entry.getY();
        getTransformer(axisDependency).pointValuesToPixel(this.mGetPositionBuffer);
        float[] fArr = this.mGetPositionBuffer;
        return MPPointF.getInstance(fArr[0], fArr[1]);
    }

    public YAxisRenderer getRendererLeftYAxis() {
        return this.mAxisRendererLeft;
    }

    public YAxisRenderer getRendererRightYAxis() {
        return this.mAxisRendererRight;
    }

    public XAxisRenderer getRendererXAxis() {
        return this.mXAxisRenderer;
    }

    public float getScaleX() {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (viewPortHandler == null) {
            return 1.0f;
        }
        return viewPortHandler.getScaleX();
    }

    public float getScaleY() {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (viewPortHandler == null) {
            return 1.0f;
        }
        return viewPortHandler.getScaleY();
    }

    public Transformer getTransformer(YAxis.AxisDependency axisDependency) {
        return axisDependency == YAxis.AxisDependency.LEFT ? this.mLeftAxisTransformer : this.mRightAxisTransformer;
    }

    public MPPointD getValuesByTouchPoint(float f2, float f3, YAxis.AxisDependency axisDependency) {
        MPPointD instance = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(f2, f3, axisDependency, instance);
        return instance;
    }

    public float getVisibleXRange() {
        return Math.abs(getHighestVisibleX() - getLowestVisibleX());
    }

    public float getYChartMax() {
        return Math.max(this.mAxisLeft.mAxisMaximum, this.mAxisRight.mAxisMaximum);
    }

    public float getYChartMin() {
        return Math.min(this.mAxisLeft.mAxisMinimum, this.mAxisRight.mAxisMinimum);
    }

    public boolean hasNoDragOffset() {
        return this.mViewPortHandler.hasNoDragOffset();
    }

    public void init() {
        super.init();
        this.mAxisLeft = new YAxis(YAxis.AxisDependency.LEFT);
        this.mAxisRight = new YAxis(YAxis.AxisDependency.RIGHT);
        this.mLeftAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mRightAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mAxisRendererLeft = new YAxisRenderer(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRenderer(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRenderer(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer);
        setHighlighter(new ChartHighlighter(this));
        this.mChartTouchListener = new BarLineChartTouchListener(this, this.mViewPortHandler.getMatrixTouch(), 3.0f);
        Paint paint = new Paint();
        this.mGridBackgroundPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mGridBackgroundPaint.setColor(Color.rgb(240, 240, 240));
        Paint paint2 = new Paint();
        this.mBorderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
    }

    public boolean isAnyAxisInverted() {
        return this.mAxisLeft.isInverted() || this.mAxisRight.isInverted();
    }

    public boolean isAutoScaleMinMaxEnabled() {
        return this.mAutoScaleMinMaxEnabled;
    }

    public boolean isClipValuesToContentEnabled() {
        return this.mClipValuesToContent;
    }

    public boolean isDoubleTapToZoomEnabled() {
        return this.mDoubleTapToZoomEnabled;
    }

    public boolean isDragEnabled() {
        return this.mDragXEnabled || this.mDragYEnabled;
    }

    public boolean isDragXEnabled() {
        return this.mDragXEnabled;
    }

    public boolean isDragYEnabled() {
        return this.mDragYEnabled;
    }

    public boolean isDrawBordersEnabled() {
        return this.mDrawBorders;
    }

    public boolean isFullyZoomedOut() {
        return this.mViewPortHandler.isFullyZoomedOut();
    }

    public boolean isHighlightPerDragEnabled() {
        return this.mHighlightPerDragEnabled;
    }

    public boolean isInverted(YAxis.AxisDependency axisDependency) {
        return getAxis(axisDependency).isInverted();
    }

    public boolean isKeepPositionOnRotation() {
        return this.mKeepPositionOnRotation;
    }

    public boolean isPinchZoomEnabled() {
        return this.mPinchZoomEnabled;
    }

    public boolean isScaleXEnabled() {
        return this.mScaleXEnabled;
    }

    public boolean isScaleYEnabled() {
        return this.mScaleYEnabled;
    }

    public void moveViewTo(float f2, float f3, YAxis.AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f2, ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f3, getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void moveViewToAnimated(float f2, float f3, YAxis.AxisDependency axisDependency, long j2) {
        YAxis.AxisDependency axisDependency2 = axisDependency;
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency);
        float axisRange = getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY();
        addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, f2, (axisRange / 2.0f) + f3, getTransformer(axisDependency), this, (float) valuesByTouchPoint.f6572x, (float) valuesByTouchPoint.f6573y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void moveViewToX(float f2) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f2, 0.0f, getTransformer(YAxis.AxisDependency.LEFT), this));
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "Preparing...");
            }
            DataRenderer dataRenderer = this.mRenderer;
            if (dataRenderer != null) {
                dataRenderer.initBuffers();
            }
            calcMinMax();
            YAxisRenderer yAxisRenderer = this.mAxisRendererLeft;
            YAxis yAxis = this.mAxisLeft;
            yAxisRenderer.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
            YAxisRenderer yAxisRenderer2 = this.mAxisRendererRight;
            YAxis yAxis2 = this.mAxisRight;
            yAxisRenderer2.computeAxis(yAxis2.mAxisMinimum, yAxis2.mAxisMaximum, yAxis2.isInverted());
            XAxisRenderer xAxisRenderer = this.mXAxisRenderer;
            XAxis xAxis = this.mXAxis;
            xAxisRenderer.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        } else if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing... DATA NOT SET.");
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            long currentTimeMillis = System.currentTimeMillis();
            drawGridBackground(canvas);
            if (this.mAutoScaleMinMaxEnabled) {
                autoScale();
            }
            if (this.mAxisLeft.isEnabled()) {
                YAxisRenderer yAxisRenderer = this.mAxisRendererLeft;
                YAxis yAxis = this.mAxisLeft;
                yAxisRenderer.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
            }
            if (this.mAxisRight.isEnabled()) {
                YAxisRenderer yAxisRenderer2 = this.mAxisRendererRight;
                YAxis yAxis2 = this.mAxisRight;
                yAxisRenderer2.computeAxis(yAxis2.mAxisMinimum, yAxis2.mAxisMaximum, yAxis2.isInverted());
            }
            if (this.mXAxis.isEnabled()) {
                XAxisRenderer xAxisRenderer = this.mXAxisRenderer;
                XAxis xAxis = this.mXAxis;
                xAxisRenderer.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
            }
            this.mXAxisRenderer.renderAxisLine(canvas);
            this.mAxisRendererLeft.renderAxisLine(canvas);
            this.mAxisRendererRight.renderAxisLine(canvas);
            if (this.mXAxis.isDrawGridLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderGridLines(canvas);
            }
            if (this.mAxisLeft.isDrawGridLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderGridLines(canvas);
            }
            if (this.mAxisRight.isDrawGridLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderGridLines(canvas);
            }
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isEnabled() && this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isEnabled() && this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            int save = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            this.mRenderer.drawData(canvas);
            if (!this.mXAxis.isDrawGridLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderGridLines(canvas);
            }
            if (!this.mAxisLeft.isDrawGridLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderGridLines(canvas);
            }
            if (!this.mAxisRight.isDrawGridLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderGridLines(canvas);
            }
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            canvas.restoreToCount(save);
            this.mRenderer.drawExtras(canvas);
            if (this.mXAxis.isEnabled() && !this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isEnabled() && !this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isEnabled() && !this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            this.mXAxisRenderer.renderAxisLabels(canvas);
            this.mAxisRendererLeft.renderAxisLabels(canvas);
            this.mAxisRendererRight.renderAxisLabels(canvas);
            if (isClipValuesToContentEnabled()) {
                int save2 = canvas.save();
                canvas.clipRect(this.mViewPortHandler.getContentRect());
                this.mRenderer.drawValues(canvas);
                canvas.restoreToCount(save2);
            } else {
                this.mRenderer.drawValues(canvas);
            }
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
            if (this.mLogEnabled) {
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                long j2 = this.totalTime + currentTimeMillis2;
                this.totalTime = j2;
                long j3 = this.drawCycles + 1;
                this.drawCycles = j3;
                StringBuilder u3 = a.u("Drawtime: ", currentTimeMillis2, " ms, average: ");
                u3.append(j2 / j3);
                u3.append(" ms, cycles: ");
                u3.append(this.drawCycles);
                Log.i(Chart.LOG_TAG, u3.toString());
            }
        }
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        float[] fArr = this.mOnSizeChangedBuffer;
        fArr[1] = 0.0f;
        fArr[0] = 0.0f;
        if (this.mKeepPositionOnRotation) {
            fArr[0] = this.mViewPortHandler.contentLeft();
            this.mOnSizeChangedBuffer[1] = this.mViewPortHandler.contentTop();
            getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(this.mOnSizeChangedBuffer);
        }
        super.onSizeChanged(i3, i4, i5, i6);
        if (this.mKeepPositionOnRotation) {
            getTransformer(YAxis.AxisDependency.LEFT).pointValuesToPixel(this.mOnSizeChangedBuffer);
            this.mViewPortHandler.centerViewPort(this.mOnSizeChangedBuffer, this);
            return;
        }
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        viewPortHandler.refresh(viewPortHandler.getMatrixTouch(), this, true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if (chartTouchListener == null || this.mData == null || !this.mTouchEnabled) {
            return false;
        }
        return chartTouchListener.onTouch(this, motionEvent);
    }

    public void prepareOffsetMatrix() {
        this.mRightAxisTransformer.prepareMatrixOffset(this.mAxisRight.isInverted());
        this.mLeftAxisTransformer.prepareMatrixOffset(this.mAxisLeft.isInverted());
    }

    public void prepareValuePxMatrix() {
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing Value-Px Matrix, xmin: " + this.mXAxis.mAxisMinimum + ", xmax: " + this.mXAxis.mAxisMaximum + ", xdelta: " + this.mXAxis.mAxisRange);
        }
        Transformer transformer = this.mRightAxisTransformer;
        XAxis xAxis = this.mXAxis;
        float f2 = xAxis.mAxisMinimum;
        float f3 = xAxis.mAxisRange;
        YAxis yAxis = this.mAxisRight;
        transformer.prepareMatrixValuePx(f2, f3, yAxis.mAxisRange, yAxis.mAxisMinimum);
        Transformer transformer2 = this.mLeftAxisTransformer;
        XAxis xAxis2 = this.mXAxis;
        float f4 = xAxis2.mAxisMinimum;
        float f5 = xAxis2.mAxisRange;
        YAxis yAxis2 = this.mAxisLeft;
        transformer2.prepareMatrixValuePx(f4, f5, yAxis2.mAxisRange, yAxis2.mAxisMinimum);
    }

    public void resetTracking() {
        this.totalTime = 0;
        this.drawCycles = 0;
    }

    public void resetViewPortOffsets() {
        this.mCustomViewPortEnabled = false;
        calculateOffsets();
    }

    public void resetZoom() {
        this.mViewPortHandler.resetZoom(this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void setAutoScaleMinMaxEnabled(boolean z2) {
        this.mAutoScaleMinMaxEnabled = z2;
    }

    public void setBorderColor(int i3) {
        this.mBorderPaint.setColor(i3);
    }

    public void setBorderWidth(float f2) {
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(f2));
    }

    public void setClipValuesToContent(boolean z2) {
        this.mClipValuesToContent = z2;
    }

    public void setDoubleTapToZoomEnabled(boolean z2) {
        this.mDoubleTapToZoomEnabled = z2;
    }

    public void setDragEnabled(boolean z2) {
        this.mDragXEnabled = z2;
        this.mDragYEnabled = z2;
    }

    public void setDragOffsetX(float f2) {
        this.mViewPortHandler.setDragOffsetX(f2);
    }

    public void setDragOffsetY(float f2) {
        this.mViewPortHandler.setDragOffsetY(f2);
    }

    public void setDragXEnabled(boolean z2) {
        this.mDragXEnabled = z2;
    }

    public void setDragYEnabled(boolean z2) {
        this.mDragYEnabled = z2;
    }

    public void setDrawBorders(boolean z2) {
        this.mDrawBorders = z2;
    }

    public void setDrawGridBackground(boolean z2) {
        this.mDrawGridBackground = z2;
    }

    public void setGridBackgroundColor(int i3) {
        this.mGridBackgroundPaint.setColor(i3);
    }

    public void setHighlightPerDragEnabled(boolean z2) {
        this.mHighlightPerDragEnabled = z2;
    }

    public void setKeepPositionOnRotation(boolean z2) {
        this.mKeepPositionOnRotation = z2;
    }

    public void setMaxVisibleValueCount(int i3) {
        this.mMaxVisibleCount = i3;
    }

    public void setMinOffset(float f2) {
        this.mMinOffset = f2;
    }

    public void setOnDrawListener(OnDrawListener onDrawListener) {
        this.mDrawListener = onDrawListener;
    }

    public void setPaint(Paint paint, int i3) {
        super.setPaint(paint, i3);
        if (i3 == 4) {
            this.mGridBackgroundPaint = paint;
        }
    }

    public void setPinchZoom(boolean z2) {
        this.mPinchZoomEnabled = z2;
    }

    public void setRendererLeftYAxis(YAxisRenderer yAxisRenderer) {
        this.mAxisRendererLeft = yAxisRenderer;
    }

    public void setRendererRightYAxis(YAxisRenderer yAxisRenderer) {
        this.mAxisRendererRight = yAxisRenderer;
    }

    public void setScaleEnabled(boolean z2) {
        this.mScaleXEnabled = z2;
        this.mScaleYEnabled = z2;
    }

    public void setScaleMinima(float f2, float f3) {
        this.mViewPortHandler.setMinimumScaleX(f2);
        this.mViewPortHandler.setMinimumScaleY(f3);
    }

    public void setScaleXEnabled(boolean z2) {
        this.mScaleXEnabled = z2;
    }

    public void setScaleYEnabled(boolean z2) {
        this.mScaleYEnabled = z2;
    }

    public void setViewPortOffsets(float f2, float f3, float f4, float f5) {
        this.mCustomViewPortEnabled = true;
        final float f6 = f2;
        final float f7 = f3;
        final float f8 = f4;
        final float f9 = f5;
        post(new Runnable() {
            public void run() {
                BarLineChartBase.this.mViewPortHandler.restrainViewPort(f6, f7, f8, f9);
                BarLineChartBase.this.prepareOffsetMatrix();
                BarLineChartBase.this.prepareValuePxMatrix();
            }
        });
    }

    public void setVisibleXRange(float f2, float f3) {
        float f4 = this.mXAxis.mAxisRange;
        this.mViewPortHandler.setMinMaxScaleX(f4 / f2, f4 / f3);
    }

    public void setVisibleXRangeMaximum(float f2) {
        this.mViewPortHandler.setMinimumScaleX(this.mXAxis.mAxisRange / f2);
    }

    public void setVisibleXRangeMinimum(float f2) {
        this.mViewPortHandler.setMaximumScaleX(this.mXAxis.mAxisRange / f2);
    }

    public void setVisibleYRange(float f2, float f3, YAxis.AxisDependency axisDependency) {
        this.mViewPortHandler.setMinMaxScaleY(getAxisRange(axisDependency) / f2, getAxisRange(axisDependency) / f3);
    }

    public void setVisibleYRangeMaximum(float f2, YAxis.AxisDependency axisDependency) {
        this.mViewPortHandler.setMinimumScaleY(getAxisRange(axisDependency) / f2);
    }

    public void setVisibleYRangeMinimum(float f2, YAxis.AxisDependency axisDependency) {
        this.mViewPortHandler.setMaximumScaleY(getAxisRange(axisDependency) / f2);
    }

    public void setXAxisRenderer(XAxisRenderer xAxisRenderer) {
        this.mXAxisRenderer = xAxisRenderer;
    }

    public void zoom(float f2, float f3, float f4, float f5) {
        this.mViewPortHandler.zoom(f2, f3, f4, -f5, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        calculateOffsets();
        postInvalidate();
    }

    @TargetApi(11)
    public void zoomAndCenterAnimated(float f2, float f3, float f4, float f5, YAxis.AxisDependency axisDependency, long j2) {
        YAxis.AxisDependency axisDependency2 = axisDependency;
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency2);
        addViewportJob(AnimatedZoomJob.getInstance(this.mViewPortHandler, this, getTransformer(axisDependency2), getAxis(axisDependency2), this.mXAxis.mAxisRange, f2, f3, this.mViewPortHandler.getScaleX(), this.mViewPortHandler.getScaleY(), f4, f5, (float) valuesByTouchPoint.f6572x, (float) valuesByTouchPoint.f6573y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void zoomIn() {
        MPPointF contentCenter = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomIn(contentCenter.f6574x, -contentCenter.f6575y, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomOut() {
        MPPointF contentCenter = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomOut(contentCenter.f6574x, -contentCenter.f6575y, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomToCenter(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        Matrix matrix = this.mZoomMatrixBuffer;
        this.mViewPortHandler.zoom(f2, f3, centerOffsets.f6574x, -centerOffsets.f6575y, matrix);
        this.mViewPortHandler.refresh(matrix, this, false);
    }

    public void getValuesByTouchPoint(float f2, float f3, YAxis.AxisDependency axisDependency, MPPointD mPPointD) {
        getTransformer(axisDependency).getValuesByTouchPoint(f2, f3, mPPointD);
    }

    public void zoom(float f2, float f3, float f4, float f5, YAxis.AxisDependency axisDependency) {
        addViewportJob(ZoomJob.getInstance(this.mViewPortHandler, f2, f3, f4, f5, getTransformer(axisDependency), axisDependency, this));
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BarLineChartBase(Context context) {
        super(context);
    }
}
