package com.reown.walletkit.client;

import com.reown.sign.client.Sign;
import com.reown.walletkit.client.Wallet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/reown/walletkit/client/WalletKit$pingSession$signPingLister$1", "Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "onSuccess", "", "pingSuccess", "Lcom/reown/sign/client/Sign$Model$Ping$Success;", "onError", "pingError", "Lcom/reown/sign/client/Sign$Model$Ping$Error;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletKit$pingSession$signPingLister$1 implements Sign.Listeners.SessionPing {
    final /* synthetic */ Wallet.Listeners.SessionPing $sessionPing;

    public WalletKit$pingSession$signPingLister$1(Wallet.Listeners.SessionPing sessionPing) {
        this.$sessionPing = sessionPing;
    }

    public void onError(Sign.Model.Ping.Error error) {
        Intrinsics.checkNotNullParameter(error, "pingError");
        Wallet.Listeners.SessionPing sessionPing = this.$sessionPing;
        if (sessionPing != null) {
            sessionPing.onError(new Wallet.Model.Ping.Error(error.getError()));
        }
    }

    public void onSuccess(Sign.Model.Ping.Success success) {
        Intrinsics.checkNotNullParameter(success, "pingSuccess");
        Wallet.Listeners.SessionPing sessionPing = this.$sessionPing;
        if (sessionPing != null) {
            sessionPing.onSuccess(new Wallet.Model.Ping.Success(success.getTopic()));
        }
    }
}
