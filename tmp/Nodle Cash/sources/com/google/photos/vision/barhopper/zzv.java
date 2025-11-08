package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzv extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzv zzb;
    private int zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzv zzv = new zzv();
        zzb = zzv;
        zzeh.zzV(zzv.class, zzv);
    }

    private zzv() {
    }

    public static zzv zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final String zze() {
        return this.zzg;
    }

    public final int zzf() {
        int zza = zzu.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"zzd", "zze", zzt.zza, "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzv();
        } else {
            if (i4 == 4) {
                return new zzs((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
