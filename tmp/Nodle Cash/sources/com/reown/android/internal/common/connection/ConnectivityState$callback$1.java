package com.reown.android.internal.common.connection;

import android.net.ConnectivityManager;
import android.net.Network;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/reown/android/internal/common/connection/ConnectivityState$callback$1", "Landroid/net/ConnectivityManager$NetworkCallback;", "onAvailable", "", "network", "Landroid/net/Network;", "onLost", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConnectivityState$callback$1 extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ ConnectivityState this$0;

    public ConnectivityState$callback$1(ConnectivityState connectivityState) {
        this.this$0 = connectivityState;
    }

    public void onAvailable(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        if (this.this$0.isCapable(network)) {
            this.this$0.networks.add(network);
            this.this$0._isAvailable.setValue(Boolean.TRUE);
            return;
        }
        this.this$0._isAvailable.setValue(Boolean.FALSE);
    }

    public void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        this.this$0.networks.remove(network);
        this.this$0._isAvailable.setValue(Boolean.valueOf(!this.this$0.networks.isEmpty()));
    }
}
