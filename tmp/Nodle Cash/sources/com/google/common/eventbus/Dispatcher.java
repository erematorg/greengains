package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ElementTypesAreNonnullByDefault
abstract class Dispatcher {

    public static final class ImmediateDispatcher extends Dispatcher {
        /* access modifiers changed from: private */
        public static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        private ImmediateDispatcher() {
        }

        public void dispatch(Object obj, Iterator<Subscriber> it) {
            Preconditions.checkNotNull(obj);
            while (it.hasNext()) {
                it.next().dispatchEvent(obj);
            }
        }
    }

    public static final class LegacyAsyncDispatcher extends Dispatcher {
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        public static final class EventWithSubscriber {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Subscriber subscriber;

            private EventWithSubscriber(Object obj, Subscriber subscriber2) {
                this.event = obj;
                this.subscriber = subscriber2;
            }
        }

        private LegacyAsyncDispatcher() {
            this.queue = Queues.newConcurrentLinkedQueue();
        }

        public void dispatch(Object obj, Iterator<Subscriber> it) {
            Preconditions.checkNotNull(obj);
            while (it.hasNext()) {
                this.queue.add(new EventWithSubscriber(obj, it.next()));
            }
            while (true) {
                EventWithSubscriber poll = this.queue.poll();
                if (poll != null) {
                    poll.subscriber.dispatchEvent(poll.event);
                } else {
                    return;
                }
            }
        }
    }

    public static final class PerThreadQueuedDispatcher extends Dispatcher {
        private final ThreadLocal<Boolean> dispatching;
        private final ThreadLocal<Queue<Event>> queue;

        public static final class Event {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Iterator<Subscriber> subscribers;

            private Event(Object obj, Iterator<Subscriber> it) {
                this.event = obj;
                this.subscribers = it;
            }
        }

        private PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>(this) {
                public Queue<Event> initialValue() {
                    return Queues.newArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>(this) {
                public Boolean initialValue() {
                    return Boolean.FALSE;
                }
            };
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0052 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0034 A[Catch:{ all -> 0x0050 }, LOOP:1: B:6:0x0034->B:8:0x003e, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispatch(java.lang.Object r4, java.util.Iterator<com.google.common.eventbus.Subscriber> r5) {
            /*
                r3 = this;
                com.google.common.base.Preconditions.checkNotNull(r4)
                com.google.common.base.Preconditions.checkNotNull(r5)
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r0 = r3.queue
                java.lang.Object r0 = r0.get()
                java.util.Queue r0 = (java.util.Queue) r0
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r1 = new com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event
                r2 = 0
                r1.<init>(r4, r5)
                r0.offer(r1)
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.dispatching
                java.lang.Object r4 = r4.get()
                java.lang.Boolean r4 = (java.lang.Boolean) r4
                boolean r4 = r4.booleanValue()
                if (r4 != 0) goto L_0x0068
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.dispatching
                java.lang.Boolean r5 = java.lang.Boolean.TRUE
                r4.set(r5)
            L_0x002c:
                java.lang.Object r4 = r0.poll()     // Catch:{ all -> 0x0050 }
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r4 = (com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.Event) r4     // Catch:{ all -> 0x0050 }
                if (r4 == 0) goto L_0x0052
            L_0x0034:
                java.util.Iterator r5 = r4.subscribers     // Catch:{ all -> 0x0050 }
                boolean r5 = r5.hasNext()     // Catch:{ all -> 0x0050 }
                if (r5 == 0) goto L_0x002c
                java.util.Iterator r5 = r4.subscribers     // Catch:{ all -> 0x0050 }
                java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x0050 }
                com.google.common.eventbus.Subscriber r5 = (com.google.common.eventbus.Subscriber) r5     // Catch:{ all -> 0x0050 }
                java.lang.Object r1 = r4.event     // Catch:{ all -> 0x0050 }
                r5.dispatchEvent(r1)     // Catch:{ all -> 0x0050 }
                goto L_0x0034
            L_0x0050:
                r4 = move-exception
                goto L_0x005d
            L_0x0052:
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.dispatching
                r4.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r3 = r3.queue
                r3.remove()
                goto L_0x0068
            L_0x005d:
                java.lang.ThreadLocal<java.lang.Boolean> r5 = r3.dispatching
                r5.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r3 = r3.queue
                r3.remove()
                throw r4
            L_0x0068:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.dispatch(java.lang.Object, java.util.Iterator):void");
        }
    }

    public static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    public static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    public static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    public abstract void dispatch(Object obj, Iterator<Subscriber> it);
}
