package com.google.firebase.crashlytics;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/google/firebase/crashlytics/KeyValueBuilder;", "", "crashlytics", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "(Lcom/google/firebase/crashlytics/FirebaseCrashlytics;)V", "key", "", "", "value", "", "", "", "", "", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyValueBuilder {
    @NotNull
    private final FirebaseCrashlytics crashlytics;

    public KeyValueBuilder(@NotNull FirebaseCrashlytics firebaseCrashlytics) {
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "crashlytics");
        this.crashlytics = firebaseCrashlytics;
    }

    public final void key(@NotNull String str, boolean z2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.crashlytics.setCustomKey(str, z2);
    }

    public final void key(@NotNull String str, double d2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.crashlytics.setCustomKey(str, d2);
    }

    public final void key(@NotNull String str, float f2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.crashlytics.setCustomKey(str, f2);
    }

    public final void key(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.crashlytics.setCustomKey(str, i3);
    }

    public final void key(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.crashlytics.setCustomKey(str, j2);
    }

    public final void key(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(str2, "value");
        this.crashlytics.setCustomKey(str, str2);
    }
}
