package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

final class LeftSheetDelegate extends SheetDelegate {
    final SideSheetBehavior<? extends View> sheetBehavior;

    public LeftSheetDelegate(@NonNull SideSheetBehavior<? extends View> sideSheetBehavior) {
        this.sheetBehavior = sideSheetBehavior;
    }

    public int calculateInnerMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    public float calculateSlideOffset(int i3) {
        float hiddenOffset = (float) getHiddenOffset();
        return (((float) i3) - hiddenOffset) / (((float) getExpandedOffset()) - hiddenOffset);
    }

    public int getCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    public int getExpandedOffset() {
        return Math.max(0, this.sheetBehavior.getInnerMargin() + this.sheetBehavior.getParentInnerEdge());
    }

    public int getHiddenOffset() {
        return (-this.sheetBehavior.getChildWidth()) - this.sheetBehavior.getInnerMargin();
    }

    public int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.getInnerMargin();
    }

    public int getMinViewPositionHorizontal() {
        return -this.sheetBehavior.getChildWidth();
    }

    public <V extends View> int getOuterEdge(@NonNull V v2) {
        return this.sheetBehavior.getInnerMargin() + v2.getRight();
    }

    public int getParentInnerEdge(@NonNull CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getLeft();
    }

    public int getSheetEdge() {
        return 1;
    }

    public boolean isExpandingOutwards(float f2) {
        return f2 > 0.0f;
    }

    public boolean isReleasedCloseToInnerEdge(@NonNull View view) {
        return view.getRight() < (getExpandedOffset() - getHiddenOffset()) / 2;
    }

    public boolean isSwipeSignificant(float f2, float f3) {
        return SheetUtils.isSwipeMostlyHorizontal(f2, f3) && Math.abs(f2) > ((float) this.sheetBehavior.getSignificantVelocityThreshold());
    }

    public boolean shouldHide(@NonNull View view, float f2) {
        return Math.abs((this.sheetBehavior.getHideFriction() * f2) + ((float) view.getLeft())) > this.sheetBehavior.getHideThreshold();
    }

    public void updateCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i3) {
        marginLayoutParams.leftMargin = i3;
    }

    public void updateCoplanarSiblingLayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i3, int i4) {
        if (i3 <= this.sheetBehavior.getParentWidth()) {
            marginLayoutParams.leftMargin = i4;
        }
    }
}
