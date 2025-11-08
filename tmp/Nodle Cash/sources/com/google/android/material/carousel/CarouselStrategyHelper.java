package com.google.android.material.carousel;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.material.R;
import com.google.android.material.carousel.KeylineState;

final class CarouselStrategyHelper {
    private CarouselStrategyHelper() {
    }

    public static float addEnd(float f2, float f3, int i3) {
        return (((float) Math.max(0, i3 - 1)) * f3) + f2;
    }

    public static float addStart(float f2, float f3, int i3) {
        return i3 > 0 ? (f3 / 2.0f) + f2 : f2;
    }

    public static KeylineState createCenterAlignedKeylineState(@NonNull Context context, float f2, float f3, @NonNull Arrangement arrangement) {
        float f4;
        float f5;
        float f6 = f2;
        float f7 = f3;
        Arrangement arrangement2 = arrangement;
        float min = Math.min(getExtraSmallSize(context) + f6, arrangement2.largeSize);
        float f8 = min / 2.0f;
        float f9 = 0.0f - f8;
        float addStart = addStart(0.0f, arrangement2.smallSize, arrangement2.smallCount);
        float updateCurPosition = updateCurPosition(0.0f, addEnd(addStart, arrangement2.smallSize, (int) Math.floor((double) (((float) arrangement2.smallCount) / 2.0f))), arrangement2.smallSize, arrangement2.smallCount);
        float addStart2 = addStart(updateCurPosition, arrangement2.mediumSize, arrangement2.mediumCount);
        float updateCurPosition2 = updateCurPosition(updateCurPosition, addEnd(addStart2, arrangement2.mediumSize, (int) Math.floor((double) (((float) arrangement2.mediumCount) / 2.0f))), arrangement2.mediumSize, arrangement2.mediumCount);
        float addStart3 = addStart(updateCurPosition2, arrangement2.largeSize, arrangement2.largeCount);
        float updateCurPosition3 = updateCurPosition(updateCurPosition2, addEnd(addStart3, arrangement2.largeSize, arrangement2.largeCount), arrangement2.largeSize, arrangement2.largeCount);
        float addStart4 = addStart(updateCurPosition3, arrangement2.mediumSize, arrangement2.mediumCount);
        float addStart5 = addStart(updateCurPosition(updateCurPosition3, addEnd(addStart4, arrangement2.mediumSize, (int) Math.ceil((double) (((float) arrangement2.mediumCount) / 2.0f))), arrangement2.mediumSize, arrangement2.mediumCount), arrangement2.smallSize, arrangement2.smallCount);
        float f10 = f8 + f7;
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(min, arrangement2.largeSize, f6);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(arrangement2.smallSize, arrangement2.largeSize, f6);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(arrangement2.mediumSize, arrangement2.largeSize, f6);
        KeylineState.Builder addAnchorKeyline = new KeylineState.Builder(arrangement2.largeSize, f7).addAnchorKeyline(f9, childMaskPercentage, min);
        int i3 = arrangement2.smallCount;
        if (i3 > 0) {
            f4 = f10;
            addAnchorKeyline.addKeylineRange(addStart, childMaskPercentage2, arrangement2.smallSize, (int) Math.floor((double) (((float) i3) / 2.0f)));
        } else {
            f4 = f10;
        }
        int i4 = arrangement2.mediumCount;
        if (i4 > 0) {
            addAnchorKeyline.addKeylineRange(addStart2, childMaskPercentage3, arrangement2.mediumSize, (int) Math.floor((double) (((float) i4) / 2.0f)));
        }
        float f11 = childMaskPercentage2;
        addAnchorKeyline.addKeylineRange(addStart3, 0.0f, arrangement2.largeSize, arrangement2.largeCount, true);
        int i5 = arrangement2.mediumCount;
        if (i5 > 0) {
            f5 = 2.0f;
            addAnchorKeyline.addKeylineRange(addStart4, childMaskPercentage3, arrangement2.mediumSize, (int) Math.ceil((double) (((float) i5) / 2.0f)));
        } else {
            f5 = 2.0f;
        }
        int i6 = arrangement2.smallCount;
        if (i6 > 0) {
            addAnchorKeyline.addKeylineRange(addStart5, f11, arrangement2.smallSize, (int) Math.ceil((double) (((float) i6) / f5)));
        }
        addAnchorKeyline.addAnchorKeyline(f4, childMaskPercentage, min);
        return addAnchorKeyline.build();
    }

    public static KeylineState createKeylineState(@NonNull Context context, float f2, float f3, @NonNull Arrangement arrangement, int i3) {
        return i3 == 1 ? createCenterAlignedKeylineState(context, f2, f3, arrangement) : createLeftAlignedKeylineState(context, f2, f3, arrangement);
    }

    public static KeylineState createLeftAlignedKeylineState(@NonNull Context context, float f2, float f3, @NonNull Arrangement arrangement) {
        float min = Math.min(getExtraSmallSize(context) + f2, arrangement.largeSize);
        float f4 = min / 2.0f;
        float f5 = 0.0f - f4;
        float addStart = addStart(0.0f, arrangement.largeSize, arrangement.largeCount);
        float updateCurPosition = updateCurPosition(0.0f, addEnd(addStart, arrangement.largeSize, arrangement.largeCount), arrangement.largeSize, arrangement.largeCount);
        float addStart2 = addStart(updateCurPosition, arrangement.mediumSize, arrangement.mediumCount);
        float addStart3 = addStart(updateCurPosition(updateCurPosition, addStart2, arrangement.mediumSize, arrangement.mediumCount), arrangement.smallSize, arrangement.smallCount);
        float f6 = f4 + f3;
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(min, arrangement.largeSize, f2);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(arrangement.smallSize, arrangement.largeSize, f2);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(arrangement.mediumSize, arrangement.largeSize, f2);
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(arrangement.largeSize, f3).addAnchorKeyline(f5, childMaskPercentage, min).addKeylineRange(addStart, 0.0f, arrangement.largeSize, arrangement.largeCount, true);
        if (arrangement.mediumCount > 0) {
            addKeylineRange.addKeyline(addStart2, childMaskPercentage3, arrangement.mediumSize);
        }
        int i3 = arrangement.smallCount;
        if (i3 > 0) {
            addKeylineRange.addKeylineRange(addStart3, childMaskPercentage2, arrangement.smallSize, i3);
        }
        addKeylineRange.addAnchorKeyline(f6, childMaskPercentage, min);
        return addKeylineRange.build();
    }

    public static float getExtraSmallSize(@NonNull Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_gone_size);
    }

    public static float getSmallSizeMax(@NonNull Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
    }

    public static float getSmallSizeMin(@NonNull Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
    }

    public static int maxValue(int[] iArr) {
        int i3 = Integer.MIN_VALUE;
        for (int i4 : iArr) {
            if (i4 > i3) {
                i3 = i4;
            }
        }
        return i3;
    }

    public static float updateCurPosition(float f2, float f3, float f4, int i3) {
        return i3 > 0 ? (f4 / 2.0f) + f3 : f2;
    }
}
