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

@SourceDebugExtension({"SMAP\nOnSessionUpdateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionUpdateUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionUpdateUseCase$invoke$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,79:1\n44#2,13:80\n*S KotlinDebug\n*F\n+ 1 OnSessionUpdateUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionUpdateUseCase$invoke$2\n*L\n52#1:80,13\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase$invoke$2", f = "OnSessionUpdateUseCase.kt", i = {0, 0, 1, 1}, l = {67, 75}, m = "invokeSuspend", n = {"irnParams", "session", "irnParams", "e"}, s = {"L$0", "L$1", "L$0", "L$1"})
public final class OnSessionUpdateUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.UpdateNamespacesParams $params;
    final /* synthetic */ WCRequest $request;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OnSessionUpdateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionUpdateUseCase$invoke$2(WCRequest wCRequest, OnSessionUpdateUseCase onSessionUpdateUseCase, SignParams.UpdateNamespacesParams updateNamespacesParams, Continuation<? super OnSessionUpdateUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onSessionUpdateUseCase;
        this.$params = updateNamespacesParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionUpdateUseCase$invoke$2(this.$request, this.this$0, this.$params, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x0572 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 2
            r4 = 1
            java.lang.String r5 = "Session update received failure on topic: "
            if (r0 == 0) goto L_0x003a
            if (r0 == r4) goto L_0x0027
            if (r0 != r3) goto L_0x001f
            java.lang.Object r0 = r1.L$1
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x0573
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
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ Exception -> 0x0035 }
            goto L_0x04ed
        L_0x0035:
            r0 = move-exception
            r14 = r0
            r0 = r4
            goto L_0x04f8
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r18)
            com.reown.android.internal.common.model.IrnParams r15 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r7 = com.reown.android.internal.common.model.Tags.SESSION_UPDATE_RESPONSE
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
            r18 = r15
            r15 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0
            com.reown.foundation.util.Logger r0 = r0.logger
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r13 = "Session update received on topic: "
            r7.<init>(r13)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r0.log((java.lang.String) r6)
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x04f4 }
            boolean r0 = r0.isSessionValid(r6)     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r6 = "SESSION"
            if (r0 != 0) goto L_0x00e0
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = " - invalid session"
            r7.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r0.error((java.lang.String) r4)     // Catch:{ Exception -> 0x00da }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r0 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic r8 = new com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r4 = r7.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = r4.getValue()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r6, r4)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r6 = r0
            r9 = r18
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x00da:
            r0 = move-exception
            r14 = r0
            r0 = r18
            goto L_0x04f8
        L_0x00e0:
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.storage.sequence.SessionStorageRepository r0 = r0.sessionStorageRepository     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = r0.getSessionWithoutMetadataByTopic(r7)     // Catch:{ Exception -> 0x04f4 }
            boolean r7 = r0.isPeerController()     // Catch:{ Exception -> 0x04f4 }
            if (r7 != 0) goto L_0x0133
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = " - unauthorized peer"
            r7.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r0.error((java.lang.String) r4)     // Catch:{ Exception -> 0x00da }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r0 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Unauthorized$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Unauthorized$UpdateRequest     // Catch:{ Exception -> 0x00da }
            r8.<init>(r6)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r6 = r0
            r9 = r18
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0133:
            com.reown.sign.common.validator.SignValidator r6 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$UpdateNamespacesParams r7 = r1.$params     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r7 = r7.getNamespaces()     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r8 = r0.getRequiredNamespaces()     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r10 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            boolean r11 = r7.isEmpty()     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r12 = " - namespaces validation: "
            if (r11 == 0) goto L_0x0188
            com.reown.sign.engine.model.ValidationError$EmptyNamespaces r0 = com.reown.sign.engine.model.ValidationError.EmptyNamespaces.INSTANCE     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0188:
            boolean r11 = r6.areNamespacesKeysProperlyFormatted(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x01cb
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r0 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x01cb:
            boolean r11 = r6.areChainsNotEmpty(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x0213
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = "Chains must not be empty"
            r0.<init>(r4)     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0213:
            boolean r11 = r6.areChainIdsValid(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x025b
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = "Chains must be CAIP-2 compliant"
            r0.<init>(r4)     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x025b:
            boolean r11 = r6.areChainsInMatchingNamespace(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x02a3
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = "Chains must be defined in matching namespace"
            r0.<init>(r4)     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x02a3:
            boolean r11 = r6.areAccountIdsValid(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x02eb
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = "Accounts must be CAIP-10 compliant"
            r0.<init>(r4)     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x02eb:
            boolean r11 = r6.areAccountsInMatchingNamespaceAndChains(r7)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x0333
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = "Accounts must be defined in matching namespace"
            r0.<init>(r4)     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0333:
            java.util.Set r11 = r7.keySet()     // Catch:{ Exception -> 0x04f4 }
            java.util.Set r14 = r8.keySet()     // Catch:{ Exception -> 0x04f4 }
            boolean r11 = r6.areAllNamespacesApproved(r11, r14)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x037e
            com.reown.sign.engine.model.ValidationError$UserRejected r0 = com.reown.sign.engine.model.ValidationError.UserRejected.INSTANCE     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x037e:
            java.util.Map r11 = r6.allMethodsWithChains(r7)     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r14 = r6.allMethodsWithChains(r8)     // Catch:{ Exception -> 0x04f4 }
            boolean r11 = r6.areAllMethodsApproved(r11, r14)     // Catch:{ Exception -> 0x04f4 }
            if (r11 != 0) goto L_0x03c9
            com.reown.sign.engine.model.ValidationError$UserRejectedMethods r0 = com.reown.sign.engine.model.ValidationError.UserRejectedMethods.INSTANCE     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x03c9:
            java.util.Map r7 = r6.allEventsWithChains(r7)     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r8 = r6.allEventsWithChains(r8)     // Catch:{ Exception -> 0x04f4 }
            boolean r6 = r6.areAllEventsApproved(r7, r8)     // Catch:{ Exception -> 0x04f4 }
            if (r6 != 0) goto L_0x0414
            com.reown.sign.engine.model.ValidationError$UserRejectedEvents r0 = com.reown.sign.engine.model.ValidationError.UserRejectedEvents.INSTANCE     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r4 = r9.logger     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r6 = r10.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r7.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r7.append(r6)     // Catch:{ Exception -> 0x00da }
            r7.append(r12)     // Catch:{ Exception -> 0x00da }
            r7.append(r0)     // Catch:{ Exception -> 0x00da }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00da }
            r4.error((java.lang.String) r6)     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00da }
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r7 = r10
            r9 = r18
            r10 = r0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0414:
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.storage.sequence.SessionStorageRepository r6 = r6.sessionStorageRepository     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r7 = r0.getTopic()     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r7 = r7.getValue()     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            long r8 = r8.getId()     // Catch:{ Exception -> 0x04f4 }
            long r8 = com.reown.utils.UtilFunctionsKt.extractTimestamp(r8)     // Catch:{ Exception -> 0x04f4 }
            boolean r6 = r6.isUpdatedNamespaceValid(r7, r8)     // Catch:{ Exception -> 0x04f4 }
            if (r6 != 0) goto L_0x0470
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x00da }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
            r6.<init>(r5)     // Catch:{ Exception -> 0x00da }
            r6.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = " - Update Namespace Request ID too old"
            r6.append(r4)     // Catch:{ Exception -> 0x00da }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x00da }
            r0.error((java.lang.String) r4)     // Catch:{ Exception -> 0x00da }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x00da }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x00da }
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r8 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest     // Catch:{ Exception -> 0x00da }
            java.lang.String r0 = "Update Namespace Request ID too old"
            r8.<init>(r0)     // Catch:{ Exception -> 0x00da }
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r18
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x00da }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00da }
            return r0
        L_0x0470:
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.storage.sequence.SessionStorageRepository r6 = r6.sessionStorageRepository     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r7 = r0.getTopic()     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r7 = r7.getValue()     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$UpdateNamespacesParams r8 = r1.$params     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r8 = r8.getNamespaces()     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            long r9 = r9.getId()     // Catch:{ Exception -> 0x04f4 }
            r6.deleteNamespaceAndInsertNewNamespace(r7, r8, r9)     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r6.jsonRpcInteractor     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            r11 = 12
            r12 = 0
            r9 = 0
            r10 = 0
            r8 = r18
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithSuccess$default(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.util.Logger r6 = r6.logger     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()     // Catch:{ Exception -> 0x04f4 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04f4 }
            r8.<init>(r13)     // Catch:{ Exception -> 0x04f4 }
            r8.append(r7)     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r7 = " - emitting"
            r8.append(r7)     // Catch:{ Exception -> 0x04f4 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x04f4 }
            r6.log((java.lang.String) r7)     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x04f4 }
            kotlinx.coroutines.flow.MutableSharedFlow r6 = r6._events     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.engine.model.EngineDO$SessionUpdateNamespaces r7 = new com.reown.sign.engine.model.EngineDO$SessionUpdateNamespaces     // Catch:{ Exception -> 0x04f4 }
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request     // Catch:{ Exception -> 0x04f4 }
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()     // Catch:{ Exception -> 0x04f4 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$UpdateNamespacesParams r9 = r1.$params     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r9 = r9.getNamespaces()     // Catch:{ Exception -> 0x04f4 }
            java.util.Map r9 = com.reown.sign.engine.model.mapper.EngineMapperKt.toMapOfEngineNamespacesSession(r9)     // Catch:{ Exception -> 0x04f4 }
            r7.<init>(r8, r9)     // Catch:{ Exception -> 0x04f4 }
            r8 = r18
            r1.L$0 = r8     // Catch:{ Exception -> 0x04f0 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x04f0 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x04f0 }
            r1.label = r4     // Catch:{ Exception -> 0x04f0 }
            java.lang.Object r0 = r6.emit(r7, r1)     // Catch:{ Exception -> 0x04f0 }
            if (r0 != r2) goto L_0x04ed
            return r2
        L_0x04ed:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x04f0:
            r0 = move-exception
        L_0x04f1:
            r14 = r0
            r0 = r8
            goto L_0x04f8
        L_0x04f4:
            r0 = move-exception
            r8 = r18
            goto L_0x04f1
        L_0x04f8:
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r4 = r1.this$0
            com.reown.foundation.util.Logger r4 = r4.logger
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r7.append(r6)
            java.lang.String r5 = " - "
            r7.append(r5)
            r7.append(r14)
            java.lang.String r5 = r7.toString()
            r4.error((java.lang.String) r5)
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r4 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r4 = r4.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r5 = r1.$request
            com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest r6 = new com.reown.sign.common.exceptions.PeerError$Invalid$UpdateRequest
            java.lang.String r7 = r14.getMessage()
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Updating Namespace Failed. Review Namespace structure. Error: "
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
            com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase r4 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r4._events
            com.reown.android.internal.common.model.SDKError r5 = new com.reown.android.internal.common.model.SDKError
            r5.<init>(r14)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r1.L$1 = r0
            r1.label = r3
            java.lang.Object r0 = r4.emit(r5, r1)
            if (r0 != r2) goto L_0x0573
            return r2
        L_0x0573:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionUpdateUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
