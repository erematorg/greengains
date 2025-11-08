package com.fasterxml.jackson.databind.util;

import androidx.compose.animation.core.a;

public abstract class PrimitiveArrayBuilder<T> {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    protected Node<T> _bufferHead;
    protected Node<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;

    public static final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(T t2, int i3) {
            this._data = t2;
            this._dataLength = i3;
        }

        public int copyData(T t2, int i3) {
            System.arraycopy(this._data, 0, t2, i3, this._dataLength);
            return i3 + this._dataLength;
        }

        public T getData() {
            return this._data;
        }

        public void linkNext(Node<T> node) {
            if (this._next == null) {
                this._next = node;
                return;
            }
            throw new IllegalStateException();
        }

        public Node<T> next() {
            return this._next;
        }
    }

    public abstract T _constructArray(int i3);

    public void _reset() {
        Node<T> node = this._bufferTail;
        if (node != null) {
            this._freeBuffer = node.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    public final T appendCompletedChunk(T t2, int i3) {
        Node<T> node = new Node<>(t2, i3);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        this._bufferedEntryCount += i3;
        return _constructArray(i3 < 16384 ? i3 + i3 : i3 + (i3 >> 2));
    }

    public int bufferedSize() {
        return this._bufferedEntryCount;
    }

    public T completeAndClearBuffer(T t2, int i3) {
        int i4 = this._bufferedEntryCount + i3;
        T _constructArray = _constructArray(i4);
        int i5 = 0;
        for (Node<T> node = this._bufferHead; node != null; node = node.next()) {
            i5 = node.copyData(_constructArray, i5);
        }
        System.arraycopy(t2, 0, _constructArray, i5, i3);
        int i6 = i5 + i3;
        if (i6 == i4) {
            return _constructArray;
        }
        throw new IllegalStateException(a.r("Should have gotten ", i4, " entries, got ", i6));
    }

    public T resetAndStart() {
        _reset();
        T t2 = this._freeBuffer;
        return t2 == null ? _constructArray(12) : t2;
    }
}
