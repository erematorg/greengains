package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzac extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzac zzb;
    private int zzd;
    private String zze = "";
    private zzdf zzf;
    private String zzg;
    private zzdf zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    static {
        zzac zzac = new zzac();
        zzb = zzac;
        zzeh.zzV(zzac.class, zzac);
    }

    private zzac() {
        zzdf zzdf = zzdf.zzb;
        this.zzf = zzdf;
        this.zzg = "";
        this.zzh = zzdf;
        this.zzi = 0.25f;
        this.zzj = 0.25f;
        this.zzk = 0.5f;
        this.zzl = 0.85f;
        this.zzm = 1;
    }

    public static zzab zza() {
        return (zzab) zzb.zzG();
    }

    public static /* synthetic */ void zzc(zzac zzac, zzdf zzdf) {
        zzdf.getClass();
        zzac.zzd |= 2;
        zzac.zzf = zzdf;
    }

    public static /* synthetic */ void zzd(zzac zzac, zzdf zzdf) {
        zzdf.getClass();
        zzac.zzd |= 8;
        zzac.zzh = zzdf;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ည\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tင\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        } else if (i4 == 3) {
            return new zzac();
        } else {
            if (i4 == 4) {
                return new zzab((zzaa) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
