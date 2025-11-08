package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.PeerError;
import com.reown.sign.common.exceptions.SessionProposalExpiredException;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RejectSessionUseCase$reject$2", f = "RejectSessionUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class RejectSessionUseCase$reject$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $proposerPublicKey;
    final /* synthetic */ String $reason;
    int label;
    final /* synthetic */ RejectSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RejectSessionUseCase$reject$2(RejectSessionUseCase rejectSessionUseCase, String str, String str2, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, Continuation<? super RejectSessionUseCase$reject$2> continuation) {
        super(2, continuation);
        this.this$0 = rejectSessionUseCase;
        this.$proposerPublicKey = str;
        this.$reason = str2;
        this.$onSuccess = function0;
        this.$onFailure = function1;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(RejectSessionUseCase rejectSessionUseCase, ProposalVO proposalVO, Function0 function0, String str, WCRequest wCRequest) {
        Logger access$getLogger$p = rejectSessionUseCase.logger;
        String value = proposalVO.getPairingTopic().getValue();
        access$getLogger$p.log("Session rejection sent successfully, topic: " + value);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RejectSessionUseCase$reject$2$2$1(rejectSessionUseCase, str, proposalVO, (Continuation<? super RejectSessionUseCase$reject$2$2$1>) null), 3, (Object) null);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(RejectSessionUseCase rejectSessionUseCase, ProposalVO proposalVO, Function1 function1, Throwable th) {
        Logger access$getLogger$p = rejectSessionUseCase.logger;
        String value = proposalVO.getPairingTopic().getValue();
        access$getLogger$p.error("Session rejection sent failure, topic: " + value + ". Error: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RejectSessionUseCase$reject$2(this.this$0, this.$proposerPublicKey, this.$reason, this.$onSuccess, this.$onFailure, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ProposalVO proposalByKey$sign_release = this.this$0.proposalStorageRepository.getProposalByKey$sign_release(this.$proposerPublicKey);
            Expiry expiry = proposalByKey$sign_release.getExpiry();
            if (expiry != null) {
                RejectSessionUseCase rejectSessionUseCase = this.this$0;
                if (CoreValidator.INSTANCE.isExpired(expiry)) {
                    Logger access$getLogger$p = rejectSessionUseCase.logger;
                    String value = proposalByKey$sign_release.getPairingTopic().getValue();
                    long requestId = proposalByKey$sign_release.getRequestId();
                    access$getLogger$p.error("Proposal expired on reject, topic: " + value + ", id: " + requestId);
                    throw new SessionProposalExpiredException("Session proposal expired");
                }
            }
            Logger access$getLogger$p2 = this.this$0.logger;
            String value2 = proposalByKey$sign_release.getPairingTopic().getValue();
            access$getLogger$p2.log("Sending session rejection, topic: " + value2);
            RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
            WCRequest sessionProposeRequest = EngineMapperKt.toSessionProposeRequest(proposalByKey$sign_release);
            PeerError.EIP1193.UserRejectedRequest userRejectedRequest = new PeerError.EIP1193.UserRejectedRequest(this.$reason);
            IrnParams irnParams = new IrnParams(Tags.SESSION_PROPOSE_RESPONSE_REJECT, new Ttl(Time.getFiveMinutesInSeconds()), Boxing.boxLong(proposalByKey$sign_release.getRequestId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            RejectSessionUseCase rejectSessionUseCase2 = this.this$0;
            RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p, sessionProposeRequest, userRejectedRequest, irnParams, (EnvelopeType) null, (Participants) null, new f(1, rejectSessionUseCase2, proposalByKey$sign_release, this.$onSuccess, this.$proposerPublicKey), new a((Object) rejectSessionUseCase2, 5, (Object) proposalByKey$sign_release, (Object) this.$onFailure), 24, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RejectSessionUseCase$reject$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
