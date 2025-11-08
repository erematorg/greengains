package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.highlight.RadarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart;
import com.github.mikephil.charting.renderer.YAxisRendererRadarChart;
import com.github.mikephil.charting.utils.Utils;

public class RadarChart extends PieRadarChartBase<RadarData> {
    private boolean mDrawWeb = true;
    private float mInnerWebLineWidth = 1.5f;
    private int mSkipWebLineCount = 0;
    private int mWebAlpha = 150;
    private int mWebColor = Color.rgb(122, 122, 122);
    private int mWebColorInner = Color.rgb(122, 122, 122);
    private float mWebLineWidth = 2.5f;
    protected XAxisRendererRadarChart mXAxisRenderer;
    private YAxis mYAxis;
    protected YAxisRendererRadarChart mYAxisRenderer;

    public RadarChart(Context context) {
        super(context);
    }

    public void calcMinMax() {
        super.calcMinMax();
        YAxis yAxis = this.mYAxis;
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate(((RadarData) this.mData).getYMin(axisDependency), ((RadarData) this.mData).getYMax(axisDependency));
        this.mXAxis.calculate(0.0f, (float) ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount());
    }

    public float getFactor() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f) / this.mYAxis.mAxisRange;
    }

    public int getIndexForAngle(float f2) {
        float normalizedAngle = Utils.getNormalizedAngle(f2 - getRotationAngle());
        float sliceAngle = getSliceAngle();
        int entryCount = ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount();
        int i3 = 0;
        while (i3 < entryCount) {
            int i4 = i3 + 1;
            if ((((float) i4) * sliceAngle) - (sliceAngle / 2.0f) > normalizedAngle) {
                return i3;
            }
            i3 = i4;
        }
        return 0;
    }

    public float getRadius() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f);
    }

    public float getRequiredBaseOffset() {
        return (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled()) ? Utils.convertDpToPixel(10.0f) : (float) this.mXAxis.mLabelRotatedWidth;
    }

    public float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 4.0f;
    }

    public int getSkipWebLineCount() {
        return this.mSkipWebLineCount;
    }

    public float getSliceAngle() {
        return 360.0f / ((float) ((IRadarDataSet) ((RadarData) this.mData).getMaxEntryCountSet()).getEntryCount());
    }

    public int getWebAlpha() {
        return this.mWebAlpha;
    }

    public int getWebColor() {
        return this.mWebColor;
    }

    public int getWebColorInner() {
        return this.mWebColorInner;
    }

    public float getWebLineWidth() {
        return this.mWebLineWidth;
    }

    public float getWebLineWidthInner() {
        return this.mInnerWebLineWidth;
    }

    public YAxis getYAxis() {
        return this.mYAxis;
    }

    public float getYChartMax() {
        return this.mYAxis.mAxisMaximum;
    }

    public float getYChartMin() {
        return this.mYAxis.mAxisMinimum;
    }

    public float getYRange() {
        return this.mYAxis.mAxisRange;
    }

    public void init() {
        super.init();
        this.mYAxis = new YAxis(YAxis.AxisDependency.LEFT);
        this.mWebLineWidth = Utils.convertDpToPixel(1.5f);
        this.mInnerWebLineWidth = Utils.convertDpToPixel(0.75f);
        this.mRenderer = new RadarChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mYAxisRenderer = new YAxisRendererRadarChart(this.mViewPortHandler, this.mYAxis, this);
        this.mXAxisRenderer = new XAxisRendererRadarChart(this.mViewPortHandler, this.mXAxis, this);
        this.mHighlighter = new RadarHighlighter(this);
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            calcMinMax();
            YAxisRendererRadarChart yAxisRendererRadarChart = this.mYAxisRenderer;
            YAxis yAxis = this.mYAxis;
            yAxisRendererRadarChart.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
            XAxisRendererRadarChart xAxisRendererRadarChart = this.mXAxisRenderer;
            XAxis xAxis = this.mXAxis;
            xAxisRendererRadarChart.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
            Legend legend = this.mLegend;
            if (legend != null && !legend.isLegendCustom()) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            if (this.mXAxis.isEnabled()) {
                XAxisRendererRadarChart xAxisRendererRadarChart = this.mXAxisRenderer;
                XAxis xAxis = this.mXAxis;
                xAxisRendererRadarChart.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
            }
            this.mXAxisRenderer.renderAxisLabels(canvas);
            if (this.mDrawWeb) {
                this.mRenderer.drawExtras(canvas);
            }
            if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mYAxisRenderer.renderLimitLines(canvas);
            }
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            if (this.mYAxis.isEnabled() && !this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mYAxisRenderer.renderLimitLines(canvas);
            }
            this.mYAxisRenderer.renderAxisLabels(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
        }
    }

    public void setDrawWeb(boolean z2) {
        this.mDrawWeb = z2;
    }

    public void setSkipWebLineCount(int i3) {
        this.mSkipWebLineCount = Math.max(0, i3);
    }

    public void setWebAlpha(int i3) {
        this.mWebAlpha = i3;
    }

    public void setWebColor(int i3) {
        this.mWebColor = i3;
    }

    public void setWebColorInner(int i3) {
        this.mWebColorInner = i3;
    }

    public void setWebLineWidth(float f2) {
        this.mWebLineWidth = Utils.convertDpToPixel(f2);
    }

    public void setWebLineWidthInner(float f2) {
        this.mInnerWebLineWidth = Utils.convertDpToPixel(f2);
    }

    public RadarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadarChart(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
