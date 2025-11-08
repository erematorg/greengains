package com.appsamurai.storyly.exoplayer2.core.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.Spatializer;
import android.media.Spatializer$OnSpatializerStateChangedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.interop.d;
import androidx.emoji2.text.a;
import androidx.navigation.ui.b;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionOverride;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.RendererCapabilities;
import com.appsamurai.storyly.exoplayer2.core.RendererConfiguration;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.AdaptiveTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.MappingTrackSelector;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultTrackSelector extends MappingTrackSelector {
    private static final String AUDIO_CHANNEL_COUNT_CONSTRAINTS_WARN_MESSAGE = "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.";
    /* access modifiers changed from: private */
    public static final Ordering<Integer> FORMAT_VALUE_ORDERING = Ordering.from(new a(6));
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    /* access modifiers changed from: private */
    public static final Ordering<Integer> NO_ORDER = Ordering.from(new a(7));
    protected static final int SELECTION_ELIGIBILITY_ADAPTIVE = 2;
    protected static final int SELECTION_ELIGIBILITY_FIXED = 1;
    protected static final int SELECTION_ELIGIBILITY_NO = 0;
    private static final String TAG = "DefaultTrackSelector";
    @GuardedBy("lock")
    private AudioAttributes audioAttributes;
    @Nullable
    public final Context context;
    private final boolean deviceIsTV;
    private final Object lock;
    @GuardedBy("lock")
    private Parameters parameters;
    @GuardedBy("lock")
    @Nullable
    private SpatializerWrapperV32 spatializer;
    private final ExoTrackSelection.Factory trackSelectionFactory;

    public static final class AudioTrackInfo extends TrackInfo<AudioTrackInfo> implements Comparable<AudioTrackInfo> {
        private final int bitrate;
        private final int channelCount;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean isDefaultSelectionFlag;
        private final boolean isWithinConstraints;
        private final boolean isWithinRendererCapabilities;
        @Nullable
        private final String language = DefaultTrackSelector.normalizeUndeterminedLanguageToNull(this.format.language);
        private final int localeLanguageMatchIndex;
        private final int localeLanguageScore;
        private final Parameters parameters;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int sampleRate;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        public AudioTrackInfo(int i3, TrackGroup trackGroup, int i4, Parameters parameters2, int i5, boolean z2, Predicate<Format> predicate) {
            super(i3, trackGroup, i4);
            int i6;
            int i7;
            int i8;
            this.parameters = parameters2;
            boolean z3 = false;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i5, false);
            int i9 = 0;
            while (true) {
                i6 = Integer.MAX_VALUE;
                if (i9 >= parameters2.preferredAudioLanguages.size()) {
                    i7 = 0;
                    i9 = Integer.MAX_VALUE;
                    break;
                }
                i7 = DefaultTrackSelector.getFormatLanguageScore(this.format, parameters2.preferredAudioLanguages.get(i9), false);
                if (i7 > 0) {
                    break;
                }
                i9++;
            }
            this.preferredLanguageIndex = i9;
            this.preferredLanguageScore = i7;
            this.preferredRoleFlagsScore = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, parameters2.preferredAudioRoleFlags);
            Format format = this.format;
            int i10 = format.roleFlags;
            this.hasMainOrNoRoleFlag = i10 == 0 || (i10 & 1) != 0;
            this.isDefaultSelectionFlag = (format.selectionFlags & 1) != 0;
            int i11 = format.channelCount;
            this.channelCount = i11;
            this.sampleRate = format.sampleRate;
            int i12 = format.bitrate;
            this.bitrate = i12;
            this.isWithinConstraints = (i12 == -1 || i12 <= parameters2.maxAudioBitrate) && (i11 == -1 || i11 <= parameters2.maxAudioChannelCount) && predicate.apply(format);
            String[] systemLanguageCodes = Util.getSystemLanguageCodes();
            int i13 = 0;
            while (true) {
                if (i13 >= systemLanguageCodes.length) {
                    i8 = 0;
                    i13 = Integer.MAX_VALUE;
                    break;
                }
                i8 = DefaultTrackSelector.getFormatLanguageScore(this.format, systemLanguageCodes[i13], false);
                if (i8 > 0) {
                    break;
                }
                i13++;
            }
            this.localeLanguageMatchIndex = i13;
            this.localeLanguageScore = i8;
            int i14 = 0;
            while (true) {
                if (i14 < parameters2.preferredAudioMimeTypes.size()) {
                    String str = this.format.sampleMimeType;
                    if (str != null && str.equals(parameters2.preferredAudioMimeTypes.get(i14))) {
                        i6 = i14;
                        break;
                    }
                    i14++;
                } else {
                    break;
                }
            }
            this.preferredMimeTypeMatchIndex = i6;
            this.usesPrimaryDecoder = RendererCapabilities.getDecoderSupport(i5) == 128;
            this.usesHardwareAcceleration = RendererCapabilities.getHardwareAccelerationSupport(i5) == 64 ? true : z3;
            this.selectionEligibility = evaluateSelectionEligibility(i5, z2);
        }

        public static int compareSelections(List<AudioTrackInfo> list, List<AudioTrackInfo> list2) {
            return ((AudioTrackInfo) Collections.max(list)).compareTo((AudioTrackInfo) Collections.max(list2));
        }

        public static ImmutableList<AudioTrackInfo> createForTrackGroup(int i3, TrackGroup trackGroup, Parameters parameters2, int[] iArr, boolean z2, Predicate<Format> predicate) {
            ImmutableList.Builder builder = ImmutableList.builder();
            TrackGroup trackGroup2 = trackGroup;
            for (int i4 = 0; i4 < trackGroup2.length; i4++) {
                builder.add((Object) new AudioTrackInfo(i3, trackGroup, i4, parameters2, iArr[i4], z2, predicate));
            }
            return builder.build();
        }

        private int evaluateSelectionEligibility(int i3, boolean z2) {
            if (!DefaultTrackSelector.isSupported(i3, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinConstraints && !this.parameters.exceedAudioConstraintsIfNecessary) {
                return 0;
            }
            if (DefaultTrackSelector.isSupported(i3, false) && this.isWithinConstraints && this.format.bitrate != -1) {
                Parameters parameters2 = this.parameters;
                return (parameters2.forceHighestSupportedBitrate || parameters2.forceLowestBitrate || (!parameters2.allowMultipleAdaptiveSelections && z2)) ? 1 : 2;
            }
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public int compareTo(AudioTrackInfo audioTrackInfo) {
            Ordering ordering;
            if (!this.isWithinConstraints || !this.isWithinRendererCapabilities) {
                ordering = DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse();
            } else {
                ordering = DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            }
            ComparisonChain compare = ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, audioTrackInfo.isWithinRendererCapabilities).compare(Integer.valueOf(this.preferredLanguageIndex), Integer.valueOf(audioTrackInfo.preferredLanguageIndex), Ordering.natural().reverse()).compare(this.preferredLanguageScore, audioTrackInfo.preferredLanguageScore).compare(this.preferredRoleFlagsScore, audioTrackInfo.preferredRoleFlagsScore).compareFalseFirst(this.isDefaultSelectionFlag, audioTrackInfo.isDefaultSelectionFlag).compareFalseFirst(this.hasMainOrNoRoleFlag, audioTrackInfo.hasMainOrNoRoleFlag).compare(Integer.valueOf(this.localeLanguageMatchIndex), Integer.valueOf(audioTrackInfo.localeLanguageMatchIndex), Ordering.natural().reverse()).compare(this.localeLanguageScore, audioTrackInfo.localeLanguageScore).compareFalseFirst(this.isWithinConstraints, audioTrackInfo.isWithinConstraints).compare(Integer.valueOf(this.preferredMimeTypeMatchIndex), Integer.valueOf(audioTrackInfo.preferredMimeTypeMatchIndex), Ordering.natural().reverse()).compare(Integer.valueOf(this.bitrate), Integer.valueOf(audioTrackInfo.bitrate), this.parameters.forceLowestBitrate ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse() : DefaultTrackSelector.NO_ORDER).compareFalseFirst(this.usesPrimaryDecoder, audioTrackInfo.usesPrimaryDecoder).compareFalseFirst(this.usesHardwareAcceleration, audioTrackInfo.usesHardwareAcceleration).compare(Integer.valueOf(this.channelCount), Integer.valueOf(audioTrackInfo.channelCount), ordering).compare(Integer.valueOf(this.sampleRate), Integer.valueOf(audioTrackInfo.sampleRate), ordering);
            Integer valueOf = Integer.valueOf(this.bitrate);
            Integer valueOf2 = Integer.valueOf(audioTrackInfo.bitrate);
            if (!Util.areEqual(this.language, audioTrackInfo.language)) {
                ordering = DefaultTrackSelector.NO_ORDER;
            }
            return compare.compare(valueOf, valueOf2, ordering).result();
        }

        public boolean isCompatibleForAdaptationWith(AudioTrackInfo audioTrackInfo) {
            int i3;
            String str;
            int i4;
            Parameters parameters2 = this.parameters;
            if ((parameters2.allowAudioMixedChannelCountAdaptiveness || ((i4 = this.format.channelCount) != -1 && i4 == audioTrackInfo.format.channelCount)) && (parameters2.allowAudioMixedMimeTypeAdaptiveness || ((str = this.format.sampleMimeType) != null && TextUtils.equals(str, audioTrackInfo.format.sampleMimeType)))) {
                Parameters parameters3 = this.parameters;
                if ((parameters3.allowAudioMixedSampleRateAdaptiveness || ((i3 = this.format.sampleRate) != -1 && i3 == audioTrackInfo.format.sampleRate)) && (parameters3.allowAudioMixedDecoderSupportAdaptiveness || (this.usesPrimaryDecoder == audioTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == audioTrackInfo.usesHardwareAcceleration))) {
                    return true;
                }
            }
            return false;
        }
    }

    public static final class OtherTrackScore implements Comparable<OtherTrackScore> {
        private final boolean isDefault;
        private final boolean isWithinRendererCapabilities;

        public OtherTrackScore(Format format, int i3) {
            this.isDefault = (format.selectionFlags & 1) == 0 ? false : true;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i3, false);
        }

        public int compareTo(OtherTrackScore otherTrackScore) {
            return ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, otherTrackScore.isWithinRendererCapabilities).compareFalseFirst(this.isDefault, otherTrackScore.isDefault).result();
        }
    }

    public static final class Parameters extends TrackSelectionParameters implements Bundleable {
        public static final Bundleable.Creator<Parameters> CREATOR = new c(0);
        @Deprecated
        public static final Parameters DEFAULT;
        public static final Parameters DEFAULT_WITHOUT_CONTEXT;
        private static final int FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS = 1006;
        private static final int FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = 1015;
        private static final int FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS = 1004;
        private static final int FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS = 1005;
        private static final int FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS = 1009;
        private static final int FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = 1014;
        private static final int FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS = 1001;
        private static final int FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS = 1002;
        private static final int FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES = 1016;
        private static final int FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NCESSARY = 1003;
        private static final int FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY = 1007;
        private static final int FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY = 1000;
        private static final int FIELD_RENDERER_DISABLED_INDICES = 1013;
        private static final int FIELD_SELECTION_OVERRIDES = 1012;
        private static final int FIELD_SELECTION_OVERRIDES_RENDERER_INDICES = 1010;
        private static final int FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS = 1011;
        private static final int FIELD_TUNNELING_ENABLED = 1008;
        public final boolean allowAudioMixedChannelCountAdaptiveness;
        public final boolean allowAudioMixedDecoderSupportAdaptiveness;
        public final boolean allowAudioMixedMimeTypeAdaptiveness;
        public final boolean allowAudioMixedSampleRateAdaptiveness;
        public final boolean allowMultipleAdaptiveSelections;
        public final boolean allowVideoMixedDecoderSupportAdaptiveness;
        public final boolean allowVideoMixedMimeTypeAdaptiveness;
        public final boolean allowVideoNonSeamlessAdaptiveness;
        public final boolean constrainAudioChannelCountToDeviceCapabilities;
        public final boolean exceedAudioConstraintsIfNecessary;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        /* access modifiers changed from: private */
        public final SparseBooleanArray rendererDisabledFlags;
        /* access modifiers changed from: private */
        public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final boolean tunnelingEnabled;

        public static final class Builder extends TrackSelectionParameters.Builder {
            /* access modifiers changed from: private */
            public boolean allowAudioMixedChannelCountAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedDecoderSupportAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedMimeTypeAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedSampleRateAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowMultipleAdaptiveSelections;
            /* access modifiers changed from: private */
            public boolean allowVideoMixedDecoderSupportAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowVideoMixedMimeTypeAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowVideoNonSeamlessAdaptiveness;
            /* access modifiers changed from: private */
            public boolean constrainAudioChannelCountToDeviceCapabilities;
            /* access modifiers changed from: private */
            public boolean exceedAudioConstraintsIfNecessary;
            /* access modifiers changed from: private */
            public boolean exceedRendererCapabilitiesIfNecessary;
            /* access modifiers changed from: private */
            public boolean exceedVideoConstraintsIfNecessary;
            /* access modifiers changed from: private */
            public final SparseBooleanArray rendererDisabledFlags;
            /* access modifiers changed from: private */
            public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
            /* access modifiers changed from: private */
            public boolean tunnelingEnabled;

            private static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
                SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2 = new SparseArray<>();
                for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                    sparseArray2.put(sparseArray.keyAt(i3), new HashMap(sparseArray.valueAt(i3)));
                }
                return sparseArray2;
            }

            private void init() {
                this.exceedVideoConstraintsIfNecessary = true;
                this.allowVideoMixedMimeTypeAdaptiveness = false;
                this.allowVideoNonSeamlessAdaptiveness = true;
                this.allowVideoMixedDecoderSupportAdaptiveness = false;
                this.exceedAudioConstraintsIfNecessary = true;
                this.allowAudioMixedMimeTypeAdaptiveness = false;
                this.allowAudioMixedSampleRateAdaptiveness = false;
                this.allowAudioMixedChannelCountAdaptiveness = false;
                this.allowAudioMixedDecoderSupportAdaptiveness = false;
                this.constrainAudioChannelCountToDeviceCapabilities = true;
                this.exceedRendererCapabilitiesIfNecessary = true;
                this.tunnelingEnabled = false;
                this.allowMultipleAdaptiveSelections = true;
            }

            private SparseBooleanArray makeSparseBooleanArrayFromTrueKeys(@Nullable int[] iArr) {
                if (iArr == null) {
                    return new SparseBooleanArray();
                }
                SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(iArr.length);
                for (int append : iArr) {
                    sparseBooleanArray.append(append, true);
                }
                return sparseBooleanArray;
            }

            private void setSelectionOverridesFromBundle(Bundle bundle) {
                int[] intArray = bundle.getIntArray(TrackSelectionParameters.keyForField(1010));
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(TrackSelectionParameters.keyForField(1011));
                ImmutableList<TrackGroupArray> of = parcelableArrayList == null ? ImmutableList.of() : BundleableUtil.fromBundleList(TrackGroupArray.CREATOR, parcelableArrayList);
                SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(TrackSelectionParameters.keyForField(1012));
                SparseArray<SelectionOverride> sparseArray = sparseParcelableArray == null ? new SparseArray<>() : BundleableUtil.fromBundleSparseArray(SelectionOverride.CREATOR, sparseParcelableArray);
                if (intArray != null && intArray.length == of.size()) {
                    for (int i3 = 0; i3 < intArray.length; i3++) {
                        setSelectionOverride(intArray[i3], of.get(i3), sparseArray.get(i3));
                    }
                }
            }

            @Deprecated
            public Builder clearSelectionOverride(int i3, TrackGroupArray trackGroupArray) {
                Map map = this.selectionOverrides.get(i3);
                if (map != null && map.containsKey(trackGroupArray)) {
                    map.remove(trackGroupArray);
                    if (map.isEmpty()) {
                        this.selectionOverrides.remove(i3);
                    }
                }
                return this;
            }

            @Deprecated
            public Builder clearSelectionOverrides(int i3) {
                Map map = this.selectionOverrides.get(i3);
                if (map != null && !map.isEmpty()) {
                    this.selectionOverrides.remove(i3);
                }
                return this;
            }

            public Builder setAllowAudioMixedChannelCountAdaptiveness(boolean z2) {
                this.allowAudioMixedChannelCountAdaptiveness = z2;
                return this;
            }

            public Builder setAllowAudioMixedDecoderSupportAdaptiveness(boolean z2) {
                this.allowAudioMixedDecoderSupportAdaptiveness = z2;
                return this;
            }

            public Builder setAllowAudioMixedMimeTypeAdaptiveness(boolean z2) {
                this.allowAudioMixedMimeTypeAdaptiveness = z2;
                return this;
            }

            public Builder setAllowAudioMixedSampleRateAdaptiveness(boolean z2) {
                this.allowAudioMixedSampleRateAdaptiveness = z2;
                return this;
            }

            public Builder setAllowMultipleAdaptiveSelections(boolean z2) {
                this.allowMultipleAdaptiveSelections = z2;
                return this;
            }

            public Builder setAllowVideoMixedDecoderSupportAdaptiveness(boolean z2) {
                this.allowVideoMixedDecoderSupportAdaptiveness = z2;
                return this;
            }

            public Builder setAllowVideoMixedMimeTypeAdaptiveness(boolean z2) {
                this.allowVideoMixedMimeTypeAdaptiveness = z2;
                return this;
            }

            public Builder setAllowVideoNonSeamlessAdaptiveness(boolean z2) {
                this.allowVideoNonSeamlessAdaptiveness = z2;
                return this;
            }

            public Builder setConstrainAudioChannelCountToDeviceCapabilities(boolean z2) {
                this.constrainAudioChannelCountToDeviceCapabilities = z2;
                return this;
            }

            @Deprecated
            public Builder setDisabledTextTrackSelectionFlags(int i3) {
                return setIgnoredTextSelectionFlags(i3);
            }

            public Builder setExceedAudioConstraintsIfNecessary(boolean z2) {
                this.exceedAudioConstraintsIfNecessary = z2;
                return this;
            }

            public Builder setExceedRendererCapabilitiesIfNecessary(boolean z2) {
                this.exceedRendererCapabilitiesIfNecessary = z2;
                return this;
            }

            public Builder setExceedVideoConstraintsIfNecessary(boolean z2) {
                this.exceedVideoConstraintsIfNecessary = z2;
                return this;
            }

            public Builder setRendererDisabled(int i3, boolean z2) {
                if (this.rendererDisabledFlags.get(i3) == z2) {
                    return this;
                }
                if (z2) {
                    this.rendererDisabledFlags.put(i3, true);
                } else {
                    this.rendererDisabledFlags.delete(i3);
                }
                return this;
            }

            @Deprecated
            public Builder setSelectionOverride(int i3, TrackGroupArray trackGroupArray, @Nullable SelectionOverride selectionOverride) {
                Map map = this.selectionOverrides.get(i3);
                if (map == null) {
                    map = new HashMap();
                    this.selectionOverrides.put(i3, map);
                }
                if (map.containsKey(trackGroupArray) && Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
                    return this;
                }
                map.put(trackGroupArray, selectionOverride);
                return this;
            }

            public Builder setTunnelingEnabled(boolean z2) {
                this.tunnelingEnabled = z2;
                return this;
            }

            public Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
                super.addOverride(trackSelectionOverride);
                return this;
            }

            public Parameters build() {
                return new Parameters(this);
            }

            public Builder clearOverride(TrackGroup trackGroup) {
                super.clearOverride(trackGroup);
                return this;
            }

            public Builder clearOverrides() {
                super.clearOverrides();
                return this;
            }

            public Builder clearOverridesOfType(int i3) {
                super.clearOverridesOfType(i3);
                return this;
            }

            public Builder clearVideoSizeConstraints() {
                super.clearVideoSizeConstraints();
                return this;
            }

            public Builder clearViewportSizeConstraints() {
                super.clearViewportSizeConstraints();
                return this;
            }

            public Builder set(TrackSelectionParameters trackSelectionParameters) {
                super.set(trackSelectionParameters);
                return this;
            }

            @Deprecated
            public Builder setDisabledTrackTypes(Set<Integer> set) {
                super.setDisabledTrackTypes(set);
                return this;
            }

            public Builder setForceHighestSupportedBitrate(boolean z2) {
                super.setForceHighestSupportedBitrate(z2);
                return this;
            }

            public Builder setForceLowestBitrate(boolean z2) {
                super.setForceLowestBitrate(z2);
                return this;
            }

            public Builder setIgnoredTextSelectionFlags(int i3) {
                super.setIgnoredTextSelectionFlags(i3);
                return this;
            }

            public Builder setMaxAudioBitrate(int i3) {
                super.setMaxAudioBitrate(i3);
                return this;
            }

            public Builder setMaxAudioChannelCount(int i3) {
                super.setMaxAudioChannelCount(i3);
                return this;
            }

            public Builder setMaxVideoBitrate(int i3) {
                super.setMaxVideoBitrate(i3);
                return this;
            }

            public Builder setMaxVideoFrameRate(int i3) {
                super.setMaxVideoFrameRate(i3);
                return this;
            }

            public Builder setMaxVideoSize(int i3, int i4) {
                super.setMaxVideoSize(i3, i4);
                return this;
            }

            public Builder setMaxVideoSizeSd() {
                super.setMaxVideoSizeSd();
                return this;
            }

            public Builder setMinVideoBitrate(int i3) {
                super.setMinVideoBitrate(i3);
                return this;
            }

            public Builder setMinVideoFrameRate(int i3) {
                super.setMinVideoFrameRate(i3);
                return this;
            }

            public Builder setMinVideoSize(int i3, int i4) {
                super.setMinVideoSize(i3, i4);
                return this;
            }

            public Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
                super.setOverrideForType(trackSelectionOverride);
                return this;
            }

            public Builder setPreferredAudioLanguage(@Nullable String str) {
                super.setPreferredAudioLanguage(str);
                return this;
            }

            public Builder setPreferredAudioLanguages(String... strArr) {
                super.setPreferredAudioLanguages(strArr);
                return this;
            }

            public Builder setPreferredAudioMimeType(@Nullable String str) {
                super.setPreferredAudioMimeType(str);
                return this;
            }

            public Builder setPreferredAudioMimeTypes(String... strArr) {
                super.setPreferredAudioMimeTypes(strArr);
                return this;
            }

            public Builder setPreferredAudioRoleFlags(int i3) {
                super.setPreferredAudioRoleFlags(i3);
                return this;
            }

            public Builder setPreferredTextLanguage(@Nullable String str) {
                super.setPreferredTextLanguage(str);
                return this;
            }

            public Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
                super.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
                return this;
            }

            public Builder setPreferredTextLanguages(String... strArr) {
                super.setPreferredTextLanguages(strArr);
                return this;
            }

            public Builder setPreferredTextRoleFlags(int i3) {
                super.setPreferredTextRoleFlags(i3);
                return this;
            }

            public Builder setPreferredVideoMimeType(@Nullable String str) {
                super.setPreferredVideoMimeType(str);
                return this;
            }

            public Builder setPreferredVideoMimeTypes(String... strArr) {
                super.setPreferredVideoMimeTypes(strArr);
                return this;
            }

            public Builder setPreferredVideoRoleFlags(int i3) {
                super.setPreferredVideoRoleFlags(i3);
                return this;
            }

            public Builder setSelectUndeterminedTextLanguage(boolean z2) {
                super.setSelectUndeterminedTextLanguage(z2);
                return this;
            }

            public Builder setTrackTypeDisabled(int i3, boolean z2) {
                super.setTrackTypeDisabled(i3, z2);
                return this;
            }

            public Builder setViewportSize(int i3, int i4, boolean z2) {
                super.setViewportSize(i3, i4, z2);
                return this;
            }

            public Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z2) {
                super.setViewportSizeToPhysicalDisplaySize(context, z2);
                return this;
            }

            @Deprecated
            public Builder() {
                this.selectionOverrides = new SparseArray<>();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            @Deprecated
            public Builder clearSelectionOverrides() {
                if (this.selectionOverrides.size() == 0) {
                    return this;
                }
                this.selectionOverrides.clear();
                return this;
            }

            public Builder(Context context) {
                super(context);
                this.selectionOverrides = new SparseArray<>();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            private Builder(Parameters parameters) {
                super((TrackSelectionParameters) parameters);
                this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
                this.allowVideoMixedMimeTypeAdaptiveness = parameters.allowVideoMixedMimeTypeAdaptiveness;
                this.allowVideoNonSeamlessAdaptiveness = parameters.allowVideoNonSeamlessAdaptiveness;
                this.allowVideoMixedDecoderSupportAdaptiveness = parameters.allowVideoMixedDecoderSupportAdaptiveness;
                this.exceedAudioConstraintsIfNecessary = parameters.exceedAudioConstraintsIfNecessary;
                this.allowAudioMixedMimeTypeAdaptiveness = parameters.allowAudioMixedMimeTypeAdaptiveness;
                this.allowAudioMixedSampleRateAdaptiveness = parameters.allowAudioMixedSampleRateAdaptiveness;
                this.allowAudioMixedChannelCountAdaptiveness = parameters.allowAudioMixedChannelCountAdaptiveness;
                this.allowAudioMixedDecoderSupportAdaptiveness = parameters.allowAudioMixedDecoderSupportAdaptiveness;
                this.constrainAudioChannelCountToDeviceCapabilities = parameters.constrainAudioChannelCountToDeviceCapabilities;
                this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
                this.tunnelingEnabled = parameters.tunnelingEnabled;
                this.allowMultipleAdaptiveSelections = parameters.allowMultipleAdaptiveSelections;
                this.selectionOverrides = cloneSelectionOverrides(parameters.selectionOverrides);
                this.rendererDisabledFlags = parameters.rendererDisabledFlags.clone();
            }

            private Builder(Bundle bundle) {
                super(bundle);
                init();
                Parameters parameters = Parameters.DEFAULT_WITHOUT_CONTEXT;
                setExceedVideoConstraintsIfNecessary(bundle.getBoolean(TrackSelectionParameters.keyForField(1000), parameters.exceedVideoConstraintsIfNecessary));
                setAllowVideoMixedMimeTypeAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1001), parameters.allowVideoMixedMimeTypeAdaptiveness));
                setAllowVideoNonSeamlessAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1002), parameters.allowVideoNonSeamlessAdaptiveness));
                setAllowVideoMixedDecoderSupportAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1014), parameters.allowVideoMixedDecoderSupportAdaptiveness));
                setExceedAudioConstraintsIfNecessary(bundle.getBoolean(TrackSelectionParameters.keyForField(1003), parameters.exceedAudioConstraintsIfNecessary));
                setAllowAudioMixedMimeTypeAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1004), parameters.allowAudioMixedMimeTypeAdaptiveness));
                setAllowAudioMixedSampleRateAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1005), parameters.allowAudioMixedSampleRateAdaptiveness));
                setAllowAudioMixedChannelCountAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1006), parameters.allowAudioMixedChannelCountAdaptiveness));
                setAllowAudioMixedDecoderSupportAdaptiveness(bundle.getBoolean(TrackSelectionParameters.keyForField(1015), parameters.allowAudioMixedDecoderSupportAdaptiveness));
                setConstrainAudioChannelCountToDeviceCapabilities(bundle.getBoolean(TrackSelectionParameters.keyForField(1016), parameters.constrainAudioChannelCountToDeviceCapabilities));
                setExceedRendererCapabilitiesIfNecessary(bundle.getBoolean(TrackSelectionParameters.keyForField(1007), parameters.exceedRendererCapabilitiesIfNecessary));
                setTunnelingEnabled(bundle.getBoolean(TrackSelectionParameters.keyForField(1008), parameters.tunnelingEnabled));
                setAllowMultipleAdaptiveSelections(bundle.getBoolean(TrackSelectionParameters.keyForField(1009), parameters.allowMultipleAdaptiveSelections));
                this.selectionOverrides = new SparseArray<>();
                setSelectionOverridesFromBundle(bundle);
                this.rendererDisabledFlags = makeSparseBooleanArrayFromTrueKeys(bundle.getIntArray(TrackSelectionParameters.keyForField(1013)));
            }
        }

        static {
            Parameters build = new Builder().build();
            DEFAULT_WITHOUT_CONTEXT = build;
            DEFAULT = build;
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray sparseBooleanArray, SparseBooleanArray sparseBooleanArray2) {
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i3)) < 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2) {
            int size = sparseArray.size();
            if (sparseArray2.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i3));
                if (indexOfKey < 0 || !areSelectionOverridesEqual(sparseArray.valueAt(i3), sparseArray2.valueAt(indexOfKey))) {
                    return false;
                }
            }
            return true;
        }

        public static Parameters getDefaults(Context context) {
            return new Builder(context).build();
        }

        private static int[] getKeysFromSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
            int[] iArr = new int[sparseBooleanArray.size()];
            for (int i3 = 0; i3 < sparseBooleanArray.size(); i3++) {
                iArr[i3] = sparseBooleanArray.keyAt(i3);
            }
            return iArr;
        }

        private static void putSelectionOverridesToBundle(Bundle bundle, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            SparseArray sparseArray2 = new SparseArray();
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                int keyAt = sparseArray.keyAt(i3);
                for (Map.Entry entry : sparseArray.valueAt(i3).entrySet()) {
                    SelectionOverride selectionOverride = (SelectionOverride) entry.getValue();
                    if (selectionOverride != null) {
                        sparseArray2.put(arrayList2.size(), selectionOverride);
                    }
                    arrayList2.add((TrackGroupArray) entry.getKey());
                    arrayList.add(Integer.valueOf(keyAt));
                }
                bundle.putIntArray(TrackSelectionParameters.keyForField(1010), Ints.toArray(arrayList));
                bundle.putParcelableArrayList(TrackSelectionParameters.keyForField(1011), BundleableUtil.toBundleArrayList(arrayList2));
                bundle.putSparseParcelableArray(TrackSelectionParameters.keyForField(1012), BundleableUtil.toBundleSparseArray(sparseArray2));
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Parameters.class != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            return super.equals(parameters) && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.allowVideoMixedMimeTypeAdaptiveness == parameters.allowVideoMixedMimeTypeAdaptiveness && this.allowVideoNonSeamlessAdaptiveness == parameters.allowVideoNonSeamlessAdaptiveness && this.allowVideoMixedDecoderSupportAdaptiveness == parameters.allowVideoMixedDecoderSupportAdaptiveness && this.exceedAudioConstraintsIfNecessary == parameters.exceedAudioConstraintsIfNecessary && this.allowAudioMixedMimeTypeAdaptiveness == parameters.allowAudioMixedMimeTypeAdaptiveness && this.allowAudioMixedSampleRateAdaptiveness == parameters.allowAudioMixedSampleRateAdaptiveness && this.allowAudioMixedChannelCountAdaptiveness == parameters.allowAudioMixedChannelCountAdaptiveness && this.allowAudioMixedDecoderSupportAdaptiveness == parameters.allowAudioMixedDecoderSupportAdaptiveness && this.constrainAudioChannelCountToDeviceCapabilities == parameters.constrainAudioChannelCountToDeviceCapabilities && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.tunnelingEnabled == parameters.tunnelingEnabled && this.allowMultipleAdaptiveSelections == parameters.allowMultipleAdaptiveSelections && areRendererDisabledFlagsEqual(this.rendererDisabledFlags, parameters.rendererDisabledFlags) && areSelectionOverridesEqual(this.selectionOverrides, parameters.selectionOverrides);
        }

        public boolean getRendererDisabled(int i3) {
            return this.rendererDisabledFlags.get(i3);
        }

        @Deprecated
        @Nullable
        public SelectionOverride getSelectionOverride(int i3, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i3);
            if (map != null) {
                return (SelectionOverride) map.get(trackGroupArray);
            }
            return null;
        }

        @Deprecated
        public boolean hasSelectionOverride(int i3, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i3);
            return map != null && map.containsKey(trackGroupArray);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((super.hashCode() + 31) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowVideoMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.exceedAudioConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowAudioMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedSampleRateAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedChannelCountAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.constrainAudioChannelCountToDeviceCapabilities ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.tunnelingEnabled ? 1 : 0)) * 31) + (this.allowMultipleAdaptiveSelections ? 1 : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = super.toBundle();
            bundle.putBoolean(TrackSelectionParameters.keyForField(1000), this.exceedVideoConstraintsIfNecessary);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1001), this.allowVideoMixedMimeTypeAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1002), this.allowVideoNonSeamlessAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1014), this.allowVideoMixedDecoderSupportAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1003), this.exceedAudioConstraintsIfNecessary);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1004), this.allowAudioMixedMimeTypeAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1005), this.allowAudioMixedSampleRateAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1006), this.allowAudioMixedChannelCountAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1015), this.allowAudioMixedDecoderSupportAdaptiveness);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1016), this.constrainAudioChannelCountToDeviceCapabilities);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1007), this.exceedRendererCapabilitiesIfNecessary);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1008), this.tunnelingEnabled);
            bundle.putBoolean(TrackSelectionParameters.keyForField(1009), this.allowMultipleAdaptiveSelections);
            putSelectionOverridesToBundle(bundle, this.selectionOverrides);
            bundle.putIntArray(TrackSelectionParameters.keyForField(1013), getKeysFromSparseBooleanArray(this.rendererDisabledFlags));
            return bundle;
        }

        private Parameters(Builder builder) {
            super(builder);
            this.exceedVideoConstraintsIfNecessary = builder.exceedVideoConstraintsIfNecessary;
            this.allowVideoMixedMimeTypeAdaptiveness = builder.allowVideoMixedMimeTypeAdaptiveness;
            this.allowVideoNonSeamlessAdaptiveness = builder.allowVideoNonSeamlessAdaptiveness;
            this.allowVideoMixedDecoderSupportAdaptiveness = builder.allowVideoMixedDecoderSupportAdaptiveness;
            this.exceedAudioConstraintsIfNecessary = builder.exceedAudioConstraintsIfNecessary;
            this.allowAudioMixedMimeTypeAdaptiveness = builder.allowAudioMixedMimeTypeAdaptiveness;
            this.allowAudioMixedSampleRateAdaptiveness = builder.allowAudioMixedSampleRateAdaptiveness;
            this.allowAudioMixedChannelCountAdaptiveness = builder.allowAudioMixedChannelCountAdaptiveness;
            this.allowAudioMixedDecoderSupportAdaptiveness = builder.allowAudioMixedDecoderSupportAdaptiveness;
            this.constrainAudioChannelCountToDeviceCapabilities = builder.constrainAudioChannelCountToDeviceCapabilities;
            this.exceedRendererCapabilitiesIfNecessary = builder.exceedRendererCapabilitiesIfNecessary;
            this.tunnelingEnabled = builder.tunnelingEnabled;
            this.allowMultipleAdaptiveSelections = builder.allowMultipleAdaptiveSelections;
            this.selectionOverrides = builder.selectionOverrides;
            this.rendererDisabledFlags = builder.rendererDisabledFlags;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        /* JADX WARNING: Removed duplicated region for block: B:6:0x001a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean areSelectionOverridesEqual(java.util.Map<com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray, com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.SelectionOverride> r4, java.util.Map<com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray, com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.SelectionOverride> r5) {
            /*
                int r0 = r4.size()
                int r1 = r5.size()
                r2 = 0
                if (r1 == r0) goto L_0x000c
                return r2
            L_0x000c:
                java.util.Set r4 = r4.entrySet()
                java.util.Iterator r4 = r4.iterator()
            L_0x0014:
                boolean r0 = r4.hasNext()
                if (r0 == 0) goto L_0x003b
                java.lang.Object r0 = r4.next()
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r1 = r0.getKey()
                com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray r1 = (com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray) r1
                boolean r3 = r5.containsKey(r1)
                if (r3 == 0) goto L_0x003a
                java.lang.Object r0 = r0.getValue()
                java.lang.Object r1 = r5.get(r1)
                boolean r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.areEqual(r0, r1)
                if (r0 != 0) goto L_0x0014
            L_0x003a:
                return r2
            L_0x003b:
                r4 = 1
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.Parameters.areSelectionOverridesEqual(java.util.Map, java.util.Map):boolean");
        }
    }

    @Deprecated
    public static final class ParametersBuilder extends TrackSelectionParameters.Builder {
        private final Parameters.Builder delegate;

        @Deprecated
        public ParametersBuilder() {
            this.delegate = new Parameters.Builder();
        }

        @Deprecated
        public ParametersBuilder clearSelectionOverride(int i3, TrackGroupArray trackGroupArray) {
            this.delegate.clearSelectionOverride(i3, trackGroupArray);
            return this;
        }

        @Deprecated
        public ParametersBuilder clearSelectionOverrides(int i3) {
            this.delegate.clearSelectionOverrides(i3);
            return this;
        }

        public ParametersBuilder setAllowAudioMixedChannelCountAdaptiveness(boolean z2) {
            this.delegate.setAllowAudioMixedChannelCountAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowAudioMixedDecoderSupportAdaptiveness(boolean z2) {
            this.delegate.setAllowAudioMixedDecoderSupportAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowAudioMixedMimeTypeAdaptiveness(boolean z2) {
            this.delegate.setAllowAudioMixedMimeTypeAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowAudioMixedSampleRateAdaptiveness(boolean z2) {
            this.delegate.setAllowAudioMixedSampleRateAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowMultipleAdaptiveSelections(boolean z2) {
            this.delegate.setAllowMultipleAdaptiveSelections(z2);
            return this;
        }

        public ParametersBuilder setAllowVideoMixedDecoderSupportAdaptiveness(boolean z2) {
            this.delegate.setAllowVideoMixedDecoderSupportAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowVideoMixedMimeTypeAdaptiveness(boolean z2) {
            this.delegate.setAllowVideoMixedMimeTypeAdaptiveness(z2);
            return this;
        }

        public ParametersBuilder setAllowVideoNonSeamlessAdaptiveness(boolean z2) {
            this.delegate.setAllowVideoNonSeamlessAdaptiveness(z2);
            return this;
        }

        @Deprecated
        public ParametersBuilder setDisabledTextTrackSelectionFlags(int i3) {
            this.delegate.setDisabledTextTrackSelectionFlags(i3);
            return this;
        }

        public ParametersBuilder setExceedAudioConstraintsIfNecessary(boolean z2) {
            this.delegate.setExceedAudioConstraintsIfNecessary(z2);
            return this;
        }

        public ParametersBuilder setExceedRendererCapabilitiesIfNecessary(boolean z2) {
            this.delegate.setExceedRendererCapabilitiesIfNecessary(z2);
            return this;
        }

        public ParametersBuilder setExceedVideoConstraintsIfNecessary(boolean z2) {
            this.delegate.setExceedVideoConstraintsIfNecessary(z2);
            return this;
        }

        public ParametersBuilder setRendererDisabled(int i3, boolean z2) {
            this.delegate.setRendererDisabled(i3, z2);
            return this;
        }

        @Deprecated
        public ParametersBuilder setSelectionOverride(int i3, TrackGroupArray trackGroupArray, @Nullable SelectionOverride selectionOverride) {
            this.delegate.setSelectionOverride(i3, trackGroupArray, selectionOverride);
            return this;
        }

        public ParametersBuilder setTunnelingEnabled(boolean z2) {
            this.delegate.setTunnelingEnabled(z2);
            return this;
        }

        public ParametersBuilder addOverride(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.addOverride(trackSelectionOverride);
            return this;
        }

        public Parameters build() {
            return this.delegate.build();
        }

        public ParametersBuilder clearOverride(TrackGroup trackGroup) {
            this.delegate.clearOverride(trackGroup);
            return this;
        }

        public ParametersBuilder clearOverrides() {
            this.delegate.clearOverrides();
            return this;
        }

        public ParametersBuilder clearOverridesOfType(int i3) {
            this.delegate.clearOverridesOfType(i3);
            return this;
        }

        @Deprecated
        public ParametersBuilder clearSelectionOverrides() {
            this.delegate.clearSelectionOverrides();
            return this;
        }

        public ParametersBuilder clearVideoSizeConstraints() {
            this.delegate.clearVideoSizeConstraints();
            return this;
        }

        public ParametersBuilder clearViewportSizeConstraints() {
            this.delegate.clearViewportSizeConstraints();
            return this;
        }

        public ParametersBuilder set(TrackSelectionParameters trackSelectionParameters) {
            this.delegate.set(trackSelectionParameters);
            return this;
        }

        @Deprecated
        public ParametersBuilder setDisabledTrackTypes(Set<Integer> set) {
            this.delegate.setDisabledTrackTypes((Set) set);
            return this;
        }

        public ParametersBuilder setForceHighestSupportedBitrate(boolean z2) {
            this.delegate.setForceHighestSupportedBitrate(z2);
            return this;
        }

        public ParametersBuilder setForceLowestBitrate(boolean z2) {
            this.delegate.setForceLowestBitrate(z2);
            return this;
        }

        public ParametersBuilder setIgnoredTextSelectionFlags(int i3) {
            this.delegate.setIgnoredTextSelectionFlags(i3);
            return this;
        }

        public ParametersBuilder setMaxAudioBitrate(int i3) {
            this.delegate.setMaxAudioBitrate(i3);
            return this;
        }

        public ParametersBuilder setMaxAudioChannelCount(int i3) {
            this.delegate.setMaxAudioChannelCount(i3);
            return this;
        }

        public ParametersBuilder setMaxVideoBitrate(int i3) {
            this.delegate.setMaxVideoBitrate(i3);
            return this;
        }

        public ParametersBuilder setMaxVideoFrameRate(int i3) {
            this.delegate.setMaxVideoFrameRate(i3);
            return this;
        }

        public ParametersBuilder setMaxVideoSize(int i3, int i4) {
            this.delegate.setMaxVideoSize(i3, i4);
            return this;
        }

        public ParametersBuilder setMaxVideoSizeSd() {
            this.delegate.setMaxVideoSizeSd();
            return this;
        }

        public ParametersBuilder setMinVideoBitrate(int i3) {
            this.delegate.setMinVideoBitrate(i3);
            return this;
        }

        public ParametersBuilder setMinVideoFrameRate(int i3) {
            this.delegate.setMinVideoFrameRate(i3);
            return this;
        }

        public ParametersBuilder setMinVideoSize(int i3, int i4) {
            this.delegate.setMinVideoSize(i3, i4);
            return this;
        }

        public ParametersBuilder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.setOverrideForType(trackSelectionOverride);
            return this;
        }

        public ParametersBuilder setPreferredAudioLanguage(@Nullable String str) {
            this.delegate.setPreferredAudioLanguage(str);
            return this;
        }

        public ParametersBuilder setPreferredAudioLanguages(String... strArr) {
            this.delegate.setPreferredAudioLanguages(strArr);
            return this;
        }

        public ParametersBuilder setPreferredAudioMimeType(@Nullable String str) {
            this.delegate.setPreferredAudioMimeType(str);
            return this;
        }

        public ParametersBuilder setPreferredAudioMimeTypes(String... strArr) {
            this.delegate.setPreferredAudioMimeTypes(strArr);
            return this;
        }

        public ParametersBuilder setPreferredAudioRoleFlags(int i3) {
            this.delegate.setPreferredAudioRoleFlags(i3);
            return this;
        }

        public ParametersBuilder setPreferredTextLanguage(@Nullable String str) {
            this.delegate.setPreferredTextLanguage(str);
            return this;
        }

        public ParametersBuilder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            this.delegate.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
            return this;
        }

        public ParametersBuilder setPreferredTextLanguages(String... strArr) {
            this.delegate.setPreferredTextLanguages(strArr);
            return this;
        }

        public ParametersBuilder setPreferredTextRoleFlags(int i3) {
            this.delegate.setPreferredTextRoleFlags(i3);
            return this;
        }

        public ParametersBuilder setPreferredVideoMimeType(@Nullable String str) {
            this.delegate.setPreferredVideoMimeType(str);
            return this;
        }

        public ParametersBuilder setPreferredVideoMimeTypes(String... strArr) {
            this.delegate.setPreferredVideoMimeTypes(strArr);
            return this;
        }

        public ParametersBuilder setPreferredVideoRoleFlags(int i3) {
            this.delegate.setPreferredVideoRoleFlags(i3);
            return this;
        }

        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean z2) {
            this.delegate.setSelectUndeterminedTextLanguage(z2);
            return this;
        }

        public ParametersBuilder setTrackTypeDisabled(int i3, boolean z2) {
            this.delegate.setTrackTypeDisabled(i3, z2);
            return this;
        }

        public ParametersBuilder setViewportSize(int i3, int i4, boolean z2) {
            this.delegate.setViewportSize(i3, i4, z2);
            return this;
        }

        public ParametersBuilder setViewportSizeToPhysicalDisplaySize(Context context, boolean z2) {
            this.delegate.setViewportSizeToPhysicalDisplaySize(context, z2);
            return this;
        }

        public ParametersBuilder(Context context) {
            this.delegate = new Parameters.Builder(context);
        }
    }

    public static final class SelectionOverride implements Bundleable {
        public static final Bundleable.Creator<SelectionOverride> CREATOR = new c(1);
        private static final int FIELD_GROUP_INDEX = 0;
        private static final int FIELD_TRACKS = 1;
        private static final int FIELD_TRACK_TYPE = 2;
        public final int groupIndex;
        public final int length;
        public final int[] tracks;
        public final int type;

        public SelectionOverride(int i3, int... iArr) {
            this(i3, iArr, 0);
        }

        private static String keyForField(int i3) {
            return Integer.toString(i3, 36);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ SelectionOverride lambda$static$0(Bundle bundle) {
            boolean z2 = false;
            int i3 = bundle.getInt(keyForField(0), -1);
            int[] intArray = bundle.getIntArray(keyForField(1));
            int i4 = bundle.getInt(keyForField(2), -1);
            if (i3 >= 0 && i4 >= 0) {
                z2 = true;
            }
            Assertions.checkArgument(z2);
            Assertions.checkNotNull(intArray);
            return new SelectionOverride(i3, intArray, i4);
        }

        public boolean containsTrack(int i3) {
            for (int i4 : this.tracks) {
                if (i4 == i3) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SelectionOverride.class != obj.getClass()) {
                return false;
            }
            SelectionOverride selectionOverride = (SelectionOverride) obj;
            return this.groupIndex == selectionOverride.groupIndex && Arrays.equals(this.tracks, selectionOverride.tracks) && this.type == selectionOverride.type;
        }

        public int hashCode() {
            return ((Arrays.hashCode(this.tracks) + (this.groupIndex * 31)) * 31) + this.type;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(keyForField(0), this.groupIndex);
            bundle.putIntArray(keyForField(1), this.tracks);
            bundle.putInt(keyForField(2), this.type);
            return bundle;
        }

        public SelectionOverride(int i3, int[] iArr, int i4) {
            this.groupIndex = i3;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            this.tracks = copyOf;
            this.length = iArr.length;
            this.type = i4;
            Arrays.sort(copyOf);
        }
    }

    @RequiresApi(32)
    public static class SpatializerWrapperV32 {
        @Nullable
        private Handler handler;
        @Nullable
        private Spatializer$OnSpatializerStateChangedListener listener;
        private final boolean spatializationSupported;
        private final Spatializer spatializer;

        private SpatializerWrapperV32(Spatializer spatializer2) {
            this.spatializer = spatializer2;
            this.spatializationSupported = spatializer2.getImmersiveAudioLevel() != 0;
        }

        @Nullable
        public static SpatializerWrapperV32 tryCreateInstance(Context context) {
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            if (audioManager == null) {
                return null;
            }
            return new SpatializerWrapperV32(audioManager.getSpatializer());
        }

        public boolean canBeSpatialized(AudioAttributes audioAttributes, Format format) {
            AudioFormat.Builder channelMask = new AudioFormat.Builder().setEncoding(2).setChannelMask(Util.getAudioTrackChannelConfig((!MimeTypes.AUDIO_E_AC3_JOC.equals(format.sampleMimeType) || format.channelCount != 16) ? format.channelCount : 12));
            int i3 = format.sampleRate;
            if (i3 != -1) {
                channelMask.setSampleRate(i3);
            }
            return this.spatializer.canBeSpatialized(audioAttributes.getAudioAttributesV21().audioAttributes, channelMask.build());
        }

        public void ensureInitialized(final DefaultTrackSelector defaultTrackSelector, Looper looper) {
            if (this.listener == null && this.handler == null) {
                this.listener = new Spatializer$OnSpatializerStateChangedListener() {
                    public void onSpatializerAvailableChanged(Spatializer spatializer, boolean z2) {
                        defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                    }

                    public void onSpatializerEnabledChanged(Spatializer spatializer, boolean z2) {
                        defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                    }
                };
                Handler handler2 = new Handler(looper);
                this.handler = handler2;
                this.spatializer.addOnSpatializerStateChangedListener(new a(handler2), this.listener);
            }
        }

        public boolean isAvailable() {
            return this.spatializer.isAvailable();
        }

        public boolean isEnabled() {
            return this.spatializer.isEnabled();
        }

        public boolean isSpatializationSupported() {
            return this.spatializationSupported;
        }

        public void release() {
            Spatializer$OnSpatializerStateChangedListener spatializer$OnSpatializerStateChangedListener = this.listener;
            if (spatializer$OnSpatializerStateChangedListener != null && this.handler != null) {
                this.spatializer.removeOnSpatializerStateChangedListener(spatializer$OnSpatializerStateChangedListener);
                ((Handler) Util.castNonNull(this.handler)).removeCallbacksAndMessages((Object) null);
                this.handler = null;
                this.listener = null;
            }
        }
    }

    public static final class TextTrackInfo extends TrackInfo<TextTrackInfo> implements Comparable<TextTrackInfo> {
        private final boolean hasCaptionRoleFlags;
        private final boolean isDefault;
        private final boolean isForced;
        private final boolean isWithinRendererCapabilities;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredRoleFlagsScore;
        private final int selectedAudioLanguageScore;
        private final int selectionEligibility;

        public TextTrackInfo(int i3, TrackGroup trackGroup, int i4, Parameters parameters, int i5, @Nullable String str) {
            super(i3, trackGroup, i4);
            int i6;
            int i7 = 0;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i5, false);
            int i8 = this.format.selectionFlags & (~parameters.ignoredTextSelectionFlags);
            this.isDefault = (i8 & 1) != 0;
            this.isForced = (i8 & 2) != 0;
            ImmutableList<String> of = parameters.preferredTextLanguages.isEmpty() ? ImmutableList.of("") : parameters.preferredTextLanguages;
            int i9 = 0;
            while (true) {
                if (i9 >= of.size()) {
                    i9 = Integer.MAX_VALUE;
                    i6 = 0;
                    break;
                }
                i6 = DefaultTrackSelector.getFormatLanguageScore(this.format, of.get(i9), parameters.selectUndeterminedTextLanguage);
                if (i6 > 0) {
                    break;
                }
                i9++;
            }
            this.preferredLanguageIndex = i9;
            this.preferredLanguageScore = i6;
            int access$3800 = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, parameters.preferredTextRoleFlags);
            this.preferredRoleFlagsScore = access$3800;
            this.hasCaptionRoleFlags = (this.format.roleFlags & 1088) != 0;
            int formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(this.format, str, DefaultTrackSelector.normalizeUndeterminedLanguageToNull(str) == null);
            this.selectedAudioLanguageScore = formatLanguageScore;
            boolean z2 = i6 > 0 || (parameters.preferredTextLanguages.isEmpty() && access$3800 > 0) || this.isDefault || (this.isForced && formatLanguageScore > 0);
            if (DefaultTrackSelector.isSupported(i5, parameters.exceedRendererCapabilitiesIfNecessary) && z2) {
                i7 = 1;
            }
            this.selectionEligibility = i7;
        }

        public static int compareSelections(List<TextTrackInfo> list, List<TextTrackInfo> list2) {
            return list.get(0).compareTo(list2.get(0));
        }

        public static ImmutableList<TextTrackInfo> createForTrackGroup(int i3, TrackGroup trackGroup, Parameters parameters, int[] iArr, @Nullable String str) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i4 = 0; i4 < trackGroup.length; i4++) {
                builder.add((Object) new TextTrackInfo(i3, trackGroup, i4, parameters, iArr[i4], str));
            }
            return builder.build();
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(TextTrackInfo textTrackInfo) {
            return false;
        }

        public int compareTo(TextTrackInfo textTrackInfo) {
            ComparisonChain compare = ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, textTrackInfo.isWithinRendererCapabilities).compare(Integer.valueOf(this.preferredLanguageIndex), Integer.valueOf(textTrackInfo.preferredLanguageIndex), Ordering.natural().reverse()).compare(this.preferredLanguageScore, textTrackInfo.preferredLanguageScore).compare(this.preferredRoleFlagsScore, textTrackInfo.preferredRoleFlagsScore).compareFalseFirst(this.isDefault, textTrackInfo.isDefault).compare(Boolean.valueOf(this.isForced), Boolean.valueOf(textTrackInfo.isForced), this.preferredLanguageScore == 0 ? Ordering.natural() : Ordering.natural().reverse()).compare(this.selectedAudioLanguageScore, textTrackInfo.selectedAudioLanguageScore);
            if (this.preferredRoleFlagsScore == 0) {
                compare = compare.compareTrueFirst(this.hasCaptionRoleFlags, textTrackInfo.hasCaptionRoleFlags);
            }
            return compare.result();
        }
    }

    public static abstract class TrackInfo<T extends TrackInfo<T>> {
        public final Format format;
        public final int rendererIndex;
        public final TrackGroup trackGroup;
        public final int trackIndex;

        public interface Factory<T extends TrackInfo<T>> {
            List<T> create(int i3, TrackGroup trackGroup, int[] iArr);
        }

        public TrackInfo(int i3, TrackGroup trackGroup2, int i4) {
            this.rendererIndex = i3;
            this.trackGroup = trackGroup2;
            this.trackIndex = i4;
            this.format = trackGroup2.getFormat(i4);
        }

        public abstract int getSelectionEligibility();

        public abstract boolean isCompatibleForAdaptationWith(T t2);
    }

    public static final class VideoTrackInfo extends TrackInfo<VideoTrackInfo> {
        private final boolean allowMixedMimeTypes;
        private final int bitrate;
        private final int codecPreferenceScore;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean isWithinMaxConstraints;
        private final boolean isWithinMinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final Parameters parameters;
        private final int pixelCount;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        /* JADX WARNING: Removed duplicated region for block: B:54:0x00a4  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00a6  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00b2  */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00d5  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00d7  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x00e2  */
        /* JADX WARNING: Removed duplicated region for block: B:74:0x00c8 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public VideoTrackInfo(int r5, com.appsamurai.storyly.exoplayer2.common.source.TrackGroup r6, int r7, com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.Parameters r8, int r9, int r10, boolean r11) {
            /*
                r4 = this;
                r4.<init>(r5, r6, r7)
                r4.parameters = r8
                boolean r5 = r8.allowVideoNonSeamlessAdaptiveness
                if (r5 == 0) goto L_0x000c
                r5 = 24
                goto L_0x000e
            L_0x000c:
                r5 = 16
            L_0x000e:
                boolean r6 = r8.allowVideoMixedMimeTypeAdaptiveness
                r7 = 0
                r0 = 1
                if (r6 == 0) goto L_0x001a
                r6 = r10 & r5
                if (r6 == 0) goto L_0x001a
                r6 = r0
                goto L_0x001b
            L_0x001a:
                r6 = r7
            L_0x001b:
                r4.allowMixedMimeTypes = r6
                r6 = -1082130432(0xffffffffbf800000, float:-1.0)
                r10 = -1
                if (r11 == 0) goto L_0x004b
                com.appsamurai.storyly.exoplayer2.common.Format r1 = r4.format
                int r2 = r1.width
                if (r2 == r10) goto L_0x002c
                int r3 = r8.maxVideoWidth
                if (r2 > r3) goto L_0x004b
            L_0x002c:
                int r2 = r1.height
                if (r2 == r10) goto L_0x0034
                int r3 = r8.maxVideoHeight
                if (r2 > r3) goto L_0x004b
            L_0x0034:
                float r2 = r1.frameRate
                int r3 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r3 == 0) goto L_0x0041
                int r3 = r8.maxVideoFrameRate
                float r3 = (float) r3
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 > 0) goto L_0x004b
            L_0x0041:
                int r1 = r1.bitrate
                if (r1 == r10) goto L_0x0049
                int r2 = r8.maxVideoBitrate
                if (r1 > r2) goto L_0x004b
            L_0x0049:
                r1 = r0
                goto L_0x004c
            L_0x004b:
                r1 = r7
            L_0x004c:
                r4.isWithinMaxConstraints = r1
                if (r11 == 0) goto L_0x0079
                com.appsamurai.storyly.exoplayer2.common.Format r11 = r4.format
                int r1 = r11.width
                if (r1 == r10) goto L_0x005a
                int r2 = r8.minVideoWidth
                if (r1 < r2) goto L_0x0079
            L_0x005a:
                int r1 = r11.height
                if (r1 == r10) goto L_0x0062
                int r2 = r8.minVideoHeight
                if (r1 < r2) goto L_0x0079
            L_0x0062:
                float r1 = r11.frameRate
                int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r6 == 0) goto L_0x006f
                int r6 = r8.minVideoFrameRate
                float r6 = (float) r6
                int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r6 < 0) goto L_0x0079
            L_0x006f:
                int r6 = r11.bitrate
                if (r6 == r10) goto L_0x0077
                int r10 = r8.minVideoBitrate
                if (r6 < r10) goto L_0x0079
            L_0x0077:
                r6 = r0
                goto L_0x007a
            L_0x0079:
                r6 = r7
            L_0x007a:
                r4.isWithinMinConstraints = r6
                boolean r6 = com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.isSupported(r9, r7)
                r4.isWithinRendererCapabilities = r6
                com.appsamurai.storyly.exoplayer2.common.Format r6 = r4.format
                int r10 = r6.bitrate
                r4.bitrate = r10
                int r6 = r6.getPixelCount()
                r4.pixelCount = r6
                com.appsamurai.storyly.exoplayer2.common.Format r6 = r4.format
                int r6 = r6.roleFlags
                int r10 = r8.preferredVideoRoleFlags
                int r6 = com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.getRoleFlagMatchScore(r6, r10)
                r4.preferredRoleFlagsScore = r6
                com.appsamurai.storyly.exoplayer2.common.Format r6 = r4.format
                int r6 = r6.roleFlags
                if (r6 == 0) goto L_0x00a6
                r6 = r6 & r0
                if (r6 == 0) goto L_0x00a4
                goto L_0x00a6
            L_0x00a4:
                r6 = r7
                goto L_0x00a7
            L_0x00a6:
                r6 = r0
            L_0x00a7:
                r4.hasMainOrNoRoleFlag = r6
                r6 = r7
            L_0x00aa:
                com.google.common.collect.ImmutableList<java.lang.String> r10 = r8.preferredVideoMimeTypes
                int r10 = r10.size()
                if (r6 >= r10) goto L_0x00c8
                com.appsamurai.storyly.exoplayer2.common.Format r10 = r4.format
                java.lang.String r10 = r10.sampleMimeType
                if (r10 == 0) goto L_0x00c5
                com.google.common.collect.ImmutableList<java.lang.String> r11 = r8.preferredVideoMimeTypes
                java.lang.Object r11 = r11.get(r6)
                boolean r10 = r10.equals(r11)
                if (r10 == 0) goto L_0x00c5
                goto L_0x00cb
            L_0x00c5:
                int r6 = r6 + 1
                goto L_0x00aa
            L_0x00c8:
                r6 = 2147483647(0x7fffffff, float:NaN)
            L_0x00cb:
                r4.preferredMimeTypeMatchIndex = r6
                int r6 = com.appsamurai.storyly.exoplayer2.core.RendererCapabilities.getDecoderSupport(r9)
                r8 = 128(0x80, float:1.794E-43)
                if (r6 != r8) goto L_0x00d7
                r6 = r0
                goto L_0x00d8
            L_0x00d7:
                r6 = r7
            L_0x00d8:
                r4.usesPrimaryDecoder = r6
                int r6 = com.appsamurai.storyly.exoplayer2.core.RendererCapabilities.getHardwareAccelerationSupport(r9)
                r8 = 64
                if (r6 != r8) goto L_0x00e3
                r7 = r0
            L_0x00e3:
                r4.usesHardwareAcceleration = r7
                com.appsamurai.storyly.exoplayer2.common.Format r6 = r4.format
                java.lang.String r6 = r6.sampleMimeType
                int r6 = com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.getVideoCodecPreferenceScore(r6)
                r4.codecPreferenceScore = r6
                int r5 = r4.evaluateSelectionEligibility(r9, r5)
                r4.selectionEligibility = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.VideoTrackInfo.<init>(int, com.appsamurai.storyly.exoplayer2.common.source.TrackGroup, int, com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector$Parameters, int, int, boolean):void");
        }

        /* access modifiers changed from: private */
        public static int compareNonQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            ComparisonChain compareFalseFirst = ComparisonChain.start().compareFalseFirst(videoTrackInfo.isWithinRendererCapabilities, videoTrackInfo2.isWithinRendererCapabilities).compare(videoTrackInfo.preferredRoleFlagsScore, videoTrackInfo2.preferredRoleFlagsScore).compareFalseFirst(videoTrackInfo.hasMainOrNoRoleFlag, videoTrackInfo2.hasMainOrNoRoleFlag).compareFalseFirst(videoTrackInfo.isWithinMaxConstraints, videoTrackInfo2.isWithinMaxConstraints).compareFalseFirst(videoTrackInfo.isWithinMinConstraints, videoTrackInfo2.isWithinMinConstraints).compare(Integer.valueOf(videoTrackInfo.preferredMimeTypeMatchIndex), Integer.valueOf(videoTrackInfo2.preferredMimeTypeMatchIndex), Ordering.natural().reverse()).compareFalseFirst(videoTrackInfo.usesPrimaryDecoder, videoTrackInfo2.usesPrimaryDecoder).compareFalseFirst(videoTrackInfo.usesHardwareAcceleration, videoTrackInfo2.usesHardwareAcceleration);
            if (videoTrackInfo.usesPrimaryDecoder && videoTrackInfo.usesHardwareAcceleration) {
                compareFalseFirst = compareFalseFirst.compare(videoTrackInfo.codecPreferenceScore, videoTrackInfo2.codecPreferenceScore);
            }
            return compareFalseFirst.result();
        }

        /* access modifiers changed from: private */
        public static int compareQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            Ordering reverse = (!videoTrackInfo.isWithinMaxConstraints || !videoTrackInfo.isWithinRendererCapabilities) ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse() : DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            return ComparisonChain.start().compare(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), videoTrackInfo.parameters.forceLowestBitrate ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse() : DefaultTrackSelector.NO_ORDER).compare(Integer.valueOf(videoTrackInfo.pixelCount), Integer.valueOf(videoTrackInfo2.pixelCount), reverse).compare(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), reverse).result();
        }

        public static int compareSelections(List<VideoTrackInfo> list, List<VideoTrackInfo> list2) {
            return ComparisonChain.start().compare((VideoTrackInfo) Collections.max(list, new a(3)), (VideoTrackInfo) Collections.max(list2, new a(3)), new a(3)).compare(list.size(), list2.size()).compare((VideoTrackInfo) Collections.max(list, new a(4)), (VideoTrackInfo) Collections.max(list2, new a(4)), new a(4)).result();
        }

        public static ImmutableList<VideoTrackInfo> createForTrackGroup(int i3, TrackGroup trackGroup, Parameters parameters2, int[] iArr, int i4) {
            TrackGroup trackGroup2 = trackGroup;
            Parameters parameters3 = parameters2;
            int access$3700 = DefaultTrackSelector.getMaxVideoPixelsToRetainForViewport(trackGroup2, parameters3.viewportWidth, parameters3.viewportHeight, parameters3.viewportOrientationMayChange);
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                int pixelCount2 = trackGroup2.getFormat(i5).getPixelCount();
                builder.add((Object) new VideoTrackInfo(i3, trackGroup, i5, parameters2, iArr[i5], i4, access$3700 == Integer.MAX_VALUE || (pixelCount2 != -1 && pixelCount2 <= access$3700)));
            }
            return builder.build();
        }

        private int evaluateSelectionEligibility(int i3, int i4) {
            if ((this.format.roleFlags & 16384) != 0 || !DefaultTrackSelector.isSupported(i3, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinMaxConstraints && !this.parameters.exceedVideoConstraintsIfNecessary) {
                return 0;
            }
            if (DefaultTrackSelector.isSupported(i3, false) && this.isWithinMinConstraints && this.isWithinMaxConstraints && this.format.bitrate != -1) {
                Parameters parameters2 = this.parameters;
                return (parameters2.forceHighestSupportedBitrate || parameters2.forceLowestBitrate || (i3 & i4) == 0) ? 1 : 2;
            }
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(VideoTrackInfo videoTrackInfo) {
            return (this.allowMixedMimeTypes || Util.areEqual(this.format.sampleMimeType, videoTrackInfo.format.sampleMimeType)) && (this.parameters.allowVideoMixedDecoderSupportAdaptiveness || (this.usesPrimaryDecoder == videoTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == videoTrackInfo.usesHardwareAcceleration));
        }
    }

    @Deprecated
    public DefaultTrackSelector() {
        this((TrackSelectionParameters) Parameters.DEFAULT_WITHOUT_CONTEXT, (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
    }

    private static void applyLegacyRendererOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, Parameters parameters2, ExoTrackSelection.Definition[] definitionArr) {
        int rendererCount = mappedTrackInfo.getRendererCount();
        for (int i3 = 0; i3 < rendererCount; i3++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i3);
            if (parameters2.hasSelectionOverride(i3, trackGroups)) {
                SelectionOverride selectionOverride = parameters2.getSelectionOverride(i3, trackGroups);
                definitionArr[i3] = (selectionOverride == null || selectionOverride.tracks.length == 0) ? null : new ExoTrackSelection.Definition(trackGroups.get(selectionOverride.groupIndex), selectionOverride.tracks, selectionOverride.type);
            }
        }
    }

    private static void applyTrackSelectionOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Definition[] definitionArr) {
        int rendererCount = mappedTrackInfo.getRendererCount();
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < rendererCount; i3++) {
            collectTrackSelectionOverrides(mappedTrackInfo.getTrackGroups(i3), trackSelectionParameters, hashMap);
        }
        collectTrackSelectionOverrides(mappedTrackInfo.getUnmappedTrackGroups(), trackSelectionParameters, hashMap);
        for (int i4 = 0; i4 < rendererCount; i4++) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) hashMap.get(Integer.valueOf(mappedTrackInfo.getRendererType(i4)));
            if (trackSelectionOverride != null) {
                definitionArr[i4] = (trackSelectionOverride.trackIndices.isEmpty() || mappedTrackInfo.getTrackGroups(i4).indexOf(trackSelectionOverride.mediaTrackGroup) == -1) ? null : new ExoTrackSelection.Definition(trackSelectionOverride.mediaTrackGroup, Ints.toArray(trackSelectionOverride.trackIndices));
            }
        }
    }

    private static void collectTrackSelectionOverrides(TrackGroupArray trackGroupArray, TrackSelectionParameters trackSelectionParameters, Map<Integer, TrackSelectionOverride> map) {
        TrackSelectionOverride trackSelectionOverride;
        for (int i3 = 0; i3 < trackGroupArray.length; i3++) {
            TrackSelectionOverride trackSelectionOverride2 = trackSelectionParameters.overrides.get(trackGroupArray.get(i3));
            if (trackSelectionOverride2 != null && ((trackSelectionOverride = map.get(Integer.valueOf(trackSelectionOverride2.getType()))) == null || (trackSelectionOverride.trackIndices.isEmpty() && !trackSelectionOverride2.trackIndices.isEmpty()))) {
                map.put(Integer.valueOf(trackSelectionOverride2.getType()), trackSelectionOverride2);
            }
        }
    }

    public static int getFormatLanguageScore(Format format, @Nullable String str, boolean z2) {
        if (!TextUtils.isEmpty(str) && str.equals(format.language)) {
            return 4;
        }
        String normalizeUndeterminedLanguageToNull = normalizeUndeterminedLanguageToNull(str);
        String normalizeUndeterminedLanguageToNull2 = normalizeUndeterminedLanguageToNull(format.language);
        if (normalizeUndeterminedLanguageToNull2 == null || normalizeUndeterminedLanguageToNull == null) {
            return (!z2 || normalizeUndeterminedLanguageToNull2 != null) ? 0 : 1;
        }
        if (normalizeUndeterminedLanguageToNull2.startsWith(normalizeUndeterminedLanguageToNull) || normalizeUndeterminedLanguageToNull.startsWith(normalizeUndeterminedLanguageToNull2)) {
            return 3;
        }
        return Util.splitAtFirst(normalizeUndeterminedLanguageToNull2, "-")[0].equals(Util.splitAtFirst(normalizeUndeterminedLanguageToNull, "-")[0]) ? 2 : 0;
    }

    /* access modifiers changed from: private */
    public static int getMaxVideoPixelsToRetainForViewport(TrackGroup trackGroup, int i3, int i4, boolean z2) {
        int i5;
        int i6 = Integer.MAX_VALUE;
        if (!(i3 == Integer.MAX_VALUE || i4 == Integer.MAX_VALUE)) {
            for (int i7 = 0; i7 < trackGroup.length; i7++) {
                Format format = trackGroup.getFormat(i7);
                int i8 = format.width;
                if (i8 > 0 && (i5 = format.height) > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z2, i3, i4, i8, i5);
                    int i9 = format.width;
                    int i10 = format.height;
                    int i11 = i9 * i10;
                    if (i9 >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i10 >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i11 < i6) {
                        i6 = i11;
                    }
                }
            }
        }
        return i6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r1 != r3) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x000f
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L_0x0008
            r1 = r0
            goto L_0x0009
        L_0x0008:
            r1 = r3
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            r3 = r0
        L_0x000c:
            if (r1 == r3) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0012:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0022
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.appsamurai.storyly.exoplayer2.common.util.Util.ceilDivide((int) r0, (int) r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0022:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.appsamurai.storyly.exoplayer2.common.util.Util.ceilDivide((int) r3, (int) r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    /* access modifiers changed from: private */
    public static int getRoleFlagMatchScore(int i3, int i4) {
        if (i3 == 0 || i3 != i4) {
            return Integer.bitCount(i3 & i4);
        }
        return Integer.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static int getVideoCodecPreferenceScore(@Nullable String str) {
        if (str == null) {
            return 0;
        }
        char c3 = 65535;
        switch (str.hashCode()) {
            case -1662735862:
                if (str.equals(MimeTypes.VIDEO_AV1)) {
                    c3 = 0;
                    break;
                }
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    c3 = 1;
                    break;
                }
                break;
            case 1331836730:
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    c3 = 2;
                    break;
                }
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    c3 = 3;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return 4;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    public boolean isAudioFormatWithinAudioChannelCountConstraints(Format format) {
        boolean z2;
        SpatializerWrapperV32 spatializerWrapperV32;
        SpatializerWrapperV32 spatializerWrapperV322;
        synchronized (this.lock) {
            try {
                if (this.parameters.constrainAudioChannelCountToDeviceCapabilities && !this.deviceIsTV && format.channelCount > 2) {
                    if (isDolbyAudio(format)) {
                        if (Util.SDK_INT >= 32 && (spatializerWrapperV322 = this.spatializer) != null && spatializerWrapperV322.isSpatializationSupported()) {
                        }
                    }
                    if (Util.SDK_INT < 32 || (spatializerWrapperV32 = this.spatializer) == null || !spatializerWrapperV32.isSpatializationSupported() || !this.spatializer.isAvailable() || !this.spatializer.isEnabled() || !this.spatializer.canBeSpatialized(this.audioAttributes, format)) {
                        z2 = false;
                    }
                }
                z2 = true;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    private static boolean isDolbyAudio(Format format) {
        String str = format.sampleMimeType;
        if (str == null) {
            return false;
        }
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals(MimeTypes.AUDIO_E_AC3_JOC)) {
                    c3 = 0;
                    break;
                }
                break;
            case 187078296:
                if (str.equals(MimeTypes.AUDIO_AC3)) {
                    c3 = 1;
                    break;
                }
                break;
            case 187078297:
                if (str.equals(MimeTypes.AUDIO_AC4)) {
                    c3 = 2;
                    break;
                }
                break;
            case 1504578661:
                if (str.equals(MimeTypes.AUDIO_E_AC3)) {
                    c3 = 3;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupported(int i3, boolean z2) {
        int formatSupport = RendererCapabilities.getFormatSupport(i3);
        return formatSupport == 4 || (z2 && formatSupport == 3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$selectAudioTrack$3(Parameters parameters2, boolean z2, int i3, TrackGroup trackGroup, int[] iArr) {
        return AudioTrackInfo.createForTrackGroup(i3, trackGroup, parameters2, iArr, z2, new b(this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Integer num, Integer num2) {
        if (num.intValue() == -1) {
            return num2.intValue() == -1 ? 0 : -1;
        }
        if (num2.intValue() == -1) {
            return 1;
        }
        return num.intValue() - num2.intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$1(Integer num, Integer num2) {
        return 0;
    }

    private static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr) {
        boolean z2;
        boolean z3 = false;
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            if (i5 >= mappedTrackInfo.getRendererCount()) {
                z2 = true;
                break;
            }
            int rendererType = mappedTrackInfo.getRendererType(i5);
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i5];
            if ((rendererType == 1 || rendererType == 2) && exoTrackSelection != null && rendererSupportsTunneling(iArr[i5], mappedTrackInfo.getTrackGroups(i5), exoTrackSelection)) {
                if (rendererType == 1) {
                    if (i4 != -1) {
                        break;
                    }
                    i4 = i5;
                } else if (i3 != -1) {
                    break;
                } else {
                    i3 = i5;
                }
            }
            i5++;
        }
        z2 = false;
        if (!(i4 == -1 || i3 == -1)) {
            z3 = true;
        }
        if (z2 && z3) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(true);
            rendererConfigurationArr[i4] = rendererConfiguration;
            rendererConfigurationArr[i3] = rendererConfiguration;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r1 = r3.spatializer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void maybeInvalidateForAudioChannelCountConstraints() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector$Parameters r1 = r3.parameters     // Catch:{ all -> 0x001f }
            boolean r1 = r1.constrainAudioChannelCountToDeviceCapabilities     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0021
            boolean r1 = r3.deviceIsTV     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x0021
            int r1 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT     // Catch:{ all -> 0x001f }
            r2 = 32
            if (r1 < r2) goto L_0x0021
            com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector$SpatializerWrapperV32 r1 = r3.spatializer     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0021
            boolean r1 = r1.isSpatializationSupported()     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0021
            r1 = 1
            goto L_0x0022
        L_0x001f:
            r3 = move-exception
            goto L_0x0029
        L_0x0021:
            r1 = 0
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0028
            r3.invalidate()
        L_0x0028:
            return
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints():void");
    }

    @Nullable
    public static String normalizeUndeterminedLanguageToNull(@Nullable String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, C.LANGUAGE_UNDETERMINED)) {
            return null;
        }
        return str;
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, ExoTrackSelection exoTrackSelection) {
        if (exoTrackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
        for (int i3 = 0; i3 < exoTrackSelection.length(); i3++) {
            if (RendererCapabilities.getTunnelingSupport(iArr[indexOf][exoTrackSelection.getIndexInTrackGroup(i3)]) != 32) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    private <T extends TrackInfo<T>> Pair<ExoTrackSelection.Definition, Integer> selectTracksForType(int i3, MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, TrackInfo.Factory<T> factory, Comparator<List<T>> comparator) {
        int i4;
        Object obj;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        ArrayList arrayList = new ArrayList();
        int rendererCount = mappedTrackInfo.getRendererCount();
        int i5 = 0;
        while (i5 < rendererCount) {
            if (i3 == mappedTrackInfo2.getRendererType(i5)) {
                TrackGroupArray trackGroups = mappedTrackInfo2.getTrackGroups(i5);
                int i6 = 0;
                while (i6 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i6);
                    List<T> create = factory.create(i5, trackGroup, iArr[i5][i6]);
                    boolean[] zArr = new boolean[trackGroup.length];
                    int i7 = 0;
                    while (i7 < trackGroup.length) {
                        TrackInfo trackInfo = (TrackInfo) create.get(i7);
                        int selectionEligibility = trackInfo.getSelectionEligibility();
                        if (zArr[i7] || selectionEligibility == 0) {
                            i4 = rendererCount;
                        } else {
                            if (selectionEligibility == 1) {
                                obj = ImmutableList.of(trackInfo);
                                i4 = rendererCount;
                            } else {
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(trackInfo);
                                int i8 = i7 + 1;
                                while (i8 < trackGroup.length) {
                                    TrackInfo trackInfo2 = (TrackInfo) create.get(i8);
                                    int i9 = rendererCount;
                                    if (trackInfo2.getSelectionEligibility() == 2 && trackInfo.isCompatibleForAdaptationWith(trackInfo2)) {
                                        arrayList2.add(trackInfo2);
                                        zArr[i8] = true;
                                    }
                                    i8++;
                                    MappingTrackSelector.MappedTrackInfo mappedTrackInfo3 = mappedTrackInfo;
                                    rendererCount = i9;
                                }
                                i4 = rendererCount;
                                obj = arrayList2;
                            }
                            arrayList.add(obj);
                        }
                        i7++;
                        MappingTrackSelector.MappedTrackInfo mappedTrackInfo4 = mappedTrackInfo;
                        rendererCount = i4;
                    }
                    int i10 = rendererCount;
                    i6++;
                    MappingTrackSelector.MappedTrackInfo mappedTrackInfo5 = mappedTrackInfo;
                }
            }
            TrackInfo.Factory<T> factory2 = factory;
            i5++;
            mappedTrackInfo2 = mappedTrackInfo;
            rendererCount = rendererCount;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        List list = (List) Collections.max(arrayList, comparator);
        int[] iArr2 = new int[list.size()];
        for (int i11 = 0; i11 < list.size(); i11++) {
            iArr2[i11] = ((TrackInfo) list.get(i11)).trackIndex;
        }
        TrackInfo trackInfo3 = (TrackInfo) list.get(0);
        return Pair.create(new ExoTrackSelection.Definition(trackInfo3.trackGroup, iArr2), Integer.valueOf(trackInfo3.rendererIndex));
    }

    private void setParametersInternal(Parameters parameters2) {
        boolean equals;
        Assertions.checkNotNull(parameters2);
        synchronized (this.lock) {
            equals = this.parameters.equals(parameters2);
            this.parameters = parameters2;
        }
        if (!equals) {
            if (parameters2.constrainAudioChannelCountToDeviceCapabilities && this.context == null) {
                Log.w(TAG, AUDIO_CHANNEL_COUNT_CONSTRAINTS_WARN_MESSAGE);
            }
            invalidate();
        }
    }

    public Parameters.Builder buildUponParameters() {
        return getParameters().buildUpon();
    }

    public boolean isSetParametersSupported() {
        return true;
    }

    public void release() {
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                if (Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null) {
                    spatializerWrapperV32.release();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        super.release();
    }

    public ExoTrackSelection.Definition[] selectAllTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2) throws ExoPlaybackException {
        String str;
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] definitionArr = new ExoTrackSelection.Definition[rendererCount];
        Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack = selectVideoTrack(mappedTrackInfo, iArr, iArr2, parameters2);
        if (selectVideoTrack != null) {
            definitionArr[((Integer) selectVideoTrack.second).intValue()] = (ExoTrackSelection.Definition) selectVideoTrack.first;
        }
        Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack = selectAudioTrack(mappedTrackInfo, iArr, iArr2, parameters2);
        if (selectAudioTrack != null) {
            definitionArr[((Integer) selectAudioTrack.second).intValue()] = (ExoTrackSelection.Definition) selectAudioTrack.first;
        }
        if (selectAudioTrack == null) {
            str = null;
        } else {
            Object obj = selectAudioTrack.first;
            str = ((ExoTrackSelection.Definition) obj).group.getFormat(((ExoTrackSelection.Definition) obj).tracks[0]).language;
        }
        Pair<ExoTrackSelection.Definition, Integer> selectTextTrack = selectTextTrack(mappedTrackInfo, iArr, parameters2, str);
        if (selectTextTrack != null) {
            definitionArr[((Integer) selectTextTrack.second).intValue()] = (ExoTrackSelection.Definition) selectTextTrack.first;
        }
        for (int i3 = 0; i3 < rendererCount; i3++) {
            int rendererType = mappedTrackInfo.getRendererType(i3);
            if (!(rendererType == 2 || rendererType == 1 || rendererType == 3)) {
                definitionArr[i3] = selectOtherTrack(rendererType, mappedTrackInfo.getTrackGroups(i3), iArr[i3], parameters2);
            }
        }
        return definitionArr;
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2) throws ExoPlaybackException {
        boolean z2 = false;
        int i3 = 0;
        while (true) {
            if (i3 < mappedTrackInfo.getRendererCount()) {
                if (2 == mappedTrackInfo.getRendererType(i3) && mappedTrackInfo.getTrackGroups(i3).length > 0) {
                    z2 = true;
                    break;
                }
                i3++;
            } else {
                break;
            }
        }
        return selectTracksForType(1, mappedTrackInfo, iArr, new b(this, parameters2, z2), new a(1));
    }

    @Nullable
    public ExoTrackSelection.Definition selectOtherTrack(int i3, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters2) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        OtherTrackScore otherTrackScore = null;
        int i4 = 0;
        for (int i5 = 0; i5 < trackGroupArray.length; i5++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i5);
            int[] iArr2 = iArr[i5];
            for (int i6 = 0; i6 < trackGroup2.length; i6++) {
                if (isSupported(iArr2[i6], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    OtherTrackScore otherTrackScore2 = new OtherTrackScore(trackGroup2.getFormat(i6), iArr2[i6]);
                    if (otherTrackScore == null || otherTrackScore2.compareTo(otherTrackScore) > 0) {
                        trackGroup = trackGroup2;
                        i4 = i6;
                        otherTrackScore = otherTrackScore2;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new ExoTrackSelection.Definition(trackGroup, i4);
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectTextTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, Parameters parameters2, @Nullable String str) throws ExoPlaybackException {
        return selectTracksForType(3, mappedTrackInfo, iArr, new d(parameters2, str, 21), new a(0));
    }

    public final Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException {
        Parameters parameters2;
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                parameters2 = this.parameters;
                if (parameters2.constrainAudioChannelCountToDeviceCapabilities && Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null) {
                    spatializerWrapperV32.ensureInitialized(this, (Looper) Assertions.checkStateNotNull(Looper.myLooper()));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] selectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters2);
        applyTrackSelectionOverrides(mappedTrackInfo, parameters2, selectAllTracks);
        applyLegacyRendererOverrides(mappedTrackInfo, parameters2, selectAllTracks);
        for (int i3 = 0; i3 < rendererCount; i3++) {
            int rendererType = mappedTrackInfo.getRendererType(i3);
            if (parameters2.getRendererDisabled(i3) || parameters2.disabledTrackTypes.contains(Integer.valueOf(rendererType))) {
                selectAllTracks[i3] = null;
            }
        }
        ExoTrackSelection[] createTrackSelections = this.trackSelectionFactory.createTrackSelections(selectAllTracks, getBandwidthMeter(), mediaPeriodId, timeline);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCount];
        for (int i4 = 0; i4 < rendererCount; i4++) {
            rendererConfigurationArr[i4] = (parameters2.getRendererDisabled(i4) || parameters2.disabledTrackTypes.contains(Integer.valueOf(mappedTrackInfo.getRendererType(i4))) || (mappedTrackInfo.getRendererType(i4) != -2 && createTrackSelections[i4] == null)) ? null : RendererConfiguration.DEFAULT;
        }
        if (parameters2.tunnelingEnabled) {
            maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, createTrackSelections);
        }
        return Pair.create(rendererConfigurationArr, createTrackSelections);
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2) throws ExoPlaybackException {
        return selectTracksForType(2, mappedTrackInfo, iArr, new d(parameters2, iArr2, 22), new a(2));
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        boolean equals;
        synchronized (this.lock) {
            equals = this.audioAttributes.equals(audioAttributes2);
            this.audioAttributes = audioAttributes2;
        }
        if (!equals) {
            maybeInvalidateForAudioChannelCountConstraints();
        }
    }

    public void setParameters(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters instanceof Parameters) {
            setParametersInternal((Parameters) trackSelectionParameters);
        }
        setParametersInternal(new Parameters.Builder().set(trackSelectionParameters).build());
    }

    public DefaultTrackSelector(Context context2) {
        this(context2, (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
    }

    public Parameters getParameters() {
        Parameters parameters2;
        synchronized (this.lock) {
            parameters2 = this.parameters;
        }
        return parameters2;
    }

    public DefaultTrackSelector(Context context2, ExoTrackSelection.Factory factory) {
        this(context2, (TrackSelectionParameters) Parameters.getDefaults(context2), factory);
    }

    public DefaultTrackSelector(Context context2, TrackSelectionParameters trackSelectionParameters) {
        this(context2, trackSelectionParameters, (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
    }

    @Deprecated
    public DefaultTrackSelector(TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory) {
        this(trackSelectionParameters, factory, (Context) null);
    }

    @Deprecated
    public void setParameters(ParametersBuilder parametersBuilder) {
        setParametersInternal(parametersBuilder.build());
    }

    public DefaultTrackSelector(Context context2, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory) {
        this(trackSelectionParameters, factory, context2);
    }

    public void setParameters(Parameters.Builder builder) {
        setParametersInternal(builder.build());
    }

    private DefaultTrackSelector(TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory, @Nullable Context context2) {
        this.lock = new Object();
        this.context = context2 != null ? context2.getApplicationContext() : null;
        this.trackSelectionFactory = factory;
        if (trackSelectionParameters instanceof Parameters) {
            this.parameters = (Parameters) trackSelectionParameters;
        } else {
            this.parameters = (context2 == null ? Parameters.DEFAULT_WITHOUT_CONTEXT : Parameters.getDefaults(context2)).buildUpon().set(trackSelectionParameters).build();
        }
        this.audioAttributes = AudioAttributes.DEFAULT;
        boolean z2 = context2 != null && Util.isTv(context2);
        this.deviceIsTV = z2;
        if (!z2 && context2 != null && Util.SDK_INT >= 32) {
            this.spatializer = SpatializerWrapperV32.tryCreateInstance(context2);
        }
        if (this.parameters.constrainAudioChannelCountToDeviceCapabilities && context2 == null) {
            Log.w(TAG, AUDIO_CHANNEL_COUNT_CONSTRAINTS_WARN_MESSAGE);
        }
    }
}
