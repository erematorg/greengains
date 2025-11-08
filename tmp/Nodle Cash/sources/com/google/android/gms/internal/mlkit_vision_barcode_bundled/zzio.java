package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzio extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzio zzb;
    private int zzd;
    private boolean zze;
    private int zzf;
    private boolean zzg = true;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private String zzl = "";
    private String zzm = "";

    static {
        zzio zzio = new zzio();
        zzb = zzio;
        zzeh.zzV(zzio.class, zzio);
    }

    private zzio() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            zzel zzel = zziq.zza;
            zzel zzel2 = zzim.zza;
            zzel zzel3 = zzip.zza;
            return zzeh.zzS(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006\bဈ\u0007\tဈ\b", new Object[]{"zzd", "zze", "zzf", zzel, "zzg", "zzh", zzel2, "zzi", zzel3, "zzj", zzel3, "zzk", zzel3, "zzl", "zzm"});
        } else if (i4 == 3) {
            return new zzio();
        } else {
            if (i4 == 4) {
                return new zzin((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
