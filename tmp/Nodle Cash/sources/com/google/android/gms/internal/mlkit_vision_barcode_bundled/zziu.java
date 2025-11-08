package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zziu extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zziu zzb;
    private int zzd;
    private int zze = -1;
    private boolean zzf;
    private int zzg;
    private zzdf zzh = zzdf.zzb;
    private String zzi = "";
    private boolean zzj;
    private boolean zzk = true;
    private boolean zzl = true;
    private int zzm;
    private int zzn;
    private boolean zzo;

    static {
        zziu zziu = new zziu();
        zzb = zziu;
        zzeh.zzV(zziu.class, zziu);
    }

    private zziu() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            zzel zzel = zzis.zza;
            zzel zzel2 = zzit.zza;
            return zzeh.zzS(zzb, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ည\u0003\u0005ဈ\u0004\u0006ဇ\u0005\u0007ဇ\u0006\bဇ\u0007\t᠌\b\n᠌\t\u000bဇ\n", new Object[]{"zzd", "zze", "zzf", "zzg", zzel, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", zzel2, "zzn", zzel2, "zzo"});
        } else if (i4 == 3) {
            return new zziu();
        } else {
            if (i4 == 4) {
                return new zzir((zzhi) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
