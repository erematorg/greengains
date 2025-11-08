package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class RadarDataSet extends LineRadarDataSet<RadarEntry> implements IRadarDataSet {
    protected boolean mDrawHighlightCircleEnabled = false;
    protected int mHighlightCircleFillColor = -1;
    protected float mHighlightCircleInnerRadius = 3.0f;
    protected float mHighlightCircleOuterRadius = 4.0f;
    protected int mHighlightCircleStrokeAlpha = 76;
    protected int mHighlightCircleStrokeColor = ColorTemplate.COLOR_NONE;
    protected float mHighlightCircleStrokeWidth = 2.0f;

    public RadarDataSet(List<RadarEntry> list, String str) {
        super(list, str);
    }

    public DataSet<RadarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            arrayList.add(((RadarEntry) this.mValues.get(i3)).copy());
        }
        RadarDataSet radarDataSet = new RadarDataSet(arrayList, getLabel());
        copy(radarDataSet);
        return radarDataSet;
    }

    public int getHighlightCircleFillColor() {
        return this.mHighlightCircleFillColor;
    }

    public float getHighlightCircleInnerRadius() {
        return this.mHighlightCircleInnerRadius;
    }

    public float getHighlightCircleOuterRadius() {
        return this.mHighlightCircleOuterRadius;
    }

    public int getHighlightCircleStrokeAlpha() {
        return this.mHighlightCircleStrokeAlpha;
    }

    public int getHighlightCircleStrokeColor() {
        return this.mHighlightCircleStrokeColor;
    }

    public float getHighlightCircleStrokeWidth() {
        return this.mHighlightCircleStrokeWidth;
    }

    public boolean isDrawHighlightCircleEnabled() {
        return this.mDrawHighlightCircleEnabled;
    }

    public void setDrawHighlightCircleEnabled(boolean z2) {
        this.mDrawHighlightCircleEnabled = z2;
    }

    public void setHighlightCircleFillColor(int i3) {
        this.mHighlightCircleFillColor = i3;
    }

    public void setHighlightCircleInnerRadius(float f2) {
        this.mHighlightCircleInnerRadius = f2;
    }

    public void setHighlightCircleOuterRadius(float f2) {
        this.mHighlightCircleOuterRadius = f2;
    }

    public void setHighlightCircleStrokeAlpha(int i3) {
        this.mHighlightCircleStrokeAlpha = i3;
    }

    public void setHighlightCircleStrokeColor(int i3) {
        this.mHighlightCircleStrokeColor = i3;
    }

    public void setHighlightCircleStrokeWidth(float f2) {
        this.mHighlightCircleStrokeWidth = f2;
    }

    public void copy(RadarDataSet radarDataSet) {
        super.copy(radarDataSet);
        radarDataSet.mDrawHighlightCircleEnabled = this.mDrawHighlightCircleEnabled;
        radarDataSet.mHighlightCircleFillColor = this.mHighlightCircleFillColor;
        radarDataSet.mHighlightCircleInnerRadius = this.mHighlightCircleInnerRadius;
        radarDataSet.mHighlightCircleStrokeAlpha = this.mHighlightCircleStrokeAlpha;
        radarDataSet.mHighlightCircleStrokeColor = this.mHighlightCircleStrokeColor;
        radarDataSet.mHighlightCircleStrokeWidth = this.mHighlightCircleStrokeWidth;
    }
}
