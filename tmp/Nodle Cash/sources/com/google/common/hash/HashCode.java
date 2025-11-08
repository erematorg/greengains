package com.google.common.hash;

import androidx.compose.animation.core.a;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        public BytesHashCode(byte[] bArr) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        public int asInt() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", bArr.length);
            byte[] bArr2 = this.bytes;
            return ((bArr2[3] & 255) << Ascii.CAN) | (bArr2[0] & 255) | ((bArr2[1] & 255) << 8) | ((bArr2[2] & 255) << 16);
        }

        public long asLong() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", bArr.length);
            return padToLong();
        }

        public int bits() {
            return this.bytes.length * 8;
        }

        public boolean equalsSameBits(HashCode hashCode) {
            if (this.bytes.length != hashCode.getBytesInternal().length) {
                return false;
            }
            boolean z2 = true;
            int i3 = 0;
            while (true) {
                byte[] bArr = this.bytes;
                if (i3 >= bArr.length) {
                    return z2;
                }
                z2 &= bArr[i3] == hashCode.getBytesInternal()[i3];
                i3++;
            }
        }

        public byte[] getBytesInternal() {
            return this.bytes;
        }

        public long padToLong() {
            long j2 = (long) (this.bytes[0] & 255);
            for (int i3 = 1; i3 < Math.min(this.bytes.length, 8); i3++) {
                j2 |= (((long) this.bytes[i3]) & 255) << (i3 * 8);
            }
            return j2;
        }

        public void writeBytesToImpl(byte[] bArr, int i3, int i4) {
            System.arraycopy(this.bytes, 0, bArr, i3, i4);
        }
    }

    public static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final int hash;

        public IntHashCode(int i3) {
            this.hash = i3;
        }

        public byte[] asBytes() {
            int i3 = this.hash;
            return new byte[]{(byte) i3, (byte) (i3 >> 8), (byte) (i3 >> 16), (byte) (i3 >> 24)};
        }

        public int asInt() {
            return this.hash;
        }

        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        public int bits() {
            return 32;
        }

        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asInt();
        }

        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        public void writeBytesToImpl(byte[] bArr, int i3, int i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                bArr[i3 + i5] = (byte) (this.hash >> (i5 * 8));
            }
        }
    }

    public static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final long hash;

        public LongHashCode(long j2) {
            this.hash = j2;
        }

        public byte[] asBytes() {
            long j2 = this.hash;
            return new byte[]{(byte) ((int) j2), (byte) ((int) (j2 >> 8)), (byte) ((int) (j2 >> 16)), (byte) ((int) (j2 >> 24)), (byte) ((int) (j2 >> 32)), (byte) ((int) (j2 >> 40)), (byte) ((int) (j2 >> 48)), (byte) ((int) (j2 >> 56))};
        }

        public int asInt() {
            return (int) this.hash;
        }

        public long asLong() {
            return this.hash;
        }

        public int bits() {
            return 64;
        }

        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asLong();
        }

        public long padToLong() {
            return this.hash;
        }

        public void writeBytesToImpl(byte[] bArr, int i3, int i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                bArr[i3 + i5] = (byte) ((int) (this.hash >> (i5 * 8)));
            }
        }
    }

    private static int decode(char c3) {
        if (c3 >= '0' && c3 <= '9') {
            return c3 - '0';
        }
        if (c3 >= 'a' && c3 <= 'f') {
            return c3 - 'W';
        }
        throw new IllegalArgumentException(a.p("Illegal hexadecimal character: ", c3));
    }

    public static HashCode fromBytes(byte[] bArr) {
        boolean z2 = true;
        if (bArr.length < 1) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "A HashCode must contain at least 1 byte.");
        return fromBytesNoCopy((byte[]) bArr.clone());
    }

    public static HashCode fromBytesNoCopy(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    public static HashCode fromInt(int i3) {
        return new IntHashCode(i3);
    }

    public static HashCode fromLong(long j2) {
        return new LongHashCode(j2);
    }

    public static HashCode fromString(String str) {
        boolean z2 = true;
        Preconditions.checkArgument(str.length() >= 2, "input string (%s) must have at least 2 characters", (Object) str);
        if (str.length() % 2 != 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "input string (%s) must have an even number of characters", (Object) str);
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i3 = 0; i3 < str.length(); i3 += 2) {
            bArr[i3 / 2] = (byte) ((decode(str.charAt(i3)) << 4) + decode(str.charAt(i3 + 1)));
        }
        return fromBytesNoCopy(bArr);
    }

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    public final boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof HashCode)) {
            return false;
        }
        HashCode hashCode = (HashCode) obj;
        return bits() == hashCode.bits() && equalsSameBits(hashCode);
    }

    public abstract boolean equalsSameBits(HashCode hashCode);

    public byte[] getBytesInternal() {
        return asBytes();
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] bytesInternal = getBytesInternal();
        byte b3 = bytesInternal[0] & 255;
        for (int i3 = 1; i3 < bytesInternal.length; i3++) {
            b3 |= (bytesInternal[i3] & 255) << (i3 * 8);
        }
        return b3;
    }

    public abstract long padToLong();

    public final String toString() {
        byte[] bytesInternal = getBytesInternal();
        StringBuilder sb = new StringBuilder(bytesInternal.length * 2);
        for (byte b3 : bytesInternal) {
            char[] cArr = hexDigits;
            sb.append(cArr[(b3 >> 4) & 15]);
            sb.append(cArr[b3 & Ascii.SI]);
        }
        return sb.toString();
    }

    @CanIgnoreReturnValue
    public int writeBytesTo(byte[] bArr, int i3, int i4) {
        int min = Ints.min(i4, bits() / 8);
        Preconditions.checkPositionIndexes(i3, i3 + min, bArr.length);
        writeBytesToImpl(bArr, i3, min);
        return min;
    }

    public abstract void writeBytesToImpl(byte[] bArr, int i3, int i4);
}
