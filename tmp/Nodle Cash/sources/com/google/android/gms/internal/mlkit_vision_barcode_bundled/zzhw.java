package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzhw extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzhw zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh = 2;

    static {
        zzhw zzhw = new zzhw();
        zzb = zzhw;
        zzeh.zzV(zzhw.class, zzhw);
    }

    private zzhw() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzd", "zze", zzhv.zza, "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzhw();
        } else {
            if (i4 == 4) {
                return new zzhu((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
