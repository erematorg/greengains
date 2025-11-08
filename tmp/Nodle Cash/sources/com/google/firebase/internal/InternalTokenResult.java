package com.google.firebase.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import org.apache.xerces.impl.xs.SchemaSymbols;

@KeepForSdk
public class InternalTokenResult {
    @Nullable
    private String zza;

    @KeepForSdk
    public InternalTokenResult(@Nullable String str) {
        this.zza = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof InternalTokenResult)) {
            return false;
        }
        return Objects.equal(this.zza, ((InternalTokenResult) obj).zza);
    }

    @KeepForSdk
    @Nullable
    public String getToken() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(SchemaSymbols.ATTVAL_TOKEN, this.zza).toString();
    }
}
