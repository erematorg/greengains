package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzt extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzt zzb;

    static {
        zzt zzt = new zzt();
        zzb = zzt;
        zzeh.zzV(zzt.class, zzt);
    }

    private zzt() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0000", (Object[]) null);
        }
        if (i4 == 3) {
            return new zzt();
        }
        if (i4 == 4) {
            return new zzs((zzr) null);
        }
        if (i4 != 5) {
            return null;
        }
        return zzb;
    }
}
