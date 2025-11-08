package com.reown.android.pairing.client;

import com.reown.android.Core;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.engine.model.EngineDO;
import com.reown.android.pairing.model.mapper.PairingMapperKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "event", "Lcom/reown/android/pairing/engine/model/EngineDO;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.client.PairingProtocol$setDelegate$1", f = "PairingProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PairingProtocol$setDelegate$1 extends SuspendLambda implements Function2<EngineDO, Continuation<? super Unit>, Object> {
    final /* synthetic */ PairingInterface.Delegate $delegate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingProtocol$setDelegate$1(PairingInterface.Delegate delegate, Continuation<? super PairingProtocol$setDelegate$1> continuation) {
        super(2, continuation);
        this.$delegate = delegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PairingProtocol$setDelegate$1 pairingProtocol$setDelegate$1 = new PairingProtocol$setDelegate$1(this.$delegate, continuation);
        pairingProtocol$setDelegate$1.L$0 = obj;
        return pairingProtocol$setDelegate$1;
    }

    public final Object invoke(EngineDO engineDO, Continuation<? super Unit> continuation) {
        return ((PairingProtocol$setDelegate$1) create(engineDO, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        EngineDO engineDO = (EngineDO) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (engineDO instanceof EngineDO.PairingDelete) {
                this.$delegate.onPairingDelete(Intrinsics.checkNotNullParameter((EngineDO.PairingDelete) engineDO, "<this>"));
            } else if (engineDO instanceof EngineDO.PairingExpire) {
                this.$delegate.onPairingExpired(new Core.Model.ExpiredPairing(PairingMapperKt.toCore(((EngineDO.PairingExpire) engineDO).getPairing())));
            } else if (engineDO instanceof EngineDO.PairingState) {
                this.$delegate.onPairingState(new Core.Model.PairingState(((EngineDO.PairingState) engineDO).isPairingState()));
            } else {
                throw new NoWhenBranchMatchedException();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
