package com.apollographql.apollo3.internal;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/internal/CloseableSingleThreadDispatcher;", "Ljava/io/Closeable;", "Lokio/Closeable;", "()V", "_dispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "closed", "", "coroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getCoroutineDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "close", "", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CloseableSingleThreadDispatcher implements Closeable {
    @NotNull
    private final ExecutorCoroutineDispatcher _dispatcher;
    private boolean closed;

    public CloseableSingleThreadDispatcher() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        this._dispatcher = ExecutorsKt.from(newSingleThreadExecutor);
    }

    public void close() {
        if (!this.closed) {
            this._dispatcher.close();
            this.closed = true;
        }
    }

    @NotNull
    public final CoroutineDispatcher getCoroutineDispatcher() {
        return this._dispatcher;
    }
}
