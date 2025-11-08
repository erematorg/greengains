package com.google.android.play.integrity.internal;

import android.os.IBinder;

public final /* synthetic */ class t implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ac f6841a;

    public /* synthetic */ t(ac acVar) {
        this.f6841a = acVar;
    }

    public final void binderDied() {
        ac.k(this.f6841a);
    }
}
