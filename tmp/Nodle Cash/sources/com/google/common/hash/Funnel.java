package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;

@ElementTypesAreNonnullByDefault
@Beta
@DoNotMock("Implement with a lambda")
public interface Funnel<T> extends Serializable {
    void funnel(@ParametricNullness T t2, PrimitiveSink primitiveSink);
}
