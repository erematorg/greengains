package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    @VisibleForTesting
    final int maximumSize;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    /* access modifiers changed from: private */
    public int modCount;
    /* access modifiers changed from: private */
    public Object[] queue;
    /* access modifiers changed from: private */
    public int size;

    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        /* access modifiers changed from: private */
        public int maximumSize;

        /* access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i3) {
            Preconditions.checkArgument(i3 >= 0);
            this.expectedSize = i3;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i3) {
            Preconditions.checkArgument(i3 > 0);
            this.maximumSize = i3;
            return this;
        }

        private Builder(Comparator<B> comparator2) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, iterable));
            for (Object offer : iterable) {
                minMaxPriorityQueue.offer(offer);
            }
            return minMaxPriorityQueue;
        }
    }

    public class Heap {
        final Ordering<E> ordering;
        @Weak
        MinMaxPriorityQueue<E>.Heap otherHeap;

        public Heap(Ordering<E> ordering2) {
            this.ordering = ordering2;
        }

        private int getGrandparentIndex(int i3) {
            return getParentIndex(getParentIndex(i3));
        }

        private int getLeftChildIndex(int i3) {
            return (i3 * 2) + 1;
        }

        private int getParentIndex(int i3) {
            return (i3 - 1) / 2;
        }

        private int getRightChildIndex(int i3) {
            return (i3 * 2) + 2;
        }

        /* access modifiers changed from: private */
        public boolean verifyIndex(int i3) {
            if (getLeftChildIndex(i3) < MinMaxPriorityQueue.this.size && compareElements(i3, getLeftChildIndex(i3)) > 0) {
                return false;
            }
            if (getRightChildIndex(i3) < MinMaxPriorityQueue.this.size && compareElements(i3, getRightChildIndex(i3)) > 0) {
                return false;
            }
            if (i3 <= 0 || compareElements(i3, getParentIndex(i3)) <= 0) {
                return i3 <= 2 || compareElements(getGrandparentIndex(i3), i3) <= 0;
            }
            return false;
        }

        public void bubbleUp(int i3, E e3) {
            int crossOverUp = crossOverUp(i3, e3);
            if (crossOverUp != i3) {
                this = this.otherHeap;
                i3 = crossOverUp;
            }
            this.bubbleUpAlternatingLevels(i3, e3);
        }

        @CanIgnoreReturnValue
        public int bubbleUpAlternatingLevels(int i3, E e3) {
            while (i3 > 2) {
                int grandparentIndex = getGrandparentIndex(i3);
                Object elementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (this.ordering.compare(elementData, e3) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i3] = elementData;
                i3 = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i3] = e3;
            return i3;
        }

        public int compareElements(int i3, int i4) {
            return this.ordering.compare(MinMaxPriorityQueue.this.elementData(i3), MinMaxPriorityQueue.this.elementData(i4));
        }

        public int crossOver(int i3, E e3) {
            int findMinChild = findMinChild(i3);
            if (findMinChild <= 0 || this.ordering.compare(MinMaxPriorityQueue.this.elementData(findMinChild), e3) >= 0) {
                return crossOverUp(i3, e3);
            }
            MinMaxPriorityQueue.this.queue[i3] = MinMaxPriorityQueue.this.elementData(findMinChild);
            MinMaxPriorityQueue.this.queue[findMinChild] = e3;
            return findMinChild;
        }

        public int crossOverUp(int i3, E e3) {
            int rightChildIndex;
            if (i3 == 0) {
                MinMaxPriorityQueue.this.queue[0] = e3;
                return 0;
            }
            int parentIndex = getParentIndex(i3);
            Object elementData = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (!(parentIndex == 0 || (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(rightChildIndex) < MinMaxPriorityQueue.this.size)) {
                Object elementData2 = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (this.ordering.compare(elementData2, elementData) < 0) {
                    parentIndex = rightChildIndex;
                    elementData = elementData2;
                }
            }
            if (this.ordering.compare(elementData, e3) < 0) {
                MinMaxPriorityQueue.this.queue[i3] = elementData;
                MinMaxPriorityQueue.this.queue[parentIndex] = e3;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[i3] = e3;
            return i3;
        }

        public int fillHoleAt(int i3) {
            while (true) {
                int findMinGrandChild = findMinGrandChild(i3);
                if (findMinGrandChild <= 0) {
                    return i3;
                }
                MinMaxPriorityQueue.this.queue[i3] = MinMaxPriorityQueue.this.elementData(findMinGrandChild);
                i3 = findMinGrandChild;
            }
        }

        public int findMin(int i3, int i4) {
            if (i3 >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i3 > 0);
            int min = Math.min(i3, MinMaxPriorityQueue.this.size - i4) + i4;
            for (int i5 = i3 + 1; i5 < min; i5++) {
                if (compareElements(i5, i3) < 0) {
                    i3 = i5;
                }
            }
            return i3;
        }

        public int findMinChild(int i3) {
            return findMin(getLeftChildIndex(i3), 2);
        }

        public int findMinGrandChild(int i3) {
            int leftChildIndex = getLeftChildIndex(i3);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        public int swapWithConceptuallyLastElement(E e3) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (!(parentIndex == 0 || (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(rightChildIndex) < MinMaxPriorityQueue.this.size)) {
                Object elementData = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (this.ordering.compare(elementData, e3) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = e3;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = elementData;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        @CheckForNull
        public MoveDesc<E> tryCrossOverAndBubbleUp(int i3, int i4, E e3) {
            int crossOver = crossOver(i4, e3);
            if (crossOver == i4) {
                return null;
            }
            Object elementData = crossOver < i3 ? MinMaxPriorityQueue.this.elementData(i3) : MinMaxPriorityQueue.this.elementData(getParentIndex(i3));
            if (this.otherHeap.bubbleUpAlternatingLevels(crossOver, e3) < i3) {
                return new MoveDesc<>(e3, elementData);
            }
            return null;
        }
    }

    public static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        public MoveDesc(E e3, E e4) {
            this.toTrickle = e3;
            this.replaced = e4;
        }
    }

    public class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        @CheckForNull
        private Queue<E> forgetMeNot;
        @CheckForNull
        private E lastFromForgetMeNot;
        private int nextCursor;
        @CheckForNull
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e3) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e3) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        private void nextNotInSkipMe(int i3) {
            if (this.nextCursor < i3) {
                if (this.skipMe != null) {
                    while (i3 < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.elementData(i3))) {
                        i3++;
                    }
                }
                this.nextCursor = i3;
            }
        }

        private boolean removeExact(Object obj) {
            for (int i3 = 0; i3 < MinMaxPriorityQueue.this.size; i3++) {
                if (MinMaxPriorityQueue.this.queue[i3] == obj) {
                    MinMaxPriorityQueue.this.removeAt(i3);
                    return true;
                }
            }
            return false;
        }

        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                return true;
            }
            Queue<E> queue = this.forgetMeNot;
            return queue != null && !queue.isEmpty();
        }

        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                int i3 = this.nextCursor;
                this.cursor = i3;
                this.canRemove = true;
                return MinMaxPriorityQueue.this.elementData(i3);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                E poll = this.forgetMeNot.poll();
                this.lastFromForgetMeNot = poll;
                if (poll != null) {
                    this.canRemove = true;
                    return poll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc removeAt = MinMaxPriorityQueue.this.removeAt(this.cursor);
                if (removeAt != null) {
                    if (this.forgetMeNot == null || this.skipMe == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    if (!foundAndRemovedExactReference(this.skipMe, removeAt.toTrickle)) {
                        this.forgetMeNot.add(removeAt.toTrickle);
                    }
                    if (!foundAndRemovedExactReference(this.forgetMeNot, removeAt.replaced)) {
                        this.skipMe.add(removeAt.replaced);
                    }
                }
                this.cursor--;
                this.nextCursor--;
                return;
            }
            E e3 = this.lastFromForgetMeNot;
            Objects.requireNonNull(e3);
            Preconditions.checkState(removeExact(e3));
            this.lastFromForgetMeNot = null;
        }
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    private static int capAtMaximumSize(int i3, int i4) {
        return Math.min(i3 - 1, i4) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static Builder<Comparable> expectedSize(int i3) {
        return new Builder(Ordering.natural()).expectedSize(i3);
    }

    @CheckForNull
    private MoveDesc<E> fillHole(int i3, E e3) {
        MinMaxPriorityQueue<E>.Heap heapForIndex = heapForIndex(i3);
        int fillHoleAt = heapForIndex.fillHoleAt(i3);
        int bubbleUpAlternatingLevels = heapForIndex.bubbleUpAlternatingLevels(fillHoleAt, e3);
        if (bubbleUpAlternatingLevels == fillHoleAt) {
            return heapForIndex.tryCrossOverAndBubbleUp(i3, fillHoleAt, e3);
        }
        if (bubbleUpAlternatingLevels < i3) {
            return new MoveDesc<>(e3, elementData(i3));
        }
        return null;
    }

    private int getMaxElementIndex() {
        int i3 = this.size;
        if (i3 != 1) {
            return (i3 == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i3) {
        return isEvenLevel(i3) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    public static int initialQueueSize(int i3, int i4, Iterable<?> iterable) {
        if (i3 == -1) {
            i3 = 11;
        }
        if (iterable instanceof Collection) {
            i3 = Math.max(i3, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i3, i4);
    }

    @VisibleForTesting
    public static boolean isEvenLevel(int i3) {
        int i4 = ~(~(i3 + 1));
        Preconditions.checkState(i4 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & i4) > (i4 & ODD_POWERS_OF_TWO);
    }

    public static Builder<Comparable> maximumSize(int i3) {
        return new Builder(Ordering.natural()).maximumSize(i3);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i3) {
        E elementData = elementData(i3);
        removeAt(i3);
        return elementData;
    }

    @CanIgnoreReturnValue
    public boolean add(E e3) {
        offer(e3);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        boolean z2 = false;
        for (Object offer : collection) {
            offer(offer);
            z2 = true;
        }
        return z2;
    }

    @VisibleForTesting
    public int capacity() {
        return this.queue.length;
    }

    public void clear() {
        for (int i3 = 0; i3 < this.size; i3++) {
            this.queue[i3] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    public E elementData(int i3) {
        E e3 = this.queue[i3];
        Objects.requireNonNull(e3);
        return e3;
    }

    @VisibleForTesting
    public boolean isIntact() {
        for (int i3 = 1; i3 < this.size; i3++) {
            if (!heapForIndex(i3).verifyIndex(i3)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @CanIgnoreReturnValue
    public boolean offer(E e3) {
        Preconditions.checkNotNull(e3);
        this.modCount++;
        int i3 = this.size;
        this.size = i3 + 1;
        growIfNeeded();
        heapForIndex(i3).bubbleUp(i3, e3);
        return this.size <= this.maximumSize || pollLast() != e3;
    }

    @CheckForNull
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    @CheckForNull
    public E peekFirst() {
        return peek();
    }

    @CheckForNull
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @CanIgnoreReturnValue
    @VisibleForTesting
    @CheckForNull
    public MoveDesc<E> removeAt(int i3) {
        Preconditions.checkPositionIndex(i3, this.size);
        this.modCount++;
        int i4 = this.size - 1;
        this.size = i4;
        if (i4 == i3) {
            this.queue[i4] = null;
            return null;
        }
        Object elementData = elementData(i4);
        int swapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(elementData);
        if (swapWithConceptuallyLastElement == i3) {
            this.queue[this.size] = null;
            return null;
        }
        Object elementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> fillHole = fillHole(i3, elementData2);
        return swapWithConceptuallyLastElement < i3 ? fillHole == null ? new MoveDesc<>(elementData, elementData2) : new MoveDesc<>(elementData, fillHole.replaced) : fillHole;
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (!isEmpty()) {
            return removeAndGet(getMaxElementIndex());
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return this.size;
    }

    @J2ktIncompatible
    public Object[] toArray() {
        int i3 = this.size;
        Object[] objArr = new Object[i3];
        System.arraycopy(this.queue, 0, objArr, 0, i3);
        return objArr;
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int i3) {
        Ordering access$200 = builder.ordering();
        MinMaxPriorityQueue<E>.Heap heap = new Heap(access$200);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(access$200.reverse());
        this.maxHeap = heap2;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = builder.maximumSize;
        this.queue = new Object[i3];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }
}
