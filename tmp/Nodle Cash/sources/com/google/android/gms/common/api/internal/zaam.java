package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.errorprone.annotations.concurrent.GuardedBy;

final class zaam extends zabg {
    final /* synthetic */ ConnectionResult zaa;
    final /* synthetic */ zaao zab;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaam(zaao zaao, zabf zabf, ConnectionResult connectionResult) {
        super(zabf);
        this.zab = zaao;
        this.zaa = connectionResult;
    }

    @GuardedBy("lock")
    public final void zaa() {
        this.zab.zaa.zaD(this.zaa);
    }
}
