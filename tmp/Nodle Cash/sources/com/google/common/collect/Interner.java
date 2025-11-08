package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.DoNotMock;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@DoNotMock("Use Interners.new*Interner")
public interface Interner<E> {
    E intern(E e3);
}
