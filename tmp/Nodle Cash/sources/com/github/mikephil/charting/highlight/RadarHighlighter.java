package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class RadarHighlighter extends PieRadarHighlighter<RadarChart> {
    public RadarHighlighter(RadarChart radarChart) {
        super(radarChart);
    }

    public Highlight getClosestHighlight(int i3, float f2, float f3) {
        List<Highlight> highlightsAtIndex = getHighlightsAtIndex(i3);
        float distanceToCenter = ((RadarChart) this.mChart).distanceToCenter(f2, f3) / ((RadarChart) this.mChart).getFactor();
        Highlight highlight = null;
        float f4 = Float.MAX_VALUE;
        for (int i4 = 0; i4 < highlightsAtIndex.size(); i4++) {
            Highlight highlight2 = highlightsAtIndex.get(i4);
            float abs = Math.abs(highlight2.getY() - distanceToCenter);
            if (abs < f4) {
                highlight = highlight2;
                f4 = abs;
            }
        }
        return highlight;
    }

    public List<Highlight> getHighlightsAtIndex(int i3) {
        int i4 = i3;
        this.mHighlightBuffer.clear();
        float phaseX = ((RadarChart) this.mChart).getAnimator().getPhaseX();
        float phaseY = ((RadarChart) this.mChart).getAnimator().getPhaseY();
        float sliceAngle = ((RadarChart) this.mChart).getSliceAngle();
        float factor = ((RadarChart) this.mChart).getFactor();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        int i5 = 0;
        while (i5 < ((RadarData) ((RadarChart) this.mChart).getData()).getDataSetCount()) {
            IDataSet dataSetByIndex = ((RadarData) ((RadarChart) this.mChart).getData()).getDataSetByIndex(i5);
            Entry entryForIndex = dataSetByIndex.getEntryForIndex(i4);
            float f2 = (float) i4;
            Utils.getPosition(((RadarChart) this.mChart).getCenterOffsets(), (entryForIndex.getY() - ((RadarChart) this.mChart).getYChartMin()) * factor * phaseY, ((RadarChart) this.mChart).getRotationAngle() + (sliceAngle * f2 * phaseX), instance);
            List<Highlight> list = this.mHighlightBuffer;
            Highlight highlight = r8;
            Highlight highlight2 = new Highlight(f2, entryForIndex.getY(), instance.f6574x, instance.f6575y, i5, dataSetByIndex.getAxisDependency());
            list.add(highlight);
            i5++;
            i4 = i3;
        }
        return this.mHighlightBuffer;
    }
}
