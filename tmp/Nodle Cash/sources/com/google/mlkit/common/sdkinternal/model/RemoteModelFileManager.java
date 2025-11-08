package com.google.mlkit.common.sdkinternal.model;

import android.annotation.SuppressLint;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.internal.model.ModelUtils;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@KeepForSdk
public class RemoteModelFileManager {
    private static final GmsLogger zza = new GmsLogger("RemoteModelFileManager", "");
    private final MlKitContext zzb;
    private final String zzc;
    private final ModelType zzd;
    @Nullable
    private final ModelValidator zze;
    private final RemoteModelFileMover zzf;
    private final SharedPrefManager zzg;
    private final ModelFileHelper zzh;

    @SuppressLint({"FirebaseLambdaLast"})
    public RemoteModelFileManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @Nullable ModelValidator modelValidator, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        this.zzb = mlKitContext;
        ModelType modelType = remoteModel.getModelType();
        this.zzd = modelType;
        this.zzc = modelType == ModelType.TRANSLATE ? remoteModel.getModelNameForBackend() : remoteModel.getUniqueModelNameForPersist();
        this.zze = modelValidator;
        this.zzg = SharedPrefManager.getInstance(mlKitContext);
        this.zzh = modelFileHelper;
        this.zzf = remoteModelFileMover;
    }

    @NonNull
    @KeepForSdk
    public File getModelDirUnsafe(boolean z2) {
        return this.zzh.getModelDirUnsafe(this.zzc, this.zzd, z2);
    }

    @WorkerThread
    @Nullable
    @KeepForSdk
    public synchronized File moveModelToPrivateFolder(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull String str, @NonNull RemoteModel remoteModel) throws MlKitException {
        File file;
        FileOutputStream fileOutputStream;
        MlKitException mlKitException;
        ModelValidator modelValidator;
        try {
            file = new File(this.zzh.zza(this.zzc, this.zzd), "to_be_validated_model.tmp");
            ModelValidator.ValidationResult validationResult = null;
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
            try {
                fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = autoCloseInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                autoCloseInputStream.close();
                boolean zza2 = ModelUtils.zza(file, str);
                if (zza2 && (modelValidator = this.zze) != null) {
                    validationResult = modelValidator.validateModel(file, remoteModel);
                    if (validationResult.getErrorCode().equals(ModelValidator.ValidationResult.ErrorCode.TFLITE_VERSION_INCOMPATIBLE)) {
                        String appVersion = CommonUtils.getAppVersion(this.zzb.getApplicationContext());
                        this.zzg.setIncompatibleModelInfo(remoteModel, str, appVersion);
                        String valueOf = String.valueOf(str);
                        GmsLogger gmsLogger = zza;
                        gmsLogger.d("RemoteModelFileManager", "Model is not compatible. Model hash: ".concat(valueOf));
                        gmsLogger.d("RemoteModelFileManager", "The current app version is: ".concat(String.valueOf(appVersion)));
                    }
                }
                if (zza2) {
                    if (validationResult == null || validationResult.isValid()) {
                    }
                }
                if (!zza2) {
                    zza.d("RemoteModelFileManager", "Hash does not match with expected: ".concat(String.valueOf(str)));
                    RemoteModel remoteModel2 = remoteModel;
                    zzss.zzb("common").zzf(zzsk.zzg(), remoteModel2, zzmu.MODEL_HASH_MISMATCH, true, this.zzd, zzna.SUCCEEDED);
                    mlKitException = new MlKitException("Hash does not match with expected", 102);
                } else {
                    mlKitException = new MlKitException("Model is not compatible with TFLite run time", 100);
                }
                if (!file.delete()) {
                    zza.d("RemoteModelFileManager", "Failed to delete the temp file: ".concat(String.valueOf(file.getAbsolutePath())));
                }
                throw mlKitException;
            } catch (Throwable th) {
                autoCloseInputStream.close();
                throw th;
            }
        } catch (IOException e3) {
            zza.e("RemoteModelFileManager", "Failed to copy downloaded model file to private folder: ".concat(e3.toString()));
            return null;
        } catch (Throwable th2) {
            while (true) {
                throw th2;
            }
        }
        return this.zzf.moveAllFilesFromPrivateTempToPrivateDestination(file);
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (r4.renameTo(r1) == false) goto L_0x002e;
     */
    @androidx.annotation.WorkerThread
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.io.File zza(@androidx.annotation.NonNull java.io.File r4) throws com.google.mlkit.common.MlKitException {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.mlkit.common.sdkinternal.model.ModelFileHelper r0 = r3.zzh     // Catch:{ all -> 0x002f }
            java.lang.String r1 = r3.zzc     // Catch:{ all -> 0x002f }
            com.google.mlkit.common.sdkinternal.ModelType r2 = r3.zzd     // Catch:{ all -> 0x002f }
            java.io.File r0 = r0.getModelDir(r1, r2)     // Catch:{ all -> 0x002f }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x002f }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x002f }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x002f }
            java.lang.String r2 = "/0"
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x002f }
            r1.<init>(r0)     // Catch:{ all -> 0x002f }
            boolean r0 = r1.exists()     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0026
            monitor-exit(r3)
            return r4
        L_0x0026:
            boolean r0 = r4.renameTo(r1)     // Catch:{ all -> 0x002f }
            monitor-exit(r3)
            if (r0 == 0) goto L_0x002e
            return r1
        L_0x002e:
            return r4
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.zza(java.io.File):java.io.File");
    }

    @WorkerThread
    @Nullable
    public final synchronized String zzb() throws MlKitException {
        return this.zzh.zzb(this.zzc, this.zzd);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        return;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzc(@androidx.annotation.NonNull java.io.File r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            java.io.File r1 = r3.getModelDirUnsafe(r0)     // Catch:{ all -> 0x0025 }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x0025 }
            if (r2 != 0) goto L_0x000d
            goto L_0x002a
        L_0x000d:
            java.io.File[] r1 = r1.listFiles()     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x002a
        L_0x0013:
            int r2 = r1.length     // Catch:{ all -> 0x0025 }
            if (r0 >= r2) goto L_0x002a
            r2 = r1[r0]     // Catch:{ all -> 0x0025 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x0025 }
            if (r2 == 0) goto L_0x0027
            com.google.mlkit.common.sdkinternal.model.ModelFileHelper r0 = r3.zzh     // Catch:{ all -> 0x0025 }
            r0.deleteRecursively(r4)     // Catch:{ all -> 0x0025 }
            monitor-exit(r3)
            return
        L_0x0025:
            r4 = move-exception
            goto L_0x002c
        L_0x0027:
            int r0 = r0 + 1
            goto L_0x0013
        L_0x002a:
            monitor-exit(r3)
            return
        L_0x002c:
            monitor-exit(r3)     // Catch:{ all -> 0x0025 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.zzc(java.io.File):void");
    }

    @WorkerThread
    public final synchronized boolean zzd(@NonNull File file) throws MlKitException {
        File modelDir = this.zzh.getModelDir(this.zzc, this.zzd);
        if (!modelDir.exists()) {
            return false;
        }
        File[] listFiles = modelDir.listFiles();
        boolean z2 = true;
        if (listFiles == null) {
            return true;
        }
        for (File file2 : listFiles) {
            if (!file2.equals(file) && !this.zzh.deleteRecursively(file2)) {
                z2 = false;
            }
        }
        return z2;
    }
}
