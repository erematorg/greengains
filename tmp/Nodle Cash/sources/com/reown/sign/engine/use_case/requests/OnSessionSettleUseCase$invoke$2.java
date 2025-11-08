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

@SourceDebugExtension({"SMAP\nOnSessionSettleUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionSettleUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionSettleUseCase$invoke$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,101:1\n1#2:102\n44#3,13:103\n*S KotlinDebug\n*F\n+ 1 OnSessionSettleUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionSettleUseCase$invoke$2\n*L\n69#1:103,13\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase$invoke$2", f = "OnSessionSettleUseCase.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {91, 97}, m = "invokeSuspend", n = {"sessionTopic", "irnParams", "selfPublicKey", "peerMetadata", "proposal", "requiredNamespaces", "optionalNamespaces", "properties", "session", "sessionTopic", "irnParams", "selfPublicKey", "peerMetadata", "proposal", "requiredNamespaces", "optionalNamespaces", "properties", "e"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
public final class OnSessionSettleUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WCRequest $request;
    final /* synthetic */ SignParams.SessionSettleParams $settleParams;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ OnSessionSettleUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionSettleUseCase$invoke$2(OnSessionSettleUseCase onSessionSettleUseCase, WCRequest wCRequest, SignParams.SessionSettleParams sessionSettleParams, Continuation<? super OnSessionSettleUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionSettleUseCase;
        this.$request = wCRequest;
        this.$settleParams = sessionSettleParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionSettleUseCase$invoke$2(this.this$0, this.$request, this.$settleParams, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v67, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: com.reown.sign.common.model.vo.proposal.ProposalVO} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v71, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: com.reown.foundation.common.model.Topic} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x04f5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x055d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r33) {
        /*
            r32 = this;
            r1 = r32
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 1
            java.lang.String r4 = "Session settle received failure: "
            r5 = 2
            java.lang.String r6 = ", error: "
            if (r0 == 0) goto L_0x007b
            if (r0 == r3) goto L_0x0045
            if (r0 != r5) goto L_0x003d
            java.lang.Object r0 = r1.L$8
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$7
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r1.L$6
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r1.L$5
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r1.L$4
            com.reown.sign.common.model.vo.proposal.ProposalVO r0 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r0
            java.lang.Object r0 = r1.L$3
            com.reown.android.internal.common.model.AppMetaData r0 = (com.reown.android.internal.common.model.AppMetaData) r0
            java.lang.Object r0 = r1.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$1
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            java.lang.Object r0 = r1.L$0
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            kotlin.ResultKt.throwOnFailure(r33)
            goto L_0x055e
        L_0x003d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0045:
            java.lang.Object r0 = r1.L$8
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = (com.reown.sign.common.model.vo.sequence.SessionVO) r0
            java.lang.Object r0 = r1.L$7
            r3 = r0
            java.util.Map r3 = (java.util.Map) r3
            java.lang.Object r0 = r1.L$6
            r7 = r0
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r0 = r1.L$5
            r8 = r0
            java.util.Map r8 = (java.util.Map) r8
            java.lang.Object r0 = r1.L$4
            r9 = r0
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r0 = r1.L$3
            r10 = r0
            com.reown.android.internal.common.model.AppMetaData r10 = (com.reown.android.internal.common.model.AppMetaData) r10
            java.lang.Object r0 = r1.L$2
            r11 = r0
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r0 = r1.L$1
            r12 = r0
            com.reown.android.internal.common.model.IrnParams r12 = (com.reown.android.internal.common.model.IrnParams) r12
            java.lang.Object r0 = r1.L$0
            r13 = r0
            com.reown.foundation.common.model.Topic r13 = (com.reown.foundation.common.model.Topic) r13
            kotlin.ResultKt.throwOnFailure(r33)     // Catch:{ Exception -> 0x0076 }
            goto L_0x0498
        L_0x0076:
            r0 = move-exception
            r21 = r4
            goto L_0x04b0
        L_0x007b:
            kotlin.ResultKt.throwOnFailure(r33)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r0 = r1.this$0
            com.reown.foundation.util.Logger r0 = r0.logger
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Session settle received on topic: "
            r8.<init>(r9)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r0.log((java.lang.String) r7)
            com.reown.android.internal.common.model.WCRequest r0 = r1.$request
            com.reown.foundation.common.model.Topic r7 = r0.getTopic()
            com.reown.android.internal.common.model.IrnParams r8 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r11 = com.reown.android.internal.common.model.Tags.SESSION_SETTLE_RESPONSE
            com.reown.foundation.common.model.Ttl r12 = new com.reown.foundation.common.model.Ttl
            long r13 = com.reown.android.internal.utils.Time.getFiveMinutesInSeconds()
            r12.<init>(r13)
            com.reown.android.internal.common.model.WCRequest r0 = r1.$request
            long r13 = r0.getId()
            java.lang.Long r13 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r13)
            r19 = 248(0xf8, float:3.48E-43)
            r20 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r10 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x05ae }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r0 = r0.crypto     // Catch:{ Exception -> 0x05ae }
            java.lang.String r15 = r0.m8729getSelfPublicFromKeyAgreementp9DwDrs(r7)     // Catch:{ Exception -> 0x05ae }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionSettleParams r0 = r1.$settleParams
            com.reown.sign.common.model.vo.clientsync.common.SessionParticipant r0 = r0.getController()
            com.reown.android.internal.common.model.AppMetaData r14 = r0.getMetadata()
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0561 }
            com.reown.sign.storage.proposal.ProposalStorageRepository r0 = r0.proposalStorageRepository     // Catch:{ Exception -> 0x0561 }
            com.reown.sign.common.model.vo.proposal.ProposalVO r13 = r0.getProposalByKey$sign_release(r15)     // Catch:{ Exception -> 0x0561 }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0561 }
            com.reown.sign.storage.proposal.ProposalStorageRepository r0 = r0.proposalStorageRepository     // Catch:{ Exception -> 0x0561 }
            r0.deleteProposal$sign_release(r15)     // Catch:{ Exception -> 0x0561 }
            kotlin.Triple r0 = new kotlin.Triple
            java.util.Map r10 = r13.getRequiredNamespaces()
            java.util.Map r11 = r13.getOptionalNamespaces()
            java.util.Map r12 = r13.getProperties()
            r0.<init>(r10, r11, r12)
            java.lang.Object r10 = r0.component1()
            r12 = r10
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r10 = r0.component2()
            r18 = r10
            java.util.Map r18 = (java.util.Map) r18
            java.lang.Object r0 = r0.component3()
            r19 = r0
            java.util.Map r19 = (java.util.Map) r19
            com.reown.sign.common.validator.SignValidator r0 = com.reown.sign.common.validator.SignValidator.INSTANCE
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionSettleParams r10 = r1.$settleParams
            java.util.Map r10 = r10.getNamespaces()
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r11 = r1.this$0
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request
            boolean r16 = r10.isEmpty()
            java.lang.String r5 = "Session settle received failure - namespace validation: "
            if (r16 == 0) goto L_0x0164
            com.reown.sign.engine.model.ValidationError$EmptyNamespaces r0 = com.reown.sign.engine.model.ValidationError.EmptyNamespaces.INSTANCE
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0164:
            boolean r16 = r0.areNamespacesKeysProperlyFormatted(r10)
            if (r16 != 0) goto L_0x01a3
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r0 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01a3:
            boolean r16 = r0.areChainsNotEmpty(r10)
            if (r16 != 0) goto L_0x01e7
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r1 = "Chains must not be empty"
            r0.<init>(r1)
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01e7:
            boolean r16 = r0.areChainIdsValid(r10)
            if (r16 != 0) goto L_0x022b
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r1 = "Chains must be CAIP-2 compliant"
            r0.<init>(r1)
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x022b:
            boolean r16 = r0.areChainsInMatchingNamespace(r10)
            if (r16 != 0) goto L_0x026f
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r1 = "Chains must be defined in matching namespace"
            r0.<init>(r1)
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x026f:
            boolean r16 = r0.areAccountIdsValid(r10)
            if (r16 != 0) goto L_0x02b3
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains
            java.lang.String r1 = "Accounts must be CAIP-10 compliant"
            r0.<init>(r1)
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02b3:
            boolean r16 = r0.areAccountsInMatchingNamespaceAndChains(r10)
            if (r16 != 0) goto L_0x02f7
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains
            java.lang.String r1 = "Accounts must be defined in matching namespace"
            r0.<init>(r1)
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02f7:
            r33 = r14
            java.util.Set r14 = r10.keySet()
            r21 = r4
            java.util.Set r4 = r12.keySet()
            boolean r4 = r0.areAllNamespacesApproved(r14, r4)
            if (r4 != 0) goto L_0x0342
            com.reown.sign.engine.model.ValidationError$UserRejected r0 = com.reown.sign.engine.model.ValidationError.UserRejected.INSTANCE
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0342:
            java.util.Map r4 = r0.allMethodsWithChains(r10)
            java.util.Map r14 = r0.allMethodsWithChains(r12)
            boolean r4 = r0.areAllMethodsApproved(r4, r14)
            if (r4 != 0) goto L_0x0389
            com.reown.sign.engine.model.ValidationError$UserRejectedMethods r0 = com.reown.sign.engine.model.ValidationError.UserRejectedMethods.INSTANCE
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0389:
            java.util.Map r4 = r0.allEventsWithChains(r10)
            java.util.Map r10 = r0.allEventsWithChains(r12)
            boolean r0 = r0.areAllEventsApproved(r4, r10)
            if (r0 != 0) goto L_0x03d0
            com.reown.sign.engine.model.ValidationError$UserRejectedEvents r0 = com.reown.sign.engine.model.ValidationError.UserRejectedEvents.INSTANCE
            com.reown.foundation.util.Logger r1 = r11.logger
            com.reown.foundation.common.model.Topic r2 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r5)
            r4.append(r2)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r2 = r4.toString()
            r1.error((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r11.jsonRpcInteractor
            com.reown.sign.common.exceptions.PeerError r12 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r3
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x03d0:
            com.reown.sign.common.model.vo.sequence.SessionVO$Companion r10 = com.reown.sign.common.model.vo.sequence.SessionVO.Companion     // Catch:{ Exception -> 0x04a8 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionSettleParams r0 = r1.$settleParams     // Catch:{ Exception -> 0x04a8 }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r3 = r1.this$0     // Catch:{ Exception -> 0x04a8 }
            com.reown.android.internal.common.model.AppMetaData r14 = r3.selfAppMetaData     // Catch:{ Exception -> 0x04a8 }
            com.reown.foundation.common.model.Topic r3 = r13.getPairingTopic()     // Catch:{ Exception -> 0x04a8 }
            java.lang.String r17 = r3.getValue()     // Catch:{ Exception -> 0x04a8 }
            r11 = r7
            r3 = r12
            r12 = r0
            r4 = r13
            r13 = r15
            r5 = r33
            r22 = r15
            r15 = r3
            r16 = r18
            com.reown.sign.common.model.vo.sequence.SessionVO r0 = r10.m8879createAcknowledgedSessionOl7oN5g$sign_release(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x049b }
            com.reown.sign.storage.sequence.SessionStorageRepository r10 = r10.sessionStorageRepository     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request     // Catch:{ Exception -> 0x049b }
            long r11 = r11.getId()     // Catch:{ Exception -> 0x049b }
            r10.insertSession(r0, r11)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x049b }
            com.reown.android.pairing.handler.PairingControllerInterface r10 = r10.pairingController     // Catch:{ Exception -> 0x049b }
            com.reown.android.Core$Params$UpdateMetadata r11 = new com.reown.android.Core$Params$UpdateMetadata     // Catch:{ Exception -> 0x049b }
            com.reown.foundation.common.model.Topic r12 = r4.getPairingTopic()     // Catch:{ Exception -> 0x049b }
            java.lang.String r12 = r12.getValue()     // Catch:{ Exception -> 0x049b }
            com.reown.android.Core$Model$AppMetaData r13 = com.reown.android.utils.ExtensionsKt.toClient(r5)     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.model.AppMetaDataType r14 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x049b }
            r11.<init>(r12, r13, r14)     // Catch:{ Exception -> 0x049b }
            r12 = 0
            r13 = 2
            com.reown.android.pairing.handler.PairingControllerInterface.updateMetadata$default(r10, r11, r12, r13, r12)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r10 = r10.metadataStorageRepository     // Catch:{ Exception -> 0x049b }
            r10.insertOrAbortMetadata(r7, r5, r14)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r10.jsonRpcInteractor     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request     // Catch:{ Exception -> 0x049b }
            r15 = 12
            r16 = 0
            r13 = 0
            r14 = 0
            r12 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithSuccess$default(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x049b }
            com.reown.foundation.util.Logger r10 = r10.logger     // Catch:{ Exception -> 0x049b }
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request     // Catch:{ Exception -> 0x049b }
            com.reown.foundation.common.model.Topic r11 = r11.getTopic()     // Catch:{ Exception -> 0x049b }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x049b }
            r12.<init>(r9)     // Catch:{ Exception -> 0x049b }
            r12.append(r11)     // Catch:{ Exception -> 0x049b }
            java.lang.String r9 = " - emitting"
            r12.append(r9)     // Catch:{ Exception -> 0x049b }
            java.lang.String r9 = r12.toString()     // Catch:{ Exception -> 0x049b }
            r10.log((java.lang.String) r9)     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x049b }
            kotlinx.coroutines.flow.MutableSharedFlow r9 = r9._events     // Catch:{ Exception -> 0x049b }
            com.reown.sign.engine.model.EngineDO$SessionApproved r10 = com.reown.sign.engine.model.mapper.EngineMapperKt.toSessionApproved(r0)     // Catch:{ Exception -> 0x049b }
            r1.L$0 = r7     // Catch:{ Exception -> 0x049b }
            r1.L$1 = r8     // Catch:{ Exception -> 0x049b }
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ Exception -> 0x049b }
            r1.L$2 = r11     // Catch:{ Exception -> 0x049b }
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x049b }
            r1.L$3 = r11     // Catch:{ Exception -> 0x049b }
            r1.L$4 = r4     // Catch:{ Exception -> 0x049b }
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x049b }
            r1.L$5 = r11     // Catch:{ Exception -> 0x049b }
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)     // Catch:{ Exception -> 0x049b }
            r1.L$6 = r11     // Catch:{ Exception -> 0x049b }
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ Exception -> 0x049b }
            r1.L$7 = r11     // Catch:{ Exception -> 0x049b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x049b }
            r1.L$8 = r0     // Catch:{ Exception -> 0x049b }
            r0 = 1
            r1.label = r0     // Catch:{ Exception -> 0x049b }
            java.lang.Object r0 = r9.emit(r10, r1)     // Catch:{ Exception -> 0x049b }
            if (r0 != r2) goto L_0x0498
            return r2
        L_0x0498:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x049b:
            r0 = move-exception
        L_0x049c:
            r9 = r4
            r10 = r5
            r13 = r7
            r12 = r8
            r7 = r18
            r11 = r22
            r8 = r3
            r3 = r19
            goto L_0x04b0
        L_0x04a8:
            r0 = move-exception
            r5 = r33
            r3 = r12
            r4 = r13
            r22 = r15
            goto L_0x049c
        L_0x04b0:
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r4 = r1.this$0
            com.reown.foundation.util.Logger r4 = r4.logger
            com.reown.android.internal.common.model.WCRequest r5 = r1.$request
            com.reown.foundation.common.model.Topic r5 = r5.getTopic()
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r15 = r21
            r14.<init>(r15)
            r14.append(r5)
            r14.append(r6)
            r14.append(r0)
            java.lang.String r5 = r14.toString()
            r4.error((java.lang.String) r5)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r4 = r1.this$0
            com.reown.sign.storage.proposal.ProposalStorageRepository r4 = r4.proposalStorageRepository
            r4.insertProposal$sign_release(r9)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r4 = r1.this$0
            com.reown.sign.storage.sequence.SessionStorageRepository r4 = r4.sessionStorageRepository
            r4.deleteSession(r13)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r4 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r22 = r4.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r4 = r1.$request
            com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed r5 = new com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed
            java.lang.String r6 = r0.getMessage()
            if (r6 != 0) goto L_0x04fb
            kotlin.jvm.internal.StringCompanionObject r6 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, "<this>")
        L_0x04fb:
            r5.<init>(r6)
            r30 = 120(0x78, float:1.68E-43)
            r31 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r23 = r4
            r24 = r5
            r25 = r12
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r4 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r4._events
            com.reown.android.internal.common.model.SDKError r5 = new com.reown.android.internal.common.model.SDKError
            r5.<init>(r0)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r1.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r1.L$1 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r1.L$2 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r1.L$3 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r1.L$4 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r1.L$5 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r1.L$6 = r6
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r1.L$7 = r3
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$8 = r0
            r3 = 2
            r1.label = r3
            java.lang.Object r0 = r4.emit(r5, r1)
            if (r0 != r2) goto L_0x055e
            return r2
        L_0x055e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0561:
            r0 = move-exception
            r15 = r4
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r2 = r1.this$0
            com.reown.foundation.util.Logger r2 = r2.logger
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request
            com.reown.foundation.common.model.Topic r3 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r15)
            r4.append(r3)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r3 = r4.toString()
            r2.error((java.lang.String) r3)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r2 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r2.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request
            com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed r12 = new com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed
            java.lang.String r0 = r0.getMessage()
            if (r0 != 0) goto L_0x059a
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
        L_0x059a:
            r12.<init>(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x05ae:
            r0 = move-exception
            r15 = r4
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r2 = r1.this$0
            com.reown.foundation.util.Logger r2 = r2.logger
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request
            com.reown.foundation.common.model.Topic r3 = r3.getTopic()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r15)
            r4.append(r3)
            r4.append(r6)
            r4.append(r0)
            java.lang.String r3 = r4.toString()
            r2.error((java.lang.String) r3)
            com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase r2 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r2.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r11 = r1.$request
            com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed r12 = new com.reown.sign.common.exceptions.PeerError$Failure$SessionSettlementFailed
            java.lang.String r0 = r0.getMessage()
            if (r0 != 0) goto L_0x05e7
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
        L_0x05e7:
            r12.<init>(r0)
            r18 = 120(0x78, float:1.68E-43)
            r19 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r13 = r8
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionSettleUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
