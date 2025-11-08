package com.reown.android.internal.common.connection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004*\u0001\u0012\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0014\u001a\u00020\n*\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/reown/android/internal/common/connection/ConnectivityState;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "_isAvailable", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isAvailable", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "networks", "", "Landroid/net/Network;", "callback", "com/reown/android/internal/common/connection/ConnectivityState$callback$1", "Lcom/reown/android/internal/common/connection/ConnectivityState$callback$1;", "isCapable", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"MissingPermission"})
public final class ConnectivityState {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableStateFlow<Boolean> _isAvailable;
    @NotNull
    private final ConnectivityState$callback$1 callback;
    @NotNull
    private final ConnectivityManager connectivityManager;
    @NotNull
    private final StateFlow<Boolean> isAvailable;
    /* access modifiers changed from: private */
    @NotNull
    public final Set<Network> networks = new LinkedHashSet();

    public ConnectivityState(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager2 = (ConnectivityManager) systemService;
        this.connectivityManager = connectivityManager2;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._isAvailable = MutableStateFlow;
        this.isAvailable = FlowKt.asStateFlow(MutableStateFlow);
        ConnectivityState$callback$1 connectivityState$callback$1 = new ConnectivityState$callback$1(this);
        this.callback = connectivityState$callback$1;
        try {
            connectivityManager2.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).addCapability(16).addTransportType(0).addTransportType(1).build(), connectivityState$callback$1);
        } catch (Exception e3) {
            Timber.Forest.e(e3, "Failed to register network callback", new Object[0]);
            this._isAvailable.setValue(Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public final boolean isCapable(Network network) {
        try {
            NetworkCapabilities networkCapabilities = this.connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16) && (networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(1));
        } catch (Throwable th) {
            Timber.Forest.e(th, "Failed to check network capabilities", new Object[0]);
            return true;
        }
    }

    @NotNull
    public final StateFlow<Boolean> isAvailable() {
        return this.isAvailable;
    }
}
