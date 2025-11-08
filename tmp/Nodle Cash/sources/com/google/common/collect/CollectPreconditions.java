package com.google.common.collect;

import androidx.camera.view.f;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class CollectPreconditions {
    public static void checkEntryNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException(f.a(obj2, "null key in entry: null="));
        } else if (obj2 == null) {
            throw new NullPointerException("null value in entry: " + obj + "=null");
        }
    }

    @CanIgnoreReturnValue
    public static int checkNonnegative(int i3, String str) {
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException(a.b(i3, str, " cannot be negative but was: "));
    }

    public static void checkPositive(int i3, String str) {
        if (i3 <= 0) {
            throw new IllegalArgumentException(a.b(i3, str, " must be positive but was: "));
        }
    }

    public static void checkRemove(boolean z2) {
        Preconditions.checkState(z2, "no calls to next() since the last call to remove()");
    }

    @CanIgnoreReturnValue
    public static long checkNonnegative(long j2, String str) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + j2);
    }
}
