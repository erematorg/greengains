package com.google.protobuf;

import androidx.compose.animation.core.a;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    static final int COMPLETE = 0;
    static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor = ((!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) ? new SafeProcessor() : new UnsafeProcessor());

    public static class DecodeUtil {
        private DecodeUtil() {
        }

        /* access modifiers changed from: private */
        public static void handleFourBytes(byte b3, byte b4, byte b5, byte b6, char[] cArr, int i3) throws InvalidProtocolBufferException {
            if (!isNotTrailingByte(b4)) {
                if ((((b4 + 112) + (b3 << Ascii.FS)) >> 30) == 0 && !isNotTrailingByte(b5) && !isNotTrailingByte(b6)) {
                    int trailingByteValue = ((b3 & 7) << Ascii.DC2) | (trailingByteValue(b4) << 12) | (trailingByteValue(b5) << 6) | trailingByteValue(b6);
                    cArr[i3] = highSurrogate(trailingByteValue);
                    cArr[i3 + 1] = lowSurrogate(trailingByteValue);
                    return;
                }
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* access modifiers changed from: private */
        public static void handleOneByte(byte b3, char[] cArr, int i3) {
            cArr[i3] = (char) b3;
        }

        /* access modifiers changed from: private */
        public static void handleThreeBytes(byte b3, byte b4, byte b5, char[] cArr, int i3) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(b4) || ((b3 == -32 && b4 < -96) || ((b3 == -19 && b4 >= -96) || isNotTrailingByte(b5)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i3] = (char) (((b3 & Ascii.SI) << Ascii.FF) | (trailingByteValue(b4) << 6) | trailingByteValue(b5));
        }

        /* access modifiers changed from: private */
        public static void handleTwoBytes(byte b3, byte b4, char[] cArr, int i3) throws InvalidProtocolBufferException {
            if (b3 < -62 || isNotTrailingByte(b4)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i3] = (char) (((b3 & 31) << 6) | trailingByteValue(b4));
        }

        private static char highSurrogate(int i3) {
            return (char) ((i3 >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        private static boolean isNotTrailingByte(byte b3) {
            return b3 > -65;
        }

        /* access modifiers changed from: private */
        public static boolean isOneByte(byte b3) {
            return b3 >= 0;
        }

        /* access modifiers changed from: private */
        public static boolean isThreeBytes(byte b3) {
            return b3 < -16;
        }

        /* access modifiers changed from: private */
        public static boolean isTwoBytes(byte b3) {
            return b3 < -32;
        }

        private static char lowSurrogate(int i3) {
            return (char) ((i3 & 1023) + 56320);
        }

        private static int trailingByteValue(byte b3) {
            return b3 & okio.Utf8.REPLACEMENT_BYTE;
        }
    }

    public static abstract class Processor {
        public final String decodeUtf8(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i3, i4);
            } else if (byteBuffer.isDirect()) {
                return decodeUtf8Direct(byteBuffer, i3, i4);
            } else {
                return decodeUtf8Default(byteBuffer, i3, i4);
            }
        }

        public abstract String decodeUtf8(byte[] bArr, int i3, int i4) throws InvalidProtocolBufferException;

        public final String decodeUtf8Default(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException {
            if ((i3 | i4 | ((byteBuffer.limit() - i3) - i4)) >= 0) {
                int i5 = i3 + i4;
                char[] cArr = new char[i4];
                int i6 = 0;
                while (r10 < i5) {
                    byte b3 = byteBuffer.get(r10);
                    if (!DecodeUtil.isOneByte(b3)) {
                        break;
                    }
                    i3 = r10 + 1;
                    DecodeUtil.handleOneByte(b3, cArr, i6);
                    i6++;
                }
                int i7 = i6;
                while (r10 < i5) {
                    int i8 = r10 + 1;
                    byte b4 = byteBuffer.get(r10);
                    if (DecodeUtil.isOneByte(b4)) {
                        int i9 = i7 + 1;
                        DecodeUtil.handleOneByte(b4, cArr, i7);
                        while (i8 < i5) {
                            byte b5 = byteBuffer.get(i8);
                            if (!DecodeUtil.isOneByte(b5)) {
                                break;
                            }
                            i8++;
                            DecodeUtil.handleOneByte(b5, cArr, i9);
                            i9++;
                        }
                        i7 = i9;
                        r10 = i8;
                    } else if (DecodeUtil.isTwoBytes(b4)) {
                        if (i8 < i5) {
                            r10 += 2;
                            DecodeUtil.handleTwoBytes(b4, byteBuffer.get(i8), cArr, i7);
                            i7++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b4)) {
                        if (i8 < i5 - 1) {
                            int i10 = r10 + 2;
                            r10 += 3;
                            DecodeUtil.handleThreeBytes(b4, byteBuffer.get(i8), byteBuffer.get(i10), cArr, i7);
                            i7++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (i8 < i5 - 2) {
                        byte b6 = byteBuffer.get(i8);
                        int i11 = r10 + 3;
                        byte b7 = byteBuffer.get(r10 + 2);
                        r10 += 4;
                        DecodeUtil.handleFourBytes(b4, b6, b7, byteBuffer.get(i11), cArr, i7);
                        i7 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i7);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i3), Integer.valueOf(i4)}));
        }

        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException;

        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i3, int i4);

        public final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                Java8Compatibility.position(byteBuffer, Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        public final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int i3;
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i4 = 0;
            while (i4 < length) {
                try {
                    char charAt = charSequence.charAt(i4);
                    if (charAt >= 128) {
                        break;
                    }
                    byteBuffer.put(position + i4, (byte) charAt);
                    i4++;
                } catch (IndexOutOfBoundsException unused) {
                    int position2 = byteBuffer.position();
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i4) + " at index " + (Math.max(i4, (position - byteBuffer.position()) + 1) + position2));
                }
            }
            if (i4 == length) {
                Java8Compatibility.position(byteBuffer, position + i4);
                return;
            }
            position += i4;
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 < 128) {
                    byteBuffer.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    i3 = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                        byteBuffer.put(i3, (byte) ((charAt2 & '?') | 128));
                        position = i3;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i3;
                        int position22 = byteBuffer.position();
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i4) + " at index " + (Math.max(i4, (position - byteBuffer.position()) + 1) + position22));
                    }
                } else if (charAt2 < 55296 || 57343 < charAt2) {
                    i3 = position + 1;
                    byteBuffer.put(position, (byte) ((charAt2 >>> 12) | 224));
                    position += 2;
                    byteBuffer.put(i3, (byte) (((charAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
                } else {
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        try {
                            char charAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i6 = position + 1;
                                try {
                                    byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                                    int i7 = position + 2;
                                    try {
                                        byteBuffer.put(i6, (byte) (((codePoint >>> 12) & 63) | 128));
                                        position += 3;
                                        byteBuffer.put(i7, (byte) (((codePoint >>> 6) & 63) | 128));
                                        byteBuffer.put(position, (byte) ((codePoint & 63) | 128));
                                        i4 = i5;
                                    } catch (IndexOutOfBoundsException unused3) {
                                        i4 = i5;
                                        position = i7;
                                        int position222 = byteBuffer.position();
                                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i4) + " at index " + (Math.max(i4, (position - byteBuffer.position()) + 1) + position222));
                                    }
                                } catch (IndexOutOfBoundsException unused4) {
                                    position = i6;
                                    i4 = i5;
                                    int position2222 = byteBuffer.position();
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i4) + " at index " + (Math.max(i4, (position - byteBuffer.position()) + 1) + position2222));
                                }
                            } else {
                                i4 = i5;
                            }
                        } catch (IndexOutOfBoundsException unused5) {
                            i4 = i5;
                            int position22222 = byteBuffer.position();
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i4) + " at index " + (Math.max(i4, (position - byteBuffer.position()) + 1) + position22222));
                        }
                    }
                    throw new UnpairedSurrogateException(i4, length);
                }
                i4++;
                position++;
            }
            Java8Compatibility.position(byteBuffer, position);
        }

        public abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        public final boolean isValidUtf8(byte[] bArr, int i3, int i4) {
            return partialIsValidUtf8(0, bArr, i3, i4) == 0;
        }

        public final int partialIsValidUtf8(int i3, ByteBuffer byteBuffer, int i4, int i5) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i3, byteBuffer.array(), i4 + arrayOffset, arrayOffset + i5);
            } else if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i3, byteBuffer, i4, i5);
            } else {
                return partialIsValidUtf8Default(i3, byteBuffer, i4, i5);
            }
        }

        public abstract int partialIsValidUtf8(int i3, byte[] bArr, int i4, int i5);

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
            if (r7.get(r8) > -65) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x008f, code lost:
            if (r7.get(r6) > -65) goto L_0x0091;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
            if (r7.get(r8) > -65) goto L_0x001d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int partialIsValidUtf8Default(int r6, java.nio.ByteBuffer r7, int r8, int r9) {
            /*
                r5 = this;
                if (r6 == 0) goto L_0x0092
                if (r8 < r9) goto L_0x0005
                return r6
            L_0x0005:
                byte r5 = (byte) r6
                r0 = -32
                r1 = -1
                r2 = -65
                if (r5 >= r0) goto L_0x001e
                r6 = -62
                if (r5 < r6) goto L_0x001d
                int r5 = r8 + 1
                byte r6 = r7.get(r8)
                if (r6 <= r2) goto L_0x001a
                goto L_0x001d
            L_0x001a:
                r8 = r5
                goto L_0x0092
            L_0x001d:
                return r1
            L_0x001e:
                r3 = -16
                if (r5 >= r3) goto L_0x004f
                int r6 = r6 >> 8
                int r6 = ~r6
                byte r6 = (byte) r6
                if (r6 != 0) goto L_0x0038
                int r6 = r8 + 1
                byte r8 = r7.get(r8)
                if (r6 < r9) goto L_0x0035
                int r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r8)
                return r5
            L_0x0035:
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x0038:
                if (r6 > r2) goto L_0x004e
                r3 = -96
                if (r5 != r0) goto L_0x0040
                if (r6 < r3) goto L_0x004e
            L_0x0040:
                r0 = -19
                if (r5 != r0) goto L_0x0046
                if (r6 >= r3) goto L_0x004e
            L_0x0046:
                int r5 = r8 + 1
                byte r6 = r7.get(r8)
                if (r6 <= r2) goto L_0x001a
            L_0x004e:
                return r1
            L_0x004f:
                int r0 = r6 >> 8
                int r0 = ~r0
                byte r0 = (byte) r0
                if (r0 != 0) goto L_0x0064
                int r6 = r8 + 1
                byte r0 = r7.get(r8)
                if (r6 < r9) goto L_0x0062
                int r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0)
                return r5
            L_0x0062:
                r8 = 0
                goto L_0x006a
            L_0x0064:
                int r6 = r6 >> 16
                byte r6 = (byte) r6
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x006a:
                if (r8 != 0) goto L_0x007c
                int r8 = r6 + 1
                byte r6 = r7.get(r6)
                if (r8 < r9) goto L_0x0079
                int r5 = com.google.protobuf.Utf8.incompleteStateFor((int) r5, (int) r0, (int) r6)
                return r5
            L_0x0079:
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x007c:
                if (r0 > r2) goto L_0x0091
                int r5 = r5 << 28
                int r0 = r0 + 112
                int r0 = r0 + r5
                int r5 = r0 >> 30
                if (r5 != 0) goto L_0x0091
                if (r8 > r2) goto L_0x0091
                int r8 = r6 + 1
                byte r5 = r7.get(r6)
                if (r5 <= r2) goto L_0x0092
            L_0x0091:
                return r1
            L_0x0092:
                int r5 = partialIsValidUtf8(r7, r8, r9)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        public abstract int partialIsValidUtf8Direct(int i3, ByteBuffer byteBuffer, int i4, int i5);

        public final boolean isValidUtf8(ByteBuffer byteBuffer, int i3, int i4) {
            return partialIsValidUtf8(0, byteBuffer, i3, i4) == 0;
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i3, int i4) {
            int access$200 = i3 + Utf8.estimateConsecutiveAscii(byteBuffer, i3, i4);
            while (access$200 < i4) {
                int i5 = access$200 + 1;
                byte b3 = byteBuffer.get(access$200);
                if (b3 >= 0) {
                    access$200 = i5;
                } else if (b3 < -32) {
                    if (i5 >= i4) {
                        return b3;
                    }
                    if (b3 < -62 || byteBuffer.get(i5) > -65) {
                        return -1;
                    }
                    access$200 += 2;
                } else if (b3 < -16) {
                    if (i5 >= i4 - 1) {
                        return Utf8.incompleteStateFor(byteBuffer, b3, i5, i4 - i5);
                    }
                    int i6 = access$200 + 2;
                    byte b4 = byteBuffer.get(i5);
                    if (b4 > -65 || ((b3 == -32 && b4 < -96) || ((b3 == -19 && b4 >= -96) || byteBuffer.get(i6) > -65))) {
                        return -1;
                    }
                    access$200 += 3;
                } else if (i5 >= i4 - 2) {
                    return Utf8.incompleteStateFor(byteBuffer, b3, i5, i4 - i5);
                } else {
                    int i7 = access$200 + 2;
                    byte b5 = byteBuffer.get(i5);
                    if (b5 <= -65) {
                        if ((((b5 + 112) + (b3 << Ascii.FS)) >> 30) == 0) {
                            int i8 = access$200 + 3;
                            if (byteBuffer.get(i7) <= -65) {
                                access$200 += 4;
                                if (byteBuffer.get(i8) > -65) {
                                }
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }
    }

    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i3, int i4) {
            super(a.r("Unpaired surrogate at index ", i3, " of ", i4));
        }
    }

    private Utf8() {
    }

    public static String decodeUtf8(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(byteBuffer, i3, i4);
    }

    public static int encode(CharSequence charSequence, byte[] bArr, int i3, int i4) {
        return processor.encodeUtf8(charSequence, bArr, i3, i4);
    }

    public static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 < length) {
                char charAt = charSequence.charAt(i3);
                if (charAt >= 2048) {
                    i4 += encodedLengthGeneral(charSequence, i3);
                    break;
                }
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                break;
            }
        }
        if (i4 >= length) {
            return i4;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i4) + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        int i4 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
            } else {
                i4 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) >= 65536) {
                        i3++;
                    } else {
                        throw new UnpairedSurrogateException(i3, length);
                    }
                }
            }
            i3++;
        }
        return i4;
    }

    /* access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i3, int i4) {
        int i5 = i4 - 7;
        int i6 = i3;
        while (i6 < i5 && (byteBuffer.getLong(i6) & -9187201950435737472L) == 0) {
            i6 += 8;
        }
        return i6 - i3;
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i3) {
        if (i3 > -12) {
            return -1;
        }
        return i3;
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static int partialIsValidUtf8(int i3, byte[] bArr, int i4, int i5) {
        return processor.partialIsValidUtf8(i3, bArr, i4, i5);
    }

    public static String decodeUtf8(byte[] bArr, int i3, int i4) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(bArr, i3, i4);
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i3, int i4) {
        if (i3 > -12 || i4 > -65) {
            return -1;
        }
        return i3 ^ (i4 << 8);
    }

    public static boolean isValidUtf8(byte[] bArr, int i3, int i4) {
        return processor.isValidUtf8(bArr, i3, i4);
    }

    public static int partialIsValidUtf8(int i3, ByteBuffer byteBuffer, int i4, int i5) {
        return processor.partialIsValidUtf8(i3, byteBuffer, i4, i5);
    }

    public static final class UnsafeProcessor extends Processor {
        public static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j2, int i3) {
            int i4 = 0;
            if (i3 < 16) {
                return 0;
            }
            int i5 = 8 - (((int) j2) & 7);
            while (i4 < i5) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(bArr, j2) < 0) {
                    return i4;
                }
                i4++;
                j2 = j3;
            }
            while (true) {
                int i6 = i4 + 8;
                if (i6 <= i3 && (UnsafeUtil.getLong((Object) bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j2) & -9187201950435737472L) == 0) {
                    j2 += 8;
                    i4 = i6;
                }
            }
            while (i4 < i3) {
                long j4 = j2 + 1;
                if (UnsafeUtil.getByte(bArr, j2) < 0) {
                    return i4;
                }
                i4++;
                j2 = j4;
            }
            return i3;
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i3, long j2, int i4) {
            if (i4 == 0) {
                return Utf8.incompleteStateFor(i3);
            }
            if (i4 == 1) {
                return Utf8.incompleteStateFor(i3, UnsafeUtil.getByte(bArr, j2));
            }
            if (i4 == 2) {
                return Utf8.incompleteStateFor(i3, (int) UnsafeUtil.getByte(bArr, j2), (int) UnsafeUtil.getByte(bArr, j2 + 1));
            }
            throw new AssertionError();
        }

        public String decodeUtf8(byte[] bArr, int i3, int i4) throws InvalidProtocolBufferException {
            Charset charset = Internal.UTF_8;
            String str = new String(bArr, i3, i4, charset);
            if (!str.contains("�") || Arrays.equals(str.getBytes(charset), Arrays.copyOfRange(bArr, i3, i4 + i3))) {
                return str;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException {
            long j2;
            int i5 = i3;
            int i6 = i4;
            if ((i5 | i6 | ((byteBuffer.limit() - i5) - i6)) >= 0) {
                long addressOffset = UnsafeUtil.addressOffset(byteBuffer) + ((long) i5);
                long j3 = ((long) i6) + addressOffset;
                char[] cArr = new char[i6];
                int i7 = 0;
                while (j2 < j3) {
                    byte b3 = UnsafeUtil.getByte(j2);
                    if (!DecodeUtil.isOneByte(b3)) {
                        break;
                    }
                    addressOffset = j2 + 1;
                    DecodeUtil.handleOneByte(b3, cArr, i7);
                    i7++;
                }
                int i8 = i7;
                while (j2 < j3) {
                    long j4 = j2 + 1;
                    byte b4 = UnsafeUtil.getByte(j2);
                    if (DecodeUtil.isOneByte(b4)) {
                        int i9 = i8 + 1;
                        DecodeUtil.handleOneByte(b4, cArr, i8);
                        while (j4 < j3) {
                            byte b5 = UnsafeUtil.getByte(j4);
                            if (!DecodeUtil.isOneByte(b5)) {
                                break;
                            }
                            j4++;
                            DecodeUtil.handleOneByte(b5, cArr, i9);
                            i9++;
                        }
                        i8 = i9;
                        j2 = j4;
                    } else if (DecodeUtil.isTwoBytes(b4)) {
                        if (j4 < j3) {
                            j2 += 2;
                            DecodeUtil.handleTwoBytes(b4, UnsafeUtil.getByte(j4), cArr, i8);
                            i8++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b4)) {
                        if (j4 < j3 - 1) {
                            long j5 = 2 + j2;
                            j2 += 3;
                            DecodeUtil.handleThreeBytes(b4, UnsafeUtil.getByte(j4), UnsafeUtil.getByte(j5), cArr, i8);
                            i8++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (j4 < j3 - 2) {
                        byte b6 = UnsafeUtil.getByte(j4);
                        long j6 = 3 + j2;
                        byte b7 = UnsafeUtil.getByte(2 + j2);
                        j2 += 4;
                        DecodeUtil.handleFourBytes(b4, b6, b7, UnsafeUtil.getByte(j6), cArr, i8);
                        i8 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i8);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i3), Integer.valueOf(i4)}));
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[LOOP:1: B:13:0x0033->B:38:0x00f8, LOOP_START, PHI: r2 r4 r6 r9 r10 r11 
          PHI: (r2v3 int) = (r2v2 int), (r2v5 int) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r4v3 long) = (r4v2 long), (r4v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r6v3 long) = (r6v1 long), (r6v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r9v1 java.lang.String) = (r9v0 java.lang.String), (r9v2 java.lang.String) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r10v1 java.lang.String) = (r10v0 java.lang.String), (r10v2 java.lang.String) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int encodeUtf8(java.lang.CharSequence r24, byte[] r25, int r26, int r27) {
            /*
                r23 = this;
                r0 = r24
                r1 = r25
                r2 = r26
                r3 = r27
                long r4 = (long) r2
                long r6 = (long) r3
                long r6 = r6 + r4
                int r8 = r24.length()
                java.lang.String r9 = " at index "
                java.lang.String r10 = "Failed writing "
                if (r8 > r3) goto L_0x0141
                int r11 = r1.length
                int r11 = r11 - r3
                if (r11 < r2) goto L_0x0141
                r2 = 0
            L_0x001a:
                r11 = 1
                r3 = 128(0x80, float:1.794E-43)
                if (r2 >= r8) goto L_0x002f
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x002f
                long r11 = r11 + r4
                byte r3 = (byte) r13
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r4, (byte) r3)
                int r2 = r2 + 1
                r4 = r11
                goto L_0x001a
            L_0x002f:
                if (r2 != r8) goto L_0x0033
                int r0 = (int) r4
                return r0
            L_0x0033:
                if (r2 >= r8) goto L_0x013f
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x004f
                int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r14 >= 0) goto L_0x004f
                long r14 = r4 + r11
                byte r13 = (byte) r13
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r4, (byte) r13)
                r21 = r6
                r23 = r9
                r19 = r11
                r4 = r14
                r14 = r10
                goto L_0x00f8
            L_0x004f:
                r14 = 2048(0x800, float:2.87E-42)
                r15 = 2
                if (r13 >= r14) goto L_0x0076
                long r17 = r6 - r15
                int r14 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
                if (r14 > 0) goto L_0x0076
                r23 = r9
                r14 = r10
                long r9 = r4 + r11
                int r11 = r13 >>> 6
                r11 = r11 | 960(0x3c0, float:1.345E-42)
                byte r11 = (byte) r11
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r4, (byte) r11)
                long r4 = r4 + r15
                r11 = r13 & 63
                r11 = r11 | r3
                byte r11 = (byte) r11
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r9, (byte) r11)
            L_0x0070:
                r21 = r6
                r19 = 1
                goto L_0x00f8
            L_0x0076:
                r23 = r9
                r14 = r10
                r9 = 57343(0xdfff, float:8.0355E-41)
                r10 = 55296(0xd800, float:7.7486E-41)
                r11 = 3
                if (r13 < r10) goto L_0x0085
                if (r9 >= r13) goto L_0x00ad
            L_0x0085:
                long r17 = r6 - r11
                int r17 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
                if (r17 > 0) goto L_0x00ad
                r17 = 1
                long r9 = r4 + r17
                int r11 = r13 >>> 12
                r11 = r11 | 480(0x1e0, float:6.73E-43)
                byte r11 = (byte) r11
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r4, (byte) r11)
                long r11 = r4 + r15
                int r15 = r13 >>> 6
                r15 = r15 & 63
                r15 = r15 | r3
                byte r15 = (byte) r15
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r9, (byte) r15)
                r9 = 3
                long r4 = r4 + r9
                r9 = r13 & 63
                r9 = r9 | r3
                byte r9 = (byte) r9
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r11, (byte) r9)
                goto L_0x0070
            L_0x00ad:
                r11 = 4
                long r19 = r6 - r11
                int r19 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
                if (r19 > 0) goto L_0x010c
                int r9 = r2 + 1
                if (r9 == r8) goto L_0x0104
                char r2 = r0.charAt(r9)
                boolean r10 = java.lang.Character.isSurrogatePair(r13, r2)
                if (r10 == 0) goto L_0x0103
                int r2 = java.lang.Character.toCodePoint(r13, r2)
                r19 = 1
                long r11 = r4 + r19
                int r10 = r2 >>> 18
                r10 = r10 | 240(0xf0, float:3.36E-43)
                byte r10 = (byte) r10
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r4, (byte) r10)
                r21 = r6
                long r6 = r4 + r15
                int r10 = r2 >>> 12
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r11, (byte) r10)
                r10 = 3
                long r11 = r4 + r10
                int r10 = r2 >>> 6
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r6, (byte) r10)
                r6 = 4
                long r4 = r4 + r6
                r2 = r2 & 63
                r2 = r2 | r3
                byte r2 = (byte) r2
                com.google.protobuf.UnsafeUtil.putByte((byte[]) r1, (long) r11, (byte) r2)
                r2 = r9
            L_0x00f8:
                int r2 = r2 + 1
                r9 = r23
                r10 = r14
                r11 = r19
                r6 = r21
                goto L_0x0033
            L_0x0103:
                r2 = r9
            L_0x0104:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                int r2 = r2 + -1
                r0.<init>(r2, r8)
                throw r0
            L_0x010c:
                if (r10 > r13) goto L_0x0124
                if (r13 > r9) goto L_0x0124
                int r1 = r2 + 1
                if (r1 == r8) goto L_0x011e
                char r0 = r0.charAt(r1)
                boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
                if (r0 != 0) goto L_0x0124
            L_0x011e:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                r0.<init>(r2, r8)
                throw r0
            L_0x0124:
                java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r6 = r14
                r1.<init>(r6)
                r1.append(r13)
                r7 = r23
                r1.append(r7)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x013f:
                int r0 = (int) r4
                return r0
            L_0x0141:
                r7 = r9
                r6 = r10
                java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>(r6)
                int r8 = r8 + -1
                char r0 = r0.charAt(r8)
                r4.append(r0)
                r4.append(r7)
                int r0 = r2 + r3
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.encodeUtf8(java.lang.CharSequence, byte[], int, int):int");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0041 A[LOOP:1: B:11:0x0041->B:36:0x0100, LOOP_START, PHI: r2 r4 r9 r12 r14 
          PHI: (r2v2 long) = (r2v0 long), (r2v3 long) binds: [B:8:0x0039, B:36:0x0100] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r4v4 long) = (r4v3 long), (r4v6 long) binds: [B:8:0x0039, B:36:0x0100] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r9v4 int) = (r9v3 int), (r9v6 int) binds: [B:8:0x0039, B:36:0x0100] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r12v1 long) = (r12v0 long), (r12v2 long) binds: [B:8:0x0039, B:36:0x0100] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r14v1 char) = (r14v0 char), (r14v2 char) binds: [B:8:0x0039, B:36:0x0100] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x003b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void encodeUtf8Direct(java.lang.CharSequence r27, java.nio.ByteBuffer r28) {
            /*
                r26 = this;
                r0 = r27
                r1 = r28
                long r2 = com.google.protobuf.UnsafeUtil.addressOffset(r28)
                int r4 = r28.position()
                long r4 = (long) r4
                long r4 = r4 + r2
                int r6 = r28.limit()
                long r6 = (long) r6
                long r6 = r6 + r2
                int r8 = r27.length()
                long r9 = (long) r8
                long r11 = r6 - r4
                int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                java.lang.String r10 = " at index "
                java.lang.String r11 = "Failed writing "
                if (r9 > 0) goto L_0x0150
                r9 = 0
            L_0x0024:
                r12 = 1
                r14 = 128(0x80, float:1.794E-43)
                if (r9 >= r8) goto L_0x0039
                char r15 = r0.charAt(r9)
                if (r15 >= r14) goto L_0x0039
                long r12 = r12 + r4
                byte r14 = (byte) r15
                com.google.protobuf.UnsafeUtil.putByte(r4, r14)
                int r9 = r9 + 1
                r4 = r12
                goto L_0x0024
            L_0x0039:
                if (r9 != r8) goto L_0x0041
                long r4 = r4 - r2
                int r0 = (int) r4
                com.google.protobuf.Java8Compatibility.position(r1, r0)
                return
            L_0x0041:
                if (r9 >= r8) goto L_0x0145
                char r15 = r0.charAt(r9)
                if (r15 >= r14) goto L_0x005b
                int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r16 >= 0) goto L_0x005b
                long r16 = r4 + r12
                byte r15 = (byte) r15
                com.google.protobuf.UnsafeUtil.putByte(r4, r15)
                r18 = r2
                r1 = r9
                r9 = r14
                r4 = r16
                goto L_0x0100
            L_0x005b:
                r14 = 2048(0x800, float:2.87E-42)
                r16 = 2
                if (r15 >= r14) goto L_0x0083
                long r18 = r6 - r16
                int r14 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
                if (r14 > 0) goto L_0x0083
                r18 = r2
                long r1 = r4 + r12
                int r3 = r15 >>> 6
                r3 = r3 | 960(0x3c0, float:1.345E-42)
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r4, r3)
                long r4 = r4 + r16
                r3 = r15 & 63
                r14 = 128(0x80, float:1.794E-43)
                r3 = r3 | r14
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r1, r3)
            L_0x007e:
                r1 = r9
                r9 = 128(0x80, float:1.794E-43)
                goto L_0x0100
            L_0x0083:
                r18 = r2
                r1 = 57343(0xdfff, float:8.0355E-41)
                r2 = 55296(0xd800, float:7.7486E-41)
                r20 = 3
                if (r15 < r2) goto L_0x0091
                if (r1 >= r15) goto L_0x00b8
            L_0x0091:
                long r22 = r6 - r20
                int r3 = (r4 > r22 ? 1 : (r4 == r22 ? 0 : -1))
                if (r3 > 0) goto L_0x00b8
                long r1 = r4 + r12
                int r3 = r15 >>> 12
                r3 = r3 | 480(0x1e0, float:6.73E-43)
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r4, r3)
                long r12 = r4 + r16
                int r3 = r15 >>> 6
                r3 = r3 & 63
                r14 = 128(0x80, float:1.794E-43)
                r3 = r3 | r14
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r1, r3)
                long r4 = r4 + r20
                r1 = r15 & 63
                r1 = r1 | r14
                byte r1 = (byte) r1
                com.google.protobuf.UnsafeUtil.putByte(r12, r1)
                goto L_0x007e
            L_0x00b8:
                r12 = 4
                long r24 = r6 - r12
                int r3 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
                if (r3 > 0) goto L_0x0115
                int r1 = r9 + 1
                if (r1 == r8) goto L_0x010d
                char r2 = r0.charAt(r1)
                boolean r3 = java.lang.Character.isSurrogatePair(r15, r2)
                if (r3 == 0) goto L_0x010c
                int r2 = java.lang.Character.toCodePoint(r15, r2)
                r14 = 1
                long r12 = r4 + r14
                int r3 = r2 >>> 18
                r3 = r3 | 240(0xf0, float:3.36E-43)
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r4, r3)
                long r14 = r4 + r16
                int r3 = r2 >>> 12
                r3 = r3 & 63
                r9 = 128(0x80, float:1.794E-43)
                r3 = r3 | r9
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r12, r3)
                long r12 = r4 + r20
                int r3 = r2 >>> 6
                r3 = r3 & 63
                r3 = r3 | r9
                byte r3 = (byte) r3
                com.google.protobuf.UnsafeUtil.putByte(r14, r3)
                r14 = 4
                long r4 = r4 + r14
                r2 = r2 & 63
                r2 = r2 | r9
                byte r2 = (byte) r2
                com.google.protobuf.UnsafeUtil.putByte(r12, r2)
            L_0x0100:
                int r1 = r1 + 1
                r14 = r9
                r2 = r18
                r12 = 1
                r9 = r1
                r1 = r28
                goto L_0x0041
            L_0x010c:
                r9 = r1
            L_0x010d:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                int r9 = r9 + -1
                r0.<init>(r9, r8)
                throw r0
            L_0x0115:
                if (r2 > r15) goto L_0x012d
                if (r15 > r1) goto L_0x012d
                int r1 = r9 + 1
                if (r1 == r8) goto L_0x0127
                char r0 = r0.charAt(r1)
                boolean r0 = java.lang.Character.isSurrogatePair(r15, r0)
                if (r0 != 0) goto L_0x012d
            L_0x0127:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                r0.<init>(r9, r8)
                throw r0
            L_0x012d:
                java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r11)
                r1.append(r15)
                r1.append(r10)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0145:
                r18 = r2
                long r4 = r4 - r18
                int r0 = (int) r4
                r1 = r28
                com.google.protobuf.Java8Compatibility.position(r1, r0)
                return
            L_0x0150:
                java.lang.ArrayIndexOutOfBoundsException r2 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>(r11)
                int r8 = r8 + -1
                char r0 = r0.charAt(r8)
                r3.append(r0)
                r3.append(r10)
                int r0 = r28.limit()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.encodeUtf8Direct(java.lang.CharSequence, java.nio.ByteBuffer):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0058, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r11, r0) > -65) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x009e, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r11, r0) > -65) goto L_0x00a0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8(int r10, byte[] r11, int r12, int r13) {
            /*
                r9 = this;
                r9 = r12 | r13
                int r0 = r11.length
                int r0 = r0 - r13
                r9 = r9 | r0
                if (r9 < 0) goto L_0x00a8
                long r0 = (long) r12
                long r12 = (long) r13
                if (r10 == 0) goto L_0x00a1
                int r9 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
                if (r9 < 0) goto L_0x0010
                return r10
            L_0x0010:
                byte r9 = (byte) r10
                r2 = -32
                r3 = -1
                r4 = -65
                r5 = 1
                if (r9 >= r2) goto L_0x002a
                r10 = -62
                if (r9 < r10) goto L_0x0029
                long r5 = r5 + r0
                byte r9 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                if (r9 <= r4) goto L_0x0026
                goto L_0x0029
            L_0x0026:
                r0 = r5
                goto L_0x00a1
            L_0x0029:
                return r3
            L_0x002a:
                r7 = -16
                if (r9 >= r7) goto L_0x005e
                int r10 = r10 >> 8
                int r10 = ~r10
                byte r10 = (byte) r10
                if (r10 != 0) goto L_0x0044
                long r7 = r0 + r5
                byte r10 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                int r0 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
                if (r0 < 0) goto L_0x0043
                int r9 = com.google.protobuf.Utf8.incompleteStateFor(r9, r10)
                return r9
            L_0x0043:
                r0 = r7
            L_0x0044:
                if (r10 > r4) goto L_0x005d
                r7 = -96
                if (r9 != r2) goto L_0x004c
                if (r10 < r7) goto L_0x005d
            L_0x004c:
                r2 = -19
                if (r9 != r2) goto L_0x0052
                if (r10 >= r7) goto L_0x005d
            L_0x0052:
                long r9 = r0 + r5
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                if (r0 <= r4) goto L_0x005b
                goto L_0x005d
            L_0x005b:
                r0 = r9
                goto L_0x00a1
            L_0x005d:
                return r3
            L_0x005e:
                int r2 = r10 >> 8
                int r2 = ~r2
                byte r2 = (byte) r2
                if (r2 != 0) goto L_0x0076
                long r7 = r0 + r5
                byte r2 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                int r10 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
                if (r10 < 0) goto L_0x0073
                int r9 = com.google.protobuf.Utf8.incompleteStateFor(r9, r2)
                return r9
            L_0x0073:
                r10 = 0
                r0 = r7
                goto L_0x0079
            L_0x0076:
                int r10 = r10 >> 16
                byte r10 = (byte) r10
            L_0x0079:
                if (r10 != 0) goto L_0x008b
                long r7 = r0 + r5
                byte r10 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                int r0 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
                if (r0 < 0) goto L_0x008a
                int r9 = com.google.protobuf.Utf8.incompleteStateFor((int) r9, (int) r2, (int) r10)
                return r9
            L_0x008a:
                r0 = r7
            L_0x008b:
                if (r2 > r4) goto L_0x00a0
                int r9 = r9 << 28
                int r2 = r2 + 112
                int r2 = r2 + r9
                int r9 = r2 >> 30
                if (r9 != 0) goto L_0x00a0
                if (r10 > r4) goto L_0x00a0
                long r9 = r0 + r5
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r11, (long) r0)
                if (r0 <= r4) goto L_0x005b
            L_0x00a0:
                return r3
            L_0x00a1:
                long r12 = r12 - r0
                int r9 = (int) r12
                int r9 = partialIsValidUtf8(r11, r0, r9)
                return r9
            L_0x00a8:
                java.lang.ArrayIndexOutOfBoundsException r9 = new java.lang.ArrayIndexOutOfBoundsException
                int r10 = r11.length
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
                java.lang.Integer r11 = java.lang.Integer.valueOf(r12)
                java.lang.Integer r12 = java.lang.Integer.valueOf(r13)
                java.lang.Object[] r10 = new java.lang.Object[]{r10, r11, r12}
                java.lang.String r11 = "Array length=%d, index=%d, limit=%d"
                java.lang.String r10 = java.lang.String.format(r11, r10)
                r9.<init>(r10)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0062, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r0) > -65) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a8, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r0) > -65) goto L_0x00aa;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8Direct(int r9, java.nio.ByteBuffer r10, int r11, int r12) {
            /*
                r8 = this;
                r8 = r11 | r12
                int r0 = r10.limit()
                int r0 = r0 - r12
                r8 = r8 | r0
                if (r8 < 0) goto L_0x00b2
                long r0 = com.google.protobuf.UnsafeUtil.addressOffset(r10)
                long r2 = (long) r11
                long r0 = r0 + r2
                int r12 = r12 - r11
                long r10 = (long) r12
                long r10 = r10 + r0
                if (r9 == 0) goto L_0x00ab
                int r8 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
                if (r8 < 0) goto L_0x001a
                return r9
            L_0x001a:
                byte r8 = (byte) r9
                r12 = -32
                r2 = -1
                r3 = -65
                r4 = 1
                if (r8 >= r12) goto L_0x0034
                r9 = -62
                if (r8 < r9) goto L_0x0033
                long r4 = r4 + r0
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r8 <= r3) goto L_0x0030
                goto L_0x0033
            L_0x0030:
                r0 = r4
                goto L_0x00ab
            L_0x0033:
                return r2
            L_0x0034:
                r6 = -16
                if (r8 >= r6) goto L_0x0068
                int r9 = r9 >> 8
                int r9 = ~r9
                byte r9 = (byte) r9
                if (r9 != 0) goto L_0x004e
                long r6 = r0 + r4
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
                if (r0 < 0) goto L_0x004d
                int r8 = com.google.protobuf.Utf8.incompleteStateFor(r8, r9)
                return r8
            L_0x004d:
                r0 = r6
            L_0x004e:
                if (r9 > r3) goto L_0x0067
                r6 = -96
                if (r8 != r12) goto L_0x0056
                if (r9 < r6) goto L_0x0067
            L_0x0056:
                r12 = -19
                if (r8 != r12) goto L_0x005c
                if (r9 >= r6) goto L_0x0067
            L_0x005c:
                long r8 = r0 + r4
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r12 <= r3) goto L_0x0065
                goto L_0x0067
            L_0x0065:
                r0 = r8
                goto L_0x00ab
            L_0x0067:
                return r2
            L_0x0068:
                int r12 = r9 >> 8
                int r12 = ~r12
                byte r12 = (byte) r12
                if (r12 != 0) goto L_0x0080
                long r6 = r0 + r4
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r9 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
                if (r9 < 0) goto L_0x007d
                int r8 = com.google.protobuf.Utf8.incompleteStateFor(r8, r12)
                return r8
            L_0x007d:
                r9 = 0
                r0 = r6
                goto L_0x0083
            L_0x0080:
                int r9 = r9 >> 16
                byte r9 = (byte) r9
            L_0x0083:
                if (r9 != 0) goto L_0x0095
                long r6 = r0 + r4
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
                if (r0 < 0) goto L_0x0094
                int r8 = com.google.protobuf.Utf8.incompleteStateFor((int) r8, (int) r12, (int) r9)
                return r8
            L_0x0094:
                r0 = r6
            L_0x0095:
                if (r12 > r3) goto L_0x00aa
                int r8 = r8 << 28
                int r12 = r12 + 112
                int r12 = r12 + r8
                int r8 = r12 >> 30
                if (r8 != 0) goto L_0x00aa
                if (r9 > r3) goto L_0x00aa
                long r8 = r0 + r4
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r12 <= r3) goto L_0x0065
            L_0x00aa:
                return r2
            L_0x00ab:
                long r10 = r10 - r0
                int r8 = (int) r10
                int r8 = partialIsValidUtf8(r0, r8)
                return r8
            L_0x00b2:
                java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
                int r9 = r10.limit()
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                java.lang.Integer r10 = java.lang.Integer.valueOf(r11)
                java.lang.Integer r11 = java.lang.Integer.valueOf(r12)
                java.lang.Object[] r9 = new java.lang.Object[]{r9, r10, r11}
                java.lang.String r10 = "buffer limit=%d, index=%d, limit=%d"
                java.lang.String r9 = java.lang.String.format(r10, r9)
                r8.<init>(r9)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        private static int unsafeEstimateConsecutiveAscii(long j2, int i3) {
            if (i3 < 16) {
                return 0;
            }
            int i4 = (int) ((-j2) & 7);
            int i5 = i4;
            while (i5 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(j2) < 0) {
                    return i4 - i5;
                }
                i5--;
                j2 = j3;
            }
            int i6 = i3 - i4;
            while (i6 >= 8 && (UnsafeUtil.getLong(j2) & -9187201950435737472L) == 0) {
                j2 += 8;
                i6 -= 8;
            }
            return i3 - i6;
        }

        private static int unsafeIncompleteStateFor(long j2, int i3, int i4) {
            if (i4 == 0) {
                return Utf8.incompleteStateFor(i3);
            }
            if (i4 == 1) {
                return Utf8.incompleteStateFor(i3, UnsafeUtil.getByte(j2));
            }
            if (i4 == 2) {
                return Utf8.incompleteStateFor(i3, (int) UnsafeUtil.getByte(j2), (int) UnsafeUtil.getByte(j2 + 1));
            }
            throw new AssertionError();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(byte[] r10, long r11, int r13) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r10, r11, r13)
                int r13 = r13 - r0
                long r0 = (long) r0
                long r11 = r11 + r0
            L_0x0007:
                r0 = 0
                r1 = r0
            L_0x0009:
                r2 = 1
                if (r13 <= 0) goto L_0x001a
                long r4 = r11 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r11)
                if (r1 < 0) goto L_0x0019
                int r13 = r13 + -1
                r11 = r4
                goto L_0x0009
            L_0x0019:
                r11 = r4
            L_0x001a:
                if (r13 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r0 = r13 + -1
                r4 = -32
                r5 = -1
                r6 = -65
                if (r1 >= r4) goto L_0x003a
                if (r0 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r13 = r13 + -2
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r11
                byte r11 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r11)
                if (r11 <= r6) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r11 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r7 = -16
                r8 = 2
                if (r1 >= r7) goto L_0x0065
                r7 = 2
                if (r0 >= r7) goto L_0x0048
                int r10 = unsafeIncompleteStateFor(r10, r1, r11, r0)
                return r10
            L_0x0048:
                int r13 = r13 + -3
                long r2 = r2 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r11)
                if (r0 > r6) goto L_0x0064
                r7 = -96
                if (r1 != r4) goto L_0x0057
                if (r0 < r7) goto L_0x0064
            L_0x0057:
                r4 = -19
                if (r1 != r4) goto L_0x005d
                if (r0 >= r7) goto L_0x0064
            L_0x005d:
                long r11 = r11 + r8
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r2)
                if (r0 <= r6) goto L_0x0007
            L_0x0064:
                return r5
            L_0x0065:
                r4 = 3
                if (r0 >= r4) goto L_0x006d
                int r10 = unsafeIncompleteStateFor(r10, r1, r11, r0)
                return r10
            L_0x006d:
                int r13 = r13 + -4
                long r2 = r2 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r11)
                if (r0 > r6) goto L_0x008f
                int r1 = r1 << 28
                int r0 = r0 + 112
                int r0 = r0 + r1
                int r0 = r0 >> 30
                if (r0 != 0) goto L_0x008f
                long r8 = r8 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r2)
                if (r0 > r6) goto L_0x008f
                r0 = 3
                long r11 = r11 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte((byte[]) r10, (long) r8)
                if (r0 <= r6) goto L_0x0007
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(long r10, int r12) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r10, r12)
                long r1 = (long) r0
                long r10 = r10 + r1
                int r12 = r12 - r0
            L_0x0007:
                r0 = 0
                r1 = r0
            L_0x0009:
                r2 = 1
                if (r12 <= 0) goto L_0x001a
                long r4 = r10 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r1 < 0) goto L_0x0019
                int r12 = r12 + -1
                r10 = r4
                goto L_0x0009
            L_0x0019:
                r10 = r4
            L_0x001a:
                if (r12 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r0 = r12 + -1
                r4 = -32
                r5 = -1
                r6 = -65
                if (r1 >= r4) goto L_0x003a
                if (r0 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r12 = r12 + -2
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r10
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r10 <= r6) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r10 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r7 = -16
                r8 = 2
                if (r1 >= r7) goto L_0x0065
                r7 = 2
                if (r0 >= r7) goto L_0x0048
                int r10 = unsafeIncompleteStateFor(r10, r1, r0)
                return r10
            L_0x0048:
                int r12 = r12 + -3
                long r2 = r2 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r0 > r6) goto L_0x0064
                r7 = -96
                if (r1 != r4) goto L_0x0057
                if (r0 < r7) goto L_0x0064
            L_0x0057:
                r4 = -19
                if (r1 != r4) goto L_0x005d
                if (r0 >= r7) goto L_0x0064
            L_0x005d:
                long r10 = r10 + r8
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r2)
                if (r0 <= r6) goto L_0x0007
            L_0x0064:
                return r5
            L_0x0065:
                r4 = 3
                if (r0 >= r4) goto L_0x006d
                int r10 = unsafeIncompleteStateFor(r10, r1, r0)
                return r10
            L_0x006d:
                int r12 = r12 + -4
                long r2 = r2 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r0 > r6) goto L_0x008f
                int r1 = r1 << 28
                int r0 = r0 + 112
                int r0 = r0 + r1
                int r0 = r0 >> 30
                if (r0 != 0) goto L_0x008f
                long r8 = r8 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r2)
                if (r0 > r6) goto L_0x008f
                r0 = 3
                long r10 = r10 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r0 <= r6) goto L_0x0007
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i3, int i4, int i5) {
        if (i3 > -12 || i4 > -65 || i5 > -65) {
            return -1;
        }
        return (i3 ^ (i4 << 8)) ^ (i5 << 16);
    }

    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i3, int i4) {
        byte b3 = bArr[i3 - 1];
        int i5 = i4 - i3;
        if (i5 == 0) {
            return incompleteStateFor(b3);
        }
        if (i5 == 1) {
            return incompleteStateFor(b3, bArr[i3]);
        }
        if (i5 == 2) {
            return incompleteStateFor((int) b3, (int) bArr[i3], (int) bArr[i3 + 1]);
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i3, int i4, int i5) {
        if (i5 == 0) {
            return incompleteStateFor(i3);
        }
        if (i5 == 1) {
            return incompleteStateFor(i3, byteBuffer.get(i4));
        }
        if (i5 == 2) {
            return incompleteStateFor(i3, (int) byteBuffer.get(i4), (int) byteBuffer.get(i4 + 1));
        }
        throw new AssertionError();
    }

    public static final class SafeProcessor extends Processor {
        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i3, int i4) {
            while (i3 < i4) {
                int i5 = i3 + 1;
                byte b3 = bArr[i3];
                if (b3 >= 0) {
                    i3 = i5;
                } else if (b3 < -32) {
                    if (i5 >= i4) {
                        return b3;
                    }
                    if (b3 >= -62) {
                        i3 += 2;
                        if (bArr[i5] > -65) {
                        }
                    }
                    return -1;
                } else if (b3 < -16) {
                    if (i5 >= i4 - 1) {
                        return Utf8.incompleteStateFor(bArr, i5, i4);
                    }
                    int i6 = i3 + 2;
                    byte b4 = bArr[i5];
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        i3 += 3;
                        if (bArr[i6] > -65) {
                        }
                    }
                    return -1;
                } else if (i5 >= i4 - 2) {
                    return Utf8.incompleteStateFor(bArr, i5, i4);
                } else {
                    int i7 = i3 + 2;
                    byte b5 = bArr[i5];
                    if (b5 <= -65) {
                        if ((((b5 + 112) + (b3 << Ascii.FS)) >> 30) == 0) {
                            int i8 = i3 + 3;
                            if (bArr[i7] <= -65) {
                                i3 += 4;
                                if (bArr[i8] > -65) {
                                }
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }

        public String decodeUtf8(byte[] bArr, int i3, int i4) throws InvalidProtocolBufferException {
            if ((i3 | i4 | ((bArr.length - i3) - i4)) >= 0) {
                int i5 = i3 + i4;
                char[] cArr = new char[i4];
                int i6 = 0;
                while (r10 < i5) {
                    byte b3 = bArr[r10];
                    if (!DecodeUtil.isOneByte(b3)) {
                        break;
                    }
                    i3 = r10 + 1;
                    DecodeUtil.handleOneByte(b3, cArr, i6);
                    i6++;
                }
                int i7 = i6;
                while (r10 < i5) {
                    int i8 = r10 + 1;
                    byte b4 = bArr[r10];
                    if (DecodeUtil.isOneByte(b4)) {
                        int i9 = i7 + 1;
                        DecodeUtil.handleOneByte(b4, cArr, i7);
                        while (i8 < i5) {
                            byte b5 = bArr[i8];
                            if (!DecodeUtil.isOneByte(b5)) {
                                break;
                            }
                            i8++;
                            DecodeUtil.handleOneByte(b5, cArr, i9);
                            i9++;
                        }
                        i7 = i9;
                        r10 = i8;
                    } else if (DecodeUtil.isTwoBytes(b4)) {
                        if (i8 < i5) {
                            r10 += 2;
                            DecodeUtil.handleTwoBytes(b4, bArr[i8], cArr, i7);
                            i7++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b4)) {
                        if (i8 < i5 - 1) {
                            int i10 = r10 + 2;
                            r10 += 3;
                            DecodeUtil.handleThreeBytes(b4, bArr[i8], bArr[i10], cArr, i7);
                            i7++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (i8 < i5 - 2) {
                        byte b6 = bArr[i8];
                        int i11 = r10 + 3;
                        byte b7 = bArr[r10 + 2];
                        r10 += 4;
                        DecodeUtil.handleFourBytes(b4, b6, b7, bArr[i11], cArr, i7);
                        i7 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i7);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
        }

        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i3, int i4) throws InvalidProtocolBufferException {
            return decodeUtf8Default(byteBuffer, i3, i4);
        }

        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i3, int i4) {
            int i5;
            int i6;
            char charAt;
            int length = charSequence.length();
            int i7 = i4 + i3;
            int i8 = 0;
            while (i8 < length && (i6 = i8 + i3) < i7 && (charAt = charSequence.charAt(i8)) < 128) {
                bArr[i6] = (byte) charAt;
                i8++;
            }
            if (i8 == length) {
                return i3 + length;
            }
            int i9 = i3 + i8;
            while (i8 < length) {
                char charAt2 = charSequence.charAt(i8);
                if (charAt2 < 128 && i9 < i7) {
                    bArr[i9] = (byte) charAt2;
                    i9++;
                } else if (charAt2 < 2048 && i9 <= i7 - 2) {
                    int i10 = i9 + 1;
                    bArr[i9] = (byte) ((charAt2 >>> 6) | 960);
                    i9 += 2;
                    bArr[i10] = (byte) ((charAt2 & '?') | 128);
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i9 <= i7 - 3) {
                    bArr[i9] = (byte) ((charAt2 >>> 12) | 480);
                    int i11 = i9 + 2;
                    bArr[i9 + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i9 += 3;
                    bArr[i11] = (byte) ((charAt2 & '?') | 128);
                } else if (i9 <= i7 - 4) {
                    int i12 = i8 + 1;
                    if (i12 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i12);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            bArr[i9] = (byte) ((codePoint >>> 18) | 240);
                            bArr[i9 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i13 = i9 + 3;
                            bArr[i9 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i9 += 4;
                            bArr[i13] = (byte) ((codePoint & 63) | 128);
                            i8 = i12;
                        } else {
                            i8 = i12;
                        }
                    }
                    throw new UnpairedSurrogateException(i8 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i5 = i8 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i5)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i9);
                } else {
                    throw new UnpairedSurrogateException(i8, length);
                }
                i8++;
            }
            return i9;
        }

        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
            if (r7[r8] > -65) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0083, code lost:
            if (r7[r6] > -65) goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r7[r8] > -65) goto L_0x001b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8(int r6, byte[] r7, int r8, int r9) {
            /*
                r5 = this;
                if (r6 == 0) goto L_0x0086
                if (r8 < r9) goto L_0x0005
                return r6
            L_0x0005:
                byte r5 = (byte) r6
                r0 = -32
                r1 = -1
                r2 = -65
                if (r5 >= r0) goto L_0x001c
                r6 = -62
                if (r5 < r6) goto L_0x001b
                int r5 = r8 + 1
                byte r6 = r7[r8]
                if (r6 <= r2) goto L_0x0018
                goto L_0x001b
            L_0x0018:
                r8 = r5
                goto L_0x0086
            L_0x001b:
                return r1
            L_0x001c:
                r3 = -16
                if (r5 >= r3) goto L_0x0049
                int r6 = r6 >> 8
                int r6 = ~r6
                byte r6 = (byte) r6
                if (r6 != 0) goto L_0x0034
                int r6 = r8 + 1
                byte r8 = r7[r8]
                if (r6 < r9) goto L_0x0031
                int r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r8)
                return r5
            L_0x0031:
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x0034:
                if (r6 > r2) goto L_0x0048
                r3 = -96
                if (r5 != r0) goto L_0x003c
                if (r6 < r3) goto L_0x0048
            L_0x003c:
                r0 = -19
                if (r5 != r0) goto L_0x0042
                if (r6 >= r3) goto L_0x0048
            L_0x0042:
                int r5 = r8 + 1
                byte r6 = r7[r8]
                if (r6 <= r2) goto L_0x0018
            L_0x0048:
                return r1
            L_0x0049:
                int r0 = r6 >> 8
                int r0 = ~r0
                byte r0 = (byte) r0
                if (r0 != 0) goto L_0x005c
                int r6 = r8 + 1
                byte r0 = r7[r8]
                if (r6 < r9) goto L_0x005a
                int r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0)
                return r5
            L_0x005a:
                r8 = 0
                goto L_0x0062
            L_0x005c:
                int r6 = r6 >> 16
                byte r6 = (byte) r6
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x0062:
                if (r8 != 0) goto L_0x0072
                int r8 = r6 + 1
                byte r6 = r7[r6]
                if (r8 < r9) goto L_0x006f
                int r5 = com.google.protobuf.Utf8.incompleteStateFor((int) r5, (int) r0, (int) r6)
                return r5
            L_0x006f:
                r4 = r8
                r8 = r6
                r6 = r4
            L_0x0072:
                if (r0 > r2) goto L_0x0085
                int r5 = r5 << 28
                int r0 = r0 + 112
                int r0 = r0 + r5
                int r5 = r0 >> 30
                if (r5 != 0) goto L_0x0085
                if (r8 > r2) goto L_0x0085
                int r8 = r6 + 1
                byte r5 = r7[r6]
                if (r5 <= r2) goto L_0x0086
            L_0x0085:
                return r1
            L_0x0086:
                int r5 = partialIsValidUtf8(r7, r8, r9)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        public int partialIsValidUtf8Direct(int i3, ByteBuffer byteBuffer, int i4, int i5) {
            return partialIsValidUtf8Default(i3, byteBuffer, i4, i5);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i3, int i4) {
            while (i3 < i4 && bArr[i3] >= 0) {
                i3++;
            }
            if (i3 >= i4) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i3, i4);
        }
    }
}
