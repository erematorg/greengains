package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.jvm.functions.Function1;

public final /* synthetic */ class e implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7340a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7341b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ RelayJsonRpcInteractor f7342c;

    public /* synthetic */ e(Function1 function1, RelayJsonRpcInteractor relayJsonRpcInteractor, int i3) {
        this.f7340a = i3;
        this.f7341b = function1;
        this.f7342c = relayJsonRpcInteractor;
    }

    public final Object invoke(Object obj) {
        Throwable th = (Throwable) obj;
        switch (this.f7340a) {
            case 0:
                return RelayJsonRpcInteractor.respondWithError$lambda$28(this.f7341b, this.f7342c, th);
            default:
                return RelayJsonRpcInteractor.respondWithError$lambda$30(this.f7341b, this.f7342c, th);
        }
    }
}
