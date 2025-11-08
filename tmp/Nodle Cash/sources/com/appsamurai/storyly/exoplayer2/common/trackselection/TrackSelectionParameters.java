package com.appsamurai.storyly.exoplayer2.common.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.view.accessibility.CaptioningManager;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.trackselection.AdaptiveTrackSelection;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public class TrackSelectionParameters implements Bundleable {
    @Deprecated
    public static final Bundleable.Creator<TrackSelectionParameters> CREATOR = new a(22);
    @Deprecated
    public static final TrackSelectionParameters DEFAULT;
    public static final TrackSelectionParameters DEFAULT_WITHOUT_CONTEXT;
    protected static final int FIELD_CUSTOM_ID_BASE = 1000;
    private static final int FIELD_DISABLED_TRACK_TYPE = 24;
    private static final int FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE = 22;
    private static final int FIELD_FORCE_LOWEST_BITRATE = 21;
    private static final int FIELD_IGNORED_TEXT_SELECTION_FLAGS = 26;
    private static final int FIELD_MAX_AUDIO_BITRATE = 19;
    private static final int FIELD_MAX_AUDIO_CHANNEL_COUNT = 18;
    private static final int FIELD_MAX_VIDEO_BITRATE = 9;
    private static final int FIELD_MAX_VIDEO_FRAMERATE = 8;
    private static final int FIELD_MAX_VIDEO_HEIGHT = 7;
    private static final int FIELD_MAX_VIDEO_WIDTH = 6;
    private static final int FIELD_MIN_VIDEO_BITRATE = 13;
    private static final int FIELD_MIN_VIDEO_FRAMERATE = 12;
    private static final int FIELD_MIN_VIDEO_HEIGHT = 11;
    private static final int FIELD_MIN_VIDEO_WIDTH = 10;
    private static final int FIELD_PREFERRED_AUDIO_LANGUAGES = 1;
    private static final int FIELD_PREFERRED_AUDIO_MIME_TYPES = 20;
    private static final int FIELD_PREFERRED_AUDIO_ROLE_FLAGS = 2;
    private static final int FIELD_PREFERRED_TEXT_LANGUAGES = 3;
    private static final int FIELD_PREFERRED_TEXT_ROLE_FLAGS = 4;
    private static final int FIELD_PREFERRED_VIDEO_MIMETYPES = 17;
    private static final int FIELD_PREFERRED_VIDEO_ROLE_FLAGS = 25;
    private static final int FIELD_SELECTION_OVERRIDES = 23;
    private static final int FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE = 5;
    private static final int FIELD_VIEWPORT_HEIGHT = 15;
    private static final int FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE = 16;
    private static final int FIELD_VIEWPORT_WIDTH = 14;
    public final ImmutableSet<Integer> disabledTrackTypes;
    public final boolean forceHighestSupportedBitrate;
    public final boolean forceLowestBitrate;
    public final int ignoredTextSelectionFlags;
    public final int maxAudioBitrate;
    public final int maxAudioChannelCount;
    public final int maxVideoBitrate;
    public final int maxVideoFrameRate;
    public final int maxVideoHeight;
    public final int maxVideoWidth;
    public final int minVideoBitrate;
    public final int minVideoFrameRate;
    public final int minVideoHeight;
    public final int minVideoWidth;
    public final ImmutableMap<TrackGroup, TrackSelectionOverride> overrides;
    public final ImmutableList<String> preferredAudioLanguages;
    public final ImmutableList<String> preferredAudioMimeTypes;
    public final int preferredAudioRoleFlags;
    public final ImmutableList<String> preferredTextLanguages;
    public final int preferredTextRoleFlags;
    public final ImmutableList<String> preferredVideoMimeTypes;
    public final int preferredVideoRoleFlags;
    public final boolean selectUndeterminedTextLanguage;
    public final int viewportHeight;
    public final boolean viewportOrientationMayChange;
    public final int viewportWidth;

    static {
        TrackSelectionParameters build = new Builder().build();
        DEFAULT_WITHOUT_CONTEXT = build;
        DEFAULT = build;
    }

    public TrackSelectionParameters(Builder builder) {
        this.maxVideoWidth = builder.maxVideoWidth;
        this.maxVideoHeight = builder.maxVideoHeight;
        this.maxVideoFrameRate = builder.maxVideoFrameRate;
        this.maxVideoBitrate = builder.maxVideoBitrate;
        this.minVideoWidth = builder.minVideoWidth;
        this.minVideoHeight = builder.minVideoHeight;
        this.minVideoFrameRate = builder.minVideoFrameRate;
        this.minVideoBitrate = builder.minVideoBitrate;
        this.viewportWidth = builder.viewportWidth;
        this.viewportHeight = builder.viewportHeight;
        this.viewportOrientationMayChange = builder.viewportOrientationMayChange;
        this.preferredVideoMimeTypes = builder.preferredVideoMimeTypes;
        this.preferredVideoRoleFlags = builder.preferredVideoRoleFlags;
        this.preferredAudioLanguages = builder.preferredAudioLanguages;
        this.preferredAudioRoleFlags = builder.preferredAudioRoleFlags;
        this.maxAudioChannelCount = builder.maxAudioChannelCount;
        this.maxAudioBitrate = builder.maxAudioBitrate;
        this.preferredAudioMimeTypes = builder.preferredAudioMimeTypes;
        this.preferredTextLanguages = builder.preferredTextLanguages;
        this.preferredTextRoleFlags = builder.preferredTextRoleFlags;
        this.ignoredTextSelectionFlags = builder.ignoredTextSelectionFlags;
        this.selectUndeterminedTextLanguage = builder.selectUndeterminedTextLanguage;
        this.forceLowestBitrate = builder.forceLowestBitrate;
        this.forceHighestSupportedBitrate = builder.forceHighestSupportedBitrate;
        this.overrides = ImmutableMap.copyOf(builder.overrides);
        this.disabledTrackTypes = ImmutableSet.copyOf(builder.disabledTrackTypes);
    }

    public static TrackSelectionParameters fromBundle(Bundle bundle) {
        return new Builder(bundle).build();
    }

    public static TrackSelectionParameters getDefaults(Context context) {
        return new Builder(context).build();
    }

    public static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackSelectionParameters trackSelectionParameters = (TrackSelectionParameters) obj;
        return this.maxVideoWidth == trackSelectionParameters.maxVideoWidth && this.maxVideoHeight == trackSelectionParameters.maxVideoHeight && this.maxVideoFrameRate == trackSelectionParameters.maxVideoFrameRate && this.maxVideoBitrate == trackSelectionParameters.maxVideoBitrate && this.minVideoWidth == trackSelectionParameters.minVideoWidth && this.minVideoHeight == trackSelectionParameters.minVideoHeight && this.minVideoFrameRate == trackSelectionParameters.minVideoFrameRate && this.minVideoBitrate == trackSelectionParameters.minVideoBitrate && this.viewportOrientationMayChange == trackSelectionParameters.viewportOrientationMayChange && this.viewportWidth == trackSelectionParameters.viewportWidth && this.viewportHeight == trackSelectionParameters.viewportHeight && this.preferredVideoMimeTypes.equals(trackSelectionParameters.preferredVideoMimeTypes) && this.preferredVideoRoleFlags == trackSelectionParameters.preferredVideoRoleFlags && this.preferredAudioLanguages.equals(trackSelectionParameters.preferredAudioLanguages) && this.preferredAudioRoleFlags == trackSelectionParameters.preferredAudioRoleFlags && this.maxAudioChannelCount == trackSelectionParameters.maxAudioChannelCount && this.maxAudioBitrate == trackSelectionParameters.maxAudioBitrate && this.preferredAudioMimeTypes.equals(trackSelectionParameters.preferredAudioMimeTypes) && this.preferredTextLanguages.equals(trackSelectionParameters.preferredTextLanguages) && this.preferredTextRoleFlags == trackSelectionParameters.preferredTextRoleFlags && this.ignoredTextSelectionFlags == trackSelectionParameters.ignoredTextSelectionFlags && this.selectUndeterminedTextLanguage == trackSelectionParameters.selectUndeterminedTextLanguage && this.forceLowestBitrate == trackSelectionParameters.forceLowestBitrate && this.forceHighestSupportedBitrate == trackSelectionParameters.forceHighestSupportedBitrate && this.overrides.equals(trackSelectionParameters.overrides) && this.disabledTrackTypes.equals(trackSelectionParameters.disabledTrackTypes);
    }

    public int hashCode() {
        int hashCode = this.preferredVideoMimeTypes.hashCode();
        int hashCode2 = this.preferredAudioLanguages.hashCode();
        int hashCode3 = this.preferredAudioMimeTypes.hashCode();
        int hashCode4 = this.preferredTextLanguages.hashCode();
        int hashCode5 = this.overrides.hashCode();
        return this.disabledTrackTypes.hashCode() + ((hashCode5 + ((((((((((((hashCode4 + ((hashCode3 + ((((((((hashCode2 + ((((hashCode + ((((((((((((((((((((((this.maxVideoWidth + 31) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.maxVideoBitrate) * 31) + this.minVideoWidth) * 31) + this.minVideoHeight) * 31) + this.minVideoFrameRate) * 31) + this.minVideoBitrate) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31)) * 31) + this.preferredVideoRoleFlags) * 31)) * 31) + this.preferredAudioRoleFlags) * 31) + this.maxAudioChannelCount) * 31) + this.maxAudioBitrate) * 31)) * 31)) * 31) + this.preferredTextRoleFlags) * 31) + this.ignoredTextSelectionFlags) * 31) + (this.selectUndeterminedTextLanguage ? 1 : 0)) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.forceHighestSupportedBitrate ? 1 : 0)) * 31)) * 31);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(6), this.maxVideoWidth);
        bundle.putInt(keyForField(7), this.maxVideoHeight);
        bundle.putInt(keyForField(8), this.maxVideoFrameRate);
        bundle.putInt(keyForField(9), this.maxVideoBitrate);
        bundle.putInt(keyForField(10), this.minVideoWidth);
        bundle.putInt(keyForField(11), this.minVideoHeight);
        bundle.putInt(keyForField(12), this.minVideoFrameRate);
        bundle.putInt(keyForField(13), this.minVideoBitrate);
        bundle.putInt(keyForField(14), this.viewportWidth);
        bundle.putInt(keyForField(15), this.viewportHeight);
        bundle.putBoolean(keyForField(16), this.viewportOrientationMayChange);
        bundle.putStringArray(keyForField(17), (String[]) this.preferredVideoMimeTypes.toArray(new String[0]));
        bundle.putInt(keyForField(25), this.preferredVideoRoleFlags);
        bundle.putStringArray(keyForField(1), (String[]) this.preferredAudioLanguages.toArray(new String[0]));
        bundle.putInt(keyForField(2), this.preferredAudioRoleFlags);
        bundle.putInt(keyForField(18), this.maxAudioChannelCount);
        bundle.putInt(keyForField(19), this.maxAudioBitrate);
        bundle.putStringArray(keyForField(20), (String[]) this.preferredAudioMimeTypes.toArray(new String[0]));
        bundle.putStringArray(keyForField(3), (String[]) this.preferredTextLanguages.toArray(new String[0]));
        bundle.putInt(keyForField(4), this.preferredTextRoleFlags);
        bundle.putInt(keyForField(26), this.ignoredTextSelectionFlags);
        bundle.putBoolean(keyForField(5), this.selectUndeterminedTextLanguage);
        bundle.putBoolean(keyForField(21), this.forceLowestBitrate);
        bundle.putBoolean(keyForField(22), this.forceHighestSupportedBitrate);
        bundle.putParcelableArrayList(keyForField(23), BundleableUtil.toBundleArrayList(this.overrides.values()));
        bundle.putIntArray(keyForField(24), Ints.toArray(this.disabledTrackTypes));
        return bundle;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public HashSet<Integer> disabledTrackTypes;
        /* access modifiers changed from: private */
        public boolean forceHighestSupportedBitrate;
        /* access modifiers changed from: private */
        public boolean forceLowestBitrate;
        /* access modifiers changed from: private */
        public int ignoredTextSelectionFlags;
        /* access modifiers changed from: private */
        public int maxAudioBitrate;
        /* access modifiers changed from: private */
        public int maxAudioChannelCount;
        /* access modifiers changed from: private */
        public int maxVideoBitrate;
        /* access modifiers changed from: private */
        public int maxVideoFrameRate;
        /* access modifiers changed from: private */
        public int maxVideoHeight;
        /* access modifiers changed from: private */
        public int maxVideoWidth;
        /* access modifiers changed from: private */
        public int minVideoBitrate;
        /* access modifiers changed from: private */
        public int minVideoFrameRate;
        /* access modifiers changed from: private */
        public int minVideoHeight;
        /* access modifiers changed from: private */
        public int minVideoWidth;
        /* access modifiers changed from: private */
        public HashMap<TrackGroup, TrackSelectionOverride> overrides;
        /* access modifiers changed from: private */
        public ImmutableList<String> preferredAudioLanguages;
        /* access modifiers changed from: private */
        public ImmutableList<String> preferredAudioMimeTypes;
        /* access modifiers changed from: private */
        public int preferredAudioRoleFlags;
        /* access modifiers changed from: private */
        public ImmutableList<String> preferredTextLanguages;
        /* access modifiers changed from: private */
        public int preferredTextRoleFlags;
        /* access modifiers changed from: private */
        public ImmutableList<String> preferredVideoMimeTypes;
        /* access modifiers changed from: private */
        public int preferredVideoRoleFlags;
        /* access modifiers changed from: private */
        public boolean selectUndeterminedTextLanguage;
        /* access modifiers changed from: private */
        public int viewportHeight;
        /* access modifiers changed from: private */
        public boolean viewportOrientationMayChange;
        /* access modifiers changed from: private */
        public int viewportWidth;

        @Deprecated
        public Builder() {
            this.maxVideoWidth = Integer.MAX_VALUE;
            this.maxVideoHeight = Integer.MAX_VALUE;
            this.maxVideoFrameRate = Integer.MAX_VALUE;
            this.maxVideoBitrate = Integer.MAX_VALUE;
            this.viewportWidth = Integer.MAX_VALUE;
            this.viewportHeight = Integer.MAX_VALUE;
            this.viewportOrientationMayChange = true;
            this.preferredVideoMimeTypes = ImmutableList.of();
            this.preferredVideoRoleFlags = 0;
            this.preferredAudioLanguages = ImmutableList.of();
            this.preferredAudioRoleFlags = 0;
            this.maxAudioChannelCount = Integer.MAX_VALUE;
            this.maxAudioBitrate = Integer.MAX_VALUE;
            this.preferredAudioMimeTypes = ImmutableList.of();
            this.preferredTextLanguages = ImmutableList.of();
            this.preferredTextRoleFlags = 0;
            this.ignoredTextSelectionFlags = 0;
            this.selectUndeterminedTextLanguage = false;
            this.forceLowestBitrate = false;
            this.forceHighestSupportedBitrate = false;
            this.overrides = new HashMap<>();
            this.disabledTrackTypes = new HashSet<>();
        }

        @EnsuresNonNull({"preferredVideoMimeTypes", "preferredAudioLanguages", "preferredAudioMimeTypes", "preferredTextLanguages", "overrides", "disabledTrackTypes"})
        private void init(TrackSelectionParameters trackSelectionParameters) {
            this.maxVideoWidth = trackSelectionParameters.maxVideoWidth;
            this.maxVideoHeight = trackSelectionParameters.maxVideoHeight;
            this.maxVideoFrameRate = trackSelectionParameters.maxVideoFrameRate;
            this.maxVideoBitrate = trackSelectionParameters.maxVideoBitrate;
            this.minVideoWidth = trackSelectionParameters.minVideoWidth;
            this.minVideoHeight = trackSelectionParameters.minVideoHeight;
            this.minVideoFrameRate = trackSelectionParameters.minVideoFrameRate;
            this.minVideoBitrate = trackSelectionParameters.minVideoBitrate;
            this.viewportWidth = trackSelectionParameters.viewportWidth;
            this.viewportHeight = trackSelectionParameters.viewportHeight;
            this.viewportOrientationMayChange = trackSelectionParameters.viewportOrientationMayChange;
            this.preferredVideoMimeTypes = trackSelectionParameters.preferredVideoMimeTypes;
            this.preferredVideoRoleFlags = trackSelectionParameters.preferredVideoRoleFlags;
            this.preferredAudioLanguages = trackSelectionParameters.preferredAudioLanguages;
            this.preferredAudioRoleFlags = trackSelectionParameters.preferredAudioRoleFlags;
            this.maxAudioChannelCount = trackSelectionParameters.maxAudioChannelCount;
            this.maxAudioBitrate = trackSelectionParameters.maxAudioBitrate;
            this.preferredAudioMimeTypes = trackSelectionParameters.preferredAudioMimeTypes;
            this.preferredTextLanguages = trackSelectionParameters.preferredTextLanguages;
            this.preferredTextRoleFlags = trackSelectionParameters.preferredTextRoleFlags;
            this.ignoredTextSelectionFlags = trackSelectionParameters.ignoredTextSelectionFlags;
            this.selectUndeterminedTextLanguage = trackSelectionParameters.selectUndeterminedTextLanguage;
            this.forceLowestBitrate = trackSelectionParameters.forceLowestBitrate;
            this.forceHighestSupportedBitrate = trackSelectionParameters.forceHighestSupportedBitrate;
            this.disabledTrackTypes = new HashSet<>(trackSelectionParameters.disabledTrackTypes);
            this.overrides = new HashMap<>(trackSelectionParameters.overrides);
        }

        private static ImmutableList<String> normalizeLanguageCodes(String[] strArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String checkNotNull : (String[]) Assertions.checkNotNull(strArr)) {
                builder.add((Object) Util.normalizeLanguageCode((String) Assertions.checkNotNull(checkNotNull)));
            }
            return builder.build();
        }

        @RequiresApi(19)
        private void setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(Context context) {
            CaptioningManager captioningManager;
            if ((Util.SDK_INT >= 23 || Looper.myLooper() != null) && (captioningManager = (CaptioningManager) context.getSystemService("captioning")) != null && captioningManager.isEnabled()) {
                this.preferredTextRoleFlags = 1088;
                Locale locale = captioningManager.getLocale();
                if (locale != null) {
                    this.preferredTextLanguages = ImmutableList.of(Util.getLocaleLanguageTag(locale));
                }
            }
        }

        public Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
            this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            return this;
        }

        public TrackSelectionParameters build() {
            return new TrackSelectionParameters(this);
        }

        public Builder clearOverride(TrackGroup trackGroup) {
            this.overrides.remove(trackGroup);
            return this;
        }

        public Builder clearOverrides() {
            this.overrides.clear();
            return this;
        }

        public Builder clearOverridesOfType(int i3) {
            Iterator<TrackSelectionOverride> it = this.overrides.values().iterator();
            while (it.hasNext()) {
                if (it.next().getType() == i3) {
                    it.remove();
                }
            }
            return this;
        }

        public Builder clearVideoSizeConstraints() {
            return setMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public Builder clearViewportSizeConstraints() {
            return setViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public Builder set(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
            return this;
        }

        @Deprecated
        public Builder setDisabledTrackTypes(Set<Integer> set) {
            this.disabledTrackTypes.clear();
            this.disabledTrackTypes.addAll(set);
            return this;
        }

        public Builder setForceHighestSupportedBitrate(boolean z2) {
            this.forceHighestSupportedBitrate = z2;
            return this;
        }

        public Builder setForceLowestBitrate(boolean z2) {
            this.forceLowestBitrate = z2;
            return this;
        }

        public Builder setIgnoredTextSelectionFlags(int i3) {
            this.ignoredTextSelectionFlags = i3;
            return this;
        }

        public Builder setMaxAudioBitrate(int i3) {
            this.maxAudioBitrate = i3;
            return this;
        }

        public Builder setMaxAudioChannelCount(int i3) {
            this.maxAudioChannelCount = i3;
            return this;
        }

        public Builder setMaxVideoBitrate(int i3) {
            this.maxVideoBitrate = i3;
            return this;
        }

        public Builder setMaxVideoFrameRate(int i3) {
            this.maxVideoFrameRate = i3;
            return this;
        }

        public Builder setMaxVideoSize(int i3, int i4) {
            this.maxVideoWidth = i3;
            this.maxVideoHeight = i4;
            return this;
        }

        public Builder setMaxVideoSizeSd() {
            return setMaxVideoSize(AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD);
        }

        public Builder setMinVideoBitrate(int i3) {
            this.minVideoBitrate = i3;
            return this;
        }

        public Builder setMinVideoFrameRate(int i3) {
            this.minVideoFrameRate = i3;
            return this;
        }

        public Builder setMinVideoSize(int i3, int i4) {
            this.minVideoWidth = i3;
            this.minVideoHeight = i4;
            return this;
        }

        public Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
            clearOverridesOfType(trackSelectionOverride.getType());
            this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            return this;
        }

        public Builder setPreferredAudioLanguage(@Nullable String str) {
            return str == null ? setPreferredAudioLanguages(new String[0]) : setPreferredAudioLanguages(str);
        }

        public Builder setPreferredAudioLanguages(String... strArr) {
            this.preferredAudioLanguages = normalizeLanguageCodes(strArr);
            return this;
        }

        public Builder setPreferredAudioMimeType(@Nullable String str) {
            return str == null ? setPreferredAudioMimeTypes(new String[0]) : setPreferredAudioMimeTypes(str);
        }

        public Builder setPreferredAudioMimeTypes(String... strArr) {
            this.preferredAudioMimeTypes = ImmutableList.copyOf((E[]) strArr);
            return this;
        }

        public Builder setPreferredAudioRoleFlags(int i3) {
            this.preferredAudioRoleFlags = i3;
            return this;
        }

        public Builder setPreferredTextLanguage(@Nullable String str) {
            return str == null ? setPreferredTextLanguages(new String[0]) : setPreferredTextLanguages(str);
        }

        public Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            if (Util.SDK_INT >= 19) {
                setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(context);
            }
            return this;
        }

        public Builder setPreferredTextLanguages(String... strArr) {
            this.preferredTextLanguages = normalizeLanguageCodes(strArr);
            return this;
        }

        public Builder setPreferredTextRoleFlags(int i3) {
            this.preferredTextRoleFlags = i3;
            return this;
        }

        public Builder setPreferredVideoMimeType(@Nullable String str) {
            return str == null ? setPreferredVideoMimeTypes(new String[0]) : setPreferredVideoMimeTypes(str);
        }

        public Builder setPreferredVideoMimeTypes(String... strArr) {
            this.preferredVideoMimeTypes = ImmutableList.copyOf((E[]) strArr);
            return this;
        }

        public Builder setPreferredVideoRoleFlags(int i3) {
            this.preferredVideoRoleFlags = i3;
            return this;
        }

        public Builder setSelectUndeterminedTextLanguage(boolean z2) {
            this.selectUndeterminedTextLanguage = z2;
            return this;
        }

        public Builder setTrackTypeDisabled(int i3, boolean z2) {
            if (z2) {
                this.disabledTrackTypes.add(Integer.valueOf(i3));
            } else {
                this.disabledTrackTypes.remove(Integer.valueOf(i3));
            }
            return this;
        }

        public Builder setViewportSize(int i3, int i4, boolean z2) {
            this.viewportWidth = i3;
            this.viewportHeight = i4;
            this.viewportOrientationMayChange = z2;
            return this;
        }

        public Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z2) {
            Point currentDisplayModeSize = Util.getCurrentDisplayModeSize(context);
            return setViewportSize(currentDisplayModeSize.x, currentDisplayModeSize.y, z2);
        }

        public Builder(Context context) {
            this();
            setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
            setViewportSizeToPhysicalDisplaySize(context, true);
        }

        public Builder(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
        }

        public Builder(Bundle bundle) {
            ImmutableList<TrackSelectionOverride> immutableList;
            String keyForField = TrackSelectionParameters.keyForField(6);
            TrackSelectionParameters trackSelectionParameters = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
            this.maxVideoWidth = bundle.getInt(keyForField, trackSelectionParameters.maxVideoWidth);
            this.maxVideoHeight = bundle.getInt(TrackSelectionParameters.keyForField(7), trackSelectionParameters.maxVideoHeight);
            this.maxVideoFrameRate = bundle.getInt(TrackSelectionParameters.keyForField(8), trackSelectionParameters.maxVideoFrameRate);
            this.maxVideoBitrate = bundle.getInt(TrackSelectionParameters.keyForField(9), trackSelectionParameters.maxVideoBitrate);
            this.minVideoWidth = bundle.getInt(TrackSelectionParameters.keyForField(10), trackSelectionParameters.minVideoWidth);
            this.minVideoHeight = bundle.getInt(TrackSelectionParameters.keyForField(11), trackSelectionParameters.minVideoHeight);
            this.minVideoFrameRate = bundle.getInt(TrackSelectionParameters.keyForField(12), trackSelectionParameters.minVideoFrameRate);
            this.minVideoBitrate = bundle.getInt(TrackSelectionParameters.keyForField(13), trackSelectionParameters.minVideoBitrate);
            this.viewportWidth = bundle.getInt(TrackSelectionParameters.keyForField(14), trackSelectionParameters.viewportWidth);
            this.viewportHeight = bundle.getInt(TrackSelectionParameters.keyForField(15), trackSelectionParameters.viewportHeight);
            this.viewportOrientationMayChange = bundle.getBoolean(TrackSelectionParameters.keyForField(16), trackSelectionParameters.viewportOrientationMayChange);
            this.preferredVideoMimeTypes = ImmutableList.copyOf((E[]) (String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.keyForField(17)), new String[0]));
            this.preferredVideoRoleFlags = bundle.getInt(TrackSelectionParameters.keyForField(25), trackSelectionParameters.preferredVideoRoleFlags);
            this.preferredAudioLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.keyForField(1)), new String[0]));
            this.preferredAudioRoleFlags = bundle.getInt(TrackSelectionParameters.keyForField(2), trackSelectionParameters.preferredAudioRoleFlags);
            this.maxAudioChannelCount = bundle.getInt(TrackSelectionParameters.keyForField(18), trackSelectionParameters.maxAudioChannelCount);
            this.maxAudioBitrate = bundle.getInt(TrackSelectionParameters.keyForField(19), trackSelectionParameters.maxAudioBitrate);
            this.preferredAudioMimeTypes = ImmutableList.copyOf((E[]) (String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.keyForField(20)), new String[0]));
            this.preferredTextLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.keyForField(3)), new String[0]));
            this.preferredTextRoleFlags = bundle.getInt(TrackSelectionParameters.keyForField(4), trackSelectionParameters.preferredTextRoleFlags);
            this.ignoredTextSelectionFlags = bundle.getInt(TrackSelectionParameters.keyForField(26), trackSelectionParameters.ignoredTextSelectionFlags);
            this.selectUndeterminedTextLanguage = bundle.getBoolean(TrackSelectionParameters.keyForField(5), trackSelectionParameters.selectUndeterminedTextLanguage);
            this.forceLowestBitrate = bundle.getBoolean(TrackSelectionParameters.keyForField(21), trackSelectionParameters.forceLowestBitrate);
            this.forceHighestSupportedBitrate = bundle.getBoolean(TrackSelectionParameters.keyForField(22), trackSelectionParameters.forceHighestSupportedBitrate);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(TrackSelectionParameters.keyForField(23));
            if (parcelableArrayList == null) {
                immutableList = ImmutableList.of();
            } else {
                immutableList = BundleableUtil.fromBundleList(TrackSelectionOverride.CREATOR, parcelableArrayList);
            }
            this.overrides = new HashMap<>();
            for (int i3 = 0; i3 < immutableList.size(); i3++) {
                TrackSelectionOverride trackSelectionOverride = immutableList.get(i3);
                this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            }
            int[] iArr = (int[]) MoreObjects.firstNonNull(bundle.getIntArray(TrackSelectionParameters.keyForField(24)), new int[0]);
            this.disabledTrackTypes = new HashSet<>();
            for (int valueOf : iArr) {
                this.disabledTrackTypes.add(Integer.valueOf(valueOf));
            }
        }
    }
}
