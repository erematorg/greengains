package com.reown.android.pairing.client;

import kotlin.jvm.functions.Function0;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7350a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ PairingProtocol f7351b;

    public /* synthetic */ a(PairingProtocol pairingProtocol, int i3) {
        this.f7350a = i3;
        this.f7351b = pairingProtocol;
    }

    public final Object invoke() {
        int i3 = this.f7350a;
        PairingProtocol pairingProtocol = this.f7351b;
        switch (i3) {
            case 0:
                return PairingProtocol.logger_delegate$lambda$0(pairingProtocol);
            case 1:
                return PairingProtocol.relayClient_delegate$lambda$1(pairingProtocol);
            default:
                return PairingProtocol.insertEventUseCase_delegate$lambda$2(pairingProtocol);
        }
    }
}
