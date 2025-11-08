package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class ValueFormatter implements IAxisValueFormatter, IValueFormatter {
    public String getAxisLabel(float f2, AxisBase axisBase) {
        return getFormattedValue(f2);
    }

    public String getBarLabel(BarEntry barEntry) {
        return getFormattedValue(barEntry.getY());
    }

    public String getBarStackedLabel(float f2, BarEntry barEntry) {
        return getFormattedValue(f2);
    }

    public String getBubbleLabel(BubbleEntry bubbleEntry) {
        return getFormattedValue(bubbleEntry.getSize());
    }

    public String getCandleLabel(CandleEntry candleEntry) {
        return getFormattedValue(candleEntry.getHigh());
    }

    @Deprecated
    public String getFormattedValue(float f2, AxisBase axisBase) {
        return getFormattedValue(f2);
    }

    public String getPieLabel(float f2, PieEntry pieEntry) {
        return getFormattedValue(f2);
    }

    public String getPointLabel(Entry entry) {
        return getFormattedValue(entry.getY());
    }

    public String getRadarLabel(RadarEntry radarEntry) {
        return getFormattedValue(radarEntry.getY());
    }

    @Deprecated
    public String getFormattedValue(float f2, Entry entry, int i3, ViewPortHandler viewPortHandler) {
        return getFormattedValue(f2);
    }

    public String getFormattedValue(float f2) {
        return String.valueOf(f2);
    }
}
