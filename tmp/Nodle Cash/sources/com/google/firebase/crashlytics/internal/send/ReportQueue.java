package com.google.firebase.crashlytics.internal.send;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.ForcedSender;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.common.OnDemandCounter;
import com.google.firebase.crashlytics.internal.common.Utils;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.Settings;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ReportQueue {
    private static final int MAX_DELAY_MS = 3600000;
    private static final int MS_PER_MINUTE = 60000;
    private static final int MS_PER_SECOND = 1000;
    private static final int STARTUP_DURATION_MS = 2000;
    private final double base;
    private long lastUpdatedMs;
    /* access modifiers changed from: private */
    public final OnDemandCounter onDemandCounter;
    private final BlockingQueue<Runnable> queue;
    private final int queueCapacity;
    private final double ratePerMinute;
    private final ThreadPoolExecutor singleThreadExecutor;
    private final long startTimeMs;
    private int step;
    private final long stepDurationMs;
    private final Transport<CrashlyticsReport> transport;

    public final class ReportRunnable implements Runnable {
        private final CrashlyticsReportWithSessionId reportWithSessionId;
        private final TaskCompletionSource<CrashlyticsReportWithSessionId> tcs;

        public void run() {
            ReportQueue.this.sendReport(this.reportWithSessionId, this.tcs);
            ReportQueue.this.onDemandCounter.resetDroppedOnDemandExceptions();
            double access$300 = ReportQueue.this.calcDelay();
            Logger logger = Logger.getLogger();
            logger.d("Delay for: " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(access$300 / 1000.0d)}) + " s for report: " + this.reportWithSessionId.getSessionId());
            ReportQueue.sleep(access$300);
        }

        private ReportRunnable(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource) {
            this.reportWithSessionId = crashlyticsReportWithSessionId;
            this.tcs = taskCompletionSource;
        }
    }

    public ReportQueue(Transport<CrashlyticsReport> transport2, Settings settings, OnDemandCounter onDemandCounter2) {
        this(settings.onDemandUploadRatePerMinute, settings.onDemandBackoffBase, ((long) settings.onDemandBackoffStepDurationSeconds) * 1000, transport2, onDemandCounter2);
    }

    /* access modifiers changed from: private */
    public double calcDelay() {
        return Math.min(3600000.0d, Math.pow(this.base, (double) calcStep()) * (60000.0d / this.ratePerMinute));
    }

    private int calcStep() {
        if (this.lastUpdatedMs == 0) {
            this.lastUpdatedMs = now();
        }
        int now = (int) ((now() - this.lastUpdatedMs) / this.stepDurationMs);
        int min = isQueueFull() ? Math.min(100, this.step + now) : Math.max(0, this.step - now);
        if (this.step != min) {
            this.step = min;
            this.lastUpdatedMs = now();
        }
        return min;
    }

    private boolean isQueueAvailable() {
        return this.queue.size() < this.queueCapacity;
    }

    private boolean isQueueFull() {
        return this.queue.size() == this.queueCapacity;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$flushScheduledReportsIfAble$0(CountDownLatch countDownLatch) {
        try {
            ForcedSender.sendBlocking(this.transport, Priority.HIGHEST);
        } catch (Exception unused) {
        }
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendReport$1(TaskCompletionSource taskCompletionSource, boolean z2, CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, Exception exc) {
        if (exc != null) {
            taskCompletionSource.trySetException(exc);
            return;
        }
        if (z2) {
            flushScheduledReportsIfAble();
        }
        taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
    }

    private long now() {
        return System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public void sendReport(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource) {
        Logger logger = Logger.getLogger();
        logger.d("Sending report through Google DataTransport: " + crashlyticsReportWithSessionId.getSessionId());
        this.transport.schedule(Event.ofUrgent(crashlyticsReportWithSessionId.getReport()), new b(this, taskCompletionSource, SystemClock.elapsedRealtime() - this.startTimeMs < ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, crashlyticsReportWithSessionId));
    }

    /* access modifiers changed from: private */
    public static void sleep(double d2) {
        try {
            Thread.sleep((long) d2);
        } catch (InterruptedException unused) {
        }
    }

    public TaskCompletionSource<CrashlyticsReportWithSessionId> enqueueReport(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, boolean z2) {
        synchronized (this.queue) {
            try {
                TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource = new TaskCompletionSource<>();
                if (z2) {
                    this.onDemandCounter.incrementRecordedOnDemandExceptions();
                    if (isQueueAvailable()) {
                        Logger logger = Logger.getLogger();
                        logger.d("Enqueueing report: " + crashlyticsReportWithSessionId.getSessionId());
                        Logger logger2 = Logger.getLogger();
                        logger2.d("Queue size: " + this.queue.size());
                        this.singleThreadExecutor.execute(new ReportRunnable(crashlyticsReportWithSessionId, taskCompletionSource));
                        Logger logger3 = Logger.getLogger();
                        logger3.d("Closing task for report: " + crashlyticsReportWithSessionId.getSessionId());
                        taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
                        return taskCompletionSource;
                    }
                    calcStep();
                    Logger logger4 = Logger.getLogger();
                    logger4.d("Dropping report due to queue being full: " + crashlyticsReportWithSessionId.getSessionId());
                    this.onDemandCounter.incrementDroppedOnDemandExceptions();
                    taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
                    return taskCompletionSource;
                }
                sendReport(crashlyticsReportWithSessionId, taskCompletionSource);
                return taskCompletionSource;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SuppressLint({"DiscouragedApi", "ThreadPoolCreation"})
    public void flushScheduledReportsIfAble() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new a(this, countDownLatch)).start();
        Utils.awaitUninterruptibly(countDownLatch, 2, TimeUnit.SECONDS);
    }

    @SuppressLint({"ThreadPoolCreation"})
    public ReportQueue(double d2, double d3, long j2, Transport<CrashlyticsReport> transport2, OnDemandCounter onDemandCounter2) {
        this.ratePerMinute = d2;
        this.base = d3;
        this.stepDurationMs = j2;
        this.transport = transport2;
        this.onDemandCounter = onDemandCounter2;
        this.startTimeMs = SystemClock.elapsedRealtime();
        int i3 = (int) d2;
        this.queueCapacity = i3;
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(i3);
        this.queue = arrayBlockingQueue;
        this.singleThreadExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, arrayBlockingQueue);
        this.step = 0;
        this.lastUpdatedMs = 0;
    }
}
