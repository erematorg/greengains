package com.reown.android;

import android.app.Application;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.reown.android.Core;
import com.reown.android.CoreInterface;
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

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u00015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jc\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00050\u0016H\u0001Jc\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00050\u0016H\u0001J\u0011\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0001R\u0014\u0010\u001c\u001a\u00020\u001d8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020!X\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0012\u0010$\u001a\u00020%X\u0005¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0012\u0010(\u001a\u00020)X\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0012\u0010,\u001a\u00020\u001dX\u0005¢\u0006\u0006\u001a\u0004\b-\u0010\u001fR\u0012\u0010.\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u000202X\u0005¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/reown/android/CoreClient;", "Lcom/reown/android/CoreInterface;", "<init>", "()V", "initialize", "", "application", "Landroid/app/Application;", "projectId", "", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "connectionType", "Lcom/reown/android/relay/ConnectionType;", "relay", "Lcom/reown/android/relay/RelayConnectionInterface;", "keyServerUrl", "networkClientTimeout", "Lcom/reown/android/relay/NetworkClientTimeout;", "telemetryEnabled", "", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "relayServerUrl", "setDelegate", "delegate", "Lcom/reown/android/CoreInterface$Delegate;", "Echo", "Lcom/reown/android/push/PushInterface;", "getEcho", "()Lcom/reown/android/push/PushInterface;", "Explorer", "Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "getExplorer", "()Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "Pairing", "Lcom/reown/android/pairing/client/PairingInterface;", "getPairing", "()Lcom/reown/android/pairing/client/PairingInterface;", "PairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "getPairingController", "()Lcom/reown/android/pairing/handler/PairingControllerInterface;", "Push", "getPush", "Relay", "getRelay", "()Lcom/reown/android/relay/RelayConnectionInterface;", "Verify", "Lcom/reown/android/verify/client/VerifyInterface;", "getVerify", "()Lcom/reown/android/verify/client/VerifyInterface;", "CoreDelegate", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreClient implements CoreInterface {
    @NotNull
    public static final CoreClient INSTANCE = new CoreClient();
    private final /* synthetic */ CoreProtocol $$delegate_0 = CoreProtocol.Companion.getInstance();

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Lcom/reown/android/CoreClient$CoreDelegate;", "Lcom/reown/android/CoreInterface$Delegate;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface CoreDelegate extends CoreInterface.Delegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated(message = "onPairingDelete callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingDelete(@NotNull CoreDelegate coreDelegate, @NotNull Core.Model.DeletedPairing deletedPairing) {
                Intrinsics.checkNotNullParameter(deletedPairing, "deletedPairing");
                CoreDelegate.super.onPairingDelete(deletedPairing);
            }

            @Deprecated(message = "onPairingExpired callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingExpired(@NotNull CoreDelegate coreDelegate, @NotNull Core.Model.ExpiredPairing expiredPairing) {
                Intrinsics.checkNotNullParameter(expiredPairing, "expiredPairing");
                CoreDelegate.super.onPairingExpired(expiredPairing);
            }

            @Deprecated
            public static void onPairingState(@NotNull CoreDelegate coreDelegate, @NotNull Core.Model.PairingState pairingState) {
                Intrinsics.checkNotNullParameter(pairingState, "pairingState");
                CoreDelegate.super.onPairingState(pairingState);
            }
        }
    }

    private CoreClient() {
    }

    @NotNull
    public PushInterface getEcho() {
        return this.$$delegate_0.getEcho();
    }

    @NotNull
    public ExplorerInterface getExplorer() {
        return this.$$delegate_0.getExplorer();
    }

    @NotNull
    public PairingInterface getPairing() {
        return this.$$delegate_0.getPairing();
    }

    @NotNull
    public PairingControllerInterface getPairingController() {
        return this.$$delegate_0.getPairingController();
    }

    @NotNull
    public PushInterface getPush() {
        return this.$$delegate_0.getPush();
    }

    @NotNull
    public RelayConnectionInterface getRelay() {
        return this.$$delegate_0.getRelay();
    }

    @NotNull
    public VerifyInterface getVerify() {
        return this.$$delegate_0.getVerify();
    }

    public void initialize(@NotNull Application application, @NotNull String str, @NotNull Core.Model.AppMetaData appMetaData, @NotNull ConnectionType connectionType, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(str, "projectId");
        Intrinsics.checkNotNullParameter(appMetaData, "metaData");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        Function1<? super Core.Model.Error, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.initialize(application, str, appMetaData, connectionType, relayConnectionInterface, str2, networkClientTimeout, z2, function12);
    }

    public void setDelegate(@NotNull CoreInterface.Delegate delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.$$delegate_0.setDelegate(delegate);
    }

    public void initialize(@NotNull Core.Model.AppMetaData appMetaData, @NotNull String str, @NotNull ConnectionType connectionType, @NotNull Application application, @Nullable RelayConnectionInterface relayConnectionInterface, @Nullable String str2, @Nullable NetworkClientTimeout networkClientTimeout, boolean z2, @NotNull Function1<? super Core.Model.Error, Unit> function1) {
        Intrinsics.checkNotNullParameter(appMetaData, "metaData");
        Intrinsics.checkNotNullParameter(str, "relayServerUrl");
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Function1<? super Core.Model.Error, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(function12, "onError");
        this.$$delegate_0.initialize(appMetaData, str, connectionType, application, relayConnectionInterface, str2, networkClientTimeout, z2, function12);
    }
}
