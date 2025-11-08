package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import org.jspecify.nullness.NullMarked;

@NullMarked
public abstract class zzaj implements Iterator {
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
