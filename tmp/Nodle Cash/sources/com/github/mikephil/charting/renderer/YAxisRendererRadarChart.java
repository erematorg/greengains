package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class YAxisRendererRadarChart extends YAxisRenderer {
    private RadarChart mChart;
    private Path mRenderLimitLinesPathBuffer = new Path();

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart radarChart) {
        super(viewPortHandler, yAxis, (Transformer) null);
        this.mChart = radarChart;
    }

    public void computeAxisValues(float f2, float f3) {
        boolean z2;
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
        boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
        if (this.mAxis.isForceLabelsEnabled()) {
            float f6 = ((float) abs) / ((float) (labelCount - 1));
            AxisBase axisBase2 = this.mAxis;
            axisBase2.mEntryCount = labelCount;
            if (axisBase2.mEntries.length < labelCount) {
                axisBase2.mEntries = new float[labelCount];
            }
            for (int i3 = 0; i3 < labelCount; i3++) {
                this.mAxis.mEntries[i3] = f4;
                f4 += f6;
            }
        } else {
            int i4 = (roundToNextSignificant > 0.0d ? 1 : (roundToNextSignificant == 0.0d ? 0 : -1));
            double ceil = i4 == 0 ? 0.0d : Math.ceil(((double) f4) / roundToNextSignificant) * roundToNextSignificant;
            if (isCenterAxisLabelsEnabled) {
                ceil -= roundToNextSignificant;
            }
            double nextUp = i4 == 0 ? 0.0d : Utils.nextUp(Math.floor(((double) f5) / roundToNextSignificant) * roundToNextSignificant);
            if (i4 != 0) {
                z2 = isCenterAxisLabelsEnabled;
                for (double d2 = ceil; d2 <= nextUp; d2 += roundToNextSignificant) {
                    z2++;
                }
            } else {
                z2 = isCenterAxisLabelsEnabled;
            }
            int i5 = ((int) z2) + 1;
            AxisBase axisBase3 = this.mAxis;
            axisBase3.mEntryCount = i5;
            if (axisBase3.mEntries.length < i5) {
                axisBase3.mEntries = new float[i5];
            }
            for (int i6 = 0; i6 < i5; i6++) {
                if (ceil == 0.0d) {
                    ceil = 0.0d;
                }
                this.mAxis.mEntries[i6] = (float) ceil;
                ceil += roundToNextSignificant;
            }
            labelCount = i5;
        }
        if (roundToNextSignificant < 1.0d) {
            this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            this.mAxis.mDecimals = 0;
        }
        if (isCenterAxisLabelsEnabled) {
            AxisBase axisBase4 = this.mAxis;
            if (axisBase4.mCenteredEntries.length < labelCount) {
                axisBase4.mCenteredEntries = new float[labelCount];
            }
            float[] fArr = axisBase4.mEntries;
            float f7 = (fArr[1] - fArr[0]) / 2.0f;
            for (int i7 = 0; i7 < labelCount; i7++) {
                AxisBase axisBase5 = this.mAxis;
                axisBase5.mCenteredEntries[i7] = axisBase5.mEntries[i7] + f7;
            }
        }
        AxisBase axisBase6 = this.mAxis;
        float[] fArr2 = axisBase6.mEntries;
        float f8 = fArr2[0];
        axisBase6.mAxisMinimum = f8;
        float f9 = fArr2[labelCount - 1];
        axisBase6.mAxisMaximum = f9;
        axisBase6.mAxisRange = Math.abs(f9 - f8);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            float factor = this.mChart.getFactor();
            int i3 = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
            for (int i4 = !this.mYAxis.isDrawBottomYLabelEntryEnabled(); i4 < i3; i4++) {
                YAxis yAxis = this.mYAxis;
                Utils.getPosition(centerOffsets, (yAxis.mEntries[i4] - yAxis.mAxisMinimum) * factor, this.mChart.getRotationAngle(), instance);
                canvas.drawText(this.mYAxis.getFormattedLabel(i4), instance.f6574x + 10.0f, instance.f6575y, this.mAxisLabelPaint);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(instance);
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines != null) {
            float sliceAngle = this.mChart.getSliceAngle();
            float factor = this.mChart.getFactor();
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            for (int i3 = 0; i3 < limitLines.size(); i3++) {
                LimitLine limitLine = limitLines.get(i3);
                if (limitLine.isEnabled()) {
                    this.mLimitLinePaint.setColor(limitLine.getLineColor());
                    this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                    this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                    float limit = (limitLine.getLimit() - this.mChart.getYChartMin()) * factor;
                    Path path = this.mRenderLimitLinesPathBuffer;
                    path.reset();
                    for (int i4 = 0; i4 < ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount(); i4++) {
                        Utils.getPosition(centerOffsets, limit, this.mChart.getRotationAngle() + (((float) i4) * sliceAngle), instance);
                        if (i4 == 0) {
                            path.moveTo(instance.f6574x, instance.f6575y);
                        } else {
                            path.lineTo(instance.f6574x, instance.f6575y);
                        }
                    }
                    path.close();
                    canvas.drawPath(path, this.mLimitLinePaint);
                }
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(instance);
        }
    }
}
