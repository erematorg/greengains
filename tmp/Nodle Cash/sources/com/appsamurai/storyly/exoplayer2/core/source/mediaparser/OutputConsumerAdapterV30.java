package com.appsamurai.storyly.exoplayer2.core.source.mediaparser;

import android.annotation.SuppressLint;
import android.media.DrmInitData;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaParser;
import android.media.MediaParser$InputReader;
import android.media.MediaParser$OutputConsumer;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.SieveCacheKt;
import androidx.window.layout.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.ColorInfo;
import com.appsamurai.storyly.exoplayer2.core.source.f;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.CharUtils;

@RequiresApi(30)
@SuppressLint({"Override"})
public final class OutputConsumerAdapterV30 implements MediaParser$OutputConsumer {
    private static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_DURATIONS = "chunk-index-long-us-durations";
    private static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_OFFSETS = "chunk-index-long-offsets";
    private static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_SIZES = "chunk-index-int-sizes";
    private static final String MEDIA_FORMAT_KEY_CHUNK_INDEX_TIMES = "chunk-index-long-us-times";
    private static final String MEDIA_FORMAT_KEY_TRACK_TYPE = "track-type-string";
    private static final Pattern REGEX_CRYPTO_INFO_PATTERN = Pattern.compile("pattern \\(encrypt: (\\d+), skip: (\\d+)\\)");
    private static final Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> SEEK_POINT_PAIR_START = Pair.create(MediaParser.SeekPoint.START, MediaParser.SeekPoint.START);
    private static final String TAG = "OConsumerAdapterV30";
    @Nullable
    private String containerMimeType;
    @Nullable
    private MediaParser.SeekMap dummySeekMap;
    private final boolean expectDummySeekMap;
    private ExtractorOutput extractorOutput;
    @Nullable
    private ChunkIndex lastChunkIndex;
    private final ArrayList<TrackOutput.CryptoData> lastOutputCryptoDatas;
    private final ArrayList<MediaCodec.CryptoInfo> lastReceivedCryptoInfos;
    @Nullable
    private MediaParser.SeekMap lastSeekMap;
    private List<Format> muxedCaptionFormats;
    private int primaryTrackIndex;
    @Nullable
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private long sampleTimestampUpperLimitFilterUs;
    private final DataReaderAdapter scratchDataReaderAdapter;
    private boolean seekingDisabled;
    @Nullable
    private TimestampAdjuster timestampAdjuster;
    private final ArrayList<Format> trackFormats;
    private final ArrayList<TrackOutput> trackOutputs;
    private boolean tracksEnded;
    private boolean tracksFoundCalled;

    public static final class DataReaderAdapter implements DataReader {
        @Nullable
        public MediaParser$InputReader input;

        private DataReaderAdapter() {
        }

        public int read(byte[] bArr, int i3, int i4) throws IOException {
            return a.o(Util.castNonNull(this.input)).read(bArr, i3, i4);
        }
    }

    public static final class SeekMapAdapter implements SeekMap {
        private final MediaParser.SeekMap adaptedSeekMap;

        public SeekMapAdapter(MediaParser.SeekMap seekMap) {
            this.adaptedSeekMap = seekMap;
        }

        private static SeekPoint asExoPlayerSeekPoint(MediaParser.SeekPoint seekPoint) {
            return new SeekPoint(seekPoint.timeMicros, seekPoint.position);
        }

        public long getDurationUs() {
            long h3 = this.adaptedSeekMap.getDurationMicros();
            return h3 != SieveCacheKt.NodeMetaAndPreviousMask ? h3 : C.TIME_UNSET;
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            Pair s3 = this.adaptedSeekMap.getSeekPoints(j2);
            Object obj = s3.first;
            return obj == s3.second ? new SeekMap.SeekPoints(asExoPlayerSeekPoint(f.b(obj))) : new SeekMap.SeekPoints(asExoPlayerSeekPoint(f.b(obj)), asExoPlayerSeekPoint(f.b(s3.second)));
        }

        public boolean isSeekable() {
            return this.adaptedSeekMap.isSeekable();
        }
    }

    public OutputConsumerAdapterV30() {
        this((Format) null, -2, false);
    }

    private void ensureSpaceForTrackIndex(int i3) {
        for (int size = this.trackOutputs.size(); size <= i3; size++) {
            this.trackOutputs.add((Object) null);
            this.trackFormats.add((Object) null);
            this.lastReceivedCryptoInfos.add((Object) null);
            this.lastOutputCryptoDatas.add((Object) null);
        }
    }

