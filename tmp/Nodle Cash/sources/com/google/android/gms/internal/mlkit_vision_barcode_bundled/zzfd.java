package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzfd implements zzfk {
    private final zzfk[] zza;

    public zzfd(zzfk... zzfkArr) {
        this.zza = zzfkArr;
    }

    public final zzfj zzb(Class cls) {
        for (int i3 = 0; i3 < 2; i3++) {
            zzfk zzfk = this.zza[i3];
            if (zzfk.zzc(cls)) {
                return zzfk.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean zzc(Class cls) {
        for (int i3 = 0; i3 < 2; i3++) {
            if (this.zza[i3].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
