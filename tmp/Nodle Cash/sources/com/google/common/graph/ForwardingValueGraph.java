package com.google.common.graph;

import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class ForwardingValueGraph<N, V> extends AbstractValueGraph<N, V> {
    public Set<N> adjacentNodes(N n2) {
        return delegate().adjacentNodes(n2);
    }

    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    public int degree(N n2) {
        return delegate().degree(n2);
    }

    public abstract ValueGraph<N, V> delegate();

    public long edgeCount() {
        return (long) delegate().edges().size();
    }

    @CheckForNull
    public V edgeValueOrDefault(N n2, N n3, @CheckForNull V v2) {
        return delegate().edgeValueOrDefault(n2, n3, v2);
    }

    public boolean hasEdgeConnecting(N n2, N n3) {
        return delegate().hasEdgeConnecting(n2, n3);
    }

    public int inDegree(N n2) {
        return delegate().inDegree(n2);
    }

    public ElementOrder<N> incidentEdgeOrder() {
        return delegate().incidentEdgeOrder();
    }

    public boolean isDirected() {
        return delegate().isDirected();
    }

    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    public Set<N> nodes() {
        return delegate().nodes();
    }

    public int outDegree(N n2) {
        return delegate().outDegree(n2);
    }

    @CheckForNull
    public V edgeValueOrDefault(EndpointPair<N> endpointPair, @CheckForNull V v2) {
        return delegate().edgeValueOrDefault(endpointPair, v2);
    }

    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        return delegate().hasEdgeConnecting(endpointPair);
    }

    public Set<N> predecessors(N n2) {
        return delegate().predecessors((Object) n2);
    }

    public Set<N> successors(N n2) {
        return delegate().successors((Object) n2);
    }
}
