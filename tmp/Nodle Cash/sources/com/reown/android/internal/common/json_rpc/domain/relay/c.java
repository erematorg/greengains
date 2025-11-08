package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.Result;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RelayJsonRpcInteractor f7331a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f7332b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7333c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function0 f7334d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Function1 f7335e;

    public /* synthetic */ c(RelayJsonRpcInteractor relayJsonRpcInteractor, long j2, String str, Function0 function0, Function1 function1) {
        this.f7331a = relayJsonRpcInteractor;
        this.f7332b = j2;
        this.f7333c = str;
        this.f7334d = function0;
        this.f7335e = function1;
    }

    public final Object invoke(Object obj) {
        String str = this.f7333c;
        Function0 function0 = this.f7334d;
        return RelayJsonRpcInteractor.approveSession$lambda$6(this.f7331a, this.f7332b, str, function0, this.f7335e, (Result) obj);
    }
}
