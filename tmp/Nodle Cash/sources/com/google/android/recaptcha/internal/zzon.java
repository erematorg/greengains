package com.google.android.recaptcha.internal;

public final class zzon extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzon zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzon zzon = new zzon();
        zzb = zzon;
        zzit.zzD(zzon.class, zzon);
    }

    private zzon() {
    }

    public final String zzg() {
        return this.zze;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzon();
        } else {
            if (i4 == 4) {
                return new zzom((zzoh) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzi() {
        return this.zzf;
    }
}
