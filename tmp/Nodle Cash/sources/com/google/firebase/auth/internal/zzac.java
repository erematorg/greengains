package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.android.gms.internal.p002firebaseauthapi.zzaft;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzf;
import io.reactivex.annotations.SchedulerSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SafeParcelable.Class(creator = "DefaultFirebaseUserCreator")
public class zzac extends FirebaseUser {
    public static final Parcelable.Creator<zzac> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "getCachedTokenState", id = 1)
    private zzafm zza;
    @SafeParcelable.Field(getter = "getDefaultAuthUserInfo", id = 2)
    private zzy zzb;
    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private String zzc;
    @SafeParcelable.Field(getter = "getUserType", id = 4)
    private String zzd;
    @SafeParcelable.Field(getter = "getUserInfos", id = 5)
    private List<zzy> zze;
    @SafeParcelable.Field(getter = "getProviders", id = 6)
    private List<String> zzf;
    @SafeParcelable.Field(getter = "getCurrentVersion", id = 7)
    private String zzg;
    @SafeParcelable.Field(getter = "isAnonymous", id = 8)
    private Boolean zzh;
    @SafeParcelable.Field(getter = "getMetadata", id = 9)
    private zzae zzi;
    @SafeParcelable.Field(getter = "isNewUser", id = 10)
    private boolean zzj;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 11)
    private zzf zzk;
    @SafeParcelable.Field(getter = "getMultiFactorInfoList", id = 12)
    private zzbg zzl;
    @SafeParcelable.Field(getter = "getEnrolledPasskeys", id = 13)
    private List<zzaft> zzm;

    public zzac(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = "2";
        zza(list);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    @Nullable
    public String getEmail() {
        return this.zzb.getEmail();
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public /* synthetic */ MultiFactor getMultiFactor() {
        return new zzag(this);
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    @NonNull
    public String getProviderId() {
        return this.zzb.getProviderId();
    }

    @Nullable
    public String getTenantId() {
        Map map;
        zzafm zzafm = this.zza;
        if (zzafm == null || zzafm.zzc() == null || (map = (Map) zzbf.zza(this.zza.zzc()).getClaims().get("firebase")) == null) {
            return null;
        }
        return (String) map.get("tenant");
    }

    @NonNull
    public String getUid() {
        return this.zzb.getUid();
    }

    public boolean isAnonymous() {
        GetTokenResult zza2;
        Boolean bool = this.zzh;
        if (bool == null || bool.booleanValue()) {
            zzafm zzafm = this.zza;
            String str = "";
            if (!(zzafm == null || (zza2 = zzbf.zza(zzafm.zzc())) == null)) {
                str = zza2.getSignInProvider();
            }
            boolean z2 = true;
            if (getProviderData().size() > 1 || (str != null && str.equals(SchedulerSupport.CUSTOM))) {
                z2 = false;
            }
            this.zzh = Boolean.valueOf(z2);
        }
        return this.zzh.booleanValue();
    }

    public boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzc(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, zzg(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i3, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i3, false);
        SafeParcelWriter.writeTypedList(parcel, 13, zzf(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final FirebaseApp zza() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final /* synthetic */ FirebaseUser zzb() {
        this.zzh = Boolean.FALSE;
        return this;
    }

    @NonNull
    public final zzafm zzc() {
        return this.zza;
    }

    @NonNull
    public final String zzd() {
        return zzc().zzc();
    }

    @NonNull
    public final String zze() {
        return this.zza.zzf();
    }

    public final List<zzaft> zzf() {
        return this.zzm;
    }

    @Nullable
    public final List<String> zzg() {
        return this.zzf;
    }

    @Nullable
    public final zzf zzh() {
        return this.zzk;
    }

    @Nullable
    public final List<MultiFactorInfo> zzi() {
        zzbg zzbg = this.zzl;
        return zzbg != null ? zzbg.zza() : new ArrayList();
    }

    public final List<zzy> zzj() {
        return this.zze;
    }

    public final boolean zzk() {
        return this.zzj;
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzac zzac = new zzac(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzac) {
            zzac zzac2 = (zzac) firebaseUser;
            zzac.zzg = zzac2.zzg;
            zzac.zzd = zzac2.zzd;
            zzac.zzi = (zzae) zzac2.getMetadata();
        } else {
            zzac.zzi = null;
        }
        if (firebaseUser.zzc() != null) {
            zzac.zza(firebaseUser.zzc());
        }
        if (!firebaseUser.isAnonymous()) {
            zzac.zzb();
        }
        return zzac;
    }

    public final void zzb(List<zzaft> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.zzm = list;
    }

    public final void zzc(List<MultiFactorInfo> list) {
        this.zzl = zzbg.zza(list);
    }

    @SafeParcelable.Constructor
    public zzac(@SafeParcelable.Param(id = 1) zzafm zzafm, @SafeParcelable.Param(id = 2) zzy zzy, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) List<zzy> list, @SafeParcelable.Param(id = 6) List<String> list2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) Boolean bool, @SafeParcelable.Param(id = 9) zzae zzae, @SafeParcelable.Param(id = 10) boolean z2, @SafeParcelable.Param(id = 11) zzf zzf2, @SafeParcelable.Param(id = 12) zzbg zzbg, @SafeParcelable.Param(id = 13) List<zzaft> list3) {
        this.zza = zzafm;
        this.zzb = zzy;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzae;
        this.zzj = z2;
        this.zzk = zzf2;
        this.zzl = zzbg;
        this.zzm = list3;
    }

    @NonNull
    public final synchronized FirebaseUser zza(List<? extends UserInfo> list) {
        try {
            Preconditions.checkNotNull(list);
            this.zze = new ArrayList(list.size());
            this.zzf = new ArrayList(list.size());
            for (int i3 = 0; i3 < list.size(); i3++) {
                UserInfo userInfo = (UserInfo) list.get(i3);
                if (userInfo.getProviderId().equals("firebase")) {
                    this.zzb = (zzy) userInfo;
                } else {
                    this.zzf.add(userInfo.getProviderId());
                }
                this.zze.add((zzy) userInfo);
            }
            if (this.zzb == null) {
                this.zzb = this.zze.get(0);
            }
        } finally {
            while (true) {
            }
        }
        return this;
    }

    public final zzac zza(String str) {
        this.zzg = str;
        return this;
    }

    public final void zza(zzafm zzafm) {
        this.zza = (zzafm) Preconditions.checkNotNull(zzafm);
    }

    public final void zza(@Nullable zzf zzf2) {
        this.zzk = zzf2;
    }

    public final void zza(boolean z2) {
        this.zzj = z2;
    }

    public final void zza(zzae zzae) {
        this.zzi = zzae;
    }
}
