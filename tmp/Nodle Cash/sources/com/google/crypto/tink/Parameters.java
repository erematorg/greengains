package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.Immutable;

@Immutable
@Alpha
public abstract class Parameters {
    public abstract boolean hasIdRequirement();
}
