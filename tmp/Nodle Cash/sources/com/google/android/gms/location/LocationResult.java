package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "LocationResultCreator")
@SafeParcelable.Reserved({1000})
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzag();
    static final List zza = Collections.emptyList();
    @SafeParcelable.Field(defaultValueUnchecked = "LocationResult.DEFAULT_LOCATIONS", getter = "getLocations", id = 1)
    private final List zzb;

    @SafeParcelable.Constructor
    public LocationResult(@SafeParcelable.Param(id = 1) List list) {
        this.zzb = list;
    }

    @NonNull
    public static LocationResult create(@NonNull List<Location> list) {
        if (list == null) {
            list = zza;
        }
        return new LocationResult(list);
    }

    @Nullable
    public static LocationResult extractResult(@NonNull Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        LocationResult locationResult = (LocationResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES", CREATOR);
        return locationResult == null ? (LocationResult) intent.getParcelableExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") : locationResult;
    }

    public static boolean hasResult(@NonNull Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") || intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.Nullable java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.google.android.gms.location.LocationResult
            r1 = 0
            if (r0 == 0) goto L_0x008c
            com.google.android.gms.location.LocationResult r8 = (com.google.android.gms.location.LocationResult) r8
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L_0x0016
            java.util.List r7 = r7.zzb
            java.util.List r8 = r8.zzb
            boolean r7 = r7.equals(r8)
            return r7
        L_0x0016:
            java.util.List r0 = r7.zzb
            int r0 = r0.size()
            java.util.List r2 = r8.zzb
            int r2 = r2.size()
            if (r0 == r2) goto L_0x0025
            return r1
        L_0x0025:
            java.util.List r7 = r7.zzb
            java.util.Iterator r7 = r7.iterator()
            java.util.List r8 = r8.zzb
            java.util.Iterator r8 = r8.iterator()
        L_0x0031:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x008a
            java.lang.Object r0 = r7.next()
            android.location.Location r0 = (android.location.Location) r0
            java.lang.Object r2 = r8.next()
            android.location.Location r2 = (android.location.Location) r2
            double r3 = r0.getLatitude()
            double r5 = r2.getLatitude()
            int r3 = java.lang.Double.compare(r3, r5)
            if (r3 == 0) goto L_0x0052
            return r1
        L_0x0052:
            double r3 = r0.getLongitude()
            double r5 = r2.getLongitude()
            int r3 = java.lang.Double.compare(r3, r5)
            if (r3 == 0) goto L_0x0061
            return r1
        L_0x0061:
            long r3 = r0.getTime()
            long r5 = r2.getTime()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x006e
            return r1
        L_0x006e:
            long r3 = r0.getElapsedRealtimeNanos()
            long r5 = r2.getElapsedRealtimeNanos()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x007b
            return r1
        L_0x007b:
            java.lang.String r0 = r0.getProvider()
            java.lang.String r2 = r2.getProvider()
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 != 0) goto L_0x0031
            return r1
        L_0x008a:
            r7 = 1
            return r7
        L_0x008c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationResult.equals(java.lang.Object):boolean");
    }

    @Nullable
    public Location getLastLocation() {
        int size = this.zzb.size();
        if (size == 0) {
            return null;
        }
        return (Location) this.zzb.get(size - 1);
    }

    @NonNull
    public List<Location> getLocations() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("LocationResult");
        int i3 = zzak.zza;
        List<Location> list = this.zzb;
        sb.ensureCapacity(list.size() * 100);
        sb.append("[");
        boolean z2 = false;
        for (Location zza2 : list) {
            zzak.zza(zza2, sb);
            sb.append(", ");
            z2 = true;
        }
        if (z2) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
