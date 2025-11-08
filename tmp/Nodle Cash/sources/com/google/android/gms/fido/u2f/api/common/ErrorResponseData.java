package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fido.zzam;
import com.google.android.gms.internal.fido.zzan;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "ErrorResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class ErrorResponseData extends ResponseData {
    @NonNull
    public static final Parcelable.Creator<ErrorResponseData> CREATOR = new zzd();
    @VisibleForTesting
    @NonNull
    public static final String JSON_ERROR_CODE = "errorCode";
    @VisibleForTesting
    @NonNull
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    @SafeParcelable.Field(getter = "getErrorCodeAsInt", id = 2, type = "int")
    private final ErrorCode zza;
    @SafeParcelable.Field(getter = "getErrorMessage", id = 3)
    private final String zzb;

    @SafeParcelable.Constructor
    public ErrorResponseData(@SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) String str) {
        this.zza = ErrorCode.toErrorCode(i3);
        this.zzb = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ErrorResponseData)) {
            return false;
        }
        ErrorResponseData errorResponseData = (ErrorResponseData) obj;
        return Objects.equal(this.zza, errorResponseData.zza) && Objects.equal(this.zzb, errorResponseData.zzb);
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return this.zza;
    }

    public int getErrorCodeAsInt() {
        return this.zza.getCode();
    }

    @NonNull
    public String getErrorMessage() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    @NonNull
    public final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.zza.getCode());
            String str = this.zzb;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e3) {
            throw new RuntimeException(e3);
        }
    }

    @NonNull
    public String toString() {
        zzam zza2 = zzan.zza(this);
        zza2.zza("errorCode", this.zza.getCode());
        String str = this.zzb;
        if (str != null) {
            zza2.zzb("errorMessage", str);
        }
        return zza2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getErrorCodeAsInt());
        SafeParcelWriter.writeString(parcel, 3, getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ErrorResponseData(@NonNull ErrorCode errorCode) {
        this.zza = (ErrorCode) Preconditions.checkNotNull(errorCode);
        this.zzb = null;
    }

    public ErrorResponseData(@NonNull ErrorCode errorCode, @NonNull String str) {
        this.zza = (ErrorCode) Preconditions.checkNotNull(errorCode);
        this.zzb = str;
    }
}
