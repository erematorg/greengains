package com.reown.walletkit.client;

import com.reown.sign.client.Sign;
import com.reown.walletkit.client.WalletKit;
import kotlin.jvm.functions.Function2;

public final /* synthetic */ class b implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WalletKit.WalletDelegate f7640a;

    public /* synthetic */ b(WalletKit.WalletDelegate walletDelegate) {
        this.f7640a = walletDelegate;
    }

    public final Object invoke(Object obj, Object obj2) {
        return WalletKit$setWalletDelegate$signWalletDelegate$1._get_onSessionAuthenticate_$lambda$0(this.f7640a, (Sign.Model.SessionAuthenticate) obj, (Sign.Model.VerifyContext) obj2);
    }
}
