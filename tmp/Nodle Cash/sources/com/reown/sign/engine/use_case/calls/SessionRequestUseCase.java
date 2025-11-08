package com.reown.sign.engine.use_case.calls;

import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.engine.domain.wallet_service.WalletServiceFinder;
import com.reown.sign.engine.domain.wallet_service.WalletServiceRequester;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.tvf.TVF;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.apache.commons.text.StringSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J>\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020$0(2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020$0(H@¢\u0006\u0002\u0010,JP\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020)2\u0006\u00101\u001a\u0002022\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020$0(2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020$0(H\u0002J>\u00103\u001a\u00020$*\u00020\u00002\u0006\u00101\u001a\u0002022\u0006\u0010%\u001a\u00020&2\u0006\u00104\u001a\u00020\r2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020$0(H@¢\u0006\u0002\u00105J2\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020)2\u001a\b\u0002\u00108\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020:09\u0012\u0004\u0012\u00020$0(H@¢\u0006\u0002\u0010;R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0019X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001e¨\u0006<"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCase;", "Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCaseInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "linkModeJsonRpcInteractor", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "logger", "Lcom/reown/foundation/util/Logger;", "tvf", "Lcom/reown/sign/engine/model/tvf/TVF;", "walletServiceFinder", "Lcom/reown/sign/engine/domain/wallet_service/WalletServiceFinder;", "walletServiceRequester", "Lcom/reown/sign/engine/domain/wallet_service/WalletServiceRequester;", "<init>", "(Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/foundation/util/Logger;Lcom/reown/sign/engine/model/tvf/TVF;Lcom/reown/sign/engine/domain/wallet_service/WalletServiceFinder;Lcom/reown/sign/engine/domain/wallet_service/WalletServiceRequester;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "requestEvents", "Lkotlinx/coroutines/flow/SharedFlow;", "getRequestEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "_errors", "Lcom/reown/android/internal/common/model/SDKError;", "errors", "getErrors", "sessionRequest", "", "request", "Lcom/reown/sign/engine/model/EngineDO$Request;", "onSuccess", "Lkotlin/Function1;", "", "onFailure", "", "(Lcom/reown/sign/engine/model/EngineDO$Request;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerRelayRequest", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "nowInSeconds", "sessionPayload", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;", "triggerLinkModeRequest", "peerAppLink", "(Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCase;Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;Lcom/reown/sign/engine/model/EngineDO$Request;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectResponse", "id", "onResponse", "Lkotlin/Result;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "(JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionRequestUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 4 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 5 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,217:1\n1#2:218\n17#3:219\n19#3:223\n46#4:220\n51#4:222\n105#5:221\n*S KotlinDebug\n*F\n+ 1 SessionRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionRequestUseCase\n*L\n203#1:219\n203#1:223\n203#1:220\n203#1:222\n203#1:221\n*E\n"})
public final class SessionRequestUseCase implements SessionRequestUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<SDKError> _errors;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    @NotNull
    private final String clientId;
    @NotNull
    private final SharedFlow<SDKError> errors;
    @NotNull
    private final InsertEventUseCase insertEventUseCase;
    @NotNull
    private final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    @NotNull
    private final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    @NotNull
    private final SharedFlow<EngineEvent> requestEvents;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;
    @NotNull
    private final TVF tvf;
    /* access modifiers changed from: private */
    @NotNull
    public final WalletServiceFinder walletServiceFinder;
    /* access modifiers changed from: private */
    @NotNull
    public final WalletServiceRequester walletServiceRequester;

    public SessionRequestUseCase(@NotNull SessionStorageRepository sessionStorageRepository2, @NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull Logger logger2, @NotNull TVF tvf2, @NotNull WalletServiceFinder walletServiceFinder2, @NotNull WalletServiceRequester walletServiceRequester2) {
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(tvf2, "tvf");
        Intrinsics.checkNotNullParameter(walletServiceFinder2, "walletServiceFinder");
        Intrinsics.checkNotNullParameter(walletServiceRequester2, "walletServiceRequester");
        this.sessionStorageRepository = sessionStorageRepository2;
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.logger = logger2;
        this.tvf = tvf2;
        this.walletServiceFinder = walletServiceFinder2;
        this.walletServiceRequester = walletServiceRequester2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.requestEvents = FlowKt.asSharedFlow(MutableSharedFlow$default);
        MutableSharedFlow<SDKError> MutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._errors = MutableSharedFlow$default2;
        this.errors = FlowKt.asSharedFlow(MutableSharedFlow$default2);
    }

    /* access modifiers changed from: private */
    public final Object collectResponse(long j2, Function1<? super Result<JsonRpcResponse.JsonRpcResult>, Unit> function1, Continuation<? super Unit> continuation) {
        Object collect = new SessionRequestUseCase$collectResponse$$inlined$filter$1(this.jsonRpcInteractor.getPeerResponse(), j2).collect(new SessionRequestUseCase$collectResponse$4(function1), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public static /* synthetic */ Object collectResponse$default(SessionRequestUseCase sessionRequestUseCase, long j2, Function1 function1, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            function1 = new g(4);
        }
        return sessionRequestUseCase.collectResponse(j2, function1, continuation);
    }

    /* access modifiers changed from: private */
    public static final Unit collectResponse$lambda$5(Result result) {
        return Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit>} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object triggerLinkModeRequest(com.reown.sign.engine.use_case.calls.SessionRequestUseCase r26, com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest r27, com.reown.sign.engine.model.EngineDO.Request r28, java.lang.String r29, kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            r25 = this;
            r0 = r26
            r1 = r31
            boolean r2 = r1 instanceof com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerLinkModeRequest$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerLinkModeRequest$1 r2 = (com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerLinkModeRequest$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001e
        L_0x0017:
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerLinkModeRequest$1 r2 = new com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerLinkModeRequest$1
            r3 = r25
            r2.<init>(r3, r1)
        L_0x001e:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x0051
            if (r4 != r5) goto L_0x0049
            java.lang.Object r0 = r2.L$4
            r3 = r0
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            java.lang.Object r0 = r2.L$3
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r2.L$2
            com.reown.sign.engine.model.EngineDO$Request r0 = (com.reown.sign.engine.model.EngineDO.Request) r0
            java.lang.Object r0 = r2.L$1
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest r0 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest) r0
            java.lang.Object r0 = r2.L$0
            com.reown.sign.engine.use_case.calls.SessionRequestUseCase r0 = (com.reown.sign.engine.use_case.calls.SessionRequestUseCase) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ Exception -> 0x0045 }
            goto L_0x00df
        L_0x0045:
            r0 = move-exception
            r5 = r3
            goto L_0x00dc
        L_0x0049:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r1)
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface r6 = r0.linkModeJsonRpcInteractor     // Catch:{ Exception -> 0x00d9 }
            com.reown.foundation.common.model.Topic r8 = new com.reown.foundation.common.model.Topic     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r1 = r28.getTopic()     // Catch:{ Exception -> 0x00d9 }
            r8.<init>(r1)     // Catch:{ Exception -> 0x00d9 }
            r11 = 8
            r12 = 0
            r10 = 0
            r7 = r27
            r9 = r29
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface.triggerRequest$default(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x00d9 }
            com.reown.android.pulse.domain.InsertEventUseCase r1 = r0.insertEventUseCase     // Catch:{ Exception -> 0x00d9 }
            com.reown.android.pulse.model.properties.Props r4 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r6 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r7 = com.reown.android.internal.common.model.Tags.SESSION_REQUEST_LINK_MODE     // Catch:{ Exception -> 0x00d9 }
            int r7 = r7.getId()     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x00d9 }
            com.reown.android.pulse.model.properties.Properties r15 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x00d9 }
            long r8 = r27.getId()     // Catch:{ Exception -> 0x00d9 }
            java.lang.Long r17 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r14 = r0.clientId     // Catch:{ Exception -> 0x00d9 }
            com.reown.android.pulse.model.Direction r8 = com.reown.android.pulse.model.Direction.SENT     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r19 = r8.getState()     // Catch:{ Exception -> 0x00d9 }
            r21 = 2303(0x8ff, float:3.227E-42)
            r22 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r16 = 0
            r18 = 0
            r20 = 0
            r23 = 0
            r8 = r15
            r24 = r14
            r14 = r16
            r5 = r15
            r15 = r18
            r16 = r20
            r18 = r24
            r20 = r23
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x00d9 }
            r4.<init>(r6, r7, r5)     // Catch:{ Exception -> 0x00d9 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r26)     // Catch:{ Exception -> 0x00d9 }
            r2.L$0 = r0     // Catch:{ Exception -> 0x00d9 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x00d9 }
            r2.L$1 = r0     // Catch:{ Exception -> 0x00d9 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r28)     // Catch:{ Exception -> 0x00d9 }
            r2.L$2 = r0     // Catch:{ Exception -> 0x00d9 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r29)     // Catch:{ Exception -> 0x00d9 }
            r2.L$3 = r0     // Catch:{ Exception -> 0x00d9 }
            r5 = r30
            r2.L$4 = r5     // Catch:{ Exception -> 0x00d7 }
            r0 = 1
            r2.label = r0     // Catch:{ Exception -> 0x00d7 }
            java.lang.Object r0 = r1.invoke(r4, r2)     // Catch:{ Exception -> 0x00d7 }
            if (r0 != r3) goto L_0x00df
            return r3
        L_0x00d7:
            r0 = move-exception
            goto L_0x00dc
        L_0x00d9:
            r0 = move-exception
            r5 = r30
        L_0x00dc:
            r5.invoke(r0)
        L_0x00df:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionRequestUseCase.triggerLinkModeRequest(com.reown.sign.engine.use_case.calls.SessionRequestUseCase, com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest, com.reown.sign.engine.model.EngineDO$Request, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void triggerRelayRequest(Expiry expiry, long j2, SignRpc.SessionRequest sessionRequest, EngineDO.Request request, Function1<? super Long, Unit> function1, Function1<? super Throwable, Unit> function12) {
        long fiveMinutesInSeconds = Time.getFiveMinutesInSeconds();
        long seconds = expiry.getSeconds() - j2;
        Long valueOf = Long.valueOf(seconds);
        if (seconds < fiveMinutesInSeconds) {
            valueOf = null;
        }
        if (valueOf != null) {
            fiveMinutesInSeconds = valueOf.longValue();
        }
        Ttl ttl = new Ttl(fiveMinutesInSeconds);
        Triple<List<String>, List<String>, String> collect = this.tvf.collect(sessionRequest.getRpcMethod(), sessionRequest.getRpcParams(), sessionRequest.getParams().getChainId());
        Tags tags = Tags.SESSION_REQUEST;
        long id = sessionRequest.getId();
        String third = collect.getThird();
        IrnParams irnParams = new IrnParams(tags, ttl, Long.valueOf(id), collect.getFirst(), third, (List) null, collect.getSecond(), true, 32, (DefaultConstructorMarker) null);
        long seconds2 = expiry.getSeconds() - j2;
        Logger logger2 = this.logger;
        String topic = request.getTopic();
        logger2.log("Sending session request on topic: " + topic + StringSubstitutor.DEFAULT_VAR_END);
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = this.jsonRpcInteractor;
        RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(relayJsonRpcInteractorInterface, new Topic(request.getTopic()), irnParams, sessionRequest, (EnvelopeType) null, (Participants) null, new c(this, request, function1, sessionRequest, seconds2, 1), new k(this, function12, 1), 24, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit triggerRelayRequest$lambda$3(SessionRequestUseCase sessionRequestUseCase, EngineDO.Request request, Function1 function1, SignRpc.SessionRequest sessionRequest, long j2) {
        Logger logger2 = sessionRequestUseCase.logger;
        String topic = request.getTopic();
        logger2.log("Session request sent successfully on topic: " + topic);
        function1.invoke(Long.valueOf(sessionRequest.getId()));
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SessionRequestUseCase$triggerRelayRequest$1$1(j2, sessionRequestUseCase, sessionRequest, (Continuation<? super SessionRequestUseCase$triggerRelayRequest$1$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit triggerRelayRequest$lambda$4(SessionRequestUseCase sessionRequestUseCase, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Logger logger2 = sessionRequestUseCase.logger;
        logger2.error("Sending session request error: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public SharedFlow<SDKError> getErrors() {
        return this.errors;
    }

    @NotNull
    public SharedFlow<EngineEvent> getRequestEvents() {
        return this.requestEvents;
    }

    @Nullable
    public Object sessionRequest(@NotNull EngineDO.Request request, @NotNull Function1<? super Long, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new SessionRequestUseCase$sessionRequest$2(this, request, function12, function1, (Continuation<? super SessionRequestUseCase$sessionRequest$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
