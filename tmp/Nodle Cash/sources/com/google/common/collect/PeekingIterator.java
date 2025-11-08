package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Iterator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use Iterators.peekingIterator")
public interface PeekingIterator<E> extends Iterator<E> {
    @ParametricNullness
    @CanIgnoreReturnValue
    E next();

    @ParametricNullness
    E peek();

    void remove();
}
