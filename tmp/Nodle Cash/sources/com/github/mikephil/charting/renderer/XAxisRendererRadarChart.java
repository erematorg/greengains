package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XAxisRendererRadarChart extends XAxisRenderer {
    private RadarChart mChart;

    public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart radarChart) {
        super(viewPortHandler, xAxis, (Transformer) null);
        this.mChart = radarChart;
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
            MPPointF instance = MPPointF.getInstance(0.5f, 0.25f);
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            float sliceAngle = this.mChart.getSliceAngle();
            float factor = this.mChart.getFactor();
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
            for (int i3 = 0; i3 < ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount(); i3++) {
                float f2 = (float) i3;
                String axisLabel = this.mXAxis.getValueFormatter().getAxisLabel(f2, this.mXAxis);
                Utils.getPosition(centerOffsets, (((float) this.mXAxis.mLabelRotatedWidth) / 2.0f) + (this.mChart.getYRange() * factor), (this.mChart.getRotationAngle() + (f2 * sliceAngle)) % 360.0f, instance2);
                drawLabel(canvas, axisLabel, instance2.f6574x, instance2.f6575y - (((float) this.mXAxis.mLabelRotatedHeight) / 2.0f), instance, labelRotationAngle);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(instance2);
            MPPointF.recycleInstance(instance);
        }
    }

    public void renderLimitLines(Canvas canvas) {
    }
}
