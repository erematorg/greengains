package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public final class zzjv extends zzed implements zzfn {
    /* access modifiers changed from: private */
    public static final zzjv zzd;
    private byte zze = 2;

    static {
        zzjv zzjv = new zzjv();
        zzd = zzjv;
        zzeh.zzV(zzjv.class, zzjv);
    }

    private zzjv() {
    }

    public static zzjv zzf() {
        return zzd;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zze);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzd, "\u0003\u0000", (Object[]) null);
        }
        if (i4 == 3) {
            return new zzjv();
        }
        if (i4 == 4) {
            return new zzju((zzjt) null);
        }
        if (i4 == 5) {
            return zzd;
        }
        this.zze = obj == null ? (byte) 0 : 1;
        return null;
    }
}
