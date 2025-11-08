package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> implements IHighlighter {
    protected T mChart;
    protected List<Highlight> mHighlightBuffer = new ArrayList();

    public ChartHighlighter(T t2) {
        this.mChart = t2;
    }

    public List<Highlight> buildHighlights(IDataSet iDataSet, int i3, float f2, DataSet.Rounding rounding) {
        Entry entryForXValue;
        ArrayList arrayList = new ArrayList();
        List<Entry> entriesForXValue = iDataSet.getEntriesForXValue(f2);
        if (entriesForXValue.size() == 0 && (entryForXValue = iDataSet.getEntryForXValue(f2, Float.NaN, rounding)) != null) {
            entriesForXValue = iDataSet.getEntriesForXValue(entryForXValue.getX());
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            MPPointD pixelForValues = this.mChart.getTransformer(iDataSet.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.f6572x, (float) pixelForValues.f6573y, i3, iDataSet.getAxisDependency()));
        }
        return arrayList;
    }

    public Highlight getClosestHighlightByPixel(List<Highlight> list, float f2, float f3, YAxis.AxisDependency axisDependency, float f4) {
        Highlight highlight = null;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Highlight highlight2 = list.get(i3);
            if (axisDependency == null || highlight2.getAxis() == axisDependency) {
                float distance = getDistance(f2, f3, highlight2.getXPx(), highlight2.getYPx());
                if (distance < f4) {
                    highlight = highlight2;
                    f4 = distance;
                }
            }
        }
        return highlight;
    }

    public BarLineScatterCandleBubbleData getData() {
        return this.mChart.getData();
    }

    public float getDistance(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot((double) (f2 - f4), (double) (f3 - f5));
    }

    public Highlight getHighlight(float f2, float f3) {
        MPPointD valsForTouch = getValsForTouch(f2, f3);
        MPPointD.recycleInstance(valsForTouch);
        return getHighlightForX((float) valsForTouch.f6572x, f2, f3);
    }

    public Highlight getHighlightForX(float f2, float f3, float f4) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(f2, f3, f4);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        float minimumDistance = getMinimumDistance(highlightsAtXValue, f4, axisDependency);
        YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        return getClosestHighlightByPixel(highlightsAtXValue, f3, f4, minimumDistance < getMinimumDistance(highlightsAtXValue, f4, axisDependency2) ? axisDependency : axisDependency2, this.mChart.getMaxHighlightDistance());
    }

    public float getHighlightPos(Highlight highlight) {
        return highlight.getYPx();
    }

    public List<Highlight> getHighlightsAtXValue(float f2, float f3, float f4) {
        this.mHighlightBuffer.clear();
        BarLineScatterCandleBubbleData data = getData();
        if (data == null) {
            return this.mHighlightBuffer;
        }
        int dataSetCount = data.getDataSetCount();
        for (int i3 = 0; i3 < dataSetCount; i3++) {
            IDataSet dataSetByIndex = data.getDataSetByIndex(i3);
            if (dataSetByIndex.isHighlightEnabled()) {
                this.mHighlightBuffer.addAll(buildHighlights(dataSetByIndex, i3, f2, DataSet.Rounding.CLOSEST));
            }
        }
        return this.mHighlightBuffer;
    }

    public float getMinimumDistance(List<Highlight> list, float f2, YAxis.AxisDependency axisDependency) {
        float f3 = Float.MAX_VALUE;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Highlight highlight = list.get(i3);
            if (highlight.getAxis() == axisDependency) {
                float abs = Math.abs(getHighlightPos(highlight) - f2);
                if (abs < f3) {
                    f3 = abs;
                }
            }
        }
        return f3;
    }

    public MPPointD getValsForTouch(float f2, float f3) {
        return this.mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(f2, f3);
    }
}
