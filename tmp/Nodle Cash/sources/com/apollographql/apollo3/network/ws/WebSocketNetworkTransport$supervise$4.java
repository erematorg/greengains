package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4", f = "WebSocketNetworkTransport.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {})
public final class WebSocketNetworkTransport$supervise$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<Job> $connectionJob;
    final /* synthetic */ Ref.ObjectRef<Job> $idleJob;
    final /* synthetic */ Ref.ObjectRef<WsProtocol> $protocol;
    int label;
    final /* synthetic */ WebSocketNetworkTransport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$supervise$4(WebSocketNetworkTransport webSocketNetworkTransport, Ref.ObjectRef<WsProtocol> objectRef, Ref.ObjectRef<Job> objectRef2, Ref.ObjectRef<Job> objectRef3, Continuation<? super WebSocketNetworkTransport$supervise$4> continuation) {
        super(2, continuation);
        this.this$0 = webSocketNetworkTransport;
        this.$protocol = objectRef;
        this.$connectionJob = objectRef2;
        this.$idleJob = objectRef3;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WebSocketNetworkTransport$supervise$4(this.this$0, this.$protocol, this.$connectionJob, this.$idleJob, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long access$getIdleTimeoutMillis$p = this.this$0.idleTimeoutMillis;
            this.label = 1;
            if (DelayKt.delay(access$getIdleTimeoutMillis$p, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        WebSocketNetworkTransport.supervise$closeProtocol(this.$protocol, this.$connectionJob, this.$idleJob);
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WebSocketNetworkTransport$supervise$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
