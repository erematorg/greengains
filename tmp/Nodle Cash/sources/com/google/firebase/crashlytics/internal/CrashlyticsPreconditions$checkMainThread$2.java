package com.google.firebase.crashlytics.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CrashlyticsPreconditions$checkMainThread$2 extends Lambda implements Function0<String> {
    public static final CrashlyticsPreconditions$checkMainThread$2 INSTANCE = new CrashlyticsPreconditions$checkMainThread$2();

    public CrashlyticsPreconditions$checkMainThread$2() {
        super(0);
    }

    @NotNull
    public final String invoke() {
        return "Must be called on the main thread, was called on " + CrashlyticsPreconditions.INSTANCE.getThreadName() + ClassUtils.PACKAGE_SEPARATOR_CHAR;
    }
}
