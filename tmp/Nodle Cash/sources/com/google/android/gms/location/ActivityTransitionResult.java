package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "ActivityTransitionResultCreator")
@SafeParcelable.Reserved({1000})
public class ActivityTransitionResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransitionResult> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getTransitionEvents", id = 1)
    private final List zza;
    @SafeParcelable.Field(getter = "getExtras", id = 2)
    @Nullable
    private Bundle zzb;

    public ActivityTransitionResult(@SafeParcelable.Param(id = 1) @NonNull List<ActivityTransitionEvent> list) {
        this.zzb = null;
        Preconditions.checkNotNull(list, "transitionEvents list can't be null.");
        if (!list.isEmpty()) {
            for (int i3 = 1; i3 < list.size(); i3++) {
                int i4 = i3 - 1;
                Preconditions.checkArgument(list.get(i3).getElapsedRealTimeNanos() >= list.get(i4).getElapsedRealTimeNanos(), "Transition out of order: ts1=%d, ts2=%d", Long.valueOf(list.get(i3).getElapsedRealTimeNanos()), Long.valueOf(list.get(i4).getElapsedRealTimeNanos()));
            }
        }
        this.zza = Collections.unmodifiableList(list);
    }

    @Nullable
    public static ActivityTransitionResult extractResult(@NonNull Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (ActivityTransitionResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", CREATOR);
    }

    public static boolean hasResult(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.zza.equals(((ActivityTransitionResult) obj).zza);
    }

    @NonNull
    public List<ActivityTransitionEvent> getTransitionEvents() {
        return this.zza;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getTransitionEvents(), false);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ActivityTransitionResult(@SafeParcelable.Param(id = 1) @NonNull List list, @SafeParcelable.Param(id = 2) @Nullable Bundle bundle) {
        this(list);
        this.zzb = bundle;
    }
}
