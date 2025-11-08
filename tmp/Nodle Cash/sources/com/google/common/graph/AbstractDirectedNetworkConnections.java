package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    final Map<E, N> inEdgeMap;
    final Map<E, N> outEdgeMap;
    /* access modifiers changed from: private */
    public int selfLoopCount;

    public AbstractDirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i3) {
        this.inEdgeMap = (Map) Preconditions.checkNotNull(map);
        this.outEdgeMap = (Map) Preconditions.checkNotNull(map2);
        this.selfLoopCount = Graphs.checkNonNegative(i3);
        Preconditions.checkState(i3 <= map.size() && i3 <= map2.size());
    }

    public void addInEdge(E e3, N n2, boolean z2) {
        Preconditions.checkNotNull(e3);
        Preconditions.checkNotNull(n2);
        boolean z3 = true;
        if (z2) {
            int i3 = this.selfLoopCount + 1;
            this.selfLoopCount = i3;
            Graphs.checkPositive(i3);
        }
        if (this.inEdgeMap.put(e3, n2) != null) {
            z3 = false;
        }
        Preconditions.checkState(z3);
    }

    public void addOutEdge(E e3, N n2) {
        Preconditions.checkNotNull(e3);
        Preconditions.checkNotNull(n2);
        Preconditions.checkState(this.outEdgeMap.put(e3, n2) == null);
    }

    public N adjacentNode(E e3) {
        N n2 = this.outEdgeMap.get(e3);
        Objects.requireNonNull(n2);
        return n2;
    }

    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    public Set<E> incidentEdges() {
        return new AbstractSet<E>() {
            public boolean contains(@CheckForNull Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }

            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            public UnmodifiableIterator<E> iterator() {
                Iterable<T> iterable;
                if (AbstractDirectedNetworkConnections.this.selfLoopCount == 0) {
                    iterable = Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                } else {
                    iterable = Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                }
                return Iterators.unmodifiableIterator(iterable.iterator());
            }
        };
    }

    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    public N removeInEdge(E e3, boolean z2) {
        if (z2) {
            int i3 = this.selfLoopCount - 1;
            this.selfLoopCount = i3;
            Graphs.checkNonNegative(i3);
        }
        N remove = this.inEdgeMap.remove(e3);
        Objects.requireNonNull(remove);
        return remove;
    }

    public N removeOutEdge(E e3) {
        N remove = this.outEdgeMap.remove(e3);
        Objects.requireNonNull(remove);
        return remove;
    }
}
