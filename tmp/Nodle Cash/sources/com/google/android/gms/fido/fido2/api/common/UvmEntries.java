package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fido.zzap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

@SafeParcelable.Class(creator = "UvmEntriesCreator")
public class UvmEntries extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<UvmEntries> CREATOR = new zzaz();
    @SafeParcelable.Field(getter = "getUvmEntryList", id = 1)
    @Nullable
    private final List zza;

    public static final class Builder {
        private final List zza = new ArrayList();

        @NonNull
        public Builder addAll(@NonNull List<UvmEntry> list) {
            zzap.zzc(list.size() + this.zza.size() <= 3);
            this.zza.addAll(list);
            return this;
        }

        @NonNull
        public Builder addUvmEntry(@Nullable UvmEntry uvmEntry) {
            if (this.zza.size() < 3) {
                this.zza.add(uvmEntry);
                return this;
            }
            throw new IllegalStateException();
        }

        @NonNull
        public UvmEntries build() {
            return new UvmEntries(this.zza);
        }
    }

    @SafeParcelable.Constructor
    public UvmEntries(@SafeParcelable.Param(id = 1) @Nullable List list) {
        this.zza = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r3 = r5.zza;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.google.android.gms.fido.fido2.api.common.UvmEntries
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.gms.fido.fido2.api.common.UvmEntries r5 = (com.google.android.gms.fido.fido2.api.common.UvmEntries) r5
            java.util.List r0 = r4.zza
            r2 = 1
            if (r0 != 0) goto L_0x0014
            java.util.List r3 = r5.zza
            if (r3 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = r2
            goto L_0x002b
        L_0x0014:
            if (r0 == 0) goto L_0x002b
            java.util.List r3 = r5.zza
            if (r3 == 0) goto L_0x002b
            boolean r0 = r0.containsAll(r3)
            if (r0 == 0) goto L_0x002b
            java.util.List r5 = r5.zza
            java.util.List r4 = r4.zza
            boolean r4 = r5.containsAll(r4)
            if (r4 == 0) goto L_0x002b
            goto L_0x0012
        L_0x002b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.UvmEntries.equals(java.lang.Object):boolean");
    }

    @Nullable
    public List<UvmEntry> getUvmEntryList() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new HashSet(this.zza));
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getUvmEntryList(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final JSONArray zza() {
        try {
            JSONArray jSONArray = new JSONArray();
            if (this.zza != null) {
                for (int i3 = 0; i3 < this.zza.size(); i3++) {
                    UvmEntry uvmEntry = (UvmEntry) this.zza.get(i3);
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(uvmEntry.getMatcherProtectionType());
                    jSONArray2.put(uvmEntry.getKeyProtectionType());
                    jSONArray2.put(uvmEntry.getMatcherProtectionType());
                    jSONArray.put(i3, jSONArray2);
                }
            }
            return jSONArray;
        } catch (JSONException e3) {
            throw new RuntimeException("Error encoding UvmEntries to JSON object", e3);
        }
    }
}
