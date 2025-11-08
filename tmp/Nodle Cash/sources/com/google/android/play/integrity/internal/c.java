package com.google.android.play.integrity.internal;

import A.a;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final ClassLoader f6834a = c.class.getClassLoader();

    private c() {
    }

    public static Parcelable a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void b(Parcel parcel) {
        int dataAvail = parcel.dataAvail();
        if (dataAvail > 0) {
            throw new BadParcelableException(a.k("Parcel data not fully consumed, unread size: ", dataAvail));
        }
    }

    public static void c(Parcel parcel, Parcelable parcelable) {
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
