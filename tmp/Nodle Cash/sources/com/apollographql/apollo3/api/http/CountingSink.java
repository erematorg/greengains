package com.apollographql.apollo3.api.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\t\u0010\t\u001a\u00020\nH\u0001J\t\u0010\u000b\u001a\u00020\nH\u0001J\t\u0010\f\u001a\u00020\rH\u0001J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/http/CountingSink;", "Lokio/Sink;", "delegate", "(Lokio/Sink;)V", "<set-?>", "", "bytesWritten", "getBytesWritten", "()J", "close", "", "flush", "timeout", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
final class CountingSink implements Sink {
    private long bytesWritten;
    @NotNull
    private final Sink delegate;

    public CountingSink(@NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "delegate");
        this.delegate = sink;
    }

    public void close() {
        this.delegate.close();
    }

    public void flush() {
        this.delegate.flush();
    }

    public final long getBytesWritten() {
        return this.bytesWritten;
    }

    @NotNull
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public void write(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "source");
        this.delegate.write(buffer, j2);
        this.bytesWritten += j2;
    }
}
