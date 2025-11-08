package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4485a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4486b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f4485a = i3;
        this.f4486b = obj;
    }

    public final void run() {
        int i3 = this.f4485a;
        Object obj = this.f4486b;
        switch (i3) {
            case 0:
                ((DefaultDrmSessionManager.PreacquiredSessionReference) obj).lambda$release$1();
                return;
            default:
                ((DefaultDrmSession) obj).release((DrmSessionEventListener.EventDispatcher) null);
                return;
        }
    }
}
