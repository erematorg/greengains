package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

final class zzaw implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return zzay.zza(parcel.readString());
        } catch (zzax e3) {
            throw new RuntimeException(e3);
        }
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzay[i3];
    }
}
