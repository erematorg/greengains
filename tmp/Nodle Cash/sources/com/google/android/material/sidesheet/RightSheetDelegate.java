package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior<? extends View> sheetBehavior;

    public RightSheetDelegate(@NonNull SideSheetBehavior<? extends View> sideSheetBehavior) {
        this.sheetBehavior = sideSheetBehavior;
    }

    public int calculateInnerMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    public float calculateSlideOffset(int i3) {
        float hiddenOffset = (float) getHiddenOffset();
        return (hiddenOffset - ((float) i3)) / (hiddenOffset - ((float) getExpandedOffset()));
    }

    public int getCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    public int getExpandedOffset() {
        return Math.max(0, (getHiddenOffset() - this.sheetBehavior.getChildWidth()) - this.sheetBehavior.getInnerMargin());
    }

    public int getHiddenOffset() {
        return this.sheetBehavior.getParentWidth();
    }

    public int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.getParentWidth();
    }

    public int getMinViewPositionHorizontal() {
        return getExpandedOffset();
    }

    public <V extends View> int getOuterEdge(@NonNull V v2) {
        return v2.getLeft() - this.sheetBehavior.getInnerMargin();
    }

    public int getParentInnerEdge(@NonNull CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getRight();
    }

    public int getSheetEdge() {
        return 0;
    }

    public boolean isExpandingOutwards(float f2) {
        return f2 < 0.0f;
    }

    public boolean isReleasedCloseToInnerEdge(@NonNull View view) {
        return view.getLeft() > (getHiddenOffset() + getExpandedOffset()) / 2;
    }

    public boolean isSwipeSignificant(float f2, float f3) {
        return SheetUtils.isSwipeMostlyHorizontal(f2, f3) && Math.abs(f2) > ((float) this.sheetBehavior.getSignificantVelocityThreshold());
    }

    public boolean shouldHide(@NonNull View view, float f2) {
        return Math.abs((this.sheetBehavior.getHideFriction() * f2) + ((float) view.getRight())) > this.sheetBehavior.getHideThreshold();
    }

    public void updateCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i3) {
        marginLayoutParams.rightMargin = i3;
    }

    public void updateCoplanarSiblingLayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i3, int i4) {
        int parentWidth = this.sheetBehavior.getParentWidth();
        if (i3 <= parentWidth) {
            marginLayoutParams.rightMargin = parentWidth - i3;
        }
    }
}
