package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;
import javax.annotation.CheckForNull;

@Beta
@ElementTypesAreNonnullByDefault
public interface ValueGraph<N, V> extends BaseGraph<N> {
    Set<N> adjacentNodes(N n2);

    boolean allowsSelfLoops();

    Graph<N> asGraph();

    int degree(N n2);

    @CheckForNull
    V edgeValueOrDefault(EndpointPair<N> endpointPair, @CheckForNull V v2);

    @CheckForNull
    V edgeValueOrDefault(N n2, N n3, @CheckForNull V v2);

    Set<EndpointPair<N>> edges();

    boolean equals(@CheckForNull Object obj);

    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    boolean hasEdgeConnecting(N n2, N n3);

    int hashCode();

    int inDegree(N n2);

    ElementOrder<N> incidentEdgeOrder();

    Set<EndpointPair<N>> incidentEdges(N n2);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N n2);

    Set<N> predecessors(N n2);

    Set<N> successors(N n2);
}
