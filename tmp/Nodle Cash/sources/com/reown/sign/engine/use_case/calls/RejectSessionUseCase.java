package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.foundation.util.Logger;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ@\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u0014H@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/RejectSessionUseCase;", "Lcom/reown/sign/engine/use_case/calls/RejectSessionUseCaseInterface;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/proposal/ProposalStorageRepository;Lcom/reown/foundation/util/Logger;)V", "reject", "", "proposerPublicKey", "", "reason", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RejectSessionUseCase implements RejectSessionUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalStorageRepository proposalStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository verifyContextStorageRepository;

    public RejectSessionUseCase(@NotNull VerifyContextStorageRepository verifyContextStorageRepository2, @NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull ProposalStorageRepository proposalStorageRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(proposalStorageRepository2, "proposalStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.proposalStorageRepository = proposalStorageRepository2;
        this.logger = logger2;
    }

    @Nullable
    public Object reject(@NotNull String str, @NotNull String str2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new RejectSessionUseCase$reject$2(this, str, str2, function0, function1, (Continuation<? super RejectSessionUseCase$reject$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
