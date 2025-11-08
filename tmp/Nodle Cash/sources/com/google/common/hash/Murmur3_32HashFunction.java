package com.google.common.hash;

import A.a;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable
final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {

    /* renamed from: C1  reason: collision with root package name */
    private static final int f6916C1 = -862048943;
    private static final int C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED, true);
    static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0, false);
    static final HashFunction MURMUR3_32_FIXED = new Murmur3_32HashFunction(0, true);
    private static final long serialVersionUID = 0;
    private final int seed;
    private final boolean supplementaryPlaneFix;

    public static final class Murmur3_32Hasher extends AbstractHasher {
        private long buffer;

        /* renamed from: h1  reason: collision with root package name */
        private int f6917h1;
        private boolean isDone = false;
        private int length = 0;
        private int shift;

        public Murmur3_32Hasher(int i3) {
            this.f6917h1 = i3;
        }

        private void update(int i3, long j2) {
            long j3 = this.buffer;
            int i4 = this.shift;
            long j4 = ((j2 & 4294967295L) << i4) | j3;
            this.buffer = j4;
            int i5 = (i3 * 8) + i4;
            this.shift = i5;
            this.length += i3;
            if (i5 >= 32) {
                this.f6917h1 = Murmur3_32HashFunction.mixH1(this.f6917h1, Murmur3_32HashFunction.mixK1((int) j4));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int access$000 = this.f6917h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.f6917h1 = access$000;
            return Murmur3_32HashFunction.fmix(access$000, this.length);
        }

        @CanIgnoreReturnValue
        public Hasher putByte(byte b3) {
            update(1, (long) (b3 & 255));
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putChar(char c3) {
            update(2, (long) c3);
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putInt(int i3) {
            update(4, (long) i3);
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putLong(long j2) {
            update(4, (long) ((int) j2));
            update(4, j2 >>> 32);
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putString(CharSequence charSequence, Charset charset) {
            if (!Charsets.UTF_8.equals(charset)) {
                return super.putString(charSequence, charset);
            }
            int length2 = charSequence.length();
            int i3 = 0;
            while (true) {
                int i4 = i3 + 4;
                if (i4 > length2) {
                    break;
                }
                char charAt = charSequence.charAt(i3);
                char charAt2 = charSequence.charAt(i3 + 1);
                char charAt3 = charSequence.charAt(i3 + 2);
                char charAt4 = charSequence.charAt(i3 + 3);
                if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                    break;
                }
                update(4, (long) ((charAt2 << 8) | charAt | (charAt3 << 16) | (charAt4 << 24)));
                i3 = i4;
            }
            while (i3 < length2) {
                char charAt5 = charSequence.charAt(i3);
                if (charAt5 < 128) {
                    update(1, (long) charAt5);
                } else if (charAt5 < 2048) {
                    update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(charAt5));
                } else if (charAt5 < 55296 || charAt5 > 57343) {
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(charAt5));
                } else {
                    int codePointAt = Character.codePointAt(charSequence, i3);
                    if (codePointAt == charAt5) {
                        putBytes(charSequence.subSequence(i3, length2).toString().getBytes(charset));
                        return this;
                    }
                    i3++;
                    update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(codePointAt));
                }
                i3++;
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putBytes(byte[] bArr, int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
            int i5 = 0;
            while (true) {
                int i6 = i5 + 4;
                if (i6 > i4) {
                    break;
                }
                update(4, (long) Murmur3_32HashFunction.getIntLittleEndian(bArr, i5 + i3));
                i5 = i6;
            }
            while (i5 < i4) {
                putByte(bArr[i3 + i5]);
                i5++;
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Hasher putBytes(ByteBuffer byteBuffer) {
            ByteOrder order = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                putInt(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                putByte(byteBuffer.get());
            }
            byteBuffer.order(order);
            return this;
        }
    }

    public Murmur3_32HashFunction(int i3, boolean z2) {
        this.seed = i3;
        this.supplementaryPlaneFix = z2;
    }

    /* access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c3) {
        return ((long) (c3 >>> 12)) | 224 | ((long) ((((c3 >>> 6) & 63) | 128) << 8)) | ((long) (((c3 & '?') | 128) << 16));
    }

    /* access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c3) {
        return ((long) (c3 >>> 6)) | 192 | ((long) (((c3 & '?') | 128) << 8));
    }

    /* access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int i3) {
        return ((long) (i3 >>> 18)) | 240 | ((((long) ((i3 >>> 12) & 63)) | 128) << 8) | ((((long) ((i3 >>> 6) & 63)) | 128) << 16) | ((((long) (i3 & 63)) | 128) << 24);
    }

    /* access modifiers changed from: private */
    public static HashCode fmix(int i3, int i4) {
        int i5 = i3 ^ i4;
        int i6 = (i5 ^ (i5 >>> 16)) * -2048144789;
        int i7 = (i6 ^ (i6 >>> 13)) * -1028477387;
        return HashCode.fromInt(i7 ^ (i7 >>> 16));
    }

    /* access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] bArr, int i3) {
        return Ints.fromBytes(bArr[i3 + 3], bArr[i3 + 2], bArr[i3 + 1], bArr[i3]);
    }

    /* access modifiers changed from: private */
    public static int mixH1(int i3, int i4) {
        return (Integer.rotateLeft(i3 ^ i4, 13) * 5) - 430675100;
    }

    /* access modifiers changed from: private */
    public static int mixK1(int i3) {
        return Integer.rotateLeft(i3 * -862048943, 15) * C2;
    }

    public int bits() {
        return 32;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof Murmur3_32HashFunction)) {
            return false;
        }
        Murmur3_32HashFunction murmur3_32HashFunction = (Murmur3_32HashFunction) obj;
        return this.seed == murmur3_32HashFunction.seed && this.supplementaryPlaneFix == murmur3_32HashFunction.supplementaryPlaneFix;
    }

    public HashCode hashBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        int i5 = this.seed;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = i7 + 4;
            if (i8 > i4) {
                break;
            }
            i5 = mixH1(i5, mixK1(getIntLittleEndian(bArr, i7 + i3)));
            i7 = i8;
        }
        int i9 = i7;
        int i10 = 0;
        while (i9 < i4) {
            i6 ^= UnsignedBytes.toInt(bArr[i3 + i9]) << i10;
            i9++;
            i10 += 8;
        }
        return fmix(i5 ^ mixK1(i6), i4);
    }

    public int hashCode() {
        return this.seed ^ Murmur3_32HashFunction.class.hashCode();
    }

    public HashCode hashInt(int i3) {
        return fmix(mixH1(this.seed, mixK1(i3)), 4);
    }

    public HashCode hashLong(long j2) {
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j2)), mixK1((int) (j2 >>> 32))), 8);
    }

    public HashCode hashString(CharSequence charSequence, Charset charset) {
        int i3;
        if (!Charsets.UTF_8.equals(charset)) {
            return hashBytes(charSequence.toString().getBytes(charset));
        }
        int length = charSequence.length();
        int i4 = this.seed;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = i6 + 4;
            if (i8 > length) {
                break;
            }
            char charAt = charSequence.charAt(i6);
            char charAt2 = charSequence.charAt(i6 + 1);
            char charAt3 = charSequence.charAt(i6 + 2);
            char charAt4 = charSequence.charAt(i6 + 3);
            if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                break;
            }
            i4 = mixH1(i4, mixK1((charAt2 << 8) | charAt | (charAt3 << 16) | (charAt4 << 24)));
            i7 = i3 + 4;
            i6 = i8;
        }
        long j2 = 0;
        while (i6 < length) {
            char charAt5 = charSequence.charAt(i6);
            if (charAt5 < 128) {
                j2 |= ((long) charAt5) << i5;
                i5 += 8;
                i3++;
            } else if (charAt5 < 2048) {
                j2 |= charToTwoUtf8Bytes(charAt5) << i5;
                i5 += 16;
                i3 += 2;
            } else if (charAt5 < 55296 || charAt5 > 57343) {
                j2 |= charToThreeUtf8Bytes(charAt5) << i5;
                i5 += 24;
                i3 += 3;
            } else {
                int codePointAt = Character.codePointAt(charSequence, i6);
                if (codePointAt == charAt5) {
                    return hashBytes(charSequence.toString().getBytes(charset));
                }
                i6++;
                j2 |= codePointToFourUtf8Bytes(codePointAt) << i5;
                if (this.supplementaryPlaneFix) {
                    i5 += 32;
                }
                i3 += 4;
            }
            if (i5 >= 32) {
                i4 = mixH1(i4, mixK1((int) j2));
                j2 >>>= 32;
                i5 -= 32;
            }
            i6++;
        }
        return fmix(mixK1((int) j2) ^ i4, i3);
    }

    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int i3 = this.seed;
        for (int i4 = 1; i4 < charSequence.length(); i4 += 2) {
            i3 = mixH1(i3, mixK1(charSequence.charAt(i4 - 1) | (charSequence.charAt(i4) << 16)));
        }
        if ((charSequence.length() & 1) == 1) {
            i3 ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(i3, charSequence.length() * 2);
    }

    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return a.m(new StringBuilder("Hashing.murmur3_32("), ")", this.seed);
    }
}
