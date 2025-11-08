package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;

public final class Id3Reader implements ElementaryStreamReader {
    private static final String TAG = "Id3Reader";
    private final ParsableByteArray id3Header = new ParsableByteArray(10);
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs = C.TIME_UNSET;
    private boolean writingSample;

    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample) {
            int bytesLeft = parsableByteArray.bytesLeft();
            int i3 = this.sampleBytesRead;
            if (i3 < 10) {
                int min = Math.min(bytesLeft, 10 - i3);
                System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), this.id3Header.getData(), this.sampleBytesRead, min);
                if (this.sampleBytesRead + min == 10) {
                    this.id3Header.setPosition(0);
                    if (73 == this.id3Header.readUnsignedByte() && 68 == this.id3Header.readUnsignedByte() && 51 == this.id3Header.readUnsignedByte()) {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
                    } else {
                        Log.w(TAG, "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    }
                }
            }
            int min2 = Math.min(bytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, min2);
            this.sampleBytesRead += min2;
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
        this.output = track;
        track.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
    }

    public void packetFinished() {
        int i3;
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample && (i3 = this.sampleSize) != 0 && this.sampleBytesRead == i3) {
            long j2 = this.sampleTimeUs;
            if (j2 != C.TIME_UNSET) {
                this.output.sampleMetadata(j2, 1, i3, 0, (TrackOutput.CryptoData) null);
            }
            this.writingSample = false;
        }
    }

    public void packetStarted(long j2, int i3) {
        if ((i3 & 4) != 0) {
            this.writingSample = true;
            if (j2 != C.TIME_UNSET) {
                this.sampleTimeUs = j2;
            }
            this.sampleSize = 0;
            this.sampleBytesRead = 0;
        }
    }

    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = C.TIME_UNSET;
    }
}
