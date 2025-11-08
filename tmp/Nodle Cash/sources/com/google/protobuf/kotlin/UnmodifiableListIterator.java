package com.google.protobuf.kotlin;

import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010*\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H\u0003J\t\u0010\u0007\u001a\u00020\u0006H\u0001J\u000e\u0010\b\u001a\u00028\u0000H\u0003¢\u0006\u0002\u0010\tJ\t\u0010\n\u001a\u00020\u000bH\u0001J\u000e\u0010\f\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\tJ\t\u0010\r\u001a\u00020\u000bH\u0001¨\u0006\u000e"}, d2 = {"Lcom/google/protobuf/kotlin/UnmodifiableListIterator;", "E", "", "delegate", "(Ljava/util/ListIterator;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "", "previous", "previousIndex", "java_kotlin-shared_runtime"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UnmodifiableListIterator<E> implements ListIterator<E>, KMappedMarker {
    private final /* synthetic */ ListIterator<E> $$delegate_0;

    public UnmodifiableListIterator(@NotNull ListIterator<? extends E> listIterator) {
        Intrinsics.checkNotNullParameter(listIterator, "delegate");
        this.$$delegate_0 = listIterator;
    }

    public void add(E e3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean hasNext() {
        return this.$$delegate_0.hasNext();
    }

    public boolean hasPrevious() {
        return this.$$delegate_0.hasPrevious();
    }

    public E next() {
        return this.$$delegate_0.next();
    }

    public int nextIndex() {
        return this.$$delegate_0.nextIndex();
    }

    public E previous() {
        return this.$$delegate_0.previous();
    }

    public int previousIndex() {
        return this.$$delegate_0.previousIndex();
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void set(E e3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
