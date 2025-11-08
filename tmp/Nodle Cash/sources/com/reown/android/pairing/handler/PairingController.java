package com.reown.android.pairing.handler;

import S0.h;
import Y0.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.reown.android.Core;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.pairing.engine.domain.PairingEngine;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u001c\u001a\u00020\u0019H\u0016J!\u0010\u001d\u001a\u00020\u00192\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001f\"\u00020\u0014H\u0016¢\u0006\u0002\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0012H\u0016J$\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00190(H\u0016J$\u0010*\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00190(H\u0016J$\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020.2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00190(H\u0016J\b\u0010/\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR3\u0010\u000f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00110\u00108VX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00108VX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001a\u0010\u0016¨\u00060"}, d2 = {"Lcom/reown/android/pairing/handler/PairingController;", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "pairingEngine", "Lcom/reown/android/pairing/engine/domain/PairingEngine;", "findWrongMethodsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/reown/android/internal/common/model/SDKError;", "getFindWrongMethodsFlow", "()Lkotlinx/coroutines/flow/Flow;", "findWrongMethodsFlow$delegate", "Lkotlin/Lazy;", "storedPairingFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/Topic;", "", "", "getStoredPairingFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "storedPairingFlow$delegate", "checkVerifyKeyFlow", "", "getCheckVerifyKeyFlow", "checkVerifyKeyFlow$delegate", "initialize", "register", "method", "", "([Ljava/lang/String;)V", "getPairingByTopic", "Lcom/reown/android/internal/common/model/Pairing;", "topic", "setRequestReceived", "activate", "Lcom/reown/android/Core$Params$RequestReceived;", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "updateMetadata", "Lcom/reown/android/Core$Params$UpdateMetadata;", "deleteAndUnsubscribePairing", "deletePairing", "Lcom/reown/android/Core$Params$Delete;", "checkEngineInitialization", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPairingController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingController.kt\ncom/reown/android/pairing/handler/PairingController\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,82:1\n124#2,4:83\n142#3:87\n*S KotlinDebug\n*F\n+ 1 PairingController.kt\ncom/reown/android/pairing/handler/PairingController\n*L\n22#1:83,4\n22#1:87\n*E\n"})
public final class PairingController implements PairingControllerInterface {
    @NotNull
    private final Lazy checkVerifyKeyFlow$delegate;
    @NotNull
    private final Lazy findWrongMethodsFlow$delegate;
    @NotNull
    private final KoinApplication koinApp;
    private PairingEngine pairingEngine;
    @NotNull
    private final Lazy storedPairingFlow$delegate;

    public PairingController() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    private final void checkEngineInitialization() throws IllegalStateException {
        if (this.pairingEngine == null) {
            throw new IllegalStateException("CoreClient needs to be initialized first using the initialize function");
        }
    }

    /* access modifiers changed from: private */
    public static final SharedFlow checkVerifyKeyFlow_delegate$lambda$2(PairingController pairingController) {
        PairingEngine pairingEngine2 = pairingController.pairingEngine;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        return pairingEngine2.getCheckVerifyKeyFlow();
    }

    /* access modifiers changed from: private */
    public static final Flow findWrongMethodsFlow_delegate$lambda$0(PairingController pairingController) {
        PairingEngine pairingEngine2 = pairingController.pairingEngine;
        PairingEngine pairingEngine3 = null;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        MutableSharedFlow<SDKError> internalErrorFlow = pairingEngine2.getInternalErrorFlow();
        PairingEngine pairingEngine4 = pairingController.pairingEngine;
        if (pairingEngine4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
        } else {
            pairingEngine3 = pairingEngine4;
        }
        return FlowKt.merge((Flow<? extends T>[]) new Flow[]{internalErrorFlow, pairingEngine3.getJsonRpcErrorFlow()});
    }

    /* access modifiers changed from: private */
    public static final Unit setRequestReceived$lambda$3(Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        function1.invoke(new Core.Model.Error(th));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final SharedFlow storedPairingFlow_delegate$lambda$1(PairingController pairingController) {
        PairingEngine pairingEngine2 = pairingController.pairingEngine;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        return pairingEngine2.getStoredPairingTopicFlow();
    }

    public void deleteAndUnsubscribePairing(@NotNull Core.Params.Delete delete, @NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(delete, "deletePairing");
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.deleteAndUnsubscribePairing(delete.getTopic());
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    @NotNull
    public SharedFlow<Unit> getCheckVerifyKeyFlow() {
        return (SharedFlow) this.checkVerifyKeyFlow$delegate.getValue();
    }

    @NotNull
    public Flow<SDKError> getFindWrongMethodsFlow() {
        return (Flow) this.findWrongMethodsFlow$delegate.getValue();
    }

    @Nullable
    public Pairing getPairingByTopic(@NotNull Topic topic) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            return pairingEngine2.getPairingByTopic(topic);
        } catch (Exception unused) {
            return null;
        }
    }

    @NotNull
    public SharedFlow<Pair<Topic, List<String>>> getStoredPairingFlow() {
        return (SharedFlow) this.storedPairingFlow$delegate.getValue();
    }

    public void initialize() {
        this.pairingEngine = (PairingEngine) this.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PairingEngine.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    public void register(@NotNull String... strArr) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(strArr, FirebaseAnalytics.Param.METHOD);
        checkEngineInitialization();
        PairingEngine pairingEngine2 = this.pairingEngine;
        if (pairingEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
            pairingEngine2 = null;
        }
        pairingEngine2.register((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void setRequestReceived(@NotNull Core.Params.RequestReceived requestReceived, @NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(requestReceived, RemoteConfigComponent.ACTIVATE_FILE_NAME);
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.setRequestReceived(requestReceived.getTopic(), new h(function1, 1));
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    public void updateMetadata(@NotNull Core.Params.UpdateMetadata updateMetadata, @NotNull Function1<? super Core.Model.Error, Unit> function1) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(updateMetadata, "updateMetadata");
        Intrinsics.checkNotNullParameter(function1, "onError");
        checkEngineInitialization();
        try {
            PairingEngine pairingEngine2 = this.pairingEngine;
            if (pairingEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pairingEngine");
                pairingEngine2 = null;
            }
            pairingEngine2.updateMetadata(updateMetadata.getTopic(), Intrinsics.checkNotNullParameter(updateMetadata.getMetadata(), "<this>"), updateMetadata.getMetaDataType());
        } catch (Exception e3) {
            function1.invoke(new Core.Model.Error(e3));
        }
    }

    public PairingController(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
        this.findWrongMethodsFlow$delegate = LazyKt.lazy(new a(this, 0));
        this.storedPairingFlow$delegate = LazyKt.lazy(new a(this, 1));
        this.checkVerifyKeyFlow$delegate = LazyKt.lazy(new a(this, 2));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PairingController(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }
}
