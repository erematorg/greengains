package com.google.protobuf;

import A.a;
import java.nio.ByteBuffer;

@CheckReturnValue
abstract class AllocatedBuffer {
    public static AllocatedBuffer wrap(byte[] bArr) {
        return wrapNoCheck(bArr, 0, bArr.length);
    }

    private static AllocatedBuffer wrapNoCheck(final byte[] bArr, final int i3, final int i4) {
        return new AllocatedBuffer() {
            private int position;

            public byte[] array() {
                return bArr;
            }

            public int arrayOffset() {
                return i3;
            }

            public boolean hasArray() {
                return true;
            }

            public boolean hasNioBuffer() {
                return false;
            }

            public int limit() {
                return i4;
            }

            public ByteBuffer nioBuffer() {
                throw new UnsupportedOperationException();
            }

            public int position() {
                return this.position;
            }

            public int remaining() {
                return i4 - this.position;
            }

            public AllocatedBuffer position(int i3) {
                if (i3 < 0 || i3 > i4) {
                    throw new IllegalArgumentException(a.k("Invalid position: ", i3));
                }
                this.position = i3;
                return this;
            }
        };
    }

    public abstract byte[] array();

    public abstract int arrayOffset();

    public abstract boolean hasArray();

    public abstract boolean hasNioBuffer();

    public abstract int limit();

    public abstract ByteBuffer nioBuffer();

    public abstract int position();

    @CanIgnoreReturnValue
    public abstract AllocatedBuffer position(int i3);

    public abstract int remaining();

    public static AllocatedBuffer wrap(byte[] bArr, int i3, int i4) {
        if (i3 >= 0 && i4 >= 0 && i3 + i4 <= bArr.length) {
            return wrapNoCheck(bArr, i3, i4);
        }
        throw new IndexOutOfBoundsException(String.format("bytes.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
    }

    public static AllocatedBuffer wrap(final ByteBuffer byteBuffer) {
        Internal.checkNotNull(byteBuffer, "buffer");
        return new AllocatedBuffer() {
            public byte[] array() {
                return byteBuffer.array();
            }

            public int arrayOffset() {
                return byteBuffer.arrayOffset();
            }

            public boolean hasArray() {
                return byteBuffer.hasArray();
            }

            public boolean hasNioBuffer() {
                return true;
            }

            public int limit() {
                return byteBuffer.limit();
            }

            public ByteBuffer nioBuffer() {
                return byteBuffer;
            }

            public int position() {
                return byteBuffer.position();
            }

            public int remaining() {
                return byteBuffer.remaining();
            }

            public AllocatedBuffer position(int i3) {
                Java8Compatibility.position(byteBuffer, i3);
                return this;
            }
        };
    }
}
