package com.reown.android.relay;

import S0.h;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.connection.ConnectivityState;
import com.reown.android.internal.common.connection.DefaultConnectionLifecycle;
import com.reown.android.internal.common.connection.ManualConnectionLifecycle;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.exception.MessagesKt;
import com.reown.android.relay.WSSConnectionState;
import com.reown.android.utils.ExtensionsKt;
import com.reown.foundation.network.BaseRelayClient;
import com.reown.foundation.network.data.service.RelayService;
import com.reown.foundation.network.model.Relay;
import com.reown.foundation.util.Logger;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010(\u001a\u00020)2\u0006\u0010!\u001a\u00020\"2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020)0+J\u001c\u0010-\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020)0+H\u0002J\b\u0010.\u001a\u00020)H\u0002J\u0010\u0010/\u001a\u00020)2\u0006\u00100\u001a\u000201H\u0002J\u001c\u00102\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020)0+H\u0016J\u001c\u00104\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020)0+H\u0016J\u001c\u00105\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020)0+H\u0016J0\u00102\u001a\u00020)2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020)0+2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020)0+H\u0017J0\u00104\u001a\u00020)2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020)0+2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020)0+H\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R#\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00188VX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u0017\u0010\u001aR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u000e\u0010!\u001a\u00020\"X.¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$8VX\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00068"}, d2 = {"Lcom/reown/android/relay/RelayClient;", "Lcom/reown/foundation/network/BaseRelayClient;", "Lcom/reown/android/relay/RelayConnectionInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "manualConnection", "Lcom/reown/android/internal/common/connection/ManualConnectionLifecycle;", "getManualConnection", "()Lcom/reown/android/internal/common/connection/ManualConnectionLifecycle;", "manualConnection$delegate", "Lkotlin/Lazy;", "defaultConnection", "Lcom/reown/android/internal/common/connection/DefaultConnectionLifecycle;", "getDefaultConnection", "()Lcom/reown/android/internal/common/connection/DefaultConnectionLifecycle;", "defaultConnection$delegate", "networkState", "Lcom/reown/android/internal/common/connection/ConnectivityState;", "getNetworkState", "()Lcom/reown/android/internal/common/connection/ConnectivityState;", "networkState$delegate", "isNetworkAvailable", "Lkotlinx/coroutines/flow/StateFlow;", "", "()Lkotlinx/coroutines/flow/StateFlow;", "isNetworkAvailable$delegate", "_wssConnectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/reown/android/relay/WSSConnectionState;", "wssConnectionState", "getWssConnectionState", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "onResubscribe", "Lkotlinx/coroutines/flow/Flow;", "", "getOnResubscribe", "()Lkotlinx/coroutines/flow/Flow;", "initialize", "", "onError", "Lkotlin/Function1;", "", "collectConnectionInitializationErrors", "monitorConnectionState", "setIsWSSConnectionOpened", "event", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "connect", "Lcom/reown/android/Core$Model$Error;", "disconnect", "restart", "onErrorModel", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRelayClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayClient.kt\ncom/reown/android/relay/RelayClient\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 Koin.kt\norg/koin/core/Koin\n+ 6 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,146:1\n17#2:147\n19#2:151\n46#3:148\n51#3:150\n105#4:149\n124#5,4:152\n124#5,4:157\n124#5,4:162\n124#5,4:167\n124#5,4:172\n142#6:156\n142#6:161\n142#6:166\n142#6:171\n142#6:176\n*S KotlinDebug\n*F\n+ 1 RelayClient.kt\ncom/reown/android/relay/RelayClient\n*L\n41#1:147\n41#1:151\n41#1:148\n41#1:150\n41#1:149\n48#1:152,4\n49#1:157,4\n31#1:162,4\n32#1:167,4\n33#1:172,4\n48#1:156\n49#1:161\n31#1:166\n32#1:171\n33#1:176\n*E\n"})
public final class RelayClient extends BaseRelayClient implements RelayConnectionInterface {
    @NotNull
    private final MutableStateFlow<WSSConnectionState> _wssConnectionState;
    private ConnectionType connectionType;
    @NotNull
    private final Lazy defaultConnection$delegate;
    @NotNull
    private final Lazy isNetworkAvailable$delegate;
    @NotNull
    private final KoinApplication koinApp;
    @NotNull
    private final Lazy manualConnection$delegate;
    @NotNull
    private final Lazy networkState$delegate;
    @NotNull
    private final StateFlow<WSSConnectionState> wssConnectionState;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.reown.android.relay.ConnectionType[] r0 = com.reown.android.relay.ConnectionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.reown.android.relay.ConnectionType r1 = com.reown.android.relay.ConnectionType.AUTOMATIC     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.reown.android.relay.ConnectionType r1 = com.reown.android.relay.ConnectionType.MANUAL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.android.relay.RelayClient.WhenMappings.<clinit>():void");
        }
    }

    public RelayClient() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    private final void collectConnectionInitializationErrors(Function1<? super Throwable, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RelayClient$collectConnectionInitializationErrors$1(this, function1, (Continuation<? super RelayClient$collectConnectionInitializationErrors$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final DefaultConnectionLifecycle defaultConnection_delegate$lambda$1(RelayClient relayClient) {
        Koin koin = relayClient.koinApp.getKoin();
        return (DefaultConnectionLifecycle) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(DefaultConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.DEFAULT_CONNECTION_LIFECYCLE), (Function0<? extends ParametersHolder>) null);
    }

    private final DefaultConnectionLifecycle getDefaultConnection() {
        return (DefaultConnectionLifecycle) this.defaultConnection$delegate.getValue();
    }

    private final ManualConnectionLifecycle getManualConnection() {
        return (ManualConnectionLifecycle) this.manualConnection$delegate.getValue();
    }

    private final ConnectivityState getNetworkState() {
        return (ConnectivityState) this.networkState$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Unit initialize$lambda$5(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final StateFlow isNetworkAvailable_delegate$lambda$3(RelayClient relayClient) {
        return relayClient.getNetworkState().isAvailable();
    }

    /* access modifiers changed from: private */
    public static final ManualConnectionLifecycle manualConnection_delegate$lambda$0(RelayClient relayClient) {
        Koin koin = relayClient.koinApp.getKoin();
        return (ManualConnectionLifecycle) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(ManualConnectionLifecycle.class), QualifierKt.named(AndroidCommonDITags.MANUAL_CONNECTION_LIFECYCLE), (Function0<? extends ParametersHolder>) null);
    }

    private final void monitorConnectionState() {
        FlowKt.launchIn(FlowKt.onEach(getEventsFlow(), new RelayClient$monitorConnectionState$1(this, (Continuation<? super RelayClient$monitorConnectionState$1>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public static final ConnectivityState networkState_delegate$lambda$2(RelayClient relayClient) {
        Koin koin = relayClient.koinApp.getKoin();
        return (ConnectivityState) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(ConnectivityState.class), QualifierKt.named(AndroidCommonDITags.CONNECTIVITY_STATE), (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public final void setIsWSSConnectionOpened(Relay.Model.Event event) {
        if ((event instanceof Relay.Model.Event.OnConnectionOpened) && (this._wssConnectionState.getValue() instanceof WSSConnectionState.Disconnected)) {
            this._wssConnectionState.setValue(WSSConnectionState.Connected.INSTANCE);
        } else if ((event instanceof Relay.Model.Event.OnConnectionFailed) && (this._wssConnectionState.getValue() instanceof WSSConnectionState.Connected)) {
            this._wssConnectionState.setValue(new WSSConnectionState.Disconnected.ConnectionFailed(ExtensionsKt.getToWalletConnectException(((Relay.Model.Event.OnConnectionFailed) event).getThrowable())));
        } else if ((event instanceof Relay.Model.Event.OnConnectionClosed) && (this._wssConnectionState.getValue() instanceof WSSConnectionState.Connected)) {
            MutableStateFlow<WSSConnectionState> mutableStateFlow = this._wssConnectionState;
            Relay.Model.Event.OnConnectionClosed onConnectionClosed = (Relay.Model.Event.OnConnectionClosed) event;
            String reason = onConnectionClosed.getShutdownReason().getReason();
            int code = onConnectionClosed.getShutdownReason().getCode();
            mutableStateFlow.setValue(new WSSConnectionState.Disconnected.ConnectionClosed("Connection closed: " + reason + StringUtils.SPACE + code));
        }
    }

    public void connect(@NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "onError");
        try {
            ConnectionType connectionType2 = this.connectionType;
            if (connectionType2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionType");
                connectionType2 = null;
            }
            int i3 = WhenMappings.$EnumSwitchMapping$0[connectionType2.ordinal()];
            if (i3 == 1) {
                function1.invoke(new Core.Model.Error(new IllegalStateException(MessagesKt.WRONG_CONNECTION_TYPE)));
            } else if (i3 == 2) {
                getManualConnection().connect();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    public void disconnect(@NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "onError");
        try {
            ConnectionType connectionType2 = this.connectionType;
            if (connectionType2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionType");
                connectionType2 = null;
            }
            int i3 = WhenMappings.$EnumSwitchMapping$0[connectionType2.ordinal()];
            if (i3 == 1) {
                function1.invoke(new Core.Model.Error(new IllegalStateException(MessagesKt.WRONG_CONNECTION_TYPE)));
            } else if (i3 == 2) {
                getManualConnection().disconnect();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    @NotNull
    public Flow<Object> getOnResubscribe() {
        return FlowKt.merge((Flow<? extends T>[]) new Flow[]{new RelayClient$special$$inlined$filter$1(getConnectionLifecycle().getOnResume()), FlowKt.filterIsInstance(getWssConnectionState(), Reflection.getOrCreateKotlinClass(WSSConnectionState.Connected.class))});
    }

    @NotNull
    public StateFlow<WSSConnectionState> getWssConnectionState() {
        return this.wssConnectionState;
    }

    public final /* synthetic */ void initialize(ConnectionType connectionType2, Function1 function1) {
        Intrinsics.checkNotNullParameter(connectionType2, "connectionType");
        Intrinsics.checkNotNullParameter(function1, "onError");
        this.connectionType = connectionType2;
        setLogger((Logger) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
        setRelayService((RelayService) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(RelayService.class), QualifierKt.named(AndroidCommonDITags.RELAY_SERVICE), (Function0<? extends ParametersHolder>) null));
        setConnectionLifecycle(connectionType2 == ConnectionType.MANUAL ? getManualConnection() : getDefaultConnection());
        collectConnectionInitializationErrors(new h(function1, 12));
        monitorConnectionState();
        observeResults();
    }

    @NotNull
    public StateFlow<Boolean> isNetworkAvailable() {
        return (StateFlow) this.isNetworkAvailable$delegate.getValue();
    }

    public void restart(@NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "onError");
        try {
            ConnectionType connectionType2 = this.connectionType;
            if (connectionType2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionType");
                connectionType2 = null;
            }
            int i3 = WhenMappings.$EnumSwitchMapping$0[connectionType2.ordinal()];
            if (i3 == 1) {
                function1.invoke(new Core.Model.Error(new IllegalStateException(MessagesKt.WRONG_CONNECTION_TYPE)));
            } else if (i3 == 2) {
                getManualConnection().reconnect();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    public RelayClient(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
        this.manualConnection$delegate = LazyKt.lazy(new a(this, 0));
        this.defaultConnection$delegate = LazyKt.lazy(new a(this, 1));
        this.networkState$delegate = LazyKt.lazy(new a(this, 2));
        this.isNetworkAvailable$delegate = LazyKt.lazy(new a(this, 3));
        MutableStateFlow<WSSConnectionState> MutableStateFlow = StateFlowKt.MutableStateFlow(new WSSConnectionState.Disconnected.ConnectionClosed((String) null, 1, (DefaultConstructorMarker) null));
        this._wssConnectionState = MutableStateFlow;
        this.wssConnectionState = MutableStateFlow;
    }

    @Deprecated(message = "This has become deprecate in favor of the onError returning Core.Model.Error", replaceWith = @ReplaceWith(expression = "this.connect(onErrorModel)", imports = {}))
    public void connect(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull Function1<? super String, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "onErrorModel");
        Intrinsics.checkNotNullParameter(function12, "onError");
        ConnectionType connectionType2 = this.connectionType;
        if (connectionType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionType");
            connectionType2 = null;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[connectionType2.ordinal()];
        if (i3 == 1) {
            function12.invoke(MessagesKt.WRONG_CONNECTION_TYPE);
        } else if (i3 == 2) {
            getManualConnection().connect();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @Deprecated(message = "This has become deprecate in favor of the onError returning Core.Model.Error", replaceWith = @ReplaceWith(expression = "this.disconnect(onErrorModel)", imports = {}))
    public void disconnect(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull Function1<? super String, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "onErrorModel");
        Intrinsics.checkNotNullParameter(function12, "onError");
        ConnectionType connectionType2 = this.connectionType;
        if (connectionType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionType");
            connectionType2 = null;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[connectionType2.ordinal()];
        if (i3 == 1) {
            function12.invoke(MessagesKt.WRONG_CONNECTION_TYPE);
        } else if (i3 == 2) {
            getManualConnection().disconnect();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RelayClient(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }
}
