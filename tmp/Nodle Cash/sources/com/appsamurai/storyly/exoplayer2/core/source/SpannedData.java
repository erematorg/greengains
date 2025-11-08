package com.appsamurai.storyly.exoplayer2.core.source;

import android.util.SparseArray;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Consumer;

final class SpannedData<V> {
    private int memoizedReadIndex;
    private final Consumer<V> removeCallback;
    private final SparseArray<V> spans;

    public SpannedData() {
        this(new l(1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(Object obj) {
    }

    public void appendSpan(int i3, V v2) {
        boolean z2 = false;
        if (this.memoizedReadIndex == -1) {
            Assertions.checkState(this.spans.size() == 0);
            this.memoizedReadIndex = 0;
        }
        if (this.spans.size() > 0) {
            SparseArray<V> sparseArray = this.spans;
            int keyAt = sparseArray.keyAt(sparseArray.size() - 1);
            if (i3 >= keyAt) {
                z2 = true;
            }
            Assertions.checkArgument(z2);
            if (keyAt == i3) {
                Consumer<V> consumer = this.removeCallback;
                SparseArray<V> sparseArray2 = this.spans;
                consumer.accept(sparseArray2.valueAt(sparseArray2.size() - 1));
            }
        }
        this.spans.append(i3, v2);
    }

    public void clear() {
        for (int i3 = 0; i3 < this.spans.size(); i3++) {
            this.removeCallback.accept(this.spans.valueAt(i3));
        }
        this.memoizedReadIndex = -1;
        this.spans.clear();
    }

    public void discardFrom(int i3) {
        int size = this.spans.size() - 1;
        while (size >= 0 && i3 < this.spans.keyAt(size)) {
            this.removeCallback.accept(this.spans.valueAt(size));
            this.spans.removeAt(size);
            size--;
        }
        this.memoizedReadIndex = this.spans.size() > 0 ? Math.min(this.memoizedReadIndex, this.spans.size() - 1) : -1;
    }

    public void discardTo(int i3) {
        int i4 = 0;
        while (i4 < this.spans.size() - 1) {
            int i5 = i4 + 1;
            if (i3 >= this.spans.keyAt(i5)) {
                this.removeCallback.accept(this.spans.valueAt(i4));
                this.spans.removeAt(i4);
                int i6 = this.memoizedReadIndex;
                if (i6 > 0) {
                    this.memoizedReadIndex = i6 - 1;
                }
                i4 = i5;
            } else {
                return;
            }
        }
    }

    public V get(int i3) {
        if (this.memoizedReadIndex == -1) {
            this.memoizedReadIndex = 0;
        }
        while (true) {
            int i4 = this.memoizedReadIndex;
            if (i4 > 0 && i3 < this.spans.keyAt(i4)) {
                this.memoizedReadIndex--;
            }
        }
        while (this.memoizedReadIndex < this.spans.size() - 1 && i3 >= this.spans.keyAt(this.memoizedReadIndex + 1)) {
            this.memoizedReadIndex++;
        }
        return this.spans.valueAt(this.memoizedReadIndex);
    }

    public V getEndValue() {
        SparseArray<V> sparseArray = this.spans;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    public boolean isEmpty() {
        return this.spans.size() == 0;
    }

    public SpannedData(Consumer<V> consumer) {
        this.spans = new SparseArray<>();
        this.removeCallback = consumer;
        this.memoizedReadIndex = -1;
    }
}
