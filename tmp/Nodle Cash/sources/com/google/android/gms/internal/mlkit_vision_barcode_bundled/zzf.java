package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzf extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzf zzb;
    private int zzd;
    private String zze = "";
    private zzeo zzf = zzeh.zzP();
    private zzeo zzg = zzeh.zzP();
    private zzeo zzh = zzeh.zzP();
    private zzjv zzi;
    private zzf zzj;
    private zzx zzk;
    private byte zzl = 2;

    static {
        zzf zzf2 = new zzf();
        zzb = zzf2;
        zzeh.zzV(zzf.class, zzf2);
        zzeh.zzI(zzjv.zzf(), zzf2, zzf2, (zzek) null, 12208774, zzhf.MESSAGE, zzf.class);
    }

    private zzf() {
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzl);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0001\u0007\u0000\u0001\u0002Ǵ\u0007\u0000\u0003\u0004\u0002Л\u0005Л\u0006\u001b\bᐉ\u0001\nဈ\u0000\u000bᐉ\u0002Ǵဉ\u0003", new Object[]{"zzd", "zzf", zzj.class, "zzh", zzj.class, "zzg", zzm.class, "zzi", "zze", "zzj", "zzk"});
        } else if (i4 == 3) {
            return new zzf();
        } else {
            if (i4 == 4) {
                return new zze((zzd) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzl = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
