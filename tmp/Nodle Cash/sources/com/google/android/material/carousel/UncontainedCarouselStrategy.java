package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.KeylineState;

public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    private float calculateMediumChildSize(float f2, float f3, float f4) {
        float max = Math.max(1.5f * f4, f2);
        float f5 = MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD * f3;
        if (max > f5) {
            max = Math.max(f5, f4 * 1.2f);
        }
        return Math.min(f3, max);
    }

    private KeylineState createCenterAlignedKeylineState(float f2, float f3, float f4, int i3, float f5, float f6, float f7) {
        float f8 = f3;
        float f9 = f4;
        float f10 = f5;
        float min = Math.min(f6, f9);
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(min, f9, f8);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(f10, f9, f8);
        float f11 = f10 / 2.0f;
        float f12 = (f7 + 0.0f) - f11;
        float f13 = f12 + f11;
        float f14 = min / 2.0f;
        float f15 = (((float) i3) * f9) + f13;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f9, f2).addAnchorKeyline((f12 - f11) - f14, childMaskPercentage, min).addKeyline(f12, childMaskPercentage2, f10, false).addKeylineRange((f9 / 2.0f) + f13, 0.0f, f9, i3, true);
        addKeylineRange.addKeyline(f11 + f15, childMaskPercentage2, f10, false);
        addKeylineRange.addAnchorKeyline(f15 + f10 + f14, childMaskPercentage, min);
        return addKeylineRange.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context context, float f2, float f3, float f4, int i3, float f5, int i4, float f6) {
        float f7 = f2;
        float f8 = f4;
        float f9 = f5;
        float min = Math.min(f6, f8);
        float max = Math.max(min, 0.5f * f9);
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(max, f8, f7);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(min, f8, f7);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(f9, f8, f7);
        float f10 = (((float) i3) * f8) + 0.0f;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f8, f3).addAnchorKeyline(0.0f - (max / 2.0f), childMaskPercentage, max).addKeylineRange(f8 / 2.0f, 0.0f, f8, i3, true);
        if (i4 > 0) {
            float f11 = (f9 / 2.0f) + f10;
            f10 += f9;
            addKeylineRange.addKeyline(f11, childMaskPercentage3, f9, false);
        }
        addKeylineRange.addAnchorKeyline((CarouselStrategyHelper.getExtraSmallSize(context) / 2.0f) + f10, childMaskPercentage2, min);
        return addKeylineRange.build();
    }

    public boolean isContained() {
        return false;
    }

    @NonNull
    public KeylineState onFirstChildMeasuredWithMargins(@NonNull Carousel carousel, @NonNull View view) {
        float f2;
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f3 = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredHeight = (float) view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            float f4 = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredHeight = (float) view.getMeasuredWidth();
            f2 = f4;
        } else {
            f2 = f3;
        }
        float f5 = measuredHeight + f2;
        float extraSmallSize = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f2;
        float extraSmallSize2 = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f2;
        int max = Math.max(1, (int) Math.floor((double) (containerWidth / f5)));
        float f6 = containerWidth - (((float) max) * f5);
        if (carousel.getCarouselAlignment() == 1) {
            float f7 = f6 / 2.0f;
            return createCenterAlignedKeylineState(containerWidth, f2, f5, max, Math.max(Math.min(3.0f * f7, f5), getSmallItemSizeMin() + f2), extraSmallSize2, f7);
        }
        return createLeftAlignedKeylineState(view.getContext(), f2, containerWidth, f5, max, calculateMediumChildSize(extraSmallSize, f5, f6), f6 > 0.0f ? 1 : 0, extraSmallSize2);
    }
}
