package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import java.util.List;

public interface IDataSet<T extends Entry> {
    boolean addEntry(T t2);

    void addEntryOrdered(T t2);

    void calcMinMax();

    void calcMinMaxY(float f2, float f3);

    void clear();

    boolean contains(T t2);

    YAxis.AxisDependency getAxisDependency();

    int getColor();

    int getColor(int i3);

    List<Integer> getColors();

    List<T> getEntriesForXValue(float f2);

    int getEntryCount();

    T getEntryForIndex(int i3);

    T getEntryForXValue(float f2, float f3);

    T getEntryForXValue(float f2, float f3, DataSet.Rounding rounding);

    int getEntryIndex(float f2, float f3, DataSet.Rounding rounding);

    int getEntryIndex(T t2);

    Legend.LegendForm getForm();

    DashPathEffect getFormLineDashEffect();

    float getFormLineWidth();

    float getFormSize();

    GradientColor getGradientColor();

    GradientColor getGradientColor(int i3);

    List<GradientColor> getGradientColors();

    MPPointF getIconsOffset();

    int getIndexInEntries(int i3);

    String getLabel();

    ValueFormatter getValueFormatter();

    int getValueTextColor();

    int getValueTextColor(int i3);

    float getValueTextSize();

    Typeface getValueTypeface();

    float getXMax();

    float getXMin();

    float getYMax();

    float getYMin();

    boolean isDrawIconsEnabled();

    boolean isDrawValuesEnabled();

    boolean isHighlightEnabled();

    boolean isVisible();

    boolean needsFormatter();

    boolean removeEntry(int i3);

    boolean removeEntry(T t2);

    boolean removeEntryByXValue(float f2);

    boolean removeFirst();

    boolean removeLast();

    void setAxisDependency(YAxis.AxisDependency axisDependency);

    void setDrawIcons(boolean z2);

    void setDrawValues(boolean z2);

    void setHighlightEnabled(boolean z2);

    void setIconsOffset(MPPointF mPPointF);

    void setLabel(String str);

    void setValueFormatter(ValueFormatter valueFormatter);

    void setValueTextColor(int i3);

    void setValueTextColors(List<Integer> list);

    void setValueTextSize(float f2);

    void setValueTypeface(Typeface typeface);

    void setVisible(boolean z2);
}
