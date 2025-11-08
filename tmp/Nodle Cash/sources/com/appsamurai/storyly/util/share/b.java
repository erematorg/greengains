package com.appsamurai.storyly.util.share;

import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class b {
    public static /* synthetic */ Intent a(String str, String str2, int i3) {
        return a(str, (i3 & 2) != 0 ? "text/plain" : null);
    }

    @NotNull
    public static final Intent a(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "applicationPackage");
        Intrinsics.checkNotNullParameter(str2, "mimeType");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType(str2);
        intent.setPackage(str);
        return intent;
    }
}
