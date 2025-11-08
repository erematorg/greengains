package com.google.android.material.carousel;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.compose.animation.core.a;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

public final class MultiBrowseCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = {1, 0};
    private static final int[] SMALL_COUNTS = {1};
    private int keylineCount = 0;

    public boolean ensureArrangementFitsItemCount(Arrangement arrangement, int i3) {
        int itemCount = arrangement.getItemCount() - i3;
        boolean z2 = itemCount > 0 && (arrangement.smallCount > 0 || arrangement.mediumCount > 1);
        while (itemCount > 0) {
            int i4 = arrangement.smallCount;
            if (i4 > 0) {
                arrangement.smallCount = i4 - 1;
            } else {
                int i5 = arrangement.mediumCount;
                if (i5 > 1) {
                    arrangement.mediumCount = i5 - 1;
                }
            }
            itemCount--;
        }
        return z2;
    }

    @NonNull
    public KeylineState onFirstChildMeasuredWithMargins(@NonNull Carousel carousel, @NonNull View view) {
        float containerHeight = (float) carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = (float) carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredHeight = (float) view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            f2 = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredHeight = (float) view.getMeasuredWidth();
        }
        float f3 = f2;
        float smallItemSizeMin = getSmallItemSizeMin() + f3;
        float max = Math.max(getSmallItemSizeMax() + f3, smallItemSizeMin);
        float min = Math.min(measuredHeight + f3, containerHeight);
        float clamp = MathUtils.clamp((measuredHeight / 3.0f) + f3, smallItemSizeMin + f3, max + f3);
        float f4 = (min + clamp) / 2.0f;
        int[] iArr = SMALL_COUNTS;
        if (containerHeight < 2.0f * smallItemSizeMin) {
            iArr = new int[]{0};
        }
        int[] iArr2 = MEDIUM_COUNTS;
        if (carousel.getCarouselAlignment() == 1) {
            iArr = CarouselStrategy.doubleCounts(iArr);
            iArr2 = CarouselStrategy.doubleCounts(iArr2);
        }
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        float maxValue = (float) CarouselStrategyHelper.maxValue(iArr3);
        int ceil = (int) Math.ceil((double) (containerHeight / min));
        int max2 = (ceil - ((int) Math.max(1.0d, Math.floor((double) a.a(max, maxValue, containerHeight - (((float) CarouselStrategyHelper.maxValue(iArr4)) * f4), min))))) + 1;
        int[] iArr5 = new int[max2];
        for (int i3 = 0; i3 < max2; i3++) {
            iArr5[i3] = ceil - i3;
        }
        Arrangement findLowestCostArrangement = Arrangement.findLowestCostArrangement(containerHeight, clamp, smallItemSizeMin, max, iArr3, f4, iArr4, min, iArr5);
        this.keylineCount = findLowestCostArrangement.getItemCount();
        if (ensureArrangementFitsItemCount(findLowestCostArrangement, carousel.getItemCount())) {
            findLowestCostArrangement = Arrangement.findLowestCostArrangement(containerHeight, clamp, smallItemSizeMin, max, new int[]{findLowestCostArrangement.smallCount}, f4, new int[]{findLowestCostArrangement.mediumCount}, min, new int[]{findLowestCostArrangement.largeCount});
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f3, containerHeight, findLowestCostArrangement, carousel.getCarouselAlignment());
    }

    public boolean shouldRefreshKeylineState(Carousel carousel, int i3) {
        return (i3 < this.keylineCount && carousel.getItemCount() >= this.keylineCount) || (i3 >= this.keylineCount && carousel.getItemCount() < this.keylineCount);
    }
}
