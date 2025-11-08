package com.google.android.gms.internal.mlkit_vision_barcode;

final class zzez implements zzfe {
    private final int zza;
    private final zzfd zzb;

    public zzez(int i3, zzfd zzfd) {
        this.zza = i3;
        this.zzb = zzfd;
    }

    public final Class annotationType() {
        return zzfe.class;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfe)) {
            return false;
        }
        zzfe zzfe = (zzfe) obj;
        return this.zza == zzfe.zza() && this.zzb.equals(zzfe.zzb());
    }

    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    public final int zza() {
        return this.zza;
    }

    public final zzfd zzb() {
        return this.zzb;
    }
}
