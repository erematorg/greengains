package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet<T extends Entry> implements IDataSet<T> {
    protected YAxis.AxisDependency mAxisDependency;
    protected List<Integer> mColors;
    protected boolean mDrawIcons;
    protected boolean mDrawValues;
    private Legend.LegendForm mForm;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    protected GradientColor mGradientColor;
    protected List<GradientColor> mGradientColors;
    protected boolean mHighlightEnabled;
    protected MPPointF mIconsOffset;
    private String mLabel;
    protected List<Integer> mValueColors;
    protected transient ValueFormatter mValueFormatter;
    protected float mValueTextSize;
    protected Typeface mValueTypeface;
    protected boolean mVisible;

    public BaseDataSet() {
        this.mColors = null;
        this.mGradientColor = null;
        this.mGradientColors = null;
        this.mValueColors = null;
        this.mLabel = "DataSet";
        this.mAxisDependency = YAxis.AxisDependency.LEFT;
        this.mHighlightEnabled = true;
        this.mForm = Legend.LegendForm.DEFAULT;
        this.mFormSize = Float.NaN;
        this.mFormLineWidth = Float.NaN;
        this.mFormLineDashEffect = null;
        this.mDrawValues = true;
        this.mDrawIcons = true;
        this.mIconsOffset = new MPPointF();
        this.mValueTextSize = 17.0f;
        this.mVisible = true;
        this.mColors = new ArrayList();
        this.mValueColors = new ArrayList();
        this.mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
        this.mValueColors.add(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
    }

    public void addColor(int i3) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.add(Integer.valueOf(i3));
    }

    public boolean contains(T t2) {
        for (int i3 = 0; i3 < getEntryCount(); i3++) {
            if (getEntryForIndex(i3).equals(t2)) {
                return true;
            }
        }
        return false;
    }

    public void copy(BaseDataSet baseDataSet) {
        baseDataSet.mAxisDependency = this.mAxisDependency;
        baseDataSet.mColors = this.mColors;
        baseDataSet.mDrawIcons = this.mDrawIcons;
        baseDataSet.mDrawValues = this.mDrawValues;
        baseDataSet.mForm = this.mForm;
        baseDataSet.mFormLineDashEffect = this.mFormLineDashEffect;
        baseDataSet.mFormLineWidth = this.mFormLineWidth;
        baseDataSet.mFormSize = this.mFormSize;
        baseDataSet.mGradientColor = this.mGradientColor;
        baseDataSet.mGradientColors = this.mGradientColors;
        baseDataSet.mHighlightEnabled = this.mHighlightEnabled;
        baseDataSet.mIconsOffset = this.mIconsOffset;
        baseDataSet.mValueColors = this.mValueColors;
        baseDataSet.mValueFormatter = this.mValueFormatter;
        baseDataSet.mValueColors = this.mValueColors;
        baseDataSet.mValueTextSize = this.mValueTextSize;
        baseDataSet.mVisible = this.mVisible;
    }

    public YAxis.AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public int getColor() {
        return this.mColors.get(0).intValue();
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public Legend.LegendForm getForm() {
        return this.mForm;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public GradientColor getGradientColor() {
        return this.mGradientColor;
    }

    public List<GradientColor> getGradientColors() {
        return this.mGradientColors;
    }

    public MPPointF getIconsOffset() {
        return this.mIconsOffset;
    }

    public int getIndexInEntries(int i3) {
        for (int i4 = 0; i4 < getEntryCount(); i4++) {
            if (((float) i3) == getEntryForIndex(i4).getX()) {
                return i4;
            }
        }
        return -1;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public List<Integer> getValueColors() {
        return this.mValueColors;
    }

    public ValueFormatter getValueFormatter() {
        return needsFormatter() ? Utils.getDefaultValueFormatter() : this.mValueFormatter;
    }

    public int getValueTextColor() {
        return this.mValueColors.get(0).intValue();
    }

    public float getValueTextSize() {
        return this.mValueTextSize;
    }

    public Typeface getValueTypeface() {
        return this.mValueTypeface;
    }

    public boolean isDrawIconsEnabled() {
        return this.mDrawIcons;
    }

    public boolean isDrawValuesEnabled() {
        return this.mDrawValues;
    }

    public boolean isHighlightEnabled() {
        return this.mHighlightEnabled;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public boolean needsFormatter() {
        return this.mValueFormatter == null;
    }

    public void notifyDataSetChanged() {
        calcMinMax();
    }

    public boolean removeEntry(int i3) {
        return removeEntry(getEntryForIndex(i3));
    }

    public boolean removeEntryByXValue(float f2) {
        return removeEntry(getEntryForXValue(f2, Float.NaN));
    }

    public boolean removeFirst() {
        if (getEntryCount() > 0) {
            return removeEntry(getEntryForIndex(0));
        }
        return false;
    }

    public boolean removeLast() {
        if (getEntryCount() > 0) {
            return removeEntry(getEntryForIndex(getEntryCount() - 1));
        }
        return false;
    }

    public void resetColors() {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
    }

    public void setAxisDependency(YAxis.AxisDependency axisDependency) {
        this.mAxisDependency = axisDependency;
    }

    public void setColor(int i3) {
        resetColors();
        this.mColors.add(Integer.valueOf(i3));
    }

    public void setColors(List<Integer> list) {
        this.mColors = list;
    }

    public void setDrawIcons(boolean z2) {
        this.mDrawIcons = z2;
    }

    public void setDrawValues(boolean z2) {
        this.mDrawValues = z2;
    }

    public void setForm(Legend.LegendForm legendForm) {
        this.mForm = legendForm;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public void setFormLineWidth(float f2) {
        this.mFormLineWidth = f2;
    }

    public void setFormSize(float f2) {
        this.mFormSize = f2;
    }

    public void setGradientColor(int i3, int i4) {
        this.mGradientColor = new GradientColor(i3, i4);
    }

    public void setGradientColors(List<GradientColor> list) {
        this.mGradientColors = list;
    }

    public void setHighlightEnabled(boolean z2) {
        this.mHighlightEnabled = z2;
    }

    public void setIconsOffset(MPPointF mPPointF) {
        MPPointF mPPointF2 = this.mIconsOffset;
        mPPointF2.f6574x = mPPointF.f6574x;
        mPPointF2.f6575y = mPPointF.f6575y;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public void setValueFormatter(ValueFormatter valueFormatter) {
        if (valueFormatter != null) {
            this.mValueFormatter = valueFormatter;
        }
    }

    public void setValueTextColor(int i3) {
        this.mValueColors.clear();
        this.mValueColors.add(Integer.valueOf(i3));
    }

    public void setValueTextColors(List<Integer> list) {
        this.mValueColors = list;
    }

    public void setValueTextSize(float f2) {
        this.mValueTextSize = Utils.convertDpToPixel(f2);
    }

    public void setValueTypeface(Typeface typeface) {
        this.mValueTypeface = typeface;
    }

    public void setVisible(boolean z2) {
        this.mVisible = z2;
    }

    public int getColor(int i3) {
        List<Integer> list = this.mColors;
        return list.get(i3 % list.size()).intValue();
    }

    public GradientColor getGradientColor(int i3) {
        List<GradientColor> list = this.mGradientColors;
        return list.get(i3 % list.size());
    }

    public int getValueTextColor(int i3) {
        List<Integer> list = this.mValueColors;
        return list.get(i3 % list.size()).intValue();
    }

    public void setColors(int... iArr) {
        this.mColors = ColorTemplate.createColors(iArr);
    }

    public void setColor(int i3, int i4) {
        setColor(Color.argb(i4, Color.red(i3), Color.green(i3), Color.blue(i3)));
    }

    public void setColors(int[] iArr, Context context) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
        for (int color : iArr) {
            this.mColors.add(Integer.valueOf(context.getResources().getColor(color)));
        }
    }

    public void setColors(int[] iArr, int i3) {
        resetColors();
        for (int i4 : iArr) {
            addColor(Color.argb(i3, Color.red(i4), Color.green(i4), Color.blue(i4)));
        }
    }

    public BaseDataSet(String str) {
        this();
        this.mLabel = str;
    }
}
