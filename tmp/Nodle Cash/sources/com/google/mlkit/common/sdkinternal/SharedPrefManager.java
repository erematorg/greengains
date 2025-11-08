package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.Component;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.RemoteModel;
import java.util.UUID;

@KeepForSdk
public class SharedPrefManager {
    @NonNull
    @KeepForSdk
    public static final Component<?> COMPONENT = Component.builder(SharedPrefManager.class).add(Dependency.required((Class<?>) MlKitContext.class)).add(Dependency.required((Class<?>) Context.class)).factory(new zzs()).build();
    @NonNull
    @KeepForSdk
    public static final String PREF_FILE = "com.google.mlkit.internal";
    @NonNull
    protected final Context zza;

    public SharedPrefManager(@NonNull Context context) {
        this.zza = context;
    }

    @NonNull
    @KeepForSdk
    public static SharedPrefManager getInstance(@NonNull MlKitContext mlKitContext) {
        return (SharedPrefManager) mlKitContext.get(SharedPrefManager.class);
    }

    @KeepForSdk
    public synchronized void clearDownloadingModelInfo(@NonNull RemoteModel remoteModel) {
        String downloadingModelHash = getDownloadingModelHash(remoteModel);
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        SharedPreferences.Editor remove = edit.remove("downloading_model_id_" + uniqueModelNameForPersist);
        String uniqueModelNameForPersist2 = remoteModel.getUniqueModelNameForPersist();
        SharedPreferences.Editor remove2 = remove.remove("downloading_model_hash_" + uniqueModelNameForPersist2);
        SharedPreferences.Editor remove3 = remove2.remove("downloading_model_type_" + downloadingModelHash);
        String uniqueModelNameForPersist3 = remoteModel.getUniqueModelNameForPersist();
        SharedPreferences.Editor remove4 = remove3.remove("downloading_begin_time_" + uniqueModelNameForPersist3);
        String uniqueModelNameForPersist4 = remoteModel.getUniqueModelNameForPersist();
        remove4.remove("model_first_use_time_" + uniqueModelNameForPersist4).apply();
    }

    @KeepForSdk
    public synchronized void clearIncompatibleModelInfo(@NonNull RemoteModel remoteModel) {
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        edit.remove("bad_hash_" + uniqueModelNameForPersist).remove("app_version").apply();
    }

    @WorkerThread
    @KeepForSdk
    public synchronized void clearLatestModelHash(@NonNull RemoteModel remoteModel) {
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        edit.remove("current_model_hash_" + uniqueModelNameForPersist).commit();
    }

    @KeepForSdk
    @Nullable
    public synchronized String getDownloadingModelHash(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2;
        String uniqueModelNameForPersist;
        zza2 = zza();
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        return zza2.getString("downloading_model_hash_" + uniqueModelNameForPersist, (String) null);
    }

    @KeepForSdk
    @Nullable
    public synchronized Long getDownloadingModelId(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2 = zza();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        long j2 = zza2.getLong("downloading_model_id_" + uniqueModelNameForPersist, -1);
        if (j2 < 0) {
            return null;
        }
        return Long.valueOf(j2);
    }

    @KeepForSdk
    @Nullable
    public synchronized String getIncompatibleModelHash(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2;
        String uniqueModelNameForPersist;
        zza2 = zza();
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        return zza2.getString("bad_hash_" + uniqueModelNameForPersist, (String) null);
    }

    @KeepForSdk
    @Nullable
    public synchronized String getLatestModelHash(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2;
        String uniqueModelNameForPersist;
        zza2 = zza();
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        return zza2.getString("current_model_hash_" + uniqueModelNameForPersist, (String) null);
    }

    @NonNull
    @KeepForSdk
    public synchronized String getMlSdkInstanceId() {
        String string = zza().getString("ml_sdk_instance_id", (String) null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        zza().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    @KeepForSdk
    public synchronized long getModelDownloadBeginTimeMs(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2;
        String uniqueModelNameForPersist;
        zza2 = zza();
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        return zza2.getLong("downloading_begin_time_" + uniqueModelNameForPersist, 0);
    }

    @KeepForSdk
    public synchronized long getModelFirstUseTimeMs(@NonNull RemoteModel remoteModel) {
        SharedPreferences zza2;
        String uniqueModelNameForPersist;
        zza2 = zza();
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        return zza2.getLong("model_first_use_time_" + uniqueModelNameForPersist, 0);
    }

    @KeepForSdk
    @Nullable
    public synchronized String getPreviousAppVersion() {
        return zza().getString("app_version", (String) null);
    }

    @KeepForSdk
    public synchronized void setDownloadingModelInfo(long j2, @NonNull ModelInfo modelInfo) {
        String modelNameForPersist = modelInfo.getModelNameForPersist();
        String modelHash = modelInfo.getModelHash();
        SharedPreferences.Editor edit = zza().edit();
        SharedPreferences.Editor putString = edit.putString("downloading_model_hash_" + modelNameForPersist, modelHash);
        SharedPreferences.Editor putLong = putString.putLong("downloading_model_id_" + modelNameForPersist, j2);
        putLong.putLong("downloading_begin_time_" + modelNameForPersist, SystemClock.elapsedRealtime()).apply();
    }

    @KeepForSdk
    public synchronized void setIncompatibleModelInfo(@NonNull RemoteModel remoteModel, @NonNull String str, @NonNull String str2) {
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        edit.putString("bad_hash_" + uniqueModelNameForPersist, str).putString("app_version", str2).apply();
    }

    @KeepForSdk
    public synchronized void setLatestModelHash(@NonNull RemoteModel remoteModel, @NonNull String str) {
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        edit.putString("current_model_hash_" + uniqueModelNameForPersist, str).apply();
    }

    @KeepForSdk
    public synchronized void setModelFirstUseTimeMs(@NonNull RemoteModel remoteModel, long j2) {
        SharedPreferences.Editor edit = zza().edit();
        String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        edit.putLong("model_first_use_time_" + uniqueModelNameForPersist, j2).apply();
    }

    @NonNull
    public final SharedPreferences zza() {
        return this.zza.getSharedPreferences(PREF_FILE, 0);
    }

    @Nullable
    public final synchronized String zzb(@NonNull String str, long j2) {
        return zza().getString(String.format("cached_local_model_hash_%1s_%2s", new Object[]{Preconditions.checkNotNull(str), Long.valueOf(j2)}), (String) null);
    }

    public final synchronized void zzc(@NonNull String str, long j2, @NonNull String str2) {
        zza().edit().putString(String.format("cached_local_model_hash_%1s_%2s", new Object[]{Preconditions.checkNotNull(str), Long.valueOf(j2)}), str2).apply();
    }
}
