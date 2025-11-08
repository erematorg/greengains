package com.google.android.gms.common.api.internal;

final class zabn implements Runnable {
    final /* synthetic */ int zaa;
    final /* synthetic */ zabq zab;

    public zabn(zabq zabq, int i3) {
        this.zab = zabq;
        this.zaa = i3;
    }

    public final void run() {
        this.zab.zaI(this.zaa);
    }
}
