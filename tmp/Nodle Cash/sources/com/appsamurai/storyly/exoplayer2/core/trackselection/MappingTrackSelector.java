package com.appsamurai.storyly.exoplayer2.core.trackselection;

import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.RendererCapabilities;
import com.appsamurai.storyly.exoplayer2.core.RendererConfiguration;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public abstract class MappingTrackSelector extends TrackSelector {
    @Nullable
    private MappedTrackInfo currentMappedTrackInfo;

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup, int[] iArr, boolean z2) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i3 = 0;
        boolean z3 = true;
        for (int i4 = 0; i4 < rendererCapabilitiesArr.length; i4++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i4];
            int i5 = 0;
            for (int i6 = 0; i6 < trackGroup.length; i6++) {
                i5 = Math.max(i5, RendererCapabilities.getFormatSupport(rendererCapabilities.supportsFormat(trackGroup.getFormat(i6))));
            }
            boolean z4 = iArr[i4] == 0;
            if (i5 > i3 || (i5 == i3 && z2 && !z3 && z4)) {
                length = i4;
                z3 = z4;
                i3 = i5;
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i3 = 0; i3 < trackGroup.length; i3++) {
            iArr[i3] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i3));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = rendererCapabilitiesArr[i3].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    @Nullable
    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final void onSelectionActivated(@Nullable Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    public abstract Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException;

    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException {
        int[] iArr;
        RendererCapabilities[] rendererCapabilitiesArr2 = rendererCapabilitiesArr;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int[] iArr2 = new int[(rendererCapabilitiesArr2.length + 1)];
        int length = rendererCapabilitiesArr2.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr3 = new int[(rendererCapabilitiesArr2.length + 1)][][];
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = trackGroupArray2.length;
            trackGroupArr[i3] = new TrackGroup[i4];
            iArr3[i3] = new int[i4][];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int i5 = 0; i5 < trackGroupArray2.length; i5++) {
            TrackGroup trackGroup = trackGroupArray2.get(i5);
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup, iArr2, trackGroup.type == 5);
            if (findRenderer == rendererCapabilitiesArr2.length) {
                iArr = new int[trackGroup.length];
            } else {
                iArr = getFormatSupport(rendererCapabilitiesArr2[findRenderer], trackGroup);
            }
            int i6 = iArr2[findRenderer];
            trackGroupArr[findRenderer][i6] = trackGroup;
            iArr3[findRenderer][i6] = iArr;
            iArr2[findRenderer] = i6 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr2.length];
        String[] strArr = new String[rendererCapabilitiesArr2.length];
        int[] iArr4 = new int[rendererCapabilitiesArr2.length];
        for (int i7 = 0; i7 < rendererCapabilitiesArr2.length; i7++) {
            int i8 = iArr2[i7];
            trackGroupArrayArr[i7] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[i7], i8));
            iArr3[i7] = (int[][]) Util.nullSafeArrayCopy(iArr3[i7], i8);
            strArr[i7] = rendererCapabilitiesArr2[i7].getName();
            iArr4[i7] = rendererCapabilitiesArr2[i7].getTrackType();
        }
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(strArr, iArr4, trackGroupArrayArr, mixedMimeTypeAdaptationSupports, iArr3, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[rendererCapabilitiesArr2.length], iArr2[rendererCapabilitiesArr2.length])));
        Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks = selectTracks(mappedTrackInfo, iArr3, mixedMimeTypeAdaptationSupports, mediaPeriodId, timeline);
        return new TrackSelectorResult((RendererConfiguration[]) selectTracks.first, (ExoTrackSelection[]) selectTracks.second, TrackSelectionUtil.buildTracks(mappedTrackInfo, (TrackSelection[]) selectTracks.second), mappedTrackInfo);
    }

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        @Documented
        @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface RendererSupport {
        }

        @VisibleForTesting
        public MappedTrackInfo(String[] strArr, int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererNames = strArr;
            this.rendererTrackTypes = iArr;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = iArr3;
            this.rendererMixedMimeTypeAdaptiveSupports = iArr2;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = iArr.length;
        }

        public int getAdaptiveSupport(int i3, int i4, boolean z2) {
            int i5 = this.rendererTrackGroups[i3].get(i4).length;
            int[] iArr = new int[i5];
            int i6 = 0;
            for (int i7 = 0; i7 < i5; i7++) {
                int trackSupport = getTrackSupport(i3, i4, i7);
                if (trackSupport == 4 || (z2 && trackSupport == 3)) {
                    iArr[i6] = i7;
                    i6++;
                }
            }
            return getAdaptiveSupport(i3, i4, Arrays.copyOf(iArr, i6));
        }

        public int getCapabilities(int i3, int i4, int i5) {
            return this.rendererFormatSupports[i3][i4][i5];
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public String getRendererName(int i3) {
            return this.rendererNames[i3];
        }

        public int getRendererSupport(int i3) {
            int i4 = 0;
            for (int[] iArr : this.rendererFormatSupports[i3]) {
                for (int formatSupport : r9[r1]) {
                    int formatSupport2 = RendererCapabilities.getFormatSupport(formatSupport);
                    int i5 = 1;
                    if (!(formatSupport2 == 0 || formatSupport2 == 1 || formatSupport2 == 2)) {
                        if (formatSupport2 == 3) {
                            i5 = 2;
                        } else if (formatSupport2 == 4) {
                            return 3;
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                    i4 = Math.max(i4, i5);
                }
            }
            return i4;
        }

        public int getRendererType(int i3) {
            return this.rendererTrackTypes[i3];
        }

        public TrackGroupArray getTrackGroups(int i3) {
            return this.rendererTrackGroups[i3];
        }

        public int getTrackSupport(int i3, int i4, int i5) {
            return RendererCapabilities.getFormatSupport(getCapabilities(i3, i4, i5));
        }

        public int getTypeSupport(int i3) {
            int i4 = 0;
            for (int i5 = 0; i5 < this.rendererCount; i5++) {
                if (this.rendererTrackTypes[i5] == i3) {
                    i4 = Math.max(i4, getRendererSupport(i5));
                }
            }
            return i4;
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }

        public int getAdaptiveSupport(int i3, int i4, int[] iArr) {
            int i5 = 0;
            int i6 = 16;
            String str = null;
            boolean z2 = false;
            int i7 = 0;
            while (i5 < iArr.length) {
                String str2 = this.rendererTrackGroups[i3].get(i4).getFormat(iArr[i5]).sampleMimeType;
                int i8 = i7 + 1;
                if (i7 == 0) {
                    str = str2;
                } else {
                    z2 |= !Util.areEqual(str, str2);
                }
                i6 = Math.min(i6, RendererCapabilities.getAdaptiveSupport(this.rendererFormatSupports[i3][i4][i5]));
                i5++;
                i7 = i8;
            }
            return z2 ? Math.min(i6, this.rendererMixedMimeTypeAdaptiveSupports[i3]) : i6;
        }
    }
}
