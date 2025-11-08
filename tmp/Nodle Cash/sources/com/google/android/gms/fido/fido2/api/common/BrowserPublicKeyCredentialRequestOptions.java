package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@SafeParcelable.Class(creator = "BrowserPublicKeyCredentialRequestOptionsCreator")
@SafeParcelable.Reserved({1})
public class BrowserPublicKeyCredentialRequestOptions extends BrowserRequestOptions {
    @NonNull
    public static final Parcelable.Creator<BrowserPublicKeyCredentialRequestOptions> CREATOR = new zzo();
    @SafeParcelable.Field(getter = "getPublicKeyCredentialRequestOptions", id = 2)
    @NonNull
    private final PublicKeyCredentialRequestOptions zza;
    @SafeParcelable.Field(getter = "getOrigin", id = 3)
    @NonNull
    private final Uri zzb;
    @SafeParcelable.Field(getter = "getClientDataHash", id = 4)
    @Nullable
    private final byte[] zzc;

    public static final class Builder {
        private PublicKeyCredentialRequestOptions zza;
        private Uri zzb;
        private byte[] zzc;

        @NonNull
        public BrowserPublicKeyCredentialRequestOptions build() {
            return new BrowserPublicKeyCredentialRequestOptions(this.zza, this.zzb, this.zzc);
        }

        @NonNull
        public Builder setClientDataHash(@NonNull byte[] bArr) {
            byte[] unused = BrowserPublicKeyCredentialRequestOptions.zzd(bArr);
            this.zzc = bArr;
            return this;
        }

        @NonNull
        public Builder setOrigin(@NonNull Uri uri) {
            Uri unused = BrowserPublicKeyCredentialRequestOptions.zzc(uri);
            this.zzb = uri;
            return this;
        }

        @NonNull
        public Builder setPublicKeyCredentialRequestOptions(@NonNull PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
            this.zza = (PublicKeyCredentialRequestOptions) Preconditions.checkNotNull(publicKeyCredentialRequestOptions);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public BrowserPublicKeyCredentialRequestOptions(@SafeParcelable.Param(id = 2) @NonNull PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions, @SafeParcelable.Param(id = 3) @NonNull Uri uri, @SafeParcelable.Param(id = 4) @Nullable byte[] bArr) {
        this.zza = (PublicKeyCredentialRequestOptions) Preconditions.checkNotNull(publicKeyCredentialRequestOptions);
        zzc(uri);
        this.zzb = uri;
        zzd(bArr);
        this.zzc = bArr;
    }

    @NonNull
    public static BrowserPublicKeyCredentialRequestOptions deserializeFromBytes(@NonNull byte[] bArr) {
        return (BrowserPublicKeyCredentialRequestOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    /* access modifiers changed from: private */
    public static Uri zzc(Uri uri) {
        Preconditions.checkNotNull(uri);
        boolean z2 = false;
        Preconditions.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        if (uri.getAuthority() != null) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "origin authority must be non-empty");
        return uri;
    }

    /* access modifiers changed from: private */
    public static byte[] zzd(byte[] bArr) {
        boolean z2 = true;
        if (!(bArr == null || bArr.length == 32)) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "clientDataHash must be 32 bytes long");
        return bArr;
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof BrowserPublicKeyCredentialRequestOptions)) {
            return false;
        }
        BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions = (BrowserPublicKeyCredentialRequestOptions) obj;
        return Objects.equal(this.zza, browserPublicKeyCredentialRequestOptions.zza) && Objects.equal(this.zzb, browserPublicKeyCredentialRequestOptions.zzb);
    }

    @Nullable
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zza.getAuthenticationExtensions();
    }

    @NonNull
    public byte[] getChallenge() {
        return this.zza.getChallenge();
    }

    @Nullable
    public byte[] getClientDataHash() {
        return this.zzc;
    }

    @NonNull
    public Uri getOrigin() {
        return this.zzb;
    }

    @NonNull
    public PublicKeyCredentialRequestOptions getPublicKeyCredentialRequestOptions() {
        return this.zza;
    }

    @Nullable
    public Integer getRequestId() {
        return this.zza.getRequestId();
    }

    @Nullable
    public Double getTimeoutSeconds() {
        return this.zza.getTimeoutSeconds();
    }

    @Nullable
    public TokenBinding getTokenBinding() {
        return this.zza.getTokenBinding();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPublicKeyCredentialRequestOptions(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getOrigin(), i3, false);
        SafeParcelWriter.writeByteArray(parcel, 4, getClientDataHash(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
