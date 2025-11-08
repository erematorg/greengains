package com.reown.foundation.network;

import G1.C0235a;
import S0.h;
import androidx.compose.material.C0179t;
import androidx.compose.material.T;
import androidx.compose.runtime.e;
import com.google.firebase.messaging.Constants;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.k;
import com.reown.foundation.common.model.SubscriptionId;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.di.FoundationCommonModuleKt;
import com.reown.foundation.network.ConnectionState;
import com.reown.foundation.network.data.service.RelayService;
import com.reown.foundation.network.model.Relay;
import com.reown.foundation.network.model.RelayDTO;
import com.reown.foundation.util.Logger;
import com.reown.foundation.util.ScopeKt;
import com.reown.util.UtilFunctionsKt;
import com.tinder.scarlet.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
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
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 }2\u00020\u0001:\u0001}B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u00101\u001a\u000202JI\u0010@\u001a\u0002022\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020$2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0I\u0012\u0004\u0012\u0002020HH\u0016¢\u0006\u0002\u0010KJ*\u0010L\u001a\u0002022\u0006\u0010F\u001a\u00020E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0I\u0012\u0004\u0012\u0002020HH\u0002JY\u0010M\u001a\u0002022\u0006\u0010A\u001a\u00020B2\u0006\u0010N\u001a\u00020B2\u0006\u0010O\u001a\u00020$2\u0006\u0010P\u001a\u00020$2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0I\u0012\u0004\u0012\u0002020HH\u0016¢\u0006\u0002\u0010RJ*\u0010S\u001a\u0002022\u0006\u0010F\u001a\u00020E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0I\u0012\u0004\u0012\u0002020HH\u0002JI\u0010T\u001a\u0002022\u0006\u0010U\u001a\u00020$2\u0006\u0010V\u001a\u00020$2\u0006\u0010W\u001a\u00020X2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0I\u0012\u0004\u0012\u0002020HH\u0017¢\u0006\u0002\u0010ZJ*\u0010[\u001a\u0002022\u0006\u0010F\u001a\u00020E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0I\u0012\u0004\u0012\u0002020HH\u0002J9\u0010\\\u001a\u0002022\u0006\u0010U\u001a\u00020$2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0I\u0012\u0004\u0012\u0002020HH\u0017¢\u0006\u0002\u0010^J*\u0010_\u001a\u0002022\u0006\u0010F\u001a\u00020E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0I\u0012\u0004\u0012\u0002020HH\u0002J?\u0010`\u001a\u0002022\f\u0010a\u001a\b\u0012\u0004\u0012\u00020$0b2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0I\u0012\u0004\u0012\u0002020HH\u0017¢\u0006\u0002\u0010dJ8\u0010e\u001a\u0002022\u0006\u0010F\u001a\u00020E2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020$0b2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0I\u0012\u0004\u0012\u0002020HH\u0002JA\u0010f\u001a\u0002022\u0006\u0010U\u001a\u00020$2\u0006\u0010g\u001a\u00020$2\b\u0010F\u001a\u0004\u0018\u00010E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020h0I\u0012\u0004\u0012\u0002020HH\u0017¢\u0006\u0002\u0010iJ*\u0010j\u001a\u0002022\u0006\u0010F\u001a\u00020E2\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020h0I\u0012\u0004\u0012\u0002020HH\u0002J*\u0010k\u001a\u0002022\f\u0010l\u001a\b\u0012\u0004\u0012\u0002020m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u0002020HH\u0002J*\u0010p\u001a\u0002022\f\u0010l\u001a\b\u0012\u0004\u0012\u0002020m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u0002020HH\u0002J\b\u0010q\u001a\u00020*H\u0002J*\u0010r\u001a\u0002022\f\u0010l\u001a\b\u0012\u0004\u0012\u0002020m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u0002020HH\u0002J,\u0010s\u001a\u0002022\f\u0010l\u001a\b\u0012\u0004\u0012\u0002020m2\u0014\b\u0002\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u0002020HH\u0002J(\u0010t\u001a\u000202*\u00020u2\u0006\u0010v\u001a\u00020\u001d2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u0002020HH\u0002J\u0010\u0010w\u001a\u00020o2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010z\u001a\u0002022\u0006\u0010F\u001a\u00020EH\u0002J\f\u0010{\u001a\u000202*\u00020uH\u0002J\b\u0010|\u001a\u000202H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0004¢\u0006\u0002\n\u0000R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R!\u00103\u001a\b\u0012\u0004\u0012\u000205048VX\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b6\u00107R!\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;8VX\u0002¢\u0006\f\n\u0004\b?\u00109\u001a\u0004\b=\u0010>¨\u0006~"}, d2 = {"Lcom/reown/foundation/network/BaseRelayClient;", "Lcom/reown/foundation/network/RelayInterface;", "<init>", "()V", "foundationKoinApp", "Lorg/koin/core/KoinApplication;", "relayService", "Lcom/reown/foundation/network/data/service/RelayService;", "getRelayService", "()Lcom/reown/foundation/network/data/service/RelayService;", "setRelayService", "(Lcom/reown/foundation/network/data/service/RelayService;)V", "connectionLifecycle", "Lcom/reown/foundation/network/ConnectionLifecycle;", "getConnectionLifecycle", "()Lcom/reown/foundation/network/ConnectionLifecycle;", "setConnectionLifecycle", "(Lcom/reown/foundation/network/ConnectionLifecycle;)V", "logger", "Lcom/reown/foundation/util/Logger;", "getLogger", "()Lcom/reown/foundation/util/Logger;", "setLogger", "(Lcom/reown/foundation/util/Logger;)V", "resultState", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/foundation/network/model/RelayDTO;", "connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/reown/foundation/network/ConnectionState;", "getConnectionState$foundation", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "setConnectionState$foundation", "(Lkotlinx/coroutines/flow/MutableStateFlow;)V", "ackedTopics", "", "", "getAckedTopics$foundation", "()Ljava/util/List;", "setAckedTopics$foundation", "(Ljava/util/List;)V", "isConnecting", "", "retryCount", "", "isLoggingEnabled", "()Z", "setLoggingEnabled", "(Z)V", "observeResults", "", "eventsFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "getEventsFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "eventsFlow$delegate", "Lkotlin/Lazy;", "subscriptionRequest", "Lkotlinx/coroutines/flow/Flow;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "getSubscriptionRequest", "()Lkotlinx/coroutines/flow/Flow;", "subscriptionRequest$delegate", "proposeSession", "pairingTopic", "Lcom/reown/foundation/common/model/Topic;", "sessionProposal", "correlationId", "", "id", "onResult", "Lkotlin/Function1;", "Lkotlin/Result;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Acknowledgement;", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;JLjava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observeProposeSessionResult", "approveSession", "sessionTopic", "sessionProposalResponse", "sessionSettlementRequest", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Acknowledgement;", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observeApproveSessionResult", "publish", "topic", "message", "params", "Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$IrnParams;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observePublishResult", "subscribe", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observeSubscribeResult", "batchSubscribe", "topics", "", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Acknowledgement;", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observeBatchSubscribeResult", "unsubscribe", "subscriptionId", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "observeUnsubscribeResult", "connectAndCallRelay", "onConnected", "Lkotlin/Function0;", "onFailure", "", "awaitConnection", "shouldConnect", "connect", "connectWithRetry", "handleTries", "Lkotlinx/coroutines/CoroutineScope;", "state", "getError", "event", "Lcom/tinder/scarlet/WebSocket$Event;", "publishSubscriptionAcknowledgement", "cancelJobIfActive", "reset", "Companion", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseRelayClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Koin.kt\norg/koin/core/Koin\n+ 4 Scope.kt\norg/koin/core/scope/Scope\n+ 5 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 6 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 7 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,533:1\n1#2:534\n124#3,4:535\n142#4:539\n47#5:540\n49#5:544\n47#5:545\n49#5:549\n50#6:541\n55#6:543\n50#6:546\n55#6:548\n106#7:542\n106#7:547\n*S KotlinDebug\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient\n*L\n65#1:535,4\n65#1:539\n106#1:540\n106#1:544\n115#1:545\n115#1:549\n106#1:541\n106#1:543\n115#1:546\n115#1:548\n106#1:542\n115#1:547\n*E\n"})
public abstract class BaseRelayClient implements RelayInterface {
    @Deprecated
    public static final long CONNECTION_TIMEOUT = 15000;
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final int MAX_RETRIES = 3;
    @Deprecated
    public static final int REPLAY = 1;
    @Deprecated
    public static final long RESULT_TIMEOUT = 60000;
    @NotNull
    private List<String> ackedTopics = new ArrayList();
    public ConnectionLifecycle connectionLifecycle;
    @NotNull
    private MutableStateFlow<ConnectionState> connectionState = StateFlowKt.MutableStateFlow(ConnectionState.Idle.INSTANCE);
    @NotNull
    private final Lazy eventsFlow$delegate;
    @NotNull
    private KoinApplication foundationKoinApp = KoinApplication.Companion.init();
    private boolean isConnecting;
    private boolean isLoggingEnabled;
    @NotNull
    private Logger logger;
    public RelayService relayService;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<RelayDTO> resultState = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
    private int retryCount;
    @NotNull
    private final Lazy subscriptionRequest$delegate;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/BaseRelayClient$Companion;", "", "<init>", "()V", "REPLAY", "", "RESULT_TIMEOUT", "", "CONNECTION_TIMEOUT", "MAX_RETRIES", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public BaseRelayClient() {
        this.foundationKoinApp.modules(FoundationCommonModuleKt.foundationCommonModule());
        this.logger = (Logger) this.foundationKoinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Logger.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        this.eventsFlow$delegate = LazyKt.lazy(new a(this, 1));
        this.subscriptionRequest$delegate = LazyKt.lazy(new a(this, 0));
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$7(Topic topic, Topic topic2, String str, String str2, long j2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        RelayDTO.ApproveSession.Request request = new RelayDTO.ApproveSession.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.ApproveSession.Request.Params(topic, topic2, str, str2, j2), 6, (DefaultConstructorMarker) null);
        baseRelayClient.observeApproveSessionResult(request.getId(), function1);
        baseRelayClient.getRelayService().approveSessionRequest(request);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$8(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    private final void awaitConnection(Function0<Unit> function0, Function1<? super Throwable, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$awaitConnection$1(function1, this, function0, (Continuation<? super BaseRelayClient$awaitConnection$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit batchSubscribe$lambda$14(BaseRelayClient baseRelayClient, List list, Long l2, Function1 function1) {
        if (!baseRelayClient.ackedTopics.containsAll(list)) {
            RelayDTO.BatchSubscribe.Request request = new RelayDTO.BatchSubscribe.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.BatchSubscribe.Request.Params(list), 6, (DefaultConstructorMarker) null);
            baseRelayClient.observeBatchSubscribeResult(request.getId(), list, function1);
            baseRelayClient.getRelayService().batchSubscribeRequest(request);
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit batchSubscribe$lambda$15(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void cancelJobIfActive(CoroutineScope coroutineScope) {
        if (JobKt.getJob(coroutineScope.getCoroutineContext()).isActive()) {
            Job.DefaultImpls.cancel$default(JobKt.getJob(coroutineScope.getCoroutineContext()), (CancellationException) null, 1, (Object) null);
        }
    }

    private final void connect(Function0<Unit> function0, Function1<? super Throwable, Unit> function1) {
        this.isConnecting = true;
        connectWithRetry(new e(this, function0, 6), new C0235a(this, function1, 11));
    }

    /* access modifiers changed from: private */
    public static final Unit connect$lambda$18(BaseRelayClient baseRelayClient, Function0 function0) {
        baseRelayClient.reset();
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit connect$lambda$19(BaseRelayClient baseRelayClient, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        baseRelayClient.reset();
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    private final void connectAndCallRelay(Function0<Unit> function0, Function1<? super Throwable, Unit> function1) {
        if (shouldConnect()) {
            connect(function0, function1);
        } else if (Intrinsics.areEqual((Object) this.connectionState.getValue(), (Object) ConnectionState.Open.INSTANCE)) {
            function0.invoke();
        } else if (this.isConnecting) {
            awaitConnection(function0, function1);
        }
    }

    private final void connectWithRetry(Function0<Unit> function0, Function1<? super Throwable, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$connectWithRetry$2(function1, this, function0, (Continuation<? super BaseRelayClient$connectWithRetry$2>) null), 3, (Object) null);
    }

    public static /* synthetic */ void connectWithRetry$default(BaseRelayClient baseRelayClient, Function0 function0, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new k(15);
            }
            baseRelayClient.connectWithRetry(function0, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: connectWithRetry");
    }

    /* access modifiers changed from: private */
    public static final Unit connectWithRetry$lambda$20(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final SharedFlow eventsFlow_delegate$lambda$2(BaseRelayClient baseRelayClient) {
        return FlowKt.shareIn(new BaseRelayClient$eventsFlow_delegate$lambda$2$$inlined$map$1(FlowKt.onEach(baseRelayClient.getRelayService().observeWebSocketEvent(), new BaseRelayClient$eventsFlow$2$1(baseRelayClient, (Continuation<? super BaseRelayClient$eventsFlow$2$1>) null)), baseRelayClient), ScopeKt.getScope(), SharingStarted.Companion.getLazily(), 1);
    }

    /* access modifiers changed from: private */
    public final Throwable getError(WebSocket.Event event) {
        return event instanceof WebSocket.Event.OnConnectionClosed ? new Throwable(((WebSocket.Event.OnConnectionClosed) event).getShutdownReason().getReason()) : event instanceof WebSocket.Event.OnConnectionFailed ? ((WebSocket.Event.OnConnectionFailed) event).getThrowable() : new Throwable("Unknown");
    }

    /* access modifiers changed from: private */
    public final void handleTries(CoroutineScope coroutineScope, ConnectionState connectionState2, Function1<? super Throwable, Unit> function1) {
        if (!(connectionState2 instanceof ConnectionState.Closed) && !(connectionState2 instanceof ConnectionState.Idle)) {
            return;
        }
        if (this.retryCount == 3) {
            function1.invoke(new Throwable("Connectivity error, please check your Internet connection and try again"));
            cancelJobIfActive(coroutineScope);
            return;
        }
        getConnectionLifecycle().reconnect();
        this.retryCount++;
    }

    private final void observeApproveSessionResult(long j2, Function1<? super Result<Relay.Model.Call.ApproveSession.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeApproveSessionResult$1(function1, this, j2, (Continuation<? super BaseRelayClient$observeApproveSessionResult$1>) null), 3, (Object) null);
    }

    private final void observeBatchSubscribeResult(long j2, List<String> list, Function1<? super Result<Relay.Model.Call.BatchSubscribe.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeBatchSubscribeResult$1(function1, this, j2, list, (Continuation<? super BaseRelayClient$observeBatchSubscribeResult$1>) null), 3, (Object) null);
    }

    private final void observeProposeSessionResult(long j2, Function1<? super Result<Relay.Model.Call.ProposeSession.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeProposeSessionResult$1(function1, this, j2, (Continuation<? super BaseRelayClient$observeProposeSessionResult$1>) null), 3, (Object) null);
    }

    private final void observePublishResult(long j2, Function1<? super Result<Relay.Model.Call.Publish.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observePublishResult$1(function1, this, j2, (Continuation<? super BaseRelayClient$observePublishResult$1>) null), 3, (Object) null);
    }

    private final void observeSubscribeResult(long j2, Function1<? super Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeSubscribeResult$1(function1, this, j2, (Continuation<? super BaseRelayClient$observeSubscribeResult$1>) null), 3, (Object) null);
    }

    private final void observeUnsubscribeResult(long j2, Function1<? super Result<Relay.Model.Call.Unsubscribe.Acknowledgement>, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeUnsubscribeResult$1(function1, this, j2, (Continuation<? super BaseRelayClient$observeUnsubscribeResult$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit proposeSession$lambda$5(Topic topic, String str, long j2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        RelayDTO.ProposeSession.Request request = new RelayDTO.ProposeSession.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.ProposeSession.Request.Params(topic, str, (String) null, j2), 6, (DefaultConstructorMarker) null);
        baseRelayClient.observeProposeSessionResult(request.getId(), function1);
        baseRelayClient.getRelayService().proposeSessionRequest(request);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit proposeSession$lambda$6(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit publish$lambda$10(Relay.Model.IrnParams irnParams, String str, String str2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        String str3 = str;
        RelayDTO.Publish.Request request = new RelayDTO.Publish.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.Publish.Request.Params(new Topic(str), str2, new Ttl(irnParams.getTtl()), irnParams.getTag(), Boolean.valueOf(irnParams.getPrompt()), irnParams.getCorrelationId(), irnParams.getRpcMethods(), irnParams.getChainId(), irnParams.getTxHashes(), irnParams.getContractAddresses()), 6, (DefaultConstructorMarker) null);
        baseRelayClient.observePublishResult(request.getId(), function1);
        baseRelayClient.getRelayService().publishRequest(request);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit publish$lambda$11(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void publishSubscriptionAcknowledgement(long j2) {
        getRelayService().publishSubscriptionAcknowledgement(new RelayDTO.Subscription.Result.Acknowledgement(j2, (String) null, true, 2, (DefaultConstructorMarker) null));
    }

    private final void reset() {
        this.isConnecting = false;
        this.retryCount = 0;
    }

    private final boolean shouldConnect() {
        return !this.isConnecting && ((this.connectionState.getValue() instanceof ConnectionState.Closed) || (this.connectionState.getValue() instanceof ConnectionState.Idle));
    }

    /* access modifiers changed from: private */
    public static final Unit subscribe$lambda$12(Long l2, String str, BaseRelayClient baseRelayClient, Function1 function1) {
        RelayDTO.Subscribe.Request request = new RelayDTO.Subscribe.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.Subscribe.Request.Params(new Topic(str)), 6, (DefaultConstructorMarker) null);
        if (baseRelayClient.isLoggingEnabled()) {
            Logger logger2 = baseRelayClient.logger;
            long currentTimeMillis = System.currentTimeMillis();
            logger2.log("Sending SubscribeRequest: " + request + ";  timestamp: " + currentTimeMillis);
        }
        baseRelayClient.observeSubscribeResult(request.getId(), function1);
        baseRelayClient.getRelayService().subscribeRequest(request);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit subscribe$lambda$13(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Flow subscriptionRequest_delegate$lambda$4(BaseRelayClient baseRelayClient) {
        return FlowKt.onEach(new BaseRelayClient$subscriptionRequest_delegate$lambda$4$$inlined$map$1(baseRelayClient.getRelayService().observeSubscriptionRequest()), new BaseRelayClient$subscriptionRequest$2$2(baseRelayClient, (Continuation<? super BaseRelayClient$subscriptionRequest$2$2>) null));
    }

    /* access modifiers changed from: private */
    public static final Unit unsubscribe$lambda$16(Long l2, String str, String str2, BaseRelayClient baseRelayClient, Function1 function1) {
        RelayDTO.Unsubscribe.Request request = new RelayDTO.Unsubscribe.Request(l2 != null ? l2.longValue() : UtilFunctionsKt.generateClientToServerId(), (String) null, (String) null, new RelayDTO.Unsubscribe.Request.Params(new Topic(str), new SubscriptionId(str2)), 6, (DefaultConstructorMarker) null);
        baseRelayClient.observeUnsubscribeResult(request.getId(), function1);
        baseRelayClient.getRelayService().unsubscribeRequest(request);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit unsubscribe$lambda$17(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        Result.Companion companion = Result.Companion;
        function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    public void approveSession(@NotNull Topic topic, @NotNull Topic topic2, @NotNull String str, @NotNull String str2, long j2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.ApproveSession.Acknowledgement>, Unit> function1) {
        Function1<? super Result<Relay.Model.Call.ApproveSession.Acknowledgement>, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(topic, "pairingTopic");
        Intrinsics.checkNotNullParameter(topic2, "sessionTopic");
        Intrinsics.checkNotNullParameter(str, "sessionProposalResponse");
        String str3 = str2;
        Intrinsics.checkNotNullParameter(str3, "sessionSettlementRequest");
        Intrinsics.checkNotNullParameter(function12, "onResult");
        connectAndCallRelay(new b(topic, topic2, str, str3, j2, l2, this, function1), new h(function12, 20));
    }

    @ExperimentalCoroutinesApi
    public void batchSubscribe(@NotNull List<String> list, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.BatchSubscribe.Acknowledgement>, Unit> function1) {
        Intrinsics.checkNotNullParameter(list, "topics");
        Intrinsics.checkNotNullParameter(function1, "onResult");
        connectAndCallRelay(new T(this, list, l2, function1, 2), new h(function1, 19));
    }

    @NotNull
    public final List<String> getAckedTopics$foundation() {
        return this.ackedTopics;
    }

    @NotNull
    public final ConnectionLifecycle getConnectionLifecycle() {
        ConnectionLifecycle connectionLifecycle2 = this.connectionLifecycle;
        if (connectionLifecycle2 != null) {
            return connectionLifecycle2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("connectionLifecycle");
        return null;
    }

    @NotNull
    public final MutableStateFlow<ConnectionState> getConnectionState$foundation() {
        return this.connectionState;
    }

    @NotNull
    public SharedFlow<Relay.Model.Event> getEventsFlow() {
        return (SharedFlow) this.eventsFlow$delegate.getValue();
    }

    @NotNull
    public final Logger getLogger() {
        return this.logger;
    }

    @NotNull
    public final RelayService getRelayService() {
        RelayService relayService2 = this.relayService;
        if (relayService2 != null) {
            return relayService2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("relayService");
        return null;
    }

    @NotNull
    public Flow<Relay.Model.Call.Subscription.Request> getSubscriptionRequest() {
        return (Flow) this.subscriptionRequest$delegate.getValue();
    }

    public boolean isLoggingEnabled() {
        return this.isLoggingEnabled;
    }

    public final void observeResults() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseRelayClient$observeResults$1(this, (Continuation<? super BaseRelayClient$observeResults$1>) null), 3, (Object) null);
    }

    public void proposeSession(@NotNull Topic topic, @NotNull String str, long j2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.ProposeSession.Acknowledgement>, Unit> function1) {
        Intrinsics.checkNotNullParameter(topic, "pairingTopic");
        Intrinsics.checkNotNullParameter(str, "sessionProposal");
        Intrinsics.checkNotNullParameter(function1, "onResult");
        connectAndCallRelay(new d(topic, str, j2, l2, this, function1), new h(function1, 23));
    }

    @ExperimentalCoroutinesApi
    public void publish(@NotNull String str, @NotNull String str2, @NotNull Relay.Model.IrnParams irnParams, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Publish.Acknowledgement>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(irnParams, "params");
        Intrinsics.checkNotNullParameter(function1, "onResult");
        connectAndCallRelay(new c(irnParams, str, str2, l2, this, function1), new h(function1, 22));
    }

    public final void setAckedTopics$foundation(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.ackedTopics = list;
    }

    public final void setConnectionLifecycle(@NotNull ConnectionLifecycle connectionLifecycle2) {
        Intrinsics.checkNotNullParameter(connectionLifecycle2, "<set-?>");
        this.connectionLifecycle = connectionLifecycle2;
    }

    public final void setConnectionState$foundation(@NotNull MutableStateFlow<ConnectionState> mutableStateFlow) {
        Intrinsics.checkNotNullParameter(mutableStateFlow, "<set-?>");
        this.connectionState = mutableStateFlow;
    }

    public final void setLogger(@NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(logger2, "<set-?>");
        this.logger = logger2;
    }

    public void setLoggingEnabled(boolean z2) {
        this.isLoggingEnabled = z2;
    }

    public final void setRelayService(@NotNull RelayService relayService2) {
        Intrinsics.checkNotNullParameter(relayService2, "<set-?>");
        this.relayService = relayService2;
    }

    @ExperimentalCoroutinesApi
    public void subscribe(@NotNull String str, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function1, "onResult");
        connectAndCallRelay(new T(l2, str, this, function1, 3), new h(function1, 24));
    }

    @ExperimentalCoroutinesApi
    public void unsubscribe(@NotNull String str, @NotNull String str2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Unsubscribe.Acknowledgement>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, "subscriptionId");
        Intrinsics.checkNotNullParameter(function1, "onResult");
        connectAndCallRelay(new C0179t(l2, str, str2, this, (Function1) function1), new h(function1, 21));
    }
}
