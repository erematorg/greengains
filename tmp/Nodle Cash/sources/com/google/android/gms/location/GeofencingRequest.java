package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzek;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "GeofencingRequestCreator")
@SafeParcelable.Reserved({3, 1000})
public class GeofencingRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzp();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @SafeParcelable.Field(getter = "getParcelableGeofences", id = 1)
    private final List zza;
    @InitialTrigger
    @SafeParcelable.Field(getter = "getInitialTrigger", id = 2)
    private final int zzb;
    @SafeParcelable.Field(getter = "getAttributionTag", id = 4)
    @Nullable
    private final String zzc;

    public static final class Builder {
        private final List zza = new ArrayList();
        @InitialTrigger
        private int zzb = 5;

        @NonNull
        public Builder addGeofence(@NonNull Geofence geofence) {
            Preconditions.checkArgument(geofence instanceof zzek, "Geofence must be created using Geofence.Builder.");
            this.zza.add((zzek) geofence);
            return this;
        }

        @NonNull
        public Builder addGeofences(@NonNull List<? extends Geofence> list) {
            for (Geofence addGeofence : list) {
                addGeofence(addGeofence);
            }
            return this;
        }

        @NonNull
        public GeofencingRequest build() {
            Preconditions.checkArgument(!this.zza.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(new ArrayList(this.zza), this.zzb, (String) null);
        }

        @NonNull
        public Builder setInitialTrigger(@InitialTrigger int i3) {
            this.zzb = i3 & 7;
            return this;
        }
    }

    public @interface InitialTrigger {
    }

    @SafeParcelable.Constructor
    public GeofencingRequest(@SafeParcelable.Param(id = 1) List list, @InitialTrigger @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 4) @Nullable String str) {
        this.zza = list;
        this.zzb = i3;
        this.zzc = str;
    }

    @NonNull
    public List<Geofence> getGeofences() {
        return new ArrayList(this.zza);
    }

    @InitialTrigger
    public int getInitialTrigger() {
        return this.zzb;
    }

    @NonNull
    public String toString() {
        String valueOf = String.valueOf(this.zza);
        int length = valueOf.length();
        int i3 = this.zzb;
        StringBuilder sb = new StringBuilder(length + 45 + String.valueOf(i3).length() + 1);
        sb.append("GeofencingRequest[geofences=");
        sb.append(valueOf);
        sb.append(", initialTrigger=");
        sb.append(i3);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
