package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.model.RemoteModel;

public final /* synthetic */ class zzsg implements Runnable {
    public final /* synthetic */ zzsh zza;
    public final /* synthetic */ zzry zzb;
    public final /* synthetic */ zzsj zzc;
    public final /* synthetic */ RemoteModel zzd;

    public /* synthetic */ zzsg(zzsh zzsh, zzry zzry, zzsj zzsj, RemoteModel remoteModel) {
        this.zza = zzsh;
        this.zzb = zzry;
        this.zzc = zzsj;
        this.zzd = remoteModel;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
