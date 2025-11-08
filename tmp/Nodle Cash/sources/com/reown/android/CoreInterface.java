package com.reown.android;

import android.app.Application;
import com.reown.android.Core;
import com.reown.android.internal.common.explorer.ExplorerInterface;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.push.PushInterface;
import com.reown.android.relay.ConnectionType;
import com.reown.android.relay.NetworkClientTimeout;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.android.verify.client.VerifyInterface;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u00015J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H&Jl\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\b\b\u0002\u0010/\u001a\u0002002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u001f02H&Jl\u0010\"\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0006\u00104\u001a\u00020&2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020(2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\b\b\u0002\u0010/\u001a\u0002002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u001f02H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00066À\u0006\u0003"}, d2 = {"Lcom/reown/android/CoreInterface;", "", "Pairing", "Lcom/reown/android/pairing/client/PairingInterface;", "getPairing", "()Lcom/reown/android/pairing/client/PairingInterface;", "PairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "getPairingController", "()Lcom/reown/android/pairing/handler/PairingControllerInterface;", "Relay", "Lcom/reown/android/relay/RelayConnectionInterface;", "getRelay", "()Lcom/reown/android/relay/RelayConnectionInterface;", "Echo", "Lcom/reown/android/push/PushInterface;", "getEcho$annotations", "()V", "getEcho", "()Lcom/reown/android/push/PushInterface;", "Push", "getPush", "Verify", "Lcom/reown/android/verify/client/VerifyInterface;", "getVerify", "()Lcom/reown/android/verify/client/VerifyInterface;", "Explorer", "Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "getExplorer", "()Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "setDelegate", "", "delegate", "Lcom/reown/android/CoreInterface$Delegate;", "initialize", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "relayServerUrl", "", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "application", "Landroid/app/Application;", "relay", "keyServerUrl", "networkClientTimeout", "Lcom/reown/android/relay/NetworkClientTimeout;", "telemetryEnabled", "", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "projectId", "Delegate", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CoreInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated(message = "Replaced with Push")
        public static /* synthetic */ void getEcho$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Lcom/reown/android/CoreInterface$Delegate;", "Lcom/reown/android/pairing/client/PairingInterface$Delegate;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Delegate extends PairingInterface.Delegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated(message = "onPairingDelete callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingDelete(@NotNull Delegate delegate, @NotNull Core.Model.DeletedPairing deletedPairing) {
                Intrinsics.checkNotNullParameter(deletedPairing, "deletedPairing");
                Delegate.super.onPairingDelete(deletedPairing);
            }

            @Deprecated(message = "onPairingExpired callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingExpired(@NotNull Delegate delegate, @NotNull Core.Model.ExpiredPairing expiredPairing) {
                Intrinsics.checkNotNullParameter(expiredPairing, "expiredPairing");
                Delegate.super.onPairingExpired(expiredPairing);
            }

            @Deprecated
            public static void onPairingState(@NotNull Delegate delegate, @NotNull Core.Model.PairingState pairingState) {
                Intrinsics.checkNotNullParameter(pairingState, "pairingState");
                Delegate.super.onPairingState(pairingState);
            }
        }
    }

    static /* synthetic */ void initialize$default(CoreInterface coreInterface, Core.Model.AppMetaData appMetaData, String str, ConnectionType connectionType, Application application, RelayConnectionInterface relayConnectionInterface, String str2, NetworkClientTimeout networkClientTimeout, boolean z2, Function1 function1, int i3, Object obj) {
        int i4 = i3;
        if (obj == null) {
            coreInterface.initialize(appMetaData, str, (i4 & 4) != 0 ? ConnectionType.AUTOMATIC : connectionType, application, (i4 & 16) != 0 ? null : relayConnectionInterface, (i4 & 32) != 0 ? null : str2, (i4 & 64) != 0 ? null : networkClientTimeout, (i4 & 128) != 0 ? true : z2, (Function1<? super Core.Model.Error, Unit>) function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initialize");
    }

    @NotNull
    PushInterface getEcho();

    @NotNull
    ExplorerInterface getExplorer();

    @NotNull
    PairingInterface getPairing();

    @NotNull
    PairingControllerInterface getPairingController();

    @NotNull
    PushInterface getPush();

    @NotNull
    RelayConnectionInterface getRelay();

    @NotNull
    VerifyInterface getVerify();

    void initialize(@NotNull Application application, @NotNull String str, @NotNull Core.Model.AppMetaData appMetaData, @NotNull ConnectionType connectionType, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    void initialize(@NotNull Core.Model.AppMetaData appMetaData, @NotNull String str, @NotNull ConnectionType connectionType, @NotNull Application application, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    void setDelegate(@NotNull Delegate delegate);

    static /* synthetic */ void initialize$default(CoreInterface coreInterface, Application application, String str, Core.Model.AppMetaData appMetaData, ConnectionType connectionType, RelayConnectionInterface relayConnectionInterface, String str2, NetworkClientTimeout networkClientTimeout, boolean z2, Function1 function1, int i3, Object obj) {
        int i4 = i3;
        if (obj == null) {
            coreInterface.initialize(application, str, appMetaData, (i4 & 8) != 0 ? ConnectionType.AUTOMATIC : connectionType, (i4 & 16) != 0 ? null : relayConnectionInterface, (i4 & 32) != 0 ? null : str2, (i4 & 64) != 0 ? null : networkClientTimeout, (i4 & 128) != 0 ? true : z2, (Function1<? super Core.Model.Error, Unit>) function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initialize");
    }
}
