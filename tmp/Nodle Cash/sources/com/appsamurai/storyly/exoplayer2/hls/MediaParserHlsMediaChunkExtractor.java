package com.appsamurai.storyly.exoplayer2.hls;

import android.annotation.SuppressLint;
import android.media.MediaFormat;
import android.media.MediaParser;
import android.media.MediaParser$OutputConsumer;
import android.media.MediaParser$SeekableInputReader;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.FileTypes;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.InputReaderAdapterV30;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.MediaParserUtil;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.OutputConsumerAdapterV30;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiresApi(30)
public final class MediaParserHlsMediaChunkExtractor implements HlsMediaChunkExtractor {
    public static final HlsExtractorFactory FACTORY = new a(8);
    private final Format format;
    private final InputReaderAdapterV30 inputReaderAdapter = new InputReaderAdapterV30();
    private final MediaParser mediaParser;
    private final ImmutableList<MediaFormat> muxedCaptionMediaFormats;
    private final OutputConsumerAdapterV30 outputConsumerAdapter;
    private final boolean overrideInBandCaptionDeclarations;
    private int pendingSkipBytes;
    private final PlayerId playerId;

    public static final class PeekingInputReader implements MediaParser$SeekableInputReader {
        private final ExtractorInput extractorInput;
        /* access modifiers changed from: private */
        public int totalPeekedBytes;

        public long getLength() {
            return this.extractorInput.getLength();
        }

        public long getPosition() {
            return this.extractorInput.getPeekPosition();
        }

        public int read(byte[] bArr, int i3, int i4) throws IOException {
            int peek = this.extractorInput.peek(bArr, i3, i4);
            this.totalPeekedBytes += peek;
            return peek;
        }

        public void seekToPosition(long j2) {
            throw new UnsupportedOperationException();
        }

        private PeekingInputReader(ExtractorInput extractorInput2) {
            this.extractorInput = extractorInput2;
        }
    }

    public MediaParserHlsMediaChunkExtractor(MediaParser mediaParser2, OutputConsumerAdapterV30 outputConsumerAdapterV30, Format format2, boolean z2, ImmutableList<MediaFormat> immutableList, int i3, PlayerId playerId2) {
        this.mediaParser = mediaParser2;
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        this.overrideInBandCaptionDeclarations = z2;
        this.muxedCaptionMediaFormats = immutableList;
        this.format = format2;
        this.playerId = playerId2;
        this.pendingSkipBytes = i3;
    }

