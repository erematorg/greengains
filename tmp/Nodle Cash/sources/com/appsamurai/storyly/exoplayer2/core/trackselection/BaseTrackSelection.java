package com.appsamurai.storyly.exoplayer2.core.trackselection;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import java.util.Arrays;
import java.util.List;

public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    protected final int[] tracks;
    private final int type;

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        this(trackGroup, iArr, 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$0(Format format, Format format2) {
        return format2.bitrate - format.bitrate;
    }

    public boolean blacklist(int i3, long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean isBlacklisted = isBlacklisted(i3, elapsedRealtime);
        int i4 = 0;
        while (i4 < this.length && !isBlacklisted) {
            isBlacklisted = i4 != i3 && !isBlacklisted(i4, elapsedRealtime);
            i4++;
        }
        if (!isBlacklisted) {
            return false;
        }
        long[] jArr = this.excludeUntilTimes;
        jArr[i3] = Math.max(jArr[i3], Util.addWithOverflowDefault(elapsedRealtime, j2, Long.MAX_VALUE));
        return true;
    }

    public void disable() {
    }

    public void enable() {
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
        return this.group == baseTrackSelection.group && Arrays.equals(this.tracks, baseTrackSelection.tracks);
    }

    public int evaluateQueueSize(long j2, List<? extends MediaChunk> list) {
        return list.size();
    }

    public final Format getFormat(int i3) {
        return this.formats[i3];
    }

    public final int getIndexInTrackGroup(int i3) {
        return this.tracks[i3];
    }

    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.tracks) + (System.identityHashCode(this.group) * 31);
        }
        return this.hashCode;
    }

    public final int indexOf(Format format) {
        for (int i3 = 0; i3 < this.length; i3++) {
            if (this.formats[i3] == format) {
                return i3;
            }
        }
        return -1;
    }

    public boolean isBlacklisted(int i3, long j2) {
        return this.excludeUntilTimes[i3] > j2;
    }

    public final int length() {
        return this.tracks.length;
    }

    public void onPlaybackSpeed(float f2) {
    }

    public BaseTrackSelection(TrackGroup trackGroup, int[] iArr, int i3) {
        int i4 = 0;
        Assertions.checkState(iArr.length > 0);
        this.type = i3;
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        int length2 = iArr.length;
        this.length = length2;
        this.formats = new Format[length2];
        for (int i5 = 0; i5 < iArr.length; i5++) {
            this.formats[i5] = trackGroup.getFormat(iArr[i5]);
        }
        Arrays.sort(this.formats, new a(5));
        this.tracks = new int[this.length];
        while (true) {
            int i6 = this.length;
            if (i4 < i6) {
                this.tracks[i4] = trackGroup.indexOf(this.formats[i4]);
                i4++;
            } else {
                this.excludeUntilTimes = new long[i6];
                return;
            }
        }
    }

    public final int indexOf(int i3) {
        for (int i4 = 0; i4 < this.length; i4++) {
            if (this.tracks[i4] == i3) {
                return i4;
            }
        }
        return -1;
    }
}
