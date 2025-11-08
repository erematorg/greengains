package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzj extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzj zzb;
    private int zzd;
    private String zze = "";
    private zzeo zzf = zzeh.zzP();

    static {
        zzj zzj = new zzj();
        zzb = zzj;
        zzeh.zzV(zzj.class, zzj);
    }

    private zzj() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzd", "zze", "zzf", zzx.class});
        } else if (i4 == 3) {
            return new zzj();
        } else {
            if (i4 == 4) {
                return new zzi((zza) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
