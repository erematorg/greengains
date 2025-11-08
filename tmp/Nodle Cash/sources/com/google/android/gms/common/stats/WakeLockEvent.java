package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.state.b;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import javax.annotation.Nullable;

@KeepForSdk
@SafeParcelable.Class(creator = "WakeLockEventCreator")
@Deprecated
public final class WakeLockEvent extends StatsEvent {
    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();
    @SafeParcelable.VersionField(id = 1)
    final int zza;
    @SafeParcelable.Field(getter = "getTimeMillis", id = 2)
    private final long zzb;
    @SafeParcelable.Field(getter = "getEventType", id = 11)
    private final int zzc;
    @SafeParcelable.Field(getter = "getWakeLockName", id = 4)
    private final String zzd;
    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", id = 10)
    private final String zze;
    @SafeParcelable.Field(getter = "getCodePackage", id = 17)
    private final String zzf;
    @SafeParcelable.Field(getter = "getWakeLockType", id = 5)
    private final int zzg;
    @SafeParcelable.Field(getter = "getCallingPackages", id = 6)
    @Nullable
    private final List zzh;
    @SafeParcelable.Field(getter = "getEventKey", id = 12)
    private final String zzi;
    @SafeParcelable.Field(getter = "getElapsedRealtime", id = 8)
    private final long zzj;
    @SafeParcelable.Field(getter = "getDeviceState", id = 14)
    private final int zzk;
    @SafeParcelable.Field(getter = "getHostPackage", id = 13)
    private final String zzl;
    @SafeParcelable.Field(getter = "getBeginPowerPercentage", id = 15)
    private final float zzm;
    @SafeParcelable.Field(getter = "getTimeout", id = 16)
    private final long zzn;
    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", id = 18)
    private final boolean zzo;

    @SafeParcelable.Constructor
    public WakeLockEvent(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 11) int i4, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) int i5, @SafeParcelable.Param(id = 6) @Nullable List list, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 8) long j3, @SafeParcelable.Param(id = 14) int i6, @SafeParcelable.Param(id = 10) String str3, @SafeParcelable.Param(id = 13) String str4, @SafeParcelable.Param(id = 15) float f2, @SafeParcelable.Param(id = 16) long j4, @SafeParcelable.Param(id = 17) String str5, @SafeParcelable.Param(id = 18) boolean z2) {
        this.zza = i3;
        this.zzb = j2;
        this.zzc = i4;
        this.zzd = str;
        this.zze = str3;
        this.zzf = str5;
        this.zzg = i5;
        this.zzh = list;
        this.zzi = str2;
        this.zzj = j3;
        this.zzk = i6;
        this.zzl = str4;
        this.zzm = f2;
        this.zzn = j4;
        this.zzo = z2;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzg);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzj);
        SafeParcelWriter.writeString(parcel, 10, this.zze, false);
        SafeParcelWriter.writeInt(parcel, 11, this.zzc);
        SafeParcelWriter.writeString(parcel, 12, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeInt(parcel, 14, this.zzk);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzm);
        SafeParcelWriter.writeLong(parcel, 16, this.zzn);
        SafeParcelWriter.writeString(parcel, 17, this.zzf, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zzc;
    }

    public final long zzb() {
        return this.zzb;
    }

    @NonNull
    public final String zzc() {
        List list = this.zzh;
        String str = "";
        String join = list == null ? str : TextUtils.join(",", list);
        int i3 = this.zzk;
        String str2 = this.zze;
        String str3 = this.zzl;
        float f2 = this.zzm;
        String str4 = this.zzf;
        int i4 = this.zzg;
        String str5 = this.zzd;
        boolean z2 = this.zzo;
        StringBuilder x2 = a.x("\t", str5, "\t", i4, "\t");
        b.v(x2, join, "\t", i3, "\t");
        if (str2 == null) {
            str2 = str;
        }
        x2.append(str2);
        x2.append("\t");
        if (str3 == null) {
            str3 = str;
        }
        x2.append(str3);
        x2.append("\t");
        x2.append(f2);
        x2.append("\t");
        if (str4 != null) {
            str = str4;
        }
        x2.append(str);
        x2.append("\t");
        x2.append(z2);
        return x2.toString();
    }
}
