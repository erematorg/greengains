package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BlockingAnalyticsEventLogger implements AnalyticsEventReceiver, AnalyticsEventLogger {
    static final String APP_EXCEPTION_EVENT_NAME = "_ae";
    private final CrashlyticsOriginAnalyticsEventLogger baseAnalyticsEventLogger;
    private boolean callbackReceived = false;
    private CountDownLatch eventLatch;
    private final Object latchLock = new Object();
    private final TimeUnit timeUnit;
    private final int timeout;

    public BlockingAnalyticsEventLogger(@NonNull CrashlyticsOriginAnalyticsEventLogger crashlyticsOriginAnalyticsEventLogger, int i3, TimeUnit timeUnit2) {
        this.baseAnalyticsEventLogger = crashlyticsOriginAnalyticsEventLogger;
        this.timeout = i3;
        this.timeUnit = timeUnit2;
    }

    public boolean isCallbackReceived() {
        return this.callbackReceived;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().e("Interrupted while awaiting app exception callback from Analytics listener.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logEvent(@androidx.annotation.NonNull java.lang.String r6, @androidx.annotation.Nullable android.os.Bundle r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Logging event "
            java.lang.Object r1 = r5.latchLock
            monitor-enter(r1)
            com.google.firebase.crashlytics.internal.Logger r2 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r3.<init>(r0)     // Catch:{ all -> 0x0052 }
            r3.append(r6)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = " to Firebase Analytics with params "
            r3.append(r0)     // Catch:{ all -> 0x0052 }
            r3.append(r7)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0052 }
            r2.v(r0)     // Catch:{ all -> 0x0052 }
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0052 }
            r2 = 1
            r0.<init>(r2)     // Catch:{ all -> 0x0052 }
            r5.eventLatch = r0     // Catch:{ all -> 0x0052 }
            r0 = 0
            r5.callbackReceived = r0     // Catch:{ all -> 0x0052 }
            com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger r0 = r5.baseAnalyticsEventLogger     // Catch:{ all -> 0x0052 }
            r0.logEvent(r6, r7)     // Catch:{ all -> 0x0052 }
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "Awaiting app exception callback from Analytics..."
            r6.v(r7)     // Catch:{ all -> 0x0052 }
            java.util.concurrent.CountDownLatch r6 = r5.eventLatch     // Catch:{ InterruptedException -> 0x005e }
            int r7 = r5.timeout     // Catch:{ InterruptedException -> 0x005e }
            long r3 = (long) r7     // Catch:{ InterruptedException -> 0x005e }
            java.util.concurrent.TimeUnit r7 = r5.timeUnit     // Catch:{ InterruptedException -> 0x005e }
            boolean r6 = r6.await(r3, r7)     // Catch:{ InterruptedException -> 0x005e }
            if (r6 == 0) goto L_0x0054
            r5.callbackReceived = r2     // Catch:{ InterruptedException -> 0x005e }
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ InterruptedException -> 0x005e }
            java.lang.String r7 = "App exception callback received from Analytics listener."
            r6.v(r7)     // Catch:{ InterruptedException -> 0x005e }
            goto L_0x0067
        L_0x0052:
            r5 = move-exception
            goto L_0x006c
        L_0x0054:
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ InterruptedException -> 0x005e }
            java.lang.String r7 = "Timeout exceeded while awaiting app exception callback from Analytics listener."
            r6.w(r7)     // Catch:{ InterruptedException -> 0x005e }
            goto L_0x0067
        L_0x005e:
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "Interrupted while awaiting app exception callback from Analytics listener."
            r6.e(r7)     // Catch:{ all -> 0x0052 }
        L_0x0067:
            r6 = 0
            r5.eventLatch = r6     // Catch:{ all -> 0x0052 }
            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
            return
        L_0x006c:
            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger.logEvent(java.lang.String, android.os.Bundle):void");
    }

    public void onEvent(@NonNull String str, @NonNull Bundle bundle) {
        CountDownLatch countDownLatch = this.eventLatch;
        if (countDownLatch != null && APP_EXCEPTION_EVENT_NAME.equals(str)) {
            countDownLatch.countDown();
        }
    }
}
