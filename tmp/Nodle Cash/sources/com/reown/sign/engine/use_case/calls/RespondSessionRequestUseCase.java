package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.engine.model.tvf.TVF;
import com.reown.sign.json_rpc.domain.GetPendingJsonRpcHistoryEntryByIdUseCase;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
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

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J@\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020 0%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020 0'H@¢\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,H@¢\u0006\u0002\u0010-R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lcom/reown/sign/engine/use_case/calls/RespondSessionRequestUseCase;", "Lcom/reown/sign/engine/use_case/calls/RespondSessionRequestUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "getPendingJsonRpcHistoryEntryByIdUseCase", "Lcom/reown/sign/json_rpc/domain/GetPendingJsonRpcHistoryEntryByIdUseCase;", "linkModeJsonRpcInteractor", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "logger", "Lcom/reown/foundation/util/Logger;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "tvf", "Lcom/reown/sign/engine/model/tvf/TVF;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/sign/json_rpc/domain/GetPendingJsonRpcHistoryEntryByIdUseCase;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;Lcom/reown/foundation/util/Logger;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/TVF;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "respondSessionRequest", "", "topic", "jsonRpcResponse", "Lcom/reown/android/internal/common/JsonRpcResponse;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Lcom/reown/android/internal/common/JsonRpcResponse;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removePendingSessionRequestAndEmit", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRespondSessionRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RespondSessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/RespondSessionRequestUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,155:1\n1#2:156\n*E\n"})
public final class RespondSessionRequestUseCase implements RespondSessionRequestUseCaseInterface {
    @NotNull
    private final MutableSharedFlow<EngineEvent> _events;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final GetPendingJsonRpcHistoryEntryByIdUseCase getPendingJsonRpcHistoryEntryByIdUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final TVF tvf;
    @NotNull
    private final VerifyContextStorageRepository verifyContextStorageRepository;

    public RespondSessionRequestUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull GetPendingJsonRpcHistoryEntryByIdUseCase getPendingJsonRpcHistoryEntryByIdUseCase2, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, @NotNull Logger logger2, @NotNull VerifyContextStorageRepository verifyContextStorageRepository2, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull TVF tvf2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(getPendingJsonRpcHistoryEntryByIdUseCase2, "getPendingJsonRpcHistoryEntryByIdUseCase");
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(tvf2, "tvf");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.getPendingJsonRpcHistoryEntryByIdUseCase = getPendingJsonRpcHistoryEntryByIdUseCase2;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
        this.logger = logger2;
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.tvf = tvf2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0076 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c0 A[EDGE_INSN: B:45:0x00c0->B:36:0x00c0 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removePendingSessionRequestAndEmit(long r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1 r0 = (com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1 r0 = new com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1
            r0.<init>(r7, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r7 = r0.L$0
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r7 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00da
        L_0x0031:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0039:
            long r8 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x004f
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r10)
            com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository r10 = r7.verifyContextStorageRepository
            r0.J$0 = r8
            r0.label = r4
            java.lang.Object r10 = r10.delete(r8, r0)
            if (r10 != r1) goto L_0x004f
            return r1
        L_0x004f:
            java.util.concurrent.ConcurrentLinkedQueue r10 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            java.util.Iterator r10 = r10.iterator()
        L_0x0057:
            boolean r2 = r10.hasNext()
            r4 = 0
            if (r2 == 0) goto L_0x0076
            java.lang.Object r2 = r10.next()
            r5 = r2
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r5 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r5
            com.reown.sign.engine.model.EngineDO$SessionRequest r5 = r5.getRequest()
            com.reown.sign.engine.model.EngineDO$SessionRequest$JSONRPCRequest r5 = r5.getRequest()
            long r5 = r5.getId()
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 != 0) goto L_0x0057
            goto L_0x0077
        L_0x0076:
            r2 = r4
        L_0x0077:
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r2 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r2
            if (r2 == 0) goto L_0x0086
            java.util.concurrent.ConcurrentLinkedQueue r10 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            boolean r10 = r10.remove(r2)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)
        L_0x0086:
            java.util.concurrent.ConcurrentLinkedQueue r10 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x00da
            java.util.concurrent.ConcurrentLinkedQueue r10 = com.reown.sign.engine.SessionRequestQueueKt.getSessionRequestEventsQueue()
            java.util.Iterator r10 = r10.iterator()
        L_0x0098:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x00c0
            java.lang.Object r2 = r10.next()
            r5 = r2
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r5 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r5
            com.reown.sign.engine.model.EngineDO$SessionRequest r6 = r5.getRequest()
            com.reown.android.internal.common.model.Expiry r6 = r6.getExpiry()
            if (r6 == 0) goto L_0x00bf
            com.reown.android.internal.utils.CoreValidator r6 = com.reown.android.internal.utils.CoreValidator.INSTANCE
            com.reown.sign.engine.model.EngineDO$SessionRequest r5 = r5.getRequest()
            com.reown.android.internal.common.model.Expiry r5 = r5.getExpiry()
            boolean r5 = r6.isExpired(r5)
            if (r5 != 0) goto L_0x0098
        L_0x00bf:
            r4 = r2
        L_0x00c0:
            com.reown.sign.engine.model.EngineDO$SessionRequestEvent r4 = (com.reown.sign.engine.model.EngineDO.SessionRequestEvent) r4
            if (r4 == 0) goto L_0x00da
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.type.EngineEvent> r7 = r7._events
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$0 = r10
            r0.J$0 = r8
            r8 = 0
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.emit(r4, r0)
            if (r7 != r1) goto L_0x00da
            return r1
        L_0x00da:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase.removePendingSessionRequestAndEmit(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public Object respondSessionRequest(@NotNull String str, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new RespondSessionRequestUseCase$respondSessionRequest$2(str, this, jsonRpcResponse, function1, function0, (Continuation<? super RespondSessionRequestUseCase$respondSessionRequest$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
