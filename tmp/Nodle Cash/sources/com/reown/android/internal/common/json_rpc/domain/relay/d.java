package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.Result;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7336a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function0 f7337b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ RelayJsonRpcInteractor f7338c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function1 f7339d;

    public /* synthetic */ d(Function0 function0, RelayJsonRpcInteractor relayJsonRpcInteractor, Function1 function1, int i3) {
        this.f7336a = i3;
        this.f7337b = function0;
        this.f7338c = relayJsonRpcInteractor;
        this.f7339d = function1;
    }

    public final Object invoke(Object obj) {
        Result result = (Result) obj;
        switch (this.f7336a) {
            case 0:
                return RelayJsonRpcInteractor.publishJsonRpcRequest$lambda$9(this.f7337b, this.f7338c, this.f7339d, result);
            default:
                return RelayJsonRpcInteractor.proposeSession$lambda$3(this.f7337b, this.f7338c, this.f7339d, result);
        }
    }
}
