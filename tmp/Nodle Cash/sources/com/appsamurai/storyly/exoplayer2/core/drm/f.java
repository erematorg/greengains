package com.appsamurai.storyly.exoplayer2.core.drm;

import android.media.MediaDrm;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;

public final /* synthetic */ class f implements MediaDrm.OnEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FrameworkMediaDrm f4492a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ExoMediaDrm.OnEventListener f4493b;

    public /* synthetic */ f(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnEventListener onEventListener) {
        this.f4492a = frameworkMediaDrm;
        this.f4493b = onEventListener;
    }

    public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i3, int i4, byte[] bArr2) {
        this.f4492a.lambda$setOnEventListener$1(this.f4493b, mediaDrm, bArr, i3, i4, bArr2);
    }
}
