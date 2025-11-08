package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzdr {
    private final Object zza;
    private final int zzb;

    public zzdr(Object obj, int i3) {
        this.zza = obj;
        this.zzb = i3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdr)) {
            return false;
        }
        zzdr zzdr = (zzdr) obj;
        return this.zza == zzdr.zza && this.zzb == zzdr.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
