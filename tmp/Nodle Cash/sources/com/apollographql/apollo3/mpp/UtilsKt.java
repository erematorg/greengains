package com.apollographql.apollo3.mpp;

import java.text.SimpleDateFormat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0006\u0010\b\u001a\u00020\t\u001a\u0006\u0010\n\u001a\u00020\t\u001a\u0006\u0010\u000b\u001a\u00020\t\u001a\u0006\u0010\f\u001a\u00020\r\u001a\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010\u001a\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010\u001a\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010\u001a\u0006\u0010\u0014\u001a\u00020\u0015\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0016"}, d2 = {"simpleDateFormat", "Ljava/text/SimpleDateFormat;", "getSimpleDateFormat", "()Ljava/text/SimpleDateFormat;", "simpleDateFormat$delegate", "Lkotlin/Lazy;", "assertMainThreadOnNative", "", "currentThreadId", "", "currentThreadName", "currentTimeFormatted", "currentTimeMillis", "", "ensureNeverFrozen", "obj", "", "freeze", "isFrozen", "", "platform", "Lcom/apollographql/apollo3/mpp/Platform;", "apollo-mpp-utils"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UtilsKt {
    @NotNull
    private static final Lazy simpleDateFormat$delegate = LazyKt.lazy(UtilsKt$simpleDateFormat$2.INSTANCE);

    public static final void assertMainThreadOnNative() {
    }

    @NotNull
    public static final String currentThreadId() {
        return String.valueOf(Thread.currentThread().getId());
    }

    @NotNull
    public static final String currentThreadName() {
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        return name;
    }

    @NotNull
    public static final String currentTimeFormatted() {
        String format = getSimpleDateFormat().format(Long.valueOf(currentTimeMillis()));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(currentTimeMillis())");
        return format;
    }

    public static final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static final void ensureNeverFrozen(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
    }

    public static final void freeze(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
    }

    private static final SimpleDateFormat getSimpleDateFormat() {
        return (SimpleDateFormat) simpleDateFormat$delegate.getValue();
    }

    public static final boolean isFrozen(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        return false;
    }

    @NotNull
    public static final Platform platform() {
        return Platform.Jvm;
    }
}
