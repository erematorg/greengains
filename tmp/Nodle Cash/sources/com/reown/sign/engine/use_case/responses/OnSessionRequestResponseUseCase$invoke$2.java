package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.model.WCResponse;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase$invoke$2", f = "OnSessionRequestResponseUseCase.kt", i = {0, 0, 1, 1, 1, 2}, l = {43, 53, 56}, m = "invokeSuspend", n = {"jsonRpcHistoryEntry", "result", "jsonRpcHistoryEntry", "result", "method", "e"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0"})
public final class OnSessionRequestResponseUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionRequestParams $params;
    final /* synthetic */ WCResponse $wcResponse;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OnSessionRequestResponseUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionRequestResponseUseCase$invoke$2(OnSessionRequestResponseUseCase onSessionRequestResponseUseCase, WCResponse wCResponse, SignParams.SessionRequestParams sessionRequestParams, Continuation<? super OnSessionRequestResponseUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionRequestResponseUseCase;
        this.$wcResponse = wCResponse;
        this.$params = sessionRequestParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionRequestResponseUseCase$invoke$2(this.this$0, this.$wcResponse, this.$params, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x018b A[Catch:{ Exception -> 0x0038 }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r30) {
        /*
            r29 = this;
            r1 = r29
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            java.lang.String r7 = "Session request response received on topic: "
            if (r0 == 0) goto L_0x0048
            if (r0 == r5) goto L_0x003b
            if (r0 == r4) goto L_0x0027
            if (r0 != r3) goto L_0x001f
            java.lang.Object r0 = r1.L$0
            java.lang.Exception r0 = (java.lang.Exception) r0
            kotlin.ResultKt.throwOnFailure(r30)
            goto L_0x01c8
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0027:
            java.lang.Object r0 = r1.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$1
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse r0 = (com.reown.sign.engine.model.EngineDO.JsonRpcResponse) r0
            java.lang.Object r0 = r1.L$0
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            kotlin.ResultKt.throwOnFailure(r30)     // Catch:{ Exception -> 0x0038 }
            goto L_0x01c8
        L_0x0038:
            r0 = move-exception
            goto L_0x0192
        L_0x003b:
            java.lang.Object r0 = r1.L$1
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse r0 = (com.reown.sign.engine.model.EngineDO.JsonRpcResponse) r0
            java.lang.Object r5 = r1.L$0
            com.reown.sign.common.model.Request r5 = (com.reown.sign.common.model.Request) r5
            kotlin.ResultKt.throwOnFailure(r30)     // Catch:{ Exception -> 0x0038 }
            goto L_0x0127
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r30)
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.json_rpc.domain.GetSessionRequestByIdUseCase r0 = r0.getSessionRequestByIdUseCase     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r8 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse r8 = r8.getResponse()     // Catch:{ Exception -> 0x0038 }
            long r8 = r8.getId()     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.common.model.Request r0 = r0.invoke(r8)     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            com.reown.foundation.util.Logger r8 = r8.logger     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r9 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x0038 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0038 }
            r10.<init>(r7)     // Catch:{ Exception -> 0x0038 }
            r10.append(r9)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x0038 }
            r8.log((java.lang.String) r9)     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r8 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse r8 = r8.getResponse()     // Catch:{ Exception -> 0x0038 }
            boolean r9 = r8 instanceof com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult     // Catch:{ Exception -> 0x0038 }
            if (r9 == 0) goto L_0x0096
            com.reown.android.internal.common.model.WCResponse r8 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse r8 = r8.getResponse()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r9 = "null cannot be cast to non-null type com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r9)     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r8 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r8     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcResult r8 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, "<this>")     // Catch:{ Exception -> 0x0038 }
            goto L_0x00ab
        L_0x0096:
            boolean r8 = r8 instanceof com.reown.android.internal.common.JsonRpcResponse.JsonRpcError     // Catch:{ Exception -> 0x0038 }
            if (r8 == 0) goto L_0x018c
            com.reown.android.internal.common.model.WCResponse r8 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse r8 = r8.getResponse()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r9 = "null cannot be cast to non-null type com.reown.android.internal.common.JsonRpcResponse.JsonRpcError"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r9)     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r8 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r8     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcError r8 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, "<this>")     // Catch:{ Exception -> 0x0038 }
        L_0x00ab:
            if (r0 == 0) goto L_0x00b2
            com.reown.android.internal.common.model.TransportType r9 = r0.getTransportType()     // Catch:{ Exception -> 0x0038 }
            goto L_0x00b3
        L_0x00b2:
            r9 = r6
        L_0x00b3:
            com.reown.android.internal.common.model.TransportType r10 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0038 }
            if (r9 != r10) goto L_0x0129
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            com.reown.android.pulse.domain.InsertEventUseCase r9 = r9.insertEventUseCase     // Catch:{ Exception -> 0x0038 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0038 }
            java.lang.String r11 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r12 = com.reown.android.internal.common.model.Tags.SESSION_REQUEST_LINK_MODE_RESPONSE     // Catch:{ Exception -> 0x0038 }
            int r12 = r12.getId()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x0038 }
            com.reown.android.pulse.model.properties.Properties r15 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r13 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.JsonRpcResponse r13 = r13.getResponse()     // Catch:{ Exception -> 0x0038 }
            long r13 = r13.getId()     // Catch:{ Exception -> 0x0038 }
            java.lang.Long r22 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r13)     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r13 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            java.lang.String r23 = r13.clientId     // Catch:{ Exception -> 0x0038 }
            com.reown.android.pulse.model.Direction r13 = com.reown.android.pulse.model.Direction.RECEIVED     // Catch:{ Exception -> 0x0038 }
            java.lang.String r24 = r13.getState()     // Catch:{ Exception -> 0x0038 }
            r26 = 2303(0x8ff, float:3.227E-42)
            r27 = 0
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r25 = 0
            r28 = 0
            r13 = r15
            r3 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r25
            r25 = r28
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)     // Catch:{ Exception -> 0x0038 }
            r10.<init>(r11, r12, r3)     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0038 }
            r1.L$0 = r3     // Catch:{ Exception -> 0x0038 }
            r1.L$1 = r8     // Catch:{ Exception -> 0x0038 }
            r1.label = r5     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r3 = r9.invoke(r10, r1)     // Catch:{ Exception -> 0x0038 }
            if (r3 != r2) goto L_0x0125
            return r2
        L_0x0125:
            r5 = r0
            r0 = r8
        L_0x0127:
            r8 = r0
            r0 = r5
        L_0x0129:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r3 = r1.$params     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO r3 = r3.getRequest()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r3 = r3.getMethod()     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            com.reown.foundation.util.Logger r5 = r5.logger     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r9 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x0038 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0038 }
            r10.<init>(r7)     // Catch:{ Exception -> 0x0038 }
            r10.append(r9)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r7 = " - emitting: "
            r10.append(r7)     // Catch:{ Exception -> 0x0038 }
            r10.append(r8)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r7 = r10.toString()     // Catch:{ Exception -> 0x0038 }
            r5.log((java.lang.String) r7)     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x0038 }
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5._events     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.engine.model.EngineDO$SessionPayloadResponse r7 = new com.reown.sign.engine.model.EngineDO$SessionPayloadResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.android.internal.common.model.WCResponse r9 = r1.$wcResponse     // Catch:{ Exception -> 0x0038 }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r9 = r9.getValue()     // Catch:{ Exception -> 0x0038 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r10 = r1.$params     // Catch:{ Exception -> 0x0038 }
            java.lang.String r10 = r10.getChainId()     // Catch:{ Exception -> 0x0038 }
            r7.<init>(r9, r10, r3, r8)     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0038 }
            r1.L$0 = r0     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x0038 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0038 }
            r1.L$2 = r0     // Catch:{ Exception -> 0x0038 }
            r1.label = r4     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r0 = r5.emit(r7, r1)     // Catch:{ Exception -> 0x0038 }
            if (r0 != r2) goto L_0x01c8
            return r2
        L_0x018c:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException     // Catch:{ Exception -> 0x0038 }
            r0.<init>()     // Catch:{ Exception -> 0x0038 }
            throw r0     // Catch:{ Exception -> 0x0038 }
        L_0x0192:
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r3 = r1.this$0
            com.reown.foundation.util.Logger r3 = r3.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Session request response received failure: "
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.error((java.lang.String) r4)
            com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase r3 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r3 = r3._events
            com.reown.android.internal.common.model.SDKError r4 = new com.reown.android.internal.common.model.SDKError
            r4.<init>(r0)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$0 = r0
            r1.L$1 = r6
            r1.L$2 = r6
            r5 = 3
            r1.label = r5
            java.lang.Object r0 = r3.emit(r4, r1)
            if (r0 != r2) goto L_0x01c8
            return r2
        L_0x01c8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionRequestResponseUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
