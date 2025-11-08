package com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class IncidentEdgeSet<N> extends AbstractSet<EndpointPair<N>> {
    final BaseGraph<N> graph;
    final N node;

    public IncidentEdgeSet(BaseGraph<N> baseGraph, N n2) {
        this.graph = baseGraph;
        this.node = n2;
    }

    public boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof EndpointPair)) {
            return false;
        }
        EndpointPair endpointPair = (EndpointPair) obj;
        if (this.graph.isDirected()) {
            if (!endpointPair.isOrdered()) {
                return false;
            }
            Object source = endpointPair.source();
            Object target = endpointPair.target();
            return (this.node.equals(source) && this.graph.successors((Object) this.node).contains(target)) || (this.node.equals(target) && this.graph.predecessors((Object) this.node).contains(source));
        } else if (endpointPair.isOrdered()) {
            return false;
        } else {
            Set<N> adjacentNodes = this.graph.adjacentNodes(this.node);
            Object nodeU = endpointPair.nodeU();
            Object nodeV = endpointPair.nodeV();
            return (this.node.equals(nodeV) && adjacentNodes.contains(nodeU)) || (this.node.equals(nodeU) && adjacentNodes.contains(nodeV));
        }
    }

    public boolean remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.graph.isDirected() ? (this.graph.inDegree(this.node) + this.graph.outDegree(this.node)) - (this.graph.successors((Object) this.node).contains(this.node) ? 1 : 0) : this.graph.adjacentNodes(this.node).size();
    }
}
