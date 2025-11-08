package com.reown.foundation.network;

import kotlin.jvm.functions.Function0;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7437a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ BaseRelayClient f7438b;

    public /* synthetic */ a(BaseRelayClient baseRelayClient, int i3) {
        this.f7437a = i3;
        this.f7438b = baseRelayClient;
    }

    public final Object invoke() {
        int i3 = this.f7437a;
        BaseRelayClient baseRelayClient = this.f7438b;
        switch (i3) {
            case 0:
                return BaseRelayClient.subscriptionRequest_delegate$lambda$4(baseRelayClient);
            default:
                return BaseRelayClient.eventsFlow_delegate$lambda$2(baseRelayClient);
        }
    }
}
