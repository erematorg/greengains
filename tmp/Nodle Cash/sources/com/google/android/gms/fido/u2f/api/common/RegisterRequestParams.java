package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SafeParcelable.Class(creator = "RegisterRequestParamsCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class RegisterRequestParams extends RequestParams {
    @NonNull
    public static final Parcelable.Creator<RegisterRequestParams> CREATOR = new zzh();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    @SafeParcelable.Field(getter = "getRequestId", id = 2)
    private final Integer zza;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    private final Double zzb;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    private final Uri zzc;
    @SafeParcelable.Field(getter = "getRegisterRequests", id = 5)
    private final List zzd;
    @SafeParcelable.Field(getter = "getRegisteredKeys", id = 6)
    private final List zze;
    @SafeParcelable.Field(getter = "getChannelIdValue", id = 7)
    private final ChannelIdValue zzf;
    @SafeParcelable.Field(getter = "getDisplayHint", id = 8)
    private final String zzg;
    private Set zzh;

    public static final class Builder {
        Integer zza;
        Double zzb;
        Uri zzc;
        List zzd;
        List zze;
        ChannelIdValue zzf;
        String zzg;

        @NonNull
        public RegisterRequestParams build() {
            return new RegisterRequestParams(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        @NonNull
        public Builder setAppId(@NonNull Uri uri) {
            this.zzc = uri;
            return this;
        }

        @NonNull
        public Builder setChannelIdValue(@NonNull ChannelIdValue channelIdValue) {
            this.zzf = channelIdValue;
            return this;
        }

        @NonNull
        public Builder setDisplayHint(@NonNull String str) {
            this.zzg = str;
            return this;
        }

        @NonNull
        public Builder setRegisterRequests(@NonNull List<RegisterRequest> list) {
            this.zzd = list;
            return this;
        }

        @NonNull
        public Builder setRegisteredKeys(@NonNull List<RegisteredKey> list) {
            this.zze = list;
            return this;
        }

        @NonNull
        public Builder setRequestId(@NonNull Integer num) {
            this.zza = num;
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@NonNull Double d2) {
            this.zzb = d2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public RegisterRequestParams(@SafeParcelable.Param(id = 2) Integer num, @SafeParcelable.Param(id = 3) Double d2, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) List list, @SafeParcelable.Param(id = 6) List list2, @SafeParcelable.Param(id = 7) ChannelIdValue channelIdValue, @SafeParcelable.Param(id = 8) String str) {
        this.zza = num;
        this.zzb = d2;
        this.zzc = uri;
        boolean z2 = false;
        Preconditions.checkArgument(list != null && !list.isEmpty(), "empty list of register requests is provided");
        this.zzd = list;
        this.zze = list2;
        this.zzf = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RegisterRequest registerRequest = (RegisterRequest) it.next();
            Preconditions.checkArgument((uri == null && registerRequest.getAppId() == null) ? false : true, "register request has null appId and no request appId is provided");
            if (registerRequest.getAppId() != null) {
                hashSet.add(Uri.parse(registerRequest.getAppId()));
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it2.next();
            Preconditions.checkArgument((uri == null && registeredKey.getAppId() == null) ? false : true, "registered key has null appId and no request appId is provided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        this.zzh = hashSet;
        Preconditions.checkArgument((str == null || str.length() <= 80) ? true : z2, "Display Hint cannot be longer than 80 characters");
        this.zzg = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r3 = r5.zze;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.fido.u2f.api.common.RegisterRequestParams
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.android.gms.fido.u2f.api.common.RegisterRequestParams r5 = (com.google.android.gms.fido.u2f.api.common.RegisterRequestParams) r5
            java.lang.Integer r1 = r4.zza
            java.lang.Integer r3 = r5.zza
            boolean r1 = com.google.android.gms.common.internal.Objects.equal(r1, r3)
            if (r1 == 0) goto L_0x0067
            java.lang.Double r1 = r4.zzb
            java.lang.Double r3 = r5.zzb
            boolean r1 = com.google.android.gms.common.internal.Objects.equal(r1, r3)
            if (r1 == 0) goto L_0x0067
            android.net.Uri r1 = r4.zzc
            android.net.Uri r3 = r5.zzc
            boolean r1 = com.google.android.gms.common.internal.Objects.equal(r1, r3)
            if (r1 == 0) goto L_0x0067
            java.util.List r1 = r4.zzd
            java.util.List r3 = r5.zzd
            boolean r1 = com.google.android.gms.common.internal.Objects.equal(r1, r3)
            if (r1 == 0) goto L_0x0067
            java.util.List r1 = r4.zze
            if (r1 != 0) goto L_0x003c
            java.util.List r3 = r5.zze
            if (r3 == 0) goto L_0x0052
        L_0x003c:
            if (r1 == 0) goto L_0x0067
            java.util.List r3 = r5.zze
            if (r3 == 0) goto L_0x0067
            boolean r1 = r1.containsAll(r3)
            if (r1 == 0) goto L_0x0067
            java.util.List r1 = r5.zze
            java.util.List r3 = r4.zze
            boolean r1 = r1.containsAll(r3)
            if (r1 == 0) goto L_0x0067
        L_0x0052:
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r1 = r4.zzf
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r3 = r5.zzf
            boolean r1 = com.google.android.gms.common.internal.Objects.equal(r1, r3)
            if (r1 == 0) goto L_0x0067
            java.lang.String r4 = r4.zzg
            java.lang.String r5 = r5.zzg
            boolean r4 = com.google.android.gms.common.internal.Objects.equal(r4, r5)
            if (r4 == 0) goto L_0x0067
            return r0
        L_0x0067:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.RegisterRequestParams.equals(java.lang.Object):boolean");
    }

    @NonNull
    public Set<Uri> getAllAppIds() {
        return this.zzh;
    }

    @NonNull
    public Uri getAppId() {
        return this.zzc;
    }

    @NonNull
    public ChannelIdValue getChannelIdValue() {
        return this.zzf;
    }

    @NonNull
    public String getDisplayHint() {
        return this.zzg;
    }

    @NonNull
    public List<RegisterRequest> getRegisterRequests() {
        return this.zzd;
    }

    @NonNull
    public List<RegisteredKey> getRegisteredKeys() {
        return this.zze;
    }

    @NonNull
    public Integer getRequestId() {
        return this.zza;
    }

    @NonNull
    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzc, this.zzb, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 2, getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getAppId(), i3, false);
        SafeParcelWriter.writeTypedList(parcel, 5, getRegisterRequests(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getChannelIdValue(), i3, false);
        SafeParcelWriter.writeString(parcel, 8, getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
