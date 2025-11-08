package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import java.io.File;
import java.util.concurrent.Executor;

@KeepForSdk
public abstract class LegacyModelMigrator {
    @NonNull
    @KeepForSdk
    protected final ModelFileHelper modelFileHelper;
    private final TaskCompletionSource zza = new TaskCompletionSource();
    private final Context zzb;
    private final Executor zzc;

    @KeepForSdk
    public LegacyModelMigrator(@NonNull Context context, @NonNull ModelFileHelper modelFileHelper2) {
        this.zzb = context;
        this.modelFileHelper = modelFileHelper2;
        this.zzc = MLTaskExecutor.workerThreadExecutor();
    }

    @KeepForSdk
    public static void deleteIfEmpty(@NonNull File file) {
        File[] listFiles = file.listFiles();
        if ((listFiles == null || listFiles.length == 0) && !file.delete()) {
            Log.e("MlKitLegacyMigration", "Error deleting model directory ".concat(String.valueOf(file)));
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isValidFirebasePersistenceKey(@androidx.annotation.NonNull java.lang.String r3) {
        /*
            java.lang.String r0 = "\\+"
            r1 = -1
            java.lang.String[] r3 = r3.split(r0, r1)
            int r0 = r3.length
            r1 = 2
            r2 = 0
            if (r0 == r1) goto L_0x000d
            return r2
        L_0x000d:
            r0 = r3[r2]     // Catch:{ IllegalArgumentException -> 0x0019 }
            com.google.android.gms.common.util.Base64Utils.decodeUrlSafeNoPadding(r0)     // Catch:{ IllegalArgumentException -> 0x0019 }
            r0 = 1
            r3 = r3[r0]     // Catch:{  }
            com.google.android.gms.common.util.Base64Utils.decodeUrlSafeNoPadding(r3)     // Catch:{  }
            return r0
        L_0x0019:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.LegacyModelMigrator.isValidFirebasePersistenceKey(java.lang.String):boolean");
    }

    @VisibleForTesting
    @KeepForSdk
    public static void migrateFile(@NonNull File file, @NonNull File file2) {
        if (file.exists()) {
            if (!file2.exists() && !file.renameTo(file2)) {
                String valueOf = String.valueOf(file);
                String valueOf2 = String.valueOf(file2);
                Log.e("MlKitLegacyMigration", "Error moving model file " + valueOf + " to " + valueOf2);
            }
            if (file.exists() && !file.delete()) {
                Log.e("MlKitLegacyMigration", "Error deleting model file ".concat(String.valueOf(file)));
            }
        }
    }

    @VisibleForTesting
    @NonNull
    @KeepForSdk
    public abstract String getLegacyModelDirName();

    @VisibleForTesting
    @NonNull
    @KeepForSdk
    public File getLegacyRootDir() {
        Context context = this.zzb;
        return new File(context.getNoBackupFilesDir(), getLegacyModelDirName());
    }

    @NonNull
    @KeepForSdk
    public Task<Void> getMigrationTask() {
        return this.zza.getTask();
    }

    @KeepForSdk
    public abstract void migrateAllModelDirs(@NonNull File file);

    @KeepForSdk
    public void start() {
        this.zzc.execute(new zza(this));
    }

    public final /* synthetic */ void zza() {
        File legacyRootDir = getLegacyRootDir();
        File[] listFiles = legacyRootDir.listFiles();
        if (listFiles != null) {
            for (File migrateAllModelDirs : listFiles) {
                migrateAllModelDirs(migrateAllModelDirs);
            }
            deleteIfEmpty(legacyRootDir);
        }
        this.zza.setResult(null);
    }
}
