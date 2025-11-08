package com.google.android.material.carousel;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FullScreenCarouselStrategy extends CarouselStrategy {
    @NonNull
    public KeylineState onFirstChildMeasuredWithMargins(@NonNull Carousel carousel, @NonNull View view) {
        float containerHeight;
        int i3;
        int i4;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (carousel.isHorizontal()) {
            containerHeight = (float) carousel.getContainerWidth();
            i3 = layoutParams.leftMargin;
            i4 = layoutParams.rightMargin;
        } else {
            containerHeight = (float) carousel.getContainerHeight();
            i3 = layoutParams.topMargin;
            i4 = layoutParams.bottomMargin;
        }
        float f2 = (float) (i3 + i4);
        return CarouselStrategyHelper.createLeftAlignedKeylineState(view.getContext(), f2, containerHeight, new Arrangement(0, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0, Math.min(containerHeight + f2, containerHeight), 1, containerHeight));
    }
}
