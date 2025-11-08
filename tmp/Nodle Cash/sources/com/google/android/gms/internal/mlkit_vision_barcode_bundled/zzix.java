package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzix extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzix zzb;
    private int zzd;
    private int zze;
    private int zzf = 100;
    private int zzg;

    static {
        zzix zzix = new zzix();
        zzb = zzix;
        zzeh.zzV(zzix.class, zzix);
    }

    private zzix() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzd", "zze", zziv.zza, "zzf", "zzg"});
        } else if (i4 == 3) {
            return new zzix();
        } else {
            if (i4 == 4) {
                return new zziw((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
