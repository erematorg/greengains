package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/AutoPersistedQueryInfo;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "hit", "", "(Z)V", "getHit", "()Z", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Key", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class AutoPersistedQueryInfo implements ExecutionContext.Element {
    @NotNull
    public static final Key Key = new Key((DefaultConstructorMarker) null);
    private final boolean hit;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/AutoPersistedQueryInfo$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/AutoPersistedQueryInfo;", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Key implements ExecutionContext.Key<AutoPersistedQueryInfo> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }

    public AutoPersistedQueryInfo(boolean z2) {
        this.hit = z2;
    }

    public <R> R fold(R r2, @NotNull Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        return ExecutionContext.Element.DefaultImpls.fold(this, r2, function2);
    }

    @Nullable
    public <E extends ExecutionContext.Element> E get(@NotNull ExecutionContext.Key<E> key) {
        return ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    public final boolean getHit() {
        return this.hit;
    }

    @NotNull
    public ExecutionContext.Key<?> getKey() {
        return Key;
    }

    @NotNull
    public ExecutionContext minusKey(@NotNull ExecutionContext.Key<?> key) {
        return ExecutionContext.Element.DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public ExecutionContext plus(@NotNull ExecutionContext executionContext) {
        return ExecutionContext.Element.DefaultImpls.plus(this, executionContext);
    }
}
