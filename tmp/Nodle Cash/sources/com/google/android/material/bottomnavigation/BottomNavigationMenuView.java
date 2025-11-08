package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List<Integer> tempChildWidths = new ArrayList();

    public BottomNavigationMenuView(@NonNull Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
    }

    @NonNull
    public NavigationBarItemView createNavigationBarItemView(@NonNull Context context) {
        return new BottomNavigationItemView(context);
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    public void onLayout(boolean z2, int i3, int i4, int i5, int i6) {
        int childCount = getChildCount();
        int i7 = i5 - i3;
        int i8 = i6 - i4;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    int i11 = i7 - i9;
                    childAt.layout(i11 - childAt.getMeasuredWidth(), 0, i11, i8);
                } else {
                    childAt.layout(i9, 0, childAt.getMeasuredWidth() + i9, i8);
                }
                i9 += childAt.getMeasuredWidth();
            }
        }
    }

    public void onMeasure(int i3, int i4) {
        int i5;
        int i6;
        MenuBuilder menu = getMenu();
        int size = View.MeasureSpec.getSize(i3);
        int size2 = menu.getVisibleItems().size();
        int childCount = getChildCount();
        this.tempChildWidths.clear();
        int size3 = View.MeasureSpec.getSize(i4);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, 1073741824);
        int i7 = 1;
        if (!isShifting(getLabelVisibilityMode(), size2) || !isItemHorizontalTranslationEnabled()) {
            if (size2 != 0) {
                i7 = size2;
            }
            int min = Math.min(size / i7, this.activeItemMaxWidth);
            int i8 = size - (size2 * min);
            for (int i9 = 0; i9 < childCount; i9++) {
                if (getChildAt(i9).getVisibility() == 8) {
                    i5 = 0;
                } else if (i8 > 0) {
                    i5 = min + 1;
                    i8--;
                } else {
                    i5 = min;
                }
                this.tempChildWidths.add(Integer.valueOf(i5));
            }
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            int i10 = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), makeMeasureSpec);
                i10 = Math.max(i10, childAt.getMeasuredWidth());
            }
            int i11 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int min2 = Math.min(size - (this.inactiveItemMinWidth * i11), Math.min(i10, this.activeItemMaxWidth));
            int i12 = size - min2;
            if (i11 != 0) {
                i7 = i11;
            }
            int min3 = Math.min(i12 / i7, this.inactiveItemMaxWidth);
            int i13 = i12 - (i11 * min3);
            int i14 = 0;
            while (i14 < childCount) {
                if (getChildAt(i14).getVisibility() != 8) {
                    i6 = i14 == getSelectedItemPosition() ? min2 : min3;
                    if (i13 > 0) {
                        i6++;
                        i13--;
                    }
                } else {
                    i6 = 0;
                }
                this.tempChildWidths.add(Integer.valueOf(i6));
                i14++;
            }
        }
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt2 = getChildAt(i16);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(i16).intValue(), 1073741824), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i15 = childAt2.getMeasuredWidth() + i15;
            }
        }
        setMeasuredDimension(i15, size3);
    }

    public void setItemHorizontalTranslationEnabled(boolean z2) {
        this.itemHorizontalTranslationEnabled = z2;
    }
}
