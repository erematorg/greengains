package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;
import java.util.List;

public final class zzp extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzp zzb;
    private int zzd;
    private zzck zze;
    private String zzf = "";
    private String zzg = "";
    private zzeo zzh = zzeh.zzP();
    private zzeo zzi = zzeh.zzP();
    private zzeo zzj = zzeh.zzP();
    private zzeo zzk = zzeh.zzP();
    private String zzl = "";
    private byte zzm = 2;

    static {
        zzp zzp = new zzp();
        zzb = zzp;
        zzeh.zzV(zzp.class, zzp);
    }

    private zzp() {
    }

    public static zzp zzc() {
        return zzb;
    }

    public final zzck zza() {
        zzck zzck = this.zze;
        return zzck == null ? zzck.zzb() : zzck;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzg;
    }

    public final List zzf() {
        return this.zzk;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0004\u0001\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u001b\u0005\u001b\u0006\u001a\u0007Л\bဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzco.class, "zzi", zzv.class, "zzj", "zzk", zzci.class, "zzl"});
        } else if (i4 == 3) {
            return new zzp();
        } else {
            if (i4 == 4) {
                return new zzo((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzm = obj == null ? (byte) 0 : 1;
            return null;
        }
    }

    public final List zzh() {
        return this.zzi;
    }

    public final List zzi() {
        return this.zzh;
    }

    public final List zzj() {
        return this.zzj;
    }
}
