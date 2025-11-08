package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@KeepForSdk
@SafeParcelable.Class(creator = "ApiFeatureRequestCreator")
public class ApiFeatureRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ApiFeatureRequest> CREATOR = new zac();
    private static final Comparator zaa = zab.zaa;
    @SafeParcelable.Field(getter = "getApiFeatures", id = 1)
    private final List zab;
    @SafeParcelable.Field(getter = "getIsUrgent", id = 2)
    private final boolean zac;
    @SafeParcelable.Field(getter = "getFeatureRequestSessionId", id = 3)
    @Nullable
    private final String zad;
    @SafeParcelable.Field(getter = "getCallingPackage", id = 4)
    @Nullable
    private final String zae;

    @SafeParcelable.Constructor
    public ApiFeatureRequest(@SafeParcelable.Param(id = 1) @NonNull List list, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) @Nullable String str, @SafeParcelable.Param(id = 4) @Nullable String str2) {
        Preconditions.checkNotNull(list);
        this.zab = list;
        this.zac = z2;
        this.zad = str;
        this.zae = str2;
    }

    @NonNull
    @KeepForSdk
    public static ApiFeatureRequest fromModuleInstallRequest(@NonNull ModuleInstallRequest moduleInstallRequest) {
        return zaa(moduleInstallRequest.getApis(), true);
    }

    public static ApiFeatureRequest zaa(List list, boolean z2) {
        TreeSet treeSet = new TreeSet(zaa);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Collections.addAll(treeSet, ((OptionalModuleApi) it.next()).getOptionalFeatures());
        }
        return new ApiFeatureRequest(new ArrayList(treeSet), z2, (String) null, (String) null);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof ApiFeatureRequest)) {
            return false;
        }
        ApiFeatureRequest apiFeatureRequest = (ApiFeatureRequest) obj;
        return this.zac == apiFeatureRequest.zac && Objects.equal(this.zab, apiFeatureRequest.zab) && Objects.equal(this.zad, apiFeatureRequest.zad) && Objects.equal(this.zae, apiFeatureRequest.zae);
    }

    @NonNull
    @KeepForSdk
    public List<Feature> getApiFeatures() {
        return this.zab;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zac), this.zab, this.zad, this.zae);
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getApiFeatures(), false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zac);
        SafeParcelWriter.writeString(parcel, 3, this.zad, false);
        SafeParcelWriter.writeString(parcel, 4, this.zae, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
