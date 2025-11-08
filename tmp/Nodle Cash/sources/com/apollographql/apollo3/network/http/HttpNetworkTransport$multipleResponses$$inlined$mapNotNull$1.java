package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,112:1\n51#2,5:113\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1 implements Flow<ApolloResponse<D>> {
    final /* synthetic */ CustomScalarAdapters $customScalarAdapters$inlined;
    final /* synthetic */ Ref.ObjectRef $jsonMerger$inlined;
    final /* synthetic */ Operation $operation$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1(Flow flow, Operation operation, CustomScalarAdapters customScalarAdapters, Ref.ObjectRef objectRef) {
        this.$this_unsafeTransform$inlined = flow;
        this.$operation$inlined = operation;
        this.$customScalarAdapters$inlined = customScalarAdapters;
        this.$jsonMerger$inlined = objectRef;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final Operation operation = this.$operation$inlined;
        final CustomScalarAdapters customScalarAdapters = this.$customScalarAdapters$inlined;
        final Ref.ObjectRef objectRef = this.$jsonMerger$inlined;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r9
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1$2$1 r0 = (com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1$2$1 r0 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1$2$1
                    r0.<init>(r7, r9)
                L_0x0018:
                    java.lang.Object r9 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L_0x0133
                L_0x002a:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r9)
                    kotlinx.coroutines.flow.FlowCollector r9 = r5
                    okio.BufferedSource r8 = (okio.BufferedSource) r8
                    com.apollographql.apollo3.api.Operation r2 = r2
                    boolean r2 = r2 instanceof com.apollographql.apollo3.api.Subscription
                    r4 = 0
                    if (r2 == 0) goto L_0x00c7
                    okio.BufferedSource r2 = r8.peek()
                    com.apollographql.apollo3.api.json.JsonReader r2 = com.apollographql.apollo3.api.json.JsonReaders.jsonReader((okio.BufferedSource) r2)
                    r2.beginObject()     // Catch:{ all -> 0x0054 }
                    boolean r5 = r2.hasNext()     // Catch:{ all -> 0x0054 }
                    if (r5 != 0) goto L_0x0056
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r5 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.EMPTY     // Catch:{ all -> 0x0054 }
                    goto L_0x0072
                L_0x0054:
                    r5 = move-exception
                    goto L_0x0075
                L_0x0056:
                    java.lang.String r5 = r2.nextName()     // Catch:{ all -> 0x0054 }
                    java.lang.String r6 = "payload"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x0054 }
                    if (r5 == 0) goto L_0x0070
                    com.apollographql.apollo3.api.json.JsonReader$Token r5 = r2.peek()     // Catch:{ all -> 0x0054 }
                    com.apollographql.apollo3.api.json.JsonReader$Token r6 = com.apollographql.apollo3.api.json.JsonReader.Token.NULL     // Catch:{ all -> 0x0054 }
                    if (r5 != r6) goto L_0x006d
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r5 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.OTHER     // Catch:{ all -> 0x0054 }
                    goto L_0x0072
                L_0x006d:
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r5 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.PAYLOAD     // Catch:{ all -> 0x0054 }
                    goto L_0x0072
                L_0x0070:
                    com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r5 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.OTHER     // Catch:{ all -> 0x0054 }
                L_0x0072:
                    r6 = r5
                    r5 = r4
                    goto L_0x0076
                L_0x0075:
                    r6 = r4
                L_0x0076:
                    if (r2 == 0) goto L_0x0084
                    r2.close()     // Catch:{ all -> 0x007c }
                    goto L_0x0084
                L_0x007c:
                    r2 = move-exception
                    if (r5 != 0) goto L_0x0081
                    r5 = r2
                    goto L_0x0084
                L_0x0081:
                    kotlin.ExceptionsKt.addSuppressed(r5, r2)
                L_0x0084:
                    if (r5 != 0) goto L_0x00c6
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                    int[] r2 = com.apollographql.apollo3.network.http.HttpNetworkTransport.WhenMappings.$EnumSwitchMapping$0
                    int r5 = r6.ordinal()
                    r2 = r2[r5]
                    if (r2 == r3) goto L_0x0128
                    r4 = 2
                    if (r2 == r4) goto L_0x00b3
                    r9 = 3
                    if (r2 == r9) goto L_0x009f
                    kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                    r7.<init>()
                    throw r7
                L_0x009f:
                    com.apollographql.apollo3.api.json.JsonReader r8 = com.apollographql.apollo3.api.json.JsonReaders.jsonReader((okio.BufferedSource) r8)
                    java.lang.Object r8 = com.apollographql.apollo3.api.json.JsonReaders.readAny(r8)
                    com.apollographql.apollo3.exception.SubscriptionOperationException r9 = new com.apollographql.apollo3.exception.SubscriptionOperationException
                    com.apollographql.apollo3.api.Operation r7 = r2
                    java.lang.String r7 = r7.name()
                    r9.<init>(r7, r8)
                    throw r9
                L_0x00b3:
                    com.apollographql.apollo3.api.json.JsonReader r8 = com.apollographql.apollo3.api.json.JsonReaders.jsonReader((okio.BufferedSource) r8)
                    r8.beginObject()
                    r8.nextName()
                    com.apollographql.apollo3.api.Operation r2 = r2
                    com.apollographql.apollo3.api.CustomScalarAdapters r7 = r3
                    com.apollographql.apollo3.api.ApolloResponse r4 = com.apollographql.apollo3.api.Operations.parseJsonResponse(r2, (com.apollographql.apollo3.api.json.JsonReader) r8, (com.apollographql.apollo3.api.CustomScalarAdapters) r7)
                    goto L_0x0128
                L_0x00c6:
                    throw r5
                L_0x00c7:
                    kotlin.jvm.internal.Ref$ObjectRef r2 = r4
                    T r5 = r2.element
                    if (r5 != 0) goto L_0x00d4
                    com.apollographql.apollo3.internal.DeferredJsonMerger r5 = new com.apollographql.apollo3.internal.DeferredJsonMerger
                    r5.<init>()
                    r2.element = r5
                L_0x00d4:
                    kotlin.jvm.internal.Ref$ObjectRef r2 = r4
                    T r2 = r2.element
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    com.apollographql.apollo3.internal.DeferredJsonMerger r2 = (com.apollographql.apollo3.internal.DeferredJsonMerger) r2
                    java.util.Map r8 = r2.merge((okio.BufferedSource) r8)
                    kotlin.jvm.internal.Ref$ObjectRef r2 = r4
                    T r2 = r2.element
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    com.apollographql.apollo3.internal.DeferredJsonMerger r2 = (com.apollographql.apollo3.internal.DeferredJsonMerger) r2
                    java.util.Set r2 = r2.getMergedFragmentIds()
                    kotlin.jvm.internal.Ref$ObjectRef r5 = r4
                    T r5 = r5.element
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                    com.apollographql.apollo3.internal.DeferredJsonMerger r5 = (com.apollographql.apollo3.internal.DeferredJsonMerger) r5
                    boolean r5 = r5.getHasNext()
                    r5 = r5 ^ r3
                    kotlin.jvm.internal.Ref$ObjectRef r6 = r4
                    T r6 = r6.element
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                    com.apollographql.apollo3.internal.DeferredJsonMerger r6 = (com.apollographql.apollo3.internal.DeferredJsonMerger) r6
                    boolean r6 = r6.isEmptyPayload()
                    if (r6 == 0) goto L_0x010c
                    goto L_0x0128
                L_0x010c:
                    com.apollographql.apollo3.api.Operation r4 = r2
                    com.apollographql.apollo3.api.json.JsonReader r8 = com.apollographql.apollo3.api.json.JsonReaders.jsonReader((java.util.Map<java.lang.String, ? extends java.lang.Object>) r8)
                    com.apollographql.apollo3.api.CustomScalarAdapters r7 = r3
                    com.apollographql.apollo3.api.CustomScalarAdapters r7 = com.apollographql.apollo3.api.AdapterContext.withDeferredFragmentIds(r7, r2)
                    com.apollographql.apollo3.api.ApolloResponse r7 = com.apollographql.apollo3.api.Operations.parseJsonResponse(r4, (com.apollographql.apollo3.api.json.JsonReader) r8, (com.apollographql.apollo3.api.CustomScalarAdapters) r7)
                    com.apollographql.apollo3.api.ApolloResponse$Builder r7 = r7.newBuilder()
                    com.apollographql.apollo3.api.ApolloResponse$Builder r7 = r7.isLast(r5)
                    com.apollographql.apollo3.api.ApolloResponse r4 = r7.build()
                L_0x0128:
                    if (r4 == 0) goto L_0x0133
                    r0.label = r3
                    java.lang.Object r7 = r9.emit(r4, r0)
                    if (r7 != r1) goto L_0x0133
                    return r1
                L_0x0133:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
