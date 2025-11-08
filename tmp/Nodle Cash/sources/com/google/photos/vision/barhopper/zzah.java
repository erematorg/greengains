package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzah extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzah zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private byte zzg = 2;

    static {
        zzah zzah = new zzah();
        zzb = zzah;
        zzeh.zzV(zzah.class, zzah);
    }

    private zzah() {
    }

    public static zzah zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᔈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzah();
        } else {
            if (i4 == 4) {
                return new zzag((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
