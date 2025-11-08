package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzhk extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzhk zzb;
    private int zzd;
    private int zze;
    private zzjp zzf;
    private zzjb zzg;
    private zzjd zzh;

    static {
        zzhk zzhk = new zzhk();
        zzb = zzhk;
        zzeh.zzV(zzhk.class, zzhk);
    }

    private zzhk() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0005ဉ\u0003", new Object[]{"zzd", "zze", zzij.zza, "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzhk();
        } else {
            if (i4 == 4) {
                return new zzhj((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
