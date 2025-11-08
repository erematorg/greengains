package com.reown.sign.client;

import com.reown.foundation.common.model.Topic;
import com.reown.sign.client.Sign;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.engine.domain.SignEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$getPendingSessionRequests$1", f = "SignProtocol.kt", i = {}, l = {463}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$getPendingSessionRequests$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Sign.Model.SessionRequest>>, Object> {
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$getPendingSessionRequests$1(SignProtocol signProtocol, String str, Continuation<? super SignProtocol$getPendingSessionRequests$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$topic = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$getPendingSessionRequests$1(this.this$0, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SignEngine access$getSignEngine$p = this.this$0.signEngine;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            Topic topic = new Topic(this.$topic);
            this.label = 1;
            obj = access$getSignEngine$p.getPendingSessionRequests(topic, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ClientMapperKt.mapToPendingSessionRequests((List) obj);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Sign.Model.SessionRequest>> continuation) {
        return ((SignProtocol$getPendingSessionRequests$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
