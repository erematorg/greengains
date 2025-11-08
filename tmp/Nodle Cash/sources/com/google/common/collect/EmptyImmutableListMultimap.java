package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;

@GwtCompatible(serializable = true)
@ElementTypesAreNonnullByDefault
class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
    static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableListMultimap() {
        super(ImmutableMap.of(), 0);
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public ImmutableMap<Object, Collection<Object>> asMap() {
        return super.asMap();
    }
}
