package com.appsamurai.storyly.exoplayer2.core.video;

import android.view.Surface;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecDecoderException;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo;

public class MediaCodecVideoDecoderException extends MediaCodecDecoderException {
    public final boolean isSurfaceValid;
    public final int surfaceIdentityHashCode;

    public MediaCodecVideoDecoderException(Throwable th, @Nullable MediaCodecInfo mediaCodecInfo, @Nullable Surface surface) {
        super(th, mediaCodecInfo);
        this.surfaceIdentityHashCode = System.identityHashCode(surface);
        this.isSurfaceValid = surface == null || surface.isValid();
    }
}
