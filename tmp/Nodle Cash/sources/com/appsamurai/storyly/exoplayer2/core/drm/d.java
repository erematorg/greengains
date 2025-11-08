package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;

public final /* synthetic */ class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4487a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4488b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4489c;

    public /* synthetic */ d(Object obj, Object obj2, int i3) {
        this.f4487a = i3;
        this.f4488b = obj;
        this.f4489c = obj2;
    }

    public final void run() {
        switch (this.f4487a) {
            case 0:
                ((DrmSessionEventListener.EventDispatcher) this.f4488b).lambda$drmSessionReleased$5((DrmSessionEventListener) this.f4489c);
                return;
            case 1:
                ((DrmSessionEventListener.EventDispatcher) this.f4488b).lambda$drmKeysRemoved$4((DrmSessionEventListener) this.f4489c);
                return;
            case 2:
                ((DrmSessionEventListener.EventDispatcher) this.f4488b).lambda$drmKeysLoaded$1((DrmSessionEventListener) this.f4489c);
                return;
            case 3:
                ((DrmSessionEventListener.EventDispatcher) this.f4488b).lambda$drmKeysRestored$3((DrmSessionEventListener) this.f4489c);
                return;
            default:
                ((DefaultDrmSessionManager.PreacquiredSessionReference) this.f4488b).lambda$acquire$0((Format) this.f4489c);
                return;
        }
    }
}
