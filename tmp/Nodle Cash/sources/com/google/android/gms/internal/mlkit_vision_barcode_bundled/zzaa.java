package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzaa extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzaa zzb;
    private int zzd;
    private zzad zze;
    private boolean zzf;

    static {
        zzaa zzaa = new zzaa();
        zzb = zzaa;
        zzeh.zzV(zzaa.class, zzaa);
    }

    private zzaa() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzaa();
        } else {
            if (i4 == 4) {
                return new zzz((zzy) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
