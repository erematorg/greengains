package com.google.mlkit.vision.barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.mlkit.vision.barcode.common.Barcode;
import java.util.concurrent.Executor;

public class BarcodeScannerOptions {
    private final int zza;
    private final boolean zzb;
    @Nullable
    private final Executor zzc;
    @Nullable
    private final ZoomSuggestionOptions zzd;

    public static class Builder {
        private int zza = 0;
        private boolean zzb;
        @Nullable
        private Executor zzc;
        @Nullable
        private ZoomSuggestionOptions zzd;

        @NonNull
        public BarcodeScannerOptions build() {
            return new BarcodeScannerOptions(this.zza, this.zzb, this.zzc, this.zzd, (zza) null);
        }

        @NonNull
        public Builder enableAllPotentialBarcodes() {
            this.zzb = true;
            return this;
        }

        @NonNull
        public Builder setBarcodeFormats(@Barcode.BarcodeFormat int i3, @Barcode.BarcodeFormat @NonNull int... iArr) {
            this.zza = i3;
            if (iArr != null) {
                for (int i4 : iArr) {
                    this.zza = i4 | this.zza;
                }
            }
            return this;
        }

        @NonNull
        public Builder setExecutor(@NonNull Executor executor) {
            this.zzc = executor;
            return this;
        }

        @NonNull
        public Builder setZoomSuggestionOptions(@NonNull ZoomSuggestionOptions zoomSuggestionOptions) {
            this.zzd = zoomSuggestionOptions;
            return this;
        }
    }

    public /* synthetic */ BarcodeScannerOptions(int i3, boolean z2, Executor executor, ZoomSuggestionOptions zoomSuggestionOptions, zza zza2) {
        this.zza = i3;
        this.zzb = z2;
        this.zzc = executor;
        this.zzd = zoomSuggestionOptions;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BarcodeScannerOptions)) {
            return false;
        }
        BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
        return this.zza == barcodeScannerOptions.zza && this.zzb == barcodeScannerOptions.zzb && Objects.equal(this.zzc, barcodeScannerOptions.zzc) && Objects.equal(this.zzd, barcodeScannerOptions.zzd);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Boolean.valueOf(this.zzb), this.zzc, this.zzd);
    }

    public final int zza() {
        return this.zza;
    }

    @Nullable
    public final ZoomSuggestionOptions zzb() {
        return this.zzd;
    }

    @Nullable
    public final Executor zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzb;
    }
}
