package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzfe implements zzgf {
    private static final zzfk zza = new zzfc();
    private final zzfk zzb;

    public zzfe() {
        zzea zza2 = zzea.zza();
        int i3 = zzfu.zza;
        zzfd zzfd = new zzfd(zza2, zza);
        byte[] bArr = zzep.zzb;
        this.zzb = zzfd;
    }

    public final zzge zza(Class cls) {
        int i3 = zzgg.zza;
        if (!zzeh.class.isAssignableFrom(cls)) {
            int i4 = zzfu.zza;
        }
        zzfj zzb2 = this.zzb.zzb(cls);
        if (!zzb2.zzb()) {
            int i5 = zzfu.zza;
            return zzfp.zzl(cls, zzb2, zzft.zza(), zzfa.zza(), zzgg.zzm(), zzb2.zzc() + -1 != 1 ? zzdv.zza() : null, zzfi.zza());
        }
        int i6 = zzfu.zza;
        return zzfq.zzc(zzgg.zzm(), zzdv.zza(), zzb2.zza());
    }
}
