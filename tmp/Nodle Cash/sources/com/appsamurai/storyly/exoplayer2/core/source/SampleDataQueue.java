package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocation;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoInfo;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

class SampleDataQueue {
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private AllocationNode firstAllocationNode;
    private AllocationNode readAllocationNode;
    private final ParsableByteArray scratch = new ParsableByteArray(32);
    private long totalBytesWritten;
    private AllocationNode writeAllocationNode;

    public static final class AllocationNode implements Allocator.AllocationNode {
        @Nullable
        public Allocation allocation;
        public long endPosition;
        @Nullable
        public AllocationNode next;
        public long startPosition;

        public AllocationNode(long j2, int i3) {
            reset(j2, i3);
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode allocationNode = this.next;
            this.next = null;
            return allocationNode;
        }

        public Allocation getAllocation() {
            return (Allocation) Assertions.checkNotNull(this.allocation);
        }

        public void initialize(Allocation allocation2, AllocationNode allocationNode) {
            this.allocation = allocation2;
            this.next = allocationNode;
        }

        @Nullable
        public Allocator.AllocationNode next() {
            AllocationNode allocationNode = this.next;
            if (allocationNode == null || allocationNode.allocation == null) {
                return null;
            }
            return allocationNode;
        }

        public void reset(long j2, int i3) {
            Assertions.checkState(this.allocation == null);
            this.startPosition = j2;
            this.endPosition = j2 + ((long) i3);
        }

        public int translateOffset(long j2) {
            return ((int) (j2 - this.startPosition)) + this.allocation.offset;
        }
    }

    public SampleDataQueue(Allocator allocator2) {
        this.allocator = allocator2;
        int individualAllocationLength = allocator2.getIndividualAllocationLength();
        this.allocationLength = individualAllocationLength;
        AllocationNode allocationNode = new AllocationNode(0, individualAllocationLength);
        this.firstAllocationNode = allocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
    }

    private void clearAllocationNodes(AllocationNode allocationNode) {
        if (allocationNode.allocation != null) {
            this.allocator.release((Allocator.AllocationNode) allocationNode);
            allocationNode.clear();
        }
    }

    private static AllocationNode getNodeContainingPosition(AllocationNode allocationNode, long j2) {
        while (j2 >= allocationNode.endPosition) {
            allocationNode = allocationNode.next;
        }
        return allocationNode;
    }

    private void postAppend(int i3) {
        long j2 = this.totalBytesWritten + ((long) i3);
        this.totalBytesWritten = j2;
        AllocationNode allocationNode = this.writeAllocationNode;
        if (j2 == allocationNode.endPosition) {
            this.writeAllocationNode = allocationNode.next;
        }
    }

