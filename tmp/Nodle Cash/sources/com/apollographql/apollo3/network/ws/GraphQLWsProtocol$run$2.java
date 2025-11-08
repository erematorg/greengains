package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.GraphQLWsProtocol$run$2", f = "GraphQLWsProtocol.kt", i = {0}, l = {128}, m = "invokeSuspend", n = {"map"}, s = {"L$0"})
public final class GraphQLWsProtocol$run$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ GraphQLWsProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphQLWsProtocol$run$2(GraphQLWsProtocol graphQLWsProtocol, Continuation<? super GraphQLWsProtocol$run$2> continuation) {
        super(2, continuation);
        this.this$0 = graphQLWsProtocol;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new GraphQLWsProtocol$run$2(this.this$0, continuation);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0053 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            java.lang.Object r1 = r5.L$0
            java.util.Map r1 = (java.util.Map) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0054
        L_0x0013:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = "type"
            java.lang.String r1 = "ping"
            kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r1)
            kotlin.Pair[] r6 = new kotlin.Pair[]{r6}
            java.util.Map r6 = kotlin.collections.MapsKt.mutableMapOf(r6)
            com.apollographql.apollo3.network.ws.GraphQLWsProtocol r1 = r5.this$0
            java.util.Map r1 = r1.pingPayload
            if (r1 == 0) goto L_0x0042
            com.apollographql.apollo3.network.ws.GraphQLWsProtocol r1 = r5.this$0
            java.util.Map r1 = r1.pingPayload
            java.lang.String r3 = "payload"
            r6.put(r3, r1)
        L_0x0042:
            r1 = r6
        L_0x0043:
            com.apollographql.apollo3.network.ws.GraphQLWsProtocol r6 = r5.this$0
            long r3 = r6.pingIntervalMillis
            r5.L$0 = r1
            r5.label = r2
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r3, r5)
            if (r6 != r0) goto L_0x0054
            return r0
        L_0x0054:
            com.apollographql.apollo3.network.ws.GraphQLWsProtocol r6 = r5.this$0
            com.apollographql.apollo3.network.ws.WsFrameType r3 = r6.frameType
            r6.sendMessageMap(r1, r3)
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.GraphQLWsProtocol$run$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((GraphQLWsProtocol$run$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
