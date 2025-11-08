package com.appsamurai.storyly.exoplayer2.common;

import A.a;
import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;

public abstract class Rating implements Bundleable {
    public static final Bundleable.Creator<Rating> CREATOR = new a(7);
    static final int FIELD_RATING_TYPE = 0;
    static final int RATING_TYPE_HEART = 0;
    static final int RATING_TYPE_PERCENTAGE = 1;
    static final int RATING_TYPE_STAR = 2;
    static final int RATING_TYPE_THUMB = 3;
    static final int RATING_TYPE_UNSET = -1;
    static final float RATING_UNSET = -1.0f;

    /* access modifiers changed from: private */
    public static Rating fromBundle(Bundle bundle) {
        int i3 = bundle.getInt(keyForField(0), -1);
        if (i3 == 0) {
            return HeartRating.CREATOR.fromBundle(bundle);
        }
        if (i3 == 1) {
            return PercentageRating.CREATOR.fromBundle(bundle);
        }
        if (i3 == 2) {
            return StarRating.CREATOR.fromBundle(bundle);
        }
        if (i3 == 3) {
            return ThumbRating.CREATOR.fromBundle(bundle);
        }
        throw new IllegalArgumentException(a.k("Unknown RatingType: ", i3));
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public abstract boolean isRated();
}
