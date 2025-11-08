package com.bumptech.glide.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z2) {
        checkArgument(z2, "");
    }

    @NonNull
    public static String checkNotEmpty(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T t2) {
        return checkNotNull(t2, "Argument must not be null");
    }

    public static void checkArgument(boolean z2, @NonNull String str) {
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T t2, @NonNull String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str);
    }

    @NonNull
    public static <T extends Collection<Y>, Y> T checkNotEmpty(@NonNull T t2) {
        if (!t2.isEmpty()) {
            return t2;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }
}
