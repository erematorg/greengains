package com.fasterxml.jackson.databind.util.internal;

import com.fasterxml.jackson.databind.util.internal.Linked;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class LinkedDeque<E extends Linked<E>> extends AbstractCollection<E> implements Deque<E> {
    E first;
    E last;

    public abstract class AbstractLinkedIterator implements Iterator<E> {
        E cursor;

        public AbstractLinkedIterator(E e3) {
            this.cursor = e3;
        }

        public abstract E computeNext();

        public boolean hasNext() {
            return this.cursor != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (hasNext()) {
                E e3 = this.cursor;
                this.cursor = computeNext();
                return e3;
            }
            throw new NoSuchElementException();
        }
    }

    public void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public void clear() {
        E e3 = this.first;
        while (e3 != null) {
            E next = e3.getNext();
            e3.setPrevious(null);
            e3.setNext(null);
            e3 = next;
        }
        this.last = null;
        this.first = null;
    }

    public boolean contains(Object obj) {
        return (obj instanceof Linked) && contains((Linked<?>) (Linked) obj);
    }

    public Iterator<E> descendingIterator() {
        return new LinkedDeque<E>.AbstractLinkedIterator(this.last) {
            public E computeNext() {
                return this.cursor.getPrevious();
            }
        };
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public Iterator<E> iterator() {
        return new LinkedDeque<E>.AbstractLinkedIterator(this.first) {
            public E computeNext() {
                return this.cursor.getNext();
            }
        };
    }

    public void linkFirst(E e3) {
        E e4 = this.first;
        this.first = e3;
        if (e4 == null) {
            this.last = e3;
            return;
        }
        e4.setPrevious(e3);
        e3.setNext(e4);
    }

    public void linkLast(E e3) {
        E e4 = this.last;
        this.last = e3;
        if (e4 == null) {
            this.first = e3;
            return;
        }
        e4.setNext(e3);
        e3.setPrevious(e4);
    }

    public void moveToBack(E e3) {
        if (e3 != this.last) {
            unlink(e3);
            linkLast(e3);
        }
    }

    public void moveToFront(E e3) {
        if (e3 != this.first) {
            unlink(e3);
            linkFirst(e3);
        }
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z2 = false;
        for (Object remove : collection) {
            z2 |= remove((Object) remove);
        }
        return z2;
    }

    public boolean removeFirstOccurrence(Object obj) {
        return remove(obj);
    }

    public boolean removeLastOccurrence(Object obj) {
        return remove(obj);
    }

    public int size() {
        int i3 = 0;
        for (E e3 = this.first; e3 != null; e3 = e3.getNext()) {
            i3++;
        }
        return i3;
    }

    public void unlink(E e3) {
        E previous = e3.getPrevious();
        E next = e3.getNext();
        if (previous == null) {
            this.first = next;
        } else {
            previous.setNext(next);
            e3.setPrevious(null);
        }
        if (next == null) {
            this.last = previous;
            return;
        }
        next.setPrevious(previous);
        e3.setNext(null);
    }

    public E unlinkFirst() {
        E e3 = this.first;
        E next = e3.getNext();
        e3.setNext(null);
        this.first = next;
        if (next == null) {
            this.last = null;
        } else {
            next.setPrevious(null);
        }
        return e3;
    }

    public E unlinkLast() {
        E e3 = this.last;
        E previous = e3.getPrevious();
        e3.setPrevious(null);
        this.last = previous;
        if (previous == null) {
            this.first = null;
        } else {
            previous.setNext(null);
        }
        return e3;
    }

    public boolean add(E e3) {
        return offerLast(e3);
    }

    public void addFirst(E e3) {
        if (!offerFirst(e3)) {
            throw new IllegalArgumentException();
        }
    }

    public void addLast(E e3) {
        if (!offerLast(e3)) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(Linked<?> linked) {
        return (linked.getPrevious() == null && linked.getNext() == null && linked != this.first) ? false : true;
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        checkNotEmpty();
        return peekFirst();
    }

    public E getLast() {
        checkNotEmpty();
        return peekLast();
    }

    public boolean offer(E e3) {
        return offerLast(e3);
    }

    public boolean offerFirst(E e3) {
        if (contains((Linked<?>) e3)) {
            return false;
        }
        linkFirst(e3);
        return true;
    }

    public boolean offerLast(E e3) {
        if (contains((Linked<?>) e3)) {
            return false;
        }
        linkLast(e3);
        return true;
    }

    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        return this.first;
    }

    public E peekLast() {
        return this.last;
    }

    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        return unlinkFirst();
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return unlinkLast();
    }

    public E pop() {
        return removeFirst();
    }

    public void push(E e3) {
        addFirst(e3);
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        checkNotEmpty();
        return pollFirst();
    }

    public E removeLast() {
        checkNotEmpty();
        return pollLast();
    }

    public boolean remove(Object obj) {
        return (obj instanceof Linked) && remove((Linked) obj);
    }

    public boolean remove(E e3) {
        if (!contains((Linked<?>) e3)) {
            return false;
        }
        unlink(e3);
        return true;
    }
}
