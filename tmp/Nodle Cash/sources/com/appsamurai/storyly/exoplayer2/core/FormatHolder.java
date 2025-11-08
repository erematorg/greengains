package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;

public final class FormatHolder {
    @Nullable
    public DrmSession drmSession;
    @Nullable
    public Format format;

    public void clear() {
        this.drmSession = null;
        this.format = null;
    }
}
