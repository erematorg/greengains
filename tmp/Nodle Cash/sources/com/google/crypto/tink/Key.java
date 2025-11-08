package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.Immutable;
import javax.annotation.Nullable;

@Immutable
@Alpha
public abstract class Key {
    public abstract boolean equalsKey(Key key);

    @Nullable
    public abstract Integer getIdRequirementOrNull();

    public abstract Parameters getParameters();
}
