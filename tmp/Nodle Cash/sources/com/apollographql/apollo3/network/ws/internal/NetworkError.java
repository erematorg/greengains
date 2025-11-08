package com.apollographql.apollo3.network.ws.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/network/ws/internal/NetworkError;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "id", "", "getId", "()Ljava/lang/String;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class NetworkError implements Event {
    @NotNull
    private final Throwable cause;
    @Nullable
    private final String id;

    public NetworkError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "cause");
        this.cause = th;
    }

    @NotNull
    public final Throwable getCause() {
        return this.cause;
    }

    @Nullable
    public String getId() {
        return this.id;
    }
}
