package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.common.MediaItem;

public interface DrmSessionManagerProvider {
    DrmSessionManager get(MediaItem mediaItem);
}
