package com.google.android.recaptcha.internal;

public final class zzog extends zzit implements zzkf {
    /* access modifiers changed from: private */
    public static final zzog zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzog zzog = new zzog();
        zzb = zzog;
        zzit.zzD(zzog.class, zzog);
    }

    private zzog() {
    }

    public static /* synthetic */ void zzJ(zzog zzog, String str) {
        str.getClass();
        zzog.zzd |= 1;
        zzog.zze = str;
    }

    public static zzof zzf() {
        return (zzof) zzb.zzp();
    }

    public static zzog zzi(byte[] bArr) throws zzje {
        return (zzog) zzit.zzu(zzb, bArr);
    }

    public final String zzH() {
        return this.zzf;
    }

    public final String zzI() {
        return this.zzg;
    }

    public final Object zzh(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzit.zzA(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i4 == 3) {
            return new zzog();
        } else {
            if (i4 == 4) {
                return new zzof((zzoa) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzj() {
        return this.zzh;
    }

    public final String zzk() {
        return this.zze;
    }
}
