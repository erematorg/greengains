package com.google.android.recaptcha.internal;

public final class zznz extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zznz zzb;
    private int zzd;
    private zznx zze;
    private zznx zzf;

    static {
        zznz zznz = new zznz();
        zzb = zznz;
        zzit.zzD(zznz.class, zznz);
    }

    private zznz() {
    }

    public static zznz zzj(byte[] bArr) throws zzje {
        return (zznz) zzit.zzu(zzb, bArr);
    }

    public final zznx zzf() {
        zznx zznx = this.zze;
        return zznx == null ? zznx.zzg() : zznx;
    }

    public final zznx zzg() {
        zznx zznx = this.zzf;
        return zznx == null ? zznx.zzg() : zznx;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zznz();
        } else {
            if (i4 == 4) {
                return new zzny((zznv) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
