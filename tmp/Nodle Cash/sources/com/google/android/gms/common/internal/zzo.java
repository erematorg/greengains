package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;

public final class zzo {
    private static final Uri zza = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority("com.google.android.gms.chimera").build();
    @Nullable
    private final String zzb;
    @Nullable
    private final String zzc;
    @Nullable
    private final ComponentName zzd;
    private final int zze;
    private final boolean zzf;

    public zzo(ComponentName componentName, int i3) {
        this.zzb = null;
        this.zzc = null;
        Preconditions.checkNotNull(componentName);
        this.zzd = componentName;
        this.zze = 4225;
        this.zzf = false;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzo = (zzo) obj;
        return Objects.equal(this.zzb, zzo.zzb) && Objects.equal(this.zzc, zzo.zzc) && Objects.equal(this.zzd, zzo.zzd) && this.zzf == zzo.zzf;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb, this.zzc, this.zzd, 4225, Boolean.valueOf(this.zzf));
    }

    public final String toString() {
        String str = this.zzb;
        if (str != null) {
            return str;
        }
        Preconditions.checkNotNull(this.zzd);
        return this.zzd.flattenToString();
    }

    @Nullable
    public final ComponentName zza() {
        return this.zzd;
    }

    /* JADX WARNING: type inference failed for: r6v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent zzb(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = "ConnectionStatusConfig"
            java.lang.String r1 = r5.zzb
            if (r1 == 0) goto L_0x0060
            boolean r1 = r5.zzf
            r2 = 0
            if (r1 == 0) goto L_0x0050
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r3 = r5.zzb
            java.lang.String r4 = "serviceActionBundleKey"
            r1.putString(r4, r3)
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x0024 }
            android.net.Uri r3 = zza     // Catch:{ IllegalArgumentException -> 0x0024 }
            java.lang.String r4 = "serviceIntentCall"
            android.os.Bundle r6 = r6.call(r3, r4, r2, r1)     // Catch:{ IllegalArgumentException -> 0x0024 }
            goto L_0x0033
        L_0x0024:
            r6 = move-exception
            java.lang.String r1 = "Dynamic intent resolution failed: "
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = r1.concat(r6)
            android.util.Log.w(r0, r6)
            r6 = r2
        L_0x0033:
            if (r6 != 0) goto L_0x0036
            goto L_0x003f
        L_0x0036:
            java.lang.String r1 = "serviceResponseIntentKey"
            android.os.Parcelable r6 = r6.getParcelable(r1)
            r2 = r6
            android.content.Intent r2 = (android.content.Intent) r2
        L_0x003f:
            if (r2 != 0) goto L_0x0050
            java.lang.String r6 = r5.zzb
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r1 = "Dynamic lookup for intent failed for action: "
            java.lang.String r6 = r1.concat(r6)
            android.util.Log.w(r0, r6)
        L_0x0050:
            if (r2 != 0) goto L_0x006b
            java.lang.String r6 = r5.zzb
            android.content.Intent r0 = new android.content.Intent
            r0.<init>(r6)
            java.lang.String r5 = r5.zzc
            android.content.Intent r5 = r0.setPackage(r5)
            return r5
        L_0x0060:
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.content.ComponentName r5 = r5.zzd
            android.content.Intent r2 = r6.setComponent(r5)
        L_0x006b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzo.zzb(android.content.Context):android.content.Intent");
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    public zzo(String str, int i3, boolean z2) {
        this(str, "com.google.android.gms", 4225, false);
    }

    public zzo(String str, String str2, int i3, boolean z2) {
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        Preconditions.checkNotEmpty(str2);
        this.zzc = str2;
        this.zzd = null;
        this.zze = 4225;
        this.zzf = z2;
    }
}
