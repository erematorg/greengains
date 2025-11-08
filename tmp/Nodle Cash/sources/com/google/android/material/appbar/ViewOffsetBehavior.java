package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private int tempLeftRightOffset = 0;
    private int tempTopBottomOffset = 0;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
    }

    public int getLeftAndRightOffset() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.getLeftAndRightOffset();
        }
        return 0;
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.getTopAndBottomOffset();
        }
        return 0;
    }

    public boolean isHorizontalOffsetEnabled() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        return viewOffsetHelper2 != null && viewOffsetHelper2.isHorizontalOffsetEnabled();
    }

    public boolean isVerticalOffsetEnabled() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        return viewOffsetHelper2 != null && viewOffsetHelper2.isVerticalOffsetEnabled();
    }

    public void layoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, int i3) {
        coordinatorLayout.onLayoutChild(v2, i3);
    }

    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, int i3) {
        layoutChild(coordinatorLayout, v2, i3);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(v2);
        }
        this.viewOffsetHelper.onViewLayout();
        this.viewOffsetHelper.applyOffsets();
        int i4 = this.tempTopBottomOffset;
        if (i4 != 0) {
            this.viewOffsetHelper.setTopAndBottomOffset(i4);
            this.tempTopBottomOffset = 0;
        }
        int i5 = this.tempLeftRightOffset;
        if (i5 == 0) {
            return true;
        }
        this.viewOffsetHelper.setLeftAndRightOffset(i5);
        this.tempLeftRightOffset = 0;
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean z2) {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            viewOffsetHelper2.setHorizontalOffsetEnabled(z2);
        }
    }

    public boolean setLeftAndRightOffset(int i3) {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.setLeftAndRightOffset(i3);
        }
        this.tempLeftRightOffset = i3;
        return false;
    }

    public boolean setTopAndBottomOffset(int i3) {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.setTopAndBottomOffset(i3);
        }
        this.tempTopBottomOffset = i3;
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z2) {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            viewOffsetHelper2.setVerticalOffsetEnabled(z2);
        }
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
