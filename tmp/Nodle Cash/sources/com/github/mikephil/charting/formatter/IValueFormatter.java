package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

@Deprecated
public interface IValueFormatter {
    @Deprecated
    String getFormattedValue(float f2, Entry entry, int i3, ViewPortHandler viewPortHandler);
}
