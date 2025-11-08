package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ThreadLocalBufferManager {
    private final Object RELEASE_LOCK = new Object();
    private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap();

    public static final class ThreadLocalBufferManagerHolder {
        static final ThreadLocalBufferManager manager = new ThreadLocalBufferManager();

        private ThreadLocalBufferManagerHolder() {
        }
    }

    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.manager;
    }

    private void removeSoftRefsClearedByGc() {
        while (true) {
            SoftReference softReference = (SoftReference) this._refQueue.poll();
            if (softReference != null) {
                this._trackedRecyclers.remove(softReference);
            } else {
                return;
            }
        }
    }

    public int releaseBuffers() {
        int i3;
        synchronized (this.RELEASE_LOCK) {
            try {
                removeSoftRefsClearedByGc();
                i3 = 0;
                for (SoftReference<BufferRecycler> clear : this._trackedRecyclers.keySet()) {
                    clear.clear();
                    i3++;
                }
                this._trackedRecyclers.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        return i3;
    }

    public SoftReference<BufferRecycler> wrapAndTrack(BufferRecycler bufferRecycler) {
        SoftReference<BufferRecycler> softReference = new SoftReference<>(bufferRecycler, this._refQueue);
        this._trackedRecyclers.put(softReference, Boolean.TRUE);
        removeSoftRefsClearedByGc();
        return softReference;
    }
}
