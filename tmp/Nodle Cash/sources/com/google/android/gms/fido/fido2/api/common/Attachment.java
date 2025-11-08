package com.google.android.gms.fido.fido2.api.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amplitude.api.Constants;

public enum Attachment implements Parcelable {
    PLATFORM(Constants.AMP_TRACKING_OPTION_PLATFORM),
    CROSS_PLATFORM("cross-platform");
    
    @NonNull
    public static final Parcelable.Creator<Attachment> CREATOR = null;
    @NonNull
    private final String zzb;

    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(@NonNull String str) {
            super(a.l("Attachment ", str, " not supported"));
        }
    }

    static {
        CREATOR = new zza();
    }

    private Attachment(String str) {
        this.zzb = str;
    }

    @NonNull
    public static Attachment fromString(@NonNull String str) throws UnsupportedAttachmentException {
        for (Attachment attachment : values()) {
            if (str.equals(attachment.zzb)) {
                return attachment;
            }
        }
        throw new UnsupportedAttachmentException(str);
    }

    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeString(this.zzb);
    }
}
