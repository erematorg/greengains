package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Optional;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0002H\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"toInput", "Lcom/apollographql/apollo3/api/Optional;", "T", "", "-toInput", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/Optional;", "-toInputOrAbsent", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class InputKt {
    @NotNull
    @Deprecated(message = "toInput() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.Present(this)", imports = {}))
    @JvmName(name = "-toInput")
    /* renamed from: -toInput  reason: not valid java name */
    public static final <T> Optional<T> m8212toInput(@NotNull T t2) {
        Intrinsics.checkNotNullParameter(t2, "<this>");
        return new Optional.Present(t2);
    }

    @NotNull
    @Deprecated(message = "toInput() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.presentIfNotNull(this)", imports = {}))
    @JvmName(name = "-toInputOrAbsent")
    /* renamed from: -toInputOrAbsent  reason: not valid java name */
    public static final <T> Optional<T> m8213toInputOrAbsent(T t2) {
        return Optional.Companion.presentIfNotNull(t2);
    }
}
