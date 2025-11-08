package com.google.android.gms.internal.measurement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public final class zzct {
    public static final int zza = (Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);

    public static PendingIntent zza(Context context, int i3, Intent intent, int i4) {
        return PendingIntent.getBroadcast(context, 0, intent, i4);
    }
}
