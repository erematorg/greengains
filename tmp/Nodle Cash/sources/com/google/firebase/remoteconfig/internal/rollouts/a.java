package com.google.firebase.remoteconfig.internal.rollouts;

import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7177a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RolloutsStateSubscriber f7178b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ RolloutsState f7179c;

    public /* synthetic */ a(RolloutsStateSubscriber rolloutsStateSubscriber, RolloutsState rolloutsState, int i3) {
        this.f7177a = i3;
        this.f7178b = rolloutsStateSubscriber;
        this.f7179c = rolloutsState;
    }

    public final void run() {
        switch (this.f7177a) {
            case 0:
                this.f7178b.onRolloutsStateChanged(this.f7179c);
                return;
            default:
                this.f7178b.onRolloutsStateChanged(this.f7179c);
                return;
        }
    }
}
