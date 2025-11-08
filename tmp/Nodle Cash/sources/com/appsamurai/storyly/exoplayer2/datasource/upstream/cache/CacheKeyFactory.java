package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;

public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new a(2);

    /* access modifiers changed from: private */
    static /* synthetic */ String lambda$static$0(DataSpec dataSpec) {
        String str = dataSpec.key;
        return str != null ? str : dataSpec.uri.toString();
    }

    String buildCacheKey(DataSpec dataSpec);
}
