package com.reown.android.internal.common.di;

import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class e implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7306a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7307b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7308c;

    public /* synthetic */ e(String str, String str2, int i3) {
        this.f7306a = i3;
        this.f7307b = str;
        this.f7308c = str2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Scope scope = (Scope) obj;
        ParametersHolder parametersHolder = (ParametersHolder) obj2;
        switch (this.f7306a) {
            case 0:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7$lambda$2(this.f7307b, this.f7308c, scope, parametersHolder);
            default:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$0(this.f7307b, this.f7308c, scope, parametersHolder);
        }
    }
}
