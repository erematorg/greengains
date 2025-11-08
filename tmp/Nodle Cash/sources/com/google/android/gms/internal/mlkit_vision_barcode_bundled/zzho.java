package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzho extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzho zzb;
    private int zzd;
    private int zze = -1;

    static {
        zzho zzho = new zzho();
        zzb = zzho;
        zzeh.zzV(zzho.class, zzho);
    }

    private zzho() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzd", "zze"});
        } else if (i4 == 3) {
            return new zzho();
        } else {
            if (i4 == 4) {
                return new zzhn((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
