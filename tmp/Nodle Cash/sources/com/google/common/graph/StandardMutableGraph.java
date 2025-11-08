package com.google.common.graph;

import com.google.common.graph.GraphConstants;

@ElementTypesAreNonnullByDefault
final class StandardMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
    private final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    public StandardMutableGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this.backingValueGraph = new StandardMutableValueGraph(abstractGraphBuilder);
    }

    public boolean addNode(N n2) {
        return this.backingValueGraph.addNode(n2);
    }

    public BaseGraph<N> delegate() {
        return this.backingValueGraph;
    }

    public boolean putEdge(N n2, N n3) {
        return this.backingValueGraph.putEdgeValue(n2, n3, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    public boolean removeEdge(N n2, N n3) {
        return this.backingValueGraph.removeEdge(n2, n3) != null;
    }

    public boolean removeNode(N n2) {
        return this.backingValueGraph.removeNode(n2);
    }

    public boolean putEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return putEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    public boolean removeEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }
}
