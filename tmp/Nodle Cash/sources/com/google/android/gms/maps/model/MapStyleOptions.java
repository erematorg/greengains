package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;

@SafeParcelable.Class(creator = "MapStyleOptionsCreator")
@SafeParcelable.Reserved({1})
public final class MapStyleOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzo();
    @SafeParcelable.Field(getter = "getJson", id = 2)
    private final String zza;

    @SafeParcelable.Constructor
    public MapStyleOptions(@SafeParcelable.Param(id = 2) @NonNull String str) {
        Preconditions.checkNotNull(str, "json must not be null");
        this.zza = str;
    }

    @NonNull
    public static MapStyleOptions loadRawResourceStyle(@NonNull Context context, int i3) throws Resources.NotFoundException {
        try {
            return new MapStyleOptions(new String(IOUtils.readInputStreamFully(context.getResources().openRawResource(i3)), "UTF-8"));
        } catch (IOException e3) {
            String obj = e3.toString();
            throw new Resources.NotFoundException("Failed to read resource " + i3 + ": " + obj);
        }
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, str, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
