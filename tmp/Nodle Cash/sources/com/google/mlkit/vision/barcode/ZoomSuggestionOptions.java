package com.google.mlkit.vision.barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;

public class ZoomSuggestionOptions {
    private final ZoomCallback zza;
    private final float zzb;

    public static class Builder {
        private final ZoomCallback zza;
        private float zzb;

        public Builder(@NonNull ZoomCallback zoomCallback) {
            this.zza = zoomCallback;
        }

        @NonNull
        public ZoomSuggestionOptions build() {
            return new ZoomSuggestionOptions(this.zza, this.zzb, (zzb) null);
        }

        @NonNull
        public Builder setMaxSupportedZoomRatio(float f2) {
            this.zzb = f2;
            return this;
        }
    }

    public interface ZoomCallback {
        boolean setZoom(float f2);
    }

    public /* synthetic */ ZoomSuggestionOptions(ZoomCallback zoomCallback, float f2, zzb zzb2) {
        this.zza = zoomCallback;
        this.zzb = f2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoomSuggestionOptions)) {
            return false;
        }
        ZoomSuggestionOptions zoomSuggestionOptions = (ZoomSuggestionOptions) obj;
        return Objects.equal(this.zza, zoomSuggestionOptions.zza) && this.zzb == zoomSuggestionOptions.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, Float.valueOf(this.zzb));
    }

    public final float zza() {
        return this.zzb;
    }

    @NonNull
    public final ZoomCallback zzb() {
        return this.zza;
    }
}
