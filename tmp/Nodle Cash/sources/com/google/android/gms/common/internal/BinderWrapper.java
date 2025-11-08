package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
@KeepForSdk
public final class BinderWrapper implements Parcelable {
    @NonNull
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zzh();
    private final IBinder zza;

    @KeepForSdk
    public BinderWrapper(@NonNull IBinder iBinder) {
        this.zza = iBinder;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeStrongBinder(this.zza);
    }

    public /* synthetic */ BinderWrapper(Parcel parcel, zzi zzi) {
        this.zza = parcel.readStrongBinder();
    }
}
