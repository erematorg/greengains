package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.ui.autofill.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.internal.zzo;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "VerifyAssertionRequestCreator")
@SafeParcelable.Reserved({1})
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzags  reason: invalid package */
public final class zzags extends AbstractSafeParcelable implements zzacr {
    public static final Parcelable.Creator<zzags> CREATOR = new zzagv();
    @SafeParcelable.Field(getter = "getRequestUri", id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getCurrentIdToken", id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "getIdToken", id = 4)
    private String zzc;
    @SafeParcelable.Field(getter = "getAccessToken", id = 5)
    private String zzd;
    @SafeParcelable.Field(getter = "getProviderId", id = 6)
    private String zze;
    @SafeParcelable.Field(getter = "getEmail", id = 7)
    @Nullable
    private String zzf;
    @SafeParcelable.Field(getter = "getPostBody", id = 8)
    private String zzg;
    @SafeParcelable.Field(getter = "getOauthTokenSecret", id = 9)
    private String zzh;
    @SafeParcelable.Field(getter = "getReturnSecureToken", id = 10)
    private boolean zzi;
    @SafeParcelable.Field(getter = "getAutoCreate", id = 11)
    private boolean zzj;
    @SafeParcelable.Field(getter = "getAuthCode", id = 12)
    private String zzk;
    @SafeParcelable.Field(getter = "getSessionId", id = 13)
    private String zzl;
    @SafeParcelable.Field(getter = "getIdpResponseUrl", id = 14)
    private String zzm;
    @SafeParcelable.Field(getter = "getTenantId", id = 15)
    private String zzn;
    @SafeParcelable.Field(getter = "getReturnIdpCredential", id = 16)
    private boolean zzo;
    @SafeParcelable.Field(getter = "getPendingToken", id = 17)
    @Nullable
    private String zzp;

    public zzags() {
        this.zzi = true;
        this.zzj = true;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzn, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeString(parcel, 17, this.zzp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzags zza(boolean z2) {
        this.zzj = false;
        return this;
    }

    public final zzags zzb(boolean z2) {
        this.zzo = true;
        return this;
    }

    public final zzags zzc(boolean z2) {
        this.zzi = true;
        return this;
    }

    public final zzags zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzags zzb(@Nullable String str) {
        this.zzn = str;
        return this;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("autoCreate", this.zzj);
        jSONObject.put("returnSecureToken", this.zzi);
        String str = this.zzb;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        String str2 = this.zzg;
        if (str2 != null) {
            jSONObject.put("postBody", str2);
        }
        String str3 = this.zzn;
        if (str3 != null) {
            jSONObject.put("tenantId", str3);
        }
        String str4 = this.zzp;
        if (str4 != null) {
            jSONObject.put("pendingToken", str4);
        }
        if (!TextUtils.isEmpty(this.zzl)) {
            jSONObject.put("sessionId", this.zzl);
        }
        if (!TextUtils.isEmpty(this.zzm)) {
            jSONObject.put("requestUri", this.zzm);
        } else {
            String str5 = this.zza;
            if (str5 != null) {
                jSONObject.put("requestUri", str5);
            }
        }
        jSONObject.put("returnIdpCredential", this.zzo);
        return jSONObject.toString();
    }

    public zzags(zzo zzo2, String str) {
        Preconditions.checkNotNull(zzo2);
        this.zzl = Preconditions.checkNotEmpty(zzo2.zzd());
        this.zzm = Preconditions.checkNotEmpty(str);
        this.zze = Preconditions.checkNotEmpty(zzo2.zzc());
        this.zzi = true;
        this.zzg = "providerId=" + this.zze;
    }

    public zzags(@Nullable String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.zza = "http://localhost";
        this.zzc = str;
        this.zzd = str2;
        this.zzh = str5;
        this.zzk = str6;
        this.zzn = str7;
        this.zzp = str8;
        this.zzi = true;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.zzd) || !TextUtils.isEmpty(this.zzk)) {
            this.zze = Preconditions.checkNotEmpty(str3);
            this.zzf = null;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.zzc)) {
                sb.append("id_token=");
                sb.append(this.zzc);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                sb.append("access_token=");
                sb.append(this.zzd);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzf)) {
                sb.append("identifier=");
                sb.append(this.zzf);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzh)) {
                sb.append("oauth_token_secret=");
                sb.append(this.zzh);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzk)) {
                sb.append("code=");
                sb.append(this.zzk);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(str9)) {
                a.o(sb, "nonce=", str9, "&");
            }
            sb.append("providerId=");
            sb.append(this.zze);
            this.zzg = sb.toString();
            this.zzj = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    @SafeParcelable.Constructor
    public zzags(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) String str8, @SafeParcelable.Param(id = 10) boolean z2, @SafeParcelable.Param(id = 11) boolean z3, @SafeParcelable.Param(id = 12) String str9, @SafeParcelable.Param(id = 13) String str10, @SafeParcelable.Param(id = 14) String str11, @SafeParcelable.Param(id = 15) String str12, @SafeParcelable.Param(id = 16) boolean z4, @SafeParcelable.Param(id = 17) String str13) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
        this.zzh = str8;
        this.zzi = z2;
        this.zzj = z3;
        this.zzk = str9;
        this.zzl = str10;
        this.zzm = str11;
        this.zzn = str12;
        this.zzo = z4;
        this.zzp = str13;
    }
}
