package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.firebase.FirebaseApp;

public final class zzby {
    private volatile int zza;
    /* access modifiers changed from: private */
    public final zzan zzb;
    /* access modifiers changed from: private */
    public volatile boolean zzc;

    public zzby(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), new zzan(firebaseApp));
    }

    @VisibleForTesting
    private zzby(Context context, zzan zzan) {
        this.zzc = false;
        this.zza = 0;
        this.zzb = zzan;
        BackgroundDetector.initialize((Application) context.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzbx(this));
    }

    /* access modifiers changed from: private */
    public final boolean zzb() {
        return this.zza > 0 && !this.zzc;
    }

    public final void zza() {
        this.zzb.zzb();
    }

    public final void zza(int i3) {
        if (i3 > 0 && this.zza == 0) {
            this.zza = i3;
            if (zzb()) {
                this.zzb.zzc();
            }
        } else if (i3 == 0 && this.zza != 0) {
            this.zzb.zzb();
        }
        this.zza = i3;
    }

    public final void zza(zzafm zzafm) {
        if (zzafm != null) {
            long zza2 = zzafm.zza();
            if (zza2 <= 0) {
                zza2 = 3600;
            }
            zzan zzan = this.zzb;
            zzan.zza = (zza2 * 1000) + zzafm.zzb();
            zzan.zzb = -1;
            if (zzb()) {
                this.zzb.zzc();
            }
        }
    }
}
