package app.cash.sqldelight.driver.android;

import android.database.AbstractWindowedCursor;
import android.database.CursorWindow;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@RequiresApi(28)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lapp/cash/sqldelight/driver/android/Api28Impl;", "", "()V", "setWindowSize", "", "Landroid/database/AbstractWindowedCursor;", "windowSizeBytes", "", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class Api28Impl {
    @NotNull
    public static final Api28Impl INSTANCE = new Api28Impl();

    private Api28Impl() {
    }

    @JvmStatic
    @DoNotInline
    public static final void setWindowSize(@NotNull AbstractWindowedCursor abstractWindowedCursor, long j2) {
        Intrinsics.checkNotNullParameter(abstractWindowedCursor, "<this>");
        abstractWindowedCursor.setWindow(new CursorWindow((String) null, j2));
    }
}
