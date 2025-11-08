package com.appsamurai.storyly.exoplayer2.core.source;

import java.util.Arrays;
import java.util.Random;

public interface ShuffleOrder {

    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int i3) {
            this(i3, new Random());
        }

        private static int[] createShuffledList(int i3, Random random2) {
            int[] iArr = new int[i3];
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i4 + 1;
                int nextInt = random2.nextInt(i5);
                iArr[i4] = iArr[nextInt];
                iArr[nextInt] = i4;
                i4 = i5;
            }
            return iArr;
        }

        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        public ShuffleOrder cloneAndInsert(int i3, int i4) {
            int[] iArr = new int[i4];
            int[] iArr2 = new int[i4];
            int i5 = 0;
            int i6 = 0;
            while (i6 < i4) {
                iArr[i6] = this.random.nextInt(this.shuffled.length + 1);
                int i7 = i6 + 1;
                int nextInt = this.random.nextInt(i7);
                iArr2[i6] = iArr2[nextInt];
                iArr2[nextInt] = i6 + i3;
                i6 = i7;
            }
            Arrays.sort(iArr);
            int[] iArr3 = new int[(this.shuffled.length + i4)];
            int i8 = 0;
            int i9 = 0;
            while (true) {
                int[] iArr4 = this.shuffled;
                if (i5 >= iArr4.length + i4) {
                    return new DefaultShuffleOrder(iArr3, new Random(this.random.nextLong()));
                }
                if (i8 >= i4 || i9 != iArr[i8]) {
                    int i10 = i9 + 1;
                    int i11 = iArr4[i9];
                    iArr3[i5] = i11;
                    if (i11 >= i3) {
                        iArr3[i5] = i11 + i4;
                    }
                    i9 = i10;
                } else {
                    iArr3[i5] = iArr2[i8];
                    i8++;
                }
                i5++;
            }
        }

        public ShuffleOrder cloneAndRemove(int i3, int i4) {
            int i5 = i4 - i3;
            int[] iArr = new int[(this.shuffled.length - i5)];
            int i6 = 0;
            int i7 = 0;
            while (true) {
                int[] iArr2 = this.shuffled;
                if (i6 >= iArr2.length) {
                    return new DefaultShuffleOrder(iArr, new Random(this.random.nextLong()));
                }
                int i8 = iArr2[i6];
                if (i8 < i3 || i8 >= i4) {
                    int i9 = i6 - i7;
                    if (i8 >= i3) {
                        i8 -= i5;
                    }
                    iArr[i9] = i8;
                } else {
                    i7++;
                }
                i6++;
            }
        }

        public int getFirstIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[0];
            }
            return -1;
        }

        public int getLastIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[iArr.length - 1];
            }
            return -1;
        }

        public int getLength() {
            return this.shuffled.length;
        }

        public int getNextIndex(int i3) {
            int i4 = this.indexInShuffled[i3] + 1;
            int[] iArr = this.shuffled;
            if (i4 < iArr.length) {
                return iArr[i4];
            }
            return -1;
        }

        public int getPreviousIndex(int i3) {
            int i4 = this.indexInShuffled[i3] - 1;
            if (i4 >= 0) {
                return this.shuffled[i4];
            }
            return -1;
        }

        public DefaultShuffleOrder(int i3, long j2) {
            this(i3, new Random(j2));
        }

        public DefaultShuffleOrder(int[] iArr, long j2) {
            this(Arrays.copyOf(iArr, iArr.length), new Random(j2));
        }

        private DefaultShuffleOrder(int i3, Random random2) {
            this(createShuffledList(i3, random2), random2);
        }

        private DefaultShuffleOrder(int[] iArr, Random random2) {
            this.shuffled = iArr;
            this.random = random2;
            this.indexInShuffled = new int[iArr.length];
            for (int i3 = 0; i3 < iArr.length; i3++) {
                this.indexInShuffled[iArr[i3]] = i3;
            }
        }
    }

    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public UnshuffledShuffleOrder(int i3) {
            this.length = i3;
        }

        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }

        public ShuffleOrder cloneAndInsert(int i3, int i4) {
            return new UnshuffledShuffleOrder(this.length + i4);
        }

        public ShuffleOrder cloneAndRemove(int i3, int i4) {
            return new UnshuffledShuffleOrder((this.length - i4) + i3);
        }

        public int getFirstIndex() {
            return this.length > 0 ? 0 : -1;
        }

        public int getLastIndex() {
            int i3 = this.length;
            if (i3 > 0) {
                return i3 - 1;
            }
            return -1;
        }

        public int getLength() {
            return this.length;
        }

        public int getNextIndex(int i3) {
            int i4 = i3 + 1;
            if (i4 < this.length) {
                return i4;
            }
            return -1;
        }

        public int getPreviousIndex(int i3) {
            int i4 = i3 - 1;
            if (i4 >= 0) {
                return i4;
            }
            return -1;
        }
    }

    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int i3, int i4);

    ShuffleOrder cloneAndRemove(int i3, int i4);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int i3);

    int getPreviousIndex(int i3);
}
