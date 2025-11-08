package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

public final /* synthetic */ class ak implements SuccessContinuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ al f6738a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ StandardIntegrityManager.PrepareIntegrityTokenRequest f6739b;

    public /* synthetic */ ak(al alVar, StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest) {
        this.f6738a = alVar;
        this.f6739b = prepareIntegrityTokenRequest;
    }

    public final Task then(Object obj) {
        return this.f6738a.a(this.f6739b, (Long) obj);
    }
}
