package com.github.mikephil.charting.data;

import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CandleDataSet extends LineScatterCandleRadarDataSet<CandleEntry> implements ICandleDataSet {
    private float mBarSpace = 0.1f;
    protected int mDecreasingColor = ColorTemplate.COLOR_SKIP;
    protected Paint.Style mDecreasingPaintStyle = Paint.Style.FILL;
    protected int mIncreasingColor = ColorTemplate.COLOR_SKIP;
    protected Paint.Style mIncreasingPaintStyle = Paint.Style.STROKE;
    protected int mNeutralColor = ColorTemplate.COLOR_SKIP;
    protected int mShadowColor = ColorTemplate.COLOR_SKIP;
    private boolean mShadowColorSameAsCandle = false;
    private float mShadowWidth = 3.0f;
    private boolean mShowCandleBar = true;

    public CandleDataSet(List<CandleEntry> list, String str) {
        super(list, str);
    }

    public DataSet<CandleEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            arrayList.add(((CandleEntry) this.mValues.get(i3)).copy());
        }
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, getLabel());
        copy(candleDataSet);
        return candleDataSet;
    }

    public float getBarSpace() {
        return this.mBarSpace;
    }

    public int getDecreasingColor() {
        return this.mDecreasingColor;
    }

    public Paint.Style getDecreasingPaintStyle() {
        return this.mDecreasingPaintStyle;
    }

    public int getIncreasingColor() {
        return this.mIncreasingColor;
    }

    public Paint.Style getIncreasingPaintStyle() {
        return this.mIncreasingPaintStyle;
    }

    public int getNeutralColor() {
        return this.mNeutralColor;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }

    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    public boolean getShowCandleBar() {
        return this.mShowCandleBar;
    }

    public void setBarSpace(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 0.45f) {
            f2 = 0.45f;
        }
        this.mBarSpace = f2;
    }

    public void setDecreasingColor(int i3) {
        this.mDecreasingColor = i3;
    }

    public void setDecreasingPaintStyle(Paint.Style style) {
        this.mDecreasingPaintStyle = style;
    }

    public void setIncreasingColor(int i3) {
        this.mIncreasingColor = i3;
    }

    public void setIncreasingPaintStyle(Paint.Style style) {
        this.mIncreasingPaintStyle = style;
    }

    public void setNeutralColor(int i3) {
        this.mNeutralColor = i3;
    }

    public void setShadowColor(int i3) {
        this.mShadowColor = i3;
    }

    public void setShadowColorSameAsCandle(boolean z2) {
        this.mShadowColorSameAsCandle = z2;
    }

    public void setShadowWidth(float f2) {
        this.mShadowWidth = Utils.convertDpToPixel(f2);
    }

    public void setShowCandleBar(boolean z2) {
        this.mShowCandleBar = z2;
    }

    public void calcMinMax(CandleEntry candleEntry) {
        if (candleEntry.getLow() < this.mYMin) {
            this.mYMin = candleEntry.getLow();
        }
        if (candleEntry.getHigh() > this.mYMax) {
            this.mYMax = candleEntry.getHigh();
        }
        calcMinMaxX(candleEntry);
    }

    public void calcMinMaxY(CandleEntry candleEntry) {
        if (candleEntry.getHigh() < this.mYMin) {
            this.mYMin = candleEntry.getHigh();
        }
        if (candleEntry.getHigh() > this.mYMax) {
            this.mYMax = candleEntry.getHigh();
        }
        if (candleEntry.getLow() < this.mYMin) {
            this.mYMin = candleEntry.getLow();
        }
        if (candleEntry.getLow() > this.mYMax) {
            this.mYMax = candleEntry.getLow();
        }
    }

    public void copy(CandleDataSet candleDataSet) {
        super.copy(candleDataSet);
        candleDataSet.mShadowWidth = this.mShadowWidth;
        candleDataSet.mShowCandleBar = this.mShowCandleBar;
        candleDataSet.mBarSpace = this.mBarSpace;
        candleDataSet.mShadowColorSameAsCandle = this.mShadowColorSameAsCandle;
        candleDataSet.mHighLightColor = this.mHighLightColor;
        candleDataSet.mIncreasingPaintStyle = this.mIncreasingPaintStyle;
        candleDataSet.mDecreasingPaintStyle = this.mDecreasingPaintStyle;
        candleDataSet.mNeutralColor = this.mNeutralColor;
        candleDataSet.mIncreasingColor = this.mIncreasingColor;
        candleDataSet.mDecreasingColor = this.mDecreasingColor;
        candleDataSet.mShadowColor = this.mShadowColor;
    }
}
