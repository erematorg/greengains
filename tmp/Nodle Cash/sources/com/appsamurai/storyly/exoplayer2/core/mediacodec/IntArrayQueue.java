package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import java.util.NoSuchElementException;

final class IntArrayQueue {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int[] data;
    private int headIndex = 0;
    private int size = 0;
    private int tailIndex = -1;
    private int wrapAroundMask;

    public IntArrayQueue() {
        int[] iArr = new int[16];
        this.data = iArr;
        this.wrapAroundMask = iArr.length - 1;
    }

    private void doubleArraySize() {
        int[] iArr = this.data;
        int length = iArr.length << 1;
        if (length >= 0) {
            int[] iArr2 = new int[length];
            int length2 = iArr.length;
            int i3 = this.headIndex;
            int i4 = length2 - i3;
            System.arraycopy(iArr, i3, iArr2, 0, i4);
            System.arraycopy(this.data, 0, iArr2, i4, i3);
            this.headIndex = 0;
            this.tailIndex = this.size - 1;
            this.data = iArr2;
            this.wrapAroundMask = iArr2.length - 1;
            return;
        }
        throw new IllegalStateException();
    }

    public void add(int i3) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int i4 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = i4;
        this.data[i4] = i3;
        this.size++;
    }

    public int capacity() {
        return this.data.length;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int remove() {
        int i3 = this.size;
        if (i3 != 0) {
            int[] iArr = this.data;
            int i4 = this.headIndex;
            int i5 = iArr[i4];
            this.headIndex = (i4 + 1) & this.wrapAroundMask;
            this.size = i3 - 1;
            return i5;
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return this.size;
    }
}
