package com.google.android.recaptcha.internal;

import java.util.List;

public final class zznx extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zznx zzb;
    private zzjb zzd = zzit.zzx();

    static {
        zznx zznx = new zznx();
        zzb = zznx;
        zzit.zzD(zznx.class, zznx);
    }

    private zznx() {
    }

    public static zznx zzg() {
        return zzb;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zzd"});
        } else if (i4 == 3) {
            return new zznx();
        } else {
            if (i4 == 4) {
                return new zznw((zznv) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final List zzi() {
        return this.zzd;
    }
}
