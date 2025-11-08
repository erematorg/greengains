package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    @GuardedBy("RemoteModelLoader.class")
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private final zzsh zzh;
    private boolean zzi = true;

    private RemoteModelLoader(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zzf = remoteModelFileManager;
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
        this.zzh = zzss.zzb("common");
    }

    @NonNull
    @KeepForSdk
    public static synchronized RemoteModelLoader getInstance(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelLoader remoteModelLoader;
        synchronized (RemoteModelLoader.class) {
            try {
                String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
                Map map = zzb;
                if (!map.containsKey(uniqueModelNameForPersist)) {
                    map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
                }
                remoteModelLoader = (RemoteModelLoader) map.get(uniqueModelNameForPersist);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return remoteModelLoader;
    }

    @WorkerThread
    @NonNull
    private final MappedByteBuffer zza(@NonNull String str) throws MlKitException {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) throws MlKitException {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e3) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e3);
        }
    }

    @NonNull
    @KeepForSdk
    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b8 A[Catch:{ Exception -> 0x00d4, all -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f4 A[Catch:{ Exception -> 0x00d4, all -> 0x002e }] */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.nio.MappedByteBuffer load() throws com.google.mlkit.common.MlKitException {
        /*
            r10 = this;
            monitor-enter(r10)
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x002e }
            java.lang.String r1 = "RemoteModelLoader"
            java.lang.String r2 = "Try to load newly downloaded model file."
            r0.d(r1, r2)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r10.zze     // Catch:{ all -> 0x002e }
            boolean r2 = r10.zzi     // Catch:{ all -> 0x002e }
            java.lang.Long r3 = r1.getDownloadingId()     // Catch:{ all -> 0x002e }
            java.lang.String r1 = r1.getDownloadingModelHash()     // Catch:{ all -> 0x002e }
            r4 = 0
            r5 = 0
            if (r3 == 0) goto L_0x00a8
            if (r1 != 0) goto L_0x001e
            goto L_0x00a8
        L_0x001e:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r6 = r10.zze     // Catch:{ all -> 0x002e }
            java.lang.Integer r6 = r6.getDownloadingModelStatusCode()     // Catch:{ all -> 0x002e }
            if (r6 != 0) goto L_0x0031
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r10.zze     // Catch:{ all -> 0x002e }
            r1.removeOrCancelDownload()     // Catch:{ all -> 0x002e }
        L_0x002b:
            r6 = r5
            goto L_0x00b6
        L_0x002e:
            r0 = move-exception
            goto L_0x00f9
        L_0x0031:
            java.lang.String r7 = "Download Status code: "
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x002e }
            java.lang.String r7 = r7.concat(r8)     // Catch:{ all -> 0x002e }
            java.lang.String r8 = "RemoteModelLoader"
            r0.d(r8, r7)     // Catch:{ all -> 0x002e }
            int r7 = r6.intValue()     // Catch:{ all -> 0x002e }
            r8 = 8
            if (r7 != r8) goto L_0x0089
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r10.zze     // Catch:{ all -> 0x002e }
            java.io.File r3 = r3.zzi(r1)     // Catch:{ all -> 0x002e }
            if (r3 != 0) goto L_0x0051
            goto L_0x002b
        L_0x0051:
            java.nio.MappedByteBuffer r6 = r10.zzb(r3)     // Catch:{ all -> 0x002e }
            java.lang.String r7 = r3.getParent()     // Catch:{ all -> 0x002e }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x002e }
            java.lang.String r8 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r9 = "RemoteModelLoader"
            java.lang.String r7 = r8.concat(r7)     // Catch:{ all -> 0x002e }
            r0.d(r9, r7)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r7 = r10.zze     // Catch:{ all -> 0x002e }
            r7.updateLatestModelHashAndType(r1)     // Catch:{ all -> 0x002e }
            if (r2 == 0) goto L_0x00b6
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r1 = r10.zzf     // Catch:{ all -> 0x002e }
            boolean r1 = r1.zzd(r3)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x00b6
            java.lang.String r1 = "RemoteModelLoader"
            java.lang.String r2 = "All old models are deleted."
            r0.d(r1, r2)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r1 = r10.zzf     // Catch:{ all -> 0x002e }
            java.io.File r1 = r1.zza(r3)     // Catch:{ all -> 0x002e }
            java.nio.MappedByteBuffer r6 = r10.zzb(r1)     // Catch:{ all -> 0x002e }
            goto L_0x00b6
        L_0x0089:
            int r1 = r6.intValue()     // Catch:{ all -> 0x002e }
            r2 = 16
            if (r1 != r2) goto L_0x002b
            com.google.android.gms.internal.mlkit_common.zzsh r1 = r10.zzh     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.model.RemoteModel r2 = r10.zzd     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r6 = r10.zze     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.mlkit_common.zzry r7 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch:{ all -> 0x002e }
            int r3 = r6.getFailureReason(r3)     // Catch:{ all -> 0x002e }
            r1.zze(r7, r2, r4, r3)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r10.zze     // Catch:{ all -> 0x002e }
            r1.removeOrCancelDownload()     // Catch:{ all -> 0x002e }
            goto L_0x002b
        L_0x00a8:
            java.lang.String r1 = "RemoteModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r0.d(r1, r2)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r10.zze     // Catch:{ all -> 0x002e }
            r1.removeOrCancelDownload()     // Catch:{ all -> 0x002e }
            goto L_0x002b
        L_0x00b6:
            if (r6 != 0) goto L_0x00f4
            java.lang.String r1 = "RemoteModelLoader"
            java.lang.String r2 = "Loading existing model file."
            r0.d(r1, r2)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r1 = r10.zzf     // Catch:{ all -> 0x002e }
            java.lang.String r1 = r1.zzb()     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x00cf
            java.lang.String r1 = "RemoteModelLoader"
            java.lang.String r2 = "No existing model file"
            r0.d(r1, r2)     // Catch:{ all -> 0x002e }
            goto L_0x00f7
        L_0x00cf:
            java.nio.MappedByteBuffer r5 = r10.zza(r1)     // Catch:{ Exception -> 0x00d4 }
            goto L_0x00f7
        L_0x00d4:
            r0 = move-exception
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r2 = r10.zzf     // Catch:{ all -> 0x002e }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x002e }
            r3.<init>(r1)     // Catch:{ all -> 0x002e }
            r2.zzc(r3)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.MlKitContext r1 = r10.zzc     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.model.RemoteModel r2 = r10.zzd     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r1 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r1)     // Catch:{ all -> 0x002e }
            r1.clearLatestModelHash(r2)     // Catch:{ all -> 0x002e }
            com.google.mlkit.common.MlKitException r1 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x002e }
            java.lang.String r2 = "Failed to load an already downloaded model."
            r3 = 14
            r1.<init>(r2, r3, r0)     // Catch:{ all -> 0x002e }
            throw r1     // Catch:{ all -> 0x002e }
        L_0x00f4:
            r10.zzi = r4     // Catch:{ all -> 0x002e }
            r5 = r6
        L_0x00f7:
            monitor-exit(r10)
            return r5
        L_0x00f9:
            monitor-exit(r10)     // Catch:{ all -> 0x002e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelLoader.load():java.nio.MappedByteBuffer");
    }
}
