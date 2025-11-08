package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationRailMenuView extends NavigationBarMenuView {
    @Px
    private int itemMinimumHeight = -1;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(@NonNull Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = layoutParams2;
        layoutParams2.gravity = 49;
        setLayoutParams(layoutParams2);
        setItemActiveIndicatorResizeable(true);
    }

    private int makeSharedHeightSpec(int i3, int i4, int i5) {
        int max = i4 / Math.max(1, i5);
        int i6 = this.itemMinimumHeight;
        if (i6 == -1) {
            i6 = View.MeasureSpec.getSize(i3);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(i6, max), 0);
    }

    private int measureChildHeight(View view, int i3, int i4) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        view.measure(i3, i4);
        return view.getMeasuredHeight();
    }

    private int measureSharedChildHeights(int i3, int i4, int i5, View view) {
        int makeSharedHeightSpec = view == null ? makeSharedHeightSpec(i3, i4, i5) : View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        int childCount = getChildCount();
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt != view) {
                i6 += measureChildHeight(childAt, i3, makeSharedHeightSpec);
            }
        }
        return i6;
    }

    private int measureShiftingChildHeights(int i3, int i4, int i5) {
        int i6;
        View childAt = getChildAt(getSelectedItemPosition());
        if (childAt != null) {
            i6 = measureChildHeight(childAt, i3, makeSharedHeightSpec(i3, i4, i5));
            i4 -= i6;
            i5--;
        } else {
            i6 = 0;
        }
        return i6 + measureSharedChildHeights(i3, i4, i5, childAt);
    }

    @NonNull
    public NavigationBarItemView createNavigationBarItemView(@NonNull Context context) {
        return new NavigationRailItemView(context);
    }

    @Px
    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    public int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    public boolean isTopGravity() {
        return (this.layoutParams.gravity & 112) == 48;
    }

    public void onLayout(boolean z2, int i3, int i4, int i5, int i6) {
        int childCount = getChildCount();
        int i7 = i5 - i3;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i8;
                childAt.layout(0, i8, i7, measuredHeight);
                i8 = measuredHeight;
            }
        }
    }

    public void onMeasure(int i3, int i4) {
        int size = View.MeasureSpec.getSize(i4);
        int size2 = getMenu().getVisibleItems().size();
        setMeasuredDimension(View.MeasureSpec.getSize(i3), View.resolveSizeAndState((size2 <= 1 || !isShifting(getLabelVisibilityMode(), size2)) ? measureSharedChildHeights(i3, size, size2, (View) null) : measureShiftingChildHeights(i3, size, size2), i4, 0));
    }

    public void setItemMinimumHeight(@Px int i3) {
        if (this.itemMinimumHeight != i3) {
            this.itemMinimumHeight = i3;
            requestLayout();
        }
    }

    public void setMenuGravity(int i3) {
        FrameLayout.LayoutParams layoutParams2 = this.layoutParams;
        if (layoutParams2.gravity != i3) {
            layoutParams2.gravity = i3;
            setLayoutParams(layoutParams2);
        }
    }
}
