package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnPingUseCase$invoke$2", f = "OnPingUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class OnPingUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WCRequest $request;
    int label;
    final /* synthetic */ OnPingUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnPingUseCase$invoke$2(WCRequest wCRequest, OnPingUseCase onPingUseCase, Continuation<? super OnPingUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onPingUseCase;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnPingUseCase$invoke$2(this.$request, this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            IrnParams irnParams = new IrnParams(Tags.SESSION_PING_RESPONSE, new Ttl(Time.getThirtySeconds()), Boxing.boxLong(this.$request.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            Logger access$getLogger$p = this.this$0.logger;
            Topic topic = this.$request.getTopic();
            access$getLogger$p.log("Session ping received on topic: " + topic);
            RelayJsonRpcInteractorInterface.respondWithSuccess$default(this.this$0.jsonRpcInteractor, this.$request, irnParams, (EnvelopeType) null, (Participants) null, 12, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnPingUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
