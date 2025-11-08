package com.reown.android.internal.common.json_rpc.domain.relay;

import java.util.Map;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7327a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RelayJsonRpcInteractor f7328b;

    public /* synthetic */ a(RelayJsonRpcInteractor relayJsonRpcInteractor, int i3) {
        this.f7327a = i3;
        this.f7328b = relayJsonRpcInteractor;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7327a;
        RelayJsonRpcInteractor relayJsonRpcInteractor = this.f7328b;
        switch (i3) {
            case 0:
                return RelayJsonRpcInteractor.respondWithSuccess$lambda$26(relayJsonRpcInteractor, (Throwable) obj);
            default:
                return RelayJsonRpcInteractor.subscriptions$lambda$0(relayJsonRpcInteractor, (Map) obj);
        }
    }
}
