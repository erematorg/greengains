package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EmailAuthCredentialCreator")
public class EmailAuthCredential extends AuthCredential {
    @NonNull
    public static final Parcelable.Creator<EmailAuthCredential> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getEmail", id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getPassword", id = 2)
    @Nullable
    private String zzb;
    @SafeParcelable.Field(getter = "getSignInLink", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getCachedState", id = 4)
    @Nullable
    private String zzd;
    @SafeParcelable.Field(getter = "isForLinking", id = 5)
    private boolean zze;

    public EmailAuthCredential(String str, String str2) {
        this(str, str2, (String) null, (String) null, false);
    }

    @NonNull
    public String getProvider() {
        return "password";
    }

    @NonNull
    public String getSignInMethod() {
        return !TextUtils.isEmpty(this.zzb) ? "password" : EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final AuthCredential zza() {
        return new EmailAuthCredential(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }

    @Nullable
    public final String zzb() {
        return this.zzd;
    }

    @NonNull
    public final String zzc() {
        return this.zza;
    }

    @Nullable
    public final String zzd() {
        return this.zzb;
    }

    @Nullable
    public final String zze() {
        return this.zzc;
    }

    public final boolean zzf() {
        return !TextUtils.isEmpty(this.zzc);
    }

    public final boolean zzg() {
        return this.zze;
    }

    @SafeParcelable.Constructor
    public EmailAuthCredential(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 5) boolean z2) {
        this.zza = Preconditions.checkNotEmpty(str);
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.zzb = str2;
            this.zzc = str3;
            this.zzd = str4;
            this.zze = z2;
            return;
        }
        throw new IllegalArgumentException("Cannot create an EmailAuthCredential without a password or emailLink.");
    }

    @NonNull
    public final EmailAuthCredential zza(@NonNull FirebaseUser firebaseUser) {
        this.zzd = firebaseUser.zze();
        this.zze = true;
        return this;
    }

    public static boolean zza(@NonNull String str) {
        ActionCodeUrl parseLink;
        if (!TextUtils.isEmpty(str) && (parseLink = ActionCodeUrl.parseLink(str)) != null && parseLink.getOperation() == 4) {
            return true;
        }
        return false;
    }
}
