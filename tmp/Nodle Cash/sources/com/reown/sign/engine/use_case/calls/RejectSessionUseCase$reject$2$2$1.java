package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RejectSessionUseCase$reject$2$2$1", f = "RejectSessionUseCase.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
public final class RejectSessionUseCase$reject$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ProposalVO $proposal;
    final /* synthetic */ String $proposerPublicKey;
    int label;
    final /* synthetic */ RejectSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RejectSessionUseCase$reject$2$2$1(RejectSessionUseCase rejectSessionUseCase, String str, ProposalVO proposalVO, Continuation<? super RejectSessionUseCase$reject$2$2$1> continuation) {
        super(2, continuation);
        this.this$0 = rejectSessionUseCase;
        this.$proposerPublicKey = str;
        this.$proposal = proposalVO;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RejectSessionUseCase$reject$2$2$1(this.this$0, this.$proposerPublicKey, this.$proposal, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.proposalStorageRepository.deleteProposal$sign_release(this.$proposerPublicKey);
            VerifyContextStorageRepository access$getVerifyContextStorageRepository$p = this.this$0.verifyContextStorageRepository;
            long requestId = this.$proposal.getRequestId();
            this.label = 1;
            if (access$getVerifyContextStorageRepository$p.delete(requestId, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        RelayJsonRpcInteractorInterface.unsubscribe$default(this.this$0.jsonRpcInteractor, this.$proposal.getPairingTopic(), (Function0) null, (Function1) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RejectSessionUseCase$reject$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
