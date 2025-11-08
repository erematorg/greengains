package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

final class NioByteString extends ByteString.LeafByteString {
    /* access modifiers changed from: private */
    public final ByteBuffer buffer;

    public NioByteString(ByteBuffer byteBuffer) {
        Internal.checkNotNull(byteBuffer, "buffer");
        this.buffer = byteBuffer.slice().order(ByteOrder.nativeOrder());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
    }

    private ByteBuffer slice(int i3, int i4) {
        if (i3 < this.buffer.position() || i4 > this.buffer.limit() || i3 > i4) {
            throw new IllegalArgumentException(String.format("Invalid indices [%d, %d]", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        }
        ByteBuffer slice = this.buffer.slice();
        Java8Compatibility.position(slice, i3 - this.buffer.position());
        Java8Compatibility.limit(slice, i4 - this.buffer.position());
        return slice;
    }

    private Object writeReplace() {
        return ByteString.copyFrom(this.buffer.slice());
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return this.buffer.asReadOnlyBuffer();
    }

    public List<ByteBuffer> asReadOnlyByteBufferList() {
        return Collections.singletonList(asReadOnlyByteBuffer());
    }

    public byte byteAt(int i3) {
        try {
            return this.buffer.get(i3);
        } catch (ArrayIndexOutOfBoundsException e3) {
            throw e3;
        } catch (IndexOutOfBoundsException e4) {
            throw new ArrayIndexOutOfBoundsException(e4.getMessage());
        }
    }

    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.buffer.slice());
    }

    public void copyToInternal(byte[] bArr, int i3, int i4, int i5) {
        ByteBuffer slice = this.buffer.slice();
        Java8Compatibility.position(slice, i3);
        slice.get(bArr, i4, i5);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (size() != byteString.size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        return obj instanceof NioByteString ? this.buffer.equals(((NioByteString) obj).buffer) : obj instanceof RopeByteString ? obj.equals(this) : this.buffer.equals(byteString.asReadOnlyByteBuffer());
    }

    public boolean equalsRange(ByteString byteString, int i3, int i4) {
        return substring(0, i4).equals(byteString.substring(i3, i4 + i3));
    }

    public byte internalByteAt(int i3) {
        return byteAt(i3);
    }

    public boolean isValidUtf8() {
        return Utf8.isValidUtf8(this.buffer);
    }

    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this.buffer, true);
    }

    public InputStream newInput() {
        return new InputStream() {
            private final ByteBuffer buf;

            {
                this.buf = NioByteString.this.buffer.slice();
            }

            public int available() throws IOException {
                return this.buf.remaining();
            }

            public void mark(int i3) {
                Java8Compatibility.mark(this.buf);
            }

            public boolean markSupported() {
                return true;
            }

            public int read() throws IOException {
                if (!this.buf.hasRemaining()) {
                    return -1;
                }
                return this.buf.get() & 255;
            }

            public void reset() throws IOException {
                try {
                    Java8Compatibility.reset(this.buf);
                } catch (InvalidMarkException e3) {
                    throw new IOException(e3);
                }
            }

            public int read(byte[] bArr, int i3, int i4) throws IOException {
                if (!this.buf.hasRemaining()) {
                    return -1;
                }
                int min = Math.min(i4, this.buf.remaining());
                this.buf.get(bArr, i3, min);
                return min;
            }
        };
    }

    public int partialHash(int i3, int i4, int i5) {
        for (int i6 = i4; i6 < i4 + i5; i6++) {
            i3 = (i3 * 31) + this.buffer.get(i6);
        }
        return i3;
    }

    public int partialIsValidUtf8(int i3, int i4, int i5) {
        return Utf8.partialIsValidUtf8(i3, this.buffer, i4, i5 + i4);
    }

    public int size() {
        return this.buffer.remaining();
    }

    public ByteString substring(int i3, int i4) {
        try {
            return new NioByteString(slice(i3, i4));
        } catch (ArrayIndexOutOfBoundsException e3) {
            throw e3;
        } catch (IndexOutOfBoundsException e4) {
            throw new ArrayIndexOutOfBoundsException(e4.getMessage());
        }
    }

    public String toStringInternal(Charset charset) {
        int i3;
        int i4;
        byte[] bArr;
        if (this.buffer.hasArray()) {
            bArr = this.buffer.array();
            i4 = this.buffer.position() + this.buffer.arrayOffset();
            i3 = this.buffer.remaining();
        } else {
            bArr = toByteArray();
            i3 = bArr.length;
            i4 = 0;
        }
        return new String(bArr, i4, i3, charset);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    public void writeToInternal(OutputStream outputStream, int i3, int i4) throws IOException {
        if (this.buffer.hasArray()) {
            int arrayOffset = this.buffer.arrayOffset();
            outputStream.write(this.buffer.array(), this.buffer.position() + arrayOffset + i3, i4);
            return;
        }
        ByteBufferWriter.write(slice(i3, i4 + i3), outputStream);
    }

    public void writeTo(ByteOutput byteOutput) throws IOException {
        byteOutput.writeLazy(this.buffer.slice());
    }
}
