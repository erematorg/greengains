package com.reown.android.internal.common.di;

import com.reown.foundation.di.FoundationNetworkModuleKt;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.koin.core.scope.Scope;

public final /* synthetic */ class a implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7301a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7302b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7301a = i3;
        this.f7302b = obj;
    }

    public final Response intercept(Interceptor.Chain chain) {
        int i3 = this.f7301a;
        Object obj = this.f7302b;
        switch (i3) {
            case 0:
                return AppKitModuleKt.appKitModule$lambda$11$lambda$2$lambda$1((Scope) obj, chain);
            case 1:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$4$lambda$3((Scope) obj, chain);
            default:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$1$lambda$0((String) obj, chain);
        }
    }
}
