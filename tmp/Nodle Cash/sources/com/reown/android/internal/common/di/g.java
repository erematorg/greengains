package com.reown.android.internal.common.di;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor;
import com.reown.android.relay.ConnectionType;
import com.reown.android.relay.NetworkClientTimeout;
import kotlin.Result;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.koin.core.module.Module;

public final /* synthetic */ class g implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7311a = 1;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7312b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7313c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7314d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7315e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Object f7316f;

    public /* synthetic */ g(RelayJsonRpcInteractor relayJsonRpcInteractor, JsonRpcResponse jsonRpcResponse, String str, Function0 function0, Function1 function1) {
        this.f7313c = relayJsonRpcInteractor;
        this.f7314d = jsonRpcResponse;
        this.f7312b = str;
        this.f7315e = function0;
        this.f7316f = function1;
    }

    public final Object invoke(Object obj) {
        switch (this.f7311a) {
            case 0:
                String str = (String) this.f7314d;
                String str2 = (String) this.f7315e;
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20((NetworkClientTimeout) this.f7313c, this.f7312b, str, str2, (ConnectionType) this.f7316f, (Module) obj);
            default:
                String str3 = this.f7312b;
                Function0 function0 = (Function0) this.f7315e;
                return RelayJsonRpcInteractor.publishJsonRpcResponse$lambda$12((RelayJsonRpcInteractor) this.f7313c, (JsonRpcResponse) this.f7314d, str3, function0, (Function1) this.f7316f, (Result) obj);
        }
    }

    public /* synthetic */ g(String str, ConnectionType connectionType, String str2, NetworkClientTimeout networkClientTimeout, String str3) {
        this.f7313c = networkClientTimeout;
        this.f7312b = str;
        this.f7314d = str2;
        this.f7315e = str3;
        this.f7316f = connectionType;
    }
}
