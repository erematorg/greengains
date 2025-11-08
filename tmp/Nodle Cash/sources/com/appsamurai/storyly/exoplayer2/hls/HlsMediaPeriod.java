package com.appsamurai.storyly.exoplayer2.hls;

import A.a;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.source.CompositeSequenceableLoaderFactory;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.SequenceableLoader;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMultivariantPlaylist;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsPlaylistTracker;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private int audioVideoSampleStreamWrapperCount;
    @Nullable
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private int[][] manifestUrlIndicesPerWrapper = new int[0][];
    @Nullable
    private final TransferListener mediaTransferListener;
    private final int metadataType;
    private int pendingPrepareCount;
    private final PlayerId playerId;
    private final HlsPlaylistTracker playlistTracker;
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private TrackGroupArray trackGroups;
    private final boolean useSessionKeys;

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, @Nullable TransferListener transferListener, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher3, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, boolean z2, int i3, boolean z3, PlayerId playerId2) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher3;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.allowChunklessPreparation = z2;
        this.metadataType = i3;
        this.useSessionKeys = z3;
        this.playerId = playerId2;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.createCompositeSequenceableLoader(new SequenceableLoader[0]);
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long j2, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        List<HlsMultivariantPlaylist.Rendition> list4 = list;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < list.size(); i3++) {
            String str = list4.get(i3).name;
            if (!hashSet.add(str)) {
                List<HlsSampleStreamWrapper> list5 = list2;
                List<int[]> list6 = list3;
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z2 = true;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    if (Util.areEqual(str, list4.get(i4).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list4.get(i4);
                        arrayList3.add(Integer.valueOf(i4));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                        z2 &= Util.getCodecCountOfType(rendition.format.codecs, 1) == 1;
                    }
                }
                String a2 = c.a("audio:", str);
                HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(a2, 1, (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[0])), (Format[]) arrayList2.toArray(new Format[0]), (Format) null, Collections.emptyList(), map, j2);
                list3.add(Ints.toArray(arrayList3));
                list2.add(buildSampleStreamWrapper);
                if (this.allowChunklessPreparation && z2) {
                    buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(a2, (Format[]) arrayList2.toArray(new Format[0]))}, 0, new int[0]);
                }
            }
        }
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMultivariantPlaylist hlsMultivariantPlaylist, long j2, List<HlsSampleStreamWrapper> list, List<int[]> list2, Map<String, DrmInitData> map) {
        boolean z2;
        boolean z3;
        HlsMultivariantPlaylist hlsMultivariantPlaylist2 = hlsMultivariantPlaylist;
        int size = hlsMultivariantPlaylist2.variants.size();
        int[] iArr = new int[size];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < hlsMultivariantPlaylist2.variants.size(); i5++) {
            Format format = hlsMultivariantPlaylist2.variants.get(i5).format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                iArr[i5] = 2;
                i3++;
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                iArr[i5] = 1;
                i4++;
            } else {
                iArr[i5] = -1;
            }
        }
        if (i3 > 0) {
            size = i3;
            z3 = true;
            z2 = false;
        } else if (i4 < size) {
            size -= i4;
            z3 = false;
            z2 = true;
        } else {
            z3 = false;
            z2 = false;
        }
        Uri[] uriArr = new Uri[size];
        Format[] formatArr = new Format[size];
        int[] iArr2 = new int[size];
        int i6 = 0;
        for (int i7 = 0; i7 < hlsMultivariantPlaylist2.variants.size(); i7++) {
            if ((!z3 || iArr[i7] == 2) && (!z2 || iArr[i7] != 1)) {
                HlsMultivariantPlaylist.Variant variant = hlsMultivariantPlaylist2.variants.get(i7);
                uriArr[i6] = variant.url;
                formatArr[i6] = variant.format;
                iArr2[i6] = i7;
                i6++;
            }
        }
        String str = formatArr[0].codecs;
        int codecCountOfType = Util.getCodecCountOfType(str, 2);
        int codecCountOfType2 = Util.getCodecCountOfType(str, 1);
        boolean z4 = (codecCountOfType2 == 1 || (codecCountOfType2 == 0 && hlsMultivariantPlaylist2.audios.isEmpty())) && codecCountOfType <= 1 && codecCountOfType2 + codecCountOfType > 0;
        String str2 = "main";
        HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper("main", (z3 || codecCountOfType2 <= 0) ? 0 : 1, uriArr, formatArr, hlsMultivariantPlaylist2.muxedAudioFormat, hlsMultivariantPlaylist2.muxedCaptionFormats, map, j2);
        list.add(buildSampleStreamWrapper);
        list2.add(iArr2);
        if (this.allowChunklessPreparation && z4) {
            ArrayList arrayList = new ArrayList();
            if (codecCountOfType > 0) {
                Format[] formatArr2 = new Format[size];
                for (int i8 = 0; i8 < size; i8++) {
                    formatArr2[i8] = deriveVideoFormat(formatArr[i8]);
                }
                arrayList.add(new TrackGroup(str2, formatArr2));
                if (codecCountOfType2 > 0 && (hlsMultivariantPlaylist2.muxedAudioFormat != null || hlsMultivariantPlaylist2.audios.isEmpty())) {
                    arrayList.add(new TrackGroup("main:audio", deriveAudioFormat(formatArr[0], hlsMultivariantPlaylist2.muxedAudioFormat, false)));
                }
                List<Format> list3 = hlsMultivariantPlaylist2.muxedCaptionFormats;
                if (list3 != null) {
                    for (int i9 = 0; i9 < list3.size(); i9++) {
                        arrayList.add(new TrackGroup(a.k("main:cc:", i9), list3.get(i9)));
                    }
                }
            } else {
                Format[] formatArr3 = new Format[size];
                for (int i10 = 0; i10 < size; i10++) {
                    formatArr3[i10] = deriveAudioFormat(formatArr[i10], hlsMultivariantPlaylist2.muxedAudioFormat, true);
                }
                arrayList.add(new TrackGroup(str2, formatArr3));
            }
            TrackGroup trackGroup = new TrackGroup("main:id3", new Format.Builder().setId("ID3").setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            arrayList.add(trackGroup);
            buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo((TrackGroup[]) arrayList.toArray(new TrackGroup[0]), 0, arrayList.indexOf(trackGroup));
        }
    }

    private void buildAndPrepareSampleStreamWrappers(long j2) {
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist());
        Map<String, DrmInitData> deriveOverridingDrmInitData = this.useSessionKeys ? deriveOverridingDrmInitData(hlsMultivariantPlaylist.sessionKeyDrmInitData) : Collections.emptyMap();
        boolean isEmpty = hlsMultivariantPlaylist.variants.isEmpty();
        List<HlsMultivariantPlaylist.Rendition> list = hlsMultivariantPlaylist.audios;
        List<HlsMultivariantPlaylist.Rendition> list2 = hlsMultivariantPlaylist.subtitles;
        int i3 = 0;
        this.pendingPrepareCount = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!isEmpty) {
            buildAndPrepareMainSampleStreamWrapper(hlsMultivariantPlaylist, j2, arrayList, arrayList2, deriveOverridingDrmInitData);
        }
        buildAndPrepareAudioSampleStreamWrappers(j2, list, arrayList, arrayList2, deriveOverridingDrmInitData);
        this.audioVideoSampleStreamWrapperCount = arrayList.size();
        int i4 = 0;
        while (i4 < list2.size()) {
            HlsMultivariantPlaylist.Rendition rendition = list2.get(i4);
            StringBuilder o3 = a.o(i4, "subtitle:", ":");
            o3.append(rendition.name);
            String sb = o3.toString();
            int i5 = i4;
            HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(sb, 3, new Uri[]{rendition.url}, new Format[]{rendition.format}, (Format) null, Collections.emptyList(), deriveOverridingDrmInitData, j2);
            arrayList2.add(new int[]{i5});
            arrayList.add(buildSampleStreamWrapper);
            buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(sb, rendition.format)}, 0, new int[0]);
            i4 = i5 + 1;
            i3 = 0;
            deriveOverridingDrmInitData = deriveOverridingDrmInitData;
        }
        int i6 = i3;
        this.sampleStreamWrappers = (HlsSampleStreamWrapper[]) arrayList.toArray(new HlsSampleStreamWrapper[i6]);
        this.manifestUrlIndicesPerWrapper = (int[][]) arrayList2.toArray(new int[i6][]);
        this.pendingPrepareCount = this.sampleStreamWrappers.length;
        for (int i7 = i6; i7 < this.audioVideoSampleStreamWrapperCount; i7++) {
            this.sampleStreamWrappers[i7].setIsTimestampMaster(true);
        }
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
        int length = hlsSampleStreamWrapperArr.length;
        for (int i8 = i6; i8 < length; i8++) {
            hlsSampleStreamWrapperArr[i8].continuePreparing();
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(String str, int i3, Uri[] uriArr, Format[] formatArr, @Nullable Format format, @Nullable List<Format> list, Map<String, DrmInitData> map, long j2) {
        return new HlsSampleStreamWrapper(str, i3, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, uriArr, formatArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, list, this.playerId), map, this.allocator, j2, format, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.eventDispatcher, this.metadataType);
    }

    private static Format deriveAudioFormat(Format format, @Nullable Format format2, boolean z2) {
        String str;
        String str2;
        int i3;
        int i4;
        int i5;
        Metadata metadata;
        String str3;
        int i6 = -1;
        if (format2 != null) {
            str3 = format2.codecs;
            metadata = format2.metadata;
            i5 = format2.channelCount;
            i4 = format2.selectionFlags;
            i3 = format2.roleFlags;
            str2 = format2.language;
            str = format2.label;
        } else {
            str3 = Util.getCodecsOfType(format.codecs, 1);
            metadata = format.metadata;
            if (z2) {
                i5 = format.channelCount;
                i4 = format.selectionFlags;
                i3 = format.roleFlags;
                str2 = format.language;
                str = format.label;
            } else {
                i4 = 0;
                str2 = null;
                i5 = -1;
                i3 = 0;
                str = null;
            }
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str3);
        int i7 = z2 ? format.averageBitrate : -1;
        if (z2) {
            i6 = format.peakBitrate;
        }
        return new Format.Builder().setId(format.id).setLabel(str).setContainerMimeType(format.containerMimeType).setSampleMimeType(mediaMimeType).setCodecs(str3).setMetadata(metadata).setAverageBitrate(i7).setPeakBitrate(i6).setChannelCount(i5).setSelectionFlags(i4).setRoleFlags(i3).setLanguage(str2).build();
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> list) {
        ArrayList arrayList = new ArrayList(list);
        HashMap hashMap = new HashMap();
        int i3 = 0;
        while (i3 < arrayList.size()) {
            DrmInitData drmInitData = list.get(i3);
            String str = drmInitData.schemeType;
            i3++;
            int i4 = i3;
            while (i4 < arrayList.size()) {
                DrmInitData drmInitData2 = (DrmInitData) arrayList.get(i4);
                if (TextUtils.equals(drmInitData2.schemeType, str)) {
                    drmInitData = drmInitData.merge(drmInitData2);
                    arrayList.remove(i4);
                } else {
                    i4++;
                }
            }
            hashMap.put(str, drmInitData);
        }
        return hashMap;
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return new Format.Builder().setId(format.id).setLabel(format.label).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(codecsOfType)).setCodecs(codecsOfType).setMetadata(format.metadata).setAverageBitrate(format.averageBitrate).setPeakBitrate(format.peakBitrate).setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).build();
    }

    public boolean continueLoading(long j2) {
        if (this.trackGroups != null) {
            return this.compositeSequenceableLoader.continueLoading(j2);
        }
        for (HlsSampleStreamWrapper continuePreparing : this.sampleStreamWrappers) {
            continuePreparing.continuePreparing();
        }
        return false;
    }

    public void discardBuffer(long j2, boolean z2) {
        for (HlsSampleStreamWrapper discardBuffer : this.enabledSampleStreamWrappers) {
            discardBuffer.discardBuffer(j2, z2);
        }
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            if (hlsSampleStreamWrapper.isVideoSampleStream()) {
                return hlsSampleStreamWrapper.getAdjustedSeekPositionUs(j2, seekParameters);
            }
        }
        return j2;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        TrackGroupArray trackGroupArray;
        int[] iArr;
        int i3;
        HlsMediaPeriod hlsMediaPeriod = this;
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(hlsMediaPeriod.playlistTracker.getMultivariantPlaylist());
        boolean isEmpty = hlsMultivariantPlaylist.variants.isEmpty();
        boolean z2 = !isEmpty;
        int length = hlsMediaPeriod.sampleStreamWrappers.length - hlsMultivariantPlaylist.subtitles.size();
        int i4 = 0;
        if (!isEmpty) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = hlsMediaPeriod.sampleStreamWrappers[0];
            iArr = hlsMediaPeriod.manifestUrlIndicesPerWrapper[0];
            trackGroupArray = hlsSampleStreamWrapper.getTrackGroups();
            i3 = hlsSampleStreamWrapper.getPrimaryTrackGroupIndex();
        } else {
            iArr = new int[0];
            trackGroupArray = TrackGroupArray.EMPTY;
            i3 = 0;
        }
        ArrayList arrayList = new ArrayList();
        boolean z3 = false;
        boolean z4 = false;
        for (ExoTrackSelection next : list) {
            TrackGroup trackGroup = next.getTrackGroup();
            int indexOf = trackGroupArray.indexOf(trackGroup);
            if (indexOf == -1) {
                int i5 = z2;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = hlsMediaPeriod.sampleStreamWrappers;
                    if (i5 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    } else if (hlsSampleStreamWrapperArr[i5].getTrackGroups().indexOf(trackGroup) != -1) {
                        int i6 = i5 < length ? 1 : 2;
                        int[] iArr2 = hlsMediaPeriod.manifestUrlIndicesPerWrapper[i5];
                        int i7 = 0;
                        while (i7 < next.length()) {
                            arrayList.add(new StreamKey(i6, iArr2[next.getIndexInTrackGroup(i7)]));
                            i7++;
                        }
                    } else {
                        i5++;
                        hlsMediaPeriod = this;
                    }
                }
            } else if (indexOf == i3) {
                for (int i8 = i4; i8 < next.length(); i8++) {
                    arrayList.add(new StreamKey(i4, iArr[next.getIndexInTrackGroup(i8)]));
                }
                z4 = true;
            } else {
                z3 = true;
            }
            hlsMediaPeriod = this;
            i4 = 0;
        }
        if (z3 && !z4) {
            int i9 = iArr[0];
            int i10 = hlsMultivariantPlaylist.variants.get(i9).format.bitrate;
            for (int i11 = 1; i11 < iArr.length; i11++) {
                int i12 = hlsMultivariantPlaylist.variants.get(iArr[i11]).format.bitrate;
                if (i12 < i10) {
                    i9 = iArr[i11];
                    i10 = i12;
                }
            }
            arrayList.add(new StreamKey(0, i9));
        }
        return arrayList;
    }

    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper maybeThrowPrepareError : this.sampleStreamWrappers) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public void onPlaylistChanged() {
        for (HlsSampleStreamWrapper onPlaylistUpdated : this.sampleStreamWrappers) {
            onPlaylistUpdated.onPlaylistUpdated();
        }
        this.callback.onContinueLoadingRequested(this);
    }

    public boolean onPlaylistError(Uri uri, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, boolean z2) {
        boolean z3 = true;
        for (HlsSampleStreamWrapper onPlaylistError : this.sampleStreamWrappers) {
            z3 &= onPlaylistError.onPlaylistError(uri, loadErrorInfo, z2);
        }
        this.callback.onContinueLoadingRequested(this);
        return z3;
    }

    public void onPlaylistRefreshRequired(Uri uri) {
        this.playlistTracker.refreshPlaylist(uri);
    }

    public void onPrepared() {
        int i3 = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i3;
        if (i3 <= 0) {
            int i4 = 0;
            for (HlsSampleStreamWrapper trackGroups2 : this.sampleStreamWrappers) {
                i4 += trackGroups2.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i4];
            int i5 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
                int i6 = hlsSampleStreamWrapper.getTrackGroups().length;
                int i7 = 0;
                while (i7 < i6) {
                    trackGroupArr[i5] = hlsSampleStreamWrapper.getTrackGroups().get(i7);
                    i7++;
                    i5++;
                }
            }
            this.trackGroups = new TrackGroupArray(trackGroupArr);
            this.callback.onPrepared(this);
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j2);
    }

    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public void reevaluateBuffer(long j2) {
        this.compositeSequenceableLoader.reevaluateBuffer(j2);
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper release : this.sampleStreamWrappers) {
            release.release();
        }
        this.callback = null;
    }

    public long seekToUs(long j2) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean seekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j2, false);
            int i3 = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i3 >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i3].seekToUs(j2, seekToUs);
                i3++;
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j2;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        boolean z2;
        ExoTrackSelection[] exoTrackSelectionArr2 = exoTrackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[exoTrackSelectionArr2.length];
        int[] iArr2 = new int[exoTrackSelectionArr2.length];
        for (int i3 = 0; i3 < exoTrackSelectionArr2.length; i3++) {
            SampleStream sampleStream = sampleStreamArr2[i3];
            iArr[i3] = sampleStream == null ? -1 : this.streamWrapperIndices.get(sampleStream).intValue();
            iArr2[i3] = -1;
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr2[i3];
            if (exoTrackSelection != null) {
                TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
                int i4 = 0;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (i4 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    } else if (hlsSampleStreamWrapperArr[i4].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i3] = i4;
                        break;
                    } else {
                        i4++;
                    }
                }
            }
        }
        this.streamWrapperIndices.clear();
        int length = exoTrackSelectionArr2.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[exoTrackSelectionArr2.length];
        ExoTrackSelection[] exoTrackSelectionArr3 = new ExoTrackSelection[exoTrackSelectionArr2.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i5 = 0;
        int i6 = 0;
        boolean z3 = false;
        while (i6 < this.sampleStreamWrappers.length) {
            for (int i7 = 0; i7 < exoTrackSelectionArr2.length; i7++) {
                ExoTrackSelection exoTrackSelection2 = null;
                sampleStreamArr4[i7] = iArr[i7] == i6 ? sampleStreamArr2[i7] : null;
                if (iArr2[i7] == i6) {
                    exoTrackSelection2 = exoTrackSelectionArr2[i7];
                }
                exoTrackSelectionArr3[i7] = exoTrackSelection2;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i6];
            HlsSampleStreamWrapper hlsSampleStreamWrapper2 = hlsSampleStreamWrapper;
            int i8 = i5;
            int i9 = length;
            int i10 = i6;
            ExoTrackSelection[] exoTrackSelectionArr4 = exoTrackSelectionArr3;
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr3 = hlsSampleStreamWrapperArr2;
            boolean selectTracks = hlsSampleStreamWrapper.selectTracks(exoTrackSelectionArr3, zArr, sampleStreamArr4, zArr2, j2, z3);
            int i11 = 0;
            boolean z4 = false;
            while (true) {
                z2 = true;
                if (i11 >= exoTrackSelectionArr2.length) {
                    break;
                }
                SampleStream sampleStream2 = sampleStreamArr4[i11];
                if (iArr2[i11] == i10) {
                    Assertions.checkNotNull(sampleStream2);
                    sampleStreamArr3[i11] = sampleStream2;
                    this.streamWrapperIndices.put(sampleStream2, Integer.valueOf(i10));
                    z4 = true;
                } else if (iArr[i11] == i10) {
                    if (sampleStream2 != null) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                }
                i11++;
            }
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr4 = hlsSampleStreamWrapperArr3;
            if (z4) {
                hlsSampleStreamWrapperArr4[i8] = hlsSampleStreamWrapper2;
                i5 = i8 + 1;
                if (i8 == 0) {
                    hlsSampleStreamWrapper2.setIsTimestampMaster(true);
                    if (!selectTracks) {
                        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr5 = this.enabledSampleStreamWrappers;
                        if (hlsSampleStreamWrapperArr5.length != 0 && hlsSampleStreamWrapper2 == hlsSampleStreamWrapperArr5[0]) {
                        }
                    }
                    this.timestampAdjusterProvider.reset();
                    z3 = true;
                } else {
                    if (i10 >= this.audioVideoSampleStreamWrapperCount) {
                        z2 = false;
                    }
                    hlsSampleStreamWrapper2.setIsTimestampMaster(z2);
                }
            } else {
                i5 = i8;
            }
            i6 = i10 + 1;
            sampleStreamArr2 = sampleStreamArr;
            hlsSampleStreamWrapperArr2 = hlsSampleStreamWrapperArr4;
            length = i9;
            exoTrackSelectionArr3 = exoTrackSelectionArr4;
        }
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr2, 0, length);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr6 = (HlsSampleStreamWrapper[]) Util.nullSafeArrayCopy(hlsSampleStreamWrapperArr2, i5);
        this.enabledSampleStreamWrappers = hlsSampleStreamWrapperArr6;
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(hlsSampleStreamWrapperArr6);
        return j2;
    }

    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.callback.onContinueLoadingRequested(this);
    }
}
