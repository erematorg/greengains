package com.reown.sign.storage.proposal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.storage.proposal.ProposalStorageRepository$deleteProposal$1", f = "ProposalStorageRepository.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {})
public final class ProposalStorageRepository$deleteProposal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $key;
    int label;
    final /* synthetic */ ProposalStorageRepository this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.storage.proposal.ProposalStorageRepository$deleteProposal$1$1", f = "ProposalStorageRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.storage.proposal.ProposalStorageRepository$deleteProposal$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(proposalStorageRepository, str, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                proposalStorageRepository.requiredNamespaceDaoQueries.deleteProposalNamespacesProposerKey(str);
                proposalStorageRepository.optionalNamespaceDaoQueries.deleteProposalNamespacesProposerKey(str);
                proposalStorageRepository.proposalDaoQueries.deleteProposal(str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProposalStorageRepository$deleteProposal$1(ProposalStorageRepository proposalStorageRepository, String str, Continuation<? super ProposalStorageRepository$deleteProposal$1> continuation) {
        super(2, continuation);
        this.this$0 = proposalStorageRepository;
        this.$key = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProposalStorageRepository$deleteProposal$1(this.this$0, this.$key, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            final ProposalStorageRepository proposalStorageRepository = this.this$0;
            final String str = this.$key;
            AnonymousClass1 r12 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (BuildersKt.withContext(io2, r12, this) == coroutine_suspended) {
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
        return ((ProposalStorageRepository$deleteProposal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
