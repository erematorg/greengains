package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.WorkerThread;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzry;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsi;
import com.google.android.gms.internal.mlkit_common.zzsj;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;

@WorkerThread
final class zzc extends BroadcastReceiver {
    final /* synthetic */ RemoteModelDownloadManager zza;
    private final long zzb;
    private final TaskCompletionSource zzc;

    public /* synthetic */ zzc(RemoteModelDownloadManager remoteModelDownloadManager, long j2, TaskCompletionSource taskCompletionSource, zzb zzb2) {
        this.zza = remoteModelDownloadManager;
        this.zzb = j2;
        this.zzc = taskCompletionSource;
    }

    public final void onReceive(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("extra_download_id", -1);
        if (longExtra == this.zzb) {
            RemoteModelDownloadManager remoteModelDownloadManager = this.zza;
            Integer downloadingModelStatusCode = remoteModelDownloadManager.getDownloadingModelStatusCode();
            synchronized (remoteModelDownloadManager) {
                try {
                    this.zza.zze.getApplicationContext().unregisterReceiver(this);
                } catch (IllegalArgumentException e3) {
                    RemoteModelDownloadManager.zza.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e3);
                }
                this.zza.zzc.remove(this.zzb);
                this.zza.zzd.remove(this.zzb);
            }
            if (downloadingModelStatusCode != null) {
                if (downloadingModelStatusCode.intValue() == 16) {
                    RemoteModelDownloadManager remoteModelDownloadManager2 = this.zza;
                    zzsh zzh = remoteModelDownloadManager2.zzi;
                    zzry zzg = zzsk.zzg();
                    RemoteModel zze = remoteModelDownloadManager2.zzg;
                    Long valueOf = Long.valueOf(longExtra);
                    zzh.zze(zzg, zze, false, remoteModelDownloadManager2.getFailureReason(valueOf));
                    this.zzc.setException(this.zza.zzl(valueOf));
                    return;
                } else if (downloadingModelStatusCode.intValue() == 8) {
                    RemoteModelDownloadManager remoteModelDownloadManager3 = this.zza;
                    zzsh zzh2 = remoteModelDownloadManager3.zzi;
                    zzry zzg2 = zzsk.zzg();
                    RemoteModel zze2 = remoteModelDownloadManager3.zzg;
                    zzsi zzh3 = zzsj.zzh();
                    zzh3.zzb(zzmu.NO_ERROR);
                    zzh3.zze(true);
                    zzh3.zzd(this.zza.zzg.getModelType());
                    zzh3.zza(zzna.SUCCEEDED);
                    zzh2.zzg(zzg2, zze2, zzh3.zzh());
                    this.zzc.setResult(null);
                    return;
                }
            }
            RemoteModelDownloadManager remoteModelDownloadManager4 = this.zza;
            remoteModelDownloadManager4.zzi.zze(zzsk.zzg(), remoteModelDownloadManager4.zzg, false, 0);
            this.zzc.setException(new MlKitException("Model downloading failed", 13));
        }
    }
}
