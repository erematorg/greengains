package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzc extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzc zzb;
    private int zzd;
    private zzem zze = zzeh.zzM();
    private zzem zzf = zzeh.zzM();
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        zzc zzc = new zzc();
        zzb = zzc;
        zzeh.zzV(zzc.class, zzc);
    }

    private zzc() {
    }

    public static zzb zza() {
        return (zzb) zzb.zzG();
    }

    public static /* synthetic */ void zzc(zzc zzc, int i3) {
        zzc.zzd |= 2;
        zzc.zzh = i3;
    }

    public static /* synthetic */ void zzd(zzc zzc, float f2) {
        zzem zzem = zzc.zze;
        if (!zzem.zzc()) {
            zzc.zze = zzeh.zzN(zzem);
        }
        zzc.zze.zzh(f2);
    }

    public static /* synthetic */ void zze(zzc zzc, float f2) {
        zzem zzem = zzc.zzf;
        if (!zzem.zzc()) {
            zzc.zzf = zzeh.zzN(zzem);
        }
        zzc.zzf.zzh(f2);
    }

    public static /* synthetic */ void zzf(zzc zzc, int i3) {
        zzc.zzd |= 1;
        zzc.zzg = i3;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0013\u0002\u0013\u0003ဋ\u0000\u0004ဋ\u0001\u0005ဋ\u0002\u0006ဋ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i4 == 3) {
            return new zzc();
        } else {
            if (i4 == 4) {
                return new zzb((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
