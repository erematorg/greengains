package com.github.mikephil.charting.data;

import android.support.v4.media.session.a;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {
    protected List<T> mValues;
    protected float mXMax = -3.4028235E38f;
    protected float mXMin = Float.MAX_VALUE;
    protected float mYMax = -3.4028235E38f;
    protected float mYMin = Float.MAX_VALUE;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public DataSet(List<T> list, String str) {
        super(str);
        this.mValues = list;
        if (list == null) {
            this.mValues = new ArrayList();
        }
        calcMinMax();
    }

    public boolean addEntry(T t2) {
        if (t2 == null) {
            return false;
        }
        List values = getValues();
        if (values == null) {
            values = new ArrayList();
        }
        calcMinMax(t2);
        return values.add(t2);
    }

    public void addEntryOrdered(T t2) {
        if (t2 != null) {
            if (this.mValues == null) {
                this.mValues = new ArrayList();
            }
            calcMinMax(t2);
            if (this.mValues.size() <= 0 || ((Entry) a.h(this.mValues, 1)).getX() <= t2.getX()) {
                this.mValues.add(t2);
                return;
            }
            this.mValues.add(getEntryIndex(t2.getX(), t2.getY(), Rounding.UP), t2);
        }
    }

    public void calcMinMax() {
        List<T> list = this.mValues;
        if (list != null && !list.isEmpty()) {
            this.mYMax = -3.4028235E38f;
            this.mYMin = Float.MAX_VALUE;
            this.mXMax = -3.4028235E38f;
            this.mXMin = Float.MAX_VALUE;
            for (T calcMinMax : this.mValues) {
                calcMinMax(calcMinMax);
            }
        }
    }

    public void calcMinMaxX(T t2) {
        if (t2.getX() < this.mXMin) {
            this.mXMin = t2.getX();
        }
        if (t2.getX() > this.mXMax) {
            this.mXMax = t2.getX();
        }
    }

    public void calcMinMaxY(float f2, float f3) {
        List<T> list = this.mValues;
        if (list != null && !list.isEmpty()) {
            this.mYMax = -3.4028235E38f;
            this.mYMin = Float.MAX_VALUE;
            int entryIndex = getEntryIndex(f3, Float.NaN, Rounding.UP);
            for (int entryIndex2 = getEntryIndex(f2, Float.NaN, Rounding.DOWN); entryIndex2 <= entryIndex; entryIndex2++) {
                calcMinMaxY((Entry) this.mValues.get(entryIndex2));
            }
        }
    }

    public void clear() {
        this.mValues.clear();
        notifyDataSetChanged();
    }

    public abstract DataSet<T> copy();

    public void copy(DataSet dataSet) {
        super.copy(dataSet);
    }

    public List<T> getEntriesForXValue(float f2) {
        ArrayList arrayList = new ArrayList();
        int size = this.mValues.size() - 1;
        int i3 = 0;
        while (true) {
            if (i3 > size) {
                break;
            }
            int i4 = (size + i3) / 2;
            Entry entry = (Entry) this.mValues.get(i4);
            if (f2 == entry.getX()) {
                while (i4 > 0 && ((Entry) this.mValues.get(i4 - 1)).getX() == f2) {
                    i4--;
                }
                int size2 = this.mValues.size();
                while (i4 < size2) {
                    Entry entry2 = (Entry) this.mValues.get(i4);
                    if (entry2.getX() != f2) {
                        break;
                    }
                    arrayList.add(entry2);
                    i4++;
                }
            } else if (f2 > entry.getX()) {
                i3 = i4 + 1;
            } else {
                size = i4 - 1;
            }
        }
        return arrayList;
    }

    public int getEntryCount() {
        return this.mValues.size();
    }

    public T getEntryForIndex(int i3) {
        return (Entry) this.mValues.get(i3);
    }

    public T getEntryForXValue(float f2, float f3, Rounding rounding) {
        int entryIndex = getEntryIndex(f2, f3, rounding);
        if (entryIndex > -1) {
            return (Entry) this.mValues.get(entryIndex);
        }
        return null;
    }

    public int getEntryIndex(Entry entry) {
        return this.mValues.indexOf(entry);
    }

    public List<T> getValues() {
        return this.mValues;
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

    public boolean removeEntry(T t2) {
        List<T> list;
        if (t2 == null || (list = this.mValues) == null) {
            return false;
        }
        boolean remove = list.remove(t2);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    public void setValues(List<T> list) {
        this.mValues = list;
        notifyDataSetChanged();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder("DataSet, label: ");
        sb.append(getLabel() == null ? "" : getLabel());
        sb.append(", entries: ");
        sb.append(this.mValues.size());
        sb.append("\n");
        stringBuffer.append(sb.toString());
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toSimpleString());
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            stringBuffer.append(((Entry) this.mValues.get(i3)).toString() + StringUtils.SPACE);
        }
        return stringBuffer.toString();
    }

    public int getEntryIndex(float f2, float f3, Rounding rounding) {
        int i3;
        Entry entry;
        List<T> list = this.mValues;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int size = this.mValues.size() - 1;
        int i4 = 0;
        while (i4 < size) {
            int i5 = (i4 + size) / 2;
            float x2 = ((Entry) this.mValues.get(i5)).getX() - f2;
            int i6 = i5 + 1;
            float abs = Math.abs(x2);
            float abs2 = Math.abs(((Entry) this.mValues.get(i6)).getX() - f2);
            if (abs2 >= abs) {
                if (abs >= abs2) {
                    double d2 = (double) x2;
                    if (d2 < 0.0d) {
                        if (d2 >= 0.0d) {
                        }
                    }
                }
                size = i5;
            }
            i4 = i6;
        }
        if (size == -1) {
            return size;
        }
        float x3 = ((Entry) this.mValues.get(size)).getX();
        if (rounding == Rounding.UP) {
            if (x3 < f2 && size < this.mValues.size() - 1) {
                size++;
            }
        } else if (rounding == Rounding.DOWN && x3 > f2 && size > 0) {
            size--;
        }
        if (Float.isNaN(f3)) {
            return size;
        }
        while (size > 0 && ((Entry) this.mValues.get(size - 1)).getX() == x3) {
            size--;
        }
        float y2 = ((Entry) this.mValues.get(size)).getY();
        loop2:
        while (true) {
            i3 = size;
            do {
                size++;
                if (size >= this.mValues.size()) {
                    break loop2;
                }
                entry = (Entry) this.mValues.get(size);
                if (entry.getX() != x3) {
                    break loop2;
                }
            } while (Math.abs(entry.getY() - f3) >= Math.abs(y2 - f3));
            y2 = f3;
        }
        return i3;
    }

    public T getEntryForXValue(float f2, float f3) {
        return getEntryForXValue(f2, f3, Rounding.CLOSEST);
    }

    public void calcMinMaxY(T t2) {
        if (t2.getY() < this.mYMin) {
            this.mYMin = t2.getY();
        }
        if (t2.getY() > this.mYMax) {
            this.mYMax = t2.getY();
        }
    }

    public void calcMinMax(T t2) {
        if (t2 != null) {
            calcMinMaxX(t2);
            calcMinMaxY(t2);
        }
    }
}
