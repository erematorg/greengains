package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AxisRenderer extends Renderer {
    protected AxisBase mAxis;
    protected Paint mAxisLabelPaint;
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer transformer, AxisBase axisBase) {
        super(viewPortHandler);
        this.mTrans = transformer;
        this.mAxis = axisBase;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint = new Paint(1);
            Paint paint = new Paint();
            this.mGridPaint = paint;
            paint.setColor(-7829368);
            this.mGridPaint.setStrokeWidth(1.0f);
            Paint paint2 = this.mGridPaint;
            Paint.Style style = Paint.Style.STROKE;
            paint2.setStyle(style);
            this.mGridPaint.setAlpha(90);
            Paint paint3 = new Paint();
            this.mAxisLinePaint = paint3;
            paint3.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLinePaint.setStrokeWidth(1.0f);
            this.mAxisLinePaint.setStyle(style);
            Paint paint4 = new Paint(1);
            this.mLimitLinePaint = paint4;
            paint4.setStyle(style);
        }
    }

    public void computeAxis(float f2, float f3, boolean z2) {
        float f4;
        double d2;
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (viewPortHandler != null && viewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutY()) {
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            if (!z2) {
                f4 = (float) valuesByTouchPoint2.f6573y;
                d2 = valuesByTouchPoint.f6573y;
            } else {
                f4 = (float) valuesByTouchPoint.f6573y;
                d2 = valuesByTouchPoint2.f6573y;
            }
            float f5 = (float) d2;
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f3 = f5;
        }
        computeAxisValues(f2, f3);
    }

    public void computeAxisValues(float f2, float f3) {
        float f4 = f2;
        float f5 = f3;
        int labelCount = this.mAxis.getLabelCount();
        double abs = (double) Math.abs(f5 - f4);
        if (labelCount == 0 || abs <= 0.0d || Double.isInfinite(abs)) {
            AxisBase axisBase = this.mAxis;
            axisBase.mEntries = new float[0];
            axisBase.mCenteredEntries = new float[0];
            axisBase.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = (double) Utils.roundToNextSignificant(abs / ((double) labelCount));
        if (this.mAxis.isGranularityEnabled() && roundToNextSignificant < ((double) this.mAxis.getGranularity())) {
            roundToNextSignificant = (double) this.mAxis.getGranularity();
        }
        double roundToNextSignificant2 = (double) Utils.roundToNextSignificant(Math.pow(10.0d, (double) ((int) Math.log10(roundToNextSignificant))));
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
        }
        int isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
        if (this.mAxis.isForceLabelsEnabled()) {
            roundToNextSignificant = (double) (((float) abs) / ((float) (labelCount - 1)));
            AxisBase axisBase2 = this.mAxis;
            axisBase2.mEntryCount = labelCount;
            if (axisBase2.mEntries.length < labelCount) {
                axisBase2.mEntries = new float[labelCount];
            }
            for (int i3 = 0; i3 < labelCount; i3++) {
                this.mAxis.mEntries[i3] = f4;
                f4 = (float) (((double) f4) + roundToNextSignificant);
            }
        } else {
            int i4 = (roundToNextSignificant > 0.0d ? 1 : (roundToNextSignificant == 0.0d ? 0 : -1));
            double ceil = i4 == 0 ? 0.0d : Math.ceil(((double) f4) / roundToNextSignificant) * roundToNextSignificant;
            if (this.mAxis.isCenterAxisLabelsEnabled()) {
                ceil -= roundToNextSignificant;
            }
            double nextUp = i4 == 0 ? 0.0d : Utils.nextUp(Math.floor(((double) f5) / roundToNextSignificant) * roundToNextSignificant);
            if (i4 != 0) {
                for (double d2 = ceil; d2 <= nextUp; d2 += roundToNextSignificant) {
                    isCenterAxisLabelsEnabled++;
                }
            }
            AxisBase axisBase3 = this.mAxis;
            axisBase3.mEntryCount = isCenterAxisLabelsEnabled;
            if (axisBase3.mEntries.length < isCenterAxisLabelsEnabled) {
                axisBase3.mEntries = new float[isCenterAxisLabelsEnabled];
            }
            for (int i5 = 0; i5 < isCenterAxisLabelsEnabled; i5++) {
                if (ceil == 0.0d) {
                    ceil = 0.0d;
                }
                this.mAxis.mEntries[i5] = (float) ceil;
                ceil += roundToNextSignificant;
            }
            labelCount = isCenterAxisLabelsEnabled;
        }
        if (roundToNextSignificant < 1.0d) {
            this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            this.mAxis.mDecimals = 0;
        }
        if (this.mAxis.isCenterAxisLabelsEnabled()) {
            AxisBase axisBase4 = this.mAxis;
            if (axisBase4.mCenteredEntries.length < labelCount) {
                axisBase4.mCenteredEntries = new float[labelCount];
            }
            float f6 = ((float) roundToNextSignificant) / 2.0f;
            for (int i6 = 0; i6 < labelCount; i6++) {
                AxisBase axisBase5 = this.mAxis;
                axisBase5.mCenteredEntries[i6] = axisBase5.mEntries[i6] + f6;
            }
        }
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);
}
