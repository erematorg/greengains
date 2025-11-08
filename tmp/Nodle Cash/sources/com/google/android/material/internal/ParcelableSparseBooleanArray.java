package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = new Parcelable.Creator<ParcelableSparseBooleanArray>() {
        @NonNull
        public ParcelableSparseBooleanArray createFromParcel(@NonNull Parcel parcel) {
            int readInt = parcel.readInt();
            ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(readInt);
            int[] iArr = new int[readInt];
            boolean[] zArr = new boolean[readInt];
            parcel.readIntArray(iArr);
            parcel.readBooleanArray(zArr);
            for (int i3 = 0; i3 < readInt; i3++) {
                parcelableSparseBooleanArray.put(iArr[i3], zArr[i3]);
            }
            return parcelableSparseBooleanArray;
        }

        @NonNull
        public ParcelableSparseBooleanArray[] newArray(int i3) {
            return new ParcelableSparseBooleanArray[i3];
        }
    };

    public ParcelableSparseBooleanArray() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int[] iArr = new int[size()];
        boolean[] zArr = new boolean[size()];
        for (int i4 = 0; i4 < size(); i4++) {
            iArr[i4] = keyAt(i4);
            zArr[i4] = valueAt(i4);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeBooleanArray(zArr);
    }

    public ParcelableSparseBooleanArray(int i3) {
        super(i3);
    }

    public ParcelableSparseBooleanArray(@NonNull SparseBooleanArray sparseBooleanArray) {
        super(sparseBooleanArray.size());
        for (int i3 = 0; i3 < sparseBooleanArray.size(); i3++) {
            put(sparseBooleanArray.keyAt(i3), sparseBooleanArray.valueAt(i3));
        }
    }
}
