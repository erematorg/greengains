package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

final class CacheFileMetadata {
    public final long lastTouchTimestamp;
    public final long length;

    public CacheFileMetadata(long j2, long j3) {
        this.length = j2;
        this.lastTouchTimestamp = j3;
    }
}
