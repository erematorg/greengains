package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.List;

public final class zzci extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzci zzb;
    private int zzd;
    private int zze;
    private zzeo zzf = zzeh.zzP();
    private zzf zzg;
    private byte zzh = 2;

    static {
        zzci zzci = new zzci();
        zzb = zzci;
        zzeh.zzV(zzci.class, zzci);
    }

    private zzci() {
    }

    public final List zzb() {
        return this.zzf;
    }

    public final int zzc() {
        int zza = zzch.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001᠌\u0000\u0002\u001a\u0003ᐉ\u0001", new Object[]{"zzd", "zze", zzcg.zza, "zzf", "zzg"});
        } else if (i4 == 3) {
            return new zzci();
        } else {
            if (i4 == 4) {
                return new zzcf((zzce) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzh = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
