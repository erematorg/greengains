package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

@SuppressLint({"ParcelCreator"})
public class BubbleEntry extends Entry {
    private float mSize;

    public BubbleEntry(float f2, float f3, float f4) {
        super(f2, f3);
        this.mSize = f4;
    }

    public float getSize() {
        return this.mSize;
    }

    public void setSize(float f2) {
        this.mSize = f2;
    }

    public BubbleEntry copy() {
        return new BubbleEntry(getX(), getY(), this.mSize, getData());
    }

    public BubbleEntry(float f2, float f3, float f4, Object obj) {
        super(f2, f3, obj);
        this.mSize = f4;
    }

    public BubbleEntry(float f2, float f3, float f4, Drawable drawable) {
        super(f2, f3, drawable);
        this.mSize = f4;
    }

    public BubbleEntry(float f2, float f3, float f4, Drawable drawable, Object obj) {
        super(f2, f3, drawable, obj);
        this.mSize = f4;
    }
}
