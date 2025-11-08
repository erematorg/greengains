package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzck extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzck zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";

    static {
        zzck zzck = new zzck();
        zzb = zzck;
        zzeh.zzV(zzck.class, zzck);
    }

    private zzck() {
    }

    public static zzck zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zze;
    }

    public final String zze() {
        return this.zzj;
    }

    public final String zzf() {
        return this.zzi;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i4 == 3) {
            return new zzck();
        } else {
            if (i4 == 4) {
                return new zzcj((zzce) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzh() {
        return this.zzg;
    }

    public final String zzi() {
        return this.zzf;
    }

    public final String zzj() {
        return this.zzk;
    }
}
