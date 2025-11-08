package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "TotpInfoCreator")
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagq  reason: invalid package */
public final class zzagq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzagq> CREATOR = new zzagt();

    public final void writeToParcel(Parcel parcel, int i3) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
