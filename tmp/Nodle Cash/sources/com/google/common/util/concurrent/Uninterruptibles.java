package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Uninterruptibles {
    private Uninterruptibles() {
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void awaitTerminationUninterruptibly(ExecutorService executorService) {
        Verify.verify(awaitTerminationUninterruptibly(executorService, Long.MAX_VALUE, TimeUnit.NANOSECONDS));
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void awaitUninterruptibly(CountDownLatch countDownLatch) {
        boolean z2 = false;
        while (true) {
            try {
                countDownLatch.await();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v2;
        boolean z2 = false;
        while (true) {
            try {
                v2 = future.get();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return v2;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void joinUninterruptibly(Thread thread) {
        boolean z2 = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static <E> void putUninterruptibly(BlockingQueue<E> blockingQueue, E e3) {
        boolean z2 = false;
        while (true) {
            try {
                blockingQueue.put(e3);
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void sleepUninterruptibly(long j2, TimeUnit timeUnit) {
        long nanos;
        long nanoTime;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            nanoTime = System.nanoTime() + nanos;
            while (true) {
                TimeUnit.NANOSECONDS.sleep(nanos);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static <E> E takeUninterruptibly(BlockingQueue<E> blockingQueue) {
        E take;
        boolean z2 = false;
        while (true) {
            try {
                take = blockingQueue.take();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return take;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, long j2, TimeUnit timeUnit) {
        return tryAcquireUninterruptibly(semaphore, 1, j2, timeUnit);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean tryLockUninterruptibly(Lock lock, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean tryLock;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                tryLock = lock.tryLock(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean awaitTerminationUninterruptibly(ExecutorService executorService, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean awaitTermination;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                awaitTermination = executorService.awaitTermination(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return awaitTermination;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int i3, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean tryAcquire;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                tryAcquire = semaphore.tryAcquire(i3, nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return tryAcquire;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean await;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                await = countDownLatch.await(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return await;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @ParametricNullness
    @J2ktIncompatible
    public static <V> V getUninterruptibly(Future<V> future, long j2, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        long nanos;
        V v2;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                v2 = future.get(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return v2;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void joinUninterruptibly(Thread thread, long j2, TimeUnit timeUnit) {
        long nanos;
        long nanoTime;
        Preconditions.checkNotNull(thread);
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            nanoTime = System.nanoTime() + nanos;
            while (true) {
                TimeUnit.NANOSECONDS.timedJoin(thread, nanos);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean awaitUninterruptibly(Condition condition, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean await;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(j2);
            while (true) {
                await = condition.await(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return await;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }
}
