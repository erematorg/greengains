package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.a;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.concurrent.Executor;

final class ProxyNotificationPreferences {
    private static final String FCM_PREFERENCES = "com.google.firebase.messaging";

    private ProxyNotificationPreferences() {
    }

    private static SharedPreferences getPreference(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        return context.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    @WorkerThread
    public static boolean isProxyNotificationInitialized(Context context) {
        return getPreference(context).getBoolean("proxy_notification_initialized", false);
    }

    @WorkerThread
    public static boolean isProxyNotificationRetentionSet(SharedPreferences sharedPreferences, boolean z2) {
        return sharedPreferences.contains("proxy_retention") && sharedPreferences.getBoolean("proxy_retention", false) == z2;
    }

    @WorkerThread
    public static void setProxyNotificationsInitialized(Context context, boolean z2) {
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putBoolean("proxy_notification_initialized", z2);
        edit.apply();
    }

    @WorkerThread
    public static void setProxyRetention(Context context, GmsRpc gmsRpc, boolean z2) {
        if (PlatformVersion.isAtLeastQ() && !isProxyNotificationRetentionSet(getPreference(context), z2)) {
            gmsRpc.setRetainProxiedNotifications(z2).addOnSuccessListener((Executor) new a(2), new m(context, z2));
        }
    }

    @WorkerThread
    public static void setProxyRetentionPreferences(Context context, boolean z2) {
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putBoolean("proxy_retention", z2);
        edit.apply();
    }
}
