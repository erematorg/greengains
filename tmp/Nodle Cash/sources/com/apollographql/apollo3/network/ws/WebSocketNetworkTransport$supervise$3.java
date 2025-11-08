package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3", f = "WebSocketNetworkTransport.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
public final class WebSocketNetworkTransport$supervise$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<WsProtocol> $protocol;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$supervise$3(Ref.ObjectRef<WsProtocol> objectRef, Continuation<? super WebSocketNetworkTransport$supervise$3> continuation) {
        super(2, continuation);
        this.$protocol = objectRef;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WebSocketNetworkTransport$supervise$3(this.$protocol, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            T t2 = this.$protocol.element;
            Intrinsics.checkNotNull(t2);
            this.label = 1;
            if (((WsProtocol) t2).run(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WebSocketNetworkTransport$supervise$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
