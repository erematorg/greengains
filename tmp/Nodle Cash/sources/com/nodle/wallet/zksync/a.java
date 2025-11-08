package com.nodle.wallet.zksync;

import io.zksync.crypto.eip712.Eip712Domain;
import io.zksync.transaction.type.Transaction712;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7229a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ZkWallet f7230b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Transaction712 f7231c;

    public /* synthetic */ a(ZkWallet zkWallet, Transaction712 transaction712, int i3) {
        this.f7229a = i3;
        this.f7230b = zkWallet;
        this.f7231c = transaction712;
    }

    public final Object invoke(Object obj) {
        switch (this.f7229a) {
            case 0:
                return ZkWallet.executeWithPaymaster$lambda$0(this.f7230b, this.f7231c, (Eip712Domain) obj);
            default:
                return ZkWallet.buildSignature$lambda$3(this.f7230b, this.f7231c, (Eip712Domain) obj);
        }
    }
}
