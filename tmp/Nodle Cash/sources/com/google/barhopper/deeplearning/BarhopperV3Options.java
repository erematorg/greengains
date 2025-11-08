package com.google.barhopper.deeplearning;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class BarhopperV3Options extends zzeh<BarhopperV3Options, zzk> implements zzfn {
    /* access modifiers changed from: private */
    public static final BarhopperV3Options zzb;
    private int zzd;
    private zzi zze;
    private zzac zzf;
    private zzq zzg;

    static {
        BarhopperV3Options barhopperV3Options = new BarhopperV3Options();
        zzb = barhopperV3Options;
        zzeh.zzV(BarhopperV3Options.class, barhopperV3Options);
    }

    private BarhopperV3Options() {
    }

    public static zzk zza() {
        return (zzk) zzb.zzG();
    }

    public static /* synthetic */ void zzc(BarhopperV3Options barhopperV3Options, zzi zzi) {
        zzi.getClass();
        barhopperV3Options.zze = zzi;
        barhopperV3Options.zzd |= 1;
    }

    public static /* synthetic */ void zzd(BarhopperV3Options barhopperV3Options, zzac zzac) {
        zzac.getClass();
        barhopperV3Options.zzf = zzac;
        barhopperV3Options.zzd |= 2;
    }

    @NonNull
    public final Object zzg(int i3, @NonNull Object obj, @NonNull Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i4 == 3) {
            return new BarhopperV3Options();
        } else {
            if (i4 == 4) {
                return new zzk((zzj) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
