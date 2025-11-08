package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.verify.domain.ResolveAttestationIdUseCase;
import com.reown.android.verify.model.VerifyContext;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eHB¢\u0006\u0002\u0010\u001fJ \u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006#"}, d2 = {"Lcom/reown/sign/engine/use_case/requests/OnSessionAuthenticateUseCase;", "", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "resolveAttestationIdUseCase", "Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "insertTelemetryEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;Lcom/reown/android/pairing/handler/PairingControllerInterface;Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/foundation/util/Logger;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "authenticateSessionParams", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "(Lcom/reown/android/internal/common/model/WCRequest;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSessionAuthenticate", "verifyContext", "Lcom/reown/android/verify/model/VerifyContext;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnSessionAuthenticateUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertTelemetryEventUseCase insertTelemetryEventUseCase;
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
    public final ResolveAttestationIdUseCase resolveAttestationIdUseCase;

    public OnSessionAuthenticateUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull ResolveAttestationIdUseCase resolveAttestationIdUseCase2, @NotNull PairingControllerInterface pairingControllerInterface, @NotNull InsertTelemetryEventUseCase insertTelemetryEventUseCase2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(resolveAttestationIdUseCase2, "resolveAttestationIdUseCase");
        Intrinsics.checkNotNullParameter(pairingControllerInterface, "pairingController");
        Intrinsics.checkNotNullParameter(insertTelemetryEventUseCase2, "insertTelemetryEventUseCase");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.resolveAttestationIdUseCase = resolveAttestationIdUseCase2;
        this.pairingController = pairingControllerInterface;
        this.insertTelemetryEventUseCase = insertTelemetryEventUseCase2;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.logger = logger2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    /* access modifiers changed from: private */
    public final void emitSessionAuthenticate(WCRequest wCRequest, SignParams.SessionAuthenticateParams sessionAuthenticateParams, VerifyContext verifyContext) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new OnSessionAuthenticateUseCase$emitSessionAuthenticate$1(this, wCRequest, sessionAuthenticateParams, verifyContext, (Continuation<? super OnSessionAuthenticateUseCase$emitSessionAuthenticate$1>) null), 3, (Object) null);
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCRequest wCRequest, @NotNull SignParams.SessionAuthenticateParams sessionAuthenticateParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionAuthenticateUseCase$invoke$2(wCRequest, this, sessionAuthenticateParams, (Continuation<? super OnSessionAuthenticateUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
