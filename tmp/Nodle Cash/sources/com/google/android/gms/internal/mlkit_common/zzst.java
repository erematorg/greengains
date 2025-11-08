package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

public final class zzst {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    @WorkerThread
    public static zznc zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzsj zzsj) {
        ModelType zzb = zzsj.zzb();
        String modelHash = remoteModel.getModelHash();
        zzni zzni = new zzni();
        zznd zznd = new zznd();
        zznd.zzc(remoteModel.getModelNameForBackend());
        zznd.zzd(zznf.CLOUD);
        zznd.zza(zzu.zzb(modelHash));
        int ordinal = zzb.ordinal();
        zznd.zzb(ordinal != 2 ? ordinal != 4 ? ordinal != 5 ? zzne.TYPE_UNKNOWN : zzne.BASE_DIGITAL_INK : zzne.CUSTOM : zzne.BASE_TRANSLATE);
        zzni.zzb(zznd.zzg());
        zznl zzc = zzni.zzc();
        zzmz zzmz = new zzmz();
        zzmz.zzd(zzsj.zzc());
        zzmz.zzc(zzsj.zzd());
        zzmz.zzb(Long.valueOf((long) zzsj.zza()));
        zzmz.zzf(zzc);
        if (zzsj.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zzmz.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzsj.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zzmz.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zzmz.zzi();
    }
}
