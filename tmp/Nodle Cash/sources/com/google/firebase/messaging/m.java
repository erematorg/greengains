package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.gms.tasks.OnSuccessListener;

public final /* synthetic */ class m implements OnSuccessListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7149a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ boolean f7150b;

    public /* synthetic */ m(Context context, boolean z2) {
        this.f7149a = context;
        this.f7150b = z2;
    }

    public final void onSuccess(Object obj) {
        ProxyNotificationPreferences.setProxyRetentionPreferences(this.f7149a, this.f7150b);
    }
}
