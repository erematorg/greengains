package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pulse.model.Trace;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ApproveSessionUseCase$approve$2$4$1", f = "ApproveSessionUseCase.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
public final class ApproveSessionUseCase$approve$2$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ProposalVO $proposal;
    final /* synthetic */ String $proposerPublicKey;
    final /* synthetic */ List<String> $trace;
    int label;
    final /* synthetic */ ApproveSessionUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ApproveSessionUseCase$approve$2$4$1$1", f = "ApproveSessionUseCase.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.use_case.calls.ApproveSessionUseCase$approve$2$4$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(list, approveSessionUseCase, str, proposalVO, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                list.add(Trace.Session.SESSION_APPROVE_SUCCESS);
                approveSessionUseCase.proposalStorageRepository.deleteProposal$sign_release(str);
                VerifyContextStorageRepository access$getVerifyContextStorageRepository$p = approveSessionUseCase.verifyContextStorageRepository;
                long requestId = proposalVO.getRequestId();
                this.label = 1;
                if (access$getVerifyContextStorageRepository$p.delete(requestId, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApproveSessionUseCase$approve$2$4$1(List<String> list, ApproveSessionUseCase approveSessionUseCase, String str, ProposalVO proposalVO, Continuation<? super ApproveSessionUseCase$approve$2$4$1> continuation) {
        super(2, continuation);
        this.$trace = list;
        this.this$0 = approveSessionUseCase;
        this.$proposerPublicKey = str;
        this.$proposal = proposalVO;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ApproveSessionUseCase$approve$2$4$1(this.$trace, this.this$0, this.$proposerPublicKey, this.$proposal, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final List<String> list = this.$trace;
            final ApproveSessionUseCase approveSessionUseCase = this.this$0;
            final String str = this.$proposerPublicKey;
            final ProposalVO proposalVO = this.$proposal;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ApproveSessionUseCase$approve$2$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
