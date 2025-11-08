package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;

public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds = new RectF();
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer = new Path();
    protected RectF mDrawHighlightedRectF = new RectF();
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath = new Path();
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer = new RectF();
    private Path mPathBuffer = new Path();
    private RectF[] mRectBuffer = {new RectF(), new RectF(), new RectF()};
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = pieChart;
        Paint paint = new Paint(1);
        this.mHolePaint = paint;
        paint.setColor(-1);
        Paint paint2 = this.mHolePaint;
        Paint.Style style = Paint.Style.FILL;
        paint2.setStyle(style);
        Paint paint3 = new Paint(1);
        this.mTransparentCirclePaint = paint3;
        paint3.setColor(-1);
        this.mTransparentCirclePaint.setStyle(style);
        this.mTransparentCirclePaint.setAlpha(105);
        TextPaint textPaint = new TextPaint(1);
        this.mCenterTextPaint = textPaint;
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        Paint paint4 = this.mValuePaint;
        Paint.Align align = Paint.Align.CENTER;
        paint4.setTextAlign(align);
        Paint paint5 = new Paint(1);
        this.mEntryLabelsPaint = paint5;
        paint5.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(align);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        Paint paint6 = new Paint(1);
        this.mValueLinePaint = paint6;
        paint6.setStyle(Paint.Style.STROKE);
    }

    public float calculateMinimumRadiusForSpacedSlice(MPPointF mPPointF, float f2, float f3, float f4, float f5, float f6, float f7) {
        MPPointF mPPointF2 = mPPointF;
        double d2 = (double) ((f6 + f7) * 0.017453292f);
        float cos = (((float) Math.cos(d2)) * f2) + mPPointF2.f6574x;
        float sin = (((float) Math.sin(d2)) * f2) + mPPointF2.f6575y;
        double d3 = (double) (((f7 / 2.0f) + f6) * 0.017453292f);
        float cos2 = (((float) Math.cos(d3)) * f2) + mPPointF2.f6574x;
        float sin2 = (((float) Math.sin(d3)) * f2) + mPPointF2.f6575y;
        double pow = Math.pow((double) (cos - f4), 2.0d);
        return (float) (((double) (f2 - ((float) (Math.tan(((180.0d - ((double) f3)) / 2.0d) * 0.017453292519943295d) * (Math.sqrt(Math.pow((double) (sin - f5), 2.0d) + pow) / 2.0d))))) - Math.sqrt(Math.pow((double) (sin2 - ((sin + f5) / 2.0f)), 2.0d) + Math.pow((double) (cos2 - ((cos + f4) / 2.0f)), 2.0d)));
    }

    public void drawCenterText(Canvas canvas) {
        float f2;
        MPPointF mPPointF;
        Canvas canvas2 = canvas;
        CharSequence centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            MPPointF centerTextOffset = this.mChart.getCenterTextOffset();
            float f3 = centerCircleBox.f6574x + centerTextOffset.f6574x;
            float f4 = centerCircleBox.f6575y + centerTextOffset.f6575y;
            if (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) {
                f2 = this.mChart.getRadius();
            } else {
                f2 = (this.mChart.getHoleRadius() / 100.0f) * this.mChart.getRadius();
            }
            RectF[] rectFArr = this.mRectBuffer;
            RectF rectF = rectFArr[0];
            rectF.left = f3 - f2;
            rectF.top = f4 - f2;
            rectF.right = f3 + f2;
            rectF.bottom = f4 + f2;
            RectF rectF2 = rectFArr[1];
            rectF2.set(rectF);
            float centerTextRadiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
            if (((double) centerTextRadiusPercent) > 0.0d) {
                rectF2.inset((rectF2.width() - (rectF2.width() * centerTextRadiusPercent)) / 2.0f, (rectF2.height() - (rectF2.height() * centerTextRadiusPercent)) / 2.0f);
            }
            if (!centerText.equals(this.mCenterTextLastValue) || !rectF2.equals(this.mCenterTextLastBounds)) {
                this.mCenterTextLastBounds.set(rectF2);
                this.mCenterTextLastValue = centerText;
                mPPointF = centerTextOffset;
                StaticLayout staticLayout = r3;
                StaticLayout staticLayout2 = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil((double) this.mCenterTextLastBounds.width()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
                this.mCenterTextLayout = staticLayout;
            } else {
                mPPointF = centerTextOffset;
            }
            canvas.save();
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(rectF, Path.Direction.CW);
            canvas2.clipPath(path);
            canvas2.translate(rectF2.left, ((rectF2.height() - ((float) this.mCenterTextLayout.getHeight())) / 2.0f) + rectF2.top);
            this.mCenterTextLayout.draw(canvas2);
            canvas.restore();
            MPPointF.recycleInstance(centerCircleBox);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        Bitmap bitmap = weakReference == null ? null : weakReference.get();
        if (!(bitmap != null && bitmap.getWidth() == chartWidth && bitmap.getHeight() == chartHeight)) {
            if (chartWidth > 0 && chartHeight > 0) {
                bitmap = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);
                this.mDrawBitmap = new WeakReference<>(bitmap);
                this.mBitmapCanvas = new Canvas(bitmap);
            } else {
                return;
            }
        }
        bitmap.eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.mChart.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    public void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        float[] fArr;
        float f2;
        float f3;
        int i3;
        RectF rectF;
        int i4;
        float f4;
        int i5;
        MPPointF mPPointF;
        RectF rectF2;
        float f5;
        int i6;
        float f6;
        float f7;
        float f8;
        int i7;
        RectF rectF3;
        RectF rectF4;
        MPPointF mPPointF2;
        int i8;
        float f9;
        MPPointF mPPointF3;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = this.mChart.getRotationAngle();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        RectF circleBox = this.mChart.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = this.mChart.getDrawAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean z2 = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        float holeRadius = z2 ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        float holeRadius2 = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
        RectF rectF5 = new RectF();
        boolean z3 = z2 && this.mChart.isDrawRoundedSlicesEnabled();
        int i9 = 0;
        for (int i10 = 0; i10 < entryCount; i10++) {
            if (Math.abs(((PieEntry) iPieDataSet2.getEntryForIndex(i10)).getY()) > Utils.FLOAT_EPSILON) {
                i9++;
            }
        }
        float sliceSpace = i9 <= 1 ? 0.0f : getSliceSpace(iPieDataSet2);
        int i11 = 0;
        float f10 = 0.0f;
        while (i11 < entryCount) {
            float f11 = drawAngles[i11];
            float abs = Math.abs(iPieDataSet2.getEntryForIndex(i11).getY());
            float f12 = Utils.FLOAT_EPSILON;
            if (abs > f12 && (!this.mChart.needsHighlight(i11) || z3)) {
                boolean z4 = sliceSpace > 0.0f && f11 <= 180.0f;
                i4 = entryCount;
                this.mRenderPaint.setColor(iPieDataSet2.getColor(i11));
                float f13 = i9 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                float f14 = (((f13 / 2.0f) + f10) * phaseY) + rotationAngle;
                float f15 = (f11 - f13) * phaseY;
                float f16 = f15 < 0.0f ? 0.0f : f15;
                this.mPathBuffer.reset();
                if (z3) {
                    float f17 = radius - holeRadius2;
                    i3 = i11;
                    i6 = i9;
                    double d2 = (double) (f14 * 0.017453292f);
                    f3 = rotationAngle;
                    f2 = phaseX;
                    float cos = (((float) Math.cos(d2)) * f17) + centerCircleBox.f6574x;
                    float sin = (f17 * ((float) Math.sin(d2))) + centerCircleBox.f6575y;
                    rectF5.set(cos - holeRadius2, sin - holeRadius2, cos + holeRadius2, sin + holeRadius2);
                } else {
                    i3 = i11;
                    i6 = i9;
                    f3 = rotationAngle;
                    f2 = phaseX;
                }
                double d3 = (double) (f14 * 0.017453292f);
                float f18 = holeRadius;
                float cos2 = (((float) Math.cos(d3)) * radius) + centerCircleBox.f6574x;
                float sin2 = (((float) Math.sin(d3)) * radius) + centerCircleBox.f6575y;
                int i12 = (f16 > 360.0f ? 1 : (f16 == 360.0f ? 0 : -1));
                if (i12 < 0 || f16 % 360.0f > f12) {
                    fArr = drawAngles;
                    if (z3) {
                        this.mPathBuffer.arcTo(rectF5, f14 + 180.0f, -180.0f);
                    }
                    this.mPathBuffer.arcTo(circleBox, f14, f16);
                } else {
                    fArr = drawAngles;
                    this.mPathBuffer.addCircle(centerCircleBox.f6574x, centerCircleBox.f6575y, radius, Path.Direction.CW);
                }
                RectF rectF6 = this.mInnerRectBuffer;
                float f19 = centerCircleBox.f6574x;
                float f20 = centerCircleBox.f6575y;
                RectF rectF7 = rectF5;
                rectF6.set(f19 - f18, f20 - f18, f19 + f18, f20 + f18);
                if (!z2) {
                    f7 = radius;
                    f8 = f18;
                    i7 = i6;
                    rectF3 = rectF7;
                    rectF4 = circleBox;
                    mPPointF = centerCircleBox;
                    f6 = 360.0f;
                } else if (f18 > 0.0f || z4) {
                    if (z4) {
                        int i13 = i3;
                        i5 = i6;
                        float f21 = radius;
                        rectF = circleBox;
                        RectF rectF8 = rectF7;
                        f5 = f18;
                        i8 = 1;
                        f4 = radius;
                        float f22 = f14;
                        mPPointF2 = centerCircleBox;
                        float calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(centerCircleBox, f21, f11 * phaseY, cos2, sin2, f22, f16);
                        if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                            calculateMinimumRadiusForSpacedSlice = -calculateMinimumRadiusForSpacedSlice;
                        }
                        f9 = Math.max(f5, calculateMinimumRadiusForSpacedSlice);
                    } else {
                        f4 = radius;
                        mPPointF2 = centerCircleBox;
                        f5 = f18;
                        i5 = i6;
                        rectF = circleBox;
                        i8 = 1;
                        f9 = f5;
                    }
                    float f23 = (i5 == i8 || f9 == 0.0f) ? 0.0f : sliceSpace / (f9 * 0.017453292f);
                    float f24 = (((f23 / 2.0f) + f10) * phaseY) + f3;
                    float f25 = (f11 - f23) * phaseY;
                    if (f25 < 0.0f) {
                        f25 = 0.0f;
                    }
                    float f26 = f24 + f25;
                    if (i12 < 0 || f16 % 360.0f > f12) {
                        if (z3) {
                            float f27 = f4 - holeRadius2;
                            double d4 = (double) (0.017453292f * f26);
                            mPPointF3 = mPPointF2;
                            float cos3 = (((float) Math.cos(d4)) * f27) + mPPointF2.f6574x;
                            float sin3 = (f27 * ((float) Math.sin(d4))) + mPPointF3.f6575y;
                            rectF2 = rectF7;
                            rectF2.set(cos3 - holeRadius2, sin3 - holeRadius2, cos3 + holeRadius2, sin3 + holeRadius2);
                            this.mPathBuffer.arcTo(rectF2, f26, 180.0f);
                        } else {
                            mPPointF3 = mPPointF2;
                            rectF2 = rectF7;
                            double d5 = (double) (f26 * 0.017453292f);
                            this.mPathBuffer.lineTo((((float) Math.cos(d5)) * f9) + mPPointF3.f6574x, (f9 * ((float) Math.sin(d5))) + mPPointF3.f6575y);
                        }
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, f26, -f25);
                    } else {
                        this.mPathBuffer.addCircle(mPPointF2.f6574x, mPPointF2.f6575y, f9, Path.Direction.CCW);
                        mPPointF3 = mPPointF2;
                        rectF2 = rectF7;
                    }
                    mPPointF = mPPointF3;
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                    f10 = (f11 * f2) + f10;
                } else {
                    f7 = radius;
                    f8 = f18;
                    i7 = i6;
                    rectF3 = rectF7;
                    f6 = 360.0f;
                    rectF4 = circleBox;
                    mPPointF = centerCircleBox;
                }
                if (f16 % f6 > f12) {
                    if (z4) {
                        float calculateMinimumRadiusForSpacedSlice2 = calculateMinimumRadiusForSpacedSlice(mPPointF, f4, f11 * phaseY, cos2, sin2, f14, f16);
                        double d6 = (double) (0.017453292f * ((f16 / 2.0f) + f14));
                        this.mPathBuffer.lineTo((((float) Math.cos(d6)) * calculateMinimumRadiusForSpacedSlice2) + mPPointF.f6574x, (calculateMinimumRadiusForSpacedSlice2 * ((float) Math.sin(d6))) + mPPointF.f6575y);
                    } else {
                        this.mPathBuffer.lineTo(mPPointF.f6574x, mPPointF.f6575y);
                    }
                }
                this.mPathBuffer.close();
                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                f10 = (f11 * f2) + f10;
            } else {
                i3 = i11;
                f4 = radius;
                f3 = rotationAngle;
                f2 = phaseX;
                rectF = circleBox;
                i4 = entryCount;
                fArr = drawAngles;
                f10 = (f11 * phaseX) + f10;
                i5 = i9;
                rectF2 = rectF5;
                f5 = holeRadius;
                mPPointF = centerCircleBox;
            }
            i11 = i3 + 1;
            iPieDataSet2 = iPieDataSet;
            holeRadius = f5;
            rectF5 = rectF2;
            centerCircleBox = mPPointF;
            i9 = i5;
            radius = f4;
            entryCount = i4;
            circleBox = rectF;
            rotationAngle = f3;
            phaseX = f2;
            drawAngles = fArr;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public void drawEntryLabel(Canvas canvas, String str, float f2, float f3) {
        canvas.drawText(str, f2, f3, this.mEntryLabelsPaint);
    }

    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(canvas);
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        boolean z2;
        float[] fArr;
        float f2;
        MPPointF mPPointF;
        float f3;
        int i3;
        RectF rectF;
        float f4;
        IPieDataSet dataSetByIndex;
        float f5;
        int i4;
        int i5;
        float f6;
        float[] fArr2;
        float f7;
        float f8;
        Highlight[] highlightArr2 = highlightArr;
        boolean z3 = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        if (!z3 || !this.mChart.isDrawRoundedSlicesEnabled()) {
            float phaseX = this.mAnimator.getPhaseX();
            float phaseY = this.mAnimator.getPhaseY();
            float rotationAngle = this.mChart.getRotationAngle();
            float[] drawAngles = this.mChart.getDrawAngles();
            float[] absoluteAngles = this.mChart.getAbsoluteAngles();
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            float radius = this.mChart.getRadius();
            float holeRadius = z3 ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
            RectF rectF2 = this.mDrawHighlightedRectF;
            rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
            int i6 = 0;
            while (i6 < highlightArr2.length) {
                int x2 = (int) highlightArr2[i6].getX();
                if (x2 < drawAngles.length && (dataSetByIndex = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr2[i6].getDataSetIndex())) != null && dataSetByIndex.isHighlightEnabled()) {
                    int entryCount = dataSetByIndex.getEntryCount();
                    int i7 = 0;
                    for (int i8 = 0; i8 < entryCount; i8++) {
                        if (Math.abs(((PieEntry) dataSetByIndex.getEntryForIndex(i8)).getY()) > Utils.FLOAT_EPSILON) {
                            i7++;
                        }
                    }
                    if (x2 == 0) {
                        i4 = 1;
                        f5 = 0.0f;
                    } else {
                        f5 = absoluteAngles[x2 - 1] * phaseX;
                        i4 = 1;
                    }
                    float sliceSpace = i7 <= i4 ? 0.0f : dataSetByIndex.getSliceSpace();
                    float f9 = drawAngles[x2];
                    float selectionShift = dataSetByIndex.getSelectionShift();
                    int i9 = i6;
                    float f10 = radius + selectionShift;
                    float f11 = holeRadius;
                    rectF2.set(this.mChart.getCircleBox());
                    float f12 = -selectionShift;
                    rectF2.inset(f12, f12);
                    boolean z4 = sliceSpace > 0.0f && f9 <= 180.0f;
                    this.mRenderPaint.setColor(dataSetByIndex.getColor(x2));
                    float f13 = i7 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                    float f14 = i7 == 1 ? 0.0f : sliceSpace / (f10 * 0.017453292f);
                    float f15 = (((f13 / 2.0f) + f5) * phaseY) + rotationAngle;
                    float f16 = (f9 - f13) * phaseY;
                    float f17 = f16 < 0.0f ? 0.0f : f16;
                    float f18 = (((f14 / 2.0f) + f5) * phaseY) + rotationAngle;
                    float f19 = (f9 - f14) * phaseY;
                    if (f19 < 0.0f) {
                        f19 = 0.0f;
                    }
                    this.mPathBuffer.reset();
                    int i10 = (f17 > 360.0f ? 1 : (f17 == 360.0f ? 0 : -1));
                    if (i10 < 0 || f17 % 360.0f > Utils.FLOAT_EPSILON) {
                        fArr2 = drawAngles;
                        f6 = f5;
                        double d2 = (double) (f18 * 0.017453292f);
                        i5 = i7;
                        z2 = z3;
                        this.mPathBuffer.moveTo((((float) Math.cos(d2)) * f10) + centerCircleBox.f6574x, (f10 * ((float) Math.sin(d2))) + centerCircleBox.f6575y);
                        this.mPathBuffer.arcTo(rectF2, f18, f19);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.f6574x, centerCircleBox.f6575y, f10, Path.Direction.CW);
                        fArr2 = drawAngles;
                        f6 = f5;
                        i5 = i7;
                        z2 = z3;
                    }
                    if (z4) {
                        double d3 = (double) (f15 * 0.017453292f);
                        i3 = i9;
                        rectF = rectF2;
                        f3 = f11;
                        mPPointF = centerCircleBox;
                        fArr = fArr2;
                        f7 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f9 * phaseY, (((float) Math.cos(d3)) * radius) + centerCircleBox.f6574x, (((float) Math.sin(d3)) * radius) + centerCircleBox.f6575y, f15, f17);
                    } else {
                        rectF = rectF2;
                        mPPointF = centerCircleBox;
                        i3 = i9;
                        f3 = f11;
                        fArr = fArr2;
                        f7 = 0.0f;
                    }
                    RectF rectF3 = this.mInnerRectBuffer;
                    float f20 = mPPointF.f6574x;
                    float f21 = mPPointF.f6575y;
                    rectF3.set(f20 - f3, f21 - f3, f20 + f3, f21 + f3);
                    if (!z2 || (f3 <= 0.0f && !z4)) {
                        f4 = phaseX;
                        f2 = phaseY;
                        if (f17 % 360.0f > Utils.FLOAT_EPSILON) {
                            if (z4) {
                                double d4 = (double) (((f17 / 2.0f) + f15) * 0.017453292f);
                                this.mPathBuffer.lineTo((((float) Math.cos(d4)) * f7) + mPPointF.f6574x, (f7 * ((float) Math.sin(d4))) + mPPointF.f6575y);
                            } else {
                                this.mPathBuffer.lineTo(mPPointF.f6574x, mPPointF.f6575y);
                            }
                        }
                    } else {
                        if (z4) {
                            if (f7 < 0.0f) {
                                f7 = -f7;
                            }
                            f8 = Math.max(f3, f7);
                        } else {
                            f8 = f3;
                        }
                        float f22 = (i5 == 1 || f8 == 0.0f) ? 0.0f : sliceSpace / (f8 * 0.017453292f);
                        float f23 = (((f22 / 2.0f) + f6) * phaseY) + rotationAngle;
                        float f24 = (f9 - f22) * phaseY;
                        if (f24 < 0.0f) {
                            f24 = 0.0f;
                        }
                        float f25 = f23 + f24;
                        if (i10 < 0 || f17 % 360.0f > Utils.FLOAT_EPSILON) {
                            double d5 = (double) (f25 * 0.017453292f);
                            f4 = phaseX;
                            f2 = phaseY;
                            this.mPathBuffer.lineTo((((float) Math.cos(d5)) * f8) + mPPointF.f6574x, (f8 * ((float) Math.sin(d5))) + mPPointF.f6575y);
                            this.mPathBuffer.arcTo(this.mInnerRectBuffer, f25, -f24);
                        } else {
                            this.mPathBuffer.addCircle(mPPointF.f6574x, mPPointF.f6575y, f8, Path.Direction.CCW);
                            f4 = phaseX;
                            f2 = phaseY;
                        }
                    }
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                } else {
                    i3 = i6;
                    rectF = rectF2;
                    f3 = holeRadius;
                    fArr = drawAngles;
                    z2 = z3;
                    f4 = phaseX;
                    f2 = phaseY;
                    mPPointF = centerCircleBox;
                }
                i6 = i3 + 1;
                phaseX = f4;
                rectF2 = rectF;
                holeRadius = f3;
                centerCircleBox = mPPointF;
                phaseY = f2;
                drawAngles = fArr;
                z3 = z2;
                highlightArr2 = highlightArr;
            }
            MPPointF.recycleInstance(centerCircleBox);
        }
    }

    public void drawHole(Canvas canvas) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0) {
                this.mBitmapCanvas.drawCircle(centerCircleBox.f6574x, centerCircleBox.f6575y, holeRadius, this.mHolePaint);
            }
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                float transparentCircleRadius = (this.mChart.getTransparentCircleRadius() / 100.0f) * radius;
                this.mTransparentCirclePaint.setAlpha((int) (this.mAnimator.getPhaseY() * this.mAnimator.getPhaseX() * ((float) alpha)));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(centerCircleBox.f6574x, centerCircleBox.f6575y, transparentCircleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle(centerCircleBox.f6574x, centerCircleBox.f6575y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(centerCircleBox);
        }
    }

    public void drawRoundedSlices(Canvas canvas) {
        float f2;
        float f3;
        float[] fArr;
        if (this.mChart.isDrawRoundedSlicesEnabled()) {
            IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
            if (dataSet.isVisible()) {
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
                float radius = this.mChart.getRadius();
                float holeRadius = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles();
                float rotationAngle = this.mChart.getRotationAngle();
                int i3 = 0;
                while (i3 < dataSet.getEntryCount()) {
                    float f4 = drawAngles[i3];
                    if (Math.abs(dataSet.getEntryForIndex(i3).getY()) > Utils.FLOAT_EPSILON) {
                        double d2 = (double) (radius - holeRadius);
                        double d3 = (double) ((rotationAngle + f4) * phaseY);
                        f2 = phaseY;
                        fArr = drawAngles;
                        f3 = rotationAngle;
                        double d4 = (double) centerCircleBox.f6574x;
                        this.mRenderPaint.setColor(dataSet.getColor(i3));
                        this.mBitmapCanvas.drawCircle((float) (d4 + (Math.cos(Math.toRadians(d3)) * d2)), (float) ((Math.sin(Math.toRadians(d3)) * d2) + ((double) centerCircleBox.f6575y)), holeRadius, this.mRenderPaint);
                    } else {
                        f2 = phaseY;
                        fArr = drawAngles;
                        f3 = rotationAngle;
                    }
                    rotationAngle = (f4 * phaseX) + f3;
                    i3++;
                    phaseY = f2;
                    drawAngles = fArr;
                }
                MPPointF.recycleInstance(centerCircleBox);
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x03c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawValues(android.graphics.Canvas r51) {
        /*
            r50 = this;
            r6 = r50
            r7 = r51
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            com.github.mikephil.charting.utils.MPPointF r8 = r0.getCenterCircleBox()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            float r9 = r0.getRadius()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            float r0 = r0.getRotationAngle()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float[] r10 = r1.getDrawAngles()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float[] r11 = r1.getAbsoluteAngles()
            com.github.mikephil.charting.animation.ChartAnimator r1 = r6.mAnimator
            float r12 = r1.getPhaseX()
            com.github.mikephil.charting.animation.ChartAnimator r1 = r6.mAnimator
            float r13 = r1.getPhaseY()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float r1 = r1.getHoleRadius()
            float r1 = r1 * r9
            r14 = 1120403456(0x42c80000, float:100.0)
            float r1 = r1 / r14
            float r1 = r9 - r1
            r15 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r15
            com.github.mikephil.charting.charts.PieChart r2 = r6.mChart
            float r2 = r2.getHoleRadius()
            float r5 = r2 / r14
            r2 = 1092616192(0x41200000, float:10.0)
            float r2 = r9 / r2
            r3 = 1080452710(0x40666666, float:3.6)
            float r2 = r2 * r3
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawHoleEnabled()
            if (r3 == 0) goto L_0x0079
            float r2 = androidx.compose.animation.core.a.a(r9, r5, r9, r15)
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawSlicesUnderHoleEnabled()
            if (r3 != 0) goto L_0x0079
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawRoundedSlicesEnabled()
            if (r3 == 0) goto L_0x0079
            double r3 = (double) r0
            r0 = 1135869952(0x43b40000, float:360.0)
            float r1 = r1 * r0
            double r0 = (double) r1
            r16 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r14 = (double) r9
            double r14 = r14 * r16
            double r0 = r0 / r14
            double r0 = r0 + r3
            float r0 = (float) r0
        L_0x0079:
            r14 = r0
            float r15 = r9 - r2
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            com.github.mikephil.charting.data.ChartData r0 = r0.getData()
            r16 = r0
            com.github.mikephil.charting.data.PieData r16 = (com.github.mikephil.charting.data.PieData) r16
            java.util.List r4 = r16.getDataSets()
            float r17 = r16.getYValueSum()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            boolean r20 = r0.isDrawEntryLabelsEnabled()
            r51.save()
            r0 = 1084227584(0x40a00000, float:5.0)
            float r21 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r22 = 0
            r0 = r22
            r3 = r0
        L_0x00a2:
            int r1 = r4.size()
            if (r3 >= r1) goto L_0x0414
            java.lang.Object r1 = r4.get(r3)
            r2 = r1
            com.github.mikephil.charting.interfaces.datasets.IPieDataSet r2 = (com.github.mikephil.charting.interfaces.datasets.IPieDataSet) r2
            boolean r23 = r2.isDrawValuesEnabled()
            if (r23 != 0) goto L_0x00d1
            if (r20 != 0) goto L_0x00d1
            r25 = r3
            r27 = r4
            r29 = r5
            r28 = r9
            r34 = r10
            r36 = r11
            r37 = r12
            r38 = r13
            r40 = r14
            r18 = 1120403456(0x42c80000, float:100.0)
            r19 = 1073741824(0x40000000, float:2.0)
            r9 = r7
            r10 = r8
            goto L_0x03fe
        L_0x00d1:
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r1 = r2.getXValuePosition()
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r7 = r2.getYValuePosition()
            r6.applyValueTextStyle(r2)
            r24 = r0
            android.graphics.Paint r0 = r6.mValuePaint
            r25 = r3
            java.lang.String r3 = "Q"
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r0, r3)
            float r0 = (float) r0
            r3 = 1082130432(0x40800000, float:4.0)
            float r3 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r3)
            float r26 = r3 + r0
            com.github.mikephil.charting.formatter.ValueFormatter r3 = r2.getValueFormatter()
            int r0 = r2.getEntryCount()
            r27 = r4
            android.graphics.Paint r4 = r6.mValueLinePaint
            r28 = r8
            int r8 = r2.getValueLineColor()
            r4.setColor(r8)
            android.graphics.Paint r4 = r6.mValueLinePaint
            float r8 = r2.getValueLineWidth()
            float r8 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r8)
            r4.setStrokeWidth(r8)
            float r8 = r6.getSliceSpace(r2)
            com.github.mikephil.charting.utils.MPPointF r4 = r2.getIconsOffset()
            com.github.mikephil.charting.utils.MPPointF r4 = com.github.mikephil.charting.utils.MPPointF.getInstance(r4)
            r29 = r5
            float r5 = r4.f6574x
            float r5 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r5)
            r4.f6574x = r5
            float r5 = r4.f6575y
            float r5 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r5)
            r4.f6575y = r5
            r5 = r22
        L_0x0133:
            if (r5 >= r0) goto L_0x03e4
            com.github.mikephil.charting.data.Entry r30 = r2.getEntryForIndex(r5)
            r31 = r4
            r4 = r30
            com.github.mikephil.charting.data.PieEntry r4 = (com.github.mikephil.charting.data.PieEntry) r4
            if (r24 != 0) goto L_0x0144
            r30 = 0
            goto L_0x014a
        L_0x0144:
            int r30 = r24 + -1
            r30 = r11[r30]
            float r30 = r30 * r12
        L_0x014a:
            r32 = r10[r24]
            r33 = 1016003125(0x3c8efa35, float:0.017453292)
            float r34 = r15 * r33
            float r34 = r8 / r34
            r19 = 1073741824(0x40000000, float:2.0)
            float r34 = r34 / r19
            float r32 = r32 - r34
            float r32 = r32 / r19
            float r32 = r32 + r30
            float r32 = r32 * r13
            r30 = r0
            float r0 = r32 + r14
            r32 = r8
            com.github.mikephil.charting.charts.PieChart r8 = r6.mChart
            boolean r8 = r8.isUsePercentValuesEnabled()
            if (r8 == 0) goto L_0x0178
            float r8 = r4.getY()
            float r8 = r8 / r17
            r18 = 1120403456(0x42c80000, float:100.0)
            float r8 = r8 * r18
            goto L_0x017c
        L_0x0178:
            float r8 = r4.getY()
        L_0x017c:
            java.lang.String r8 = r3.getPieLabel(r8, r4)
            r34 = r10
            java.lang.String r10 = r4.getLabel()
            r35 = r3
            float r3 = r0 * r33
            r33 = r4
            double r3 = (double) r3
            r36 = r11
            r37 = r12
            double r11 = java.lang.Math.cos(r3)
            float r11 = (float) r11
            r38 = r13
            double r12 = java.lang.Math.sin(r3)
            float r12 = (float) r12
            if (r20 == 0) goto L_0x01a5
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r13 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.OUTSIDE_SLICE
            if (r1 != r13) goto L_0x01a5
            r13 = 1
            goto L_0x01a7
        L_0x01a5:
            r13 = r22
        L_0x01a7:
            r40 = r14
            if (r23 == 0) goto L_0x01b1
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r14 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.OUTSIDE_SLICE
            if (r7 != r14) goto L_0x01b1
            r14 = 1
            goto L_0x01b3
        L_0x01b1:
            r14 = r22
        L_0x01b3:
            r41 = r10
            if (r20 == 0) goto L_0x01bd
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r10 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.INSIDE_SLICE
            if (r1 != r10) goto L_0x01bd
            r10 = 1
            goto L_0x01bf
        L_0x01bd:
            r10 = r22
        L_0x01bf:
            r42 = r1
            if (r23 == 0) goto L_0x01ca
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r1 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.INSIDE_SLICE
            if (r7 != r1) goto L_0x01ca
            r39 = 1
            goto L_0x01cc
        L_0x01ca:
            r39 = r22
        L_0x01cc:
            if (r13 != 0) goto L_0x01e9
            if (r14 == 0) goto L_0x01d1
            goto L_0x01e9
        L_0x01d1:
            r44 = r10
            r48 = r12
            r10 = r28
            r49 = r31
            r31 = r33
            r14 = r41
            r18 = 1120403456(0x42c80000, float:100.0)
            r12 = r5
            r33 = r7
            r28 = r9
            r9 = r51
            r7 = r2
            goto L_0x032a
        L_0x01e9:
            float r1 = r2.getValueLinePart1Length()
            float r43 = r2.getValueLinePart2Length()
            float r44 = r2.getValueLinePart1OffsetPercentage()
            r45 = r7
            r18 = 1120403456(0x42c80000, float:100.0)
            float r7 = r44 / r18
            r44 = r10
            com.github.mikephil.charting.charts.PieChart r10 = r6.mChart
            boolean r10 = r10.isDrawHoleEnabled()
            if (r10 == 0) goto L_0x020c
            float r10 = r9 * r29
            float r7 = android.support.v4.media.session.a.b(r9, r10, r7, r10)
            goto L_0x020d
        L_0x020c:
            float r7 = r7 * r9
        L_0x020d:
            boolean r10 = r2.isValueLineVariableLength()
            if (r10 == 0) goto L_0x0221
            float r43 = r43 * r15
            double r3 = java.lang.Math.sin(r3)
            double r3 = java.lang.Math.abs(r3)
            float r3 = (float) r3
            float r43 = r43 * r3
            goto L_0x0223
        L_0x0221:
            float r43 = r43 * r15
        L_0x0223:
            float r3 = r7 * r11
            r10 = r28
            float r4 = r10.f6574x
            float r3 = r3 + r4
            float r7 = r7 * r12
            r28 = r9
            float r9 = r10.f6575y
            float r7 = r7 + r9
            r46 = 1065353216(0x3f800000, float:1.0)
            float r1 = r1 + r46
            float r1 = r1 * r15
            float r46 = r1 * r11
            float r46 = r46 + r4
            float r1 = r1 * r12
            float r9 = r9 + r1
            double r0 = (double) r0
            r47 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r0 = r0 % r47
            r47 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r4 = (r0 > r47 ? 1 : (r0 == r47 ? 0 : -1))
            if (r4 < 0) goto L_0x026b
            r47 = 4643457506423603200(0x4070e00000000000, double:270.0)
            int r0 = (r0 > r47 ? 1 : (r0 == r47 ? 0 : -1))
            if (r0 > 0) goto L_0x026b
            float r0 = r46 - r43
            android.graphics.Paint r1 = r6.mValuePaint
            android.graphics.Paint$Align r4 = android.graphics.Paint.Align.RIGHT
            r1.setTextAlign(r4)
            if (r13 == 0) goto L_0x0265
            android.graphics.Paint r1 = r6.mEntryLabelsPaint
            r1.setTextAlign(r4)
        L_0x0265:
            float r1 = r0 - r21
            r43 = r0
            r4 = r1
            goto L_0x027e
        L_0x026b:
            float r43 = r46 + r43
            android.graphics.Paint r0 = r6.mValuePaint
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.LEFT
            r0.setTextAlign(r1)
            if (r13 == 0) goto L_0x027b
            android.graphics.Paint r0 = r6.mEntryLabelsPaint
            r0.setTextAlign(r1)
        L_0x027b:
            float r0 = r43 + r21
            r4 = r0
        L_0x027e:
            int r0 = r2.getValueLineColor()
            r1 = 1122867(0x112233, float:1.573472E-39)
            if (r0 == r1) goto L_0x02bc
            boolean r0 = r2.isUsingSliceColorAsValueLineColor()
            if (r0 == 0) goto L_0x0296
            android.graphics.Paint r0 = r6.mValueLinePaint
            int r1 = r2.getColor(r5)
            r0.setColor(r1)
        L_0x0296:
            android.graphics.Paint r1 = r6.mValueLinePaint
            r0 = r51
            r47 = r1
            r1 = r3
            r3 = r2
            r2 = r7
            r7 = r3
            r3 = r46
            r49 = r31
            r31 = r33
            r33 = r4
            r4 = r9
            r48 = r12
            r12 = r5
            r5 = r47
            r0.drawLine(r1, r2, r3, r4, r5)
            android.graphics.Paint r5 = r6.mValueLinePaint
            r1 = r46
            r2 = r9
            r3 = r43
            r0.drawLine(r1, r2, r3, r4, r5)
            goto L_0x02c6
        L_0x02bc:
            r7 = r2
            r48 = r12
            r49 = r31
            r31 = r33
            r33 = r4
            r12 = r5
        L_0x02c6:
            if (r13 == 0) goto L_0x02f8
            if (r14 == 0) goto L_0x02f8
            int r5 = r7.getValueTextColor(r12)
            r0 = r50
            r1 = r51
            r2 = r8
            r3 = r33
            r4 = r9
            r0.drawValue(r1, r2, r3, r4, r5)
            int r0 = r16.getEntryCount()
            if (r12 >= r0) goto L_0x02f1
            if (r41 == 0) goto L_0x02f1
            float r9 = r9 + r26
            r5 = r51
            r3 = r33
            r4 = r41
            r33 = r45
            r6.drawEntryLabel(r5, r4, r3, r9)
        L_0x02ee:
            r14 = r4
            r9 = r5
            goto L_0x032a
        L_0x02f1:
            r33 = r45
            r9 = r51
            r14 = r41
            goto L_0x032a
        L_0x02f8:
            r5 = r51
            r3 = r33
            r4 = r41
            r33 = r45
            if (r13 == 0) goto L_0x0313
            int r0 = r16.getEntryCount()
            if (r12 >= r0) goto L_0x02ee
            if (r4 == 0) goto L_0x02ee
            r0 = 1073741824(0x40000000, float:2.0)
            float r1 = r26 / r0
            float r1 = r1 + r9
            r6.drawEntryLabel(r5, r4, r3, r1)
            goto L_0x02ee
        L_0x0313:
            r0 = 1073741824(0x40000000, float:2.0)
            if (r14 == 0) goto L_0x02ee
            float r1 = r26 / r0
            float r9 = r9 + r1
            int r13 = r7.getValueTextColor(r12)
            r0 = r50
            r1 = r51
            r2 = r8
            r14 = r4
            r4 = r9
            r9 = r5
            r5 = r13
            r0.drawValue(r1, r2, r3, r4, r5)
        L_0x032a:
            if (r44 != 0) goto L_0x0332
            if (r39 == 0) goto L_0x032f
            goto L_0x0332
        L_0x032f:
            r19 = 1073741824(0x40000000, float:2.0)
            goto L_0x038f
        L_0x0332:
            float r0 = r15 * r11
            float r1 = r10.f6574x
            float r13 = r0 + r1
            float r0 = r15 * r48
            float r1 = r10.f6575y
            float r41 = r0 + r1
            android.graphics.Paint r0 = r6.mValuePaint
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.CENTER
            r0.setTextAlign(r1)
            if (r44 == 0) goto L_0x0366
            if (r39 == 0) goto L_0x0366
            int r5 = r7.getValueTextColor(r12)
            r0 = r50
            r1 = r51
            r2 = r8
            r3 = r13
            r4 = r41
            r0.drawValue(r1, r2, r3, r4, r5)
            int r0 = r16.getEntryCount()
            if (r12 >= r0) goto L_0x032f
            if (r14 == 0) goto L_0x032f
            float r0 = r41 + r26
            r6.drawEntryLabel(r9, r14, r13, r0)
            goto L_0x032f
        L_0x0366:
            if (r44 == 0) goto L_0x037a
            int r0 = r16.getEntryCount()
            if (r12 >= r0) goto L_0x032f
            if (r14 == 0) goto L_0x032f
            r19 = 1073741824(0x40000000, float:2.0)
            float r0 = r26 / r19
            float r0 = r0 + r41
            r6.drawEntryLabel(r9, r14, r13, r0)
            goto L_0x038f
        L_0x037a:
            r19 = 1073741824(0x40000000, float:2.0)
            if (r39 == 0) goto L_0x038f
            float r0 = r26 / r19
            float r4 = r0 + r41
            int r5 = r7.getValueTextColor(r12)
            r0 = r50
            r1 = r51
            r2 = r8
            r3 = r13
            r0.drawValue(r1, r2, r3, r4, r5)
        L_0x038f:
            android.graphics.drawable.Drawable r0 = r31.getIcon()
            if (r0 == 0) goto L_0x03c2
            boolean r0 = r7.isDrawIconsEnabled()
            if (r0 == 0) goto L_0x03c2
            android.graphics.drawable.Drawable r1 = r31.getIcon()
            r8 = r49
            float r0 = r8.f6575y
            float r2 = r15 + r0
            float r2 = r2 * r11
            float r3 = r10.f6574x
            float r2 = r2 + r3
            float r0 = r0 + r15
            float r0 = r0 * r48
            float r3 = r10.f6575y
            float r0 = r0 + r3
            float r3 = r8.f6574x
            float r0 = r0 + r3
            int r2 = (int) r2
            int r3 = (int) r0
            int r4 = r1.getIntrinsicWidth()
            int r5 = r1.getIntrinsicHeight()
            r0 = r51
            com.github.mikephil.charting.utils.Utils.drawImage(r0, r1, r2, r3, r4, r5)
            goto L_0x03c4
        L_0x03c2:
            r8 = r49
        L_0x03c4:
            int r24 = r24 + 1
            int r5 = r12 + 1
            r2 = r7
            r4 = r8
            r9 = r28
            r0 = r30
            r8 = r32
            r7 = r33
            r3 = r35
            r11 = r36
            r12 = r37
            r13 = r38
            r14 = r40
            r1 = r42
            r28 = r10
            r10 = r34
            goto L_0x0133
        L_0x03e4:
            r8 = r4
            r34 = r10
            r36 = r11
            r37 = r12
            r38 = r13
            r40 = r14
            r10 = r28
            r18 = 1120403456(0x42c80000, float:100.0)
            r19 = 1073741824(0x40000000, float:2.0)
            r28 = r9
            r9 = r51
            com.github.mikephil.charting.utils.MPPointF.recycleInstance(r8)
            r0 = r24
        L_0x03fe:
            int r3 = r25 + 1
            r7 = r9
            r8 = r10
            r4 = r27
            r9 = r28
            r5 = r29
            r10 = r34
            r11 = r36
            r12 = r37
            r13 = r38
            r14 = r40
            goto L_0x00a2
        L_0x0414:
            r9 = r7
            r10 = r8
            com.github.mikephil.charting.utils.MPPointF.recycleInstance(r10)
            r51.restore()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.PieChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public float getSliceSpace(IPieDataSet iPieDataSet) {
        if (!iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return iPieDataSet.getSliceSpace();
        }
        if (iPieDataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f) {
            return 0.0f;
        }
        return iPieDataSet.getSliceSpace();
    }

    public void initBuffers() {
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (canvas != null) {
            canvas.setBitmap((Bitmap) null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (weakReference != null) {
            Bitmap bitmap = weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
