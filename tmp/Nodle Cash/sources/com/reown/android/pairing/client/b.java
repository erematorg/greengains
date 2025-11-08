package com.reown.android.pairing.client;

import com.reown.android.Core;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7352a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Core.Listeners.PairingPing f7353b;

    public /* synthetic */ b(Core.Listeners.PairingPing pairingPing, int i3) {
        this.f7352a = i3;
        this.f7353b = pairingPing;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7352a;
        Core.Listeners.PairingPing pairingPing = this.f7353b;
        switch (i3) {
            case 0:
                return PairingProtocol.ping$lambda$9(pairingPing, (String) obj);
            default:
                return PairingProtocol.ping$lambda$10(pairingPing, (Throwable) obj);
        }
    }
}
