package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AuthenticationExtensionsCreator")
@SafeParcelable.Reserved({1})
public class AuthenticationExtensions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticationExtensions> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getFidoAppIdExtension", id = 2)
    @Nullable
    private final FidoAppIdExtension zza;
    @SafeParcelable.Field(getter = "getCableAuthenticationExtension", id = 3)
    @Nullable
    private final zzs zzb;
    @SafeParcelable.Field(getter = "getUserVerificationMethodExtension", id = 4)
    @Nullable
    private final UserVerificationMethodExtension zzc;
    @SafeParcelable.Field(getter = "getGoogleMultiAssertionExtension", id = 5)
    @Nullable
    private final zzz zzd;
    @SafeParcelable.Field(getter = "getGoogleSessionIdExtension", id = 6)
    @Nullable
    private final zzab zze;
    @SafeParcelable.Field(getter = "getGoogleSilentVerificationExtension", id = 7)
    @Nullable
    private final zzad zzf;
    @SafeParcelable.Field(getter = "getDevicePublicKeyExtension", id = 8)
    @Nullable
    private final zzu zzg;
    @SafeParcelable.Field(getter = "getGoogleTunnelServerIdExtension", id = 9)
    @Nullable
    private final zzag zzh;
    @SafeParcelable.Field(getter = "getGoogleThirdPartyPaymentExtension", id = 10)
    @Nullable
    private final GoogleThirdPartyPaymentExtension zzi;
    @SafeParcelable.Field(getter = "getPrfExtension", id = 11)
    @Nullable
    private final zzai zzj;

    public static final class Builder {
        private FidoAppIdExtension zza;
        private UserVerificationMethodExtension zzb;
        private zzs zzc;
        private zzz zzd;
        private zzab zze;
        private zzad zzf;
        private zzu zzg;
        private zzag zzh;
        private GoogleThirdPartyPaymentExtension zzi;
        private zzai zzj;

        public Builder() {
        }

        @NonNull
        public AuthenticationExtensions build() {
            return new AuthenticationExtensions(this.zza, this.zzc, this.zzb, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj);
        }

        @NonNull
        public Builder setFido2Extension(@Nullable FidoAppIdExtension fidoAppIdExtension) {
            this.zza = fidoAppIdExtension;
            return this;
        }

        @NonNull
        public Builder setGoogleThirdPartyPaymentExtension(@Nullable GoogleThirdPartyPaymentExtension googleThirdPartyPaymentExtension) {
            this.zzi = googleThirdPartyPaymentExtension;
            return this;
        }

        @NonNull
        public Builder setUserVerificationMethodExtension(@Nullable UserVerificationMethodExtension userVerificationMethodExtension) {
            this.zzb = userVerificationMethodExtension;
            return this;
        }

        public Builder(@Nullable AuthenticationExtensions authenticationExtensions) {
            if (authenticationExtensions != null) {
                this.zza = authenticationExtensions.getFidoAppIdExtension();
                this.zzb = authenticationExtensions.getUserVerificationMethodExtension();
                this.zzc = authenticationExtensions.zza();
                this.zzd = authenticationExtensions.zzc();
                this.zze = authenticationExtensions.zzd();
                this.zzf = authenticationExtensions.zze();
                this.zzg = authenticationExtensions.zzb();
                this.zzh = authenticationExtensions.zzg();
                this.zzi = authenticationExtensions.zzf();
                this.zzj = authenticationExtensions.zzh();
            }
        }
    }

    @SafeParcelable.Constructor
    public AuthenticationExtensions(@SafeParcelable.Param(id = 2) @Nullable FidoAppIdExtension fidoAppIdExtension, @SafeParcelable.Param(id = 3) @Nullable zzs zzs, @SafeParcelable.Param(id = 4) @Nullable UserVerificationMethodExtension userVerificationMethodExtension, @SafeParcelable.Param(id = 5) @Nullable zzz zzz, @SafeParcelable.Param(id = 6) @Nullable zzab zzab, @SafeParcelable.Param(id = 7) @Nullable zzad zzad, @SafeParcelable.Param(id = 8) @Nullable zzu zzu, @SafeParcelable.Param(id = 9) @Nullable zzag zzag, @SafeParcelable.Param(id = 10) @Nullable GoogleThirdPartyPaymentExtension googleThirdPartyPaymentExtension, @SafeParcelable.Param(id = 11) @Nullable zzai zzai) {
        this.zza = fidoAppIdExtension;
        this.zzc = userVerificationMethodExtension;
        this.zzb = zzs;
        this.zzd = zzz;
        this.zze = zzab;
        this.zzf = zzad;
        this.zzg = zzu;
        this.zzh = zzag;
        this.zzi = googleThirdPartyPaymentExtension;
        this.zzj = zzai;
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof AuthenticationExtensions)) {
            return false;
        }
        AuthenticationExtensions authenticationExtensions = (AuthenticationExtensions) obj;
        return Objects.equal(this.zza, authenticationExtensions.zza) && Objects.equal(this.zzb, authenticationExtensions.zzb) && Objects.equal(this.zzc, authenticationExtensions.zzc) && Objects.equal(this.zzd, authenticationExtensions.zzd) && Objects.equal(this.zze, authenticationExtensions.zze) && Objects.equal(this.zzf, authenticationExtensions.zzf) && Objects.equal(this.zzg, authenticationExtensions.zzg) && Objects.equal(this.zzh, authenticationExtensions.zzh) && Objects.equal(this.zzi, authenticationExtensions.zzi) && Objects.equal(this.zzj, authenticationExtensions.zzj);
    }

    @Nullable
    public FidoAppIdExtension getFidoAppIdExtension() {
        return this.zza;
    }

    @Nullable
    public UserVerificationMethodExtension getUserVerificationMethodExtension() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getFidoAppIdExtension(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getUserVerificationMethodExtension(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzh, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzj, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzs zza() {
        return this.zzb;
    }

    @Nullable
    public final zzu zzb() {
        return this.zzg;
    }

    @Nullable
    public final zzz zzc() {
        return this.zzd;
    }

    @Nullable
    public final zzab zzd() {
        return this.zze;
    }

    @Nullable
    public final zzad zze() {
        return this.zzf;
    }

    @Nullable
    public final GoogleThirdPartyPaymentExtension zzf() {
        return this.zzi;
    }

    @Nullable
    public final zzag zzg() {
        return this.zzh;
    }

    @Nullable
    public final zzai zzh() {
        return this.zzj;
    }
}
