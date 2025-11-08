package com.google.android.gms.auth.api.proxy;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;
import androidx.annotation.NonNull;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@KeepForSdkWithMembers
@ShowFirstParty
@SafeParcelable.Class(creator = "ProxyRequestCreator")
public class ProxyRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ProxyRequest> CREATOR = new zza();
    public static final int HTTP_METHOD_DELETE = 3;
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_HEAD = 4;
    public static final int HTTP_METHOD_OPTIONS = 5;
    public static final int HTTP_METHOD_PATCH = 7;
    public static final int HTTP_METHOD_POST = 1;
    public static final int HTTP_METHOD_PUT = 2;
    public static final int HTTP_METHOD_TRACE = 6;
    public static final int LAST_CODE = 7;
    public static final int VERSION_CODE = 2;
    @SafeParcelable.Field(id = 4)
    @NonNull
    public final byte[] body;
    @SafeParcelable.Field(id = 2)
    public final int httpMethod;
    @SafeParcelable.Field(id = 3)
    public final long timeoutMillis;
    @SafeParcelable.Field(id = 1)
    @NonNull
    public final String url;
    @SafeParcelable.VersionField(id = 1000)
    final int zza;
    @SafeParcelable.Field(id = 5)
    final Bundle zzb;

    @KeepForSdkWithMembers
    @ShowFirstParty
    public static class Builder {
        private final String zza;
        private int zzb = ProxyRequest.HTTP_METHOD_GET;
        private long zzc = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        private byte[] zzd = new byte[0];
        private final Bundle zze = new Bundle();

        public Builder(@NonNull String str) {
            Preconditions.checkNotEmpty(str);
            if (Patterns.WEB_URL.matcher(str).matches()) {
                this.zza = str;
                return;
            }
            throw new IllegalArgumentException(a.l("The supplied url [ ", str, "] is not match Patterns.WEB_URL!"));
        }

        @NonNull
        public ProxyRequest build() {
            if (this.zzd == null) {
                this.zzd = new byte[0];
            }
            return new ProxyRequest(2, this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        @NonNull
        public Builder putHeader(@NonNull String str, @NonNull String str2) {
            Preconditions.checkNotEmpty(str, "Header name cannot be null or empty!");
            Bundle bundle = this.zze;
            if (str2 == null) {
                str2 = "";
            }
            bundle.putString(str, str2);
            return this;
        }

        @NonNull
        public Builder setBody(@NonNull byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        @NonNull
        public Builder setHttpMethod(int i3) {
            boolean z2 = false;
            if (i3 >= 0 && i3 <= ProxyRequest.LAST_CODE) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "Unrecognized http method code.");
            this.zzb = i3;
            return this;
        }

        @NonNull
        public Builder setTimeoutMillis(long j2) {
            Preconditions.checkArgument(j2 >= 0, "The specified timeout must be non-negative.");
            this.zzc = j2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public ProxyRequest(@SafeParcelable.Param(id = 1000) int i3, @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i4, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) Bundle bundle) {
        this.zza = i3;
        this.url = str;
        this.httpMethod = i4;
        this.timeoutMillis = j2;
        this.body = bArr;
        this.zzb = bundle;
    }

    @NonNull
    public Map<String, String> getHeaderMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.zzb.size());
        for (String next : this.zzb.keySet()) {
            String string = this.zzb.getString(next);
            if (string == null) {
                string = "";
            }
            linkedHashMap.put(next, string);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    @NonNull
    public String toString() {
        String str = this.url;
        int i3 = this.httpMethod;
        return "ProxyRequest[ url: " + str + ", method: " + i3 + " ]";
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeInt(parcel, 2, this.httpMethod);
        SafeParcelWriter.writeLong(parcel, 3, this.timeoutMillis);
        SafeParcelWriter.writeByteArray(parcel, 4, this.body, false);
        SafeParcelWriter.writeBundle(parcel, 5, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
