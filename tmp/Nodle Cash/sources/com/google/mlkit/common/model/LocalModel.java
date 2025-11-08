package com.google.mlkit.common.model;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;

public class LocalModel {
    @Nullable
    private final String zza;
    @Nullable
    private final String zzb;
    @Nullable
    private final Uri zzc;
    private final boolean zzd;

    public static class Builder {
        @Nullable
        private String zza = null;
        @Nullable
        private String zzb = null;
        @Nullable
        private Uri zzc = null;
        private boolean zzd = false;

        @NonNull
        public LocalModel build() {
            String str = this.zza;
            boolean z2 = true;
            if (!((str != null && this.zzb == null && this.zzc == null) || ((str == null && this.zzb != null && this.zzc == null) || (str == null && this.zzb == null && this.zzc != null)))) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "Set one of filePath, assetFilePath and URI.");
            return new LocalModel(this.zza, this.zzb, this.zzc, this.zzd, (zzc) null);
        }

        @NonNull
        public Builder setAbsoluteFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            boolean z2 = false;
            if (this.zzb == null && this.zzc == null && !this.zzd) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setAbsoluteManifestFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Manifest file path can not be empty");
            boolean z2 = false;
            if (this.zzb == null && this.zzc == null && (this.zza == null || this.zzd)) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.zza = str;
            this.zzd = true;
            return this;
        }

        @NonNull
        public Builder setAssetFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            boolean z2 = false;
            if (this.zza == null && this.zzc == null && !this.zzd) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setAssetManifestFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Manifest file path can not be empty");
            boolean z2 = false;
            if (this.zza == null && this.zzc == null && (this.zzb == null || this.zzd)) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.zzb = str;
            this.zzd = true;
            return this;
        }

        @NonNull
        public Builder setUri(@NonNull Uri uri) {
            boolean z2 = false;
            if (this.zza == null && this.zzb == null) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.zzc = uri;
            return this;
        }
    }

    public /* synthetic */ LocalModel(String str, String str2, Uri uri, boolean z2, zzc zzc2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = uri;
        this.zzd = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalModel)) {
            return false;
        }
        LocalModel localModel = (LocalModel) obj;
        return Objects.equal(this.zza, localModel.zza) && Objects.equal(this.zzb, localModel.zzb) && Objects.equal(this.zzc, localModel.zzc) && this.zzd == localModel.zzd;
    }

    @KeepForSdk
    @Nullable
    public String getAbsoluteFilePath() {
        return this.zza;
    }

    @KeepForSdk
    @Nullable
    public String getAssetFilePath() {
        return this.zzb;
    }

    @KeepForSdk
    @Nullable
    public Uri getUri() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, Boolean.valueOf(this.zzd));
    }

    @KeepForSdk
    public boolean isManifestFile() {
        return this.zzd;
    }

    @NonNull
    public String toString() {
        zzq zza2 = zzr.zza(this);
        zza2.zza("absoluteFilePath", this.zza);
        zza2.zza("assetFilePath", this.zzb);
        zza2.zza("uri", this.zzc);
        zza2.zzb("isManifestFile", this.zzd);
        return zza2.toString();
    }
}
