package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzym;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import com.google.firebase.auth.zzf;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorResolverCreator")
public final class zzai extends MultiFactorResolver {
    public static final Parcelable.Creator<zzai> CREATOR = new zzak();
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 1)
    private final List<PhoneMultiFactorInfo> zza;
    @SafeParcelable.Field(getter = "getSession", id = 2)
    private final zzaj zzb;
    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private final String zzc;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 4)
    public final zzf zzd;
    @SafeParcelable.Field(getter = "getReauthUser", id = 5)
    private final zzac zze;
    @SafeParcelable.Field(getter = "getTotpMultiFactorInfoList", id = 6)
    private final List<TotpMultiFactorInfo> zzf;

    @SafeParcelable.Constructor
    public zzai(@SafeParcelable.Param(id = 1) List<PhoneMultiFactorInfo> list, @SafeParcelable.Param(id = 2) zzaj zzaj, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable zzf zzf2, @SafeParcelable.Param(id = 5) @Nullable zzac zzac, @SafeParcelable.Param(id = 6) List<TotpMultiFactorInfo> list2) {
        this.zza = (List) Preconditions.checkNotNull(list);
        this.zzb = (zzaj) Preconditions.checkNotNull(zzaj);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zzf2;
        this.zze = zzac;
        this.zzf = (List) Preconditions.checkNotNull(list2);
    }

    public final FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzc));
    }

    public final List<MultiFactorInfo> getHints() {
        ArrayList arrayList = new ArrayList();
        for (PhoneMultiFactorInfo add : this.zza) {
            arrayList.add(add);
        }
        for (TotpMultiFactorInfo add2 : this.zzf) {
            arrayList.add(add2);
        }
        return arrayList;
    }

    public final MultiFactorSession getSession() {
        return this.zzb;
    }

    public final Task<AuthResult> resolveSignIn(MultiFactorAssertion multiFactorAssertion) {
        return getFirebaseAuth().zza(multiFactorAssertion, this.zzb, (FirebaseUser) this.zze).continueWithTask(new zzah(this));
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSession(), i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i3, false);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzai zza(zzym zzym, FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        List<MultiFactorInfo> zzc2 = zzym.zzc();
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo next : zzc2) {
            if (next instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) next);
            }
        }
        List<MultiFactorInfo> zzc3 = zzym.zzc();
        ArrayList arrayList2 = new ArrayList();
        for (MultiFactorInfo next2 : zzc3) {
            if (next2 instanceof TotpMultiFactorInfo) {
                arrayList2.add((TotpMultiFactorInfo) next2);
            }
        }
        return new zzai(arrayList, zzaj.zza(zzym.zzc(), zzym.zzb()), firebaseAuth.getApp().getName(), zzym.zza(), (zzac) firebaseUser, arrayList2);
    }
}