    private int preAppend(int i3) {
        AllocationNode allocationNode = this.writeAllocationNode;
        if (allocationNode.allocation == null) {
            allocationNode.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
        }
        return Math.min(i3, (int) (this.writeAllocationNode.endPosition - this.totalBytesWritten));
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j2, ByteBuffer byteBuffer, int i3) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j2);
        while (i3 > 0) {
            int min = Math.min(i3, (int) (nodeContainingPosition.endPosition - j2));
            byteBuffer.put(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j2), min);
            i3 -= min;
            j2 += (long) min;
            if (j2 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    private static AllocationNode readEncryptionData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        SampleQueue.SampleExtrasHolder sampleExtrasHolder2 = sampleExtrasHolder;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        long j2 = sampleExtrasHolder2.offset;
        int i3 = 1;
        parsableByteArray2.reset(1);
        AllocationNode readData = readData(allocationNode, j2, parsableByteArray.getData(), 1);
        long j3 = j2 + 1;
        byte b3 = parsableByteArray.getData()[0];
        boolean z2 = (b3 & 128) != 0;
        byte b4 = b3 & Byte.MAX_VALUE;
        CryptoInfo cryptoInfo = decoderInputBuffer.cryptoInfo;
        byte[] bArr = cryptoInfo.iv;
        if (bArr == null) {
            cryptoInfo.iv = new byte[16];
        } else {
            Arrays.fill(bArr, (byte) 0);
        }
        AllocationNode readData2 = readData(readData, j3, cryptoInfo.iv, (int) b4);
        long j4 = j3 + ((long) b4);
        if (z2) {
            parsableByteArray2.reset(2);
            readData2 = readData(readData2, j4, parsableByteArray.getData(), 2);
            j4 += 2;
            i3 = parsableByteArray.readUnsignedShort();
        }
        int i4 = i3;
        int[] iArr = cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < i4) {
            iArr = new int[i4];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i4) {
            iArr3 = new int[i4];
        }
        int[] iArr4 = iArr3;
        if (z2) {
            int i5 = i4 * 6;
            parsableByteArray2.reset(i5);
            readData2 = readData(readData2, j4, parsableByteArray.getData(), i5);
            j4 += (long) i5;
            parsableByteArray2.setPosition(0);
            for (int i6 = 0; i6 < i4; i6++) {
                iArr2[i6] = parsableByteArray.readUnsignedShort();
                iArr4[i6] = parsableByteArray.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleExtrasHolder2.size - ((int) (j4 - sampleExtrasHolder2.offset));
        }
        TrackOutput.CryptoData cryptoData = (TrackOutput.CryptoData) Util.castNonNull(sampleExtrasHolder2.cryptoData);
        cryptoInfo.set(i4, iArr2, iArr4, cryptoData.encryptionKey, cryptoInfo.iv, cryptoData.cryptoMode, cryptoData.encryptedBlocks, cryptoData.clearBlocks);
        long j5 = sampleExtrasHolder2.offset;
        int i7 = (int) (j4 - j5);
        sampleExtrasHolder2.offset = j5 + ((long) i7);
        sampleExtrasHolder2.size -= i7;
        return readData2;
    }

    private static AllocationNode readSampleData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        if (decoderInputBuffer.isEncrypted()) {
            allocationNode = readEncryptionData(allocationNode, decoderInputBuffer, sampleExtrasHolder, parsableByteArray);
        }
        if (decoderInputBuffer.hasSupplementalData()) {
            parsableByteArray.reset(4);
            AllocationNode readData = readData(allocationNode, sampleExtrasHolder.offset, parsableByteArray.getData(), 4);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            sampleExtrasHolder.offset += 4;
            sampleExtrasHolder.size -= 4;
            decoderInputBuffer.ensureSpaceForWrite(readUnsignedIntToInt);
            AllocationNode readData2 = readData(readData, sampleExtrasHolder.offset, decoderInputBuffer.data, readUnsignedIntToInt);
            sampleExtrasHolder.offset += (long) readUnsignedIntToInt;
            int i3 = sampleExtrasHolder.size - readUnsignedIntToInt;
            sampleExtrasHolder.size = i3;
            decoderInputBuffer.resetSupplementalData(i3);
            return readData(readData2, sampleExtrasHolder.offset, decoderInputBuffer.supplementalData, sampleExtrasHolder.size);
        }
        decoderInputBuffer.ensureSpaceForWrite(sampleExtrasHolder.size);
        return readData(allocationNode, sampleExtrasHolder.offset, decoderInputBuffer.data, sampleExtrasHolder.size);
    }

    public void discardDownstreamTo(long j2) {
        AllocationNode allocationNode;
        if (j2 != -1) {
            while (true) {
                allocationNode = this.firstAllocationNode;
                if (j2 < allocationNode.endPosition) {
                    break;
                }
                this.allocator.release(allocationNode.allocation);
                this.firstAllocationNode = this.firstAllocationNode.clear();
            }
            if (this.readAllocationNode.startPosition < allocationNode.startPosition) {
                this.readAllocationNode = allocationNode;
            }
        }
    }

    public void discardUpstreamSampleBytes(long j2) {
        Assertions.checkArgument(j2 <= this.totalBytesWritten);
        this.totalBytesWritten = j2;
        if (j2 != 0) {
            AllocationNode allocationNode = this.firstAllocationNode;
            if (j2 != allocationNode.startPosition) {
                while (this.totalBytesWritten > allocationNode.endPosition) {
                    allocationNode = allocationNode.next;
                }
                AllocationNode allocationNode2 = (AllocationNode) Assertions.checkNotNull(allocationNode.next);
                clearAllocationNodes(allocationNode2);
                AllocationNode allocationNode3 = new AllocationNode(allocationNode.endPosition, this.allocationLength);
                allocationNode.next = allocationNode3;
                if (this.totalBytesWritten == allocationNode.endPosition) {
                    allocationNode = allocationNode3;
                }
                this.writeAllocationNode = allocationNode;
                if (this.readAllocationNode == allocationNode2) {
                    this.readAllocationNode = allocationNode3;
                    return;
                }
                return;
            }
        }
        clearAllocationNodes(this.firstAllocationNode);
        AllocationNode allocationNode4 = new AllocationNode(this.totalBytesWritten, this.allocationLength);
        this.firstAllocationNode = allocationNode4;
        this.readAllocationNode = allocationNode4;
        this.writeAllocationNode = allocationNode4;
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void peekToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void readToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        this.readAllocationNode = readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void reset() {
        clearAllocationNodes(this.firstAllocationNode);
        this.firstAllocationNode.reset(0, this.allocationLength);
        AllocationNode allocationNode = this.firstAllocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
        this.totalBytesWritten = 0;
        this.allocator.trim();
    }

    public void rewind() {
        this.readAllocationNode = this.firstAllocationNode;
    }

    public int sampleData(DataReader dataReader, int i3, boolean z2) throws IOException {
        int preAppend = preAppend(i3);
        AllocationNode allocationNode = this.writeAllocationNode;
        int read = dataReader.read(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), preAppend);
        if (read != -1) {
            postAppend(read);
            return read;
        } else if (z2) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j2, byte[] bArr, int i3) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j2);
        int i4 = i3;
        while (i4 > 0) {
            int min = Math.min(i4, (int) (nodeContainingPosition.endPosition - j2));
            System.arraycopy(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j2), bArr, i3 - i4, min);
            i4 -= min;
            j2 += (long) min;
            if (j2 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i3) {
        while (i3 > 0) {
            int preAppend = preAppend(i3);
            AllocationNode allocationNode = this.writeAllocationNode;
            parsableByteArray.readBytes(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), preAppend);
            i3 -= preAppend;
            postAppend(preAppend);
        }
    }
}
