package com.reown.foundation.network;

import com.reown.foundation.network.model.Relay;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 3 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt\n*L\n1#1,112:1\n51#2:113\n55#2:117\n72#3,3:114\n*S KotlinDebug\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n51#1:114,3\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, k = 1, mv = {2, 2, 0})
public final class BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1 implements Flow<Relay.Model.Event> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ BaseRelayClient this$0;

    public BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1(Flow flow, BaseRelayClient baseRelayClient) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = baseRelayClient;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector<WebSocket.Event>() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r8
                    com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1$2$1 r0 = (com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1$2$1 r0 = new com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1$2$1
                    r0.<init>(r6, r8)
                L_0x0018:
                    java.lang.Object r8 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x003d
                    if (r2 != r3) goto L_0x0035
                    java.lang.Object r6 = r0.L$5
                    kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                    java.lang.Object r6 = r0.L$3
                    com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1$2$1 r6 = (com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r6
                    java.lang.Object r6 = r0.L$1
                    com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1$2$1 r6 = (com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r6
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L_0x0094
                L_0x0035:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L_0x003d:
                    kotlin.ResultKt.throwOnFailure(r8)
                    kotlinx.coroutines.flow.FlowCollector r8 = r3
                    r2 = r7
                    com.tinder.scarlet.WebSocket$Event r2 = (com.tinder.scarlet.WebSocket.Event) r2
                    com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1 r6 = r2
                    com.reown.foundation.network.BaseRelayClient r6 = r6.this$0
                    com.reown.foundation.util.Logger r6 = r6.getLogger()
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    java.lang.String r5 = "Event: "
                    r4.<init>(r5)
                    r4.append(r2)
                    java.lang.String r4 = r4.toString()
                    r6.log((java.lang.String) r4)
                    com.reown.foundation.network.model.Relay$Model$Event r6 = com.reown.foundation.common.RelayMapperKt.toRelayEvent(r2)
                    java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                    r0.L$0 = r2
                    java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                    r0.L$1 = r2
                    java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                    r0.L$2 = r2
                    java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                    r0.L$3 = r2
                    java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                    r0.L$4 = r7
                    java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                    r0.L$5 = r7
                    r7 = 0
                    r0.I$0 = r7
                    r0.I$1 = r7
                    r0.label = r3
                    java.lang.Object r6 = r8.emit(r6, r0)
                    if (r6 != r1) goto L_0x0094
                    return r1
                L_0x0094:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.foundation.network.BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
