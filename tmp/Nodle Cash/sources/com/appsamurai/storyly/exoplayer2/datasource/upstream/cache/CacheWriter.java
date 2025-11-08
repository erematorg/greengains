package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import java.io.IOException;
import java.io.InterruptedIOException;

public final class CacheWriter {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;
    private long bytesCached;
    private final Cache cache;
    private final String cacheKey;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private long endPosition;
    private volatile boolean isCanceled;
    private long nextPosition;
    @Nullable
    private final ProgressListener progressListener;
    private final byte[] temporaryBuffer;

    public interface ProgressListener {
        void onProgress(long j2, long j3, long j4);
    }

    public CacheWriter(CacheDataSource cacheDataSource, DataSpec dataSpec2, @Nullable byte[] bArr, @Nullable ProgressListener progressListener2) {
        this.dataSource = cacheDataSource;
        this.cache = cacheDataSource.getCache();
        this.dataSpec = dataSpec2;
        this.temporaryBuffer = bArr == null ? new byte[131072] : bArr;
        this.progressListener = progressListener2;
        this.cacheKey = cacheDataSource.getCacheKeyFactory().buildCacheKey(dataSpec2);
        this.nextPosition = dataSpec2.position;
    }

    private long getLength() {
        long j2 = this.endPosition;
        if (j2 == -1) {
            return -1;
        }
        return j2 - this.dataSpec.position;
    }

    private void onNewBytesCached(long j2) {
        this.bytesCached += j2;
        ProgressListener progressListener2 = this.progressListener;
        if (progressListener2 != null) {
            progressListener2.onProgress(getLength(), this.bytesCached, j2);
        }
    }

    private void onRequestEndPosition(long j2) {
        if (this.endPosition != j2) {
            this.endPosition = j2;
            ProgressListener progressListener2 = this.progressListener;
            if (progressListener2 != null) {
                progressListener2.onProgress(getLength(), this.bytesCached, 0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f A[Catch:{ IOException -> 0x0068 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0085 A[Catch:{ IOException -> 0x0068 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long readBlockToCache(long r7, long r9) throws java.io.IOException {
        /*
            r6 = this;
            long r0 = r7 + r9
            long r2 = r6.endPosition
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            r2 = 0
            r3 = -1
            if (r0 == 0) goto L_0x0013
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r0 = r2
            goto L_0x0014
        L_0x0013:
            r0 = r1
        L_0x0014:
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0036
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r5 = r6.dataSpec
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r5 = r5.buildUpon()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r5 = r5.setPosition(r7)
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r9 = r5.setLength(r9)
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r9 = r9.build()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r10 = r6.dataSource     // Catch:{ IOException -> 0x0031 }
            long r9 = r10.open(r9)     // Catch:{ IOException -> 0x0031 }
            goto L_0x0038
        L_0x0031:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r9 = r6.dataSource
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil.closeQuietly(r9)
        L_0x0036:
            r1 = r2
            r9 = r3
        L_0x0038:
            if (r1 != 0) goto L_0x005d
            r6.throwIfCanceled()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r9 = r6.dataSpec
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r9 = r9.buildUpon()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r9 = r9.setPosition(r7)
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec$Builder r9 = r9.setLength(r3)
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r9 = r9.build()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r10 = r6.dataSource     // Catch:{ IOException -> 0x0056 }
            long r9 = r10.open(r9)     // Catch:{ IOException -> 0x0056 }
            goto L_0x005d
        L_0x0056:
            r7 = move-exception
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r6 = r6.dataSource
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil.closeQuietly(r6)
            throw r7
        L_0x005d:
            if (r0 == 0) goto L_0x006a
            int r1 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x006a
            long r9 = r9 + r7
            r6.onRequestEndPosition(r9)     // Catch:{ IOException -> 0x0068 }
            goto L_0x006a
        L_0x0068:
            r7 = move-exception
            goto L_0x008b
        L_0x006a:
            r9 = r2
            r10 = r9
        L_0x006c:
            r1 = -1
            if (r9 == r1) goto L_0x0083
            r6.throwIfCanceled()     // Catch:{ IOException -> 0x0068 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r9 = r6.dataSource     // Catch:{ IOException -> 0x0068 }
            byte[] r3 = r6.temporaryBuffer     // Catch:{ IOException -> 0x0068 }
            int r4 = r3.length     // Catch:{ IOException -> 0x0068 }
            int r9 = r9.read(r3, r2, r4)     // Catch:{ IOException -> 0x0068 }
            if (r9 == r1) goto L_0x006c
            long r3 = (long) r9     // Catch:{ IOException -> 0x0068 }
            r6.onNewBytesCached(r3)     // Catch:{ IOException -> 0x0068 }
            int r10 = r10 + r9
            goto L_0x006c
        L_0x0083:
            if (r0 == 0) goto L_0x0091
            long r0 = (long) r10     // Catch:{ IOException -> 0x0068 }
            long r7 = r7 + r0
            r6.onRequestEndPosition(r7)     // Catch:{ IOException -> 0x0068 }
            goto L_0x0091
        L_0x008b:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r6 = r6.dataSource
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil.closeQuietly(r6)
            throw r7
        L_0x0091:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r6 = r6.dataSource
            r6.close()
            long r6 = (long) r10
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheWriter.readBlockToCache(long, long):long");
    }

    private void throwIfCanceled() throws InterruptedIOException {
        if (this.isCanceled) {
            throw new InterruptedIOException();
        }
    }

    @WorkerThread
    public void cache() throws IOException {
        throwIfCanceled();
        Cache cache2 = this.cache;
        String str = this.cacheKey;
        DataSpec dataSpec2 = this.dataSpec;
        this.bytesCached = cache2.getCachedBytes(str, dataSpec2.position, dataSpec2.length);
        DataSpec dataSpec3 = this.dataSpec;
        long j2 = dataSpec3.length;
        if (j2 != -1) {
            this.endPosition = dataSpec3.position + j2;
        } else {
            long contentLength = ContentMetadata.getContentLength(this.cache.getContentMetadata(this.cacheKey));
            if (contentLength == -1) {
                contentLength = -1;
            }
            this.endPosition = contentLength;
        }
        ProgressListener progressListener2 = this.progressListener;
        if (progressListener2 != null) {
            progressListener2.onProgress(getLength(), this.bytesCached, 0);
        }
        while (true) {
            long j3 = this.endPosition;
            if (j3 == -1 || this.nextPosition < j3) {
                throwIfCanceled();
                long j4 = this.endPosition;
                long cachedLength = this.cache.getCachedLength(this.cacheKey, this.nextPosition, j4 == -1 ? Long.MAX_VALUE : j4 - this.nextPosition);
                if (cachedLength > 0) {
                    this.nextPosition += cachedLength;
                } else {
                    long j5 = -cachedLength;
                    if (j5 == Long.MAX_VALUE) {
                        j5 = -1;
                    }
                    long j6 = this.nextPosition;
                    this.nextPosition = j6 + readBlockToCache(j6, j5);
                }
            } else {
                return;
            }
        }
    }

    public void cancel() {
        this.isCanceled = true;
    }
}
