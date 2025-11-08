package com.nodle.wallet.zksync;

import kotlin.jvm.functions.Function1;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.methods.response.EthCall;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7232a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function f7233b;

    public /* synthetic */ b(Function function, int i3) {
        this.f7232a = i3;
        this.f7233b = function;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7232a;
        Function function = this.f7233b;
        EthCall ethCall = (EthCall) obj;
        switch (i3) {
            case 0:
                return ZkWallet$getBridgeTransactionDelay$2.invokeSuspend$lambda$0(function, ethCall);
            case 1:
                return ZkWallet$getRewardsSequence$2.invokeSuspend$lambda$0(function, ethCall);
            default:
                return ZkWallet$queryProposalByHash$2.invokeSuspend$lambda$0(function, ethCall);
        }
    }
}
