package com.appsamurai.storyly.exoplayer2.common.util;

import androidx.annotation.GuardedBy;
import com.appsamurai.storyly.exoplayer2.common.C;

public final class TimestampAdjuster {
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;
    @GuardedBy("this")
    private long firstSampleTimestampUs;
    @GuardedBy("this")
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    @GuardedBy("this")
    private long timestampOffsetUs;

    public TimestampAdjuster(long j2) {
        reset(j2);
    }

    public static long ptsToUs(long j2) {
        return (j2 * 1000000) / 90000;
    }

    public static long usToNonWrappedPts(long j2) {
        return (j2 * 90000) / 1000000;
    }

    public static long usToWrappedPts(long j2) {
        return usToNonWrappedPts(j2) % MAX_PTS_PLUS_ONE;
    }

    public synchronized long adjustSampleTimestamp(long j2) {
        if (j2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        try {
            if (this.timestampOffsetUs == C.TIME_UNSET) {
                long j3 = this.firstSampleTimestampUs;
                if (j3 == MODE_SHARED) {
                    j3 = ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue();
                }
                this.timestampOffsetUs = j3 - j2;
                notifyAll();
            }
            this.lastUnadjustedTimestampUs = j2;
            return j2 + this.timestampOffsetUs;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long adjustTsTimestamp(long j2) {
        if (j2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        try {
            long j3 = this.lastUnadjustedTimestampUs;
            if (j3 != C.TIME_UNSET) {
                long usToNonWrappedPts = usToNonWrappedPts(j3);
                long j4 = (4294967296L + usToNonWrappedPts) / MAX_PTS_PLUS_ONE;
                long j5 = ((j4 - 1) * MAX_PTS_PLUS_ONE) + j2;
                long j6 = (j4 * MAX_PTS_PLUS_ONE) + j2;
                j2 = Math.abs(j5 - usToNonWrappedPts) < Math.abs(j6 - usToNonWrappedPts) ? j5 : j6;
            }
            return adjustSampleTimestamp(ptsToUs(j2));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j2;
        j2 = this.firstSampleTimestampUs;
        if (j2 == Long.MAX_VALUE || j2 == MODE_SHARED) {
            j2 = C.TIME_UNSET;
        }
        return j2;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long j2;
        try {
            j2 = this.lastUnadjustedTimestampUs;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return j2 != C.TIME_UNSET ? j2 + this.timestampOffsetUs : getFirstSampleTimestampUs();
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized void reset(long j2) {
        this.firstSampleTimestampUs = j2;
        this.timestampOffsetUs = j2 == Long.MAX_VALUE ? 0 : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = C.TIME_UNSET;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void sharedInitializeOrWait(boolean r5, long r6) throws java.lang.InterruptedException {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.firstSampleTimestampUs     // Catch:{ all -> 0x002b }
            r2 = 9223372036854775806(0x7ffffffffffffffe, double:NaN)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x000e
            r0 = 1
            goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x002b }
            long r0 = r4.timestampOffsetUs     // Catch:{ all -> 0x002b }
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x001f
            monitor-exit(r4)
            return
        L_0x001f:
            if (r5 == 0) goto L_0x002d
            java.lang.ThreadLocal<java.lang.Long> r5 = r4.nextSampleTimestampUs     // Catch:{ all -> 0x002b }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x002b }
            r5.set(r6)     // Catch:{ all -> 0x002b }
            goto L_0x0037
        L_0x002b:
            r5 = move-exception
            goto L_0x0039
        L_0x002d:
            long r5 = r4.timestampOffsetUs     // Catch:{ all -> 0x002b }
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x0037
            r4.wait()     // Catch:{ all -> 0x002b }
            goto L_0x002d
        L_0x0037:
            monitor-exit(r4)
            return
        L_0x0039:
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster.sharedInitializeOrWait(boolean, long):void");
    }
}
