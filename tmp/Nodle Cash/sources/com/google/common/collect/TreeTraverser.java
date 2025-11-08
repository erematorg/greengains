package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@Deprecated
@Beta
public abstract class TreeTraverser<T> {

    public final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Queue<T> queue;

        public BreadthFirstIterator(T t2) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.queue = arrayDeque;
            arrayDeque.add(t2);
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        public T next() {
            T remove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(remove));
            return remove;
        }

        public T peek() {
            return this.queue.element();
        }
    }

    public final class PostOrderIterator extends AbstractIterator<T> {
        private final ArrayDeque<PostOrderNode<T>> stack;

        public PostOrderIterator(T t2) {
            ArrayDeque<PostOrderNode<T>> arrayDeque = new ArrayDeque<>();
            this.stack = arrayDeque;
            arrayDeque.addLast(expand(t2));
        }

        private PostOrderNode<T> expand(T t2) {
            return new PostOrderNode<>(t2, TreeTraverser.this.children(t2).iterator());
        }

        @CheckForNull
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode last = this.stack.getLast();
                if (last.childIterator.hasNext()) {
                    this.stack.addLast(expand(last.childIterator.next()));
                } else {
                    this.stack.removeLast();
                    return last.root;
                }
            }
            return endOfData();
        }
    }

    public static final class PostOrderNode<T> {
        final Iterator<T> childIterator;
        final T root;

        public PostOrderNode(T t2, Iterator<T> it) {
            this.root = Preconditions.checkNotNull(t2);
            this.childIterator = (Iterator) Preconditions.checkNotNull(it);
        }
    }

    public final class PreOrderIterator extends UnmodifiableIterator<T> {
        private final Deque<Iterator<T>> stack;

        public PreOrderIterator(T t2) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.stack = arrayDeque;
            arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(t2)));
        }

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            Iterator last = this.stack.getLast();
            T checkNotNull = Preconditions.checkNotNull(last.next());
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator it = TreeTraverser.this.children(checkNotNull).iterator();
            if (it.hasNext()) {
                this.stack.addLast(it);
            }
            return checkNotNull;
        }
    }

    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> function) {
        Preconditions.checkNotNull(function);
        return new TreeTraverser<T>() {
            public Iterable<T> children(T t2) {
                return (Iterable) Function.this.apply(t2);
            }
        };
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(t2);
            }
        };
    }

    public abstract Iterable<T> children(T t2);

    public UnmodifiableIterator<T> postOrderIterator(T t2) {
        return new PostOrderIterator(t2);
    }

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.postOrderIterator(t2);
            }
        };
    }

    public UnmodifiableIterator<T> preOrderIterator(T t2) {
        return new PreOrderIterator(t2);
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.preOrderIterator(t2);
            }
        };
    }
}
