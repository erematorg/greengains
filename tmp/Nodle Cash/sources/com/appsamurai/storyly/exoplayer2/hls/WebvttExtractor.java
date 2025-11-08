package com.appsamurai.storyly.exoplayer2.hls;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.text.webvtt.WebvttParserUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class WebvttExtractor implements Extractor {
    private static final int HEADER_MAX_LENGTH = 9;
    private static final int HEADER_MIN_LENGTH = 6;
    private static final Pattern LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(-?\\d+)");
    @Nullable
    private final String language;
    private ExtractorOutput output;
    private byte[] sampleData = new byte[1024];
    private final ParsableByteArray sampleDataWrapper = new ParsableByteArray();
    private int sampleSize;
    private final TimestampAdjuster timestampAdjuster;

    public WebvttExtractor(@Nullable String str, TimestampAdjuster timestampAdjuster2) {
        this.language = str;
        this.timestampAdjuster = timestampAdjuster2;
    }

    @RequiresNonNull({"output"})
    private TrackOutput buildTrackOutput(long j2) {
        TrackOutput track = this.output.track(0, 3);
        track.format(new Format.Builder().setSampleMimeType(MimeTypes.TEXT_VTT).setLanguage(this.language).setSubsampleOffsetUs(j2).build());
        this.output.endTracks();
        return track;
    }

    @RequiresNonNull({"output"})
    private void processSample() throws ParserException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.sampleData);
        WebvttParserUtil.validateWebvttHeaderLine(parsableByteArray);
        long j2 = 0;
        long j3 = 0;
        for (String readLine = parsableByteArray.readLine(); !TextUtils.isEmpty(readLine); readLine = parsableByteArray.readLine()) {
            if (readLine.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = LOCAL_TIMESTAMP.matcher(readLine);
                if (matcher.find()) {
                    Matcher matcher2 = MEDIA_TIMESTAMP.matcher(readLine);
                    if (matcher2.find()) {
                        j3 = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(matcher.group(1)));
                        j2 = TimestampAdjuster.ptsToUs(Long.parseLong((String) Assertions.checkNotNull(matcher2.group(1))));
                    } else {
                        throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain media timestamp: ".concat(readLine), (Throwable) null);
                    }
                } else {
                    throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain local timestamp: ".concat(readLine), (Throwable) null);
                }
            }
        }
        Matcher findNextCueHeader = WebvttParserUtil.findNextCueHeader(parsableByteArray);
        if (findNextCueHeader == null) {
            buildTrackOutput(0);
            return;
        }
        long parseTimestampUs = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(findNextCueHeader.group(1)));
        long adjustTsTimestamp = this.timestampAdjuster.adjustTsTimestamp(TimestampAdjuster.usToWrappedPts((j2 + parseTimestampUs) - j3));
        TrackOutput buildTrackOutput = buildTrackOutput(adjustTsTimestamp - parseTimestampUs);
        this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
        buildTrackOutput.sampleData(this.sampleDataWrapper, this.sampleSize);
        buildTrackOutput.sampleMetadata(adjustTsTimestamp, 1, this.sampleSize, 0, (TrackOutput.CryptoData) null);
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkNotNull(this.output);
        int length = (int) extractorInput.getLength();
        int i3 = this.sampleSize;
        byte[] bArr = this.sampleData;
        if (i3 == bArr.length) {
            this.sampleData = Arrays.copyOf(bArr, ((length != -1 ? length : bArr.length) * 3) / 2);
        }
        byte[] bArr2 = this.sampleData;
        int i4 = this.sampleSize;
        int read = extractorInput.read(bArr2, i4, bArr2.length - i4);
        if (read != -1) {
            int i5 = this.sampleSize + read;
            this.sampleSize = i5;
            if (length == -1 || i5 != length) {
                return 0;
            }
        }
        processSample();
        return -1;
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        throw new IllegalStateException();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.sampleData, 0, 6, false);
        this.sampleDataWrapper.reset(this.sampleData, 6);
        if (WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper)) {
            return true;
        }
        extractorInput.peekFully(this.sampleData, 6, 3, false);
        this.sampleDataWrapper.reset(this.sampleData, 9);
        return WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper);
    }
}
