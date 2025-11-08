package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import java.text.DecimalFormat;

public class PercentFormatter extends ValueFormatter {
    public DecimalFormat mFormat;
    private PieChart pieChart;

    public PercentFormatter() {
        this.mFormat = new DecimalFormat("###,###,##0.0");
    }

    public String getFormattedValue(float f2) {
        return this.mFormat.format((double) f2) + " %";
    }

    public String getPieLabel(float f2, PieEntry pieEntry) {
        PieChart pieChart2 = this.pieChart;
        return (pieChart2 == null || !pieChart2.isUsePercentValuesEnabled()) ? this.mFormat.format((double) f2) : getFormattedValue(f2);
    }

    public PercentFormatter(PieChart pieChart2) {
        this();
        this.pieChart = pieChart2;
    }
}
