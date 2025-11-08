package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;

final class y extends r {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f6846a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ab f6847b;

    public y(ab abVar, IBinder iBinder) {
        this.f6847b = abVar;
        this.f6846a = iBinder;
    }

    public final void b() {
        ac acVar = this.f6847b.f6812a;
        acVar.f6827o = (IInterface) acVar.f6822j.a(this.f6846a);
        ac.r(this.f6847b.f6812a);
        this.f6847b.f6812a.f6820h = false;
        for (Runnable run : this.f6847b.f6812a.f6817e) {
            run.run();
        }
        this.f6847b.f6812a.f6817e.clear();
    }
}
