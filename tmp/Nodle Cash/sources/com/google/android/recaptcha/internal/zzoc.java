package com.google.android.recaptcha.internal;

public final class zzoc extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzoc zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private String zzl = "";

    static {
        zzoc zzoc = new zzoc();
        zzb = zzoc;
        zzit.zzD(zzoc.class, zzoc);
    }

    private zzoc() {
    }

    public static /* synthetic */ void zzL(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 8;
        zzoc.zzh = str;
    }

    public static /* synthetic */ void zzM(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 16;
        zzoc.zzi = str;
    }

    public static /* synthetic */ void zzN(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 32;
        zzoc.zzj = str;
    }

    public static /* synthetic */ void zzO(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 64;
        zzoc.zzk = str;
    }

    public static /* synthetic */ void zzP(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 128;
        zzoc.zzl = str;
    }

    public static /* synthetic */ void zzQ(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 2;
        zzoc.zzf = str;
    }

    public static /* synthetic */ void zzR(zzoc zzoc, String str) {
        str.getClass();
        zzoc.zzd |= 4;
        zzoc.zzg = str;
    }

    public static zzob zzf() {
        return (zzob) zzb.zzp();
    }

    public final String zzH() {
        return this.zzf;
    }

    public final String zzI() {
        return this.zzh;
    }

    public final String zzJ() {
        return this.zzk;
    }

    public final String zzK() {
        return this.zzj;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007ለ\u0006\bለ\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (i4 == 3) {
            return new zzoc();
        } else {
            if (i4 == 4) {
                return new zzob((zzoa) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzi() {
        return this.zzi;
    }

    public final String zzj() {
        return this.zzl;
    }

    public final String zzk() {
        return this.zzg;
    }
}
