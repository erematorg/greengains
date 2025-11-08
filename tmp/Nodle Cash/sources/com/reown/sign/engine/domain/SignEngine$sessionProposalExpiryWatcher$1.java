package com.reown.sign.engine.domain;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nSignEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$sessionProposalExpiryWatcher$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,522:1\n2756#2:523\n1#3:524\n*S KotlinDebug\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$sessionProposalExpiryWatcher$1\n*L\n420#1:523\n420#1:524\n*E\n"})
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$sessionProposalExpiryWatcher$1", f = "SignEngine.kt", i = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {419, 424, 425}, m = "invokeSuspend", n = {"$this$onEach$iv", "$this$onEach_u24lambda_u2418$iv", "element$iv", "proposal", "it", "$i$f$onEach", "$i$a$-apply-CollectionsKt___CollectionsKt$onEach$1$iv", "$i$a$-onEach-SignEngine$sessionProposalExpiryWatcher$1$1", "$i$a$-let-SignEngine$sessionProposalExpiryWatcher$1$1$1", "$this$onEach$iv", "$this$onEach_u24lambda_u2418$iv", "element$iv", "proposal", "it", "$i$f$onEach", "$i$a$-apply-CollectionsKt___CollectionsKt$onEach$1$iv", "$i$a$-onEach-SignEngine$sessionProposalExpiryWatcher$1$1", "$i$a$-let-SignEngine$sessionProposalExpiryWatcher$1$1$1"}, s = {"L$0", "L$3", "L$5", "L$6", "L$7", "I$0", "I$1", "I$2", "I$3", "L$0", "L$3", "L$5", "L$6", "L$7", "I$0", "I$1", "I$2", "I$3"})
public final class SignEngine$sessionProposalExpiryWatcher$1 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$sessionProposalExpiryWatcher$1(SignEngine signEngine, Continuation<? super SignEngine$sessionProposalExpiryWatcher$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignEngine$sessionProposalExpiryWatcher$1(this.this$0, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ad A[Catch:{ Exception -> 0x003b }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0155 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r1 = r18
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0087
            if (r2 == r5) goto L_0x0081
            if (r2 == r4) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            int r2 = r1.I$1
            int r5 = r1.I$0
            java.lang.Object r7 = r1.L$7
            com.reown.android.internal.common.model.Expiry r7 = (com.reown.android.internal.common.model.Expiry) r7
            java.lang.Object r7 = r1.L$6
            com.reown.sign.common.model.vo.proposal.ProposalVO r7 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r7
            java.lang.Object r7 = r1.L$4
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r1.L$3
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.lang.Object r9 = r1.L$2
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.lang.Object r10 = r1.L$1
            com.reown.sign.engine.domain.SignEngine r10 = (com.reown.sign.engine.domain.SignEngine) r10
            java.lang.Object r11 = r1.L$0
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ Exception -> 0x003b }
            r4 = r0
            r0 = r3
            goto L_0x0156
        L_0x003b:
            r0 = move-exception
            goto L_0x0167
        L_0x003e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0046:
            int r2 = r1.I$3
            int r5 = r1.I$2
            int r7 = r1.I$1
            int r8 = r1.I$0
            java.lang.Object r9 = r1.L$7
            com.reown.android.internal.common.model.Expiry r9 = (com.reown.android.internal.common.model.Expiry) r9
            java.lang.Object r10 = r1.L$6
            com.reown.sign.common.model.vo.proposal.ProposalVO r10 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r10
            java.lang.Object r11 = r1.L$5
            java.lang.Object r12 = r1.L$4
            java.util.Iterator r12 = (java.util.Iterator) r12
            java.lang.Object r13 = r1.L$3
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.lang.Object r14 = r1.L$2
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.lang.Object r15 = r1.L$1
            com.reown.sign.engine.domain.SignEngine r15 = (com.reown.sign.engine.domain.SignEngine) r15
            java.lang.Object r3 = r1.L$0
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ Exception -> 0x003b }
            r17 = r3
            r3 = r2
            r2 = r7
            r7 = r12
            r12 = r10
            r10 = r15
            r15 = r4
            r4 = r5
            r5 = r8
            r8 = r13
            r13 = r9
            r9 = r14
            r14 = r11
            r11 = r17
            goto L_0x0114
        L_0x0081:
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ Exception -> 0x003b }
            r2 = r19
            goto L_0x0099
        L_0x0087:
            kotlin.ResultKt.throwOnFailure(r19)
            com.reown.sign.engine.domain.SignEngine r2 = r1.this$0     // Catch:{ Exception -> 0x003b }
            com.reown.sign.storage.proposal.ProposalStorageRepository r2 = r2.proposalStorageRepository     // Catch:{ Exception -> 0x003b }
            r1.label = r5     // Catch:{ Exception -> 0x003b }
            java.lang.Object r2 = r2.getProposals$sign_release(r1)     // Catch:{ Exception -> 0x003b }
            if (r2 != r0) goto L_0x0099
            return r0
        L_0x0099:
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ Exception -> 0x003b }
            com.reown.sign.engine.domain.SignEngine r3 = r1.this$0     // Catch:{ Exception -> 0x003b }
            java.util.Iterator r5 = r2.iterator()     // Catch:{ Exception -> 0x003b }
            r7 = r3
            r8 = r5
            r9 = r6
            r10 = r9
            r3 = r2
            r5 = r3
        L_0x00a7:
            boolean r11 = r8.hasNext()     // Catch:{ Exception -> 0x003b }
            if (r11 == 0) goto L_0x0170
            java.lang.Object r11 = r8.next()     // Catch:{ Exception -> 0x003b }
            r12 = r11
            com.reown.sign.common.model.vo.proposal.ProposalVO r12 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r12     // Catch:{ Exception -> 0x003b }
            com.reown.android.internal.common.model.Expiry r13 = r12.getExpiry()     // Catch:{ Exception -> 0x003b }
            if (r13 == 0) goto L_0x015e
            com.reown.android.internal.utils.CoreValidator r14 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x003b }
            boolean r14 = r14.isExpired(r13)     // Catch:{ Exception -> 0x003b }
            if (r14 == 0) goto L_0x015e
            com.reown.sign.storage.proposal.ProposalStorageRepository r14 = r7.proposalStorageRepository     // Catch:{ Exception -> 0x003b }
            java.lang.String r15 = r12.getProposerPublicKey()     // Catch:{ Exception -> 0x003b }
            r14.deleteProposal$sign_release(r15)     // Catch:{ Exception -> 0x003b }
            com.reown.sign.json_rpc.domain.DeleteRequestByIdUseCase r14 = r7.deleteRequestByIdUseCase     // Catch:{ Exception -> 0x003b }
            r19 = r5
            long r4 = r12.getRequestId()     // Catch:{ Exception -> 0x003b }
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x003b }
            r1.L$0 = r15     // Catch:{ Exception -> 0x003b }
            r1.L$1 = r7     // Catch:{ Exception -> 0x003b }
            r1.L$2 = r3     // Catch:{ Exception -> 0x003b }
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ Exception -> 0x003b }
            r1.L$3 = r15     // Catch:{ Exception -> 0x003b }
            r1.L$4 = r8     // Catch:{ Exception -> 0x003b }
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch:{ Exception -> 0x003b }
            r1.L$5 = r15     // Catch:{ Exception -> 0x003b }
            r1.L$6 = r12     // Catch:{ Exception -> 0x003b }
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x003b }
            r1.L$7 = r15     // Catch:{ Exception -> 0x003b }
            r1.I$0 = r9     // Catch:{ Exception -> 0x003b }
            r1.I$1 = r10     // Catch:{ Exception -> 0x003b }
            r1.I$2 = r6     // Catch:{ Exception -> 0x003b }
            r1.I$3 = r6     // Catch:{ Exception -> 0x003b }
            r15 = 2
            r1.label = r15     // Catch:{ Exception -> 0x003b }
            java.lang.Object r4 = r14.invoke(r4, r1)     // Catch:{ Exception -> 0x003b }
            if (r4 != r0) goto L_0x0109
            return r0
        L_0x0109:
            r4 = r6
            r5 = r9
            r14 = r11
            r11 = r2
            r9 = r3
            r3 = r4
            r2 = r10
            r10 = r7
            r7 = r8
            r8 = r19
        L_0x0114:
            kotlinx.coroutines.flow.MutableSharedFlow r6 = r10._engineEvent     // Catch:{ Exception -> 0x003b }
            com.reown.sign.engine.model.EngineDO$ExpiredProposal r15 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, "<this>")     // Catch:{ Exception -> 0x003b }
            r16 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch:{ Exception -> 0x003b }
            r1.L$0 = r0     // Catch:{ Exception -> 0x003b }
            r1.L$1 = r10     // Catch:{ Exception -> 0x003b }
            r1.L$2 = r9     // Catch:{ Exception -> 0x003b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x003b }
            r1.L$3 = r0     // Catch:{ Exception -> 0x003b }
            r1.L$4 = r7     // Catch:{ Exception -> 0x003b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ Exception -> 0x003b }
            r1.L$5 = r0     // Catch:{ Exception -> 0x003b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x003b }
            r1.L$6 = r0     // Catch:{ Exception -> 0x003b }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x003b }
            r1.L$7 = r0     // Catch:{ Exception -> 0x003b }
            r1.I$0 = r5     // Catch:{ Exception -> 0x003b }
            r1.I$1 = r2     // Catch:{ Exception -> 0x003b }
            r1.I$2 = r4     // Catch:{ Exception -> 0x003b }
            r1.I$3 = r3     // Catch:{ Exception -> 0x003b }
            r0 = 3
            r1.label = r0     // Catch:{ Exception -> 0x003b }
            java.lang.Object r3 = r6.emit(r15, r1)     // Catch:{ Exception -> 0x003b }
            r4 = r16
            if (r3 != r4) goto L_0x0156
            return r4
        L_0x0156:
            r3 = r9
            r9 = r5
            r5 = r8
            r8 = r7
            r7 = r10
            r10 = r2
            r2 = r11
            goto L_0x0162
        L_0x015e:
            r4 = r0
            r19 = r5
            r0 = 3
        L_0x0162:
            r0 = r4
            r4 = 2
            r6 = 0
            goto L_0x00a7
        L_0x0167:
            com.reown.sign.engine.domain.SignEngine r1 = r1.this$0
            com.reown.foundation.util.Logger r1 = r1.logger
            r1.error((java.lang.Throwable) r0)
        L_0x0170:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.domain.SignEngine$sessionProposalExpiryWatcher$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((SignEngine$sessionProposalExpiryWatcher$1) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
