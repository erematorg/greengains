package com.appsamurai.storyly.exoplayer2.hls;

import A.a;
import androidx.annotation.Nullable;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.IOException;

public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(@Nullable String str) {
        super(a.l("Unable to bind a sample queue to TrackGroup with mime type ", str, JwtUtilsKt.JWT_DELIMITER));
    }
}
