package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.common.util.Consumer;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;

public final /* synthetic */ class a implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Exception f4483a;

    public /* synthetic */ a(Exception exc) {
        this.f4483a = exc;
    }

    public final void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmSessionManagerError(this.f4483a);
    }
}
