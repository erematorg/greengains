package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;
import java.util.List;

public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new Object();

    List<MediaCodecInfo> getDecoderInfos(String str, boolean z2, boolean z3) throws MediaCodecUtil.DecoderQueryException;
}
