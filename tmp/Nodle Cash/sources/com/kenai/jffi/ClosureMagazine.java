package com.kenai.jffi;

import com.kenai.jffi.Closure;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class ClosureMagazine {
    private static final AtomicIntegerFieldUpdater<ClosureMagazine> UPDATER = AtomicIntegerFieldUpdater.newUpdater(ClosureMagazine.class, "disposed");
    private final CallContext callContext;
    private volatile int disposed;
    private final Foreign foreign;
    private final long magazineAddress;

    public static final class Handle implements Closure.Handle {
        private final long closureAddress;
        private final long codeAddress;
        private final ClosureMagazine magazine;

        public void dispose() {
        }

        public void free() {
        }

        public long getAddress() {
            return this.codeAddress;
        }

        public void setAutoRelease(boolean z2) {
        }

        private Handle(ClosureMagazine closureMagazine, long j2, long j3) {
            this.magazine = closureMagazine;
            this.closureAddress = j2;
            this.codeAddress = j3;
        }
    }

    public ClosureMagazine(Foreign foreign2, CallContext callContext2, long j2) {
        this.foreign = foreign2;
        this.callContext = callContext2;
        this.magazineAddress = j2;
    }

    public Closure.Handle allocate(Object obj) {
        long closureMagazineGet = this.foreign.closureMagazineGet(this.magazineAddress, obj);
        if (closureMagazineGet == 0) {
            return null;
        }
        return new Handle(closureMagazineGet, MemoryIO.getInstance().getAddress(closureMagazineGet));
    }

    public void dispose() {
        int andSet = UPDATER.getAndSet(this, 1);
        long j2 = this.magazineAddress;
        if (j2 != 0 && andSet == 0) {
            this.foreign.freeClosureMagazine(j2);
        }
    }

    public void finalize() throws Throwable {
        Class<ClosureMagazine> cls = ClosureMagazine.class;
        try {
            int andSet = UPDATER.getAndSet(this, 1);
            long j2 = this.magazineAddress;
            if (j2 != 0 && andSet == 0) {
                this.foreign.freeClosureMagazine(j2);
            }
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
        super.finalize();
    }
}
