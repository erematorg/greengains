package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.i;

final class as extends aw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6751a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f6752b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f6753c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6754d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ax f6755e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public as(ax axVar, TaskCompletionSource taskCompletionSource, String str, long j2, long j3, TaskCompletionSource taskCompletionSource2) {
        super(axVar, taskCompletionSource);
        this.f6755e = axVar;
        this.f6751a = str;
        this.f6752b = j2;
        this.f6753c = j3;
        this.f6754d = taskCompletionSource2;
    }

    public final void b() {
        if (ax.g(this.f6755e)) {
            a(new StandardIntegrityException(-2, (Throwable) null));
            return;
        }
        try {
            ax axVar = this.f6755e;
            ((i) axVar.f6761a.e()).c(ax.a(axVar, this.f6751a, this.f6752b, this.f6753c), new au(this.f6755e, this.f6754d));
        } catch (RemoteException e3) {
            this.f6755e.f6762b.b(e3, "requestExpressIntegrityToken(%s, %s)", this.f6751a, Long.valueOf(this.f6752b));
            this.f6754d.trySetException(new StandardIntegrityException(-100, e3));
        }
    }
}
