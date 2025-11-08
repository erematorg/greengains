package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;

public final class zzft {
    private final zzrb zza;
    private final Boolean zzb = null;
    private final Boolean zzc;
    private final zzqk zzd;
    private final zzvz zze;
    private final zzcs zzf;
    private final zzcs zzg;

    public /* synthetic */ zzft(zzfr zzfr, zzfs zzfs) {
        this.zza = zzfr.zza;
        this.zzc = zzfr.zzb;
        this.zzd = null;
        this.zze = zzfr.zzc;
        this.zzf = zzfr.zzd;
        this.zzg = zzfr.zze;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzft)) {
            return false;
        }
        zzft zzft = (zzft) obj;
        return Objects.equal(this.zza, zzft.zza) && Objects.equal((Object) null, (Object) null) && Objects.equal(this.zzc, zzft.zzc) && Objects.equal((Object) null, (Object) null) && Objects.equal(this.zze, zzft.zze) && Objects.equal(this.zzf, zzft.zzf) && Objects.equal(this.zzg, zzft.zzg);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, this.zzg);
    }

    @zzfe(zza = 6)
    @Nullable
    public final zzcs zza() {
        return this.zzf;
    }

    @zzfe(zza = 7)
    @Nullable
    public final zzcs zzb() {
        return this.zzg;
    }

    @zzfe(zza = 1)
    @Nullable
    public final zzrb zzc() {
        return this.zza;
    }

    @zzfe(zza = 5)
    @Nullable
    public final zzvz zzd() {
        return this.zze;
    }

    @zzfe(zza = 3)
    @Nullable
    public final Boolean zze() {
        return this.zzc;
    }
}
