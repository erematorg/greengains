package com.appsamurai.storyly.exoplayer2.extractor.text;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderOutputBuffer;
import java.util.List;

public abstract class SubtitleOutputBuffer extends DecoderOutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    @Nullable
    private Subtitle subtitle;

    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    public List<Cue> getCues(long j2) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getCues(j2 - this.subsampleOffsetUs);
    }

    public long getEventTime(int i3) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getEventTime(i3) + this.subsampleOffsetUs;
    }

    public int getEventTimeCount() {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getEventTimeCount();
    }

    public int getNextEventTimeIndex(long j2) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getNextEventTimeIndex(j2 - this.subsampleOffsetUs);
    }

    public void setContent(long j2, Subtitle subtitle2, long j3) {
        this.timeUs = j2;
        this.subtitle = subtitle2;
        if (j3 != Long.MAX_VALUE) {
            j2 = j3;
        }
        this.subsampleOffsetUs = j2;
    }
}
