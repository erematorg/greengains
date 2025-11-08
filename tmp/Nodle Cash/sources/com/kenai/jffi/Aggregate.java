package com.kenai.jffi;

import com.kenai.jffi.Type;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public abstract class Aggregate extends Type {
    private static final AtomicIntegerFieldUpdater<Aggregate> UPDATER = AtomicIntegerFieldUpdater.newUpdater(Aggregate.class, "disposed");
    private volatile int disposed;
    private final Foreign foreign;
    private final long handle;
    private final Type.TypeInfo typeInfo;

    public Aggregate(Foreign foreign2, long j2) {
        if (j2 != 0) {
            this.foreign = foreign2;
            this.handle = j2;
            this.typeInfo = new Type.TypeInfo(j2, foreign2.getTypeType(j2), foreign2.getTypeSize(j2), foreign2.getTypeAlign(j2));
            return;
        }
        throw new NullPointerException("Invalid ffi_type handle");
    }

    public final synchronized void dispose() {
    }

    public void finalize() throws Throwable {
        try {
            if (UPDATER.getAndSet(this, 1) == 0) {
                this.foreign.freeAggregate(this.typeInfo.handle);
            }
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
        super.finalize();
    }

    public final Type.TypeInfo getTypeInfo() {
        return this.typeInfo;
    }
}
