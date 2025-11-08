package com.google.android.gms.location;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.dataflow.qual.Pure;

@SafeParcelable.Class(creator = "CurrentLocationRequestCreator")
@SafeParcelable.Reserved({8})
public final class CurrentLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CurrentLocationRequest> CREATOR = new zzj();
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getMaxUpdateAgeMillis", id = 1)
    private final long zza;
    @SafeParcelable.Field(defaultValueUnchecked = "Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 2)
    private final int zzb;
    @SafeParcelable.Field(defaultValueUnchecked = "Priority.PRIORITY_BALANCED_POWER_ACCURACY", getter = "getPriority", id = 3)
    private final int zzc;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getDurationMillis", id = 4)
    private final long zzd;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 5)
    private final boolean zze;
    @SafeParcelable.Field(defaultValueUnchecked = "ThrottleBehavior.THROTTLE_BACKGROUND", getter = "getThrottleBehavior", id = 7)
    private final int zzf;
    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.WorkSource()", getter = "getWorkSource", id = 6)
    private final WorkSource zzg;
    @SafeParcelable.Field(getter = "getImpersonation", id = 9)
    @Nullable
    private final zze zzh;

    public static final class Builder {
        private long zza;
        private int zzb;
        private int zzc;
        private long zzd;
        private final boolean zze;
        private final int zzf;
        @Nullable
        private final WorkSource zzg;
        @Nullable
        private final zze zzh;

        public Builder() {
            this.zza = 10000;
            this.zzb = 0;
            this.zzc = 102;
            this.zzd = Long.MAX_VALUE;
            this.zze = false;
            this.zzf = 0;
            this.zzg = null;
            this.zzh = null;
        }

        @NonNull
        public CurrentLocationRequest build() {
            return new CurrentLocationRequest(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, new WorkSource(this.zzg), this.zzh);
        }

        @NonNull
        public Builder setDurationMillis(long j2) {
            Preconditions.checkArgument(j2 > 0, "durationMillis must be greater than 0");
            this.zzd = j2;
            return this;
        }

        @NonNull
        public Builder setGranularity(int i3) {
            zzq.zza(i3);
            this.zzb = i3;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j2) {
            Preconditions.checkArgument(j2 >= 0, "maxUpdateAgeMillis must be greater than or equal to 0");
            this.zza = j2;
            return this;
        }

        @NonNull
        public Builder setPriority(int i3) {
            zzan.zza(i3);
            this.zzc = i3;
            return this;
        }

        public Builder(@NonNull CurrentLocationRequest currentLocationRequest) {
            this.zza = currentLocationRequest.getMaxUpdateAgeMillis();
            this.zzb = currentLocationRequest.getGranularity();
            this.zzc = currentLocationRequest.getPriority();
            this.zzd = currentLocationRequest.getDurationMillis();
            this.zze = currentLocationRequest.zza();
            this.zzf = currentLocationRequest.zzb();
            this.zzg = new WorkSource(currentLocationRequest.zzc());
            this.zzh = currentLocationRequest.zzd();
        }
    }

    @SafeParcelable.Constructor
    public CurrentLocationRequest(@SafeParcelable.Param(id = 1) long j2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) int i4, @SafeParcelable.Param(id = 4) long j3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 7) int i5, @SafeParcelable.Param(id = 6) WorkSource workSource, @SafeParcelable.Param(id = 9) @Nullable zze zze2) {
        this.zza = j2;
        this.zzb = i3;
        this.zzc = i4;
        this.zzd = j3;
        this.zze = z2;
        this.zzf = i5;
        this.zzg = workSource;
        this.zzh = zze2;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CurrentLocationRequest)) {
            return false;
        }
        CurrentLocationRequest currentLocationRequest = (CurrentLocationRequest) obj;
        return this.zza == currentLocationRequest.zza && this.zzb == currentLocationRequest.zzb && this.zzc == currentLocationRequest.zzc && this.zzd == currentLocationRequest.zzd && this.zze == currentLocationRequest.zze && this.zzf == currentLocationRequest.zzf && Objects.equal(this.zzg, currentLocationRequest.zzg) && Objects.equal(this.zzh, currentLocationRequest.zzh);
    }

    @Pure
    public long getDurationMillis() {
        return this.zzd;
    }

    @Pure
    public int getGranularity() {
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.zza;
    }

    @Pure
    public int getPriority() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Long.valueOf(this.zzd));
    }

    @NonNull
    public String toString() {
        StringBuilder p2 = a.p("CurrentLocationRequest[");
        p2.append(zzan.zzb(this.zzc));
        if (this.zza != Long.MAX_VALUE) {
            p2.append(", maxAge=");
            zzeo.zzc(this.zza, p2);
        }
        if (this.zzd != Long.MAX_VALUE) {
            p2.append(", duration=");
            p2.append(this.zzd);
            p2.append("ms");
        }
        if (this.zzb != 0) {
            p2.append(", ");
            p2.append(zzq.zzb(this.zzb));
        }
        if (this.zze) {
            p2.append(", bypass");
        }
        if (this.zzf != 0) {
            p2.append(", ");
            p2.append(zzar.zzb(this.zzf));
        }
        if (!WorkSourceUtil.isEmpty(this.zzg)) {
            p2.append(", workSource=");
            p2.append(this.zzg);
        }
        if (this.zzh != null) {
            p2.append(", impersonation=");
            p2.append(this.zzh);
        }
        p2.append(AbstractJsonLexerKt.END_LIST);
        return p2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 2, getGranularity());
        SafeParcelWriter.writeInt(parcel, 3, getPriority());
        SafeParcelWriter.writeLong(parcel, 4, getDurationMillis());
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzg, i3, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzh, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final boolean zza() {
        return this.zze;
    }

    @Pure
    public final int zzb() {
        return this.zzf;
    }

    @NonNull
    @Pure
    public final WorkSource zzc() {
        return this.zzg;
    }

    @Nullable
    @Pure
    public final zze zzd() {
        return this.zzh;
    }
}
