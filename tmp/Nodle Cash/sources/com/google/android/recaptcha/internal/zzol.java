package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class zzol extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzol zzb;
    private int zzd;
    private String zze = "";
    private long zzf;
    private int zzg;
    private zzjb zzh = zzit.zzx();

    static {
        zzol zzol = new zzol();
        zzb = zzol;
        zzit.zzD(zzol.class, zzol);
    }

    private zzol() {
    }

    public static zzol zzg(InputStream inputStream) throws IOException {
        return (zzol) zzit.zzt(zzb, inputStream);
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0001\u0000\u0001ለ\u0000\u0002ဂ\u0001\u0004ဌ\u0002\u0005\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzon.class});
        } else if (i4 == 3) {
            return new zzol();
        } else {
            if (i4 == 4) {
                return new zzok((zzoh) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzi() {
        return this.zze;
    }

    public final List zzj() {
        return this.zzh;
    }
}
