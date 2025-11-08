package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.browser.trusted.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorSessionCreator")
public final class zzaj extends MultiFactorSession {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzam();
    @SafeParcelable.Field(getter = "getIdToken", id = 1)
    @Nullable
    private String zza;
    @SafeParcelable.Field(getter = "getPendingCredential", id = 2)
    @Nullable
    private String zzb;
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 3)
    @Nullable
    private List<PhoneMultiFactorInfo> zzc;
    @SafeParcelable.Field(getter = "getTotpMultiFactorInfoList", id = 4)
    @Nullable
    private List<TotpMultiFactorInfo> zzd;
    @SafeParcelable.Field(getter = "getFirebaseUser", id = 5)
    @Nullable
    private zzac zze;

    private zzaj() {
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzac zza() {
        return this.zze;
    }

    @Nullable
    public final String zzb() {
        return this.zza;
    }

    @Nullable
    public final String zzc() {
        return this.zzb;
    }

    public final boolean zzd() {
        return this.zza != null;
    }

    @SafeParcelable.Constructor
    public zzaj(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) List<PhoneMultiFactorInfo> list, @SafeParcelable.Param(id = 4) List<TotpMultiFactorInfo> list2, @SafeParcelable.Param(id = 5) zzac zzac) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
        this.zzd = list2;
        this.zze = zzac;
    }

    public static zzaj zza(String str, @Nullable zzac zzac) {
        Preconditions.checkNotEmpty(str);
        zzaj zzaj = new zzaj();
        zzaj.zza = str;
        zzaj.zze = zzac;
        return zzaj;
    }

    public static zzaj zza(List<MultiFactorInfo> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzaj zzaj = new zzaj();
        zzaj.zzc = new ArrayList();
        zzaj.zzd = new ArrayList();
        for (MultiFactorInfo next : list) {
            if (next instanceof PhoneMultiFactorInfo) {
                zzaj.zzc.add((PhoneMultiFactorInfo) next);
            } else if (next instanceof TotpMultiFactorInfo) {
                zzaj.zzd.add((TotpMultiFactorInfo) next);
            } else {
                throw new IllegalArgumentException(c.a("MultiFactorInfo must be either PhoneMultiFactorInfo or TotpMultiFactorInfo. The factorId of this MultiFactorInfo: ", next.getFactorId()));
            }
        }
        zzaj.zzb = str;
        return zzaj;
    }
}
