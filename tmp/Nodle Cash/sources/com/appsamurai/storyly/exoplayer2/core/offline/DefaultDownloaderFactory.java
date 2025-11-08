package com.appsamurai.storyly.exoplayer2.core.offline;

import android.util.SparseArray;
import androidx.arch.core.executor.a;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;

public class DefaultDownloaderFactory implements DownloaderFactory {
    private static final SparseArray<Constructor<? extends Downloader>> CONSTRUCTORS = createDownloaderConstructors();
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final Executor executor;

    @Deprecated
    public DefaultDownloaderFactory(CacheDataSource.Factory factory) {
        this(factory, new a(2));
    }

    private static SparseArray<Constructor<? extends Downloader>> createDownloaderConstructors() {
        SparseArray<Constructor<? extends Downloader>> sparseArray = new SparseArray<>();
        try {
            sparseArray.put(0, getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.dash.offline.DashDownloader")));
        } catch (ClassNotFoundException unused) {
        }
        try {
            sparseArray.put(2, getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.hls.offline.HlsDownloader")));
        } catch (ClassNotFoundException unused2) {
        }
        try {
            sparseArray.put(1, getDownloaderConstructor(Class.forName("com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloader")));
        } catch (ClassNotFoundException unused3) {
        }
        return sparseArray;
    }

    private static Constructor<? extends Downloader> getDownloaderConstructor(Class<?> cls) {
        try {
            return cls.asSubclass(Downloader.class).getConstructor(new Class[]{MediaItem.class, CacheDataSource.Factory.class, Executor.class});
        } catch (NoSuchMethodException e3) {
            throw new IllegalStateException("Downloader constructor missing", e3);
        }
    }

    public Downloader createDownloader(DownloadRequest downloadRequest) {
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(downloadRequest.uri, downloadRequest.mimeType);
        if (inferContentTypeForUriAndMimeType == 0 || inferContentTypeForUriAndMimeType == 1 || inferContentTypeForUriAndMimeType == 2) {
            return createDownloader(downloadRequest, inferContentTypeForUriAndMimeType);
        }
        if (inferContentTypeForUriAndMimeType == 4) {
            return new ProgressiveDownloader(new MediaItem.Builder().setUri(downloadRequest.uri).setCustomCacheKey(downloadRequest.customCacheKey).build(), this.cacheDataSourceFactory, this.executor);
        }
        throw new IllegalArgumentException(A.a.k("Unsupported type: ", inferContentTypeForUriAndMimeType));
    }

    public DefaultDownloaderFactory(CacheDataSource.Factory factory, Executor executor2) {
        this.cacheDataSourceFactory = (CacheDataSource.Factory) Assertions.checkNotNull(factory);
        this.executor = (Executor) Assertions.checkNotNull(executor2);
    }

    private Downloader createDownloader(DownloadRequest downloadRequest, int i3) {
        Constructor constructor = CONSTRUCTORS.get(i3);
        if (constructor != null) {
            try {
                return (Downloader) constructor.newInstance(new Object[]{new MediaItem.Builder().setUri(downloadRequest.uri).setStreamKeys(downloadRequest.streamKeys).setCustomCacheKey(downloadRequest.customCacheKey).build(), this.cacheDataSourceFactory, this.executor});
            } catch (Exception unused) {
                throw new IllegalStateException(A.a.k("Failed to instantiate downloader for content type ", i3));
            }
        } else {
            throw new IllegalStateException(A.a.k("Module missing for content type ", i3));
        }
    }
}
