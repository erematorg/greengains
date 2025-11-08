package com.appsamurai.storyly.util.notification;

import android.content.BroadcastReceiver;
import androidx.annotation.Keep;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\n"}, d2 = {"Lcom/appsamurai/storyly/util/notification/StorylyNotificationReceiver;", "Landroid/content/BroadcastReceiver;", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "", "onReceive", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyNotificationReceiver extends BroadcastReceiver {
    /* JADX WARNING: type inference failed for: r6v2, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(@org.jetbrains.annotations.Nullable android.content.Context r7, @org.jetbrains.annotations.Nullable android.content.Intent r8) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r6 = "notification"
            r0 = 0
            if (r7 != 0) goto L_0x000a
            r1 = r0
            goto L_0x000e
        L_0x000a:
            java.lang.Object r1 = r7.getSystemService(r6)
        L_0x000e:
            boolean r2 = r1 instanceof android.app.NotificationManager
            if (r2 == 0) goto L_0x0015
            android.app.NotificationManager r1 = (android.app.NotificationManager) r1
            goto L_0x0016
        L_0x0015:
            r1 = r0
        L_0x0016:
            if (r1 != 0) goto L_0x0019
            return
        L_0x0019:
            android.content.pm.PackageManager r2 = r7.getPackageManager()
            java.lang.String r3 = r7.getPackageName()
            android.content.Intent r2 = r2.getLaunchIntentForPackage(r3)
            if (r2 != 0) goto L_0x0028
            return
        L_0x0028:
            java.lang.String r3 = r7.getPackageName()
            r2.setPackage(r3)
            java.lang.String r3 = "com.appsamurai.storyly.ACTION_COUNTDOWN_NOTIFICATION"
            r2.setAction(r3)
            android.os.Bundle r3 = r8.getExtras()
            java.lang.String r4 = "storyly-notification-outlink"
            if (r3 != 0) goto L_0x003f
            r3 = r0
            goto L_0x0043
        L_0x003f:
            java.lang.String r3 = r3.getString(r4)
        L_0x0043:
            r2.putExtra(r4, r3)
            int r3 = r2.getFlags()
            r3 = r3 | 32
            r2.setFlags(r3)
            android.os.Parcelable r6 = r8.getParcelableExtra(r6)
            boolean r3 = r6 instanceof android.app.Notification
            if (r3 == 0) goto L_0x005a
            r0 = r6
            android.app.Notification r0 = (android.app.Notification) r0
        L_0x005a:
            if (r0 != 0) goto L_0x005d
            return
        L_0x005d:
            java.lang.String r6 = "storyly-notification-id"
            r3 = 0
            int r4 = r8.getIntExtra(r6, r3)
            r5 = 134217728(0x8000000, float:3.85186E-34)
            int r5 = com.appsamurai.storyly.util.notification.b.a(r5)
            android.app.PendingIntent r7 = android.app.PendingIntent.getActivity(r7, r4, r2, r5)
            r0.contentIntent = r7
            android.app.NotificationChannel r7 = new android.app.NotificationChannel
            java.lang.String r2 = "storyly-notification-channel-name"
            r4 = 4
            java.lang.String r5 = "storyly-notification-channel-id"
            r7.<init>(r5, r2, r4)
            r1.createNotificationChannel(r7)
            int r6 = r8.getIntExtra(r6, r3)
            r1.notify(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.notification.StorylyNotificationReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
