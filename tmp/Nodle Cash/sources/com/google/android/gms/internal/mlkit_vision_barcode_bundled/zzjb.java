package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzjb extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzjb zzb;
    private int zzd;
    private String zze = "";
    private zzjh zzf;

    static {
        zzjb zzjb = new zzjb();
        zzb = zzjb;
        zzeh.zzV(zzjb.class, zzjb);
    }

    private zzjb() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzjb();
        } else {
            if (i4 == 4) {
                return new zzja((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
