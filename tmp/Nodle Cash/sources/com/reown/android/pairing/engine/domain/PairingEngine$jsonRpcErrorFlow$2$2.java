package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.exception.Invalid;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Ttl;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$jsonRpcErrorFlow$2$2", f = "PairingEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$jsonRpcErrorFlow$2$2 extends SuspendLambda implements Function2<WCRequest, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$jsonRpcErrorFlow$2$2(PairingEngine pairingEngine, Continuation<? super PairingEngine$jsonRpcErrorFlow$2$2> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PairingEngine$jsonRpcErrorFlow$2$2 pairingEngine$jsonRpcErrorFlow$2$2 = new PairingEngine$jsonRpcErrorFlow$2$2(this.this$0, continuation);
        pairingEngine$jsonRpcErrorFlow$2$2.L$0 = obj;
        return pairingEngine$jsonRpcErrorFlow$2$2;
    }

    public final Object invoke(WCRequest wCRequest, Continuation<? super Unit> continuation) {
        return ((PairingEngine$jsonRpcErrorFlow$2$2) create(wCRequest, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WCRequest wCRequest = (WCRequest) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            RelayJsonRpcInteractorInterface.respondWithError$default(this.this$0.jsonRpcInteractor, wCRequest, new Invalid.MethodUnsupported(wCRequest.getMethod()), new IrnParams(Tags.UNSUPPORTED_METHOD, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(wCRequest.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null), (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
