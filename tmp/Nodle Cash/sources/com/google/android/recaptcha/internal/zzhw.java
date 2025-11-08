package com.google.android.recaptcha.internal;

public final class zzhw extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzhw zzb;
    private int zzd;
    private String zze = "";
    private boolean zzf;
    private byte zzg = 2;

    static {
        zzhw zzhw = new zzhw();
        zzb = zzhw;
        zzit.zzD(zzhw.class, zzhw);
    }

    private zzhw() {
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i4 == 2) {
            return new zzkp(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔈ\u0000\u0002ᔇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzhw();
        } else {
            if (i4 == 4) {
                return new zzhv((zzhj) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
