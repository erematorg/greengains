package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ApolloResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "it", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$2", f = "HttpNetworkTransport.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class HttpNetworkTransport$multipleResponses$2 extends SuspendLambda implements Function3<FlowCollector<? super ApolloResponse<D>>, Throwable, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;

    public HttpNetworkTransport$multipleResponses$2(Continuation<? super HttpNetworkTransport$multipleResponses$2> continuation) {
        super(3, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        throw HttpNetworkTransport.Companion.wrapThrowableIfNeeded((Throwable) this.L$0);
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super ApolloResponse<D>> flowCollector, @NotNull Throwable th, @Nullable Continuation<? super Unit> continuation) {
        HttpNetworkTransport$multipleResponses$2 httpNetworkTransport$multipleResponses$2 = new HttpNetworkTransport$multipleResponses$2(continuation);
        httpNetworkTransport$multipleResponses$2.L$0 = th;
        return httpNetworkTransport$multipleResponses$2.invokeSuspend(Unit.INSTANCE);
    }
}
