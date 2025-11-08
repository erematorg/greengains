package com.google.protobuf.kotlin;

import com.google.protobuf.ExtensionLite;
import com.google.protobuf.MessageLite;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B/\b\u0007\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\bJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0003¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0001J\u0013\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\fH\u0016J\u0016\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u001eJ\t\u0010\u001f\u001a\u00020\u0010H\u0001J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0002J\u0016\u0010\"\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u001eJ\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u001f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0001J\b\u0010(\u001a\u00020)H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006*"}, d2 = {"Lcom/google/protobuf/kotlin/ExtensionList;", "E", "M", "Lcom/google/protobuf/MessageLite;", "", "extension", "Lcom/google/protobuf/ExtensionLite;", "delegate", "(Lcom/google/protobuf/ExtensionLite;Ljava/util/List;)V", "getExtension", "()Lcom/google/protobuf/ExtensionLite;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "", "equals", "other", "", "get", "index", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "", "java_kotlin-shared_runtime"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ExtensionList<E, M extends MessageLite> implements List<E>, KMappedMarker {
    @NotNull
    private final List<E> delegate;
    @NotNull
    private final ExtensionLite<M, List<E>> extension;

    @OnlyForUseByGeneratedProtoCode
    public ExtensionList(@NotNull ExtensionLite<M, List<E>> extensionLite, @NotNull List<? extends E> list) {
        Intrinsics.checkNotNullParameter(extensionLite, SchemaSymbols.ATTVAL_EXTENSION);
        Intrinsics.checkNotNullParameter(list, "delegate");
        this.extension = extensionLite;
        this.delegate = list;
    }

    public void add(int i3, E e3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i3, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Object obj) {
        return this.delegate.contains(obj);
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        return this.delegate.containsAll(collection);
    }

    public boolean equals(@Nullable Object obj) {
        return Intrinsics.areEqual((Object) this.delegate, obj);
    }

    public E get(int i3) {
        return this.delegate.get(i3);
    }

    @NotNull
    public final ExtensionLite<M, List<E>> getExtension() {
        return this.extension;
    }

    public int getSize() {
        return this.delegate.size();
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public int indexOf(Object obj) {
        return this.delegate.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @NotNull
    public Iterator<E> iterator() {
        return new UnmodifiableIterator(this.delegate.iterator());
    }

    public int lastIndexOf(Object obj) {
        return this.delegate.lastIndexOf(obj);
    }

    @NotNull
    public ListIterator<E> listIterator() {
        return new UnmodifiableListIterator(this.delegate.listIterator());
    }

    public E remove(int i3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void replaceAll(UnaryOperator<E> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public E set(int i3, E e3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public void sort(Comparator<? super E> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public List<E> subList(int i3, int i4) {
        return this.delegate.subList(i3, i4);
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @NotNull
    public String toString() {
        return this.delegate.toString();
    }

    public boolean add(E e3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public ListIterator<E> listIterator(int i3) {
        return new UnmodifiableListIterator(this.delegate.listIterator(i3));
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public <T> T[] toArray(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "array");
        return CollectionToArray.toArray(this, tArr);
    }
}
