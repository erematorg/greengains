package com.google.android.gms.location;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zze;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@SafeParcelable.Class(creator = "LocationAvailabilityRequestCreator")
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 1)
    private final boolean zza;
    @SafeParcelable.Field(getter = "getImpersonation", id = 2)
    @Nullable
    private final zze zzb;

    @SafeParcelable.Constructor
    public zzad(@SafeParcelable.Param(id = 1) boolean z2, @SafeParcelable.Param(id = 2) @Nullable zze zze) {
        this.zza = z2;
        this.zzb = zze;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        return this.zza == zzad.zza && Objects.equal(this.zzb, zzad.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza));
    }

    public final String toString() {
        StringBuilder p2 = a.p("LocationAvailabilityRequest[");
        if (this.zza) {
            p2.append("bypass, ");
        }
        if (this.zzb != null) {
            p2.append("impersonation=");
            p2.append(this.zzb);
            p2.append(", ");
        }
        p2.setLength(p2.length() - 2);
        p2.append(AbstractJsonLexerKt.END_LIST);
        return p2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        boolean z2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, z2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
