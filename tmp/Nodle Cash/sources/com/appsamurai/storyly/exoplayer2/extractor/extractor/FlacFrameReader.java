package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.IOException;

public final class FlacFrameReader {

    public static final class SampleNumberHolder {
        public long sampleNumber;
    }

    private FlacFrameReader() {
    }

    private static boolean checkAndReadBlockSizeSamples(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i3) {
        int readFrameBlockSizeSamplesFromKey = readFrameBlockSizeSamplesFromKey(parsableByteArray, i3);
        return readFrameBlockSizeSamplesFromKey != -1 && readFrameBlockSizeSamplesFromKey <= flacStreamMetadata.maxBlockSizeSamples;
    }

    private static boolean checkAndReadCrc(ParsableByteArray parsableByteArray, int i3) {
        return parsableByteArray.readUnsignedByte() == Util.crc8(parsableByteArray.getData(), i3, parsableByteArray.getPosition() - 1, 0);
    }

    private static boolean checkAndReadFirstSampleNumber(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, boolean z2, SampleNumberHolder sampleNumberHolder) {
        try {
            long readUtf8EncodedLong = parsableByteArray.readUtf8EncodedLong();
            if (!z2) {
                readUtf8EncodedLong *= (long) flacStreamMetadata.maxBlockSizeSamples;
            }
            sampleNumberHolder.sampleNumber = readUtf8EncodedLong;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean checkAndReadFrameHeader(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i3, SampleNumberHolder sampleNumberHolder) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        FlacStreamMetadata flacStreamMetadata2 = flacStreamMetadata;
        int position = parsableByteArray.getPosition();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long j2 = readUnsignedInt >>> 16;
        if (j2 != ((long) i3)) {
            return false;
        }
        return checkChannelAssignment((int) ((readUnsignedInt >> 4) & 15), flacStreamMetadata2) && checkBitsPerSample((int) ((readUnsignedInt >> 1) & 7), flacStreamMetadata2) && !(((readUnsignedInt & 1) > 1 ? 1 : ((readUnsignedInt & 1) == 1 ? 0 : -1)) == 0) && checkAndReadFirstSampleNumber(parsableByteArray2, flacStreamMetadata2, ((j2 & 1) > 1 ? 1 : ((j2 & 1) == 1 ? 0 : -1)) == 0, sampleNumberHolder) && checkAndReadBlockSizeSamples(parsableByteArray2, flacStreamMetadata2, (int) ((readUnsignedInt >> 12) & 15)) && checkAndReadSampleRate(parsableByteArray2, flacStreamMetadata2, (int) ((readUnsignedInt >> 8) & 15)) && checkAndReadCrc(parsableByteArray2, position);
    }

    private static boolean checkAndReadSampleRate(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i3) {
        int i4 = flacStreamMetadata.sampleRate;
        if (i3 == 0) {
            return true;
        }
        if (i3 <= 11) {
            return i3 == flacStreamMetadata.sampleRateLookupKey;
        }
        if (i3 == 12) {
            return parsableByteArray.readUnsignedByte() * 1000 == i4;
        }
        if (i3 > 14) {
            return false;
        }
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        if (i3 == 14) {
            readUnsignedShort *= 10;
        }
        return readUnsignedShort == i4;
    }

    private static boolean checkBitsPerSample(int i3, FlacStreamMetadata flacStreamMetadata) {
        return i3 == 0 || i3 == flacStreamMetadata.bitsPerSampleLookupKey;
    }

    private static boolean checkChannelAssignment(int i3, FlacStreamMetadata flacStreamMetadata) {
        return i3 <= 7 ? i3 == flacStreamMetadata.channels - 1 : i3 <= 10 && flacStreamMetadata.channels == 2;
    }

    public static boolean checkFrameHeaderFromPeek(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata, int i3, SampleNumberHolder sampleNumberHolder) throws IOException {
        long peekPosition = extractorInput.getPeekPosition();
        byte[] bArr = new byte[2];
        extractorInput.peekFully(bArr, 0, 2);
        if ((((bArr[0] & 255) << 8) | (bArr[1] & 255)) != i3) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
            return false;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        System.arraycopy(bArr, 0, parsableByteArray.getData(), 0, 2);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 2, 14));
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
        return checkAndReadFrameHeader(parsableByteArray, flacStreamMetadata, i3, sampleNumberHolder);
    }

    public static long getFirstSampleNumber(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata) throws IOException {
        extractorInput.resetPeekPosition();
        boolean z2 = true;
        extractorInput.advancePeekPosition(1);
        byte[] bArr = new byte[1];
        extractorInput.peekFully(bArr, 0, 1);
        if ((bArr[0] & 1) != 1) {
            z2 = false;
        }
        extractorInput.advancePeekPosition(2);
        int i3 = z2 ? 7 : 6;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i3);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 0, i3));
        extractorInput.resetPeekPosition();
        SampleNumberHolder sampleNumberHolder = new SampleNumberHolder();
        if (checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, z2, sampleNumberHolder)) {
            return sampleNumberHolder.sampleNumber;
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    public static int readFrameBlockSizeSamplesFromKey(ParsableByteArray parsableByteArray, int i3) {
        switch (i3) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i3 - 2);
            case 6:
                return parsableByteArray.readUnsignedByte() + 1;
            case 7:
                return parsableByteArray.readUnsignedShort() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i3 - 8);
            default:
                return -1;
        }
    }
}
