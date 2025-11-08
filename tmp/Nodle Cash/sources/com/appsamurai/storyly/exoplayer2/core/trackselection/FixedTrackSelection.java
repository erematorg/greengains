package com.appsamurai.storyly.exoplayer2.core.trackselection;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator;
import java.util.List;

public final class FixedTrackSelection extends BaseTrackSelection {
    @Nullable
    private final Object data;
    private final int reason;

    public FixedTrackSelection(TrackGroup trackGroup, int i3) {
        this(trackGroup, i3, 0);
    }

    public int getSelectedIndex() {
        return 0;
    }

    @Nullable
    public Object getSelectionData() {
        return this.data;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    public void updateSelectedTrack(long j2, long j3, long j4, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i3, int i4) {
        this(trackGroup, i3, i4, 0, (Object) null);
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i3, int i4, int i5, @Nullable Object obj) {
        super(trackGroup, new int[]{i3}, i4);
        this.reason = i5;
        this.data = obj;
    }
}
