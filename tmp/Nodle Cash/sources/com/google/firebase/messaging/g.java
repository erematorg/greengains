package com.google.firebase.messaging;

import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;

public final /* synthetic */ class g implements OnSuccessListener, FirebaseInstanceIdInternal.NewTokenListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7137a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ FirebaseMessaging f7138b;

    public /* synthetic */ g(FirebaseMessaging firebaseMessaging, int i3) {
        this.f7137a = i3;
        this.f7138b = firebaseMessaging;
    }

    public void onNewToken(String str) {
        this.f7138b.lambda$new$1(str);
    }

    public void onSuccess(Object obj) {
        int i3 = this.f7137a;
        FirebaseMessaging firebaseMessaging = this.f7138b;
        switch (i3) {
            case 0:
                firebaseMessaging.lambda$new$3((TopicsSubscriber) obj);
                return;
            case 2:
                firebaseMessaging.lambda$setNotificationDelegationEnabled$6((Void) obj);
                return;
            default:
                firebaseMessaging.lambda$handleProxiedNotificationData$5((CloudMessage) obj);
                return;
        }
    }
}
