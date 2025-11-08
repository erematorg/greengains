package com.google.firebase.concurrent;

import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

final class PausableExecutorImpl implements PausableExecutor {
    private final Executor delegate;
    private volatile boolean paused;
    @VisibleForTesting
    final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public PausableExecutorImpl(boolean z2, Executor executor) {
        this.paused = z2;
        this.delegate = executor;
    }

    private void maybeEnqueueNext() {
        if (!this.paused) {
            Runnable poll = this.queue.poll();
            while (poll != null) {
                this.delegate.execute(poll);
                poll = !this.paused ? this.queue.poll() : null;
            }
        }
    }

    public void execute(Runnable runnable) {
        this.queue.offer(runnable);
        maybeEnqueueNext();
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
        maybeEnqueueNext();
    }
}
