package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.errorprone.annotations.InlineMe;

@KeepForSdk
@SafeParcelable.Class(creator = "MethodInvocationCreator")
public class MethodInvocation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new zan();
    @SafeParcelable.Field(getter = "getMethodKey", id = 1)
    private final int zaa;
    @SafeParcelable.Field(getter = "getResultStatusCode", id = 2)
    private final int zab;
    @SafeParcelable.Field(getter = "getConnectionResultStatusCode", id = 3)
    private final int zac;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 4)
    private final long zad;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 5)
    private final long zae;
    @SafeParcelable.Field(getter = "getCallingModuleId", id = 6)
    @Nullable
    private final String zaf;
    @SafeParcelable.Field(getter = "getCallingEntryPoint", id = 7)
    @Nullable
    private final String zag;
    @SafeParcelable.Field(defaultValue = "0", getter = "getServiceId", id = 8)
    private final int zah;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLatencyMillis", id = 9)
    private final int zai;

    @InlineMe(replacement = "this(methodKey, resultStatusCode, connectionResultStatusCode, startTimeMillis, endTimeMillis, callingModuleId, callingEntryPoint, serviceId, -1)")
    @KeepForSdk
    @Deprecated
    public MethodInvocation(int i3, int i4, int i5, long j2, long j3, @Nullable String str, @Nullable String str2, int i6) {
        this(i3, i4, i5, j2, j3, str, str2, i6, -1);
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int i4 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeInt(parcel, 2, this.zab);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.writeLong(parcel, 4, this.zad);
        SafeParcelWriter.writeLong(parcel, 5, this.zae);
        SafeParcelWriter.writeString(parcel, 6, this.zaf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zag, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zah);
        SafeParcelWriter.writeInt(parcel, 9, this.zai);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public MethodInvocation(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) int i4, @SafeParcelable.Param(id = 3) int i5, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) long j3, @SafeParcelable.Param(id = 6) @Nullable String str, @SafeParcelable.Param(id = 7) @Nullable String str2, @SafeParcelable.Param(id = 8) int i6, @SafeParcelable.Param(id = 9) int i7) {
        this.zaa = i3;
        this.zab = i4;
        this.zac = i5;
        this.zad = j2;
        this.zae = j3;
        this.zaf = str;
        this.zag = str2;
        this.zah = i6;
        this.zai = i7;
    }
}
