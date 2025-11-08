package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzia extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzia zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private zzeo zzg = zzeh.zzP();
    private int zzh;

    static {
        zzia zzia = new zzia();
        zzb = zzia;
        zzeh.zzV(zzia.class, zzia);
    }

    private zzia() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002င\u0001\u0003\u001a\u0004င\u0002", new Object[]{"zzd", "zze", zzhz.zza, "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzia();
        } else {
            if (i4 == 4) {
                return new zzhy((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
