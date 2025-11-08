package com.yttrium;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H ¨\u0006\u0007"}, d2 = {"Lcom/yttrium/YttriumKt;", "", "()V", "initializeTls", "", "context", "Landroid/content/Context;", "android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YttriumKt {
    @NotNull
    public static final YttriumKt INSTANCE = new YttriumKt();

    static {
        System.loadLibrary("uniffi_yttrium");
    }

    private YttriumKt() {
    }

    public final native void initializeTls(@NotNull Context context);
}