    private static byte[] getArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    @Nullable
    private static ColorInfo getColorInfo(MediaFormat mediaFormat) {
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("hdr-static-info");
        byte[] array = byteBuffer != null ? getArray(byteBuffer) : null;
        int integer = mediaFormat.getInteger("color-transfer", -1);
        int integer2 = mediaFormat.getInteger("color-range", -1);
        int integer3 = mediaFormat.getInteger("color-standard", -1);
        if (array == null && integer == -1 && integer2 == -1 && integer3 == -1) {
            return null;
        }
        return new ColorInfo(integer3, integer2, integer, array);
    }

    private static int getFlag(MediaFormat mediaFormat, String str, int i3) {
        if (mediaFormat.getInteger(str, 0) != 0) {
            return i3;
        }
        return 0;
    }

    private static List<byte[]> getInitializationData(MediaFormat mediaFormat) {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            StringBuilder sb = new StringBuilder("csd-");
            int i4 = i3 + 1;
            sb.append(i3);
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer(sb.toString());
            if (byteBuffer == null) {
                return arrayList;
            }
            arrayList.add(getArray(byteBuffer));
            i3 = i4;
        }
    }

    private static String getMimeType(String str) {
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -2063506020:
                if (str.equals("android.media.mediaparser.Mp4Parser")) {
                    c3 = 0;
                    break;
                }
                break;
            case -1870824006:
                if (str.equals("android.media.mediaparser.OggParser")) {
                    c3 = 1;
                    break;
                }
                break;
            case -1566427438:
                if (str.equals("android.media.mediaparser.TsParser")) {
                    c3 = 2;
                    break;
                }
                break;
            case -900207883:
                if (str.equals("android.media.mediaparser.AdtsParser")) {
                    c3 = 3;
                    break;
                }
                break;
            case -589864617:
                if (str.equals("android.media.mediaparser.WavParser")) {
                    c3 = 4;
                    break;
                }
                break;
            case 52265814:
                if (str.equals("android.media.mediaparser.PsParser")) {
                    c3 = 5;
                    break;
                }
                break;
            case 116768877:
                if (str.equals("android.media.mediaparser.FragmentedMp4Parser")) {
                    c3 = 6;
                    break;
                }
                break;
            case 376876796:
                if (str.equals("android.media.mediaparser.Ac3Parser")) {
                    c3 = 7;
                    break;
                }
                break;
            case 703268017:
                if (str.equals("android.media.mediaparser.AmrParser")) {
                    c3 = 8;
                    break;
                }
                break;
            case 768643067:
                if (str.equals("android.media.mediaparser.FlacParser")) {
                    c3 = 9;
                    break;
                }
                break;
            case 965962719:
                if (str.equals("android.media.mediaparser.MatroskaParser")) {
                    c3 = 10;
                    break;
                }
                break;
            case 1264380477:
                if (str.equals("android.media.mediaparser.Ac4Parser")) {
                    c3 = 11;
                    break;
                }
                break;
            case 1343957595:
                if (str.equals("android.media.mediaparser.Mp3Parser")) {
                    c3 = 12;
                    break;
                }
                break;
            case 2063134683:
                if (str.equals("android.media.mediaparser.FlvParser")) {
                    c3 = CharUtils.CR;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            case 6:
                return MimeTypes.VIDEO_MP4;
            case 1:
                return MimeTypes.AUDIO_OGG;
            case 2:
                return MimeTypes.VIDEO_MP2T;
            case 3:
                return MimeTypes.AUDIO_AAC;
            case 4:
                return MimeTypes.AUDIO_RAW;
            case 5:
                return MimeTypes.VIDEO_PS;
            case 7:
                return MimeTypes.AUDIO_AC3;
            case 8:
                return MimeTypes.AUDIO_AMR;
            case 9:
                return MimeTypes.AUDIO_FLAC;
            case 10:
                return MimeTypes.VIDEO_WEBM;
            case 11:
                return MimeTypes.AUDIO_AC4;
            case 12:
                return MimeTypes.AUDIO_MPEG;
            case 13:
                return MimeTypes.VIDEO_FLV;
            default:
                throw new IllegalArgumentException("Illegal parser name: ".concat(str));
        }
    }

    private static int getSelectionFlags(MediaFormat mediaFormat) {
        return getFlag(mediaFormat, "is-forced-subtitle", 2) | getFlag(mediaFormat, "is-autoselect", 4) | getFlag(mediaFormat, "is-default", 1);
    }

    private void maybeEndTracks() {
        if (this.tracksFoundCalled && !this.tracksEnded) {
            int size = this.trackOutputs.size();
            int i3 = 0;
            while (i3 < size) {
                if (this.trackOutputs.get(i3) != null) {
                    i3++;
                } else {
                    return;
                }
            }
            this.extractorOutput.endTracks();
            this.tracksEnded = true;
        }
    }

    private boolean maybeObtainChunkIndex(MediaFormat mediaFormat) {
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer(MEDIA_FORMAT_KEY_CHUNK_INDEX_SIZES);
        if (byteBuffer == null) {
            return false;
        }
        IntBuffer asIntBuffer = byteBuffer.asIntBuffer();
        LongBuffer asLongBuffer = ((ByteBuffer) Assertions.checkNotNull(mediaFormat.getByteBuffer(MEDIA_FORMAT_KEY_CHUNK_INDEX_OFFSETS))).asLongBuffer();
        LongBuffer asLongBuffer2 = ((ByteBuffer) Assertions.checkNotNull(mediaFormat.getByteBuffer(MEDIA_FORMAT_KEY_CHUNK_INDEX_DURATIONS))).asLongBuffer();
        LongBuffer asLongBuffer3 = ((ByteBuffer) Assertions.checkNotNull(mediaFormat.getByteBuffer(MEDIA_FORMAT_KEY_CHUNK_INDEX_TIMES))).asLongBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        long[] jArr = new long[asLongBuffer.remaining()];
        long[] jArr2 = new long[asLongBuffer2.remaining()];
        long[] jArr3 = new long[asLongBuffer3.remaining()];
        asIntBuffer.get(iArr);
        asLongBuffer.get(jArr);
        asLongBuffer2.get(jArr2);
        asLongBuffer3.get(jArr3);
        ChunkIndex chunkIndex = new ChunkIndex(iArr, jArr, jArr2, jArr3);
        this.lastChunkIndex = chunkIndex;
        this.extractorOutput.seekMap(chunkIndex);
        return true;
    }

    @Nullable
    private TrackOutput.CryptoData toExoPlayerCryptoData(int i3, @Nullable MediaCodec.CryptoInfo cryptoInfo) {
        int i4;
        int i5;
        if (cryptoInfo == null) {
            return null;
        }
        if (this.lastReceivedCryptoInfos.get(i3) == cryptoInfo) {
            return (TrackOutput.CryptoData) Assertions.checkNotNull(this.lastOutputCryptoDatas.get(i3));
        }
        try {
            Matcher matcher = REGEX_CRYPTO_INFO_PATTERN.matcher(cryptoInfo.toString());
            matcher.find();
            i4 = Integer.parseInt((String) Util.castNonNull(matcher.group(1)));
            i5 = Integer.parseInt((String) Util.castNonNull(matcher.group(2)));
        } catch (RuntimeException e3) {
            Log.e(TAG, "Unexpected error while parsing CryptoInfo: " + cryptoInfo, e3);
            i4 = 0;
            i5 = 0;
        }
        TrackOutput.CryptoData cryptoData = new TrackOutput.CryptoData(cryptoInfo.mode, cryptoInfo.key, i4, i5);
        this.lastReceivedCryptoInfos.set(i3, cryptoInfo);
        this.lastOutputCryptoDatas.set(i3, cryptoData);
        return cryptoData;
    }

    @Nullable
    private static DrmInitData toExoPlayerDrmInitData(@Nullable String str, @Nullable android.media.DrmInitData drmInitData) {
        if (drmInitData == null) {
            return null;
        }
        int c3 = drmInitData.getSchemeInitDataCount();
        DrmInitData.SchemeData[] schemeDataArr = new DrmInitData.SchemeData[c3];
        for (int i3 = 0; i3 < c3; i3++) {
            DrmInitData.SchemeInitData l2 = drmInitData.getSchemeInitDataAt(i3);
            schemeDataArr[i3] = new DrmInitData.SchemeData(l2.uuid, l2.mimeType, l2.data);
        }
        return new com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData(str, schemeDataArr);
    }

    private Format toExoPlayerFormat(MediaParser.TrackData trackData) {
        MediaFormat n2 = trackData.mediaFormat;
        String string = n2.getString("mime");
        int integer = n2.getInteger("caption-service-number", -1);
        int i3 = 0;
        Format.Builder accessibilityChannel = new Format.Builder().setDrmInitData(toExoPlayerDrmInitData(n2.getString("crypto-mode-fourcc"), trackData.drmInitData)).setContainerMimeType(this.containerMimeType).setPeakBitrate(n2.getInteger("bitrate", -1)).setChannelCount(n2.getInteger("channel-count", -1)).setColorInfo(getColorInfo(n2)).setSampleMimeType(string).setCodecs(n2.getString("codecs-string")).setFrameRate(n2.getFloat("frame-rate", -1.0f)).setWidth(n2.getInteger("width", -1)).setHeight(n2.getInteger("height", -1)).setInitializationData(getInitializationData(n2)).setLanguage(n2.getString("language")).setMaxInputSize(n2.getInteger("max-input-size", -1)).setPcmEncoding(n2.getInteger("exo-pcm-encoding", -1)).setRotationDegrees(n2.getInteger("rotation-degrees", 0)).setSampleRate(n2.getInteger("sample-rate", -1)).setSelectionFlags(getSelectionFlags(n2)).setEncoderDelay(n2.getInteger("encoder-delay", 0)).setEncoderPadding(n2.getInteger("encoder-padding", 0)).setPixelWidthHeightRatio(n2.getFloat("pixel-width-height-ratio-float", 1.0f)).setSubsampleOffsetUs(n2.getLong("subsample-offset-us-long", Long.MAX_VALUE)).setAccessibilityChannel(integer);
        while (true) {
            if (i3 >= this.muxedCaptionFormats.size()) {
                break;
            }
            Format format = this.muxedCaptionFormats.get(i3);
            if (Util.areEqual(format.sampleMimeType, string) && format.accessibilityChannel == integer) {
                accessibilityChannel.setLanguage(format.language).setRoleFlags(format.roleFlags).setSelectionFlags(format.selectionFlags).setLabel(format.label).setMetadata(format.metadata);
                break;
            }
            i3++;
        }
        return accessibilityChannel.build();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int toTrackTypeConstant(@androidx.annotation.Nullable java.lang.String r5) {
        /*
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = -1
            if (r5 != 0) goto L_0x0007
            return r3
        L_0x0007:
            int r4 = r5.hashCode()
            switch(r4) {
                case -450004177: goto L_0x003f;
                case -284840886: goto L_0x0033;
                case 3556653: goto L_0x0027;
                case 93166550: goto L_0x001c;
                case 112202875: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r4 = r3
            goto L_0x0049
        L_0x0010:
            java.lang.String r4 = "video"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x001a
            goto L_0x000e
        L_0x001a:
            r4 = 4
            goto L_0x0049
        L_0x001c:
            java.lang.String r4 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0025
            goto L_0x000e
        L_0x0025:
            r4 = r0
            goto L_0x0049
        L_0x0027:
            java.lang.String r4 = "text"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0031
            goto L_0x000e
        L_0x0031:
            r4 = r1
            goto L_0x0049
        L_0x0033:
            java.lang.String r4 = "unknown"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x003d
            goto L_0x000e
        L_0x003d:
            r4 = r2
            goto L_0x0049
        L_0x003f:
            java.lang.String r4 = "metadata"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0048
            goto L_0x000e
        L_0x0048:
            r4 = 0
        L_0x0049:
            switch(r4) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0054;
                case 2: goto L_0x0053;
                case 3: goto L_0x0052;
                case 4: goto L_0x0051;
                default: goto L_0x004c;
            }
        L_0x004c:
            int r5 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.getTrackType(r5)
            return r5
        L_0x0051:
            return r1
        L_0x0052:
            return r2
        L_0x0053:
            return r0
        L_0x0054:
            return r3
        L_0x0055:
            r5 = 5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.mediaparser.OutputConsumerAdapterV30.toTrackTypeConstant(java.lang.String):int");
    }

    public void disableSeeking() {
        this.seekingDisabled = true;
    }

    @Nullable
    public ChunkIndex getChunkIndex() {
        return this.lastChunkIndex;
    }

    @Nullable
    public MediaParser.SeekMap getDummySeekMap() {
        return this.dummySeekMap;
    }

    @Nullable
    public Format[] getSampleFormats() {
        if (!this.tracksFoundCalled) {
            return null;
        }
        Format[] formatArr = new Format[this.trackFormats.size()];
        for (int i3 = 0; i3 < this.trackFormats.size(); i3++) {
            formatArr[i3] = (Format) Assertions.checkNotNull(this.trackFormats.get(i3));
        }
        return formatArr;
    }

    public Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> getSeekPoints(long j2) {
        MediaParser.SeekMap seekMap = this.lastSeekMap;
        return seekMap != null ? seekMap.getSeekPoints(j2) : SEEK_POINT_PAIR_START;
    }

    public void onSampleCompleted(int i3, long j2, int i4, int i5, int i6, @Nullable MediaCodec.CryptoInfo cryptoInfo) {
        long j3 = this.sampleTimestampUpperLimitFilterUs;
        if (j3 == C.TIME_UNSET || j2 < j3) {
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                j2 = timestampAdjuster2.adjustSampleTimestamp(j2);
            }
            ((TrackOutput) Assertions.checkNotNull(this.trackOutputs.get(i3))).sampleMetadata(j2, i4, i5, i6, toExoPlayerCryptoData(i3, cryptoInfo));
        }
    }

    public void onSampleDataFound(int i3, MediaParser$InputReader mediaParser$InputReader) throws IOException {
        ensureSpaceForTrackIndex(i3);
        this.scratchDataReaderAdapter.input = mediaParser$InputReader;
        TrackOutput trackOutput = this.trackOutputs.get(i3);
        if (trackOutput == null) {
            trackOutput = this.extractorOutput.track(i3, -1);
            this.trackOutputs.set(i3, trackOutput);
        }
        trackOutput.sampleData((DataReader) this.scratchDataReaderAdapter, (int) mediaParser$InputReader.getLength(), true);
    }

    public void onSeekMapFound(MediaParser.SeekMap seekMap) {
        SeekMap seekMap2;
        if (!this.expectDummySeekMap || this.dummySeekMap != null) {
            this.lastSeekMap = seekMap;
            long h3 = seekMap.getDurationMicros();
            ExtractorOutput extractorOutput2 = this.extractorOutput;
            if (this.seekingDisabled) {
                if (h3 == SieveCacheKt.NodeMetaAndPreviousMask) {
                    h3 = C.TIME_UNSET;
                }
                seekMap2 = new SeekMap.Unseekable(h3);
            } else {
                seekMap2 = new SeekMapAdapter(seekMap);
            }
            extractorOutput2.seekMap(seekMap2);
            return;
        }
        this.dummySeekMap = seekMap;
    }

    public void onTrackCountFound(int i3) {
        this.tracksFoundCalled = true;
        maybeEndTracks();
    }

    public void onTrackDataFound(int i3, MediaParser.TrackData trackData) {
        if (!maybeObtainChunkIndex(trackData.mediaFormat)) {
            ensureSpaceForTrackIndex(i3);
            TrackOutput trackOutput = this.trackOutputs.get(i3);
            if (trackOutput == null) {
                String string = trackData.mediaFormat.getString(MEDIA_FORMAT_KEY_TRACK_TYPE);
                int trackTypeConstant = toTrackTypeConstant(string != null ? string : trackData.mediaFormat.getString("mime"));
                if (trackTypeConstant == this.primaryTrackType) {
                    this.primaryTrackIndex = i3;
                }
                TrackOutput track = this.extractorOutput.track(i3, trackTypeConstant);
                this.trackOutputs.set(i3, track);
                if (string == null) {
                    trackOutput = track;
                } else {
                    return;
                }
            }
            Format exoPlayerFormat = toExoPlayerFormat(trackData);
            Format format = this.primaryTrackManifestFormat;
            trackOutput.format((format == null || i3 != this.primaryTrackIndex) ? exoPlayerFormat : exoPlayerFormat.withManifestFormatInfo(format));
            this.trackFormats.set(i3, exoPlayerFormat);
            maybeEndTracks();
        }
    }

    public void setExtractorOutput(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void setMuxedCaptionFormats(List<Format> list) {
        this.muxedCaptionFormats = list;
    }

    public void setSampleTimestampUpperLimitFilterUs(long j2) {
        this.sampleTimestampUpperLimitFilterUs = j2;
    }

    public void setSelectedParserName(String str) {
        this.containerMimeType = getMimeType(str);
    }

    public void setTimestampAdjuster(TimestampAdjuster timestampAdjuster2) {
        this.timestampAdjuster = timestampAdjuster2;
    }

    public OutputConsumerAdapterV30(@Nullable Format format, int i3, boolean z2) {
        this.expectDummySeekMap = z2;
        this.primaryTrackManifestFormat = format;
        this.primaryTrackType = i3;
        this.trackOutputs = new ArrayList<>();
        this.trackFormats = new ArrayList<>();
        this.lastReceivedCryptoInfos = new ArrayList<>();
        this.lastOutputCryptoDatas = new ArrayList<>();
        this.scratchDataReaderAdapter = new DataReaderAdapter();
        this.extractorOutput = new DummyExtractorOutput();
        this.sampleTimestampUpperLimitFilterUs = C.TIME_UNSET;
        this.muxedCaptionFormats = ImmutableList.of();
    }
}
