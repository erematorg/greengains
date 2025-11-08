package com.google.android.play.core.integrity;

import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ad;
import com.google.android.play.integrity.internal.n;
import com.google.android.play.integrity.internal.r;

final class ab extends r {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ byte[] f6721a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Long f6722b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6723c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ IntegrityTokenRequest f6724d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ad f6725e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ab(ad adVar, TaskCompletionSource taskCompletionSource, byte[] bArr, Long l2, Parcelable parcelable, TaskCompletionSource taskCompletionSource2, IntegrityTokenRequest integrityTokenRequest) {
        super(taskCompletionSource);
        this.f6725e = adVar;
        this.f6721a = bArr;
        this.f6722b = l2;
        this.f6723c = taskCompletionSource2;
        this.f6724d = integrityTokenRequest;
    }

    public final void a(Exception exc) {
        if (exc instanceof ad) {
            super.a(new IntegrityServiceException(-9, exc));
        } else {
            super.a(exc);
        }
    }

    public final void b() {
        try {
            ((n) this.f6725e.f6729a.e()).c(ad.a(this.f6725e, this.f6721a, this.f6722b, (Parcelable) null), new ac(this.f6725e, this.f6723c));
        } catch (RemoteException e3) {
            this.f6725e.f6730b.b(e3, "requestIntegrityToken(%s)", this.f6724d);
            this.f6723c.trySetException(new IntegrityServiceException(-100, e3));
        }
    }
}
