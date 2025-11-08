package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzu extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzu zzb;
    private int zzd;
    private long zze;
    private long zzf;
    private zzjv zzg;
    private byte zzh = 2;

    static {
        zzu zzu = new zzu();
        zzb = zzu;
        zzeh.zzV(zzu.class, zzu);
        zzeh.zzI(zzjv.zzf(), zzu, zzu, (zzek) null, 13258261, zzhf.MESSAGE, zzu.class);
    }

    private zzu() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔅ\u0000\u0002ᔅ\u0001\u0003ᐉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i4 == 3) {
            return new zzu();
        } else {
            if (i4 == 4) {
                return new zzt((zzs) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzh = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
