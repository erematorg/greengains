package com.appsamurai.storyly.exoplayer2.extractor.metadata.id3;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.MetadataInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder extends SimpleMetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = 4801587;
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = new a(7);
    private static final String TAG = "Id3Decoder";
    @Nullable
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i3, int i4, int i5, int i6, int i7);
    }

    public static final class Id3Header {
        /* access modifiers changed from: private */
        public final int framesSize;
        /* access modifiers changed from: private */
        public final boolean isUnsynchronized;
        /* access modifiers changed from: private */
        public final int majorVersion;

        public Id3Header(int i3, boolean z2, int i4) {
            this.majorVersion = i3;
            this.isUnsynchronized = z2;
            this.framesSize = i4;
        }
    }

    public Id3Decoder() {
        this((FramePredicate) null);
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i3, int i4) {
        return i4 <= i3 ? Util.EMPTY_BYTE_ARRAY : Arrays.copyOfRange(bArr, i3, i4);
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i3, int i4) throws UnsupportedEncodingException {
        int i5;
        String str;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i6 = i3 - 1;
        byte[] bArr = new byte[i6];
        parsableByteArray.readBytes(bArr, 0, i6);
        if (i4 == 2) {
            str = "image/" + Ascii.toLowerCase(new String(bArr, 0, 3, "ISO-8859-1"));
            if ("image/jpg".equals(str)) {
                str = "image/jpeg";
            }
            i5 = 2;
        } else {
            i5 = indexOfZeroByte(bArr, 0);
            str = Ascii.toLowerCase(new String(bArr, 0, i5, "ISO-8859-1"));
            if (str.indexOf(47) == -1) {
                str = "image/".concat(str);
            }
        }
        int i7 = i5 + 2;
        int indexOfEos = indexOfEos(bArr, i7, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i7, indexOfEos - i7, charsetName), bArr[i5 + 1] & 255, copyOfRangeIfValid(bArr, indexOfEos + delimiterLength(readUnsignedByte), i6));
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i3, String str) {
        byte[] bArr = new byte[i3];
        parsableByteArray.readBytes(bArr, 0, i3);
        return new BinaryFrame(str, bArr);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i3, int i4, boolean z2, int i5, @Nullable FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray.readInt();
        int readInt2 = parsableByteArray.readInt();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long j2 = readUnsignedInt == 4294967295L ? -1 : readUnsignedInt;
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        long j3 = readUnsignedInt2 == 4294967295L ? -1 : readUnsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i6 = position + i3;
        while (parsableByteArray.getPosition() < i6) {
            Id3Frame decodeFrame = decodeFrame(i4, parsableByteArray, z2, i5, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterFrame(str, readInt, readInt2, j2, j3, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i3, int i4, boolean z2, int i5, @Nullable FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z3 = (readUnsignedByte & 2) != 0;
        boolean z4 = (readUnsignedByte & 1) != 0;
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte2];
        for (int i6 = 0; i6 < readUnsignedByte2; i6++) {
            int position2 = parsableByteArray.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray.getData(), position2);
            strArr[i6] = new String(parsableByteArray.getData(), position2, indexOfZeroByte2 - position2, "ISO-8859-1");
            parsableByteArray2.setPosition(indexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i7 = position + i3;
        while (parsableByteArray.getPosition() < i7) {
            Id3Frame decodeFrame = decodeFrame(i4, parsableByteArray2, z2, i5, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterTocFrame(str, z3, z4, strArr, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    @Nullable
    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i3) throws UnsupportedEncodingException {
        if (i3 < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i4 = i3 - 4;
        byte[] bArr2 = new byte[i4];
        parsableByteArray.readBytes(bArr2, 0, i4);
        int indexOfEos = indexOfEos(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, delimiterLength, indexOfEos(bArr2, delimiterLength, readUnsignedByte), charsetName));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0196, code lost:
        if (r14 == 67) goto L_0x0198;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Frame decodeFrame(int r20, com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r21, boolean r22, int r23, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder.FramePredicate r24) {
        /*
            r0 = r20
            r7 = r21
            java.lang.String r8 = "Failed to decode frame: id="
            int r9 = r21.readUnsignedByte()
            int r10 = r21.readUnsignedByte()
            int r11 = r21.readUnsignedByte()
            r13 = 3
            if (r0 < r13) goto L_0x001b
            int r1 = r21.readUnsignedByte()
            r14 = r1
            goto L_0x001c
        L_0x001b:
            r14 = 0
        L_0x001c:
            r15 = 4
            if (r0 != r15) goto L_0x003f
            int r1 = r21.readUnsignedIntToInt()
            if (r22 != 0) goto L_0x003c
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 21
            r1 = r1 | r2
        L_0x003c:
            r16 = r1
            goto L_0x004b
        L_0x003f:
            if (r0 != r13) goto L_0x0046
            int r1 = r21.readUnsignedIntToInt()
            goto L_0x003c
        L_0x0046:
            int r1 = r21.readUnsignedInt24()
            goto L_0x003c
        L_0x004b:
            if (r0 < r13) goto L_0x0053
            int r1 = r21.readUnsignedShort()
            r6 = r1
            goto L_0x0054
        L_0x0053:
            r6 = 0
        L_0x0054:
            r17 = 0
            if (r9 != 0) goto L_0x006a
            if (r10 != 0) goto L_0x006a
            if (r11 != 0) goto L_0x006a
            if (r14 != 0) goto L_0x006a
            if (r16 != 0) goto L_0x006a
            if (r6 != 0) goto L_0x006a
            int r0 = r21.limit()
            r7.setPosition(r0)
            return r17
        L_0x006a:
            int r1 = r21.getPosition()
            int r5 = r1 + r16
            int r1 = r21.limit()
            java.lang.String r4 = "Id3Decoder"
            if (r5 <= r1) goto L_0x0085
            java.lang.String r0 = "Frame size exceeds remaining tag data"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r4, r0)
            int r0 = r21.limit()
            r7.setPosition(r0)
            return r17
        L_0x0085:
            if (r24 == 0) goto L_0x009d
            r1 = r24
            r2 = r20
            r3 = r9
            r12 = r4
            r4 = r10
            r15 = r5
            r5 = r11
            r19 = r6
            r6 = r14
            boolean r1 = r1.evaluate(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x00a1
            r7.setPosition(r15)
            return r17
        L_0x009d:
            r12 = r4
            r15 = r5
            r19 = r6
        L_0x00a1:
            r1 = 1
            if (r0 != r13) goto L_0x00bf
            r2 = r19
            r3 = r2 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x00ac
            r3 = r1
            goto L_0x00ad
        L_0x00ac:
            r3 = 0
        L_0x00ad:
            r4 = r2 & 64
            if (r4 == 0) goto L_0x00b3
            r4 = r1
            goto L_0x00b4
        L_0x00b3:
            r4 = 0
        L_0x00b4:
            r2 = r2 & 32
            if (r2 == 0) goto L_0x00ba
            r2 = r1
            goto L_0x00bb
        L_0x00ba:
            r2 = 0
        L_0x00bb:
            r18 = r3
            r6 = 0
            goto L_0x00f5
        L_0x00bf:
            r2 = r19
            r3 = 4
            if (r0 != r3) goto L_0x00ef
            r3 = r2 & 64
            if (r3 == 0) goto L_0x00ca
            r3 = r1
            goto L_0x00cb
        L_0x00ca:
            r3 = 0
        L_0x00cb:
            r4 = r2 & 8
            if (r4 == 0) goto L_0x00d1
            r4 = r1
            goto L_0x00d2
        L_0x00d1:
            r4 = 0
        L_0x00d2:
            r5 = r2 & 4
            if (r5 == 0) goto L_0x00d8
            r5 = r1
            goto L_0x00d9
        L_0x00d8:
            r5 = 0
        L_0x00d9:
            r6 = r2 & 2
            if (r6 == 0) goto L_0x00df
            r6 = r1
            goto L_0x00e0
        L_0x00df:
            r6 = 0
        L_0x00e0:
            r2 = r2 & r1
            if (r2 == 0) goto L_0x00e6
            r18 = r1
            goto L_0x00e8
        L_0x00e6:
            r18 = 0
        L_0x00e8:
            r2 = r3
            r3 = r18
            r18 = r4
            r4 = r5
            goto L_0x00f5
        L_0x00ef:
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = 0
            r18 = 0
        L_0x00f5:
            if (r18 != 0) goto L_0x022a
            if (r4 == 0) goto L_0x00fb
            goto L_0x022a
        L_0x00fb:
            if (r2 == 0) goto L_0x0102
            int r16 = r16 + -1
            r7.skipBytes(r1)
        L_0x0102:
            if (r3 == 0) goto L_0x010a
            int r16 = r16 + -4
            r1 = 4
            r7.skipBytes(r1)
        L_0x010a:
            r1 = r16
            if (r6 == 0) goto L_0x0112
            int r1 = removeUnsynchronization(r7, r1)
        L_0x0112:
            r13 = r1
            r1 = 2
            r2 = 84
            r3 = 88
            if (r9 != r2) goto L_0x012b
            if (r10 != r3) goto L_0x012b
            if (r11 != r3) goto L_0x012b
            if (r0 == r1) goto L_0x0122
            if (r14 != r3) goto L_0x012b
        L_0x0122:
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame r1 = decodeTxxxFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x0128:
            r0 = move-exception
            goto L_0x0226
        L_0x012b:
            if (r9 != r2) goto L_0x0137
            java.lang.String r1 = getFrameId(r0, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame r1 = decodeTextInformationFrame(r7, r13, r1)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x0137:
            r4 = 87
            if (r9 != r4) goto L_0x0149
            if (r10 != r3) goto L_0x0149
            if (r11 != r3) goto L_0x0149
            if (r0 == r1) goto L_0x0143
            if (r14 != r3) goto L_0x0149
        L_0x0143:
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.UrlLinkFrame r1 = decodeWxxxFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x0149:
            if (r9 != r4) goto L_0x0155
            java.lang.String r1 = getFrameId(r0, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.UrlLinkFrame r1 = decodeUrlLinkFrame(r7, r13, r1)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x0155:
            r3 = 73
            r4 = 80
            if (r9 != r4) goto L_0x016b
            r5 = 82
            if (r10 != r5) goto L_0x016b
            if (r11 != r3) goto L_0x016b
            r5 = 86
            if (r14 != r5) goto L_0x016b
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.PrivFrame r1 = decodePrivFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x016b:
            r5 = 71
            r6 = 79
            if (r9 != r5) goto L_0x0183
            r5 = 69
            if (r10 != r5) goto L_0x0183
            if (r11 != r6) goto L_0x0183
            r5 = 66
            if (r14 == r5) goto L_0x017d
            if (r0 != r1) goto L_0x0183
        L_0x017d:
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.GeobFrame r1 = decodeGeobFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x0183:
            r5 = 65
            r2 = 67
            if (r0 != r1) goto L_0x0190
            if (r9 != r4) goto L_0x019e
            if (r10 != r3) goto L_0x019e
            if (r11 != r2) goto L_0x019e
            goto L_0x0198
        L_0x0190:
            if (r9 != r5) goto L_0x019e
            if (r10 != r4) goto L_0x019e
            if (r11 != r3) goto L_0x019e
            if (r14 != r2) goto L_0x019e
        L_0x0198:
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.ApicFrame r1 = decodeApicFrame(r7, r13, r0)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x019e:
            r3 = 77
            if (r9 != r2) goto L_0x01af
            if (r10 != r6) goto L_0x01af
            if (r11 != r3) goto L_0x01af
            if (r14 == r3) goto L_0x01aa
            if (r0 != r1) goto L_0x01af
        L_0x01aa:
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.CommentFrame r1 = decodeCommentFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x01af:
            if (r9 != r2) goto L_0x01c9
            r1 = 72
            if (r10 != r1) goto L_0x01c9
            if (r11 != r5) goto L_0x01c9
            if (r14 != r4) goto L_0x01c9
            r1 = r21
            r2 = r13
            r3 = r20
            r4 = r22
            r5 = r23
            r6 = r24
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.ChapterFrame r1 = decodeChapterFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x01c9:
            if (r9 != r2) goto L_0x01e3
            r1 = 84
            if (r10 != r1) goto L_0x01e3
            if (r11 != r6) goto L_0x01e3
            if (r14 != r2) goto L_0x01e3
            r1 = r21
            r2 = r13
            r3 = r20
            r4 = r22
            r5 = r23
            r6 = r24
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.ChapterTocFrame r1 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x01e3:
            if (r9 != r3) goto L_0x01f4
            r1 = 76
            if (r10 != r1) goto L_0x01f4
            if (r11 != r1) goto L_0x01f4
            r1 = 84
            if (r14 != r1) goto L_0x01f4
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.MlltFrame r1 = decodeMlltFrame(r7, r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            goto L_0x01fc
        L_0x01f4:
            java.lang.String r1 = getFrameId(r0, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.BinaryFrame r1 = decodeBinaryFrame(r7, r13, r1)     // Catch:{ UnsupportedEncodingException -> 0x021d }
        L_0x01fc:
            if (r1 != 0) goto L_0x0219
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x021d }
            r2.<init>(r8)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            java.lang.String r0 = getFrameId(r0, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            r2.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            java.lang.String r0 = ", frameSize="
            r2.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            r2.append(r13)     // Catch:{ UnsupportedEncodingException -> 0x021d }
            java.lang.String r0 = r2.toString()     // Catch:{ UnsupportedEncodingException -> 0x021d }
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r12, r0)     // Catch:{ UnsupportedEncodingException -> 0x021d }
        L_0x0219:
            r7.setPosition(r15)
            return r1
        L_0x021d:
            java.lang.String r0 = "Unsupported character encoding"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r12, r0)     // Catch:{ all -> 0x0128 }
            r7.setPosition(r15)
            return r17
        L_0x0226:
            r7.setPosition(r15)
            throw r0
        L_0x022a:
            java.lang.String r0 = "Skipping unsupported compressed or encrypted frame"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r12, r0)
            r7.setPosition(r15)
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder.decodeFrame(int, com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray, boolean, int, com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder$FramePredicate):com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Frame");
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i3) throws UnsupportedEncodingException {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i3 - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, indexOfZeroByte, "ISO-8859-1");
        int i5 = indexOfZeroByte + 1;
        int indexOfEos = indexOfEos(bArr, i5, readUnsignedByte);
        String decodeStringIfValid = decodeStringIfValid(bArr, i5, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        int indexOfEos2 = indexOfEos(bArr, delimiterLength, readUnsignedByte);
        return new GeobFrame(str, decodeStringIfValid, decodeStringIfValid(bArr, delimiterLength, indexOfEos2, charsetName), copyOfRangeIfValid(bArr, indexOfEos2 + delimiterLength(readUnsignedByte), i4));
    }

    @Nullable
    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (readUnsignedInt24 != 4801587) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: 0x".concat(String.format("%06X", new Object[]{Integer.valueOf(readUnsignedInt24)})));
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z2 = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        if (readUnsignedByte == 2) {
            if ((readUnsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedByte == 3) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedByte == 4) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readSynchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readSynchSafeInt2 - 4);
                readSynchSafeInt -= readSynchSafeInt2;
            }
            if ((readUnsignedByte2 & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            r.a(readUnsignedByte, "Skipped ID3 tag with unsupported majorVersion=", TAG);
            return null;
        }
        if (readUnsignedByte >= 4 || (readUnsignedByte2 & 128) == 0) {
            z2 = false;
        }
        return new Id3Header(readUnsignedByte, z2, readSynchSafeInt);
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i3) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        int readUnsignedInt242 = parsableByteArray.readUnsignedInt24();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i4 = ((i3 - 10) * 8) / (readUnsignedByte + readUnsignedByte2);
        int[] iArr = new int[i4];
        int[] iArr2 = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int readBits = parsableBitArray.readBits(readUnsignedByte);
            int readBits2 = parsableBitArray.readBits(readUnsignedByte2);
            iArr[i5] = readBits;
            iArr2[i5] = readBits2;
        }
        return new MlltFrame(readUnsignedShort, readUnsignedInt24, readUnsignedInt242, iArr, iArr2);
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i3) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i3];
        parsableByteArray.readBytes(bArr, 0, i3);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, indexOfZeroByte, "ISO-8859-1"), copyOfRangeIfValid(bArr, indexOfZeroByte + 1, i3));
    }

    private static String decodeStringIfValid(byte[] bArr, int i3, int i4, String str) throws UnsupportedEncodingException {
        return (i4 <= i3 || i4 > bArr.length) ? "" : new String(bArr, i3, i4 - i3, str);
    }

    @Nullable
    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i3, String str) throws UnsupportedEncodingException {
        if (i3 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i3 - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        return new TextInformationFrame(str, (String) null, new String(bArr, 0, indexOfEos(bArr, 0, readUnsignedByte), charsetName));
    }

    @Nullable
    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i3) throws UnsupportedEncodingException {
        if (i3 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i3 - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new TextInformationFrame("TXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfEos(bArr, delimiterLength, readUnsignedByte), charsetName));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i3, String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i3];
        parsableByteArray.readBytes(bArr, 0, i3);
        return new UrlLinkFrame(str, (String) null, new String(bArr, 0, indexOfZeroByte(bArr, 0), "ISO-8859-1"));
    }

    @Nullable
    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i3) throws UnsupportedEncodingException {
        if (i3 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i3 - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfZeroByte(bArr, delimiterLength), "ISO-8859-1"));
    }

    private static int delimiterLength(int i3) {
        return (i3 == 0 || i3 == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? "ISO-8859-1" : "UTF-8" : "UTF-16BE" : "UTF-16";
    }

    private static String getFrameId(int i3, int i4, int i5, int i6, int i7) {
        return i3 == 2 ? String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)}) : String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7)});
    }

    private static int indexOfEos(byte[] bArr, int i3, int i4) {
        int indexOfZeroByte = indexOfZeroByte(bArr, i3);
        if (i4 == 0 || i4 == 3) {
            return indexOfZeroByte;
        }
        while (indexOfZeroByte < bArr.length - 1) {
            if ((indexOfZeroByte - i3) % 2 == 0 && bArr[indexOfZeroByte + 1] == 0) {
                return indexOfZeroByte;
            }
            indexOfZeroByte = indexOfZeroByte(bArr, indexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i3) {
        while (i3 < bArr.length) {
            if (bArr[i3] == 0) {
                return i3;
            }
            i3++;
        }
        return bArr.length;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(int i3, int i4, int i5, int i6, int i7) {
        return false;
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i3) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int i4 = position;
        while (true) {
            int i5 = i4 + 1;
            if (i5 >= position + i3) {
                return i3;
            }
            if ((data[i4] & 255) == 255 && data[i5] == 0) {
                System.arraycopy(data, i4 + 2, data, i5, (i3 - (i4 - position)) - 2);
                i3--;
            }
            i4 = i5;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0079, code lost:
        if ((r10 & 1) != 0) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0089, code lost:
        if ((r10 & 128) != 0) goto L_0x008e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009b A[SYNTHETIC, Splitter:B:48:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0097 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean validateFrames(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r18.getPosition()
        L_0x0008:
            int r3 = r18.bytesLeft()     // Catch:{ all -> 0x0022 }
            r4 = 1
            r5 = r20
            if (r3 < r5) goto L_0x00ae
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L_0x0025
            int r7 = r18.readInt()     // Catch:{ all -> 0x0022 }
            long r8 = r18.readUnsignedInt()     // Catch:{ all -> 0x0022 }
            int r10 = r18.readUnsignedShort()     // Catch:{ all -> 0x0022 }
            goto L_0x002f
        L_0x0022:
            r0 = move-exception
            goto L_0x00b2
        L_0x0025:
            int r7 = r18.readUnsignedInt24()     // Catch:{ all -> 0x0022 }
            int r8 = r18.readUnsignedInt24()     // Catch:{ all -> 0x0022 }
            long r8 = (long) r8
            r10 = r6
        L_0x002f:
            r11 = 0
            if (r7 != 0) goto L_0x003d
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x003d
            if (r10 != 0) goto L_0x003d
            r1.setPosition(r2)
            return r4
        L_0x003d:
            r7 = 4
            if (r0 != r7) goto L_0x006e
            if (r21 != 0) goto L_0x006e
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004e
            r1.setPosition(r2)
            return r6
        L_0x004e:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L_0x006e:
            if (r0 != r7) goto L_0x007e
            r3 = r10 & 64
            if (r3 == 0) goto L_0x0076
            r3 = r4
            goto L_0x0077
        L_0x0076:
            r3 = r6
        L_0x0077:
            r7 = r10 & 1
            if (r7 == 0) goto L_0x007c
            goto L_0x008e
        L_0x007c:
            r4 = r6
            goto L_0x008e
        L_0x007e:
            if (r0 != r3) goto L_0x008c
            r3 = r10 & 32
            if (r3 == 0) goto L_0x0086
            r3 = r4
            goto L_0x0087
        L_0x0086:
            r3 = r6
        L_0x0087:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x007c
            goto L_0x008e
        L_0x008c:
            r3 = r6
            r4 = r3
        L_0x008e:
            if (r4 == 0) goto L_0x0092
            int r3 = r3 + 4
        L_0x0092:
            long r3 = (long) r3
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x009b
            r1.setPosition(r2)
            return r6
        L_0x009b:
            int r3 = r18.bytesLeft()     // Catch:{ all -> 0x0022 }
            long r3 = (long) r3
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x00a8
            r1.setPosition(r2)
            return r6
        L_0x00a8:
            int r3 = (int) r8
            r1.skipBytes(r3)     // Catch:{ all -> 0x0022 }
            goto L_0x0008
        L_0x00ae:
            r1.setPosition(r2)
            return r4
        L_0x00b2:
            r1.setPosition(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder.validateFrames(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray, int, int, boolean):boolean");
    }

    @Nullable
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    public Id3Decoder(@Nullable FramePredicate framePredicate2) {
        this.framePredicate = framePredicate2;
    }

    @Nullable
    public Metadata decode(byte[] bArr, int i3) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i3);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i4 = decodeHeader.majorVersion == 2 ? 6 : 10;
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z2 = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i4, false)) {
            if (decodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i4, true)) {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + decodeHeader.majorVersion);
                return null;
            }
            z2 = true;
        }
        while (parsableByteArray.bytesLeft() >= i4) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z2, i4, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }
}
