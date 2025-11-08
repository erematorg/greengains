package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzz extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzz zzb;

    static {
        zzz zzz = new zzz();
        zzb = zzz;
        zzeh.zzV(zzz.class, zzz);
    }

    private zzz() {
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
            return new zzz();
        }
        if (i4 == 4) {
            return new zzy((zzx) null);
        }
        if (i4 != 5) {
            return null;
        }
        return zzb;
    }
}
