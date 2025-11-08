package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.utils.Utils;

public abstract class ComponentBase {
    protected boolean mEnabled = true;
    protected int mTextColor = ViewCompat.MEASURED_STATE_MASK;
    protected float mTextSize = Utils.convertDpToPixel(10.0f);
    protected Typeface mTypeface = null;
    protected float mXOffset = 5.0f;
    protected float mYOffset = 5.0f;

    public int getTextColor() {
        return this.mTextColor;
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public float getXOffset() {
        return this.mXOffset;
    }

    public float getYOffset() {
        return this.mYOffset;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z2) {
        this.mEnabled = z2;
    }

    public void setTextColor(int i3) {
        this.mTextColor = i3;
    }

    public void setTextSize(float f2) {
        if (f2 > 24.0f) {
            f2 = 24.0f;
        }
        if (f2 < 6.0f) {
            f2 = 6.0f;
        }
        this.mTextSize = Utils.convertDpToPixel(f2);
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    public void setXOffset(float f2) {
        this.mXOffset = Utils.convertDpToPixel(f2);
    }

    public void setYOffset(float f2) {
        this.mYOffset = Utils.convertDpToPixel(f2);
    }
}
