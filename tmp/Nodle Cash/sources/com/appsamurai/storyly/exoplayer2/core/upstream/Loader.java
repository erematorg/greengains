package com.appsamurai.storyly.exoplayer2.core.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.TraceUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.DefaultLoadControl;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class Loader implements LoaderErrorThrower {
    private static final int ACTION_TYPE_DONT_RETRY = 2;
    private static final int ACTION_TYPE_DONT_RETRY_FATAL = 3;
    private static final int ACTION_TYPE_RETRY = 0;
    private static final int ACTION_TYPE_RETRY_AND_RESET_ERROR_COUNT = 1;
    public static final LoadErrorAction DONT_RETRY = new LoadErrorAction(2, C.TIME_UNSET);
    public static final LoadErrorAction DONT_RETRY_FATAL = new LoadErrorAction(3, C.TIME_UNSET);
    public static final LoadErrorAction RETRY = createRetryAction(false, C.TIME_UNSET);
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT = createRetryAction(true, C.TIME_UNSET);
    private static final String THREAD_NAME_PREFIX = "ExoPlayer:Loader:";
    /* access modifiers changed from: private */
    @Nullable
    public LoadTask<? extends Loadable> currentTask;
    /* access modifiers changed from: private */
    public final ExecutorService downloadExecutorService;
    /* access modifiers changed from: private */
    @Nullable
    public IOException fatalError;

    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t2, long j2, long j3, boolean z2);

        void onLoadCompleted(T t2, long j2, long j3);

        LoadErrorAction onLoadError(T t2, long j2, long j3, IOException iOException, int i3);
    }

    public static final class LoadErrorAction {
        /* access modifiers changed from: private */
        public final long retryDelayMillis;
        /* access modifiers changed from: private */
        public final int type;

        public boolean isRetry() {
            int i3 = this.type;
            return i3 == 0 || i3 == 1;
        }

        private LoadErrorAction(int i3, long j2) {
            this.type = i3;
            this.retryDelayMillis = j2;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private static final int MSG_FATAL_ERROR = 3;
        private static final int MSG_FINISH = 1;
        private static final int MSG_IO_EXCEPTION = 2;
        private static final int MSG_START = 0;
        private static final String TAG = "LoadTask";
        @Nullable
        private Callback<T> callback;
        private boolean canceled;
        @Nullable
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        @Nullable
        private Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Looper looper, T t2, Callback<T> callback2, int i3, long j2) {
            super(looper);
            this.loadable = t2;
            this.callback = callback2;
            this.defaultMinRetryCount = i3;
            this.startTimeMs = j2;
        }

        private void execute() {
            this.currentError = null;
            Loader.this.downloadExecutorService.execute((Runnable) Assertions.checkNotNull(Loader.this.currentTask));
        }

        private void finish() {
            LoadTask unused = Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return (long) Math.min((this.errorCount - 1) * 1000, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
        }

        public void cancel(boolean z2) {
            this.released = z2;
            this.currentError = null;
            if (hasMessages(0)) {
                this.canceled = true;
                removeMessages(0);
                if (!z2) {
                    sendEmptyMessage(1);
                }
            } else {
                synchronized (this) {
                    try {
                        this.canceled = true;
                        this.loadable.cancelLoad();
                        Thread thread = this.executorThread;
                        if (thread != null) {
                            thread.interrupt();
                        }
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            }
            if (z2) {
                finish();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ((Callback) Assertions.checkNotNull(this.callback)).onLoadCanceled(this.loadable, elapsedRealtime, elapsedRealtime - this.startTimeMs, true);
                this.callback = null;
            }
        }

        public void handleMessage(Message message) {
            if (!this.released) {
                int i3 = message.what;
                if (i3 == 0) {
                    execute();
                } else if (i3 != 3) {
                    finish();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j2 = elapsedRealtime - this.startTimeMs;
                    Callback callback2 = (Callback) Assertions.checkNotNull(this.callback);
                    if (this.canceled) {
                        callback2.onLoadCanceled(this.loadable, elapsedRealtime, j2, false);
                        return;
                    }
                    int i4 = message.what;
                    if (i4 == 1) {
                        try {
                            callback2.onLoadCompleted(this.loadable, elapsedRealtime, j2);
                        } catch (RuntimeException e3) {
                            Log.e(TAG, "Unexpected exception handling load completed", e3);
                            IOException unused = Loader.this.fatalError = new UnexpectedLoaderException(e3);
                        }
                    } else if (i4 == 2) {
                        IOException iOException = (IOException) message.obj;
                        this.currentError = iOException;
                        int i5 = this.errorCount + 1;
                        this.errorCount = i5;
                        LoadErrorAction onLoadError = callback2.onLoadError(this.loadable, elapsedRealtime, j2, iOException, i5);
                        if (onLoadError.type == 3) {
                            IOException unused2 = Loader.this.fatalError = this.currentError;
                        } else if (onLoadError.type != 2) {
                            if (onLoadError.type == 1) {
                                this.errorCount = 1;
                            }
                            start(onLoadError.retryDelayMillis != C.TIME_UNSET ? onLoadError.retryDelayMillis : getRetryDelayMillis());
                        }
                    }
                } else {
                    throw ((Error) message.obj);
                }
            }
        }

        public void maybeThrowError(int i3) throws IOException {
            IOException iOException = this.currentError;
            if (iOException != null && this.errorCount > i3) {
                throw iOException;
            }
        }

        public void run() {
            boolean z2;
            try {
                synchronized (this) {
                    z2 = this.canceled;
                    this.executorThread = Thread.currentThread();
                }
                if (!z2) {
                    TraceUtil.beginSection("load:".concat(this.loadable.getClass().getSimpleName()));
                    this.loadable.load();
                    TraceUtil.endSection();
                }
                synchronized (this) {
                    this.executorThread = null;
                    Thread.interrupted();
                }
                if (!this.released) {
                    sendEmptyMessage(1);
                }
            } catch (IOException e3) {
                if (!this.released) {
                    obtainMessage(2, e3).sendToTarget();
                }
            } catch (Exception e4) {
                if (!this.released) {
                    Log.e(TAG, "Unexpected exception loading stream", e4);
                    obtainMessage(2, new UnexpectedLoaderException(e4)).sendToTarget();
                }
            } catch (OutOfMemoryError e5) {
                if (!this.released) {
                    Log.e(TAG, "OutOfMemory error loading stream", e5);
                    obtainMessage(2, new UnexpectedLoaderException(e5)).sendToTarget();
                }
            } catch (Error e6) {
                if (!this.released) {
                    Log.e(TAG, "Unexpected error loading stream", e6);
                    obtainMessage(3, e6).sendToTarget();
                }
                throw e6;
            } catch (Throwable th) {
                TraceUtil.endSection();
                throw th;
            }
        }

        public void start(long j2) {
            Assertions.checkState(Loader.this.currentTask == null);
            LoadTask unused = Loader.this.currentTask = this;
            if (j2 > 0) {
                sendEmptyMessageDelayed(0, j2);
            } else {
                execute();
            }
        }
    }

    public interface Loadable {
        void cancelLoad();

        void load() throws IOException;
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    public static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable th) {
            super("Unexpected " + th.getClass().getSimpleName() + ": " + th.getMessage(), th);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(THREAD_NAME_PREFIX + str);
    }

    public static LoadErrorAction createRetryAction(boolean z2, long j2) {
        return new LoadErrorAction(z2 ? 1 : 0, j2);
    }

    public void cancelLoading() {
        ((LoadTask) Assertions.checkStateNotNull(this.currentTask)).cancel(false);
    }

    public void clearFatalError() {
        this.fatalError = null;
    }

    public boolean hasFatalError() {
        return this.fatalError != null;
    }

    public boolean isLoading() {
        return this.currentTask != null;
    }

    public void maybeThrowError() throws IOException {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void release() {
        release((ReleaseCallback) null);
    }

    public <T extends Loadable> long startLoading(T t2, Callback<T> callback, int i3) {
        this.fatalError = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask((Looper) Assertions.checkStateNotNull(Looper.myLooper()), t2, callback, i3, elapsedRealtime).start(0);
        return elapsedRealtime;
    }

    public void maybeThrowError(int i3) throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            LoadTask<? extends Loadable> loadTask = this.currentTask;
            if (loadTask != null) {
                if (i3 == Integer.MIN_VALUE) {
                    i3 = loadTask.defaultMinRetryCount;
                }
                loadTask.maybeThrowError(i3);
                return;
            }
            return;
        }
        throw iOException;
    }

    public void release(@Nullable ReleaseCallback releaseCallback) {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            loadTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutorService.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutorService.shutdown();
    }
}
