package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "StampStyleCreator")
@SafeParcelable.Reserved({1})
public class StampStyle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StampStyle> CREATOR = new zzw();
    @SafeParcelable.Field(getter = "getWrappedStampBinder", id = 2, type = "android.os.IBinder")
    @NonNull
    protected final BitmapDescriptor zza;

    public static abstract class Builder<T extends Builder<T>> {
        BitmapDescriptor zza;

        public abstract T self();

        public T stamp(BitmapDescriptor bitmapDescriptor) {
            this.zza = bitmapDescriptor;
            return self();
        }
    }

    @SafeParcelable.Constructor
    public StampStyle(@SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
    }

    @NonNull
    public BitmapDescriptor getStamp() {
        return this.zza;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        BitmapDescriptor bitmapDescriptor = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public StampStyle(@NonNull BitmapDescriptor bitmapDescriptor) {
        this.zza = bitmapDescriptor;
    }
}
