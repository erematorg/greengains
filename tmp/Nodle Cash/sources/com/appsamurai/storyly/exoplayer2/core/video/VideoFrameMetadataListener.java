package com.appsamurai.storyly.exoplayer2.core.video;

import android.media.MediaFormat;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;

public interface VideoFrameMetadataListener {
    void onVideoFrameAboutToBeRendered(long j2, long j3, Format format, @Nullable MediaFormat mediaFormat);
}
