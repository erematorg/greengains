package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Uploader f6579a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ TransportContext f6580b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f6581c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Runnable f6582d;

    public /* synthetic */ a(Uploader uploader, TransportContext transportContext, int i3, Runnable runnable) {
        this.f6579a = uploader;
        this.f6580b = transportContext;
        this.f6581c = i3;
        this.f6582d = runnable;
    }

    public final void run() {
        this.f6579a.lambda$upload$1(this.f6580b, this.f6581c, this.f6582d);
    }
}
