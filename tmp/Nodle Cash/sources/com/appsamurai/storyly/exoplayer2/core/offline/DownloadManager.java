package com.appsamurai.storyly.exoplayer2.core.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CheckResult;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.DefaultLoadControl;
import com.appsamurai.storyly.exoplayer2.core.offline.Downloader;
import com.appsamurai.storyly.exoplayer2.core.scheduler.Requirements;
import com.appsamurai.storyly.exoplayer2.core.scheduler.RequirementsWatcher;
import com.appsamurai.storyly.exoplayer2.database.DatabaseProvider;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheDataSource;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

public final class DownloadManager {
    public static final int DEFAULT_MAX_PARALLEL_DOWNLOADS = 3;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    public static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1);
    private static final int MSG_ADD_DOWNLOAD = 6;
    private static final int MSG_CONTENT_LENGTH_CHANGED = 10;
    private static final int MSG_DOWNLOAD_UPDATE = 2;
    private static final int MSG_INITIALIZE = 0;
    private static final int MSG_INITIALIZED = 0;
    private static final int MSG_PROCESSED = 1;
    private static final int MSG_RELEASE = 12;
    private static final int MSG_REMOVE_ALL_DOWNLOADS = 8;
    private static final int MSG_REMOVE_DOWNLOAD = 7;
    private static final int MSG_SET_DOWNLOADS_PAUSED = 1;
    private static final int MSG_SET_MAX_PARALLEL_DOWNLOADS = 4;
    private static final int MSG_SET_MIN_RETRY_COUNT = 5;
    private static final int MSG_SET_NOT_MET_REQUIREMENTS = 2;
    private static final int MSG_SET_STOP_REASON = 3;
    private static final int MSG_TASK_STOPPED = 9;
    private static final int MSG_UPDATE_PROGRESS = 11;
    private static final String TAG = "DownloadManager";
    private int activeTaskCount;
    private final Handler applicationHandler;
    private final Context context;
    private final WritableDownloadIndex downloadIndex;
    private List<Download> downloads;
    private boolean downloadsPaused;
    private boolean initialized;
    private final InternalHandler internalHandler;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int maxParallelDownloads;
    private int minRetryCount;
    private int notMetRequirements;
    private int pendingMessages;
    private final RequirementsWatcher.Listener requirementsListener;
    private RequirementsWatcher requirementsWatcher;
    private boolean waitingForRequirements;

    public static final class DownloadUpdate {
        public final Download download;
        public final List<Download> downloads;
        @Nullable
        public final Exception finalException;
        public final boolean isRemove;

        public DownloadUpdate(Download download2, boolean z2, List<Download> list, @Nullable Exception exc) {
            this.download = download2;
            this.isRemove = z2;
            this.downloads = list;
            this.finalException = exc;
        }
    }

    public interface Listener {
        void onDownloadChanged(DownloadManager downloadManager, Download download, @Nullable Exception exc) {
        }

        void onDownloadRemoved(DownloadManager downloadManager, Download download) {
        }

        void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z2) {
        }

        void onIdle(DownloadManager downloadManager) {
        }

        void onInitialized(DownloadManager downloadManager) {
        }

        void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i3) {
        }

        void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z2) {
        }
    }

    public static class Task extends Thread implements Downloader.ProgressListener {
        private long contentLength;
        private final DownloadProgress downloadProgress;
        private final Downloader downloader;
        /* access modifiers changed from: private */
        @Nullable
        public Exception finalException;
        @Nullable
        private volatile InternalHandler internalHandler;
        /* access modifiers changed from: private */
        public volatile boolean isCanceled;
        /* access modifiers changed from: private */
        public final boolean isRemove;
        private final int minRetryCount;
        /* access modifiers changed from: private */
        public final DownloadRequest request;

        private static int getRetryDelayMillis(int i3) {
            return Math.min((i3 - 1) * 1000, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
        }

        public void cancel(boolean z2) {
            if (z2) {
                this.internalHandler = null;
            }
            if (!this.isCanceled) {
                this.isCanceled = true;
                this.downloader.cancel();
                interrupt();
            }
        }

        public void onProgress(long j2, long j3, float f2) {
            this.downloadProgress.bytesDownloaded = j3;
            this.downloadProgress.percentDownloaded = f2;
            if (j2 != this.contentLength) {
                this.contentLength = j2;
                InternalHandler internalHandler2 = this.internalHandler;
                if (internalHandler2 != null) {
                    internalHandler2.obtainMessage(10, (int) (j2 >> 32), (int) j2, this).sendToTarget();
                }
            }
        }

        public void run() {
            long j2;
            int i3;
            try {
                if (this.isRemove) {
                    this.downloader.remove();
                } else {
                    j2 = -1;
                    i3 = 0;
                    while (!this.isCanceled) {
                        this.downloader.download(this);
                    }
                }
            } catch (IOException e3) {
                if (!this.isCanceled) {
                    long j3 = this.downloadProgress.bytesDownloaded;
                    if (j3 != j2) {
                        i3 = 0;
                        j2 = j3;
                    }
                    i3++;
                    if (i3 <= this.minRetryCount) {
                        Thread.sleep((long) getRetryDelayMillis(i3));
                    } else {
                        throw e3;
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Exception e4) {
                this.finalException = e4;
            }
            InternalHandler internalHandler2 = this.internalHandler;
            if (internalHandler2 != null) {
                internalHandler2.obtainMessage(9, this).sendToTarget();
            }
        }

        private Task(DownloadRequest downloadRequest, Downloader downloader2, DownloadProgress downloadProgress2, boolean z2, int i3, InternalHandler internalHandler2) {
            this.request = downloadRequest;
            this.downloader = downloader2;
            this.downloadProgress = downloadProgress2;
            this.isRemove = z2;
            this.minRetryCount = i3;
            this.internalHandler = internalHandler2;
            this.contentLength = -1;
        }
    }

    @Deprecated
    public DownloadManager(Context context2, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory) {
        this(context2, databaseProvider, cache, factory, new a(2));
    }

    /* access modifiers changed from: private */
    public boolean handleMainMessage(Message message) {
        int i3 = message.what;
        if (i3 == 0) {
            onInitialized((List) message.obj);
        } else if (i3 == 1) {
            onMessageProcessed(message.arg1, message.arg2);
        } else if (i3 == 2) {
            onDownloadUpdate((DownloadUpdate) message.obj);
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    public static Download mergeRequest(Download download, DownloadRequest downloadRequest, int i3, long j2) {
        int i4;
        Download download2 = download;
        int i5 = download2.state;
        long j3 = (i5 == 5 || download.isTerminalState()) ? j2 : download2.startTimeMs;
        if (i5 == 5 || i5 == 7) {
            i4 = 7;
        } else {
            i4 = i3 != 0 ? 1 : 0;
        }
        return new Download(download2.request.copyWithMergedRequest(downloadRequest), i4, j3, j2, -1, i3, 0);
    }

    private void notifyWaitingForRequirementsChanged() {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onWaitingForRequirementsChanged(this, this.waitingForRequirements);
        }
    }

    private void onDownloadUpdate(DownloadUpdate downloadUpdate) {
        this.downloads = Collections.unmodifiableList(downloadUpdate.downloads);
        Download download = downloadUpdate.download;
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        if (downloadUpdate.isRemove) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadRemoved(this, download);
            }
        } else {
            Iterator<Listener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().onDownloadChanged(this, download, downloadUpdate.finalException);
            }
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onInitialized(List<Download> list) {
        this.initialized = true;
        this.downloads = Collections.unmodifiableList(list);
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onInitialized(this);
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onMessageProcessed(int i3, int i4) {
        this.pendingMessages -= i3;
        this.activeTaskCount = i4;
        if (isIdle()) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onIdle(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher2, int i3) {
        Requirements requirements = requirementsWatcher2.getRequirements();
        if (this.notMetRequirements != i3) {
            this.notMetRequirements = i3;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(2, i3, 0).sendToTarget();
        }
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRequirementsStateChanged(this, requirements, i3);
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void setDownloadsPaused(boolean z2) {
        if (this.downloadsPaused != z2) {
            this.downloadsPaused = z2;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(1, z2 ? 1 : 0, 0).sendToTarget();
            boolean updateWaitingForRequirements = updateWaitingForRequirements();
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadsPausedChanged(this, z2);
            }
            if (updateWaitingForRequirements) {
                notifyWaitingForRequirementsChanged();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateWaitingForRequirements() {
        /*
            r4 = this;
            boolean r0 = r4.downloadsPaused
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0024
            int r0 = r4.notMetRequirements
            if (r0 == 0) goto L_0x0024
            r0 = r2
        L_0x000b:
            java.util.List<com.appsamurai.storyly.exoplayer2.core.offline.Download> r3 = r4.downloads
            int r3 = r3.size()
            if (r0 >= r3) goto L_0x0024
            java.util.List<com.appsamurai.storyly.exoplayer2.core.offline.Download> r3 = r4.downloads
            java.lang.Object r3 = r3.get(r0)
            com.appsamurai.storyly.exoplayer2.core.offline.Download r3 = (com.appsamurai.storyly.exoplayer2.core.offline.Download) r3
            int r3 = r3.state
            if (r3 != 0) goto L_0x0021
            r0 = r1
            goto L_0x0025
        L_0x0021:
            int r0 = r0 + 1
            goto L_0x000b
        L_0x0024:
            r0 = r2
        L_0x0025:
            boolean r3 = r4.waitingForRequirements
            if (r3 == r0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r1 = r2
        L_0x002b:
            r4.waitingForRequirements = r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.offline.DownloadManager.updateWaitingForRequirements():boolean");
    }

    public void addDownload(DownloadRequest downloadRequest) {
        addDownload(downloadRequest, 0);
    }

    public void addListener(Listener listener) {
        Assertions.checkNotNull(listener);
        this.listeners.add(listener);
    }

    public Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    public List<Download> getCurrentDownloads() {
        return this.downloads;
    }

    public DownloadIndex getDownloadIndex() {
        return this.downloadIndex;
    }

    public boolean getDownloadsPaused() {
        return this.downloadsPaused;
    }

    public int getMaxParallelDownloads() {
        return this.maxParallelDownloads;
    }

    public int getMinRetryCount() {
        return this.minRetryCount;
    }

    public int getNotMetRequirements() {
        return this.notMetRequirements;
    }

    public Requirements getRequirements() {
        return this.requirementsWatcher.getRequirements();
    }

    public boolean isIdle() {
        return this.activeTaskCount == 0 && this.pendingMessages == 0;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isWaitingForRequirements() {
        return this.waitingForRequirements;
    }

    public void pauseDownloads() {
        setDownloadsPaused(true);
    }

    public void release() {
        synchronized (this.internalHandler) {
            try {
                InternalHandler internalHandler2 = this.internalHandler;
                if (!internalHandler2.released) {
                    internalHandler2.sendEmptyMessage(12);
                    boolean z2 = false;
                    while (true) {
                        InternalHandler internalHandler3 = this.internalHandler;
                        if (internalHandler3.released) {
                            break;
                        }
                        try {
                            internalHandler3.wait();
                        } catch (InterruptedException unused) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        Thread.currentThread().interrupt();
                    }
                    this.applicationHandler.removeCallbacksAndMessages((Object) null);
                    this.downloads = Collections.emptyList();
                    this.pendingMessages = 0;
                    this.activeTaskCount = 0;
                    this.initialized = false;
                    this.notMetRequirements = 0;
                    this.waitingForRequirements = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeAllDownloads() {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(8).sendToTarget();
    }

    public void removeDownload(String str) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(7, str).sendToTarget();
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void resumeDownloads() {
        setDownloadsPaused(false);
    }

    public void setMaxParallelDownloads(@IntRange(from = 1) int i3) {
        Assertions.checkArgument(i3 > 0);
        if (this.maxParallelDownloads != i3) {
            this.maxParallelDownloads = i3;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(4, i3, 0).sendToTarget();
        }
    }

    public void setMinRetryCount(int i3) {
        Assertions.checkArgument(i3 >= 0);
        if (this.minRetryCount != i3) {
            this.minRetryCount = i3;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(5, i3, 0).sendToTarget();
        }
    }

    public void setRequirements(Requirements requirements) {
        if (!requirements.equals(this.requirementsWatcher.getRequirements())) {
            this.requirementsWatcher.stop();
            RequirementsWatcher requirementsWatcher2 = new RequirementsWatcher(this.context, this.requirementsListener, requirements);
            this.requirementsWatcher = requirementsWatcher2;
            onRequirementsStateChanged(this.requirementsWatcher, requirementsWatcher2.start());
        }
    }

    public void setStopReason(@Nullable String str, int i3) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(3, i3, 0, str).sendToTarget();
    }

    public DownloadManager(Context context2, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory, Executor executor) {
        this(context2, new DefaultDownloadIndex(databaseProvider), new DefaultDownloaderFactory(new CacheDataSource.Factory().setCache(cache).setUpstreamDataSourceFactory(factory), executor));
    }

    public void addDownload(DownloadRequest downloadRequest, int i3) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(6, i3, 0, downloadRequest).sendToTarget();
    }

    public DownloadManager(Context context2, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory) {
        this.context = context2.getApplicationContext();
        this.downloadIndex = writableDownloadIndex;
        this.maxParallelDownloads = 3;
        this.minRetryCount = 5;
        this.downloadsPaused = true;
        this.downloads = Collections.emptyList();
        this.listeners = new CopyOnWriteArraySet<>();
        Handler createHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper(new e(this, 1));
        this.applicationHandler = createHandlerForCurrentOrMainLooper;
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadManager");
        handlerThread.start();
        InternalHandler internalHandler2 = new InternalHandler(handlerThread, writableDownloadIndex, downloaderFactory, createHandlerForCurrentOrMainLooper, this.maxParallelDownloads, this.minRetryCount, this.downloadsPaused);
        this.internalHandler = internalHandler2;
        f fVar = new f(this);
        this.requirementsListener = fVar;
        RequirementsWatcher requirementsWatcher2 = new RequirementsWatcher(context2, fVar, DEFAULT_REQUIREMENTS);
        this.requirementsWatcher = requirementsWatcher2;
        int start = requirementsWatcher2.start();
        this.notMetRequirements = start;
        this.pendingMessages = 1;
        internalHandler2.obtainMessage(0, start, 0).sendToTarget();
    }

    public static final class InternalHandler extends Handler {
        private static final int UPDATE_PROGRESS_INTERVAL_MS = 5000;
        private int activeDownloadTaskCount;
        private final HashMap<String, Task> activeTasks = new HashMap<>();
        private final WritableDownloadIndex downloadIndex;
        private final DownloaderFactory downloaderFactory;
        private final ArrayList<Download> downloads = new ArrayList<>();
        private boolean downloadsPaused;
        private final Handler mainHandler;
        private int maxParallelDownloads;
        private int minRetryCount;
        private int notMetRequirements;
        public boolean released;
        private final HandlerThread thread;

        public InternalHandler(HandlerThread handlerThread, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory2, Handler handler, int i3, int i4, boolean z2) {
            super(handlerThread.getLooper());
            this.thread = handlerThread;
            this.downloadIndex = writableDownloadIndex;
            this.downloaderFactory = downloaderFactory2;
            this.mainHandler = handler;
            this.maxParallelDownloads = i3;
            this.minRetryCount = i4;
            this.downloadsPaused = z2;
        }

        private void addDownload(DownloadRequest downloadRequest, int i3) {
            int i4 = 1;
            Download download = getDownload(downloadRequest.id, true);
            long currentTimeMillis = System.currentTimeMillis();
            if (download != null) {
                putDownload(DownloadManager.mergeRequest(download, downloadRequest, i3, currentTimeMillis));
            } else {
                if (i3 == 0) {
                    i4 = 0;
                }
                putDownload(new Download(downloadRequest, i4, currentTimeMillis, currentTimeMillis, -1, i3, 0));
            }
            syncTasks();
        }

        private boolean canDownloadsRun() {
            return !this.downloadsPaused && this.notMetRequirements == 0;
        }

        /* access modifiers changed from: private */
        public static int compareStartTimes(Download download, Download download2) {
            return Util.compareLong(download.startTimeMs, download2.startTimeMs);
        }

        private static Download copyDownloadWithState(Download download, int i3, int i4) {
            return new Download(download.request, i3, download.startTimeMs, System.currentTimeMillis(), download.contentLength, i4, 0, download.progress);
        }

        @Nullable
        private Download getDownload(String str, boolean z2) {
            int downloadIndex2 = getDownloadIndex(str);
            if (downloadIndex2 != -1) {
                return this.downloads.get(downloadIndex2);
            }
            if (!z2) {
                return null;
            }
            try {
                return this.downloadIndex.getDownload(str);
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to load download: " + str, e3);
                return null;
            }
        }

        private int getDownloadIndex(String str) {
            for (int i3 = 0; i3 < this.downloads.size(); i3++) {
                if (this.downloads.get(i3).request.id.equals(str)) {
                    return i3;
                }
            }
            return -1;
        }

        private void initialize(int i3) {
            this.notMetRequirements = i3;
            DownloadCursor downloadCursor = null;
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
                downloadCursor = this.downloadIndex.getDownloads(0, 1, 2, 5, 7);
                while (downloadCursor.moveToNext()) {
                    this.downloads.add(downloadCursor.getDownload());
                }
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to load index.", e3);
                this.downloads.clear();
            } catch (Throwable th) {
                Util.closeQuietly((Closeable) null);
                throw th;
            }
            Util.closeQuietly(downloadCursor);
            this.mainHandler.obtainMessage(0, new ArrayList(this.downloads)).sendToTarget();
            syncTasks();
        }

        private void onContentLengthChanged(Task task, long j2) {
            Download download = (Download) Assertions.checkNotNull(getDownload(task.request.id, false));
            if (j2 != download.contentLength && j2 != -1) {
                putDownload(new Download(download.request, download.state, download.startTimeMs, System.currentTimeMillis(), j2, download.stopReason, download.failureReason, download.progress));
            }
        }

        private void onDownloadTaskStopped(Download download, @Nullable Exception exc) {
            Download download2 = download;
            Exception exc2 = exc;
            Download download3 = new Download(download2.request, exc2 == null ? 3 : 4, download2.startTimeMs, System.currentTimeMillis(), download2.contentLength, download2.stopReason, exc2 == null ? 0 : 1, download2.progress);
            this.downloads.remove(getDownloadIndex(download3.request.id));
            try {
                this.downloadIndex.putDownload(download3);
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e3);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download3, false, new ArrayList(this.downloads), exc2)).sendToTarget();
        }

        private void onRemoveTaskStopped(Download download) {
            int i3 = 1;
            if (download.state == 7) {
                int i4 = download.stopReason;
                if (i4 == 0) {
                    i3 = 0;
                }
                putDownloadWithState(download, i3, i4);
                syncTasks();
                return;
            }
            this.downloads.remove(getDownloadIndex(download.request.id));
            try {
                this.downloadIndex.removeDownload(download.request.id);
            } catch (IOException unused) {
                Log.e(DownloadManager.TAG, "Failed to remove from database");
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, true, new ArrayList(this.downloads), (Exception) null)).sendToTarget();
        }

        private void onTaskStopped(Task task) {
            String str = task.request.id;
            this.activeTasks.remove(str);
            boolean access$000 = task.isRemove;
            if (!access$000) {
                int i3 = this.activeDownloadTaskCount - 1;
                this.activeDownloadTaskCount = i3;
                if (i3 == 0) {
                    removeMessages(11);
                }
            }
            if (task.isCanceled) {
                syncTasks();
                return;
            }
            Exception access$400 = task.finalException;
            if (access$400 != null) {
                Log.e(DownloadManager.TAG, "Task failed: " + task.request + ", " + access$000, access$400);
            }
            Download download = (Download) Assertions.checkNotNull(getDownload(str, false));
            int i4 = download.state;
            if (i4 == 2) {
                Assertions.checkState(!access$000);
                onDownloadTaskStopped(download, access$400);
            } else if (i4 == 5 || i4 == 7) {
                Assertions.checkState(access$000);
                onRemoveTaskStopped(download);
            } else {
                throw new IllegalStateException();
            }
            syncTasks();
        }

        /* JADX WARNING: type inference failed for: r1v10, types: [java.lang.Object, java.util.Comparator] */
        /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Object, java.util.Comparator] */
        private Download putDownload(Download download) {
            int i3 = download.state;
            boolean z2 = true;
            Assertions.checkState((i3 == 3 || i3 == 4) ? false : true);
            int downloadIndex2 = getDownloadIndex(download.request.id);
            if (downloadIndex2 == -1) {
                this.downloads.add(download);
                Collections.sort(this.downloads, new Object());
            } else {
                if (download.startTimeMs == this.downloads.get(downloadIndex2).startTimeMs) {
                    z2 = false;
                }
                this.downloads.set(downloadIndex2, download);
                if (z2) {
                    Collections.sort(this.downloads, new Object());
                }
            }
            try {
                this.downloadIndex.putDownload(download);
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e3);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, false, new ArrayList(this.downloads), (Exception) null)).sendToTarget();
            return download;
        }

        private Download putDownloadWithState(Download download, int i3, int i4) {
            Assertions.checkState((i3 == 3 || i3 == 4) ? false : true);
            return putDownload(copyDownloadWithState(download, i3, i4));
        }

        private void release() {
            for (Task cancel : this.activeTasks.values()) {
                cancel.cancel(true);
            }
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e3);
            }
            this.downloads.clear();
            this.thread.quit();
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        }

        /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Object, java.util.Comparator] */
        private void removeAllDownloads() {
            DownloadCursor downloads2;
            ArrayList arrayList = new ArrayList();
            try {
                downloads2 = this.downloadIndex.getDownloads(3, 4);
                while (downloads2.moveToNext()) {
                    arrayList.add(downloads2.getDownload());
                }
                downloads2.close();
            } catch (IOException unused) {
                Log.e(DownloadManager.TAG, "Failed to load downloads.");
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            for (int i3 = 0; i3 < this.downloads.size(); i3++) {
                ArrayList<Download> arrayList2 = this.downloads;
                arrayList2.set(i3, copyDownloadWithState(arrayList2.get(i3), 5, 0));
            }
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                this.downloads.add(copyDownloadWithState((Download) arrayList.get(i4), 5, 0));
            }
            Collections.sort(this.downloads, new Object());
            try {
                this.downloadIndex.setStatesToRemoving();
            } catch (IOException e3) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e3);
            }
            ArrayList arrayList3 = new ArrayList(this.downloads);
            for (int i5 = 0; i5 < this.downloads.size(); i5++) {
                this.mainHandler.obtainMessage(2, new DownloadUpdate(this.downloads.get(i5), false, arrayList3, (Exception) null)).sendToTarget();
            }
            syncTasks();
            return;
            throw th;
        }

        private void removeDownload(String str) {
            Download download = getDownload(str, true);
            if (download == null) {
                Log.e(DownloadManager.TAG, "Failed to remove nonexistent download: " + str);
                return;
            }
            putDownloadWithState(download, 5, 0);
            syncTasks();
        }

        private void setDownloadsPaused(boolean z2) {
            this.downloadsPaused = z2;
            syncTasks();
        }

        private void setMaxParallelDownloads(int i3) {
            this.maxParallelDownloads = i3;
            syncTasks();
        }

        private void setMinRetryCount(int i3) {
            this.minRetryCount = i3;
        }

        private void setNotMetRequirements(int i3) {
            this.notMetRequirements = i3;
            syncTasks();
        }

        private void setStopReason(@Nullable String str, int i3) {
            if (str == null) {
                for (int i4 = 0; i4 < this.downloads.size(); i4++) {
                    setStopReason(this.downloads.get(i4), i3);
                }
                try {
                    this.downloadIndex.setStopReason(i3);
                } catch (IOException e3) {
                    Log.e(DownloadManager.TAG, "Failed to set manual stop reason", e3);
                }
            } else {
                Download download = getDownload(str, false);
                if (download != null) {
                    setStopReason(download, i3);
                } else {
                    try {
                        this.downloadIndex.setStopReason(str, i3);
                    } catch (IOException e4) {
                        Log.e(DownloadManager.TAG, "Failed to set manual stop reason: ".concat(str), e4);
                    }
                }
            }
            syncTasks();
        }

        private void syncDownloadingDownload(Task task, Download download, int i3) {
            Assertions.checkState(!task.isRemove);
            if (!canDownloadsRun() || i3 >= this.maxParallelDownloads) {
                putDownloadWithState(download, 0, 0);
                task.cancel(false);
            }
        }

        @CheckResult
        @Nullable
        private Task syncQueuedDownload(@Nullable Task task, Download download) {
            if (task != null) {
                Assertions.checkState(!task.isRemove);
                task.cancel(false);
                return task;
            } else if (!canDownloadsRun() || this.activeDownloadTaskCount >= this.maxParallelDownloads) {
                return null;
            } else {
                Download putDownloadWithState = putDownloadWithState(download, 2, 0);
                Task task2 = new Task(putDownloadWithState.request, this.downloaderFactory.createDownloader(putDownloadWithState.request), putDownloadWithState.progress, false, this.minRetryCount, this);
                this.activeTasks.put(putDownloadWithState.request.id, task2);
                int i3 = this.activeDownloadTaskCount;
                this.activeDownloadTaskCount = i3 + 1;
                if (i3 == 0) {
                    sendEmptyMessageDelayed(11, 5000);
                }
                task2.start();
                return task2;
            }
        }

        private void syncRemovingDownload(@Nullable Task task, Download download) {
            if (task == null) {
                Task task2 = new Task(download.request, this.downloaderFactory.createDownloader(download.request), download.progress, true, this.minRetryCount, this);
                this.activeTasks.put(download.request.id, task2);
                task2.start();
            } else if (!task.isRemove) {
                task.cancel(false);
            }
        }

        private void syncStoppedDownload(@Nullable Task task) {
            if (task != null) {
                Assertions.checkState(!task.isRemove);
                task.cancel(false);
            }
        }

        private void syncTasks() {
            int i3 = 0;
            for (int i4 = 0; i4 < this.downloads.size(); i4++) {
                Download download = this.downloads.get(i4);
                Task task = this.activeTasks.get(download.request.id);
                int i5 = download.state;
                if (i5 == 0) {
                    task = syncQueuedDownload(task, download);
                } else if (i5 == 1) {
                    syncStoppedDownload(task);
                } else if (i5 == 2) {
                    Assertions.checkNotNull(task);
                    syncDownloadingDownload(task, download, i3);
                } else if (i5 == 5 || i5 == 7) {
                    syncRemovingDownload(task, download);
                } else {
                    throw new IllegalStateException();
                }
                if (task != null && !task.isRemove) {
                    i3++;
                }
            }
        }

        private void updateProgress() {
            for (int i3 = 0; i3 < this.downloads.size(); i3++) {
                Download download = this.downloads.get(i3);
                if (download.state == 2) {
                    try {
                        this.downloadIndex.putDownload(download);
                    } catch (IOException e3) {
                        Log.e(DownloadManager.TAG, "Failed to update index.", e3);
                    }
                }
            }
            sendEmptyMessageDelayed(11, 5000);
        }

        public void handleMessage(Message message) {
            boolean z2 = false;
            switch (message.what) {
                case 0:
                    initialize(message.arg1);
                    break;
                case 1:
                    if (message.arg1 != 0) {
                        z2 = true;
                    }
                    setDownloadsPaused(z2);
                    break;
                case 2:
                    setNotMetRequirements(message.arg1);
                    break;
                case 3:
                    setStopReason((String) message.obj, message.arg1);
                    break;
                case 4:
                    setMaxParallelDownloads(message.arg1);
                    break;
                case 5:
                    setMinRetryCount(message.arg1);
                    break;
                case 6:
                    addDownload((DownloadRequest) message.obj, message.arg1);
                    break;
                case 7:
                    removeDownload((String) message.obj);
                    break;
                case 8:
                    removeAllDownloads();
                    break;
                case 9:
                    onTaskStopped((Task) message.obj);
                    break;
                case 10:
                    onContentLengthChanged((Task) message.obj, Util.toLong(message.arg1, message.arg2));
                    return;
                case 11:
                    updateProgress();
                    return;
                case 12:
                    release();
                    return;
                default:
                    throw new IllegalStateException();
            }
            z2 = true;
            this.mainHandler.obtainMessage(1, z2 ? 1 : 0, this.activeTasks.size()).sendToTarget();
        }

        private void setStopReason(Download download, int i3) {
            Download download2 = download;
            int i4 = i3;
            if (i4 == 0) {
                if (download2.state == 1) {
                    putDownloadWithState(download, 0, 0);
                }
            } else if (i4 != download2.stopReason) {
                int i5 = download2.state;
                if (i5 == 0 || i5 == 2) {
                    i5 = 1;
                }
                putDownload(new Download(download2.request, i5, download2.startTimeMs, System.currentTimeMillis(), download2.contentLength, i3, 0, download2.progress));
            }
        }
    }
}
