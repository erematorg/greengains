package com.apollographql.apollo3.api;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0002¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "minusKey", "plus", "context", "Companion", "Element", "Key", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ExecutionContext {
    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;
    @NotNull
    @JvmField
    public static final ExecutionContext Empty = EmptyExecutionContext.INSTANCE;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Companion;", "", "()V", "Empty", "Lcom/apollographql/apollo3/api/ExecutionContext;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        @NotNull
        public static ExecutionContext plus(@NotNull ExecutionContext executionContext, @NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "context");
            return executionContext2 == EmptyExecutionContext.INSTANCE ? executionContext : (ExecutionContext) executionContext2.fold(executionContext, ExecutionContext$plus$1.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "minusKey", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Element extends ExecutionContext {

        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        public static final class DefaultImpls {
            public static <R> R fold(@NotNull Element element, R r2, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
                Intrinsics.checkNotNullParameter(function2, "operation");
                return function2.invoke(r2, element);
            }

            @Nullable
            public static <E extends Element> E get(@NotNull Element element, @NotNull Key<E> key) {
                Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
                if (!Intrinsics.areEqual((Object) element.getKey(), (Object) key)) {
                    return null;
                }
                Intrinsics.checkNotNull(element, "null cannot be cast to non-null type E of com.apollographql.apollo3.api.ExecutionContext.Element.get");
                return element;
            }

            @NotNull
            public static ExecutionContext minusKey(@NotNull Element element, @NotNull Key<?> key) {
                Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
                return Intrinsics.areEqual((Object) element.getKey(), (Object) key) ? EmptyExecutionContext.INSTANCE : element;
            }

            @NotNull
            public static ExecutionContext plus(@NotNull Element element, @NotNull ExecutionContext executionContext) {
                Intrinsics.checkNotNullParameter(executionContext, "context");
                return DefaultImpls.plus(element, executionContext);
            }
        }

        <R> R fold(R r2, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

        @Nullable
        <E extends Element> E get(@NotNull Key<E> key);

        @NotNull
        Key<?> getKey();

        @NotNull
        ExecutionContext minusKey(@NotNull Key<?> key);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "E", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Key<E extends Element> {
    }

    <R> R fold(R r2, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

    @Nullable
    <E extends Element> E get(@NotNull Key<E> key);

    @NotNull
    ExecutionContext minusKey(@NotNull Key<?> key);

    @NotNull
    ExecutionContext plus(@NotNull ExecutionContext executionContext);
}
