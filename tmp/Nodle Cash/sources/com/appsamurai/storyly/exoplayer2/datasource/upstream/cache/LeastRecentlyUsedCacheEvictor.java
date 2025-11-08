package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import androidx.compose.foundation.lazy.layout.a;
import java.util.TreeSet;

public final class LeastRecentlyUsedCacheEvictor implements CacheEvictor {
    private long currentSize;
    private final TreeSet<CacheSpan> leastRecentlyUsed = new TreeSet<>(new a(3));
    private final long maxBytes;

    public LeastRecentlyUsedCacheEvictor(long j2) {
        this.maxBytes = j2;
    }

    /* access modifiers changed from: private */
    public static int compare(CacheSpan cacheSpan, CacheSpan cacheSpan2) {
        long j2 = cacheSpan.lastTouchTimestamp;
        long j3 = cacheSpan2.lastTouchTimestamp;
        return j2 - j3 == 0 ? cacheSpan.compareTo(cacheSpan2) : j2 < j3 ? -1 : 1;
    }

    private void evictCache(Cache cache, long j2) {
        while (this.currentSize + j2 > this.maxBytes && !this.leastRecentlyUsed.isEmpty()) {
            cache.removeSpan(this.leastRecentlyUsed.first());
        }
    }

    public void onCacheInitialized() {
    }

    public void onSpanAdded(Cache cache, CacheSpan cacheSpan) {
        this.leastRecentlyUsed.add(cacheSpan);
        this.currentSize += cacheSpan.length;
        evictCache(cache, 0);
    }

    public void onSpanRemoved(Cache cache, CacheSpan cacheSpan) {
        this.leastRecentlyUsed.remove(cacheSpan);
        this.currentSize -= cacheSpan.length;
    }

    public void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
        onSpanRemoved(cache, cacheSpan);
        onSpanAdded(cache, cacheSpan2);
    }

    public void onStartFile(Cache cache, String str, long j2, long j3) {
        if (j3 != -1) {
            evictCache(cache, j3);
        }
    }

    public boolean requiresCacheSpanTouches() {
        return true;
    }
}
