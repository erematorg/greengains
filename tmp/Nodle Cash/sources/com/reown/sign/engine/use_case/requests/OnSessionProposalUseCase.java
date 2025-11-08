package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.push.client.a;
import com.reown.android.verify.domain.ResolveAttestationIdUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HB¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0017\u0010\u0019¨\u0006$"}, d2 = {"Lcom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase;", "", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "resolveAttestationIdUseCase", "Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/proposal/ProposalStorageRepository;Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;Lcom/reown/android/pairing/handler/PairingControllerInterface;Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;Lcom/reown/foundation/util/Logger;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "isAuthenticateEnabled", "", "()Z", "isAuthenticateEnabled$delegate", "Lkotlin/Lazy;", "invoke", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "payloadParams", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "(Lcom/reown/android/internal/common/model/WCRequest;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSessionAuthenticateImplemented", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOnSessionProposalUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionProposalUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,105:1\n124#2,4:106\n142#3:110\n*S KotlinDebug\n*F\n+ 1 OnSessionProposalUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase\n*L\n49#1:106,4\n49#1:110\n*E\n"})
public final class OnSessionProposalUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertTelemetryEventUseCase insertEventUseCase;
    @NotNull
    private final Lazy isAuthenticateEnabled$delegate = LazyKt.lazy(new a(7));
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
    /* access modifiers changed from: private */
    @NotNull
    public final ResolveAttestationIdUseCase resolveAttestationIdUseCase;

    public OnSessionProposalUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull ProposalStorageRepository proposalStorageRepository2, @NotNull ResolveAttestationIdUseCase resolveAttestationIdUseCase2, @NotNull PairingControllerInterface pairingControllerInterface, @NotNull InsertTelemetryEventUseCase insertTelemetryEventUseCase, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(proposalStorageRepository2, "proposalStorageRepository");
        Intrinsics.checkNotNullParameter(resolveAttestationIdUseCase2, "resolveAttestationIdUseCase");
        Intrinsics.checkNotNullParameter(pairingControllerInterface, "pairingController");
        Intrinsics.checkNotNullParameter(insertTelemetryEventUseCase, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.proposalStorageRepository = proposalStorageRepository2;
        this.resolveAttestationIdUseCase = resolveAttestationIdUseCase2;
        this.pairingController = pairingControllerInterface;
        this.insertEventUseCase = insertTelemetryEventUseCase;
        this.logger = logger2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    private final boolean isAuthenticateEnabled() {
        return ((Boolean) this.isAuthenticateEnabled$delegate.getValue()).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final boolean isAuthenticateEnabled_delegate$lambda$0() {
        Koin koin = KoinApplicationKt.getWcKoinApp().getKoin();
        return ((Boolean) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Boolean.class), QualifierKt.named(AndroidCommonDITags.ENABLE_AUTHENTICATE), (Function0<? extends ParametersHolder>) null)).booleanValue();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r2 = r2.getMethods();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isSessionAuthenticateImplemented(com.reown.android.internal.common.model.WCRequest r2) {
        /*
            r1 = this;
            com.reown.android.pairing.handler.PairingControllerInterface r0 = r1.pairingController
            com.reown.foundation.common.model.Topic r2 = r2.getTopic()
            com.reown.android.internal.common.model.Pairing r2 = r0.getPairingByTopic(r2)
            if (r2 == 0) goto L_0x0022
            java.lang.String r2 = r2.getMethods()
            if (r2 == 0) goto L_0x0022
            java.lang.String r0 = "wc_sessionAuthenticate"
            boolean r2 = kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r2, (java.lang.CharSequence) r0, false, 2, (java.lang.Object) null)
            r0 = 1
            if (r2 != r0) goto L_0x0022
            boolean r1 = r1.isAuthenticateEnabled()
            if (r1 == 0) goto L_0x0022
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase.isSessionAuthenticateImplemented(com.reown.android.internal.common.model.WCRequest):boolean");
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCRequest wCRequest, @NotNull SignParams.SessionProposeParams sessionProposeParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionProposalUseCase$invoke$2(wCRequest, this, sessionProposeParams, (Continuation<? super OnSessionProposalUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
