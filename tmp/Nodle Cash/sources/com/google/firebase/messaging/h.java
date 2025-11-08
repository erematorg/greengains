package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.RequestDeduplicator;
import com.google.firebase.messaging.Store;

public final /* synthetic */ class h implements SuccessContinuation, RequestDeduplicator.GetTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FirebaseMessaging f7139a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7140b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Store.Token f7141c;

    public /* synthetic */ h(FirebaseMessaging firebaseMessaging, String str, Store.Token token) {
        this.f7139a = firebaseMessaging;
        this.f7140b = str;
        this.f7141c = token;
    }

    public Task start() {
        return this.f7139a.lambda$blockingGetToken$14(this.f7140b, this.f7141c);
    }

    public Task then(Object obj) {
        return this.f7139a.lambda$blockingGetToken$13(this.f7140b, this.f7141c, (String) obj);
    }
}
