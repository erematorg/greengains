package com.appsamurai.storyly.exoplayer2.core.offline;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.offline.Downloader;
import com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest;
import com.appsamurai.storyly.exoplayer2.core.upstream.ParsingLoadable;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private static final long MAX_MERGED_SEGMENT_START_TIME_DIFF_US = 20000000;
    private final ArrayList<RunnableFutureTask<?, ?>> activeRunnables = new ArrayList<>();
    private final Cache cache;
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final CacheKeyFactory cacheKeyFactory;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final DataSpec manifestDataSpec;
    /* access modifiers changed from: private */
    public final ParsingLoadable.Parser<M> manifestParser;
    @Nullable
    private final PriorityTaskManager priorityTaskManager;
    private final ArrayList<StreamKey> streamKeys;

    public static final class ProgressNotifier implements CacheWriter.ProgressListener {
        private long bytesDownloaded;
        private final long contentLength;
        private final Downloader.ProgressListener progressListener;
        private int segmentsDownloaded;
        private final int totalSegments;

        public ProgressNotifier(Downloader.ProgressListener progressListener2, long j2, int i3, long j3, int i4) {
            this.progressListener = progressListener2;
            this.contentLength = j2;
            this.totalSegments = i3;
            this.bytesDownloaded = j3;
            this.segmentsDownloaded = i4;
        }

        private float getPercentDownloaded() {
            float f2;
            float f3;
            long j2 = this.contentLength;
            if (j2 == -1 || j2 == 0) {
                int i3 = this.totalSegments;
                if (i3 == 0) {
                    return -1.0f;
                }
                f2 = ((float) this.segmentsDownloaded) * 100.0f;
                f3 = (float) i3;
            } else {
                f2 = ((float) this.bytesDownloaded) * 100.0f;
                f3 = (float) j2;
            }
            return f2 / f3;
        }

        public void onProgress(long j2, long j3, long j4) {
            long j5 = this.bytesDownloaded + j4;
            this.bytesDownloaded = j5;
            this.progressListener.onProgress(this.contentLength, j5, getPercentDownloaded());
        }

        public void onSegmentDownloaded() {
            this.segmentsDownloaded++;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }
    }

    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j2, DataSpec dataSpec2) {
            this.startTimeUs = j2;
            this.dataSpec = dataSpec2;
        }

        public int compareTo(Segment segment) {
            return Util.compareLong(this.startTimeUs, segment.startTimeUs);
        }
    }

    public static final class SegmentDownloadRunnable extends RunnableFutureTask<Void, IOException> {
        private final CacheWriter cacheWriter;
        public final CacheDataSource dataSource;
        @Nullable
        private final ProgressNotifier progressNotifier;
        public final Segment segment;
        public final byte[] temporaryBuffer;

        public SegmentDownloadRunnable(Segment segment2, CacheDataSource cacheDataSource, @Nullable ProgressNotifier progressNotifier2, byte[] bArr) {
            this.segment = segment2;
            this.dataSource = cacheDataSource;
            this.progressNotifier = progressNotifier2;
            this.temporaryBuffer = bArr;
            this.cacheWriter = new CacheWriter(cacheDataSource, segment2.dataSpec, bArr, progressNotifier2);
        }

        public void cancelWork() {
            this.cacheWriter.cancel();
        }

        public Void doWork() throws IOException {
            this.cacheWriter.cache();
            ProgressNotifier progressNotifier2 = this.progressNotifier;
            if (progressNotifier2 == null) {
                return null;
            }
            progressNotifier2.onSegmentDownloaded();
            return null;
        }
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> parser, CacheDataSource.Factory factory, Executor executor2) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        this.manifestDataSpec = getCompressibleDataSpec(mediaItem.localConfiguration.uri);
        this.manifestParser = parser;
        this.streamKeys = new ArrayList<>(mediaItem.localConfiguration.streamKeys);
        this.cacheDataSourceFactory = factory;
        this.executor = executor2;
        this.cache = (Cache) Assertions.checkNotNull(factory.getCache());
        this.cacheKeyFactory = factory.getCacheKeyFactory();
        this.priorityTaskManager = factory.getUpstreamPriorityTaskManager();
    }

    private <T> void addActiveRunnable(RunnableFutureTask<T, ?> runnableFutureTask) throws InterruptedException {
        synchronized (this.activeRunnables) {
            try {
                if (!this.isCanceled) {
                    this.activeRunnables.add(runnableFutureTask);
                } else {
                    throw new InterruptedException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean canMergeSegments(DataSpec dataSpec, DataSpec dataSpec2) {
        if (dataSpec.uri.equals(dataSpec2.uri)) {
            long j2 = dataSpec.length;
            return j2 != -1 && dataSpec.position + j2 == dataSpec2.position && Util.areEqual(dataSpec.key, dataSpec2.key) && dataSpec.flags == dataSpec2.flags && dataSpec.httpMethod == dataSpec2.httpMethod && dataSpec.httpRequestHeaders.equals(dataSpec2.httpRequestHeaders);
        }
    }

    public static DataSpec getCompressibleDataSpec(Uri uri) {
        return new DataSpec.Builder().setUri(uri).setFlags(1).build();
    }

    private static void mergeSegments(List<Segment> list, CacheKeyFactory cacheKeyFactory2) {
        HashMap hashMap = new HashMap();
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            Segment segment = list.get(i4);
            String buildCacheKey = cacheKeyFactory2.buildCacheKey(segment.dataSpec);
            Integer num = (Integer) hashMap.get(buildCacheKey);
            Segment segment2 = num == null ? null : list.get(num.intValue());
            if (segment2 == null || segment.startTimeUs > segment2.startTimeUs + MAX_MERGED_SEGMENT_START_TIME_DIFF_US || !canMergeSegments(segment2.dataSpec, segment.dataSpec)) {
                hashMap.put(buildCacheKey, Integer.valueOf(i3));
                list.set(i3, segment);
                i3++;
            } else {
                long j2 = segment.dataSpec.length;
                long j3 = -1;
                if (j2 != -1) {
                    j3 = segment2.dataSpec.length + j2;
                }
                list.set(((Integer) Assertions.checkNotNull(num)).intValue(), new Segment(segment2.startTimeUs, segment2.dataSpec.subrange(0, j3)));
            }
        }
        Util.removeRange(list, i3, list.size());
    }

    private void removeActiveRunnable(RunnableFutureTask<?, ?> runnableFutureTask) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(runnableFutureTask);
        }
    }

    public void cancel() {
        synchronized (this.activeRunnables) {
            try {
                this.isCanceled = true;
                for (int i3 = 0; i3 < this.activeRunnables.size(); i3++) {
                    this.activeRunnables.get(i3).cancel(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:89:0x01ad A[LOOP:5: B:87:0x01a5->B:89:0x01ad, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c6 A[LOOP:6: B:91:0x01c4->B:92:0x01c6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void download(@androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.core.offline.Downloader.ProgressListener r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r25 = this;
            r1 = r25
            java.util.ArrayDeque r2 = new java.util.ArrayDeque
            r2.<init>()
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager r0 = r1.priorityTaskManager
            r4 = -1000(0xfffffffffffffc18, float:NaN)
            if (r0 == 0) goto L_0x0015
            r0.add(r4)
        L_0x0015:
            r5 = 1
            r6 = 0
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource$Factory r0 = r1.cacheDataSourceFactory     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r0 = r0.createDataSourceForDownloading()     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r7 = r1.manifestDataSpec     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest r7 = r1.getManifest(r0, r7, r6)     // Catch:{ all -> 0x0034 }
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.offline.StreamKey> r8 = r1.streamKeys     // Catch:{ all -> 0x0034 }
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x0034 }
            if (r8 != 0) goto L_0x0038
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.offline.StreamKey> r8 = r1.streamKeys     // Catch:{ all -> 0x0034 }
            java.lang.Object r7 = r7.copy(r8)     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest r7 = (com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest) r7     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r4 = r1
            goto L_0x01a4
        L_0x0038:
            java.util.List r0 = r1.getSegments(r0, r7, r6)     // Catch:{ all -> 0x0034 }
            java.util.Collections.sort(r0)     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory r7 = r1.cacheKeyFactory     // Catch:{ all -> 0x0034 }
            mergeSegments(r0, r7)     // Catch:{ all -> 0x0034 }
            int r12 = r0.size()     // Catch:{ all -> 0x0034 }
            int r7 = r0.size()     // Catch:{ all -> 0x0034 }
            int r7 = r7 - r5
            r8 = 0
            r15 = r6
            r10 = r8
            r13 = r10
        L_0x0052:
            if (r7 < 0) goto L_0x00b5
            java.lang.Object r8 = r0.get(r7)     // Catch:{ all -> 0x009d }
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$Segment r8 = (com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.Segment) r8     // Catch:{ all -> 0x009d }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r8 = r8.dataSpec     // Catch:{ all -> 0x009d }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory r9 = r1.cacheKeyFactory     // Catch:{ all -> 0x009d }
            java.lang.String r9 = r9.buildCacheKey(r8)     // Catch:{ all -> 0x009d }
            long r5 = r8.length     // Catch:{ all -> 0x009d }
            r22 = -1
            int r16 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1))
            if (r16 != 0) goto L_0x007c
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r4 = r1.cache     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.ContentMetadata r4 = r4.getContentMetadata(r9)     // Catch:{ all -> 0x0034 }
            long r16 = com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.ContentMetadata.getContentLength(r4)     // Catch:{ all -> 0x0034 }
            int r4 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1))
            if (r4 == 0) goto L_0x007c
            long r4 = r8.position     // Catch:{ all -> 0x0034 }
            long r5 = r16 - r4
        L_0x007c:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r4 = r1.cache     // Catch:{ all -> 0x009d }
            r24 = r2
            long r1 = r8.position     // Catch:{ all -> 0x009d }
            r16 = r4
            r17 = r9
            r18 = r1
            r20 = r5
            long r1 = r16.getCachedBytes(r17, r18, r20)     // Catch:{ all -> 0x009d }
            long r13 = r13 + r1
            int r4 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1))
            if (r4 == 0) goto L_0x00a8
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00a2
            int r15 = r15 + 1
            r0.remove(r7)     // Catch:{ all -> 0x009d }
            goto L_0x00a2
        L_0x009d:
            r0 = move-exception
            r4 = r25
            goto L_0x01a4
        L_0x00a2:
            int r1 = (r10 > r22 ? 1 : (r10 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x00aa
            long r10 = r10 + r5
            goto L_0x00aa
        L_0x00a8:
            r10 = r22
        L_0x00aa:
            int r7 = r7 + -1
            r1 = r25
            r2 = r24
            r4 = -1000(0xfffffffffffffc18, float:NaN)
            r5 = 1
            r6 = 0
            goto L_0x0052
        L_0x00b5:
            r24 = r2
            if (r26 == 0) goto L_0x00c4
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$ProgressNotifier r1 = new com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$ProgressNotifier     // Catch:{ all -> 0x009d }
            r8 = r1
            r9 = r26
            r8.<init>(r9, r10, r12, r13, r15)     // Catch:{ all -> 0x009d }
        L_0x00c1:
            r2 = r24
            goto L_0x00c6
        L_0x00c4:
            r1 = 0
            goto L_0x00c1
        L_0x00c6:
            r2.addAll(r0)     // Catch:{ all -> 0x009d }
            r4 = r25
        L_0x00cb:
            boolean r0 = r4.isCanceled     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x0167
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x0167
            com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager r0 = r4.priorityTaskManager     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x00e2
            r5 = -1000(0xfffffffffffffc18, float:NaN)
            r0.proceed(r5)     // Catch:{ all -> 0x00df }
            goto L_0x00e2
        L_0x00df:
            r0 = move-exception
            goto L_0x01a4
        L_0x00e2:
            boolean r0 = r3.isEmpty()     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x00f3
            java.lang.Object r0 = r3.removeFirst()     // Catch:{ all -> 0x00df }
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$SegmentDownloadRunnable r0 = (com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.SegmentDownloadRunnable) r0     // Catch:{ all -> 0x00df }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r5 = r0.dataSource     // Catch:{ all -> 0x00df }
            byte[] r0 = r0.temporaryBuffer     // Catch:{ all -> 0x00df }
            goto L_0x00fd
        L_0x00f3:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource$Factory r0 = r4.cacheDataSourceFactory     // Catch:{ all -> 0x00df }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r5 = r0.createDataSourceForDownloading()     // Catch:{ all -> 0x00df }
            r0 = 131072(0x20000, float:1.83671E-40)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x00df }
        L_0x00fd:
            java.lang.Object r6 = r2.removeFirst()     // Catch:{ all -> 0x00df }
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$Segment r6 = (com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.Segment) r6     // Catch:{ all -> 0x00df }
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$SegmentDownloadRunnable r7 = new com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$SegmentDownloadRunnable     // Catch:{ all -> 0x00df }
            r7.<init>(r6, r5, r1, r0)     // Catch:{ all -> 0x00df }
            r4.addActiveRunnable(r7)     // Catch:{ all -> 0x00df }
            java.util.concurrent.Executor r0 = r4.executor     // Catch:{ all -> 0x00df }
            r0.execute(r7)     // Catch:{ all -> 0x00df }
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r0 = r4.activeRunnables     // Catch:{ all -> 0x00df }
            int r0 = r0.size()     // Catch:{ all -> 0x00df }
            r5 = 1
            int r0 = r0 - r5
            r5 = r0
        L_0x0119:
            if (r5 < 0) goto L_0x0162
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r0 = r4.activeRunnables     // Catch:{ all -> 0x00df }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00df }
            r6 = r0
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$SegmentDownloadRunnable r6 = (com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.SegmentDownloadRunnable) r6     // Catch:{ all -> 0x00df }
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x0130
            boolean r0 = r6.isDone()     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x015c
        L_0x0130:
            r6.get()     // Catch:{ ExecutionException -> 0x013a }
            r4.removeActiveRunnable((int) r5)     // Catch:{ ExecutionException -> 0x013a }
            r3.addLast(r6)     // Catch:{ ExecutionException -> 0x013a }
            goto L_0x015c
        L_0x013a:
            r0 = move-exception
            java.lang.Throwable r0 = r0.getCause()     // Catch:{ all -> 0x00df }
            java.lang.Object r0 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x00df }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x00df }
            boolean r8 = r0 instanceof com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager.PriorityTooLowException     // Catch:{ all -> 0x00df }
            if (r8 == 0) goto L_0x0155
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$Segment r0 = r6.segment     // Catch:{ all -> 0x00df }
            r2.addFirst(r0)     // Catch:{ all -> 0x00df }
            r4.removeActiveRunnable((int) r5)     // Catch:{ all -> 0x00df }
            r3.addLast(r6)     // Catch:{ all -> 0x00df }
            goto L_0x015c
        L_0x0155:
            boolean r6 = r0 instanceof java.io.IOException     // Catch:{ all -> 0x00df }
            if (r6 != 0) goto L_0x015f
            com.appsamurai.storyly.exoplayer2.common.util.Util.sneakyThrow(r0)     // Catch:{ all -> 0x00df }
        L_0x015c:
            int r5 = r5 + -1
            goto L_0x0119
        L_0x015f:
            java.io.IOException r0 = (java.io.IOException) r0     // Catch:{ all -> 0x00df }
            throw r0     // Catch:{ all -> 0x00df }
        L_0x0162:
            r7.blockUntilStarted()     // Catch:{ all -> 0x00df }
            goto L_0x00cb
        L_0x0167:
            r6 = 0
        L_0x0168:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r0 = r4.activeRunnables
            int r0 = r0.size()
            if (r6 >= r0) goto L_0x017f
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r0 = r4.activeRunnables
            java.lang.Object r0 = r0.get(r6)
            com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask r0 = (com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask) r0
            r1 = 1
            r0.cancel(r1)
            int r6 = r6 + 1
            goto L_0x0168
        L_0x017f:
            r1 = 1
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r0 = r4.activeRunnables
            int r0 = r0.size()
            int r0 = r0 - r1
        L_0x0187:
            if (r0 < 0) goto L_0x019a
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r1 = r4.activeRunnables
            java.lang.Object r1 = r1.get(r0)
            com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask r1 = (com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask) r1
            r1.blockUntilFinished()
            r4.removeActiveRunnable((int) r0)
            int r0 = r0 + -1
            goto L_0x0187
        L_0x019a:
            com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager r0 = r4.priorityTaskManager
            if (r0 == 0) goto L_0x01a3
            r1 = -1000(0xfffffffffffffc18, float:NaN)
            r0.remove(r1)
        L_0x01a3:
            return
        L_0x01a4:
            r6 = 0
        L_0x01a5:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r1 = r4.activeRunnables
            int r1 = r1.size()
            if (r6 >= r1) goto L_0x01bc
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r1 = r4.activeRunnables
            java.lang.Object r1 = r1.get(r6)
            com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask r1 = (com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask) r1
            r2 = 1
            r1.cancel(r2)
            int r6 = r6 + 1
            goto L_0x01a5
        L_0x01bc:
            r2 = 1
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r1 = r4.activeRunnables
            int r1 = r1.size()
            int r1 = r1 - r2
        L_0x01c4:
            if (r1 < 0) goto L_0x01d7
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask<?, ?>> r2 = r4.activeRunnables
            java.lang.Object r2 = r2.get(r1)
            com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask r2 = (com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask) r2
            r2.blockUntilFinished()
            r4.removeActiveRunnable((int) r1)
            int r1 = r1 + -1
            goto L_0x01c4
        L_0x01d7:
            com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager r1 = r4.priorityTaskManager
            if (r1 == 0) goto L_0x01e0
            r2 = -1000(0xfffffffffffffc18, float:NaN)
            r1.remove(r2)
        L_0x01e0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.download(com.appsamurai.storyly.exoplayer2.core.offline.Downloader$ProgressListener):void");
    }

    public final <T> T execute(RunnableFutureTask<T, ?> runnableFutureTask, boolean z2) throws InterruptedException, IOException {
        if (z2) {
            runnableFutureTask.run();
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e3) {
                Throwable th = (Throwable) Assertions.checkNotNull(e3.getCause());
                if (!(th instanceof IOException)) {
                    Util.sneakyThrow(e3);
                } else {
                    throw ((IOException) th);
                }
            }
        }
        while (!this.isCanceled) {
            PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
            if (priorityTaskManager2 != null) {
                priorityTaskManager2.proceed(-1000);
            }
            addActiveRunnable(runnableFutureTask);
            this.executor.execute(runnableFutureTask);
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e4) {
                Throwable th2 = (Throwable) Assertions.checkNotNull(e4.getCause());
                if (!(th2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (!(th2 instanceof IOException)) {
                        Util.sneakyThrow(e4);
                    } else {
                        throw ((IOException) th2);
                    }
                }
            } finally {
                runnableFutureTask.blockUntilFinished();
                removeActiveRunnable((RunnableFutureTask<?, ?>) runnableFutureTask);
            }
        }
        throw new InterruptedException();
    }

    public final M getManifest(final DataSource dataSource, final DataSpec dataSpec, boolean z2) throws InterruptedException, IOException {
        return (FilterableManifest) execute(new RunnableFutureTask<M, IOException>() {
            public M doWork() throws IOException {
                return (FilterableManifest) ParsingLoadable.load(dataSource, SegmentDownloader.this.manifestParser, dataSpec, 4);
            }
        }, z2);
    }

    public abstract List<Segment> getSegments(DataSource dataSource, M m3, boolean z2) throws IOException, InterruptedException;

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void remove() {
        /*
            r5 = this;
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource$Factory r0 = r5.cacheDataSourceFactory
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource r0 = r0.createDataSourceForRemovingDownload()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r1 = r5.manifestDataSpec     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            r2 = 1
            com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest r1 = r5.getManifest(r0, r1, r2)     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            java.util.List r0 = r5.getSegments(r0, r1, r2)     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            r1 = 0
        L_0x0012:
            int r2 = r0.size()     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            if (r1 >= r2) goto L_0x0030
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r2 = r5.cache     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory r3 = r5.cacheKeyFactory     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            java.lang.Object r4 = r0.get(r1)     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader$Segment r4 = (com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.Segment) r4     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r4 = r4.dataSpec     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            java.lang.String r3 = r3.buildCacheKey(r4)     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            r2.removeResource(r3)     // Catch:{ InterruptedException -> 0x003e, Exception -> 0x0030 }
            int r1 = r1 + 1
            goto L_0x0012
        L_0x002e:
            r0 = move-exception
            goto L_0x0047
        L_0x0030:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r0 = r5.cache
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory r1 = r5.cacheKeyFactory
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r5 = r5.manifestDataSpec
            java.lang.String r5 = r1.buildCacheKey(r5)
            r0.removeResource(r5)
            goto L_0x0046
        L_0x003e:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x002e }
            r0.interrupt()     // Catch:{ all -> 0x002e }
            goto L_0x0030
        L_0x0046:
            return
        L_0x0047:
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r1 = r5.cache
            com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory r2 = r5.cacheKeyFactory
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r5 = r5.manifestDataSpec
            java.lang.String r5 = r2.buildCacheKey(r5)
            r1.removeResource(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.offline.SegmentDownloader.remove():void");
    }

    private void removeActiveRunnable(int i3) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(i3);
        }
    }
}
