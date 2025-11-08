package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

class SafeLoggingExecutor implements Executor {
    private final Executor delegate;

    public static class SafeLoggingRunnable implements Runnable {
        private final Runnable delegate;

        public SafeLoggingRunnable(Runnable runnable) {
            this.delegate = runnable;
        }

        public void run() {
            try {
                this.delegate.run();
            } catch (Exception e3) {
                Logging.e("Executor", "Background execution failure.", e3);
            }
        }
    }

    public SafeLoggingExecutor(Executor executor) {
        this.delegate = executor;
    }

    public void execute(Runnable runnable) {
        this.delegate.execute(new SafeLoggingRunnable(runnable));
    }
}
