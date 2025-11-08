package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

@SafeParcelable.Class(creator = "SignInCredentialCreator")
@Deprecated
public final class SignInCredential extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zbt();
    @SafeParcelable.Field(getter = "getId", id = 1)
    private final String zba;
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    @Nullable
    private final String zbb;
    @SafeParcelable.Field(getter = "getGivenName", id = 3)
    @Nullable
    private final String zbc;
    @SafeParcelable.Field(getter = "getFamilyName", id = 4)
    @Nullable
    private final String zbd;
    @SafeParcelable.Field(getter = "getProfilePictureUri", id = 5)
    @Nullable
    private final Uri zbe;
    @SafeParcelable.Field(getter = "getPassword", id = 6)
    @Nullable
    private final String zbf;
    @SafeParcelable.Field(getter = "getGoogleIdToken", id = 7)
    @Nullable
    private final String zbg;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 8)
    @Nullable
    private final String zbh;
    @SafeParcelable.Field(getter = "getPublicKeyCredential", id = 9)
    @Nullable
    private final PublicKeyCredential zbi;

    @SafeParcelable.Constructor
    public SignInCredential(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 5) @Nullable Uri uri, @SafeParcelable.Param(id = 6) @Nullable String str5, @SafeParcelable.Param(id = 7) @Nullable String str6, @SafeParcelable.Param(id = 8) @Nullable String str7, @SafeParcelable.Param(id = 9) @Nullable PublicKeyCredential publicKeyCredential) {
        this.zba = (String) Preconditions.checkNotNull(str);
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = str4;
        this.zbe = uri;
        this.zbf = str5;
        this.zbg = str6;
        this.zbh = str7;
        this.zbi = publicKeyCredential;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SignInCredential)) {
            return false;
        }
        SignInCredential signInCredential = (SignInCredential) obj;
        return Objects.equal(this.zba, signInCredential.zba) && Objects.equal(this.zbb, signInCredential.zbb) && Objects.equal(this.zbc, signInCredential.zbc) && Objects.equal(this.zbd, signInCredential.zbd) && Objects.equal(this.zbe, signInCredential.zbe) && Objects.equal(this.zbf, signInCredential.zbf) && Objects.equal(this.zbg, signInCredential.zbg) && Objects.equal(this.zbh, signInCredential.zbh) && Objects.equal(this.zbi, signInCredential.zbi);
    }

    @Nullable
    public String getDisplayName() {
        return this.zbb;
    }

    @Nullable
    public String getFamilyName() {
        return this.zbd;
    }

    @Nullable
    public String getGivenName() {
        return this.zbc;
    }

    @Nullable
    public String getGoogleIdToken() {
        return this.zbg;
    }

    @NonNull
    public String getId() {
        return this.zba;
    }

    @Nullable
    public String getPassword() {
        return this.zbf;
    }

    @Deprecated
    @Nullable
    public String getPhoneNumber() {
        return this.zbh;
    }

    @Nullable
    public Uri getProfilePictureUri() {
        return this.zbe;
    }

    @Nullable
    public PublicKeyCredential getPublicKeyCredential() {
        return this.zbi;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getProfilePictureUri(), i3, false);
        SafeParcelWriter.writeString(parcel, 6, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 7, getGoogleIdToken(), false);
        SafeParcelWriter.writeString(parcel, 8, getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getPublicKeyCredential(), i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
