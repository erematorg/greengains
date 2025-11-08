package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzq();
    private static final String zza = "PatternItem";
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int zzb;
    @SafeParcelable.Field(getter = "getLength", id = 3)
    @Nullable
    private final Float zzc;

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) @Nullable Float f2) {
        boolean z2 = true;
        if (i3 != 1 && (f2 == null || f2.floatValue() < 0.0f)) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Invalid PatternItem: type=" + i3 + " length=" + f2);
        this.zzb = i3;
        this.zzc = f2;
    }

    @Nullable
    public static List zza(@Nullable List list) {
        PatternItem dash;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            PatternItem patternItem = (PatternItem) it.next();
            if (patternItem == null) {
                patternItem = null;
            } else {
                int i3 = patternItem.zzb;
                boolean z2 = false;
                if (i3 == 0) {
                    if (patternItem.zzc != null) {
                        z2 = true;
                    }
                    Preconditions.checkState(z2, "length must not be null.");
                    dash = new Dash(patternItem.zzc.floatValue());
                } else if (i3 == 1) {
                    patternItem = new Dot();
                } else if (i3 != 2) {
                    Log.w(zza, "Unknown PatternItem type: " + i3);
                } else {
                    if (patternItem.zzc != null) {
                        z2 = true;
                    }
                    Preconditions.checkState(z2, "length must not be null.");
                    dash = new Gap(patternItem.zzc.floatValue());
                }
                patternItem = dash;
            }
            arrayList.add(patternItem);
        }
        return arrayList;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.zzb == patternItem.zzb && Objects.equal(this.zzc, patternItem.zzc);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), this.zzc);
    }

    @NonNull
    public String toString() {
        return "[PatternItem: type=" + this.zzb + " length=" + this.zzc + "]";
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int i4 = this.zzb;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i4);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
