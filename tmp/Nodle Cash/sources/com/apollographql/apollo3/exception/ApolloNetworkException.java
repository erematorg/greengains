package com.apollographql.apollo3.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloNetworkException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "message", "", "platformCause", "", "(Ljava/lang/String;Ljava/lang/Object;)V", "getPlatformCause", "()Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloNetworkException extends ApolloException {
    @Nullable
    private final Object platformCause;

    public ApolloNetworkException() {
        this((String) null, (Object) null, 3, (DefaultConstructorMarker) null);
    }

    @Nullable
    public final Object getPlatformCause() {
        return this.platformCause;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApolloNetworkException(String str, Object obj, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApolloNetworkException(@Nullable String str, @Nullable Object obj) {
        super(str, obj instanceof Throwable ? (Throwable) obj : null);
        this.platformCause = obj;
    }
}
