package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    private boolean mDrawFilled = false;
    private int mFillAlpha = 85;
    private int mFillColor = Color.rgb(140, 234, 255);
    protected Drawable mFillDrawable;
    private float mLineWidth = 2.5f;

    public LineRadarDataSet(List<T> list, String str) {
        super(list, str);
    }

    public void copy(LineRadarDataSet lineRadarDataSet) {
        super.copy(lineRadarDataSet);
        lineRadarDataSet.mDrawFilled = this.mDrawFilled;
        lineRadarDataSet.mFillAlpha = this.mFillAlpha;
        lineRadarDataSet.mFillColor = this.mFillColor;
        lineRadarDataSet.mFillDrawable = this.mFillDrawable;
        lineRadarDataSet.mLineWidth = this.mLineWidth;
    }

    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public boolean isDrawFilledEnabled() {
        return this.mDrawFilled;
    }

    public void setDrawFilled(boolean z2) {
        this.mDrawFilled = z2;
    }

    public void setFillAlpha(int i3) {
        this.mFillAlpha = i3;
    }

    public void setFillColor(int i3) {
        this.mFillColor = i3;
        this.mFillDrawable = null;
    }

    @TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    public void setLineWidth(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 10.0f) {
            f2 = 10.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel(f2);
    }
}
