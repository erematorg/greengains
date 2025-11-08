package com.google.protobuf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.google.common.base.Ascii;
import com.google.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    private static volatile int defaultRecursionLimit = 100;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    public static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private void recomputeBufferSizeAfterLimit() {
            int i3 = this.limit + this.bufferSizeAfterLimit;
            this.limit = i3;
            int i4 = i3 - this.startPos;
            int i5 = this.currentLimit;
            if (i4 > i5) {
                int i6 = i4 - i5;
                this.bufferSizeAfterLimit = i6;
                this.limit = i3 - i6;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private void skipRawVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                byte[] bArr = this.buffer;
                int i4 = this.pos;
                this.pos = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                if (readRawByte() < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public void checkLastTagWas(int i3) throws InvalidProtocolBufferException {
            if (this.lastTag != i3) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public void enableAliasing(boolean z2) {
            this.enableAliasing = z2;
        }

        public int getBytesUntilLimit() {
            int i3 = this.currentLimit;
            if (i3 == Integer.MAX_VALUE) {
                return -1;
            }
            return i3 - getTotalBytesRead();
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public void popLimit(int i3) {
            this.currentLimit = i3;
            recomputeBufferSizeAfterLimit();
        }

        public int pushLimit(int i3) throws InvalidProtocolBufferException {
            if (i3 >= 0) {
                int totalBytesRead = i3 + getTotalBytesRead();
                if (totalBytesRead >= 0) {
                    int i4 = this.currentLimit;
                    if (totalBytesRead <= i4) {
                        this.currentLimit = totalBytesRead;
                        recomputeBufferSizeAfterLimit();
                        return i4;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.parseFailure();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i3 = this.limit;
                int i4 = this.pos;
                if (readRawVarint32 <= i3 - i4) {
                    ByteBuffer wrap = (this.immutable || !this.enableAliasing) ? ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i4, i4 + readRawVarint32)) : ByteBuffer.wrap(this.buffer, i4, readRawVarint32).slice();
                    this.pos += readRawVarint32;
                    return wrap;
                }
            }
            if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i3 = this.limit;
                int i4 = this.pos;
                if (readRawVarint32 <= i3 - i4) {
                    ByteString copyFrom = (!this.immutable || !this.enableAliasing) ? ByteString.copyFrom(this.buffer, i4, readRawVarint32) : ByteString.wrap(this.buffer, i4, readRawVarint32);
                    this.pos += readRawVarint32;
                    return copyFrom;
                }
            }
            return readRawVarint32 == 0 ? ByteString.EMPTY : ByteString.wrap(readRawBytes(readRawVarint32));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public void readGroup(int i3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte readRawByte() throws IOException {
            int i3 = this.pos;
            if (i3 != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i3 + 1;
                return bArr[i3];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int i3) throws IOException {
            if (i3 > 0) {
                int i4 = this.limit;
                int i5 = this.pos;
                if (i3 <= i4 - i5) {
                    int i6 = i3 + i5;
                    this.pos = i6;
                    return Arrays.copyOfRange(this.buffer, i5, i6);
                }
            }
            if (i3 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i3 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public int readRawLittleEndian32() throws IOException {
            int i3 = this.pos;
            if (this.limit - i3 >= 4) {
                byte[] bArr = this.buffer;
                this.pos = i3 + 4;
                return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            int i3 = this.pos;
            if (this.limit - i3 >= 8) {
                byte[] bArr = this.buffer;
                this.pos = i3 + 8;
                return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readRawVarint32() throws IOException {
            byte b3;
            byte b4;
            int i3 = this.pos;
            int i4 = this.limit;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b5 = bArr[i3];
                if (b5 >= 0) {
                    this.pos = i5;
                    return b5;
                } else if (i4 - i5 >= 9) {
                    int i6 = i3 + 2;
                    byte b6 = (bArr[i5] << 7) ^ b5;
                    if (b6 < 0) {
                        b3 = b6 ^ Byte.MIN_VALUE;
                    } else {
                        int i7 = i3 + 3;
                        byte b7 = (bArr[i6] << Ascii.SO) ^ b6;
                        if (b7 >= 0) {
                            b4 = b7 ^ 16256;
                        } else {
                            int i8 = i3 + 4;
                            byte b8 = b7 ^ (bArr[i7] << Ascii.NAK);
                            if (b8 < 0) {
                                b3 = -2080896 ^ b8;
                            } else {
                                i7 = i3 + 5;
                                byte b9 = bArr[i8];
                                byte b10 = (b8 ^ (b9 << Ascii.FS)) ^ 266354560;
                                if (b9 < 0) {
                                    i8 = i3 + 6;
                                    if (bArr[i7] < 0) {
                                        i7 = i3 + 7;
                                        if (bArr[i8] < 0) {
                                            i8 = i3 + 8;
                                            if (bArr[i7] < 0) {
                                                i7 = i3 + 9;
                                                if (bArr[i8] < 0) {
                                                    int i9 = i3 + 10;
                                                    if (bArr[i7] >= 0) {
                                                        byte b11 = b10;
                                                        i6 = i9;
                                                        b3 = b11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    b3 = b10;
                                }
                                b4 = b10;
                            }
                            i6 = i8;
                        }
                        i6 = i7;
                    }
                    this.pos = i6;
                    return b3;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() throws IOException {
            long j2;
            long j3;
            long j4;
            int i3 = this.pos;
            int i4 = this.limit;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b3 = bArr[i3];
                if (b3 >= 0) {
                    this.pos = i5;
                    return (long) b3;
                } else if (i4 - i5 >= 9) {
                    int i6 = i3 + 2;
                    byte b4 = (bArr[i5] << 7) ^ b3;
                    if (b4 < 0) {
                        j2 = (long) (b4 ^ Byte.MIN_VALUE);
                    } else {
                        int i7 = i3 + 3;
                        byte b5 = (bArr[i6] << Ascii.SO) ^ b4;
                        if (b5 >= 0) {
                            j2 = (long) (b5 ^ 16256);
                            i6 = i7;
                        } else {
                            int i8 = i3 + 4;
                            byte b6 = b5 ^ (bArr[i7] << Ascii.NAK);
                            if (b6 < 0) {
                                i6 = i8;
                                j2 = (long) (-2080896 ^ b6);
                            } else {
                                long j5 = (long) b6;
                                int i9 = i3 + 5;
                                long j6 = j5 ^ (((long) bArr[i8]) << 28);
                                if (j6 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    int i10 = i3 + 6;
                                    long j7 = j6 ^ (((long) bArr[i9]) << 35);
                                    if (j7 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        i9 = i3 + 7;
                                        j6 = j7 ^ (((long) bArr[i10]) << 42);
                                        if (j6 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            i10 = i3 + 8;
                                            j7 = j6 ^ (((long) bArr[i9]) << 49);
                                            if (j7 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                i6 = i3 + 9;
                                                long j8 = (j7 ^ (((long) bArr[i10]) << 56)) ^ 71499008037633920L;
                                                if (j8 < 0) {
                                                    int i11 = i3 + 10;
                                                    if (((long) bArr[i6]) >= 0) {
                                                        i6 = i11;
                                                    }
                                                }
                                                j2 = j8;
                                            }
                                        }
                                    }
                                    j2 = j7 ^ j3;
                                    i6 = i10;
                                }
                                j2 = j6 ^ j4;
                            }
                        }
                    }
                    this.pos = i6;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() throws IOException {
            long j2 = 0;
            for (int i3 = 0; i3 < 64; i3 += 7) {
                byte readRawByte = readRawByte();
                j2 |= ((long) (readRawByte & Byte.MAX_VALUE)) << i3;
                if ((readRawByte & 128) == 0) {
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i3 = this.limit;
                int i4 = this.pos;
                if (readRawVarint32 <= i3 - i4) {
                    String str = new String(this.buffer, i4, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i3 = this.limit;
                int i4 = this.pos;
                if (readRawVarint32 <= i3 - i4) {
                    String decodeUtf8 = Utf8.decodeUtf8(this.buffer, i4, readRawVarint32);
                    this.pos += readRawVarint32;
                    return decodeUtf8;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Deprecated
        public void readUnknownGroup(int i3, MessageLite.Builder builder) throws IOException {
            readGroup(i3, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public boolean skipField(int i3) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.skipMessage():void");
        }

        public void skipRawBytes(int i3) throws IOException {
            if (i3 >= 0) {
                int i4 = this.limit;
                int i5 = this.pos;
                if (i3 <= i4 - i5) {
                    this.pos = i5 + i3;
                    return;
                }
            }
            if (i3 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private ArrayDecoder(byte[] bArr, int i3, int i4, boolean z2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i4 + i3;
            this.pos = i3;
            this.startPos = i3;
            this.immutable = z2;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r2) throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0, r2)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public <T extends MessageLite> T readGroup(int i3, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
            return t2;
        }

        public boolean skipField(int i3, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeUInt32NoTag(i3);
                skipMessage(codedOutputStream);
                int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4);
                checkLastTagWas(makeTag);
                codedOutputStream.writeUInt32NoTag(makeTag);
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeUInt32NoTag(i3);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return t2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private final Iterable<ByteBuffer> input;
        private final Iterator<ByteBuffer> iterator;
        private int lastTag;
        private int startOffset;
        private int totalBufferSize;
        private int totalBytesRead;

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if (this.iterator.hasNext()) {
                tryGetNextByteBuffer();
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private void readRawBytesTo(byte[] bArr, int i3, int i4) throws IOException {
            if (i4 >= 0 && i4 <= remaining()) {
                int i5 = i4;
                while (i5 > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(i5, (int) currentRemaining());
                    long j2 = (long) min;
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, (long) ((i4 - i5) + i3), j2);
                    i5 -= min;
                    this.currentByteBufferPos += j2;
                }
            } else if (i4 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i4 != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            int i3 = this.totalBufferSize + this.bufferSizeAfterCurrentLimit;
            this.totalBufferSize = i3;
            int i4 = i3 - this.startOffset;
            int i5 = this.currentLimit;
            if (i4 > i5) {
                int i6 = i4 - i5;
                this.bufferSizeAfterCurrentLimit = i6;
                this.totalBufferSize = i3 - i6;
                return;
            }
            this.bufferSizeAfterCurrentLimit = 0;
        }

        private int remaining() {
            return (int) ((((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private void skipRawVarint() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                if (readRawByte() < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
            throw r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0021, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
            throw com.google.protobuf.InvalidProtocolBufferException.truncatedMessage();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
            r2.position(r0);
            r2.limit(r1);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0023 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.nio.ByteBuffer slice(int r4, int r5) throws java.io.IOException {
            /*
                r3 = this;
                java.nio.ByteBuffer r0 = r3.currentByteBuffer
                int r0 = r0.position()
                java.nio.ByteBuffer r1 = r3.currentByteBuffer
                int r1 = r1.limit()
                java.nio.ByteBuffer r2 = r3.currentByteBuffer
                r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0023 }
                r2.limit(r5)     // Catch:{ IllegalArgumentException -> 0x0023 }
                java.nio.ByteBuffer r3 = r3.currentByteBuffer     // Catch:{ IllegalArgumentException -> 0x0023 }
                java.nio.ByteBuffer r3 = r3.slice()     // Catch:{ IllegalArgumentException -> 0x0023 }
                r2.position(r0)
                r2.limit(r1)
                return r3
            L_0x0021:
                r3 = move-exception
                goto L_0x0028
            L_0x0023:
                com.google.protobuf.InvalidProtocolBufferException r3 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()     // Catch:{ all -> 0x0021 }
                throw r3     // Catch:{ all -> 0x0021 }
            L_0x0028:
                r2.position(r0)
                r2.limit(r1)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.slice(int, int):java.nio.ByteBuffer");
        }

        private void tryGetNextByteBuffer() {
            ByteBuffer next = this.iterator.next();
            this.currentByteBuffer = next;
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            long position = (long) next.position();
            this.currentByteBufferPos = position;
            this.currentByteBufferStartPos = position;
            this.currentByteBufferLimit = (long) this.currentByteBuffer.limit();
            long addressOffset = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentAddress = addressOffset;
            this.currentByteBufferPos += addressOffset;
            this.currentByteBufferStartPos += addressOffset;
            this.currentByteBufferLimit += addressOffset;
        }

        public void checkLastTagWas(int i3) throws InvalidProtocolBufferException {
            if (this.lastTag != i3) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public void enableAliasing(boolean z2) {
            this.enableAliasing = z2;
        }

        public int getBytesUntilLimit() {
            int i3 = this.currentLimit;
            if (i3 == Integer.MAX_VALUE) {
                return -1;
            }
            return i3 - getTotalBytesRead();
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public int getTotalBytesRead() {
            return (int) ((((long) (this.totalBytesRead - this.startOffset)) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public boolean isAtEnd() throws IOException {
            return (((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos == ((long) this.totalBufferSize);
        }

        public void popLimit(int i3) {
            this.currentLimit = i3;
            recomputeBufferSizeAfterLimit();
        }

        public int pushLimit(int i3) throws InvalidProtocolBufferException {
            if (i3 >= 0) {
                int totalBytesRead2 = i3 + getTotalBytesRead();
                int i4 = this.currentLimit;
                if (totalBytesRead2 <= i4) {
                    this.currentLimit = totalBytesRead2;
                    recomputeBufferSizeAfterLimit();
                    return i4;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j2 = (long) readRawVarint32;
                if (j2 <= currentRemaining()) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[readRawVarint32];
                        UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0, j2);
                        this.currentByteBufferPos += j2;
                        return ByteBuffer.wrap(bArr);
                    }
                    long j3 = this.currentByteBufferPos + j2;
                    this.currentByteBufferPos = j3;
                    long j4 = this.currentAddress;
                    return slice((int) ((j3 - j4) - j2), (int) (j3 - j4));
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return ByteBuffer.wrap(bArr2);
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j2 = (long) readRawVarint32;
                long j3 = this.currentByteBufferLimit;
                long j4 = this.currentByteBufferPos;
                if (j2 <= j3 - j4) {
                    if (!this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[readRawVarint32];
                        UnsafeUtil.copyMemory(j4, bArr, 0, j2);
                        this.currentByteBufferPos += j2;
                        return ByteString.wrap(bArr);
                    }
                    int i3 = (int) (j4 - this.currentAddress);
                    ByteString wrap = ByteString.wrap(slice(i3, readRawVarint32 + i3));
                    this.currentByteBufferPos += j2;
                    return wrap;
                }
            }
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return ByteString.wrap(bArr2);
            } else {
                ArrayList arrayList = new ArrayList();
                while (readRawVarint32 > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(readRawVarint32, (int) currentRemaining());
                    int i4 = (int) (this.currentByteBufferPos - this.currentAddress);
                    arrayList.add(ByteString.wrap(slice(i4, i4 + min)));
                    readRawVarint32 -= min;
                    this.currentByteBufferPos += (long) min;
                }
                return ByteString.copyFrom((Iterable<ByteString>) arrayList);
            }
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public void readGroup(int i3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte readRawByte() throws IOException {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j2 = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j2;
            return UnsafeUtil.getByte(j2);
        }

        public byte[] readRawBytes(int i3) throws IOException {
            if (i3 >= 0) {
                long j2 = (long) i3;
                if (j2 <= currentRemaining()) {
                    byte[] bArr = new byte[i3];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0, j2);
                    this.currentByteBufferPos += j2;
                    return bArr;
                }
            }
            if (i3 >= 0 && i3 <= remaining()) {
                byte[] bArr2 = new byte[i3];
                readRawBytesTo(bArr2, 0, i3);
                return bArr2;
            } else if (i3 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i3 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public int readRawLittleEndian32() throws IOException {
            if (currentRemaining() >= 4) {
                long j2 = this.currentByteBufferPos;
                this.currentByteBufferPos = 4 + j2;
                return (UnsafeUtil.getByte(j2) & 255) | ((UnsafeUtil.getByte(1 + j2) & 255) << 8) | ((UnsafeUtil.getByte(2 + j2) & 255) << 16) | ((UnsafeUtil.getByte(j2 + 3) & 255) << Ascii.CAN);
            }
            return ((readRawByte() & 255) << Ascii.CAN) | (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16);
        }

        public long readRawLittleEndian64() throws IOException {
            if (currentRemaining() >= 8) {
                long j2 = this.currentByteBufferPos;
                this.currentByteBufferPos = 8 + j2;
                return (((long) UnsafeUtil.getByte(j2)) & 255) | ((((long) UnsafeUtil.getByte(1 + j2)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j2)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j2)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j2)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j2)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j2)) & 255) << 48) | ((((long) UnsafeUtil.getByte(j2 + 7)) & 255) << 56);
            }
            return ((((long) readRawByte()) & 255) << 56) | (((long) readRawByte()) & 255) | ((((long) readRawByte()) & 255) << 8) | ((((long) readRawByte()) & 255) << 16) | ((((long) readRawByte()) & 255) << 24) | ((((long) readRawByte()) & 255) << 32) | ((((long) readRawByte()) & 255) << 40) | ((((long) readRawByte()) & 255) << 48);
        }

        public int readRawVarint32() throws IOException {
            byte b3;
            byte b4;
            long j2 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j2) {
                long j3 = j2 + 1;
                byte b5 = UnsafeUtil.getByte(j2);
                if (b5 >= 0) {
                    this.currentByteBufferPos++;
                    return b5;
                } else if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j4 = 2 + j2;
                    byte b6 = (UnsafeUtil.getByte(j3) << 7) ^ b5;
                    if (b6 < 0) {
                        b3 = b6 ^ Byte.MIN_VALUE;
                    } else {
                        long j5 = 3 + j2;
                        byte b7 = (UnsafeUtil.getByte(j4) << Ascii.SO) ^ b6;
                        if (b7 >= 0) {
                            b4 = b7 ^ 16256;
                        } else {
                            long j6 = 4 + j2;
                            byte b8 = b7 ^ (UnsafeUtil.getByte(j5) << Ascii.NAK);
                            if (b8 < 0) {
                                b3 = -2080896 ^ b8;
                            } else {
                                j5 = 5 + j2;
                                byte b9 = UnsafeUtil.getByte(j6);
                                byte b10 = (b8 ^ (b9 << Ascii.FS)) ^ 266354560;
                                if (b9 < 0) {
                                    j6 = 6 + j2;
                                    if (UnsafeUtil.getByte(j5) < 0) {
                                        j5 = 7 + j2;
                                        if (UnsafeUtil.getByte(j6) < 0) {
                                            j6 = 8 + j2;
                                            if (UnsafeUtil.getByte(j5) < 0) {
                                                j5 = 9 + j2;
                                                if (UnsafeUtil.getByte(j6) < 0) {
                                                    long j7 = j2 + 10;
                                                    if (UnsafeUtil.getByte(j5) >= 0) {
                                                        long j8 = j7;
                                                        b3 = b10;
                                                        j4 = j8;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    b3 = b10;
                                }
                                b4 = b10;
                            }
                            j4 = j6;
                        }
                        j4 = j5;
                    }
                    this.currentByteBufferPos = j4;
                    return b3;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() throws IOException {
            long j2;
            long j3;
            long j4;
            long j5 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j5) {
                long j6 = j5 + 1;
                byte b3 = UnsafeUtil.getByte(j5);
                if (b3 >= 0) {
                    this.currentByteBufferPos++;
                    return (long) b3;
                } else if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j7 = 2 + j5;
                    byte b4 = (UnsafeUtil.getByte(j6) << 7) ^ b3;
                    if (b4 < 0) {
                        j2 = (long) (b4 ^ Byte.MIN_VALUE);
                    } else {
                        long j8 = 3 + j5;
                        byte b5 = (UnsafeUtil.getByte(j7) << Ascii.SO) ^ b4;
                        if (b5 >= 0) {
                            j2 = (long) (b5 ^ 16256);
                            j7 = j8;
                        } else {
                            long j9 = 4 + j5;
                            byte b6 = b5 ^ (UnsafeUtil.getByte(j8) << Ascii.NAK);
                            if (b6 < 0) {
                                j2 = (long) (-2080896 ^ b6);
                                j7 = j9;
                            } else {
                                long j10 = 5 + j5;
                                long j11 = (((long) UnsafeUtil.getByte(j9)) << 28) ^ ((long) b6);
                                if (j11 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    long j12 = 6 + j5;
                                    long j13 = j11 ^ (((long) UnsafeUtil.getByte(j10)) << 35);
                                    if (j13 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        j10 = 7 + j5;
                                        j11 = j13 ^ (((long) UnsafeUtil.getByte(j12)) << 42);
                                        if (j11 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            j12 = 8 + j5;
                                            j13 = j11 ^ (((long) UnsafeUtil.getByte(j10)) << 49);
                                            if (j13 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                j10 = 9 + j5;
                                                long j14 = (j13 ^ (((long) UnsafeUtil.getByte(j12)) << 56)) ^ 71499008037633920L;
                                                if (j14 < 0) {
                                                    long j15 = j5 + 10;
                                                    if (((long) UnsafeUtil.getByte(j10)) >= 0) {
                                                        long j16 = j15;
                                                        j2 = j14;
                                                        j7 = j16;
                                                    }
                                                } else {
                                                    j2 = j14;
                                                    j7 = j10;
                                                }
                                            }
                                        }
                                    }
                                    j2 = j3 ^ j13;
                                    j7 = j12;
                                }
                                j2 = j4 ^ j11;
                                j7 = j10;
                            }
                        }
                    }
                    this.currentByteBufferPos = j7;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() throws IOException {
            long j2 = 0;
            for (int i3 = 0; i3 < 64; i3 += 7) {
                byte readRawByte = readRawByte();
                j2 |= ((long) (readRawByte & Byte.MAX_VALUE)) << i3;
                if ((readRawByte & 128) == 0) {
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j2 = (long) readRawVarint32;
                long j3 = this.currentByteBufferLimit;
                long j4 = this.currentByteBufferPos;
                if (j2 <= j3 - j4) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(j4, bArr, 0, j2);
                    String str = new String(bArr, Internal.UTF_8);
                    this.currentByteBufferPos += j2;
                    return str;
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return new String(bArr2, Internal.UTF_8);
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j2 = (long) readRawVarint32;
                long j3 = this.currentByteBufferLimit;
                long j4 = this.currentByteBufferPos;
                if (j2 <= j3 - j4) {
                    String decodeUtf8 = Utf8.decodeUtf8(this.currentByteBuffer, (int) (j4 - this.currentByteBufferStartPos), readRawVarint32);
                    this.currentByteBufferPos += j2;
                    return decodeUtf8;
                }
            }
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                readRawBytesTo(bArr, 0, readRawVarint32);
                return Utf8.decodeUtf8(bArr, 0, readRawVarint32);
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Deprecated
        public void readUnknownGroup(int i3, MessageLite.Builder builder) throws IOException {
            readGroup(i3, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void resetSizeCounter() {
            this.startOffset = (int) ((((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public boolean skipField(int i3) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.skipMessage():void");
        }

        public void skipRawBytes(int i3) throws IOException {
            if (i3 >= 0 && ((long) i3) <= (((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                while (i3 > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(i3, (int) currentRemaining());
                    i3 -= min;
                    this.currentByteBufferPos += (long) min;
                }
            } else if (i3 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> iterable, int i3, boolean z2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.totalBufferSize = i3;
            this.input = iterable;
            this.iterator = iterable.iterator();
            this.immutable = z2;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if (i3 == 0) {
                this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
                this.currentByteBufferPos = 0;
                this.currentByteBufferStartPos = 0;
                this.currentByteBufferLimit = 0;
                this.currentAddress = 0;
                return;
            }
            tryGetNextByteBuffer();
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r2) throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0, r2)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public <T extends MessageLite> T readGroup(int i3, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
            return t2;
        }

        public boolean skipField(int i3, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeUInt32NoTag(i3);
                skipMessage(codedOutputStream);
                int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4);
                checkLastTagWas(makeTag);
                codedOutputStream.writeUInt32NoTag(makeTag);
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeUInt32NoTag(i3);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return t2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public static final class StreamDecoder extends CodedInputStream {
        /* access modifiers changed from: private */
        public final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        /* access modifiers changed from: private */
        public int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        public interface RefillCallback {
            void onRefill();
        }

        public class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos;

            private SkippedDataSink() {
                this.lastPos = StreamDecoder.this.pos;
            }

            public ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }

            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }
        }

        private static int available(InputStream inputStream) throws IOException {
            try {
                return inputStream.available();
            } catch (InvalidProtocolBufferException e3) {
                e3.setThrownFromInputStream();
                throw e3;
            }
        }

        private static int read(InputStream inputStream, byte[] bArr, int i3, int i4) throws IOException {
            try {
                return inputStream.read(bArr, i3, i4);
            } catch (InvalidProtocolBufferException e3) {
                e3.setThrownFromInputStream();
                throw e3;
            }
        }

        private ByteString readBytesSlowPath(int i3) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i3);
            if (readRawBytesSlowPathOneChunk != null) {
                return ByteString.copyFrom(readRawBytesSlowPathOneChunk);
            }
            int i4 = this.pos;
            int i5 = this.bufferSize;
            int i6 = i5 - i4;
            this.totalBytesRetired += i5;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i3 - i6);
            byte[] bArr = new byte[i3];
            System.arraycopy(this.buffer, i4, bArr, 0, i6);
            for (byte[] next : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(next, 0, bArr, i6, next.length);
                i6 += next.length;
            }
            return ByteString.wrap(bArr);
        }

        private byte[] readRawBytesSlowPath(int i3, boolean z2) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i3);
            if (readRawBytesSlowPathOneChunk != null) {
                return z2 ? (byte[]) readRawBytesSlowPathOneChunk.clone() : readRawBytesSlowPathOneChunk;
            }
            int i4 = this.pos;
            int i5 = this.bufferSize;
            int i6 = i5 - i4;
            this.totalBytesRetired += i5;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i3 - i6);
            byte[] bArr = new byte[i3];
            System.arraycopy(this.buffer, i4, bArr, 0, i6);
            for (byte[] next : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(next, 0, bArr, i6, next.length);
                i6 += next.length;
            }
            return bArr;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i3) throws IOException {
            if (i3 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i3 >= 0) {
                int i4 = this.totalBytesRetired;
                int i5 = this.pos;
                int i6 = i4 + i5 + i3;
                if (i6 - this.sizeLimit <= 0) {
                    int i7 = this.currentLimit;
                    if (i6 <= i7) {
                        int i8 = this.bufferSize - i5;
                        int i9 = i3 - i8;
                        if (i9 >= 4096 && i9 > available(this.input)) {
                            return null;
                        }
                        byte[] bArr = new byte[i3];
                        System.arraycopy(this.buffer, this.pos, bArr, 0, i8);
                        this.totalBytesRetired += this.bufferSize;
                        this.pos = 0;
                        this.bufferSize = 0;
                        while (i8 < i3) {
                            int read = read(this.input, bArr, i8, i3 - i8);
                            if (read != -1) {
                                this.totalBytesRetired += read;
                                i8 += read;
                            } else {
                                throw InvalidProtocolBufferException.truncatedMessage();
                            }
                        }
                        return bArr;
                    }
                    skipRawBytes((i7 - i4) - i5);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int i3) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i3 > 0) {
                int min = Math.min(i3, 4096);
                byte[] bArr = new byte[min];
                int i4 = 0;
                while (i4 < min) {
                    int read = this.input.read(bArr, i4, min - i4);
                    if (read != -1) {
                        this.totalBytesRetired += read;
                        i4 += read;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                i3 -= min;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private void recomputeBufferSizeAfterLimit() {
            int i3 = this.bufferSize + this.bufferSizeAfterLimit;
            this.bufferSize = i3;
            int i4 = this.totalBytesRetired + i3;
            int i5 = this.currentLimit;
            if (i4 > i5) {
                int i6 = i4 - i5;
                this.bufferSizeAfterLimit = i6;
                this.bufferSize = i3 - i6;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private void refillBuffer(int i3) throws IOException {
            if (tryRefillBuffer(i3)) {
                return;
            }
            if (i3 > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private static long skip(InputStream inputStream, long j2) throws IOException {
            try {
                return inputStream.skip(j2);
            } catch (InvalidProtocolBufferException e3) {
                e3.setThrownFromInputStream();
                throw e3;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
            throw new java.lang.IllegalStateException(r8.input.getClass() + "#skip returned invalid result: " + r0 + "\nThe InputStream implementation is buggy.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void skipRawBytesSlowPath(int r9) throws java.io.IOException {
            /*
                r8 = this;
                if (r9 < 0) goto L_0x0097
                int r0 = r8.totalBytesRetired
                int r1 = r8.pos
                int r2 = r0 + r1
                int r2 = r2 + r9
                int r3 = r8.currentLimit
                if (r2 > r3) goto L_0x008d
                com.google.protobuf.CodedInputStream$StreamDecoder$RefillCallback r2 = r8.refillCallback
                r3 = 0
                if (r2 != 0) goto L_0x006f
                int r0 = r0 + r1
                r8.totalBytesRetired = r0
                int r0 = r8.bufferSize
                int r0 = r0 - r1
                r8.bufferSize = r3
                r8.pos = r3
                r3 = r0
            L_0x001d:
                if (r3 >= r9) goto L_0x0067
                int r0 = r9 - r3
                java.io.InputStream r1 = r8.input     // Catch:{ all -> 0x005d }
                long r4 = (long) r0     // Catch:{ all -> 0x005d }
                long r0 = skip(r1, r4)     // Catch:{ all -> 0x005d }
                r6 = 0
                int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                if (r2 < 0) goto L_0x0038
                int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r4 > 0) goto L_0x0038
                if (r2 != 0) goto L_0x0035
                goto L_0x0067
            L_0x0035:
                int r0 = (int) r0     // Catch:{ all -> 0x005d }
                int r3 = r3 + r0
                goto L_0x001d
            L_0x0038:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x005d }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
                r2.<init>()     // Catch:{ all -> 0x005d }
                java.io.InputStream r4 = r8.input     // Catch:{ all -> 0x005d }
                java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x005d }
                r2.append(r4)     // Catch:{ all -> 0x005d }
                java.lang.String r4 = "#skip returned invalid result: "
                r2.append(r4)     // Catch:{ all -> 0x005d }
                r2.append(r0)     // Catch:{ all -> 0x005d }
                java.lang.String r0 = "\nThe InputStream implementation is buggy."
                r2.append(r0)     // Catch:{ all -> 0x005d }
                java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x005d }
                r9.<init>(r0)     // Catch:{ all -> 0x005d }
                throw r9     // Catch:{ all -> 0x005d }
            L_0x005d:
                r9 = move-exception
                int r0 = r8.totalBytesRetired
                int r0 = r0 + r3
                r8.totalBytesRetired = r0
                r8.recomputeBufferSizeAfterLimit()
                throw r9
            L_0x0067:
                int r0 = r8.totalBytesRetired
                int r0 = r0 + r3
                r8.totalBytesRetired = r0
                r8.recomputeBufferSizeAfterLimit()
            L_0x006f:
                if (r3 >= r9) goto L_0x008c
                int r0 = r8.bufferSize
                int r1 = r8.pos
                int r1 = r0 - r1
                r8.pos = r0
                r0 = 1
                r8.refillBuffer(r0)
            L_0x007d:
                int r2 = r9 - r1
                int r3 = r8.bufferSize
                if (r2 <= r3) goto L_0x008a
                int r1 = r1 + r3
                r8.pos = r3
                r8.refillBuffer(r0)
                goto L_0x007d
            L_0x008a:
                r8.pos = r2
            L_0x008c:
                return
            L_0x008d:
                int r3 = r3 - r0
                int r3 = r3 - r1
                r8.skipRawBytes(r3)
                com.google.protobuf.InvalidProtocolBufferException r8 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
                throw r8
            L_0x0097:
                com.google.protobuf.InvalidProtocolBufferException r8 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipRawBytesSlowPath(int):void");
        }

        private void skipRawVarint() throws IOException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                byte[] bArr = this.buffer;
                int i4 = this.pos;
                this.pos = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                if (readRawByte() < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private boolean tryRefillBuffer(int i3) throws IOException {
            int i4 = this.pos;
            if (i4 + i3 > this.bufferSize) {
                int i5 = this.sizeLimit;
                int i6 = this.totalBytesRetired;
                if (i3 > (i5 - i6) - i4 || i6 + i4 + i3 > this.currentLimit) {
                    return false;
                }
                RefillCallback refillCallback2 = this.refillCallback;
                if (refillCallback2 != null) {
                    refillCallback2.onRefill();
                }
                int i7 = this.pos;
                if (i7 > 0) {
                    int i8 = this.bufferSize;
                    if (i8 > i7) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, i7, bArr, 0, i8 - i7);
                    }
                    this.totalBytesRetired += i7;
                    this.bufferSize -= i7;
                    this.pos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int i9 = this.bufferSize;
                int read = read(inputStream, bArr2, i9, Math.min(bArr2.length - i9, (this.sizeLimit - this.totalBytesRetired) - i9));
                if (read == 0 || read < -1 || read > this.buffer.length) {
                    throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read <= 0) {
                    return false;
                } else {
                    this.bufferSize += read;
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= i3) {
                        return true;
                    }
                    return tryRefillBuffer(i3);
                }
            } else {
                throw new IllegalStateException(C0118y.c(i3, "refillBuffer() called when ", " bytes were already available in buffer"));
            }
        }

        public void checkLastTagWas(int i3) throws InvalidProtocolBufferException {
            if (this.lastTag != i3) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public void enableAliasing(boolean z2) {
        }

        public int getBytesUntilLimit() {
            int i3 = this.currentLimit;
            if (i3 == Integer.MAX_VALUE) {
                return -1;
            }
            return i3 - (this.totalBytesRetired + this.pos);
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        public void popLimit(int i3) {
            this.currentLimit = i3;
            recomputeBufferSizeAfterLimit();
        }

        public int pushLimit(int i3) throws InvalidProtocolBufferException {
            if (i3 >= 0) {
                int i4 = this.totalBytesRetired + this.pos + i3;
                int i5 = this.currentLimit;
                if (i4 <= i5) {
                    this.currentLimit = i4;
                    recomputeBufferSizeAfterLimit();
                    return i5;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public byte[] readByteArray() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i3 = this.bufferSize;
            int i4 = this.pos;
            if (readRawVarint32 > i3 - i4 || readRawVarint32 <= 0) {
                return readRawBytesSlowPath(readRawVarint32, false);
            }
            byte[] copyOfRange = Arrays.copyOfRange(this.buffer, i4, i4 + readRawVarint32);
            this.pos += readRawVarint32;
            return copyOfRange;
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i3 = this.bufferSize;
            int i4 = this.pos;
            if (readRawVarint32 > i3 - i4 || readRawVarint32 <= 0) {
                return readRawVarint32 == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32, true));
            }
            ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i4, i4 + readRawVarint32));
            this.pos += readRawVarint32;
            return wrap;
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i3 = this.bufferSize;
            int i4 = this.pos;
            if (readRawVarint32 > i3 - i4 || readRawVarint32 <= 0) {
                return readRawVarint32 == 0 ? ByteString.EMPTY : readBytesSlowPath(readRawVarint32);
            }
            ByteString copyFrom = ByteString.copyFrom(this.buffer, i4, readRawVarint32);
            this.pos += readRawVarint32;
            return copyFrom;
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public void readGroup(int i3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 + 1;
            return bArr[i3];
        }

        public byte[] readRawBytes(int i3) throws IOException {
            int i4 = this.pos;
            if (i3 > this.bufferSize - i4 || i3 <= 0) {
                return readRawBytesSlowPath(i3, false);
            }
            int i5 = i3 + i4;
            this.pos = i5;
            return Arrays.copyOfRange(this.buffer, i4, i5);
        }

        public int readRawLittleEndian32() throws IOException {
            int i3 = this.pos;
            if (this.bufferSize - i3 < 4) {
                refillBuffer(4);
                i3 = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i3 + 4;
            return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
        }

        public long readRawLittleEndian64() throws IOException {
            int i3 = this.pos;
            if (this.bufferSize - i3 < 8) {
                refillBuffer(8);
                i3 = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i3 + 8;
            return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
        }

        public int readRawVarint32() throws IOException {
            byte b3;
            byte b4;
            int i3 = this.pos;
            int i4 = this.bufferSize;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b5 = bArr[i3];
                if (b5 >= 0) {
                    this.pos = i5;
                    return b5;
                } else if (i4 - i5 >= 9) {
                    int i6 = i3 + 2;
                    byte b6 = (bArr[i5] << 7) ^ b5;
                    if (b6 < 0) {
                        b3 = b6 ^ Byte.MIN_VALUE;
                    } else {
                        int i7 = i3 + 3;
                        byte b7 = (bArr[i6] << Ascii.SO) ^ b6;
                        if (b7 >= 0) {
                            b4 = b7 ^ 16256;
                        } else {
                            int i8 = i3 + 4;
                            byte b8 = b7 ^ (bArr[i7] << Ascii.NAK);
                            if (b8 < 0) {
                                b3 = -2080896 ^ b8;
                            } else {
                                i7 = i3 + 5;
                                byte b9 = bArr[i8];
                                byte b10 = (b8 ^ (b9 << Ascii.FS)) ^ 266354560;
                                if (b9 < 0) {
                                    i8 = i3 + 6;
                                    if (bArr[i7] < 0) {
                                        i7 = i3 + 7;
                                        if (bArr[i8] < 0) {
                                            i8 = i3 + 8;
                                            if (bArr[i7] < 0) {
                                                i7 = i3 + 9;
                                                if (bArr[i8] < 0) {
                                                    int i9 = i3 + 10;
                                                    if (bArr[i7] >= 0) {
                                                        byte b11 = b10;
                                                        i6 = i9;
                                                        b3 = b11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    b3 = b10;
                                }
                                b4 = b10;
                            }
                            i6 = i8;
                        }
                        i6 = i7;
                    }
                    this.pos = i6;
                    return b3;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() throws IOException {
            long j2;
            long j3;
            long j4;
            int i3 = this.pos;
            int i4 = this.bufferSize;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b3 = bArr[i3];
                if (b3 >= 0) {
                    this.pos = i5;
                    return (long) b3;
                } else if (i4 - i5 >= 9) {
                    int i6 = i3 + 2;
                    byte b4 = (bArr[i5] << 7) ^ b3;
                    if (b4 < 0) {
                        j2 = (long) (b4 ^ Byte.MIN_VALUE);
                    } else {
                        int i7 = i3 + 3;
                        byte b5 = (bArr[i6] << Ascii.SO) ^ b4;
                        if (b5 >= 0) {
                            j2 = (long) (b5 ^ 16256);
                            i6 = i7;
                        } else {
                            int i8 = i3 + 4;
                            byte b6 = b5 ^ (bArr[i7] << Ascii.NAK);
                            if (b6 < 0) {
                                i6 = i8;
                                j2 = (long) (-2080896 ^ b6);
                            } else {
                                long j5 = (long) b6;
                                int i9 = i3 + 5;
                                long j6 = j5 ^ (((long) bArr[i8]) << 28);
                                if (j6 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    int i10 = i3 + 6;
                                    long j7 = j6 ^ (((long) bArr[i9]) << 35);
                                    if (j7 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        i9 = i3 + 7;
                                        j6 = j7 ^ (((long) bArr[i10]) << 42);
                                        if (j6 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            i10 = i3 + 8;
                                            j7 = j6 ^ (((long) bArr[i9]) << 49);
                                            if (j7 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                i6 = i3 + 9;
                                                long j8 = (j7 ^ (((long) bArr[i10]) << 56)) ^ 71499008037633920L;
                                                if (j8 < 0) {
                                                    int i11 = i3 + 10;
                                                    if (((long) bArr[i6]) >= 0) {
                                                        i6 = i11;
                                                    }
                                                }
                                                j2 = j8;
                                            }
                                        }
                                    }
                                    j2 = j7 ^ j3;
                                    i6 = i10;
                                }
                                j2 = j6 ^ j4;
                            }
                        }
                    }
                    this.pos = i6;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() throws IOException {
            long j2 = 0;
            for (int i3 = 0; i3 < 64; i3 += 7) {
                byte readRawByte = readRawByte();
                j2 |= ((long) (readRawByte & Byte.MAX_VALUE)) << i3;
                if ((readRawByte & 128) == 0) {
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i3 = this.bufferSize;
                int i4 = this.pos;
                if (readRawVarint32 <= i3 - i4) {
                    String str = new String(this.buffer, i4, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(readRawVarint32, false), Internal.UTF_8);
            }
            refillBuffer(readRawVarint32);
            String str2 = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
            this.pos += readRawVarint32;
            return str2;
        }

        public String readStringRequireUtf8() throws IOException {
            byte[] bArr;
            int readRawVarint32 = readRawVarint32();
            int i3 = this.pos;
            int i4 = this.bufferSize;
            if (readRawVarint32 <= i4 - i3 && readRawVarint32 > 0) {
                bArr = this.buffer;
                this.pos = i3 + readRawVarint32;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                i3 = 0;
                if (readRawVarint32 <= i4) {
                    refillBuffer(readRawVarint32);
                    bArr = this.buffer;
                    this.pos = readRawVarint32;
                } else {
                    bArr = readRawBytesSlowPath(readRawVarint32, false);
                }
            }
            return Utf8.decodeUtf8(bArr, i3, readRawVarint32);
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Deprecated
        public void readUnknownGroup(int i3, MessageLite.Builder builder) throws IOException {
            readGroup(i3, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        public boolean skipField(int i3) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipMessage():void");
        }

        public void skipRawBytes(int i3) throws IOException {
            int i4 = this.bufferSize;
            int i5 = this.pos;
            if (i3 > i4 - i5 || i3 < 0) {
                skipRawBytesSlowPath(i3);
            } else {
                this.pos = i5 + i3;
            }
        }

        private StreamDecoder(InputStream inputStream, int i3) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i3];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r2) throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0, r2)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public <T extends MessageLite> T readGroup(int i3, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
            return t2;
        }

        public boolean skipField(int i3, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeUInt32NoTag(i3);
                skipMessage(codedOutputStream);
                int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4);
                checkLastTagWas(makeTag);
                codedOutputStream.writeUInt32NoTag(makeTag);
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeUInt32NoTag(i3);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return t2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        private int bufferPos(long j2) {
            return (int) (j2 - this.address);
        }

        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private void recomputeBufferSizeAfterLimit() {
            long j2 = this.limit + ((long) this.bufferSizeAfterLimit);
            this.limit = j2;
            int i3 = (int) (j2 - this.startPos);
            int i4 = this.currentLimit;
            if (i3 > i4) {
                int i5 = i3 - i4;
                this.bufferSizeAfterLimit = i5;
                this.limit = j2 - ((long) i5);
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private void skipRawVarint() throws IOException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                long j2 = this.pos;
                this.pos = 1 + j2;
                if (UnsafeUtil.getByte(j2) < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i3 = 0;
            while (i3 < 10) {
                if (readRawByte() < 0) {
                    i3++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(long j2, long j3) throws IOException {
            int position = this.buffer.position();
            int limit2 = this.buffer.limit();
            ByteBuffer byteBuffer = this.buffer;
            try {
                byteBuffer.position(bufferPos(j2));
                byteBuffer.limit(bufferPos(j3));
                ByteBuffer slice = this.buffer.slice();
                byteBuffer.position(position);
                byteBuffer.limit(limit2);
                return slice;
            } catch (IllegalArgumentException e3) {
                InvalidProtocolBufferException truncatedMessage = InvalidProtocolBufferException.truncatedMessage();
                truncatedMessage.initCause(e3);
                throw truncatedMessage;
            } catch (Throwable th) {
                byteBuffer.position(position);
                byteBuffer.limit(limit2);
                throw th;
            }
        }

        public void checkLastTagWas(int i3) throws InvalidProtocolBufferException {
            if (this.lastTag != i3) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public void enableAliasing(boolean z2) {
            this.enableAliasing = z2;
        }

        public int getBytesUntilLimit() {
            int i3 = this.currentLimit;
            if (i3 == Integer.MAX_VALUE) {
                return -1;
            }
            return i3 - getTotalBytesRead();
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public void popLimit(int i3) {
            this.currentLimit = i3;
            recomputeBufferSizeAfterLimit();
        }

        public int pushLimit(int i3) throws InvalidProtocolBufferException {
            if (i3 >= 0) {
                int totalBytesRead = i3 + getTotalBytesRead();
                int i4 = this.currentLimit;
                if (totalBytesRead <= i4) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i4;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                long j2 = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j2);
                this.pos += j2;
                return ByteBuffer.wrap(bArr);
            } else {
                long j3 = this.pos;
                long j4 = (long) readRawVarint32;
                ByteBuffer slice = slice(j3, j3 + j4);
                this.pos += j4;
                return slice;
            }
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                long j2 = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j2);
                this.pos += j2;
                return ByteString.wrap(bArr);
            } else {
                long j3 = this.pos;
                long j4 = (long) readRawVarint32;
                ByteBuffer slice = slice(j3, j3 + j4);
                this.pos += j4;
                return ByteString.wrap(slice);
            }
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public void readGroup(int i3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte readRawByte() throws IOException {
            long j2 = this.pos;
            if (j2 != this.limit) {
                this.pos = 1 + j2;
                return UnsafeUtil.getByte(j2);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int i3) throws IOException {
            if (i3 >= 0 && i3 <= remaining()) {
                byte[] bArr = new byte[i3];
                long j2 = this.pos;
                long j3 = (long) i3;
                slice(j2, j2 + j3).get(bArr);
                this.pos += j3;
                return bArr;
            } else if (i3 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i3 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public int readRawLittleEndian32() throws IOException {
            long j2 = this.pos;
            if (this.limit - j2 >= 4) {
                this.pos = 4 + j2;
                return (UnsafeUtil.getByte(j2) & 255) | ((UnsafeUtil.getByte(1 + j2) & 255) << 8) | ((UnsafeUtil.getByte(2 + j2) & 255) << 16) | ((UnsafeUtil.getByte(j2 + 3) & 255) << Ascii.CAN);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            long j2 = this.pos;
            if (this.limit - j2 >= 8) {
                this.pos = 8 + j2;
                return ((((long) UnsafeUtil.getByte(j2 + 7)) & 255) << 56) | (((long) UnsafeUtil.getByte(j2)) & 255) | ((((long) UnsafeUtil.getByte(1 + j2)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j2)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j2)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j2)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j2)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j2)) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008c, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r3) < 0) goto L_0x008e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r9 = this;
                long r0 = r9.pos
                long r2 = r9.limit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto L_0x000a
                goto L_0x008e
            L_0x000a:
                r2 = 1
                long r2 = r2 + r0
                byte r4 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r4 < 0) goto L_0x0016
                r9.pos = r2
                return r4
            L_0x0016:
                long r5 = r9.limit
                long r5 = r5 - r2
                r7 = 9
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 >= 0) goto L_0x0021
                goto L_0x008e
            L_0x0021:
                r5 = 2
                long r5 = r5 + r0
                byte r2 = com.google.protobuf.UnsafeUtil.getByte(r2)
                int r2 = r2 << 7
                r2 = r2 ^ r4
                if (r2 >= 0) goto L_0x0031
                r0 = r2 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0098
            L_0x0031:
                r3 = 3
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r5 = r5 << 14
                r2 = r2 ^ r5
                if (r2 < 0) goto L_0x0041
                r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
            L_0x003f:
                r5 = r3
                goto L_0x0098
            L_0x0041:
                r5 = 4
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                int r3 = r3 << 21
                r2 = r2 ^ r3
                if (r2 >= 0) goto L_0x0052
                r0 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L_0x0098
            L_0x0052:
                r3 = 5
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r6 = r5 << 28
                r2 = r2 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r2 = r2 ^ r6
                if (r5 >= 0) goto L_0x0096
                r5 = 6
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L_0x0094
                r3 = 7
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L_0x0096
                r5 = 8
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L_0x0094
                long r3 = r0 + r7
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L_0x0096
                r5 = 10
                long r5 = r5 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r0 >= 0) goto L_0x0094
            L_0x008e:
                long r0 = r9.readRawVarint64SlowPath()
                int r9 = (int) r0
                return r9
            L_0x0094:
                r0 = r2
                goto L_0x0098
            L_0x0096:
                r0 = r2
                goto L_0x003f
            L_0x0098:
                r9.pos = r5
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        public long readRawVarint64() throws IOException {
            long j2;
            long j3;
            long j4;
            byte b3;
            long j5 = this.pos;
            if (this.limit != j5) {
                long j6 = 1 + j5;
                byte b4 = UnsafeUtil.getByte(j5);
                if (b4 >= 0) {
                    this.pos = j6;
                    return (long) b4;
                } else if (this.limit - j6 >= 9) {
                    long j7 = 2 + j5;
                    byte b5 = (UnsafeUtil.getByte(j6) << 7) ^ b4;
                    if (b5 < 0) {
                        b3 = b5 ^ Byte.MIN_VALUE;
                    } else {
                        long j8 = 3 + j5;
                        byte b6 = b5 ^ (UnsafeUtil.getByte(j7) << Ascii.SO);
                        if (b6 >= 0) {
                            j2 = (long) (b6 ^ 16256);
                            j7 = j8;
                        } else {
                            j7 = 4 + j5;
                            byte b7 = b6 ^ (UnsafeUtil.getByte(j8) << Ascii.NAK);
                            if (b7 < 0) {
                                b3 = -2080896 ^ b7;
                            } else {
                                long j9 = 5 + j5;
                                long j10 = ((long) b7) ^ (((long) UnsafeUtil.getByte(j7)) << 28);
                                if (j10 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    long j11 = 6 + j5;
                                    long j12 = j10 ^ (((long) UnsafeUtil.getByte(j9)) << 35);
                                    if (j12 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        j9 = 7 + j5;
                                        j10 = j12 ^ (((long) UnsafeUtil.getByte(j11)) << 42);
                                        if (j10 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            j11 = 8 + j5;
                                            j12 = j10 ^ (((long) UnsafeUtil.getByte(j9)) << 49);
                                            if (j12 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                long j13 = j5 + 9;
                                                long j14 = (j12 ^ (((long) UnsafeUtil.getByte(j11)) << 56)) ^ 71499008037633920L;
                                                if (j14 < 0) {
                                                    long j15 = j5 + 10;
                                                    if (((long) UnsafeUtil.getByte(j13)) >= 0) {
                                                        j7 = j15;
                                                        j2 = j14;
                                                    }
                                                } else {
                                                    j2 = j14;
                                                    j7 = j13;
                                                }
                                            }
                                        }
                                    }
                                    j2 = j3 ^ j12;
                                    j7 = j11;
                                }
                                j2 = j4 ^ j10;
                                j7 = j9;
                            }
                        }
                        this.pos = j7;
                        return j2;
                    }
                    j2 = (long) b3;
                    this.pos = j7;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() throws IOException {
            long j2 = 0;
            for (int i3 = 0; i3 < 64; i3 += 7) {
                byte readRawByte = readRawByte();
                j2 |= ((long) (readRawByte & Byte.MAX_VALUE)) << i3;
                if ((readRawByte & 128) == 0) {
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j2 = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j2);
                String str = new String(bArr, Internal.UTF_8);
                this.pos += j2;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                String decodeUtf8 = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), readRawVarint32);
                this.pos += (long) readRawVarint32;
                return decodeUtf8;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Deprecated
        public void readUnknownGroup(int i3, MessageLite.Builder builder) throws IOException {
            readGroup(i3, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public boolean skipField(int i3) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.skipMessage():void");
        }

        public void skipRawBytes(int i3) throws IOException {
            if (i3 >= 0 && i3 <= remaining()) {
                this.pos += (long) i3;
            } else if (i3 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            this.address = addressOffset;
            this.limit = ((long) byteBuffer.limit()) + addressOffset;
            long position = addressOffset + ((long) byteBuffer.position());
            this.pos = position;
            this.startPos = position;
            this.immutable = z2;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r2) throws java.io.IOException {
            /*
                r1 = this;
            L_0x0000:
                int r0 = r1.readTag()
                if (r0 == 0) goto L_0x000c
                boolean r0 = r1.skipField(r0, r2)
                if (r0 != 0) goto L_0x0000
            L_0x000c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public <T extends MessageLite> T readGroup(int i3, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i3, 4));
            this.recursionDepth--;
            return t2;
        }

        public boolean skipField(int i3, CodedOutputStream codedOutputStream) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                long readInt64 = readInt64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            } else if (tagWireType == 1) {
                long readRawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                return true;
            } else if (tagWireType == 2) {
                ByteString readBytes = readBytes();
                codedOutputStream.writeUInt32NoTag(i3);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            } else if (tagWireType == 3) {
                codedOutputStream.writeUInt32NoTag(i3);
                skipMessage(codedOutputStream);
                int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i3), 4);
                checkLastTagWas(makeTag);
                codedOutputStream.writeUInt32NoTag(makeTag);
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeUInt32NoTag(i3);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            checkRecursionLimit();
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T t2 = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(pushLimit);
                return t2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public static int decodeZigZag32(int i3) {
        return (-(i3 & 1)) ^ (i3 >>> 1);
    }

    public static long decodeZigZag64(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    public static int readRawVarint32(int i3, InputStream inputStream) throws IOException {
        if ((i3 & 128) == 0) {
            return i3;
        }
        int i4 = i3 & 127;
        int i5 = 7;
        while (i5 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i4 |= (read & 127) << i5;
                if ((read & 128) == 0) {
                    return i4;
                }
                i5 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i5 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read2 & 128) == 0) {
                return i4;
            } else {
                i5 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public abstract void checkLastTagWas(int i3) throws InvalidProtocolBufferException;

    public void checkRecursionLimit() throws InvalidProtocolBufferException {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    public final void discardUnknownFields() {
        this.shouldDiscardUnknownFields = true;
    }

    public abstract void enableAliasing(boolean z2);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i3);

    public abstract int pushLimit(int i3) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i3, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i3) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    public abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i3, MessageLite.Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public final int setRecursionLimit(int i3) {
        if (i3 >= 0) {
            int i4 = this.recursionLimit;
            this.recursionLimit = i3;
            return i4;
        }
        throw new IllegalArgumentException(a.k("Recursion limit cannot be negative: ", i3));
    }

    public final int setSizeLimit(int i3) {
        if (i3 >= 0) {
            int i4 = this.sizeLimit;
            this.sizeLimit = i3;
            return i4;
        }
        throw new IllegalArgumentException(a.k("Size limit cannot be negative: ", i3));
    }

    public final boolean shouldDiscardUnknownFields() {
        return this.shouldDiscardUnknownFields;
    }

    public abstract boolean skipField(int i3) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i3, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i3) throws IOException;

    public final void unsetDiscardUnknownFields() {
        this.shouldDiscardUnknownFields = false;
    }

    private CodedInputStream() {
        this.recursionLimit = defaultRecursionLimit;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public static CodedInputStream newInstance(InputStream inputStream, int i3) {
        if (i3 <= 0) {
            throw new IllegalArgumentException("bufferSize must be > 0");
        } else if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        } else {
            return new StreamDecoder(inputStream, i3);
        }
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable) {
        if (!UnsafeDirectNioDecoder.isSupported()) {
            return newInstance((InputStream) new IterableByteBufferInputStream(iterable));
        }
        return newInstance(iterable, false);
    }

    public static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable, boolean z2) {
        boolean z3 = false;
        int i3 = 0;
        for (ByteBuffer next : iterable) {
            i3 += next.remaining();
            if (next.hasArray()) {
                z3 |= true;
            } else {
                z3 = next.isDirect() ? z3 | true : z3 | true;
            }
        }
        if (z3) {
            return new IterableDirectByteBufferDecoder(iterable, i3, z2);
        }
        return newInstance((InputStream) new IterableByteBufferInputStream(iterable));
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i3, int i4) {
        return newInstance(bArr, i3, i4, false);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i3, int i4, boolean z2) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i3, i4, z2);
        try {
            arrayDecoder.pushLimit(i4);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z2) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining(), z2);
        } else if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z2);
        } else {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.duplicate().get(bArr);
            return newInstance(bArr, 0, remaining, true);
        }
    }
}
