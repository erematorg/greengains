package com.sun.jna;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.IdentityHashMap;

public class WeakMemoryHolder {
    IdentityHashMap<Reference<Object>, Memory> backingMap = new IdentityHashMap<>();
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    public synchronized void clean() {
        ReferenceQueue<Object> referenceQueue2 = this.referenceQueue;
        while (true) {
            Reference<? extends Object> poll = referenceQueue2.poll();
            if (poll != null) {
                this.backingMap.remove(poll);
                referenceQueue2 = this.referenceQueue;
            }
        }
    }

    public synchronized void put(Object obj, Memory memory) {
        clean();
        this.backingMap.put(new WeakReference(obj, this.referenceQueue), memory);
    }
}
