package com.appsamurai.storyly.exoplayer2.extractor.extractor.wav;

import android.util.Pair;
import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.WavUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.google.common.base.Ascii;
import java.io.IOException;
import jnr.ffi.provider.jffi.JNINativeInterface;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(14);
    private static final int STATE_READING_FILE_TYPE = 0;
    private static final int STATE_READING_FORMAT = 2;
    private static final int STATE_READING_RF64_SAMPLE_DATA_SIZE = 1;
    private static final int STATE_READING_SAMPLE_DATA = 4;
    private static final int STATE_SKIPPING_TO_SAMPLE_DATA = 3;
    private static final String TAG = "WavExtractor";
    private static final int TARGET_SAMPLES_PER_SECOND = 10;
    private long dataEndPosition = -1;
    private int dataStartPosition = -1;
    private ExtractorOutput extractorOutput;
    private OutputWriter outputWriter;
    private long rf64SampleDataSize = -1;
    private int state = 0;
    private TrackOutput trackOutput;

    public static final class ImaAdPcmOutputWriter implements OutputWriter {
        private static final int[] INDEX_TABLE = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
        private static final int[] STEP_TABLE = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, 190, JNINativeInterface.SetCharArrayRegion, JNINativeInterface.GetDirectBufferAddress, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
        private final ParsableByteArray decodedData;
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private final int framesPerBlock;
        private final byte[] inputData;
        private long outputFrameCount;
        private int pendingInputBytes;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeFrames;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        public ImaAdPcmOutputWriter(ExtractorOutput extractorOutput2, TrackOutput trackOutput2, WavFormat wavFormat2) throws ParserException {
            this.extractorOutput = extractorOutput2;
            this.trackOutput = trackOutput2;
            this.wavFormat = wavFormat2;
            int max = Math.max(1, wavFormat2.frameRateHz / 10);
            this.targetSampleSizeFrames = max;
            ParsableByteArray parsableByteArray = new ParsableByteArray(wavFormat2.extraData);
            parsableByteArray.readLittleEndianUnsignedShort();
            int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
            this.framesPerBlock = readLittleEndianUnsignedShort;
            int i3 = wavFormat2.numChannels;
            int c3 = (com.appsamurai.storyly.exoplayer2.core.source.chunk.a.c(i3, 4, wavFormat2.blockSize, 8) / (wavFormat2.bitsPerSample * i3)) + 1;
            if (readLittleEndianUnsignedShort == c3) {
                int ceilDivide = Util.ceilDivide(max, readLittleEndianUnsignedShort);
                this.inputData = new byte[(wavFormat2.blockSize * ceilDivide)];
                this.decodedData = new ParsableByteArray(ceilDivide * numOutputFramesToBytes(readLittleEndianUnsignedShort, i3));
                int i4 = ((wavFormat2.frameRateHz * wavFormat2.blockSize) * 8) / readLittleEndianUnsignedShort;
                this.format = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setAverageBitrate(i4).setPeakBitrate(i4).setMaxInputSize(numOutputFramesToBytes(max, i3)).setChannelCount(wavFormat2.numChannels).setSampleRate(wavFormat2.frameRateHz).setPcmEncoding(2).build();
                return;
            }
            throw ParserException.createForMalformedContainer("Expected frames per block: " + c3 + "; got: " + readLittleEndianUnsignedShort, (Throwable) null);
        }

        private void decode(byte[] bArr, int i3, ParsableByteArray parsableByteArray) {
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 < this.wavFormat.numChannels; i5++) {
                    decodeBlockForChannel(bArr, i4, i5, parsableByteArray.getData());
                }
            }
            int numOutputFramesToBytes = numOutputFramesToBytes(this.framesPerBlock * i3);
            parsableByteArray.setPosition(0);
            parsableByteArray.setLimit(numOutputFramesToBytes);
        }

        private void decodeBlockForChannel(byte[] bArr, int i3, int i4, byte[] bArr2) {
            WavFormat wavFormat2 = this.wavFormat;
            int i5 = wavFormat2.blockSize;
            int i6 = wavFormat2.numChannels;
            int i7 = (i4 * 4) + (i3 * i5);
            int i8 = (i6 * 4) + i7;
            int i9 = (i5 / i6) - 4;
            int i10 = (short) (((bArr[i7 + 1] & 255) << 8) | (bArr[i7] & 255));
            int min = Math.min(bArr[i7 + 2] & 255, 88);
            int i11 = STEP_TABLE[min];
            int i12 = ((i3 * this.framesPerBlock * i6) + i4) * 2;
            bArr2[i12] = (byte) (i10 & 255);
            bArr2[i12 + 1] = (byte) (i10 >> 8);
            for (int i13 = 0; i13 < i9 * 2; i13++) {
                byte b3 = bArr[((i13 / 8) * i6 * 4) + i8 + ((i13 / 2) % 4)];
                int i14 = i13 % 2 == 0 ? b3 & Ascii.SI : (b3 & 255) >> 4;
                int A2 = b.A(i14 & 7, 2, 1, i11) >> 3;
                if ((i14 & 8) != 0) {
                    A2 = -A2;
                }
                i10 = Util.constrainValue(i10 + A2, -32768, 32767);
                i12 += i6 * 2;
                bArr2[i12] = (byte) (i10 & 255);
                bArr2[i12 + 1] = (byte) (i10 >> 8);
                int i15 = min + INDEX_TABLE[i14];
                int[] iArr = STEP_TABLE;
                min = Util.constrainValue(i15, 0, iArr.length - 1);
                i11 = iArr[min];
            }
        }

        private int numOutputBytesToFrames(int i3) {
            return i3 / (this.wavFormat.numChannels * 2);
        }

        private static int numOutputFramesToBytes(int i3, int i4) {
            return i3 * 2 * i4;
        }

        private void writeSampleMetadata(int i3) {
            long scaleLargeTimestamp = this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, (long) this.wavFormat.frameRateHz);
            int numOutputFramesToBytes = numOutputFramesToBytes(i3);
            this.trackOutput.sampleMetadata(scaleLargeTimestamp, 1, numOutputFramesToBytes, this.pendingOutputBytes - numOutputFramesToBytes, (TrackOutput.CryptoData) null);
            this.outputFrameCount += (long) i3;
            this.pendingOutputBytes -= numOutputFramesToBytes;
        }

        public void init(int i3, long j2) {
            this.extractorOutput.seekMap(new WavSeekMap(this.wavFormat, this.framesPerBlock, (long) i3, j2));
            this.trackOutput.format(this.format);
        }

        public void reset(long j2) {
            this.pendingInputBytes = 0;
            this.startTimeUs = j2;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x003e A[EDGE_INSN: B:22:0x003e->B:10:0x003e ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:5:0x0020  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean sampleData(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r7, long r8) throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r6.targetSampleSizeFrames
                int r1 = r6.pendingOutputBytes
                int r1 = r6.numOutputBytesToFrames(r1)
                int r0 = r0 - r1
                int r1 = r6.framesPerBlock
                int r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.ceilDivide((int) r0, (int) r1)
                com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavFormat r1 = r6.wavFormat
                int r1 = r1.blockSize
                int r0 = r0 * r1
                r1 = 0
                int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
                r2 = 1
                if (r1 != 0) goto L_0x001d
            L_0x001b:
                r1 = r2
                goto L_0x001e
            L_0x001d:
                r1 = 0
            L_0x001e:
                if (r1 != 0) goto L_0x003e
                int r3 = r6.pendingInputBytes
                if (r3 >= r0) goto L_0x003e
                int r3 = r0 - r3
                long r3 = (long) r3
                long r3 = java.lang.Math.min(r3, r8)
                int r3 = (int) r3
                byte[] r4 = r6.inputData
                int r5 = r6.pendingInputBytes
                int r3 = r7.read(r4, r5, r3)
                r4 = -1
                if (r3 != r4) goto L_0x0038
                goto L_0x001b
            L_0x0038:
                int r4 = r6.pendingInputBytes
                int r4 = r4 + r3
                r6.pendingInputBytes = r4
                goto L_0x001e
            L_0x003e:
                int r7 = r6.pendingInputBytes
                com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavFormat r8 = r6.wavFormat
                int r8 = r8.blockSize
                int r7 = r7 / r8
                if (r7 <= 0) goto L_0x0075
                byte[] r8 = r6.inputData
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r9 = r6.decodedData
                r6.decode(r8, r7, r9)
                int r8 = r6.pendingInputBytes
                com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavFormat r9 = r6.wavFormat
                int r9 = r9.blockSize
                int r7 = r7 * r9
                int r8 = r8 - r7
                r6.pendingInputBytes = r8
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r7 = r6.decodedData
                int r7 = r7.limit()
                com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput r8 = r6.trackOutput
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r9 = r6.decodedData
                r8.sampleData(r9, r7)
                int r8 = r6.pendingOutputBytes
                int r8 = r8 + r7
                r6.pendingOutputBytes = r8
                int r7 = r6.numOutputBytesToFrames(r8)
                int r8 = r6.targetSampleSizeFrames
                if (r7 < r8) goto L_0x0075
                r6.writeSampleMetadata(r8)
            L_0x0075:
                if (r1 == 0) goto L_0x0082
                int r7 = r6.pendingOutputBytes
                int r7 = r6.numOutputBytesToFrames(r7)
                if (r7 <= 0) goto L_0x0082
                r6.writeSampleMetadata(r7)
            L_0x0082:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavExtractor.ImaAdPcmOutputWriter.sampleData(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput, long):boolean");
        }

        private int numOutputFramesToBytes(int i3) {
            return numOutputFramesToBytes(i3, this.wavFormat.numChannels);
        }
    }

    public interface OutputWriter {
        void init(int i3, long j2) throws ParserException;

        void reset(long j2);

        boolean sampleData(ExtractorInput extractorInput, long j2) throws IOException;
    }

    public static final class PassthroughOutputWriter implements OutputWriter {
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private long outputFrameCount;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeBytes;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        public PassthroughOutputWriter(ExtractorOutput extractorOutput2, TrackOutput trackOutput2, WavFormat wavFormat2, String str, int i3) throws ParserException {
            this.extractorOutput = extractorOutput2;
            this.trackOutput = trackOutput2;
            this.wavFormat = wavFormat2;
            int i4 = (wavFormat2.numChannels * wavFormat2.bitsPerSample) / 8;
            if (wavFormat2.blockSize == i4) {
                int i5 = wavFormat2.frameRateHz;
                int i6 = i5 * i4 * 8;
                int max = Math.max(i4, (i5 * i4) / 10);
                this.targetSampleSizeBytes = max;
                this.format = new Format.Builder().setSampleMimeType(str).setAverageBitrate(i6).setPeakBitrate(i6).setMaxInputSize(max).setChannelCount(wavFormat2.numChannels).setSampleRate(wavFormat2.frameRateHz).setPcmEncoding(i3).build();
                return;
            }
            StringBuilder o3 = A.a.o(i4, "Expected block size: ", "; got: ");
            o3.append(wavFormat2.blockSize);
            throw ParserException.createForMalformedContainer(o3.toString(), (Throwable) null);
        }

        public void init(int i3, long j2) {
            this.extractorOutput.seekMap(new WavSeekMap(this.wavFormat, 1, (long) i3, j2));
            this.trackOutput.format(this.format);
        }

        public void reset(long j2) {
            this.startTimeUs = j2;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        public boolean sampleData(ExtractorInput extractorInput, long j2) throws IOException {
            int i3;
            int i4;
            int i5;
            long j3 = j2;
            while (true) {
                i3 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
                if (i3 <= 0 || (i4 = this.pendingOutputBytes) >= (i5 = this.targetSampleSizeBytes)) {
                    WavFormat wavFormat2 = this.wavFormat;
                    int i6 = wavFormat2.blockSize;
                    int i7 = this.pendingOutputBytes / i6;
                } else {
                    int sampleData = this.trackOutput.sampleData((DataReader) extractorInput, (int) Math.min((long) (i5 - i4), j3), true);
                    if (sampleData == -1) {
                        j3 = 0;
                    } else {
                        this.pendingOutputBytes += sampleData;
                        j3 -= (long) sampleData;
                    }
                }
            }
            WavFormat wavFormat22 = this.wavFormat;
            int i62 = wavFormat22.blockSize;
            int i72 = this.pendingOutputBytes / i62;
            if (i72 > 0) {
                int i8 = i72 * i62;
                int i9 = this.pendingOutputBytes - i8;
                this.trackOutput.sampleMetadata(this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, (long) wavFormat22.frameRateHz), 1, i8, i9, (TrackOutput.CryptoData) null);
                this.outputFrameCount += (long) i72;
                this.pendingOutputBytes = i9;
            }
            return i3 <= 0;
        }
    }

    @EnsuresNonNull({"extractorOutput", "trackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new WavExtractor()};
    }

    private void readFileType(ExtractorInput extractorInput) throws IOException {
        Assertions.checkState(extractorInput.getPosition() == 0);
        int i3 = this.dataStartPosition;
        if (i3 != -1) {
            extractorInput.skipFully(i3);
            this.state = 4;
        } else if (WavHeaderReader.checkFileType(extractorInput)) {
            extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
            this.state = 1;
        } else {
            throw ParserException.createForMalformedContainer("Unsupported or unrecognized wav file type.", (Throwable) null);
        }
    }

    @RequiresNonNull({"extractorOutput", "trackOutput"})
    private void readFormat(ExtractorInput extractorInput) throws IOException {
        WavFormat readFormat = WavHeaderReader.readFormat(extractorInput);
        int i3 = readFormat.formatType;
        if (i3 == 17) {
            this.outputWriter = new ImaAdPcmOutputWriter(this.extractorOutput, this.trackOutput, readFormat);
        } else if (i3 == 6) {
            this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, MimeTypes.AUDIO_ALAW, -1);
        } else if (i3 == 7) {
            this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, MimeTypes.AUDIO_MLAW, -1);
        } else {
            int pcmEncodingForType = WavUtil.getPcmEncodingForType(i3, readFormat.bitsPerSample);
            if (pcmEncodingForType != 0) {
                this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, MimeTypes.AUDIO_RAW, pcmEncodingForType);
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Unsupported WAV format type: " + readFormat.formatType);
            }
        }
        this.state = 3;
    }

    private void readRf64SampleDataSize(ExtractorInput extractorInput) throws IOException {
        this.rf64SampleDataSize = WavHeaderReader.readRf64SampleDataSize(extractorInput);
        this.state = 2;
    }

    private int readSampleData(ExtractorInput extractorInput) throws IOException {
        Assertions.checkState(this.dataEndPosition != -1);
        return ((OutputWriter) Assertions.checkNotNull(this.outputWriter)).sampleData(extractorInput, this.dataEndPosition - extractorInput.getPosition()) ? -1 : 0;
    }

    private void skipToSampleData(ExtractorInput extractorInput) throws IOException {
        Pair<Long, Long> skipToSampleData = WavHeaderReader.skipToSampleData(extractorInput);
        this.dataStartPosition = ((Long) skipToSampleData.first).intValue();
        long longValue = ((Long) skipToSampleData.second).longValue();
        long j2 = this.rf64SampleDataSize;
        if (j2 != -1 && longValue == 4294967295L) {
            longValue = j2;
        }
        this.dataEndPosition = ((long) this.dataStartPosition) + longValue;
        long length = extractorInput.getLength();
        if (length != -1 && this.dataEndPosition > length) {
            Log.w(TAG, "Data exceeds input length: " + this.dataEndPosition + ", " + length);
            this.dataEndPosition = length;
        }
        ((OutputWriter) Assertions.checkNotNull(this.outputWriter)).init(this.dataStartPosition, this.dataEndPosition);
        this.state = 4;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int i3 = this.state;
        if (i3 == 0) {
            readFileType(extractorInput);
            return 0;
        } else if (i3 == 1) {
            readRf64SampleDataSize(extractorInput);
            return 0;
        } else if (i3 == 2) {
            readFormat(extractorInput);
            return 0;
        } else if (i3 == 3) {
            skipToSampleData(extractorInput);
            return 0;
        } else if (i3 == 4) {
            return readSampleData(extractorInput);
        } else {
            throw new IllegalStateException();
        }
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        this.state = j2 == 0 ? 0 : 4;
        OutputWriter outputWriter2 = this.outputWriter;
        if (outputWriter2 != null) {
            outputWriter2.reset(j3);
        }
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return WavHeaderReader.checkFileType(extractorInput);
    }
}
