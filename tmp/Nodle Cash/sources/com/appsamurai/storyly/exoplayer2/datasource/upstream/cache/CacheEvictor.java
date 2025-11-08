package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;

public interface CacheEvictor extends Cache.Listener {
    void onCacheInitialized();

    void onStartFile(Cache cache, String str, long j2, long j3);

    boolean requiresCacheSpanTouches();
}
