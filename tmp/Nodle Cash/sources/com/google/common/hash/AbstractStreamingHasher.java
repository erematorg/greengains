package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@ElementTypesAreNonnullByDefault
abstract class AbstractStreamingHasher extends AbstractHasher {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final int chunkSize;

    public AbstractStreamingHasher(int i3) {
        this(i3, i3);
    }

    private void munch() {
        Java8Compatibility.flip(this.buffer);
        while (this.buffer.remaining() >= this.chunkSize) {
            process(this.buffer);
        }
        this.buffer.compact();
    }

    private void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    @CanIgnoreReturnValue
    private Hasher putBytesInternal(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(byteBuffer);
            munchIfFull();
            return this;
        }
        int position = this.bufferSize - this.buffer.position();
        for (int i3 = 0; i3 < position; i3++) {
            this.buffer.put(byteBuffer.get());
        }
        munch();
        while (byteBuffer.remaining() >= this.chunkSize) {
            process(byteBuffer);
        }
        this.buffer.put(byteBuffer);
        return this;
    }

    public final HashCode hash() {
        munch();
        Java8Compatibility.flip(this.buffer);
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        }
        return makeHash();
    }

    public abstract HashCode makeHash();

    public abstract void process(ByteBuffer byteBuffer);

    public void processRemaining(ByteBuffer byteBuffer) {
        Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        Java8Compatibility.limit(byteBuffer, this.chunkSize + 7);
        while (true) {
            int position = byteBuffer.position();
            int i3 = this.chunkSize;
            if (position < i3) {
                byteBuffer.putLong(0);
            } else {
                Java8Compatibility.limit(byteBuffer, i3);
                Java8Compatibility.flip(byteBuffer);
                process(byteBuffer);
                return;
            }
        }
    }

    public AbstractStreamingHasher(int i3, int i4) {
        Preconditions.checkArgument(i4 % i3 == 0);
        this.buffer = ByteBuffer.allocate(i4 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = i4;
        this.chunkSize = i3;
    }

    @CanIgnoreReturnValue
    public final Hasher putByte(byte b3) {
        this.buffer.put(b3);
        munchIfFull();
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putChar(char c3) {
        this.buffer.putChar(c3);
        munchIfFull();
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putInt(int i3) {
        this.buffer.putInt(i3);
        munchIfFull();
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putLong(long j2) {
        this.buffer.putLong(j2);
        munchIfFull();
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putShort(short s3) {
        this.buffer.putShort(s3);
        munchIfFull();
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putBytes(byte[] bArr, int i3, int i4) {
        return putBytesInternal(ByteBuffer.wrap(bArr, i3, i4).order(ByteOrder.LITTLE_ENDIAN));
    }

    /* JADX INFO: finally extract failed */
    @CanIgnoreReturnValue
    public final Hasher putBytes(ByteBuffer byteBuffer) {
        ByteOrder order = byteBuffer.order();
        try {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            Hasher putBytesInternal = putBytesInternal(byteBuffer);
            byteBuffer.order(order);
            return putBytesInternal;
        } catch (Throwable th) {
            byteBuffer.order(order);
            throw th;
        }
    }
}
