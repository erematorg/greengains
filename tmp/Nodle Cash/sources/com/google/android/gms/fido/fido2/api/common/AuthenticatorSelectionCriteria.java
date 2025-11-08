package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement;

@SafeParcelable.Class(creator = "AuthenticatorSelectionCriteriaCreator")
@SafeParcelable.Reserved({1})
public class AuthenticatorSelectionCriteria extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticatorSelectionCriteria> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getAttachmentAsString", id = 2, type = "java.lang.String")
    @Nullable
    private final Attachment zza;
    @SafeParcelable.Field(getter = "getRequireResidentKey", id = 3)
    @Nullable
    private final Boolean zzb;
    @SafeParcelable.Field(getter = "getRequireUserVerificationAsString", id = 4, type = "java.lang.String")
    @Nullable
    private final zzay zzc;
    @SafeParcelable.Field(getter = "getResidentKeyRequirementAsString", id = 5, type = "java.lang.String")
    @Nullable
    private final ResidentKeyRequirement zzd;

    public static class Builder {
        private Attachment zza;
        private Boolean zzb;
        private ResidentKeyRequirement zzc;

        @NonNull
        public AuthenticatorSelectionCriteria build() {
            Attachment attachment = this.zza;
            String attachment2 = attachment == null ? null : attachment.toString();
            Boolean bool = this.zzb;
            ResidentKeyRequirement residentKeyRequirement = this.zzc;
            return new AuthenticatorSelectionCriteria(attachment2, bool, (String) null, residentKeyRequirement == null ? null : residentKeyRequirement.toString());
        }

        @NonNull
        public Builder setAttachment(@Nullable Attachment attachment) {
            this.zza = attachment;
            return this;
        }

        @NonNull
        public Builder setRequireResidentKey(@Nullable Boolean bool) {
            this.zzb = bool;
            return this;
        }

        @NonNull
        public Builder setResidentKeyRequirement(@Nullable ResidentKeyRequirement residentKeyRequirement) {
            this.zzc = residentKeyRequirement;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public AuthenticatorSelectionCriteria(@SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable Boolean bool, @SafeParcelable.Param(id = 4) @Nullable String str2, @SafeParcelable.Param(id = 5) @Nullable String str3) {
        Attachment attachment;
        ResidentKeyRequirement residentKeyRequirement = null;
        if (str == null) {
            attachment = null;
        } else {
            try {
                attachment = Attachment.fromString(str);
            } catch (Attachment.UnsupportedAttachmentException | ResidentKeyRequirement.UnsupportedResidentKeyRequirementException | zzax e3) {
                throw new IllegalArgumentException(e3);
            }
        }
        this.zza = attachment;
        this.zzb = bool;
        this.zzc = str2 == null ? null : zzay.zza(str2);
        if (str3 != null) {
            residentKeyRequirement = ResidentKeyRequirement.fromString(str3);
        }
        this.zzd = residentKeyRequirement;
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof AuthenticatorSelectionCriteria)) {
            return false;
        }
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) obj;
        return Objects.equal(this.zza, authenticatorSelectionCriteria.zza) && Objects.equal(this.zzb, authenticatorSelectionCriteria.zzb) && Objects.equal(this.zzc, authenticatorSelectionCriteria.zzc) && Objects.equal(getResidentKeyRequirement(), authenticatorSelectionCriteria.getResidentKeyRequirement());
    }

    @Nullable
    public Attachment getAttachment() {
        return this.zza;
    }

    @Nullable
    public String getAttachmentAsString() {
        Attachment attachment = this.zza;
        if (attachment == null) {
            return null;
        }
        return attachment.toString();
    }

    @Nullable
    public Boolean getRequireResidentKey() {
        return this.zzb;
    }

    @Nullable
    public ResidentKeyRequirement getResidentKeyRequirement() {
        ResidentKeyRequirement residentKeyRequirement = this.zzd;
        if (residentKeyRequirement == null) {
            Boolean bool = this.zzb;
            residentKeyRequirement = null;
            if (bool != null && bool.booleanValue()) {
                return ResidentKeyRequirement.RESIDENT_KEY_REQUIRED;
            }
        }
        return residentKeyRequirement;
    }

    @Nullable
    public String getResidentKeyRequirementAsString() {
        if (getResidentKeyRequirement() == null) {
            return null;
        }
        return getResidentKeyRequirement().toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, getResidentKeyRequirement());
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAttachmentAsString(), false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, getRequireResidentKey(), false);
        zzay zzay = this.zzc;
        SafeParcelWriter.writeString(parcel, 4, zzay == null ? null : zzay.toString(), false);
        SafeParcelWriter.writeString(parcel, 5, getResidentKeyRequirementAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
