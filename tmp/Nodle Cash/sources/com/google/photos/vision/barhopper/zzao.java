package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzao extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzao zzb;
    private int zzd;
    private String zze = "";
    private int zzf;
    private String zzg = "";
    private boolean zzh;
    private byte zzi = 2;

    static {
        zzao zzao = new zzao();
        zzb = zzao;
        zzeh.zzV(zzao.class, zzao);
    }

    private zzao() {
    }

    public static zzao zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zze;
    }

    public final int zze() {
        int zza = zzan.zza(this.zzf);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzi);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ᔈ\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", zzam.zza, "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzao();
        } else {
            if (i4 == 4) {
                return new zzal((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzi = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
