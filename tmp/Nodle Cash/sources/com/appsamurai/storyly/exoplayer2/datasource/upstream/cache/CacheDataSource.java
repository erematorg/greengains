package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSink;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceException;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.FileDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.PlaceholderDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.PriorityDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TeeDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSink;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CacheDataSource implements DataSource {
    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    private static final int CACHE_NOT_IGNORED = -1;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    @Nullable
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    @Nullable
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    @Nullable
    private DataSource currentDataSource;
    private long currentDataSourceBytesRead;
    @Nullable
    private DataSpec currentDataSpec;
    @Nullable
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    @Nullable
    private final EventListener eventListener;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private long readPosition;
    @Nullable
    private DataSpec requestDataSpec;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheIgnoredReason {
    }

    public interface EventListener {
        void onCacheIgnored(int i3);

        void onCachedBytesRead(long j2, long j3);
    }

    public static final class Factory implements DataSource.Factory {
        private Cache cache;
        private boolean cacheIsReadOnly;
        private CacheKeyFactory cacheKeyFactory = CacheKeyFactory.DEFAULT;
        private DataSource.Factory cacheReadDataSourceFactory = new FileDataSource.Factory();
        @Nullable
        private DataSink.Factory cacheWriteDataSinkFactory;
        @Nullable
        private EventListener eventListener;
        private int flags;
        @Nullable
        private DataSource.Factory upstreamDataSourceFactory;
        private int upstreamPriority;
        @Nullable
        private PriorityTaskManager upstreamPriorityTaskManager;

        private CacheDataSource createDataSourceInternal(@Nullable DataSource dataSource, int i3, int i4) {
            DataSink dataSink;
            Cache cache2 = (Cache) Assertions.checkNotNull(this.cache);
            if (this.cacheIsReadOnly || dataSource == null) {
                dataSink = null;
            } else {
                DataSink.Factory factory = this.cacheWriteDataSinkFactory;
                dataSink = factory != null ? factory.createDataSink() : new CacheDataSink.Factory().setCache(cache2).createDataSink();
            }
            return new CacheDataSource(cache2, dataSource, this.cacheReadDataSourceFactory.createDataSource(), dataSink, this.cacheKeyFactory, i3, this.upstreamPriorityTaskManager, i4, this.eventListener);
        }

        public CacheDataSource createDataSourceForDownloading() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags | 1, -1000);
        }

        public CacheDataSource createDataSourceForRemovingDownload() {
            return createDataSourceInternal((DataSource) null, this.flags | 1, -1000);
        }

        @Nullable
        public Cache getCache() {
            return this.cache;
        }

        public CacheKeyFactory getCacheKeyFactory() {
            return this.cacheKeyFactory;
        }

        @Nullable
        public PriorityTaskManager getUpstreamPriorityTaskManager() {
            return this.upstreamPriorityTaskManager;
        }

        public Factory setCache(Cache cache2) {
            this.cache = cache2;
            return this;
        }

        public Factory setCacheKeyFactory(CacheKeyFactory cacheKeyFactory2) {
            this.cacheKeyFactory = cacheKeyFactory2;
            return this;
        }

        public Factory setCacheReadDataSourceFactory(DataSource.Factory factory) {
            this.cacheReadDataSourceFactory = factory;
            return this;
        }

        public Factory setCacheWriteDataSinkFactory(@Nullable DataSink.Factory factory) {
            this.cacheWriteDataSinkFactory = factory;
            this.cacheIsReadOnly = factory == null;
            return this;
        }

        public Factory setEventListener(@Nullable EventListener eventListener2) {
            this.eventListener = eventListener2;
            return this;
        }

        public Factory setFlags(int i3) {
            this.flags = i3;
            return this;
        }

        public Factory setUpstreamDataSourceFactory(@Nullable DataSource.Factory factory) {
            this.upstreamDataSourceFactory = factory;
            return this;
        }

        public Factory setUpstreamPriority(int i3) {
            this.upstreamPriority = i3;
            return this;
        }

        public Factory setUpstreamPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
            this.upstreamPriorityTaskManager = priorityTaskManager;
            return this;
        }

        public CacheDataSource createDataSource() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags, this.upstreamPriority);
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.currentDataSpec = null;
                this.currentDataSource = null;
                CacheSpan cacheSpan = this.currentHoleSpan;
                if (cacheSpan != null) {
                    this.cache.releaseHoleSpan(cacheSpan);
                    this.currentHoleSpan = null;
                }
            }
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache2, String str, Uri uri) {
        Uri redirectedUri = ContentMetadata.getRedirectedUri(cache2.getContentMetadata(str));
        return redirectedUri != null ? redirectedUri : uri;
    }

    private void handleBeforeThrow(Throwable th) {
        if (isReadingFromCache() || (th instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void notifyBytesRead() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null && this.totalCachedBytesRead > 0) {
            eventListener2.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
            this.totalCachedBytesRead = 0;
        }
    }

    private void notifyCacheIgnored(int i3) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            eventListener2.onCacheIgnored(i3);
        }
    }

    private void openNextSource(DataSpec dataSpec, boolean z2) throws IOException {
        CacheSpan cacheSpan;
        DataSpec dataSpec2;
        DataSource dataSource;
        long j2;
        DataSpec dataSpec3 = dataSpec;
        String str = (String) Util.castNonNull(dataSpec3.key);
        Uri uri = null;
        if (this.currentRequestIgnoresCache) {
            cacheSpan = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpan = this.cache.startReadWrite(str, this.readPosition, this.bytesRemaining);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpan = this.cache.startReadWriteNonBlocking(str, this.readPosition, this.bytesRemaining);
        }
        if (cacheSpan == null) {
            dataSource = this.upstreamDataSource;
            dataSpec2 = dataSpec.buildUpon().setPosition(this.readPosition).setLength(this.bytesRemaining).build();
        } else if (cacheSpan.isCached) {
            Uri fromFile = Uri.fromFile((File) Util.castNonNull(cacheSpan.file));
            long j3 = cacheSpan.position;
            long j4 = this.readPosition - j3;
            long j5 = cacheSpan.length - j4;
            long j6 = this.bytesRemaining;
            if (j6 != -1) {
                j5 = Math.min(j5, j6);
            }
            dataSpec2 = dataSpec.buildUpon().setUri(fromFile).setUriPositionOffset(j3).setPosition(j4).setLength(j5).build();
            dataSource = this.cacheReadDataSource;
        } else {
            if (cacheSpan.isOpenEnded()) {
                j2 = this.bytesRemaining;
            } else {
                j2 = cacheSpan.length;
                long j7 = this.bytesRemaining;
                if (j7 != -1) {
                    j2 = Math.min(j2, j7);
                }
            }
            dataSpec2 = dataSpec.buildUpon().setPosition(this.readPosition).setLength(j2).build();
            dataSource = this.cacheWriteDataSource;
            if (dataSource == null) {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpan);
                cacheSpan = null;
            }
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
        if (z2) {
            Assertions.checkState(isBypassingCache());
            if (dataSource != this.upstreamDataSource) {
                try {
                    closeCurrentSource();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (((CacheSpan) Util.castNonNull(cacheSpan)).isHoleSpan()) {
                        this.cache.releaseHoleSpan(cacheSpan);
                    }
                    throw th2;
                }
            } else {
                return;
            }
        }
        if (cacheSpan != null && cacheSpan.isHoleSpan()) {
            this.currentHoleSpan = cacheSpan;
        }
        this.currentDataSource = dataSource;
        this.currentDataSpec = dataSpec2;
        this.currentDataSourceBytesRead = 0;
        long open = dataSource.open(dataSpec2);
        ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
        if (dataSpec2.length == -1 && open != -1) {
            this.bytesRemaining = open;
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition + open);
        }
        if (isReadingFromUpstream()) {
            Uri uri2 = dataSource.getUri();
            this.actualUri = uri2;
            if (!dataSpec3.uri.equals(uri2)) {
                uri = this.actualUri;
            }
            ContentMetadataMutations.setRedirectedUri(contentMetadataMutations, uri);
        }
        if (isWritingToCache()) {
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    private void setNoBytesRemainingAndMaybeStoreLength(String str) throws IOException {
        this.bytesRemaining = 0;
        if (isWritingToCache()) {
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition);
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (!this.ignoreCacheOnError || !this.seenCacheError) {
            return (!this.ignoreCacheForUnsetLengthRequests || dataSpec.length != -1) ? -1 : 1;
        }
        return 0;
    }

    public void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    public void close() throws IOException {
        this.requestDataSpec = null;
        this.actualUri = null;
        this.readPosition = 0;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public Cache getCache() {
        return this.cache;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return isReadingFromUpstream() ? this.upstreamDataSource.getResponseHeaders() : Collections.emptyMap();
    }

    @Nullable
    public Uri getUri() {
        return this.actualUri;
    }

    public long open(DataSpec dataSpec) throws IOException {
        try {
            String buildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
            DataSpec build = dataSpec.buildUpon().setKey(buildCacheKey).build();
            this.requestDataSpec = build;
            this.actualUri = getRedirectedUriOrDefault(this.cache, buildCacheKey, build.uri);
            this.readPosition = dataSpec.position;
            int shouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z2 = shouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z2;
            if (z2) {
                notifyCacheIgnored(shouldIgnoreCacheForRequest);
            }
            if (this.currentRequestIgnoresCache) {
                this.bytesRemaining = -1;
            } else {
                long contentLength = ContentMetadata.getContentLength(this.cache.getContentMetadata(buildCacheKey));
                this.bytesRemaining = contentLength;
                if (contentLength != -1) {
                    long j2 = contentLength - dataSpec.position;
                    this.bytesRemaining = j2;
                    if (j2 < 0) {
                        throw new DataSourceException(2008);
                    }
                }
            }
            long j3 = dataSpec.length;
            if (j3 != -1) {
                long j4 = this.bytesRemaining;
                if (j4 != -1) {
                    j3 = Math.min(j4, j3);
                }
                this.bytesRemaining = j3;
            }
            long j5 = this.bytesRemaining;
            if (j5 > 0 || j5 == -1) {
                openNextSource(build, false);
            }
            long j6 = dataSpec.length;
            return j6 != -1 ? j6 : this.bytesRemaining;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        int i5 = i4;
        if (i5 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        DataSpec dataSpec = (DataSpec) Assertions.checkNotNull(this.requestDataSpec);
        DataSpec dataSpec2 = (DataSpec) Assertions.checkNotNull(this.currentDataSpec);
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(dataSpec, true);
            }
            int read = ((DataSource) Assertions.checkNotNull(this.currentDataSource)).read(bArr, i3, i5);
            if (read != -1) {
                if (isReadingFromCache()) {
                    this.totalCachedBytesRead += (long) read;
                }
                long j2 = (long) read;
                this.readPosition += j2;
                this.currentDataSourceBytesRead += j2;
                long j3 = this.bytesRemaining;
                if (j3 != -1) {
                    this.bytesRemaining = j3 - j2;
                }
            } else {
                if (isReadingFromUpstream()) {
                    long j4 = dataSpec2.length;
                    if (j4 == -1 || this.currentDataSourceBytesRead < j4) {
                        setNoBytesRemainingAndMaybeStoreLength((String) Util.castNonNull(dataSpec.key));
                    }
                }
                long j5 = this.bytesRemaining;
                if (j5 <= 0) {
                    if (j5 == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(dataSpec, false);
                return read(bArr, i3, i4);
            }
            return read;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public CacheDataSource(Cache cache2, @Nullable DataSource dataSource) {
        this(cache2, dataSource, 0);
    }

    public CacheDataSource(Cache cache2, @Nullable DataSource dataSource, int i3) {
        this(cache2, dataSource, new FileDataSource(), new CacheDataSink(cache2, CacheDataSink.DEFAULT_FRAGMENT_SIZE), i3, (EventListener) null);
    }

    public CacheDataSource(Cache cache2, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, int i3, @Nullable EventListener eventListener2) {
        this(cache2, dataSource, dataSource2, dataSink, i3, eventListener2, (CacheKeyFactory) null);
    }

    public CacheDataSource(Cache cache2, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, int i3, @Nullable EventListener eventListener2, @Nullable CacheKeyFactory cacheKeyFactory2) {
        this(cache2, dataSource, dataSource2, dataSink, cacheKeyFactory2, i3, (PriorityTaskManager) null, 0, eventListener2);
    }

    private CacheDataSource(Cache cache2, @Nullable DataSource dataSource, DataSource dataSource2, @Nullable DataSink dataSink, @Nullable CacheKeyFactory cacheKeyFactory2, int i3, @Nullable PriorityTaskManager priorityTaskManager, int i4, @Nullable EventListener eventListener2) {
        this.cache = cache2;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory2 == null ? CacheKeyFactory.DEFAULT : cacheKeyFactory2;
        boolean z2 = false;
        this.blockOnCache = (i3 & 1) != 0;
        this.ignoreCacheOnError = (i3 & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i3 & 4) != 0 ? true : z2;
        TeeDataSource teeDataSource = null;
        if (dataSource != null) {
            dataSource = priorityTaskManager != null ? new PriorityDataSource(dataSource, priorityTaskManager, i4) : dataSource;
            this.upstreamDataSource = dataSource;
            this.cacheWriteDataSource = dataSink != null ? new TeeDataSource(dataSource, dataSink) : teeDataSource;
        } else {
            this.upstreamDataSource = PlaceholderDataSource.INSTANCE;
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener2;
    }
}
