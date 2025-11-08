package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

public final class zzaf extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzaf zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg = 2;

    static {
        zzaf zzaf = new zzaf();
        zzb = zzaf;
        zzeh.zzV(zzaf.class, zzaf);
    }

    private zzaf() {
    }

    public static zzae zzc() {
        return (zzae) zzb.zzG();
    }

    public static /* synthetic */ void zze(zzaf zzaf, int i3) {
        zzaf.zzd |= 1;
        zzaf.zze = i3;
    }

    public static /* synthetic */ void zzf(zzaf zzaf, int i3) {
        zzaf.zzd |= 2;
        zzaf.zzf = i3;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i4 == 3) {
            return new zzaf();
        } else {
            if (i4 == 4) {
                return new zzae((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
