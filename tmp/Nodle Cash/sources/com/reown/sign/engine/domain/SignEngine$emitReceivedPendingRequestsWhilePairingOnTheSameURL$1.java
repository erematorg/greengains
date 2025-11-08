package com.reown.sign.engine.domain;

import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nSignEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,522:1\n774#2:523\n865#2,2:524\n1869#2,2:526\n*S KotlinDebug\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1\n*L\n459#1:523\n459#1:524,2\n461#1:526,2\n*E\n"})
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\n"}, d2 = {"<anonymous>", "", "<destruct>", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/Topic;", "", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1", f = "SignEngine.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, l = {459, 462, 483, 492}, m = "invokeSuspend", n = {"<destruct>", "pairingTopic", "trace", "<destruct>", "pairingTopic", "trace", "pendingAuthenticateRequests", "$this$forEach$iv", "element$iv", "request", "$i$f$forEach", "$i$a$-forEach-SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$1", "<destruct>", "pairingTopic", "trace", "pendingAuthenticateRequests", "proposal", "<destruct>", "pairingTopic", "trace", "pendingAuthenticateRequests", "proposal"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$7", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
public final class SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1 extends SuspendLambda implements Function2<Pair<? extends Topic, ? extends List<String>>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1(SignEngine signEngine, Continuation<? super SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1 signEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1 = new SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1(this.this$0, continuation);
        signEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1.L$0 = obj;
        return signEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v13, resolved type: com.reown.sign.common.model.Request} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.reown.foundation.common.model.Topic} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ed A[Catch:{ Exception -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x013b A[Catch:{ Exception -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x02a0 A[Catch:{ Exception -> 0x0032 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r29) {
        /*
            r28 = this;
            r1 = r28
            java.lang.Object r0 = r1.L$0
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r3 == 0) goto L_0x0086
            if (r3 == r8) goto L_0x0078
            if (r3 == r7) goto L_0x0052
            if (r3 == r6) goto L_0x003d
            if (r3 != r5) goto L_0x0035
            java.lang.Object r0 = r1.L$4
            com.reown.sign.common.model.vo.proposal.ProposalVO r0 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r0
            java.lang.Object r2 = r1.L$3
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$2
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r3 = r1.L$1
            com.reown.foundation.common.model.Topic r3 = (com.reown.foundation.common.model.Topic) r3
            kotlin.ResultKt.throwOnFailure(r29)     // Catch:{ Exception -> 0x0032 }
            r4 = r0
            r0 = r29
            goto L_0x029c
        L_0x0032:
            r0 = move-exception
            goto L_0x02f7
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003d:
            java.lang.Object r0 = r1.L$4
            com.reown.sign.common.model.vo.proposal.ProposalVO r0 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r0
            java.lang.Object r2 = r1.L$3
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$2
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$1
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            kotlin.ResultKt.throwOnFailure(r29)     // Catch:{ Exception -> 0x0032 }
            goto L_0x024e
        L_0x0052:
            int r3 = r1.I$0
            java.lang.Object r5 = r1.L$8
            com.reown.sign.common.model.Request r5 = (com.reown.sign.common.model.Request) r5
            java.lang.Object r6 = r1.L$6
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r8 = r1.L$5
            com.reown.sign.engine.domain.SignEngine r8 = (com.reown.sign.engine.domain.SignEngine) r8
            java.lang.Object r10 = r1.L$4
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.lang.Object r11 = r1.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r1.L$2
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r1.L$1
            com.reown.foundation.common.model.Topic r13 = (com.reown.foundation.common.model.Topic) r13
            kotlin.ResultKt.throwOnFailure(r29)     // Catch:{ Exception -> 0x0032 }
            r4 = r29
            r9 = 0
            goto L_0x0137
        L_0x0078:
            java.lang.Object r3 = r1.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r10 = r1.L$1
            com.reown.foundation.common.model.Topic r10 = (com.reown.foundation.common.model.Topic) r10
            kotlin.ResultKt.throwOnFailure(r29)     // Catch:{ Exception -> 0x0032 }
            r11 = r29
            goto L_0x00af
        L_0x0086:
            kotlin.ResultKt.throwOnFailure(r29)
            java.lang.Object r3 = r0.component1()
            r10 = r3
            com.reown.foundation.common.model.Topic r10 = (com.reown.foundation.common.model.Topic) r10
            java.lang.Object r3 = r0.component2()
            java.util.List r3 = (java.util.List) r3
            com.reown.sign.engine.domain.SignEngine r11 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.use_case.calls.GetPendingAuthenticateRequestUseCaseInterface r11 = r11.getPendingAuthenticateRequestUseCase     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0032 }
            r1.L$0 = r12     // Catch:{ Exception -> 0x0032 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0032 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0032 }
            r1.label = r8     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r11 = r11.getPendingAuthenticateRequests(r1)     // Catch:{ Exception -> 0x0032 }
            if (r11 != r2) goto L_0x00af
            return r2
        L_0x00af:
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0032 }
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ Exception -> 0x0032 }
            r15.<init>()     // Catch:{ Exception -> 0x0032 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Exception -> 0x0032 }
        L_0x00ba:
            boolean r12 = r11.hasNext()     // Catch:{ Exception -> 0x0032 }
            if (r12 == 0) goto L_0x00d5
            java.lang.Object r12 = r11.next()     // Catch:{ Exception -> 0x0032 }
            r13 = r12
            com.reown.sign.common.model.Request r13 = (com.reown.sign.common.model.Request) r13     // Catch:{ Exception -> 0x0032 }
            com.reown.foundation.common.model.Topic r13 = r13.getTopic()     // Catch:{ Exception -> 0x0032 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0032 }
            if (r13 == 0) goto L_0x00ba
            r15.add(r12)     // Catch:{ Exception -> 0x0032 }
            goto L_0x00ba
        L_0x00d5:
            boolean r11 = r15.isEmpty()     // Catch:{ Exception -> 0x0032 }
            if (r11 != 0) goto L_0x01d1
            com.reown.sign.engine.domain.SignEngine r5 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            java.util.Iterator r6 = r15.iterator()     // Catch:{ Exception -> 0x0032 }
            r12 = r3
            r8 = r5
            r13 = r10
            r10 = r15
            r11 = r10
            r3 = 0
        L_0x00e7:
            boolean r5 = r6.hasNext()     // Catch:{ Exception -> 0x0032 }
            if (r5 == 0) goto L_0x01cd
            java.lang.Object r5 = r6.next()     // Catch:{ Exception -> 0x0032 }
            r14 = r5
            com.reown.sign.common.model.Request r14 = (com.reown.sign.common.model.Request) r14     // Catch:{ Exception -> 0x0032 }
            com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository r15 = r8.verifyContextStorageRepository     // Catch:{ Exception -> 0x0032 }
            r29 = r5
            long r4 = r14.getId()     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0032 }
            r1.L$0 = r9     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x0032 }
            r1.L$1 = r9     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x0032 }
            r1.L$2 = r9     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch:{ Exception -> 0x0032 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x0032 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0032 }
            r1.L$5 = r8     // Catch:{ Exception -> 0x0032 }
            r1.L$6 = r6     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r29)     // Catch:{ Exception -> 0x0032 }
            r1.L$7 = r9     // Catch:{ Exception -> 0x0032 }
            r1.L$8 = r14     // Catch:{ Exception -> 0x0032 }
            r1.I$0 = r3     // Catch:{ Exception -> 0x0032 }
            r9 = 0
            r1.I$1 = r9     // Catch:{ Exception -> 0x0032 }
            r1.label = r7     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r4 = r15.get(r4, r1)     // Catch:{ Exception -> 0x0032 }
            if (r4 != r2) goto L_0x0136
            return r2
        L_0x0136:
            r5 = r14
        L_0x0137:
            com.reown.android.verify.model.VerifyContext r4 = (com.reown.android.verify.model.VerifyContext) r4     // Catch:{ Exception -> 0x0032 }
            if (r4 != 0) goto L_0x0153
            com.reown.android.verify.model.VerifyContext r4 = new com.reown.android.verify.model.VerifyContext     // Catch:{ Exception -> 0x0032 }
            long r15 = r5.getId()     // Catch:{ Exception -> 0x0032 }
            kotlin.jvm.internal.StringCompanionObject r14 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ Exception -> 0x0032 }
            java.lang.String r17 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, "<this>")     // Catch:{ Exception -> 0x0032 }
            com.reown.android.internal.common.model.Validation r18 = com.reown.android.internal.common.model.Validation.UNKNOWN     // Catch:{ Exception -> 0x0032 }
            java.lang.String r19 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, "<this>")     // Catch:{ Exception -> 0x0032 }
            r20 = 0
            r14 = r4
            r14.<init>(r15, r17, r18, r19, r20)     // Catch:{ Exception -> 0x0032 }
        L_0x0153:
            com.reown.sign.engine.model.EngineDO$SessionAuthenticateEvent r15 = new com.reown.sign.engine.model.EngineDO$SessionAuthenticateEvent     // Catch:{ Exception -> 0x0032 }
            long r16 = r5.getId()     // Catch:{ Exception -> 0x0032 }
            com.reown.foundation.common.model.Topic r14 = r5.getTopic()     // Catch:{ Exception -> 0x0032 }
            java.lang.String r18 = r14.getValue()     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r14 = r5.getParams()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r14 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r14     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.clientsync.common.PayloadParams r14 = r14.getAuthPayload()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.model.EngineDO$PayloadParams r19 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDO((com.reown.sign.common.model.vo.clientsync.common.PayloadParams) r14)     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r14 = r5.getParams()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r14 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r14     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r14 = r14.getRequester()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.model.EngineDO$Participant r20 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, "<this>")     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r5 = r5.getParams()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r5 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r5     // Catch:{ Exception -> 0x0032 }
            long r21 = r5.getExpiryTimestamp()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.model.EngineDO$VerifyContext r4 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, "<this>")     // Catch:{ Exception -> 0x0032 }
            r14 = r15
            r5 = r15
            r15 = r16
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r22 = r4
            r14.<init>(r15, r17, r18, r19, r20, r22)     // Catch:{ Exception -> 0x0032 }
            com.reown.foundation.util.Logger r4 = r8.logger     // Catch:{ Exception -> 0x0032 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0032 }
            r14.<init>()     // Catch:{ Exception -> 0x0032 }
            java.lang.String r15 = "Emitting pending authenticate request from active pairing: "
            r14.append(r15)     // Catch:{ Exception -> 0x0032 }
            r14.append(r5)     // Catch:{ Exception -> 0x0032 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0032 }
            r4.log((java.lang.String) r14)     // Catch:{ Exception -> 0x0032 }
            kotlinx.coroutines.CoroutineScope r15 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$1$1 r4 = new com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$1$1     // Catch:{ Exception -> 0x0032 }
            r14 = 0
            r4.<init>(r8, r5, r14)     // Catch:{ Exception -> 0x0032 }
            r19 = 3
            r20 = 0
            r16 = 0
            r17 = 0
            r18 = r4
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r15, r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x0032 }
            goto L_0x00e7
        L_0x01cd:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0032 }
            goto L_0x0321
        L_0x01d1:
            com.reown.sign.engine.domain.SignEngine r4 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.storage.proposal.ProposalStorageRepository r4 = r4.proposalStorageRepository     // Catch:{ Exception -> 0x0032 }
            java.lang.String r7 = r10.getValue()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.common.model.vo.proposal.ProposalVO r4 = r4.getProposalByTopic$sign_release(r7)     // Catch:{ Exception -> 0x0032 }
            com.reown.android.internal.common.model.Expiry r7 = r4.getExpiry()     // Catch:{ Exception -> 0x0032 }
            if (r7 == 0) goto L_0x0270
            com.reown.android.internal.utils.CoreValidator r9 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x0032 }
            boolean r7 = r9.isExpired(r7)     // Catch:{ Exception -> 0x0032 }
            if (r7 != r8) goto L_0x0270
            com.reown.sign.engine.domain.SignEngine r5 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r5 = r5.insertEventUseCase     // Catch:{ Exception -> 0x0032 }
            com.reown.android.pulse.model.properties.Props r7 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0032 }
            java.lang.String r8 = "PROPOSAL_EXPIRED"
            com.reown.android.pulse.model.properties.Properties r9 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0032 }
            java.lang.String r20 = r10.getValue()     // Catch:{ Exception -> 0x0032 }
            r25 = 3903(0xf3f, float:5.469E-42)
            r26 = 0
            r13 = 0
            r14 = 0
            r11 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r12 = r9
            r27 = r15
            r15 = r11
            r19 = r3
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)     // Catch:{ Exception -> 0x0032 }
            r20 = 1
            r21 = 0
            r17 = 0
            r16 = r7
            r18 = r8
            r19 = r9
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0032 }
            r1.L$0 = r0     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x0032 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0032 }
            r1.L$2 = r0     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x0032 }
            r1.L$3 = r0     // Catch:{ Exception -> 0x0032 }
            r1.L$4 = r4     // Catch:{ Exception -> 0x0032 }
            r1.label = r6     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = r5.invoke(r7, r1)     // Catch:{ Exception -> 0x0032 }
            if (r0 != r2) goto L_0x024d
            return r2
        L_0x024d:
            r0 = r4
        L_0x024e:
            com.reown.sign.engine.domain.SignEngine r2 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.storage.proposal.ProposalStorageRepository r2 = r2.proposalStorageRepository     // Catch:{ Exception -> 0x0032 }
            java.lang.String r3 = r0.getProposerPublicKey()     // Catch:{ Exception -> 0x0032 }
            r2.deleteProposal$sign_release(r3)     // Catch:{ Exception -> 0x0032 }
            kotlinx.coroutines.CoroutineScope r4 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$2 r7 = new com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$2     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine r2 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            r3 = 0
            r7.<init>(r2, r0, r3)     // Catch:{ Exception -> 0x0032 }
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0032 }
            goto L_0x0321
        L_0x0270:
            r27 = r15
            com.reown.sign.engine.domain.SignEngine r6 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository r6 = r6.verifyContextStorageRepository     // Catch:{ Exception -> 0x0032 }
            long r7 = r4.getRequestId()     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0032 }
            r1.L$0 = r0     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x0032 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0032 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x0032 }
            r1.L$3 = r0     // Catch:{ Exception -> 0x0032 }
            r1.L$4 = r4     // Catch:{ Exception -> 0x0032 }
            r1.label = r5     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r0 = r6.get(r7, r1)     // Catch:{ Exception -> 0x0032 }
            if (r0 != r2) goto L_0x029b
            return r2
        L_0x029b:
            r2 = r3
        L_0x029c:
            com.reown.android.verify.model.VerifyContext r0 = (com.reown.android.verify.model.VerifyContext) r0     // Catch:{ Exception -> 0x0032 }
            if (r0 != 0) goto L_0x02b7
            com.reown.android.verify.model.VerifyContext r0 = new com.reown.android.verify.model.VerifyContext     // Catch:{ Exception -> 0x0032 }
            long r6 = r4.getRequestId()     // Catch:{ Exception -> 0x0032 }
            kotlin.jvm.internal.StringCompanionObject r3 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ Exception -> 0x0032 }
            java.lang.String r8 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, "<this>")     // Catch:{ Exception -> 0x0032 }
            com.reown.android.internal.common.model.Validation r9 = com.reown.android.internal.common.model.Validation.UNKNOWN     // Catch:{ Exception -> 0x0032 }
            java.lang.String r10 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, "<this>")     // Catch:{ Exception -> 0x0032 }
            r11 = 0
            r5 = r0
            r5.<init>(r6, r8, r9, r10, r11)     // Catch:{ Exception -> 0x0032 }
        L_0x02b7:
            com.reown.sign.engine.model.EngineDO$SessionProposalEvent r3 = new com.reown.sign.engine.model.EngineDO$SessionProposalEvent     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.model.EngineDO$SessionProposal r4 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDO((com.reown.sign.common.model.vo.proposal.ProposalVO) r4)     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.model.EngineDO$VerifyContext r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")     // Catch:{ Exception -> 0x0032 }
            r3.<init>(r4, r0)     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine r0 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x0032 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0032 }
            r4.<init>()     // Catch:{ Exception -> 0x0032 }
            java.lang.String r5 = "Emitting session proposal from active pairing: "
            r4.append(r5)     // Catch:{ Exception -> 0x0032 }
            r4.append(r3)     // Catch:{ Exception -> 0x0032 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0032 }
            r0.log((java.lang.String) r4)     // Catch:{ Exception -> 0x0032 }
            java.lang.String r0 = "emit_session_proposal"
            r2.add(r0)     // Catch:{ Exception -> 0x0032 }
            kotlinx.coroutines.CoroutineScope r4 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$3 r7 = new com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$3     // Catch:{ Exception -> 0x0032 }
            com.reown.sign.engine.domain.SignEngine r0 = r1.this$0     // Catch:{ Exception -> 0x0032 }
            r2 = 0
            r7.<init>(r0, r3, r2)     // Catch:{ Exception -> 0x0032 }
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0032 }
            goto L_0x0321
        L_0x02f7:
            com.reown.sign.engine.domain.SignEngine r2 = r1.this$0
            com.reown.foundation.util.Logger r2 = r2.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "No proposal or pending session authenticate request for pairing topic: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.log((java.lang.String) r3)
            kotlinx.coroutines.CoroutineScope r4 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()
            com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$4 r7 = new com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1$4
            com.reown.sign.engine.domain.SignEngine r1 = r1.this$0
            r2 = 0
            r7.<init>(r1, r0, r2)
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
        L_0x0321:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.domain.SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(Pair<Topic, ? extends List<String>> pair, Continuation<? super Unit> continuation) {
        return ((SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
