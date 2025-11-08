package com.google.android.material.carousel;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.compose.animation.core.a;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = {0, 1};
    private static final int[] SMALL_COUNTS = {1};
    private int keylineCount = 0;

    @NonNull
    public KeylineState onFirstChildMeasuredWithMargins(@NonNull Carousel carousel, @NonNull View view) {
        int i3;
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredWidth = (float) (view.getMeasuredWidth() * 2);
        if (carousel.isHorizontal()) {
            f2 = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredWidth = (float) (view.getMeasuredHeight() * 2);
        }
        float smallItemSizeMin = getSmallItemSizeMin() + f2;
        float max = Math.max(getSmallItemSizeMax() + f2, smallItemSizeMin);
        float f3 = (float) containerHeight;
        float min = Math.min(measuredWidth + f2, f3);
        float clamp = MathUtils.clamp((measuredWidth / 3.0f) + f2, smallItemSizeMin + f2, max + f2);
        float f4 = (min + clamp) / 2.0f;
        int[] iArr = SMALL_COUNTS;
        int[] iArr2 = f3 < 2.0f * smallItemSizeMin ? new int[]{0} : iArr;
        int max2 = (int) Math.max(1.0d, Math.floor((double) a.a(max, (float) CarouselStrategyHelper.maxValue(iArr), f3, min)));
        int ceil = (((int) Math.ceil((double) (f3 / min))) - max2) + 1;
        int[] iArr3 = new int[ceil];
        for (int i4 = 0; i4 < ceil; i4++) {
            iArr3[i4] = max2 + i4;
        }
        int i5 = carousel.getCarouselAlignment() == 1 ? 1 : 0;
        int[] iArr4 = iArr3;
        Arrangement findLowestCostArrangement = Arrangement.findLowestCostArrangement(f3, clamp, smallItemSizeMin, max, i5 != 0 ? CarouselStrategy.doubleCounts(iArr2) : iArr2, f4, i5 != 0 ? CarouselStrategy.doubleCounts(MEDIUM_COUNTS) : MEDIUM_COUNTS, min, iArr3);
        this.keylineCount = findLowestCostArrangement.getItemCount();
        if (findLowestCostArrangement.getItemCount() > carousel.getItemCount()) {
            findLowestCostArrangement = Arrangement.findLowestCostArrangement(f3, clamp, smallItemSizeMin, max, iArr2, f4, MEDIUM_COUNTS, min, iArr4);
            i3 = 0;
        } else {
            i3 = i5;
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f2, f3, findLowestCostArrangement, i3);
    }

    public boolean shouldRefreshKeylineState(@NonNull Carousel carousel, int i3) {
        if (carousel.getCarouselAlignment() == 1) {
            if (i3 >= this.keylineCount || carousel.getItemCount() < this.keylineCount) {
                return i3 >= this.keylineCount && carousel.getItemCount() < this.keylineCount;
            }
            return true;
        }
    }
}
