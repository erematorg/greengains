package com.google.android.material.internal;

import android.content.Context;
import android.support.v4.media.session.a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
    private int baseline = -1;

    public BaselineLayout(Context context) {
        super(context, (AttributeSet) null, 0);
    }

    public int getBaseline() {
        return this.baseline;
    }

    public void onLayout(boolean z2, int i3, int i4, int i5, int i6) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i5 - i3) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int d2 = a.d(paddingRight, measuredWidth, 2, paddingLeft);
                int baseline2 = (this.baseline == -1 || childAt.getBaseline() == -1) ? paddingTop : (this.baseline + paddingTop) - childAt.getBaseline();
                childAt.layout(d2, baseline2, measuredWidth + d2, measuredHeight + baseline2);
            }
        }
    }

    public void onMeasure(int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = -1;
        int i9 = -1;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i3, i4);
                int baseline2 = childAt.getBaseline();
                if (baseline2 != -1) {
                    i8 = Math.max(i8, baseline2);
                    i9 = Math.max(i9, childAt.getMeasuredHeight() - baseline2);
                }
                i6 = Math.max(i6, childAt.getMeasuredWidth());
                i5 = Math.max(i5, childAt.getMeasuredHeight());
                i7 = View.combineMeasuredStates(i7, childAt.getMeasuredState());
            }
        }
        if (i8 != -1) {
            i5 = Math.max(i5, Math.max(i9, getPaddingBottom()) + i8);
            this.baseline = i8;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i6, getSuggestedMinimumWidth()), i3, i7), View.resolveSizeAndState(Math.max(i5, getSuggestedMinimumHeight()), i4, i7 << 16));
    }

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public BaselineLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
