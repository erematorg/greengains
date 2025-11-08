package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class SeekParameters {
    public static final SeekParameters CLOSEST_SYNC = new SeekParameters(Long.MAX_VALUE, Long.MAX_VALUE);
    public static final SeekParameters DEFAULT;
    public static final SeekParameters EXACT;
    public static final SeekParameters NEXT_SYNC = new SeekParameters(0, Long.MAX_VALUE);
    public static final SeekParameters PREVIOUS_SYNC = new SeekParameters(Long.MAX_VALUE, 0);
    public final long toleranceAfterUs;
    public final long toleranceBeforeUs;

    static {
        SeekParameters seekParameters = new SeekParameters(0, 0);
        EXACT = seekParameters;
        DEFAULT = seekParameters;
    }

    public SeekParameters(long j2, long j3) {
        boolean z2 = false;
        Assertions.checkArgument(j2 >= 0);
        Assertions.checkArgument(j3 >= 0 ? true : z2);
        this.toleranceBeforeUs = j2;
        this.toleranceAfterUs = j3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SeekParameters.class != obj.getClass()) {
            return false;
        }
        SeekParameters seekParameters = (SeekParameters) obj;
        return this.toleranceBeforeUs == seekParameters.toleranceBeforeUs && this.toleranceAfterUs == seekParameters.toleranceAfterUs;
    }

    public int hashCode() {
        return (((int) this.toleranceBeforeUs) * 31) + ((int) this.toleranceAfterUs);
    }

    public long resolveSeekPositionUs(long j2, long j3, long j4) {
        long j5 = this.toleranceBeforeUs;
        if (j5 == 0 && this.toleranceAfterUs == 0) {
            return j2;
        }
        long subtractWithOverflowDefault = Util.subtractWithOverflowDefault(j2, j5, Long.MIN_VALUE);
        long addWithOverflowDefault = Util.addWithOverflowDefault(j2, this.toleranceAfterUs, Long.MAX_VALUE);
        boolean z2 = false;
        boolean z3 = subtractWithOverflowDefault <= j3 && j3 <= addWithOverflowDefault;
        if (subtractWithOverflowDefault <= j4 && j4 <= addWithOverflowDefault) {
            z2 = true;
        }
        return (!z3 || !z2) ? z3 ? j3 : z2 ? j4 : subtractWithOverflowDefault : Math.abs(j3 - j2) <= Math.abs(j4 - j2) ? j3 : j4;
    }
}
