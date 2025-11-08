package com.google.common.reflect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@DoNotMock("Use ImmutableTypeToInstanceMap or MutableTypeToInstanceMap")
public interface TypeToInstanceMap<B> extends Map<TypeToken<? extends B>, B> {
    @CheckForNull
    <T extends B> T getInstance(TypeToken<T> typeToken);

    @CheckForNull
    <T extends B> T getInstance(Class<T> cls);

    @CheckForNull
    @CanIgnoreReturnValue
    <T extends B> T putInstance(TypeToken<T> typeToken, @ParametricNullness T t2);

    @CheckForNull
    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> cls, @ParametricNullness T t2);
}
