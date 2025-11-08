package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.google.common.base.Objects;

public final class StarRating extends Rating {
    public static final Bundleable.Creator<StarRating> CREATOR = new a(8);
    private static final int FIELD_MAX_STARS = 1;
    private static final int FIELD_STAR_RATING = 2;
    private static final int MAX_STARS_DEFAULT = 5;
    private static final int TYPE = 2;
    @IntRange(from = 1)
    private final int maxStars;
    private final float starRating;

    public StarRating(@IntRange(from = 1) int i3) {
        Assertions.checkArgument(i3 > 0, "maxStars must be a positive integer");
        this.maxStars = i3;
        this.starRating = -1.0f;
    }

    /* access modifiers changed from: private */
    public static StarRating fromBundle(Bundle bundle) {
        boolean z2 = false;
        if (bundle.getInt(keyForField(0), -1) == 2) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        int i3 = bundle.getInt(keyForField(1), 5);
        float f2 = bundle.getFloat(keyForField(2), -1.0f);
        return f2 == -1.0f ? new StarRating(i3) : new StarRating(i3, f2);
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof StarRating)) {
            return false;
        }
        StarRating starRating2 = (StarRating) obj;
        return this.maxStars == starRating2.maxStars && this.starRating == starRating2.starRating;
    }

    @IntRange(from = 1)
    public int getMaxStars() {
        return this.maxStars;
    }

    public float getStarRating() {
        return this.starRating;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxStars), Float.valueOf(this.starRating));
    }

    public boolean isRated() {
        return this.starRating != -1.0f;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(0), 2);
        bundle.putInt(keyForField(1), this.maxStars);
        bundle.putFloat(keyForField(2), this.starRating);
        return bundle;
    }

    public StarRating(@IntRange(from = 1) int i3, @FloatRange(from = 0.0d) float f2) {
        boolean z2 = false;
        Assertions.checkArgument(i3 > 0, "maxStars must be a positive integer");
        if (f2 >= 0.0f && f2 <= ((float) i3)) {
            z2 = true;
        }
        Assertions.checkArgument(z2, "starRating is out of range [0, maxStars]");
        this.maxStars = i3;
        this.starRating = f2;
    }
}
