package com.adjust.sdk.scheduler;

import com.adjust.sdk.AdjustFactory;
import com.adjust.sdk.ILogger;
import com.adjust.sdk.Util;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerOnce {
    /* access modifiers changed from: private */
    public Runnable command;
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    /* access modifiers changed from: private */
    public String name;
    private FutureScheduler scheduler;
    /* access modifiers changed from: private */
    public ScheduledFuture waitingTask;

    public class a implements Runnable {
        public a() {
        }

        public final void run() {
            TimerOnce.this.logger.verbose("%s fired", TimerOnce.this.name);
            TimerOnce.this.command.run();
            ScheduledFuture unused = TimerOnce.this.waitingTask = null;
        }
    }

    public TimerOnce(Runnable runnable, String str) {
        this.name = str;
        this.scheduler = new SingleThreadFutureScheduler(str, true);
        this.command = runnable;
    }

    public void cancel() {
        cancel(false);
    }

    public long getFireIn() {
        ScheduledFuture scheduledFuture = this.waitingTask;
        if (scheduledFuture == null) {
            return 0;
        }
        return scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
    }

    public void startIn(long j2) {
        cancel(false);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, Util.SecondsDisplayFormat.format(((double) j2) / 1000.0d));
        this.waitingTask = this.scheduler.scheduleFuture(new a(), j2);
    }

    public void teardown() {
        cancel(true);
        FutureScheduler futureScheduler = this.scheduler;
        if (futureScheduler != null) {
            futureScheduler.teardown();
        }
        this.scheduler = null;
    }

    private void cancel(boolean z2) {
        ScheduledFuture scheduledFuture = this.waitingTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(z2);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }
}
