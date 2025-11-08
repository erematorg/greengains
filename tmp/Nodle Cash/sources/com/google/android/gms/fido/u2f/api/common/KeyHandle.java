package com.google.android.gms.fido.u2f.api.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import java.util.Arrays;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "KeyHandleCreator")
@Deprecated
public class KeyHandle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<KeyHandle> CREATOR = new zze();
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getBytes", id = 2)
    private final byte[] zzb;
    @SafeParcelable.Field(getter = "getProtocolVersionAsString", id = 3, type = "java.lang.String")
    private final ProtocolVersion zzc;
    @SafeParcelable.Field(getter = "getTransports", id = 4)
    @Nullable
    private final List zzd;

    @SafeParcelable.Constructor
    public KeyHandle(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable List list) {
        this.zza = i3;
        this.zzb = bArr;
        try {
            this.zzc = ProtocolVersion.fromString(str);
            this.zzd = list;
        } catch (ProtocolVersion.UnsupportedProtocolException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    @NonNull
    public static KeyHandle parseFromJson(@NonNull JSONObject jSONObject) throws JSONException {
        List<Transport> list = null;
        try {
            ProtocolVersion fromString = ProtocolVersion.fromString(jSONObject.has("version") ? jSONObject.getString("version") : null);
            try {
                byte[] decode = Base64.decode(jSONObject.getString(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE), 8);
                if (jSONObject.has("transports")) {
                    list = Transport.parseTransports(jSONObject.getJSONArray("transports"));
                }
                return new KeyHandle(decode, fromString, list);
            } catch (IllegalArgumentException e3) {
                throw new JSONException(e3.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e4) {
            throw new JSONException(e4.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r3 = r5.zzd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.fido.u2f.api.common.KeyHandle
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.android.gms.fido.u2f.api.common.KeyHandle r5 = (com.google.android.gms.fido.u2f.api.common.KeyHandle) r5
            byte[] r1 = r4.zzb
            byte[] r3 = r5.zzb
            boolean r1 = java.util.Arrays.equals(r1, r3)
            if (r1 != 0) goto L_0x0017
            return r2
        L_0x0017:
            com.google.android.gms.fido.u2f.api.common.ProtocolVersion r1 = r4.zzc
            com.google.android.gms.fido.u2f.api.common.ProtocolVersion r3 = r5.zzc
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0022
            return r2
        L_0x0022:
            java.util.List r1 = r4.zzd
            if (r1 != 0) goto L_0x002c
            java.util.List r3 = r5.zzd
            if (r3 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            return r0
        L_0x002c:
            if (r1 == 0) goto L_0x0044
            java.util.List r3 = r5.zzd
            if (r3 != 0) goto L_0x0033
            goto L_0x0044
        L_0x0033:
            boolean r1 = r1.containsAll(r3)
            if (r1 == 0) goto L_0x0044
            java.util.List r5 = r5.zzd
            java.util.List r4 = r4.zzd
            boolean r4 = r5.containsAll(r4)
            if (r4 == 0) goto L_0x0044
            return r0
        L_0x0044:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.KeyHandle.equals(java.lang.Object):boolean");
    }

    @NonNull
    public byte[] getBytes() {
        return this.zzb;
    }

    @NonNull
    public ProtocolVersion getProtocolVersion() {
        return this.zzc;
    }

    @NonNull
    public List<Transport> getTransports() {
        return this.zzd;
    }

    public int getVersionCode() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zzb)), this.zzc, this.zzd);
    }

    @NonNull
    public JSONObject toJson() {
        return zza();
    }

    @NonNull
    public String toString() {
        List list = this.zzd;
        String obj = list == null ? AbstractJsonLexerKt.NULL : list.toString();
        String encode = Base64Utils.encode(this.zzb);
        ProtocolVersion protocolVersion = this.zzc;
        StringBuilder sb = new StringBuilder("{keyHandle: ");
        sb.append(encode);
        sb.append(", version: ");
        sb.append(protocolVersion);
        sb.append(", transports: ");
        return a.n(sb, obj, StringSubstitutor.DEFAULT_VAR_END);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeByteArray(parcel, 2, getBytes(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc.toString(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            byte[] bArr = this.zzb;
            if (bArr != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(bArr, 11));
            }
            ProtocolVersion protocolVersion = this.zzc;
            if (protocolVersion != null) {
                jSONObject.put("version", protocolVersion.toString());
            }
            if (this.zzd != null) {
                JSONArray jSONArray = new JSONArray();
                for (Transport transport : this.zzd) {
                    jSONArray.put(transport.toString());
                }
                jSONObject.put("transports", jSONArray);
            }
            return jSONObject;
        } catch (JSONException e3) {
            throw new RuntimeException(e3);
        }
    }

    public KeyHandle(@NonNull byte[] bArr, @NonNull ProtocolVersion protocolVersion, @Nullable List<Transport> list) {
        this.zza = 1;
        this.zzb = bArr;
        this.zzc = protocolVersion;
        this.zzd = list;
    }
}
