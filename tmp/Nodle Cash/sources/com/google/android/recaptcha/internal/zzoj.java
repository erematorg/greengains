package com.google.android.recaptcha.internal;

public final class zzoj extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzoj zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";

    static {
        zzoj zzoj = new zzoj();
        zzb = zzoj;
        zzit.zzD(zzoj.class, zzoj);
    }

    private zzoj() {
    }

    public static /* synthetic */ void zzH(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 32;
        zzoj.zzj = str;
    }

    public static /* synthetic */ void zzI(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 64;
        zzoj.zzk = str;
    }

    public static /* synthetic */ void zzJ(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 2;
        zzoj.zzf = str;
    }

    public static /* synthetic */ void zzK(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 4;
        zzoj.zzg = str;
    }

    public static zzoi zzf() {
        return (zzoi) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 1;
        zzoj.zze = str;
    }

    public static /* synthetic */ void zzj(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 8;
        zzoj.zzh = str;
    }

    public static /* synthetic */ void zzk(zzoj zzoj, String str) {
        str.getClass();
        zzoj.zzd |= 16;
        zzoj.zzi = str;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007ለ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i4 == 3) {
            return new zzoj();
        } else {
            if (i4 == 4) {
                return new zzoi((zzoh) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
