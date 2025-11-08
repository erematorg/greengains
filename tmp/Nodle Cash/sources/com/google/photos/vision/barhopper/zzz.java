package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzz extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzz zzb;
    private int zzd;
    private double zze;
    private double zzf;
    private byte zzg = 2;

    static {
        zzz zzz = new zzz();
        zzb = zzz;
        zzeh.zzV(zzz.class, zzz);
    }

    private zzz() {
    }

    public static zzz zzd() {
        return zzb;
    }

    public final double zza() {
        return this.zze;
    }

    public final double zzb() {
        return this.zzf;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔀ\u0000\u0002ᔀ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzz();
        } else {
            if (i4 == 4) {
                return new zzy((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
