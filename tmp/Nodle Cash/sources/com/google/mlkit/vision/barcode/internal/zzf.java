package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final /* synthetic */ class zzf implements SuccessContinuation {
    public final /* synthetic */ zzh zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ int zzc;

    public /* synthetic */ zzf(zzh zzh, int i3, int i4) {
        this.zza = zzh;
        this.zzb = i3;
        this.zzc = i4;
    }

    public final Task then(Object obj) {
        return this.zza.zzd(this.zzb, this.zzc, (List) obj);
    }
}
