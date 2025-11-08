package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.PieHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class PieChart extends PieRadarChartBase<PieData> {
    private float[] mAbsoluteAngles = new float[1];
    private CharSequence mCenterText = "";
    private MPPointF mCenterTextOffset = MPPointF.getInstance(0.0f, 0.0f);
    private float mCenterTextRadiusPercent = 100.0f;
    private RectF mCircleBox = new RectF();
    private float[] mDrawAngles = new float[1];
    private boolean mDrawCenterText = true;
    private boolean mDrawEntryLabels = true;
    private boolean mDrawHole = true;
    private boolean mDrawRoundedSlices = false;
    private boolean mDrawSlicesUnderHole = false;
    private float mHoleRadiusPercent = 50.0f;
    protected float mMaxAngle = 360.0f;
    private float mMinAngleForSlices = 0.0f;
    protected float mTransparentCircleRadiusPercent = 55.0f;
    private boolean mUsePercentValues = false;

    public PieChart(Context context) {
        super(context);
    }

    private float calcAngle(float f2) {
        return calcAngle(f2, ((PieData) this.mData).getYValueSum());
    }

    private void calcAngles() {
        int entryCount = ((PieData) this.mData).getEntryCount();
        if (this.mDrawAngles.length != entryCount) {
            this.mDrawAngles = new float[entryCount];
        } else {
            for (int i3 = 0; i3 < entryCount; i3++) {
                this.mDrawAngles[i3] = 0.0f;
            }
        }
        if (this.mAbsoluteAngles.length != entryCount) {
            this.mAbsoluteAngles = new float[entryCount];
        } else {
            for (int i4 = 0; i4 < entryCount; i4++) {
                this.mAbsoluteAngles[i4] = 0.0f;
            }
        }
        float yValueSum = ((PieData) this.mData).getYValueSum();
        List dataSets = ((PieData) this.mData).getDataSets();
        float f2 = this.mMinAngleForSlices;
        boolean z2 = f2 != 0.0f && ((float) entryCount) * f2 <= this.mMaxAngle;
        float[] fArr = new float[entryCount];
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i5 = 0;
        for (int i6 = 0; i6 < ((PieData) this.mData).getDataSetCount(); i6++) {
            IPieDataSet iPieDataSet = (IPieDataSet) dataSets.get(i6);
            for (int i7 = 0; i7 < iPieDataSet.getEntryCount(); i7++) {
                float calcAngle = calcAngle(Math.abs(((PieEntry) iPieDataSet.getEntryForIndex(i7)).getY()), yValueSum);
                if (z2) {
                    float f5 = this.mMinAngleForSlices;
                    float f6 = calcAngle - f5;
                    if (f6 <= 0.0f) {
                        fArr[i5] = f5;
                        f3 += -f6;
                    } else {
                        fArr[i5] = calcAngle;
                        f4 += f6;
                    }
                }
                this.mDrawAngles[i5] = calcAngle;
                if (i5 == 0) {
                    this.mAbsoluteAngles[i5] = calcAngle;
                } else {
                    float[] fArr2 = this.mAbsoluteAngles;
                    fArr2[i5] = fArr2[i5 - 1] + calcAngle;
                }
                i5++;
            }
        }
        if (z2) {
            for (int i8 = 0; i8 < entryCount; i8++) {
                float f7 = fArr[i8];
                float f8 = f7 - (((f7 - this.mMinAngleForSlices) / f4) * f3);
                fArr[i8] = f8;
                if (i8 == 0) {
                    this.mAbsoluteAngles[0] = fArr[0];
                } else {
                    float[] fArr3 = this.mAbsoluteAngles;
                    fArr3[i8] = fArr3[i8 - 1] + f8;
                }
            }
            this.mDrawAngles = fArr;
        }
    }

    public void calcMinMax() {
        calcAngles();
    }

    public void calculateOffsets() {
        super.calculateOffsets();
        if (this.mData != null) {
            float diameter = getDiameter() / 2.0f;
            MPPointF centerOffsets = getCenterOffsets();
            float selectionShift = ((PieData) this.mData).getDataSet().getSelectionShift();
            RectF rectF = this.mCircleBox;
            float f2 = centerOffsets.f6574x;
            float f3 = centerOffsets.f6575y;
            rectF.set((f2 - diameter) + selectionShift, (f3 - diameter) + selectionShift, (f2 + diameter) - selectionShift, (f3 + diameter) - selectionShift);
            MPPointF.recycleInstance(centerOffsets);
        }
    }

    public float[] getAbsoluteAngles() {
        return this.mAbsoluteAngles;
    }

    public MPPointF getCenterCircleBox() {
        return MPPointF.getInstance(this.mCircleBox.centerX(), this.mCircleBox.centerY());
    }

    public CharSequence getCenterText() {
        return this.mCenterText;
    }

    public MPPointF getCenterTextOffset() {
        MPPointF mPPointF = this.mCenterTextOffset;
        return MPPointF.getInstance(mPPointF.f6574x, mPPointF.f6575y);
    }

    public float getCenterTextRadiusPercent() {
        return this.mCenterTextRadiusPercent;
    }

    public RectF getCircleBox() {
        return this.mCircleBox;
    }

    public int getDataSetIndexForIndex(int i3) {
        List dataSets = ((PieData) this.mData).getDataSets();
        for (int i4 = 0; i4 < dataSets.size(); i4++) {
            if (((IPieDataSet) dataSets.get(i4)).getEntryForXValue((float) i3, Float.NaN) != null) {
                return i4;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.mDrawAngles;
    }

    public float getHoleRadius() {
        return this.mHoleRadiusPercent;
    }

    public int getIndexForAngle(float f2) {
        float normalizedAngle = Utils.getNormalizedAngle(f2 - getRotationAngle());
        int i3 = 0;
        while (true) {
            float[] fArr = this.mAbsoluteAngles;
            if (i3 >= fArr.length) {
                return -1;
            }
            if (fArr[i3] > normalizedAngle) {
                return i3;
            }
            i3++;
        }
    }

    public float[] getMarkerPosition(Highlight highlight) {
        MPPointF centerCircleBox = getCenterCircleBox();
        float radius = getRadius();
        float f2 = (radius / 10.0f) * 3.6f;
        if (isDrawHoleEnabled()) {
            f2 = (radius - (getHoleRadius() * (radius / 100.0f))) / 2.0f;
        }
        float f3 = radius - f2;
        float rotationAngle = getRotationAngle();
        int x2 = (int) highlight.getX();
        float f4 = this.mDrawAngles[x2] / 2.0f;
        double d2 = (double) f3;
        float cos = (float) ((Math.cos(Math.toRadians((double) (this.mAnimator.getPhaseY() * ((this.mAbsoluteAngles[x2] + rotationAngle) - f4)))) * d2) + ((double) centerCircleBox.f6574x));
        float phaseY = this.mAnimator.getPhaseY();
        MPPointF.recycleInstance(centerCircleBox);
        return new float[]{cos, (float) ((Math.sin(Math.toRadians((double) (phaseY * ((rotationAngle + this.mAbsoluteAngles[x2]) - f4)))) * d2) + ((double) centerCircleBox.f6575y))};
    }

    public float getMaxAngle() {
        return this.mMaxAngle;
    }

    public float getMinAngleForSlices() {
        return this.mMinAngleForSlices;
    }

    public float getRadius() {
        RectF rectF = this.mCircleBox;
        if (rectF == null) {
            return 0.0f;
        }
        return Math.min(rectF.width() / 2.0f, this.mCircleBox.height() / 2.0f);
    }

    public float getRequiredBaseOffset() {
        return 0.0f;
    }

    public float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 2.0f;
    }

    public float getTransparentCircleRadius() {
        return this.mTransparentCircleRadiusPercent;
    }

    @Deprecated
    public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }

    public void init() {
        super.init();
        this.mRenderer = new PieChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis = null;
        this.mHighlighter = new PieHighlighter(this);
    }

    public boolean isDrawCenterTextEnabled() {
        return this.mDrawCenterText;
    }

    public boolean isDrawEntryLabelsEnabled() {
        return this.mDrawEntryLabels;
    }

    public boolean isDrawHoleEnabled() {
        return this.mDrawHole;
    }

    public boolean isDrawRoundedSlicesEnabled() {
        return this.mDrawRoundedSlices;
    }

    public boolean isDrawSlicesUnderHoleEnabled() {
        return this.mDrawSlicesUnderHole;
    }

    public boolean isUsePercentValuesEnabled() {
        return this.mUsePercentValues;
    }

    public boolean needsHighlight(int i3) {
        if (!valuesToHighlight()) {
            return false;
        }
        int i4 = 0;
        while (true) {
            Highlight[] highlightArr = this.mIndicesToHighlight;
            if (i4 >= highlightArr.length) {
                return false;
            }
            if (((int) highlightArr[i4].getX()) == i3) {
                return true;
            }
            i4++;
        }
    }

    public void onDetachedFromWindow() {
        DataRenderer dataRenderer = this.mRenderer;
        if (dataRenderer != null && (dataRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) dataRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            this.mRenderer.drawExtras(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
        }
    }

    public void setCenterText(CharSequence charSequence) {
        if (charSequence == null) {
            this.mCenterText = "";
        } else {
            this.mCenterText = charSequence;
        }
    }

    public void setCenterTextColor(int i3) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setColor(i3);
    }

    public void setCenterTextOffset(float f2, float f3) {
        this.mCenterTextOffset.f6574x = Utils.convertDpToPixel(f2);
        this.mCenterTextOffset.f6575y = Utils.convertDpToPixel(f3);
    }

    public void setCenterTextRadiusPercent(float f2) {
        this.mCenterTextRadiusPercent = f2;
    }

    public void setCenterTextSize(float f2) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(Utils.convertDpToPixel(f2));
    }

    public void setCenterTextSizePixels(float f2) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(f2);
    }

    public void setCenterTextTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTypeface(typeface);
    }

    public void setDrawCenterText(boolean z2) {
        this.mDrawCenterText = z2;
    }

    public void setDrawEntryLabels(boolean z2) {
        this.mDrawEntryLabels = z2;
    }

    public void setDrawHoleEnabled(boolean z2) {
        this.mDrawHole = z2;
    }

    public void setDrawRoundedSlices(boolean z2) {
        this.mDrawRoundedSlices = z2;
    }

    @Deprecated
    public void setDrawSliceText(boolean z2) {
        this.mDrawEntryLabels = z2;
    }

    public void setDrawSlicesUnderHole(boolean z2) {
        this.mDrawSlicesUnderHole = z2;
    }

    public void setEntryLabelColor(int i3) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setColor(i3);
    }

    public void setEntryLabelTextSize(float f2) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(f2));
    }

    public void setEntryLabelTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTypeface(typeface);
    }

    public void setHoleColor(int i3) {
        ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(i3);
    }

    public void setHoleRadius(float f2) {
        this.mHoleRadiusPercent = f2;
    }

    public void setMaxAngle(float f2) {
        if (f2 > 360.0f) {
            f2 = 360.0f;
        }
        if (f2 < 90.0f) {
            f2 = 90.0f;
        }
        this.mMaxAngle = f2;
    }

    public void setMinAngleForSlices(float f2) {
        float f3 = this.mMaxAngle;
        if (f2 > f3 / 2.0f) {
            f2 = f3 / 2.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mMinAngleForSlices = f2;
    }

    public void setTransparentCircleAlpha(int i3) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle().setAlpha(i3);
    }

    public void setTransparentCircleColor(int i3) {
        Paint paintTransparentCircle = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle();
        int alpha = paintTransparentCircle.getAlpha();
        paintTransparentCircle.setColor(i3);
        paintTransparentCircle.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float f2) {
        this.mTransparentCircleRadiusPercent = f2;
    }

    public void setUsePercentValues(boolean z2) {
        this.mUsePercentValues = z2;
    }

    private float calcAngle(float f2, float f3) {
        return (f2 / f3) * this.mMaxAngle;
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PieChart(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
