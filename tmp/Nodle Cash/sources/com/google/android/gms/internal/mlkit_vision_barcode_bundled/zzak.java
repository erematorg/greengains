package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzak extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzak zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";

    static {
        zzak zzak = new zzak();
        zzb = zzak;
        zzeh.zzV(zzak.class, zzak);
    }

    private zzak() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i4 == 3) {
            return new zzak();
        } else {
            if (i4 == 4) {
                return new zzaj((zzai) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
