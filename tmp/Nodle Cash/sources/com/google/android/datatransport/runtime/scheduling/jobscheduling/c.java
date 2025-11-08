package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class c implements SynchronizationGuard.CriticalSection, Deferred.DeferredHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f6586a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6587b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6588c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f6589d;

    public /* synthetic */ c(Uploader uploader, Iterable iterable, TransportContext transportContext, long j2) {
        this.f6587b = uploader;
        this.f6588c = iterable;
        this.f6589d = transportContext;
        this.f6586a = j2;
    }

    public Object execute() {
        return ((Uploader) this.f6587b).lambda$logAndUpdateState$4((Iterable) this.f6588c, (TransportContext) this.f6589d, this.f6586a);
    }

    public void handle(Provider provider) {
        ((CrashlyticsNativeComponent) provider.get()).prepareNativeSession((String) this.f6587b, (String) this.f6588c, this.f6586a, (StaticSessionData) this.f6589d);
    }

    public /* synthetic */ c(String str, String str2, long j2, StaticSessionData staticSessionData) {
        this.f6587b = str;
        this.f6588c = str2;
        this.f6586a = j2;
        this.f6589d = staticSessionData;
    }
}
