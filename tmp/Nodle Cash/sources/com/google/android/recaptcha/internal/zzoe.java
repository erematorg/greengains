package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class zzoe extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzoe zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzja zzk = zzit.zzw();
    private zzgw zzl = zzgw.zzb;
    private String zzm = "";

    static {
        zzoe zzoe = new zzoe();
        zzb = zzoe;
        zzit.zzD(zzoe.class, zzoe);
    }

    private zzoe() {
    }

    public static zzoe zzi(InputStream inputStream) throws IOException {
        return (zzoe) zzit.zzt(zzb, inputStream);
    }

    public final String zzH() {
        return this.zzg;
    }

    public final String zzI() {
        return this.zzi;
    }

    public final String zzJ() {
        return this.zzj;
    }

    public final List zzK() {
        return this.zzk;
    }

    public final zzgw zzf() {
        return this.zzl;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\t\u0000\u0001\u0001\t\t\u0000\u0001\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007%\bည\u0006\tለ\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        } else if (i4 == 3) {
            return new zzoe();
        } else {
            if (i4 == 4) {
                return new zzod((zzoa) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzj() {
        return this.zzf;
    }

    public final String zzk() {
        return this.zzh;
    }
}
