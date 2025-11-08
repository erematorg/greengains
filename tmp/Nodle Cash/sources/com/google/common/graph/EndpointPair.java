package com.google.common.graph;

import androidx.camera.core.impl.i;
import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable(containerOf = {"N"})
@Beta
public abstract class EndpointPair<N> implements Iterable<N> {
    private final N nodeU;
    private final N nodeV;

    public static final class Ordered<N> extends EndpointPair<N> {
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair endpointPair = (EndpointPair) obj;
            if (isOrdered() != endpointPair.isOrdered()) {
                return false;
            }
            return source().equals(endpointPair.source()) && target().equals(endpointPair.target());
        }

        public int hashCode() {
            return Objects.hashCode(source(), target());
        }

        public boolean isOrdered() {
            return true;
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        public N source() {
            return nodeU();
        }

        public N target() {
            return nodeV();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(source());
            sb.append(" -> ");
            return i.b(sb, target(), ">");
        }

        private Ordered(N n2, N n3) {
            super(n2, n3);
        }
    }

    public static final class Unordered<N> extends EndpointPair<N> {
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair endpointPair = (EndpointPair) obj;
            if (isOrdered() != endpointPair.isOrdered()) {
                return false;
            }
            return nodeU().equals(endpointPair.nodeU()) ? nodeV().equals(endpointPair.nodeV()) : nodeU().equals(endpointPair.nodeV()) && nodeV().equals(endpointPair.nodeU());
        }

        public int hashCode() {
            return nodeV().hashCode() + nodeU().hashCode();
        }

        public boolean isOrdered() {
            return false;
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        public N source() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        public N target() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        public String toString() {
            return "[" + nodeU() + ", " + nodeV() + "]";
        }

        private Unordered(N n2, N n3) {
            super(n2, n3);
        }
    }

    public static <N> EndpointPair<N> of(Graph<?> graph, N n2, N n3) {
        return graph.isDirected() ? ordered(n2, n3) : unordered(n2, n3);
    }

    public static <N> EndpointPair<N> ordered(N n2, N n3) {
        return new Ordered(n2, n3);
    }

    public static <N> EndpointPair<N> unordered(N n2, N n3) {
        return new Unordered(n3, n2);
    }

    public final N adjacentNode(N n2) {
        if (n2.equals(this.nodeU)) {
            return this.nodeV;
        }
        if (n2.equals(this.nodeV)) {
            return this.nodeU;
        }
        throw new IllegalArgumentException("EndpointPair " + this + " does not contain node " + n2);
    }

    public abstract boolean equals(@CheckForNull Object obj);

    public abstract int hashCode();

    public abstract boolean isOrdered();

    public final N nodeU() {
        return this.nodeU;
    }

    public final N nodeV() {
        return this.nodeV;
    }

    public abstract N source();

    public abstract N target();

    private EndpointPair(N n2, N n3) {
        this.nodeU = Preconditions.checkNotNull(n2);
        this.nodeV = Preconditions.checkNotNull(n3);
    }

    public static <N> EndpointPair<N> of(Network<?, ?> network, N n2, N n3) {
        return network.isDirected() ? ordered(n2, n3) : unordered(n2, n3);
    }

    public final UnmodifiableIterator<N> iterator() {
        return Iterators.forArray(this.nodeU, this.nodeV);
    }
}
