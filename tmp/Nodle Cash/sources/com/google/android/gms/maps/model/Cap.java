package com.google.android.gms.maps.model;

import A.a;
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

@SafeParcelable.Class(creator = "CapCreator")
@SafeParcelable.Reserved({1})
public class Cap extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String zza = "Cap";
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int zzb;
    @SafeParcelable.Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    @Nullable
    private final BitmapDescriptor zzc;
    @SafeParcelable.Field(getter = "getBitmapRefWidth", id = 4)
    @Nullable
    private final Float zzd;

    public Cap(int i3) {
        this(i3, (BitmapDescriptor) null, (Float) null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.zzb == cap.zzb && Objects.equal(this.zzc, cap.zzc) && Objects.equal(this.zzd, cap.zzd);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), this.zzc, this.zzd);
    }

    @NonNull
    public String toString() {
        return a.m(new StringBuilder("[Cap: type="), "]", this.zzb);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int i4 = this.zzb;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i4);
        BitmapDescriptor bitmapDescriptor = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, bitmapDescriptor == null ? null : bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Cap zza() {
        int i3 = this.zzb;
        if (i3 == 0) {
            return new ButtCap();
        }
        boolean z2 = true;
        if (i3 == 1) {
            return new SquareCap();
        }
        if (i3 == 2) {
            return new RoundCap();
        }
        if (i3 != 3) {
            Log.w(zza, "Unknown Cap type: " + i3);
            return this;
        }
        Preconditions.checkState(this.zzc != null, "bitmapDescriptor must not be null");
        if (this.zzd == null) {
            z2 = false;
        }
        Preconditions.checkState(z2, "bitmapRefWidth must not be null");
        return new CustomCap(this.zzc, this.zzd.floatValue());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Cap(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 2) int r2, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 3) @androidx.annotation.Nullable android.os.IBinder r3, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 4) @androidx.annotation.Nullable java.lang.Float r4) {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000e
        L_0x0004:
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = new com.google.android.gms.maps.model.BitmapDescriptor
            r0.<init>(r3)
            r3 = r0
        L_0x000e:
            r1.<init>((int) r2, (com.google.android.gms.maps.model.BitmapDescriptor) r3, (java.lang.Float) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.<init>(int, android.os.IBinder, java.lang.Float):void");
    }

    private Cap(int i3, @Nullable BitmapDescriptor bitmapDescriptor, @Nullable Float f2) {
        boolean z2 = true;
        boolean z3 = f2 != null && f2.floatValue() > 0.0f;
        if (i3 == 3) {
            z2 = (bitmapDescriptor == null || !z3) ? false : z2;
            i3 = 3;
        }
        Preconditions.checkArgument(z2, "Invalid Cap: type=" + i3 + " bitmapDescriptor=" + bitmapDescriptor + " bitmapRefWidth=" + f2);
        this.zzb = i3;
        this.zzc = bitmapDescriptor;
        this.zzd = f2;
    }

    public Cap(@NonNull BitmapDescriptor bitmapDescriptor, float f2) {
        this(3, bitmapDescriptor, Float.valueOf(f2));
    }
}
