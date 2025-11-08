package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.fido.zzam;
import com.google.android.gms.internal.fido.zzan;
import com.google.android.gms.internal.fido.zzch;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "RegisterResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class RegisterResponseData extends ResponseData {
    @NonNull
    public static final Parcelable.Creator<RegisterResponseData> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getRegisterData", id = 2)
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getProtocolVersionAsString", id = 3, type = "java.lang.String")
    private final ProtocolVersion zzb;
    @SafeParcelable.Field(getter = "getClientDataString", id = 4)
    @Nullable
    private final String zzc;

    public RegisterResponseData(@NonNull byte[] bArr) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = ProtocolVersion.V1;
        this.zzc = null;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof RegisterResponseData)) {
            return false;
        }
        RegisterResponseData registerResponseData = (RegisterResponseData) obj;
        return Objects.equal(this.zzb, registerResponseData.zzb) && Arrays.equals(this.zza, registerResponseData.zza) && Objects.equal(this.zzc, registerResponseData.zzc);
    }

    @NonNull
    public String getClientDataString() {
        return this.zzc;
    }

    @NonNull
    public ProtocolVersion getProtocolVersion() {
        return this.zzb;
    }

    @NonNull
    public byte[] getRegisterData() {
        return this.zza;
    }

    public int getVersionCode() {
        ProtocolVersion protocolVersion = ProtocolVersion.UNKNOWN;
        int ordinal = this.zzb.ordinal();
        int i3 = 1;
        if (ordinal != 1) {
            i3 = 2;
            if (ordinal != 2) {
                return -1;
            }
        }
        return i3;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, Integer.valueOf(Arrays.hashCode(this.zza)), this.zzc);
    }

    @NonNull
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationData", Base64.encodeToString(this.zza, 11));
            jSONObject.put("version", this.zzb.toString());
            String str = this.zzc;
            if (str != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(str.getBytes(), 11));
            }
            return jSONObject;
        } catch (JSONException e3) {
            throw new RuntimeException(e3);
        }
    }

    @NonNull
    public String toString() {
        zzam zza2 = zzan.zza(this);
        zza2.zzb("protocolVersion", this.zzb);
        zzch zzf = zzch.zzf();
        byte[] bArr = this.zza;
        zza2.zzb("registerData", zzf.zzg(bArr, 0, bArr.length));
        String str = this.zzc;
        if (str != null) {
            zza2.zzb("clientDataString", str);
        }
        return zza2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getRegisterData(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb.toString(), false);
        SafeParcelWriter.writeString(parcel, 4, getClientDataString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RegisterResponseData(@NonNull byte[] bArr, @NonNull ProtocolVersion protocolVersion, @Nullable String str) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        boolean z2 = false;
        Preconditions.checkArgument(protocolVersion != ProtocolVersion.UNKNOWN);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(str == null ? true : z2);
            this.zzc = null;
            return;
        }
        this.zzc = (String) Preconditions.checkNotNull(str);
    }

    @SafeParcelable.Constructor
    public RegisterResponseData(@SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable String str2) {
        this.zza = bArr;
        try {
            this.zzb = ProtocolVersion.fromString(str);
            this.zzc = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e3) {
            throw new IllegalArgumentException(e3);
        }
    }
}
