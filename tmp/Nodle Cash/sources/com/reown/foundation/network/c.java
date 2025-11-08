package com.reown.foundation.network;

import com.reown.foundation.network.model.Relay;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Relay.Model.IrnParams f7447a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7448b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7449c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Long f7450d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ BaseRelayClient f7451e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Function1 f7452f;

    public /* synthetic */ c(Relay.Model.IrnParams irnParams, String str, String str2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        this.f7447a = irnParams;
        this.f7448b = str;
        this.f7449c = str2;
        this.f7450d = l2;
        this.f7451e = baseRelayClient;
        this.f7452f = function1;
    }

    public final Object invoke() {
        return BaseRelayClient.publish$lambda$10(this.f7447a, this.f7448b, this.f7449c, this.f7450d, this.f7451e, this.f7452f);
    }
}
