package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzm extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzm zzb;
    private int zzd;
    private zzeo zze = zzeh.zzP();
    private String zzf = "";

    static {
        zzm zzm = new zzm();
        zzb = zzm;
        zzeh.zzV(zzm.class, zzm);
    }

    private zzm() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzm();
        } else {
            if (i4 == 4) {
                return new zzl((zzk) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
