package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzago  reason: invalid package */
public final class zzago extends zzagi {
    private static final String zza = "zzago";
    @Nullable
    private String zzb;
    @Nullable
    private String zzc;
    private int zzd;
    @Nullable
    private String zze;
    private int zzf;
    private long zzg;

    public final /* synthetic */ zzacu zza(String str) throws zzaah {
        return (zzago) zzb(str);
    }

    public final int zzb() {
        return this.zzf;
    }

    public final int zzc() {
        return this.zzd;
    }

    public final long zzd() {
        return this.zzg;
    }

    @Nullable
    public final String zze() {
        return this.zze;
    }

    @Nullable
    public final String zzf() {
        return this.zzc;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006e, code lost:
        throw com.google.android.gms.internal.p002firebaseauthapi.zzahe.zza(r4, zza, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0052, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0054 */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0052 A[ExcHandler: NullPointerException | JSONException (r4v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* renamed from: zzc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.p002firebaseauthapi.zzago zzb(java.lang.String r5) throws com.google.android.gms.internal.p002firebaseauthapi.zzaah {
        /*
            r4 = this;
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r0.<init>(r5)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "totpSessionInfo"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            if (r0 == 0) goto L_0x0067
            java.lang.String r1 = "sharedSecretKey"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza((java.lang.String) r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r4.zzc = r1     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "verificationCodeLength"
            int r1 = r0.optInt(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r4.zzd = r1     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "hashingAlgorithm"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza((java.lang.String) r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r4.zze = r1     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "periodSec"
            int r1 = r0.optInt(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r4.zzf = r1     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "sessionInfo"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza((java.lang.String) r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r4.zzb = r1     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r1 = "finalizeEnrollmentTime"
            java.lang.String r0 = r0.optString(r1)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            com.google.android.gms.internal.firebase-auth-api.zzamd r1 = com.google.android.gms.internal.p002firebaseauthapi.zzane.zza((java.lang.String) r0)     // Catch:{ ParseException -> 0x0054, NullPointerException | JSONException -> 0x0052 }
            long r1 = com.google.android.gms.internal.p002firebaseauthapi.zzane.zza((com.google.android.gms.internal.p002firebaseauthapi.zzamd) r1)     // Catch:{ ParseException -> 0x0054, NullPointerException | JSONException -> 0x0052 }
            r4.zzg = r1     // Catch:{ ParseException -> 0x0054, NullPointerException | JSONException -> 0x0052 }
            goto L_0x0067
        L_0x0052:
            r4 = move-exception
            goto L_0x0068
        L_0x0054:
            java.lang.String r1 = zza     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r3 = "Failed to parse timestamp: "
            r2.<init>(r3)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            r2.append(r0)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            java.lang.String r0 = r2.toString()     // Catch:{ NullPointerException | JSONException -> 0x0052 }
            android.util.Log.e(r1, r0)     // Catch:{ NullPointerException | JSONException -> 0x0052 }
        L_0x0067:
            return r4
        L_0x0068:
            java.lang.String r0 = zza
            com.google.android.gms.internal.firebase-auth-api.zzaah r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahe.zza((java.lang.Exception) r4, (java.lang.String) r0, (java.lang.String) r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzago.zzb(java.lang.String):com.google.android.gms.internal.firebase-auth-api.zzago");
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }
}
