package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.CacaoVerifier;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.json_rpc.domain.GetSessionAuthenticateRequest;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.link_mode.LinkModeStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*HB¢\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00170/H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00060"}, d2 = {"Lcom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase;", "", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "pairingInterface", "Lcom/reown/android/pairing/client/PairingInterface;", "cacaoVerifier", "Lcom/reown/android/internal/common/signing/cacao/CacaoVerifier;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "authenticateResponseTopicRepository", "Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "getSessionAuthenticateRequest", "Lcom/reown/sign/json_rpc/domain/GetSessionAuthenticateRequest;", "linkModeStorageRepository", "Lcom/reown/sign/storage/link_mode/LinkModeStorageRepository;", "<init>", "(Lcom/reown/android/pairing/handler/PairingControllerInterface;Lcom/reown/android/pairing/client/PairingInterface;Lcom/reown/android/internal/common/signing/cacao/CacaoVerifier;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;Lcom/reown/foundation/util/Logger;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/sign/json_rpc/domain/GetSessionAuthenticateRequest;Lcom/reown/sign/storage/link_mode/LinkModeStorageRepository;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "wcResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "(Lcom/reown/android/internal/common/model/WCResponse;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "areEVMAndCAIP2Chains", "", "chains", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOnSessionAuthenticateResponseUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionAuthenticateResponseUseCase.kt\ncom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,183:1\n1740#2,3:184\n*S KotlinDebug\n*F\n+ 1 OnSessionAuthenticateResponseUseCase.kt\ncom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase\n*L\n182#1:184,3\n*E\n"})
public final class OnSessionAuthenticateResponseUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    /* access modifiers changed from: private */
    @NotNull
    public final AuthenticateResponseTopicRepository authenticateResponseTopicRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final CacaoVerifier cacaoVerifier;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    /* access modifiers changed from: private */
    @NotNull
    public final KeyManagementRepository crypto;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final GetSessionAuthenticateRequest getSessionAuthenticateRequest;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final LinkModeStorageRepository linkModeStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final PairingControllerInterface pairingController;
    /* access modifiers changed from: private */
    @NotNull
    public final PairingInterface pairingInterface;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;

    public OnSessionAuthenticateResponseUseCase(@NotNull PairingControllerInterface pairingControllerInterface, @NotNull PairingInterface pairingInterface2, @NotNull CacaoVerifier cacaoVerifier2, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull KeyManagementRepository keyManagementRepository, @NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull AuthenticateResponseTopicRepository authenticateResponseTopicRepository2, @NotNull Logger logger2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull GetSessionAuthenticateRequest getSessionAuthenticateRequest2, @NotNull LinkModeStorageRepository linkModeStorageRepository2) {
        Intrinsics.checkNotNullParameter(pairingControllerInterface, "pairingController");
        Intrinsics.checkNotNullParameter(pairingInterface2, "pairingInterface");
        Intrinsics.checkNotNullParameter(cacaoVerifier2, "cacaoVerifier");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(authenticateResponseTopicRepository2, "authenticateResponseTopicRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(getSessionAuthenticateRequest2, "getSessionAuthenticateRequest");
        Intrinsics.checkNotNullParameter(linkModeStorageRepository2, "linkModeStorageRepository");
        this.pairingController = pairingControllerInterface;
        this.pairingInterface = pairingInterface2;
        this.cacaoVerifier = cacaoVerifier2;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.crypto = keyManagementRepository;
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.authenticateResponseTopicRepository = authenticateResponseTopicRepository2;
        this.logger = logger2;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.getSessionAuthenticateRequest = getSessionAuthenticateRequest2;
        this.linkModeStorageRepository = linkModeStorageRepository2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean areEVMAndCAIP2Chains(java.util.List<java.lang.String> r3) {
        /*
            r2 = this;
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            boolean r2 = r3 instanceof java.util.Collection
            r0 = 1
            if (r2 == 0) goto L_0x0011
            r2 = r3
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0011
            goto L_0x0039
        L_0x0011:
            java.util.Iterator r2 = r3.iterator()
        L_0x0015:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0039
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            com.reown.android.internal.utils.CoreValidator r1 = com.reown.android.internal.utils.CoreValidator.INSTANCE
            boolean r1 = r1.isChainIdCAIP2Compliant(r3)
            if (r1 == 0) goto L_0x0038
            com.reown.sign.common.validator.SignValidator r1 = com.reown.sign.common.validator.SignValidator.INSTANCE
            java.lang.String r3 = r1.getNamespaceKeyFromChainId$sign_release(r3)
            java.lang.String r1 = "eip155"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)
            if (r3 == 0) goto L_0x0038
            goto L_0x0015
        L_0x0038:
            r0 = 0
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase.areEVMAndCAIP2Chains(java.util.List):boolean");
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCResponse wCResponse, @NotNull SignParams.SessionAuthenticateParams sessionAuthenticateParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionAuthenticateResponseUseCase$invoke$2(this, wCResponse, sessionAuthenticateParams, (Continuation<? super OnSessionAuthenticateResponseUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