    @SuppressLint({"WrongConstant"})
    private static MediaParser createMediaParserInstance(MediaParser$OutputConsumer mediaParser$OutputConsumer, Format format2, boolean z2, ImmutableList<MediaFormat> immutableList, PlayerId playerId2, String... strArr) {
        MediaParser q2 = strArr.length == 1 ? MediaParser.createByName(strArr[0], mediaParser$OutputConsumer) : MediaParser.create(mediaParser$OutputConsumer, strArr);
        q2.setParameter(MediaParserUtil.PARAMETER_EXPOSE_CAPTION_FORMATS, immutableList);
        q2.setParameter(MediaParserUtil.PARAMETER_OVERRIDE_IN_BAND_CAPTION_DECLARATIONS, Boolean.valueOf(z2));
        q2.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, Boolean.TRUE);
        q2.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, Boolean.TRUE);
        q2.setParameter(MediaParserUtil.PARAMETER_IGNORE_TIMESTAMP_OFFSET, Boolean.TRUE);
        q2.setParameter("android.media.mediaparser.ts.ignoreSpliceInfoStream", Boolean.TRUE);
        q2.setParameter("android.media.mediaparser.ts.mode", "hls");
        String str = format2.codecs;
        if (!TextUtils.isEmpty(str)) {
            if (!MimeTypes.AUDIO_AAC.equals(MimeTypes.getAudioMediaMimeType(str))) {
                q2.setParameter("android.media.mediaparser.ts.ignoreAacStream", Boolean.TRUE);
            }
            if (!MimeTypes.VIDEO_H264.equals(MimeTypes.getVideoMediaMimeType(str))) {
                q2.setParameter("android.media.mediaparser.ts.ignoreAvcStream", Boolean.TRUE);
            }
        }
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(q2, playerId2);
        }
        return q2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HlsMediaChunkExtractor lambda$static$0(Uri uri, Format format2, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput, PlayerId playerId2) throws IOException {
        if (FileTypes.inferFileTypeFromMimeType(format2.sampleMimeType) == 13) {
            return new BundledHlsMediaChunkExtractor(new WebvttExtractor(format2.language, timestampAdjuster), format2, timestampAdjuster);
        }
        boolean z2 = list != null;
        ImmutableList.Builder builder = ImmutableList.builder();
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                builder.add((Object) MediaParserUtil.toCaptionsMediaFormat((Format) list.get(i3)));
            }
        } else {
            builder.add((Object) MediaParserUtil.toCaptionsMediaFormat(new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA608).build()));
        }
        ImmutableList build = builder.build();
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30();
        if (list == null) {
            list = ImmutableList.of();
        }
        outputConsumerAdapterV30.setMuxedCaptionFormats(list);
        outputConsumerAdapterV30.setTimestampAdjuster(timestampAdjuster);
        MediaParser createMediaParserInstance = createMediaParserInstance(outputConsumerAdapterV30, format2, z2, build, playerId2, "android.media.mediaparser.FragmentedMp4Parser", "android.media.mediaparser.Ac3Parser", "android.media.mediaparser.Ac4Parser", "android.media.mediaparser.AdtsParser", "android.media.mediaparser.Mp3Parser", "android.media.mediaparser.TsParser");
        PeekingInputReader peekingInputReader = new PeekingInputReader(extractorInput);
        createMediaParserInstance.advance(peekingInputReader);
        outputConsumerAdapterV30.setSelectedParserName(createMediaParserInstance.getParserName());
        return new MediaParserHlsMediaChunkExtractor(createMediaParserInstance, outputConsumerAdapterV30, format2, z2, build, peekingInputReader.totalPeekedBytes, playerId2);
    }

    public void init(ExtractorOutput extractorOutput) {
        this.outputConsumerAdapter.setExtractorOutput(extractorOutput);
    }

    public boolean isPackedAudioExtractor() {
        String d2 = this.mediaParser.getParserName();
        return "android.media.mediaparser.Ac3Parser".equals(d2) || "android.media.mediaparser.Ac4Parser".equals(d2) || "android.media.mediaparser.AdtsParser".equals(d2) || "android.media.mediaparser.Mp3Parser".equals(d2);
    }

    public boolean isReusable() {
        String d2 = this.mediaParser.getParserName();
        return "android.media.mediaparser.FragmentedMp4Parser".equals(d2) || "android.media.mediaparser.TsParser".equals(d2);
    }

    public void onTruncatedSegmentParsed() {
        this.mediaParser.seek(MediaParser.SeekPoint.START);
    }

    public boolean read(ExtractorInput extractorInput) throws IOException {
        extractorInput.skipFully(this.pendingSkipBytes);
        this.pendingSkipBytes = 0;
        this.inputReaderAdapter.setDataReader(extractorInput, extractorInput.getLength());
        return this.mediaParser.advance(this.inputReaderAdapter);
    }

    public HlsMediaChunkExtractor recreate() {
        Assertions.checkState(!isReusable());
        return new MediaParserHlsMediaChunkExtractor(createMediaParserInstance(this.outputConsumerAdapter, this.format, this.overrideInBandCaptionDeclarations, this.muxedCaptionMediaFormats, this.playerId, this.mediaParser.getParserName()), this.outputConsumerAdapter, this.format, this.overrideInBandCaptionDeclarations, this.muxedCaptionMediaFormats, 0, this.playerId);
    }
}
