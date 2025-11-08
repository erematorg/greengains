package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nOnSessionAuthenticateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionAuthenticateUseCase$invoke$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,101:1\n1#2:102\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase$invoke$2", f = "OnSessionAuthenticateUseCase.kt", i = {0, 0, 0, 1, 2, 2, 3, 3}, l = {55, 57, 64, 78}, m = "invokeSuspend", n = {"irnParams", "it", "$i$a$-also-OnSessionAuthenticateUseCase$invoke$2$1", "irnParams", "irnParams", "url", "irnParams", "e"}, s = {"L$0", "L$2", "I$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
public final class OnSessionAuthenticateUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionAuthenticateParams $authenticateSessionParams;
    final /* synthetic */ WCRequest $request;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OnSessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionAuthenticateUseCase$invoke$2(WCRequest wCRequest, OnSessionAuthenticateUseCase onSessionAuthenticateUseCase, SignParams.SessionAuthenticateParams sessionAuthenticateParams, Continuation<? super OnSessionAuthenticateUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onSessionAuthenticateUseCase;
        this.$authenticateSessionParams = sessionAuthenticateParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(OnSessionAuthenticateUseCase onSessionAuthenticateUseCase, WCRequest wCRequest, SignParams.SessionAuthenticateParams sessionAuthenticateParams, VerifyContext verifyContext) {
        onSessionAuthenticateUseCase.emitSessionAuthenticate(wCRequest, sessionAuthenticateParams, verifyContext);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionAuthenticateUseCase$invoke$2(this.$request, this.this$0, this.$authenticateSessionParams, continuation);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x0202=Splitter:B:44:0x0202, B:27:0x0127=Splitter:B:27:0x0127} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) {
        /*
            r35 = this;
            r1 = r35
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 4
            r4 = 3
            r5 = 1
            r6 = 2
            r7 = 0
            java.lang.String r8 = "Received session authenticate - expiry error: "
            if (r0 == 0) goto L_0x005a
            if (r0 == r5) goto L_0x0048
            if (r0 == r6) goto L_0x003e
            if (r0 == r4) goto L_0x002e
            if (r0 != r3) goto L_0x0026
            java.lang.Object r0 = r1.L$1
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            kotlin.ResultKt.throwOnFailure(r36)
            goto L_0x02a8
        L_0x0026:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002e:
            java.lang.Object r0 = r1.L$1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r4 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r4 = (com.reown.android.internal.common.model.IrnParams) r4
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x003b }
            goto L_0x01fe
        L_0x003b:
            r0 = move-exception
            goto L_0x0226
        L_0x003e:
            java.lang.Object r0 = r1.L$0
            r4 = r0
            com.reown.android.internal.common.model.IrnParams r4 = (com.reown.android.internal.common.model.IrnParams) r4
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x003b }
            goto L_0x0176
        L_0x0048:
            java.lang.Object r0 = r1.L$2
            kotlin.Unit r0 = (kotlin.Unit) r0
            java.lang.Object r0 = r1.L$1
            kotlin.Unit r0 = (kotlin.Unit) r0
            java.lang.Object r0 = r1.L$0
            r4 = r0
            com.reown.android.internal.common.model.IrnParams r4 = (com.reown.android.internal.common.model.IrnParams) r4
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x003b }
            goto L_0x0127
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r36)
            com.reown.android.internal.common.model.IrnParams r15 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r10 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_RESPONSE_AUTO_REJECT
            com.reown.foundation.common.model.Ttl r11 = new com.reown.foundation.common.model.Ttl
            long r12 = com.reown.android.internal.utils.Time.getDayInSeconds()
            r11.<init>(r12)
            com.reown.android.internal.common.model.WCRequest r0 = r1.$request
            long r12 = r0.getId()
            java.lang.Long r12 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            r16 = 0
            r17 = 0
            r13 = 0
            r14 = 0
            r0 = 0
            r18 = 248(0xf8, float:3.48E-43)
            r19 = 0
            r9 = r15
            r3 = r15
            r15 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0
            com.reown.foundation.util.Logger r0 = r0.logger
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Received session authenticate: "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r0.log((java.lang.String) r9)
            com.reown.android.internal.utils.CoreValidator r0 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.Expiry r9 = new com.reown.android.internal.common.model.Expiry     // Catch:{ Exception -> 0x0179 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r10 = r1.$authenticateSessionParams     // Catch:{ Exception -> 0x0179 }
            long r10 = r10.getExpiryTimestamp()     // Catch:{ Exception -> 0x0179 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0179 }
            boolean r0 = r0.isExpired(r9)     // Catch:{ Exception -> 0x0179 }
            if (r0 == 0) goto L_0x017d
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0179 }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x0179 }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x0179 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0179 }
            r9.<init>(r8)     // Catch:{ Exception -> 0x0179 }
            r9.append(r4)     // Catch:{ Exception -> 0x0179 }
            java.lang.String r4 = r9.toString()     // Catch:{ Exception -> 0x0179 }
            r0.log((java.lang.String) r4)     // Catch:{ Exception -> 0x0179 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0179 }
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r4 = r1.this$0     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r4 = r4.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.model.properties.Props r15 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0179 }
            java.lang.String r12 = "AUTHENTICATED_SESSION_EXPIRED"
            com.reown.android.pulse.model.properties.Properties r13 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0179 }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x0179 }
            java.lang.String r28 = r9.getValue()     // Catch:{ Exception -> 0x0179 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r13
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0179 }
            r9 = 0
            r11 = 0
            r14 = 1
            r10 = r15
            r6 = r15
            r15 = r9
            r10.<init>(r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0179 }
            r1.L$0 = r3     // Catch:{ Exception -> 0x0179 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0179 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0179 }
            r1.L$2 = r0     // Catch:{ Exception -> 0x0179 }
            r0 = 0
            r1.I$0 = r0     // Catch:{ Exception -> 0x0179 }
            r1.label = r5     // Catch:{ Exception -> 0x0179 }
            java.lang.Object r0 = r4.invoke(r6, r1)     // Catch:{ Exception -> 0x0179 }
            if (r0 != r2) goto L_0x0126
            return r2
        L_0x0126:
            r4 = r3
        L_0x0127:
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r20 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.WCRequest r0 = r1.$request     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.exception.Invalid$RequestExpired r22 = com.reown.android.internal.common.exception.Invalid.RequestExpired.INSTANCE     // Catch:{ Exception -> 0x003b }
            r26 = 0
            r27 = 0
            r28 = 120(0x78, float:1.68E-43)
            r29 = 0
            r24 = 0
            r25 = 0
            r21 = r0
            r23 = r4
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ Exception -> 0x003b }
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003b }
            kotlinx.coroutines.flow.MutableSharedFlow r0 = r0._events     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.SDKError r3 = new com.reown.android.internal.common.model.SDKError     // Catch:{ Exception -> 0x003b }
            java.lang.Throwable r5 = new java.lang.Throwable     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x003b }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x003b }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r9.<init>(r8)     // Catch:{ Exception -> 0x003b }
            r9.append(r6)     // Catch:{ Exception -> 0x003b }
            java.lang.String r6 = r9.toString()     // Catch:{ Exception -> 0x003b }
            r5.<init>(r6)     // Catch:{ Exception -> 0x003b }
            r3.<init>(r5)     // Catch:{ Exception -> 0x003b }
            r1.L$0 = r4     // Catch:{ Exception -> 0x003b }
            r1.L$1 = r7     // Catch:{ Exception -> 0x003b }
            r1.L$2 = r7     // Catch:{ Exception -> 0x003b }
            r5 = 2
            r1.label = r5     // Catch:{ Exception -> 0x003b }
            java.lang.Object r0 = r0.emit(r3, r1)     // Catch:{ Exception -> 0x003b }
            if (r0 != r2) goto L_0x0176
            return r2
        L_0x0176:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003b }
            return r0
        L_0x0179:
            r0 = move-exception
            r4 = r3
            goto L_0x0226
        L_0x017d:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = r1.$authenticateSessionParams     // Catch:{ Exception -> 0x0179 }
            java.lang.String r0 = r0.getMetadataUrl()     // Catch:{ Exception -> 0x0179 }
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pairing.handler.PairingControllerInterface r5 = r5.pairingController     // Catch:{ Exception -> 0x0179 }
            com.reown.android.Core$Params$RequestReceived r6 = new com.reown.android.Core$Params$RequestReceived     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request     // Catch:{ Exception -> 0x0179 }
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()     // Catch:{ Exception -> 0x0179 }
            java.lang.String r8 = r8.getValue()     // Catch:{ Exception -> 0x0179 }
            r6.<init>(r8)     // Catch:{ Exception -> 0x0179 }
            r8 = 2
            com.reown.android.pairing.handler.PairingControllerInterface.setRequestReceived$default(r5, r6, r7, r8, r7)     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.WCRequest r5 = r1.$request     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.TransportType r5 = r5.getTransportType()     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.TransportType r6 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0179 }
            if (r5 != r6) goto L_0x0200
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.domain.InsertEventUseCase r5 = r5.insertEventUseCase     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0179 }
            java.lang.String r8 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r9 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_LINK_MODE     // Catch:{ Exception -> 0x0179 }
            int r9 = r9.getId()     // Catch:{ Exception -> 0x0179 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0179 }
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request     // Catch:{ Exception -> 0x0179 }
            long r11 = r11.getId()     // Catch:{ Exception -> 0x0179 }
            java.lang.Long r29 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r11)     // Catch:{ Exception -> 0x0179 }
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r11 = r1.this$0     // Catch:{ Exception -> 0x0179 }
            java.lang.String r30 = r11.clientId     // Catch:{ Exception -> 0x0179 }
            com.reown.android.pulse.model.Direction r11 = com.reown.android.pulse.model.Direction.RECEIVED     // Catch:{ Exception -> 0x0179 }
            java.lang.String r31 = r11.getState()     // Catch:{ Exception -> 0x0179 }
            r28 = 0
            r32 = 0
            r33 = 2303(0x8ff, float:3.227E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0179 }
            r6.<init>(r8, r9, r10)     // Catch:{ Exception -> 0x0179 }
            r1.L$0 = r3     // Catch:{ Exception -> 0x0179 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0179 }
            r1.label = r4     // Catch:{ Exception -> 0x0179 }
            java.lang.Object r4 = r5.invoke(r6, r1)     // Catch:{ Exception -> 0x0179 }
            if (r4 != r2) goto L_0x01fd
            return r2
        L_0x01fd:
            r4 = r3
        L_0x01fe:
            r10 = r0
            goto L_0x0202
        L_0x0200:
            r10 = r0
            r4 = r3
        L_0x0202:
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003b }
            com.reown.android.verify.domain.ResolveAttestationIdUseCase r8 = r0.resolveAttestationIdUseCase     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x003b }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = r1.$authenticateSessionParams     // Catch:{ Exception -> 0x003b }
            java.lang.Boolean r11 = r0.getLinkMode()     // Catch:{ Exception -> 0x003b }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = r1.$authenticateSessionParams     // Catch:{ Exception -> 0x003b }
            java.lang.String r12 = r0.getAppLink()     // Catch:{ Exception -> 0x003b }
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request     // Catch:{ Exception -> 0x003b }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r5 = r1.$authenticateSessionParams     // Catch:{ Exception -> 0x003b }
            com.reown.sign.engine.use_case.requests.a r13 = new com.reown.sign.engine.use_case.requests.a     // Catch:{ Exception -> 0x003b }
            r13.<init>((com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase) r0, (com.reown.android.internal.common.model.WCRequest) r3, (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r5)     // Catch:{ Exception -> 0x003b }
            r8.invoke(r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x003b }
            goto L_0x02a8
        L_0x0226:
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r3 = r1.this$0
            com.reown.foundation.util.Logger r3 = r3.logger
            com.reown.android.internal.common.model.WCRequest r5 = r1.$request
            com.reown.foundation.common.model.Topic r5 = r5.getTopic()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Received session authenticate - cannot handle request: "
            r6.<init>(r8)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r3.log((java.lang.String) r5)
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r3 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r20 = r3.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request
            com.reown.android.internal.common.exception.Uncategorized$GenericError r5 = new com.reown.android.internal.common.exception.Uncategorized$GenericError
            java.lang.String r6 = r0.getMessage()
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Cannot handle a auth request: "
            r9.<init>(r10)
            r9.append(r6)
            java.lang.String r6 = ", topic: "
            r9.append(r6)
            r9.append(r8)
            java.lang.String r6 = r9.toString()
            r5.<init>(r6)
            r26 = 0
            r27 = 0
            r24 = 0
            r25 = 0
            r28 = 120(0x78, float:1.68E-43)
            r29 = 0
            r21 = r3
            r22 = r5
            r23 = r4
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase r3 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r3 = r3._events
            com.reown.android.internal.common.model.SDKError r5 = new com.reown.android.internal.common.model.SDKError
            r5.<init>(r0)
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r1.L$0 = r4
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$1 = r0
            r1.L$2 = r7
            r4 = 4
            r1.label = r4
            java.lang.Object r0 = r3.emit(r5, r1)
            if (r0 != r2) goto L_0x02a8
            return r2
        L_0x02a8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionAuthenticateUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
