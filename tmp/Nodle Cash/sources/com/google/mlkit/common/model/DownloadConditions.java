package com.google.mlkit.common.model;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.Objects;

public class DownloadConditions {
    private final boolean zza;
    private final boolean zzb;

    public static class Builder {
        private boolean zza = false;
        private boolean zzb = false;

        @NonNull
        public DownloadConditions build() {
            return new DownloadConditions(this.zza, this.zzb, (zzb) null);
        }

        @RequiresApi(24)
        @TargetApi(24)
        @NonNull
        public Builder requireCharging() {
            this.zza = true;
            return this;
        }

        @NonNull
        public Builder requireWifi() {
            this.zzb = true;
            return this;
        }
    }

    public /* synthetic */ DownloadConditions(boolean z2, boolean z3, zzb zzb2) {
        this.zza = z2;
        this.zzb = z3;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadConditions)) {
            return false;
        }
        DownloadConditions downloadConditions = (DownloadConditions) obj;
        return this.zza == downloadConditions.zza && this.zzb == downloadConditions.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza), Boolean.valueOf(this.zzb));
    }

    public boolean isChargingRequired() {
        return this.zza;
    }

    public boolean isWifiRequired() {
        return this.zzb;
    }
}
