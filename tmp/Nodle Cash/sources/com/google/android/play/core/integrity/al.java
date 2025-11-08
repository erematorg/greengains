package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.StandardIntegrityManager;

final class al implements StandardIntegrityManager {

    /* renamed from: a  reason: collision with root package name */
    private final ax f6740a;

    /* renamed from: b  reason: collision with root package name */
    private final bd f6741b;

    public al(ax axVar, bd bdVar) {
        this.f6740a = axVar;
        this.f6741b = bdVar;
    }

    public final /* synthetic */ Task a(StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest, Long l2) throws Exception {
        return Tasks.forResult(new bc(this.f6741b, prepareIntegrityTokenRequest.a(), l2.longValue()));
    }

    public final Task<StandardIntegrityManager.StandardIntegrityTokenProvider> prepareIntegrityToken(StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest) {
        return this.f6740a.d(prepareIntegrityTokenRequest.a()).onSuccessTask(new ak(this, prepareIntegrityTokenRequest));
    }
}
