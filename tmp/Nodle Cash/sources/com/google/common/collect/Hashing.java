package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class Hashing {

    /* renamed from: C1  reason: collision with root package name */
    private static final long f6873C1 = -862048943;
    private static final long C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    private Hashing() {
    }

    public static int closedTableSize(int i3, double d2) {
        int max = Math.max(i3, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d2 * ((double) highestOneBit)))) {
            return highestOneBit;
        }
        int i4 = highestOneBit << 1;
        if (i4 > 0) {
            return i4;
        }
        return 1073741824;
    }

    public static boolean needsResizing(int i3, int i4, double d2) {
        return ((double) i3) > d2 * ((double) i4) && i4 < 1073741824;
    }

    public static int smear(int i3) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i3) * f6873C1), 15)) * C2);
    }

    public static int smearedHash(@CheckForNull Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }
}
