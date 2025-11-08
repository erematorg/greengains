package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class LoadEventInfo {
    private static final AtomicLong idSource = new AtomicLong();
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final long elapsedRealtimeMs;
    public final long loadDurationMs;
    public final long loadTaskId;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uri;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LoadEventInfo(long r13, com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r15, long r16) {
        /*
            r12 = this;
            r3 = r15
            android.net.Uri r4 = r3.uri
            java.util.Map r5 = java.util.Collections.emptyMap()
            r8 = 0
            r10 = 0
            r0 = r12
            r1 = r13
            r6 = r16
            r0.<init>(r1, r3, r4, r5, r6, r8, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo.<init>(long, com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec, long):void");
    }

    public static long getNewId() {
        return idSource.getAndIncrement();
    }

    public LoadEventInfo(long j2, DataSpec dataSpec2, Uri uri2, Map<String, List<String>> map, long j3, long j4, long j5) {
        this.loadTaskId = j2;
        this.dataSpec = dataSpec2;
        this.uri = uri2;
        this.responseHeaders = map;
        this.elapsedRealtimeMs = j3;
        this.loadDurationMs = j4;
        this.bytesLoaded = j5;
    }
}
