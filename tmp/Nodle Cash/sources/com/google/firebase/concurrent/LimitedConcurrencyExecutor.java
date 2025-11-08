package com.google.firebase.concurrent;

import com.google.firebase.components.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

class LimitedConcurrencyExecutor implements Executor {
    private final Executor delegate;
    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Semaphore semaphore;

    public LimitedConcurrencyExecutor(Executor executor, int i3) {
        Preconditions.checkArgument(i3 > 0, "concurrency must be positive.");
        this.delegate = executor;
        this.semaphore = new Semaphore(i3, true);
    }

    private Runnable decorate(Runnable runnable) {
        return new a(this, runnable, 2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decorate$0(Runnable runnable) {
        try {
            runnable.run();
        } finally {
            this.semaphore.release();
            maybeEnqueueNext();
        }
    }

    private void maybeEnqueueNext() {
        while (this.semaphore.tryAcquire()) {
            Runnable poll = this.queue.poll();
            if (poll != null) {
                this.delegate.execute(decorate(poll));
            } else {
                this.semaphore.release();
                return;
            }
        }
    }

    public void execute(Runnable runnable) {
        this.queue.offer(runnable);
        maybeEnqueueNext();
    }
}
