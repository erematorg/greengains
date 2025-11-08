package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhk;

public final class zzi extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzi zzb;
    private int zzd;
    private String zze = "";
    private zzdf zzf = zzdf.zzb;
    private int zzg = 10;
    private float zzh = 0.5f;
    private float zzi = 0.05f;
    private zzf zzj;
    private int zzk = 1;
    private zzhk zzl;
    private int zzm = DilithiumEngine.DilithiumPolyT1PackedBytes;
    private int zzn = 4;
    private int zzo = 2;

    static {
        zzi zzi2 = new zzi();
        zzb = zzi2;
        zzeh.zzV(zzi.class, zzi2);
    }

    private zzi() {
    }

    public static zzh zza() {
        return (zzh) zzb.zzG();
    }

    public static /* synthetic */ void zzc(zzi zzi2, zzf zzf2) {
        zzf2.getClass();
        zzi2.zzj = zzf2;
        zzi2.zzd |= 32;
    }

    public static /* synthetic */ void zzd(zzi zzi2, zzdf zzdf) {
        zzdf.getClass();
        zzi2.zzd |= 2;
        zzi2.zzf = zzdf;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u000b\u0000\u0001\u0001\f\u000b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဋ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ဉ\u0005\bင\u0006\tဉ\u0007\nင\b\u000bင\t\fင\n", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo"});
        } else if (i4 == 3) {
            return new zzi();
        } else {
            if (i4 == 4) {
                return new zzh((zzg) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
