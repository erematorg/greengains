package com.nodle.wallet.zksync;

import java.util.function.Function;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7234a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7235b;

    public /* synthetic */ c(Function1 function1, int i3) {
        this.f7234a = i3;
        this.f7235b = function1;
    }

    public final Object apply(Object obj) {
        int i3 = this.f7234a;
        Function1 function1 = this.f7235b;
        switch (i3) {
            case 0:
                return ZkWallet$getBridgeTransactionDelay$2.invokeSuspend$lambda$1((b) function1, obj);
            case 1:
                return ZkWallet$getRewardsSequence$2.invokeSuspend$lambda$1((b) function1, obj);
            case 2:
                return ZkWallet$queryProposalByHash$2.invokeSuspend$lambda$1((b) function1, obj);
            case 3:
                return ZkWallet.executeWithPaymaster$lambda$1((a) function1, obj);
            default:
                return ZkWallet.buildSignature$lambda$4((a) function1, obj);
        }
    }
}
