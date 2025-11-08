package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,108:1\n47#2,5:109\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1 implements Flow<Subscription> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    public RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1(Flow flow, RelayJsonRpcInteractor relayJsonRpcInteractor) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = relayJsonRpcInteractor;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final RelayJsonRpcInteractor relayJsonRpcInteractor = this.this$0;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r23, kotlin.coroutines.Continuation r24) {
                /*
                    r22 = this;
                    r0 = r22
                    r1 = r24
                    boolean r2 = r1 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r2 == 0) goto L_0x0017
                    r2 = r1
                    com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1$2$1 r2 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                    int r3 = r2.label
                    r4 = -2147483648(0xffffffff80000000, float:-0.0)
                    r5 = r3 & r4
                    if (r5 == 0) goto L_0x0017
                    int r3 = r3 - r4
                    r2.label = r3
                    goto L_0x001c
                L_0x0017:
                    com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1$2$1 r2 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1$2$1
                    r2.<init>(r0, r1)
                L_0x001c:
                    java.lang.Object r1 = r2.result
                    java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r4 = r2.label
                    r5 = 1
                    if (r4 == 0) goto L_0x003e
                    if (r4 != r5) goto L_0x0036
                    java.lang.Object r0 = r2.L$3
                    kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
                    java.lang.Object r0 = r2.L$1
                    com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1$2$1 r0 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    kotlin.ResultKt.throwOnFailure(r1)
                    goto L_0x00ba
                L_0x0036:
                    java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                    java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                    r0.<init>(r1)
                    throw r0
                L_0x003e:
                    kotlin.ResultKt.throwOnFailure(r1)
                    kotlinx.coroutines.flow.FlowCollector r1 = r3
                    r4 = r23
                    com.reown.foundation.network.model.Relay$Model$Call$Subscription$Request r4 = (com.reown.foundation.network.model.Relay.Model.Call.Subscription.Request) r4
                    int r6 = r4.getTag()
                    r7 = 4050(0xfd2, float:5.675E-42)
                    if (r6 != r7) goto L_0x006d
                    com.reown.android.internal.common.json_rpc.domain.relay.Subscription r0 = new com.reown.android.internal.common.json_rpc.domain.relay.Subscription
                    kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
                    java.lang.String r9 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, "<this>")
                    java.lang.String r10 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, "<this>")
                    com.reown.foundation.common.model.Topic r11 = new com.reown.foundation.common.model.Topic
                    java.lang.String r6 = ""
                    r11.<init>(r6)
                    r12 = 0
                    java.lang.String r14 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, "<this>")
                    r8 = r0
                    r8.<init>(r9, r10, r11, r12, r14)
                    goto L_0x0096
                L_0x006d:
                    com.reown.foundation.common.model.Topic r6 = new com.reown.foundation.common.model.Topic
                    java.lang.String r7 = r4.getSubscriptionTopic()
                    r6.<init>(r7)
                    com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r7 = r2
                    r7.storePushRequestsIfEnabled(r4, r6)
                    com.reown.android.internal.common.json_rpc.domain.relay.Subscription r7 = new com.reown.android.internal.common.json_rpc.domain.relay.Subscription
                    com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r0 = r2
                    java.lang.String r16 = r0.decryptMessage(r6, r4)
                    java.lang.String r17 = r4.getMessage()
                    long r19 = r4.getPublishedAt()
                    java.lang.String r21 = r4.getAttestation()
                    r15 = r7
                    r18 = r6
                    r15.<init>(r16, r17, r18, r19, r21)
                    r0 = r7
                L_0x0096:
                    java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)
                    r2.L$0 = r4
                    java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
                    r2.L$1 = r4
                    java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)
                    r2.L$2 = r4
                    java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                    r2.L$3 = r4
                    r4 = 0
                    r2.I$0 = r4
                    r2.label = r5
                    java.lang.Object r0 = r1.emit(r0, r2)
                    if (r0 != r3) goto L_0x00ba
                    return r3
                L_0x00ba:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
