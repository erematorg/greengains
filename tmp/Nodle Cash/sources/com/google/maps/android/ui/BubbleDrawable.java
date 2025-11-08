package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.google.maps.android.R;

class BubbleDrawable extends Drawable {
    private int mColor = -1;
    private final Drawable mMask;
    private final Drawable mShadow;

    public BubbleDrawable(Context context) {
        this.mMask = ContextCompat.getDrawable(context, R.drawable.amu_bubble_mask);
        this.mShadow = ContextCompat.getDrawable(context, R.drawable.amu_bubble_shadow);
    }

    public void draw(Canvas canvas) {
        this.mMask.draw(canvas);
        canvas.drawColor(this.mColor, PorterDuff.Mode.SRC_IN);
        this.mShadow.draw(canvas);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(Rect rect) {
        return this.mMask.getPadding(rect);
    }

    public void setAlpha(int i3) {
        throw new UnsupportedOperationException();
    }

    public void setBounds(int i3, int i4, int i5, int i6) {
        this.mMask.setBounds(i3, i4, i5, i6);
        this.mShadow.setBounds(i3, i4, i5, i6);
    }

    public void setColor(int i3) {
        this.mColor = i3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    public void setBounds(Rect rect) {
        this.mMask.setBounds(rect);
        this.mShadow.setBounds(rect);
    }
}
