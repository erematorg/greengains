package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor;
import com.appsamurai.storyly.exoplayer2.core.source.f;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.InputReaderAdapterV30;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.MediaParserUtil;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.OutputConsumerAdapterV30;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(30)
public final class MediaParserChunkExtractor implements ChunkExtractor {
    public static final ChunkExtractor.Factory FACTORY = new a(1);
    private static final String TAG = "MediaPrsrChunkExtractor";
    /* access modifiers changed from: private */
    public final DummyTrackOutput dummyTrackOutput;
    private final InputReaderAdapterV30 inputReaderAdapter = new InputReaderAdapterV30();
    private final MediaParser mediaParser;
    /* access modifiers changed from: private */
    public final OutputConsumerAdapterV30 outputConsumerAdapter;
    private long pendingSeekUs;
    /* access modifiers changed from: private */
    @Nullable
    public Format[] sampleFormats;
    /* access modifiers changed from: private */
    @Nullable
    public ChunkExtractor.TrackOutputProvider trackOutputProvider;
    private final TrackOutputProviderAdapter trackOutputProviderAdapter;

    public class TrackOutputProviderAdapter implements ExtractorOutput {
        private TrackOutputProviderAdapter() {
        }

        public void endTracks() {
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            Format[] unused = mediaParserChunkExtractor.sampleFormats = mediaParserChunkExtractor.outputConsumerAdapter.getSampleFormats();
        }

        public void seekMap(SeekMap seekMap) {
        }

        public TrackOutput track(int i3, int i4) {
            ChunkExtractor.TrackOutputProvider access$100 = MediaParserChunkExtractor.this.trackOutputProvider;
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            return access$100 != null ? mediaParserChunkExtractor.trackOutputProvider.track(i3, i4) : mediaParserChunkExtractor.dummyTrackOutput;
        }
    }

    @SuppressLint({"WrongConstant"})
    public MediaParserChunkExtractor(int i3, Format format, List<Format> list, PlayerId playerId) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30(format, i3, true);
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        String str = MimeTypes.isMatroska((String) Assertions.checkNotNull(format.containerMimeType)) ? "android.media.mediaparser.MatroskaParser" : "android.media.mediaparser.FragmentedMp4Parser";
        outputConsumerAdapterV30.setSelectedParserName(str);
        MediaParser r2 = MediaParser.createByName(str, outputConsumerAdapterV30);
        this.mediaParser = r2;
        r2.setParameter("android.media.mediaparser.matroska.disableCuesSeeking", Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_INCLUDE_SUPPLEMENTAL_DATA, Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_EXPOSE_DUMMY_SEEK_MAP, Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_EXPOSE_CHUNK_INDEX_AS_MEDIA_FORMAT, Boolean.TRUE);
        r2.setParameter(MediaParserUtil.PARAMETER_OVERRIDE_IN_BAND_CAPTION_DECLARATIONS, Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < list.size(); i4++) {
            arrayList.add(MediaParserUtil.toCaptionsMediaFormat(list.get(i4)));
        }
        this.mediaParser.setParameter(MediaParserUtil.PARAMETER_EXPOSE_CAPTION_FORMATS, arrayList);
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(this.mediaParser, playerId);
        }
        this.outputConsumerAdapter.setMuxedCaptionFormats(list);
        this.trackOutputProviderAdapter = new TrackOutputProviderAdapter();
        this.dummyTrackOutput = new DummyTrackOutput();
        this.pendingSeekUs = C.TIME_UNSET;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ChunkExtractor lambda$static$0(int i3, Format format, boolean z2, List list, TrackOutput trackOutput, PlayerId playerId) {
        if (!MimeTypes.isText(format.containerMimeType)) {
            return new MediaParserChunkExtractor(i3, format, list, playerId);
        }
        Log.w(TAG, "Ignoring an unsupported text track.");
        return null;
    }

    private void maybeExecutePendingSeek() {
        MediaParser.SeekMap dummySeekMap = this.outputConsumerAdapter.getDummySeekMap();
        long j2 = this.pendingSeekUs;
        if (j2 != C.TIME_UNSET && dummySeekMap != null) {
            this.mediaParser.seek(f.b(dummySeekMap.getSeekPoints(j2).first));
            this.pendingSeekUs = C.TIME_UNSET;
        }
    }

    @Nullable
    public ChunkIndex getChunkIndex() {
        return this.outputConsumerAdapter.getChunkIndex();
    }

    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public void init(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider2, long j2, long j3) {
        this.trackOutputProvider = trackOutputProvider2;
        this.outputConsumerAdapter.setSampleTimestampUpperLimitFilterUs(j3);
        this.outputConsumerAdapter.setExtractorOutput(this.trackOutputProviderAdapter);
        this.pendingSeekUs = j2;
    }

    public boolean read(ExtractorInput extractorInput) throws IOException {
        maybeExecutePendingSeek();
        this.inputReaderAdapter.setDataReader(extractorInput, extractorInput.getLength());
        return this.mediaParser.advance(this.inputReaderAdapter);
    }

    public void release() {
        this.mediaParser.release();
    }
}
