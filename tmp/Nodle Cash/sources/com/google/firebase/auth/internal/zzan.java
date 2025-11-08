package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.animation.core.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.p002firebaseauthapi.zzg;
import com.google.firebase.FirebaseApp;

public final class zzan {
    /* access modifiers changed from: private */
    public static Logger zzc = new Logger("TokenRefresher", "FirebaseAuth:");
    @VisibleForTesting
    volatile long zza;
    @VisibleForTesting
    volatile long zzb;
    private final FirebaseApp zzd;
    @VisibleForTesting
    private long zze;
    @VisibleForTesting
    private HandlerThread zzf;
    @VisibleForTesting
    private Handler zzg = new zzg(this.zzf.getLooper());
    @VisibleForTesting
    private Runnable zzh;

    public zzan(FirebaseApp firebaseApp) {
        zzc.v("Initializing TokenRefresher", new Object[0]);
        FirebaseApp firebaseApp2 = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzd = firebaseApp2;
        HandlerThread handlerThread = new HandlerThread("TokenRefresher", 10);
        this.zzf = handlerThread;
        handlerThread.start();
        this.zzh = new zzaq(this, firebaseApp2.getName());
        this.zze = 300000;
    }

    public final void zzb() {
        this.zzg.removeCallbacks(this.zzh);
    }

    public final void zzc() {
        zzc.v(a.s("Scheduling refresh for ", this.zza - this.zze), new Object[0]);
        zzb();
        this.zzb = Math.max((this.zza - DefaultClock.getInstance().currentTimeMillis()) - this.zze, 0) / 1000;
        this.zzg.postDelayed(this.zzh, this.zzb * 1000);
    }

    public final void zzd() {
        int i3 = (int) this.zzb;
        this.zzb = (i3 == 30 || i3 == 60 || i3 == 120 || i3 == 240 || i3 == 480) ? 2 * this.zzb : i3 != 960 ? 30 : 960;
        this.zza = (this.zzb * 1000) + DefaultClock.getInstance().currentTimeMillis();
        zzc.v(a.s("Scheduling refresh for ", this.zza), new Object[0]);
        this.zzg.postDelayed(this.zzh, this.zzb * 1000);
    }
}
