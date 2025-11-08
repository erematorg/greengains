package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.i;

final class ar extends aw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f6748a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6749b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ax f6750c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ar(ax axVar, TaskCompletionSource taskCompletionSource, long j2, TaskCompletionSource taskCompletionSource2) {
        super(axVar, taskCompletionSource);
        this.f6750c = axVar;
        this.f6748a = j2;
        this.f6749b = taskCompletionSource2;
    }

    public final void b() {
        if (ax.g(this.f6750c)) {
            a(new StandardIntegrityException(-2, (Throwable) null));
            return;
        }
        try {
            ax axVar = this.f6750c;
            ((i) axVar.f6761a.e()).d(ax.b(axVar, this.f6748a), new av(this.f6750c, this.f6749b));
        } catch (RemoteException e3) {
            this.f6750c.f6762b.b(e3, "warmUpIntegrityToken(%s)", Long.valueOf(this.f6748a));
            this.f6749b.trySetException(new StandardIntegrityException(-100, e3));
        }
    }
}
