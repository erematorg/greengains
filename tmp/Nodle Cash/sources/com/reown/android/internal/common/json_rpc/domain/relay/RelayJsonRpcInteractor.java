package com.reown.android.internal.common.json_rpc.domain.relay;

import S0.h;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.material.C0140g;
import androidx.compose.runtime.e;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.ConditionalExponentialBackoffStrategy;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.di.g;
import com.reown.android.internal.common.exception.NoConnectivityException;
import com.reown.android.internal.common.exception.NoInternetConnectionException;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.internal.common.model.type.Error;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.internal.utils.ObservableMap;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.android.relay.WSSConnectionState;
import com.reown.foundation.common.model.SubscriptionId;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.network.RelayInterface;
import com.reown.foundation.network.model.Relay;
import com.reown.foundation.util.Logger;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u00100\u001a\u000201H\u0016J>\u00102\u001a\u0002012\u0006\u00103\u001a\u0002042\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016JV\u0010<\u001a\u0002012\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u0002042\u0006\u0010?\u001a\u00020@2\n\u0010A\u001a\u0006\u0012\u0002\b\u0003062\u0006\u0010B\u001a\u00020C2\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016JX\u0010D\u001a\u0002012\u0006\u00103\u001a\u0002042\u0006\u0010E\u001a\u00020F2\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010J2\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016JT\u0010K\u001a\u0002012\u0006\u00103\u001a\u0002042\u0006\u0010E\u001a\u00020F2\u0006\u0010L\u001a\u00020M2\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:2\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010G\u001a\u00020HH\u0016J8\u0010N\u001a\u0002012\u0006\u00103\u001a\u0002042\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002010:2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016JD\u0010O\u001a\u0002012\f\u0010P\u001a\b\u0012\u0004\u0012\u00020*0Q2\u0018\u00107\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0Q\u0012\u0004\u0012\u0002010:2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016J2\u0010R\u001a\u0002012\u0006\u00103\u001a\u0002042\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016JT\u0010S\u001a\u0002012\u0006\u0010T\u001a\u00020\u00162\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010J2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:2\f\u00107\u001a\b\u0012\u0004\u0012\u00020108H\u0016J\\\u0010S\u001a\u0002012\u0006\u0010X\u001a\u00020C2\u0006\u00103\u001a\u0002042\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010J2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:2\f\u00107\u001a\b\u0012\u0004\u0012\u00020108H\u0016J*\u0010Y\u001a\u0002012\u0006\u0010T\u001a\u00020\u00162\u0006\u0010W\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016JZ\u0010Z\u001a\u0002012\u0006\u0010T\u001a\u00020\u00162\u0006\u0010[\u001a\u00020\\2\u0006\u0010W\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010J2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002010:2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016J\\\u0010Z\u001a\u0002012\u0006\u0010X\u001a\u00020C2\u0006\u00103\u001a\u0002042\u0006\u0010[\u001a\u00020\\2\u0006\u0010W\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010J2\f\u00107\u001a\b\u0012\u0004\u0012\u000201082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002010:H\u0016J\b\u0010]\u001a\u000201H\u0002J\u0018\u0010^\u001a\u0002012\u0006\u0010_\u001a\u00020`2\u0006\u00103\u001a\u000204H\u0002J\u0018\u0010a\u001a\u00020*2\u0006\u00103\u001a\u0002042\u0006\u0010_\u001a\u00020`H\u0002J\u0016\u0010]\u001a\u0002012\u0006\u0010b\u001a\u00020cH@¢\u0006\u0002\u0010dJ\u001e\u0010e\u001a\u0002012\u0006\u0010f\u001a\u00020g2\u0006\u0010b\u001a\u00020cH@¢\u0006\u0002\u0010hJ\u001e\u0010i\u001a\u0002012\u0006\u0010j\u001a\u00020k2\u0006\u00103\u001a\u000204H@¢\u0006\u0002\u0010lJ\u001e\u0010m\u001a\u0002012\u0006\u0010j\u001a\u00020k2\u0006\u00103\u001a\u000204H@¢\u0006\u0002\u0010lJ\u0016\u0010n\u001a\u0002012\u0006\u0010o\u001a\u00020pH@¢\u0006\u0002\u0010qJ\u0010\u0010r\u001a\u0002012\u0006\u0010s\u001a\u00020*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118BX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0015X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0015X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$8VX\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020*0)X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0,8VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u0006t"}, d2 = {"Lcom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor;", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "relay", "Lcom/reown/android/relay/RelayConnectionInterface;", "chaChaPolyCodec", "Lcom/reown/android/internal/common/crypto/codec/Codec;", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "pushMessageStorage", "Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "backoffStrategy", "Lcom/reown/android/internal/common/ConditionalExponentialBackoffStrategy;", "<init>", "(Lcom/reown/android/relay/RelayConnectionInterface;Lcom/reown/android/internal/common/crypto/codec/Codec;Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;Lcom/reown/foundation/util/Logger;Lcom/reown/android/internal/common/ConditionalExponentialBackoffStrategy;)V", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "getSerializer", "()Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "_clientSyncJsonRpc", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/WCRequest;", "clientSyncJsonRpc", "Lkotlinx/coroutines/flow/SharedFlow;", "getClientSyncJsonRpc", "()Lkotlinx/coroutines/flow/SharedFlow;", "_peerResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "peerResponse", "getPeerResponse", "_internalErrors", "Lcom/reown/android/internal/common/model/SDKError;", "internalErrors", "getInternalErrors", "wssConnectionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/reown/android/relay/WSSConnectionState;", "getWssConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "subscriptions", "Lcom/reown/android/internal/utils/ObservableMap;", "", "onResubscribe", "Lkotlinx/coroutines/flow/Flow;", "", "getOnResubscribe", "()Lkotlinx/coroutines/flow/Flow;", "checkNetworkConnectivity", "", "proposeSession", "topic", "Lcom/reown/foundation/common/model/Topic;", "payload", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "approveSession", "pairingTopic", "sessionTopic", "sessionProposalResponse", "Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "settleRequest", "correlationId", "", "publishJsonRpcRequest", "params", "Lcom/reown/android/internal/common/model/IrnParams;", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "publishJsonRpcResponse", "response", "Lcom/reown/android/internal/common/JsonRpcResponse;", "subscribe", "batchSubscribe", "topics", "", "unsubscribe", "respondWithParams", "request", "clientParams", "Lcom/reown/android/internal/common/model/type/ClientParams;", "irnParams", "requestId", "respondWithSuccess", "respondWithError", "error", "Lcom/reown/android/internal/common/model/type/Error;", "manageSubscriptions", "storePushRequestsIfEnabled", "relayRequest", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "decryptMessage", "subscription", "Lcom/reown/android/internal/common/json_rpc/domain/relay/Subscription;", "(Lcom/reown/android/internal/common/json_rpc/domain/relay/Subscription;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleRequest", "clientJsonRpc", "Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;", "(Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;Lcom/reown/android/internal/common/json_rpc/domain/relay/Subscription;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleJsonRpcResult", "jsonRpcResult", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "(Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleJsonRpcResponsesWithoutStoredRequest", "handleJsonRpcError", "jsonRpcError", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "(Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleError", "errorMessage", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRelayJsonRpcInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n+ 4 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 5 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 6 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 7 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 8 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,558:1\n124#2,4:559\n142#3:563\n17#4:564\n19#4:568\n46#5:565\n51#5:567\n105#6:566\n56#7:569\n56#7:571\n56#7:573\n1#8:570\n1#8:572\n1#8:574\n*S KotlinDebug\n*F\n+ 1 RelayJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/relay/RelayJsonRpcInteractor\n*L\n68#1:559,4\n68#1:563\n466#1:564\n466#1:568\n466#1:565\n466#1:567\n466#1:566\n490#1:569\n492#1:571\n494#1:573\n490#1:570\n492#1:572\n494#1:574\n*E\n"})
public final class RelayJsonRpcInteractor implements RelayJsonRpcInteractorInterface {
    @NotNull
    private final MutableSharedFlow<WCRequest> _clientSyncJsonRpc;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<SDKError> _internalErrors;
    @NotNull
    private final MutableSharedFlow<WCResponse> _peerResponse;
    @NotNull
    private final ConditionalExponentialBackoffStrategy backoffStrategy;
    @NotNull
    private final Codec chaChaPolyCodec;
    @NotNull
    private final SharedFlow<WCRequest> clientSyncJsonRpc;
    @NotNull
    private final SharedFlow<SDKError> internalErrors;
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcHistory jsonRpcHistory;
    @NotNull
    private final Logger logger;
    @NotNull
    private final SharedFlow<WCResponse> peerResponse;
    /* access modifiers changed from: private */
    @NotNull
    public final PushMessagesRepository pushMessageStorage;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayConnectionInterface relay;
    /* access modifiers changed from: private */
    @NotNull
    public ObservableMap<String, String> subscriptions = new ObservableMap<>((Map) null, new a(this, 1), 1, (DefaultConstructorMarker) null);

    public RelayJsonRpcInteractor(@NotNull RelayConnectionInterface relayConnectionInterface, @NotNull Codec codec, @NotNull JsonRpcHistory jsonRpcHistory2, @NotNull PushMessagesRepository pushMessagesRepository, @NotNull Logger logger2, @NotNull ConditionalExponentialBackoffStrategy conditionalExponentialBackoffStrategy) {
        Intrinsics.checkNotNullParameter(relayConnectionInterface, "relay");
        Intrinsics.checkNotNullParameter(codec, "chaChaPolyCodec");
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(pushMessagesRepository, "pushMessageStorage");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(conditionalExponentialBackoffStrategy, "backoffStrategy");
        this.relay = relayConnectionInterface;
        this.chaChaPolyCodec = codec;
        this.jsonRpcHistory = jsonRpcHistory2;
        this.pushMessageStorage = pushMessagesRepository;
        this.logger = logger2;
        this.backoffStrategy = conditionalExponentialBackoffStrategy;
        MutableSharedFlow<WCRequest> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._clientSyncJsonRpc = MutableSharedFlow$default;
        this.clientSyncJsonRpc = FlowKt.asSharedFlow(MutableSharedFlow$default);
        MutableSharedFlow<WCResponse> MutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._peerResponse = MutableSharedFlow$default2;
        this.peerResponse = FlowKt.asSharedFlow(MutableSharedFlow$default2);
        MutableSharedFlow<SDKError> MutableSharedFlow$default3 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._internalErrors = MutableSharedFlow$default3;
        this.internalErrors = FlowKt.asSharedFlow(MutableSharedFlow$default3);
        manageSubscriptions();
    }

    /* access modifiers changed from: private */
    public static final Unit approveSession$lambda$6(RelayJsonRpcInteractor relayJsonRpcInteractor, long j2, String str, Function0 function0, Function1 function1, Result result) {
        Object r7 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r7);
        if (r02 == null) {
            Relay.Model.Call.ApproveSession.Acknowledgement acknowledgement = (Relay.Model.Call.ApproveSession.Acknowledgement) r7;
            relayJsonRpcInteractor.jsonRpcHistory.updateRequestWithResponse(j2, str);
            function0.invoke();
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("JsonRpcInteractor: Cannot send the session approve request, error: " + r02);
            function1.invoke(new Throwable(c.a("Session approve error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit batchSubscribe$lambda$18(RelayJsonRpcInteractor relayJsonRpcInteractor, List list, Function1 function1, Function1 function12, Result result) {
        Object r5 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r5);
        if (r02 == null) {
            relayJsonRpcInteractor.subscriptions.putAll(MapsKt.toMap(CollectionsKt.zip(list, ((Relay.Model.Call.BatchSubscribe.Acknowledgement) r5).getResult())));
            function1.invoke(list);
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("Batch subscribe to topics error: " + list + " error: " + r02);
            function12.invoke(new Throwable(c.a("Batch subscribe error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final String decryptMessage(Topic topic, Relay.Model.Call.Subscription.Request request) {
        try {
            Codec codec = this.chaChaPolyCodec;
            byte[] decode = Base64.decode(request.getMessage());
            Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
            return codec.decrypt(topic, decode);
        } catch (Exception e3) {
            handleError(c.a("ManSub: ", ExceptionsKt.stackTraceToString(e3)));
            return Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
    }

    private final JsonRpcSerializer getSerializer() {
        return (JsonRpcSerializer) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public final void handleError(String str) {
        Logger logger2 = this.logger;
        logger2.error("JsonRpcInteractor error: " + str);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RelayJsonRpcInteractor$handleError$1(this, str, (Continuation<? super RelayJsonRpcInteractor$handleError$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleJsonRpcError(com.reown.android.internal.common.JsonRpcResponse.JsonRpcError r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcError$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcError$1 r0 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcError$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcError$1 r0 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcError$1
            r0.<init>(r7, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r7 = r0.L$3
            com.reown.android.internal.common.model.type.ClientParams r7 = (com.reown.android.internal.common.model.type.ClientParams) r7
            java.lang.Object r7 = r0.L$2
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r7 = (com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r7 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a3
        L_0x0039:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r9)
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r9 = r7.getSerializer()
            java.lang.String r9 = r9.serialize(r8)
            if (r9 != 0) goto L_0x0056
            java.lang.String r8 = "JsonRpcInteractor: Unknown result params"
            r7.handleError(r8)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0056:
            com.reown.android.internal.common.storage.rpc.JsonRpcHistory r2 = r7.jsonRpcHistory
            long r4 = r8.getId()
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r2 = r2.updateRequestWithResponse(r4, r9)
            if (r2 == 0) goto L_0x00a3
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r4 = r7.getSerializer()
            java.lang.String r5 = r2.getMethod()
            java.lang.String r6 = r2.getBody()
            com.reown.android.internal.common.model.type.ClientParams r4 = r4.deserialize(r5, r6)
            if (r4 == 0) goto L_0x009e
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.WCResponse> r7 = r7._peerResponse
            com.reown.android.internal.common.model.WCResponse r5 = com.reown.android.internal.common.json_rpc.model.JsonRpcMapperKt.toWCResponse(r2, r8, r4)
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$0 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$2 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$3 = r8
            r8 = 0
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.emit(r5, r0)
            if (r7 != r1) goto L_0x00a3
            return r1
        L_0x009e:
            java.lang.String r8 = "JsonRpcInteractor: Unknown error params"
            r7.handleError(r8)
        L_0x00a3:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor.handleJsonRpcError(com.reown.android.internal.common.JsonRpcResponse$JsonRpcError, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object handleJsonRpcResponsesWithoutStoredRequest(JsonRpcResponse.JsonRpcResult jsonRpcResult, Topic topic, Continuation<? super Unit> continuation) {
        if (!(jsonRpcResult.getResult() instanceof ChatNotifyResponseAuthParams.ResponseAuth)) {
            return Unit.INSTANCE;
        }
        Object emit = this._peerResponse.emit(new WCResponse(topic, Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), jsonRpcResult, (ClientParams) jsonRpcResult.getResult()), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleJsonRpcResult(com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult r17, com.reown.foundation.common.model.Topic r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            boolean r3 = r2 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcResult$1
            if (r3 == 0) goto L_0x0019
            r3 = r2
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcResult$1 r3 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcResult$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0019
            int r4 = r4 - r5
            r3.label = r4
            goto L_0x001e
        L_0x0019:
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcResult$1 r3 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleJsonRpcResult$1
            r3.<init>(r0, r2)
        L_0x001e:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            if (r5 == 0) goto L_0x0068
            if (r5 == r7) goto L_0x004b
            if (r5 != r6) goto L_0x0043
            java.lang.Object r0 = r3.L$3
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r0 = (com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord) r0
            java.lang.Object r0 = r3.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r3.L$1
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r3.L$0
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r0 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r0
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x010b
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            java.lang.Object r0 = r3.L$5
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r0 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r0
            java.lang.Object r0 = r3.L$4
            com.reown.android.internal.common.model.type.ClientParams r0 = (com.reown.android.internal.common.model.type.ClientParams) r0
            java.lang.Object r0 = r3.L$3
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r0 = (com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord) r0
            java.lang.Object r0 = r3.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r3.L$1
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r3.L$0
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r0 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r0
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x00e5
        L_0x0068:
            kotlin.ResultKt.throwOnFailure(r2)
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r2 = r16.getSerializer()
            java.lang.String r2 = r2.serialize(r1)
            java.lang.String r5 = "JsonRpcInteractor: Unknown result params"
            if (r2 != 0) goto L_0x007d
            r0.handleError(r5)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x007d:
            com.reown.android.internal.common.storage.rpc.JsonRpcHistory r8 = r0.jsonRpcHistory
            long r9 = r17.getId()
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r8 = r8.updateRequestWithResponse(r9, r2)
            if (r8 == 0) goto L_0x00e8
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r6 = r16.getSerializer()
            java.lang.String r9 = r8.getMethod()
            java.lang.String r10 = r8.getBody()
            com.reown.android.internal.common.model.type.ClientParams r6 = r6.deserialize(r9, r10)
            if (r6 == 0) goto L_0x00e2
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r5 = new com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult
            long r10 = r17.getId()
            java.lang.Object r13 = r17.getResult()
            r14 = 2
            r15 = 0
            r12 = 0
            r9 = r5
            r9.<init>(r10, r12, r13, r14, r15)
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.WCResponse> r0 = r0._peerResponse
            com.reown.android.internal.common.model.WCResponse r9 = com.reown.android.internal.common.json_rpc.model.JsonRpcMapperKt.toWCResponse(r8, r5, r6)
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)
            r3.L$0 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)
            r3.L$1 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r3.L$2 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r3.L$3 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r3.L$4 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r3.L$5 = r1
            r1 = 0
            r3.I$0 = r1
            r3.label = r7
            java.lang.Object r0 = r0.emit(r9, r3)
            if (r0 != r4) goto L_0x00e5
            return r4
        L_0x00e2:
            r0.handleError(r5)
        L_0x00e5:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00e8:
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)
            r3.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)
            r3.L$1 = r5
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r3.L$2 = r2
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r3.L$3 = r2
            r3.label = r6
            r2 = r18
            java.lang.Object r0 = r0.handleJsonRpcResponsesWithoutStoredRequest(r1, r2, r3)
            if (r0 != r4) goto L_0x010b
            return r4
        L_0x010b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor.handleJsonRpcResult(com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult, com.reown.foundation.common.model.Topic, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleRequest(com.reown.android.internal.common.model.sync.ClientJsonRpc r12, com.reown.android.internal.common.json_rpc.domain.relay.Subscription r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleRequest$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleRequest$1 r0 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleRequest$1 r0 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$handleRequest$1
            r0.<init>(r11, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r11 = r0.L$2
            com.reown.android.internal.common.model.type.ClientParams r11 = (com.reown.android.internal.common.model.type.ClientParams) r11
            java.lang.Object r11 = r0.L$1
            com.reown.android.internal.common.json_rpc.domain.relay.Subscription r11 = (com.reown.android.internal.common.json_rpc.domain.relay.Subscription) r11
            java.lang.Object r11 = r0.L$0
            com.reown.android.internal.common.model.sync.ClientJsonRpc r11 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0096
        L_0x0035:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r14)
            com.reown.android.internal.common.storage.rpc.JsonRpcHistory r4 = r11.jsonRpcHistory
            long r5 = r12.getId()
            com.reown.foundation.common.model.Topic r7 = r13.getTopic()
            java.lang.String r8 = r12.getMethod()
            java.lang.String r9 = r13.getDecryptedMessage()
            com.reown.android.internal.common.model.TransportType r14 = com.reown.android.internal.common.model.TransportType.RELAY
            r10 = r14
            boolean r2 = r4.setRequest(r5, r7, r8, r9, r10)
            if (r2 == 0) goto L_0x0096
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r2 = r11.getSerializer()
            java.lang.String r4 = r12.getMethod()
            java.lang.String r5 = r13.getDecryptedMessage()
            com.reown.android.internal.common.model.type.ClientParams r2 = r2.deserialize(r4, r5)
            if (r2 == 0) goto L_0x0091
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.WCRequest> r11 = r11._clientSyncJsonRpc
            com.reown.android.internal.common.model.WCRequest r14 = com.reown.android.internal.common.json_rpc.model.JsonRpcMapperKt.toWCRequest(r13, r12, r2, r14)
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$0 = r12
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.L$1 = r12
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$2 = r12
            r12 = 0
            r0.I$0 = r12
            r0.label = r3
            java.lang.Object r11 = r11.emit(r14, r0)
            if (r11 != r1) goto L_0x0096
            return r1
        L_0x0091:
            java.lang.String r12 = "JsonRpcInteractor: Unknown request params"
            r11.handleError(r12)
        L_0x0096:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor.handleRequest(com.reown.android.internal.common.model.sync.ClientJsonRpc, com.reown.android.internal.common.json_rpc.domain.relay.Subscription, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void manageSubscriptions() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RelayJsonRpcInteractor$manageSubscriptions$1(this, (Continuation<? super RelayJsonRpcInteractor$manageSubscriptions$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit proposeSession$lambda$3(Function0 function0, RelayJsonRpcInteractor relayJsonRpcInteractor, Function1 function1, Result result) {
        Object r4 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r4);
        if (r02 == null) {
            Relay.Model.Call.ProposeSession.Acknowledgement acknowledgement = (Relay.Model.Call.ProposeSession.Acknowledgement) r4;
            function0.invoke();
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("JsonRpcInteractor: Cannot send the session proposal request, error: " + r02);
            function1.invoke(new Throwable(c.a("Session proposal error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit publishJsonRpcRequest$lambda$9(Function0 function0, RelayJsonRpcInteractor relayJsonRpcInteractor, Function1 function1, Result result) {
        Object r4 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r4);
        if (r02 == null) {
            Relay.Model.Call.Publish.Acknowledgement acknowledgement = (Relay.Model.Call.Publish.Acknowledgement) r4;
            function0.invoke();
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("JsonRpcInteractor: Cannot send the request, error: " + r02);
            function1.invoke(new Throwable(c.a("Publish error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit publishJsonRpcResponse$lambda$12(RelayJsonRpcInteractor relayJsonRpcInteractor, JsonRpcResponse jsonRpcResponse, String str, Function0 function0, Function1 function1, Result result) {
        Object r6 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r6);
        if (r02 == null) {
            Relay.Model.Call.Publish.Acknowledgement acknowledgement = (Relay.Model.Call.Publish.Acknowledgement) r6;
            relayJsonRpcInteractor.jsonRpcHistory.updateRequestWithResponse(jsonRpcResponse.getId(), str);
            function0.invoke();
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("JsonRpcInteractor: Cannot send the response, error: " + r02);
            function1.invoke(new Throwable(c.a("Publish error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithError$lambda$27(Function1 function1, WCRequest wCRequest) {
        function1.invoke(wCRequest);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithError$lambda$28(Function1 function1, RelayJsonRpcInteractor relayJsonRpcInteractor, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "failure");
        function1.invoke(th);
        relayJsonRpcInteractor.handleError(c.a("Cannot send respondWithError: ", ExceptionsKt.stackTraceToString(th)));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithError$lambda$29(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithError$lambda$30(Function1 function1, RelayJsonRpcInteractor relayJsonRpcInteractor, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "failure");
        function1.invoke(th);
        relayJsonRpcInteractor.handleError(c.a("Cannot send respondWithError: ", ExceptionsKt.stackTraceToString(th)));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithParams$lambda$22(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithParams$lambda$23(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithParams$lambda$24(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithParams$lambda$25(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit respondWithSuccess$lambda$26(RelayJsonRpcInteractor relayJsonRpcInteractor, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        relayJsonRpcInteractor.handleError(c.a("Cannot send the responseWithSuccess, error: ", ExceptionsKt.stackTraceToString(th)));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void storePushRequestsIfEnabled(Relay.Model.Call.Subscription.Request request, Topic topic) {
        FlowKt.launchIn(FlowKt.onEach(new RelayJsonRpcInteractor$storePushRequestsIfEnabled$$inlined$filter$1(this.pushMessageStorage.getArePushNotificationsEnabled()), new RelayJsonRpcInteractor$storePushRequestsIfEnabled$2(this, request, topic, (Continuation<? super RelayJsonRpcInteractor$storePushRequestsIfEnabled$2>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public static final Unit subscribe$lambda$15(RelayJsonRpcInteractor relayJsonRpcInteractor, Topic topic, Function1 function1, Function1 function12, Result result) {
        Object r5 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r5);
        if (r02 == null) {
            relayJsonRpcInteractor.subscriptions.put(topic.getValue(), ((Relay.Model.Call.Subscribe.Acknowledgement) r5).getResult());
            function1.invoke(topic);
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("Subscribe to topic error: " + topic + " error: " + r02);
            function12.invoke(new Throwable(c.a("Subscribe error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit subscriptions$lambda$0(RelayJsonRpcInteractor relayJsonRpcInteractor, Map map) {
        Intrinsics.checkNotNullParameter(map, "newMap");
        if (map.isEmpty()) {
            relayJsonRpcInteractor.backoffStrategy.shouldBackoff(false);
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit unsubscribe$lambda$21(RelayJsonRpcInteractor relayJsonRpcInteractor, Topic topic, Function0 function0, Function1 function1, Result result) {
        Object r10 = result.m8988unboximpl();
        Throwable r02 = Result.m8982exceptionOrNullimpl(r10);
        if (r02 == null) {
            Relay.Model.Call.Unsubscribe.Acknowledgement acknowledgement = (Relay.Model.Call.Unsubscribe.Acknowledgement) r10;
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RelayJsonRpcInteractor$unsubscribe$1$1$1(relayJsonRpcInteractor, topic, function0, (Continuation<? super RelayJsonRpcInteractor$unsubscribe$1$1$1>) null), 3, (Object) null);
        } else {
            Logger logger2 = relayJsonRpcInteractor.logger;
            logger2.error("Unsubscribe to topic: " + topic + " error: " + r02);
            function1.invoke(new Throwable(c.a("Unsubscribe error: ", r02.getMessage())));
        }
        return Unit.INSTANCE;
    }

    public void approveSession(@NotNull Topic topic, @NotNull Topic topic2, @NotNull CoreSignParams.ApprovalParams approvalParams, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, long j2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        JsonRpcClientSync<?> jsonRpcClientSync2 = jsonRpcClientSync;
        Function1<? super Throwable, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(topic, "pairingTopic");
        Intrinsics.checkNotNullParameter(topic2, "sessionTopic");
        Intrinsics.checkNotNullParameter(approvalParams, "sessionProposalResponse");
        Intrinsics.checkNotNullParameter(jsonRpcClientSync2, "settleRequest");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        try {
            checkNetworkConnectivity();
            try {
                String serialize = getSerializer().serialize(new JsonRpcResponse.JsonRpcResult(j2, (String) null, approvalParams, 2, (DefaultConstructorMarker) null));
                if (serialize != null) {
                    String serialize2 = getSerializer().serialize(jsonRpcClientSync2);
                    if (serialize2 != null) {
                        if (this.jsonRpcHistory.setRequest(jsonRpcClientSync.getId(), topic2, jsonRpcClientSync.getMethod(), serialize2, TransportType.RELAY)) {
                            Codec codec = this.chaChaPolyCodec;
                            EnvelopeType envelopeType = EnvelopeType.ZERO;
                            byte[] encrypt$default = Codec.encrypt$default(codec, topic, serialize, envelopeType, (Participants) null, 8, (Object) null);
                            byte[] encrypt$default2 = Codec.encrypt$default(this.chaChaPolyCodec, topic2, serialize2, envelopeType, (Participants) null, 8, (Object) null);
                            String base64String = Base64.toBase64String(encrypt$default);
                            String base64String2 = Base64.toBase64String(encrypt$default2);
                            RelayConnectionInterface relayConnectionInterface = this.relay;
                            Intrinsics.checkNotNull(base64String2);
                            Intrinsics.checkNotNull(base64String);
                            RelayInterface.approveSession$default(relayConnectionInterface, topic, topic2, base64String, base64String2, j2, (Long) null, new c(this, j2, serialize, function0, function1), 32, (Object) null);
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("RelayJsonRpcInteractor: Unknown Request Params");
                }
                throw new IllegalStateException("RelayJsonRpcInteractor: Unknown Request Params");
            } catch (Exception e3) {
                Logger logger2 = this.logger;
                logger2.error("JsonRpcInteractor: Cannot send the request, exception: " + e3);
                function12.invoke(new Throwable(C0118y.e("Publish Request Error: ", e3)));
            }
        } catch (NoConnectivityException e4) {
            function12.invoke(e4);
        }
    }

    public void batchSubscribe(@NotNull List<String> list, @NotNull Function1<? super List<String>, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(list, "topics");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        try {
            checkNetworkConnectivity();
            if (!list.isEmpty()) {
                this.backoffStrategy.shouldBackoff(true);
                try {
                    RelayInterface.batchSubscribe$default(this.relay, list, (Long) null, new C0140g(this, (Object) list, (Function1) function1, (Function1) function12, 3), 2, (Object) null);
                } catch (Exception e3) {
                    Logger logger2 = this.logger;
                    logger2.error("Batch subscribe to topics error: " + list + " error: " + e3);
                    function12.invoke(new Throwable(c.a("Batch subscribe error: ", e3.getMessage())));
                }
            }
        } catch (NoConnectivityException e4) {
            function12.invoke(e4);
        }
    }

    public void checkNetworkConnectivity() {
        if (this.relay.isNetworkAvailable().getValue() != null && Intrinsics.areEqual((Object) this.relay.isNetworkAvailable().getValue(), (Object) Boolean.FALSE)) {
            throw new NoInternetConnectionException("Connection error: Please check your Internet connection");
        }
    }

    @NotNull
    public SharedFlow<WCRequest> getClientSyncJsonRpc() {
        return this.clientSyncJsonRpc;
    }

    @NotNull
    public SharedFlow<SDKError> getInternalErrors() {
        return this.internalErrors;
    }

    @NotNull
    public Flow<Object> getOnResubscribe() {
        return this.relay.getOnResubscribe();
    }

    @NotNull
    public SharedFlow<WCResponse> getPeerResponse() {
        return this.peerResponse;
    }

    @NotNull
    public StateFlow<WSSConnectionState> getWssConnectionState() {
        return this.relay.getWssConnectionState();
    }

    public void proposeSession(@NotNull Topic topic, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(jsonRpcClientSync, "payload");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onFailure");
        try {
            checkNetworkConnectivity();
            try {
                String serialize = getSerializer().serialize(jsonRpcClientSync);
                if (serialize != null) {
                    if (this.jsonRpcHistory.setRequest(jsonRpcClientSync.getId(), topic, jsonRpcClientSync.getMethod(), serialize, TransportType.RELAY)) {
                        String base64String = Base64.toBase64String(Codec.encrypt$default(this.chaChaPolyCodec, topic, serialize, EnvelopeType.ZERO, (Participants) null, 8, (Object) null));
                        RelayConnectionInterface relayConnectionInterface = this.relay;
                        Intrinsics.checkNotNull(base64String);
                        RelayInterface.proposeSession$default(relayConnectionInterface, topic, base64String, jsonRpcClientSync.getId(), (Long) null, new d(function0, this, function1, 1), 8, (Object) null);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("RelayJsonRpcInteractor: Unknown Request Params");
            } catch (Exception e3) {
                Logger logger2 = this.logger;
                logger2.error("JsonRpcInteractor: Cannot send the request, exception: " + e3);
                function1.invoke(new Throwable(C0118y.e("Publish Request Error: ", e3)));
            }
        } catch (NoConnectivityException e4) {
            function1.invoke(e4);
        }
    }

    public void publishJsonRpcRequest(@NotNull Topic topic, @NotNull IrnParams irnParams, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(irnParams, "params");
        Intrinsics.checkNotNullParameter(jsonRpcClientSync, "payload");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onFailure");
        try {
            checkNetworkConnectivity();
            try {
                String serialize = getSerializer().serialize(jsonRpcClientSync);
                if (serialize != null) {
                    if (this.jsonRpcHistory.setRequest(jsonRpcClientSync.getId(), topic, jsonRpcClientSync.getMethod(), serialize, TransportType.RELAY)) {
                        String base64String = Base64.toBase64String(this.chaChaPolyCodec.encrypt(topic, serialize, envelopeType, participants));
                        RelayConnectionInterface relayConnectionInterface = this.relay;
                        String value = topic.getValue();
                        Intrinsics.checkNotNull(base64String);
                        RelayInterface.publish$default(relayConnectionInterface, value, base64String, Intrinsics.checkNotNullParameter(irnParams, "<this>"), (Long) null, new d(function0, this, function1, 0), 8, (Object) null);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("RelayJsonRpcInteractor: Unknown Request Params");
            } catch (Exception e3) {
                Logger logger2 = this.logger;
                logger2.error("JsonRpcInteractor: Cannot send the request, exception: " + e3);
                function1.invoke(new Throwable(C0118y.e("Publish Request Error: ", e3)));
            }
        } catch (NoConnectivityException e4) {
            function1.invoke(e4);
        }
    }

    public void publishJsonRpcResponse(@NotNull Topic topic, @NotNull IrnParams irnParams, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @Nullable Participants participants, @NotNull EnvelopeType envelopeType) {
        Topic topic2 = topic;
        JsonRpcResponse jsonRpcResponse2 = jsonRpcResponse;
        Function1<? super Throwable, Unit> function12 = function1;
        EnvelopeType envelopeType2 = envelopeType;
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(irnParams, "params");
        Intrinsics.checkNotNullParameter(jsonRpcResponse2, "response");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        Intrinsics.checkNotNullParameter(envelopeType2, "envelopeType");
        try {
            checkNetworkConnectivity();
            try {
                String serialize = getSerializer().serialize(jsonRpcResponse2);
                if (serialize != null) {
                    String base64String = Base64.toBase64String(this.chaChaPolyCodec.encrypt(topic2, serialize, envelopeType2, participants));
                    RelayConnectionInterface relayConnectionInterface = this.relay;
                    String value = topic.getValue();
                    Intrinsics.checkNotNull(base64String);
                    RelayInterface.publish$default(relayConnectionInterface, value, base64String, Intrinsics.checkNotNullParameter(irnParams, "<this>"), (Long) null, new g(this, jsonRpcResponse, serialize, (Function0) function0, (Function1) function1), 8, (Object) null);
                    return;
                }
                throw new IllegalStateException("RelayJsonRpcInteractor: Unknown Response Params");
            } catch (Exception e3) {
                Logger logger2 = this.logger;
                logger2.error("JsonRpcInteractor: Cannot send the response, exception: " + e3);
                function12.invoke(new Throwable(C0118y.e("Publish Response Error: ", e3)));
            }
        } catch (NoConnectivityException e4) {
            function12.invoke(e4);
        }
    }

    public void respondWithError(@NotNull WCRequest wCRequest, @NotNull Error error, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super WCRequest, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        WCRequest wCRequest2 = wCRequest;
        Function1<? super WCRequest, Unit> function13 = function1;
        Function1<? super Throwable, Unit> function14 = function12;
        Intrinsics.checkNotNullParameter(wCRequest2, "request");
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Intrinsics.checkNotNullParameter(irnParams, "irnParams");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        Intrinsics.checkNotNullParameter(function13, "onSuccess");
        Intrinsics.checkNotNullParameter(function14, "onFailure");
        Logger logger2 = this.logger;
        String message = error.getMessage();
        int code = error.getCode();
        logger2.error("Responding with error: " + message + ": " + code);
        JsonRpcResponse.JsonRpcError jsonRpcError = new JsonRpcResponse.JsonRpcError(wCRequest.getId(), (String) null, new JsonRpcResponse.Error(error.getCode(), error.getMessage()), 2, (DefaultConstructorMarker) null);
        try {
            publishJsonRpcResponse(wCRequest.getTopic(), irnParams, jsonRpcError, new e(function13, wCRequest2, 3), new e(function14, this, 0), participants, envelopeType);
        } catch (Exception e3) {
            handleError(c.a("publishFailure; ", ExceptionsKt.stackTraceToString(e3)));
        }
    }

    public void respondWithParams(@NotNull WCRequest wCRequest, @NotNull ClientParams clientParams, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Function0<Unit> function0) {
        Function1<? super Throwable, Unit> function12 = function1;
        Function0<Unit> function02 = function0;
        WCRequest wCRequest2 = wCRequest;
        Intrinsics.checkNotNullParameter(wCRequest, "request");
        Intrinsics.checkNotNullParameter(clientParams, "clientParams");
        Intrinsics.checkNotNullParameter(irnParams, "irnParams");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        Intrinsics.checkNotNullParameter(function02, "onSuccess");
        JsonRpcResponse.JsonRpcResult jsonRpcResult = new JsonRpcResponse.JsonRpcResult(wCRequest.getId(), (String) null, clientParams, 2, (DefaultConstructorMarker) null);
        publishJsonRpcResponse(wCRequest.getTopic(), irnParams, jsonRpcResult, new b(function02, 1), new h(function12, 5), participants, envelopeType);
    }

    public void respondWithSuccess(@NotNull WCRequest wCRequest, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants) {
        Intrinsics.checkNotNullParameter(wCRequest, "request");
        Intrinsics.checkNotNullParameter(irnParams, "irnParams");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        try {
            RelayJsonRpcInteractorInterface.publishJsonRpcResponse$default(this, wCRequest.getTopic(), irnParams, new JsonRpcResponse.JsonRpcResult(wCRequest.getId(), (String) null, Boolean.TRUE, 2, (DefaultConstructorMarker) null), (Function0) null, new a(this, 0), participants, envelopeType, 8, (Object) null);
        } catch (Exception e3) {
            handleError(c.a("publishFailure; ", ExceptionsKt.stackTraceToString(e3)));
        }
    }

    public void subscribe(@NotNull Topic topic, @NotNull Function1<? super Topic, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        try {
            checkNetworkConnectivity();
            try {
                this.backoffStrategy.shouldBackoff(true);
                RelayInterface.subscribe$default(this.relay, topic.getValue(), (Long) null, new C0140g(this, (Object) topic, (Function1) function1, (Function1) function12, 5), 2, (Object) null);
            } catch (Exception e3) {
                Logger logger2 = this.logger;
                logger2.error("Subscribe to topic error: " + topic + " error: " + e3);
                function12.invoke(new Throwable(c.a("Subscribe error: ", e3.getMessage())));
            }
        } catch (NoConnectivityException e4) {
            function12.invoke(e4);
        }
    }

    public void unsubscribe(@NotNull Topic topic, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onFailure");
        try {
            checkNetworkConnectivity();
            if (this.subscriptions.containsKey(topic.getValue())) {
                RelayInterface.unsubscribe$default(this.relay, topic.getValue(), new SubscriptionId(String.valueOf(this.subscriptions.get(topic.getValue()))).getId(), (Long) null, new C0140g(this, topic, (Function0) function0, (Function1) function1), 4, (Object) null);
            }
        } catch (NoConnectivityException e3) {
            function1.invoke(e3);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object manageSubscriptions(com.reown.android.internal.common.json_rpc.domain.relay.Subscription r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$2
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$2 r0 = (com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$2 r0 = new com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$2
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0052
            if (r2 == r5) goto L_0x0045
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r9 = r0.L$1
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r9 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r9
            java.lang.Object r9 = r0.L$0
            com.reown.android.internal.common.json_rpc.domain.relay.Subscription r9 = (com.reown.android.internal.common.json_rpc.domain.relay.Subscription) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0131
        L_0x0038:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0040:
            java.lang.Object r8 = r0.L$1
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r8 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r8
            goto L_0x0049
        L_0x0045:
            java.lang.Object r8 = r0.L$1
            com.reown.android.internal.common.model.sync.ClientJsonRpc r8 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r8
        L_0x0049:
            java.lang.Object r8 = r0.L$0
            com.reown.android.internal.common.json_rpc.domain.relay.Subscription r8 = (com.reown.android.internal.common.json_rpc.domain.relay.Subscription) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x013a
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r10)
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r10 = r8.getSerializer()
            java.lang.String r2 = r9.getDecryptedMessage()
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0072 }
            com.squareup.moshi.Moshi r10 = r10.getMoshi()     // Catch:{ all -> 0x0072 }
            java.lang.Class<com.reown.android.internal.common.model.sync.ClientJsonRpc> r6 = com.reown.android.internal.common.model.sync.ClientJsonRpc.class
            com.squareup.moshi.JsonAdapter r10 = r10.adapter(r6)     // Catch:{ all -> 0x0072 }
            java.lang.Object r10 = r10.fromJson((java.lang.String) r2)     // Catch:{ all -> 0x0072 }
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)     // Catch:{ all -> 0x0072 }
            goto L_0x007d
        L_0x0072:
            r10 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)
        L_0x007d:
            boolean r2 = kotlin.Result.m8985isFailureimpl(r10)
            r6 = 0
            if (r2 == 0) goto L_0x0085
            r10 = r6
        L_0x0085:
            com.reown.android.internal.common.model.sync.ClientJsonRpc r10 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r10
            r2 = 0
            if (r10 == 0) goto L_0x009d
            r0.L$0 = r9
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r0.L$1 = r3
            r0.I$0 = r2
            r0.label = r5
            java.lang.Object r8 = r8.handleRequest(r10, r9, r0)
            if (r8 != r1) goto L_0x013a
            return r1
        L_0x009d:
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r10 = r8.getSerializer()
            java.lang.String r5 = r9.getDecryptedMessage()
            com.squareup.moshi.Moshi r10 = r10.getMoshi()     // Catch:{ all -> 0x00b8 }
            java.lang.Class<com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult> r7 = com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult.class
            com.squareup.moshi.JsonAdapter r10 = r10.adapter(r7)     // Catch:{ all -> 0x00b8 }
            java.lang.Object r10 = r10.fromJson((java.lang.String) r5)     // Catch:{ all -> 0x00b8 }
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)     // Catch:{ all -> 0x00b8 }
            goto L_0x00c3
        L_0x00b8:
            r10 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)
        L_0x00c3:
            boolean r5 = kotlin.Result.m8985isFailureimpl(r10)
            if (r5 == 0) goto L_0x00ca
            r10 = r6
        L_0x00ca:
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r10 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r10
            if (r10 == 0) goto L_0x00e9
            com.reown.foundation.common.model.Topic r3 = r9.getTopic()
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$0 = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r0.L$1 = r9
            r0.I$0 = r2
            r0.label = r4
            java.lang.Object r8 = r8.handleJsonRpcResult(r10, r3, r0)
            if (r8 != r1) goto L_0x013a
            return r1
        L_0x00e9:
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r10 = r8.getSerializer()
            java.lang.String r4 = r9.getDecryptedMessage()
            com.squareup.moshi.Moshi r10 = r10.getMoshi()     // Catch:{ all -> 0x0104 }
            java.lang.Class<com.reown.android.internal.common.JsonRpcResponse$JsonRpcError> r5 = com.reown.android.internal.common.JsonRpcResponse.JsonRpcError.class
            com.squareup.moshi.JsonAdapter r10 = r10.adapter(r5)     // Catch:{ all -> 0x0104 }
            java.lang.Object r10 = r10.fromJson((java.lang.String) r4)     // Catch:{ all -> 0x0104 }
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)     // Catch:{ all -> 0x0104 }
            goto L_0x010f
        L_0x0104:
            r10 = move-exception
            kotlin.Result$Companion r4 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)
        L_0x010f:
            boolean r4 = kotlin.Result.m8985isFailureimpl(r10)
            if (r4 == 0) goto L_0x0116
            r10 = r6
        L_0x0116:
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r10 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r10
            if (r10 == 0) goto L_0x0133
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$0 = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r0.L$1 = r9
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r9 = r8.handleJsonRpcError(r10, r0)
            if (r9 != r1) goto L_0x0131
            return r1
        L_0x0131:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
        L_0x0133:
            if (r6 != 0) goto L_0x013a
            java.lang.String r9 = "JsonRpcInteractor: Received unknown object type"
            r8.handleError(r9)
        L_0x013a:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor.manageSubscriptions(com.reown.android.internal.common.json_rpc.domain.relay.Subscription, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void respondWithParams(long j2, @NotNull Topic topic, @NotNull ClientParams clientParams, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Function0<Unit> function0) {
        Function1<? super Throwable, Unit> function12 = function1;
        Function0<Unit> function02 = function0;
        Topic topic2 = topic;
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(clientParams, "clientParams");
        Intrinsics.checkNotNullParameter(irnParams, "irnParams");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        Intrinsics.checkNotNullParameter(function02, "onSuccess");
        publishJsonRpcResponse(topic2, irnParams, new JsonRpcResponse.JsonRpcResult(j2, (String) null, clientParams, 2, (DefaultConstructorMarker) null), new b(function02, 0), new h(function12, 4), participants, envelopeType);
    }

    public void respondWithError(long j2, @NotNull Topic topic, @NotNull Error error, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Function0<Unit> function02 = function0;
        Function1<? super Throwable, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Intrinsics.checkNotNullParameter(irnParams, "irnParams");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        Intrinsics.checkNotNullParameter(function02, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        Logger logger2 = this.logger;
        String message = error.getMessage();
        int code = error.getCode();
        logger2.error("Responding with error: " + message + ": " + code);
        JsonRpcResponse.JsonRpcError jsonRpcError = new JsonRpcResponse.JsonRpcError(j2, (String) null, new JsonRpcResponse.Error(error.getCode(), error.getMessage()), 2, (DefaultConstructorMarker) null);
        try {
            publishJsonRpcResponse(topic, irnParams, jsonRpcError, new b(function02, 2), new e(function12, this, 1), participants, envelopeType);
        } catch (Exception e3) {
            handleError(c.a("publishFailure; ", ExceptionsKt.stackTraceToString(e3)));
        }
    }
}
