package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@KeepForSdkWithMembers
@ShowFirstParty
@SafeParcelable.Class(creator = "ProxyResponseCreator")
public class ProxyResponse extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;
    @SafeParcelable.Field(id = 5)
    @NonNull
    public final byte[] body;
    @SafeParcelable.Field(id = 1)
    public final int googlePlayServicesStatusCode;
    @SafeParcelable.Field(id = 2)
    @NonNull
    public final PendingIntent recoveryAction;
    @SafeParcelable.Field(id = 3)
    public final int statusCode;
    @SafeParcelable.VersionField(id = 1000)
    final int zza;
    @SafeParcelable.Field(id = 4)
    final Bundle zzb;

    @SafeParcelable.Constructor
    public ProxyResponse(@SafeParcelable.Param(id = 1000) int i3, @SafeParcelable.Param(id = 1) int i4, @SafeParcelable.Param(id = 2) PendingIntent pendingIntent, @SafeParcelable.Param(id = 3) int i5, @SafeParcelable.Param(id = 4) Bundle bundle, @SafeParcelable.Param(id = 5) byte[] bArr) {
        this.zza = i3;
        this.googlePlayServicesStatusCode = i4;
        this.statusCode = i5;
        this.zzb = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    @NonNull
    public static ProxyResponse createErrorProxyResponse(int i3, @NonNull PendingIntent pendingIntent, int i4, @NonNull Map<String, String> map, @NonNull byte[] bArr) {
        return new ProxyResponse(1, i3, pendingIntent, i4, zza(map), bArr);
    }

    private static Bundle zza(Map map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

    @NonNull
    public Map<String, String> getHeaders() {
        if (this.zzb == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (String next : this.zzb.keySet()) {
            hashMap.put(next, this.zzb.getString(next));
        }
        return hashMap;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.recoveryAction, i3, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzb, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ProxyResponse(int i3, @NonNull PendingIntent pendingIntent, int i4, @NonNull Bundle bundle, @NonNull byte[] bArr) {
        this(1, i3, pendingIntent, i4, bundle, bArr);
    }

    public ProxyResponse(int i3, @NonNull Map<String, String> map, @NonNull byte[] bArr) {
        this(1, 0, (PendingIntent) null, i3, zza(map), bArr);
    }
}
