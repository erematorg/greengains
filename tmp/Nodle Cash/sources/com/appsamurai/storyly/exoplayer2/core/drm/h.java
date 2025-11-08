package com.appsamurai.storyly.exoplayer2.core.drm;

import android.media.MediaDrm;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;

public final /* synthetic */ class h implements MediaDrm.OnExpirationUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FrameworkMediaDrm f4494a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ExoMediaDrm.OnExpirationUpdateListener f4495b;

    public /* synthetic */ h(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener) {
        this.f4494a = frameworkMediaDrm;
        this.f4495b = onExpirationUpdateListener;
    }

    public final void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j2) {
        this.f4494a.lambda$setOnExpirationUpdateListener$3(this.f4495b, mediaDrm, bArr, j2);
    }
}
