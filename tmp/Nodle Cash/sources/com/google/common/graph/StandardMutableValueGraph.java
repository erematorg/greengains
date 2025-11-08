package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
    private final ElementOrder<N> incidentEdgeOrder;

    public StandardMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
        this.incidentEdgeOrder = abstractGraphBuilder.incidentEdgeOrder.cast();
    }

    @CanIgnoreReturnValue
    private GraphConnections<N, V> addNodeInternal(N n2) {
        GraphConnections<N, V> newConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(n2, newConnections) == null);
        return newConnections;
    }

    private GraphConnections<N, V> newConnections() {
        return isDirected() ? DirectedGraphConnections.of(this.incidentEdgeOrder) : UndirectedGraphConnections.of(this.incidentEdgeOrder);
    }

    @CanIgnoreReturnValue
    public boolean addNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        if (containsNode(n2)) {
            return false;
        }
        addNodeInternal(n2);
        return true;
    }

    public ElementOrder<N> incidentEdgeOrder() {
        return this.incidentEdgeOrder;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V putEdgeValue(N n2, N n3, V v2) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        Preconditions.checkNotNull(v2, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n2.equals(n3), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", (Object) n2);
        }
        GraphConnections graphConnections = this.nodeConnections.get(n2);
        if (graphConnections == null) {
            graphConnections = addNodeInternal(n2);
        }
        V addSuccessor = graphConnections.addSuccessor(n3, v2);
        GraphConnections graphConnections2 = this.nodeConnections.get(n3);
        if (graphConnections2 == null) {
            graphConnections2 = addNodeInternal(n3);
        }
        graphConnections2.addPredecessor(n2, v2);
        if (addSuccessor == null) {
            long j2 = this.edgeCount + 1;
            this.edgeCount = j2;
            Graphs.checkPositive(j2);
        }
        return addSuccessor;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V removeEdge(N n2, N n3) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        GraphConnections graphConnections = this.nodeConnections.get(n2);
        GraphConnections graphConnections2 = this.nodeConnections.get(n3);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V removeSuccessor = graphConnections.removeSuccessor(n3);
        if (removeSuccessor != null) {
            graphConnections2.removePredecessor(n2);
            long j2 = this.edgeCount - 1;
            this.edgeCount = j2;
            Graphs.checkNonNegative(j2);
        }
        return removeSuccessor;
    }

    @CanIgnoreReturnValue
    public boolean removeNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        GraphConnections graphConnections = this.nodeConnections.get(n2);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(n2) != null) {
            graphConnections.removePredecessor(n2);
            this.edgeCount--;
        }
        UnmodifiableIterator it = ImmutableList.copyOf(graphConnections.successors()).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            GraphConnections withoutCaching = this.nodeConnections.getWithoutCaching(next);
            Objects.requireNonNull(withoutCaching);
            withoutCaching.removePredecessor(n2);
            Objects.requireNonNull(graphConnections.removeSuccessor(next));
            this.edgeCount--;
        }
        if (isDirected()) {
            UnmodifiableIterator it2 = ImmutableList.copyOf(graphConnections.predecessors()).iterator();
            while (it2.hasNext()) {
                Object next2 = it2.next();
                GraphConnections withoutCaching2 = this.nodeConnections.getWithoutCaching(next2);
                Objects.requireNonNull(withoutCaching2);
                Preconditions.checkState(withoutCaching2.removeSuccessor(n2) != null);
                graphConnections.removePredecessor(next2);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(n2);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V removeEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V putEdgeValue(EndpointPair<N> endpointPair, V v2) {
        validateEndpoints(endpointPair);
        return putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), v2);
    }
}
