package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.DoNotMock;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
@DoNotMock("Use an instance of one of the Finalizable*Reference classes")
public interface FinalizableReference {
    void finalizeReferent();
}
