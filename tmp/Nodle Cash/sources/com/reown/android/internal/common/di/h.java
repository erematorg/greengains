package com.reown.android.internal.common.di;

import com.reown.android.relay.ConnectionType;
import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class h implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7317a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ConnectionType f7318b;

    public /* synthetic */ h(ConnectionType connectionType, int i3) {
        this.f7317a = i3;
        this.f7318b = connectionType;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i3 = this.f7317a;
        Scope scope = (Scope) obj;
        ParametersHolder parametersHolder = (ParametersHolder) obj2;
        ConnectionType connectionType = this.f7318b;
        switch (i3) {
            case 0:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$15(connectionType, scope, parametersHolder);
            default:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$17(connectionType, scope, parametersHolder);
        }
    }
}
