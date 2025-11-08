package com.google.firebase.crashlytics.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.annotations.NotNull;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class CrashlyticsPreconditions$checkMainThread$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    public CrashlyticsPreconditions$checkMainThread$1(Object obj) {
        super(0, obj, CrashlyticsPreconditions.class, "isMainThread", "isMainThread()Z", 0);
    }

    @NotNull
    public final Boolean invoke() {
        return Boolean.valueOf(((CrashlyticsPreconditions) this.receiver).isMainThread());
    }
}
