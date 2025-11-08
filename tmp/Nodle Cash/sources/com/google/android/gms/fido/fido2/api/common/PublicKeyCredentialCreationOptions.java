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
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PublicKeyCredentialCreationOptionsCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialCreationOptions extends RequestOptions {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialCreationOptions> CREATOR = new zzak();
    @SafeParcelable.Field(getter = "getRp", id = 2)
    @NonNull
    private final PublicKeyCredentialRpEntity zza;
    @SafeParcelable.Field(getter = "getUser", id = 3)
    @NonNull
    private final PublicKeyCredentialUserEntity zzb;
    @SafeParcelable.Field(getter = "getChallenge", id = 4)
    @NonNull
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getParameters", id = 5)
    @NonNull
    private final List zzd;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 6)
    @Nullable
    private final Double zze;
    @SafeParcelable.Field(getter = "getExcludeList", id = 7)
    @Nullable
    private final List zzf;
    @SafeParcelable.Field(getter = "getAuthenticatorSelection", id = 8)
    @Nullable
    private final AuthenticatorSelectionCriteria zzg;
    @SafeParcelable.Field(getter = "getRequestId", id = 9)
    @Nullable
    private final Integer zzh;
    @SafeParcelable.Field(getter = "getTokenBinding", id = 10)
    @Nullable
    private final TokenBinding zzi;
    @SafeParcelable.Field(getter = "getAttestationConveyancePreferenceAsString", id = 11, type = "java.lang.String")
    @Nullable
    private final AttestationConveyancePreference zzj;
    @SafeParcelable.Field(getter = "getAuthenticationExtensions", id = 12)
    @Nullable
    private final AuthenticationExtensions zzk;

    public static final class Builder {
        private PublicKeyCredentialRpEntity zza;
        private PublicKeyCredentialUserEntity zzb;
        private byte[] zzc;
        private List zzd;
        private Double zze;
        private List zzf;
        private AuthenticatorSelectionCriteria zzg;
        private Integer zzh;
        private TokenBinding zzi;
        private AttestationConveyancePreference zzj;
        private AuthenticationExtensions zzk;

        @NonNull
        public PublicKeyCredentialCreationOptions build() {
            PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = this.zza;
            PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = this.zzb;
            byte[] bArr = this.zzc;
            List list = this.zzd;
            Double d2 = this.zze;
            List list2 = this.zzf;
            AuthenticatorSelectionCriteria authenticatorSelectionCriteria = this.zzg;
            Integer num = this.zzh;
            TokenBinding tokenBinding = this.zzi;
            AttestationConveyancePreference attestationConveyancePreference = this.zzj;
            return new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity, publicKeyCredentialUserEntity, bArr, list, d2, list2, authenticatorSelectionCriteria, num, tokenBinding, attestationConveyancePreference == null ? null : attestationConveyancePreference.toString(), this.zzk);
        }

        @NonNull
        public Builder setAttestationConveyancePreference(@Nullable AttestationConveyancePreference attestationConveyancePreference) {
            this.zzj = attestationConveyancePreference;
            return this;
        }

        @NonNull
        public Builder setAuthenticationExtensions(@Nullable AuthenticationExtensions authenticationExtensions) {
            this.zzk = authenticationExtensions;
            return this;
        }

        @NonNull
        public Builder setAuthenticatorSelection(@Nullable AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
            this.zzg = authenticatorSelectionCriteria;
            return this;
        }

        @NonNull
        public Builder setChallenge(@NonNull byte[] bArr) {
            this.zzc = (byte[]) Preconditions.checkNotNull(bArr);
            return this;
        }

        @NonNull
        public Builder setExcludeList(@Nullable List<PublicKeyCredentialDescriptor> list) {
            this.zzf = list;
            return this;
        }

        @NonNull
        public Builder setParameters(@NonNull List<PublicKeyCredentialParameters> list) {
            this.zzd = (List) Preconditions.checkNotNull(list);
            return this;
        }

        @NonNull
        public Builder setRequestId(@Nullable Integer num) {
            this.zzh = num;
            return this;
        }

        @NonNull
        public Builder setRp(@NonNull PublicKeyCredentialRpEntity publicKeyCredentialRpEntity) {
            this.zza = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(publicKeyCredentialRpEntity);
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@Nullable Double d2) {
            this.zze = d2;
            return this;
        }

        @NonNull
        public Builder setTokenBinding(@Nullable TokenBinding tokenBinding) {
            this.zzi = tokenBinding;
            return this;
        }

        @NonNull
        public Builder setUser(@NonNull PublicKeyCredentialUserEntity publicKeyCredentialUserEntity) {
            this.zzb = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(publicKeyCredentialUserEntity);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialCreationOptions(@SafeParcelable.Param(id = 2) @NonNull PublicKeyCredentialRpEntity publicKeyCredentialRpEntity, @SafeParcelable.Param(id = 3) @NonNull PublicKeyCredentialUserEntity publicKeyCredentialUserEntity, @SafeParcelable.Param(id = 4) @NonNull byte[] bArr, @SafeParcelable.Param(id = 5) @NonNull List list, @SafeParcelable.Param(id = 6) @Nullable Double d2, @SafeParcelable.Param(id = 7) @Nullable List list2, @SafeParcelable.Param(id = 8) @Nullable AuthenticatorSelectionCriteria authenticatorSelectionCriteria, @SafeParcelable.Param(id = 9) @Nullable Integer num, @SafeParcelable.Param(id = 10) @Nullable TokenBinding tokenBinding, @SafeParcelable.Param(id = 11) @Nullable String str, @SafeParcelable.Param(id = 12) @Nullable AuthenticationExtensions authenticationExtensions) {
        this.zza = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(publicKeyCredentialRpEntity);
        this.zzb = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(publicKeyCredentialUserEntity);
        this.zzc = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzd = (List) Preconditions.checkNotNull(list);
        this.zze = d2;
        this.zzf = list2;
        this.zzg = authenticatorSelectionCriteria;
        this.zzh = num;
        this.zzi = tokenBinding;
        if (str != null) {
            try {
                this.zzj = AttestationConveyancePreference.fromString(str);
            } catch (AttestationConveyancePreference.UnsupportedAttestationConveyancePreferenceException e3) {
                throw new IllegalArgumentException(e3);
            }
        } else {
            this.zzj = null;
        }
        this.zzk = authenticationExtensions;
    }

    @NonNull
    public static PublicKeyCredentialCreationOptions deserializeFromBytes(@NonNull byte[] bArr) {
        return (PublicKeyCredentialCreationOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r0 = r3.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        r2 = r4.zzf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions r4 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions) r4
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity r0 = r3.zza
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity r2 = r4.zza
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r0 = r3.zzb
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r2 = r4.zzb
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            byte[] r0 = r3.zzc
            byte[] r2 = r4.zzc
            boolean r0 = java.util.Arrays.equals(r0, r2)
            if (r0 == 0) goto L_0x0096
            java.lang.Double r0 = r3.zze
            java.lang.Double r2 = r4.zze
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            java.util.List r0 = r3.zzd
            java.util.List r2 = r4.zzd
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0096
            java.util.List r0 = r4.zzd
            java.util.List r2 = r3.zzd
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0096
            java.util.List r0 = r3.zzf
            if (r0 != 0) goto L_0x004c
            java.util.List r2 = r4.zzf
            if (r2 == 0) goto L_0x0062
        L_0x004c:
            if (r0 == 0) goto L_0x0096
            java.util.List r2 = r4.zzf
            if (r2 == 0) goto L_0x0096
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0096
            java.util.List r0 = r4.zzf
            java.util.List r2 = r3.zzf
            boolean r0 = r0.containsAll(r2)
            if (r0 == 0) goto L_0x0096
        L_0x0062:
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r0 = r3.zzg
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r2 = r4.zzg
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            java.lang.Integer r0 = r3.zzh
            java.lang.Integer r2 = r4.zzh
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            com.google.android.gms.fido.fido2.api.common.TokenBinding r0 = r3.zzi
            com.google.android.gms.fido.fido2.api.common.TokenBinding r2 = r4.zzi
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference r0 = r3.zzj
            com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference r2 = r4.zzj
            boolean r0 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r0 == 0) goto L_0x0096
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r3 = r3.zzk
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r4 = r4.zzk
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0096
            r3 = 1
            return r3
        L_0x0096:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions.equals(java.lang.Object):boolean");
    }

    @Nullable
    public AttestationConveyancePreference getAttestationConveyancePreference() {
        return this.zzj;
    }

    @Nullable
    public String getAttestationConveyancePreferenceAsString() {
        AttestationConveyancePreference attestationConveyancePreference = this.zzj;
        if (attestationConveyancePreference == null) {
            return null;
        }
        return attestationConveyancePreference.toString();
    }

    @Nullable
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzk;
    }

    @Nullable
    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.zzg;
    }

    @NonNull
    public byte[] getChallenge() {
        return this.zzc;
    }

    @Nullable
    public List<PublicKeyCredentialDescriptor> getExcludeList() {
        return this.zzf;
    }

    @NonNull
    public List<PublicKeyCredentialParameters> getParameters() {
        return this.zzd;
    }

    @Nullable
    public Integer getRequestId() {
        return this.zzh;
    }

    @NonNull
    public PublicKeyCredentialRpEntity getRp() {
        return this.zza;
    }

    @Nullable
    public Double getTimeoutSeconds() {
        return this.zze;
    }

    @Nullable
    public TokenBinding getTokenBinding() {
        return this.zzi;
    }

    @NonNull
    public PublicKeyCredentialUserEntity getUser() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, Integer.valueOf(Arrays.hashCode(this.zzc)), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getRp(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getUser(), i3, false);
        SafeParcelWriter.writeByteArray(parcel, 4, getChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getParameters(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 6, getTimeoutSeconds(), false);
        SafeParcelWriter.writeTypedList(parcel, 7, getExcludeList(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getAuthenticatorSelection(), i3, false);
        SafeParcelWriter.writeIntegerObject(parcel, 9, getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel, 10, getTokenBinding(), i3, false);
        SafeParcelWriter.writeString(parcel, 11, getAttestationConveyancePreferenceAsString(), false);
        SafeParcelWriter.writeParcelable(parcel, 12, getAuthenticationExtensions(), i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
