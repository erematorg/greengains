package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/ConcurrencyInfo;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineScope;)V", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Key", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ConcurrencyInfo implements ExecutionContext.Element {
    @NotNull
    public static final Key Key = new Key((DefaultConstructorMarker) null);
    @NotNull
    private final CoroutineScope coroutineScope;
    @NotNull
    private final CoroutineDispatcher dispatcher;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/ConcurrencyInfo$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/ConcurrencyInfo;", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Key implements ExecutionContext.Key<ConcurrencyInfo> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }

    public ConcurrencyInfo(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull CoroutineScope coroutineScope2) {
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
        this.dispatcher = coroutineDispatcher;
        this.coroutineScope = coroutineScope2;
    }

    public <R> R fold(R r2, @NotNull Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        return ExecutionContext.Element.DefaultImpls.fold(this, r2, function2);
    }

    @Nullable
    public <E extends ExecutionContext.Element> E get(@NotNull ExecutionContext.Key<E> key) {
        return ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    @NotNull
    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    @NotNull
    public final CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
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
