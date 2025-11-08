package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

public final /* synthetic */ class bc implements StandardIntegrityManager.StandardIntegrityTokenProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ bd f6773a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f6774b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f6775c;

    public /* synthetic */ bc(bd bdVar, long j2, long j3) {
        this.f6773a = bdVar;
        this.f6774b = j2;
        this.f6775c = j3;
    }

    public final Task request(StandardIntegrityManager.StandardIntegrityTokenRequest standardIntegrityTokenRequest) {
        return this.f6773a.a(this.f6774b, this.f6775c, standardIntegrityTokenRequest);
    }
}
