package com.reown.android.internal.common.json_rpc.domain.relay;

import com.reown.foundation.common.model.Topic;
import com.reown.foundation.network.model.Relay;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nRelayJsonRpcInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor$storePushRequestsIfEnabled$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,558:1\n774#2:559\n865#2,2:560\n2756#2:562\n1#3:563\n*S KotlinDebug\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor$storePushRequestsIfEnabled$2\n*L\n469#1:559\n469#1:560,2\n470#1:562\n470#1:563\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$storePushRequestsIfEnabled$2", f = "RelayJsonRpcInteractor.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {471}, m = "invokeSuspend", n = {"$this$onEach$iv", "$this$onEach_u24lambda_u2418$iv", "element$iv", "$i$f$onEach", "$i$a$-apply-CollectionsKt___CollectionsKt$onEach$1$iv", "tag", "$i$a$-onEach-RelayJsonRpcInteractor$storePushRequestsIfEnabled$2$2"}, s = {"L$0", "L$5", "L$7", "I$0", "I$1", "I$2", "I$3"})
public final class RelayJsonRpcInteractor$storePushRequestsIfEnabled$2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
    final /* synthetic */ Relay.Model.Call.Subscription.Request $relayRequest;
    final /* synthetic */ Topic $topic;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$storePushRequestsIfEnabled$2(RelayJsonRpcInteractor relayJsonRpcInteractor, Relay.Model.Call.Subscription.Request request, Topic topic, Continuation<? super RelayJsonRpcInteractor$storePushRequestsIfEnabled$2> continuation) {
        super(2, continuation);
        this.this$0 = relayJsonRpcInteractor;
        this.$relayRequest = request;
        this.$topic = topic;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RelayJsonRpcInteractor$storePushRequestsIfEnabled$2(this.this$0, this.$relayRequest, this.$topic, continuation);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Boolean) obj).booleanValue(), (Continuation<? super Unit>) (Continuation) obj2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r6 = r19
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.label
            r8 = 1
            if (r0 == 0) goto L_0x0047
            if (r0 != r8) goto L_0x003f
            int r0 = r6.I$1
            int r1 = r6.I$0
            java.lang.Object r2 = r6.L$6
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r6.L$5
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.lang.Object r4 = r6.L$4
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.Object r5 = r6.L$3
            com.reown.foundation.common.model.Topic r5 = (com.reown.foundation.common.model.Topic) r5
            java.lang.Object r10 = r6.L$2
            com.reown.foundation.network.model.Relay$Model$Call$Subscription$Request r10 = (com.reown.foundation.network.model.Relay.Model.Call.Subscription.Request) r10
            java.lang.Object r11 = r6.L$1
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r11 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor) r11
            java.lang.Object r12 = r6.L$0
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            kotlin.ResultKt.throwOnFailure(r20)
            r13 = r3
            r14 = r4
            r15 = r5
            r17 = r8
            r5 = r10
            r4 = r11
            r16 = r12
            r9 = 0
            r10 = r0
            r11 = r1
            r12 = r2
            goto L_0x0100
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r20)
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r0 = r6.this$0
            com.reown.android.internal.common.storage.push_messages.PushMessagesRepository r0 = r0.pushMessageStorage
            java.util.List r0 = r0.getNotificationTags()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            com.reown.foundation.network.model.Relay$Model$Call$Subscription$Request r1 = r6.$relayRequest
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0061:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x007c
            java.lang.Object r3 = r0.next()
            r4 = r3
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            int r5 = r1.getTag()
            if (r4 != r5) goto L_0x0061
            r2.add(r3)
            goto L_0x0061
        L_0x007c:
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor r0 = r6.this$0
            com.reown.foundation.network.model.Relay$Model$Call$Subscription$Request r1 = r6.$relayRequest
            com.reown.foundation.common.model.Topic r3 = r6.$topic
            java.util.Iterator r4 = r2.iterator()
            r5 = r1
            r13 = r2
            r14 = r13
            r16 = r14
            r15 = r3
            r12 = r4
            r10 = 0
            r11 = 0
            r4 = r0
        L_0x0090:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0103
            java.lang.Object r0 = r12.next()
            r1 = r0
            java.lang.Number r1 = (java.lang.Number) r1
            int r3 = r1.intValue()
            com.reown.android.internal.common.storage.push_messages.PushMessagesRepository r1 = r4.pushMessageStorage
            java.lang.String r2 = r5.getMessage()
            java.nio.charset.Charset r8 = kotlin.text.Charsets.UTF_8
            byte[] r2 = r2.getBytes(r8)
            java.lang.String r8 = "getBytes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)
            java.lang.String r2 = com.reown.android.internal.common.crypto.UtilsKt.sha256(r2)
            java.lang.String r8 = r15.getValue()
            java.lang.String r18 = r5.getMessage()
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)
            r6.L$0 = r9
            r6.L$1 = r4
            r6.L$2 = r5
            r6.L$3 = r15
            r6.L$4 = r14
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r6.L$5 = r9
            r6.L$6 = r12
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r6.L$7 = r0
            r6.I$0 = r11
            r6.I$1 = r10
            r6.I$2 = r3
            r9 = 0
            r6.I$3 = r9
            r0 = 1
            r6.label = r0
            r17 = r0
            r0 = r1
            r1 = r2
            r2 = r8
            r8 = r3
            r3 = r18
            r18 = r4
            r4 = r8
            r8 = r5
            r5 = r19
            java.lang.Object r0 = r0.insertPushMessage(r1, r2, r3, r4, r5)
            if (r0 != r7) goto L_0x00fd
            return r7
        L_0x00fd:
            r5 = r8
            r4 = r18
        L_0x0100:
            r8 = r17
            goto L_0x0090
        L_0x0103:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$storePushRequestsIfEnabled$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(boolean z2, Continuation<? super Unit> continuation) {
        return ((RelayJsonRpcInteractor$storePushRequestsIfEnabled$2) create(Boolean.valueOf(z2), continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
