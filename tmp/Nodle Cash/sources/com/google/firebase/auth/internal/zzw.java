package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzf;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultAuthResultCreator")
public final class zzw implements AuthResult {
    public static final Parcelable.Creator<zzw> CREATOR = new zzv();
    @SafeParcelable.Field(getter = "getUser", id = 1)
    @NonNull
    private zzac zza;
    @SafeParcelable.Field(getter = "getAdditionalUserInfo", id = 2)
    @Nullable
    private zzu zzb;
    @SafeParcelable.Field(getter = "getOAuthCredential", id = 3)
    @Nullable
    private zzf zzc;

    public zzw(zzac zzac) {
        zzac zzac2 = (zzac) Preconditions.checkNotNull(zzac);
        this.zza = zzac2;
        List<zzy> zzj = zzac2.zzj();
        this.zzb = null;
        for (int i3 = 0; i3 < zzj.size(); i3++) {
            if (!TextUtils.isEmpty(zzj.get(i3).zza())) {
                this.zzb = new zzu(zzj.get(i3).getProviderId(), zzj.get(i3).zza(), zzac.zzk());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzu(zzac.zzk());
        }
        this.zzc = zzac.zzh();
    }

    public final int describeContents() {
        return 0;
    }

    @Nullable
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    @Nullable
    public final AuthCredential getCredential() {
        return this.zzc;
    }

    @Nullable
    public final FirebaseUser getUser() {
        return this.zza;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzw(@SafeParcelable.Param(id = 1) @NonNull zzac zzac, @SafeParcelable.Param(id = 2) @Nullable zzu zzu, @SafeParcelable.Param(id = 3) @Nullable zzf zzf) {
        this.zza = zzac;
        this.zzb = zzu;
        this.zzc = zzf;
    }
}
