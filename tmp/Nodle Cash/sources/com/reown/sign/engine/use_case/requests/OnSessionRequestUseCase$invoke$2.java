package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nOnSessionRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase$invoke$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,142:1\n108#2,6:143\n85#2,6:149\n*S KotlinDebug\n*F\n+ 1 OnSessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase$invoke$2\n*L\n69#1:143,6\n92#1:149,6\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase$invoke$2", f = "OnSessionRequestUseCase.kt", i = {0, 0, 0, 0, 1, 1}, l = {98, 120}, m = "invokeSuspend", n = {"irnParams", "sessionNamespaces", "sessionPeerAppMetaData", "method", "irnParams", "e"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1"})
public final class OnSessionRequestUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionRequestParams $params;
    final /* synthetic */ WCRequest $request;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ OnSessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionRequestUseCase$invoke$2(WCRequest wCRequest, OnSessionRequestUseCase onSessionRequestUseCase, SignParams.SessionRequestParams sessionRequestParams, Continuation<? super OnSessionRequestUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onSessionRequestUseCase;
        this.$params = sessionRequestParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$4(OnSessionRequestUseCase onSessionRequestUseCase, SignParams.SessionRequestParams sessionRequestParams, WCRequest wCRequest, AppMetaData appMetaData, VerifyContext verifyContext) {
        Logger access$getLogger$p = onSessionRequestUseCase.logger;
        long currentTimeMillis = System.currentTimeMillis();
        access$getLogger$p.log("Session request attestation resolved: " + currentTimeMillis);
        onSessionRequestUseCase.emitSessionRequest(sessionRequestParams, wCRequest, appMetaData, verifyContext);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionRequestUseCase$invoke$2(this.$request, this.this$0, this.$params, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x02ca, code lost:
        r0 = r3.getRedirect();
     */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x02c2 A[Catch:{ Exception -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x02c3 A[Catch:{ Exception -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x02d0 A[Catch:{ Exception -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02d6 A[Catch:{ Exception -> 0x003f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r31) {
        /*
            r30 = this;
            r1 = r30
            java.lang.String r0 = "Resolving session request attestation: "
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 0
            r6 = 1
            java.lang.String r7 = "Session request received failure on topic: "
            if (r3 == 0) goto L_0x0044
            if (r3 == r6) goto L_0x002a
            if (r3 != r4) goto L_0x0022
            java.lang.Object r0 = r1.L$1
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            kotlin.ResultKt.throwOnFailure(r31)
            goto L_0x0388
        L_0x0022:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002a:
            java.lang.Object r3 = r1.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r1.L$2
            com.reown.android.internal.common.model.AppMetaData r3 = (com.reown.android.internal.common.model.AppMetaData) r3
            java.lang.Object r8 = r1.L$1
            java.util.Map r8 = (java.util.Map) r8
            java.lang.Object r8 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r8 = (com.reown.android.internal.common.model.IrnParams) r8
            kotlin.ResultKt.throwOnFailure(r31)     // Catch:{ Exception -> 0x003f }
            goto L_0x0287
        L_0x003f:
            r0 = move-exception
            r3 = r0
            r0 = r8
            goto L_0x0304
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r31)
            com.reown.android.internal.common.model.IrnParams r8 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r10 = com.reown.android.internal.common.model.Tags.SESSION_REQUEST_RESPONSE
            com.reown.foundation.common.model.Ttl r11 = new com.reown.foundation.common.model.Ttl
            long r12 = com.reown.android.internal.utils.Time.getFiveMinutesInSeconds()
            r11.<init>(r12)
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request
            long r12 = r3.getId()
            java.lang.Long r12 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            r16 = 0
            r17 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r18 = 248(0xf8, float:3.48E-43)
            r19 = 0
            r9 = r8
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r3 = r1.this$0
            com.reown.foundation.util.Logger r3 = r3.logger
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Session request received on topic: "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r3.log((java.lang.String) r9)
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r3 = r1.$params     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO r3 = r3.getRequest()     // Catch:{ Exception -> 0x003f }
            java.lang.Long r3 = r3.getExpiryTimestamp()     // Catch:{ Exception -> 0x003f }
            if (r3 == 0) goto L_0x00dd
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r10 = r1.$request     // Catch:{ Exception -> 0x003f }
            long r11 = r3.longValue()     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.utils.CoreValidator r3 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.Expiry r13 = new com.reown.android.internal.common.model.Expiry     // Catch:{ Exception -> 0x003f }
            r13.<init>(r11)     // Catch:{ Exception -> 0x003f }
            boolean r3 = r3.isExpired(r13)     // Catch:{ Exception -> 0x003f }
            if (r3 == 0) goto L_0x00dd
            com.reown.foundation.util.Logger r0 = r9.logger     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r3 = r10.getTopic()     // Catch:{ Exception -> 0x003f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003f }
            r6.<init>(r7)     // Catch:{ Exception -> 0x003f }
            r6.append(r3)     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = " - request expired"
            r6.append(r3)     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x003f }
            r0.error((java.lang.String) r3)     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r9 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.exception.Invalid$RequestExpired r11 = com.reown.android.internal.common.exception.Invalid.RequestExpired.INSTANCE     // Catch:{ Exception -> 0x003f }
            r15 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r13 = 0
            r14 = 0
            r12 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x003f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003f }
            return r0
        L_0x00dd:
            com.reown.sign.common.validator.SignValidator r3 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r3 = r1.$params     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.model.EngineDO$Request r3 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDO((com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r3, (com.reown.foundation.common.model.Topic) r9)     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r10 = r1.$request     // Catch:{ Exception -> 0x003f }
            java.lang.String r11 = r3.getParams()     // Catch:{ Exception -> 0x003f }
            int r11 = r11.length()     // Catch:{ Exception -> 0x003f }
            if (r11 != 0) goto L_0x00fa
            goto L_0x0127
        L_0x00fa:
            java.lang.String r11 = r3.getMethod()     // Catch:{ Exception -> 0x003f }
            int r11 = r11.length()     // Catch:{ Exception -> 0x003f }
            if (r11 != 0) goto L_0x0105
            goto L_0x0127
        L_0x0105:
            java.lang.String r11 = r3.getChainId()     // Catch:{ Exception -> 0x003f }
            int r11 = r11.length()     // Catch:{ Exception -> 0x003f }
            if (r11 != 0) goto L_0x0110
            goto L_0x0127
        L_0x0110:
            java.lang.String r11 = r3.getTopic()     // Catch:{ Exception -> 0x003f }
            int r11 = r11.length()     // Catch:{ Exception -> 0x003f }
            if (r11 != 0) goto L_0x011b
            goto L_0x0127
        L_0x011b:
            com.reown.android.internal.utils.CoreValidator r11 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = r3.getChainId()     // Catch:{ Exception -> 0x003f }
            boolean r3 = r11.isChainIdCAIP2Compliant(r3)     // Catch:{ Exception -> 0x003f }
            if (r3 != 0) goto L_0x015d
        L_0x0127:
            com.reown.sign.engine.model.ValidationError$InvalidSessionRequest r0 = com.reown.sign.engine.model.ValidationError.InvalidSessionRequest.INSTANCE     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.util.Logger r3 = r9.logger     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x003f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003f }
            r11.<init>(r7)     // Catch:{ Exception -> 0x003f }
            r11.append(r6)     // Catch:{ Exception -> 0x003f }
            java.lang.String r6 = " - invalid request"
            r11.append(r6)     // Catch:{ Exception -> 0x003f }
            java.lang.String r6 = r11.toString()     // Catch:{ Exception -> 0x003f }
            r3.error((java.lang.String) r6)     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r9 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.exceptions.PeerError r11 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x003f }
            r15 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r13 = 0
            r14 = 0
            r12 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x003f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003f }
            return r0
        L_0x015d:
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r3 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.sign.storage.sequence.SessionStorageRepository r3 = r3.sessionStorageRepository     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x003f }
            boolean r3 = r3.isSessionValid(r9)     // Catch:{ Exception -> 0x003f }
            if (r3 != 0) goto L_0x01b6
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r3 = r3.getTopic()     // Catch:{ Exception -> 0x003f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003f }
            r6.<init>(r7)     // Catch:{ Exception -> 0x003f }
            r6.append(r3)     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = " - invalid session"
            r6.append(r3)     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x003f }
            r0.error((java.lang.String) r3)     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r9 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r10 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic r11 = new com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic     // Catch:{ Exception -> 0x003f }
            java.lang.String r0 = "SESSION"
            com.reown.foundation.common.model.Topic r3 = r10.getTopic()     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = r3.getValue()     // Catch:{ Exception -> 0x003f }
            r11.<init>(r0, r3)     // Catch:{ Exception -> 0x003f }
            r15 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r13 = 0
            r14 = 0
            r12 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x003f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003f }
            return r0
        L_0x01b6:
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r3 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.sign.storage.sequence.SessionStorageRepository r3 = r3.sessionStorageRepository     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.sequence.SessionVO r3 = r3.getSessionWithoutMetadataByTopic(r9)     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r9 = r9.metadataStorageRepository     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.common.model.Topic r10 = r3.getTopic()     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.AppMetaDataType r11 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.AppMetaData r9 = r9.getByTopicAndType(r10, r11)     // Catch:{ Exception -> 0x003f }
            java.util.Map r3 = r3.getSessionNamespaces()     // Catch:{ Exception -> 0x003f }
            kotlin.Pair r3 = kotlin.TuplesKt.to(r3, r9)     // Catch:{ Exception -> 0x003f }
            java.lang.Object r9 = r3.component1()     // Catch:{ Exception -> 0x003f }
            java.util.Map r9 = (java.util.Map) r9     // Catch:{ Exception -> 0x003f }
            java.lang.Object r3 = r3.component2()     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.AppMetaData r3 = (com.reown.android.internal.common.model.AppMetaData) r3     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r10 = r1.$params     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO r10 = r10.getRequest()     // Catch:{ Exception -> 0x003f }
            java.lang.String r10 = r10.getMethod()     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.validator.SignValidator r11 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r12 = r1.$params     // Catch:{ Exception -> 0x003f }
            java.lang.String r12 = r12.getChainId()     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r13 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r14 = r1.$request     // Catch:{ Exception -> 0x003f }
            java.util.Map r11 = r11.allMethodsWithChains(r9)     // Catch:{ Exception -> 0x003f }
            java.lang.Object r15 = r11.get(r10)     // Catch:{ Exception -> 0x003f }
            if (r15 == 0) goto L_0x02e8
            java.lang.Object r11 = r11.get(r10)     // Catch:{ Exception -> 0x003f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ Exception -> 0x003f }
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x003f }
            boolean r11 = r11.contains(r12)     // Catch:{ Exception -> 0x003f }
            if (r11 != 0) goto L_0x021b
            goto L_0x02e8
        L_0x021b:
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.TransportType r11 = r11.getTransportType()     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.TransportType r12 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x003f }
            if (r11 != r12) goto L_0x0287
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r11 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.pulse.domain.InsertEventUseCase r11 = r11.insertEventUseCase     // Catch:{ Exception -> 0x003f }
            com.reown.android.pulse.model.properties.Props r12 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x003f }
            java.lang.String r13 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r14 = com.reown.android.internal.common.model.Tags.SESSION_REQUEST_LINK_MODE     // Catch:{ Exception -> 0x003f }
            int r14 = r14.getId()     // Catch:{ Exception -> 0x003f }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Exception -> 0x003f }
            com.reown.android.pulse.model.properties.Properties r15 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x003f }
            long r16 = r4.getId()     // Catch:{ Exception -> 0x003f }
            java.lang.Long r24 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r16)     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r4 = r1.this$0     // Catch:{ Exception -> 0x003f }
            java.lang.String r25 = r4.clientId     // Catch:{ Exception -> 0x003f }
            com.reown.android.pulse.model.Direction r4 = com.reown.android.pulse.model.Direction.RECEIVED     // Catch:{ Exception -> 0x003f }
            java.lang.String r26 = r4.getState()     // Catch:{ Exception -> 0x003f }
            r23 = 0
            r27 = 0
            r28 = 2303(0x8ff, float:3.227E-42)
            r29 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r4 = r15
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ Exception -> 0x003f }
            r12.<init>(r13, r14, r4)     // Catch:{ Exception -> 0x003f }
            r1.L$0 = r8     // Catch:{ Exception -> 0x003f }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x003f }
            r1.L$1 = r4     // Catch:{ Exception -> 0x003f }
            r1.L$2 = r3     // Catch:{ Exception -> 0x003f }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x003f }
            r1.L$3 = r4     // Catch:{ Exception -> 0x003f }
            r1.label = r6     // Catch:{ Exception -> 0x003f }
            java.lang.Object r4 = r11.invoke(r12, r1)     // Catch:{ Exception -> 0x003f }
            if (r4 != r2) goto L_0x0287
            return r2
        L_0x0287:
            if (r3 == 0) goto L_0x0292
            java.lang.String r4 = r3.getUrl()     // Catch:{ Exception -> 0x003f }
            if (r4 != 0) goto L_0x0290
            goto L_0x0292
        L_0x0290:
            r11 = r4
            goto L_0x0299
        L_0x0292:
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ Exception -> 0x003f }
            java.lang.String r4 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, "<this>")     // Catch:{ Exception -> 0x003f }
            goto L_0x0290
        L_0x0299:
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r4 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x003f }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x003f }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003f }
            r12.<init>(r0)     // Catch:{ Exception -> 0x003f }
            r12.append(r9)     // Catch:{ Exception -> 0x003f }
            java.lang.String r0 = r12.toString()     // Catch:{ Exception -> 0x003f }
            r4.log((java.lang.String) r0)     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.android.verify.domain.ResolveAttestationIdUseCase r9 = r0.resolveAttestationIdUseCase     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r10 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.TransportType r0 = r10.getTransportType()     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.TransportType r4 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x003f }
            if (r0 != r4) goto L_0x02c3
            goto L_0x02c4
        L_0x02c3:
            r6 = 0
        L_0x02c4:
            java.lang.Boolean r12 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)     // Catch:{ Exception -> 0x003f }
            if (r3 == 0) goto L_0x02d6
            com.reown.android.internal.common.model.Redirect r0 = r3.getRedirect()     // Catch:{ Exception -> 0x003f }
            if (r0 == 0) goto L_0x02d6
            java.lang.String r0 = r0.getUniversal()     // Catch:{ Exception -> 0x003f }
            r13 = r0
            goto L_0x02d7
        L_0x02d6:
            r13 = r5
        L_0x02d7:
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r4 = r1.$params     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x003f }
            com.reown.sign.engine.use_case.requests.d r14 = new com.reown.sign.engine.use_case.requests.d     // Catch:{ Exception -> 0x003f }
            r14.<init>(r0, r4, r6, r3)     // Catch:{ Exception -> 0x003f }
            r9.invoke(r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x003f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02e8:
            com.reown.sign.engine.model.ValidationError$UnauthorizedMethod r0 = com.reown.sign.engine.model.ValidationError.UnauthorizedMethod.INSTANCE     // Catch:{ Exception -> 0x003f }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r9 = r13.jsonRpcInteractor     // Catch:{ Exception -> 0x003f }
            com.reown.sign.common.exceptions.PeerError r11 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x003f }
            r15 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r13 = 0
            r0 = 0
            r10 = r14
            r12 = r8
            r14 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x003f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003f }
            return r0
        L_0x0304:
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r4 = r1.this$0
            com.reown.foundation.util.Logger r4 = r4.logger
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()
            java.lang.String r8 = r3.getMessage()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r7)
            r9.append(r6)
            java.lang.String r6 = " - "
            r9.append(r6)
            r9.append(r8)
            java.lang.String r6 = r9.toString()
            r4.error((java.lang.String) r6)
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r4 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r4.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request
            com.reown.android.internal.common.exception.Uncategorized$GenericError r8 = new com.reown.android.internal.common.exception.Uncategorized$GenericError
            java.lang.String r4 = r3.getMessage()
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Cannot handle a session request: "
            r10.<init>(r11)
            r10.append(r4)
            java.lang.String r4 = ", topic: "
            r10.append(r4)
            r10.append(r9)
            java.lang.String r4 = r10.toString()
            r8.<init>(r4)
            r12 = 0
            r13 = 0
            r10 = 0
            r11 = 0
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r9 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase r4 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r4._events
            com.reown.android.internal.common.model.SDKError r6 = new com.reown.android.internal.common.model.SDKError
            r6.<init>(r3)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r1.L$1 = r0
            r1.L$2 = r5
            r1.L$3 = r5
            r3 = 2
            r1.label = r3
            java.lang.Object r0 = r4.emit(r6, r1)
            if (r0 != r2) goto L_0x0388
            return r2
        L_0x0388:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionRequestUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
