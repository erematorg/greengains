package com.google.firebase.crashlytics.internal.send;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;

public final /* synthetic */ class b implements TransportScheduleCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ReportQueue f7108a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f7109b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f7110c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ CrashlyticsReportWithSessionId f7111d;

    public /* synthetic */ b(ReportQueue reportQueue, TaskCompletionSource taskCompletionSource, boolean z2, CrashlyticsReportWithSessionId crashlyticsReportWithSessionId) {
        this.f7108a = reportQueue;
        this.f7109b = taskCompletionSource;
        this.f7110c = z2;
        this.f7111d = crashlyticsReportWithSessionId;
    }

    public final void onSchedule(Exception exc) {
        this.f7108a.lambda$sendReport$1(this.f7109b, this.f7110c, this.f7111d, exc);
    }
}
