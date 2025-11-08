package com.reown.android.pairing.handler;

import I1.C0238b;
import com.reown.android.Core;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.SDKError;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0010H&J&\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u0017H&J&\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u0017H&J&\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u0017H&J!\u0010\u001e\u001a\u00020\u00102\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0 \"\u00020\fH&¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020\nH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R*\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000e¨\u0006%À\u0006\u0003"}, d2 = {"Lcom/reown/android/pairing/handler/PairingControllerInterface;", "", "findWrongMethodsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/reown/android/internal/common/model/SDKError;", "getFindWrongMethodsFlow", "()Lkotlinx/coroutines/flow/Flow;", "storedPairingFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/Topic;", "", "", "getStoredPairingFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "checkVerifyKeyFlow", "", "getCheckVerifyKeyFlow", "initialize", "setRequestReceived", "activate", "Lcom/reown/android/Core$Params$RequestReceived;", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "updateMetadata", "Lcom/reown/android/Core$Params$UpdateMetadata;", "deleteAndUnsubscribePairing", "deletePairing", "Lcom/reown/android/Core$Params$Delete;", "register", "method", "", "([Ljava/lang/String;)V", "getPairingByTopic", "Lcom/reown/android/internal/common/model/Pairing;", "topic", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PairingControllerInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void deleteAndUnsubscribePairing$default(PairingControllerInterface pairingControllerInterface, Core.Params.Delete delete, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new C0238b(20);
            }
            pairingControllerInterface.deleteAndUnsubscribePairing(delete, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteAndUnsubscribePairing");
    }

    /* access modifiers changed from: private */
    static Unit deleteAndUnsubscribePairing$lambda$2(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void setRequestReceived$default(PairingControllerInterface pairingControllerInterface, Core.Params.RequestReceived requestReceived, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new C0238b(19);
            }
            pairingControllerInterface.setRequestReceived(requestReceived, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setRequestReceived");
    }

    /* access modifiers changed from: private */
    static Unit setRequestReceived$lambda$0(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void updateMetadata$default(PairingControllerInterface pairingControllerInterface, Core.Params.UpdateMetadata updateMetadata, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new C0238b(18);
            }
            pairingControllerInterface.updateMetadata(updateMetadata, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateMetadata");
    }

    /* access modifiers changed from: private */
    static Unit updateMetadata$lambda$1(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    void deleteAndUnsubscribePairing(@NotNull Core.Params.Delete delete, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    @NotNull
    SharedFlow<Unit> getCheckVerifyKeyFlow();

    @NotNull
    Flow<SDKError> getFindWrongMethodsFlow();

    @Nullable
    Pairing getPairingByTopic(@NotNull Topic topic);

    @NotNull
    SharedFlow<Pair<Topic, List<String>>> getStoredPairingFlow();

    void initialize();

    void register(@NotNull String... strArr);

    void setRequestReceived(@NotNull Core.Params.RequestReceived requestReceived, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    void updateMetadata(@NotNull Core.Params.UpdateMetadata updateMetadata, @NotNull Function1<? super Core.Model.Error, Unit> function1);
}
