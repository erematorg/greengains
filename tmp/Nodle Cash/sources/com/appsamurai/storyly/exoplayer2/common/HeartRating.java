package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.work.impl.a;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.google.common.base.Objects;

public final class HeartRating extends Rating {
    public static final Bundleable.Creator<HeartRating> CREATOR = new a(26);
    private static final int FIELD_IS_HEART = 2;
    private static final int FIELD_RATED = 1;
    private static final int TYPE = 0;
    private final boolean isHeart;
    private final boolean rated;

    public HeartRating() {
        this.rated = false;
        this.isHeart = false;
    }

    /* access modifiers changed from: private */
    public static HeartRating fromBundle(Bundle bundle) {
        Assertions.checkArgument(bundle.getInt(keyForField(0), -1) == 0);
        return bundle.getBoolean(keyForField(1), false) ? new HeartRating(bundle.getBoolean(keyForField(2), false)) : new HeartRating();
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof HeartRating)) {
            return false;
        }
        HeartRating heartRating = (HeartRating) obj;
        return this.isHeart == heartRating.isHeart && this.rated == heartRating.rated;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.rated), Boolean.valueOf(this.isHeart));
    }

    public boolean isHeart() {
        return this.isHeart;
    }

    public boolean isRated() {
        return this.rated;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(0), 0);
        bundle.putBoolean(keyForField(1), this.rated);
        bundle.putBoolean(keyForField(2), this.isHeart);
        return bundle;
    }

    public HeartRating(boolean z2) {
        this.rated = true;
        this.isHeart = z2;
    }
}
