package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

class ViewOffsetHelper {
    private boolean horizontalOffsetEnabled = true;
    private int layoutLeft;
    private int layoutTop;
    private int offsetLeft;
    private int offsetTop;
    private boolean verticalOffsetEnabled = true;
    private final View view;

    public ViewOffsetHelper(View view2) {
        this.view = view2;
    }

    public void applyOffsets() {
        View view2 = this.view;
        ViewCompat.offsetTopAndBottom(view2, this.offsetTop - (view2.getTop() - this.layoutTop));
        View view3 = this.view;
        ViewCompat.offsetLeftAndRight(view3, this.offsetLeft - (view3.getLeft() - this.layoutLeft));
    }

    public int getLayoutLeft() {
        return this.layoutLeft;
    }

    public int getLayoutTop() {
        return this.layoutTop;
    }

    public int getLeftAndRightOffset() {
        return this.offsetLeft;
    }

    public int getTopAndBottomOffset() {
        return this.offsetTop;
    }

    public boolean isHorizontalOffsetEnabled() {
        return this.horizontalOffsetEnabled;
    }

    public boolean isVerticalOffsetEnabled() {
        return this.verticalOffsetEnabled;
    }

    public void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
    }

    public void setHorizontalOffsetEnabled(boolean z2) {
        this.horizontalOffsetEnabled = z2;
    }

    public boolean setLeftAndRightOffset(int i3) {
        if (!this.horizontalOffsetEnabled || this.offsetLeft == i3) {
            return false;
        }
        this.offsetLeft = i3;
        applyOffsets();
        return true;
    }

    public boolean setTopAndBottomOffset(int i3) {
        if (!this.verticalOffsetEnabled || this.offsetTop == i3) {
            return false;
        }
        this.offsetTop = i3;
        applyOffsets();
        return true;
    }

    public void setVerticalOffsetEnabled(boolean z2) {
        this.verticalOffsetEnabled = z2;
    }
}
