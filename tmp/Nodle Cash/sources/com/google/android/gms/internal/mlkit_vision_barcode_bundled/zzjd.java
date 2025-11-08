package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzjd extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzjd zzb;
    private int zzd;
    private zzeo zze = zzeh.zzP();
    private zzjf zzf;
    private zzhm zzg;

    static {
        zzjd zzjd = new zzjd();
        zzb = zzjd;
        zzeh.zzV(zzjd.class, zzjd);
    }

    private zzjd() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zzd", "zze", zzjp.class, "zzf", "zzg"});
        } else if (i4 == 3) {
            return new zzjd();
        } else {
            if (i4 == 4) {
                return new zzjc((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
