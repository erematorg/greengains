package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
final class ListenerCallQueue<L> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    public interface Event<L> {
        void call(L l2);
    }

    public static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        @GuardedBy("this")
        boolean isThreadScheduled;
        @GuardedBy("this")
        final Queue<Object> labelQueue = Queues.newArrayDeque();
        final L listener;
        @GuardedBy("this")
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();

        public PerListenerQueue(L l2, Executor executor2) {
            this.listener = Preconditions.checkNotNull(l2);
            this.executor = (Executor) Preconditions.checkNotNull(executor2);
        }

        public synchronized void add(Event<L> event, Object obj) {
            this.waitQueue.add(event);
            this.labelQueue.add(obj);
        }

        public void dispatch() {
            boolean z2;
            synchronized (this) {
                try {
                    if (!this.isThreadScheduled) {
                        z2 = true;
                        this.isThreadScheduled = true;
                    } else {
                        z2 = false;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (z2) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e3) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, e3);
                        throw e3;
                    }
                }
            }
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
            	at java.util.ArrayList.get(ArrayList.java:435)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
            */
        public void run() {
            /*
                r9 = this;
            L_0x0000:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch:{ all -> 0x002b }
                boolean r2 = r9.isThreadScheduled     // Catch:{ all -> 0x001f }
                com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x001f }
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch:{ all -> 0x001f }
                java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x001f }
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch:{ all -> 0x001f }
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch:{ all -> 0x001f }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x001f }
                if (r2 != 0) goto L_0x0024
                r9.isThreadScheduled = r0     // Catch:{ all -> 0x001f }
                monitor-exit(r9)     // Catch:{ all -> 0x001c }
                return
            L_0x001c:
                r1 = move-exception
                r2 = r0
                goto L_0x0053
            L_0x001f:
                r2 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
                goto L_0x0053
            L_0x0024:
                monitor-exit(r9)     // Catch:{ all -> 0x001f }
                L r4 = r9.listener     // Catch:{ RuntimeException -> 0x002d }
                r2.call(r4)     // Catch:{ RuntimeException -> 0x002d }
                goto L_0x0000
            L_0x002b:
                r2 = move-exception
                goto L_0x005c
            L_0x002d:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.logger     // Catch:{ all -> 0x002b }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x002b }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x002b }
                r6.<init>()     // Catch:{ all -> 0x002b }
                java.lang.String r7 = "Exception while executing callback: "
                r6.append(r7)     // Catch:{ all -> 0x002b }
                L r7 = r9.listener     // Catch:{ all -> 0x002b }
                r6.append(r7)     // Catch:{ all -> 0x002b }
                java.lang.String r7 = " "
                r6.append(r7)     // Catch:{ all -> 0x002b }
                r6.append(r3)     // Catch:{ all -> 0x002b }
                java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x002b }
                r4.log(r5, r3, r2)     // Catch:{ all -> 0x002b }
                goto L_0x0000
            L_0x0053:
                monitor-exit(r9)     // Catch:{ all -> 0x005a }
                throw r1     // Catch:{ all -> 0x0055 }
            L_0x0055:
                r1 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
                goto L_0x005c
            L_0x005a:
                r1 = move-exception
                goto L_0x0053
            L_0x005c:
                if (r1 == 0) goto L_0x0066
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch:{ all -> 0x0063 }
                monitor-exit(r9)     // Catch:{ all -> 0x0063 }
                goto L_0x0066
            L_0x0063:
                r0 = move-exception
                monitor-exit(r9)     // Catch:{ all -> 0x0063 }
                throw r0
            L_0x0066:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }

    private void enqueueHelper(Event<L> event, Object obj) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(obj, "label");
        synchronized (this.listeners) {
            try {
                for (PerListenerQueue<L> add : this.listeners) {
                    add.add(event, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addListener(L l2, Executor executor) {
        Preconditions.checkNotNull(l2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue(l2, executor));
    }

    public void dispatch() {
        for (int i3 = 0; i3 < this.listeners.size(); i3++) {
            this.listeners.get(i3).dispatch();
        }
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String str) {
        enqueueHelper(event, str);
    }
}
