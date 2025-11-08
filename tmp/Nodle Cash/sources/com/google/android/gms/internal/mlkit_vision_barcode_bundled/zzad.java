package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzad extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzad zzb;
    private int zzd;
    private int zze = 4369;
    private String zzf = "";

    static {
        zzad zzad = new zzad();
        zzb = zzad;
        zzeh.zzV(zzad.class, zzad);
    }

    private zzad() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzq.zza, "zzf"});
        } else if (i4 == 3) {
            return new zzad();
        } else {
            if (i4 == 4) {
                return new zzac((zzab) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
