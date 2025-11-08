package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzq extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzq zzb;
    private zzeo zzd = zzeh.zzP();
    private zzeo zze = zzeh.zzP();

    static {
        zzq zzq = new zzq();
        zzb = zzq;
        zzeh.zzV(zzq.class, zzq);
    }

    private zzq() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            Class<zzn> cls = zzn.class;
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"zzd", cls, "zze", cls});
        } else if (i4 == 3) {
            return new zzq();
        } else {
            if (i4 == 4) {
                return new zzp((zzo) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
