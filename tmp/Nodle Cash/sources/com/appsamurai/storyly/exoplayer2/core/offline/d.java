package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManagerProvider;

public final /* synthetic */ class d implements DrmSessionManagerProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DrmSessionManager f4524a;

    public /* synthetic */ d(DrmSessionManager drmSessionManager) {
        this.f4524a = drmSessionManager;
    }

    public final DrmSessionManager get(MediaItem mediaItem) {
        return DownloadHelper.lambda$createMediaSourceInternal$6(this.f4524a, mediaItem);
    }
}
