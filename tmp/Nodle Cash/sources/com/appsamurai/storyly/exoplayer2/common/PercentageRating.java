package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.google.common.base.Objects;

public final class PercentageRating extends Rating {
    public static final Bundleable.Creator<PercentageRating> CREATOR = new a(2);
    private static final int FIELD_PERCENT = 1;
    private static final int TYPE = 1;
    private final float percent;

    public PercentageRating() {
        this.percent = -1.0f;
    }

    /* access modifiers changed from: private */
    public static PercentageRating fromBundle(Bundle bundle) {
        boolean z2 = false;
        if (bundle.getInt(keyForField(0), -1) == 1) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        float f2 = bundle.getFloat(keyForField(1), -1.0f);
        return f2 == -1.0f ? new PercentageRating() : new PercentageRating(f2);
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof PercentageRating) && this.percent == ((PercentageRating) obj).percent;
    }

    public float getPercent() {
        return this.percent;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.percent));
    }

    public boolean isRated() {
        return this.percent != -1.0f;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(0), 1);
        bundle.putFloat(keyForField(1), this.percent);
        return bundle;
    }

    public PercentageRating(@FloatRange(from = 0.0d, to = 100.0d) float f2) {
        Assertions.checkArgument(f2 >= 0.0f && f2 <= 100.0f, "percent must be in the range of [0, 100]");
        this.percent = f2;
    }
}
