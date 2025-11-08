package com.reown.sign.engine.use_case.calls;

import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCaseInterface;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "<init>", "(Lcom/reown/sign/storage/proposal/ProposalStorageRepository;)V", "getSessionProposals", "", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetSessionProposalsUseCase implements GetSessionProposalsUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalStorageRepository proposalStorageRepository;

    public GetSessionProposalsUseCase(@NotNull ProposalStorageRepository proposalStorageRepository2) {
        Intrinsics.checkNotNullParameter(proposalStorageRepository2, "proposalStorageRepository");
        this.proposalStorageRepository = proposalStorageRepository2;
    }

    @Nullable
    public Object getSessionProposals(@NotNull Continuation<? super List<EngineDO.SessionProposal>> continuation) {
        return SupervisorKt.supervisorScope(new GetSessionProposalsUseCase$getSessionProposals$2(this, (Continuation<? super GetSessionProposalsUseCase$getSessionProposals$2>) null), continuation);
    }
}
