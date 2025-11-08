package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzcp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwo;
import com.google.mlkit.vision.common.InputImage;

public final /* synthetic */ class zzj implements zzwo {
    public final /* synthetic */ zzl zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzrb zzc;
    public final /* synthetic */ zzcp zzd;
    public final /* synthetic */ zzcp zze;
    public final /* synthetic */ InputImage zzf;

    public /* synthetic */ zzj(zzl zzl, long j2, zzrb zzrb, zzcp zzcp, zzcp zzcp2, InputImage inputImage) {
        this.zza = zzl;
        this.zzb = j2;
        this.zzc = zzrb;
        this.zzd = zzcp;
        this.zze = zzcp2;
        this.zzf = inputImage;
    }

    public final zzwe zza() {
        return this.zza.zzc(this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
