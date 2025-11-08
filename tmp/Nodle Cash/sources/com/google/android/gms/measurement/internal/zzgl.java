package com.google.android.gms.measurement.internal;

import A.a;

final class zzgl implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzgi zzf;

    public zzgl(zzgi zzgi, int i3, String str, Object obj, Object obj2, Object obj3) {
        this.zza = i3;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
        this.zzf = zzgi;
    }

    public final void run() {
        zzgu zzn = this.zzf.zzu.zzn();
        if (!zzn.zzaf()) {
            this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzf.zza == 0) {
            if (this.zzf.zze().zzab()) {
                this.zzf.zza = 'C';
            } else {
                this.zzf.zza = 'c';
            }
        }
        if (this.zzf.zzb < 0) {
            this.zzf.zzb = 102001;
        }
        char charAt = "01VDIWEA?".charAt(this.zza);
        char zza2 = this.zzf.zza;
        long zzb2 = this.zzf.zzb;
        String zza3 = zzgi.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
        StringBuilder sb = new StringBuilder("2");
        sb.append(charAt);
        sb.append(zza2);
        sb.append(zzb2);
        String n2 = a.n(sb, ":", zza3);
        if (n2.length() > 1024) {
            n2 = this.zzb.substring(0, 1024);
        }
        zzgy zzgy = zzn.zzb;
        if (zzgy != null) {
            zzgy.zza(n2, 1);
        }
    }
}
