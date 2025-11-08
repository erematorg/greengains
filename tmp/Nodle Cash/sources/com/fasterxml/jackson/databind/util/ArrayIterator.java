package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
    private final T[] _a;
    private int _index = 0;

    public ArrayIterator(T[] tArr) {
        this._a = tArr;
    }

    public boolean hasNext() {
        return this._index < this._a.length;
    }

    public Iterator<T> iterator() {
        return this;
    }

    public T next() {
        int i3 = this._index;
        T[] tArr = this._a;
        if (i3 < tArr.length) {
            this._index = i3 + 1;
            return tArr[i3];
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
