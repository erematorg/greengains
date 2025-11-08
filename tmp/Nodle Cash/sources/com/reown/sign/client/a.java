package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.client.SignInterface;
import kotlin.jvm.functions.Function1;
import org.koin.core.module.Module;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7463a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SignInterface.WalletDelegate f7464b;

    public /* synthetic */ a(SignInterface.WalletDelegate walletDelegate, int i3) {
        this.f7463a = i3;
        this.f7464b = walletDelegate;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7463a;
        SignInterface.WalletDelegate walletDelegate = this.f7464b;
        switch (i3) {
            case 0:
                return SignProtocol.setWalletDelegate$lambda$1(walletDelegate, (Module) obj);
            default:
                return SignProtocol.setWalletDelegate$lambda$2(walletDelegate, (Sign.Model.ConnectionState) obj);
        }
    }
}
