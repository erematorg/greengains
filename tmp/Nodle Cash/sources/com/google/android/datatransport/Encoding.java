package com.google.android.datatransport;

import A.a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Encoding {
    private final String name;

    private Encoding(@NonNull String str) {
        if (str != null) {
            this.name = str;
            return;
        }
        throw new NullPointerException("name is null");
    }

    public static Encoding of(@NonNull String str) {
        return new Encoding(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Encoding)) {
            return false;
        }
        return this.name.equals(((Encoding) obj).name);
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }

    @NonNull
    public String toString() {
        return a.n(new StringBuilder("Encoding{name=\""), this.name, "\"}");
    }
}
