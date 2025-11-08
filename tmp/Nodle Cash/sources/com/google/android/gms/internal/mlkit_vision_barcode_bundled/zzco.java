package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzco extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzco zzb;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzco zzco = new zzco();
        zzb = zzco;
        zzeh.zzV(zzco.class, zzco);
    }

    private zzco() {
    }

    public static zzco zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final int zzd() {
        int zza = zzcn.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzcm.zza, "zzf"});
        } else if (i4 == 3) {
            return new zzco();
        } else {
            if (i4 == 4) {
                return new zzcl((zzce) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
