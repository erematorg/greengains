package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LocationReceiverCreator")
@SafeParcelable.Reserved({5})
public final class zzee extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzee> CREATOR = new zzef();
    @SafeParcelable.Field(getter = "getType", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getOldBinderReceiver", id = 2)
    @Nullable
    private final IBinder zzb;
    @SafeParcelable.Field(getter = "getBinderReceiver", id = 3)
    @Nullable
    private final IBinder zzc;
    @SafeParcelable.Field(getter = "getPendingIntentReceiver", id = 4)
    @Nullable
    private final PendingIntent zzd;
    @SafeParcelable.Field(getter = "getListenerId", id = 6)
    @Nullable
    private final String zze;

    @SafeParcelable.Constructor
    public zzee(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @Nullable IBinder iBinder, @SafeParcelable.Param(id = 3) @Nullable IBinder iBinder2, @SafeParcelable.Param(id = 4) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(id = 6) @Nullable String str) {
        this.zza = i3;
        this.zzb = iBinder;
        this.zzc = iBinder2;
        this.zzd = pendingIntent;
        this.zze = str;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zza(@androidx.annotation.Nullable android.os.IInterface r7, com.google.android.gms.location.zzz r8, java.lang.String r9) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            if (r7 != 0) goto L_0x0005
            r7 = 0
        L_0x0005:
            r2 = r7
            r1 = 1
            r4 = 0
            r0 = r6
            r3 = r8
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zza(android.os.IInterface, com.google.android.gms.location.zzz, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zzb(@androidx.annotation.Nullable android.os.IInterface r7, com.google.android.gms.location.zzw r8, java.lang.String r9) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            if (r7 != 0) goto L_0x0005
            r7 = 0
        L_0x0005:
            r2 = r7
            r1 = 2
            r4 = 0
            r0 = r6
            r3 = r8
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzb(android.os.IInterface, com.google.android.gms.location.zzw, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    public static zzee zzc(PendingIntent pendingIntent) {
        return new zzee(3, (IBinder) null, (IBinder) null, pendingIntent, (String) null);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.location.zzee zzd(com.google.android.gms.internal.location.zzz r7) {
        /*
            com.google.android.gms.internal.location.zzee r6 = new com.google.android.gms.internal.location.zzee
            r4 = 0
            r5 = 0
            r1 = 4
            r2 = 0
            r0 = r6
            r3 = r7
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzd(com.google.android.gms.internal.location.zzz):com.google.android.gms.internal.location.zzee");
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
