package com.appsamurai.storyly.exoplayer2.core.trackselection;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.MappingTrackSelector;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

public final class TrackSelectionUtil {

    public interface AdaptiveTrackSelectionFactory {
        ExoTrackSelection createAdaptiveTrackSelection(ExoTrackSelection.Definition definition);
    }

    private TrackSelectionUtil() {
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelection[] trackSelectionArr) {
        List[] listArr = new List[trackSelectionArr.length];
        for (int i3 = 0; i3 < trackSelectionArr.length; i3++) {
            TrackSelection trackSelection = trackSelectionArr[i3];
            listArr[i3] = trackSelection != null ? ImmutableList.of(trackSelection) : ImmutableList.of();
        }
        return buildTracks(mappedTrackInfo, (List<? extends TrackSelection>[]) listArr);
    }

    public static LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (exoTrackSelection.isBlacklisted(i4, elapsedRealtime)) {
                i3++;
            }
        }
        return new LoadErrorHandlingPolicy.FallbackOptions(1, 0, length, i3);
    }

    public static ExoTrackSelection[] createTrackSelectionsForDefinitions(ExoTrackSelection.Definition[] definitionArr, AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory) {
        ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
        boolean z2 = false;
        for (int i3 = 0; i3 < definitionArr.length; i3++) {
            ExoTrackSelection.Definition definition = definitionArr[i3];
            if (definition != null) {
                int[] iArr = definition.tracks;
                if (iArr.length <= 1 || z2) {
                    exoTrackSelectionArr[i3] = new FixedTrackSelection(definition.group, iArr[0], definition.type);
                } else {
                    exoTrackSelectionArr[i3] = adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
                    z2 = true;
                }
            }
        }
        return exoTrackSelectionArr;
    }

    public static DefaultTrackSelector.Parameters updateParametersWithOverride(DefaultTrackSelector.Parameters parameters, int i3, TrackGroupArray trackGroupArray, boolean z2, @Nullable DefaultTrackSelector.SelectionOverride selectionOverride) {
        DefaultTrackSelector.Parameters.Builder rendererDisabled = parameters.buildUpon().clearSelectionOverrides(i3).setRendererDisabled(i3, z2);
        if (selectionOverride != null) {
            rendererDisabled.setSelectionOverride(i3, trackGroupArray, selectionOverride);
        }
        return rendererDisabled.build();
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, List<? extends TrackSelection>[] listArr) {
        boolean z2;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i3 = 0; i3 < mappedTrackInfo.getRendererCount(); i3++) {
            TrackGroupArray trackGroups = mappedTrackInfo2.getTrackGroups(i3);
            List<? extends TrackSelection> list = listArr[i3];
            for (int i4 = 0; i4 < trackGroups.length; i4++) {
                TrackGroup trackGroup = trackGroups.get(i4);
                boolean z3 = mappedTrackInfo2.getAdaptiveSupport(i3, i4, false) != 0;
                int i5 = trackGroup.length;
                int[] iArr = new int[i5];
                boolean[] zArr = new boolean[i5];
                for (int i6 = 0; i6 < trackGroup.length; i6++) {
                    iArr[i6] = mappedTrackInfo2.getTrackSupport(i3, i4, i6);
                    int i7 = 0;
                    while (true) {
                        if (i7 >= list.size()) {
                            z2 = false;
                            break;
                        }
                        TrackSelection trackSelection = (TrackSelection) list.get(i7);
                        if (trackSelection.getTrackGroup().equals(trackGroup) && trackSelection.indexOf(i6) != -1) {
                            z2 = true;
                            break;
                        }
                        i7++;
                    }
                    zArr[i6] = z2;
                }
                builder.add((Object) new Tracks.Group(trackGroup, z3, iArr, zArr));
            }
        }
        TrackGroupArray unmappedTrackGroups = mappedTrackInfo.getUnmappedTrackGroups();
        for (int i8 = 0; i8 < unmappedTrackGroups.length; i8++) {
            TrackGroup trackGroup2 = unmappedTrackGroups.get(i8);
            int[] iArr2 = new int[trackGroup2.length];
            Arrays.fill(iArr2, 0);
            builder.add((Object) new Tracks.Group(trackGroup2, false, iArr2, new boolean[trackGroup2.length]));
        }
        return new Tracks(builder.build());
    }
}
