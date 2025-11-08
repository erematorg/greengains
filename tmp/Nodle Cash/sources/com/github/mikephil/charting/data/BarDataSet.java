package com.github.mikephil.charting.data;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.provider.jffi.JNINativeInterface;

public class BarDataSet extends BarLineScatterCandleBubbleDataSet<BarEntry> implements IBarDataSet {
    private int mBarBorderColor = ViewCompat.MEASURED_STATE_MASK;
    private float mBarBorderWidth = 0.0f;
    private int mBarShadowColor = Color.rgb(JNINativeInterface.RegisterNatives, JNINativeInterface.RegisterNatives, JNINativeInterface.RegisterNatives);
    private int mEntryCountStacks = 0;
    private int mHighLightAlpha = 120;
    private String[] mStackLabels = {"Stack"};
    private int mStackSize = 1;

    public BarDataSet(List<BarEntry> list, String str) {
        super(list, str);
        this.mHighLightColor = Color.rgb(0, 0, 0);
        calcStackSize(list);
        calcEntryCountIncludingStacks(list);
    }

    private void calcEntryCountIncludingStacks(List<BarEntry> list) {
        this.mEntryCountStacks = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            float[] yVals = list.get(i3).getYVals();
            if (yVals == null) {
                this.mEntryCountStacks++;
            } else {
                this.mEntryCountStacks += yVals.length;
            }
        }
    }

    private void calcStackSize(List<BarEntry> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            float[] yVals = list.get(i3).getYVals();
            if (yVals != null && yVals.length > this.mStackSize) {
                this.mStackSize = yVals.length;
            }
        }
    }

    public DataSet<BarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            arrayList.add(((BarEntry) this.mValues.get(i3)).copy());
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, getLabel());
        copy(barDataSet);
        return barDataSet;
    }

    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public int getEntryCountStacks() {
        return this.mEntryCountStacks;
    }

    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public String[] getStackLabels() {
        return this.mStackLabels;
    }

    public int getStackSize() {
        return this.mStackSize;
    }

    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    public void setBarBorderColor(int i3) {
        this.mBarBorderColor = i3;
    }

    public void setBarBorderWidth(float f2) {
        this.mBarBorderWidth = f2;
    }

    public void setBarShadowColor(int i3) {
        this.mBarShadowColor = i3;
    }

    public void setHighLightAlpha(int i3) {
        this.mHighLightAlpha = i3;
    }

    public void setStackLabels(String[] strArr) {
        this.mStackLabels = strArr;
    }

    public void calcMinMax(BarEntry barEntry) {
        if (barEntry != null && !Float.isNaN(barEntry.getY())) {
            if (barEntry.getYVals() == null) {
                if (barEntry.getY() < this.mYMin) {
                    this.mYMin = barEntry.getY();
                }
                if (barEntry.getY() > this.mYMax) {
                    this.mYMax = barEntry.getY();
                }
            } else {
                if ((-barEntry.getNegativeSum()) < this.mYMin) {
                    this.mYMin = -barEntry.getNegativeSum();
                }
                if (barEntry.getPositiveSum() > this.mYMax) {
                    this.mYMax = barEntry.getPositiveSum();
                }
            }
            calcMinMaxX(barEntry);
        }
    }

    public void copy(BarDataSet barDataSet) {
        super.copy(barDataSet);
        barDataSet.mStackSize = this.mStackSize;
        barDataSet.mBarShadowColor = this.mBarShadowColor;
        barDataSet.mBarBorderWidth = this.mBarBorderWidth;
        barDataSet.mStackLabels = this.mStackLabels;
        barDataSet.mHighLightAlpha = this.mHighLightAlpha;
    }
}
