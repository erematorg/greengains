package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
@ElementTypesAreNonnullByDefault
public interface MutableNetwork<N, E> extends Network<N, E> {
    @CanIgnoreReturnValue
    boolean addEdge(EndpointPair<N> endpointPair, E e3);

    @CanIgnoreReturnValue
    boolean addEdge(N n2, N n3, E e3);

    @CanIgnoreReturnValue
    boolean addNode(N n2);

    @CanIgnoreReturnValue
    boolean removeEdge(E e3);

    @CanIgnoreReturnValue
    boolean removeNode(N n2);
}
