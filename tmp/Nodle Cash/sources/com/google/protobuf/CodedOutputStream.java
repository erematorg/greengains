package com.google.protobuf;

import androidx.browser.trusted.c;
import com.google.protobuf.Utf8;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    /* access modifiers changed from: private */
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();
    @Deprecated
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
    private boolean serializationDeterministic;
    CodedOutputStreamWriter wrapper;

    public static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        public AbstractBufferedEncoder(int i3) {
            super();
            if (i3 >= 0) {
                byte[] bArr = new byte[Math.max(i3, 20)];
                this.buffer = bArr;
                this.limit = bArr.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        public final void buffer(byte b3) {
            byte[] bArr = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = b3;
            this.totalBytesWritten++;
        }

        public final void bufferFixed32NoTag(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.position;
            int i5 = i4 + 1;
            this.position = i5;
            bArr[i4] = (byte) (i3 & 255);
            int i6 = i4 + 2;
            this.position = i6;
            bArr[i5] = (byte) ((i3 >> 8) & 255);
            int i7 = i4 + 3;
            this.position = i7;
            bArr[i6] = (byte) ((i3 >> 16) & 255);
            this.position = i4 + 4;
            bArr[i7] = (byte) ((i3 >> 24) & 255);
            this.totalBytesWritten += 4;
        }

        public final void bufferFixed64NoTag(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i3 + 1;
            this.position = i4;
            bArr[i3] = (byte) ((int) (j2 & 255));
            int i5 = i3 + 2;
            this.position = i5;
            bArr[i4] = (byte) ((int) ((j2 >> 8) & 255));
            int i6 = i3 + 3;
            this.position = i6;
            bArr[i5] = (byte) ((int) ((j2 >> 16) & 255));
            int i7 = i3 + 4;
            this.position = i7;
            bArr[i6] = (byte) ((int) (255 & (j2 >> 24)));
            int i8 = i3 + 5;
            this.position = i8;
            bArr[i7] = (byte) (((int) (j2 >> 32)) & 255);
            int i9 = i3 + 6;
            this.position = i9;
            bArr[i8] = (byte) (((int) (j2 >> 40)) & 255);
            int i10 = i3 + 7;
            this.position = i10;
            bArr[i9] = (byte) (((int) (j2 >> 48)) & 255);
            this.position = i3 + 8;
            bArr[i10] = (byte) (((int) (j2 >> 56)) & 255);
            this.totalBytesWritten += 8;
        }

        public final void bufferInt32NoTag(int i3) {
            if (i3 >= 0) {
                bufferUInt32NoTag(i3);
            } else {
                bufferUInt64NoTag((long) i3);
            }
        }

        public final void bufferTag(int i3, int i4) {
            bufferUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public final void bufferUInt32NoTag(int i3) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j2 = (long) this.position;
                while ((i3 & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    UnsafeUtil.putByte(bArr, (long) i4, (byte) ((i3 & 127) | 128));
                    i3 >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                UnsafeUtil.putByte(bArr2, (long) i5, (byte) i3);
                this.totalBytesWritten += (int) (((long) this.position) - j2);
                return;
            }
            while ((i3 & -128) != 0) {
                byte[] bArr3 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr3[i6] = (byte) ((i3 & 127) | 128);
                this.totalBytesWritten++;
                i3 >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i7 = this.position;
            this.position = i7 + 1;
            bArr4[i7] = (byte) i3;
            this.totalBytesWritten++;
        }

        public final void bufferUInt64NoTag(long j2) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j3 = (long) this.position;
                while ((j2 & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    UnsafeUtil.putByte(bArr, (long) i3, (byte) ((((int) j2) & 127) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                UnsafeUtil.putByte(bArr2, (long) i4, (byte) ((int) j2));
                this.totalBytesWritten += (int) (((long) this.position) - j3);
                return;
            }
            while ((j2 & -128) != 0) {
                byte[] bArr3 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr3[i5] = (byte) ((((int) j2) & 127) | 128);
                this.totalBytesWritten++;
                j2 >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i6 = this.position;
            this.position = i6 + 1;
            bArr4[i6] = (byte) ((int) j2);
            this.totalBytesWritten++;
        }

        public final int getTotalBytesWritten() {
            return this.totalBytesWritten;
        }

        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }
    }

    public static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        public ArrayEncoder(byte[] bArr, int i3, int i4) {
            super();
            if (bArr != null) {
                int i5 = i3 + i4;
                if ((i3 | i4 | (bArr.length - i5)) >= 0) {
                    this.buffer = bArr;
                    this.offset = i3;
                    this.position = i3;
                    this.limit = i5;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
            }
            throw new NullPointerException("buffer");
        }

        public void flush() {
        }

        public final int getTotalBytesWritten() {
            return this.position - this.offset;
        }

        public final int spaceLeft() {
            return this.limit - this.position;
        }

        public final void write(byte b3) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr[i3] = b3;
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e3);
            }
        }

        public final void writeBool(int i3, boolean z2) throws IOException {
            writeTag(i3, 0);
            write(z2 ? (byte) 1 : 0);
        }

        public final void writeByteArray(int i3, byte[] bArr) throws IOException {
            writeByteArray(i3, bArr, 0, bArr.length);
        }

        public final void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException {
            writeUInt32NoTag(i4);
            write(bArr, i3, i4);
        }

        public final void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(byteBuffer.capacity());
            writeRawBytes(byteBuffer);
        }

        public final void writeBytes(int i3, ByteString byteString) throws IOException {
            writeTag(i3, 2);
            writeBytesNoTag(byteString);
        }

        public final void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public final void writeFixed32(int i3, int i4) throws IOException {
            writeTag(i3, 5);
            writeFixed32NoTag(i4);
        }

        public final void writeFixed32NoTag(int i3) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i4 = this.position;
                int i5 = i4 + 1;
                this.position = i5;
                bArr[i4] = (byte) (i3 & 255);
                int i6 = i4 + 2;
                this.position = i6;
                bArr[i5] = (byte) ((i3 >> 8) & 255);
                int i7 = i4 + 3;
                this.position = i7;
                bArr[i6] = (byte) ((i3 >> 16) & 255);
                this.position = i4 + 4;
                bArr[i7] = (byte) ((i3 >> 24) & 255);
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e3);
            }
        }

        public final void writeFixed64(int i3, long j2) throws IOException {
            writeTag(i3, 1);
            writeFixed64NoTag(j2);
        }

        public final void writeFixed64NoTag(long j2) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                int i4 = i3 + 1;
                this.position = i4;
                bArr[i3] = (byte) (((int) j2) & 255);
                int i5 = i3 + 2;
                this.position = i5;
                bArr[i4] = (byte) (((int) (j2 >> 8)) & 255);
                int i6 = i3 + 3;
                this.position = i6;
                bArr[i5] = (byte) (((int) (j2 >> 16)) & 255);
                int i7 = i3 + 4;
                this.position = i7;
                bArr[i6] = (byte) (((int) (j2 >> 24)) & 255);
                int i8 = i3 + 5;
                this.position = i8;
                bArr[i7] = (byte) (((int) (j2 >> 32)) & 255);
                int i9 = i3 + 6;
                this.position = i9;
                bArr[i8] = (byte) (((int) (j2 >> 40)) & 255);
                int i10 = i3 + 7;
                this.position = i10;
                bArr[i9] = (byte) (((int) (j2 >> 48)) & 255);
                this.position = i3 + 8;
                bArr[i10] = (byte) (((int) (j2 >> 56)) & 255);
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e3);
            }
        }

        public final void writeInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeInt32NoTag(i4);
        }

        public final void writeInt32NoTag(int i3) throws IOException {
            if (i3 >= 0) {
                writeUInt32NoTag(i3);
            } else {
                writeUInt64NoTag((long) i3);
            }
        }

        public final void writeLazy(byte[] bArr, int i3, int i4) throws IOException {
            write(bArr, i3, i4);
        }

        public final void writeMessage(int i3, MessageLite messageLite) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite);
        }

        public final void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo((CodedOutputStream) this);
        }

        public final void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public final void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            Java8Compatibility.clear(duplicate);
            write(duplicate);
        }

        public final void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public final void writeString(int i3, String str) throws IOException {
            writeTag(i3, 2);
            writeStringNoTag(str);
        }

        public final void writeStringNoTag(String str) throws IOException {
            int i3 = this.position;
            try {
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int i4 = i3 + computeUInt32SizeNoTag2;
                    this.position = i4;
                    int encode = Utf8.encode(str, this.buffer, i4, spaceLeft());
                    this.position = i3;
                    writeUInt32NoTag((encode - i3) - computeUInt32SizeNoTag2);
                    this.position = encode;
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(str));
                this.position = Utf8.encode(str, this.buffer, this.position, spaceLeft());
            } catch (Utf8.UnpairedSurrogateException e3) {
                this.position = i3;
                inefficientWriteStringNoTag(str, e3);
            } catch (IndexOutOfBoundsException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            }
        }

        public final void writeTag(int i3, int i4) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public final void writeUInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeUInt32NoTag(i4);
        }

        public final void writeUInt32NoTag(int i3) throws IOException {
            while ((i3 & -128) != 0) {
                byte[] bArr = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr[i4] = (byte) ((i3 & 127) | 128);
                i3 >>>= 7;
            }
            try {
                byte[] bArr2 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr2[i5] = (byte) i3;
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e3);
            }
        }

        public final void writeUInt64(int i3, long j2) throws IOException {
            writeTag(i3, 0);
            writeUInt64NoTag(j2);
        }

        public final void writeUInt64NoTag(long j2) throws IOException {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((j2 & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr[i3] = (byte) ((((int) j2) & 127) | 128);
                    j2 >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr2[i4] = (byte) ((int) j2);
                } catch (IndexOutOfBoundsException e3) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e3);
                }
            } else {
                while ((j2 & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i5 = this.position;
                    this.position = i5 + 1;
                    UnsafeUtil.putByte(bArr3, (long) i5, (byte) ((((int) j2) & 127) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                UnsafeUtil.putByte(bArr4, (long) i6, (byte) ((int) j2));
            }
        }

        public final void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException {
            writeTag(i3, 2);
            writeByteArrayNoTag(bArr, i4, i5);
        }

        public final void writeLazy(ByteBuffer byteBuffer) throws IOException {
            write(byteBuffer);
        }

        public final void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public final void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public final void write(byte[] bArr, int i3, int i4) throws IOException {
            try {
                System.arraycopy(bArr, i3, this.buffer, this.position, i4);
                this.position += i4;
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i4)}), e3);
            }
        }

        public final void write(ByteBuffer byteBuffer) throws IOException {
            int remaining = byteBuffer.remaining();
            try {
                byteBuffer.get(this.buffer, this.position, remaining);
                this.position += remaining;
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(remaining)}), e3);
            }
        }
    }

    public static final class ByteOutputEncoder extends AbstractBufferedEncoder {
        private final ByteOutput out;

        public ByteOutputEncoder(ByteOutput byteOutput, int i3) {
            super(i3);
            if (byteOutput != null) {
                this.out = byteOutput;
                return;
            }
            throw new NullPointerException("out");
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        private void flushIfNotAvailable(int i3) throws IOException {
            if (this.limit - this.position < i3) {
                doFlush();
            }
        }

        public void flush() throws IOException {
            if (this.position > 0) {
                doFlush();
            }
        }

        public void write(byte b3) throws IOException {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(b3);
        }

        public void writeBool(int i3, boolean z2) throws IOException {
            flushIfNotAvailable(11);
            bufferTag(i3, 0);
            buffer(z2 ? (byte) 1 : 0);
        }

        public void writeByteArray(int i3, byte[] bArr) throws IOException {
            writeByteArray(i3, bArr, 0, bArr.length);
        }

        public void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException {
            writeUInt32NoTag(i4);
            write(bArr, i3, i4);
        }

        public void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(byteBuffer.capacity());
            writeRawBytes(byteBuffer);
        }

        public void writeBytes(int i3, ByteString byteString) throws IOException {
            writeTag(i3, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void writeFixed32(int i3, int i4) throws IOException {
            flushIfNotAvailable(14);
            bufferTag(i3, 5);
            bufferFixed32NoTag(i4);
        }

        public void writeFixed32NoTag(int i3) throws IOException {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(i3);
        }

        public void writeFixed64(int i3, long j2) throws IOException {
            flushIfNotAvailable(18);
            bufferTag(i3, 1);
            bufferFixed64NoTag(j2);
        }

        public void writeFixed64NoTag(long j2) throws IOException {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(j2);
        }

        public void writeInt32(int i3, int i4) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferInt32NoTag(i4);
        }

        public void writeInt32NoTag(int i3) throws IOException {
            if (i3 >= 0) {
                writeUInt32NoTag(i3);
            } else {
                writeUInt64NoTag((long) i3);
            }
        }

        public void writeLazy(byte[] bArr, int i3, int i4) throws IOException {
            flush();
            this.out.writeLazy(bArr, i3, i4);
            this.totalBytesWritten += i4;
        }

        public void writeMessage(int i3, MessageLite messageLite) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite);
        }

        public void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo((CodedOutputStream) this);
        }

        public void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            Java8Compatibility.clear(duplicate);
            write(duplicate);
        }

        public void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public void writeString(int i3, String str) throws IOException {
            writeTag(i3, 2);
            writeStringNoTag(str);
        }

        public void writeStringNoTag(String str) throws IOException {
            int length = str.length() * 3;
            int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(length);
            int i3 = computeUInt32SizeNoTag + length;
            int i4 = this.limit;
            if (i3 > i4) {
                byte[] bArr = new byte[length];
                int encode = Utf8.encode(str, bArr, 0, length);
                writeUInt32NoTag(encode);
                writeLazy(bArr, 0, encode);
                return;
            }
            if (i3 > i4 - this.position) {
                doFlush();
            }
            int i5 = this.position;
            try {
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int i6 = i5 + computeUInt32SizeNoTag2;
                    this.position = i6;
                    int encode2 = Utf8.encode(str, this.buffer, i6, this.limit - i6);
                    this.position = i5;
                    int i7 = (encode2 - i5) - computeUInt32SizeNoTag2;
                    bufferUInt32NoTag(i7);
                    this.position = encode2;
                    this.totalBytesWritten += i7;
                    return;
                }
                int encodedLength = Utf8.encodedLength(str);
                bufferUInt32NoTag(encodedLength);
                this.position = Utf8.encode(str, this.buffer, this.position, encodedLength);
                this.totalBytesWritten += encodedLength;
            } catch (Utf8.UnpairedSurrogateException e3) {
                this.totalBytesWritten -= this.position - i5;
                this.position = i5;
                inefficientWriteStringNoTag(str, e3);
            } catch (IndexOutOfBoundsException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            }
        }

        public void writeTag(int i3, int i4) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferUInt32NoTag(i4);
        }

        public void writeUInt32NoTag(int i3) throws IOException {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(i3);
        }

        public void writeUInt64(int i3, long j2) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferUInt64NoTag(j2);
        }

        public void writeUInt64NoTag(long j2) throws IOException {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(j2);
        }

        public void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException {
            writeTag(i3, 2);
            writeByteArrayNoTag(bArr, i4, i5);
        }

        public void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite, schema);
        }

        public void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public void write(byte[] bArr, int i3, int i4) throws IOException {
            flush();
            this.out.write(bArr, i3, i4);
            this.totalBytesWritten += i4;
        }

        public void writeLazy(ByteBuffer byteBuffer) throws IOException {
            flush();
            int remaining = byteBuffer.remaining();
            this.out.writeLazy(byteBuffer);
            this.totalBytesWritten += remaining;
        }

        public void write(ByteBuffer byteBuffer) throws IOException {
            flush();
            int remaining = byteBuffer.remaining();
            this.out.write(byteBuffer);
            this.totalBytesWritten += remaining;
        }
    }

    public static final class HeapNioEncoder extends ArrayEncoder {
        private final ByteBuffer byteBuffer;
        private int initialPosition;

        public HeapNioEncoder(ByteBuffer byteBuffer2) {
            super(byteBuffer2.array(), byteBuffer2.position() + byteBuffer2.arrayOffset(), byteBuffer2.remaining());
            this.byteBuffer = byteBuffer2;
            this.initialPosition = byteBuffer2.position();
        }

        public void flush() {
            Java8Compatibility.position(this.byteBuffer, this.initialPosition + getTotalBytesWritten());
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
        private static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException() {
            super(MESSAGE);
        }

        public OutOfSpaceException(String str) {
            super(c.a("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str));
        }

        public OutOfSpaceException(Throwable th) {
            super(MESSAGE, th);
        }

        public OutOfSpaceException(String str, Throwable th) {
            super(c.a("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str), th);
        }
    }

    public static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        public OutputStreamEncoder(OutputStream outputStream, int i3) {
            super(i3);
            if (outputStream != null) {
                this.out = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        private void flushIfNotAvailable(int i3) throws IOException {
            if (this.limit - this.position < i3) {
                doFlush();
            }
        }

        public void flush() throws IOException {
            if (this.position > 0) {
                doFlush();
            }
        }

        public void write(byte b3) throws IOException {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(b3);
        }

        public void writeBool(int i3, boolean z2) throws IOException {
            flushIfNotAvailable(11);
            bufferTag(i3, 0);
            buffer(z2 ? (byte) 1 : 0);
        }

        public void writeByteArray(int i3, byte[] bArr) throws IOException {
            writeByteArray(i3, bArr, 0, bArr.length);
        }

        public void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException {
            writeUInt32NoTag(i4);
            write(bArr, i3, i4);
        }

        public void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(byteBuffer.capacity());
            writeRawBytes(byteBuffer);
        }

        public void writeBytes(int i3, ByteString byteString) throws IOException {
            writeTag(i3, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void writeFixed32(int i3, int i4) throws IOException {
            flushIfNotAvailable(14);
            bufferTag(i3, 5);
            bufferFixed32NoTag(i4);
        }

        public void writeFixed32NoTag(int i3) throws IOException {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(i3);
        }

        public void writeFixed64(int i3, long j2) throws IOException {
            flushIfNotAvailable(18);
            bufferTag(i3, 1);
            bufferFixed64NoTag(j2);
        }

        public void writeFixed64NoTag(long j2) throws IOException {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(j2);
        }

        public void writeInt32(int i3, int i4) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferInt32NoTag(i4);
        }

        public void writeInt32NoTag(int i3) throws IOException {
            if (i3 >= 0) {
                writeUInt32NoTag(i3);
            } else {
                writeUInt64NoTag((long) i3);
            }
        }

        public void writeLazy(byte[] bArr, int i3, int i4) throws IOException {
            write(bArr, i3, i4);
        }

        public void writeMessage(int i3, MessageLite messageLite) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite);
        }

        public void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo((CodedOutputStream) this);
        }

        public void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            Java8Compatibility.clear(duplicate);
            write(duplicate);
        }

        public void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public void writeString(int i3, String str) throws IOException {
            writeTag(i3, 2);
            writeStringNoTag(str);
        }

        public void writeStringNoTag(String str) throws IOException {
            int i3;
            int i4;
            try {
                int length = str.length() * 3;
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(length);
                int i5 = computeUInt32SizeNoTag + length;
                int i6 = this.limit;
                if (i5 > i6) {
                    byte[] bArr = new byte[length];
                    int encode = Utf8.encode(str, bArr, 0, length);
                    writeUInt32NoTag(encode);
                    writeLazy(bArr, 0, encode);
                    return;
                }
                if (i5 > i6 - this.position) {
                    doFlush();
                }
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                i3 = this.position;
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int i7 = i3 + computeUInt32SizeNoTag2;
                    this.position = i7;
                    int encode2 = Utf8.encode(str, this.buffer, i7, this.limit - i7);
                    this.position = i3;
                    i4 = (encode2 - i3) - computeUInt32SizeNoTag2;
                    bufferUInt32NoTag(i4);
                    this.position = encode2;
                } else {
                    i4 = Utf8.encodedLength(str);
                    bufferUInt32NoTag(i4);
                    this.position = Utf8.encode(str, this.buffer, this.position, i4);
                }
                this.totalBytesWritten += i4;
            } catch (Utf8.UnpairedSurrogateException e3) {
                this.totalBytesWritten -= this.position - i3;
                this.position = i3;
                throw e3;
            } catch (ArrayIndexOutOfBoundsException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            } catch (Utf8.UnpairedSurrogateException e5) {
                inefficientWriteStringNoTag(str, e5);
            }
        }

        public void writeTag(int i3, int i4) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferUInt32NoTag(i4);
        }

        public void writeUInt32NoTag(int i3) throws IOException {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(i3);
        }

        public void writeUInt64(int i3, long j2) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i3, 0);
            bufferUInt64NoTag(j2);
        }

        public void writeUInt64NoTag(long j2) throws IOException {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(j2);
        }

        public void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException {
            writeTag(i3, 2);
            writeByteArrayNoTag(bArr, i4, i5);
        }

        public void writeLazy(ByteBuffer byteBuffer) throws IOException {
            write(byteBuffer);
        }

        public void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite, schema);
        }

        public void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public void write(byte[] bArr, int i3, int i4) throws IOException {
            int i5 = this.limit;
            int i6 = this.position;
            if (i5 - i6 >= i4) {
                System.arraycopy(bArr, i3, this.buffer, i6, i4);
                this.position += i4;
                this.totalBytesWritten += i4;
                return;
            }
            int i7 = i5 - i6;
            System.arraycopy(bArr, i3, this.buffer, i6, i7);
            int i8 = i3 + i7;
            int i9 = i4 - i7;
            this.position = this.limit;
            this.totalBytesWritten += i7;
            doFlush();
            if (i9 <= this.limit) {
                System.arraycopy(bArr, i8, this.buffer, 0, i9);
                this.position = i9;
            } else {
                this.out.write(bArr, i8, i9);
            }
            this.totalBytesWritten += i9;
        }

        public void write(ByteBuffer byteBuffer) throws IOException {
            int remaining = byteBuffer.remaining();
            int i3 = this.limit;
            int i4 = this.position;
            if (i3 - i4 >= remaining) {
                byteBuffer.get(this.buffer, i4, remaining);
                this.position += remaining;
                this.totalBytesWritten += remaining;
                return;
            }
            int i5 = i3 - i4;
            byteBuffer.get(this.buffer, i4, i5);
            int i6 = remaining - i5;
            this.position = this.limit;
            this.totalBytesWritten += i5;
            doFlush();
            while (true) {
                int i7 = this.limit;
                if (i6 > i7) {
                    byteBuffer.get(this.buffer, 0, i7);
                    this.out.write(this.buffer, 0, this.limit);
                    int i8 = this.limit;
                    i6 -= i8;
                    this.totalBytesWritten += i8;
                } else {
                    byteBuffer.get(this.buffer, 0, i6);
                    this.position = i6;
                    this.totalBytesWritten += i6;
                    return;
                }
            }
        }
    }

    public static final class SafeDirectNioEncoder extends CodedOutputStream {
        private final ByteBuffer buffer;
        private final int initialPosition;
        private final ByteBuffer originalBuffer;

        public SafeDirectNioEncoder(ByteBuffer byteBuffer) {
            super();
            this.originalBuffer = byteBuffer;
            this.buffer = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.initialPosition = byteBuffer.position();
        }

        private void encode(String str) throws IOException {
            try {
                Utf8.encodeUtf8(str, this.buffer);
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void flush() {
            Java8Compatibility.position(this.originalBuffer, this.buffer.position());
        }

        public int getTotalBytesWritten() {
            return this.buffer.position() - this.initialPosition;
        }

        public int spaceLeft() {
            return this.buffer.remaining();
        }

        public void write(byte b3) throws IOException {
            try {
                this.buffer.put(b3);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void writeBool(int i3, boolean z2) throws IOException {
            writeTag(i3, 0);
            write(z2 ? (byte) 1 : 0);
        }

        public void writeByteArray(int i3, byte[] bArr) throws IOException {
            writeByteArray(i3, bArr, 0, bArr.length);
        }

        public void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException {
            writeUInt32NoTag(i4);
            write(bArr, i3, i4);
        }

        public void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(byteBuffer.capacity());
            writeRawBytes(byteBuffer);
        }

        public void writeBytes(int i3, ByteString byteString) throws IOException {
            writeTag(i3, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void writeFixed32(int i3, int i4) throws IOException {
            writeTag(i3, 5);
            writeFixed32NoTag(i4);
        }

        public void writeFixed32NoTag(int i3) throws IOException {
            try {
                this.buffer.putInt(i3);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void writeFixed64(int i3, long j2) throws IOException {
            writeTag(i3, 1);
            writeFixed64NoTag(j2);
        }

        public void writeFixed64NoTag(long j2) throws IOException {
            try {
                this.buffer.putLong(j2);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void writeInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeInt32NoTag(i4);
        }

        public void writeInt32NoTag(int i3) throws IOException {
            if (i3 >= 0) {
                writeUInt32NoTag(i3);
            } else {
                writeUInt64NoTag((long) i3);
            }
        }

        public void writeLazy(byte[] bArr, int i3, int i4) throws IOException {
            write(bArr, i3, i4);
        }

        public void writeMessage(int i3, MessageLite messageLite) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite);
        }

        public void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo((CodedOutputStream) this);
        }

        public void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            Java8Compatibility.clear(duplicate);
            write(duplicate);
        }

        public void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public void writeString(int i3, String str) throws IOException {
            writeTag(i3, 2);
            writeStringNoTag(str);
        }

        public void writeStringNoTag(String str) throws IOException {
            int position = this.buffer.position();
            try {
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int position2 = this.buffer.position() + computeUInt32SizeNoTag2;
                    Java8Compatibility.position(this.buffer, position2);
                    encode(str);
                    int position3 = this.buffer.position();
                    Java8Compatibility.position(this.buffer, position);
                    writeUInt32NoTag(position3 - position2);
                    Java8Compatibility.position(this.buffer, position3);
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(str));
                encode(str);
            } catch (Utf8.UnpairedSurrogateException e3) {
                Java8Compatibility.position(this.buffer, position);
                inefficientWriteStringNoTag(str, e3);
            } catch (IllegalArgumentException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            }
        }

        public void writeTag(int i3, int i4) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeUInt32NoTag(i4);
        }

        public void writeUInt32NoTag(int i3) throws IOException {
            while ((i3 & -128) != 0) {
                this.buffer.put((byte) ((i3 & 127) | 128));
                i3 >>>= 7;
            }
            try {
                this.buffer.put((byte) i3);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void writeUInt64(int i3, long j2) throws IOException {
            writeTag(i3, 0);
            writeUInt64NoTag(j2);
        }

        public void writeUInt64NoTag(long j2) throws IOException {
            while ((-128 & j2) != 0) {
                this.buffer.put((byte) ((((int) j2) & 127) | 128));
                j2 >>>= 7;
            }
            try {
                this.buffer.put((byte) ((int) j2));
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }

        public void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException {
            writeTag(i3, 2);
            writeByteArrayNoTag(bArr, i4, i5);
        }

        public void writeLazy(ByteBuffer byteBuffer) throws IOException {
            write(byteBuffer);
        }

        public void write(byte[] bArr, int i3, int i4) throws IOException {
            try {
                this.buffer.put(bArr, i3, i4);
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            } catch (BufferOverflowException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            }
        }

        public void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite, schema);
        }

        public void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public void write(ByteBuffer byteBuffer) throws IOException {
            try {
                this.buffer.put(byteBuffer);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }
    }

    public static final class UnsafeDirectNioEncoder extends CodedOutputStream {
        private final long address;
        private final ByteBuffer buffer;
        private final long initialPosition;
        private final long limit;
        private final long oneVarintLimit;
        private final ByteBuffer originalBuffer;
        private long position;

        public UnsafeDirectNioEncoder(ByteBuffer byteBuffer) {
            super();
            this.originalBuffer = byteBuffer;
            this.buffer = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            this.address = addressOffset;
            long position2 = ((long) byteBuffer.position()) + addressOffset;
            this.initialPosition = position2;
            long limit2 = addressOffset + ((long) byteBuffer.limit());
            this.limit = limit2;
            this.oneVarintLimit = limit2 - 10;
            this.position = position2;
        }

        private int bufferPos(long j2) {
            return (int) (j2 - this.address);
        }

        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private void repositionBuffer(long j2) {
            Java8Compatibility.position(this.buffer, bufferPos(j2));
        }

        public void flush() {
            Java8Compatibility.position(this.originalBuffer, bufferPos(this.position));
        }

        public int getTotalBytesWritten() {
            return (int) (this.position - this.initialPosition);
        }

        public int spaceLeft() {
            return (int) (this.limit - this.position);
        }

        public void write(byte b3) throws IOException {
            long j2 = this.position;
            if (j2 < this.limit) {
                this.position = 1 + j2;
                UnsafeUtil.putByte(j2, b3);
                return;
            }
            throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), 1}));
        }

        public void writeBool(int i3, boolean z2) throws IOException {
            writeTag(i3, 0);
            write(z2 ? (byte) 1 : 0);
        }

        public void writeByteArray(int i3, byte[] bArr) throws IOException {
            writeByteArray(i3, bArr, 0, bArr.length);
        }

        public void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException {
            writeUInt32NoTag(i4);
            write(bArr, i3, i4);
        }

        public void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException {
            writeTag(i3, 2);
            writeUInt32NoTag(byteBuffer.capacity());
            writeRawBytes(byteBuffer);
        }

        public void writeBytes(int i3, ByteString byteString) throws IOException {
            writeTag(i3, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void writeFixed32(int i3, int i4) throws IOException {
            writeTag(i3, 5);
            writeFixed32NoTag(i4);
        }

        public void writeFixed32NoTag(int i3) throws IOException {
            this.buffer.putInt(bufferPos(this.position), i3);
            this.position += 4;
        }

        public void writeFixed64(int i3, long j2) throws IOException {
            writeTag(i3, 1);
            writeFixed64NoTag(j2);
        }

        public void writeFixed64NoTag(long j2) throws IOException {
            this.buffer.putLong(bufferPos(this.position), j2);
            this.position += 8;
        }

        public void writeInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeInt32NoTag(i4);
        }

        public void writeInt32NoTag(int i3) throws IOException {
            if (i3 >= 0) {
                writeUInt32NoTag(i3);
            } else {
                writeUInt64NoTag((long) i3);
            }
        }

        public void writeLazy(byte[] bArr, int i3, int i4) throws IOException {
            write(bArr, i3, i4);
        }

        public void writeMessage(int i3, MessageLite messageLite) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite);
        }

        public void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo((CodedOutputStream) this);
        }

        public void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            Java8Compatibility.clear(duplicate);
            write(duplicate);
        }

        public void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, i3);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public void writeString(int i3, String str) throws IOException {
            writeTag(i3, 2);
            writeStringNoTag(str);
        }

        public void writeStringNoTag(String str) throws IOException {
            long j2 = this.position;
            try {
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int bufferPos = bufferPos(this.position) + computeUInt32SizeNoTag2;
                    Java8Compatibility.position(this.buffer, bufferPos);
                    Utf8.encodeUtf8(str, this.buffer);
                    int position2 = this.buffer.position() - bufferPos;
                    writeUInt32NoTag(position2);
                    this.position += (long) position2;
                    return;
                }
                int encodedLength = Utf8.encodedLength(str);
                writeUInt32NoTag(encodedLength);
                repositionBuffer(this.position);
                Utf8.encodeUtf8(str, this.buffer);
                this.position += (long) encodedLength;
            } catch (Utf8.UnpairedSurrogateException e3) {
                this.position = j2;
                repositionBuffer(j2);
                inefficientWriteStringNoTag(str, e3);
            } catch (IllegalArgumentException e4) {
                throw new OutOfSpaceException((Throwable) e4);
            } catch (IndexOutOfBoundsException e5) {
                throw new OutOfSpaceException((Throwable) e5);
            }
        }

        public void writeTag(int i3, int i4) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) throws IOException {
            writeTag(i3, 0);
            writeUInt32NoTag(i4);
        }

        public void writeUInt32NoTag(int i3) throws IOException {
            if (this.position <= this.oneVarintLimit) {
                while ((i3 & -128) != 0) {
                    long j2 = this.position;
                    this.position = j2 + 1;
                    UnsafeUtil.putByte(j2, (byte) ((i3 & 127) | 128));
                    i3 >>>= 7;
                }
                long j3 = this.position;
                this.position = 1 + j3;
                UnsafeUtil.putByte(j3, (byte) i3);
                return;
            }
            while (true) {
                long j4 = this.position;
                if (j4 >= this.limit) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), 1}));
                } else if ((i3 & -128) == 0) {
                    this.position = 1 + j4;
                    UnsafeUtil.putByte(j4, (byte) i3);
                    return;
                } else {
                    this.position = j4 + 1;
                    UnsafeUtil.putByte(j4, (byte) ((i3 & 127) | 128));
                    i3 >>>= 7;
                }
            }
        }

        public void writeUInt64(int i3, long j2) throws IOException {
            writeTag(i3, 0);
            writeUInt64NoTag(j2);
        }

        public void writeUInt64NoTag(long j2) throws IOException {
            if (this.position <= this.oneVarintLimit) {
                while ((j2 & -128) != 0) {
                    long j3 = this.position;
                    this.position = j3 + 1;
                    UnsafeUtil.putByte(j3, (byte) ((((int) j2) & 127) | 128));
                    j2 >>>= 7;
                }
                long j4 = this.position;
                this.position = 1 + j4;
                UnsafeUtil.putByte(j4, (byte) ((int) j2));
                return;
            }
            while (true) {
                long j5 = this.position;
                if (j5 >= this.limit) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), 1}));
                } else if ((j2 & -128) == 0) {
                    this.position = 1 + j5;
                    UnsafeUtil.putByte(j5, (byte) ((int) j2));
                    return;
                } else {
                    this.position = j5 + 1;
                    UnsafeUtil.putByte(j5, (byte) ((((int) j2) & 127) | 128));
                    j2 >>>= 7;
                }
            }
        }

        public void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException {
            writeTag(i3, 2);
            writeByteArrayNoTag(bArr, i4, i5);
        }

        public void writeLazy(ByteBuffer byteBuffer) throws IOException {
            write(byteBuffer);
        }

        public void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException {
            writeTag(i3, 2);
            writeMessageNoTag(messageLite, schema);
        }

        public void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public void write(byte[] bArr, int i3, int i4) throws IOException {
            if (bArr != null && i3 >= 0 && i4 >= 0 && bArr.length - i4 >= i3) {
                long j2 = (long) i4;
                long j3 = this.position;
                if (this.limit - j2 >= j3) {
                    UnsafeUtil.copyMemory(bArr, (long) i3, j3, j2);
                    this.position += j2;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(i4)}));
        }

        public void write(ByteBuffer byteBuffer) throws IOException {
            try {
                int remaining = byteBuffer.remaining();
                repositionBuffer(this.position);
                this.buffer.put(byteBuffer);
                this.position += (long) remaining;
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException((Throwable) e3);
            }
        }
    }

    public static int computeBoolSize(int i3, boolean z2) {
        return computeBoolSizeNoTag(z2) + computeTagSize(i3);
    }

    public static int computeBoolSizeNoTag(boolean z2) {
        return 1;
    }

    public static int computeByteArraySize(int i3, byte[] bArr) {
        return computeByteArraySizeNoTag(bArr) + computeTagSize(i3);
    }

    public static int computeByteArraySizeNoTag(byte[] bArr) {
        return computeLengthDelimitedFieldSize(bArr.length);
    }

    public static int computeByteBufferSize(int i3, ByteBuffer byteBuffer) {
        return computeByteBufferSizeNoTag(byteBuffer) + computeTagSize(i3);
    }

    public static int computeByteBufferSizeNoTag(ByteBuffer byteBuffer) {
        return computeLengthDelimitedFieldSize(byteBuffer.capacity());
    }

    public static int computeBytesSize(int i3, ByteString byteString) {
        return computeBytesSizeNoTag(byteString) + computeTagSize(i3);
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeLengthDelimitedFieldSize(byteString.size());
    }

    public static int computeDoubleSize(int i3, double d2) {
        return computeDoubleSizeNoTag(d2) + computeTagSize(i3);
    }

    public static int computeDoubleSizeNoTag(double d2) {
        return 8;
    }

    public static int computeEnumSize(int i3, int i4) {
        return computeEnumSizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeEnumSizeNoTag(int i3) {
        return computeInt32SizeNoTag(i3);
    }

    public static int computeFixed32Size(int i3, int i4) {
        return computeFixed32SizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeFixed32SizeNoTag(int i3) {
        return 4;
    }

    public static int computeFixed64Size(int i3, long j2) {
        return computeFixed64SizeNoTag(j2) + computeTagSize(i3);
    }

    public static int computeFixed64SizeNoTag(long j2) {
        return 8;
    }

    public static int computeFloatSize(int i3, float f2) {
        return computeFloatSizeNoTag(f2) + computeTagSize(i3);
    }

    public static int computeFloatSizeNoTag(float f2) {
        return 4;
    }

    @Deprecated
    public static int computeGroupSize(int i3, MessageLite messageLite) {
        return messageLite.getSerializedSize() + (computeTagSize(i3) * 2);
    }

    @InlineMe(replacement = "value.getSerializedSize()")
    @Deprecated
    public static int computeGroupSizeNoTag(MessageLite messageLite) {
        return messageLite.getSerializedSize();
    }

    public static int computeInt32Size(int i3, int i4) {
        return computeInt32SizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeInt32SizeNoTag(int i3) {
        if (i3 >= 0) {
            return computeUInt32SizeNoTag(i3);
        }
        return 10;
    }

    public static int computeInt64Size(int i3, long j2) {
        return computeInt64SizeNoTag(j2) + computeTagSize(i3);
    }

    public static int computeInt64SizeNoTag(long j2) {
        return computeUInt64SizeNoTag(j2);
    }

    public static int computeLazyFieldMessageSetExtensionSize(int i3, LazyFieldLite lazyFieldLite) {
        int computeUInt32Size = computeUInt32Size(2, i3);
        return computeLazyFieldSize(3, lazyFieldLite) + computeUInt32Size + (computeTagSize(1) * 2);
    }

    public static int computeLazyFieldSize(int i3, LazyFieldLite lazyFieldLite) {
        return computeLazyFieldSizeNoTag(lazyFieldLite) + computeTagSize(i3);
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite) {
        return computeLengthDelimitedFieldSize(lazyFieldLite.getSerializedSize());
    }

    public static int computeLengthDelimitedFieldSize(int i3) {
        return computeUInt32SizeNoTag(i3) + i3;
    }

    public static int computeMessageSetExtensionSize(int i3, MessageLite messageLite) {
        int computeUInt32Size = computeUInt32Size(2, i3);
        return computeMessageSize(3, messageLite) + computeUInt32Size + (computeTagSize(1) * 2);
    }

    public static int computeMessageSize(int i3, MessageLite messageLite) {
        return computeMessageSizeNoTag(messageLite) + computeTagSize(i3);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        return computeLengthDelimitedFieldSize(messageLite.getSerializedSize());
    }

    public static int computePreferredBufferSize(int i3) {
        if (i3 > 4096) {
            return 4096;
        }
        return i3;
    }

    public static int computeRawMessageSetExtensionSize(int i3, ByteString byteString) {
        int computeUInt32Size = computeUInt32Size(2, i3);
        return computeBytesSize(3, byteString) + computeUInt32Size + (computeTagSize(1) * 2);
    }

    @InlineMe(imports = {"com.google.protobuf.CodedOutputStream"}, replacement = "CodedOutputStream.computeUInt32SizeNoTag(value)")
    @Deprecated
    public static int computeRawVarint32Size(int i3) {
        return computeUInt32SizeNoTag(i3);
    }

    @InlineMe(imports = {"com.google.protobuf.CodedOutputStream"}, replacement = "CodedOutputStream.computeUInt64SizeNoTag(value)")
    @Deprecated
    public static int computeRawVarint64Size(long j2) {
        return computeUInt64SizeNoTag(j2);
    }

    public static int computeSFixed32Size(int i3, int i4) {
        return computeSFixed32SizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeSFixed32SizeNoTag(int i3) {
        return 4;
    }

    public static int computeSFixed64Size(int i3, long j2) {
        return computeSFixed64SizeNoTag(j2) + computeTagSize(i3);
    }

    public static int computeSFixed64SizeNoTag(long j2) {
        return 8;
    }

    public static int computeSInt32Size(int i3, int i4) {
        return computeSInt32SizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeSInt32SizeNoTag(int i3) {
        return computeUInt32SizeNoTag(encodeZigZag32(i3));
    }

    public static int computeSInt64Size(int i3, long j2) {
        return computeSInt64SizeNoTag(j2) + computeTagSize(i3);
    }

    public static int computeSInt64SizeNoTag(long j2) {
        return computeUInt64SizeNoTag(encodeZigZag64(j2));
    }

    public static int computeStringSize(int i3, String str) {
        return computeStringSizeNoTag(str) + computeTagSize(i3);
    }

    public static int computeStringSizeNoTag(String str) {
        int i3;
        try {
            i3 = Utf8.encodedLength(str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i3 = str.getBytes(Internal.UTF_8).length;
        }
        return computeLengthDelimitedFieldSize(i3);
    }

    public static int computeTagSize(int i3) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(i3, 0));
    }

    public static int computeUInt32Size(int i3, int i4) {
        return computeUInt32SizeNoTag(i4) + computeTagSize(i3);
    }

    public static int computeUInt32SizeNoTag(int i3) {
        if ((i3 & -128) == 0) {
            return 1;
        }
        if ((i3 & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i3) == 0) {
            return 3;
        }
        return (i3 & -268435456) == 0 ? 4 : 5;
    }

    public static int computeUInt64Size(int i3, long j2) {
        return computeUInt64SizeNoTag(j2) + computeTagSize(i3);
    }

    public static int computeUInt64SizeNoTag(long j2) {
        int i3;
        if ((-128 & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if ((-34359738368L & j2) != 0) {
            j2 >>>= 28;
            i3 = 6;
        } else {
            i3 = 2;
        }
        if ((-2097152 & j2) != 0) {
            i3 += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i3 + 1 : i3;
    }

    public static int encodeZigZag32(int i3) {
        return (i3 >> 31) ^ (i3 << 1);
    }

    public static long encodeZigZag64(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream) {
        return newInstance(outputStream, 4096);
    }

    public static CodedOutputStream newSafeInstance(ByteBuffer byteBuffer) {
        return new SafeDirectNioEncoder(byteBuffer);
    }

    public static CodedOutputStream newUnsafeInstance(ByteBuffer byteBuffer) {
        return new UnsafeDirectNioEncoder(byteBuffer);
    }

    public final void checkNoSpaceLeft() {
        if (spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void flush() throws IOException;

    public abstract int getTotalBytesWritten();

    public final void inefficientWriteStringNoTag(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) throws IOException {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.UTF_8);
        try {
            writeUInt32NoTag(bytes.length);
            writeLazy(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e3) {
            throw new OutOfSpaceException((Throwable) e3);
        }
    }

    public boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    public abstract int spaceLeft();

    public void useDeterministicSerialization() {
        this.serializationDeterministic = true;
    }

    public abstract void write(byte b3) throws IOException;

    public abstract void write(ByteBuffer byteBuffer) throws IOException;

    public abstract void write(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void writeBool(int i3, boolean z2) throws IOException;

    public final void writeBoolNoTag(boolean z2) throws IOException {
        write(z2 ? (byte) 1 : 0);
    }

    public abstract void writeByteArray(int i3, byte[] bArr) throws IOException;

    public abstract void writeByteArray(int i3, byte[] bArr, int i4, int i5) throws IOException;

    public final void writeByteArrayNoTag(byte[] bArr) throws IOException {
        writeByteArrayNoTag(bArr, 0, bArr.length);
    }

    public abstract void writeByteArrayNoTag(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void writeByteBuffer(int i3, ByteBuffer byteBuffer) throws IOException;

    public abstract void writeBytes(int i3, ByteString byteString) throws IOException;

    public abstract void writeBytesNoTag(ByteString byteString) throws IOException;

    public final void writeDouble(int i3, double d2) throws IOException {
        writeFixed64(i3, Double.doubleToRawLongBits(d2));
    }

    public final void writeDoubleNoTag(double d2) throws IOException {
        writeFixed64NoTag(Double.doubleToRawLongBits(d2));
    }

    public final void writeEnum(int i3, int i4) throws IOException {
        writeInt32(i3, i4);
    }

    public final void writeEnumNoTag(int i3) throws IOException {
        writeInt32NoTag(i3);
    }

    public abstract void writeFixed32(int i3, int i4) throws IOException;

    public abstract void writeFixed32NoTag(int i3) throws IOException;

    public abstract void writeFixed64(int i3, long j2) throws IOException;

    public abstract void writeFixed64NoTag(long j2) throws IOException;

    public final void writeFloat(int i3, float f2) throws IOException {
        writeFixed32(i3, Float.floatToRawIntBits(f2));
    }

    public final void writeFloatNoTag(float f2) throws IOException {
        writeFixed32NoTag(Float.floatToRawIntBits(f2));
    }

    @Deprecated
    public final void writeGroup(int i3, MessageLite messageLite) throws IOException {
        writeTag(i3, 3);
        writeGroupNoTag(messageLite);
        writeTag(i3, 4);
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite messageLite) throws IOException {
        messageLite.writeTo(this);
    }

    public abstract void writeInt32(int i3, int i4) throws IOException;

    public abstract void writeInt32NoTag(int i3) throws IOException;

    public final void writeInt64(int i3, long j2) throws IOException {
        writeUInt64(i3, j2);
    }

    public final void writeInt64NoTag(long j2) throws IOException {
        writeUInt64NoTag(j2);
    }

    public abstract void writeLazy(ByteBuffer byteBuffer) throws IOException;

    public abstract void writeLazy(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void writeMessage(int i3, MessageLite messageLite) throws IOException;

    public abstract void writeMessage(int i3, MessageLite messageLite, Schema schema) throws IOException;

    public abstract void writeMessageNoTag(MessageLite messageLite) throws IOException;

    public abstract void writeMessageNoTag(MessageLite messageLite, Schema schema) throws IOException;

    public abstract void writeMessageSetExtension(int i3, MessageLite messageLite) throws IOException;

    public final void writeRawByte(byte b3) throws IOException {
        write(b3);
    }

    public abstract void writeRawBytes(ByteBuffer byteBuffer) throws IOException;

    public final void writeRawBytes(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @InlineMe(replacement = "this.writeFixed32NoTag(value)")
    @Deprecated
    public final void writeRawLittleEndian32(int i3) throws IOException {
        writeFixed32NoTag(i3);
    }

    @InlineMe(replacement = "this.writeFixed64NoTag(value)")
    @Deprecated
    public final void writeRawLittleEndian64(long j2) throws IOException {
        writeFixed64NoTag(j2);
    }

    public abstract void writeRawMessageSetExtension(int i3, ByteString byteString) throws IOException;

    @InlineMe(replacement = "this.writeUInt32NoTag(value)")
    @Deprecated
    public final void writeRawVarint32(int i3) throws IOException {
        writeUInt32NoTag(i3);
    }

    @InlineMe(replacement = "this.writeUInt64NoTag(value)")
    @Deprecated
    public final void writeRawVarint64(long j2) throws IOException {
        writeUInt64NoTag(j2);
    }

    public final void writeSFixed32(int i3, int i4) throws IOException {
        writeFixed32(i3, i4);
    }

    public final void writeSFixed32NoTag(int i3) throws IOException {
        writeFixed32NoTag(i3);
    }

    public final void writeSFixed64(int i3, long j2) throws IOException {
        writeFixed64(i3, j2);
    }

    public final void writeSFixed64NoTag(long j2) throws IOException {
        writeFixed64NoTag(j2);
    }

    public final void writeSInt32(int i3, int i4) throws IOException {
        writeUInt32(i3, encodeZigZag32(i4));
    }

    public final void writeSInt32NoTag(int i3) throws IOException {
        writeUInt32NoTag(encodeZigZag32(i3));
    }

    public final void writeSInt64(int i3, long j2) throws IOException {
        writeUInt64(i3, encodeZigZag64(j2));
    }

    public final void writeSInt64NoTag(long j2) throws IOException {
        writeUInt64NoTag(encodeZigZag64(j2));
    }

    public abstract void writeString(int i3, String str) throws IOException;

    public abstract void writeStringNoTag(String str) throws IOException;

    public abstract void writeTag(int i3, int i4) throws IOException;

    public abstract void writeUInt32(int i3, int i4) throws IOException;

    public abstract void writeUInt32NoTag(int i3) throws IOException;

    public abstract void writeUInt64(int i3, long j2) throws IOException;

    public abstract void writeUInt64NoTag(long j2) throws IOException;

    private CodedOutputStream() {
    }

    @Deprecated
    public static int computeGroupSize(int i3, MessageLite messageLite, Schema schema) {
        return computeGroupSizeNoTag(messageLite, schema) + (computeTagSize(i3) * 2);
    }

    @Deprecated
    public static int computeGroupSizeNoTag(MessageLite messageLite, Schema schema) {
        return ((AbstractMessageLite) messageLite).getSerializedSize(schema);
    }

    public static int computeMessageSize(int i3, MessageLite messageLite, Schema schema) {
        return computeMessageSizeNoTag(messageLite, schema) + computeTagSize(i3);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite, Schema schema) {
        return computeLengthDelimitedFieldSize(((AbstractMessageLite) messageLite).getSerializedSize(schema));
    }

    public static CodedOutputStream newInstance(OutputStream outputStream, int i3) {
        return new OutputStreamEncoder(outputStream, i3);
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite messageLite, Schema schema) throws IOException {
        schema.writeTo(messageLite, this.wrapper);
    }

    public final void writeRawByte(int i3) throws IOException {
        write((byte) i3);
    }

    public final void writeRawBytes(byte[] bArr, int i3, int i4) throws IOException {
        write(bArr, i3, i4);
    }

    public static CodedOutputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public final void writeRawBytes(ByteString byteString) throws IOException {
        byteString.writeTo((ByteOutput) this);
    }

    public static CodedOutputStream newInstance(byte[] bArr, int i3, int i4) {
        return new ArrayEncoder(bArr, i3, i4);
    }

    @Deprecated
    public final void writeGroup(int i3, MessageLite messageLite, Schema schema) throws IOException {
        writeTag(i3, 3);
        writeGroupNoTag(messageLite, schema);
        writeTag(i3, 4);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new HeapNioEncoder(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        } else if (UnsafeDirectNioEncoder.isSupported()) {
            return newUnsafeInstance(byteBuffer);
        } else {
            return newSafeInstance(byteBuffer);
        }
    }

    @Deprecated
    public static CodedOutputStream newInstance(ByteBuffer byteBuffer, int i3) {
        return newInstance(byteBuffer);
    }

    public static CodedOutputStream newInstance(ByteOutput byteOutput, int i3) {
        if (i3 >= 0) {
            return new ByteOutputEncoder(byteOutput, i3);
        }
        throw new IllegalArgumentException("bufferSize must be positive");
    }
}
