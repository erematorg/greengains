package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@ElementTypesAreNonnullByDefault
abstract class AbstractByteHasher extends AbstractHasher {
    private final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public abstract void update(byte b3);

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @CanIgnoreReturnValue
    public Hasher putByte(byte b3) {
        update(b3);
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putChar(char c3) {
        this.scratch.putChar(c3);
        return update(2);
    }

    @CanIgnoreReturnValue
    public Hasher putInt(int i3) {
        this.scratch.putInt(i3);
        return update(4);
    }

    @CanIgnoreReturnValue
    public Hasher putLong(long j2) {
        this.scratch.putLong(j2);
        return update(8);
    }

    @CanIgnoreReturnValue
    public Hasher putShort(short s3) {
        this.scratch.putShort(s3);
        return update(2);
    }

    public void update(byte[] bArr, int i3, int i4) {
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            update(bArr[i5]);
        }
    }

    public void update(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            update(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
            return;
        }
        for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
            update(byteBuffer.get());
        }
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        update(bArr);
        return this;
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        update(bArr, i3, i4);
        return this;
    }

    @CanIgnoreReturnValue
    private Hasher update(int i3) {
        try {
            update(this.scratch.array(), 0, i3);
            return this;
        } finally {
            Java8Compatibility.clear(this.scratch);
        }
    }

    @CanIgnoreReturnValue
    public Hasher putBytes(ByteBuffer byteBuffer) {
        update(byteBuffer);
        return this;
    }
}
