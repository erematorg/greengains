package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor$intercept$1", f = "AutoPersistedQueryInterceptor.kt", i = {0, 1}, l = {38, 49, 50, 56}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
public final class AutoPersistedQueryInterceptor$intercept$1 extends SuspendLambda implements Function2<FlowCollector<? super ApolloResponse<D>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ApolloInterceptorChain $chain;
    final /* synthetic */ boolean $isMutation;
    final /* synthetic */ ApolloRequest<D> $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AutoPersistedQueryInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoPersistedQueryInterceptor$intercept$1(ApolloInterceptorChain apolloInterceptorChain, ApolloRequest<D> apolloRequest, AutoPersistedQueryInterceptor autoPersistedQueryInterceptor, boolean z2, Continuation<? super AutoPersistedQueryInterceptor$intercept$1> continuation) {
        super(2, continuation);
        this.$chain = apolloInterceptorChain;
        this.$request = apolloRequest;
        this.this$0 = autoPersistedQueryInterceptor;
        this.$isMutation = z2;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        AutoPersistedQueryInterceptor$intercept$1 autoPersistedQueryInterceptor$intercept$1 = new AutoPersistedQueryInterceptor$intercept$1(this.$chain, this.$request, this.this$0, this.$isMutation, continuation);
        autoPersistedQueryInterceptor$intercept$1.L$0 = obj;
        return autoPersistedQueryInterceptor$intercept$1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ab A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r6) goto L_0x002b
            if (r1 == r5) goto L_0x0023
            if (r1 == r4) goto L_0x001e
            if (r1 != r3) goto L_0x0016
            goto L_0x001e
        L_0x0016:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c7
        L_0x0023:
            java.lang.Object r1 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0098
        L_0x002b:
            java.lang.Object r1 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0050
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            com.apollographql.apollo3.interceptor.ApolloInterceptorChain r1 = r10.$chain
            com.apollographql.apollo3.api.ApolloRequest<D> r7 = r10.$request
            kotlinx.coroutines.flow.Flow r1 = r1.proceed(r7)
            r10.L$0 = r11
            r10.label = r6
            java.lang.Object r1 = kotlinx.coroutines.flow.FlowKt.single(r1, r10)
            if (r1 != r0) goto L_0x004d
            return r0
        L_0x004d:
            r9 = r1
            r1 = r11
            r11 = r9
        L_0x0050:
            com.apollographql.apollo3.api.ApolloResponse r11 = (com.apollographql.apollo3.api.ApolloResponse) r11
            com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor r7 = r10.this$0
            java.util.List<com.apollographql.apollo3.api.Error> r8 = r11.errors
            boolean r7 = r7.isPersistedQueryNotFound(r8)
            if (r7 == 0) goto L_0x00ac
            com.apollographql.apollo3.api.ApolloRequest<D> r11 = r10.$request
            com.apollographql.apollo3.api.ApolloRequest$Builder r11 = r11.newBuilder()
            boolean r3 = r10.$isMutation
            if (r3 == 0) goto L_0x0069
            com.apollographql.apollo3.api.http.HttpMethod r3 = com.apollographql.apollo3.api.http.HttpMethod.Post
            goto L_0x006f
        L_0x0069:
            com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor r3 = r10.this$0
            com.apollographql.apollo3.api.http.HttpMethod r3 = r3.httpMethodForDocumentQueries
        L_0x006f:
            com.apollographql.apollo3.api.ApolloRequest$Builder r11 = r11.httpMethod((com.apollographql.apollo3.api.http.HttpMethod) r3)
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            com.apollographql.apollo3.api.ApolloRequest$Builder r11 = r11.sendDocument((java.lang.Boolean) r3)
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            com.apollographql.apollo3.api.ApolloRequest$Builder r11 = r11.sendApqExtensions((java.lang.Boolean) r3)
            com.apollographql.apollo3.api.ApolloRequest r11 = r11.build()
            com.apollographql.apollo3.interceptor.ApolloInterceptorChain r3 = r10.$chain
            kotlinx.coroutines.flow.Flow r11 = r3.proceed(r11)
            r10.L$0 = r1
            r10.label = r5
            java.lang.Object r11 = kotlinx.coroutines.flow.FlowKt.single(r11, r10)
            if (r11 != r0) goto L_0x0098
            return r0
        L_0x0098:
            com.apollographql.apollo3.api.ApolloResponse r11 = (com.apollographql.apollo3.api.ApolloResponse) r11
            com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor r3 = r10.this$0
            r5 = 0
            com.apollographql.apollo3.api.ApolloResponse r11 = r3.withAutoPersistedQueryInfo(r11, r5)
            r10.L$0 = r2
            r10.label = r4
            java.lang.Object r10 = r1.emit(r11, r10)
            if (r10 != r0) goto L_0x00c7
            return r0
        L_0x00ac:
            com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor r4 = r10.this$0
            java.util.List<com.apollographql.apollo3.api.Error> r5 = r11.errors
            boolean r4 = r4.isPersistedQueryNotSupported(r5)
            if (r4 != 0) goto L_0x00ca
            com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor r4 = r10.this$0
            com.apollographql.apollo3.api.ApolloResponse r11 = r4.withAutoPersistedQueryInfo(r11, r6)
            r10.L$0 = r2
            r10.label = r3
            java.lang.Object r10 = r1.emit(r11, r10)
            if (r10 != r0) goto L_0x00c7
            return r0
        L_0x00c7:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00ca:
            com.apollographql.apollo3.exception.AutoPersistedQueriesNotSupported r10 = new com.apollographql.apollo3.exception.AutoPersistedQueriesNotSupported
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor$intercept$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super ApolloResponse<D>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((AutoPersistedQueryInterceptor$intercept$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
