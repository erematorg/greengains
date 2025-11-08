package com.apollographql.apollo3.exception;

import kotlin.Deprecated;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\u00020\u00018G¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00018G¢\u0006\f\u0012\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloCompositeException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "first", "", "second", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "getFirst$annotations", "()V", "getFirst", "()Lcom/apollographql/apollo3/exception/ApolloException;", "getSecond$annotations", "getSecond", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloCompositeException extends ApolloException {
    public ApolloCompositeException(@Nullable Throwable th, @Nullable Throwable th2) {
        super("multiple exceptions happened", th2);
        if (th != null) {
            ExceptionsKt.addSuppressed(this, th);
        }
        if (th2 != null) {
            ExceptionsKt.addSuppressed(this, th2);
        }
    }

    public static /* synthetic */ void getFirst$annotations() {
    }

    public static /* synthetic */ void getSecond$annotations() {
    }

    @NotNull
    @Deprecated(message = "Use suppressedExceptions instead", replaceWith = @ReplaceWith(expression = "suppressedExceptions.first()", imports = {}))
    public final ApolloException getFirst() {
        Throwable th = (Throwable) CollectionsKt.firstOrNull(ExceptionsKt.getSuppressedExceptions(this));
        ApolloException apolloException = th instanceof ApolloException ? (ApolloException) th : null;
        if (apolloException != null) {
            return apolloException;
        }
        throw new RuntimeException("unexpected first exception", th);
    }

    @NotNull
    @Deprecated(message = "Use suppressedExceptions instead", replaceWith = @ReplaceWith(expression = "suppressedExceptions.getOrNull(1)", imports = {}))
    public final ApolloException getSecond() {
        Throwable th = (Throwable) CollectionsKt.getOrNull(ExceptionsKt.getSuppressedExceptions(this), 1);
        ApolloException apolloException = th instanceof ApolloException ? (ApolloException) th : null;
        if (apolloException != null) {
            return apolloException;
        }
        throw new RuntimeException("unexpected second exception", th);
    }
}
