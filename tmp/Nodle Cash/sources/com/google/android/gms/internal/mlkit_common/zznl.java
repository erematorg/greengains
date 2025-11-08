package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;

public final class zznl {
    private final zznh zza;
    private final zznj zzb = null;
    private final zznj zzc = null;
    private final Boolean zzd = null;

    public /* synthetic */ zznl(zzni zzni, zznk zznk) {
        this.zza = zzni.zza;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zznl) && Objects.equal(this.zza, ((zznl) obj).zza) && Objects.equal((Object) null, (Object) null) && Objects.equal((Object) null, (Object) null) && Objects.equal((Object) null, (Object) null);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    @zzbc(zza = 1)
    @Nullable
    public final zznh zza() {
        return this.zza;
    }
}
