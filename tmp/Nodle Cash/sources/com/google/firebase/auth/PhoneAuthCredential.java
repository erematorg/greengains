package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PhoneAuthCredentialCreator")
public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    @NonNull
    public static final Parcelable.Creator<PhoneAuthCredential> CREATOR = new zzap();
    @SafeParcelable.Field(getter = "getSessionInfo", id = 1)
    @Nullable
    private String zza;
    @SafeParcelable.Field(getter = "getSmsCode", id = 2)
    @Nullable
    private String zzb;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 4)
    @Nullable
    private String zzc;
    @SafeParcelable.Field(getter = "getAutoCreate", id = 5)
    private boolean zzd;
    @SafeParcelable.Field(getter = "getTemporaryProof", id = 6)
    @Nullable
    private String zze;

    @SafeParcelable.Constructor
    public PhoneAuthCredential(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable String str3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) @Nullable String str4) {
        Preconditions.checkArgument((!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)), "Cannot create PhoneAuthCredential without either sessionInfo + smsCode or temporary proof + phoneNumber.");
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = z2;
        this.zze = str4;
    }

    @NonNull
    public static PhoneAuthCredential zzb(@NonNull String str, @NonNull String str2) {
        return new PhoneAuthCredential((String) null, (String) null, str, true, str2);
    }

    @NonNull
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new PhoneAuthCredential(this.zza, getSmsCode(), this.zzc, this.zzd, this.zze);
    }

    @NonNull
    public String getProvider() {
        return "phone";
    }

    @NonNull
    public String getSignInMethod() {
        return "phone";
    }

    @Nullable
    public String getSmsCode() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, getSmsCode(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final AuthCredential zza() {
        return (PhoneAuthCredential) clone();
    }

    @Nullable
    public final String zzc() {
        return this.zza;
    }

    @Nullable
    public final String zzd() {
        return this.zze;
    }

    public final boolean zze() {
        return this.zzd;
    }

    @NonNull
    public static PhoneAuthCredential zza(@NonNull String str, @NonNull String str2) {
        return new PhoneAuthCredential(str, str2, (String) null, true, (String) null);
    }

    @Nullable
    public final String zzb() {
        return this.zzc;
    }

    @NonNull
    public final PhoneAuthCredential zza(boolean z2) {
        this.zzd = false;
        return this;
    }
}
