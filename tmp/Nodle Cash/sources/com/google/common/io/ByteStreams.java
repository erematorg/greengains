package com.google.common.io;

import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.camera.core.impl.i;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_ARRAY_LEN = 2147483639;
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        public void write(int i3) {
        }

        public void write(byte[] bArr) {
            Preconditions.checkNotNull(bArr);
        }

        public void write(byte[] bArr, int i3, int i4) {
            Preconditions.checkNotNull(bArr);
            Preconditions.checkPositionIndexes(i3, i4 + i3, bArr.length);
        }
    };
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;

    private ByteStreams() {
    }

    private static byte[] combineBuffers(Queue<byte[]> queue, int i3) {
        if (queue.isEmpty()) {
            return new byte[0];
        }
        byte[] remove = queue.remove();
        if (remove.length == i3) {
            return remove;
        }
        int length = i3 - remove.length;
        byte[] copyOf = Arrays.copyOf(remove, i3);
        while (length > 0) {
            byte[] remove2 = queue.remove();
            int min = Math.min(length, remove2.length);
            System.arraycopy(remove2, 0, copyOf, i3 - length, min);
            length -= min;
        }
        return copyOf;
    }

    @CanIgnoreReturnValue
    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] createBuffer = createBuffer();
        long j2 = 0;
        while (true) {
            int read = inputStream.read(createBuffer);
            if (read == -1) {
                return j2;
            }
            outputStream.write(createBuffer, 0, read);
            j2 += (long) read;
        }
    }

    public static byte[] createBuffer() {
        return new byte[8192];
    }

    @CanIgnoreReturnValue
    public static long exhaust(InputStream inputStream) throws IOException {
        byte[] createBuffer = createBuffer();
        long j2 = 0;
        while (true) {
            long read = (long) inputStream.read(createBuffer);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
    }

    public static InputStream limit(InputStream inputStream, long j2) {
        return new LimitedInputStream(inputStream, j2);
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr) {
        return newDataInput(new ByteArrayInputStream(bArr));
    }

    public static ByteArrayDataOutput newDataOutput() {
        return newDataOutput(new ByteArrayOutputStream());
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    @CanIgnoreReturnValue
    public static int read(InputStream inputStream, byte[] bArr, int i3, int i4) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        if (i4 >= 0) {
            Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
            int i5 = 0;
            while (i5 < i4) {
                int read = inputStream.read(bArr, i3 + i5, i4 - i5);
                if (read == -1) {
                    break;
                }
                i5 += read;
            }
            return i5;
        }
        throw new IndexOutOfBoundsException(C0118y.c(i4, "len (", ") cannot be negative"));
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <T> T readBytes(InputStream inputStream, ByteProcessor<T> byteProcessor) throws IOException {
        int read;
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteProcessor);
        byte[] createBuffer = createBuffer();
        do {
            read = inputStream.read(createBuffer);
            if (read == -1 || !byteProcessor.processBytes(createBuffer, 0, read)) {
            }
            read = inputStream.read(createBuffer);
            break;
        } while (!byteProcessor.processBytes(createBuffer, 0, read));
        return byteProcessor.getResult();
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void skipFully(InputStream inputStream, long j2) throws IOException {
        long skipUpTo = skipUpTo(inputStream, j2);
        if (skipUpTo < j2) {
            throw new EOFException(a.k(j2, " bytes expected", androidx.compose.animation.core.a.u("reached end of stream after skipping ", skipUpTo, " bytes; ")));
        }
    }

    private static long skipSafely(InputStream inputStream, long j2) throws IOException {
        int available = inputStream.available();
        if (available == 0) {
            return 0;
        }
        return inputStream.skip(Math.min((long) available, j2));
    }

    public static long skipUpTo(InputStream inputStream, long j2) throws IOException {
        byte[] bArr = null;
        long j3 = 0;
        while (j3 < j2) {
            long j4 = j2 - j3;
            long skipSafely = skipSafely(inputStream, j4);
            if (skipSafely == 0) {
                int min = (int) Math.min(j4, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                if (bArr == null) {
                    bArr = new byte[min];
                }
                skipSafely = (long) inputStream.read(bArr, 0, min);
                if (skipSafely == -1) {
                    break;
                }
            }
            j3 += skipSafely;
        }
        return j3;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        return toByteArrayInternal(inputStream, new ArrayDeque(20), 0);
    }

    private static byte[] toByteArrayInternal(InputStream inputStream, Queue<byte[]> queue, int i3) throws IOException {
        int min = Math.min(8192, Math.max(128, Integer.highestOneBit(i3) * 2));
        while (i3 < MAX_ARRAY_LEN) {
            int min2 = Math.min(min, MAX_ARRAY_LEN - i3);
            byte[] bArr = new byte[min2];
            queue.add(bArr);
            int i4 = 0;
            while (i4 < min2) {
                int read = inputStream.read(bArr, i4, min2 - i4);
                if (read == -1) {
                    return combineBuffers(queue, i3);
                }
                i4 += read;
                i3 += read;
            }
            min = IntMath.saturatedMultiply(min, min < 4096 ? 4 : 2);
        }
        if (inputStream.read() == -1) {
            return combineBuffers(queue, MAX_ARRAY_LEN);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    public static class ByteArrayDataInputStream implements ByteArrayDataInput {
        final DataInput input;

        public ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e3) {
                throw new IllegalStateException(e3);
            } catch (IOException e4) {
                throw new AssertionError(e4);
            }
        }

        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public void readFully(byte[] bArr) {
            try {
                this.input.readFully(bArr);
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        @CheckForNull
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public int skipBytes(int i3) {
            try {
                return this.input.skipBytes(i3);
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public void readFully(byte[] bArr, int i3, int i4) {
            try {
                this.input.readFully(bArr, i3, i4);
            } catch (IOException e3) {
                throw new IllegalStateException(e3);
            }
        }
    }

    public static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        final ByteArrayOutputStream byteArrayOutputStream;
        final DataOutput output;

        public ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream2) {
            this.byteArrayOutputStream = byteArrayOutputStream2;
            this.output = new DataOutputStream(byteArrayOutputStream2);
        }

        public byte[] toByteArray() {
            return this.byteArrayOutputStream.toByteArray();
        }

        public void write(int i3) {
            try {
                this.output.write(i3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeBoolean(boolean z2) {
            try {
                this.output.writeBoolean(z2);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeByte(int i3) {
            try {
                this.output.writeByte(i3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeBytes(String str) {
            try {
                this.output.writeBytes(str);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeChar(int i3) {
            try {
                this.output.writeChar(i3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeChars(String str) {
            try {
                this.output.writeChars(str);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeDouble(double d2) {
            try {
                this.output.writeDouble(d2);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeFloat(float f2) {
            try {
                this.output.writeFloat(f2);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeInt(int i3) {
            try {
                this.output.writeInt(i3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeLong(long j2) {
            try {
                this.output.writeLong(j2);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeShort(int i3) {
            try {
                this.output.writeShort(i3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void writeUTF(String str) {
            try {
                this.output.writeUTF(str);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void write(byte[] bArr) {
            try {
                this.output.write(bArr);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }

        public void write(byte[] bArr, int i3, int i4) {
            try {
                this.output.write(bArr, i3, i4);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr, int i3) {
        Preconditions.checkPositionIndex(i3, bArr.length);
        return newDataInput(new ByteArrayInputStream(bArr, i3, bArr.length - i3));
    }

    public static ByteArrayDataOutput newDataOutput(int i3) {
        if (i3 >= 0) {
            return newDataOutput(new ByteArrayOutputStream(i3));
        }
        throw new IllegalArgumentException(A.a.k("Invalid size: ", i3));
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i3, int i4) throws IOException {
        int read = read(inputStream, bArr, i3, i4);
        if (read != i4) {
            throw new EOFException(i.a(read, i4, "reached end of stream after reading ", " bytes; ", " bytes expected"));
        }
    }

    public static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark = -1;

        public LimitedInputStream(InputStream inputStream, long j2) {
            super(inputStream);
            Preconditions.checkNotNull(inputStream);
            Preconditions.checkArgument(j2 >= 0, "limit must be non-negative");
            this.left = j2;
        }

        public int available() throws IOException {
            return (int) Math.min((long) this.in.available(), this.left);
        }

        public synchronized void mark(int i3) {
            this.in.mark(i3);
            this.mark = this.left;
        }

        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int read = this.in.read();
            if (read != -1) {
                this.left--;
            }
            return read;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.left = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        public long skip(long j2) throws IOException {
            long skip = this.in.skip(Math.min(j2, this.left));
            this.left -= skip;
            return skip;
        }

        public int read(byte[] bArr, int i3, int i4) throws IOException {
            long j2 = this.left;
            if (j2 == 0) {
                return -1;
            }
            int read = this.in.read(bArr, i3, (int) Math.min((long) i4, j2));
            if (read != -1) {
                this.left -= (long) read;
            }
            return read;
        }
    }

    public static byte[] toByteArray(InputStream inputStream, long j2) throws IOException {
        Preconditions.checkArgument(j2 >= 0, "expectedSize (%s) must be non-negative", j2);
        if (j2 <= 2147483639) {
            int i3 = (int) j2;
            byte[] bArr = new byte[i3];
            int i4 = i3;
            while (i4 > 0) {
                int i5 = i3 - i4;
                int read = inputStream.read(bArr, i5, i4);
                if (read == -1) {
                    return Arrays.copyOf(bArr, i5);
                }
                i4 -= read;
            }
            int read2 = inputStream.read();
            if (read2 == -1) {
                return bArr;
            }
            ArrayDeque arrayDeque = new ArrayDeque(22);
            arrayDeque.add(bArr);
            arrayDeque.add(new byte[]{(byte) read2});
            return toByteArrayInternal(inputStream, arrayDeque, i3 + 1);
        }
        throw new OutOfMemoryError(j2 + " bytes is too large to fit in a byte array");
    }

    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream((ByteArrayInputStream) Preconditions.checkNotNull(byteArrayInputStream));
    }

    @CanIgnoreReturnValue
    public static long copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        Preconditions.checkNotNull(readableByteChannel);
        Preconditions.checkNotNull(writableByteChannel);
        long j2 = 0;
        if (readableByteChannel instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) readableByteChannel;
            long position = fileChannel.position();
            long j3 = position;
            while (true) {
                long transferTo = fileChannel.transferTo(j3, 524288, writableByteChannel);
                j3 += transferTo;
                fileChannel.position(j3);
                if (transferTo <= 0 && j3 >= fileChannel.size()) {
                    return j3 - position;
                }
            }
        } else {
            ByteBuffer wrap = ByteBuffer.wrap(createBuffer());
            while (readableByteChannel.read(wrap) != -1) {
                Java8Compatibility.flip(wrap);
                while (wrap.hasRemaining()) {
                    j2 += (long) writableByteChannel.write(wrap);
                }
                Java8Compatibility.clear(wrap);
            }
            return j2;
        }
    }

    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayDataOutputStream((ByteArrayOutputStream) Preconditions.checkNotNull(byteArrayOutputStream));
    }
}
