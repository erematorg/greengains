package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nOnSessionExtendUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionExtendUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionExtendUseCase$invoke$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,62:1\n125#2,7:63\n*S KotlinDebug\n*F\n+ 1 OnSessionExtendUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionExtendUseCase$invoke$2\n*L\n45#1:63,7\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase$invoke$2", f = "OnSessionExtendUseCase.kt", i = {0, 0, 0, 1, 1}, l = {54, 58}, m = "invokeSuspend", n = {"irnParams", "session", "newExpiry", "irnParams", "e"}, s = {"L$0", "L$1", "J$0", "L$0", "L$1"})
public final class OnSessionExtendUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WCRequest $request;
    final /* synthetic */ SignParams.ExtendParams $requestParams;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OnSessionExtendUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionExtendUseCase$invoke$2(WCRequest wCRequest, OnSessionExtendUseCase onSessionExtendUseCase, SignParams.ExtendParams extendParams, Continuation<? super OnSessionExtendUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onSessionExtendUseCase;
        this.$requestParams = extendParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionExtendUseCase$invoke$2(this.$request, this.this$0, this.$requestParams, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x023c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r1 = r18
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 2
            r4 = 1
            java.lang.String r5 = "Session extend received failure on topic: "
            if (r0 == 0) goto L_0x003a
            if (r0 == r4) goto L_0x0027
            if (r0 != r3) goto L_0x001f
            java.lang.Object r0 = r1.L$1
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x023d
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0027:
            java.lang.Object r0 = r1.L$1
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = (com.reown.sign.common.model.vo.sequence.SessionVO) r0
            java.lang.Object r0 = r1.L$0
            r4 = r0
            com.reown.android.internal.common.model.IrnParams r4 = (com.reown.android.internal.common.model.IrnParams) r4
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ Exception -> 0x0035 }
            goto L_0x0174
        L_0x0035:
            r0 = move-exception
        L_0x0036:
            r14 = r0
            r0 = r4
            goto L_0x01c2
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r19)
            com.reown.android.internal.common.model.IrnParams r15 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r7 = com.reown.android.internal.common.model.Tags.SESSION_EXTEND_RESPONSE
            com.reown.foundation.common.model.Ttl r8 = new com.reown.foundation.common.model.Ttl
            long r9 = com.reown.android.internal.utils.Time.getDayInSeconds()
            r8.<init>(r9)
            com.reown.android.internal.common.model.WCRequest r0 = r1.$request
            long r9 = r0.getId()
            java.lang.Long r9 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r9)
            r0 = 248(0xf8, float:3.48E-43)
            r16 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r6 = r15
            r19 = r15
            r15 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r0 = r1.this$0
            com.reown.foundation.util.Logger r0 = r0.logger
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r13 = "Session extend received on topic: "
            r7.<init>(r13)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r0.log((java.lang.String) r6)
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository     // Catch:{ Exception -> 0x01bd }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x01bd }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x01bd }
            boolean r0 = r0.isSessionValid(r6)     // Catch:{ Exception -> 0x01bd }
            if (r0 != 0) goto L_0x00da
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00d4 }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00d4 }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x00d4 }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x00d4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d4 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x00d4 }
            r6.append(r4)     // Catch:{ Exception -> 0x00d4 }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x00d4 }
            r0.error((java.lang.String) r4)     // Catch:{ Exception -> 0x00d4 }
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00d4 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x00d4 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x00d4 }
            com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic r8 = new com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic     // Catch:{ Exception -> 0x00d4 }
            java.lang.String r0 = "SESSION"
            com.reown.foundation.common.model.Topic r4 = r7.getTopic()     // Catch:{ Exception -> 0x00d4 }
            java.lang.String r4 = r4.getValue()     // Catch:{ Exception -> 0x00d4 }
            r8.<init>(r0, r4)     // Catch:{ Exception -> 0x00d4 }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r19
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00d4 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00d4 }
            return r0
        L_0x00d4:
            r0 = move-exception
            r14 = r0
            r0 = r19
            goto L_0x01c2
        L_0x00da:
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository     // Catch:{ Exception -> 0x01bd }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x01bd }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = r0.getSessionWithoutMetadataByTopic(r6)     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$ExtendParams r6 = r1.$requestParams     // Catch:{ Exception -> 0x01bd }
            long r14 = r6.getExpiry()     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.common.validator.SignValidator r6 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x01bd }
            com.reown.android.internal.common.model.Expiry r6 = r0.getExpiry()     // Catch:{ Exception -> 0x01bd }
            long r6 = r6.getSeconds()     // Catch:{ Exception -> 0x01bd }
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x01bd }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x01bd }
            long r10 = r14 - r6
            long r16 = com.reown.android.internal.utils.Time.getWeekInSeconds()     // Catch:{ Exception -> 0x01bd }
            int r6 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x010c
            int r6 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r6 <= 0) goto L_0x010f
        L_0x010c:
            r13 = r19
            goto L_0x017f
        L_0x010f:
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x017b }
            com.reown.sign.storage.sequence.SessionStorageRepository r6 = r6.sessionStorageRepository     // Catch:{ Exception -> 0x017b }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x017b }
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()     // Catch:{ Exception -> 0x017b }
            r6.extendSession(r7, r14)     // Catch:{ Exception -> 0x017b }
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x017b }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r6.jsonRpcInteractor     // Catch:{ Exception -> 0x017b }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x017b }
            r11 = 12
            r12 = 0
            r9 = 0
            r10 = 0
            r8 = r19
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithSuccess$default(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x017b }
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x017b }
            com.reown.foundation.util.Logger r6 = r6.logger     // Catch:{ Exception -> 0x017b }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x017b }
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()     // Catch:{ Exception -> 0x017b }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x017b }
            r8.<init>(r13)     // Catch:{ Exception -> 0x017b }
            r8.append(r7)     // Catch:{ Exception -> 0x017b }
            java.lang.String r7 = " - emitting"
            r8.append(r7)     // Catch:{ Exception -> 0x017b }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x017b }
            r6.log((java.lang.String) r7)     // Catch:{ Exception -> 0x017b }
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x017b }
            kotlinx.coroutines.flow.MutableSharedFlow r6 = r6._events     // Catch:{ Exception -> 0x017b }
            com.reown.android.internal.common.model.Expiry r7 = new com.reown.android.internal.common.model.Expiry     // Catch:{ Exception -> 0x017b }
            r7.<init>(r14)     // Catch:{ Exception -> 0x017b }
            com.reown.sign.engine.model.EngineDO$SessionExtend r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDOSessionExtend(r0, r7)     // Catch:{ Exception -> 0x017b }
            r13 = r19
            r1.L$0 = r13     // Catch:{ Exception -> 0x0177 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0177 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0177 }
            r1.J$0 = r14     // Catch:{ Exception -> 0x0177 }
            r1.label = r4     // Catch:{ Exception -> 0x0177 }
            java.lang.Object r0 = r6.emit(r7, r1)     // Catch:{ Exception -> 0x0177 }
            if (r0 != r2) goto L_0x0174
            return r2
        L_0x0174:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0177:
            r0 = move-exception
        L_0x0178:
            r14 = r0
            r0 = r13
            goto L_0x01c2
        L_0x017b:
            r0 = move-exception
            r13 = r19
            goto L_0x0178
        L_0x017f:
            com.reown.sign.engine.model.ValidationError$InvalidExtendRequest r0 = com.reown.sign.engine.model.ValidationError.InvalidExtendRequest.INSTANCE     // Catch:{ Exception -> 0x01b9 }
            com.reown.foundation.util.Logger r4 = r8.logger     // Catch:{ Exception -> 0x01b9 }
            com.reown.foundation.common.model.Topic r6 = r9.getTopic()     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            r7.<init>(r5)     // Catch:{ Exception -> 0x01b9 }
            r7.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = " - invalid request: "
            r7.append(r6)     // Catch:{ Exception -> 0x01b9 }
            r7.append(r0)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x01b9 }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x01b9 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r8.jsonRpcInteractor     // Catch:{ Exception -> 0x01b9 }
            com.reown.sign.common.exceptions.PeerError r8 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x01b9 }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r0 = 0
            r7 = r9
            r9 = r13
            r4 = r13
            r13 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0035 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0035 }
            return r0
        L_0x01b9:
            r0 = move-exception
            r4 = r13
            goto L_0x0036
        L_0x01bd:
            r0 = move-exception
            r4 = r19
            goto L_0x0036
        L_0x01c2:
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r4 = r1.this$0
            com.reown.foundation.util.Logger r4 = r4.logger
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r7.append(r6)
            java.lang.String r5 = ": "
            r7.append(r5)
            r7.append(r14)
            java.lang.String r5 = r7.toString()
            r4.error((java.lang.String) r5)
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r4 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r4 = r4.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r5 = r1.$request
            com.reown.android.internal.common.exception.Uncategorized$GenericError r6 = new com.reown.android.internal.common.exception.Uncategorized$GenericError
            java.lang.String r7 = r14.getMessage()
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Cannot update a session: "
            r9.<init>(r10)
            r9.append(r7)
            java.lang.String r7 = ", topic: "
            r9.append(r7)
            r9.append(r8)
            java.lang.String r7 = r9.toString()
            r6.<init>(r7)
            r12 = 120(0x78, float:1.68E-43)
            r13 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase r4 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r4._events
            com.reown.android.internal.common.model.SDKError r5 = new com.reown.android.internal.common.model.SDKError
            r5.<init>(r14)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r1.L$1 = r0
            r1.label = r3
            java.lang.Object r0 = r4.emit(r5, r1)
            if (r0 != r2) goto L_0x023d
            return r2
        L_0x023d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionExtendUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
