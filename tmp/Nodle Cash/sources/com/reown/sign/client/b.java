package com.reown.sign.client;

import com.reown.sign.client.SignInterface;
import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class b implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SignInterface.WalletDelegate f7465a;

    public /* synthetic */ b(SignInterface.WalletDelegate walletDelegate) {
        this.f7465a = walletDelegate;
    }

    public final Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(SignProtocol.setWalletDelegate$lambda$1$lambda$0(this.f7465a, (Scope) obj, (ParametersHolder) obj2));
    }
}
