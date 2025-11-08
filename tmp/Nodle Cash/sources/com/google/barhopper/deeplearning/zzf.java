package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzf extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzf zzb;
    private zzeo zzd = zzeh.zzP();

    static {
        zzf zzf = new zzf();
        zzb = zzf;
        zzeh.zzV(zzf.class, zzf);
    }

    private zzf() {
    }

    public static zze zza() {
        return (zze) zzb.zzG();
    }

    public static /* synthetic */ void zzc(zzf zzf, zzc zzc) {
        zzc.getClass();
        zzeo zzeo = zzf.zzd;
        if (!zzeo.zzc()) {
            zzf.zzd = zzeh.zzQ(zzeo);
        }
        zzf.zzd.add(zzc);
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzc.class});
        } else if (i4 == 3) {
            return new zzf();
        } else {
            if (i4 == 4) {
                return new zze((zzd) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
