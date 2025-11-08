package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzii extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzii zzb;
    private int zzd;
    private int zze;
    private zzeo zzf = zzeh.zzP();
    private int zzg = -1;
    private zzia zzh;
    private String zzi = "";
    private int zzj;
    private int zzk;
    private zzen zzl = zzeh.zzO();
    private String zzm = "";
    private int zzn;

    static {
        zzii zzii = new zzii();
        zzb = zzii;
        zzeh.zzV(zzii.class, zzii);
    }

    private zzii() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0002\u0000\u0001᠌\u0000\u0002\u001b\u0003င\u0001\u0004ဉ\u0002\u0005ဈ\u0003\u0006᠌\u0004\u0007᠌\u0005\b'\tဈ\u0006\n᠌\u0007", new Object[]{"zzd", "zze", zzid.zza, "zzf", zzic.class, "zzg", "zzh", "zzi", "zzj", zzif.zza, "zzk", zzig.zza, "zzl", "zzm", "zzn", zzih.zza});
        } else if (i4 == 3) {
            return new zzii();
        } else {
            if (i4 == 4) {
                return new zzie((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
