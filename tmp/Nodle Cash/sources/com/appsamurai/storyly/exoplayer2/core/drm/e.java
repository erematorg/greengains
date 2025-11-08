package com.appsamurai.storyly.exoplayer2.core.drm;

import android.media.MediaDrm;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;
import java.util.List;

public final /* synthetic */ class e implements MediaDrm.OnKeyStatusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FrameworkMediaDrm f4490a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ExoMediaDrm.OnKeyStatusChangeListener f4491b;

    public /* synthetic */ e(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener) {
        this.f4490a = frameworkMediaDrm;
        this.f4491b = onKeyStatusChangeListener;
    }

    public final void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List list, boolean z2) {
        this.f4490a.lambda$setOnKeyStatusChangeListener$2(this.f4491b, mediaDrm, bArr, list, z2);
    }
}
