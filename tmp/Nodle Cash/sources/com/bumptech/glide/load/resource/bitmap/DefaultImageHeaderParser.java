package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DefaultImageHeaderParser implements ImageHeaderParser {
    private static final int AVIF_BRAND = 1635150182;
    private static final int AVIS_BRAND = 1635150195;
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    static final int EXIF_MAGIC_NUMBER = 65496;
    static final int EXIF_SEGMENT_TYPE = 225;
    private static final int FTYP_HEADER = 1718909296;
    private static final int GIF_HEADER = 4671814;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes(Charset.forName("UTF-8"));
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int PNG_HEADER = -1991225785;
    private static final int RIFF_HEADER = 1380533830;
    private static final int SEGMENT_SOS = 218;
    static final int SEGMENT_START_ID = 255;
    private static final String TAG = "DfltImageHeaderParser";
    private static final int VP8_HEADER = 1448097792;
    private static final int VP8_HEADER_MASK = -256;
    private static final int VP8_HEADER_TYPE_EXTENDED = 88;
    private static final int VP8_HEADER_TYPE_LOSSLESS = 76;
    private static final int VP8_HEADER_TYPE_MASK = 255;
    private static final int WEBP_EXTENDED_ALPHA_FLAG = 16;
    private static final int WEBP_EXTENDED_ANIMATION_FLAG = 2;
    private static final int WEBP_HEADER = 1464156752;
    private static final int WEBP_LOSSLESS_ALPHA_FLAG = 8;

    public static final class ByteBufferReader implements Reader {
        private final ByteBuffer byteBuffer;

        public ByteBufferReader(ByteBuffer byteBuffer2) {
            this.byteBuffer = byteBuffer2;
            byteBuffer2.order(ByteOrder.BIG_ENDIAN);
        }

        public int getUInt16() throws Reader.EndOfFileException {
            return getUInt8() | (getUInt8() << 8);
        }

        public short getUInt8() throws Reader.EndOfFileException {
            if (this.byteBuffer.remaining() >= 1) {
                return (short) (this.byteBuffer.get() & 255);
            }
            throw new Reader.EndOfFileException();
        }

        public int read(byte[] bArr, int i3) {
            int min = Math.min(i3, this.byteBuffer.remaining());
            if (min == 0) {
                return -1;
            }
            this.byteBuffer.get(bArr, 0, min);
            return min;
        }

        public long skip(long j2) {
            int min = (int) Math.min((long) this.byteBuffer.remaining(), j2);
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.position(byteBuffer2.position() + min);
            return (long) min;
        }
    }

    public static final class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr, int i3) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i3);
        }

        private boolean isAvailable(int i3, int i4) {
            return this.data.remaining() - i3 >= i4;
        }

        public short getInt16(int i3) {
            if (isAvailable(i3, 2)) {
                return this.data.getShort(i3);
            }
            return -1;
        }

        public int getInt32(int i3) {
            if (isAvailable(i3, 4)) {
                return this.data.getInt(i3);
            }
            return -1;
        }

        public int length() {
            return this.data.remaining();
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }
    }

    public interface Reader {

        public static final class EndOfFileException extends IOException {
            private static final long serialVersionUID = 1;

            public EndOfFileException() {
                super("Unexpectedly reached end of a file");
            }
        }

        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i3) throws IOException;

        long skip(long j2) throws IOException;
    }

    public static final class StreamReader implements Reader {
        private final InputStream is;

        public StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        public int getUInt16() throws IOException {
            return getUInt8() | (getUInt8() << 8);
        }

        public short getUInt8() throws IOException {
            int read = this.is.read();
            if (read != -1) {
                return (short) read;
            }
            throw new Reader.EndOfFileException();
        }

        public int read(byte[] bArr, int i3) throws IOException {
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3 && (i5 = this.is.read(bArr, i4, i3 - i4)) != -1) {
                i4 += i5;
            }
            if (i4 != 0 || i5 != -1) {
                return i4;
            }
            throw new Reader.EndOfFileException();
        }

        public long skip(long j2) throws IOException {
            if (j2 < 0) {
                return 0;
            }
            long j3 = j2;
            while (j3 > 0) {
                long skip = this.is.skip(j3);
                if (skip <= 0) {
                    if (this.is.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j3 -= skip;
            }
            return j2 - j3;
        }
    }

    private static int calcTagOffset(int i3, int i4) {
        return (i4 * 12) + i3 + 2;
    }

    private static boolean handles(int i3) {
        return (i3 & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i3 == MOTOROLA_TIFF_MAGIC_NUMBER || i3 == INTEL_TIFF_MAGIC_NUMBER;
    }

    private boolean hasJpegExifPreamble(byte[] bArr, int i3) {
        boolean z2 = bArr != null && i3 > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z2) {
            int i4 = 0;
            while (true) {
                byte[] bArr2 = JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (i4 >= bArr2.length) {
                    break;
                } else if (bArr[i4] != bArr2[i4]) {
                    return false;
                } else {
                    i4++;
                }
            }
        }
        return z2;
    }

    private int moveToExifSegmentAndGetLength(Reader reader) throws IOException {
        short uInt8;
        int uInt16;
        long j2;
        long skip;
        do {
            short uInt82 = reader.getUInt8();
            if (uInt82 != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unknown segmentId=" + uInt82);
                }
                return -1;
            }
            uInt8 = reader.getUInt8();
            if (uInt8 == 218) {
                return -1;
            }
            if (uInt8 == 217) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            uInt16 = reader.getUInt16() - 2;
            if (uInt8 == 225) {
                return uInt16;
            }
            j2 = (long) uInt16;
            skip = reader.skip(j2);
        } while (skip == j2);
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder k2 = C0118y.k(uInt8, uInt16, "Unable to skip enough data, type: ", ", wanted to skip: ", ", but actually skipped: ");
            k2.append(skip);
            Log.d(TAG, k2.toString());
        }
        return -1;
    }

    private int parseExifSegment(Reader reader, byte[] bArr, int i3) throws IOException {
        int read = reader.read(bArr, i3);
        if (read != i3) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to read exif segment data, length: " + i3 + ", actually read: " + read);
            }
            return -1;
        } else if (hasJpegExifPreamble(bArr, i3)) {
            return parseExifSegment(new RandomAccessReader(bArr, i3));
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Missing jpeg exif preamble");
            }
            return -1;
        }
    }

    private ImageHeaderParser.ImageType sniffAvif(Reader reader, int i3) throws IOException {
        if (((reader.getUInt16() << 16) | reader.getUInt16()) != 1718909296) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int uInt16 = (reader.getUInt16() << 16) | reader.getUInt16();
        if (uInt16 == AVIS_BRAND) {
            return ImageHeaderParser.ImageType.ANIMATED_AVIF;
        }
        int i4 = 0;
        boolean z2 = uInt16 == AVIF_BRAND;
        reader.skip(4);
        int i5 = i3 - 16;
        if (i5 % 4 == 0) {
            while (i4 < 5 && i5 > 0) {
                int uInt162 = (reader.getUInt16() << 16) | reader.getUInt16();
                if (uInt162 == AVIS_BRAND) {
                    return ImageHeaderParser.ImageType.ANIMATED_AVIF;
                }
                if (uInt162 == AVIF_BRAND) {
                    z2 = true;
                }
                i4++;
                i5 -= 4;
            }
        }
        return z2 ? ImageHeaderParser.ImageType.AVIF : ImageHeaderParser.ImageType.UNKNOWN;
    }

    public int getOrientation(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        return getOrientation((Reader) new StreamReader((InputStream) Preconditions.checkNotNull(inputStream)), (ArrayPool) Preconditions.checkNotNull(arrayPool));
    }

    @NonNull
    public ImageHeaderParser.ImageType getType(@NonNull InputStream inputStream) throws IOException {
        return getType((Reader) new StreamReader((InputStream) Preconditions.checkNotNull(inputStream)));
    }

    @NonNull
    public ImageHeaderParser.ImageType getType(@NonNull ByteBuffer byteBuffer) throws IOException {
        return getType((Reader) new ByteBufferReader((ByteBuffer) Preconditions.checkNotNull(byteBuffer)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        return com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0039 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.bumptech.glide.load.ImageHeaderParser.ImageType getType(com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader r5) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r5.getUInt16()     // Catch:{ EndOfFileException -> 0x00a9 }
            r1 = 65496(0xffd8, float:9.178E-41)
            if (r0 != r1) goto L_0x000c
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.JPEG     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x000c:
            int r0 = r0 << 8
            short r1 = r5.getUInt8()     // Catch:{ EndOfFileException -> 0x00a9 }
            r0 = r0 | r1
            r1 = 4671814(0x474946, float:6.546606E-39)
            if (r0 != r1) goto L_0x001b
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.GIF     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x001b:
            int r0 = r0 << 8
            short r1 = r5.getUInt8()     // Catch:{ EndOfFileException -> 0x00a9 }
            r0 = r0 | r1
            r1 = -1991225785(0xffffffff89504e47, float:-2.5073895E-33)
            if (r0 != r1) goto L_0x003c
            r0 = 21
            r5.skip(r0)     // Catch:{ EndOfFileException -> 0x00a9 }
            short r4 = r5.getUInt8()     // Catch:{ EndOfFileException -> 0x0039 }
            r5 = 3
            if (r4 < r5) goto L_0x0036
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG_A     // Catch:{ EndOfFileException -> 0x0039 }
            goto L_0x0038
        L_0x0036:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG     // Catch:{ EndOfFileException -> 0x0039 }
        L_0x0038:
            return r4
        L_0x0039:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x003c:
            r1 = 1380533830(0x52494646, float:2.16116855E11)
            if (r0 == r1) goto L_0x0046
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = r4.sniffAvif(r5, r0)     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x0046:
            r0 = 4
            r5.skip(r0)     // Catch:{ EndOfFileException -> 0x00a9 }
            int r4 = r5.getUInt16()     // Catch:{ EndOfFileException -> 0x00a9 }
            int r4 = r4 << 16
            int r2 = r5.getUInt16()     // Catch:{ EndOfFileException -> 0x00a9 }
            r4 = r4 | r2
            r2 = 1464156752(0x57454250, float:2.16888601E14)
            if (r4 == r2) goto L_0x005e
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.UNKNOWN     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x005e:
            int r4 = r5.getUInt16()     // Catch:{ EndOfFileException -> 0x00a9 }
            int r4 = r4 << 16
            int r2 = r5.getUInt16()     // Catch:{ EndOfFileException -> 0x00a9 }
            r4 = r4 | r2
            r2 = r4 & -256(0xffffffffffffff00, float:NaN)
            r3 = 1448097792(0x56503800, float:5.7234734E13)
            if (r2 == r3) goto L_0x0073
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.UNKNOWN     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x0073:
            r4 = r4 & 255(0xff, float:3.57E-43)
            r2 = 88
            if (r4 != r2) goto L_0x0091
            r5.skip(r0)     // Catch:{ EndOfFileException -> 0x00a9 }
            short r4 = r5.getUInt8()     // Catch:{ EndOfFileException -> 0x00a9 }
            r5 = r4 & 2
            if (r5 == 0) goto L_0x0087
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.ANIMATED_WEBP     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x0087:
            r4 = r4 & 16
            if (r4 == 0) goto L_0x008e
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP_A     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x008e:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x0091:
            r2 = 76
            if (r4 != r2) goto L_0x00a6
            r5.skip(r0)     // Catch:{ EndOfFileException -> 0x00a9 }
            short r4 = r5.getUInt8()     // Catch:{ EndOfFileException -> 0x00a9 }
            r4 = r4 & 8
            if (r4 == 0) goto L_0x00a3
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP_A     // Catch:{ EndOfFileException -> 0x00a9 }
            goto L_0x00a5
        L_0x00a3:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP     // Catch:{ EndOfFileException -> 0x00a9 }
        L_0x00a5:
            return r4
        L_0x00a6:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP     // Catch:{ EndOfFileException -> 0x00a9 }
            return r4
        L_0x00a9:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.UNKNOWN
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.getType(com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser$Reader):com.bumptech.glide.load.ImageHeaderParser$ImageType");
    }

    public int getOrientation(@NonNull ByteBuffer byteBuffer, @NonNull ArrayPool arrayPool) throws IOException {
        return getOrientation((Reader) new ByteBufferReader((ByteBuffer) Preconditions.checkNotNull(byteBuffer)), (ArrayPool) Preconditions.checkNotNull(arrayPool));
    }

    private int getOrientation(Reader reader, ArrayPool arrayPool) throws IOException {
        byte[] bArr;
        try {
            int uInt16 = reader.getUInt16();
            if (!handles(uInt16)) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Parser doesn't handle magic number: " + uInt16);
                }
                return -1;
            }
            int moveToExifSegmentAndGetLength = moveToExifSegmentAndGetLength(reader);
            if (moveToExifSegmentAndGetLength == -1) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to parse exif segment length, or exif segment not found");
                }
                return -1;
            }
            bArr = (byte[]) arrayPool.get(moveToExifSegmentAndGetLength, byte[].class);
            int parseExifSegment = parseExifSegment(reader, bArr, moveToExifSegmentAndGetLength);
            arrayPool.put(bArr);
            return parseExifSegment;
        } catch (Reader.EndOfFileException unused) {
            return -1;
        } catch (Throwable th) {
            arrayPool.put(bArr);
            throw th;
        }
    }

    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        short int16 = randomAccessReader.getInt16(6);
        if (int16 == INTEL_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else if (int16 != MOTOROLA_TIFF_MAGIC_NUMBER) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unknown endianness = " + int16);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        int int32 = randomAccessReader.getInt32(10) + 6;
        short int162 = randomAccessReader.getInt16(int32);
        for (int i3 = 0; i3 < int162; i3++) {
            int calcTagOffset = calcTagOffset(int32, i3);
            short int163 = randomAccessReader.getInt16(calcTagOffset);
            if (int163 == ORIENTATION_TAG_TYPE) {
                short int164 = randomAccessReader.getInt16(calcTagOffset + 2);
                if (int164 >= 1 && int164 <= 12) {
                    int int322 = randomAccessReader.getInt32(calcTagOffset + 4);
                    if (int322 >= 0) {
                        if (Log.isLoggable(TAG, 3)) {
                            StringBuilder k2 = C0118y.k(i3, int163, "Got tagIndex=", " tagType=", " formatCode=");
                            k2.append(int164);
                            k2.append(" componentCount=");
                            k2.append(int322);
                            Log.d(TAG, k2.toString());
                        }
                        int i4 = int322 + BYTES_PER_FORMAT[int164];
                        if (i4 <= 4) {
                            int i5 = calcTagOffset + 8;
                            if (i5 < 0 || i5 > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal tagValueOffset=" + i5 + " tagType=" + int163);
                                }
                            } else if (i4 >= 0 && i4 + i5 <= randomAccessReader.length()) {
                                return randomAccessReader.getInt16(i5);
                            } else {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal number of bytes for TI tag data tagType=" + int163);
                                }
                            }
                        } else if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got byte count > 4, not orientation, continuing, formatCode=" + int164);
                        }
                    } else if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Negative tiff component count");
                    }
                } else if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Got invalid format code = " + int164);
                }
            }
        }
        return -1;
    }
}
