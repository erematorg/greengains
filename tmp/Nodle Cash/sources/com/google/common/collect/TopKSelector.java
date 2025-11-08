package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;

    /* renamed from: k  reason: collision with root package name */
    private final int f6876k;
    @CheckForNull
    private T threshold;

    private TopKSelector(Comparator<? super T> comparator2, int i3) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2, "comparator");
        this.f6876k = i3;
        boolean z2 = true;
        Preconditions.checkArgument(i3 >= 0, "k (%s) must be >= 0", i3);
        Preconditions.checkArgument(i3 > 1073741823 ? false : z2, "k (%s) must be <= Integer.MAX_VALUE / 2", i3);
        this.buffer = new Object[IntMath.checkedMultiply(i3, 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int i3) {
        return greatest(i3, Ordering.natural());
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int i3) {
        return least(i3, Ordering.natural());
    }

    private int partition(int i3, int i4, int i5) {
        T uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(this.buffer[i5]);
        T[] tArr = this.buffer;
        tArr[i5] = tArr[i4];
        int i6 = i3;
        while (i3 < i4) {
            if (this.comparator.compare(NullnessCasts.uncheckedCastNullableTToT(this.buffer[i3]), uncheckedCastNullableTToT) < 0) {
                swap(i6, i3);
                i6++;
            }
            i3++;
        }
        T[] tArr2 = this.buffer;
        tArr2[i4] = tArr2[i6];
        tArr2[i6] = uncheckedCastNullableTToT;
        return i6;
    }

    private void swap(int i3, int i4) {
        T[] tArr = this.buffer;
        T t2 = tArr[i3];
        tArr[i3] = tArr[i4];
        tArr[i4] = t2;
    }

    private void trim() {
        int i3 = (this.f6876k * 2) - 1;
        int log2 = IntMath.log2(i3, RoundingMode.CEILING) * 3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i4 < i3) {
                int partition = partition(i4, i3, ((i4 + i3) + 1) >>> 1);
                int i7 = this.f6876k;
                if (partition <= i7) {
                    if (partition >= i7) {
                        break;
                    }
                    i4 = Math.max(partition, i4 + 1);
                    i6 = partition;
                } else {
                    i3 = partition - 1;
                }
                i5++;
                if (i5 >= log2) {
                    Arrays.sort(this.buffer, i4, i3 + 1, this.comparator);
                    break;
                }
            } else {
                break;
            }
        }
        this.bufferSize = this.f6876k;
        this.threshold = NullnessCasts.uncheckedCastNullableTToT(this.buffer[i6]);
        while (true) {
            i6++;
            if (i6 >= this.f6876k) {
                return;
            }
            if (this.comparator.compare(NullnessCasts.uncheckedCastNullableTToT(this.buffer[i6]), NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                this.threshold = this.buffer[i6];
            }
        }
    }

    public void offer(@ParametricNullness T t2) {
        int i3 = this.f6876k;
        if (i3 != 0) {
            int i4 = this.bufferSize;
            if (i4 == 0) {
                this.buffer[0] = t2;
                this.threshold = t2;
                this.bufferSize = 1;
            } else if (i4 < i3) {
                T[] tArr = this.buffer;
                this.bufferSize = i4 + 1;
                tArr[i4] = t2;
                if (this.comparator.compare(t2, NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                    this.threshold = t2;
                }
            } else if (this.comparator.compare(t2, NullnessCasts.uncheckedCastNullableTToT(this.threshold)) < 0) {
                T[] tArr2 = this.buffer;
                int i5 = this.bufferSize;
                int i6 = i5 + 1;
                this.bufferSize = i6;
                tArr2[i5] = t2;
                if (i6 == this.f6876k * 2) {
                    trim();
                }
            }
        }
    }

    public void offerAll(Iterable<? extends T> iterable) {
        offerAll(iterable.iterator());
    }

    public List<T> topK() {
        T[] tArr = this.buffer;
        Arrays.sort(tArr, 0, this.bufferSize, this.comparator);
        int i3 = this.bufferSize;
        int i4 = this.f6876k;
        if (i3 > i4) {
            T[] tArr2 = this.buffer;
            Arrays.fill(tArr2, i4, tArr2.length, (Object) null);
            int i5 = this.f6876k;
            this.bufferSize = i5;
            this.threshold = this.buffer[i5 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(tArr, this.bufferSize)));
    }

    public static <T> TopKSelector<T> greatest(int i3, Comparator<? super T> comparator2) {
        return new TopKSelector<>(Ordering.from(comparator2).reverse(), i3);
    }

    public static <T> TopKSelector<T> least(int i3, Comparator<? super T> comparator2) {
        return new TopKSelector<>(comparator2, i3);
    }

    public void offerAll(Iterator<? extends T> it) {
        while (it.hasNext()) {
            offer(it.next());
        }
    }
}
