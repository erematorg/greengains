package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzl extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzl zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;

    static {
        zzl zzl = new zzl();
        zzb = zzl;
        zzeh.zzV(zzl.class, zzl);
    }

    private zzl() {
    }

    public static zzl zzi() {
        return zzb;
    }

    public final int zza() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zzh;
    }

    public final int zzc() {
        return this.zzi;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final int zze() {
        return this.zzj;
    }

    public final int zzf() {
        return this.zze;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007ဇ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i4 == 3) {
            return new zzl();
        } else {
            if (i4 == 4) {
                return new zzk((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final boolean zzj() {
        return this.zzk;
    }
}
