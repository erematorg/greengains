package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzags;
import com.google.android.gms.internal.p002firebaseauthapi.zzah;

@SafeParcelable.Class(creator = "DefaultOAuthCredentialCreator")
public class zzf extends OAuthCredential {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getProvider", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getIdToken", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getWebSignInCredential", id = 4)
    @Nullable
    private final zzags zzd;
    @SafeParcelable.Field(getter = "getPendingToken", id = 5)
    @Nullable
    private final String zze;
    @SafeParcelable.Field(getter = "getSecret", id = 6)
    @Nullable
    private final String zzf;
    @SafeParcelable.Field(getter = "getRawNonce", id = 7)
    @Nullable
    private final String zzg;

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable zzags zzags, @SafeParcelable.Param(id = 5) @Nullable String str4, @SafeParcelable.Param(id = 6) @Nullable String str5, @SafeParcelable.Param(id = 7) @Nullable String str6) {
        this.zza = zzah.zzb(str);
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzags;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    @Nullable
    public String getAccessToken() {
        return this.zzc;
    }

    @Nullable
    public String getIdToken() {
        return this.zzb;
    }

    public String getProvider() {
        return this.zza;
    }

    @Nullable
    public String getSecret() {
        return this.zzf;
    }

    public String getSignInMethod() {
        return this.zza;
    }

    public void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AuthCredential zza() {
        return new zzf(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public static zzf zza(zzags zzags) {
        Preconditions.checkNotNull(zzags, "Must specify a non-null webSignInCredential");
        return new zzf((String) null, (String) null, (String) null, zzags, (String) null, (String) null, (String) null);
    }

    public static zzf zza(String str, String str2, String str3) {
        return zza(str, str2, str3, (String) null, (String) null);
    }

    public static zzf zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzf(str, str2, str3, (zzags) null, str4, str5, (String) null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzf zza(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzf(str, str2, str3, (zzags) null, (String) null, (String) null, str4);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzags zza(zzf zzf2, @Nullable String str) {
        Preconditions.checkNotNull(zzf2);
        zzags zzags = zzf2.zzd;
        if (zzags != null) {
            return zzags;
        }
        return new zzags(zzf2.getIdToken(), zzf2.getAccessToken(), zzf2.getProvider(), (String) null, zzf2.getSecret(), (String) null, str, zzf2.zze, zzf2.zzg);
    }
}
