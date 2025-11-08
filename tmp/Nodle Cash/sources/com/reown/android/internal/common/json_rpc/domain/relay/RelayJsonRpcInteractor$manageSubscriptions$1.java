package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

@SourceDebugExtension({"SMAP\nRelayJsonRpcInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor$manageSubscriptions$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,558:1\n49#2:559\n51#2:563\n46#3:560\n51#3:562\n105#4:561\n*S KotlinDebug\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor$manageSubscriptions$1\n*L\n446#1:559\n446#1:563\n446#1:560\n446#1:562\n446#1:561\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1", f = "RelayJsonRpcInteractor.kt", i = {}, l = {452}, m = "invokeSuspend", n = {}, s = {})
public final class RelayJsonRpcInteractor$manageSubscriptions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$manageSubscriptions$1(RelayJsonRpcInteractor relayJsonRpcInteractor, Continuation<? super RelayJsonRpcInteractor$manageSubscriptions$1> continuation) {
        super(2, continuation);
        this.this$0 = relayJsonRpcInteractor;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RelayJsonRpcInteractor$manageSubscriptions$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1 relayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1 = new RelayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1(this.this$0.relay.getSubscriptionRequest(), this.this$0);
            final RelayJsonRpcInteractor relayJsonRpcInteractor = this.this$0;
            AnonymousClass2 r5 = new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:15:0x0037  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(com.reown.android.internal.common.json_rpc.domain.relay.Subscription r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1
                        if (r0 == 0) goto L_0x0013
                        r0 = r6
                        com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1 r0 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L_0x0013
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L_0x0018
                    L_0x0013:
                        com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1 r0 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1
                        r0.<init>(r4, r6)
                    L_0x0018:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x0037
                        if (r2 != r3) goto L_0x002f
                        java.lang.Object r5 = r0.L$0
                        com.reown.android.internal.common.json_rpc.domain.relay.Subscription r5 = (com.reown.android.internal.common.json_rpc.domain.relay.Subscription) r5
                        kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Exception -> 0x002d }
                        goto L_0x006c
                    L_0x002d:
                        r5 = move-exception
                        goto L_0x0055
                    L_0x002f:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L_0x0037:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.String r6 = r5.getDecryptedMessage()
                        int r6 = r6.length()
                        if (r6 <= 0) goto L_0x006c
                        com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r6 = r1     // Catch:{ Exception -> 0x002d }
                        java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x002d }
                        r0.L$0 = r2     // Catch:{ Exception -> 0x002d }
                        r0.label = r3     // Catch:{ Exception -> 0x002d }
                        java.lang.Object r4 = r6.manageSubscriptions(r5, r0)     // Catch:{ Exception -> 0x002d }
                        if (r4 != r1) goto L_0x006c
                        return r1
                    L_0x0055:
                        com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r4 = r1
                        java.lang.String r5 = kotlin.ExceptionsKt.stackTraceToString(r5)
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder
                        java.lang.String r0 = "ManSub: "
                        r6.<init>(r0)
                        r6.append(r5)
                        java.lang.String r5 = r6.toString()
                        r4.handleError(r5)
                    L_0x006c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1.AnonymousClass2.emit(com.reown.android.internal.common.json_rpc.domain.relay.Subscription, kotlin.coroutines.Continuation):java.lang.Object");
                }
            };
            this.label = 1;
            if (relayJsonRpcInteractor$manageSubscriptions$1$invokeSuspend$$inlined$map$1.collect(r5, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RelayJsonRpcInteractor$manageSubscriptions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
