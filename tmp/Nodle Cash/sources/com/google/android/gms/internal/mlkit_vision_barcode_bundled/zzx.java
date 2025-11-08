package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzx extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzx zzb;
    private int zzd;
    private zzaa zze;

    static {
        zzx zzx = new zzx();
        zzb = zzx;
        zzeh.zzV(zzx.class, zzx);
    }

    private zzx() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0001\u0000\u0001\u000f\u000f\u0001\u0000\u0000\u0000\u000fဉ\u0000", new Object[]{"zzd", "zze"});
        } else if (i4 == 3) {
            return new zzx();
        } else {
            if (i4 == 4) {
                return new zzw((zzv) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
