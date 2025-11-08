package com.google.protobuf.kotlin;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H\u0003J\u000e\u0010\u0007\u001a\u00028\u0000H\u0003¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/protobuf/kotlin/UnmodifiableIterator;", "E", "", "delegate", "(Ljava/util/Iterator;)V", "hasNext", "", "next", "()Ljava/lang/Object;", "java_kotlin-shared_runtime"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UnmodifiableIterator<E> implements Iterator<E>, KMappedMarker {
    private final /* synthetic */ Iterator<E> $$delegate_0;

    public UnmodifiableIterator(@NotNull Iterator<? extends E> it) {
        Intrinsics.checkNotNullParameter(it, "delegate");
        this.$$delegate_0 = it;
    }

    public boolean hasNext() {
        return this.$$delegate_0.hasNext();
    }

    public E next() {
        return this.$$delegate_0.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
