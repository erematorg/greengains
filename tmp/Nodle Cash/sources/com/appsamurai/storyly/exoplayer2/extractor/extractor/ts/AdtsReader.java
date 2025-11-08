package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class AdtsReader implements ElementaryStreamReader {
    private static final int CRC_SIZE = 2;
    private static final int HEADER_SIZE = 5;
    private static final int ID3_HEADER_SIZE = 10;
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private static final int ID3_SIZE_OFFSET = 6;
    private static final int MATCH_STATE_FF = 512;
    private static final int MATCH_STATE_I = 768;
    private static final int MATCH_STATE_ID = 1024;
    private static final int MATCH_STATE_START = 256;
    private static final int MATCH_STATE_VALUE_SHIFT = 8;
    private static final int STATE_CHECKING_ADTS_HEADER = 1;
    private static final int STATE_FINDING_SAMPLE = 0;
    private static final int STATE_READING_ADTS_HEADER = 3;
    private static final int STATE_READING_ID3_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 4;
    private static final String TAG = "AdtsReader";
    private static final int VERSION_UNSET = -1;
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private int currentFrameVersion;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private int firstFrameSampleRateIndex;
    private int firstFrameVersion;
    private String formatId;
    private boolean foundFirstFrame;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    @Nullable
    private final String language;
    private int matchState;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public AdtsReader(boolean z2) {
        this(z2, (String) null);
    }

    @EnsuresNonNull({"output", "currentOutput", "id3Output"})
    private void assertTracksCreated() {
        Assertions.checkNotNull(this.output);
        Util.castNonNull(this.currentOutput);
        Util.castNonNull(this.id3Output);
    }

    private void checkAdtsHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() != 0) {
            this.adtsScratch.data[0] = parsableByteArray.getData()[parsableByteArray.getPosition()];
            this.adtsScratch.setPosition(2);
            int readBits = this.adtsScratch.readBits(4);
            int i3 = this.firstFrameSampleRateIndex;
            if (i3 == -1 || readBits == i3) {
                if (!this.foundFirstFrame) {
                    this.foundFirstFrame = true;
                    this.firstFrameVersion = this.currentFrameVersion;
                    this.firstFrameSampleRateIndex = readBits;
                }
                setReadingAdtsHeaderState();
                return;
            }
            resetSync();
        }
    }

    private boolean checkSyncPositionValid(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.setPosition(i3 + 1);
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
            return false;
        }
        this.adtsScratch.setPosition(4);
        int readBits = this.adtsScratch.readBits(1);
        int i4 = this.firstFrameVersion;
        if (i4 != -1 && readBits != i4) {
            return false;
        }
        if (this.firstFrameSampleRateIndex != -1) {
            if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
                return true;
            }
            this.adtsScratch.setPosition(2);
            if (this.adtsScratch.readBits(4) != this.firstFrameSampleRateIndex) {
                return false;
            }
            parsableByteArray.setPosition(i3 + 2);
        }
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 4)) {
            return true;
        }
        this.adtsScratch.setPosition(14);
        int readBits2 = this.adtsScratch.readBits(13);
        if (readBits2 < 7) {
            return false;
        }
        byte[] data = parsableByteArray.getData();
        int limit = parsableByteArray.limit();
        int i5 = i3 + readBits2;
        if (i5 >= limit) {
            return true;
        }
        byte b3 = data[i5];
        if (b3 == -1) {
            int i6 = i5 + 1;
            if (i6 == limit) {
                return true;
            }
            return isAdtsSyncBytes((byte) -1, data[i6]) && ((data[i6] & 8) >> 3) == readBits;
        } else if (b3 != 73) {
            return false;
        } else {
            int i7 = i5 + 1;
            if (i7 == limit) {
                return true;
            }
            if (data[i7] != 68) {
                return false;
            }
            int i8 = i5 + 2;
            return i8 == limit || data[i8] == 51;
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i3) {
        int min = Math.min(parsableByteArray.bytesLeft(), i3 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i4 = this.bytesRead + min;
        this.bytesRead = i4;
        return i4 == i3;
    }

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int i3 = position + 1;
            byte b3 = data[position];
            byte b4 = b3 & 255;
            if (this.matchState != 512 || !isAdtsSyncBytes((byte) -1, (byte) b4) || (!this.foundFirstFrame && !checkSyncPositionValid(parsableByteArray, position - 1))) {
                int i4 = this.matchState;
                byte b5 = b4 | i4;
                if (b5 == 329) {
                    this.matchState = 768;
                } else if (b5 == 511) {
                    this.matchState = 512;
                } else if (b5 == 836) {
                    this.matchState = 1024;
                } else if (b5 == 1075) {
                    setReadingId3HeaderState();
                    parsableByteArray.setPosition(i3);
                    return;
                } else if (i4 != 256) {
                    this.matchState = 256;
                }
                position = i3;
            } else {
                this.currentFrameVersion = (b3 & 8) >> 3;
                boolean z2 = true;
                if ((b3 & 1) != 0) {
                    z2 = false;
                }
                this.hasCrc = z2;
                if (!this.foundFirstFrame) {
                    setCheckingAdtsHeaderState();
                } else {
                    setReadingAdtsHeaderState();
                }
                parsableByteArray.setPosition(i3);
                return;
            }
        }
        parsableByteArray.setPosition(position);
    }

    private boolean isAdtsSyncBytes(byte b3, byte b4) {
        return isAdtsSyncWord(((b3 & 255) << 8) | (b4 & 255));
    }

    public static boolean isAdtsSyncWord(int i3) {
        return (i3 & 65526) == 65520;
    }

    @RequiresNonNull({"output"})
    private void parseAdtsHeader() throws ParserException {
        this.adtsScratch.setPosition(0);
        if (!this.hasOutputFormat) {
            int i3 = 2;
            int readBits = this.adtsScratch.readBits(2) + 1;
            if (readBits != 2) {
                Log.w(TAG, "Detected audio object type: " + readBits + ", but assuming AAC LC.");
            } else {
                i3 = readBits;
            }
            this.adtsScratch.skipBits(5);
            byte[] buildAudioSpecificConfig = AacUtil.buildAudioSpecificConfig(i3, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
            AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(buildAudioSpecificConfig);
            Format build = new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.AUDIO_AAC).setCodecs(parseAudioSpecificConfig.codecs).setChannelCount(parseAudioSpecificConfig.channelCount).setSampleRate(parseAudioSpecificConfig.sampleRateHz).setInitializationData(Collections.singletonList(buildAudioSpecificConfig)).setLanguage(this.language).build();
            this.sampleDurationUs = 1024000000 / ((long) build.sampleRate);
            this.output.format(build);
            this.hasOutputFormat = true;
        } else {
            this.adtsScratch.skipBits(10);
        }
        this.adtsScratch.skipBits(4);
        int readBits2 = this.adtsScratch.readBits(13);
        int i4 = readBits2 - 7;
        if (this.hasCrc) {
            i4 = readBits2 - 9;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, i4);
    }

    @RequiresNonNull({"id3Output"})
    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    @RequiresNonNull({"currentOutput"})
    private void readSample(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, min);
        int i3 = this.bytesRead + min;
        this.bytesRead = i3;
        int i4 = this.sampleSize;
        if (i3 == i4) {
            long j2 = this.timeUs;
            if (j2 != C.TIME_UNSET) {
                this.currentOutput.sampleMetadata(j2, 1, i4, 0, (TrackOutput.CryptoData) null);
                this.timeUs += this.currentSampleDuration;
            }
            setFindingSampleState();
        }
    }

    private void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    private void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j2, int i3, int i4) {
        this.state = 4;
        this.bytesRead = i3;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j2;
        this.sampleSize = i4;
    }

    private boolean tryRead(ParsableByteArray parsableByteArray, byte[] bArr, int i3) {
        if (parsableByteArray.bytesLeft() < i3) {
            return false;
        }
        parsableByteArray.readBytes(bArr, 0, i3);
        return true;
    }

    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int i3 = this.state;
            if (i3 == 0) {
                findNextSample(parsableByteArray);
            } else if (i3 == 1) {
                checkAdtsHeader(parsableByteArray);
            } else if (i3 != 2) {
                if (i3 == 3) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else if (i3 == 4) {
                    readSample(parsableByteArray);
                } else {
                    throw new IllegalStateException();
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.getData(), 10)) {
                parseId3Header();
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.output = track;
        this.currentOutput = track;
        if (this.exposeId3) {
            trackIdGenerator.generateNewId();
            TrackOutput track2 = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
            this.id3Output = track2;
            track2.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            return;
        }
        this.id3Output = new DummyTrackOutput();
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    public void packetFinished() {
    }

    public void packetStarted(long j2, int i3) {
        if (j2 != C.TIME_UNSET) {
            this.timeUs = j2;
        }
    }

    public void seek() {
        this.timeUs = C.TIME_UNSET;
        resetSync();
    }

    public AdtsReader(boolean z2, @Nullable String str) {
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = C.TIME_UNSET;
        this.timeUs = C.TIME_UNSET;
        this.exposeId3 = z2;
        this.language = str;
    }
}
