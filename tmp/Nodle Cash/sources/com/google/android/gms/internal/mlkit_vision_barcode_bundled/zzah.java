package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzah extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzah zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private zzen zzg = zzeh.zzO();
    private String zzh = "";
    private String zzi = "";
    private zzx zzj;
    private zzjv zzk;
    private byte zzl = 2;

    static {
        zzah zzah = new zzah();
        zzb = zzah;
        zzeh.zzV(zzah.class, zzah);
        zzeh.zzI(zzjv.zzf(), zzah, zzah, (zzek) null, 308676116, zzhf.MESSAGE, zzah.class);
    }

    private zzah() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzl);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0007\u0000\u0001\u0001Ǵ\u0007\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ࠞ\u0005ဈ\u0002\u0006ဈ\u0003\u000fᐉ\u0005Ǵဉ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", zzag.zza, "zzh", "zzi", "zzk", "zzj"});
        } else if (i4 == 3) {
            return new zzah();
        } else {
            if (i4 == 4) {
                return new zzaf((zzae) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzl = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
