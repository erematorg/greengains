package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzn extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzn zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private zzl zzj;
    private zzl zzk;

    static {
        zzn zzn = new zzn();
        zzb = zzn;
        zzeh.zzV(zzn.class, zzn);
    }

    private zzn() {
    }

    public static zzn zzd() {
        return zzb;
    }

    public final zzl zza() {
        zzl zzl = this.zzk;
        return zzl == null ? zzl.zzi() : zzl;
    }

    public final zzl zzb() {
        zzl zzl = this.zzj;
        return zzl == null ? zzl.zzi() : zzl;
    }

    public final String zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဉ\u0005\u0007ဉ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i4 == 3) {
            return new zzn();
        } else {
            if (i4 == 4) {
                return new zzm((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzh() {
        return this.zzh;
    }

    public final String zzi() {
        return this.zzi;
    }

    public final String zzj() {
        return this.zze;
    }
}
