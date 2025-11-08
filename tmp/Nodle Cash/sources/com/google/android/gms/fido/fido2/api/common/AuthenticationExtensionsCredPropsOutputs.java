package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "AuthenticationExtensionsCredPropsOutputsCreator")
public class AuthenticationExtensionsCredPropsOutputs extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticationExtensionsCredPropsOutputs> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getIsDiscoverableCredential", id = 1)
    private final boolean zza;

    @SafeParcelable.Constructor
    public AuthenticationExtensionsCredPropsOutputs(@SafeParcelable.Param(id = 1) boolean z2) {
        this.zza = z2;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof AuthenticationExtensionsCredPropsOutputs) && this.zza == ((AuthenticationExtensionsCredPropsOutputs) obj).zza;
    }

    public boolean getIsDiscoverableCredential() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza));
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getIsDiscoverableCredential());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("rk", this.zza);
            return jSONObject;
        } catch (JSONException e3) {
            throw new RuntimeException("Error encoding AuthenticationExtensionsCredPropsOutputs to JSON object", e3);
        }
    }
}
