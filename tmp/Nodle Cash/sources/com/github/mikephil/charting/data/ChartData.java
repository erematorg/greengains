package com.github.mikephil.charting.data;

import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.List;

public abstract class ChartData<T extends IDataSet<? extends Entry>> {
    protected List<T> mDataSets;
    protected float mLeftAxisMax;
    protected float mLeftAxisMin;
    protected float mRightAxisMax;
    protected float mRightAxisMin;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public ChartData() {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = new ArrayList();
    }

    private List<T> arrayToList(T[] tArr) {
        ArrayList arrayList = new ArrayList();
        for (T add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void addDataSet(T t2) {
        if (t2 != null) {
            calcMinMax(t2);
            this.mDataSets.add(t2);
        }
    }

    public void addEntry(Entry entry, int i3) {
        if (this.mDataSets.size() <= i3 || i3 < 0) {
            Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
            return;
        }
        IDataSet iDataSet = (IDataSet) this.mDataSets.get(i3);
        if (iDataSet.addEntry(entry)) {
            calcMinMax(entry, iDataSet.getAxisDependency());
        }
    }

    public void calcMinMax() {
        List<T> list = this.mDataSets;
        if (list != null) {
            this.mYMax = -3.4028235E38f;
            this.mYMin = Float.MAX_VALUE;
            this.mXMax = -3.4028235E38f;
            this.mXMin = Float.MAX_VALUE;
            for (T calcMinMax : list) {
                calcMinMax(calcMinMax);
            }
            this.mLeftAxisMax = -3.4028235E38f;
            this.mLeftAxisMin = Float.MAX_VALUE;
            this.mRightAxisMax = -3.4028235E38f;
            this.mRightAxisMin = Float.MAX_VALUE;
            IDataSet firstLeft = getFirstLeft(this.mDataSets);
            if (firstLeft != null) {
                this.mLeftAxisMax = firstLeft.getYMax();
                this.mLeftAxisMin = firstLeft.getYMin();
                for (T t2 : this.mDataSets) {
                    if (t2.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                        if (t2.getYMin() < this.mLeftAxisMin) {
                            this.mLeftAxisMin = t2.getYMin();
                        }
                        if (t2.getYMax() > this.mLeftAxisMax) {
                            this.mLeftAxisMax = t2.getYMax();
                        }
                    }
                }
            }
            IDataSet firstRight = getFirstRight(this.mDataSets);
            if (firstRight != null) {
                this.mRightAxisMax = firstRight.getYMax();
                this.mRightAxisMin = firstRight.getYMin();
                for (T t3 : this.mDataSets) {
                    if (t3.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                        if (t3.getYMin() < this.mRightAxisMin) {
                            this.mRightAxisMin = t3.getYMin();
                        }
                        if (t3.getYMax() > this.mRightAxisMax) {
                            this.mRightAxisMax = t3.getYMax();
                        }
                    }
                }
            }
        }
    }

    public void calcMinMaxY(float f2, float f3) {
        for (T calcMinMaxY : this.mDataSets) {
            calcMinMaxY.calcMinMaxY(f2, f3);
        }
        calcMinMax();
    }

    public void clearValues() {
        List<T> list = this.mDataSets;
        if (list != null) {
            list.clear();
        }
        notifyDataChanged();
    }

    public boolean contains(T t2) {
        for (T equals : this.mDataSets) {
            if (equals.equals(t2)) {
                return true;
            }
        }
        return false;
    }

    public int[] getColors() {
        if (this.mDataSets == null) {
            return null;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mDataSets.size(); i4++) {
            i3 += ((IDataSet) this.mDataSets.get(i4)).getColors().size();
        }
        int[] iArr = new int[i3];
        int i5 = 0;
        for (int i6 = 0; i6 < this.mDataSets.size(); i6++) {
            for (Integer intValue : ((IDataSet) this.mDataSets.get(i6)).getColors()) {
                iArr[i5] = intValue.intValue();
                i5++;
            }
        }
        return iArr;
    }

    public T getDataSetByIndex(int i3) {
        List<T> list = this.mDataSets;
        if (list == null || i3 < 0 || i3 >= list.size()) {
            return null;
        }
        return (IDataSet) this.mDataSets.get(i3);
    }

    public T getDataSetByLabel(String str, boolean z2) {
        int dataSetIndexByLabel = getDataSetIndexByLabel(this.mDataSets, str, z2);
        if (dataSetIndexByLabel < 0 || dataSetIndexByLabel >= this.mDataSets.size()) {
            return null;
        }
        return (IDataSet) this.mDataSets.get(dataSetIndexByLabel);
    }

    public int getDataSetCount() {
        List<T> list = this.mDataSets;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getDataSetForEntry(Entry entry) {
        if (entry == null) {
            return null;
        }
        for (int i3 = 0; i3 < this.mDataSets.size(); i3++) {
            T t2 = (IDataSet) this.mDataSets.get(i3);
            for (int i4 = 0; i4 < t2.getEntryCount(); i4++) {
                if (entry.equalTo(t2.getEntryForXValue(entry.getX(), entry.getY()))) {
                    return t2;
                }
            }
        }
        return null;
    }

    public int getDataSetIndexByLabel(List<T> list, String str, boolean z2) {
        int i3 = 0;
        if (z2) {
            while (i3 < list.size()) {
                if (str.equalsIgnoreCase(((IDataSet) list.get(i3)).getLabel())) {
                    return i3;
                }
                i3++;
            }
            return -1;
        }
        while (i3 < list.size()) {
            if (str.equals(((IDataSet) list.get(i3)).getLabel())) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public String[] getDataSetLabels() {
        String[] strArr = new String[this.mDataSets.size()];
        for (int i3 = 0; i3 < this.mDataSets.size(); i3++) {
            strArr[i3] = ((IDataSet) this.mDataSets.get(i3)).getLabel();
        }
        return strArr;
    }

    public List<T> getDataSets() {
        return this.mDataSets;
    }

    public int getEntryCount() {
        int i3 = 0;
        for (T entryCount : this.mDataSets) {
            i3 += entryCount.getEntryCount();
        }
        return i3;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        if (highlight.getDataSetIndex() >= this.mDataSets.size()) {
            return null;
        }
        return ((IDataSet) this.mDataSets.get(highlight.getDataSetIndex())).getEntryForXValue(highlight.getX(), highlight.getY());
    }

    public T getFirstLeft(List<T> list) {
        for (T t2 : list) {
            if (t2.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                return t2;
            }
        }
        return null;
    }

    public T getFirstRight(List<T> list) {
        for (T t2 : list) {
            if (t2.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                return t2;
            }
        }
        return null;
    }

    public int getIndexOfDataSet(T t2) {
        return this.mDataSets.indexOf(t2);
    }

    public T getMaxEntryCountSet() {
        List<T> list = this.mDataSets;
        if (list == null || list.isEmpty()) {
            return null;
        }
        T t2 = (IDataSet) this.mDataSets.get(0);
        for (T t3 : this.mDataSets) {
            if (t3.getEntryCount() > t2.getEntryCount()) {
                t2 = t3;
            }
        }
        return t2;
    }

    public float getXMax() {
        return this.mXMax;
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getYMin() {
        return this.mYMin;
    }

    public boolean isHighlightEnabled() {
        for (T isHighlightEnabled : this.mDataSets) {
            if (!isHighlightEnabled.isHighlightEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void notifyDataChanged() {
        calcMinMax();
    }

    public boolean removeDataSet(T t2) {
        if (t2 == null) {
            return false;
        }
        boolean remove = this.mDataSets.remove(t2);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    public boolean removeEntry(Entry entry, int i3) {
        IDataSet iDataSet;
        if (entry == null || i3 >= this.mDataSets.size() || (iDataSet = (IDataSet) this.mDataSets.get(i3)) == null) {
            return false;
        }
        boolean removeEntry = iDataSet.removeEntry(entry);
        if (removeEntry) {
            calcMinMax();
        }
        return removeEntry;
    }

    public void setDrawValues(boolean z2) {
        for (T drawValues : this.mDataSets) {
            drawValues.setDrawValues(z2);
        }
    }

    public void setHighlightEnabled(boolean z2) {
        for (T highlightEnabled : this.mDataSets) {
            highlightEnabled.setHighlightEnabled(z2);
        }
    }

    public void setValueFormatter(ValueFormatter valueFormatter) {
        if (valueFormatter != null) {
            for (T valueFormatter2 : this.mDataSets) {
                valueFormatter2.setValueFormatter(valueFormatter);
            }
        }
    }

    public void setValueTextColor(int i3) {
        for (T valueTextColor : this.mDataSets) {
            valueTextColor.setValueTextColor(i3);
        }
    }

    public void setValueTextColors(List<Integer> list) {
        for (T valueTextColors : this.mDataSets) {
            valueTextColors.setValueTextColors(list);
        }
    }

    public void setValueTextSize(float f2) {
        for (T valueTextSize : this.mDataSets) {
            valueTextSize.setValueTextSize(f2);
        }
    }

    public void setValueTypeface(Typeface typeface) {
        for (T valueTypeface : this.mDataSets) {
            valueTypeface.setValueTypeface(typeface);
        }
    }

    public float getYMax(YAxis.AxisDependency axisDependency) {
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            float f2 = this.mLeftAxisMax;
            return f2 == -3.4028235E38f ? this.mRightAxisMax : f2;
        }
        float f3 = this.mRightAxisMax;
        return f3 == -3.4028235E38f ? this.mLeftAxisMax : f3;
    }

    public float getYMin(YAxis.AxisDependency axisDependency) {
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            float f2 = this.mLeftAxisMin;
            return f2 == Float.MAX_VALUE ? this.mRightAxisMin : f2;
        }
        float f3 = this.mRightAxisMin;
        return f3 == Float.MAX_VALUE ? this.mLeftAxisMin : f3;
    }

    public boolean removeDataSet(int i3) {
        if (i3 >= this.mDataSets.size() || i3 < 0) {
            return false;
        }
        return removeDataSet((IDataSet) this.mDataSets.get(i3));
    }

    public boolean removeEntry(float f2, int i3) {
        Entry entryForXValue;
        if (i3 < this.mDataSets.size() && (entryForXValue = ((IDataSet) this.mDataSets.get(i3)).getEntryForXValue(f2, Float.NaN)) != null) {
            return removeEntry(entryForXValue, i3);
        }
        return false;
    }

    public ChartData(T... tArr) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = arrayToList(tArr);
        notifyDataChanged();
    }

    public ChartData(List<T> list) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = list;
        notifyDataChanged();
    }

    public void calcMinMax(Entry entry, YAxis.AxisDependency axisDependency) {
        if (this.mYMax < entry.getY()) {
            this.mYMax = entry.getY();
        }
        if (this.mYMin > entry.getY()) {
            this.mYMin = entry.getY();
        }
        if (this.mXMax < entry.getX()) {
            this.mXMax = entry.getX();
        }
        if (this.mXMin > entry.getX()) {
            this.mXMin = entry.getX();
        }
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            if (this.mLeftAxisMax < entry.getY()) {
                this.mLeftAxisMax = entry.getY();
            }
            if (this.mLeftAxisMin > entry.getY()) {
                this.mLeftAxisMin = entry.getY();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < entry.getY()) {
            this.mRightAxisMax = entry.getY();
        }
        if (this.mRightAxisMin > entry.getY()) {
            this.mRightAxisMin = entry.getY();
        }
    }

    public void calcMinMax(T t2) {
        if (this.mYMax < t2.getYMax()) {
            this.mYMax = t2.getYMax();
        }
        if (this.mYMin > t2.getYMin()) {
            this.mYMin = t2.getYMin();
        }
        if (this.mXMax < t2.getXMax()) {
            this.mXMax = t2.getXMax();
        }
        if (this.mXMin > t2.getXMin()) {
            this.mXMin = t2.getXMin();
        }
        if (t2.getAxisDependency() == YAxis.AxisDependency.LEFT) {
            if (this.mLeftAxisMax < t2.getYMax()) {
                this.mLeftAxisMax = t2.getYMax();
            }
            if (this.mLeftAxisMin > t2.getYMin()) {
                this.mLeftAxisMin = t2.getYMin();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < t2.getYMax()) {
            this.mRightAxisMax = t2.getYMax();
        }
        if (this.mRightAxisMin > t2.getYMin()) {
            this.mRightAxisMin = t2.getYMin();
        }
    }
}
