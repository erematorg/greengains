package com.reown.android.internal.common.json_rpc.domain.link_mode;

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
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$dispatchEnvelope$1", f = "LinkModeJsonRpcInteractor.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
public final class LinkModeJsonRpcInteractor$dispatchEnvelope$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $envelope;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ LinkModeJsonRpcInteractor this$0;

    @SourceDebugExtension({"SMAP\nLinkModeJsonRpcInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LinkModeJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractor$dispatchEnvelope$1$1\n+ 2 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n56#2:137\n56#2:140\n56#2:143\n1#3:138\n1#3:139\n1#3:141\n1#3:142\n1#3:144\n1#3:145\n*S KotlinDebug\n*F\n+ 1 LinkModeJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractor$dispatchEnvelope$1$1\n*L\n83#1:137\n84#1:140\n85#1:143\n83#1:138\n84#1:141\n85#1:144\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$dispatchEnvelope$1$1", f = "LinkModeJsonRpcInteractor.kt", i = {0, 0, 1, 1, 2, 2}, l = {83, 84, 85}, m = "invokeSuspend", n = {"clientJsonRpc", "$i$a$-let-LinkModeJsonRpcInteractor$dispatchEnvelope$1$1$1", "result", "$i$a$-let-LinkModeJsonRpcInteractor$dispatchEnvelope$1$1$2", "error", "$i$a$-let-LinkModeJsonRpcInteractor$dispatchEnvelope$1$1$3"}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    /* renamed from: com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$dispatchEnvelope$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(linkModeJsonRpcInteractor, str, str2, continuation);
        }

        /* JADX WARNING: Removed duplicated region for block: B:54:0x010e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L_0x0030
                if (r1 == r4) goto L_0x0027
                if (r1 == r3) goto L_0x0022
                if (r1 != r2) goto L_0x001a
                java.lang.Object r7 = r7.L$0
                com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r7 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0107
            L_0x001a:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L_0x0022:
                java.lang.Object r7 = r7.L$0
                com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r7 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r7
                goto L_0x002b
            L_0x0027:
                java.lang.Object r7 = r7.L$0
                com.reown.android.internal.common.model.sync.ClientJsonRpc r7 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r7
            L_0x002b:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x010b
            L_0x0030:
                kotlin.ResultKt.throwOnFailure(r8)
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r8 = r1
                com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r8 = r8.getSerializer()
                java.lang.String r1 = r3
                kotlin.Result$Companion r5 = kotlin.Result.Companion     // Catch:{ all -> 0x0050 }
                com.squareup.moshi.Moshi r8 = r8.getMoshi()     // Catch:{ all -> 0x0050 }
                java.lang.Class<com.reown.android.internal.common.model.sync.ClientJsonRpc> r5 = com.reown.android.internal.common.model.sync.ClientJsonRpc.class
                com.squareup.moshi.JsonAdapter r8 = r8.adapter(r5)     // Catch:{ all -> 0x0050 }
                java.lang.Object r8 = r8.fromJson((java.lang.String) r1)     // Catch:{ all -> 0x0050 }
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)     // Catch:{ all -> 0x0050 }
                goto L_0x005b
            L_0x0050:
                r8 = move-exception
                kotlin.Result$Companion r1 = kotlin.Result.Companion
                java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)
            L_0x005b:
                boolean r1 = kotlin.Result.m8985isFailureimpl(r8)
                r5 = 0
                if (r1 == 0) goto L_0x0063
                r8 = r5
            L_0x0063:
                com.reown.android.internal.common.model.sync.ClientJsonRpc r8 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r8
                r1 = 0
                if (r8 == 0) goto L_0x007f
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r2 = r1
                java.lang.String r3 = r4
                java.lang.String r5 = r3
                java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$0 = r6
                r7.I$0 = r1
                r7.label = r4
                java.lang.Object r7 = r2.serializeRequest(r8, r3, r5, r7)
                if (r7 != r0) goto L_0x010b
                return r0
            L_0x007f:
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r8 = r1
                com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r8 = r8.getSerializer()
                java.lang.String r4 = r3
                com.squareup.moshi.Moshi r8 = r8.getMoshi()     // Catch:{ all -> 0x009a }
                java.lang.Class<com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult> r6 = com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult.class
                com.squareup.moshi.JsonAdapter r8 = r8.adapter(r6)     // Catch:{ all -> 0x009a }
                java.lang.Object r8 = r8.fromJson((java.lang.String) r4)     // Catch:{ all -> 0x009a }
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)     // Catch:{ all -> 0x009a }
                goto L_0x00a5
            L_0x009a:
                r8 = move-exception
                kotlin.Result$Companion r4 = kotlin.Result.Companion
                java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)
            L_0x00a5:
                boolean r4 = kotlin.Result.m8985isFailureimpl(r8)
                if (r4 == 0) goto L_0x00ac
                r8 = r5
            L_0x00ac:
                com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r8 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r8
                if (r8 == 0) goto L_0x00c3
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r2 = r1
                java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$0 = r4
                r7.I$0 = r1
                r7.label = r3
                java.lang.Object r7 = r2.serializeResult(r8, r7)
                if (r7 != r0) goto L_0x010b
                return r0
            L_0x00c3:
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r8 = r1
                com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r8 = r8.getSerializer()
                java.lang.String r3 = r3
                com.squareup.moshi.Moshi r8 = r8.getMoshi()     // Catch:{ all -> 0x00de }
                java.lang.Class<com.reown.android.internal.common.JsonRpcResponse$JsonRpcError> r4 = com.reown.android.internal.common.JsonRpcResponse.JsonRpcError.class
                com.squareup.moshi.JsonAdapter r8 = r8.adapter(r4)     // Catch:{ all -> 0x00de }
                java.lang.Object r8 = r8.fromJson((java.lang.String) r3)     // Catch:{ all -> 0x00de }
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)     // Catch:{ all -> 0x00de }
                goto L_0x00e9
            L_0x00de:
                r8 = move-exception
                kotlin.Result$Companion r3 = kotlin.Result.Companion
                java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
                java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)
            L_0x00e9:
                boolean r3 = kotlin.Result.m8985isFailureimpl(r8)
                if (r3 == 0) goto L_0x00f0
                r8 = r5
            L_0x00f0:
                com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r8 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r8
                if (r8 == 0) goto L_0x0109
                com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor r3 = r1
                java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$0 = r4
                r7.I$0 = r1
                r7.label = r2
                java.lang.Object r7 = r3.serializeError(r8, r7)
                if (r7 != r0) goto L_0x0107
                return r0
            L_0x0107:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
            L_0x0109:
                if (r5 == 0) goto L_0x010e
            L_0x010b:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            L_0x010e:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "LinkMode: Received unknown object type"
                r7.<init>(r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$dispatchEnvelope$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LinkModeJsonRpcInteractor$dispatchEnvelope$1(LinkModeJsonRpcInteractor linkModeJsonRpcInteractor, String str, String str2, Continuation<? super LinkModeJsonRpcInteractor$dispatchEnvelope$1> continuation) {
        super(2, continuation);
        this.this$0 = linkModeJsonRpcInteractor;
        this.$envelope = str;
        this.$topic = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LinkModeJsonRpcInteractor$dispatchEnvelope$1(this.this$0, this.$envelope, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final LinkModeJsonRpcInteractor linkModeJsonRpcInteractor = this.this$0;
            final String str = this.$envelope;
            final String str2 = this.$topic;
            AnonymousClass1 r7 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r7, this) == coroutine_suspended) {
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
        return ((LinkModeJsonRpcInteractor$dispatchEnvelope$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
