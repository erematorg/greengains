package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.h;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.flac.PictureFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.vorbis.VorbisComment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class VorbisUtil {
    private static final String TAG = "VorbisUtil";

    public static final class CodeBook {
        public final int dimensions;
        public final int entries;
        public final boolean isOrdered;
        public final long[] lengthMap;
        public final int lookupType;

        public CodeBook(int i3, int i4, long[] jArr, int i5, boolean z2) {
            this.dimensions = i3;
            this.entries = i4;
            this.lengthMap = jArr;
            this.lookupType = i5;
            this.isOrdered = z2;
        }
    }

    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String str, String[] strArr, int i3) {
            this.vendor = str;
            this.comments = strArr;
            this.length = i3;
        }
    }

    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean z2, int i3, int i4, int i5) {
            this.blockFlag = z2;
            this.windowType = i3;
            this.transformType = i4;
            this.mapping = i5;
        }
    }

    public static final class VorbisIdHeader {
        public final int bitrateMaximum;
        public final int bitrateMinimum;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final int sampleRate;
        public final int version;

        public VorbisIdHeader(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z2, byte[] bArr) {
            this.version = i3;
            this.channels = i4;
            this.sampleRate = i5;
            this.bitrateMaximum = i6;
            this.bitrateNominal = i7;
            this.bitrateMinimum = i8;
            this.blockSize0 = i9;
            this.blockSize1 = i10;
            this.framingFlag = z2;
            this.data = bArr;
        }
    }

    private VorbisUtil() {
    }

    public static int iLog(int i3) {
        int i4 = 0;
        while (i3 > 0) {
            i4++;
            i3 >>>= 1;
        }
        return i4;
    }

    private static long mapType1QuantValues(long j2, long j3) {
        return (long) Math.floor(Math.pow((double) j2, 1.0d / ((double) j3)));
    }

    @Nullable
    public static Metadata parseVorbisComments(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            String str = list.get(i3);
            String[] splitAtFirst = Util.splitAtFirst(str, StickyVariantProvider.KEY_VALUE_DELIMITER);
            if (splitAtFirst.length != 2) {
                h.a("Failed to parse Vorbis comment: ", str, TAG);
            } else if (splitAtFirst[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(PictureFrame.fromPictureBlock(new ParsableByteArray(Base64.decode(splitAtFirst[1], 0))));
                } catch (RuntimeException e3) {
                    Log.w(TAG, "Failed to parse vorbis picture", e3);
                }
            } else {
                arrayList.add(new VorbisComment(splitAtFirst[0], splitAtFirst[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static CodeBook readBook(VorbisBitArray vorbisBitArray) throws ParserException {
        if (vorbisBitArray.readBits(24) == 5653314) {
            int readBits = vorbisBitArray.readBits(16);
            int readBits2 = vorbisBitArray.readBits(24);
            long[] jArr = new long[readBits2];
            boolean readBit = vorbisBitArray.readBit();
            long j2 = 0;
            if (!readBit) {
                boolean readBit2 = vorbisBitArray.readBit();
                for (int i3 = 0; i3 < readBits2; i3++) {
                    if (!readBit2) {
                        jArr[i3] = (long) (vorbisBitArray.readBits(5) + 1);
                    } else if (vorbisBitArray.readBit()) {
                        jArr[i3] = (long) (vorbisBitArray.readBits(5) + 1);
                    } else {
                        jArr[i3] = 0;
                    }
                }
            } else {
                int readBits3 = vorbisBitArray.readBits(5) + 1;
                int i4 = 0;
                while (i4 < readBits2) {
                    int readBits4 = vorbisBitArray.readBits(iLog(readBits2 - i4));
                    for (int i5 = 0; i5 < readBits4 && i4 < readBits2; i5++) {
                        jArr[i4] = (long) readBits3;
                        i4++;
                    }
                    readBits3++;
                }
            }
            int readBits5 = vorbisBitArray.readBits(4);
            if (readBits5 <= 2) {
                if (readBits5 == 1 || readBits5 == 2) {
                    vorbisBitArray.skipBits(32);
                    vorbisBitArray.skipBits(32);
                    int readBits6 = vorbisBitArray.readBits(4) + 1;
                    vorbisBitArray.skipBits(1);
                    if (readBits5 != 1) {
                        j2 = ((long) readBits2) * ((long) readBits);
                    } else if (readBits != 0) {
                        j2 = mapType1QuantValues((long) readBits2, (long) readBits);
                    }
                    vorbisBitArray.skipBits((int) (j2 * ((long) readBits6)));
                }
                return new CodeBook(readBits, readBits2, jArr, readBits5, readBit);
            }
            throw ParserException.createForMalformedContainer("lookup type greater than 2 not decodable: " + readBits5, (Throwable) null);
        }
        throw ParserException.createForMalformedContainer("expected code book to start with [0x56, 0x43, 0x42] at " + vorbisBitArray.getPosition(), (Throwable) null);
    }

    private static void readFloors(VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int i3 = 0; i3 < readBits; i3++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 == 0) {
                vorbisBitArray.skipBits(8);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(6);
                vorbisBitArray.skipBits(8);
                int readBits3 = vorbisBitArray.readBits(4) + 1;
                for (int i4 = 0; i4 < readBits3; i4++) {
                    vorbisBitArray.skipBits(8);
                }
            } else if (readBits2 == 1) {
                int readBits4 = vorbisBitArray.readBits(5);
                int[] iArr = new int[readBits4];
                int i5 = -1;
                for (int i6 = 0; i6 < readBits4; i6++) {
                    int readBits5 = vorbisBitArray.readBits(4);
                    iArr[i6] = readBits5;
                    if (readBits5 > i5) {
                        i5 = readBits5;
                    }
                }
                int i7 = i5 + 1;
                int[] iArr2 = new int[i7];
                for (int i8 = 0; i8 < i7; i8++) {
                    iArr2[i8] = vorbisBitArray.readBits(3) + 1;
                    int readBits6 = vorbisBitArray.readBits(2);
                    if (readBits6 > 0) {
                        vorbisBitArray.skipBits(8);
                    }
                    for (int i9 = 0; i9 < (1 << readBits6); i9++) {
                        vorbisBitArray.skipBits(8);
                    }
                }
                vorbisBitArray.skipBits(2);
                int readBits7 = vorbisBitArray.readBits(4);
                int i10 = 0;
                int i11 = 0;
                for (int i12 = 0; i12 < readBits4; i12++) {
                    i10 += iArr2[iArr[i12]];
                    while (i11 < i10) {
                        vorbisBitArray.skipBits(readBits7);
                        i11++;
                    }
                }
            } else {
                throw ParserException.createForMalformedContainer("floor type greater than 1 not decodable: " + readBits2, (Throwable) null);
            }
        }
    }

    private static void readMappings(int i3, VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int i4 = 0; i4 < readBits; i4++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 != 0) {
                Log.e(TAG, "mapping type other than 0 not supported: " + readBits2);
            } else {
                int readBits3 = vorbisBitArray.readBit() ? vorbisBitArray.readBits(4) + 1 : 1;
                if (vorbisBitArray.readBit()) {
                    int readBits4 = vorbisBitArray.readBits(8) + 1;
                    for (int i5 = 0; i5 < readBits4; i5++) {
                        int i6 = i3 - 1;
                        vorbisBitArray.skipBits(iLog(i6));
                        vorbisBitArray.skipBits(iLog(i6));
                    }
                }
                if (vorbisBitArray.readBits(2) == 0) {
                    if (readBits3 > 1) {
                        for (int i7 = 0; i7 < i3; i7++) {
                            vorbisBitArray.skipBits(4);
                        }
                    }
                    for (int i8 = 0; i8 < readBits3; i8++) {
                        vorbisBitArray.skipBits(8);
                        vorbisBitArray.skipBits(8);
                        vorbisBitArray.skipBits(8);
                    }
                } else {
                    throw ParserException.createForMalformedContainer("to reserved bits must be zero after mapping coupling steps", (Throwable) null);
                }
            }
        }
    }

    private static Mode[] readModes(VorbisBitArray vorbisBitArray) {
        int readBits = vorbisBitArray.readBits(6) + 1;
        Mode[] modeArr = new Mode[readBits];
        for (int i3 = 0; i3 < readBits; i3++) {
            modeArr[i3] = new Mode(vorbisBitArray.readBit(), vorbisBitArray.readBits(16), vorbisBitArray.readBits(16), vorbisBitArray.readBits(8));
        }
        return modeArr;
    }

    private static void readResidues(VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        int i3 = 0;
        while (i3 < readBits) {
            if (vorbisBitArray.readBits(16) <= 2) {
                vorbisBitArray.skipBits(24);
                vorbisBitArray.skipBits(24);
                vorbisBitArray.skipBits(24);
                int readBits2 = vorbisBitArray.readBits(6) + 1;
                vorbisBitArray.skipBits(8);
                int[] iArr = new int[readBits2];
                for (int i4 = 0; i4 < readBits2; i4++) {
                    iArr[i4] = ((vorbisBitArray.readBit() ? vorbisBitArray.readBits(5) : 0) * 8) + vorbisBitArray.readBits(3);
                }
                for (int i5 = 0; i5 < readBits2; i5++) {
                    for (int i6 = 0; i6 < 8; i6++) {
                        if ((iArr[i5] & (1 << i6)) != 0) {
                            vorbisBitArray.skipBits(8);
                        }
                    }
                }
                i3++;
            } else {
                throw ParserException.createForMalformedContainer("residueType greater than 2 is not decodable", (Throwable) null);
            }
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray) throws ParserException {
        return readVorbisCommentHeader(parsableByteArray, true, true);
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray parsableByteArray) throws ParserException {
        boolean z2 = true;
        verifyVorbisHeaderCapturePattern(1, parsableByteArray, false);
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt <= 0) {
            readLittleEndianInt = -1;
        }
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt2 <= 0) {
            readLittleEndianInt2 = -1;
        }
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt3 <= 0) {
            readLittleEndianInt3 = -1;
        }
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int pow = (int) Math.pow(2.0d, (double) (readUnsignedByte2 & 15));
        int pow2 = (int) Math.pow(2.0d, (double) ((readUnsignedByte2 & 240) >> 4));
        if ((parsableByteArray.readUnsignedByte() & 1) <= 0) {
            z2 = false;
        }
        return new VorbisIdHeader(readLittleEndianUnsignedIntToInt, readUnsignedByte, readLittleEndianUnsignedIntToInt2, readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, pow, pow2, z2, Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit()));
    }

    public static Mode[] readVorbisModes(ParsableByteArray parsableByteArray, int i3) throws ParserException {
        int i4 = 0;
        verifyVorbisHeaderCapturePattern(5, parsableByteArray, false);
        int readUnsignedByte = parsableByteArray.readUnsignedByte() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray.getData());
        vorbisBitArray.skipBits(parsableByteArray.getPosition() * 8);
        for (int i5 = 0; i5 < readUnsignedByte; i5++) {
            readBook(vorbisBitArray);
        }
        int readBits = vorbisBitArray.readBits(6) + 1;
        while (i4 < readBits) {
            if (vorbisBitArray.readBits(16) == 0) {
                i4++;
            } else {
                throw ParserException.createForMalformedContainer("placeholder of time domain transforms not zeroed out", (Throwable) null);
            }
        }
        readFloors(vorbisBitArray);
        readResidues(vorbisBitArray);
        readMappings(i3, vorbisBitArray);
        Mode[] readModes = readModes(vorbisBitArray);
        if (vorbisBitArray.readBit()) {
            return readModes;
        }
        throw ParserException.createForMalformedContainer("framing bit after modes not set as expected", (Throwable) null);
    }

    public static boolean verifyVorbisHeaderCapturePattern(int i3, ParsableByteArray parsableByteArray, boolean z2) throws ParserException {
        if (parsableByteArray.bytesLeft() < 7) {
            if (z2) {
                return false;
            }
            throw ParserException.createForMalformedContainer("too short header: " + parsableByteArray.bytesLeft(), (Throwable) null);
        } else if (parsableByteArray.readUnsignedByte() != i3) {
            if (z2) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected header type " + Integer.toHexString(i3), (Throwable) null);
        } else if (parsableByteArray.readUnsignedByte() == 118 && parsableByteArray.readUnsignedByte() == 111 && parsableByteArray.readUnsignedByte() == 114 && parsableByteArray.readUnsignedByte() == 98 && parsableByteArray.readUnsignedByte() == 105 && parsableByteArray.readUnsignedByte() == 115) {
            return true;
        } else {
            if (z2) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected characters 'vorbis'", (Throwable) null);
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray, boolean z2, boolean z3) throws ParserException {
        if (z2) {
            verifyVorbisHeaderCapturePattern(3, parsableByteArray, false);
        }
        String readString = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
        int length = readString.length();
        long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        String[] strArr = new String[((int) readLittleEndianUnsignedInt)];
        int i3 = length + 15;
        for (int i4 = 0; ((long) i4) < readLittleEndianUnsignedInt; i4++) {
            String readString2 = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
            strArr[i4] = readString2;
            i3 = i3 + 4 + readString2.length();
        }
        if (!z3 || (parsableByteArray.readUnsignedByte() & 1) != 0) {
            return new CommentHeader(readString, strArr, i3 + 1);
        }
        throw ParserException.createForMalformedContainer("framing bit expected to be set", (Throwable) null);
    }
}
