package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class NullnessCasts {
    private NullnessCasts() {
    }

    @ParametricNullness
    public static <T> T uncheckedCastNullableTToT(@CheckForNull T t2) {
        return t2;
    }

    @ParametricNullness
    public static <T> T uncheckedNull() {
        return null;
    }
}
