package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/apollographql/apollo3/api/ExecutionContext;", "acc", "element", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class ExecutionContext$plus$1 extends Lambda implements Function2<ExecutionContext, ExecutionContext.Element, ExecutionContext> {
    public static final ExecutionContext$plus$1 INSTANCE = new ExecutionContext$plus$1();

    public ExecutionContext$plus$1() {
        super(2);
    }

    @NotNull
    public final ExecutionContext invoke(@NotNull ExecutionContext executionContext, @NotNull ExecutionContext.Element element) {
        Intrinsics.checkNotNullParameter(executionContext, "acc");
        Intrinsics.checkNotNullParameter(element, "element");
        ExecutionContext minusKey = executionContext.minusKey(element.getKey());
        return minusKey == EmptyExecutionContext.INSTANCE ? element : new CombinedExecutionContext(minusKey, element);
    }
}
