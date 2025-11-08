package com.appsamurai.storyly.exoplayer2.core.source;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import android.net.Uri;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaExtractor;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.InputReaderAdapterV30;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.MediaParserUtil;
import com.appsamurai.storyly.exoplayer2.core.source.mediaparser.OutputConsumerAdapterV30;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiresApi(30)
public final class MediaParserExtractorAdapter implements ProgressiveMediaExtractor {
    public static final ProgressiveMediaExtractor.Factory FACTORY = new l(2);
    private final InputReaderAdapterV30 inputReaderAdapter = new InputReaderAdapterV30();
    private final MediaParser mediaParser;
    private final OutputConsumerAdapterV30 outputConsumerAdapter;
    private String parserName;

    @SuppressLint({"WrongConstant"})
    public MediaParserExtractorAdapter(PlayerId playerId) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30();
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        MediaParser c3 = MediaParser.create(outputConsumerAdapterV30, new String[0]);
        this.mediaParser = c3;
        c3.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, Boolean.TRUE);
        c3.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, Boolean.TRUE);
        c3.setParameter(MediaParserUtil.PARAMETER_INCLUDE_SUPPLEMENTAL_DATA, Boolean.TRUE);
        this.parserName = "android.media.mediaparser.UNKNOWN";
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(c3, playerId);
        }
    }

    public void disableSeekingOnMp3Streams() {
        if ("android.media.mediaparser.Mp3Parser".equals(this.parserName)) {
            this.outputConsumerAdapter.disableSeeking();
        }
    }

    public long getCurrentInputPosition() {
        return this.inputReaderAdapter.getPosition();
    }

    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> map, long j2, long j3, ExtractorOutput extractorOutput) throws IOException {
        this.outputConsumerAdapter.setExtractorOutput(extractorOutput);
        this.inputReaderAdapter.setDataReader(dataReader, j3);
        this.inputReaderAdapter.setCurrentPosition(j2);
        String d2 = this.mediaParser.getParserName();
        if ("android.media.mediaparser.UNKNOWN".equals(d2)) {
            this.mediaParser.advance(this.inputReaderAdapter);
            String d3 = this.mediaParser.getParserName();
            this.parserName = d3;
            this.outputConsumerAdapter.setSelectedParserName(d3);
        } else if (!d2.equals(this.parserName)) {
            String d4 = this.mediaParser.getParserName();
            this.parserName = d4;
            this.outputConsumerAdapter.setSelectedParserName(d4);
        }
    }

    public int read(PositionHolder positionHolder) throws IOException {
        boolean h3 = this.mediaParser.advance(this.inputReaderAdapter);
        long andResetSeekPosition = this.inputReaderAdapter.getAndResetSeekPosition();
        positionHolder.position = andResetSeekPosition;
        if (!h3) {
            return -1;
        }
        return andResetSeekPosition != -1 ? 1 : 0;
    }

    public void release() {
        this.mediaParser.release();
    }

    public void seek(long j2, long j3) {
        this.inputReaderAdapter.setCurrentPosition(j2);
        Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.outputConsumerAdapter.getSeekPoints(j3);
        this.mediaParser.seek(f.b(f.b(seekPoints.second).position == j2 ? seekPoints.second : seekPoints.first));
    }
}
