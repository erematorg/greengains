package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzjf extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzjf zzb;
    private int zzd;
    private String zze = "";
    private long zzf;
    private long zzg;
    private long zzh;

    static {
        zzjf zzjf = new zzjf();
        zzb = zzjf;
        zzeh.zzV(zzjf.class, zzjf);
    }

    private zzjf() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzjf();
        } else {
            if (i4 == 4) {
                return new zzje((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
