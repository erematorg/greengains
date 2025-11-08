package com.apollographql.apollo3;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "throwable", "", "<anonymous parameter 1>", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1", f = "ApolloClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 extends SuspendLambda implements Function3<Throwable, Long, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Function1<Throwable, Boolean> $reconnectWhen;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1(Function1<? super Throwable, Boolean> function1, Continuation<? super ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1> continuation) {
        super(3, continuation);
        this.$reconnectWhen = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((Throwable) obj, ((Number) obj2).longValue(), (Continuation<? super Boolean>) (Continuation) obj3);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$reconnectWhen.invoke((Throwable) this.L$0);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Nullable
    public final Object invoke(@NotNull Throwable th, long j2, @Nullable Continuation<? super Boolean> continuation) {
        ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 = new ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1(this.$reconnectWhen, continuation);
        apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1.L$0 = th;
        return apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1.invokeSuspend(Unit.INSTANCE);
    }
}
