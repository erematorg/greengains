package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SaveAccountLinkingTokenResultCreator")
public class SaveAccountLinkingTokenResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SaveAccountLinkingTokenResult> CREATOR = new zbq();
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    @Nullable
    private final PendingIntent zba;

    @SafeParcelable.Constructor
    public SaveAccountLinkingTokenResult(@SafeParcelable.Param(id = 1) @Nullable PendingIntent pendingIntent) {
        this.zba = pendingIntent;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SaveAccountLinkingTokenResult)) {
            return false;
        }
        return Objects.equal(this.zba, ((SaveAccountLinkingTokenResult) obj).zba);
    }

    @Nullable
    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    public boolean hasResolution() {
        return this.zba != null;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
