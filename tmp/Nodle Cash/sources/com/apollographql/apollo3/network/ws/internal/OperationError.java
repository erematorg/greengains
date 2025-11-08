package com.apollographql.apollo3.network.ws.internal;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/network/ws/internal/OperationError;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "id", "", "payload", "", "", "(Ljava/lang/String;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getPayload", "()Ljava/util/Map;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OperationError implements Event {
    @Nullable
    private final String id;
    @Nullable
    private final Map<String, Object> payload;

    public OperationError(@Nullable String str, @Nullable Map<String, ? extends Object> map) {
        this.id = str;
        this.payload = map;
    }

    @Nullable
    public String getId() {
        return this.id;
    }

    @Nullable
    public final Map<String, Object> getPayload() {
        return this.payload;
    }
}
