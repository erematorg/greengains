package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.model.Pairing;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7354a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ PairingEngine f7355b;

    public /* synthetic */ a(PairingEngine pairingEngine, int i3) {
        this.f7354a = i3;
        this.f7355b = pairingEngine;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7354a;
        PairingEngine pairingEngine = this.f7355b;
        switch (i3) {
            case 0:
                return PairingEngine.sendBatchSubscribeForPairings$lambda$26(pairingEngine, (Throwable) obj);
            default:
                return PairingEngine.setRequestReceived$lambda$23(pairingEngine, (Pairing) obj);
        }
    }
}
