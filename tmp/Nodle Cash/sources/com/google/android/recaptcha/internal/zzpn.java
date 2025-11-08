package com.google.android.recaptcha.internal;

import java.util.List;

public final class zzpn extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzpn zzb;
    private String zzd = "";
    private zziy zze = zzit.zzv();
    private zzja zzf = zzit.zzw();

    static {
        zzpn zzpn = new zzpn();
        zzb = zzpn;
        zzit.zzD(zzpn.class, zzpn);
    }

    private zzpn() {
    }

    public static zzpn zzg(byte[] bArr) throws zzje {
        return (zzpn) zzit.zzu(zzb, bArr);
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0002\u0000\u0001Ȉ\u0002'\u0003%", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzpn();
        } else {
            if (i4 == 4) {
                return new zzpm((zzor) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzi() {
        return this.zzd;
    }

    public final List zzj() {
        return this.zzf;
    }
}
