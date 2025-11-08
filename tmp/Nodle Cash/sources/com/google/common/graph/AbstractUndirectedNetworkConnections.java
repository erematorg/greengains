package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    final Map<E, N> incidentEdgeMap;

    public AbstractUndirectedNetworkConnections(Map<E, N> map) {
        this.incidentEdgeMap = (Map) Preconditions.checkNotNull(map);
    }

    public void addInEdge(E e3, N n2, boolean z2) {
        if (!z2) {
            addOutEdge(e3, n2);
        }
    }

    public void addOutEdge(E e3, N n2) {
        Preconditions.checkState(this.incidentEdgeMap.put(e3, n2) == null);
    }

    public N adjacentNode(E e3) {
        N n2 = this.incidentEdgeMap.get(e3);
        Objects.requireNonNull(n2);
        return n2;
    }

    public Set<E> inEdges() {
        return incidentEdges();
    }

    public Set<E> incidentEdges() {
        return Collections.unmodifiableSet(this.incidentEdgeMap.keySet());
    }

    public Set<E> outEdges() {
        return incidentEdges();
    }

    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @CheckForNull
    public N removeInEdge(E e3, boolean z2) {
        if (!z2) {
            return removeOutEdge(e3);
        }
        return null;
    }

    public N removeOutEdge(E e3) {
        N remove = this.incidentEdgeMap.remove(e3);
        Objects.requireNonNull(remove);
        return remove;
    }

    public Set<N> successors() {
        return adjacentNodes();
    }
}
