package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "MultiFactorInfoListCreator")
public final class zzbg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbg> CREATOR = new zzbj();
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 1)
    private final List<PhoneMultiFactorInfo> zza;
    @SafeParcelable.Field(getter = "getTotpMultiFactorInfoList", id = 2)
    private final List<TotpMultiFactorInfo> zzb;

    @SafeParcelable.Constructor
    public zzbg(@SafeParcelable.Param(id = 1) List<PhoneMultiFactorInfo> list, @SafeParcelable.Param(id = 2) List<TotpMultiFactorInfo> list2) {
        this.zza = list == null ? new ArrayList<>() : list;
        this.zzb = list2 == null ? new ArrayList<>() : list2;
    }

    public static zzbg zza(List<MultiFactorInfo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MultiFactorInfo next : list) {
            if (next instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) next);
            } else if (next instanceof TotpMultiFactorInfo) {
                arrayList2.add((TotpMultiFactorInfo) next);
            }
        }
        return new zzbg(arrayList, arrayList2);
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<MultiFactorInfo> zza() {
        ArrayList arrayList = new ArrayList();
        for (PhoneMultiFactorInfo add : this.zza) {
            arrayList.add(add);
        }
        for (TotpMultiFactorInfo add2 : this.zzb) {
            arrayList.add(add2);
        }
        return arrayList;
    }
}
