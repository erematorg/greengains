package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aHB¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, d2 = {"Lcom/reown/sign/engine/use_case/responses/OnSessionProposalResponseUseCase;", "", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/pairing/handler/PairingControllerInterface;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/sign/storage/proposal/ProposalStorageRepository;Lcom/reown/foundation/util/Logger;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "wcResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "(Lcom/reown/android/internal/common/model/WCResponse;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnSessionProposalResponseUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    /* access modifiers changed from: private */
    @NotNull
    public final KeyManagementRepository crypto;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final PairingControllerInterface pairingController;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalStorageRepository proposalStorageRepository;

    public OnSessionProposalResponseUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull PairingControllerInterface pairingControllerInterface, @NotNull KeyManagementRepository keyManagementRepository, @NotNull ProposalStorageRepository proposalStorageRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(pairingControllerInterface, "pairingController");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(proposalStorageRepository2, "proposalStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.pairingController = pairingControllerInterface;
        this.crypto = keyManagementRepository;
        this.proposalStorageRepository = proposalStorageRepository2;
        this.logger = logger2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCResponse wCResponse, @NotNull SignParams.SessionProposeParams sessionProposeParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionProposalResponseUseCase$invoke$2(this, wCResponse, sessionProposeParams, (Continuation<? super OnSessionProposalResponseUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
