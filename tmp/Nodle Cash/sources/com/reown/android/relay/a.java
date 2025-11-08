package com.reown.android.relay;

import kotlin.jvm.functions.Function0;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7360a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RelayClient f7361b;

    public /* synthetic */ a(RelayClient relayClient, int i3) {
        this.f7360a = i3;
        this.f7361b = relayClient;
    }

    public final Object invoke() {
        int i3 = this.f7360a;
        RelayClient relayClient = this.f7361b;
        switch (i3) {
            case 0:
                return RelayClient.manualConnection_delegate$lambda$0(relayClient);
            case 1:
                return RelayClient.defaultConnection_delegate$lambda$1(relayClient);
            case 2:
                return RelayClient.networkState_delegate$lambda$2(relayClient);
            default:
                return RelayClient.isNetworkAvailable_delegate$lambda$3(relayClient);
        }
    }
}
