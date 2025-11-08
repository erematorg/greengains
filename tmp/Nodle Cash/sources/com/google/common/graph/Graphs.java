package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@Beta
@ElementTypesAreNonnullByDefault
public final class Graphs {

    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    public static class TransposedGraph<N> extends ForwardingGraph<N> {
        /* access modifiers changed from: private */
        public final Graph<N> graph;

        public TransposedGraph(Graph<N> graph2) {
            this.graph = graph2;
        }

        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        public Set<EndpointPair<N>> incidentEdges(N n2) {
            return new IncidentEdgeSet<N>(this, n2) {
                /* access modifiers changed from: private */
                public /* synthetic */ EndpointPair lambda$iterator$0(EndpointPair endpointPair) {
                    return EndpointPair.of((Graph<?>) TransposedGraph.this.delegate(), endpointPair.nodeV(), endpointPair.nodeU());
                }

                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(TransposedGraph.this.delegate().incidentEdges(this.node).iterator(), new c(this, 1));
                }
            };
        }

        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        public Graph<N> delegate() {
            return this.graph;
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }

        public Set<N> predecessors(N n2) {
            return delegate().successors((Object) n2);
        }

        public Set<N> successors(N n2) {
            return delegate().predecessors((Object) n2);
        }
    }

    public static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        /* access modifiers changed from: private */
        public final Network<N, E> network;

        public TransposedNetwork(Network<N, E> network2) {
            this.network = network2;
        }

        public Network<N, E> delegate() {
            return this.network;
        }

        @CheckForNull
        public E edgeConnectingOrNull(N n2, N n3) {
            return delegate().edgeConnectingOrNull(n3, n2);
        }

        public Set<E> edgesConnecting(N n2, N n3) {
            return delegate().edgesConnecting(n3, n2);
        }

        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        public Set<E> inEdges(N n2) {
            return delegate().outEdges(n2);
        }

        public EndpointPair<N> incidentNodes(E e3) {
            EndpointPair incidentNodes = delegate().incidentNodes(e3);
            return EndpointPair.of((Network<?, ?>) this.network, incidentNodes.nodeV(), incidentNodes.nodeU());
        }

        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        public Set<E> outEdges(N n2) {
            return delegate().inEdges(n2);
        }

        @CheckForNull
        public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
            return delegate().edgeConnectingOrNull(Graphs.transpose(endpointPair));
        }

        public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
            return delegate().edgesConnecting(Graphs.transpose(endpointPair));
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }

        public Set<N> predecessors(N n2) {
            return delegate().successors((Object) n2);
        }

        public Set<N> successors(N n2) {
            return delegate().predecessors((Object) n2);
        }
    }

    public static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        /* access modifiers changed from: private */
        public final ValueGraph<N, V> graph;

        public TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @CheckForNull
        public V edgeValueOrDefault(N n2, N n3, @CheckForNull V v2) {
            return delegate().edgeValueOrDefault(n3, n2, v2);
        }

        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        @CheckForNull
        public V edgeValueOrDefault(EndpointPair<N> endpointPair, @CheckForNull V v2) {
            return delegate().edgeValueOrDefault(Graphs.transpose(endpointPair), v2);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }

        public Set<N> predecessors(N n2) {
            return delegate().successors((Object) n2);
        }

        public Set<N> successors(N n2) {
            return delegate().predecessors((Object) n2);
        }
    }

    private Graphs() {
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @CheckForNull Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(int i3) {
        Preconditions.checkArgument(i3 >= 0, "Not true that %s is non-negative.", i3);
        return i3;
    }

    @CanIgnoreReturnValue
    public static int checkPositive(int i3) {
        Preconditions.checkArgument(i3 > 0, "Not true that %s is positive.", i3);
        return i3;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N addNode : graph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : graph.edges()) {
            build.putEdge(next.nodeU(), next.nodeV());
        }
        return build;
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N subgraphHasCycle : graph.nodes()) {
            if (subgraphHasCycle(graph, newHashMapWithExpectedSize, subgraphHasCycle, (N) null)) {
                return true;
            }
        }
        return false;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N1> mutableGraph;
        if (iterable instanceof Collection) {
            mutableGraph = GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableGraph = GraphBuilder.from(graph).build();
        }
        for (Object addNode : iterable) {
            mutableGraph.addNode(addNode);
        }
        for (N1 next : mutableGraph.nodes()) {
            for (Object next2 : graph.successors((Object) next)) {
                if (mutableGraph.nodes().contains(next2)) {
                    mutableGraph.putEdge(next, next2);
                }
            }
        }
        return mutableGraph;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N n2) {
        Preconditions.checkArgument(graph.nodes().contains(n2), "Node %s is not an element of this graph.", (Object) n2);
        return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst(n2));
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n2, @CheckForNull N n3) {
        NodeVisitState nodeVisitState = map.get(n2);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        NodeVisitState nodeVisitState2 = NodeVisitState.PENDING;
        if (nodeVisitState == nodeVisitState2) {
            return true;
        }
        map.put(n2, nodeVisitState2);
        for (Object next : graph.successors((Object) n2)) {
            if (canTraverseWithoutReusingEdge(graph, next, n3) && subgraphHasCycle(graph, map, next, n2)) {
                return true;
            }
        }
        map.put(n2, NodeVisitState.COMPLETE);
        return false;
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N next : graph.nodes()) {
                for (N putEdge : reachableNodes(graph, next)) {
                    build.putEdge(next, putEdge);
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N next2 : graph.nodes()) {
                if (!hashSet.contains(next2)) {
                    Set<N> reachableNodes = reachableNodes(graph, next2);
                    hashSet.addAll(reachableNodes);
                    int i3 = 1;
                    for (N next3 : reachableNodes) {
                        int i4 = i3 + 1;
                        for (T putEdge2 : Iterables.limit(reachableNodes, i3)) {
                            build.putEdge(next3, putEdge2);
                        }
                        i3 = i4;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedGraph) {
            return ((TransposedGraph) graph).graph;
        }
        return new TransposedGraph(graph);
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(long j2) {
        Preconditions.checkArgument(j2 >= 0, "Not true that %s is non-negative.", j2);
        return j2;
    }

    @CanIgnoreReturnValue
    public static long checkPositive(long j2) {
        Preconditions.checkArgument(j2 > 0, "Not true that %s is positive.", j2);
        return j2;
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (!valueGraph.isDirected()) {
            return valueGraph;
        }
        if (valueGraph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) valueGraph).graph;
        }
        return new TransposedValueGraph(valueGraph);
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N1, V1> build = ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N addNode : valueGraph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : valueGraph.edges()) {
            Object nodeU = next.nodeU();
            Object nodeV = next.nodeV();
            V edgeValueOrDefault = valueGraph.edgeValueOrDefault(next.nodeU(), next.nodeV(), null);
            java.util.Objects.requireNonNull(edgeValueOrDefault);
            build.putEdgeValue(nodeU, nodeV, edgeValueOrDefault);
        }
        return build;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N1, V1> mutableValueGraph;
        if (iterable instanceof Collection) {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).build();
        }
        for (Object addNode : iterable) {
            mutableValueGraph.addNode(addNode);
        }
        for (N1 next : mutableValueGraph.nodes()) {
            for (Object next2 : valueGraph.successors((Object) next)) {
                if (mutableValueGraph.nodes().contains(next2)) {
                    V edgeValueOrDefault = valueGraph.edgeValueOrDefault(next, next2, null);
                    java.util.Objects.requireNonNull(edgeValueOrDefault);
                    mutableValueGraph.putEdgeValue(next, next2, edgeValueOrDefault);
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N> EndpointPair<N> transpose(EndpointPair<N> endpointPair) {
        return endpointPair.isOrdered() ? EndpointPair.ordered(endpointPair.target(), endpointPair.source()) : endpointPair;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N1, E1> build = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N addNode : network.nodes()) {
            build.addNode(addNode);
        }
        for (E next : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(next);
            build.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), next);
        }
        return build;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N1, E1> mutableNetwork;
        if (iterable instanceof Collection) {
            mutableNetwork = NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableNetwork = NetworkBuilder.from(network).build();
        }
        for (Object addNode : iterable) {
            mutableNetwork.addNode(addNode);
        }
        for (N1 next : mutableNetwork.nodes()) {
            for (E next2 : network.outEdges(next)) {
                N adjacentNode = network.incidentNodes(next2).adjacentNode(next);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(next, adjacentNode, next2);
                }
            }
        }
        return mutableNetwork;
    }
}
