package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzr extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzr zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private String zzl = "";
    private String zzm = "";
    private String zzn = "";
    private String zzo = "";
    private String zzp = "";
    private String zzq = "";
    private String zzr = "";

    static {
        zzr zzr2 = new zzr();
        zzb = zzr2;
        zzeh.zzV(zzr.class, zzr2);
    }

    private zzr() {
    }

    public static zzr zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzk;
    }

    public final String zzd() {
        return this.zzl;
    }

    public final String zze() {
        return this.zzj;
    }

    public final String zzf() {
        return this.zzm;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဈ\n\fဈ\u000b\rဈ\f\u000eဈ\r", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr"});
        } else if (i4 == 3) {
            return new zzr();
        } else {
            if (i4 == 4) {
                return new zzq((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzh() {
        return this.zzq;
    }

    public final String zzi() {
        return this.zze;
    }

    public final String zzj() {
        return this.zzp;
    }

    public final String zzk() {
        return this.zzf;
    }

    public final String zzl() {
        return this.zzi;
    }

    public final String zzm() {
        return this.zzo;
    }

    public final String zzn() {
        return this.zzr;
    }

    public final String zzo() {
        return this.zzh;
    }

    public final String zzp() {
        return this.zzn;
    }

    public final String zzq() {
        return this.zzg;
    }
}
