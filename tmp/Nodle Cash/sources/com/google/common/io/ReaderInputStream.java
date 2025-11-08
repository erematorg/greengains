package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReaderInputStream(java.io.Reader r2, java.nio.charset.Charset r3, int r4) {
        /*
            r1 = this;
            java.nio.charset.CharsetEncoder r3 = r3.newEncoder()
            java.nio.charset.CodingErrorAction r0 = java.nio.charset.CodingErrorAction.REPLACE
            java.nio.charset.CharsetEncoder r3 = r3.onMalformedInput(r0)
            java.nio.charset.CharsetEncoder r3 = r3.onUnmappableCharacter(r0)
            r1.<init>((java.io.Reader) r2, (java.nio.charset.CharsetEncoder) r3, (int) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.ReaderInputStream.<init>(java.io.Reader, java.nio.charset.Charset, int):void");
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private int drain(byte[] bArr, int i3, int i4) {
        int min = Math.min(i4, this.byteBuffer.remaining());
        this.byteBuffer.get(bArr, i3, min);
        return min;
    }

    private static CharBuffer grow(CharBuffer charBuffer2) {
        CharBuffer wrap = CharBuffer.wrap(Arrays.copyOf(charBuffer2.array(), charBuffer2.capacity() * 2));
        Java8Compatibility.position(wrap, charBuffer2.position());
        Java8Compatibility.limit(wrap, charBuffer2.limit());
        return wrap;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                Java8Compatibility.flip(this.charBuffer.compact());
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int read = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (read == -1) {
            this.endOfInput = true;
        } else {
            Java8Compatibility.limit(this.charBuffer, limit + read);
        }
    }

    private void startDraining(boolean z2) {
        Java8Compatibility.flip(this.byteBuffer);
        if (!z2 || this.byteBuffer.remaining() != 0) {
            this.draining = true;
        } else {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        }
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r9 + r10
            int r1 = r8.length
            com.google.common.base.Preconditions.checkPositionIndexes(r9, r0, r1)
            r0 = 0
            if (r10 != 0) goto L_0x000a
            return r0
        L_0x000a:
            boolean r1 = r7.endOfInput
            r2 = r0
        L_0x000d:
            boolean r3 = r7.draining
            if (r3 == 0) goto L_0x002e
            int r3 = r9 + r2
            int r4 = r10 - r2
            int r3 = r7.drain(r8, r3, r4)
            int r2 = r2 + r3
            if (r2 == r10) goto L_0x0029
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L_0x0021
            goto L_0x0029
        L_0x0021:
            r7.draining = r0
            java.nio.ByteBuffer r3 = r7.byteBuffer
            com.google.common.io.Java8Compatibility.clear(r3)
            goto L_0x002e
        L_0x0029:
            if (r2 <= 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r2 = -1
        L_0x002d:
            return r2
        L_0x002e:
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L_0x0035
            java.nio.charset.CoderResult r3 = java.nio.charset.CoderResult.UNDERFLOW
            goto L_0x004c
        L_0x0035:
            if (r1 == 0) goto L_0x0040
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.ByteBuffer r4 = r7.byteBuffer
            java.nio.charset.CoderResult r3 = r3.flush(r4)
            goto L_0x004c
        L_0x0040:
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.CharBuffer r4 = r7.charBuffer
            java.nio.ByteBuffer r5 = r7.byteBuffer
            boolean r6 = r7.endOfInput
            java.nio.charset.CoderResult r3 = r3.encode(r4, r5, r6)
        L_0x004c:
            boolean r4 = r3.isOverflow()
            r5 = 1
            if (r4 == 0) goto L_0x0057
            r7.startDraining(r5)
            goto L_0x000d
        L_0x0057:
            boolean r4 = r3.isUnderflow()
            if (r4 == 0) goto L_0x006f
            if (r1 == 0) goto L_0x0065
            r7.doneFlushing = r5
            r7.startDraining(r0)
            goto L_0x000d
        L_0x0065:
            boolean r3 = r7.endOfInput
            if (r3 == 0) goto L_0x006b
            r1 = r5
            goto L_0x002e
        L_0x006b:
            r7.readMoreChars()
            goto L_0x002e
        L_0x006f:
            boolean r4 = r3.isError()
            if (r4 == 0) goto L_0x002e
            r3.throwException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.ReaderInputStream.read(byte[], int, int):int");
    }

    public ReaderInputStream(Reader reader2, CharsetEncoder charsetEncoder, int i3) {
        boolean z2 = true;
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader2);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i3 <= 0 ? false : z2, "bufferSize must be positive: %s", i3);
        charsetEncoder.reset();
        CharBuffer allocate = CharBuffer.allocate(i3);
        this.charBuffer = allocate;
        Java8Compatibility.flip(allocate);
        this.byteBuffer = ByteBuffer.allocate(i3);
    }
}
