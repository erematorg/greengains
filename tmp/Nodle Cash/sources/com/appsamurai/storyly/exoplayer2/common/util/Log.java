package com.appsamurai.storyly.exoplayer2.common.util;

import A.a;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.UnknownHostException;
import org.checkerframework.dataflow.qual.Pure;

public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }

    private Log() {
    }

    @Pure
    private static String appendThrowableString(String str, @Nullable Throwable th) {
        String throwableString = getThrowableString(th);
        if (TextUtils.isEmpty(throwableString)) {
            return str;
        }
        StringBuilder q2 = a.q(str, "\n  ");
        q2.append(throwableString.replace("\n", "\n  "));
        q2.append(10);
        return q2.toString();
    }

    @Pure
    public static void d(@Size(max = 23) String str, String str2) {
        if (logLevel == 0) {
            android.util.Log.d(str, str2);
        }
    }

    @Pure
    public static void e(@Size(max = 23) String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    @Pure
    public static int getLogLevel() {
        return logLevel;
    }

    @Nullable
    @Pure
    public static String getThrowableString(@Nullable Throwable th) {
        if (th == null) {
            return null;
        }
        return isCausedByUnknownHostException(th) ? "UnknownHostException (no network)" : !logStackTraces ? th.getMessage() : android.util.Log.getStackTraceString(th).trim().replace("\t", "    ");
    }

    @Pure
    public static void i(@Size(max = 23) String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    @Pure
    private static boolean isCausedByUnknownHostException(@Nullable Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static void setLogLevel(int i3) {
        logLevel = i3;
    }

    public static void setLogStackTraces(boolean z2) {
        logStackTraces = z2;
    }

    @Pure
    public static void w(@Size(max = 23) String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    @Pure
    public static void d(@Size(max = 23) String str, String str2, @Nullable Throwable th) {
        d(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void e(@Size(max = 23) String str, String str2, @Nullable Throwable th) {
        e(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void i(@Size(max = 23) String str, String str2, @Nullable Throwable th) {
        i(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void w(@Size(max = 23) String str, String str2, @Nullable Throwable th) {
        w(str, appendThrowableString(str2, th));
    }
}
