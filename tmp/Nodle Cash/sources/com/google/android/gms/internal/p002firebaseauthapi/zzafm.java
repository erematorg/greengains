package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GetTokenResponseCreator")
@SafeParcelable.Reserved({1})
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafm  reason: invalid package */
public class zzafm extends AbstractSafeParcelable implements zzacu<zzafm> {
    public static final Parcelable.Creator<zzafm> CREATOR = new zzafp();
    private static final String zza = "zzafm";
    @SafeParcelable.Field(getter = "getRefreshToken", id = 2)
    private String zzb;
    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    private String zzc;
    @SafeParcelable.Field(getter = "getExpiresIn", id = 4)
    private Long zzd;
    @SafeParcelable.Field(getter = "getTokenType", id = 5)
    private String zze;
    @SafeParcelable.Field(getter = "getIssuedAt", id = 6)
    private Long zzf;

    public zzafm() {
        this.zzf = Long.valueOf(System.currentTimeMillis());
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzafm zza(String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("refresh_token"));
            this.zzc = Strings.emptyToNull(jSONObject.optString("access_token"));
            this.zzd = Long.valueOf(jSONObject.optLong("expires_in", 0));
            this.zze = Strings.emptyToNull(jSONObject.optString("token_type"));
            this.zzf = Long.valueOf(System.currentTimeMillis());
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLongObject(parcel, 4, Long.valueOf(zza()), false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        Long l2 = this.zzf;
        l2.longValue();
        SafeParcelWriter.writeLongObject(parcel, 6, l2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final long zza() {
        Long l2 = this.zzd;
        if (l2 == null) {
            return 0;
        }
        return l2.longValue();
    }

    public final long zzb() {
        return this.zzf.longValue();
    }

    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zzb);
            jSONObject.put("access_token", this.zzc);
            jSONObject.put("expires_in", this.zzd);
            jSONObject.put("token_type", this.zze);
            jSONObject.put("issued_at", this.zzf);
            return jSONObject.toString();
        } catch (JSONException e3) {
            Log.d(zza, "Failed to convert GetTokenResponse to JSON");
            throw new zzxy(e3);
        }
    }

    public final boolean zzg() {
        return DefaultClock.getInstance().currentTimeMillis() + 300000 < (this.zzd.longValue() * 1000) + this.zzf.longValue();
    }

    public static zzafm zzb(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzafm zzafm = new zzafm();
            zzafm.zzb = jSONObject.optString("refresh_token", (String) null);
            zzafm.zzc = jSONObject.optString("access_token", (String) null);
            zzafm.zzd = Long.valueOf(jSONObject.optLong("expires_in"));
            zzafm.zze = jSONObject.optString("token_type", (String) null);
            zzafm.zzf = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzafm;
        } catch (JSONException e3) {
            Log.d(zza, "Failed to read GetTokenResponse from JSONObject");
            throw new zzxy(e3);
        }
    }

    public final void zzc(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    public zzafm(String str, String str2, Long l2, String str3) {
        this(str, str2, l2, str3, Long.valueOf(System.currentTimeMillis()));
    }

    @SafeParcelable.Constructor
    public zzafm(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) Long l2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) Long l3) {
        this.zzb = str;
        this.zzc = str2;
        this.zzd = l2;
        this.zze = str3;
        this.zzf = l3;
    }

    public final String zzd() {
        return this.zzb;
    }
}
