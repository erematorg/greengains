package com.google.crypto.tink.shaded.protobuf;

import androidx.constraintlayout.core.state.b;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.Utf8;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@CheckReturnValue
abstract class BinaryWriter extends ByteOutput implements Writer {
    public static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final int MAP_KEY_NUMBER = 1;
    private static final int MAP_VALUE_NUMBER = 2;
    private final BufferAllocator alloc;
    final ArrayDeque<AllocatedBuffer> buffers;
    private final int chunkSize;
    int totalDoneBytes;

    /* renamed from: com.google.crypto.tink.shaded.protobuf.BinaryWriter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType[] r0 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.BYTES     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.BinaryWriter.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class SafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private int limitMinusOne;
        private int pos;

        public SafeDirectWriter(BufferAllocator bufferAllocator, int i3) {
            super(bufferAllocator, i3, (AnonymousClass1) null);
            nextBuffer();
        }

        private int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return this.pos + 1;
        }

        private void writeVarint32FiveBytes(int i3) {
            ByteBuffer byteBuffer = this.buffer;
            int i4 = this.pos;
            this.pos = i4 - 1;
            byteBuffer.put(i4, (byte) (i3 >>> 28));
            int i5 = this.pos;
            this.pos = i5 - 4;
            this.buffer.putInt(i5 - 3, (i3 & 127) | 128 | ((((i3 >>> 21) & 127) | 128) << 24) | ((((i3 >>> 14) & 127) | 128) << 16) | ((((i3 >>> 7) & 127) | 128) << 8));
        }

        private void writeVarint32FourBytes(int i3) {
            int i4 = this.pos;
            this.pos = i4 - 4;
            this.buffer.putInt(i4 - 3, (i3 & 127) | 128 | ((266338304 & i3) << 3) | (((2080768 & i3) | 2097152) << 2) | (((i3 & 16256) | 16384) << 1));
        }

        private void writeVarint32OneByte(int i3) {
            ByteBuffer byteBuffer = this.buffer;
            int i4 = this.pos;
            this.pos = i4 - 1;
            byteBuffer.put(i4, (byte) i3);
        }

        private void writeVarint32ThreeBytes(int i3) {
            int i4 = this.pos - 3;
            this.pos = i4;
            this.buffer.putInt(i4, (((i3 & 127) | 128) << 8) | ((2080768 & i3) << 10) | (((i3 & 16256) | 16384) << 9));
        }

        private void writeVarint32TwoBytes(int i3) {
            int i4 = this.pos;
            this.pos = i4 - 2;
            this.buffer.putShort(i4 - 1, (short) ((i3 & 127) | 128 | ((i3 & 16256) << 1)));
        }

        private void writeVarint64EightBytes(long j2) {
            int i3 = this.pos;
            this.pos = i3 - 8;
            this.buffer.putLong(i3 - 7, (j2 & 127) | 128 | ((71494644084506624L & j2) << 7) | (((558551906910208L & j2) | 562949953421312L) << 6) | (((4363686772736L & j2) | 4398046511104L) << 5) | (((34091302912L & j2) | 34359738368L) << 4) | (((266338304 & j2) | 268435456) << 3) | (((2080768 & j2) | 2097152) << 2) | (((16256 & j2) | 16384) << 1));
        }

        private void writeVarint64EightBytesWithSign(long j2) {
            int i3 = this.pos;
            this.pos = i3 - 8;
            this.buffer.putLong(i3 - 7, (j2 & 127) | 128 | (((71494644084506624L & j2) | 72057594037927936L) << 7) | (((558551906910208L & j2) | 562949953421312L) << 6) | (((4363686772736L & j2) | 4398046511104L) << 5) | (((34091302912L & j2) | 34359738368L) << 4) | (((266338304 & j2) | 268435456) << 3) | (((2080768 & j2) | 2097152) << 2) | (((16256 & j2) | 16384) << 1));
        }

        private void writeVarint64FiveBytes(long j2) {
            int i3 = this.pos;
            this.pos = i3 - 5;
            this.buffer.putLong(i3 - 7, (((j2 & 127) | 128) << 24) | ((34091302912L & j2) << 28) | (((266338304 & j2) | 268435456) << 27) | (((2080768 & j2) | 2097152) << 26) | (((16256 & j2) | 16384) << 25));
        }

        private void writeVarint64FourBytes(long j2) {
            writeVarint32FourBytes((int) j2);
        }

        private void writeVarint64NineBytes(long j2) {
            ByteBuffer byteBuffer = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer.put(i3, (byte) ((int) (j2 >>> 56)));
            writeVarint64EightBytesWithSign(j2 & 72057594037927935L);
        }

        private void writeVarint64OneByte(long j2) {
            writeVarint32OneByte((int) j2);
        }

        private void writeVarint64SevenBytes(long j2) {
            int i3 = this.pos - 7;
            this.pos = i3;
            this.buffer.putLong(i3, (((j2 & 127) | 128) << 8) | ((558551906910208L & j2) << 14) | (((4363686772736L & j2) | 4398046511104L) << 13) | (((34091302912L & j2) | 34359738368L) << 12) | (((266338304 & j2) | 268435456) << 11) | (((2080768 & j2) | 2097152) << 10) | (((16256 & j2) | 16384) << 9));
        }

        private void writeVarint64SixBytes(long j2) {
            int i3 = this.pos;
            this.pos = i3 - 6;
            this.buffer.putLong(i3 - 7, (((j2 & 127) | 128) << 16) | ((4363686772736L & j2) << 21) | (((34091302912L & j2) | 34359738368L) << 20) | (((266338304 & j2) | 268435456) << 19) | (((2080768 & j2) | 2097152) << 18) | (((16256 & j2) | 16384) << 17));
        }

        private void writeVarint64TenBytes(long j2) {
            ByteBuffer byteBuffer = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer.put(i3, (byte) ((int) (j2 >>> 63)));
            ByteBuffer byteBuffer2 = this.buffer;
            int i4 = this.pos;
            this.pos = i4 - 1;
            byteBuffer2.put(i4, (byte) ((int) (((j2 >>> 56) & 127) | 128)));
            writeVarint64EightBytesWithSign(j2 & 72057594037927935L);
        }

        private void writeVarint64ThreeBytes(long j2) {
            writeVarint32ThreeBytes((int) j2);
        }

        private void writeVarint64TwoBytes(long j2) {
            writeVarint32TwoBytes((int) j2);
        }

        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(this.pos + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        public void requireSpace(int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
        }

        public void write(byte b3) {
            ByteBuffer byteBuffer = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer.put(i3, b3);
        }

        public void writeBool(int i3, boolean z2) {
            requireSpace(6);
            write(z2 ? (byte) 1 : 0);
            writeTag(i3, 0);
        }

        public void writeBytes(int i3, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i3, 2);
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }

        @Deprecated
        public void writeEndGroup(int i3) {
            writeTag(i3, 4);
        }

        public void writeFixed32(int i3, int i4) {
            requireSpace(9);
            writeFixed32(i4);
            writeTag(i3, 5);
        }

        public void writeFixed64(int i3, long j2) {
            requireSpace(13);
            writeFixed64(j2);
            writeTag(i3, 1);
        }

        @Deprecated
        public void writeGroup(int i3, Object obj) throws IOException {
            writeTag(i3, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3, int i4) {
            requireSpace(15);
            writeInt32(i4);
            writeTag(i3, 0);
        }

        public void writeLazy(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                this.totalDoneBytes += i4;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i3, i4));
                nextBuffer();
                return;
            }
            int i5 = this.pos - i4;
            this.pos = i5;
            this.buffer.position(i5 + 1);
            this.buffer.put(bArr, i3, i4);
        }

        public void writeMessage(int i3, Object obj) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeSInt32(int i3, int i4) {
            requireSpace(10);
            writeSInt32(i4);
            writeTag(i3, 0);
        }

        public void writeSInt64(int i3, long j2) {
            requireSpace(15);
            writeSInt64(j2);
            writeTag(i3, 0);
        }

        @Deprecated
        public void writeStartGroup(int i3) {
            writeTag(i3, 3);
        }

        public void writeString(int i3, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeTag(int i3, int i4) {
            writeVarint32(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) {
            requireSpace(10);
            writeVarint32(i4);
            writeTag(i3, 0);
        }

        public void writeUInt64(int i3, long j2) {
            requireSpace(15);
            writeVarint64(j2);
            writeTag(i3, 0);
        }

        public void writeVarint32(int i3) {
            if ((i3 & -128) == 0) {
                writeVarint32OneByte(i3);
            } else if ((i3 & -16384) == 0) {
                writeVarint32TwoBytes(i3);
            } else if ((-2097152 & i3) == 0) {
                writeVarint32ThreeBytes(i3);
            } else if ((-268435456 & i3) == 0) {
                writeVarint32FourBytes(i3);
            } else {
                writeVarint32FiveBytes(i3);
            }
        }

        public void writeVarint64(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        private void nextBuffer(int i3) {
            nextBuffer(newDirectBuffer(i3));
        }

        public void write(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                nextBuffer(i4);
            }
            int i5 = this.pos - i4;
            this.pos = i5;
            this.buffer.position(i5 + 1);
            this.buffer.put(bArr, i3, i4);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer nioBuffer = allocatedBuffer.nioBuffer();
                if (nioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = nioBuffer;
                    nioBuffer.limit(nioBuffer.capacity());
                    this.buffer.position(0);
                    this.buffer.order(ByteOrder.LITTLE_ENDIAN);
                    int limit = this.buffer.limit() - 1;
                    this.limitMinusOne = limit;
                    this.pos = limit;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        public void writeBool(boolean z2) {
            write(z2 ? (byte) 1 : 0);
        }

        public void writeFixed32(int i3) {
            int i4 = this.pos;
            this.pos = i4 - 4;
            this.buffer.putInt(i4 - 3, i3);
        }

        public void writeFixed64(long j2) {
            int i3 = this.pos;
            this.pos = i3 - 8;
            this.buffer.putLong(i3 - 7, j2);
        }

        public void writeGroup(int i3, Object obj, Schema schema) throws IOException {
            writeTag(i3, 4);
            schema.writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3) {
            if (i3 >= 0) {
                writeVarint32(i3);
            } else {
                writeVarint64((long) i3);
            }
        }

        public void writeSInt32(int i3) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i3));
        }

        public void writeSInt64(long j2) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j2));
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            int i3 = this.pos - remaining;
            this.pos = i3;
            this.buffer.position(i3 + 1);
            this.buffer.put(byteBuffer);
        }

        public void writeMessage(int i3, Object obj, Schema schema) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeString(String str) {
            int i3;
            int i4;
            int i5;
            char charAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (charAt = str.charAt(length)) < 128) {
                this.buffer.put(this.pos + length, (byte) charAt);
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 < 128 && (i5 = this.pos) >= 0) {
                    ByteBuffer byteBuffer = this.buffer;
                    this.pos = i5 - 1;
                    byteBuffer.put(i5, (byte) charAt2);
                } else if (charAt2 < 2048 && (i4 = this.pos) > 0) {
                    ByteBuffer byteBuffer2 = this.buffer;
                    this.pos = i4 - 1;
                    byteBuffer2.put(i4, (byte) ((charAt2 & '?') | 128));
                    ByteBuffer byteBuffer3 = this.buffer;
                    int i6 = this.pos;
                    this.pos = i6 - 1;
                    byteBuffer3.put(i6, (byte) ((charAt2 >>> 6) | 960));
                } else if ((charAt2 < 55296 || 57343 < charAt2) && (i3 = this.pos) > 1) {
                    ByteBuffer byteBuffer4 = this.buffer;
                    this.pos = i3 - 1;
                    byteBuffer4.put(i3, (byte) ((charAt2 & '?') | 128));
                    ByteBuffer byteBuffer5 = this.buffer;
                    int i7 = this.pos;
                    this.pos = i7 - 1;
                    byteBuffer5.put(i7, (byte) (((charAt2 >>> 6) & 63) | 128));
                    ByteBuffer byteBuffer6 = this.buffer;
                    int i8 = this.pos;
                    this.pos = i8 - 1;
                    byteBuffer6.put(i8, (byte) ((charAt2 >>> 12) | 480));
                } else if (this.pos > 2) {
                    if (length != 0) {
                        char charAt3 = str.charAt(length - 1);
                        if (Character.isSurrogatePair(charAt3, charAt2)) {
                            length--;
                            int codePoint = Character.toCodePoint(charAt3, charAt2);
                            ByteBuffer byteBuffer7 = this.buffer;
                            int i9 = this.pos;
                            this.pos = i9 - 1;
                            byteBuffer7.put(i9, (byte) ((codePoint & 63) | 128));
                            ByteBuffer byteBuffer8 = this.buffer;
                            int i10 = this.pos;
                            this.pos = i10 - 1;
                            byteBuffer8.put(i10, (byte) (((codePoint >>> 6) & 63) | 128));
                            ByteBuffer byteBuffer9 = this.buffer;
                            int i11 = this.pos;
                            this.pos = i11 - 1;
                            byteBuffer9.put(i11, (byte) (((codePoint >>> 12) & 63) | 128));
                            ByteBuffer byteBuffer10 = this.buffer;
                            int i12 = this.pos;
                            this.pos = i12 - 1;
                            byteBuffer10.put(i12, (byte) ((codePoint >>> 18) | 240));
                        }
                    }
                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                } else {
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }

        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.totalDoneBytes += remaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
                return;
            }
            int i3 = this.pos - remaining;
            this.pos = i3;
            this.buffer.position(i3 + 1);
            this.buffer.put(byteBuffer);
        }
    }

    public static final class SafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private int limit;
        private int limitMinusOne;
        private int offset;
        private int offsetMinusOne;
        private int pos;

        public SafeHeapWriter(BufferAllocator bufferAllocator, int i3) {
            super(bufferAllocator, i3, (AnonymousClass1) null);
            nextBuffer();
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (i3 >>> 28);
            int i6 = i4 - 2;
            this.pos = i6;
            bArr[i5] = (byte) (((i3 >>> 21) & 127) | 128);
            int i7 = i4 - 3;
            this.pos = i7;
            bArr[i6] = (byte) (((i3 >>> 14) & 127) | 128);
            int i8 = i4 - 4;
            this.pos = i8;
            bArr[i7] = (byte) (((i3 >>> 7) & 127) | 128);
            this.pos = i4 - 5;
            bArr[i8] = (byte) ((i3 & 127) | 128);
        }

        private void writeVarint32FourBytes(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (i3 >>> 21);
            int i6 = i4 - 2;
            this.pos = i6;
            bArr[i5] = (byte) (((i3 >>> 14) & 127) | 128);
            int i7 = i4 - 3;
            this.pos = i7;
            bArr[i6] = (byte) (((i3 >>> 7) & 127) | 128);
            this.pos = i4 - 4;
            bArr[i7] = (byte) ((i3 & 127) | 128);
        }

        private void writeVarint32OneByte(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) i3;
        }

        private void writeVarint32ThreeBytes(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (i3 >>> 14);
            int i6 = i4 - 2;
            this.pos = i6;
            bArr[i5] = (byte) (((i3 >>> 7) & 127) | 128);
            this.pos = i4 - 3;
            bArr[i6] = (byte) ((i3 & 127) | 128);
        }

        private void writeVarint32TwoBytes(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (i3 >>> 7);
            this.pos = i4 - 2;
            bArr[i5] = (byte) ((i3 & 127) | 128);
        }

        private void writeVarint64EightBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 49));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 42) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 35) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 28) & 127) | 128));
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i9 = i3 - 6;
            this.pos = i9;
            bArr[i8] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i10 = i3 - 7;
            this.pos = i10;
            bArr[i9] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 8;
            bArr[i10] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 28));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 5;
            bArr[i7] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64FourBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 21));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 4;
            bArr[i6] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64NineBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 56));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 49) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 42) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 35) & 127) | 128));
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) ((int) (((j2 >>> 28) & 127) | 128));
            int i9 = i3 - 6;
            this.pos = i9;
            bArr[i8] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i10 = i3 - 7;
            this.pos = i10;
            bArr[i9] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i11 = i3 - 8;
            this.pos = i11;
            bArr[i10] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 9;
            bArr[i11] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64OneByte(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) ((int) j2);
        }

        private void writeVarint64SevenBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 42));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 35) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 28) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i9 = i3 - 6;
            this.pos = i9;
            bArr[i8] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 7;
            bArr[i9] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64SixBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 35));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 28) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 6;
            bArr[i8] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64TenBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 63));
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 56) & 127) | 128));
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) ((int) (((j2 >>> 49) & 127) | 128));
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) ((int) (((j2 >>> 42) & 127) | 128));
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) ((int) (((j2 >>> 35) & 127) | 128));
            int i9 = i3 - 6;
            this.pos = i9;
            bArr[i8] = (byte) ((int) (((j2 >>> 28) & 127) | 128));
            int i10 = i3 - 7;
            this.pos = i10;
            bArr[i9] = (byte) ((int) (((j2 >>> 21) & 127) | 128));
            int i11 = i3 - 8;
            this.pos = i11;
            bArr[i10] = (byte) ((int) (((j2 >>> 14) & 127) | 128));
            int i12 = i3 - 9;
            this.pos = i12;
            bArr[i11] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 10;
            bArr[i12] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((int) j2) >>> 14);
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) ((int) (((j2 >>> 7) & 127) | 128));
            this.pos = i3 - 3;
            bArr[i5] = (byte) ((int) ((j2 & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((int) (j2 >>> 7));
            this.pos = i3 - 2;
            bArr[i4] = (byte) ((((int) j2) & 127) | 128);
        }

        public int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                AllocatedBuffer allocatedBuffer2 = this.allocatedBuffer;
                allocatedBuffer2.position((this.pos - allocatedBuffer2.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        public void requireSpace(int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
        }

        public int spaceLeft() {
            return this.pos - this.offsetMinusOne;
        }

        public void write(byte b3) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = b3;
        }

        public void writeBool(int i3, boolean z2) throws IOException {
            requireSpace(6);
            write(z2 ? (byte) 1 : 0);
            writeTag(i3, 0);
        }

        public void writeBytes(int i3, ByteString byteString) throws IOException {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i3, 2);
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }

        public void writeEndGroup(int i3) {
            writeTag(i3, 4);
        }

        public void writeFixed32(int i3, int i4) throws IOException {
            requireSpace(9);
            writeFixed32(i4);
            writeTag(i3, 5);
        }

        public void writeFixed64(int i3, long j2) throws IOException {
            requireSpace(13);
            writeFixed64(j2);
            writeTag(i3, 1);
        }

        @Deprecated
        public void writeGroup(int i3, Object obj) throws IOException {
            writeTag(i3, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3, int i4) throws IOException {
            requireSpace(15);
            writeInt32(i4);
            writeTag(i3, 0);
        }

        public void writeLazy(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                this.totalDoneBytes += i4;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i3, i4));
                nextBuffer();
                return;
            }
            int i5 = this.pos - i4;
            this.pos = i5;
            System.arraycopy(bArr, i3, this.buffer, i5 + 1, i4);
        }

        public void writeMessage(int i3, Object obj) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeSInt32(int i3, int i4) throws IOException {
            requireSpace(10);
            writeSInt32(i4);
            writeTag(i3, 0);
        }

        public void writeSInt64(int i3, long j2) throws IOException {
            requireSpace(15);
            writeSInt64(j2);
            writeTag(i3, 0);
        }

        public void writeStartGroup(int i3) {
            writeTag(i3, 3);
        }

        public void writeString(int i3, String str) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeTag(int i3, int i4) {
            writeVarint32(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) throws IOException {
            requireSpace(10);
            writeVarint32(i4);
            writeTag(i3, 0);
        }

        public void writeUInt64(int i3, long j2) throws IOException {
            requireSpace(15);
            writeVarint64(j2);
            writeTag(i3, 0);
        }

        public void writeVarint32(int i3) {
            if ((i3 & -128) == 0) {
                writeVarint32OneByte(i3);
            } else if ((i3 & -16384) == 0) {
                writeVarint32TwoBytes(i3);
            } else if ((-2097152 & i3) == 0) {
                writeVarint32ThreeBytes(i3);
            } else if ((-268435456 & i3) == 0) {
                writeVarint32FourBytes(i3);
            } else {
                writeVarint32FiveBytes(i3);
            }
        }

        public void writeVarint64(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        private void nextBuffer(int i3) {
            nextBuffer(newHeapBuffer(i3));
        }

        public void write(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                nextBuffer(i4);
            }
            int i5 = this.pos - i4;
            this.pos = i5;
            System.arraycopy(bArr, i3, this.buffer, i5 + 1, i4);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer2) {
            if (allocatedBuffer2.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer2);
                this.allocatedBuffer = allocatedBuffer2;
                this.buffer = allocatedBuffer2.array();
                int arrayOffset = allocatedBuffer2.arrayOffset();
                this.limit = allocatedBuffer2.limit() + arrayOffset;
                int position = arrayOffset + allocatedBuffer2.position();
                this.offset = position;
                this.offsetMinusOne = position - 1;
                int i3 = this.limit - 1;
                this.limitMinusOne = i3;
                this.pos = i3;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        public void writeBool(boolean z2) {
            write(z2 ? (byte) 1 : 0);
        }

        public void writeFixed32(int i3) {
            byte[] bArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) ((i3 >> 24) & 255);
            int i6 = i4 - 2;
            this.pos = i6;
            bArr[i5] = (byte) ((i3 >> 16) & 255);
            int i7 = i4 - 3;
            this.pos = i7;
            bArr[i6] = (byte) ((i3 >> 8) & 255);
            this.pos = i4 - 4;
            bArr[i7] = (byte) (i3 & 255);
        }

        public void writeFixed64(long j2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((int) (j2 >> 56)) & 255);
            int i5 = i3 - 2;
            this.pos = i5;
            bArr[i4] = (byte) (((int) (j2 >> 48)) & 255);
            int i6 = i3 - 3;
            this.pos = i6;
            bArr[i5] = (byte) (((int) (j2 >> 40)) & 255);
            int i7 = i3 - 4;
            this.pos = i7;
            bArr[i6] = (byte) (((int) (j2 >> 32)) & 255);
            int i8 = i3 - 5;
            this.pos = i8;
            bArr[i7] = (byte) (((int) (j2 >> 24)) & 255);
            int i9 = i3 - 6;
            this.pos = i9;
            bArr[i8] = (byte) (((int) (j2 >> 16)) & 255);
            int i10 = i3 - 7;
            this.pos = i10;
            bArr[i9] = (byte) (((int) (j2 >> 8)) & 255);
            this.pos = i3 - 8;
            bArr[i10] = (byte) (((int) j2) & 255);
        }

        public void writeGroup(int i3, Object obj, Schema schema) throws IOException {
            writeTag(i3, 4);
            schema.writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3) {
            if (i3 >= 0) {
                writeVarint32(i3);
            } else {
                writeVarint64((long) i3);
            }
        }

        public void writeSInt32(int i3) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i3));
        }

        public void writeSInt64(long j2) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j2));
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            int i3 = this.pos - remaining;
            this.pos = i3;
            byteBuffer.get(this.buffer, i3 + 1, remaining);
        }

        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.totalDoneBytes += remaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            int i3 = this.pos - remaining;
            this.pos = i3;
            byteBuffer.get(this.buffer, i3 + 1, remaining);
        }

        public void writeMessage(int i3, Object obj, Schema schema) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeString(String str) {
            int i3;
            int i4;
            int i5;
            char charAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (charAt = str.charAt(length)) < 128) {
                this.buffer[this.pos + length] = (byte) charAt;
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 < 128 && (i5 = this.pos) > this.offsetMinusOne) {
                    byte[] bArr = this.buffer;
                    this.pos = i5 - 1;
                    bArr[i5] = (byte) charAt2;
                } else if (charAt2 < 2048 && (i4 = this.pos) > this.offset) {
                    byte[] bArr2 = this.buffer;
                    int i6 = i4 - 1;
                    this.pos = i6;
                    bArr2[i4] = (byte) ((charAt2 & '?') | 128);
                    this.pos = i4 - 2;
                    bArr2[i6] = (byte) ((charAt2 >>> 6) | 960);
                } else if ((charAt2 < 55296 || 57343 < charAt2) && (i3 = this.pos) > this.offset + 1) {
                    byte[] bArr3 = this.buffer;
                    int i7 = i3 - 1;
                    this.pos = i7;
                    bArr3[i3] = (byte) ((charAt2 & '?') | 128);
                    int i8 = i3 - 2;
                    this.pos = i8;
                    bArr3[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    this.pos = i3 - 3;
                    bArr3[i8] = (byte) ((charAt2 >>> 12) | 480);
                } else if (this.pos > this.offset + 2) {
                    if (length != 0) {
                        char charAt3 = str.charAt(length - 1);
                        if (Character.isSurrogatePair(charAt3, charAt2)) {
                            length--;
                            int codePoint = Character.toCodePoint(charAt3, charAt2);
                            byte[] bArr4 = this.buffer;
                            int i9 = this.pos;
                            int i10 = i9 - 1;
                            this.pos = i10;
                            bArr4[i9] = (byte) ((codePoint & 63) | 128);
                            int i11 = i9 - 2;
                            this.pos = i11;
                            bArr4[i10] = (byte) (((codePoint >>> 6) & 63) | 128);
                            int i12 = i9 - 3;
                            this.pos = i12;
                            bArr4[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                            this.pos = i9 - 4;
                            bArr4[i12] = (byte) ((codePoint >>> 18) | 240);
                        }
                    }
                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                } else {
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }
    }

    public static final class UnsafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private long bufferOffset;
        private long limitMinusOne;
        private long pos;

        public UnsafeDirectWriter(BufferAllocator bufferAllocator, int i3) {
            super(bufferAllocator, i3, (AnonymousClass1) null);
            nextBuffer();
        }

        private int bufferPos() {
            return (int) (this.pos - this.bufferOffset);
        }

        private int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        /* access modifiers changed from: private */
        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return bufferPos() + 1;
        }

        private void writeVarint32FiveBytes(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) (i3 >>> 28));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) (((i3 >>> 21) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) (((i3 >>> 14) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) (((i3 >>> 7) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) (i3 >>> 21));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) (((i3 >>> 14) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) (((i3 >>> 7) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32OneByte(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) i3);
        }

        private void writeVarint32ThreeBytes(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) (i3 >>> 14));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) (((i3 >>> 7) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) (i3 >>> 7));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 49)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64FiveBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 28)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64FourBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 21)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64NineBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 56)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 49) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64OneByte(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) j2));
        }

        private void writeVarint64SevenBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 42)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64SixBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 35)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64TenBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 63)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 56) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) (((j2 >>> 49) & 127) | 128)));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64ThreeBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) (((int) j2) >>> 14));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64TwoBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((int) (j2 >>> 7)));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((((int) j2) & 127) | 128));
        }

        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(bufferPos() + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        public void requireSpace(int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
        }

        public void write(byte b3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, b3);
        }

        public void writeBool(int i3, boolean z2) {
            requireSpace(6);
            write(z2 ? (byte) 1 : 0);
            writeTag(i3, 0);
        }

        public void writeBytes(int i3, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i3, 2);
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }

        @Deprecated
        public void writeEndGroup(int i3) {
            writeTag(i3, 4);
        }

        public void writeFixed32(int i3, int i4) {
            requireSpace(9);
            writeFixed32(i4);
            writeTag(i3, 5);
        }

        public void writeFixed64(int i3, long j2) {
            requireSpace(13);
            writeFixed64(j2);
            writeTag(i3, 1);
        }

        public void writeGroup(int i3, Object obj) throws IOException {
            writeTag(i3, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3, int i4) {
            requireSpace(15);
            writeInt32(i4);
            writeTag(i3, 0);
        }

        public void writeLazy(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                this.totalDoneBytes += i4;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i3, i4));
                nextBuffer();
                return;
            }
            this.pos -= (long) i4;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i3, i4);
        }

        public void writeMessage(int i3, Object obj) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeSInt32(int i3, int i4) {
            requireSpace(10);
            writeSInt32(i4);
            writeTag(i3, 0);
        }

        public void writeSInt64(int i3, long j2) {
            requireSpace(15);
            writeSInt64(j2);
            writeTag(i3, 0);
        }

        @Deprecated
        public void writeStartGroup(int i3) {
            writeTag(i3, 3);
        }

        public void writeString(int i3, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeTag(int i3, int i4) {
            writeVarint32(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) {
            requireSpace(10);
            writeVarint32(i4);
            writeTag(i3, 0);
        }

        public void writeUInt64(int i3, long j2) {
            requireSpace(15);
            writeVarint64(j2);
            writeTag(i3, 0);
        }

        public void writeVarint32(int i3) {
            if ((i3 & -128) == 0) {
                writeVarint32OneByte(i3);
            } else if ((i3 & -16384) == 0) {
                writeVarint32TwoBytes(i3);
            } else if ((-2097152 & i3) == 0) {
                writeVarint32ThreeBytes(i3);
            } else if ((-268435456 & i3) == 0) {
                writeVarint32FourBytes(i3);
            } else {
                writeVarint32FiveBytes(i3);
            }
        }

        public void writeVarint64(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        private void nextBuffer(int i3) {
            nextBuffer(newDirectBuffer(i3));
        }

        public void write(byte[] bArr, int i3, int i4) {
            if (spaceLeft() < i4) {
                nextBuffer(i4);
            }
            this.pos -= (long) i4;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i3, i4);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer nioBuffer = allocatedBuffer.nioBuffer();
                if (nioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = nioBuffer;
                    nioBuffer.limit(nioBuffer.capacity());
                    this.buffer.position(0);
                    long addressOffset = UnsafeUtil.addressOffset(this.buffer);
                    this.bufferOffset = addressOffset;
                    long limit = addressOffset + ((long) (this.buffer.limit() - 1));
                    this.limitMinusOne = limit;
                    this.pos = limit;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        public void writeBool(boolean z2) {
            write(z2 ? (byte) 1 : 0);
        }

        public void writeFixed32(int i3) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(j2, (byte) ((i3 >> 24) & 255));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) ((i3 >> 16) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) ((i3 >> 8) & 255));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) (i3 & 255));
        }

        public void writeFixed64(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(j3, (byte) (((int) (j2 >> 56)) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(j4, (byte) (((int) (j2 >> 48)) & 255));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(j5, (byte) (((int) (j2 >> 40)) & 255));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(j6, (byte) (((int) (j2 >> 32)) & 255));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(j7, (byte) (((int) (j2 >> 24)) & 255));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(j8, (byte) (((int) (j2 >> 16)) & 255));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) (((int) (j2 >> 8)) & 255));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((int) j2) & 255));
        }

        public void writeGroup(int i3, Object obj, Schema schema) throws IOException {
            writeTag(i3, 4);
            schema.writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3) {
            if (i3 >= 0) {
                writeVarint32(i3);
            } else {
                writeVarint64((long) i3);
            }
        }

        public void writeSInt32(int i3) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i3));
        }

        public void writeSInt64(long j2) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j2));
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            this.pos -= (long) remaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }

        public void writeMessage(int i3, Object obj, Schema schema) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeString(String str) {
            char charAt;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length >= 0 && (charAt = str.charAt(length)) < 128) {
                    long j2 = this.pos;
                    this.pos = j2 - 1;
                    UnsafeUtil.putByte(j2, (byte) charAt);
                }
            }
            if (length != -1) {
                while (length >= 0) {
                    char charAt2 = str.charAt(length);
                    if (charAt2 < 128) {
                        long j3 = this.pos;
                        if (j3 >= this.bufferOffset) {
                            this.pos = j3 - 1;
                            UnsafeUtil.putByte(j3, (byte) charAt2);
                            length--;
                        }
                    }
                    if (charAt2 < 2048) {
                        long j4 = this.pos;
                        if (j4 > this.bufferOffset) {
                            this.pos = j4 - 1;
                            UnsafeUtil.putByte(j4, (byte) ((charAt2 & '?') | 128));
                            long j5 = this.pos;
                            this.pos = j5 - 1;
                            UnsafeUtil.putByte(j5, (byte) ((charAt2 >>> 6) | 960));
                            length--;
                        }
                    }
                    if (charAt2 < 55296 || 57343 < charAt2) {
                        long j6 = this.pos;
                        if (j6 > this.bufferOffset + 1) {
                            this.pos = j6 - 1;
                            UnsafeUtil.putByte(j6, (byte) ((charAt2 & '?') | 128));
                            long j7 = this.pos;
                            this.pos = j7 - 1;
                            UnsafeUtil.putByte(j7, (byte) (((charAt2 >>> 6) & 63) | 128));
                            long j8 = this.pos;
                            this.pos = j8 - 1;
                            UnsafeUtil.putByte(j8, (byte) ((charAt2 >>> 12) | 480));
                            length--;
                        }
                    }
                    if (this.pos > this.bufferOffset + 2) {
                        if (length != 0) {
                            char charAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(charAt3, charAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(charAt3, charAt2);
                                long j9 = this.pos;
                                this.pos = j9 - 1;
                                UnsafeUtil.putByte(j9, (byte) ((codePoint & 63) | 128));
                                long j10 = this.pos;
                                this.pos = j10 - 1;
                                UnsafeUtil.putByte(j10, (byte) (((codePoint >>> 6) & 63) | 128));
                                long j11 = this.pos;
                                this.pos = j11 - 1;
                                UnsafeUtil.putByte(j11, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j12 = this.pos;
                                this.pos = j12 - 1;
                                UnsafeUtil.putByte(j12, (byte) ((codePoint >>> 18) | 240));
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                    length--;
                }
            }
        }

        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.totalDoneBytes += remaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
                return;
            }
            this.pos -= (long) remaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }
    }

    public static final class UnsafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private long limit;
        private long limitMinusOne;
        private long offset;
        private long offsetMinusOne;
        private long pos;

        public UnsafeHeapWriter(BufferAllocator bufferAllocator, int i3) {
            super(bufferAllocator, i3, (AnonymousClass1) null);
            nextBuffer();
        }

        private int arrayPos() {
            return (int) this.pos;
        }

        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeArrayOperations();
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (i3 >>> 28));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((i3 >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((i3 >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((i3 >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (i3 >>> 21));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((i3 >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((i3 >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32OneByte(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) i3);
        }

        private void writeVarint32ThreeBytes(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (i3 >>> 14));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((i3 >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (i3 >>> 7));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) ((i3 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 49)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr7, j9, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr8, j10, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64FiveBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 28)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64FourBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 21)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64NineBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 56)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 49) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr7, j9, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr8, j10, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr9 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr9, j11, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64OneByte(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) j2));
        }

        private void writeVarint64SevenBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 42)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr7, j9, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64SixBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 35)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64TenBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 63)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 56) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) (((j2 >>> 49) & 127) | 128)));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) ((int) (((j2 >>> 42) & 127) | 128)));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) ((int) (((j2 >>> 35) & 127) | 128)));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) ((int) (((j2 >>> 28) & 127) | 128)));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr7, j9, (byte) ((int) (((j2 >>> 21) & 127) | 128)));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr8, j10, (byte) ((int) (((j2 >>> 14) & 127) | 128)));
            byte[] bArr9 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr9, j11, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr10 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr10, j12, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64ThreeBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) (((int) j2) >>> 14));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((int) (((j2 >>> 7) & 127) | 128)));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) ((int) ((j2 & 127) | 128)));
        }

        private void writeVarint64TwoBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) ((int) (j2 >>> 7)));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) ((((int) j2) & 127) | 128));
        }

        public int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.allocatedBuffer.position((arrayPos() - this.allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        public void requireSpace(int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
        }

        public int spaceLeft() {
            return (int) (this.pos - this.offsetMinusOne);
        }

        public void write(byte b3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, b3);
        }

        public void writeBool(int i3, boolean z2) {
            requireSpace(6);
            write(z2 ? (byte) 1 : 0);
            writeTag(i3, 0);
        }

        public void writeBytes(int i3, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i3, 2);
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }

        public void writeEndGroup(int i3) {
            writeTag(i3, 4);
        }

        public void writeFixed32(int i3, int i4) {
            requireSpace(9);
            writeFixed32(i4);
            writeTag(i3, 5);
        }

        public void writeFixed64(int i3, long j2) {
            requireSpace(13);
            writeFixed64(j2);
            writeTag(i3, 1);
        }

        public void writeGroup(int i3, Object obj) throws IOException {
            writeTag(i3, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3, int i4) {
            requireSpace(15);
            writeInt32(i4);
            writeTag(i3, 0);
        }

        public void writeLazy(byte[] bArr, int i3, int i4) {
            if (i3 < 0 || i3 + i4 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
            } else if (spaceLeft() < i4) {
                this.totalDoneBytes += i4;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i3, i4));
                nextBuffer();
            } else {
                this.pos -= (long) i4;
                System.arraycopy(bArr, i3, this.buffer, arrayPos() + 1, i4);
            }
        }

        public void writeMessage(int i3, Object obj) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeSInt32(int i3, int i4) {
            requireSpace(10);
            writeSInt32(i4);
            writeTag(i3, 0);
        }

        public void writeSInt64(int i3, long j2) {
            requireSpace(15);
            writeSInt64(j2);
            writeTag(i3, 0);
        }

        public void writeStartGroup(int i3) {
            writeTag(i3, 3);
        }

        public void writeString(int i3, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeTag(int i3, int i4) {
            writeVarint32(WireFormat.makeTag(i3, i4));
        }

        public void writeUInt32(int i3, int i4) {
            requireSpace(10);
            writeVarint32(i4);
            writeTag(i3, 0);
        }

        public void writeUInt64(int i3, long j2) {
            requireSpace(15);
            writeVarint64(j2);
            writeTag(i3, 0);
        }

        public void writeVarint32(int i3) {
            if ((i3 & -128) == 0) {
                writeVarint32OneByte(i3);
            } else if ((i3 & -16384) == 0) {
                writeVarint32TwoBytes(i3);
            } else if ((-2097152 & i3) == 0) {
                writeVarint32ThreeBytes(i3);
            } else if ((-268435456 & i3) == 0) {
                writeVarint32FourBytes(i3);
            } else {
                writeVarint32FiveBytes(i3);
            }
        }

        public void writeVarint64(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        private void nextBuffer(int i3) {
            nextBuffer(newHeapBuffer(i3));
        }

        public void write(byte[] bArr, int i3, int i4) {
            if (i3 < 0 || i3 + i4 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
            }
            requireSpace(i4);
            this.pos -= (long) i4;
            System.arraycopy(bArr, i3, this.buffer, arrayPos() + 1, i4);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer2) {
            if (allocatedBuffer2.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer2);
                this.allocatedBuffer = allocatedBuffer2;
                this.buffer = allocatedBuffer2.array();
                long arrayOffset = (long) allocatedBuffer2.arrayOffset();
                this.limit = ((long) allocatedBuffer2.limit()) + arrayOffset;
                long position = arrayOffset + ((long) allocatedBuffer2.position());
                this.offset = position;
                this.offsetMinusOne = position - 1;
                long j2 = this.limit - 1;
                this.limitMinusOne = j2;
                this.pos = j2;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        public void writeBool(boolean z2) {
            write(z2 ? (byte) 1 : 0);
        }

        public void writeFixed32(int i3) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) ((i3 >> 24) & 255));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) ((i3 >> 16) & 255));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) ((i3 >> 8) & 255));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (i3 & 255));
        }

        public void writeFixed64(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr, j3, (byte) (((int) (j2 >> 56)) & 255));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr2, j4, (byte) (((int) (j2 >> 48)) & 255));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr3, j5, (byte) (((int) (j2 >> 40)) & 255));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr4, j6, (byte) (((int) (j2 >> 32)) & 255));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr5, j7, (byte) (((int) (j2 >> 24)) & 255));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr6, j8, (byte) (((int) (j2 >> 16)) & 255));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr7, j9, (byte) (((int) (j2 >> 8)) & 255));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr8, j10, (byte) (((int) j2) & 255));
        }

        public void writeGroup(int i3, Object obj, Schema schema) throws IOException {
            writeTag(i3, 4);
            schema.writeTo(obj, this);
            writeTag(i3, 3);
        }

        public void writeInt32(int i3) {
            if (i3 >= 0) {
                writeVarint32(i3);
            } else {
                writeVarint64((long) i3);
            }
        }

        public void writeSInt32(int i3) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i3));
        }

        public void writeSInt64(long j2) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j2));
        }

        public void writeMessage(int i3, Object obj, Schema schema) throws IOException {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            requireSpace(10);
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }

        public void writeString(String str) {
            char charAt;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length >= 0 && (charAt = str.charAt(length)) < 128) {
                    byte[] bArr = this.buffer;
                    long j2 = this.pos;
                    this.pos = j2 - 1;
                    UnsafeUtil.putByte(bArr, j2, (byte) charAt);
                }
            }
            if (length != -1) {
                while (length >= 0) {
                    char charAt2 = str.charAt(length);
                    if (charAt2 < 128) {
                        long j3 = this.pos;
                        if (j3 > this.offsetMinusOne) {
                            byte[] bArr2 = this.buffer;
                            this.pos = j3 - 1;
                            UnsafeUtil.putByte(bArr2, j3, (byte) charAt2);
                            length--;
                        }
                    }
                    if (charAt2 < 2048) {
                        long j4 = this.pos;
                        if (j4 > this.offset) {
                            byte[] bArr3 = this.buffer;
                            this.pos = j4 - 1;
                            UnsafeUtil.putByte(bArr3, j4, (byte) ((charAt2 & '?') | 128));
                            byte[] bArr4 = this.buffer;
                            long j5 = this.pos;
                            this.pos = j5 - 1;
                            UnsafeUtil.putByte(bArr4, j5, (byte) ((charAt2 >>> 6) | 960));
                            length--;
                        }
                    }
                    if (charAt2 < 55296 || 57343 < charAt2) {
                        long j6 = this.pos;
                        if (j6 > this.offset + 1) {
                            byte[] bArr5 = this.buffer;
                            this.pos = j6 - 1;
                            UnsafeUtil.putByte(bArr5, j6, (byte) ((charAt2 & '?') | 128));
                            byte[] bArr6 = this.buffer;
                            long j7 = this.pos;
                            this.pos = j7 - 1;
                            UnsafeUtil.putByte(bArr6, j7, (byte) (((charAt2 >>> 6) & 63) | 128));
                            byte[] bArr7 = this.buffer;
                            long j8 = this.pos;
                            this.pos = j8 - 1;
                            UnsafeUtil.putByte(bArr7, j8, (byte) ((charAt2 >>> 12) | 480));
                            length--;
                        }
                    }
                    if (this.pos > this.offset + 2) {
                        if (length != 0) {
                            char charAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(charAt3, charAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(charAt3, charAt2);
                                byte[] bArr8 = this.buffer;
                                long j9 = this.pos;
                                this.pos = j9 - 1;
                                UnsafeUtil.putByte(bArr8, j9, (byte) ((codePoint & 63) | 128));
                                byte[] bArr9 = this.buffer;
                                long j10 = this.pos;
                                this.pos = j10 - 1;
                                UnsafeUtil.putByte(bArr9, j10, (byte) (((codePoint >>> 6) & 63) | 128));
                                byte[] bArr10 = this.buffer;
                                long j11 = this.pos;
                                this.pos = j11 - 1;
                                UnsafeUtil.putByte(bArr10, j11, (byte) (((codePoint >>> 12) & 63) | 128));
                                byte[] bArr11 = this.buffer;
                                long j12 = this.pos;
                                this.pos = j12 - 1;
                                UnsafeUtil.putByte(bArr11, j12, (byte) ((codePoint >>> 18) | 240));
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                    length--;
                }
            }
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            requireSpace(remaining);
            this.pos -= (long) remaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, remaining);
        }

        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.totalDoneBytes += remaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            this.pos -= (long) remaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, remaining);
        }
    }

    public /* synthetic */ BinaryWriter(BufferAllocator bufferAllocator, int i3, AnonymousClass1 r3) {
        this(bufferAllocator, i3);
    }

    /* access modifiers changed from: private */
    public static byte computeUInt64SizeNoTag(long j2) {
        byte b3;
        if ((-128 & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if ((-34359738368L & j2) != 0) {
            b3 = (byte) 6;
            j2 >>>= 28;
        } else {
            b3 = 2;
        }
        if ((-2097152 & j2) != 0) {
            b3 = (byte) (b3 + 2);
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? (byte) (b3 + 1) : b3;
    }

    public static boolean isUnsafeDirectSupported() {
        return UnsafeDirectWriter.isSupported();
    }

    public static boolean isUnsafeHeapSupported() {
        return UnsafeHeapWriter.isSupported();
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator) {
        return newDirectInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator) {
        return newHeapInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newSafeDirectInstance(BufferAllocator bufferAllocator, int i3) {
        return new SafeDirectWriter(bufferAllocator, i3);
    }

    public static BinaryWriter newSafeHeapInstance(BufferAllocator bufferAllocator, int i3) {
        return new SafeHeapWriter(bufferAllocator, i3);
    }

    public static BinaryWriter newUnsafeDirectInstance(BufferAllocator bufferAllocator, int i3) {
        if (isUnsafeDirectSupported()) {
            return new UnsafeDirectWriter(bufferAllocator, i3);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    public static BinaryWriter newUnsafeHeapInstance(BufferAllocator bufferAllocator, int i3) {
        if (isUnsafeHeapSupported()) {
            return new UnsafeHeapWriter(bufferAllocator, i3);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    private final void writeBoolList_Internal(int i3, List<Boolean> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(list.size() + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeBool(list.get(size).booleanValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeBool(i3, list.get(size2).booleanValue());
        }
    }

    private final void writeDoubleList_Internal(int i3, List<Double> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 8, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed64(Double.doubleToRawLongBits(list.get(size).doubleValue()));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeDouble(i3, list.get(size2).doubleValue());
        }
    }

    private final void writeFixed32List_Internal(int i3, List<Integer> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 4, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed32(list.get(size).intValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed32(i3, list.get(size2).intValue());
        }
    }

    private final void writeFixed64List_Internal(int i3, List<Long> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 8, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed64(list.get(size).longValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed64(i3, list.get(size2).longValue());
        }
    }

    private final void writeFloatList_Internal(int i3, List<Float> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 4, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed32(Float.floatToRawIntBits(list.get(size).floatValue()));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFloat(i3, list.get(size2).floatValue());
        }
    }

    private final void writeInt32List_Internal(int i3, List<Integer> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 10, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeInt32(list.get(size).intValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeInt32(i3, list.get(size2).intValue());
        }
    }

    private void writeLazyString(int i3, Object obj) throws IOException {
        if (obj instanceof String) {
            writeString(i3, (String) obj);
        } else {
            writeBytes(i3, (ByteString) obj);
        }
    }

    public static final void writeMapEntryField(Writer writer, int i3, WireFormat.FieldType fieldType, Object obj) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                writer.writeBool(i3, ((Boolean) obj).booleanValue());
                return;
            case 2:
                writer.writeFixed32(i3, ((Integer) obj).intValue());
                return;
            case 3:
                writer.writeFixed64(i3, ((Long) obj).longValue());
                return;
            case 4:
                writer.writeInt32(i3, ((Integer) obj).intValue());
                return;
            case 5:
                writer.writeInt64(i3, ((Long) obj).longValue());
                return;
            case 6:
                writer.writeSFixed32(i3, ((Integer) obj).intValue());
                return;
            case 7:
                writer.writeSFixed64(i3, ((Long) obj).longValue());
                return;
            case 8:
                writer.writeSInt32(i3, ((Integer) obj).intValue());
                return;
            case 9:
                writer.writeSInt64(i3, ((Long) obj).longValue());
                return;
            case 10:
                writer.writeString(i3, (String) obj);
                return;
            case 11:
                writer.writeUInt32(i3, ((Integer) obj).intValue());
                return;
            case 12:
                writer.writeUInt64(i3, ((Long) obj).longValue());
                return;
            case 13:
                writer.writeFloat(i3, ((Float) obj).floatValue());
                return;
            case 14:
                writer.writeDouble(i3, ((Double) obj).doubleValue());
                return;
            case 15:
                writer.writeMessage(i3, obj);
                return;
            case 16:
                writer.writeBytes(i3, (ByteString) obj);
                return;
            case 17:
                if (obj instanceof Internal.EnumLite) {
                    writer.writeEnum(i3, ((Internal.EnumLite) obj).getNumber());
                    return;
                } else if (obj instanceof Integer) {
                    writer.writeEnum(i3, ((Integer) obj).intValue());
                    return;
                } else {
                    throw new IllegalArgumentException("Unexpected type for enum in map.");
                }
            default:
                throw new IllegalArgumentException("Unsupported map value type for: " + fieldType);
        }
    }

    private final void writeSInt32List_Internal(int i3, List<Integer> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 5, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt32(list.get(size).intValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeSInt32(i3, list.get(size2).intValue());
        }
    }

    private final void writeSInt64List_Internal(int i3, List<Long> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 10, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt64(list.get(size).longValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeSInt64(i3, list.get(size2).longValue());
        }
    }

    private final void writeUInt32List_Internal(int i3, List<Integer> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 5, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeVarint32(list.get(size).intValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeUInt32(i3, list.get(size2).intValue());
        }
    }

    private final void writeUInt64List_Internal(int i3, List<Long> list, boolean z2) throws IOException {
        if (z2) {
            requireSpace(b.c(list, 10, 10));
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = list.size() - 1; size >= 0; size--) {
                writeVarint64(list.get(size).longValue());
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeUInt64(i3, list.get(size2).longValue());
        }
    }

    @CanIgnoreReturnValue
    public final Queue<AllocatedBuffer> complete() {
        finishCurrentBuffer();
        return this.buffers;
    }

    public final Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.DESCENDING;
    }

    public abstract void finishCurrentBuffer();

    public abstract int getTotalBytesWritten();

    public final AllocatedBuffer newDirectBuffer() {
        return this.alloc.allocateDirectBuffer(this.chunkSize);
    }

    public final AllocatedBuffer newHeapBuffer() {
        return this.alloc.allocateHeapBuffer(this.chunkSize);
    }

    public abstract void requireSpace(int i3);

    public abstract void writeBool(boolean z2);

    public final void writeBoolList(int i3, List<Boolean> list, boolean z2) throws IOException {
        if (list instanceof BooleanArrayList) {
            writeBoolList_Internal(i3, (BooleanArrayList) list, z2);
        } else {
            writeBoolList_Internal(i3, list, z2);
        }
    }

    public final void writeBytesList(int i3, List<ByteString> list) throws IOException {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeBytes(i3, list.get(size));
        }
    }

    public final void writeDouble(int i3, double d2) throws IOException {
        writeFixed64(i3, Double.doubleToRawLongBits(d2));
    }

    public final void writeDoubleList(int i3, List<Double> list, boolean z2) throws IOException {
        if (list instanceof DoubleArrayList) {
            writeDoubleList_Internal(i3, (DoubleArrayList) list, z2);
        } else {
            writeDoubleList_Internal(i3, list, z2);
        }
    }

    public final void writeEnum(int i3, int i4) throws IOException {
        writeInt32(i3, i4);
    }

    public final void writeEnumList(int i3, List<Integer> list, boolean z2) throws IOException {
        writeInt32List(i3, list, z2);
    }

    public abstract void writeFixed32(int i3);

    public final void writeFixed32List(int i3, List<Integer> list, boolean z2) throws IOException {
        if (list instanceof IntArrayList) {
            writeFixed32List_Internal(i3, (IntArrayList) list, z2);
        } else {
            writeFixed32List_Internal(i3, list, z2);
        }
    }

    public abstract void writeFixed64(long j2);

    public final void writeFixed64List(int i3, List<Long> list, boolean z2) throws IOException {
        if (list instanceof LongArrayList) {
            writeFixed64List_Internal(i3, (LongArrayList) list, z2);
        } else {
            writeFixed64List_Internal(i3, list, z2);
        }
    }

    public final void writeFloat(int i3, float f2) throws IOException {
        writeFixed32(i3, Float.floatToRawIntBits(f2));
    }

    public final void writeFloatList(int i3, List<Float> list, boolean z2) throws IOException {
        if (list instanceof FloatArrayList) {
            writeFloatList_Internal(i3, (FloatArrayList) list, z2);
        } else {
            writeFloatList_Internal(i3, list, z2);
        }
    }

    @Deprecated
    public final void writeGroupList(int i3, List<?> list) throws IOException {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i3, list.get(size));
        }
    }

    public abstract void writeInt32(int i3);

    public final void writeInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        if (list instanceof IntArrayList) {
            writeInt32List_Internal(i3, (IntArrayList) list, z2);
        } else {
            writeInt32List_Internal(i3, list, z2);
        }
    }

    public final void writeInt64(int i3, long j2) throws IOException {
        writeUInt64(i3, j2);
    }

    public final void writeInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        writeUInt64List(i3, list, z2);
    }

    public <K, V> void writeMap(int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            int totalBytesWritten = getTotalBytesWritten();
            writeMapEntryField(this, 2, metadata.valueType, next.getValue());
            writeMapEntryField(this, 1, metadata.keyType, next.getKey());
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
        }
    }

    public final void writeMessageList(int i3, List<?> list) throws IOException {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i3, list.get(size));
        }
    }

    public final void writeMessageSetItem(int i3, Object obj) throws IOException {
        writeTag(1, 4);
        if (obj instanceof ByteString) {
            writeBytes(3, (ByteString) obj);
        } else {
            writeMessage(3, obj);
        }
        writeUInt32(2, i3);
        writeTag(1, 3);
    }

    public final void writeSFixed32(int i3, int i4) throws IOException {
        writeFixed32(i3, i4);
    }

    public final void writeSFixed32List(int i3, List<Integer> list, boolean z2) throws IOException {
        writeFixed32List(i3, list, z2);
    }

    public final void writeSFixed64(int i3, long j2) throws IOException {
        writeFixed64(i3, j2);
    }

    public final void writeSFixed64List(int i3, List<Long> list, boolean z2) throws IOException {
        writeFixed64List(i3, list, z2);
    }

    public abstract void writeSInt32(int i3);

    public final void writeSInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        if (list instanceof IntArrayList) {
            writeSInt32List_Internal(i3, (IntArrayList) list, z2);
        } else {
            writeSInt32List_Internal(i3, list, z2);
        }
    }

    public abstract void writeSInt64(long j2);

    public final void writeSInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        if (list instanceof LongArrayList) {
            writeSInt64List_Internal(i3, (LongArrayList) list, z2);
        } else {
            writeSInt64List_Internal(i3, list, z2);
        }
    }

    public abstract void writeString(String str);

    public final void writeStringList(int i3, List<String> list) throws IOException {
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            for (int size = list.size() - 1; size >= 0; size--) {
                writeLazyString(i3, lazyStringList.getRaw(size));
            }
            return;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeString(i3, list.get(size2));
        }
    }

    public abstract void writeTag(int i3, int i4);

    public final void writeUInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        if (list instanceof IntArrayList) {
            writeUInt32List_Internal(i3, (IntArrayList) list, z2);
        } else {
            writeUInt32List_Internal(i3, list, z2);
        }
    }

    public final void writeUInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        if (list instanceof LongArrayList) {
            writeUInt64List_Internal(i3, (LongArrayList) list, z2);
        } else {
            writeUInt64List_Internal(i3, list, z2);
        }
    }

    public abstract void writeVarint32(int i3);

    public abstract void writeVarint64(long j2);

    private BinaryWriter(BufferAllocator bufferAllocator, int i3) {
        this.buffers = new ArrayDeque<>(4);
        if (i3 > 0) {
            this.alloc = (BufferAllocator) Internal.checkNotNull(bufferAllocator, "alloc");
            this.chunkSize = i3;
            return;
        }
        throw new IllegalArgumentException("chunkSize must be > 0");
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator, int i3) {
        if (isUnsafeDirectSupported()) {
            return newUnsafeDirectInstance(bufferAllocator, i3);
        }
        return newSafeDirectInstance(bufferAllocator, i3);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator, int i3) {
        if (isUnsafeHeapSupported()) {
            return newUnsafeHeapInstance(bufferAllocator, i3);
        }
        return newSafeHeapInstance(bufferAllocator, i3);
    }

    public final AllocatedBuffer newDirectBuffer(int i3) {
        return this.alloc.allocateDirectBuffer(Math.max(i3, this.chunkSize));
    }

    public final AllocatedBuffer newHeapBuffer(int i3) {
        return this.alloc.allocateHeapBuffer(Math.max(i3, this.chunkSize));
    }

    @Deprecated
    public final void writeGroupList(int i3, List<?> list, Schema schema) throws IOException {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i3, list.get(size), schema);
        }
    }

    public final void writeMessageList(int i3, List<?> list, Schema schema) throws IOException {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i3, list.get(size), schema);
        }
    }

    private final void writeBoolList_Internal(int i3, BooleanArrayList booleanArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace(booleanArrayList.size() + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = booleanArrayList.size() - 1; size >= 0; size--) {
                writeBool(booleanArrayList.getBoolean(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = booleanArrayList.size() - 1; size2 >= 0; size2--) {
            writeBool(i3, booleanArrayList.getBoolean(size2));
        }
    }

    private final void writeDoubleList_Internal(int i3, DoubleArrayList doubleArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((doubleArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = doubleArrayList.size() - 1; size >= 0; size--) {
                writeFixed64(Double.doubleToRawLongBits(doubleArrayList.getDouble(size)));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = doubleArrayList.size() - 1; size2 >= 0; size2--) {
            writeDouble(i3, doubleArrayList.getDouble(size2));
        }
    }

    private final void writeFixed32List_Internal(int i3, IntArrayList intArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((intArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeFixed32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeFixed32(i3, intArrayList.getInt(size2));
        }
    }

    private final void writeFixed64List_Internal(int i3, LongArrayList longArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((longArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeFixed64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeFixed64(i3, longArrayList.getLong(size2));
        }
    }

    private final void writeFloatList_Internal(int i3, FloatArrayList floatArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((floatArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = floatArrayList.size() - 1; size >= 0; size--) {
                writeFixed32(Float.floatToRawIntBits(floatArrayList.getFloat(size)));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = floatArrayList.size() - 1; size2 >= 0; size2--) {
            writeFloat(i3, floatArrayList.getFloat(size2));
        }
    }

    private final void writeInt32List_Internal(int i3, IntArrayList intArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((intArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeInt32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeInt32(i3, intArrayList.getInt(size2));
        }
    }

    private final void writeSInt32List_Internal(int i3, IntArrayList intArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeSInt32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeSInt32(i3, intArrayList.getInt(size2));
        }
    }

    private final void writeSInt64List_Internal(int i3, LongArrayList longArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeSInt64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeSInt64(i3, longArrayList.getLong(size2));
        }
    }

    private final void writeUInt32List_Internal(int i3, IntArrayList intArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeVarint32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeUInt32(i3, intArrayList.getInt(size2));
        }
    }

    private final void writeUInt64List_Internal(int i3, LongArrayList longArrayList, boolean z2) throws IOException {
        if (z2) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeVarint64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i3, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeUInt64(i3, longArrayList.getLong(size2));
        }
    }
}
