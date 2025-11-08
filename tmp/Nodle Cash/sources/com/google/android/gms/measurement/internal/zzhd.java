package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbz;

public final class zzhd {
    final zzhw zza;

    public zzhd(zznv zznv) {
        this.zza = zznv.zzk();
    }

    @VisibleForTesting
    @WorkerThread
    public final Bundle zza(String str, zzbz zzbz) {
        this.zza.zzl().zzt();
        if (zzbz == null) {
            this.zza.zzj().zzu().zza("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle zza2 = zzbz.zza(bundle);
            if (zza2 != null) {
                return zza2;
            }
            this.zza.zzj().zzg().zza("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e3) {
            this.zza.zzj().zzg().zza("Exception occurred while retrieving the Install Referrer", e3.getMessage());
            return null;
        }
    }

    @VisibleForTesting
    public final boolean zza() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.zza.zza());
            if (packageManager == null) {
                this.zza.zzj().zzp().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
                return false;
            } else if (packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e3) {
            this.zza.zzj().zzp().zza("Failed to retrieve Play Store version for Install Referrer", e3);
            return false;
        }
    }
}
