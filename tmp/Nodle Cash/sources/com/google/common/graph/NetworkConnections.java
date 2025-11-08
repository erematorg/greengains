package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
interface NetworkConnections<N, E> {
    void addInEdge(E e3, N n2, boolean z2);

    void addOutEdge(E e3, N n2);

    N adjacentNode(E e3);

    Set<N> adjacentNodes();

    Set<E> edgesConnecting(N n2);

    Set<E> inEdges();

    Set<E> incidentEdges();

    Set<E> outEdges();

    Set<N> predecessors();

    @CheckForNull
    @CanIgnoreReturnValue
    N removeInEdge(E e3, boolean z2);

    @CanIgnoreReturnValue
    N removeOutEdge(E e3);

    Set<N> successors();
}
