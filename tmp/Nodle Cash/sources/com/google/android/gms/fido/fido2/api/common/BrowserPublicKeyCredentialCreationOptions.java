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

@SafeParcelable.Class(creator = "BrowserPublicKeyCredentialCreationOptionsCreator")
@SafeParcelable.Reserved({1})
public class BrowserPublicKeyCredentialCreationOptions extends BrowserRequestOptions {
    @NonNull
    public static final Parcelable.Creator<BrowserPublicKeyCredentialCreationOptions> CREATOR = new zzn();
    @SafeParcelable.Field(getter = "getPublicKeyCredentialCreationOptions", id = 2)
    @NonNull
    private final PublicKeyCredentialCreationOptions zza;
    @SafeParcelable.Field(getter = "getOrigin", id = 3)
    @NonNull
    private final Uri zzb;
    @SafeParcelable.Field(getter = "getClientDataHash", id = 4)
    @Nullable
    private final byte[] zzc;

    public static final class Builder {
        private PublicKeyCredentialCreationOptions zza;
        private Uri zzb;
        private byte[] zzc;

        @NonNull
        public BrowserPublicKeyCredentialCreationOptions build() {
            return new BrowserPublicKeyCredentialCreationOptions(this.zza, this.zzb, this.zzc);
        }

        @NonNull
        public Builder setClientDataHash(@NonNull byte[] bArr) {
            byte[] unused = BrowserPublicKeyCredentialCreationOptions.zzd(bArr);
            this.zzc = bArr;
            return this;
        }

        @NonNull
        public Builder setOrigin(@NonNull Uri uri) {
            Uri unused = BrowserPublicKeyCredentialCreationOptions.zzc(uri);
            this.zzb = uri;
            return this;
        }

        @NonNull
        public Builder setPublicKeyCredentialCreationOptions(@NonNull PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions) {
            this.zza = publicKeyCredentialCreationOptions;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public BrowserPublicKeyCredentialCreationOptions(@SafeParcelable.Param(id = 2) @NonNull PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions, @SafeParcelable.Param(id = 3) @NonNull Uri uri, @SafeParcelable.Param(id = 4) @Nullable byte[] bArr) {
        this.zza = (PublicKeyCredentialCreationOptions) Preconditions.checkNotNull(publicKeyCredentialCreationOptions);
        zzc(uri);
        this.zzb = uri;
        zzd(bArr);
        this.zzc = bArr;
    }

    @NonNull
    public static BrowserPublicKeyCredentialCreationOptions deserializeFromBytes(@NonNull byte[] bArr) {
        return (BrowserPublicKeyCredentialCreationOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
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
        if (!(obj instanceof BrowserPublicKeyCredentialCreationOptions)) {
            return false;
        }
        BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions = (BrowserPublicKeyCredentialCreationOptions) obj;
        return Objects.equal(this.zza, browserPublicKeyCredentialCreationOptions.zza) && Objects.equal(this.zzb, browserPublicKeyCredentialCreationOptions.zzb);
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
    public PublicKeyCredentialCreationOptions getPublicKeyCredentialCreationOptions() {
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
        SafeParcelWriter.writeParcelable(parcel, 2, getPublicKeyCredentialCreationOptions(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getOrigin(), i3, false);
        SafeParcelWriter.writeByteArray(parcel, 4, getClientDataHash(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
