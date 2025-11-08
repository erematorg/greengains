package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

public class PieData extends ChartData<IPieDataSet> {
    public PieData() {
    }

    public IPieDataSet getDataSet() {
        return (IPieDataSet) this.mDataSets.get(0);
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        return getDataSet().getEntryForIndex((int) highlight.getX());
    }

    public float getYValueSum() {
        float f2 = 0.0f;
        for (int i3 = 0; i3 < getDataSet().getEntryCount(); i3++) {
            f2 += ((PieEntry) getDataSet().getEntryForIndex(i3)).getY();
        }
        return f2;
    }

    public void setDataSet(IPieDataSet iPieDataSet) {
        this.mDataSets.clear();
        this.mDataSets.add(iPieDataSet);
        notifyDataChanged();
    }

    public PieData(IPieDataSet iPieDataSet) {
        super((T[]) new IPieDataSet[]{iPieDataSet});
    }

    public IPieDataSet getDataSetByIndex(int i3) {
        if (i3 == 0) {
            return getDataSet();
        }
        return null;
    }

    public IPieDataSet getDataSetByLabel(String str, boolean z2) {
        if (z2) {
            if (str.equalsIgnoreCase(((IPieDataSet) this.mDataSets.get(0)).getLabel())) {
                return (IPieDataSet) this.mDataSets.get(0);
            }
            return null;
        } else if (str.equals(((IPieDataSet) this.mDataSets.get(0)).getLabel())) {
            return (IPieDataSet) this.mDataSets.get(0);
        } else {
            return null;
        }
    }
}
