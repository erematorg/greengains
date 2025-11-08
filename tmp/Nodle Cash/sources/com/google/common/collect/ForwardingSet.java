package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
    public abstract Set<E> delegate();

    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    public boolean standardEquals(@CheckForNull Object obj) {
        return Sets.equalsImpl(this, obj);
    }

    public int standardHashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean standardRemoveAll(Collection<?> collection) {
        return Sets.removeAllImpl((Set<?>) this, (Collection<?>) (Collection) Preconditions.checkNotNull(collection));
    }
}
