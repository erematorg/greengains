package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.verify.domain.ResolveAttestationIdUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
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

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eHB¢\u0006\u0002\u0010\u001fJ*\u0010 \u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase;", "", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "resolveAttestationIdUseCase", "Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/foundation/util/Logger;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "(Lcom/reown/android/internal/common/model/WCRequest;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSessionRequest", "sessionPeerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "verifyContext", "Lcom/reown/android/verify/model/VerifyContext;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOnSessionRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,142:1\n1#2:143\n*E\n"})
public final class OnSessionRequestUseCase {
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
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final ResolveAttestationIdUseCase resolveAttestationIdUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;

    public OnSessionRequestUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull ResolveAttestationIdUseCase resolveAttestationIdUseCase2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(resolveAttestationIdUseCase2, "resolveAttestationIdUseCase");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.resolveAttestationIdUseCase = resolveAttestationIdUseCase2;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.logger = logger2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004c, code lost:
        if (r10 == null) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emitSessionRequest(com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams r7, com.reown.android.internal.common.model.WCRequest r8, com.reown.android.internal.common.model.AppMetaData r9, com.reown.android.verify.model.VerifyContext r10) {
        /*
            r6 = this;
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r0 = new com.reown.sign.engine.model.EngineDO$SessionRequestEvent
            com.reown.sign.engine.model.EngineDO$SessionRequest r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDO(r7, r8, r9)
            com.reown.sign.engine.model.EngineDO$VerifyContext r9 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, "<this>")
            r0.<init>(r7, r9)
            java.util.concurrent.ConcurrentLinkedQueue r7 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            boolean r7 = r7.isEmpty()
            r9 = 0
            if (r7 == 0) goto L_0x0019
            goto L_0x004e
        L_0x0019:
            java.util.concurrent.ConcurrentLinkedQueue r7 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            java.util.Iterator r7 = r7.iterator()
        L_0x0021:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x0049
            java.lang.Object r10 = r7.next()
            r1 = r10
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r1 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r1
            com.reown.sign.engine.model.EngineDO$SessionRequest r2 = r1.getRequest()
            com.reown.android.internal.common.model.Expiry r2 = r2.getExpiry()
            if (r2 == 0) goto L_0x004a
            com.reown.android.internal.utils.CoreValidator r2 = com.reown.android.internal.utils.CoreValidator.INSTANCE
            com.reown.sign.engine.model.EngineDO$SessionRequest r1 = r1.getRequest()
            com.reown.android.internal.common.model.Expiry r1 = r1.getExpiry()
            boolean r1 = r2.isExpired(r1)
            if (r1 != 0) goto L_0x0021
            goto L_0x004a
        L_0x0049:
            r10 = r9
        L_0x004a:
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r10 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r10
            if (r10 != 0) goto L_0x004f
        L_0x004e:
            r10 = r0
        L_0x004f:
            java.util.concurrent.ConcurrentLinkedQueue r7 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            r7.add(r0)
            com.reown.foundation.util.Logger r7 = r6.logger
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Session request received on topic: "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r8 = " - emitting"
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.log((java.lang.String) r8)
            kotlinx.coroutines.CoroutineScope r0 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()
            com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase$emitSessionRequest$1 r3 = new com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase$emitSessionRequest$1
            r3.<init>(r6, r10, r9)
            r4 = 3
            r5 = 0
            r1 = 0
            r2 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase.emitSessionRequest(com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams, com.reown.android.internal.common.model.WCRequest, com.reown.android.internal.common.model.AppMetaData, com.reown.android.verify.model.VerifyContext):void");
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCRequest wCRequest, @NotNull SignParams.SessionRequestParams sessionRequestParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionRequestUseCase$invoke$2(wCRequest, this, sessionRequestParams, (Continuation<? super OnSessionRequestUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
