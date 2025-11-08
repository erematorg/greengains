package com.appsamurai.storyly.exoplayer2.hls;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import java.io.IOException;

final class UnexpectedSampleTimestampException extends IOException {
    public final long lastAcceptedSampleTimeUs;
    public final MediaChunk mediaChunk;
    public final long rejectedSampleTimeUs;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnexpectedSampleTimestampException(com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk r5, long r6, long r8) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected sample timestamp: "
            r0.<init>(r1)
            long r1 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r8)
            r0.append(r1)
            java.lang.String r1 = " in chunk ["
            r0.append(r1)
            long r1 = r5.startTimeUs
            r0.append(r1)
            java.lang.String r1 = ", "
            r0.append(r1)
            long r1 = r5.endTimeUs
            java.lang.String r3 = "]"
            java.lang.String r0 = android.support.v4.media.session.a.k(r1, r3, r0)
            r4.<init>(r0)
            r4.mediaChunk = r5
            r4.lastAcceptedSampleTimeUs = r6
            r4.rejectedSampleTimeUs = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.hls.UnexpectedSampleTimestampException.<init>(com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk, long, long):void");
    }
}
