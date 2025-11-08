package com.apollographql.apollo3.network.ws.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/network/ws/internal/OperationComplete;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OperationComplete implements Event {
    @Nullable
    private final String id;

    public OperationComplete(@Nullable String str) {
        this.id = str;
    }

    @Nullable
    public String getId() {
        return this.id;
    }
}
