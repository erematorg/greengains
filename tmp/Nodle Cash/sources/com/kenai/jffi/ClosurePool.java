package com.kenai.jffi;

import com.kenai.jffi.Closure;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class ClosurePool {
    /* access modifiers changed from: private */
    public static final Closure NULL_CLOSURE = new Closure() {
        public void invoke(Closure.Buffer buffer) {
        }
    };
    private final CallContext callContext;
    private final ConcurrentLinkedQueue<Handle> freeQueue = new ConcurrentLinkedQueue<>();
    private final Set<Magazine> magazines = Collections.synchronizedSet(new HashSet());
    private final ConcurrentLinkedQueue<Handle> partialQueue = new ConcurrentLinkedQueue<>();

    public static final class Handle implements Closure.Handle {
        private volatile boolean disposed;
        final MagazineHolder holder;
        final Magazine.Slot slot;

        public Handle(Magazine.Slot slot2, MagazineHolder magazineHolder) {
            this.slot = slot2;
            this.holder = magazineHolder;
        }

        public synchronized void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.slot.autorelease = true;
                this.slot.proxy.closure = ClosurePool.NULL_CLOSURE;
                MagazineHolder magazineHolder = this.holder;
                magazineHolder.pool.recycle(this.slot, magazineHolder);
            }
        }

        @Deprecated
        public void free() {
            dispose();
        }

        public long getAddress() {
            if (!this.disposed) {
                return this.slot.codeAddress;
            }
            throw new RuntimeException("trying to access disposed closure handle");
        }

        public void setAutoRelease(boolean z2) {
            if (!this.disposed) {
                this.slot.autorelease = z2;
            }
        }
    }

    public static final class Magazine {
        /* access modifiers changed from: private */
        public static final MemoryIO IO = MemoryIO.getInstance();
        private final CallContext ctx;
        private final Foreign foreign;
        private int freeCount;
        private final long magazine;
        private int next;
        private final Slot[] slots;

        public static final class Slot {
            volatile boolean autorelease = true;
            final long codeAddress;
            final long handle;
            final Proxy proxy;

            public Slot(long j2, Proxy proxy2) {
                this.handle = j2;
                this.proxy = proxy2;
                this.codeAddress = Magazine.IO.getAddress(j2);
            }
        }

        public Magazine(CallContext callContext) {
            Foreign instance = Foreign.getInstance();
            this.foreign = instance;
            this.ctx = callContext;
            this.magazine = instance.newClosureMagazine(callContext.getAddress(), Proxy.METHOD, false);
            ArrayList arrayList = new ArrayList();
            while (true) {
                Proxy proxy = new Proxy(callContext);
                long closureMagazineGet = this.foreign.closureMagazineGet(this.magazine, proxy);
                if (closureMagazineGet == 0) {
                    Slot[] slotArr = new Slot[arrayList.size()];
                    this.slots = slotArr;
                    arrayList.toArray(slotArr);
                    this.next = 0;
                    this.freeCount = slotArr.length;
                    return;
                }
                arrayList.add(new Slot(closureMagazineGet, proxy));
            }
        }

        public void finalize() throws Throwable {
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                try {
                    Slot[] slotArr = this.slots;
                    if (i3 >= slotArr.length) {
                        z2 = true;
                        break;
                    } else if (!slotArr[i3].autorelease) {
                        break;
                    } else {
                        i3++;
                    }
                } catch (Throwable th) {
                    super.finalize();
                    throw th;
                }
            }
            long j2 = this.magazine;
            if (j2 != 0 && z2) {
                this.foreign.freeClosureMagazine(j2);
            }
            super.finalize();
        }

        public Slot get() {
            while (this.freeCount > 0) {
                int i3 = this.next;
                Slot[] slotArr = this.slots;
                if (i3 >= slotArr.length) {
                    return null;
                }
                this.next = i3 + 1;
                Slot slot = slotArr[i3];
                if (slot.autorelease) {
                    this.freeCount--;
                    return slot;
                }
            }
            return null;
        }

        public boolean isEmpty() {
            return this.freeCount < 1;
        }

        public boolean isFull() {
            return this.slots.length == this.freeCount;
        }

        public void recycle() {
            int i3 = 0;
            while (true) {
                Slot[] slotArr = this.slots;
                if (i3 < slotArr.length) {
                    Slot slot = slotArr[i3];
                    if (slot.autorelease) {
                        this.freeCount++;
                        slot.proxy.closure = ClosurePool.NULL_CLOSURE;
                    }
                    i3++;
                } else {
                    this.next = 0;
                    return;
                }
            }
        }
    }

    public static final class MagazineHolder {
        final Magazine magazine;
        final ClosurePool pool;

        public MagazineHolder(ClosurePool closurePool, Magazine magazine2) {
            this.pool = closurePool;
            this.magazine = magazine2;
        }

        public void finalize() throws Throwable {
            try {
                this.pool.recycle(this.magazine);
            } finally {
                super.finalize();
            }
        }
    }

    public static final class Proxy {
        static final Method METHOD = getMethod();
        final CallContext callContext;
        volatile Closure closure = ClosurePool.NULL_CLOSURE;

        public Proxy(CallContext callContext2) {
            this.callContext = callContext2;
        }

        private static Method getMethod() {
            Class<Proxy> cls = Proxy.class;
            try {
                Class cls2 = Long.TYPE;
                return cls.getDeclaredMethod("invoke", new Class[]{cls2, cls2});
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }

        public void invoke(long j2, long j3) {
            this.closure.invoke(new DirectClosureBuffer(this.callContext, j2, j3));
        }
    }

    public ClosurePool(CallContext callContext2) {
        this.callContext = callContext2;
    }

    private Handle allocateNewHandle() {
        Handle poll;
        while (true) {
            poll = this.partialQueue.poll();
            if (poll != null || (poll = this.freeQueue.poll()) != null) {
                return poll;
            }
            Magazine magazine = new Magazine(this.callContext);
            useMagazine(magazine);
            this.magazines.add(magazine);
        }
        return poll;
    }

    private void useMagazine(Magazine magazine) {
        MagazineHolder magazineHolder = new MagazineHolder(this, magazine);
        ArrayList arrayList = new ArrayList();
        ConcurrentLinkedQueue<Handle> concurrentLinkedQueue = magazine.isFull() ? this.freeQueue : this.partialQueue;
        while (true) {
            Magazine.Slot slot = magazine.get();
            if (slot != null) {
                arrayList.add(new Handle(slot, magazineHolder));
            } else {
                concurrentLinkedQueue.addAll(arrayList);
                return;
            }
        }
    }

    public Closure.Handle newClosureHandle(Closure closure) {
        Handle poll = this.partialQueue.poll();
        if (poll == null) {
            poll = this.freeQueue.poll();
        }
        if (poll == null) {
            poll = allocateNewHandle();
        }
        poll.slot.proxy.closure = closure;
        return poll;
    }

    public synchronized void recycle(Magazine magazine) {
        try {
            magazine.recycle();
            if (!magazine.isEmpty()) {
                useMagazine(magazine);
            } else {
                this.magazines.remove(magazine);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void recycle(Magazine.Slot slot, MagazineHolder magazineHolder) {
        this.partialQueue.add(new Handle(slot, magazineHolder));
    }
}
