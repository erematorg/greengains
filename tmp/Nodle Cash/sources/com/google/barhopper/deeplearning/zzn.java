package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzn extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzn zzb;
    private int zzd = 0;
    private Object zze;

    static {
        zzn zzn = new zzn();
        zzb = zzn;
        zzeh.zzV(zzn.class, zzn);
    }

    private zzn() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"zze", "zzd", zzt.class, zzz.class, zzw.class});
        } else if (i4 == 3) {
            return new zzn();
        } else {
            if (i4 == 4) {
                return new zzm((zzl) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
