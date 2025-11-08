package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ActionCodeSettingsCreator")
public class ActionCodeSettings extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActionCodeSettings> CREATOR = new zzb();
    @SafeParcelable.Field(getter = "getUrl", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getIOSBundle", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getIOSAppStoreId", id = 3)
    private final String zzc;
    @SafeParcelable.Field(getter = "getAndroidPackageName", id = 4)
    private final String zzd;
    @SafeParcelable.Field(getter = "getAndroidInstallApp", id = 5)
    private final boolean zze;
    @SafeParcelable.Field(getter = "getAndroidMinimumVersion", id = 6)
    @Nullable
    private final String zzf;
    @SafeParcelable.Field(getter = "canHandleCodeInApp", id = 7)
    private final boolean zzg;
    @SafeParcelable.Field(getter = "getLocaleHeader", id = 8)
    private String zzh;
    @SafeParcelable.Field(getter = "getRequestType", id = 9)
    private int zzi;
    @SafeParcelable.Field(getter = "getDynamicLinkDomain", id = 10)
    private String zzj;

    public static class Builder {
        /* access modifiers changed from: private */
        public String zza;
        /* access modifiers changed from: private */
        @Nullable
        public String zzb;
        /* access modifiers changed from: private */
        public String zzc;
        /* access modifiers changed from: private */
        public boolean zzd;
        /* access modifiers changed from: private */
        @Nullable
        public String zze;
        /* access modifiers changed from: private */
        public boolean zzf;
        /* access modifiers changed from: private */
        public String zzg;

        @NonNull
        public ActionCodeSettings build() {
            if (this.zza != null) {
                return new ActionCodeSettings(this);
            }
            throw new IllegalArgumentException("Cannot build ActionCodeSettings with null URL. Call #setUrl(String) before calling build()");
        }

        @NonNull
        public String getDynamicLinkDomain() {
            return this.zzg;
        }

        public boolean getHandleCodeInApp() {
            return this.zzf;
        }

        @Nullable
        public String getIOSBundleId() {
            return this.zzb;
        }

        @NonNull
        public String getUrl() {
            return this.zza;
        }

        @NonNull
        public Builder setAndroidPackageName(@NonNull String str, boolean z2, @Nullable String str2) {
            this.zzc = str;
            this.zzd = z2;
            this.zze = str2;
            return this;
        }

        @NonNull
        public Builder setDynamicLinkDomain(@NonNull String str) {
            this.zzg = str;
            return this;
        }

        @NonNull
        public Builder setHandleCodeInApp(boolean z2) {
            this.zzf = z2;
            return this;
        }

        @NonNull
        public Builder setIOSBundleId(@Nullable String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setUrl(@NonNull String str) {
            this.zza = str;
            return this;
        }

        private Builder() {
            this.zzf = false;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    public static ActionCodeSettings zzb() {
        return new ActionCodeSettings(new Builder());
    }

    public boolean canHandleCodeInApp() {
        return this.zzg;
    }

    public boolean getAndroidInstallApp() {
        return this.zze;
    }

    @Nullable
    public String getAndroidMinimumVersion() {
        return this.zzf;
    }

    @Nullable
    public String getAndroidPackageName() {
        return this.zzd;
    }

    @Nullable
    public String getIOSBundle() {
        return this.zzb;
    }

    @NonNull
    public String getUrl() {
        return this.zza;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUrl(), false);
        SafeParcelWriter.writeString(parcel, 2, getIOSBundle(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, getAndroidPackageName(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getAndroidInstallApp());
        SafeParcelWriter.writeString(parcel, 6, getAndroidMinimumVersion(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, canHandleCodeInApp());
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zzi;
    }

    @NonNull
    public final String zzc() {
        return this.zzj;
    }

    @Nullable
    public final String zzd() {
        return this.zzc;
    }

    @NonNull
    public final String zze() {
        return this.zzh;
    }

    private ActionCodeSettings(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = null;
        this.zzd = builder.zzc;
        this.zze = builder.zzd;
        this.zzf = builder.zze;
        this.zzg = builder.zzf;
        this.zzj = builder.zzg;
    }

    public final void zza(@NonNull String str) {
        this.zzh = str;
    }

    public final void zza(int i3) {
        this.zzi = i3;
    }

    @SafeParcelable.Constructor
    public ActionCodeSettings(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) String str4, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) boolean z3, @SafeParcelable.Param(id = 8) String str6, @SafeParcelable.Param(id = 9) int i3, @SafeParcelable.Param(id = 10) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = z2;
        this.zzf = str5;
        this.zzg = z3;
        this.zzh = str6;
        this.zzi = i3;
        this.zzj = str7;
    }
}
