package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class b implements SynchronizationGuard.CriticalSection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6583a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Uploader f6584b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ TransportContext f6585c;

    public /* synthetic */ b(Uploader uploader, TransportContext transportContext, int i3) {
        this.f6583a = i3;
        this.f6584b = uploader;
        this.f6585c = transportContext;
    }

    public final Object execute() {
        switch (this.f6583a) {
            case 0:
                return this.f6584b.lambda$logAndUpdateState$2(this.f6585c);
            default:
                return this.f6584b.lambda$logAndUpdateState$3(this.f6585c);
        }
    }
}
