package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

abstract class CarouselOrientationHelper {
    final int orientation;

    private static CarouselOrientationHelper createHorizontalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(0) {
            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.left;
                float f3 = rectF3.left;
                if (f2 < f3 && rectF2.right > f3) {
                    float f4 = f3 - f2;
                    rectF.left += f4;
                    rectF2.left += f4;
                }
                float f5 = rectF2.right;
                float f6 = rectF3.right;
                if (f5 > f6 && rectF2.left < f6) {
                    float f7 = f5 - f6;
                    rectF.right = Math.max(rectF.right - f7, rectF.left);
                    rectF2.right = Math.max(rectF2.right - f7, rectF2.left);
                }
            }

            public int getDecoratedCrossAxisMeasurement(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }

            public float getMaskMargins(RecyclerView.LayoutParams layoutParams) {
                return (float) (layoutParams.rightMargin + layoutParams.leftMargin);
            }

            public RectF getMaskRect(float f2, float f3, float f4, float f5) {
                return new RectF(f5, 0.0f, f3 - f5, f2);
            }

            public int getParentBottom() {
                return carouselLayoutManager.getHeight() - carouselLayoutManager.getPaddingBottom();
            }

            public int getParentEnd() {
                return carouselLayoutManager.isLayoutRtl() ? getParentLeft() : getParentRight();
            }

            public int getParentLeft() {
                return 0;
            }

            public int getParentRight() {
                return carouselLayoutManager.getWidth();
            }

            public int getParentStart() {
                return carouselLayoutManager.isLayoutRtl() ? getParentRight() : getParentLeft();
            }

            public int getParentTop() {
                return carouselLayoutManager.getPaddingTop();
            }

            public void layoutDecoratedWithMargins(View view, int i3, int i4) {
                int parentTop = getParentTop();
                carouselLayoutManager.layoutDecoratedWithMargins(view, i3, parentTop, i4, parentTop + getDecoratedCrossAxisMeasurement(view));
            }

            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.right <= rectF3.left) {
                    float floor = ((float) Math.floor((double) rectF.right)) - 1.0f;
                    rectF.right = floor;
                    rectF.left = Math.min(rectF.left, floor);
                }
                if (rectF2.left >= rectF3.right) {
                    float ceil = ((float) Math.ceil((double) rectF.left)) + 1.0f;
                    rectF.left = ceil;
                    rectF.right = Math.max(ceil, rectF.right);
                }
            }

            public void offsetChild(View view, Rect rect, float f2, float f3) {
                view.offsetLeftAndRight((int) (f3 - (((float) rect.left) + f2)));
            }
        };
    }

    public static CarouselOrientationHelper createOrientationHelper(CarouselLayoutManager carouselLayoutManager, int i3) {
        if (i3 == 0) {
            return createHorizontalHelper(carouselLayoutManager);
        }
        if (i3 == 1) {
            return createVerticalHelper(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    private static CarouselOrientationHelper createVerticalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(1) {
            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.top;
                float f3 = rectF3.top;
                if (f2 < f3 && rectF2.bottom > f3) {
                    float f4 = f3 - f2;
                    rectF.top += f4;
                    rectF3.top += f4;
                }
                float f5 = rectF2.bottom;
                float f6 = rectF3.bottom;
                if (f5 > f6 && rectF2.top < f6) {
                    float f7 = f5 - f6;
                    rectF.bottom = Math.max(rectF.bottom - f7, rectF.top);
                    rectF2.bottom = Math.max(rectF2.bottom - f7, rectF2.top);
                }
            }

            public int getDecoratedCrossAxisMeasurement(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }

            public float getMaskMargins(RecyclerView.LayoutParams layoutParams) {
                return (float) (layoutParams.topMargin + layoutParams.bottomMargin);
            }

            public RectF getMaskRect(float f2, float f3, float f4, float f5) {
                return new RectF(0.0f, f4, f3, f2 - f4);
            }

            public int getParentBottom() {
                return carouselLayoutManager.getHeight();
            }

            public int getParentEnd() {
                return getParentBottom();
            }

            public int getParentLeft() {
                return carouselLayoutManager.getPaddingLeft();
            }

            public int getParentRight() {
                return carouselLayoutManager.getWidth() - carouselLayoutManager.getPaddingRight();
            }

            public int getParentStart() {
                return getParentTop();
            }

            public int getParentTop() {
                return 0;
            }

            public void layoutDecoratedWithMargins(View view, int i3, int i4) {
                int parentLeft = getParentLeft();
                carouselLayoutManager.layoutDecoratedWithMargins(view, parentLeft, i3, parentLeft + getDecoratedCrossAxisMeasurement(view), i4);
            }

            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.bottom <= rectF3.top) {
                    float floor = ((float) Math.floor((double) rectF.bottom)) - 1.0f;
                    rectF.bottom = floor;
                    rectF.top = Math.min(rectF.top, floor);
                }
                if (rectF2.top >= rectF3.bottom) {
                    float ceil = ((float) Math.ceil((double) rectF.top)) + 1.0f;
                    rectF.top = ceil;
                    rectF.bottom = Math.max(ceil, rectF.bottom);
                }
            }

            public void offsetChild(View view, Rect rect, float f2, float f3) {
                view.offsetTopAndBottom((int) (f3 - (((float) rect.top) + f2)));
            }
        };
    }

    public abstract void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3);

    public abstract int getDecoratedCrossAxisMeasurement(View view);

    public abstract float getMaskMargins(RecyclerView.LayoutParams layoutParams);

    public abstract RectF getMaskRect(float f2, float f3, float f4, float f5);

    public abstract int getParentBottom();

    public abstract int getParentEnd();

    public abstract int getParentLeft();

    public abstract int getParentRight();

    public abstract int getParentStart();

    public abstract int getParentTop();

    public abstract void layoutDecoratedWithMargins(View view, int i3, int i4);

    public abstract void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3);

    public abstract void offsetChild(View view, Rect rect, float f2, float f3);

    private CarouselOrientationHelper(int i3) {
        this.orientation = i3;
    }
}
