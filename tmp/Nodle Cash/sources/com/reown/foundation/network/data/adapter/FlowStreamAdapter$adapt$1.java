package com.reown.foundation.network.data.adapter;

import com.tinder.scarlet.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;

@SourceDebugExtension({"SMAP\nFlowStreamAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowStreamAdapter.kt\ncom/reown/foundation/network/data/adapter/FlowStreamAdapter$adapt$1\n+ 2 Channel.kt\nkotlinx/coroutines/reactive/ChannelKt\n+ 3 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n*L\n1#1,28:1\n46#2:29\n192#3:30\n169#3,6:31\n193#3,2:37\n179#3:39\n175#3,3:40\n*S KotlinDebug\n*F\n+ 1 FlowStreamAdapter.kt\ncom/reown/foundation/network/data/adapter/FlowStreamAdapter$adapt$1\n*L\n14#1:29\n14#1:30\n14#1:31,6\n14#1:37,2\n14#1:39\n14#1:40,3\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.data.adapter.FlowStreamAdapter$adapt$1", f = "FlowStreamAdapter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {37, 15}, m = "invokeSuspend", n = {"$this$flow", "$this$collect$iv", "$this$consumeEach$iv$iv", "$this$consume$iv$iv$iv", "$this$consume$iv$iv", "$i$f$collect", "$i$f$consumeEach", "$i$f$consume", "$i$a$-consume-ChannelsKt__Channels_commonKt$consumeEach$4$iv$iv", "$this$flow", "$this$collect$iv", "$this$consumeEach$iv$iv", "$this$consume$iv$iv$iv", "$this$consume$iv$iv", "e$iv$iv", "it", "$i$f$collect", "$i$f$consumeEach", "$i$f$consume", "$i$a$-consume-ChannelsKt__Channels_commonKt$consumeEach$4$iv$iv", "$i$a$-collect-FlowStreamAdapter$adapt$1$1"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "I$0", "I$1", "I$2", "I$3", "I$4"})
public final class FlowStreamAdapter$adapt$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Stream<T> $stream;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowStreamAdapter$adapt$1(Stream<T> stream, Continuation<? super FlowStreamAdapter$adapt$1> continuation) {
        super(2, continuation);
        this.$stream = stream;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowStreamAdapter$adapt$1 flowStreamAdapter$adapt$1 = new FlowStreamAdapter$adapt$1(this.$stream, continuation);
        flowStreamAdapter$adapt$1.L$0 = obj;
        return flowStreamAdapter$adapt$1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cc A[Catch:{ all -> 0x0047 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 2
            r5 = 1
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0075
            if (r3 == r5) goto L_0x0053
            if (r3 != r4) goto L_0x004b
            int r3 = r0.I$3
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r0.L$1
            org.reactivestreams.Publisher r15 = (org.reactivestreams.Publisher) r15
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0047 }
            r5 = r4
        L_0x0036:
            r16 = r15
            r15 = r3
            r3 = r16
            r17 = r14
            r14 = r8
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r9
            r9 = r13
            r13 = r17
            goto L_0x0108
        L_0x0047:
            r0 = move-exception
            r1 = r0
            goto L_0x0117
        L_0x004b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0053:
            int r3 = r0.I$3
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r0.L$1
            org.reactivestreams.Publisher r15 = (org.reactivestreams.Publisher) r15
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0047 }
            r4 = r19
            goto L_0x00c4
        L_0x0075:
            kotlin.ResultKt.throwOnFailure(r19)
            com.tinder.scarlet.Stream<T> r3 = r0.$stream
            kotlinx.coroutines.channels.ReceiveChannel r13 = kotlinx.coroutines.reactive.ChannelKt.openSubscription$default(r3, r6, r5, r7)
            kotlinx.coroutines.channels.ChannelIterator r8 = r13.iterator()     // Catch:{ all -> 0x0047 }
            r11 = r6
            r12 = r11
            r14 = r12
            r15 = r14
            r9 = r13
            r10 = r9
        L_0x0088:
            r0.L$0 = r1     // Catch:{ all -> 0x0114 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ all -> 0x0114 }
            r0.L$1 = r4     // Catch:{ all -> 0x0114 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ all -> 0x0114 }
            r0.L$2 = r4     // Catch:{ all -> 0x0114 }
            r0.L$3 = r9     // Catch:{ all -> 0x0114 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ all -> 0x0114 }
            r0.L$4 = r4     // Catch:{ all -> 0x0114 }
            r0.L$5 = r8     // Catch:{ all -> 0x0114 }
            r0.L$6 = r7     // Catch:{ all -> 0x0114 }
            r0.L$7 = r7     // Catch:{ all -> 0x0114 }
            r0.I$0 = r11     // Catch:{ all -> 0x0114 }
            r0.I$1 = r12     // Catch:{ all -> 0x0114 }
            r0.I$2 = r14     // Catch:{ all -> 0x0114 }
            r0.I$3 = r15     // Catch:{ all -> 0x0114 }
            r0.label = r5     // Catch:{ all -> 0x0114 }
            java.lang.Object r4 = r8.hasNext(r0)     // Catch:{ all -> 0x0114 }
            if (r4 != r2) goto L_0x00b5
            return r2
        L_0x00b5:
            r16 = r15
            r15 = r3
            r3 = r16
            r17 = r11
            r11 = r8
            r8 = r14
            r14 = r13
            r13 = r9
            r9 = r12
            r12 = r10
            r10 = r17
        L_0x00c4:
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0047 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x010c
            java.lang.Object r4 = r11.next()     // Catch:{ all -> 0x0047 }
            r0.L$0 = r1     // Catch:{ all -> 0x0047 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)     // Catch:{ all -> 0x0047 }
            r0.L$1 = r5     // Catch:{ all -> 0x0047 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ all -> 0x0047 }
            r0.L$2 = r5     // Catch:{ all -> 0x0047 }
            r0.L$3 = r13     // Catch:{ all -> 0x0047 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ all -> 0x0047 }
            r0.L$4 = r5     // Catch:{ all -> 0x0047 }
            r0.L$5 = r11     // Catch:{ all -> 0x0047 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0047 }
            r0.L$6 = r5     // Catch:{ all -> 0x0047 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0047 }
            r0.L$7 = r5     // Catch:{ all -> 0x0047 }
            r0.I$0 = r10     // Catch:{ all -> 0x0047 }
            r0.I$1 = r9     // Catch:{ all -> 0x0047 }
            r0.I$2 = r8     // Catch:{ all -> 0x0047 }
            r0.I$3 = r3     // Catch:{ all -> 0x0047 }
            r0.I$4 = r6     // Catch:{ all -> 0x0047 }
            r5 = 2
            r0.label = r5     // Catch:{ all -> 0x0047 }
            java.lang.Object r4 = r1.emit(r4, r0)     // Catch:{ all -> 0x0047 }
            if (r4 != r2) goto L_0x0036
            return r2
        L_0x0108:
            r4 = r5
            r5 = 1
            goto L_0x0088
        L_0x010c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0047 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0114:
            r0 = move-exception
            r1 = r0
            r13 = r9
        L_0x0117:
            throw r1     // Catch:{ all -> 0x0118 }
        L_0x0118:
            r0 = move-exception
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.foundation.network.data.adapter.FlowStreamAdapter$adapt$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((FlowStreamAdapter$adapt$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
