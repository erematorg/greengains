package com.appsamurai.storyly.exoplayer2.hls.playlist;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HlsMediaPlaylist extends HlsPlaylist {
    public static final int PLAYLIST_TYPE_EVENT = 2;
    public static final int PLAYLIST_TYPE_UNKNOWN = 0;
    public static final int PLAYLIST_TYPE_VOD = 1;
    public final int discontinuitySequence;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasPositiveStartOffset;
    public final boolean hasProgramDateTime;
    public final long mediaSequence;
    public final long partTargetDurationUs;
    public final int playlistType;
    public final boolean preciseStart;
    @Nullable
    public final DrmInitData protectionSchemes;
    public final Map<Uri, RenditionReport> renditionReports;
    public final List<Segment> segments;
    public final ServerControl serverControl;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final List<Part> trailingParts;
    public final int version;

    public static final class Part extends SegmentBase {
        public final boolean isIndependent;
        public final boolean isPreload;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Part(String str, @Nullable Segment segment, long j2, int i3, long j3, @Nullable DrmInitData drmInitData, @Nullable String str2, @Nullable String str3, long j4, long j5, boolean z2, boolean z3, boolean z4) {
            super(str, segment, j2, i3, j3, drmInitData, str2, str3, j4, j5, z2);
            this.isIndependent = z3;
            this.isPreload = z4;
        }

        public Part copyWith(long j2, int i3) {
            int i4 = i3;
            return new Part(this.url, this.initializationSegment, this.durationUs, i4, j2, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, this.isIndependent, this.isPreload);
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaylistType {
    }

    public static final class RenditionReport {
        public final long lastMediaSequence;
        public final int lastPartIndex;
        public final Uri playlistUri;

        public RenditionReport(Uri uri, long j2, int i3) {
            this.playlistUri = uri;
            this.lastMediaSequence = j2;
            this.lastPartIndex = i3;
        }
    }

    public static class SegmentBase implements Comparable<Long> {
        public final long byteRangeLength;
        public final long byteRangeOffset;
        @Nullable
        public final DrmInitData drmInitData;
        public final long durationUs;
        @Nullable
        public final String encryptionIV;
        @Nullable
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        @Nullable
        public final Segment initializationSegment;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String url;

        private SegmentBase(String str, @Nullable Segment segment, long j2, int i3, long j3, @Nullable DrmInitData drmInitData2, @Nullable String str2, @Nullable String str3, long j4, long j5, boolean z2) {
            this.url = str;
            this.initializationSegment = segment;
            this.durationUs = j2;
            this.relativeDiscontinuitySequence = i3;
            this.relativeStartTimeUs = j3;
            this.drmInitData = drmInitData2;
            this.fullSegmentEncryptionKeyUri = str2;
            this.encryptionIV = str3;
            this.byteRangeOffset = j4;
            this.byteRangeLength = j5;
            this.hasGapTag = z2;
        }

        public int compareTo(Long l2) {
            if (this.relativeStartTimeUs > l2.longValue()) {
                return 1;
            }
            return this.relativeStartTimeUs < l2.longValue() ? -1 : 0;
        }
    }

    public static final class ServerControl {
        public final boolean canBlockReload;
        public final boolean canSkipDateRanges;
        public final long holdBackUs;
        public final long partHoldBackUs;
        public final long skipUntilUs;

        public ServerControl(long j2, boolean z2, long j3, long j4, boolean z3) {
            this.skipUntilUs = j2;
            this.canSkipDateRanges = z2;
            this.holdBackUs = j3;
            this.partHoldBackUs = j4;
            this.canBlockReload = z3;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HlsMediaPlaylist(int i3, String str, List<String> list, long j2, boolean z2, long j3, boolean z3, int i4, long j4, int i5, long j5, long j6, boolean z4, boolean z5, boolean z6, @Nullable DrmInitData drmInitData, List<Segment> list2, List<Part> list3, ServerControl serverControl2, Map<Uri, RenditionReport> map) {
        super(str, list, z4);
        long j7 = j2;
        String str2 = str;
        List<String> list4 = list;
        this.playlistType = i3;
        this.startTimeUs = j3;
        this.preciseStart = z2;
        this.hasDiscontinuitySequence = z3;
        this.discontinuitySequence = i4;
        this.mediaSequence = j4;
        this.version = i5;
        this.targetDurationUs = j5;
        this.partTargetDurationUs = j6;
        this.hasEndTag = z5;
        this.hasProgramDateTime = z6;
        this.protectionSchemes = drmInitData;
        this.segments = ImmutableList.copyOf(list2);
        this.trailingParts = ImmutableList.copyOf(list3);
        this.renditionReports = ImmutableMap.copyOf(map);
        if (!list3.isEmpty()) {
            Part part = (Part) Iterables.getLast(list3);
            this.durationUs = part.relativeStartTimeUs + part.durationUs;
        } else if (!list2.isEmpty()) {
            Segment segment = (Segment) Iterables.getLast(list2);
            this.durationUs = segment.relativeStartTimeUs + segment.durationUs;
        } else {
            this.durationUs = 0;
        }
        this.startOffsetUs = j7 != C.TIME_UNSET ? j7 >= 0 ? Math.min(this.durationUs, j2) : Math.max(0, this.durationUs + j7) : C.TIME_UNSET;
        this.hasPositiveStartOffset = j7 >= 0;
        this.serverControl = serverControl2;
    }

    public HlsMediaPlaylist copy(List<StreamKey> list) {
        return this;
    }

    public HlsMediaPlaylist copyWith(long j2, int i3) {
        int i4 = this.playlistType;
        return new HlsMediaPlaylist(i4, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, j2, true, i3, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, this.hasEndTag, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        if (this.hasEndTag) {
            return this;
        }
        HlsMediaPlaylist hlsMediaPlaylist = r2;
        HlsMediaPlaylist hlsMediaPlaylist2 = new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, this.startTimeUs, this.hasDiscontinuitySequence, this.discontinuitySequence, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, true, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports);
        return hlsMediaPlaylist;
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public boolean isNewerThan(@Nullable HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsMediaPlaylist == null) {
            return true;
        }
        long j2 = this.mediaSequence;
        long j3 = hlsMediaPlaylist.mediaSequence;
        if (j2 > j3) {
            return true;
        }
        if (j2 < j3) {
            return false;
        }
        int size = this.segments.size() - hlsMediaPlaylist.segments.size();
        if (size != 0) {
            return size > 0;
        }
        int size2 = this.trailingParts.size();
        int size3 = hlsMediaPlaylist.trailingParts.size();
        if (size2 <= size3) {
            return size2 == size3 && this.hasEndTag && !hlsMediaPlaylist.hasEndTag;
        }
        return true;
    }

    public static final class Segment extends SegmentBase {
        public final List<Part> parts;
        public final String title;

        public Segment(String str, long j2, long j3, @Nullable String str2, @Nullable String str3) {
            this(str, (Segment) null, "", 0, -1, C.TIME_UNSET, (DrmInitData) null, str2, str3, j2, j3, false, ImmutableList.of());
        }

        public Segment copyWith(long j2, int i3) {
            ArrayList arrayList = new ArrayList();
            long j3 = j2;
            for (int i4 = 0; i4 < this.parts.size(); i4++) {
                Part part = this.parts.get(i4);
                arrayList.add(part.copyWith(j3, i3));
                j3 += part.durationUs;
            }
            int i5 = i3;
            return new Segment(this.url, this.initializationSegment, this.title, this.durationUs, i3, j2, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, arrayList);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Segment(String str, @Nullable Segment segment, String str2, long j2, int i3, long j3, @Nullable DrmInitData drmInitData, @Nullable String str3, @Nullable String str4, long j4, long j5, boolean z2, List<Part> list) {
            super(str, segment, j2, i3, j3, drmInitData, str3, str4, j4, j5, z2);
            this.title = str2;
            this.parts = ImmutableList.copyOf(list);
        }
    }
}
