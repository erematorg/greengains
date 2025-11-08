package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PublicKeyCredentialRequestOptionsCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialRequestOptions extends RequestOptions {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialRequestOptions> CREATOR = new zzao();
    @SafeParcelable.Field(getter = "getChallenge", id = 2)
    @NonNull
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    @Nullable
    private final Double zzb;
    @SafeParcelable.Field(getter = "getRpId", id = 4)
    @NonNull
    private final String zzc;
    @SafeParcelable.Field(getter = "getAllowList", id = 5)
    @Nullable
    private final List zzd;
    @SafeParcelable.Field(getter = "getRequestId", id = 6)
    @Nullable
    private final Integer zze;
    @SafeParcelable.Field(getter = "getTokenBinding", id = 7)
    @Nullable
    private final TokenBinding zzf;
    @SafeParcelable.Field(getter = "getUserVerificationAsString", id = 8, type = "java.lang.String")
    @Nullable
    private final zzay zzg;
    @SafeParcelable.Field(getter = "getAuthenticationExtensions", id = 9)
    @Nullable
    private final AuthenticationExtensions zzh;
    @SafeParcelable.Field(getter = "getLongRequestId", id = 10)
    @Nullable
    private final Long zzi;

    public static final class Builder {
        private byte[] zza;
        private Double zzb;
        private String zzc;
        private List zzd;
        private Integer zze;
        private TokenBinding zzf;
        private zzay zzg;
        private AuthenticationExtensions zzh;

        public Builder() {
        }

        @NonNull
        public PublicKeyCredentialRequestOptions build() {
            byte[] bArr = this.zza;
            Double d2 = this.zzb;
            String str = this.zzc;
            List list = this.zzd;
            Integer num = this.zze;
            TokenBinding tokenBinding = this.zzf;
            zzay zzay = this.zzg;
            return new PublicKeyCredentialRequestOptions(bArr, d2, str, list, num, tokenBinding, zzay == null ? null : zzay.toString(), this.zzh, (Long) null);
        }

        @NonNull
        public Builder setAllowList(@Nullable List<PublicKeyCredentialDescriptor> list) {
            this.zzd = list;
            return this;
        }

        @NonNull
        public Builder setAuthenticationExtensions(@Nullable AuthenticationExtensions authenticationExtensions) {
            this.zzh = authenticationExtensions;
            return this;
        }

        @NonNull
        public Builder setChallenge(@NonNull byte[] bArr) {
            this.zza = (byte[]) Preconditions.checkNotNull(bArr);
            return this;
        }

        @NonNull
        public Builder setRequestId(@Nullable Integer num) {
            this.zze = num;
            return this;
        }

        @NonNull
        public Builder setRpId(@NonNull String str) {
            this.zzc = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@Nullable Double d2) {
            this.zzb = d2;
            return this;
        }

        @NonNull
        public Builder setTokenBinding(@Nullable TokenBinding tokenBinding) {
            this.zzf = tokenBinding;
            return this;
        }

        public Builder(@Nullable PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
            if (publicKeyCredentialRequestOptions != null) {
                this.zza = publicKeyCredentialRequestOptions.getChallenge();
                this.zzb = publicKeyCredentialRequestOptions.getTimeoutSeconds();
                this.zzc = publicKeyCredentialRequestOptions.getRpId();
                this.zzd = publicKeyCredentialRequestOptions.getAllowList();
                this.zze = publicKeyCredentialRequestOptions.getRequestId();
                this.zzf = publicKeyCredentialRequestOptions.getTokenBinding();
                this.zzg = publicKeyCredentialRequestOptions.zza();
                this.zzh = publicKeyCredentialRequestOptions.getAuthenticationExtensions();
            }
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialRequestOptions(@SafeParcelable.Param(id = 2) @NonNull byte[] bArr, @SafeParcelable.Param(id = 3) @Nullable Double d2, @SafeParcelable.Param(id = 4) @NonNull String str, @SafeParcelable.Param(id = 5) @Nullable List list, @SafeParcelable.Param(id = 6) @Nullable Integer num, @SafeParcelable.Param(id = 7) @Nullable TokenBinding tokenBinding, @SafeParcelable.Param(id = 8) @Nullable String str2, @SafeParcelable.Param(id = 9) @Nullable AuthenticationExtensions authenticationExtensions, @SafeParcelable.Param(id = 10) @Nullable Long l2) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = d2;
        this.zzc = (String) Preconditions.checkNotNull(str);
        this.zzd = list;
        this.zze = num;
        this.zzf = tokenBinding;
        this.zzi = l2;
        if (str2 != null) {
            try {
                this.zzg = zzay.zza(str2);
            } catch (zzax e3) {
                throw new IllegalArgumentException(e3);
            }
        } else {
            this.zzg = null;
        }
        this.zzh = authenticationExtensions;
    }

    @NonNull
    public static PublicKeyCredentialRequestOptions deserializeFromBytes(@Nullable byte[] bArr) {
        return (PublicKeyCredentialRequestOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        r2 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        r0 = r3.zzd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions r4 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions) r4
            byte[] r0 = r3.zza
            byte[] r2 = r4.zza
            boolean r0 = java.util.Arrays.equals(r0, r2)
            if (r0 == 0) goto L_0x0078
            java.lang.Double r0 = r3.zzb
            java.lang.Double r2 = r4.zzb
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            java.lang.String r0 = r3.zzc
            java.lang.String r2 = r4.zzc
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            java.util.List r0 = r3.zzd
            if (r0 != 0) goto L_0x002e
            java.util.List r2 = r4.zzd
            if (r2 == 0) goto L_0x0044
        L_0x002e:
            if (r0 == 0) goto L_0x0078
            java.util.List r2 = r4.zzd
            if (r2 == 0) goto L_0x0078
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0078
            java.util.List r0 = r4.zzd
            java.util.List r2 = r3.zzd
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0078
        L_0x0044:
            java.lang.Integer r0 = r3.zze
            java.lang.Integer r2 = r4.zze
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            com.google.android.gms.fido.fido2.api.common.TokenBinding r0 = r3.zzf
            com.google.android.gms.fido.fido2.api.common.TokenBinding r2 = r4.zzf
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            com.google.android.gms.fido.fido2.api.common.zzay r0 = r3.zzg
            com.google.android.gms.fido.fido2.api.common.zzay r2 = r4.zzg
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r0 = r3.zzh
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r2 = r4.zzh
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0078
            java.lang.Long r3 = r3.zzi
            java.lang.Long r4 = r4.zzi
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0078
            r3 = 1
            return r3
        L_0x0078:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions.equals(java.lang.Object):boolean");
    }

    @Nullable
    public List<PublicKeyCredentialDescriptor> getAllowList() {
        return this.zzd;
    }

    @Nullable
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzh;
    }

    @NonNull
    public byte[] getChallenge() {
        return this.zza;
    }

    @Nullable
    public Integer getRequestId() {
        return this.zze;
    }

    @NonNull
    public String getRpId() {
        return this.zzc;
    }

    @Nullable
    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    @Nullable
    public TokenBinding getTokenBinding() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zza)), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getChallenge(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeString(parcel, 4, getRpId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getAllowList(), false);
        SafeParcelWriter.writeIntegerObject(parcel, 6, getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getTokenBinding(), i3, false);
        zzay zzay = this.zzg;
        SafeParcelWriter.writeString(parcel, 8, zzay == null ? null : zzay.toString(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getAuthenticationExtensions(), i3, false);
        SafeParcelWriter.writeLongObject(parcel, 10, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzay zza() {
        return this.zzg;
    }
}
