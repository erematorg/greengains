package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.http.HttpRequest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nHttpNetworkTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpNetworkTransport.kt\ncom/apollographql/apollo3/network/http/HttpNetworkTransport$execute$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,316:1\n47#2:317\n49#2:321\n50#3:318\n55#3:320\n106#4:319\n*S KotlinDebug\n*F\n+ 1 HttpNetworkTransport.kt\ncom/apollographql/apollo3/network/http/HttpNetworkTransport$execute$1\n*L\n87#1:317\n87#1:321\n87#1:318\n87#1:320\n87#1:319\n*E\n"})
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1", f = "HttpNetworkTransport.kt", i = {0, 0}, l = {65, 85, 90}, m = "invokeSuspend", n = {"$this$flow", "millisStart"}, s = {"L$0", "J$0"})
public final class HttpNetworkTransport$execute$1 extends SuspendLambda implements Function2<FlowCollector<? super ApolloResponse<D>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CustomScalarAdapters $customScalarAdapters;
    final /* synthetic */ HttpRequest $httpRequest;
    final /* synthetic */ ApolloRequest<D> $request;
    long J$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HttpNetworkTransport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpNetworkTransport$execute$1(HttpNetworkTransport httpNetworkTransport, HttpRequest httpRequest, ApolloRequest<D> apolloRequest, CustomScalarAdapters customScalarAdapters, Continuation<? super HttpNetworkTransport$execute$1> continuation) {
        super(2, continuation);
        this.this$0 = httpNetworkTransport;
        this.$httpRequest = httpRequest;
        this.$request = apolloRequest;
        this.$customScalarAdapters = customScalarAdapters;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        HttpNetworkTransport$execute$1 httpNetworkTransport$execute$1 = new HttpNetworkTransport$execute$1(this.this$0, this.$httpRequest, this.$request, this.$customScalarAdapters, continuation);
        httpNetworkTransport$execute$1.L$0 = obj;
        return httpNetworkTransport$execute$1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r4) goto L_0x001f
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00bc
        L_0x001f:
            long r4 = r12.J$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r4
            goto L_0x005e
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            r1 = r13
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            long r5 = com.apollographql.apollo3.mpp.UtilsKt.currentTimeMillis()
            com.apollographql.apollo3.network.http.DefaultHttpInterceptorChain r13 = new com.apollographql.apollo3.network.http.DefaultHttpInterceptorChain
            com.apollographql.apollo3.network.http.HttpNetworkTransport r7 = r12.this$0
            java.util.List r7 = r7.getInterceptors()
            java.util.Collection r7 = (java.util.Collection) r7
            com.apollographql.apollo3.network.http.HttpNetworkTransport r8 = r12.this$0
            com.apollographql.apollo3.network.http.HttpNetworkTransport$EngineInterceptor r8 = r8.engineInterceptor
            java.util.List r7 = kotlin.collections.CollectionsKt.plus(r7, r8)
            r8 = 0
            r13.<init>(r7, r8)
            com.apollographql.apollo3.api.http.HttpRequest r7 = r12.$httpRequest
            r12.L$0 = r1
            r12.J$0 = r5
            r12.label = r4
            java.lang.Object r13 = r13.proceed(r7, r12)
            if (r13 != r0) goto L_0x005d
            return r0
        L_0x005d:
            r9 = r5
        L_0x005e:
            com.apollographql.apollo3.api.http.HttpResponse r13 = (com.apollographql.apollo3.api.http.HttpResponse) r13
            int r4 = r13.getStatusCode()
            r5 = 200(0xc8, float:2.8E-43)
            r11 = 0
            if (r5 > r4) goto L_0x00bf
            r5 = 300(0x12c, float:4.2E-43)
            if (r4 >= r5) goto L_0x00bf
            boolean r4 = com.apollographql.apollo3.internal.MultipartKt.isMultipart(r13)
            if (r4 == 0) goto L_0x0097
            com.apollographql.apollo3.network.http.HttpNetworkTransport r2 = r12.this$0
            com.apollographql.apollo3.api.ApolloRequest<D> r4 = r12.$request
            com.apollographql.apollo3.api.Operation r4 = r4.getOperation()
            com.apollographql.apollo3.api.CustomScalarAdapters r5 = r12.$customScalarAdapters
            kotlinx.coroutines.flow.Flow r5 = r2.multipleResponses(r4, r5, r13)
            com.apollographql.apollo3.network.http.HttpNetworkTransport r6 = r12.this$0
            com.apollographql.apollo3.api.ApolloRequest<D> r7 = r12.$request
            com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1 r2 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1
            r4 = r2
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9)
            r12.L$0 = r11
            r12.label = r3
            java.lang.Object r12 = kotlinx.coroutines.flow.FlowKt.emitAll(r1, r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r12)
            if (r12 != r0) goto L_0x00bc
            return r0
        L_0x0097:
            com.apollographql.apollo3.network.http.HttpNetworkTransport r4 = r12.this$0
            com.apollographql.apollo3.api.ApolloRequest<D> r3 = r12.$request
            com.apollographql.apollo3.api.Operation r3 = r3.getOperation()
            com.apollographql.apollo3.api.CustomScalarAdapters r5 = r12.$customScalarAdapters
            com.apollographql.apollo3.api.ApolloResponse r5 = r4.singleResponse(r3, r5, r13)
            com.apollographql.apollo3.api.ApolloRequest<D> r3 = r12.$request
            java.util.UUID r6 = r3.getRequestUuid()
            r7 = r13
            r8 = r9
            com.apollographql.apollo3.api.ApolloResponse r13 = r4.withHttpInfo(r5, r6, r7, r8)
            r12.L$0 = r11
            r12.label = r2
            java.lang.Object r12 = r1.emit(r13, r12)
            if (r12 != r0) goto L_0x00bc
            return r0
        L_0x00bc:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00bf:
            com.apollographql.apollo3.network.http.HttpNetworkTransport r12 = r12.this$0
            boolean r12 = r12.getExposeErrorBody()
            if (r12 != 0) goto L_0x00d2
            okio.BufferedSource r12 = r13.getBody()
            if (r12 == 0) goto L_0x00d0
            r12.close()
        L_0x00d0:
            r3 = r11
            goto L_0x00d7
        L_0x00d2:
            okio.BufferedSource r11 = r13.getBody()
            goto L_0x00d0
        L_0x00d7:
            com.apollographql.apollo3.exception.ApolloHttpException r12 = new com.apollographql.apollo3.exception.ApolloHttpException
            int r1 = r13.getStatusCode()
            java.util.List r2 = r13.getHeaders()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = "Http request failed with status code `"
            r0.<init>(r4)
            int r13 = r13.getStatusCode()
            r0.append(r13)
            r13 = 96
            r0.append(r13)
            java.lang.String r4 = r0.toString()
            r6 = 16
            r7 = 0
            r5 = 0
            r0 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super ApolloResponse<D>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((HttpNetworkTransport$execute$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
