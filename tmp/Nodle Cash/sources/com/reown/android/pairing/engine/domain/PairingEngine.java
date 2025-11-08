package com.reown.android.pairing.engine.domain;

import A.a;
import C1.C0233a;
import G1.C0235a;
import S0.h;
import androidx.browser.trusted.c;
import androidx.compose.material.C0140g;
import androidx.compose.material.C0179t;
import androidx.compose.runtime.e;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.internal.Validator;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.exception.ExpiredPairingException;
import com.reown.android.internal.common.exception.ExpiredPairingURIException;
import com.reown.android.internal.common.exception.MalformedWalletConnectUri;
import com.reown.android.internal.common.exception.MessagesKt;
import com.reown.android.internal.common.exception.NoInternetConnectionException;
import com.reown.android.internal.common.exception.NoRelayConnectionException;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.SymmetricKey;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WalletConnectUri;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.android.pairing.engine.model.EngineDO;
import com.reown.android.pairing.model.Expiration;
import com.reown.android.pairing.model.PairingJsonRpcMethod;
import com.reown.android.pairing.model.PairingParams;
import com.reown.android.pairing.model.PairingRpc;
import com.reown.android.pairing.model.mapper.PairingMapperKt;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.pulse.domain.SendBatchEventUseCase;
import com.reown.android.pulse.model.Trace;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.util.UtilFunctionsKt;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 r2\u00020\u0001:\u0001rB_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J(\u0010;\u001a\u0004\u0018\u00010<2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0015J0\u0010A\u001a\u00020.2\u0006\u0010B\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020.0D2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>J$\u0010E\u001a\u00020.2\u0006\u0010F\u001a\u00020\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>H\u0007J8\u0010G\u001a\u00020.2\u0006\u0010F\u001a\u00020\u00152\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020.0>2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>H\u0007J\f\u0010H\u001a\b\u0012\u0004\u0012\u00020J0IJ\u001f\u0010K\u001a\u00020.2\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150M\"\u00020\u0015¢\u0006\u0002\u0010NJ\u0010\u0010O\u001a\u0004\u0018\u00010J2\u0006\u0010F\u001a\u00020#J\"\u0010P\u001a\u00020.2\u0006\u0010F\u001a\u00020\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>J\u001e\u0010Q\u001a\u00020.2\u0006\u0010F\u001a\u00020\u00152\u0006\u0010R\u001a\u00020\u00052\u0006\u0010S\u001a\u00020TJ\u000e\u0010U\u001a\u00020.2\u0006\u0010F\u001a\u00020\u0015J\b\u0010V\u001a\u00020.H\u0002J\b\u0010W\u001a\u00020.H\u0002J\u000e\u0010X\u001a\u00020.H@¢\u0006\u0002\u0010YJ\b\u0010Z\u001a\u00020.H\u0002J\b\u0010[\u001a\u00020.H\u0002J\u0016\u0010\\\u001a\b\u0012\u0004\u0012\u00020.062\u0006\u0010]\u001a\u00020^H\u0002J\b\u0010_\u001a\u00020\u001aH\u0002J\u001e\u0010`\u001a\u00020.2\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020dH@¢\u0006\u0002\u0010eJ\u0010\u0010f\u001a\u00020.2\u0006\u0010a\u001a\u00020bH\u0003J@\u0010g\u001a\u00020.2\u0006\u0010h\u001a\u00020i2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020.0>2\u0006\u0010F\u001a\u00020\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>H\u0003J\b\u0010j\u001a\u00020#H\u0002JG\u0010k\u001a\u00020.2\u0006\u0010F\u001a\u00020\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020.0>2!\u0010l\u001a\u001d\u0012\u0013\u0012\u00110J¢\u0006\f\bm\u0012\b\bn\u0012\u0004\b\b(o\u0012\u0004\u0012\u00020.0>H\u0002J\f\u0010p\u001a\u00020\u001f*\u00020JH\u0002J\u0010\u0010q\u001a\u00020\u001f2\u0006\u0010F\u001a\u00020\u0015H\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u001cX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0004¢\u0006\u0002\n\u0000R&\u0010 \u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150$0\"0!X\u0004¢\u0006\u0002\n\u0000R)\u0010%\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150$0\"0&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020*0!¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0!X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020.0&¢\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020!X\u0004¢\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u0002020&¢\u0006\b\n\u0000\u001a\u0004\b4\u0010(R!\u00105\u001a\b\u0012\u0004\u0012\u00020*068FX\u0002¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b7\u00108¨\u0006s"}, d2 = {"Lcom/reown/android/pairing/engine/domain/PairingEngine;", "", "logger", "Lcom/reown/foundation/util/Logger;", "selfMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "metadataRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "pairingRepository", "Lcom/reown/android/internal/common/storage/pairing/PairingStorageRepositoryInterface;", "insertTelemetryEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "sendBatchEventUseCase", "Lcom/reown/android/pulse/domain/SendBatchEventUseCase;", "clientId", "", "userAgent", "<init>", "(Lcom/reown/foundation/util/Logger;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/internal/common/storage/pairing/PairingStorageRepositoryInterface;Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;Lcom/reown/android/pulse/domain/InsertEventUseCase;Lcom/reown/android/pulse/domain/SendBatchEventUseCase;Ljava/lang/String;Ljava/lang/String;)V", "jsonRpcRequestsJob", "Lkotlinx/coroutines/Job;", "setOfRegisteredMethods", "", "_isPairingStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_storedPairingTopicFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/Topic;", "", "storedPairingTopicFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getStoredPairingTopicFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "internalErrorFlow", "Lcom/reown/android/internal/common/model/SDKError;", "getInternalErrorFlow", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "_checkVerifyKeyFlow", "", "checkVerifyKeyFlow", "getCheckVerifyKeyFlow", "_engineEvent", "Lcom/reown/android/pairing/engine/model/EngineDO;", "engineEvent", "getEngineEvent", "jsonRpcErrorFlow", "Lkotlinx/coroutines/flow/Flow;", "getJsonRpcErrorFlow", "()Lkotlinx/coroutines/flow/Flow;", "jsonRpcErrorFlow$delegate", "Lkotlin/Lazy;", "create", "Lcom/reown/android/Core$Model$Pairing;", "onFailure", "Lkotlin/Function1;", "", "methods", "pair", "uri", "onSuccess", "Lkotlin/Function0;", "disconnect", "topic", "ping", "getPairings", "", "Lcom/reown/android/internal/common/model/Pairing;", "register", "method", "", "([Ljava/lang/String;)V", "getPairingByTopic", "setRequestReceived", "updateMetadata", "metadata", "metaDataType", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "deleteAndUnsubscribePairing", "sendEvents", "resubscribeToPairingTopics", "sendBatchSubscribeForPairings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pairingsExpiryWatcher", "isPairingStateWatcher", "repeatableFlow", "interval", "", "collectJsonRpcRequestsFlow", "onPairingDelete", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "params", "Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "(Lcom/reown/android/internal/common/model/WCRequest;Lcom/reown/android/pairing/model/PairingParams$DeleteParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPing", "onPingSuccess", "pingPayload", "Lcom/reown/android/pairing/model/PairingRpc$PairingPing;", "generateTopic", "getPairing", "onPairing", "Lkotlin/ParameterName;", "name", "pairing", "isNotExpired", "isPairingValid", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPairingEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,466:1\n49#2:467\n51#2:471\n17#2:480\n19#2:484\n17#2:485\n19#2:489\n49#2:490\n51#2:494\n46#3:468\n51#3:470\n46#3:481\n51#3:483\n46#3:486\n51#3:488\n46#3:491\n51#3:493\n105#4:469\n105#4:482\n105#4:487\n105#4:492\n1#5:472\n774#6:473\n865#6,2:474\n1563#6:476\n1634#6,3:477\n*S KotlinDebug\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine\n*L\n103#1:467\n103#1:471\n365#1:480\n365#1:484\n118#1:485\n118#1:489\n122#1:490\n122#1:494\n103#1:468\n103#1:470\n365#1:481\n365#1:483\n118#1:486\n118#1:488\n122#1:491\n122#1:493\n103#1:469\n365#1:482\n118#1:487\n122#1:492\n321#1:473\n321#1:474,2\n321#1:476\n321#1:477,3\n*E\n"})
public final class PairingEngine {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long WATCHER_INTERVAL = 30000;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<Unit> _checkVerifyKeyFlow;
    @NotNull
    private final MutableSharedFlow<EngineDO> _engineEvent;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableStateFlow<Boolean> _isPairingStateFlow;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<Pair<Topic, List<String>>> _storedPairingTopicFlow;
    @NotNull
    private final SharedFlow<Unit> checkVerifyKeyFlow;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    /* access modifiers changed from: private */
    @NotNull
    public final KeyManagementRepository crypto;
    @NotNull
    private final SharedFlow<EngineDO> engineEvent;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertTelemetryEventUseCase insertTelemetryEventUseCase;
    @NotNull
    private final MutableSharedFlow<SDKError> internalErrorFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
    @NotNull
    private final Lazy jsonRpcErrorFlow$delegate;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @Nullable
    public Job jsonRpcRequestsJob;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final PairingStorageRepositoryInterface pairingRepository;
    @NotNull
    private final AppMetaData selfMetaData;
    /* access modifiers changed from: private */
    @NotNull
    public final SendBatchEventUseCase sendBatchEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final Set<String> setOfRegisteredMethods;
    @NotNull
    private final SharedFlow<Pair<Topic, List<String>>> storedPairingTopicFlow;
    /* access modifiers changed from: private */
    @NotNull
    public final String userAgent;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/android/pairing/engine/domain/PairingEngine$Companion;", "", "<init>", "()V", "WATCHER_INTERVAL", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public PairingEngine(@NotNull Logger logger2, @NotNull AppMetaData appMetaData, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull KeyManagementRepository keyManagementRepository, @NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull PairingStorageRepositoryInterface pairingStorageRepositoryInterface, @NotNull InsertTelemetryEventUseCase insertTelemetryEventUseCase2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull SendBatchEventUseCase sendBatchEventUseCase2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(appMetaData, "selfMetaData");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataRepository");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(pairingStorageRepositoryInterface, "pairingRepository");
        Intrinsics.checkNotNullParameter(insertTelemetryEventUseCase2, "insertTelemetryEventUseCase");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(sendBatchEventUseCase2, "sendBatchEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(str2, "userAgent");
        this.logger = logger2;
        this.selfMetaData = appMetaData;
        this.metadataRepository = metadataStorageRepositoryInterface;
        this.crypto = keyManagementRepository;
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.pairingRepository = pairingStorageRepositoryInterface;
        this.insertTelemetryEventUseCase = insertTelemetryEventUseCase2;
        this.insertEventUseCase = insertEventUseCase2;
        this.sendBatchEventUseCase = sendBatchEventUseCase2;
        this.clientId = str;
        this.userAgent = str2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.setOfRegisteredMethods = linkedHashSet;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isPairingStateFlow = MutableStateFlow;
        MutableSharedFlow<Pair<Topic, List<String>>> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._storedPairingTopicFlow = MutableSharedFlow$default;
        this.storedPairingTopicFlow = FlowKt.asSharedFlow(MutableSharedFlow$default);
        MutableSharedFlow<Unit> MutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._checkVerifyKeyFlow = MutableSharedFlow$default2;
        CoroutineScope scope = WalletConnectScopeKt.getScope();
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.checkVerifyKeyFlow = FlowKt.shareIn(MutableSharedFlow$default2, scope, companion.getLazily(), 1);
        MutableSharedFlow<EngineDO> MutableSharedFlow$default3 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._engineEvent = MutableSharedFlow$default3;
        this.engineEvent = FlowKt.shareIn(FlowKt.merge((Flow<? extends T>[]) new Flow[]{MutableSharedFlow$default3, new PairingEngine$special$$inlined$map$1(MutableStateFlow)}), WalletConnectScopeKt.getScope(), companion.getLazily(), 0);
        linkedHashSet.addAll(CollectionsKt.listOf(PairingJsonRpcMethod.WC_PAIRING_DELETE, PairingJsonRpcMethod.WC_PAIRING_PING));
        resubscribeToPairingTopics();
        pairingsExpiryWatcher();
        isPairingStateWatcher();
        sendEvents();
        this.jsonRpcErrorFlow$delegate = LazyKt.lazy(new C0233a(this, 11));
    }

    /* access modifiers changed from: private */
    public final Job collectJsonRpcRequestsFlow() {
        return FlowKt.launchIn(FlowKt.onEach(new PairingEngine$collectJsonRpcRequestsFlow$$inlined$filter$1(this.jsonRpcInteractor.getClientSyncJsonRpc()), new PairingEngine$collectJsonRpcRequestsFlow$2(this, (Continuation<? super PairingEngine$collectJsonRpcRequestsFlow$2>) null)), WalletConnectScopeKt.getScope());
    }

    public static /* synthetic */ Core.Model.Pairing create$default(PairingEngine pairingEngine, Function1 function1, String str, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = null;
        }
        return pairingEngine.create(function1, str);
    }

    /* access modifiers changed from: private */
    public static final Unit disconnect$lambda$18(PairingEngine pairingEngine, String str) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$disconnect$1$1(pairingEngine, str, (Continuation<? super PairingEngine$disconnect$1$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit disconnect$lambda$19(PairingEngine pairingEngine, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Logger logger2 = pairingEngine.logger;
        logger2.error("Sending session disconnect error: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    private final Topic generateTopic() {
        return new Topic(UtilFunctionsKt.bytesToHex(UtilFunctionsKt.randomBytes(32)));
    }

    private final void getPairing(String str, Function1<? super Throwable, Unit> function1, Function1<? super Pairing, Unit> function12) {
        Pairing pairingOrNullByTopic = this.pairingRepository.getPairingOrNullByTopic(new Topic(str));
        if (pairingOrNullByTopic == null) {
            function1.invoke(new IllegalStateException(a.l("Pairing for topic ", str, " does not exist")));
        } else if (isNotExpired(pairingOrNullByTopic)) {
            function12.invoke(pairingOrNullByTopic);
        } else {
            function1.invoke(new IllegalStateException(a.l("Pairing for topic ", str, " is expired")));
        }
    }

    /* access modifiers changed from: private */
    public final boolean isNotExpired(Pairing pairing) {
        boolean z2 = pairing.getExpiry().getSeconds() > Time.getCurrentTimeInSeconds();
        if (!z2) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$isNotExpired$1$1(this, pairing, (Continuation<? super PairingEngine$isNotExpired$1$1>) null), 3, (Object) null);
        }
        return z2;
    }

    private final void isPairingStateWatcher() {
        FlowKt.launchIn(FlowKt.onEach(repeatableFlow(30000), new PairingEngine$isPairingStateWatcher$1(this, (Continuation<? super PairingEngine$isPairingStateWatcher$1>) null)), WalletConnectScopeKt.getScope());
    }

    @Deprecated(message = "This method has been deprecated. It will be removed soon.")
    private final boolean isPairingValid(String str) {
        Pairing pairingOrNullByTopic = this.pairingRepository.getPairingOrNullByTopic(new Topic(str));
        if (pairingOrNullByTopic != null) {
            return isNotExpired(pairingOrNullByTopic);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final Flow jsonRpcErrorFlow_delegate$lambda$3(PairingEngine pairingEngine) {
        return new PairingEngine$jsonRpcErrorFlow_delegate$lambda$3$$inlined$map$1(FlowKt.onEach(new PairingEngine$jsonRpcErrorFlow_delegate$lambda$3$$inlined$filter$1(pairingEngine.jsonRpcInteractor.getClientSyncJsonRpc(), pairingEngine), new PairingEngine$jsonRpcErrorFlow$2$2(pairingEngine, (Continuation<? super PairingEngine$jsonRpcErrorFlow$2$2>) null)));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    @kotlin.Deprecated(message = "This method has been deprecated. It will be removed soon.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onPairingDelete(com.reown.android.internal.common.model.WCRequest r18, com.reown.android.pairing.model.PairingParams.DeleteParams r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            boolean r2 = r0 instanceof com.reown.android.pairing.engine.domain.PairingEngine$onPairingDelete$1
            if (r2 == 0) goto L_0x0017
            r2 = r0
            com.reown.android.pairing.engine.domain.PairingEngine$onPairingDelete$1 r2 = (com.reown.android.pairing.engine.domain.PairingEngine$onPairingDelete$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.reown.android.pairing.engine.domain.PairingEngine$onPairingDelete$1 r2 = new com.reown.android.pairing.engine.domain.PairingEngine$onPairingDelete$1
            r2.<init>(r1, r0)
        L_0x001c:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x0046
            if (r4 != r5) goto L_0x003e
            java.lang.Object r3 = r2.L$2
            com.reown.android.internal.common.model.IrnParams r3 = (com.reown.android.internal.common.model.IrnParams) r3
            java.lang.Object r4 = r2.L$1
            com.reown.android.pairing.model.PairingParams$DeleteParams r4 = (com.reown.android.pairing.model.PairingParams.DeleteParams) r4
            java.lang.Object r2 = r2.L$0
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x003a }
            goto L_0x00fc
        L_0x003a:
            r0 = move-exception
            r4 = r3
            goto L_0x010b
        L_0x003e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r0)
            com.reown.android.internal.common.model.IrnParams r4 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r7 = com.reown.android.internal.common.model.Tags.PAIRING_DELETE_RESPONSE
            com.reown.foundation.common.model.Ttl r8 = new com.reown.foundation.common.model.Ttl
            long r9 = com.reown.android.internal.utils.Time.getDayInSeconds()
            r8.<init>(r9)
            long r9 = r18.getId()
            java.lang.Long r9 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r9)
            r15 = 248(0xf8, float:3.48E-43)
            r16 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r6 = r4
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            com.reown.foundation.common.model.Topic r0 = r18.getTopic()     // Catch:{ Exception -> 0x0106 }
            java.lang.String r0 = r0.getValue()     // Catch:{ Exception -> 0x0106 }
            boolean r0 = r1.isPairingValid(r0)     // Catch:{ Exception -> 0x0106 }
            if (r0 != 0) goto L_0x00a7
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r2 = r1.jsonRpcInteractor     // Catch:{ Exception -> 0x00a2 }
            com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic r0 = new com.reown.android.internal.common.exception.Uncategorized$NoMatchingTopic     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r3 = "Pairing"
            com.reown.foundation.common.model.Topic r5 = r18.getTopic()     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r5 = r5.getValue()     // Catch:{ Exception -> 0x00a2 }
            r0.<init>(r3, r5)     // Catch:{ Exception -> 0x00a2 }
            r10 = 120(0x78, float:1.68E-43)
            r11 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r3 = r18
            r12 = r4
            r4 = r0
            r5 = r12
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x009c }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x009c }
            return r0
        L_0x009c:
            r0 = move-exception
            r2 = r18
        L_0x009f:
            r4 = r12
            goto L_0x010b
        L_0x00a2:
            r0 = move-exception
            r12 = r4
            r2 = r18
            goto L_0x010b
        L_0x00a7:
            r12 = r4
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r0 = r1.crypto     // Catch:{ Exception -> 0x0102 }
            com.reown.foundation.common.model.Topic r4 = r18.getTopic()     // Catch:{ Exception -> 0x0102 }
            java.lang.String r4 = r4.getValue()     // Catch:{ Exception -> 0x0102 }
            r0.removeKeys(r4)     // Catch:{ Exception -> 0x0102 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r1.jsonRpcInteractor     // Catch:{ Exception -> 0x0102 }
            com.reown.foundation.common.model.Topic r7 = r18.getTopic()     // Catch:{ Exception -> 0x0102 }
            r10 = 6
            r11 = 0
            r8 = 0
            r9 = 0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.unsubscribe$default(r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x0102 }
            com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface r0 = r1.pairingRepository     // Catch:{ Exception -> 0x0102 }
            com.reown.foundation.common.model.Topic r4 = r18.getTopic()     // Catch:{ Exception -> 0x0102 }
            r0.deletePairing(r4)     // Catch:{ Exception -> 0x0102 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r0 = r1.metadataRepository     // Catch:{ Exception -> 0x0102 }
            com.reown.foundation.common.model.Topic r4 = r18.getTopic()     // Catch:{ Exception -> 0x0102 }
            r0.deleteMetaData(r4)     // Catch:{ Exception -> 0x0102 }
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.pairing.engine.model.EngineDO> r0 = r1._engineEvent     // Catch:{ Exception -> 0x0102 }
            com.reown.android.pairing.engine.model.EngineDO$PairingDelete r4 = new com.reown.android.pairing.engine.model.EngineDO$PairingDelete     // Catch:{ Exception -> 0x0102 }
            com.reown.foundation.common.model.Topic r6 = r18.getTopic()     // Catch:{ Exception -> 0x0102 }
            java.lang.String r6 = r6.getValue()     // Catch:{ Exception -> 0x0102 }
            java.lang.String r7 = r19.getMessage()     // Catch:{ Exception -> 0x0102 }
            r4.<init>(r6, r7)     // Catch:{ Exception -> 0x0102 }
            r6 = r18
            r2.L$0 = r6     // Catch:{ Exception -> 0x00ff }
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ Exception -> 0x00ff }
            r2.L$1 = r7     // Catch:{ Exception -> 0x00ff }
            r2.L$2 = r12     // Catch:{ Exception -> 0x00ff }
            r2.label = r5     // Catch:{ Exception -> 0x00ff }
            java.lang.Object r0 = r0.emit(r4, r2)     // Catch:{ Exception -> 0x00ff }
            if (r0 != r3) goto L_0x00fc
            return r3
        L_0x00fc:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00ff:
            r0 = move-exception
        L_0x0100:
            r2 = r6
            goto L_0x009f
        L_0x0102:
            r0 = move-exception
            r6 = r18
            goto L_0x0100
        L_0x0106:
            r0 = move-exception
            r6 = r18
            r12 = r4
            r2 = r6
        L_0x010b:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r1 = r1.jsonRpcInteractor
            com.reown.android.internal.common.exception.Uncategorized$GenericError r3 = new com.reown.android.internal.common.exception.Uncategorized$GenericError
            java.lang.String r0 = r0.getMessage()
            java.lang.String r5 = "Cannot delete pairing: "
            java.lang.String r0 = androidx.browser.trusted.c.a(r5, r0)
            r3.<init>(r0)
            r9 = 120(0x78, float:1.68E-43)
            r10 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.pairing.engine.domain.PairingEngine.onPairingDelete(com.reown.android.internal.common.model.WCRequest, com.reown.android.pairing.model.PairingParams$DeleteParams, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    @Deprecated(message = "Ping method has been deprecated. It will be removed soon.")
    public final void onPing(WCRequest wCRequest) {
        RelayJsonRpcInteractorInterface.respondWithSuccess$default(this.jsonRpcInteractor, wCRequest, new IrnParams(Tags.PAIRING_PING, new Ttl(Time.getThirtySeconds()), Long.valueOf(wCRequest.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null), (EnvelopeType) null, (Participants) null, 12, (Object) null);
    }

    @Deprecated(message = "Ping method has been deprecated. It will be removed soon.")
    private final void onPingSuccess(PairingRpc.PairingPing pairingPing, Function1<? super String, Unit> function1, String str, Function1<? super Throwable, Unit> function12) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$onPingSuccess$1(function12, this, pairingPing, function1, str, (Continuation<? super PairingEngine$onPingSuccess$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$13(List list, Function0 function0, PairingEngine pairingEngine, Topic topic, Topic topic2) {
        Intrinsics.checkNotNullParameter(topic2, "it");
        list.add(Trace.Pairing.SUBSCRIBE_PAIRING_TOPIC_SUCCESS);
        Logger logger2 = pairingEngine.logger;
        logger2.log("Subscribe pairing topic success: " + topic);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$15(Function1 function1, PairingEngine pairingEngine, List list, Topic topic, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$11$1(pairingEngine, list, topic, (Continuation<? super PairingEngine$pair$11$1>) null), 3, (Object) null);
        Logger logger2 = pairingEngine.logger;
        logger2.error("Subscribe pairing topic error: " + topic + ", error: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    private final void pairingsExpiryWatcher() {
        FlowKt.launchIn(FlowKt.onEach(repeatableFlow(30000), new PairingEngine$pairingsExpiryWatcher$1(this, (Continuation<? super PairingEngine$pairingsExpiryWatcher$1>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public static final Unit ping$lambda$20(PairingEngine pairingEngine, PairingRpc.PairingPing pairingPing, Function1 function1, String str, Function1 function12) {
        pairingEngine.onPingSuccess(pairingPing, function1, str, function12);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit ping$lambda$21(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    private final Flow<Unit> repeatableFlow(long j2) {
        return FlowKt.flow(new PairingEngine$repeatableFlow$1(j2, (Continuation<? super PairingEngine$repeatableFlow$1>) null));
    }

    private final void resubscribeToPairingTopics() {
        FlowKt.launchIn(FlowKt.onEach(this.jsonRpcInteractor.getOnResubscribe(), new PairingEngine$resubscribeToPairingTopics$1(this, (Continuation<? super PairingEngine$resubscribeToPairingTopics$1>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052 A[Catch:{ Exception -> 0x0029 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076 A[Catch:{ Exception -> 0x0029 }, LOOP:1: B:26:0x0070->B:28:0x0076, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendBatchSubscribeForPairings(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$1 r0 = (com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$1 r0 = new com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$1
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ Exception -> 0x0029 }
            goto L_0x0041
        L_0x0029:
            r7 = move-exception
            goto L_0x0097
        L_0x002b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r7)
            com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface r7 = r6.pairingRepository     // Catch:{ Exception -> 0x0029 }
            r0.label = r3     // Catch:{ Exception -> 0x0029 }
            java.lang.Object r7 = r7.getListOfPairings(r0)     // Catch:{ Exception -> 0x0029 }
            if (r7 != r1) goto L_0x0041
            return r1
        L_0x0041:
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ Exception -> 0x0029 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x0029 }
            r0.<init>()     // Catch:{ Exception -> 0x0029 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x0029 }
        L_0x004c:
            boolean r1 = r7.hasNext()     // Catch:{ Exception -> 0x0029 }
            if (r1 == 0) goto L_0x0063
            java.lang.Object r1 = r7.next()     // Catch:{ Exception -> 0x0029 }
            r2 = r1
            com.reown.android.internal.common.model.Pairing r2 = (com.reown.android.internal.common.model.Pairing) r2     // Catch:{ Exception -> 0x0029 }
            boolean r2 = r6.isNotExpired(r2)     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x004c
            r0.add(r1)     // Catch:{ Exception -> 0x0029 }
            goto L_0x004c
        L_0x0063:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0029 }
            int r7 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, 10)     // Catch:{ Exception -> 0x0029 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0029 }
            java.util.Iterator r7 = r0.iterator()     // Catch:{ Exception -> 0x0029 }
        L_0x0070:
            boolean r0 = r7.hasNext()     // Catch:{ Exception -> 0x0029 }
            if (r0 == 0) goto L_0x0088
            java.lang.Object r0 = r7.next()     // Catch:{ Exception -> 0x0029 }
            com.reown.android.internal.common.model.Pairing r0 = (com.reown.android.internal.common.model.Pairing) r0     // Catch:{ Exception -> 0x0029 }
            com.reown.foundation.common.model.Topic r0 = r0.getTopic()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r0 = r0.getValue()     // Catch:{ Exception -> 0x0029 }
            r1.add(r0)     // Catch:{ Exception -> 0x0029 }
            goto L_0x0070
        L_0x0088:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r0 = r6.jsonRpcInteractor     // Catch:{ Exception -> 0x0029 }
            com.reown.android.pairing.engine.domain.a r3 = new com.reown.android.pairing.engine.domain.a     // Catch:{ Exception -> 0x0029 }
            r7 = 0
            r3.<init>(r6, r7)     // Catch:{ Exception -> 0x0029 }
            r5 = 0
            r2 = 0
            r4 = 2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.batchSubscribe$default(r0, r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0029 }
            goto L_0x00a7
        L_0x0097:
            kotlinx.coroutines.CoroutineScope r0 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()
            com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$3 r3 = new com.reown.android.pairing.engine.domain.PairingEngine$sendBatchSubscribeForPairings$3
            r1 = 0
            r3.<init>(r6, r7, r1)
            r2 = 0
            r4 = 3
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
        L_0x00a7:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.pairing.engine.domain.PairingEngine.sendBatchSubscribeForPairings(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Unit sendBatchSubscribeForPairings$lambda$26(PairingEngine pairingEngine, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$sendBatchSubscribeForPairings$2$1(pairingEngine, th, (Continuation<? super PairingEngine$sendBatchSubscribeForPairings$2$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    private final void sendEvents() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$sendEvents$1(this, (Continuation<? super PairingEngine$sendEvents$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit setRequestReceived$lambda$23(PairingEngine pairingEngine, Pairing pairing) {
        Intrinsics.checkNotNullParameter(pairing, "pairing");
        pairingEngine.pairingRepository.setRequestReceived(pairing.getTopic());
        return Unit.INSTANCE;
    }

    @Nullable
    public final Core.Model.Pairing create(@NotNull Function1<? super Throwable, Unit> function1, @Nullable String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(function1, "onFailure");
        Topic generateTopic = generateTopic();
        Core.Model.Pairing pairing = null;
        Topic topic = generateTopic;
        Pairing pairing2 = new Pairing(topic, new RelayProtocolOptions((String) null, (String) null, 3, (DefaultConstructorMarker) null), this.crypto.m8723generateAndStoreSymmetricKeyp84wnz8(generateTopic), new Expiry(Expiration.getPairingExpiry()), str, (DefaultConstructorMarker) null);
        try {
            Result.Companion companion = Result.Companion;
            this.logger.log("Creating Pairing");
            this.pairingRepository.insertPairing(pairing2);
            this.metadataRepository.upsertPeerMetadata(pairing2.getTopic(), this.selfMetaData, AppMetaDataType.SELF);
            obj = Result.m8979constructorimpl(PairingMapperKt.toCore(pairing2));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r9 = Result.m8982exceptionOrNullimpl(obj);
        if (r9 != null) {
            try {
                this.crypto.removeKeys(generateTopic.getValue());
                this.pairingRepository.deletePairing(generateTopic);
                this.metadataRepository.deleteMetaData(generateTopic);
                RelayJsonRpcInteractorInterface.unsubscribe$default(this.jsonRpcInteractor, generateTopic, (Function0) null, (Function1) null, 6, (Object) null);
                Logger logger2 = this.logger;
                logger2.error("Pairing - subscribed failure on pairing topic: " + generateTopic + ", error: " + r9);
                function1.invoke(r9);
            } catch (Exception e3) {
                Logger logger3 = this.logger;
                logger3.error("Pairing - subscribed failure on pairing topic: " + generateTopic + ", error: " + e3);
                function1.invoke(e3);
            }
        }
        if (!Result.m8985isFailureimpl(obj)) {
            pairing = obj;
        }
        return pairing;
    }

    public final void deleteAndUnsubscribePairing(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        RelayJsonRpcInteractorInterface.unsubscribe$default(this.jsonRpcInteractor, new Topic(str), (Function0) null, (Function1) null, 6, (Object) null);
        this.pairingRepository.deletePairing(new Topic(str));
    }

    @Deprecated(message = "Disconnect method has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
    public final void disconnect(@NotNull String str, @NotNull Function1<? super Throwable, Unit> function1) {
        String str2 = str;
        Function1<? super Throwable, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        if (!isPairingValid(str)) {
            function12.invoke(new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", str2)));
            return;
        }
        PairingRpc.PairingDelete pairingDelete = new PairingRpc.PairingDelete(0, (String) null, (String) null, new PairingParams.DeleteParams(PlaybackException.ERROR_CODE_DRM_UNSPECIFIED, MessagesKt.DISCONNECT_MESSAGE), 7, (DefaultConstructorMarker) null);
        IrnParams irnParams = new IrnParams(Tags.PAIRING_DELETE, new Ttl(Time.getDayInSeconds()), Long.valueOf(pairingDelete.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
        this.logger.log("Sending Pairing disconnect");
        RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(this.jsonRpcInteractor, new Topic(str2), irnParams, pairingDelete, (EnvelopeType) null, (Participants) null, new e(this, str2, 5), new C0235a(this, function12, 10), 24, (Object) null);
    }

    @NotNull
    public final SharedFlow<Unit> getCheckVerifyKeyFlow() {
        return this.checkVerifyKeyFlow;
    }

    @NotNull
    public final SharedFlow<EngineDO> getEngineEvent() {
        return this.engineEvent;
    }

    @NotNull
    public final MutableSharedFlow<SDKError> getInternalErrorFlow() {
        return this.internalErrorFlow;
    }

    @NotNull
    public final Flow<SDKError> getJsonRpcErrorFlow() {
        return (Flow) this.jsonRpcErrorFlow$delegate.getValue();
    }

    @Nullable
    public final Pairing getPairingByTopic(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Pairing pairingOrNullByTopic = this.pairingRepository.getPairingOrNullByTopic(topic);
        if (pairingOrNullByTopic == null || !isNotExpired(pairingOrNullByTopic)) {
            return null;
        }
        return pairingOrNullByTopic;
    }

    @NotNull
    public final List<Pairing> getPairings() {
        return (List) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new PairingEngine$getPairings$1(this, (Continuation<? super PairingEngine$getPairings$1>) null), 1, (Object) null);
    }

    @NotNull
    public final SharedFlow<Pair<Topic, List<String>>> getStoredPairingTopicFlow() {
        return this.storedPairingTopicFlow;
    }

    public final void pair(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Topic topic;
        Exception exc;
        Object obj;
        String str2 = str;
        Function0<Unit> function02 = function0;
        Function1<? super Throwable, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(str2, "uri");
        Intrinsics.checkNotNullParameter(function02, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$1(this, (Continuation<? super PairingEngine$pair$1>) null), 3, (Object) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Trace.Pairing.PAIRING_STARTED);
        this.logger.log("Pairing started");
        WalletConnectUri validateWCUri$android_release = Validator.INSTANCE.validateWCUri$android_release(str2);
        if (validateWCUri$android_release == null) {
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$walletConnectUri$1$1(this, arrayList, (Continuation<? super PairingEngine$pair$walletConnectUri$1$1>) null), 3, (Object) null);
            function12.invoke(new MalformedWalletConnectUri(com.reown.android.internal.MessagesKt.MALFORMED_PAIRING_URI_MESSAGE));
            return;
        }
        arrayList.add(Trace.Pairing.PAIRING_URI_VALIDATION_SUCCESS);
        Pairing pairing = new Pairing(validateWCUri$android_release);
        topic = pairing.getTopic();
        String r11 = validateWCUri$android_release.m8795getSymKeyC2wS6ak();
        try {
            Expiry expiry = validateWCUri$android_release.getExpiry();
            if (expiry == null || !CoreValidator.INSTANCE.isExpired(expiry)) {
                arrayList.add(Trace.Pairing.PAIRING_URI_NOT_EXPIRED);
                if (this.pairingRepository.getPairingOrNullByTopic(topic) != null) {
                    Pairing pairingOrNullByTopic = this.pairingRepository.getPairingOrNullByTopic(topic);
                    arrayList.add(Trace.Pairing.EXISTING_PAIRING);
                    Intrinsics.checkNotNull(pairingOrNullByTopic);
                    if (!isNotExpired(pairingOrNullByTopic)) {
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$5(this, arrayList, topic, (Continuation<? super PairingEngine$pair$5>) null), 3, (Object) null);
                        Logger logger2 = this.logger;
                        logger2.error("Pairing expired: " + topic);
                        String value = topic.getValue();
                        function12.invoke(new ExpiredPairingException("Pairing expired: " + value));
                        return;
                    }
                    arrayList.add(Trace.Pairing.PAIRING_NOT_EXPIRED);
                    Job unused4 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$7(arrayList, this, topic, (Continuation<? super PairingEngine$pair$7>) null), 3, (Object) null);
                } else {
                    this.crypto.setKey(SymmetricKey.m8777boximpl(r11), topic.getValue());
                    this.pairingRepository.insertPairing(pairing);
                    arrayList.add(Trace.Pairing.STORE_NEW_PAIRING);
                    Logger logger3 = this.logger;
                    logger3.log("Storing a new pairing: " + topic);
                }
                arrayList.add(Trace.Pairing.SUBSCRIBING_PAIRING_TOPIC);
                Logger logger4 = this.logger;
                logger4.log("Subscribing pairing topic: " + topic);
                this.jsonRpcInteractor.subscribe(topic, new C0140g(arrayList, (Function0) function02, this, topic), new C0140g(7, (Object) function1, (Object) this, (Object) arrayList, (Object) topic));
                return;
            }
            Job unused5 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$3(this, arrayList, topic, (Continuation<? super PairingEngine$pair$3>) null), 3, (Object) null);
            Logger logger5 = this.logger;
            logger5.error("Pairing URI expired: " + topic);
            function12.invoke(new ExpiredPairingURIException("Pairing URI expired: " + topic));
            return;
        } catch (Exception e3) {
            exc = e3;
            Logger logger6 = this.logger;
            logger6.error("Subscribe pairing topic error: " + topic + ", error: " + exc);
            if (exc instanceof NoRelayConnectionException) {
                Job unused6 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$12(this, arrayList, topic, (Continuation<? super PairingEngine$pair$12>) null), 3, (Object) null);
            }
            if (exc instanceof NoInternetConnectionException) {
                Job unused7 = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PairingEngine$pair$13(this, arrayList, topic, (Continuation<? super PairingEngine$pair$13>) null), 3, (Object) null);
            }
            Result.Companion companion = Result.Companion;
            this.crypto.removeKeys(topic.getValue());
            obj = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r02 = Result.m8982exceptionOrNullimpl(obj);
        if (r02 != null) {
            Logger logger7 = this.logger;
            logger7.error("Remove keys error: " + topic + ", error: " + r02);
        }
        RelayJsonRpcInteractorInterface.unsubscribe$default(this.jsonRpcInteractor, topic, (Function0) null, (Function1) null, 6, (Object) null);
        function12.invoke(exc);
    }

    @Deprecated(message = "Ping method has been deprecated. It will be removed soon.")
    public final void ping(@NotNull String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        String str2 = str;
        Function1<? super Throwable, Unit> function13 = function12;
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function13, "onFailure");
        if (isPairingValid(str)) {
            PairingRpc.PairingPing pairingPing = new PairingRpc.PairingPing(0, (String) null, (String) null, new PairingParams.PingParams(), 7, (DefaultConstructorMarker) null);
            IrnParams irnParams = new IrnParams(Tags.PAIRING_PING, new Ttl(Time.getThirtySeconds()), Long.valueOf(pairingPing.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(this.jsonRpcInteractor, new Topic(str2), irnParams, pairingPing, (EnvelopeType) null, (Participants) null, new C0179t(this, pairingPing, (Function1) function1, str, (Function1) function12), new h(function13, 11), 24, (Object) null);
            return;
        }
        function13.invoke(new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", str2)));
    }

    public final void register(@NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, FirebaseAnalytics.Param.METHOD);
        CollectionsKt__MutableCollectionsKt.addAll(this.setOfRegisteredMethods, (T[]) strArr);
    }

    public final void setRequestReceived(@NotNull String str, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function1, "onFailure");
        getPairing(str, function1, new a(this, 1));
    }

    public final void updateMetadata(@NotNull String str, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullParameter(appMetaDataType, "metaDataType");
        this.metadataRepository.upsertPeerMetadata(new Topic(str), appMetaData, appMetaDataType);
    }
}
