package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzhq extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzhq zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzhq zzhq = new zzhq();
        zzb = zzhq;
        zzeh.zzV(zzhq.class, zzhq);
    }

    private zzhq() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzhq();
        } else {
            if (i4 == 4) {
                return new zzhp((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
