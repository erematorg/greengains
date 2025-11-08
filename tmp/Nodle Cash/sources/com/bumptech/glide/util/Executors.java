package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class Executors {
    private static final Executor DIRECT_EXECUTOR = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    };
    private static final Executor MAIN_THREAD_EXECUTOR = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            Util.postOnUiThread(runnable);
        }
    };

    private Executors() {
    }

    public static Executor directExecutor() {
        return DIRECT_EXECUTOR;
    }

    public static Executor mainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    @VisibleForTesting
    public static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdownNow();
        try {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (!executorService.awaitTermination(5, timeUnit)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, timeUnit)) {
                    throw new RuntimeException("Failed to shutdown");
                }
            }
        } catch (InterruptedException e3) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e3);
        }
    }
}
