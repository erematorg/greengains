package com.appsamurai.storyly.exoplayer2.core.trackselection;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.RendererConfiguration;

public final class TrackSelectorResult {
    @Nullable
    public final Object info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final ExoTrackSelection[] selections;
    public final Tracks tracks;

    @Deprecated
    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, @Nullable Object obj) {
        this(rendererConfigurationArr, exoTrackSelectionArr, Tracks.EMPTY, obj);
    }

    public boolean isEquivalent(@Nullable TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i3 = 0; i3 < this.selections.length; i3++) {
            if (!isEquivalent(trackSelectorResult, i3)) {
                return false;
            }
        }
        return true;
    }

    public boolean isRendererEnabled(int i3) {
        return this.rendererConfigurations[i3] != null;
    }

    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, Tracks tracks2, @Nullable Object obj) {
        this.rendererConfigurations = rendererConfigurationArr;
        this.selections = (ExoTrackSelection[]) exoTrackSelectionArr.clone();
        this.tracks = tracks2;
        this.info = obj;
        this.length = rendererConfigurationArr.length;
    }

    public boolean isEquivalent(@Nullable TrackSelectorResult trackSelectorResult, int i3) {
        if (trackSelectorResult != null && Util.areEqual(this.rendererConfigurations[i3], trackSelectorResult.rendererConfigurations[i3]) && Util.areEqual(this.selections[i3], trackSelectorResult.selections[i3])) {
            return true;
        }
        return false;
    }
}
