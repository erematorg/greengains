package com.google.android.recaptcha.internal;

public final class zzpd extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzpd zzb;
    private int zzd = 0;
    private Object zze;

    static {
        zzpd zzpd = new zzpd();
        zzb = zzpd;
        zzit.zzD(zzpd.class, zzpd);
    }

    private zzpd() {
    }

    public static /* synthetic */ void zzH(zzpd zzpd, zznf zznf) {
        zznf.getClass();
        zzpd.zze = zznf;
        zzpd.zzd = 1;
    }

    public static /* synthetic */ void zzI(zzpd zzpd, zznu zznu) {
        zznu.getClass();
        zzpd.zze = zznu;
        zzpd.zzd = 2;
    }

    public static zzpc zzi() {
        return (zzpc) zzb.zzp();
    }

    public static zzpd zzk(byte[] bArr) throws zzje {
        return (zzpd) zzit.zzu(zzb, bArr);
    }

    public final int zzJ() {
        int i3 = this.zzd;
        if (i3 == 0) {
            return 3;
        }
        int i4 = 1;
        if (i3 != 1) {
            i4 = 2;
            if (i3 != 2) {
                return 0;
            }
        }
        return i4;
    }

    public final zznf zzf() {
        return this.zzd == 1 ? (zznf) this.zze : zznf.zzH();
    }

    public final zznu zzg() {
        return this.zzd == 2 ? (zznu) this.zze : zznu.zzg();
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zze", "zzd", zznf.class, zznu.class});
        } else if (i4 == 3) {
            return new zzpd();
        } else {
            if (i4 == 4) {
                return new zzpc((zzor) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
