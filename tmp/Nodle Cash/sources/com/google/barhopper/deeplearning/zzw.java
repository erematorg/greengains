package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhk;

public final class zzw extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzw zzb;
    private int zzd;
    private zzdf zze = zzdf.zzb;
    private zzhk zzf;
    private int zzg = 1;
    private float zzh = 0.75f;
    private boolean zzi = true;

    static {
        zzw zzw = new zzw();
        zzb = zzw;
        zzeh.zzV(zzw.class, zzw);
    }

    private zzw() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ည\u0000\u0002ဉ\u0001\u0003င\u0002\u0004ခ\u0003\u0005ဇ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i4 == 3) {
            return new zzw();
        } else {
            if (i4 == 4) {
                return new zzv((zzu) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
