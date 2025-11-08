package com.appsamurai.storyly.exoplayer2.core.offline;

import androidx.annotation.Nullable;
import androidx.arch.core.executor.a;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.offline.Downloader;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public final class ProgressiveDownloader implements Downloader {
    /* access modifiers changed from: private */
    public final CacheWriter cacheWriter;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private volatile RunnableFutureTask<Void, IOException> downloadRunnable;
    private final Executor executor;
    private volatile boolean isCanceled;
    @Nullable
    private final PriorityTaskManager priorityTaskManager;
    @Nullable
    private Downloader.ProgressListener progressListener;

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new a(2));
    }

    /* access modifiers changed from: private */
    public void onProgress(long j2, long j3, long j4) {
        Downloader.ProgressListener progressListener2 = this.progressListener;
        if (progressListener2 != null) {
            progressListener2.onProgress(j2, j3, (j2 == -1 || j2 == 0) ? -1.0f : (((float) j3) * 100.0f) / ((float) j2));
        }
    }

    public void cancel() {
        this.isCanceled = true;
        RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
        if (runnableFutureTask != null) {
            runnableFutureTask.cancel(true);
        }
    }

    public void download(@Nullable Downloader.ProgressListener progressListener2) throws IOException, InterruptedException {
        this.progressListener = progressListener2;
        this.downloadRunnable = new RunnableFutureTask<Void, IOException>() {
            public void cancelWork() {
                ProgressiveDownloader.this.cacheWriter.cancel();
            }

            public Void doWork() throws IOException {
                ProgressiveDownloader.this.cacheWriter.cache();
                return null;
            }
        };
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (priorityTaskManager2 != null) {
            priorityTaskManager2.add(-1000);
        }
        boolean z2 = false;
        while (!z2) {
            try {
                if (this.isCanceled) {
                    break;
                }
                PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
                if (priorityTaskManager3 != null) {
                    priorityTaskManager3.proceed(-1000);
                }
                this.executor.execute(this.downloadRunnable);
                this.downloadRunnable.get();
                z2 = true;
            } catch (ExecutionException e3) {
                Throwable th = (Throwable) Assertions.checkNotNull(e3.getCause());
                if (!(th instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (!(th instanceof IOException)) {
                        Util.sneakyThrow(th);
                    } else {
                        throw ((IOException) th);
                    }
                }
            } catch (Throwable th2) {
                this.downloadRunnable.blockUntilFinished();
                PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
                if (priorityTaskManager4 != null) {
                    priorityTaskManager4.remove(-1000);
                }
                throw th2;
            }
        }
        this.downloadRunnable.blockUntilFinished();
        PriorityTaskManager priorityTaskManager5 = this.priorityTaskManager;
        if (priorityTaskManager5 != null) {
            priorityTaskManager5.remove(-1000);
        }
    }

    public void remove() {
        this.dataSource.getCache().removeResource(this.dataSource.getCacheKeyFactory().buildCacheKey(this.dataSpec));
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor2) {
        this.executor = (Executor) Assertions.checkNotNull(executor2);
        Assertions.checkNotNull(mediaItem.localConfiguration);
        DataSpec build = new DataSpec.Builder().setUri(mediaItem.localConfiguration.uri).setKey(mediaItem.localConfiguration.customCacheKey).setFlags(4).build();
        this.dataSpec = build;
        CacheDataSource createDataSourceForDownloading = factory.createDataSourceForDownloading();
        this.dataSource = createDataSourceForDownloading;
        this.cacheWriter = new CacheWriter(createDataSourceForDownloading, build, (byte[]) null, new f(this));
        this.priorityTaskManager = factory.getUpstreamPriorityTaskManager();
    }
}
