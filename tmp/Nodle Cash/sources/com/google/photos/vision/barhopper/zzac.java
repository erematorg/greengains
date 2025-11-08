package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzac extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzac zzb;
    private int zzd;
    private int zze;
    private zzdf zzf = zzdf.zzb;

    static {
        zzac zzac = new zzac();
        zzb = zzac;
        zzeh.zzV(zzac.class, zzac);
    }

    private zzac() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ည\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzac();
        } else {
            if (i4 == 4) {
                return new zzab((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
