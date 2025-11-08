package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.p002firebaseauthapi.zzags;
import com.google.android.gms.internal.p002firebaseauthapi.zzaq;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class zzbi {
    @VisibleForTesting
    private static long zza = 3600000;
    private static final zzaq<String> zzb = zzaq.zza("firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", "timestamp");
    private static final zzbi zzc = new zzbi();
    private Task<AuthResult> zzd;
    private Task<String> zze;
    private long zzf = 0;

    private zzbi() {
    }

    public static zzbi zzc() {
        return zzc;
    }

    @Nullable
    public final Task<AuthResult> zza() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zzf < zza) {
            return this.zzd;
        }
        return null;
    }

    @Nullable
    public final Task<String> zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zzf < zza) {
            return this.zze;
        }
        return null;
    }

    private static void zza(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        zzaq<String> zzaq = zzb;
        int size = zzaq.size();
        int i3 = 0;
        while (i3 < size) {
            String str = zzaq.get(i3);
            i3++;
            edit.remove(str);
        }
        edit.commit();
    }

    public final void zza(Context context) {
        Preconditions.checkNotNull(context);
        zza(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzd = null;
        this.zzf = 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008b, code lost:
        if (r4.equals("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE") == false) goto L_0x006d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.firebase.auth.FirebaseAuth r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)
            com.google.firebase.FirebaseApp r0 = r12.getApp()
            android.content.Context r0 = r0.getApplicationContext()
            java.lang.String r1 = "com.google.firebase.auth.internal.ProcessDeathHelper"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            java.lang.String r1 = "firebaseAppName"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getString(r1, r3)
            com.google.firebase.FirebaseApp r4 = r12.getApp()
            java.lang.String r4 = r4.getName()
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L_0x0029
            return
        L_0x0029:
            java.lang.String r1 = "verifyAssertionRequest"
            boolean r4 = r0.contains(r1)
            java.lang.String r5 = "operation"
            r6 = 0
            java.lang.String r8 = "timestamp"
            r9 = 0
            if (r4 == 0) goto L_0x00e2
            java.lang.String r1 = r0.getString(r1, r3)
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase-auth-api.zzags> r4 = com.google.android.gms.internal.p002firebaseauthapi.zzags.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromString(r1, r4)
            com.google.android.gms.internal.firebase-auth-api.zzags r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzags) r1
            java.lang.String r4 = r0.getString(r5, r3)
            java.lang.String r5 = "tenantId"
            java.lang.String r5 = r0.getString(r5, r9)
            java.lang.String r10 = "firebaseUserUid"
            java.lang.String r3 = r0.getString(r10, r3)
            long r6 = r0.getLong(r8, r6)
            r11.zzf = r6
            if (r5 == 0) goto L_0x0062
            r12.setTenantId(r5)
            r1.zzb((java.lang.String) r5)
        L_0x0062:
            r4.getClass()
            r5 = -1
            int r6 = r4.hashCode()
            switch(r6) {
                case -98509410: goto L_0x0085;
                case 175006864: goto L_0x007a;
                case 1450464913: goto L_0x006f;
                default: goto L_0x006d;
            }
        L_0x006d:
            r2 = r5
            goto L_0x008e
        L_0x006f:
            java.lang.String r2 = "com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L_0x0078
            goto L_0x006d
        L_0x0078:
            r2 = 2
            goto L_0x008e
        L_0x007a:
            java.lang.String r2 = "com.google.firebase.auth.internal.NONGMSCORE_LINK"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L_0x0083
            goto L_0x006d
        L_0x0083:
            r2 = 1
            goto L_0x008e
        L_0x0085:
            java.lang.String r6 = "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE"
            boolean r4 = r4.equals(r6)
            if (r4 != 0) goto L_0x008e
            goto L_0x006d
        L_0x008e:
            switch(r2) {
                case 0: goto L_0x00bf;
                case 1: goto L_0x009f;
                case 2: goto L_0x0094;
                default: goto L_0x0091;
            }
        L_0x0091:
            r11.zzd = r9
            goto L_0x00de
        L_0x0094:
            com.google.firebase.auth.zzf r1 = com.google.firebase.auth.zzf.zza(r1)
            com.google.android.gms.tasks.Task r12 = r12.signInWithCredential(r1)
            r11.zzd = r12
            goto L_0x00de
        L_0x009f:
            com.google.firebase.auth.FirebaseUser r2 = r12.getCurrentUser()
            java.lang.String r2 = r2.getUid()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00bc
            com.google.firebase.auth.FirebaseUser r2 = r12.getCurrentUser()
            com.google.firebase.auth.zzf r1 = com.google.firebase.auth.zzf.zza(r1)
            com.google.android.gms.tasks.Task r12 = r12.zza((com.google.firebase.auth.FirebaseUser) r2, (com.google.firebase.auth.AuthCredential) r1)
            r11.zzd = r12
            goto L_0x00de
        L_0x00bc:
            r11.zzd = r9
            goto L_0x00de
        L_0x00bf:
            com.google.firebase.auth.FirebaseUser r2 = r12.getCurrentUser()
            java.lang.String r2 = r2.getUid()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00dc
            com.google.firebase.auth.FirebaseUser r2 = r12.getCurrentUser()
            com.google.firebase.auth.zzf r1 = com.google.firebase.auth.zzf.zza(r1)
            com.google.android.gms.tasks.Task r12 = r12.zzc((com.google.firebase.auth.FirebaseUser) r2, (com.google.firebase.auth.AuthCredential) r1)
            r11.zzd = r12
            goto L_0x00de
        L_0x00dc:
            r11.zzd = r9
        L_0x00de:
            zza((android.content.SharedPreferences) r0)
            return
        L_0x00e2:
            java.lang.String r12 = "recaptchaToken"
            boolean r1 = r0.contains(r12)
            if (r1 == 0) goto L_0x0110
            java.lang.String r12 = r0.getString(r12, r3)
            java.lang.String r1 = r0.getString(r5, r3)
            long r2 = r0.getLong(r8, r6)
            r11.zzf = r2
            r1.getClass()
            java.lang.String r2 = "com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0106
            r11.zze = r9
            goto L_0x010c
        L_0x0106:
            com.google.android.gms.tasks.Task r12 = com.google.android.gms.tasks.Tasks.forResult(r12)
            r11.zze = r12
        L_0x010c:
            zza((android.content.SharedPreferences) r0)
            return
        L_0x0110:
            java.lang.String r12 = "statusCode"
            boolean r1 = r0.contains(r12)
            if (r1 == 0) goto L_0x013c
            r1 = 17062(0x42a6, float:2.3909E-41)
            int r12 = r0.getInt(r12, r1)
            java.lang.String r1 = "statusMessage"
            java.lang.String r1 = r0.getString(r1, r3)
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r2.<init>((int) r12, (java.lang.String) r1)
            long r3 = r0.getLong(r8, r6)
            r11.zzf = r3
            zza((android.content.SharedPreferences) r0)
            com.google.firebase.FirebaseException r12 = com.google.android.gms.internal.p002firebaseauthapi.zzach.zza((com.google.android.gms.common.api.Status) r2)
            com.google.android.gms.tasks.Task r12 = com.google.android.gms.tasks.Tasks.forException(r12)
            r11.zzd = r12
        L_0x013c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbi.zza(com.google.firebase.auth.FirebaseAuth):void");
    }

    public static void zza(Context context, Status status) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putInt("statusCode", status.getStatusCode());
        edit.putString("statusMessage", status.getStatusMessage());
        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        edit.commit();
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("firebaseAppName", firebaseAuth.getApp().getName());
        edit.commit();
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        Preconditions.checkNotNull(firebaseUser);
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("firebaseAppName", firebaseAuth.getApp().getName());
        edit.putString("firebaseUserUid", firebaseUser.getUid());
        edit.commit();
    }

    public static void zza(Context context, zzags zzags, String str, @Nullable String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzags));
        edit.putString("operation", str);
        edit.putString("tenantId", str2);
        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        edit.commit();
    }

    public static void zza(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("recaptchaToken", str);
        edit.putString("operation", str2);
        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        edit.commit();
    }
}
