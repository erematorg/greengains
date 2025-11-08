package com.reown.android.internal.common.di;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.koin.core.scope.Scope;

public final /* synthetic */ class f implements Authenticator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f7309a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Scope f7310b;

    public /* synthetic */ f(Scope scope, String str) {
        this.f7309a = str;
        this.f7310b = scope;
    }

    public final Request authenticate(Route route, Response response) {
        return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$9$lambda$8(this.f7309a, this.f7310b, route, response);
    }
}
