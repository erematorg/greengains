package com.google.android.play.integrity.internal;

import android.os.Process;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.i;
import androidx.constraintlayout.core.state.b;
import java.util.IllegalFormatException;
import java.util.Locale;

public final class q {

    /* renamed from: a  reason: collision with root package name */
    private final String f6837a;

    public q(String str) {
        this.f6837a = i.a(Process.myUid(), Process.myPid(), "UID: [", "]  PID: [", "] ").concat(str);
    }

    private static String e(String str, String str2, @Nullable Object... objArr) {
        if (objArr.length > 0) {
            try {
                str2 = String.format(Locale.US, str2, objArr);
            } catch (IllegalFormatException e3) {
                Log.e("PlayCore", "Unable to format ".concat(str2), e3);
                str2 = b.m(str2, " [", TextUtils.join(", ", objArr), "]");
            }
        }
        return a.n(str, " : ", str2);
    }

    public final int a(String str, @Nullable Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            return Log.e("PlayCore", e(this.f6837a, "Phonesky is not installed.", objArr));
        }
        return 0;
    }

    public final int b(Throwable th, String str, @Nullable Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            return Log.e("PlayCore", e(this.f6837a, str, objArr), th);
        }
        return 0;
    }

    public final int c(String str, @Nullable Object... objArr) {
        if (Log.isLoggable("PlayCore", 4)) {
            return Log.i("PlayCore", e(this.f6837a, str, objArr));
        }
        return 0;
    }

    public final int d(String str, @Nullable Object... objArr) {
        if (Log.isLoggable("PlayCore", 5)) {
            return Log.w("PlayCore", e(this.f6837a, "Phonesky package is not signed -- possibly self-built package. Could not verify.", objArr));
        }
        return 0;
    }
}
