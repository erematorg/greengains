package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class LatmReader implements ElementaryStreamReader {
    private static final int INITIAL_BUFFER_SIZE = 1024;
    private static final int STATE_FINDING_SYNC_1 = 0;
    private static final int STATE_FINDING_SYNC_2 = 1;
    private static final int STATE_READING_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 3;
    private static final int SYNC_BYTE_FIRST = 86;
    private static final int SYNC_BYTE_SECOND = 224;
    private int audioMuxVersionA;
    private int bytesRead;
    private int channelCount;
    @Nullable
    private String codecs;
    private Format format;
    private String formatId;
    private int frameLengthType;
    @Nullable
    private final String language;
    private int numSubframes;
    private long otherDataLenBits;
    private boolean otherDataPresent;
    private TrackOutput output;
    private final ParsableBitArray sampleBitArray;
    private final ParsableByteArray sampleDataBuffer;
    private long sampleDurationUs;
    private int sampleRateHz;
    private int sampleSize;
    private int secondHeaderByte;
    private int state;
    private boolean streamMuxRead;
    private long timeUs = C.TIME_UNSET;

    public LatmReader(@Nullable String str) {
        this.language = str;
        ParsableByteArray parsableByteArray = new ParsableByteArray(1024);
        this.sampleDataBuffer = parsableByteArray;
        this.sampleBitArray = new ParsableBitArray(parsableByteArray.getData());
    }

    private static long latmGetValue(ParsableBitArray parsableBitArray) {
        return (long) parsableBitArray.readBits((parsableBitArray.readBits(2) + 1) * 8);
    }

    @RequiresNonNull({"output"})
    private void parseAudioMuxElement(ParsableBitArray parsableBitArray) throws ParserException {
        if (!parsableBitArray.readBit()) {
            this.streamMuxRead = true;
            parseStreamMuxConfig(parsableBitArray);
        } else if (!this.streamMuxRead) {
            return;
        }
        if (this.audioMuxVersionA != 0) {
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        } else if (this.numSubframes == 0) {
            parsePayloadMux(parsableBitArray, parsePayloadLengthInfo(parsableBitArray));
            if (this.otherDataPresent) {
                parsableBitArray.skipBits((int) this.otherDataLenBits);
            }
        } else {
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        }
    }

    private int parseAudioSpecificConfig(ParsableBitArray parsableBitArray) throws ParserException {
        int bitsLeft = parsableBitArray.bitsLeft();
        AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(parsableBitArray, true);
        this.codecs = parseAudioSpecificConfig.codecs;
        this.sampleRateHz = parseAudioSpecificConfig.sampleRateHz;
        this.channelCount = parseAudioSpecificConfig.channelCount;
        return bitsLeft - parsableBitArray.bitsLeft();
    }

    private void parseFrameLength(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(3);
        this.frameLengthType = readBits;
        if (readBits == 0) {
            parsableBitArray.skipBits(8);
        } else if (readBits == 1) {
            parsableBitArray.skipBits(9);
        } else if (readBits == 3 || readBits == 4 || readBits == 5) {
            parsableBitArray.skipBits(6);
        } else if (readBits == 6 || readBits == 7) {
            parsableBitArray.skipBits(1);
        } else {
            throw new IllegalStateException();
        }
    }

    private int parsePayloadLengthInfo(ParsableBitArray parsableBitArray) throws ParserException {
        int readBits;
        if (this.frameLengthType == 0) {
            int i3 = 0;
            do {
                readBits = parsableBitArray.readBits(8);
                i3 += readBits;
            } while (readBits == 255);
            return i3;
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    @RequiresNonNull({"output"})
    private void parsePayloadMux(ParsableBitArray parsableBitArray, int i3) {
        int position = parsableBitArray.getPosition();
        if ((position & 7) == 0) {
            this.sampleDataBuffer.setPosition(position >> 3);
        } else {
            parsableBitArray.readBits(this.sampleDataBuffer.getData(), 0, i3 * 8);
            this.sampleDataBuffer.setPosition(0);
        }
        this.output.sampleData(this.sampleDataBuffer, i3);
        long j2 = this.timeUs;
        if (j2 != C.TIME_UNSET) {
            this.output.sampleMetadata(j2, 1, i3, 0, (TrackOutput.CryptoData) null);
            this.timeUs += this.sampleDurationUs;
        }
    }

    @RequiresNonNull({"output"})
    private void parseStreamMuxConfig(ParsableBitArray parsableBitArray) throws ParserException {
        boolean readBit;
        int readBits = parsableBitArray.readBits(1);
        int readBits2 = readBits == 1 ? parsableBitArray.readBits(1) : 0;
        this.audioMuxVersionA = readBits2;
        if (readBits2 == 0) {
            if (readBits == 1) {
                latmGetValue(parsableBitArray);
            }
            if (parsableBitArray.readBit()) {
                this.numSubframes = parsableBitArray.readBits(6);
                int readBits3 = parsableBitArray.readBits(4);
                int readBits4 = parsableBitArray.readBits(3);
                if (readBits3 == 0 && readBits4 == 0) {
                    if (readBits == 0) {
                        int position = parsableBitArray.getPosition();
                        int parseAudioSpecificConfig = parseAudioSpecificConfig(parsableBitArray);
                        parsableBitArray.setPosition(position);
                        byte[] bArr = new byte[((parseAudioSpecificConfig + 7) / 8)];
                        parsableBitArray.readBits(bArr, 0, parseAudioSpecificConfig);
                        Format build = new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.AUDIO_AAC).setCodecs(this.codecs).setChannelCount(this.channelCount).setSampleRate(this.sampleRateHz).setInitializationData(Collections.singletonList(bArr)).setLanguage(this.language).build();
                        if (!build.equals(this.format)) {
                            this.format = build;
                            this.sampleDurationUs = 1024000000 / ((long) build.sampleRate);
                            this.output.format(build);
                        }
                    } else {
                        parsableBitArray.skipBits(((int) latmGetValue(parsableBitArray)) - parseAudioSpecificConfig(parsableBitArray));
                    }
                    parseFrameLength(parsableBitArray);
                    boolean readBit2 = parsableBitArray.readBit();
                    this.otherDataPresent = readBit2;
                    this.otherDataLenBits = 0;
                    if (readBit2) {
                        if (readBits == 1) {
                            this.otherDataLenBits = latmGetValue(parsableBitArray);
                        } else {
                            do {
                                readBit = parsableBitArray.readBit();
                                this.otherDataLenBits = (this.otherDataLenBits << 8) + ((long) parsableBitArray.readBits(8));
                            } while (readBit);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(8);
                        return;
                    }
                    return;
                }
                throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
            }
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    private void resetBufferForSize(int i3) {
        this.sampleDataBuffer.reset(i3);
        this.sampleBitArray.reset(this.sampleDataBuffer.getData());
    }

    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i3 = this.state;
            if (i3 != 0) {
                if (i3 == 1) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    if ((readUnsignedByte & 224) == 224) {
                        this.secondHeaderByte = readUnsignedByte;
                        this.state = 2;
                    } else if (readUnsignedByte != 86) {
                        this.state = 0;
                    }
                } else if (i3 == 2) {
                    int readUnsignedByte2 = ((this.secondHeaderByte & -225) << 8) | parsableByteArray.readUnsignedByte();
                    this.sampleSize = readUnsignedByte2;
                    if (readUnsignedByte2 > this.sampleDataBuffer.getData().length) {
                        resetBufferForSize(this.sampleSize);
                    }
                    this.bytesRead = 0;
                    this.state = 3;
                } else if (i3 == 3) {
                    int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    parsableByteArray.readBytes(this.sampleBitArray.data, this.bytesRead, min);
                    int i4 = this.bytesRead + min;
                    this.bytesRead = i4;
                    if (i4 == this.sampleSize) {
                        this.sampleBitArray.setPosition(0);
                        parseAudioMuxElement(this.sampleBitArray);
                        this.state = 0;
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else if (parsableByteArray.readUnsignedByte() == 86) {
                this.state = 1;
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.formatId = trackIdGenerator.getFormatId();
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
        this.timeUs = C.TIME_UNSET;
        this.streamMuxRead = false;
    }
}
