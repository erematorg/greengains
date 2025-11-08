package com.appsamurai.storyly.exoplayer2.core.scheduler;

import com.appsamurai.storyly.exoplayer2.core.scheduler.RequirementsWatcher;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4541a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RequirementsWatcher.NetworkCallback f4542b;

    public /* synthetic */ a(RequirementsWatcher.NetworkCallback networkCallback, int i3) {
        this.f4541a = i3;
        this.f4542b = networkCallback;
    }

    public final void run() {
        int i3 = this.f4541a;
        RequirementsWatcher.NetworkCallback networkCallback = this.f4542b;
        switch (i3) {
            case 0:
                networkCallback.lambda$postCheckRequirements$0();
                return;
            default:
                networkCallback.lambda$postRecheckNotMetNetworkRequirements$1();
                return;
        }
    }
}
