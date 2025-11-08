package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    @CheckForNull
    private T nextOrNull;

    public AbstractSequentialIterator(@CheckForNull T t2) {
        this.nextOrNull = t2;
    }

    @CheckForNull
    public abstract T computeNext(T t2);

    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    public final T next() {
        T t2 = this.nextOrNull;
        if (t2 != null) {
            this.nextOrNull = computeNext(t2);
            return t2;
        }
        throw new NoSuchElementException();
    }
}
