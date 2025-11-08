package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManagerProvider;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;

@Deprecated
public interface MediaSourceFactory extends MediaSource.Factory {
    public static final MediaSourceFactory UNSUPPORTED = new MediaSourceFactory() {
        public MediaSource createMediaSource(MediaItem mediaItem) {
            throw new UnsupportedOperationException();
        }

        public int[] getSupportedTypes() {
            throw new UnsupportedOperationException();
        }

        public MediaSourceFactory setDrmSessionManagerProvider(@Nullable DrmSessionManagerProvider drmSessionManagerProvider) {
            return this;
        }

        public MediaSourceFactory setLoadErrorHandlingPolicy(@Nullable LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return this;
        }
    };
}
