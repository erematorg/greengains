package com.google.android.odml.image;

import org.apache.commons.text.StringSubstitutor;

final class zzc extends ImageProperties {
    private final int zza;
    private final int zzb;

    public /* synthetic */ zzc(int i3, int i4, zza zza2) {
        this.zza = i3;
        this.zzb = i4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageProperties) {
            ImageProperties imageProperties = (ImageProperties) obj;
            return this.zza == imageProperties.getImageFormat() && this.zzb == imageProperties.getStorageType();
        }
    }

    public final int getImageFormat() {
        return this.zza;
    }

    public final int getStorageType() {
        return this.zzb;
    }

    public final int hashCode() {
        return this.zzb ^ ((this.zza ^ 1000003) * 1000003);
    }

    public final String toString() {
        int i3 = this.zza;
        int i4 = this.zzb;
        StringBuilder sb = new StringBuilder(65);
        sb.append("ImageProperties{imageFormat=");
        sb.append(i3);
        sb.append(", storageType=");
        sb.append(i4);
        sb.append(StringSubstitutor.DEFAULT_VAR_END);
        return sb.toString();
    }
}
