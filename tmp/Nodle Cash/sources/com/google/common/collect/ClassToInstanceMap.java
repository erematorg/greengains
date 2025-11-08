package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use ImmutableClassToInstanceMap or MutableClassToInstanceMap")
public interface ClassToInstanceMap<B> extends Map<Class<? extends B>, B> {
    @CheckForNull
    <T extends B> T getInstance(Class<T> cls);

    @CheckForNull
    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> cls, @ParametricNullness T t2);
}
