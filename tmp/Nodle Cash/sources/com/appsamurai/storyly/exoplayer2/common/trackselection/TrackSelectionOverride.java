package com.appsamurai.storyly.exoplayer2.common.trackselection;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;

public final class TrackSelectionOverride implements Bundleable {
    public static final Bundleable.Creator<TrackSelectionOverride> CREATOR = new a(21);
    private static final int FIELD_TRACKS = 1;
    private static final int FIELD_TRACK_GROUP = 0;
    public final TrackGroup mediaTrackGroup;
    public final ImmutableList<Integer> trackIndices;

    public TrackSelectionOverride(TrackGroup trackGroup, int i3) {
        this(trackGroup, (List<Integer>) ImmutableList.of(Integer.valueOf(i3)));
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TrackSelectionOverride lambda$static$0(Bundle bundle) {
        return new TrackSelectionOverride(TrackGroup.CREATOR.fromBundle((Bundle) Assertions.checkNotNull(bundle.getBundle(keyForField(0)))), Ints.asList((int[]) Assertions.checkNotNull(bundle.getIntArray(keyForField(1)))));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackSelectionOverride.class != obj.getClass()) {
            return false;
        }
        TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) obj;
        return this.mediaTrackGroup.equals(trackSelectionOverride.mediaTrackGroup) && this.trackIndices.equals(trackSelectionOverride.trackIndices);
    }

    public int getType() {
        return this.mediaTrackGroup.type;
    }

    public int hashCode() {
        return (this.trackIndices.hashCode() * 31) + this.mediaTrackGroup.hashCode();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(keyForField(0), this.mediaTrackGroup.toBundle());
        bundle.putIntArray(keyForField(1), Ints.toArray(this.trackIndices));
        return bundle;
    }

    public TrackSelectionOverride(TrackGroup trackGroup, List<Integer> list) {
        if (list.isEmpty() || (((Integer) Collections.min(list)).intValue() >= 0 && ((Integer) Collections.max(list)).intValue() < trackGroup.length)) {
            this.mediaTrackGroup = trackGroup;
            this.trackIndices = ImmutableList.copyOf(list);
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
