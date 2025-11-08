package com.reown.android.internal.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.koin.core.KoinApplication;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\u00020\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"wcKoinApp", "Lorg/koin/core/KoinApplication;", "getWcKoinApp", "()Lorg/koin/core/KoinApplication;", "setWcKoinApp", "(Lorg/koin/core/KoinApplication;)V", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nKoinApplication.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KoinApplication.kt\ncom/reown/android/internal/common/KoinApplicationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,5:1\n1#2:6\n*E\n"})
public final class KoinApplicationKt {
    @NotNull
    private static KoinApplication wcKoinApp;

    static {
        KoinApplication init = KoinApplication.Companion.init();
        init.createEagerInstances();
        wcKoinApp = init;
    }

    @NotNull
    public static final KoinApplication getWcKoinApp() {
        return wcKoinApp;
    }

    public static final void setWcKoinApp(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "<set-?>");
        wcKoinApp = koinApplication;
    }
}
