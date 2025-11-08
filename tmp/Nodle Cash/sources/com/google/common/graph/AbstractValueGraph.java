package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@Beta
@ElementTypesAreNonnullByDefault
public abstract class AbstractValueGraph<N, V> extends AbstractBaseGraph<N> implements ValueGraph<N, V> {
    private static <N, V> Map<EndpointPair<N>, V> edgeValueMap(ValueGraph<N, V> valueGraph) {
        return Maps.asMap(valueGraph.edges(), new c(valueGraph, 2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$edgeValueMap$0(ValueGraph valueGraph, EndpointPair endpointPair) {
        Object edgeValueOrDefault = valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null);
        Objects.requireNonNull(edgeValueOrDefault);
        return edgeValueOrDefault;
    }

    public Graph<N> asGraph() {
        return new AbstractGraph<N>() {
            public Set<N> adjacentNodes(N n2) {
                return AbstractValueGraph.this.adjacentNodes(n2);
            }

            public boolean allowsSelfLoops() {
                return AbstractValueGraph.this.allowsSelfLoops();
            }

            public int degree(N n2) {
                return AbstractValueGraph.this.degree(n2);
            }

            public Set<EndpointPair<N>> edges() {
                return AbstractValueGraph.this.edges();
            }

            public int inDegree(N n2) {
                return AbstractValueGraph.this.inDegree(n2);
            }

            public ElementOrder<N> incidentEdgeOrder() {
                return AbstractValueGraph.this.incidentEdgeOrder();
            }

            public boolean isDirected() {
                return AbstractValueGraph.this.isDirected();
            }

            public ElementOrder<N> nodeOrder() {
                return AbstractValueGraph.this.nodeOrder();
            }

            public Set<N> nodes() {
                return AbstractValueGraph.this.nodes();
            }

            public int outDegree(N n2) {
                return AbstractValueGraph.this.outDegree(n2);
            }

            public Set<N> predecessors(N n2) {
                return AbstractValueGraph.this.predecessors((Object) n2);
            }

            public Set<N> successors(N n2) {
                return AbstractValueGraph.this.successors((Object) n2);
            }
        };
    }

    public /* bridge */ /* synthetic */ int degree(Object obj) {
        return super.degree(obj);
    }

    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueGraph)) {
            return false;
        }
        ValueGraph valueGraph = (ValueGraph) obj;
        return isDirected() == valueGraph.isDirected() && nodes().equals(valueGraph.nodes()) && edgeValueMap(this).equals(edgeValueMap(valueGraph));
    }

    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return super.hasEdgeConnecting(endpointPair);
    }

    public final int hashCode() {
        return edgeValueMap(this).hashCode();
    }

    public /* bridge */ /* synthetic */ int inDegree(Object obj) {
        return super.inDegree(obj);
    }

    public /* bridge */ /* synthetic */ ElementOrder incidentEdgeOrder() {
        return super.incidentEdgeOrder();
    }

    public /* bridge */ /* synthetic */ Set incidentEdges(Object obj) {
        return super.incidentEdges(obj);
    }

    public /* bridge */ /* synthetic */ int outDegree(Object obj) {
        return super.outDegree(obj);
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeValueMap(this);
    }

    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object obj, Object obj2) {
        return super.hasEdgeConnecting(obj, obj2);
    }
}
