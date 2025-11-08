package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.foundation.util.Logger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$isNotExpired$1$1", f = "PairingEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$isNotExpired$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Pairing $this_isNotExpired;
    int label;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$isNotExpired$1$1(PairingEngine pairingEngine, Pairing pairing, Continuation<? super PairingEngine$isNotExpired$1$1> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
        this.$this_isNotExpired = pairing;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$isNotExpired$1$1(this.this$0, this.$this_isNotExpired, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                RelayJsonRpcInteractorInterface.unsubscribe$default(this.this$0.jsonRpcInteractor, this.$this_isNotExpired.getTopic(), (Function0) null, (Function1) null, 6, (Object) null);
                this.this$0.pairingRepository.deletePairing(this.$this_isNotExpired.getTopic());
                this.this$0.metadataRepository.deleteMetaData(this.$this_isNotExpired.getTopic());
                this.this$0.crypto.removeKeys(this.$this_isNotExpired.getTopic().getValue());
            } catch (Exception e3) {
                Logger access$getLogger$p = this.this$0.logger;
                access$getLogger$p.error("Error when deleting pairing: " + e3);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PairingEngine$isNotExpired$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
