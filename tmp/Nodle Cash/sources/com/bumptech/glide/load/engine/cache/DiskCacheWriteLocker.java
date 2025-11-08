package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker {
    private final Map<String, WriteLock> locks = new HashMap();
    private final WriteLockPool writeLockPool = new WriteLockPool();

    public static class WriteLock {
        int interestedThreads;
        final Lock lock = new ReentrantLock();
    }

    public static class WriteLockPool {
        private static final int MAX_POOL_SIZE = 10;
        private final Queue<WriteLock> pool = new ArrayDeque();

        public WriteLock obtain() {
            WriteLock poll;
            synchronized (this.pool) {
                poll = this.pool.poll();
            }
            return poll == null ? new WriteLock() : poll;
        }

        public void offer(WriteLock writeLock) {
            synchronized (this.pool) {
                try {
                    if (this.pool.size() < 10) {
                        this.pool.offer(writeLock);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void acquire(String str) {
        WriteLock writeLock;
        synchronized (this) {
            try {
                writeLock = this.locks.get(str);
                if (writeLock == null) {
                    writeLock = this.writeLockPool.obtain();
                    this.locks.put(str, writeLock);
                }
                writeLock.interestedThreads++;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        writeLock.lock.lock();
    }

    public void release(String str) {
        WriteLock writeLock;
        synchronized (this) {
            try {
                writeLock = (WriteLock) Preconditions.checkNotNull(this.locks.get(str));
                int i3 = writeLock.interestedThreads;
                if (i3 >= 1) {
                    int i4 = i3 - 1;
                    writeLock.interestedThreads = i4;
                    if (i4 == 0) {
                        WriteLock remove = this.locks.remove(str);
                        if (remove.equals(writeLock)) {
                            this.writeLockPool.offer(remove);
                        } else {
                            throw new IllegalStateException("Removed the wrong lock, expected to remove: " + writeLock + ", but actually removed: " + remove + ", safeKey: " + str);
                        }
                    }
                } else {
                    throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + writeLock.interestedThreads);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        writeLock.lock.unlock();
    }
}
