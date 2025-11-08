package com.google.android.gms.location;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.dataflow.qual.Pure;

@SafeParcelable.Class(creator = "LastLocationRequestCreator")
@SafeParcelable.Reserved({4})
public final class LastLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LastLocationRequest> CREATOR = new zzaa();
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getMaxUpdateAgeMillis", id = 1)
    private final long zza;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.location.Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 2)
    private final int zzb;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 3)
    private final boolean zzc;
    @SafeParcelable.Field(getter = "getImpersonation", id = 5)
    @Nullable
    private final zze zzd;

    public static final class Builder {
        private long zza;
        private int zzb;
        private final boolean zzc;
        @Nullable
        private final zze zzd;

        public Builder() {
            this.zza = Long.MAX_VALUE;
            this.zzb = 0;
            this.zzc = false;
            this.zzd = null;
        }

        @NonNull
        public LastLocationRequest build() {
            return new LastLocationRequest(this.zza, this.zzb, this.zzc, this.zzd);
        }

        @NonNull
        public Builder setGranularity(int i3) {
            zzq.zza(i3);
            this.zzb = i3;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j2) {
            Preconditions.checkArgument(j2 > 0, "maxUpdateAgeMillis must be greater than 0");
            this.zza = j2;
            return this;
        }

        public Builder(@NonNull LastLocationRequest lastLocationRequest) {
            this.zza = lastLocationRequest.getMaxUpdateAgeMillis();
            this.zzb = lastLocationRequest.getGranularity();
            this.zzc = lastLocationRequest.zza();
            this.zzd = lastLocationRequest.zzb();
        }
    }

    @SafeParcelable.Constructor
    public LastLocationRequest(@SafeParcelable.Param(id = 1) long j2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 5) @Nullable zze zze) {
        this.zza = j2;
        this.zzb = i3;
        this.zzc = z2;
        this.zzd = zze;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof LastLocationRequest)) {
            return false;
        }
        LastLocationRequest lastLocationRequest = (LastLocationRequest) obj;
        return this.zza == lastLocationRequest.zza && this.zzb == lastLocationRequest.zzb && this.zzc == lastLocationRequest.zzc && Objects.equal(this.zzd, lastLocationRequest.zzd);
    }

    @Pure
    public int getGranularity() {
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Integer.valueOf(this.zzb), Boolean.valueOf(this.zzc));
    }

    @NonNull
    public String toString() {
        StringBuilder p2 = a.p("LastLocationRequest[");
        if (this.zza != Long.MAX_VALUE) {
            p2.append("maxAge=");
            zzeo.zzc(this.zza, p2);
        }
        if (this.zzb != 0) {
            p2.append(", ");
            p2.append(zzq.zzb(this.zzb));
        }
        if (this.zzc) {
            p2.append(", bypass");
        }
        if (this.zzd != null) {
            p2.append(", impersonation=");
            p2.append(this.zzd);
        }
        p2.append(AbstractJsonLexerKt.END_LIST);
        return p2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 2, getGranularity());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final boolean zza() {
        return this.zzc;
    }

    @Nullable
    @Pure
    public final zze zzb() {
        return this.zzd;
    }
}
