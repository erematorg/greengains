package com.fasterxml.jackson.databind.util;

import androidx.compose.animation.core.a;
import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    private static final int MAX_CHUNK = 262144;
    private static final int SMALL_CHUNK = 16384;
    private Object[] _freeBuffer;
    private LinkedNode<Object[]> _head;
    private int _size;
    private LinkedNode<Object[]> _tail;

    public final void _copyTo(Object obj, int i3, Object[] objArr, int i4) {
        int i5 = 0;
        for (LinkedNode<Object[]> linkedNode = this._head; linkedNode != null; linkedNode = linkedNode.next()) {
            Object[] value = linkedNode.value();
            int length = value.length;
            System.arraycopy(value, 0, obj, i5, length);
            i5 += length;
        }
        System.arraycopy(objArr, 0, obj, i5, i4);
        int i6 = i5 + i4;
        if (i6 != i3) {
            throw new IllegalStateException(a.r("Should have gotten ", i3, " entries, got ", i6));
        }
    }

    public void _reset() {
        LinkedNode<Object[]> linkedNode = this._tail;
        if (linkedNode != null) {
            this._freeBuffer = linkedNode.value();
        }
        this._tail = null;
        this._head = null;
        this._size = 0;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        LinkedNode<Object[]> linkedNode = new LinkedNode<>(objArr, (LinkedNode) null);
        if (this._head == null) {
            this._tail = linkedNode;
            this._head = linkedNode;
        } else {
            this._tail.linkNext(linkedNode);
            this._tail = linkedNode;
        }
        int length = objArr.length;
        this._size += length;
        if (length < 16384) {
            length += length;
        } else if (length < 262144) {
            length += length >> 2;
        }
        return new Object[length];
    }

    public int bufferedSize() {
        return this._size;
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int i3) {
        int i4 = this._size + i3;
        Object[] objArr2 = new Object[i4];
        _copyTo(objArr2, i4, objArr, i3);
        _reset();
        return objArr2;
    }

    public int initialCapacity() {
        Object[] objArr = this._freeBuffer;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public Object[] resetAndStart() {
        _reset();
        Object[] objArr = this._freeBuffer;
        if (objArr != null) {
            return objArr;
        }
        Object[] objArr2 = new Object[12];
        this._freeBuffer = objArr2;
        return objArr2;
    }

    public Object[] resetAndStart(Object[] objArr, int i3) {
        _reset();
        Object[] objArr2 = this._freeBuffer;
        if (objArr2 == null || objArr2.length < i3) {
            this._freeBuffer = new Object[Math.max(12, i3)];
        }
        System.arraycopy(objArr, 0, this._freeBuffer, 0, i3);
        return this._freeBuffer;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int i3, Class<T> cls) {
        int i4 = this._size + i3;
        T[] tArr = (Object[]) Array.newInstance(cls, i4);
        _copyTo(tArr, i4, objArr, i3);
        _reset();
        return tArr;
    }

    public void completeAndClearBuffer(Object[] objArr, int i3, List<Object> list) {
        int i4;
        LinkedNode<Object[]> linkedNode = this._head;
        while (true) {
            i4 = 0;
            if (linkedNode == null) {
                break;
            }
            Object[] value = linkedNode.value();
            int length = value.length;
            while (i4 < length) {
                list.add(value[i4]);
                i4++;
            }
            linkedNode = linkedNode.next();
        }
        while (i4 < i3) {
            list.add(objArr[i4]);
            i4++;
        }
        _reset();
    }
}
