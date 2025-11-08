package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

@ElementTypesAreNonnullByDefault
@Immutable
abstract class AbstractNonStreamingHashFunction extends AbstractHashFunction {

    public final class BufferingHasher extends AbstractHasher {
        final ExposedByteArrayOutputStream stream;

        public BufferingHasher(int i3) {
            this.stream = new ExposedByteArrayOutputStream(i3);
        }

        public HashCode hash() {
            return AbstractNonStreamingHashFunction.this.hashBytes(this.stream.byteArray(), 0, this.stream.length());
        }

        public Hasher putByte(byte b3) {
            this.stream.write(b3);
            return this;
        }

        public Hasher putBytes(byte[] bArr, int i3, int i4) {
            this.stream.write(bArr, i3, i4);
            return this;
        }

        public Hasher putBytes(ByteBuffer byteBuffer) {
            this.stream.write(byteBuffer);
            return this;
        }
    }

    public static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream(int i3) {
            super(i3);
        }

        public byte[] byteArray() {
            return this.buf;
        }

        public int length() {
            return this.count;
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            int i3 = this.count;
            int i4 = i3 + remaining;
            byte[] bArr = this.buf;
            if (i4 > bArr.length) {
                this.buf = Arrays.copyOf(bArr, i3 + remaining);
            }
            byteBuffer.get(this.buf, this.count, remaining);
            this.count += remaining;
        }
    }

    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).putBytes(byteBuffer).hash();
    }

    public abstract HashCode hashBytes(byte[] bArr, int i3, int i4);

    public HashCode hashInt(int i3) {
        return hashBytes(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i3).array());
    }

    public HashCode hashLong(long j2) {
        return hashBytes(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(j2).array());
    }

    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return hashBytes(charSequence.toString().getBytes(charset));
    }

    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        ByteBuffer order = ByteBuffer.allocate(length * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i3 = 0; i3 < length; i3++) {
            order.putChar(charSequence.charAt(i3));
        }
        return hashBytes(order.array());
    }

    public Hasher newHasher() {
        return newHasher(32);
    }

    public Hasher newHasher(int i3) {
        Preconditions.checkArgument(i3 >= 0);
        return new BufferingHasher(i3);
    }
}
