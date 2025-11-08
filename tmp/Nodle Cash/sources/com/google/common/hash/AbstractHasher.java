package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
abstract class AbstractHasher implements Hasher {
    @CanIgnoreReturnValue
    public <T> Hasher putObject(@ParametricNullness T t2, Funnel<? super T> funnel) {
        funnel.funnel(t2, this);
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putBoolean(boolean z2) {
        return putByte(z2 ? (byte) 1 : 0);
    }

    @CanIgnoreReturnValue
    public Hasher putChar(char c3) {
        putByte((byte) c3);
        putByte((byte) (c3 >>> 8));
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putDouble(double d2) {
        return putLong(Double.doubleToRawLongBits(d2));
    }

    @CanIgnoreReturnValue
    public final Hasher putFloat(float f2) {
        return putInt(Float.floatToRawIntBits(f2));
    }

    @CanIgnoreReturnValue
    public Hasher putInt(int i3) {
        putByte((byte) i3);
        putByte((byte) (i3 >>> 8));
        putByte((byte) (i3 >>> 16));
        putByte((byte) (i3 >>> 24));
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putLong(long j2) {
        for (int i3 = 0; i3 < 64; i3 += 8) {
            putByte((byte) ((int) (j2 >>> i3)));
        }
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putShort(short s3) {
        putByte((byte) s3);
        putByte((byte) (s3 >>> 8));
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    @CanIgnoreReturnValue
    public Hasher putUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            putChar(charSequence.charAt(i3));
        }
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        for (int i5 = 0; i5 < i4; i5++) {
            putByte(bArr[i3 + i5]);
        }
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            putBytes(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        } else {
            for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
                putByte(byteBuffer.get());
            }
        }
        return this;
    }
}
