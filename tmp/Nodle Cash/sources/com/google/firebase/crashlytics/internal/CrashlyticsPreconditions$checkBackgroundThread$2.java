package com.google.firebase.crashlytics.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CrashlyticsPreconditions$checkBackgroundThread$2 extends Lambda implements Function0<String> {
    public static final CrashlyticsPreconditions$checkBackgroundThread$2 INSTANCE = new CrashlyticsPreconditions$checkBackgroundThread$2();

    public CrashlyticsPreconditions$checkBackgroundThread$2() {
        super(0);
    }

    @NotNull
    public final String invoke() {
        return "Must be called on a background thread, was called on " + CrashlyticsPreconditions.INSTANCE.getThreadName() + ClassUtils.PACKAGE_SEPARATOR_CHAR;
    }
}
