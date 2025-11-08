package com.reown.android.push.notifications;

import com.google.firebase.messaging.RemoteMessage;
import com.reown.android.Core;
import com.reown.android.push.notifications.PushMessagingService$decryptNotification$1;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7357a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ PushMessagingService f7358b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ RemoteMessage f7359c;

    public /* synthetic */ a(PushMessagingService pushMessagingService, RemoteMessage remoteMessage, int i3) {
        this.f7357a = i3;
        this.f7358b = pushMessagingService;
        this.f7359c = remoteMessage;
    }

    public final Object invoke(Object obj) {
        switch (this.f7357a) {
            case 0:
                return PushMessagingService$decryptNotification$1.AnonymousClass1.invokeSuspend$lambda$0(this.f7358b, this.f7359c, (Core.Model.Message) obj);
            default:
                return PushMessagingService$decryptNotification$1.AnonymousClass1.invokeSuspend$lambda$1(this.f7358b, this.f7359c, (Throwable) obj);
        }
    }
}
