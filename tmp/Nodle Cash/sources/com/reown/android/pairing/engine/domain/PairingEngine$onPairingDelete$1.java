package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.pairing.model.PairingParams;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine", f = "PairingEngine.kt", i = {0, 0, 0}, l = {387}, m = "onPairingDelete", n = {"request", "params", "irnParams"}, s = {"L$0", "L$1", "L$2"})
public final class PairingEngine$onPairingDelete$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$onPairingDelete$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$onPairingDelete$1> continuation) {
        super(continuation);
        this.this$0 = pairingEngine;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onPairingDelete((WCRequest) null, (PairingParams.DeleteParams) null, this);
    }
}
