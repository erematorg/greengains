package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzkv implements Iterator {
    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
