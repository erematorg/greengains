package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.utils.ColorTemplate;

public class LegendEntry {
    public Legend.LegendForm form;
    public int formColor;
    public DashPathEffect formLineDashEffect;
    public float formLineWidth;
    public float formSize;
    public String label;

    public LegendEntry() {
        this.form = Legend.LegendForm.DEFAULT;
        this.formSize = Float.NaN;
        this.formLineWidth = Float.NaN;
        this.formLineDashEffect = null;
        this.formColor = ColorTemplate.COLOR_NONE;
    }

    public LegendEntry(String str, Legend.LegendForm legendForm, float f2, float f3, DashPathEffect dashPathEffect, int i3) {
        Legend.LegendForm legendForm2 = Legend.LegendForm.NONE;
        this.label = str;
        this.form = legendForm;
        this.formSize = f2;
        this.formLineWidth = f3;
        this.formLineDashEffect = dashPathEffect;
        this.formColor = i3;
    }
}
