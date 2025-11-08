package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;

final class zzcg extends zzbs {
    final /* synthetic */ zzci zza;
    private final Object zzb;
    private int zzc;

    public zzcg(zzci zzci, int i3) {
        this.zza = zzci;
        this.zzb = zzci.zzg(zzci, i3);
        this.zzc = i3;
    }

    private final void zza() {
        int i3 = this.zzc;
        if (i3 == -1 || i3 >= this.zza.size() || !zzax.zza(this.zzb, zzci.zzg(this.zza, this.zzc))) {
            this.zzc = this.zza.zzw(this.zzb);
        }
    }

    public final Object getKey() {
        return this.zzb;
    }

    public final Object getValue() {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.get(this.zzb);
        }
        zza();
        int i3 = this.zzc;
        if (i3 == -1) {
            return null;
        }
        return zzci.zzj(this.zza, i3);
    }

    public final Object setValue(Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.put(this.zzb, obj);
        }
        zza();
        int i3 = this.zzc;
        if (i3 == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        zzci zzci = this.zza;
        Object zzj = zzci.zzj(zzci, i3);
        zzci.zzn(zzci, this.zzc, obj);
        return zzj;
    }
}
