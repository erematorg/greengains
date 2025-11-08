package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;

public final class MediaLoadData {
    public final int dataType;
    public final long mediaEndTimeMs;
    public final long mediaStartTimeMs;
    @Nullable
    public final Format trackFormat;
    @Nullable
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int trackType;

    public MediaLoadData(int i3) {
        this(i3, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
    }

    public MediaLoadData(int i3, int i4, @Nullable Format format, int i5, @Nullable Object obj, long j2, long j3) {
        this.dataType = i3;
        this.trackType = i4;
        this.trackFormat = format;
        this.trackSelectionReason = i5;
        this.trackSelectionData = obj;
        this.mediaStartTimeMs = j2;
        this.mediaEndTimeMs = j3;
    }
}
