package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.internal.DeferredJsonMerger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,112:1\n51#2,5:113\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class WebSocketNetworkTransport$execute$$inlined$map$1 implements Flow<ApolloResponse<D>> {
    final /* synthetic */ DeferredJsonMerger $deferredJsonMerger$inlined;
    final /* synthetic */ ApolloRequest $request$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public WebSocketNetworkTransport$execute$$inlined$map$1(Flow flow, ApolloRequest apolloRequest, DeferredJsonMerger deferredJsonMerger) {
        this.$this_unsafeTransform$inlined = flow;
        this.$request$inlined = apolloRequest;
        this.$deferredJsonMerger$inlined = deferredJsonMerger;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final ApolloRequest apolloRequest = this.$request$inlined;
        final DeferredJsonMerger deferredJsonMerger = this.$deferredJsonMerger$inlined;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r7) {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r7
                    com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2$1 r0 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2$1 r0 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2$1
                    r0.<init>(r5, r7)
                L_0x0018:
                    java.lang.Object r7 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L_0x00b5
                L_0x002a:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r6)
                    throw r5
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r7)
                    kotlinx.coroutines.flow.FlowCollector r7 = r4
                    com.apollographql.apollo3.network.ws.internal.Event r6 = (com.apollographql.apollo3.network.ws.internal.Event) r6
                    boolean r2 = r6 instanceof com.apollographql.apollo3.network.ws.internal.OperationResponse
                    if (r2 == 0) goto L_0x00b8
                    com.apollographql.apollo3.network.ws.internal.OperationResponse r6 = (com.apollographql.apollo3.network.ws.internal.OperationResponse) r6
                    java.util.Map r6 = r6.getPayload()
                    com.apollographql.apollo3.api.ApolloRequest r2 = r2
                    com.apollographql.apollo3.api.ExecutionContext r2 = r2.getExecutionContext()
                    com.apollographql.apollo3.api.CustomScalarAdapters$Key r4 = com.apollographql.apollo3.api.CustomScalarAdapters.Key
                    com.apollographql.apollo3.api.ExecutionContext$Element r2 = r2.get(r4)
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    com.apollographql.apollo3.api.CustomScalarAdapters r2 = (com.apollographql.apollo3.api.CustomScalarAdapters) r2
                    boolean r4 = com.apollographql.apollo3.internal.DeferredJsonMergerKt.isDeferred(r6)
                    if (r4 == 0) goto L_0x006f
                    com.apollographql.apollo3.internal.DeferredJsonMerger r4 = r3
                    java.util.Map r6 = r4.merge((java.util.Map<java.lang.String, ? extends java.lang.Object>) r6)
                    com.apollographql.apollo3.internal.DeferredJsonMerger r4 = r3
                    java.util.Set r4 = r4.getMergedFragmentIds()
                    com.apollographql.apollo3.api.CustomScalarAdapters r2 = com.apollographql.apollo3.api.AdapterContext.withDeferredFragmentIds(r2, r4)
                    kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r2)
                    goto L_0x0073
                L_0x006f:
                    kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r2)
                L_0x0073:
                    java.lang.Object r2 = r6.component1()
                    java.util.Map r2 = (java.util.Map) r2
                    java.lang.Object r6 = r6.component2()
                    com.apollographql.apollo3.api.CustomScalarAdapters r6 = (com.apollographql.apollo3.api.CustomScalarAdapters) r6
                    com.apollographql.apollo3.api.ApolloRequest r4 = r2
                    com.apollographql.apollo3.api.Operation r4 = r4.getOperation()
                    com.apollographql.apollo3.api.json.JsonReader r2 = com.apollographql.apollo3.api.json.JsonReaders.jsonReader((java.util.Map<java.lang.String, ? extends java.lang.Object>) r2)
                    com.apollographql.apollo3.api.ApolloResponse r6 = com.apollographql.apollo3.api.Operations.parseJsonResponse(r4, (com.apollographql.apollo3.api.json.JsonReader) r2, (com.apollographql.apollo3.api.CustomScalarAdapters) r6)
                    com.apollographql.apollo3.api.ApolloResponse$Builder r6 = r6.newBuilder()
                    com.apollographql.apollo3.api.ApolloRequest r2 = r2
                    java.util.UUID r2 = r2.getRequestUuid()
                    com.apollographql.apollo3.api.ApolloResponse$Builder r6 = r6.requestUuid(r2)
                    com.apollographql.apollo3.api.ApolloResponse r6 = r6.build()
                    com.apollographql.apollo3.internal.DeferredJsonMerger r2 = r3
                    boolean r2 = r2.getHasNext()
                    if (r2 != 0) goto L_0x00ac
                    com.apollographql.apollo3.internal.DeferredJsonMerger r5 = r3
                    r5.reset()
                L_0x00ac:
                    r0.label = r3
                    java.lang.Object r5 = r7.emit(r6, r0)
                    if (r5 != r1) goto L_0x00b5
                    return r1
                L_0x00b5:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                L_0x00b8:
                    boolean r7 = r6 instanceof com.apollographql.apollo3.network.ws.internal.OperationError
                    if (r7 != 0) goto L_0x0111
                    boolean r7 = r6 instanceof com.apollographql.apollo3.network.ws.internal.NetworkError
                    if (r7 != 0) goto L_0x00ed
                    boolean r5 = r6 instanceof com.apollographql.apollo3.network.ws.internal.ConnectionReEstablished
                    if (r5 == 0) goto L_0x00c6
                    r5 = r3
                    goto L_0x00c8
                L_0x00c6:
                    boolean r5 = r6 instanceof com.apollographql.apollo3.network.ws.internal.OperationComplete
                L_0x00c8:
                    if (r5 == 0) goto L_0x00cb
                    goto L_0x00cd
                L_0x00cb:
                    boolean r3 = r6 instanceof com.apollographql.apollo3.network.ws.internal.GeneralError
                L_0x00cd:
                    if (r3 == 0) goto L_0x00e7
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    java.lang.String r0 = "Unexpected event "
                    r7.<init>(r0)
                    r7.append(r6)
                    java.lang.String r6 = r7.toString()
                    java.lang.String r6 = r6.toString()
                    r5.<init>(r6)
                    throw r5
                L_0x00e7:
                    kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                    r5.<init>()
                    throw r5
                L_0x00ed:
                    com.apollographql.apollo3.exception.ApolloNetworkException r7 = new com.apollographql.apollo3.exception.ApolloNetworkException
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r1 = "Network error while executing "
                    r0.<init>(r1)
                    com.apollographql.apollo3.api.ApolloRequest r5 = r2
                    com.apollographql.apollo3.api.Operation r5 = r5.getOperation()
                    java.lang.String r5 = r5.name()
                    r0.append(r5)
                    java.lang.String r5 = r0.toString()
                    com.apollographql.apollo3.network.ws.internal.NetworkError r6 = (com.apollographql.apollo3.network.ws.internal.NetworkError) r6
                    java.lang.Throwable r6 = r6.getCause()
                    r7.<init>(r5, r6)
                    throw r7
                L_0x0111:
                    com.apollographql.apollo3.exception.SubscriptionOperationException r7 = new com.apollographql.apollo3.exception.SubscriptionOperationException
                    com.apollographql.apollo3.api.ApolloRequest r5 = r2
                    com.apollographql.apollo3.api.Operation r5 = r5.getOperation()
                    java.lang.String r5 = r5.name()
                    com.apollographql.apollo3.network.ws.internal.OperationError r6 = (com.apollographql.apollo3.network.ws.internal.OperationError) r6
                    java.util.Map r6 = r6.getPayload()
                    r7.<init>(r5, r6)
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
