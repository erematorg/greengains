package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

public class DiskLruCacheFactory implements DiskCache.Factory {
    private final CacheDirectoryGetter cacheDirectoryGetter;
    private final long diskCacheSize;

    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    public DiskLruCacheFactory(final String str, long j2) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File getCacheDirectory() {
                return new File(str);
            }
        }, j2);
    }

    public DiskCache build() {
        File cacheDirectory = this.cacheDirectoryGetter.getCacheDirectory();
        if (cacheDirectory == null) {
            return null;
        }
        if (cacheDirectory.isDirectory() || cacheDirectory.mkdirs()) {
            return DiskLruCacheWrapper.create(cacheDirectory, this.diskCacheSize);
        }
        return null;
    }

    public DiskLruCacheFactory(final String str, final String str2, long j2) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File getCacheDirectory() {
                return new File(str, str2);
            }
        }, j2);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter2, long j2) {
        this.diskCacheSize = j2;
        this.cacheDirectoryGetter = cacheDirectoryGetter2;
    }
}
