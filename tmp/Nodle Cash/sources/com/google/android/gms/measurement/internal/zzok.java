package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserAttributeParcelCreator")
public final class zzok extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzok> CREATOR = new zzon();
    @SafeParcelable.Field(id = 2)
    public final String zza;
    @SafeParcelable.Field(id = 3)
    public final long zzb;
    @SafeParcelable.Field(id = 4)
    @Nullable
    public final Long zzc;
    @SafeParcelable.Field(id = 6)
    @Nullable
    public final String zzd;
    @SafeParcelable.Field(id = 7)
    public final String zze;
    @SafeParcelable.Field(id = 8)
    @Nullable
    public final Double zzf;
    @SafeParcelable.Field(id = 1)
    private final int zzg;

    public zzok(zzom zzom) {
        this(zzom.zzc, zzom.zzd, zzom.zze, zzom.zzb);
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, (Float) null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 7, this.zze, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final Object zza() {
        Long l2 = this.zzc;
        if (l2 != null) {
            return l2;
        }
        Double d2 = this.zzf;
        if (d2 != null) {
            return d2;
        }
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        return null;
    }

    public zzok(String str, long j2, @Nullable Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.zzg = 2;
        this.zza = str;
        this.zzb = j2;
        this.zze = str2;
        if (obj == null) {
            this.zzc = null;
            this.zzf = null;
            this.zzd = null;
        } else if (obj instanceof Long) {
            this.zzc = (Long) obj;
            this.zzf = null;
            this.zzd = null;
        } else if (obj instanceof String) {
            this.zzc = null;
            this.zzf = null;
            this.zzd = (String) obj;
        } else if (obj instanceof Double) {
            this.zzc = null;
            this.zzf = (Double) obj;
            this.zzd = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    @SafeParcelable.Constructor
    public zzok(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) @Nullable Long l2, @SafeParcelable.Param(id = 5) Float f2, @SafeParcelable.Param(id = 6) @Nullable String str2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) @Nullable Double d2) {
        this.zzg = i3;
        this.zza = str;
        this.zzb = j2;
        this.zzc = l2;
        if (i3 == 1) {
            this.zzf = f2 != null ? Double.valueOf(f2.doubleValue()) : null;
        } else {
            this.zzf = d2;
        }
        this.zzd = str2;
        this.zze = str3;
    }
}
