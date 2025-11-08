package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafz  reason: invalid package */
public class zzafz implements zzacu<zzafz> {
    private static final String zza = "zzafz";
    private String zzb;
    private String zzc;
    private String zzd;
    private zzafq zze;

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0093, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0094, code lost:
        if (r5 == 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        if (r5 == 1) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0098, code lost:
        if (r5 == 2) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009a, code lost:
        if (r5 == 3) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009c, code lost:
        if (r5 == 4) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009f, code lost:
        if (r5 == 5) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a1, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a3, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a4, code lost:
        r0.zzd = r11;
     */
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.p002firebaseauthapi.zzafz zza(java.lang.String r17) throws com.google.android.gms.internal.p002firebaseauthapi.zzaah {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.lang.String r2 = "mfaInfo"
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            r3.<init>(r1)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r4 = "email"
            java.lang.String r4 = r3.optString(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r4 = com.google.android.gms.common.util.Strings.emptyToNull(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            r0.zzb = r4     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r4 = "newEmail"
            java.lang.String r4 = r3.optString(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r4 = com.google.android.gms.common.util.Strings.emptyToNull(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            r0.zzc = r4     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r4 = "reqType"
            int r4 = r3.optInt(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            java.lang.String r5 = "PASSWORD_RESET"
            java.lang.String r6 = "VERIFY_EMAIL"
            java.lang.String r7 = "RECOVER_EMAIL"
            java.lang.String r8 = "EMAIL_SIGNIN"
            java.lang.String r9 = "VERIFY_AND_CHANGE_EMAIL"
            java.lang.String r10 = "REVERT_SECOND_FACTOR_ADDITION"
            r12 = 1
            if (r4 == r12) goto L_0x0047
            switch(r4) {
                case 4: goto L_0x0045;
                case 5: goto L_0x0043;
                case 6: goto L_0x0041;
                case 7: goto L_0x003f;
                case 8: goto L_0x003d;
                default: goto L_0x003b;
            }
        L_0x003b:
            r4 = 0
            goto L_0x0048
        L_0x003d:
            r4 = r10
            goto L_0x0048
        L_0x003f:
            r4 = r9
            goto L_0x0048
        L_0x0041:
            r4 = r8
            goto L_0x0048
        L_0x0043:
            r4 = r7
            goto L_0x0048
        L_0x0045:
            r4 = r6
            goto L_0x0048
        L_0x0047:
            r4 = r5
        L_0x0048:
            r0.zzd = r4     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r4 == 0) goto L_0x00a6
            java.lang.String r4 = "requestType"
            java.lang.String r4 = r3.optString(r4)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            int r13 = r4.hashCode()     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            r15 = 3
            r11 = 2
            r14 = 4
            switch(r13) {
                case -1874510116: goto L_0x008b;
                case -1452371317: goto L_0x0083;
                case -1341836234: goto L_0x007b;
                case -1099157829: goto L_0x0073;
                case 870738373: goto L_0x006b;
                case 970484929: goto L_0x0061;
                default: goto L_0x0060;
            }     // Catch:{ NullPointerException | JSONException -> 0x0069 }
        L_0x0060:
            goto L_0x0093
        L_0x0061:
            boolean r5 = r4.equals(r7)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = r14
            goto L_0x0094
        L_0x0069:
            r0 = move-exception
            goto L_0x00b7
        L_0x006b:
            boolean r5 = r4.equals(r8)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = r11
            goto L_0x0094
        L_0x0073:
            boolean r5 = r4.equals(r9)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = r15
            goto L_0x0094
        L_0x007b:
            boolean r5 = r4.equals(r6)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = 0
            goto L_0x0094
        L_0x0083:
            boolean r5 = r4.equals(r5)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = r12
            goto L_0x0094
        L_0x008b:
            boolean r5 = r4.equals(r10)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r5 == 0) goto L_0x0093
            r5 = 5
            goto L_0x0094
        L_0x0093:
            r5 = -1
        L_0x0094:
            if (r5 == 0) goto L_0x00a3
            if (r5 == r12) goto L_0x00a3
            if (r5 == r11) goto L_0x00a3
            if (r5 == r15) goto L_0x00a3
            if (r5 == r14) goto L_0x00a3
            r6 = 5
            if (r5 == r6) goto L_0x00a3
            r11 = 0
            goto L_0x00a4
        L_0x00a3:
            r11 = r4
        L_0x00a4:
            r0.zzd = r11     // Catch:{ NullPointerException | JSONException -> 0x0069 }
        L_0x00a6:
            boolean r4 = r3.has(r2)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            if (r4 == 0) goto L_0x00b6
            org.json.JSONObject r2 = r3.optJSONObject(r2)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            com.google.android.gms.internal.firebase-auth-api.zzafq r2 = com.google.android.gms.internal.p002firebaseauthapi.zzafq.zza((org.json.JSONObject) r2)     // Catch:{ NullPointerException | JSONException -> 0x0069 }
            r0.zze = r2     // Catch:{ NullPointerException | JSONException -> 0x0069 }
        L_0x00b6:
            return r0
        L_0x00b7:
            java.lang.String r2 = zza
            com.google.android.gms.internal.firebase-auth-api.zzaah r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahe.zza((java.lang.Exception) r0, (java.lang.String) r2, (java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzafz.zza(java.lang.String):com.google.android.gms.internal.firebase-auth-api.zzafz");
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final boolean zze() {
        return this.zzb != null;
    }

    public final boolean zzf() {
        return this.zze != null;
    }

    public final boolean zzg() {
        return this.zzc != null;
    }

    public final boolean zzh() {
        return this.zzd != null;
    }

    @Nullable
    public final zzafq zza() {
        return this.zze;
    }

    public final String zzb() {
        return this.zzb;
    }
}
