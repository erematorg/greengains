package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;

public final /* synthetic */ class zzd implements SuccessContinuation {
    public final /* synthetic */ RemoteModelDownloadManager zza;

    public /* synthetic */ zzd(RemoteModelDownloadManager remoteModelDownloadManager) {
        this.zza = remoteModelDownloadManager;
    }

    public final Task then(Object obj) {
        return this.zza.ensureModelDownloaded();
    }
}
