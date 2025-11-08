package com.reown.sign.engine.use_case.calls;

import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nSessionRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionRequestUseCase$sessionRequest$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,217:1\n108#2,6:218\n85#2,6:224\n*S KotlinDebug\n*F\n+ 1 SessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionRequestUseCase$sessionRequest$2\n*L\n88#1:218,6\n95#1:224,6\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionRequestUseCase$sessionRequest$2", f = "SessionRequestUseCase.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, l = {111, 113, 118, 123}, m = "invokeSuspend", n = {"$this$supervisorScope", "session", "expiry", "namespaces", "params", "sessionPayload", "walletServiceUrl", "nowInSeconds", "$this$supervisorScope", "session", "expiry", "namespaces", "params", "sessionPayload", "walletServiceUrl", "response", "jsonRpcResult", "nowInSeconds", "$this$supervisorScope", "session", "expiry", "namespaces", "params", "sessionPayload", "walletServiceUrl", "e", "jsonRpcResult", "nowInSeconds", "$this$supervisorScope", "session", "expiry", "namespaces", "params", "sessionPayload", "walletServiceUrl", "nowInSeconds"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0"})
public final class SessionRequestUseCase$sessionRequest$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function1<Long, Unit> $onSuccess;
    final /* synthetic */ EngineDO.Request $request;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ SessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionRequestUseCase$sessionRequest$2(SessionRequestUseCase sessionRequestUseCase, EngineDO.Request request, Function1<? super Throwable, Unit> function1, Function1<? super Long, Unit> function12, Continuation<? super SessionRequestUseCase$sessionRequest$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionRequestUseCase;
        this.$request = request;
        this.$onFailure = function1;
        this.$onSuccess = function12;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SessionRequestUseCase$sessionRequest$2 sessionRequestUseCase$sessionRequest$2 = new SessionRequestUseCase$sessionRequest$2(this.this$0, this.$request, this.$onFailure, this.$onSuccess, continuation);
        sessionRequestUseCase$sessionRequest$2.L$0 = obj;
        return sessionRequestUseCase$sessionRequest$2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v77, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v78, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v79, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Session>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v88, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v89, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v90, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace$Session>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0370  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x03e7 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r34) {
        /*
            r33 = this;
            r7 = r33
            java.lang.Object r0 = r7.L$0
            r8 = r0
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r7.label
            r1 = 4
            r10 = 3
            r11 = 2
            r12 = 1
            if (r0 == 0) goto L_0x00a8
            if (r0 == r12) goto L_0x0079
            if (r0 == r11) goto L_0x0049
            if (r0 == r10) goto L_0x0024
            if (r0 != r1) goto L_0x001c
            goto L_0x002c
        L_0x001c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0024:
            java.lang.Object r0 = r7.L$8
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcError r0 = (com.reown.sign.engine.model.EngineDO.JsonRpcResponse.JsonRpcError) r0
            java.lang.Object r0 = r7.L$7
            java.lang.Exception r0 = (java.lang.Exception) r0
        L_0x002c:
            java.lang.Object r0 = r7.L$6
            java.net.URL r0 = (java.net.URL) r0
            java.lang.Object r0 = r7.L$5
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest r0 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest) r0
            java.lang.Object r0 = r7.L$4
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r0 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r0
            java.lang.Object r0 = r7.L$3
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r7.L$2
            com.reown.android.internal.common.model.Expiry r0 = (com.reown.android.internal.common.model.Expiry) r0
            java.lang.Object r0 = r7.L$1
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = (com.reown.sign.common.model.vo.sequence.SessionVO) r0
            kotlin.ResultKt.throwOnFailure(r34)
            goto L_0x047d
        L_0x0049:
            long r1 = r7.J$0
            java.lang.Object r0 = r7.L$8
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcResult r0 = (com.reown.sign.engine.model.EngineDO.JsonRpcResponse.JsonRpcResult) r0
            java.lang.Object r0 = r7.L$7
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r7.L$6
            r3 = r0
            java.net.URL r3 = (java.net.URL) r3
            java.lang.Object r0 = r7.L$5
            r4 = r0
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest r4 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest) r4
            java.lang.Object r0 = r7.L$4
            r5 = r0
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r5 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r5
            java.lang.Object r0 = r7.L$3
            r6 = r0
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r0 = r7.L$2
            r11 = r0
            com.reown.android.internal.common.model.Expiry r11 = (com.reown.android.internal.common.model.Expiry) r11
            java.lang.Object r0 = r7.L$1
            r12 = r0
            com.reown.sign.common.model.vo.sequence.SessionVO r12 = (com.reown.sign.common.model.vo.sequence.SessionVO) r12
            kotlin.ResultKt.throwOnFailure(r34)     // Catch:{ Exception -> 0x0076 }
            goto L_0x047d
        L_0x0076:
            r0 = move-exception
            goto L_0x034b
        L_0x0079:
            long r1 = r7.J$0
            java.lang.Object r0 = r7.L$6
            r3 = r0
            java.net.URL r3 = (java.net.URL) r3
            java.lang.Object r0 = r7.L$5
            r4 = r0
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest r4 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest) r4
            java.lang.Object r0 = r7.L$4
            r5 = r0
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r5 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r5
            java.lang.Object r0 = r7.L$3
            r6 = r0
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r0 = r7.L$2
            r12 = r0
            com.reown.android.internal.common.model.Expiry r12 = (com.reown.android.internal.common.model.Expiry) r12
            java.lang.Object r0 = r7.L$1
            r13 = r0
            com.reown.sign.common.model.vo.sequence.SessionVO r13 = (com.reown.sign.common.model.vo.sequence.SessionVO) r13
            kotlin.ResultKt.throwOnFailure(r34)     // Catch:{ Exception -> 0x00a3 }
            r14 = r1
            r11 = r12
            r12 = r13
            r1 = r34
            goto L_0x02b9
        L_0x00a3:
            r0 = move-exception
            r11 = r12
            r12 = r13
            goto L_0x034b
        L_0x00a8:
            kotlin.ResultKt.throwOnFailure(r34)
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository
            com.reown.foundation.common.model.Topic r2 = new com.reown.foundation.common.model.Topic
            com.reown.sign.engine.model.EngineDO$Request r3 = r7.$request
            java.lang.String r3 = r3.getTopic()
            r2.<init>(r3)
            boolean r0 = r0.isSessionValid(r2)
            if (r0 != 0) goto L_0x00db
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r7.$onFailure
            com.reown.android.internal.common.exception.CannotFindSequenceForTopic r1 = new com.reown.android.internal.common.exception.CannotFindSequenceForTopic
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            java.lang.String r2 = r2.getTopic()
            java.lang.String r3 = "Cannot find sequence for given topic: "
            java.lang.String r2 = androidx.browser.trusted.c.a(r3, r2)
            r1.<init>(r2)
            r0.invoke(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00db:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository
            com.reown.foundation.common.model.Topic r2 = new com.reown.foundation.common.model.Topic
            com.reown.sign.engine.model.EngineDO$Request r3 = r7.$request
            java.lang.String r3 = r3.getTopic()
            r2.<init>(r3)
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = r0.getSessionWithoutMetadataByTopic(r2)
            r13 = r0
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r2 = r7.this$0
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r2 = r2.metadataStorageRepository
            com.reown.foundation.common.model.Topic r0 = r0.getTopic()
            com.reown.android.internal.common.model.AppMetaDataType r3 = com.reown.android.internal.common.model.AppMetaDataType.PEER
            com.reown.android.internal.common.model.AppMetaData r22 = r2.getByTopicAndType(r0, r3)
            r31 = 130815(0x1feff, float:1.83311E-40)
            r32 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            com.reown.sign.common.model.vo.sequence.SessionVO r13 = com.reown.sign.common.model.vo.sequence.SessionVO.m8871copypMwxKLQ$default(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            long r14 = com.reown.android.internal.utils.Time.getCurrentTimeInSeconds()
            com.reown.android.internal.utils.CoreValidator r0 = com.reown.android.internal.utils.CoreValidator.INSTANCE
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            com.reown.android.internal.common.model.Expiry r2 = r2.getExpiry()
            boolean r2 = r0.isExpiryWithinBounds(r2)
            r3 = 0
            if (r2 != 0) goto L_0x0153
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0
            com.reown.foundation.util.Logger r0 = r0.logger
            java.lang.String r1 = "Sending session request error: expiry not within bounds"
            r0.error((java.lang.String) r1)
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r7.$onFailure
            com.reown.android.internal.common.exception.InvalidExpiryException r1 = new com.reown.android.internal.common.exception.InvalidExpiryException
            r1.<init>(r3, r12, r3)
            r0.invoke(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0153:
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            com.reown.android.internal.common.model.Expiry r2 = r2.getExpiry()
            if (r2 != 0) goto L_0x016a
            com.reown.android.internal.common.model.Expiry r2 = new com.reown.android.internal.common.model.Expiry
            long r4 = com.reown.android.internal.utils.Time.getCurrentTimeInSeconds()
            long r16 = com.reown.android.internal.utils.Time.getFiveMinutesInSeconds()
            long r4 = r16 + r4
            r2.<init>(r4)
        L_0x016a:
            r16 = r2
            com.reown.sign.common.validator.SignValidator r2 = com.reown.sign.common.validator.SignValidator.INSTANCE
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r4 = r7.this$0
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r5 = r7.$onFailure
            java.lang.String r6 = r2.getParams()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x017f
            goto L_0x01aa
        L_0x017f:
            java.lang.String r6 = r2.getMethod()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x018a
            goto L_0x01aa
        L_0x018a:
            java.lang.String r6 = r2.getChainId()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0195
            goto L_0x01aa
        L_0x0195:
            java.lang.String r6 = r2.getTopic()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x01a0
            goto L_0x01aa
        L_0x01a0:
            java.lang.String r2 = r2.getChainId()
            boolean r0 = r0.isChainIdCAIP2Compliant(r2)
            if (r0 != 0) goto L_0x01d4
        L_0x01aa:
            com.reown.sign.engine.model.ValidationError$InvalidSessionRequest r0 = com.reown.sign.engine.model.ValidationError.InvalidSessionRequest.INSTANCE
            com.reown.foundation.util.Logger r1 = r4.logger
            java.lang.String r2 = r0.getMessage()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Sending session request error: invalid session request, "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.error((java.lang.String) r2)
            com.reown.sign.common.exceptions.InvalidRequestException r1 = new com.reown.sign.common.exceptions.InvalidRequestException
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            r5.invoke(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01d4:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository
            com.reown.foundation.common.model.Topic r2 = new com.reown.foundation.common.model.Topic
            com.reown.sign.engine.model.EngineDO$Request r4 = r7.$request
            java.lang.String r4 = r4.getTopic()
            r2.<init>(r4)
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = r0.getSessionWithoutMetadataByTopic(r2)
            java.util.Map r6 = r0.getSessionNamespaces()
            com.reown.sign.common.validator.SignValidator r0 = com.reown.sign.common.validator.SignValidator.INSTANCE
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            java.lang.String r2 = r2.getChainId()
            com.reown.sign.engine.model.EngineDO$Request r4 = r7.$request
            java.lang.String r4 = r4.getMethod()
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r5 = r7.this$0
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r1 = r7.$onFailure
            java.util.Map r0 = r0.allMethodsWithChains(r6)
            java.lang.Object r18 = r0.get(r4)
            if (r18 == 0) goto L_0x0480
            java.lang.Object r0 = r0.get(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.util.List r0 = (java.util.List) r0
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L_0x021a
            goto L_0x0480
        L_0x021a:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r5 = new com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams
            com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO r0 = new com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO
            com.reown.sign.engine.model.EngineDO$Request r1 = r7.$request
            java.lang.String r1 = r1.getMethod()
            com.reown.sign.engine.model.EngineDO$Request r2 = r7.$request
            java.lang.String r2 = r2.getParams()
            long r18 = r16.getSeconds()
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r18)
            r0.<init>(r1, r2, r4)
            com.reown.sign.engine.model.EngineDO$Request r1 = r7.$request
            java.lang.String r1 = r1.getChainId()
            r5.<init>(r0, r1)
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest r4 = new com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest
            r24 = 7
            r25 = 0
            r19 = 0
            r21 = 0
            r22 = 0
            r18 = r4
            r23 = r5
            r18.<init>(r19, r21, r22, r23, r24, r25)
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0     // Catch:{ Exception -> 0x025f }
            com.reown.sign.engine.domain.wallet_service.WalletServiceFinder r0 = r0.walletServiceFinder     // Catch:{ Exception -> 0x025f }
            com.reown.sign.engine.model.EngineDO$Request r1 = r7.$request     // Catch:{ Exception -> 0x025f }
            java.net.URL r0 = r0.findMatchingWalletService(r1, r13)     // Catch:{ Exception -> 0x025f }
            r2 = r0
            goto L_0x0260
        L_0x025f:
            r2 = r3
        L_0x0260:
            if (r2 == 0) goto L_0x03e8
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase$sessionRequest$2$response$1 r0 = new com.reown.sign.engine.use_case.calls.SessionRequestUseCase$sessionRequest$2$response$1     // Catch:{ Exception -> 0x033e }
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r1 = r7.this$0     // Catch:{ Exception -> 0x033e }
            r0.<init>(r1, r4, r2, r3)     // Catch:{ Exception -> 0x033e }
            r17 = 3
            r18 = 0
            r3 = 0
            r19 = 0
            r1 = r8
            r20 = r2
            r2 = r3
            r3 = r19
            r10 = r4
            r4 = r0
            r11 = r5
            r5 = r17
            r21 = r6
            r6 = r18
            kotlinx.coroutines.Deferred r1 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x0332 }
            r7.L$0 = r2     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x0332 }
            r7.L$1 = r2     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)     // Catch:{ Exception -> 0x0332 }
            r7.L$2 = r2     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)     // Catch:{ Exception -> 0x0332 }
            r7.L$3 = r2     // Catch:{ Exception -> 0x0332 }
            r7.L$4 = r11     // Catch:{ Exception -> 0x0332 }
            r7.L$5 = r10     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)     // Catch:{ Exception -> 0x0332 }
            r7.L$6 = r2     // Catch:{ Exception -> 0x0332 }
            r7.J$0 = r14     // Catch:{ Exception -> 0x0332 }
            r7.label = r12     // Catch:{ Exception -> 0x0332 }
            java.lang.Object r1 = r1.await(r7)     // Catch:{ Exception -> 0x0332 }
            if (r1 != r9) goto L_0x02b0
            return r9
        L_0x02b0:
            r4 = r10
            r5 = r11
            r12 = r13
            r11 = r16
            r3 = r20
            r6 = r21
        L_0x02b9:
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0330 }
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcResult r2 = new com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcResult     // Catch:{ Exception -> 0x0330 }
            long r21 = r4.getId()     // Catch:{ Exception -> 0x0330 }
            r25 = 2
            r26 = 0
            r23 = 0
            r20 = r2
            r24 = r1
            r20.<init>(r21, r23, r24, r25, r26)     // Catch:{ Exception -> 0x0330 }
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r10 = r7.this$0     // Catch:{ Exception -> 0x0330 }
            kotlinx.coroutines.flow.MutableSharedFlow r10 = r10._events     // Catch:{ Exception -> 0x0330 }
            com.reown.sign.engine.model.EngineDO$SessionPayloadResponse r13 = new com.reown.sign.engine.model.EngineDO$SessionPayloadResponse     // Catch:{ Exception -> 0x0330 }
            com.reown.sign.engine.model.EngineDO$Request r0 = r7.$request     // Catch:{ Exception -> 0x0330 }
            java.lang.String r0 = r0.getTopic()     // Catch:{ Exception -> 0x0330 }
            r18 = r9
            java.lang.String r9 = r5.getChainId()     // Catch:{ Exception -> 0x032b }
            r16 = r10
            com.reown.sign.engine.model.EngineDO$Request r10 = r7.$request     // Catch:{ Exception -> 0x032b }
            java.lang.String r10 = r10.getMethod()     // Catch:{ Exception -> 0x032b }
            r13.<init>(r0, r9, r10, r2)     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x032b }
            r7.L$0 = r0     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x032b }
            r7.L$1 = r0     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch:{ Exception -> 0x032b }
            r7.L$2 = r0     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x032b }
            r7.L$3 = r0     // Catch:{ Exception -> 0x032b }
            r7.L$4 = r5     // Catch:{ Exception -> 0x032b }
            r7.L$5 = r4     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x032b }
            r7.L$6 = r0     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)     // Catch:{ Exception -> 0x032b }
            r7.L$7 = r0     // Catch:{ Exception -> 0x032b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x032b }
            r7.L$8 = r0     // Catch:{ Exception -> 0x032b }
            r7.J$0 = r14     // Catch:{ Exception -> 0x032b }
            r0 = 2
            r7.label = r0     // Catch:{ Exception -> 0x032b }
            r0 = r16
            java.lang.Object r0 = r0.emit(r13, r7)     // Catch:{ Exception -> 0x032b }
            r9 = r18
            if (r0 != r9) goto L_0x047d
            return r9
        L_0x032b:
            r0 = move-exception
            r9 = r18
        L_0x032e:
            r1 = r14
            goto L_0x034b
        L_0x0330:
            r0 = move-exception
            goto L_0x032e
        L_0x0332:
            r0 = move-exception
            r4 = r10
            r5 = r11
            r12 = r13
            r1 = r14
            r11 = r16
            r3 = r20
            r6 = r21
            goto L_0x034b
        L_0x033e:
            r0 = move-exception
            r20 = r2
            r10 = r4
            r11 = r5
            r21 = r6
            r12 = r13
            r1 = r14
            r11 = r16
            r3 = r20
        L_0x034b:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r10 = r7.this$0
            com.reown.foundation.util.Logger r10 = r10.logger
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "Sending session request error: "
            r13.<init>(r14)
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            r10.error((java.lang.String) r13)
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcError r10 = new com.reown.sign.engine.model.EngineDO$JsonRpcResponse$JsonRpcError
            long r21 = r4.getId()
            com.reown.sign.engine.model.EngineDO$JsonRpcResponse$Error r13 = new com.reown.sign.engine.model.EngineDO$JsonRpcResponse$Error
            java.lang.String r14 = r0.getMessage()
            if (r14 != 0) goto L_0x0372
            java.lang.String r14 = ""
        L_0x0372:
            r15 = 0
            r13.<init>(r15, r14)
            r25 = 2
            r26 = 0
            r23 = 0
            r20 = r10
            r24 = r13
            r20.<init>(r21, r23, r24, r25, r26)
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r13 = r7.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r13 = r13._events
            com.reown.sign.engine.model.EngineDO$SessionPayloadResponse r14 = new com.reown.sign.engine.model.EngineDO$SessionPayloadResponse
            com.reown.sign.engine.model.EngineDO$Request r15 = r7.$request
            java.lang.String r15 = r15.getTopic()
            r18 = r9
            java.lang.String r9 = r5.getChainId()
            r34 = r13
            com.reown.sign.engine.model.EngineDO$Request r13 = r7.$request
            java.lang.String r13 = r13.getMethod()
            r14.<init>(r15, r9, r13, r10)
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r7.L$0 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r7.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r7.L$2 = r8
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r7.L$3 = r6
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r7.L$4 = r5
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r7.L$5 = r4
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r7.L$6 = r3
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$7 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r7.L$8 = r0
            r7.J$0 = r1
            r1 = 3
            r7.label = r1
            r0 = r34
            java.lang.Object r0 = r0.emit(r14, r7)
            r9 = r18
            if (r0 != r9) goto L_0x047d
            return r9
        L_0x03e8:
            r20 = r2
            r10 = r4
            r11 = r5
            r21 = r6
            com.reown.android.internal.common.model.TransportType r0 = r13.getTransportType()
            com.reown.android.internal.common.model.TransportType r1 = com.reown.android.internal.common.model.TransportType.LINK_MODE
            if (r0 != r1) goto L_0x0464
            java.lang.Boolean r0 = r13.getPeerLinkMode()
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x0464
            java.lang.String r0 = r13.getPeerAppLink()
            if (r0 == 0) goto L_0x0455
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0411
            goto L_0x0455
        L_0x0411:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r2 = r7.this$0
            com.reown.sign.engine.model.EngineDO$Request r4 = r7.$request
            java.lang.String r5 = r13.getPeerAppLink()
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r6 = r7.$onFailure
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r7.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r7.L$1 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)
            r7.L$2 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)
            r7.L$3 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r7.L$4 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r7.L$5 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)
            r7.L$6 = r0
            r7.J$0 = r14
            r0 = 4
            r7.label = r0
            r1 = r2
            r3 = r10
            r7 = r33
            java.lang.Object r0 = r1.triggerLinkModeRequest(r2, r3, r4, r5, r6, r7)
            if (r0 != r9) goto L_0x047d
            return r9
        L_0x0455:
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r7.$onFailure
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "App link is missing"
            r1.<init>(r2)
            r0.invoke(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0464:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = r7.this$0
            com.reown.sign.engine.model.EngineDO$Request r1 = r7.$request
            kotlin.jvm.functions.Function1<java.lang.Long, kotlin.Unit> r2 = r7.$onSuccess
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r3 = r7.$onFailure
            r4 = r14
            r14 = r0
            r15 = r16
            r16 = r4
            r18 = r10
            r19 = r1
            r20 = r2
            r21 = r3
            r14.triggerRelayRequest(r15, r16, r18, r19, r20, r21)
        L_0x047d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0480:
            com.reown.sign.engine.model.ValidationError$UnauthorizedMethod r0 = com.reown.sign.engine.model.ValidationError.UnauthorizedMethod.INSTANCE
            com.reown.foundation.util.Logger r2 = r5.logger
            java.lang.String r3 = r0.getMessage()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Sending session request error: unauthorized method, "
            r4.<init>(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r2.error((java.lang.String) r3)
            com.reown.sign.common.exceptions.UnauthorizedMethodException r2 = new com.reown.sign.common.exceptions.UnauthorizedMethodException
            java.lang.String r0 = r0.getMessage()
            r2.<init>(r0)
            r1.invoke(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionRequestUseCase$sessionRequest$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionRequestUseCase$sessionRequest$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
