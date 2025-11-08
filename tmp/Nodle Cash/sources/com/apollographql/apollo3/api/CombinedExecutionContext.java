package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.ExecutionContext;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0002¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/CombinedExecutionContext;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "left", "element", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "(Lcom/apollographql/apollo3/api/ExecutionContext;Lcom/apollographql/apollo3/api/ExecutionContext$Element;)V", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "minusKey", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nExecutionContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExecutionContext.kt\ncom/apollographql/apollo3/api/CombinedExecutionContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,117:1\n1#2:118\n*E\n"})
public final class CombinedExecutionContext implements ExecutionContext {
    @NotNull
    private final ExecutionContext.Element element;
    @NotNull
    private final ExecutionContext left;

    public CombinedExecutionContext(@NotNull ExecutionContext executionContext, @NotNull ExecutionContext.Element element2) {
        Intrinsics.checkNotNullParameter(executionContext, TtmlNode.LEFT);
        Intrinsics.checkNotNullParameter(element2, "element");
        this.left = executionContext;
        this.element = element2;
    }

    public <R> R fold(R r2, @NotNull Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(function2, "operation");
        return function2.invoke(this.left.fold(r2, function2), this.element);
    }

    @Nullable
    public <E extends ExecutionContext.Element> E get(@NotNull ExecutionContext.Key<E> key) {
        Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
        while (true) {
            E e3 = this.element.get(key);
            if (e3 != null) {
                return e3;
            }
            ExecutionContext executionContext = this.left;
            if (!(executionContext instanceof CombinedExecutionContext)) {
                return executionContext.get(key);
            }
            this = (CombinedExecutionContext) executionContext;
        }
    }

    @NotNull
    public ExecutionContext minusKey(@NotNull ExecutionContext.Key<?> key) {
        Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
        if (this.element.get(key) != null) {
            return this.left;
        }
        ExecutionContext minusKey = this.left.minusKey(key);
        return minusKey == this.left ? this : minusKey == EmptyExecutionContext.INSTANCE ? this.element : new CombinedExecutionContext(minusKey, this.element);
    }

    @NotNull
    public ExecutionContext plus(@NotNull ExecutionContext executionContext) {
        return ExecutionContext.DefaultImpls.plus(this, executionContext);
    }
}
