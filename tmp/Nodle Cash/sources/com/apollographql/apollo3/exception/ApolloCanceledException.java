package com.apollographql.apollo3.exception;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloCanceledException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "This is only used in the JVM runtime and is scheduled for removal")
public final class ApolloCanceledException extends ApolloException {
    public ApolloCanceledException() {
        this((String) null, (Throwable) null, 3, (DefaultConstructorMarker) null);
    }

    public ApolloCanceledException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApolloCanceledException(String str, Throwable th, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : th);
    }
}
