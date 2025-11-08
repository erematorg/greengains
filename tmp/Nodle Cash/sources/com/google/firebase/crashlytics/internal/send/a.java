package com.google.firebase.crashlytics.internal.send;

import java.util.concurrent.CountDownLatch;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ReportQueue f7106a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f7107b;

    public /* synthetic */ a(ReportQueue reportQueue, CountDownLatch countDownLatch) {
        this.f7106a = reportQueue;
        this.f7107b = countDownLatch;
    }

    public final void run() {
        this.f7106a.lambda$flushScheduledReportsIfAble$0(this.f7107b);
    }
}
