package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.msgpack.core.MessagePack;

public final class MpegAudioReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 4;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_FRAME = 2;
    private static final int STATE_READING_HEADER = 1;
    private String formatId;
    private int frameBytesRead;
    private long frameDurationUs;
    private int frameSize;
    private boolean hasOutputFormat;
    private final MpegAudioUtil.Header header;
    private final ParsableByteArray headerScratch;
    @Nullable
    private final String language;
    private boolean lastByteWasFF;
    private TrackOutput output;
    private int state;
    private long timeUs;

    public MpegAudioReader() {
        this((String) null);
    }

    private void findHeader(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int limit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < limit; position++) {
            byte b3 = data[position];
            boolean z2 = (b3 & 255) == 255;
            boolean z3 = this.lastByteWasFF && (b3 & MessagePack.Code.NEGFIXINT_PREFIX) == 224;
            this.lastByteWasFF = z2;
            if (z3) {
                parsableByteArray.setPosition(position + 1);
                this.lastByteWasFF = false;
                this.headerScratch.getData()[1] = data[position];
                this.frameBytesRead = 2;
                this.state = 1;
                return;
            }
        }
        parsableByteArray.setPosition(limit);
    }

    @RequiresNonNull({"output"})
    private void readFrameRemainder(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(parsableByteArray, min);
        int i3 = this.frameBytesRead + min;
        this.frameBytesRead = i3;
        int i4 = this.frameSize;
        if (i3 >= i4) {
            long j2 = this.timeUs;
            if (j2 != C.TIME_UNSET) {
                this.output.sampleMetadata(j2, 1, i4, 0, (TrackOutput.CryptoData) null);
                this.timeUs += this.frameDurationUs;
            }
            this.frameBytesRead = 0;
            this.state = 0;
        }
    }

    @RequiresNonNull({"output"})
    private void readHeaderRemainder(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.bytesLeft(), 4 - this.frameBytesRead);
        parsableByteArray.readBytes(this.headerScratch.getData(), this.frameBytesRead, min);
        int i3 = this.frameBytesRead + min;
        this.frameBytesRead = i3;
        if (i3 >= 4) {
            this.headerScratch.setPosition(0);
            if (!this.header.setForHeaderData(this.headerScratch.readInt())) {
                this.frameBytesRead = 0;
                this.state = 1;
                return;
            }
            MpegAudioUtil.Header header2 = this.header;
            this.frameSize = header2.frameSize;
            if (!this.hasOutputFormat) {
                this.frameDurationUs = (((long) header2.samplesPerFrame) * 1000000) / ((long) header2.sampleRate);
                this.output.format(new Format.Builder().setId(this.formatId).setSampleMimeType(this.header.mimeType).setMaxInputSize(4096).setChannelCount(this.header.channels).setSampleRate(this.header.sampleRate).setLanguage(this.language).build());
                this.hasOutputFormat = true;
            }
            this.headerScratch.setPosition(0);
            this.output.sampleData(this.headerScratch, 4);
            this.state = 2;
        }
    }

    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i3 = this.state;
            if (i3 == 0) {
                findHeader(parsableByteArray);
            } else if (i3 == 1) {
                readHeaderRemainder(parsableByteArray);
            } else if (i3 == 2) {
                readFrameRemainder(parsableByteArray);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    public void packetFinished() {
    }

    public void packetStarted(long j2, int i3) {
        if (j2 != C.TIME_UNSET) {
            this.timeUs = j2;
        }
    }

    public void seek() {
        this.state = 0;
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
        this.timeUs = C.TIME_UNSET;
    }

    public MpegAudioReader(@Nullable String str) {
        this.state = 0;
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        this.headerScratch = parsableByteArray;
        parsableByteArray.getData()[0] = -1;
        this.header = new MpegAudioUtil.Header();
        this.timeUs = C.TIME_UNSET;
        this.language = str;
    }
}
