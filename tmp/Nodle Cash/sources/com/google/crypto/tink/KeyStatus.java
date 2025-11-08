package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.Immutable;

@Immutable
@Alpha
public final class KeyStatus {
    public static final KeyStatus DESTROYED = new KeyStatus("DESTROYED");
    public static final KeyStatus DISABLED = new KeyStatus("DISABLED");
    public static final KeyStatus ENABLED = new KeyStatus("ENABLED");
    private final String name;

    private KeyStatus(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
