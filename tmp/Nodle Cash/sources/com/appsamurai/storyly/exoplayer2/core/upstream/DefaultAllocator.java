package com.appsamurai.storyly.exoplayer2.core.upstream;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import java.util.Arrays;

public final class DefaultAllocator implements Allocator {
    private static final int AVAILABLE_EXTRA_CAPACITY = 100;
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    @Nullable
    private final byte[] initialAllocationBlock;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean z2, int i3) {
        this(z2, i3, 0);
    }

    public synchronized Allocation allocate() {
        Allocation allocation;
        try {
            this.allocatedCount++;
            int i3 = this.availableCount;
            if (i3 > 0) {
                Allocation[] allocationArr = this.availableAllocations;
                int i4 = i3 - 1;
                this.availableCount = i4;
                allocation = (Allocation) Assertions.checkNotNull(allocationArr[i4]);
                this.availableAllocations[this.availableCount] = null;
            } else {
                allocation = new Allocation(new byte[this.individualAllocationSize], 0);
                int i5 = this.allocatedCount;
                Allocation[] allocationArr2 = this.availableAllocations;
                if (i5 > allocationArr2.length) {
                    this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, allocationArr2.length * 2);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return allocation;
    }

    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    public synchronized void release(Allocation allocation) {
        Allocation[] allocationArr = this.availableAllocations;
        int i3 = this.availableCount;
        this.availableCount = i3 + 1;
        allocationArr[i3] = allocation;
        this.allocatedCount--;
        notifyAll();
    }

    public synchronized void reset() {
        if (this.trimOnReset) {
            setTargetBufferSize(0);
        }
    }

    public synchronized void setTargetBufferSize(int i3) {
        boolean z2 = i3 < this.targetBufferSize;
        this.targetBufferSize = i3;
        if (z2) {
            trim();
        }
    }

    public synchronized void trim() {
        try {
            int i3 = 0;
            int max = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
            int i4 = this.availableCount;
            if (max < i4) {
                if (this.initialAllocationBlock != null) {
                    int i5 = i4 - 1;
                    while (i3 <= i5) {
                        Allocation allocation = (Allocation) Assertions.checkNotNull(this.availableAllocations[i3]);
                        if (allocation.data == this.initialAllocationBlock) {
                            i3++;
                        } else {
                            Allocation allocation2 = (Allocation) Assertions.checkNotNull(this.availableAllocations[i5]);
                            if (allocation2.data != this.initialAllocationBlock) {
                                i5--;
                            } else {
                                Allocation[] allocationArr = this.availableAllocations;
                                allocationArr[i3] = allocation2;
                                allocationArr[i5] = allocation;
                                i5--;
                                i3++;
                            }
                        }
                    }
                    max = Math.max(max, i3);
                    if (max >= this.availableCount) {
                        return;
                    }
                }
                Arrays.fill(this.availableAllocations, max, this.availableCount, (Object) null);
                this.availableCount = max;
            }
        } finally {
            while (true) {
            }
        }
    }

    public DefaultAllocator(boolean z2, int i3, int i4) {
        boolean z3 = true;
        Assertions.checkArgument(i3 > 0);
        Assertions.checkArgument(i4 < 0 ? false : z3);
        this.trimOnReset = z2;
        this.individualAllocationSize = i3;
        this.availableCount = i4;
        this.availableAllocations = new Allocation[(i4 + 100)];
        if (i4 > 0) {
            this.initialAllocationBlock = new byte[(i4 * i3)];
            for (int i5 = 0; i5 < i4; i5++) {
                this.availableAllocations[i5] = new Allocation(this.initialAllocationBlock, i5 * i3);
            }
            return;
        }
        this.initialAllocationBlock = null;
    }

    public synchronized void release(@Nullable Allocator.AllocationNode allocationNode) {
        while (allocationNode != null) {
            try {
                Allocation[] allocationArr = this.availableAllocations;
                int i3 = this.availableCount;
                this.availableCount = i3 + 1;
                allocationArr[i3] = allocationNode.getAllocation();
                this.allocatedCount--;
                allocationNode = allocationNode.next();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        notifyAll();
    }
}
