package com.apollographql.apollo3.network.ws;

import androidx.camera.view.f;
import java.util.Map;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.GraphQLWsProtocol$connectionInit$2", f = "GraphQLWsProtocol.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
public final class GraphQLWsProtocol$connectionInit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ GraphQLWsProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphQLWsProtocol$connectionInit$2(GraphQLWsProtocol graphQLWsProtocol, Continuation<? super GraphQLWsProtocol$connectionInit$2> continuation) {
        super(2, continuation);
        this.this$0 = graphQLWsProtocol;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new GraphQLWsProtocol$connectionInit$2(this.this$0, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            GraphQLWsProtocol graphQLWsProtocol = this.this$0;
            this.label = 1;
            obj = graphQLWsProtocol.receiveMessageMap(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Object obj2 = ((Map) obj).get("type");
        if (Intrinsics.areEqual(obj2, (Object) "connection_ack")) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(obj2, (Object) "ping")) {
            this.this$0.sendPong();
        } else {
            System.out.println(f.a(obj2, "unknown graphql-ws message while waiting for connection_ack: '"));
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((GraphQLWsProtocol$connectionInit$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
