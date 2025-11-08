package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzry;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
public class RemoteModelDownloadManager {
    /* access modifiers changed from: private */
    public static final GmsLogger zza = new GmsLogger("ModelDownloadManager", "");
    @GuardedBy("RemoteModelDownloadManager.class")
    private static final Map zzb = new HashMap();
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public final LongSparseArray zzc = new LongSparseArray();
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public final LongSparseArray zzd = new LongSparseArray();
    /* access modifiers changed from: private */
    public final MlKitContext zze;
    @Nullable
    private final DownloadManager zzf;
    /* access modifiers changed from: private */
    public final RemoteModel zzg;
    private final ModelType zzh;
    /* access modifiers changed from: private */
    public final zzsh zzi;
    private final SharedPrefManager zzj;
    private final ModelFileHelper zzk;
    @Nullable
    private final ModelInfoRetrieverInterop zzl;
    private final RemoteModelFileManager zzm;
    private DownloadConditions zzn;

    @VisibleForTesting
    public RemoteModelDownloadManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileManager remoteModelFileManager, @Nullable ModelInfoRetrieverInterop modelInfoRetrieverInterop, @NonNull zzsh zzsh) {
        this.zze = mlKitContext;
        this.zzh = remoteModel.getModelType();
        this.zzg = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.zzf = downloadManager;
        this.zzi = zzsh;
        if (downloadManager == null) {
            zza.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzk = modelFileHelper;
        this.zzj = SharedPrefManager.getInstance(mlKitContext);
        this.zzl = modelInfoRetrieverInterop;
        this.zzm = remoteModelFileManager;
    }

    @NonNull
    @KeepForSdk
    public static synchronized RemoteModelDownloadManager getInstance(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileManager remoteModelFileManager, @Nullable ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            try {
                Map map = zzb;
                if (!map.containsKey(remoteModel)) {
                    map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zzss.zzb("common")));
                }
                remoteModelDownloadManager = (RemoteModelDownloadManager) map.get(remoteModel);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return remoteModelDownloadManager;
    }

    private final Task zzj(long j2) {
        MlKitContext mlKitContext = this.zze;
        ContextCompat.registerReceiver(mlKitContext.getApplicationContext(), zzm(j2), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), (String) null, MLTaskExecutor.getInstance().getHandler(), 2);
        return zzk(j2).getTask();
    }

    private final synchronized TaskCompletionSource zzk(long j2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzd.get(j2);
        if (taskCompletionSource != null) {
            return taskCompletionSource;
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.zzd.put(j2, taskCompletionSource2);
        return taskCompletionSource2;
    }

    /* access modifiers changed from: private */
    public final MlKitException zzl(@Nullable Long l2) {
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l2 == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l2.longValue()}));
        }
        int i3 = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i4 = cursor.getInt(cursor.getColumnIndex("reason"));
            if (i4 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i3 = 101;
            } else {
                str = C0118y.c(i4, "Model downloading failed due to error code: ", " from Android DownloadManager");
            }
        }
        return new MlKitException(str, i3);
    }

    private final synchronized zzc zzm(long j2) {
        zzc zzc2 = (zzc) this.zzc.get(j2);
        if (zzc2 != null) {
            return zzc2;
        }
        zzc zzc3 = new zzc(this, j2, zzk(j2), (zzb) null);
        this.zzc.put(j2, zzc3);
        return zzc3;
    }

    @Nullable
    private final synchronized Long zzn(@NonNull DownloadManager.Request request, @NonNull ModelInfo modelInfo) {
        DownloadManager downloadManager = this.zzf;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.zzj.setDownloadingModelInfo(enqueue, modelInfo);
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    @WorkerThread
    @Nullable
    private final synchronized Long zzo(@NonNull ModelInfo modelInfo, @NonNull DownloadConditions downloadConditions) throws MlKitException {
        try {
            Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
            String downloadingModelHash = this.zzj.getDownloadingModelHash(this.zzg);
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            if (downloadingModelHash == null || !downloadingModelHash.equals(modelInfo.getModelHash()) || downloadingModelStatusCode == null) {
                GmsLogger gmsLogger = zza;
                gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
                removeOrCancelDownload();
                DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
                if (this.zzk.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
                    gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                    this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.UPDATE_AVAILABLE);
                }
                request.setRequiresCharging(downloadConditions.isChargingRequired());
                if (downloadConditions.isWifiRequired()) {
                    request.setAllowedNetworkTypes(2);
                }
                return zzn(request, modelInfo);
            }
            Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
            if (downloadingModelStatusCode2 != null) {
                if (!(downloadingModelStatusCode2.intValue() == 8 || downloadingModelStatusCode2.intValue() == 16)) {
                }
                zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
                return null;
            }
            zzsh zzsh = this.zzi;
            RemoteModel remoteModel = this.zzg;
            zzsh.zzf(zzsk.zzg(), remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.DOWNLOADING);
            zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
            return null;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    @WorkerThread
    @NonNull
    @KeepForSdk
    public Task<Void> ensureModelDownloaded() {
        MlKitException mlKitException;
        ModelInfo modelInfo;
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, ModelType.UNKNOWN, zzna.EXPLICITLY_REQUESTED);
        Long l2 = null;
        try {
            modelInfo = zzg();
            mlKitException = null;
        } catch (MlKitException e3) {
            mlKitException = e3;
            modelInfo = null;
        }
        try {
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            Long downloadingId = getDownloadingId();
            if (!modelExistsLocally()) {
                if (downloadingModelStatusCode == null || downloadingModelStatusCode.intValue() != 8) {
                    if (downloadingModelStatusCode != null && downloadingModelStatusCode.intValue() == 16) {
                        MlKitException zzl2 = zzl(downloadingId);
                        removeOrCancelDownload();
                        return Tasks.forException(zzl2);
                    } else if (downloadingModelStatusCode == null || (!(downloadingModelStatusCode.intValue() == 4 || downloadingModelStatusCode.intValue() == 2 || downloadingModelStatusCode.intValue() == 1) || downloadingId == null || getDownloadingModelHash() == null)) {
                        if (modelInfo != null) {
                            l2 = zzo(modelInfo, this.zzn);
                        }
                        return l2 == null ? Tasks.forException(new MlKitException("Failed to schedule the download task", 13, mlKitException)) : zzj(l2.longValue());
                    } else {
                        zzsh zzsh = this.zzi;
                        zzry zzg2 = zzsk.zzg();
                        RemoteModel remoteModel = this.zzg;
                        zzsh.zzf(zzg2, remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.DOWNLOADING);
                        return zzj(downloadingId.longValue());
                    }
                }
            }
            if (modelInfo != null) {
                Long zzo = zzo(modelInfo, this.zzn);
                if (zzo != null) {
                    return zzj(zzo.longValue());
                }
                zza.i("ModelDownloadManager", "Didn't schedule download for the updated model");
            }
            return Tasks.forResult(null);
        } catch (MlKitException e4) {
            return Tasks.forException(new MlKitException("Failed to ensure the model is downloaded.", 13, e4));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zza.e("ModelDownloadManager", "Downloaded file is not found");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0017 */
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.os.ParcelFileDescriptor getDownloadedFile() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x0015 }
            java.lang.Long r1 = r5.getDownloadingId()     // Catch:{ all -> 0x0015 }
            r2 = 0
            if (r0 == 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            long r3 = r1.longValue()     // Catch:{ FileNotFoundException -> 0x0017 }
            android.os.ParcelFileDescriptor r2 = r0.openDownloadedFile(r3)     // Catch:{ FileNotFoundException -> 0x0017 }
            goto L_0x0020
        L_0x0015:
            r0 = move-exception
            goto L_0x0024
        L_0x0017:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x0015 }
            java.lang.String r1 = "ModelDownloadManager"
            java.lang.String r3 = "Downloaded file is not found"
            r0.e(r1, r3)     // Catch:{ all -> 0x0015 }
        L_0x0020:
            monitor-exit(r5)
            return r2
        L_0x0022:
            monitor-exit(r5)
            return r2
        L_0x0024:
            monitor-exit(r5)     // Catch:{ all -> 0x0015 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadedFile():android.os.ParcelFileDescriptor");
    }

    @KeepForSdk
    @Nullable
    public synchronized Long getDownloadingId() {
        return this.zzj.getDownloadingModelId(this.zzg);
    }

    @KeepForSdk
    @Nullable
    public synchronized String getDownloadingModelHash() {
        return this.zzj.getDownloadingModelHash(this.zzg);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007e, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049 A[SYNTHETIC, Splitter:B:20:0x0049] */
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Integer getDownloadingModelStatusCode() {
        /*
            r8 = this;
            r0 = 1
            monitor-enter(r8)
            android.app.DownloadManager r1 = r8.zzf     // Catch:{ all -> 0x0047 }
            java.lang.Long r2 = r8.getDownloadingId()     // Catch:{ all -> 0x0047 }
            r3 = 0
            if (r1 == 0) goto L_0x007d
            if (r2 != 0) goto L_0x000f
            goto L_0x007d
        L_0x000f:
            android.app.DownloadManager$Query r4 = new android.app.DownloadManager$Query     // Catch:{ all -> 0x0047 }
            r4.<init>()     // Catch:{ all -> 0x0047 }
            long r5 = r2.longValue()     // Catch:{ all -> 0x0047 }
            long[] r2 = new long[r0]     // Catch:{ all -> 0x0047 }
            r7 = 0
            r2[r7] = r5     // Catch:{ all -> 0x0047 }
            android.app.DownloadManager$Query r2 = r4.setFilterById(r2)     // Catch:{ all -> 0x0047 }
            android.database.Cursor r1 = r1.query(r2)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x003e
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x003e
            java.lang.String r2 = "status"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x003c }
            int r2 = r1.getInt(r2)     // Catch:{ all -> 0x003c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x003c }
            goto L_0x003f
        L_0x003c:
            r0 = move-exception
            goto L_0x0074
        L_0x003e:
            r2 = r3
        L_0x003f:
            if (r2 != 0) goto L_0x0049
            if (r1 == 0) goto L_0x007d
            r1.close()     // Catch:{ all -> 0x0047 }
            goto L_0x007d
        L_0x0047:
            r0 = move-exception
            goto L_0x007f
        L_0x0049:
            int r4 = r2.intValue()     // Catch:{ all -> 0x003c }
            r5 = 2
            if (r4 == r5) goto L_0x006e
            int r4 = r2.intValue()     // Catch:{ all -> 0x003c }
            r5 = 4
            if (r4 == r5) goto L_0x006e
            int r4 = r2.intValue()     // Catch:{ all -> 0x003c }
            if (r4 == r0) goto L_0x006e
            int r0 = r2.intValue()     // Catch:{ all -> 0x003c }
            r4 = 8
            if (r0 == r4) goto L_0x006e
            int r0 = r2.intValue()     // Catch:{ all -> 0x003c }
            r4 = 16
            if (r0 == r4) goto L_0x006e
            goto L_0x006f
        L_0x006e:
            r3 = r2
        L_0x006f:
            r1.close()     // Catch:{ all -> 0x0047 }
            monitor-exit(r8)
            return r3
        L_0x0074:
            r1.close()     // Catch:{ all -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch:{ all -> 0x0047 }
        L_0x007c:
            throw r0     // Catch:{ all -> 0x0047 }
        L_0x007d:
            monitor-exit(r8)
            return r3
        L_0x007f:
            monitor-exit(r8)     // Catch:{ all -> 0x0047 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadingModelStatusCode():java.lang.Integer");
    }

    @KeepForSdk
    public int getFailureReason(@NonNull Long l2) {
        int columnIndex;
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l2 == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l2.longValue()}));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    @WorkerThread
    @KeepForSdk
    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException unused) {
            zza.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            zza.d("ModelDownloadManager", "No new model is downloading.");
            removeOrCancelDownload();
            return false;
        }
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        zza.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
        if (downloadingModelStatusCode != null) {
            return Objects.equal(downloadingModelStatusCode, 8) && zzi(downloadingModelHash) != null;
        }
        removeOrCancelDownload();
        return false;
    }

    @KeepForSdk
    public boolean modelExistsLocally() throws MlKitException {
        return this.zzk.modelExistsLocally(this.zzg.getUniqueModelNameForPersist(), this.zzh);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        return;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeOrCancelDownload() throws com.google.mlkit.common.MlKitException {
        /*
            r5 = this;
            monitor-enter(r5)
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x0036 }
            java.lang.Long r1 = r5.getDownloadingId()     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0050
            if (r1 != 0) goto L_0x000c
            goto L_0x0050
        L_0x000c:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = "Cancel or remove existing downloading task: "
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = r2.concat(r3)     // Catch:{ all -> 0x0036 }
            java.lang.String r3 = "ModelDownloadManager"
            r0.d(r3, r2)     // Catch:{ all -> 0x0036 }
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x0036 }
            long r1 = r1.longValue()     // Catch:{ all -> 0x0036 }
            r3 = 1
            long[] r3 = new long[r3]     // Catch:{ all -> 0x0036 }
            r4 = 0
            r3[r4] = r1     // Catch:{ all -> 0x0036 }
            int r0 = r0.remove(r3)     // Catch:{ all -> 0x0036 }
            if (r0 > 0) goto L_0x0038
            java.lang.Integer r0 = r5.getDownloadingModelStatusCode()     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0050
            goto L_0x0038
        L_0x0036:
            r0 = move-exception
            goto L_0x0052
        L_0x0038:
            com.google.mlkit.common.sdkinternal.model.ModelFileHelper r0 = r5.zzk     // Catch:{ all -> 0x0036 }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = r1.getUniqueModelNameForPersist()     // Catch:{ all -> 0x0036 }
            com.google.mlkit.common.sdkinternal.ModelType r1 = r1.getModelType()     // Catch:{ all -> 0x0036 }
            r0.deleteTempFilesInPrivateFolder(r2, r1)     // Catch:{ all -> 0x0036 }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r0 = r5.zzj     // Catch:{ all -> 0x0036 }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x0036 }
            r0.clearDownloadingModelInfo(r1)     // Catch:{ all -> 0x0036 }
            monitor-exit(r5)
            return
        L_0x0050:
            monitor-exit(r5)
            return
        L_0x0052:
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.removeOrCancelDownload():void");
    }

    @KeepForSdk
    public void setDownloadConditions(@NonNull DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.zzn = downloadConditions;
    }

    @KeepForSdk
    public synchronized void updateLatestModelHashAndType(@NonNull String str) throws MlKitException {
        this.zzj.setLatestModelHash(this.zzg, str);
        removeOrCancelDownload();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        return r2;
     */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.mlkit.common.sdkinternal.ModelInfo zzg() throws com.google.mlkit.common.MlKitException {
        /*
            r9 = this;
            java.lang.String r0 = "The model "
            monitor-enter(r9)
            boolean r1 = r9.modelExistsLocally()     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0021
            com.google.android.gms.internal.mlkit_common.zzsh r2 = r9.zzi     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.model.RemoteModel r4 = r9.zzg     // Catch:{ all -> 0x001e }
            com.google.android.gms.internal.mlkit_common.zzry r3 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch:{ all -> 0x001e }
            com.google.android.gms.internal.mlkit_common.zzmu r5 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.sdkinternal.ModelType r7 = r4.getModelType()     // Catch:{ all -> 0x001e }
            com.google.android.gms.internal.mlkit_common.zzna r8 = com.google.android.gms.internal.mlkit_common.zzna.LIVE     // Catch:{ all -> 0x001e }
            r6 = 0
            r2.zzf(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x001e }
            goto L_0x0021
        L_0x001e:
            r0 = move-exception
            goto L_0x00be
        L_0x0021:
            com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop r2 = r9.zzl     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x00b4
            com.google.mlkit.common.model.RemoteModel r3 = r9.zzg     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.sdkinternal.ModelInfo r2 = r2.retrieveRemoteModelInfo(r3)     // Catch:{ all -> 0x001e }
            r3 = 0
            if (r2 != 0) goto L_0x0030
            monitor-exit(r9)
            return r3
        L_0x0030:
            com.google.mlkit.common.sdkinternal.MlKitContext r4 = r9.zze     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.model.RemoteModel r5 = r9.zzg     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r2.getModelHash()     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r7 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r4)     // Catch:{ all -> 0x001e }
            java.lang.String r5 = r7.getIncompatibleModelHash(r5)     // Catch:{ all -> 0x001e }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x001e }
            r6 = 0
            r8 = 1
            if (r5 == 0) goto L_0x0064
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x001e }
            java.lang.String r4 = com.google.mlkit.common.sdkinternal.CommonUtils.getAppVersion(r4)     // Catch:{ all -> 0x001e }
            java.lang.String r5 = r7.getPreviousAppVersion()     // Catch:{ all -> 0x001e }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x001e }
            if (r4 == 0) goto L_0x0064
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x001e }
            java.lang.String r5 = "ModelDownloadManager"
            java.lang.String r7 = "The model is incompatible with TFLite and the app is not upgraded, do not download"
            r4.e(r5, r7)     // Catch:{ all -> 0x001e }
            r8 = r6
        L_0x0064:
            if (r1 != 0) goto L_0x006d
            com.google.mlkit.common.sdkinternal.SharedPrefManager r4 = r9.zzj     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.model.RemoteModel r5 = r9.zzg     // Catch:{ all -> 0x001e }
            r4.clearLatestModelHash(r5)     // Catch:{ all -> 0x001e }
        L_0x006d:
            com.google.mlkit.common.sdkinternal.MlKitContext r4 = r9.zze     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.model.RemoteModel r5 = r9.zzg     // Catch:{ all -> 0x001e }
            java.lang.String r7 = r2.getModelHash()     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r4 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r4)     // Catch:{ all -> 0x001e }
            java.lang.String r4 = r4.getLatestModelHash(r5)     // Catch:{ all -> 0x001e }
            boolean r4 = r7.equals(r4)     // Catch:{ all -> 0x001e }
            r5 = r4 ^ 1
            if (r8 == 0) goto L_0x008c
            if (r1 == 0) goto L_0x008a
            if (r4 == 0) goto L_0x008a
            goto L_0x008d
        L_0x008a:
            monitor-exit(r9)
            return r2
        L_0x008c:
            r6 = r5
        L_0x008d:
            if (r1 == 0) goto L_0x0095
            r1 = r6 ^ r8
            if (r1 == 0) goto L_0x0095
            monitor-exit(r9)
            return r3
        L_0x0095:
            com.google.mlkit.common.model.RemoteModel r1 = r9.zzg     // Catch:{ all -> 0x001e }
            com.google.mlkit.common.MlKitException r2 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x001e }
            java.lang.String r1 = r1.getModelName()     // Catch:{ all -> 0x001e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001e }
            r3.<init>(r0)     // Catch:{ all -> 0x001e }
            r3.append(r1)     // Catch:{ all -> 0x001e }
            java.lang.String r0 = " is incompatible with TFLite runtime"
            r3.append(r0)     // Catch:{ all -> 0x001e }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x001e }
            r1 = 100
            r2.<init>(r0, r1)     // Catch:{ all -> 0x001e }
            throw r2     // Catch:{ all -> 0x001e }
        L_0x00b4:
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x001e }
            java.lang.String r1 = "Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase."
            r2 = 14
            r0.<init>(r1, r2)     // Catch:{ all -> 0x001e }
            throw r0     // Catch:{ all -> 0x001e }
        L_0x00be:
            monitor-exit(r9)     // Catch:{ all -> 0x001e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.zzg():com.google.mlkit.common.sdkinternal.ModelInfo");
    }

    @Nullable
    public final File zzi(@NonNull String str) throws MlKitException {
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Model downloaded successfully");
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, true, this.zzh, zzna.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        gmsLogger.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.zzm.moveModelToPrivateFolder(downloadedFile, str, this.zzg);
        } finally {
            removeOrCancelDownload();
        }
    }
}
