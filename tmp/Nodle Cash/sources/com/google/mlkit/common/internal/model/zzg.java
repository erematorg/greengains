package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzmh;
import com.google.android.gms.internal.mlkit_common.zzmv;
import com.google.android.gms.internal.mlkit_common.zzmw;
import com.google.android.gms.internal.mlkit_common.zzne;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.ModelValidator;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.Set;

public final class zzg implements RemoteModelManagerInterface {
    private final MlKitContext zza;
    private final zzsh zzb;

    public zzg(MlKitContext mlKitContext) {
        zzsh zzb2 = zzss.zzb("common");
        this.zza = mlKitContext;
        this.zzb = zzb2;
    }

    private final RemoteModelDownloadManager zze(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.zza, customRemoteModel, (ModelValidator) null, new ModelFileHelper(this.zza), new zza(this.zza, customRemoteModel.getUniqueModelNameForPersist()));
        MlKitContext mlKitContext = this.zza;
        return RemoteModelDownloadManager.getInstance(this.zza, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
    }

    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        MLTaskExecutor.workerThreadExecutor().execute(new zzb(this, (CustomRemoteModel) remoteModel, taskCompletionSource));
        return taskCompletionSource.getTask().addOnCompleteListener(new zzc(this));
    }

    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, DownloadConditions downloadConditions) {
        RemoteModelDownloadManager zze = zze((CustomRemoteModel) remoteModel);
        zze.setDownloadConditions(downloadConditions);
        return Tasks.forResult(null).onSuccessTask(MLTaskExecutor.workerThreadExecutor(), new zzd(zze));
    }

    public final Task<Set<CustomRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new MlKitException("Custom Remote model does not support listing downloaded models", 12));
    }

    public final /* bridge */ /* synthetic */ Task isModelDownloaded(RemoteModel remoteModel) {
        return MLTaskExecutor.getInstance().scheduleCallable(new zze(this, (CustomRemoteModel) remoteModel)).addOnCompleteListener(new zzf(this));
    }

    public final /* synthetic */ Boolean zza(CustomRemoteModel customRemoteModel) throws Exception {
        return Boolean.valueOf(zze(customRemoteModel).isModelDownloadedAndValid());
    }

    public final /* synthetic */ void zzb(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.zza).deleteAllModels(ModelType.CUSTOM, (String) Preconditions.checkNotNull(customRemoteModel.getModelName()));
            taskCompletionSource.setResult(null);
        } catch (RuntimeException e3) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e3));
        }
    }

    public final /* synthetic */ void zzc(Task task) {
        boolean isSuccessful = task.isSuccessful();
        zzmw zzmw = new zzmw();
        zzlm zzlm = new zzlm();
        zzlm.zzb(zzne.CUSTOM);
        zzlm.zza(Boolean.valueOf(isSuccessful));
        zzmw.zze(zzlm.zzc());
        this.zzb.zzd(zzsk.zzf(zzmw), zzmv.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    public final /* synthetic */ void zzd(Task task) {
        Boolean bool = (Boolean) task.getResult();
        bool.booleanValue();
        zzmw zzmw = new zzmw();
        zzmh zzmh = new zzmh();
        zzmh.zzb(zzne.CUSTOM);
        zzmh.zza(bool);
        zzmw.zzg(zzmh.zzc());
        this.zzb.zzd(zzsk.zzf(zzmw), zzmv.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
