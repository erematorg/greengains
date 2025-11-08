package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzj extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzj zzb;
    private int zzd;
    private int zze = 17;
    private zzeo zzf = zzeh.zzP();
    private int zzg;
    private zzu zzh;
    private zzp zzi;
    private zzjv zzj;
    private int zzk;
    private zzeo zzl = zzeh.zzP();
    private byte zzm = 2;

    static {
        zzj zzj2 = new zzj();
        zzb = zzj2;
        zzeh.zzV(zzj.class, zzj2);
    }

    private zzj() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\b\u0000\u0001\u0001\u000f\b\u0000\u0002\u0004\u0001᠌\u0000\u0003Л\u0004င\u0001\u0005ᐉ\u0002\u0006ᐉ\u0003\u0007င\u0005\b\u001b\u000fᐉ\u0004", new Object[]{"zzd", "zze", zzi.zza, "zzf", zzah.class, "zzg", "zzh", "zzi", "zzk", "zzl", zzak.class, "zzj"});
        } else if (i4 == 3) {
            return new zzj();
        } else {
            if (i4 == 4) {
                return new zzh((zzg) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzm = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
