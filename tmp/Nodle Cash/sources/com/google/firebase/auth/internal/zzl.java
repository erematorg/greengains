package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

public final class zzl {
    private static zzl zza = new zzl();

    private zzl() {
    }

    private static SharedPreferences zza(Context context, String str) {
        return context.getSharedPreferences("com.google.firebase.auth.internal.browserSignInSessionStore." + str, 0);
    }

    @Nullable
    public final synchronized String zzb(Context context, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        SharedPreferences zza2 = zza(context, str);
        String str3 = "com.google.firebase.auth.internal.EVENT_ID." + str2 + ".OPERATION";
        String string = zza2.getString(str3, (String) null);
        String str4 = "com.google.firebase.auth.internal.EVENT_ID." + str2 + ".FIREBASE_APP_NAME";
        String string2 = zza2.getString(str4, (String) null);
        SharedPreferences.Editor edit = zza2.edit();
        edit.remove(str3);
        edit.remove(str4);
        edit.apply();
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0092, code lost:
        return null;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.firebase.auth.internal.zzo zza(android.content.Context r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.String r0 = "com.google.firebase.auth.internal.EVENT_ID."
            java.lang.String r1 = "com.google.firebase.auth.internal.EVENT_ID."
            java.lang.String r2 = "com.google.firebase.auth.internal.EVENT_ID."
            java.lang.String r3 = "com.google.firebase.auth.internal.EVENT_ID."
            monitor-enter(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)     // Catch:{ all -> 0x008f }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)     // Catch:{ all -> 0x008f }
            android.content.SharedPreferences r10 = zza(r10, r11)     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r11.<init>(r3)     // Catch:{ all -> 0x008f }
            r11.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = ".SESSION_ID"
            r11.append(r3)     // Catch:{ all -> 0x008f }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r3.<init>(r2)     // Catch:{ all -> 0x008f }
            r3.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = ".OPERATION"
            r3.append(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r3.<init>(r1)     // Catch:{ all -> 0x008f }
            r3.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r1 = ".PROVIDER_ID"
            r3.append(r1)     // Catch:{ all -> 0x008f }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r3.<init>(r0)     // Catch:{ all -> 0x008f }
            r3.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r12 = ".FIREBASE_APP_NAME"
            r3.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r12 = r3.toString()     // Catch:{ all -> 0x008f }
            r0 = 0
            java.lang.String r4 = r10.getString(r11, r0)     // Catch:{ all -> 0x008f }
            java.lang.String r5 = r10.getString(r2, r0)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = r10.getString(r1, r0)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "com.google.firebase.auth.api.gms.config.tenant.id"
            java.lang.String r7 = r10.getString(r3, r0)     // Catch:{ all -> 0x008f }
            java.lang.String r8 = r10.getString(r12, r0)     // Catch:{ all -> 0x008f }
            android.content.SharedPreferences$Editor r10 = r10.edit()     // Catch:{ all -> 0x008f }
            r10.remove(r11)     // Catch:{ all -> 0x008f }
            r10.remove(r2)     // Catch:{ all -> 0x008f }
            r10.remove(r1)     // Catch:{ all -> 0x008f }
            r10.remove(r12)     // Catch:{ all -> 0x008f }
            r10.apply()     // Catch:{ all -> 0x008f }
            if (r4 == 0) goto L_0x0091
            if (r5 == 0) goto L_0x0091
            if (r6 == 0) goto L_0x0091
            com.google.firebase.auth.internal.zzo r10 = new com.google.firebase.auth.internal.zzo     // Catch:{ all -> 0x008f }
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x008f }
            monitor-exit(r9)
            return r10
        L_0x008f:
            r10 = move-exception
            goto L_0x0093
        L_0x0091:
            monitor-exit(r9)
            return r0
        L_0x0093:
            monitor-exit(r9)     // Catch:{ all -> 0x008f }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzl.zza(android.content.Context, java.lang.String, java.lang.String):com.google.firebase.auth.internal.zzo");
    }

    public static zzl zza() {
        return zza;
    }

    private static void zza(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String remove : sharedPreferences.getAll().keySet()) {
            edit.remove(remove);
        }
        edit.apply();
    }

    public final synchronized void zza(Context context, String str, String str2, String str3, String str4, String str5, @Nullable String str6, String str7) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotEmpty(str7);
        SharedPreferences zza2 = zza(context, str);
        zza(zza2);
        SharedPreferences.Editor edit = zza2.edit();
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".SESSION_ID", str3);
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".OPERATION", str4);
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".PROVIDER_ID", str5);
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".FIREBASE_APP_NAME", str7);
        edit.putString("com.google.firebase.auth.api.gms.config.tenant.id", str6);
        edit.apply();
    }

    public final synchronized void zza(Context context, String str, String str2, String str3, String str4) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        SharedPreferences zza2 = zza(context, str);
        zza(zza2);
        SharedPreferences.Editor edit = zza2.edit();
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".OPERATION", str3);
        edit.putString("com.google.firebase.auth.internal.EVENT_ID." + str2 + ".FIREBASE_APP_NAME", str4);
        edit.apply();
    }
}
