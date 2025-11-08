package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.CombinedHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.renderer.CombinedChartRenderer;

public class CombinedChart extends BarLineChartBase<CombinedData> implements CombinedDataProvider {
    private boolean mDrawBarShadow = false;
    protected DrawOrder[] mDrawOrder;
    private boolean mDrawValueAboveBar = true;
    protected boolean mHighlightFullBarEnabled = false;

    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
    }

    public void drawMarkers(Canvas canvas) {
        if (this.mMarker != null && isDrawMarkersEnabled() && valuesToHighlight()) {
            int i3 = 0;
            while (true) {
                Highlight[] highlightArr = this.mIndicesToHighlight;
                if (i3 < highlightArr.length) {
                    Highlight highlight = highlightArr[i3];
                    IBarLineScatterCandleBubbleDataSet<? extends Entry> dataSetByHighlight = ((CombinedData) this.mData).getDataSetByHighlight(highlight);
                    Entry entryForHighlight = ((CombinedData) this.mData).getEntryForHighlight(highlight);
                    if (entryForHighlight != null) {
                        if (((float) dataSetByHighlight.getEntryIndex(entryForHighlight)) <= this.mAnimator.getPhaseX() * ((float) dataSetByHighlight.getEntryCount())) {
                            float[] markerPosition = getMarkerPosition(highlight);
                            if (this.mViewPortHandler.isInBounds(markerPosition[0], markerPosition[1])) {
                                this.mMarker.refreshContent(entryForHighlight, highlight);
                                this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                            }
                        }
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public BarData getBarData() {
        T t2 = this.mData;
        if (t2 == null) {
            return null;
        }
        return ((CombinedData) t2).getBarData();
    }

    public BubbleData getBubbleData() {
        T t2 = this.mData;
        if (t2 == null) {
            return null;
        }
        return ((CombinedData) t2).getBubbleData();
    }

    public CandleData getCandleData() {
        T t2 = this.mData;
        if (t2 == null) {
            return null;
        }
        return ((CombinedData) t2).getCandleData();
    }

    public CombinedData getCombinedData() {
        return (CombinedData) this.mData;
    }

    public DrawOrder[] getDrawOrder() {
        return this.mDrawOrder;
    }

    public Highlight getHighlightByTouchPoint(float f2, float f3) {
        if (this.mData == null) {
            Log.e(Chart.LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        Highlight highlight = getHighlighter().getHighlight(f2, f3);
        return (highlight == null || !isHighlightFullBarEnabled()) ? highlight : new Highlight(highlight.getX(), highlight.getY(), highlight.getXPx(), highlight.getYPx(), highlight.getDataSetIndex(), -1, highlight.getAxis());
    }

    public LineData getLineData() {
        T t2 = this.mData;
        if (t2 == null) {
            return null;
        }
        return ((CombinedData) t2).getLineData();
    }

    public ScatterData getScatterData() {
        T t2 = this.mData;
        if (t2 == null) {
            return null;
        }
        return ((CombinedData) t2).getScatterData();
    }

    public void init() {
        super.init();
        this.mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
        setHighlighter(new CombinedHighlighter(this, this));
        setHighlightFullBarEnabled(true);
        this.mRenderer = new CombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    public boolean isDrawBarShadowEnabled() {
        return this.mDrawBarShadow;
    }

    public boolean isDrawValueAboveBarEnabled() {
        return this.mDrawValueAboveBar;
    }

    public boolean isHighlightFullBarEnabled() {
        return this.mHighlightFullBarEnabled;
    }

    public void setDrawBarShadow(boolean z2) {
        this.mDrawBarShadow = z2;
    }

    public void setDrawOrder(DrawOrder[] drawOrderArr) {
        if (drawOrderArr != null && drawOrderArr.length > 0) {
            this.mDrawOrder = drawOrderArr;
        }
    }

    public void setDrawValueAboveBar(boolean z2) {
        this.mDrawValueAboveBar = z2;
    }

    public void setHighlightFullBarEnabled(boolean z2) {
        this.mHighlightFullBarEnabled = z2;
    }

    public void setData(CombinedData combinedData) {
        super.setData(combinedData);
        setHighlighter(new CombinedHighlighter(this, this));
        ((CombinedChartRenderer) this.mRenderer).createRenderers();
        this.mRenderer.initBuffers();
    }

    public CombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CombinedChart(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
