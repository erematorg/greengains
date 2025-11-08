package com.apollographql.apollo3.api;

import A.a;
import com.apollographql.apollo3.exception.ApolloException;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"checkFieldNotMissing", "", "value", "", "name", "", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "Assertions")
public final class Assertions {
    public static final void checkFieldNotMissing(@Nullable Object obj, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (obj == null) {
            throw new ApolloException(a.l("Field ", str, " is missing"), (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
