package com.android.volley;

import android.os.SystemClock;
import android.support.v4.media.session.a;
import android.util.Log;
import androidx.camera.core.CameraInfo;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VolleyLog {
    private static final String CLASS_NAME = VolleyLog.class.getName();
    public static boolean DEBUG = Log.isLoggable("Volley", 2);
    public static String TAG = "Volley";

    public static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private boolean mFinished = false;
        private final List<Marker> mMarkers = new ArrayList();

        public static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String str, long j2, long j3) {
                this.name = str;
                this.thread = j2;
                this.time = j3;
            }
        }

        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0;
            }
            return ((Marker) a.h(this.mMarkers, 1)).time - this.mMarkers.get(0).time;
        }

        public synchronized void add(String str, long j2) {
            if (!this.mFinished) {
                this.mMarkers.add(new Marker(str, j2, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        public void finalize() throws Throwable {
            if (!this.mFinished) {
                finish("Request on the loose");
                VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public synchronized void finish(String str) {
            this.mFinished = true;
            long totalDuration = getTotalDuration();
            if (totalDuration > 0) {
                long j2 = this.mMarkers.get(0).time;
                VolleyLog.d("(%-4d ms) %s", Long.valueOf(totalDuration), str);
                for (Marker next : this.mMarkers) {
                    long j3 = next.time;
                    VolleyLog.d("(+%-4d) [%2d] %s", Long.valueOf(j3 - j2), Long.valueOf(next.thread), next.name);
                    j2 = j3;
                }
            }
        }
    }

    private static String buildMessage(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i3 = 2;
        while (true) {
            if (i3 >= stackTrace.length) {
                str2 = CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
                break;
            } else if (!stackTrace[i3].getClassName().equals(CLASS_NAME)) {
                String className = stackTrace[i3].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                StringBuilder q2 = A.a.q(substring.substring(substring.lastIndexOf(36) + 1), JwtUtilsKt.JWT_DELIMITER);
                q2.append(stackTrace[i3].getMethodName());
                str2 = q2.toString();
                break;
            } else {
                i3++;
            }
        }
        Locale locale = Locale.US;
        return A.a.n(androidx.work.impl.a.v(Thread.currentThread().getId(), "[", "] ", str2), ": ", str);
    }

    public static void d(String str, Object... objArr) {
        Log.d(TAG, buildMessage(str, objArr));
    }

    public static void e(String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr));
    }

    public static void setTag(String str) {
        d("Changing log tag to %s", str);
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    public static void v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, buildMessage(str, objArr));
        }
    }

    public static void wtf(String str, Object... objArr) {
        Log.wtf(TAG, buildMessage(str, objArr));
    }

    public static void e(Throwable th, String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr), th);
    }

    public static void wtf(Throwable th, String str, Object... objArr) {
        Log.wtf(TAG, buildMessage(str, objArr), th);
    }
}
