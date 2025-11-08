package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.List;

public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth = 0.85f;

    public BarData() {
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public float getGroupWidth(float f2, float f3) {
        return ((this.mBarWidth + f3) * ((float) this.mDataSets.size())) + f2;
    }

    public void groupBars(float f2, float f3, float f4) {
        BarEntry barEntry;
        if (this.mDataSets.size() > 1) {
            int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
            float f5 = f3 / 2.0f;
            float f6 = f4 / 2.0f;
            float f7 = this.mBarWidth / 2.0f;
            float groupWidth = getGroupWidth(f3, f4);
            for (int i3 = 0; i3 < entryCount; i3++) {
                float f8 = f2 + f5;
                for (T t2 : this.mDataSets) {
                    float f9 = f8 + f6 + f7;
                    if (i3 < t2.getEntryCount() && (barEntry = (BarEntry) t2.getEntryForIndex(i3)) != null) {
                        barEntry.setX(f9);
                    }
                    f8 = f9 + f7 + f6;
                }
                float f10 = f8 + f5;
                float f11 = groupWidth - (f10 - f2);
                if (f11 > 0.0f || f11 < 0.0f) {
                    f10 += f11;
                }
                f2 = f10;
            }
            notifyDataChanged();
            return;
        }
        throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
    }

    public void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }

    public BarData(IBarDataSet... iBarDataSetArr) {
        super((T[]) iBarDataSetArr);
    }

    public BarData(List<IBarDataSet> list) {
        super(list);
    }
}
