package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzad extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzad zzb;
    private zzeo zzd = zzeh.zzP();

    static {
        zzad zzad = new zzad();
        zzb = zzad;
        zzeh.zzV(zzad.class, zzad);
    }

    private zzad() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzac.class});
        } else if (i4 == 3) {
            return new zzad();
        } else {
            if (i4 == 4) {
                return new zzaa((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
