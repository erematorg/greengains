package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class BubbleDataSet extends BarLineScatterCandleBubbleDataSet<BubbleEntry> implements IBubbleDataSet {
    private float mHighlightCircleWidth = 2.5f;
    protected float mMaxSize;
    protected boolean mNormalizeSize = true;

    public BubbleDataSet(List<BubbleEntry> list, String str) {
        super(list, str);
    }

    public DataSet<BubbleEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            arrayList.add(((BubbleEntry) this.mValues.get(i3)).copy());
        }
        BubbleDataSet bubbleDataSet = new BubbleDataSet(arrayList, getLabel());
        copy(bubbleDataSet);
        return bubbleDataSet;
    }

    public float getHighlightCircleWidth() {
        return this.mHighlightCircleWidth;
    }

    public float getMaxSize() {
        return this.mMaxSize;
    }

    public boolean isNormalizeSizeEnabled() {
        return this.mNormalizeSize;
    }

    public void setHighlightCircleWidth(float f2) {
        this.mHighlightCircleWidth = Utils.convertDpToPixel(f2);
    }

    public void setNormalizeSizeEnabled(boolean z2) {
        this.mNormalizeSize = z2;
    }

    public void calcMinMax(BubbleEntry bubbleEntry) {
        super.calcMinMax(bubbleEntry);
        float size = bubbleEntry.getSize();
        if (size > this.mMaxSize) {
            this.mMaxSize = size;
        }
    }

    public void copy(BubbleDataSet bubbleDataSet) {
        bubbleDataSet.mHighlightCircleWidth = this.mHighlightCircleWidth;
        bubbleDataSet.mNormalizeSize = this.mNormalizeSize;
    }
}
