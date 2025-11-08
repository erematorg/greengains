package com.chuckerteam.chucker.api;

import android.content.Context;
import android.content.Intent;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u001a\u0010\b\u001a\u00020\tXD¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\b\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/chuckerteam/chucker/api/Chucker;", "", "()V", "SCREEN_ERROR", "", "getSCREEN_ERROR$annotations", "SCREEN_HTTP", "getSCREEN_HTTP$annotations", "isOp", "", "isOp$annotations", "()Z", "dismissErrorsNotification", "", "context", "Landroid/content/Context;", "dismissTransactionsNotification", "getLaunchIntent", "Landroid/content/Intent;", "screen", "registerDefaultCrashHandler", "collector", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Chucker {
    @NotNull
    public static final Chucker INSTANCE = new Chucker();
    public static final int SCREEN_ERROR = 2;
    public static final int SCREEN_HTTP = 1;
    private static final boolean isOp = false;

    private Chucker() {
    }

    @JvmStatic
    public static final void dismissErrorsNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmStatic
    public static final void dismissTransactionsNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmStatic
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release", replaceWith = @ReplaceWith(expression = "Chucker.getLaunchIntent(context)", imports = {}))
    public static final Intent getLaunchIntent(@NotNull Context context, int i3) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent();
    }

    @Deprecated(message = "This variable will be removed in 4.x release")
    public static /* synthetic */ void getSCREEN_ERROR$annotations() {
    }

    @Deprecated(message = "This variable will be removed in 4.x release")
    public static /* synthetic */ void getSCREEN_HTTP$annotations() {
    }

    public static /* synthetic */ void isOp$annotations() {
    }

    @JvmStatic
    public static final void registerDefaultCrashHandler(@NotNull ChuckerCollector chuckerCollector) {
        Intrinsics.checkNotNullParameter(chuckerCollector, "collector");
    }

    public final boolean isOp() {
        return isOp;
    }

    @JvmStatic
    @NotNull
    public static final Intent getLaunchIntent(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent();
    }
}
