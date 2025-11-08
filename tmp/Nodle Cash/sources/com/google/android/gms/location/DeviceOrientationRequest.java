package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzer;
import org.checkerframework.dataflow.qual.Pure;

@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
@SafeParcelable.Reserved({1, 3, 4, 5})
public final class DeviceOrientationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DeviceOrientationRequest> CREATOR = new zzn();
    public static final long OUTPUT_PERIOD_DEFAULT = 20000;
    public static final long OUTPUT_PERIOD_FAST = 5000;
    public static final long OUTPUT_PERIOD_MEDIUM = 10000;
    @SafeParcelable.Field(getter = "getSamplingPeriodMicros", id = 2)
    private final long zza;
    @SafeParcelable.Field(defaultValueUnchecked = "false", getter = "isVelocityEnabled", id = 6)
    private final boolean zzb;

    public static final class Builder {
        private long zza;
        private final boolean zzb;

        public Builder(long j2) {
            this.zzb = false;
            setSamplingPeriodMicros(j2);
        }

        @NonNull
        public DeviceOrientationRequest build() {
            return new DeviceOrientationRequest(this.zza, this.zzb);
        }

        @NonNull
        public Builder setSamplingPeriodMicros(long j2) {
            boolean z2 = false;
            if (j2 >= 0 && j2 < Long.MAX_VALUE) {
                z2 = true;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(j2).length() + 102);
            sb.append("Invalid interval: ");
            sb.append(j2);
            sb.append(" should be greater than or equal to 0. Note: Long.MAX_VALUE is not a valid interval.");
            zzer.zzb(z2, sb.toString());
            this.zza = j2;
            return this;
        }

        public Builder(@NonNull DeviceOrientationRequest deviceOrientationRequest) {
            this.zza = deviceOrientationRequest.zza();
            this.zzb = deviceOrientationRequest.zzb();
        }
    }

    @SafeParcelable.Constructor
    public DeviceOrientationRequest(@SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 6) boolean z2) {
        this.zza = j2;
        this.zzb = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceOrientationRequest)) {
            return false;
        }
        DeviceOrientationRequest deviceOrientationRequest = (DeviceOrientationRequest) obj;
        return this.zza == deviceOrientationRequest.zza && this.zzb == deviceOrientationRequest.zzb;
    }

    @Pure
    public long getSamplingPeriodMicros() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Boolean.valueOf(this.zzb));
    }

    @NonNull
    public String toString() {
        long j2 = this.zza;
        int length = String.valueOf(j2).length();
        String str = true != this.zzb ? "" : ", withVelocity";
        StringBuilder sb = new StringBuilder(str.length() + length + 46 + 1);
        sb.append("DeviceOrientationRequest[samplingPeriodMicros=");
        sb.append(j2);
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getSamplingPeriodMicros());
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ long zza() {
        return this.zza;
    }

    public final /* synthetic */ boolean zzb() {
        return this.zzb;
    }
}
