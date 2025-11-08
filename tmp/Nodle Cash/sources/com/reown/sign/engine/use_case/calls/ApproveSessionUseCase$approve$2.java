package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@SourceDebugExtension({"SMAP\nApproveSessionUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApproveSessionUseCase.kt\ncom/reown/sign/engine/use_case/calls/ApproveSessionUseCase$approve$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,161:1\n1#2:162\n44#3,13:163\n*S KotlinDebug\n*F\n+ 1 ApproveSessionUseCase.kt\ncom/reown/sign/engine/use_case/calls/ApproveSessionUseCase$approve$2\n*L\n72#1:163,13\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ApproveSessionUseCase$approve$2", f = "ApproveSessionUseCase.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12}, l = {66, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 142, 145}, m = "invokeSuspend", n = {"trace", "proposal", "request", "pairingTopic", "it", "$i$a$-let-ApproveSessionUseCase$approve$2$2", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "this_$iv", "sessionNamespaces$iv", "requiredNamespaces$iv", "error", "$i$f$validateSessionNamespace$sign_release", "$i$a$-validateSessionNamespace$sign_release-ApproveSessionUseCase$approve$2$3", "trace", "proposal", "request", "pairingTopic", "e", "trace", "proposal", "request", "pairingTopic", "e"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
public final class ApproveSessionUseCase$approve$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $proposerPublicKey;
    final /* synthetic */ Map<String, String> $scopedProperties;
    final /* synthetic */ Map<String, EngineDO.Namespace.Session> $sessionNamespaces;
    final /* synthetic */ Map<String, String> $sessionProperties;
    int I$0;
    int I$1;
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
    final /* synthetic */ ApproveSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApproveSessionUseCase$approve$2(ApproveSessionUseCase approveSessionUseCase, String str, Map<String, EngineDO.Namespace.Session> map, Map<String, String> map2, Map<String, String> map3, Function1<? super Throwable, Unit> function1, Function0<Unit> function0, Continuation<? super ApproveSessionUseCase$approve$2> continuation) {
        super(2, continuation);
        this.this$0 = approveSessionUseCase;
        this.$proposerPublicKey = str;
        this.$sessionNamespaces = map;
        this.$scopedProperties = map2;
        this.$sessionProperties = map3;
        this.$onFailure = function1;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5(Function0 function0, List list, ApproveSessionUseCase approveSessionUseCase, String str, ProposalVO proposalVO) {
        function0.invoke();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ApproveSessionUseCase$approve$2$4$1(list, approveSessionUseCase, str, proposalVO, (Continuation<? super ApproveSessionUseCase$approve$2$4$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$6(Function1 function1, ApproveSessionUseCase approveSessionUseCase, List list, Topic topic, Throwable th) {
        function1.invoke(th);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ApproveSessionUseCase$approve$2$5$1(approveSessionUseCase, list, topic, (Continuation<? super ApproveSessionUseCase$approve$2$5$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ApproveSessionUseCase$approve$2(this.this$0, this.$proposerPublicKey, this.$sessionNamespaces, this.$scopedProperties, this.$sessionProperties, this.$onFailure, this.$onSuccess, continuation);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0628, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x06ee, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x07b4, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x087b, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0945, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0a13, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0ae2, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.error("Proposal expired on approve, topic: " + r6 + ", id: " + r8.getRequestId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x02f2, code lost:
        throw new com.reown.sign.common.exceptions.SessionProposalExpiredException("Session proposal expired");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x03d7, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x049c, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r5 = kotlin.Unit.INSTANCE;
        r4.logger.log("Session approve failure - invalid namespaces, error: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0562, code lost:
        throw new com.reown.sign.common.exceptions.InvalidNamespaceException(r2.getMessage());
     */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0c71  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:96:0x0540=Splitter:B:96:0x0540, B:174:0x0ac0=Splitter:B:174:0x0ac0, B:162:0x09f1=Splitter:B:162:0x09f1, B:129:0x0792=Splitter:B:129:0x0792, B:151:0x0923=Splitter:B:151:0x0923, B:73:0x03b5=Splitter:B:73:0x03b5, B:107:0x0606=Splitter:B:107:0x0606, B:140:0x0859=Splitter:B:140:0x0859, B:85:0x047a=Splitter:B:85:0x047a, B:118:0x06cc=Splitter:B:118:0x06cc} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r68) {
        /*
            r67 = this;
            r1 = r67
            java.lang.String r2 = "Proposal expired on approve, topic: "
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            java.lang.String r6 = "Session approve failure - invalid namespaces, error: "
            switch(r4) {
                case 0: goto L_0x0214;
                case 1: goto L_0x01ed;
                case 2: goto L_0x01c4;
                case 3: goto L_0x019b;
                case 4: goto L_0x0172;
                case 5: goto L_0x0149;
                case 6: goto L_0x0120;
                case 7: goto L_0x00f7;
                case 8: goto L_0x00ce;
                case 9: goto L_0x00a5;
                case 10: goto L_0x007c;
                case 11: goto L_0x0049;
                case 12: goto L_0x0030;
                case 13: goto L_0x0017;
                default: goto L_0x000f;
            }
        L_0x000f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0017:
            java.lang.Object r2 = r1.L$4
            java.lang.Exception r2 = (java.lang.Exception) r2
            java.lang.Object r3 = r1.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r3 = (com.reown.android.internal.common.model.WCRequest) r3
            java.lang.Object r3 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r3 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r3
            java.lang.Object r3 = r1.L$0
            java.util.List r3 = (java.util.List) r3
            kotlin.ResultKt.throwOnFailure(r68)
            goto L_0x0cdf
        L_0x0030:
            java.lang.Object r2 = r1.L$4
            java.lang.Exception r2 = (java.lang.Exception) r2
            java.lang.Object r4 = r1.L$3
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r6 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r6 = (com.reown.android.internal.common.model.WCRequest) r6
            java.lang.Object r7 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r7 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r7
            java.lang.Object r8 = r1.L$0
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r68)
            goto L_0x0c5a
        L_0x0049:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0ac0
        L_0x0072:
            r0 = move-exception
            r2 = r0
            r15 = r7
            r19 = r8
            r20 = r9
            r14 = r10
            goto L_0x0be1
        L_0x007c:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x09f1
        L_0x00a5:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0923
        L_0x00ce:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0859
        L_0x00f7:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0792
        L_0x0120:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x06cc
        L_0x0149:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0606
        L_0x0172:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0540
        L_0x019b:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x047a
        L_0x01c4:
            java.lang.Object r2 = r1.L$8
            com.reown.sign.engine.model.ValidationError r2 = (com.reown.sign.engine.model.ValidationError) r2
            java.lang.Object r4 = r1.L$7
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r7 = r1.L$6
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r1.L$4
            com.reown.sign.common.validator.SignValidator r7 = (com.reown.sign.common.validator.SignValidator) r7
            java.lang.Object r7 = r1.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r8 = (com.reown.android.internal.common.model.WCRequest) r8
            java.lang.Object r9 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r9 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r9
            java.lang.Object r10 = r1.L$0
            java.util.List r10 = (java.util.List) r10
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x0072 }
            goto L_0x03b5
        L_0x01ed:
            java.lang.Object r4 = r1.L$5
            com.reown.android.internal.common.model.Expiry r4 = (com.reown.android.internal.common.model.Expiry) r4
            java.lang.Object r4 = r1.L$4
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = (com.reown.sign.engine.use_case.calls.ApproveSessionUseCase) r4
            java.lang.Object r6 = r1.L$3
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r7 = (com.reown.android.internal.common.model.WCRequest) r7
            java.lang.Object r8 = r1.L$1
            com.reown.sign.common.model.vo.proposal.ProposalVO r8 = (com.reown.sign.common.model.vo.proposal.ProposalVO) r8
            java.lang.Object r9 = r1.L$0
            java.util.List r9 = (java.util.List) r9
            kotlin.ResultKt.throwOnFailure(r68)     // Catch:{ Exception -> 0x020a }
            goto L_0x02ca
        L_0x020a:
            r0 = move-exception
            r2 = r0
            r15 = r6
            r19 = r7
            r20 = r8
            r14 = r9
            goto L_0x0be1
        L_0x0214:
            kotlin.ResultKt.throwOnFailure(r68)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.String r7 = "session_approve_started"
            r4.add(r7)
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r8 = r1.this$0
            com.reown.foundation.util.Logger r8 = r8.logger
            r8.log((java.lang.String) r7)
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r7 = r1.this$0
            com.reown.sign.storage.proposal.ProposalStorageRepository r7 = r7.proposalStorageRepository
            java.lang.String r8 = r1.$proposerPublicKey
            com.reown.sign.common.model.vo.proposal.ProposalVO r7 = r7.getProposalByKey$sign_release(r8)
            com.reown.android.internal.common.model.WCRequest r8 = com.reown.sign.engine.model.mapper.EngineMapperKt.toSessionProposeRequest(r7)
            com.reown.foundation.common.model.Topic r9 = r7.getPairingTopic()
            java.lang.String r9 = r9.getValue()
            com.reown.android.internal.common.model.Expiry r15 = r7.getExpiry()     // Catch:{ Exception -> 0x0bdd }
            r14 = 0
            if (r15 == 0) goto L_0x0301
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r13 = r1.this$0     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.internal.utils.CoreValidator r10 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            boolean r10 = r10.isExpired(r15)     // Catch:{ Exception -> 0x02f3 }
            if (r10 == 0) goto L_0x02fd
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r6 = r13.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r12 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "PROPOSAL_EXPIRED"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r11 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r10 = r26
            r68 = r12
            r12 = r16
            r5 = r13
            r13 = r17
            r14 = r18
            r30 = r15
            r15 = r19
            r16 = r20
            r17 = r4
            r18 = r9
            r19 = r27
            r20 = r28
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r7     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r5     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r10     // Catch:{ Exception -> 0x02f3 }
            r15 = 0
            r1.I$0 = r15     // Catch:{ Exception -> 0x02f3 }
            r10 = 1
            r1.label = r10     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            java.lang.Object r6 = r6.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r6 != r3) goto L_0x02c2
            return r3
        L_0x02c2:
            r6 = r9
            r9 = r4
            r4 = r5
            r66 = r8
            r8 = r7
            r7 = r66
        L_0x02ca:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x020a }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x020a }
            long r10 = r8.getRequestId()     // Catch:{ Exception -> 0x020a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x020a }
            r5.<init>(r2)     // Catch:{ Exception -> 0x020a }
            r5.append(r6)     // Catch:{ Exception -> 0x020a }
            java.lang.String r2 = ", id: "
            r5.append(r2)     // Catch:{ Exception -> 0x020a }
            r5.append(r10)     // Catch:{ Exception -> 0x020a }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x020a }
            r4.error((java.lang.String) r2)     // Catch:{ Exception -> 0x020a }
            com.reown.sign.common.exceptions.SessionProposalExpiredException r2 = new com.reown.sign.common.exceptions.SessionProposalExpiredException     // Catch:{ Exception -> 0x020a }
            java.lang.String r4 = "Session proposal expired"
            r2.<init>(r4)     // Catch:{ Exception -> 0x020a }
            throw r2     // Catch:{ Exception -> 0x020a }
        L_0x02f3:
            r0 = move-exception
            r2 = r0
            r14 = r4
            r20 = r7
            r19 = r8
            r15 = r9
            goto L_0x0be1
        L_0x02fd:
            r15 = r14
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            goto L_0x0302
        L_0x0301:
            r15 = r14
        L_0x0302:
            java.lang.String r2 = "proposal_not_expired"
            r4.add(r2)     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.common.validator.SignValidator r2 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x0bdd }
            java.util.Map<java.lang.String, com.reown.sign.engine.model.EngineDO$Namespace$Session> r5 = r1.$sessionNamespaces     // Catch:{ Exception -> 0x0bdd }
            java.util.Map r5 = com.reown.sign.engine.model.mapper.EngineMapperKt.toMapOfNamespacesVOSession(r5)     // Catch:{ Exception -> 0x0bdd }
            java.util.Map r14 = r7.getRequiredNamespaces()     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r13 = r1.this$0     // Catch:{ Exception -> 0x0bdd }
            boolean r10 = r5.isEmpty()     // Catch:{ Exception -> 0x0bdd }
            if (r10 == 0) goto L_0x03d8
            com.reown.sign.engine.model.ValidationError$EmptyNamespaces r12 = com.reown.sign.engine.model.ValidationError.EmptyNamespaces.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r11 = r13.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r68 = r10
            r10 = r26
            r31 = r11
            r11 = r16
            r32 = r12
            r12 = r17
            r33 = r13
            r13 = r18
            r30 = r14
            r14 = r19
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r33
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r32
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 2
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r31
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x03ae
            return r3
        L_0x03ae:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x03b5:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x03d8:
            r30 = r14
            r14 = r15
            r15 = r13
            boolean r10 = r2.areNamespacesKeysProperlyFormatted(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x049d
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r13 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r35 = r12
            r12 = r17
            r36 = r13
            r13 = r18
            r14 = r19
            r37 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r37
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r36
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 3
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r35
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x0473
            return r3
        L_0x0473:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x047a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x049d:
            boolean r10 = r2.areChainsNotEmpty(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x0563
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r13 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r10 = "Chains must not be empty"
            r13.<init>(r10)     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r38 = r12
            r12 = r17
            r39 = r13
            r13 = r18
            r14 = r19
            r40 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r40
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r39
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 4
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r38
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x0539
            return r3
        L_0x0539:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0540:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x0563:
            boolean r10 = r2.areChainIdsValid(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x0629
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r13 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r10 = "Chains must be CAIP-2 compliant"
            r13.<init>(r10)     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r41 = r12
            r12 = r17
            r42 = r13
            r13 = r18
            r14 = r19
            r43 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r43
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r42
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 5
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r41
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x05ff
            return r3
        L_0x05ff:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0606:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x0629:
            boolean r10 = r2.areChainsInMatchingNamespace(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x06ef
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r13 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r10 = "Chains must be defined in matching namespace"
            r13.<init>(r10)     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r44 = r12
            r12 = r17
            r45 = r13
            r13 = r18
            r14 = r19
            r46 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r46
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r45
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 6
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r44
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x06c5
            return r3
        L_0x06c5:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x06cc:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x06ef:
            boolean r10 = r2.areAccountIdsValid(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x07b5
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r13 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r10 = "Accounts must be CAIP-10 compliant"
            r13.<init>(r10)     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r47 = r12
            r12 = r17
            r48 = r13
            r13 = r18
            r14 = r19
            r49 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r49
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r48
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 7
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r47
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x078b
            return r3
        L_0x078b:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0792:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x07b5:
            boolean r10 = r2.areAccountsInMatchingNamespaceAndChains(r5)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x087c
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r13 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r10 = "Accounts must be defined in matching namespace"
            r13.<init>(r10)     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r50 = r12
            r12 = r17
            r51 = r13
            r13 = r18
            r14 = r19
            r52 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r52
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r51
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 8
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r50
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x0852
            return r3
        L_0x0852:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0859:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x087c:
            java.util.Set r10 = r5.keySet()     // Catch:{ Exception -> 0x0bdd }
            java.util.Set r11 = r30.keySet()     // Catch:{ Exception -> 0x0bdd }
            boolean r10 = r2.areAllNamespacesApproved(r10, r11)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x0946
            com.reown.sign.engine.model.ValidationError$UserRejected r13 = com.reown.sign.engine.model.ValidationError.UserRejected.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r12 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r10 = r26
            r68 = r11
            r11 = r16
            r53 = r12
            r12 = r17
            r54 = r13
            r13 = r18
            r14 = r19
            r55 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r55
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r54
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 9
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r53
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x091c
            return r3
        L_0x091c:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0923:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x0946:
            java.util.Map r10 = r2.allMethodsWithChains(r5)     // Catch:{ Exception -> 0x0bdd }
            r13 = r30
            java.util.Map r11 = r2.allMethodsWithChains(r13)     // Catch:{ Exception -> 0x0bdd }
            boolean r10 = r2.areAllMethodsApproved(r10, r11)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x0a14
            com.reown.sign.engine.model.ValidationError$UserRejectedMethods r12 = com.reown.sign.engine.model.ValidationError.UserRejectedMethods.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r11 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r68 = r10
            r10 = r26
            r56 = r11
            r11 = r16
            r57 = r12
            r12 = r17
            r30 = r13
            r13 = r18
            r14 = r19
            r58 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r15 = r58
            r1.L$7 = r15     // Catch:{ Exception -> 0x02f3 }
            r2 = r57
            r1.L$8 = r2     // Catch:{ Exception -> 0x02f3 }
            r14 = 0
            r1.I$0 = r14     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r14     // Catch:{ Exception -> 0x02f3 }
            r5 = 10
            r1.label = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = r68
            r5 = r56
            java.lang.Object r5 = r5.invoke(r10, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r5 != r3) goto L_0x09ea
            return r3
        L_0x09ea:
            r10 = r4
            r4 = r15
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x09f1:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x0a14:
            r30 = r13
            java.util.Map r10 = r2.allEventsWithChains(r5)     // Catch:{ Exception -> 0x0bdd }
            java.util.Map r11 = r2.allEventsWithChains(r13)     // Catch:{ Exception -> 0x0bdd }
            boolean r10 = r2.areAllEventsApproved(r10, r11)     // Catch:{ Exception -> 0x0bdd }
            if (r10 != 0) goto L_0x0ae3
            com.reown.sign.engine.model.ValidationError$UserRejectedEvents r12 = com.reown.sign.engine.model.ValidationError.UserRejectedEvents.INSTANCE     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r11 = r15.insertEventUseCase     // Catch:{ Exception -> 0x02f3 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x02f3 }
            java.lang.String r25 = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r26 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r68 = r10
            r10 = r26
            r59 = r11
            r11 = r16
            r60 = r12
            r12 = r17
            r30 = r13
            r13 = r18
            r14 = r19
            r61 = r15
            r15 = r20
            r16 = r27
            r17 = r4
            r18 = r9
            r19 = r28
            r20 = r29
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x02f3 }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r68
            r18 = r25
            r19 = r26
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x02f3 }
            r1.L$0 = r4     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x02f3 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x02f3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.L$3 = r9     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x02f3 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x02f3 }
            r1.L$5 = r2     // Catch:{ Exception -> 0x02f3 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x02f3 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x02f3 }
            r2 = r61
            r1.L$7 = r2     // Catch:{ Exception -> 0x02f3 }
            r5 = r60
            r1.L$8 = r5     // Catch:{ Exception -> 0x02f3 }
            r10 = 0
            r1.I$0 = r10     // Catch:{ Exception -> 0x02f3 }
            r1.I$1 = r10     // Catch:{ Exception -> 0x02f3 }
            r10 = 11
            r1.label = r10     // Catch:{ Exception -> 0x02f3 }
            r11 = r68
            r10 = r59
            java.lang.Object r10 = r10.invoke(r11, r1)     // Catch:{ Exception -> 0x02f3 }
            if (r10 != r3) goto L_0x0ab8
            return r3
        L_0x0ab8:
            r10 = r4
            r4 = r2
            r2 = r5
            r66 = r9
            r9 = r7
            r7 = r66
        L_0x0ac0:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0072 }
            com.reown.foundation.util.Logger r4 = r4.logger     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r11.<init>(r6)     // Catch:{ Exception -> 0x0072 }
            r11.append(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r5 = r11.toString()     // Catch:{ Exception -> 0x0072 }
            r4.log((java.lang.String) r5)     // Catch:{ Exception -> 0x0072 }
            com.reown.sign.common.exceptions.InvalidNamespaceException r4 = new com.reown.sign.common.exceptions.InvalidNamespaceException     // Catch:{ Exception -> 0x0072 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0072 }
            throw r4     // Catch:{ Exception -> 0x0072 }
        L_0x0ae3:
            java.lang.String r2 = "session_namespaces_validation_success"
            r4.add(r2)     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0bdd }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r2 = r2.crypto     // Catch:{ Exception -> 0x0bdd }
            java.lang.String r2 = r2.m8724generateAndStoreX25519KeyPairuN_RPug()     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x0bdd }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r5 = r5.crypto     // Catch:{ Exception -> 0x0bdd }
            java.lang.String r6 = r1.$proposerPublicKey     // Catch:{ Exception -> 0x0bdd }
            java.lang.String r6 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r6)     // Catch:{ Exception -> 0x0bdd }
            com.reown.foundation.common.model.Topic r5 = r5.m8726generateTopicFromKeyAgreementV_lFtQw(r2, r6)     // Catch:{ Exception -> 0x0bdd }
            java.lang.String r6 = "create_session_topic"
            r4.add(r6)     // Catch:{ Exception -> 0x0bdd }
            com.reown.android.internal.common.model.params.CoreSignParams$ApprovalParams r6 = com.reown.sign.engine.model.mapper.EngineMapperKt.m8883toSessionApproveParamszo5FKG8(r7, r2)     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.common.model.vo.clientsync.common.SessionParticipant r15 = new com.reown.sign.common.model.vo.clientsync.common.SessionParticipant     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x0bdd }
            com.reown.android.internal.common.model.AppMetaData r10 = r10.selfAppMetaData     // Catch:{ Exception -> 0x0bdd }
            r15.<init>(r2, r10)     // Catch:{ Exception -> 0x0bdd }
            long r19 = com.reown.android.internal.utils.Expiration.getACTIVE_SESSION()     // Catch:{ Exception -> 0x0bdd }
            com.reown.sign.common.model.vo.sequence.SessionVO$Companion r2 = com.reown.sign.common.model.vo.sequence.SessionVO.Companion     // Catch:{ Exception -> 0x0bdd }
            java.util.Map<java.lang.String, com.reown.sign.engine.model.EngineDO$Namespace$Session> r13 = r1.$sessionNamespaces     // Catch:{ Exception -> 0x0bdd }
            java.util.Map<java.lang.String, java.lang.String> r14 = r1.$scopedProperties     // Catch:{ Exception -> 0x0bdd }
            java.util.Map<java.lang.String, java.lang.String> r12 = r1.$sessionProperties     // Catch:{ Exception -> 0x0bdd }
            r21 = r9
            r9 = r2
            r10 = r5
            r11 = r7
            r2 = r12
            r12 = r15
            r16 = r13
            r17 = r14
            r13 = r19
            r22 = r15
            r15 = r16
            r16 = r17
            r17 = r2
            r18 = r21
            com.reown.sign.common.model.vo.sequence.SessionVO r2 = r9.createUnacknowledgedSession$sign_release(r10, r11, r12, r13, r15, r16, r17, r18)     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.storage.sequence.SessionStorageRepository r9 = r9.sessionStorageRepository     // Catch:{ Exception -> 0x0bd3 }
            long r10 = r8.getId()     // Catch:{ Exception -> 0x0bd3 }
            r9.insertSession(r2, r10)     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r2 = r2.metadataStorageRepository     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.model.AppMetaData r9 = r9.selfAppMetaData     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.model.AppMetaDataType r10 = com.reown.android.internal.common.model.AppMetaDataType.SELF     // Catch:{ Exception -> 0x0bd3 }
            r2.insertOrAbortMetadata(r5, r9, r10)     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r2 = r2.metadataStorageRepository     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.model.AppMetaData r9 = r7.getAppMetaData()     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.model.AppMetaDataType r10 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x0bd3 }
            r2.insertOrAbortMetadata(r5, r9, r10)     // Catch:{ Exception -> 0x0bd3 }
            java.lang.String r2 = "store_session"
            r4.add(r2)     // Catch:{ Exception -> 0x0bd3 }
            java.util.Map<java.lang.String, com.reown.sign.engine.model.EngineDO$Namespace$Session> r13 = r1.$sessionNamespaces     // Catch:{ Exception -> 0x0bd3 }
            java.util.Map<java.lang.String, java.lang.String> r14 = r1.$sessionProperties     // Catch:{ Exception -> 0x0bd3 }
            java.util.Map<java.lang.String, java.lang.String> r15 = r1.$scopedProperties     // Catch:{ Exception -> 0x0bd3 }
            r9 = r7
            r10 = r22
            r11 = r19
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionSettleParams r34 = com.reown.sign.engine.model.mapper.EngineMapperKt.toSessionSettleParams(r9, r10, r11, r13, r14, r15)     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionSettle r2 = new com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionSettle     // Catch:{ Exception -> 0x0bd3 }
            r32 = 0
            r33 = 0
            r35 = 7
            r36 = 0
            r30 = 0
            r29 = r2
            r29.<init>(r30, r32, r33, r34, r35, r36)     // Catch:{ Exception -> 0x0bd3 }
            java.lang.String r9 = "publishing_session_approve"
            r4.add(r9)     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r9 = r9.jsonRpcInteractor     // Catch:{ Exception -> 0x0bd3 }
            com.reown.foundation.common.model.Topic r16 = r7.getPairingTopic()     // Catch:{ Exception -> 0x0bd3 }
            long r17 = r7.getRequestId()     // Catch:{ Exception -> 0x0bd3 }
            kotlin.jvm.functions.Function0<kotlin.Unit> r11 = r1.$onSuccess     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r15 = r1.this$0     // Catch:{ Exception -> 0x0bd3 }
            java.lang.String r14 = r1.$proposerPublicKey     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.e r19 = new com.reown.sign.engine.use_case.calls.e     // Catch:{ Exception -> 0x0bd3 }
            r10 = r19
            r12 = r4
            r13 = r15
            r20 = r15
            r15 = r7
            r10.<init>(r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0bd3 }
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r12 = r1.$onFailure     // Catch:{ Exception -> 0x0bd3 }
            com.reown.sign.engine.use_case.calls.f r22 = new com.reown.sign.engine.use_case.calls.f     // Catch:{ Exception -> 0x0bd3 }
            r11 = 0
            r10 = r22
            r13 = r20
            r14 = r4
            r15 = r5
            r10.<init>(r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0bd3 }
            r10 = r9
            r11 = r16
            r12 = r5
            r13 = r6
            r14 = r2
            r15 = r17
            r17 = r19
            r18 = r22
            r10.approveSession(r11, r12, r13, r14, r15, r17, r18)     // Catch:{ Exception -> 0x0bd3 }
            goto L_0x0ce4
        L_0x0bd3:
            r0 = move-exception
        L_0x0bd4:
            r2 = r0
            r14 = r4
            r20 = r7
            r19 = r8
            r15 = r21
            goto L_0x0be1
        L_0x0bdd:
            r0 = move-exception
            r21 = r9
            goto L_0x0bd4
        L_0x0be1:
            boolean r4 = r2 instanceof com.reown.android.internal.common.exception.NoRelayConnectionException
            if (r4 == 0) goto L_0x0c63
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = r1.this$0
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r13 = r4.insertEventUseCase
            com.reown.android.pulse.model.properties.Props r12 = new com.reown.android.pulse.model.properties.Props
            com.reown.android.pulse.model.properties.Properties r21 = new com.reown.android.pulse.model.properties.Properties
            r16 = 0
            r17 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r18 = 0
            r22 = 0
            r23 = 3903(0xf3f, float:5.469E-42)
            r24 = 0
            r4 = r21
            r11 = r14
            r68 = r12
            r12 = r15
            r62 = r13
            r13 = r18
            r63 = r14
            r14 = r22
            r64 = r15
            r15 = r16
            r16 = r17
            r17 = r23
            r18 = r24
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            java.lang.String r6 = "NO_WSS_CONNECTION"
            r8 = 1
            r4 = r68
            r7 = r21
            r4.<init>(r5, r6, r7, r8, r9)
            r4 = r63
            r1.L$0 = r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)
            r1.L$1 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)
            r1.L$2 = r5
            r9 = r64
            r1.L$3 = r9
            r1.L$4 = r2
            r5 = 0
            r1.L$5 = r5
            r1.L$6 = r5
            r1.L$7 = r5
            r1.L$8 = r5
            r5 = 12
            r1.label = r5
            r6 = r68
            r5 = r62
            java.lang.Object r5 = r5.invoke(r6, r1)
            if (r5 != r3) goto L_0x0c54
            return r3
        L_0x0c54:
            r8 = r4
            r4 = r9
            r6 = r19
            r7 = r20
        L_0x0c5a:
            r20 = r4
            r21 = r6
            r22 = r7
            r19 = r8
            goto L_0x0c6d
        L_0x0c63:
            r4 = r14
            r9 = r15
            r21 = r19
            r22 = r20
            r19 = r4
            r20 = r9
        L_0x0c6d:
            boolean r4 = r2 instanceof com.reown.android.internal.common.exception.NoInternetConnectionException
            if (r4 == 0) goto L_0x0cdf
            com.reown.sign.engine.use_case.calls.ApproveSessionUseCase r4 = r1.this$0
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r15 = r4.insertEventUseCase
            com.reown.android.pulse.model.properties.Props r14 = new com.reown.android.pulse.model.properties.Props
            com.reown.android.pulse.model.properties.Properties r23 = new com.reown.android.pulse.model.properties.Properties
            r16 = 0
            r17 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r13 = 0
            r18 = 0
            r24 = 3903(0xf3f, float:5.469E-42)
            r25 = 0
            r4 = r23
            r11 = r19
            r12 = r20
            r68 = r14
            r14 = r18
            r65 = r15
            r15 = r16
            r16 = r17
            r17 = r24
            r18 = r25
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            java.lang.String r6 = "NO_INTERNET_CONNECTION"
            r8 = 1
            r4 = r68
            r7 = r23
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)
            r1.L$0 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)
            r1.L$1 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)
            r1.L$2 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)
            r1.L$3 = r4
            r1.L$4 = r2
            r4 = 0
            r1.L$5 = r4
            r1.L$6 = r4
            r1.L$7 = r4
            r1.L$8 = r4
            r4 = 13
            r1.label = r4
            r5 = r68
            r4 = r65
            java.lang.Object r4 = r4.invoke(r5, r1)
            if (r4 != r3) goto L_0x0cdf
            return r3
        L_0x0cdf:
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r1 = r1.$onFailure
            r1.invoke(r2)
        L_0x0ce4:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.ApproveSessionUseCase$approve$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ApproveSessionUseCase$approve$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
