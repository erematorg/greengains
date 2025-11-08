package com.reown.android.pairing.client;

import S0.h;
import androidx.browser.trusted.c;
import androidx.compose.runtime.e;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.internal.Validator;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.engine.domain.PairingEngine;
import com.reown.android.pairing.model.mapper.PairingMapperKt;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.foundation.util.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00190 H\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00190 2\u0006\u0010\"\u001a\u00020#H\u0016J8\u0010$\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00190 2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00190 H\u0016J$\u0010'\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00190 H\u0017J$\u0010'\u001a\u00020\u00192\u0006\u0010)\u001a\u00020#2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00190 H\u0017J\u001a\u0010*\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0017J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001e0/H\u0016J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020#H\u0016J\b\u00103\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\u0016¨\u00064"}, d2 = {"Lcom/reown/android/pairing/client/PairingProtocol;", "Lcom/reown/android/pairing/client/PairingInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "pairingEngine", "Lcom/reown/android/pairing/engine/domain/PairingEngine;", "logger", "Lcom/reown/foundation/util/Logger;", "getLogger", "()Lcom/reown/foundation/util/Logger;", "logger$delegate", "Lkotlin/Lazy;", "relayClient", "Lcom/reown/android/relay/RelayConnectionInterface;", "getRelayClient", "()Lcom/reown/android/relay/RelayConnectionInterface;", "relayClient$delegate", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "getInsertEventUseCase", "()Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "insertEventUseCase$delegate", "initialize", "", "setDelegate", "delegate", "Lcom/reown/android/pairing/client/PairingInterface$Delegate;", "create", "Lcom/reown/android/Core$Model$Pairing;", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "methods", "", "pair", "Lcom/reown/android/Core$Params$Pair;", "onSuccess", "disconnect", "Lcom/reown/android/Core$Params$Disconnect;", "topic", "ping", "Lcom/reown/android/Core$Params$Ping;", "pairingPing", "Lcom/reown/android/Core$Listeners$PairingPing;", "getPairings", "", "validatePairingUri", "", "uri", "checkEngineInitialization", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPairingProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingProtocol.kt\ncom/reown/android/pairing/client/PairingProtocol\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,141:1\n124#2,4:142\n124#2,4:151\n124#2,4:156\n124#2,4:161\n142#3:146\n142#3:155\n142#3:160\n142#3:165\n1563#4:147\n1634#4,3:148\n*S KotlinDebug\n*F\n+ 1 PairingProtocol.kt\ncom/reown/android/pairing/client/PairingProtocol\n*L\n25#1:142,4\n20#1:151,4\n21#1:156,4\n22#1:161,4\n25#1:146\n20#1:155\n21#1:160\n22#1:165\n124#1:147\n124#1:148,3\n*E\n"})
public final class PairingProtocol implements PairingInterface {
    @NotNull
    private final Lazy insertEventUseCase$delegate;
    @NotNull
    private final KoinApplication koinApp;
    @NotNull
    private final Lazy logger$delegate;
    private PairingEngine pairingEngine;
    @NotNull
    private final Lazy relayClient$delegate;

    public PairingProtocol() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    private final void checkEngineInitialization() throws IllegalStateException {
        if (this.pairingEngine == null) {
            throw new IllegalStateException("CoreClient needs to be initialized first using the initialize function");
        }
    }

    /* access modifiers changed from: private */
    public static final Unit create$lambda$3(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit create$lambda$4(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit disconnect$lambda$7(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit disconnect$lambda$8(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    private final InsertTelemetryEventUseCase getInsertEventUseCase() {
        return (InsertTelemetryEventUseCase) this.insertEventUseCase$delegate.getValue();
    }

    private final Logger getLogger() {
        return (Logger) this.logger$delegate.getValue();
    }

    private final RelayConnectionInterface getRelayClient() {
        return (RelayConnectionInterface) this.relayClient$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final InsertTelemetryEventUseCase insertEventUseCase_delegate$lambda$2(PairingProtocol pairingProtocol) {
        return (InsertTelemetryEventUseCase) pairingProtocol.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0(PairingProtocol pairingProtocol) {
        return (Logger) pairingProtocol.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Logger.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$5(Function1 function1, Core.Params.Pair pair) {
        function1.invoke(pair);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit pair$lambda$6(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(new Throwable(c.a("Pairing error: ", th.getMessage()))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit ping$lambda$10(Core.Listeners.PairingPing pairingPing, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (pairingPing != null) {
            pairingPing.onError(new Core.Model.Ping.Error(th));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit ping$lambda$9(Core.Listeners.PairingPing pairingPing, String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        if (pairingPing != null) {
            pairingPing.onSuccess(new Core.Model.Ping.Success(str));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final RelayConnectionInterface relayClient_delegate$lambda$1(PairingProtocol pairingProtocol) {
        return (RelayConnectionInterface) pairingProtocol.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(RelayConnectionInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    @Nullable
    public Core.Model.Pairing create(@NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            return PairingEngine.create$default(pairingEngine2, new h(function1, 9), (String) null, 2, (Object) null);
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
            return null;
        }
    }

    @Deprecated(message = "Disconnect method has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
    public void disconnect(@NotNull Core.Params.Disconnect disconnect, @NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(disconnect, "disconnect");
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.disconnect(disconnect.getTopic(), new h(function1, 6));
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    @NotNull
    public List<Core.Model.Pairing> getPairings() throws IllegalStateException {
        checkEngineInitialization();
        PairingEngine pairingEngine2 = this.pairingEngine;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        Iterable<Pairing> pairings = pairingEngine2.getPairings();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(pairings, 10));
        for (Pairing core : pairings) {
            arrayList.add(PairingMapperKt.toCore(core));
        }
        return arrayList;
    }

    public void initialize() {
        this.pairingEngine = (PairingEngine) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PairingEngine.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    public void pair(@NotNull Core.Params.Pair pair, @NotNull Function1<? super Core.Params.Pair, Unit> function1, @NotNull Function1<? super Core.Model.Error, Unit> function12) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(pair, "pair");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.pair(pair.getUri(), new e(function1, pair, 4), new h(function12, 7));
        } catch (Exception e3) {
            function12.invoke(new Core.Model.Error(e3));
        }
    }

    @Deprecated(message = "Ping method has been deprecated. It will be removed soon. Please use Ping from WalletKit or Sign clients.")
    public void ping(@NotNull Core.Params.Ping ping, @Nullable Core.Listeners.PairingPing pairingPing) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(ping, "ping");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.ping(ping.getTopic(), new b(pairingPing, 0), new b(pairingPing, 1));
        } catch (Exception e3) {
            if (pairingPing != null) {
                pairingPing.onError(new Core.Model.Ping.Error(e3));
            }
        }
    }

    public void setDelegate(@NotNull PairingInterface.Delegate delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        checkEngineInitialization();
        PairingEngine pairingEngine2 = this.pairingEngine;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        FlowKt.launchIn(FlowKt.onEach(pairingEngine2.getEngineEvent(), new PairingProtocol$setDelegate$1(delegate, (Continuation<? super PairingProtocol$setDelegate$1>) null)), WalletConnectScopeKt.getScope());
    }

    public boolean validatePairingUri(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        try {
            return Validator.INSTANCE.validateWCUri$android_release(str) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public PairingProtocol(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
        this.logger$delegate = LazyKt.lazy(new a(this, 0));
        this.relayClient$delegate = LazyKt.lazy(new a(this, 1));
        this.insertEventUseCase$delegate = LazyKt.lazy(new a(this, 2));
    }

    @Nullable
    public Core.Model.Pairing create(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull String str) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(function1, "onError");
        Intrinsics.checkNotNullParameter(str, "methods");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            return pairingEngine2.create(new h(function1, 10), str);
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
            return null;
        }
    }

    @Deprecated(message = "Disconnect method has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
    public void disconnect(@NotNull String str, @NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.disconnect(str, new h(function1, 8));
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PairingProtocol(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }
}
