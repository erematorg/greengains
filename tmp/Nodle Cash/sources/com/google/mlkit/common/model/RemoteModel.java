package com.google.mlkit.common.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.BaseModel;
import java.util.EnumMap;
import java.util.Map;

public abstract class RemoteModel {
    @VisibleForTesting
    @NonNull
    public static final Map zza;
    private static final Map zzb;
    @Nullable
    private final String zzc;
    @Nullable
    private final BaseModel zzd;
    private final ModelType zze;
    private String zzf;

    static {
        Class<BaseModel> cls = BaseModel.class;
        zzb = new EnumMap(cls);
        zza = new EnumMap(cls);
    }

    @KeepForSdk
    public RemoteModel(@Nullable String str, @Nullable BaseModel baseModel, @NonNull ModelType modelType) {
        Preconditions.checkArgument(TextUtils.isEmpty(str) != (baseModel != null) ? false : true, "One of cloud model name and base model cannot be empty");
        this.zzc = str;
        this.zzd = baseModel;
        this.zze = modelType;
    }

    @KeepForSdk
    public boolean baseModelHashMatches(@NonNull String str) {
        BaseModel baseModel = this.zzd;
        if (baseModel == null) {
            return false;
        }
        return str.equals(zzb.get(baseModel));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RemoteModel)) {
            return false;
        }
        RemoteModel remoteModel = (RemoteModel) obj;
        return Objects.equal(this.zzc, remoteModel.zzc) && Objects.equal(this.zzd, remoteModel.zzd) && Objects.equal(this.zze, remoteModel.zze);
    }

    @NonNull
    @KeepForSdk
    public String getModelHash() {
        return this.zzf;
    }

    @KeepForSdk
    @Nullable
    public String getModelName() {
        return this.zzc;
    }

    @NonNull
    @KeepForSdk
    public String getModelNameForBackend() {
        String str = this.zzc;
        if (str != null) {
            return str;
        }
        return (String) zza.get(this.zzd);
    }

    @NonNull
    @KeepForSdk
    public ModelType getModelType() {
        return this.zze;
    }

    @NonNull
    @KeepForSdk
    public String getUniqueModelNameForPersist() {
        String str = this.zzc;
        if (str != null) {
            return str;
        }
        return "COM.GOOGLE.BASE_".concat(String.valueOf((String) zza.get(this.zzd)));
    }

    public int hashCode() {
        return Objects.hashCode(this.zzc, this.zzd, this.zze);
    }

    @KeepForSdk
    public boolean isBaseModel() {
        return this.zzd != null;
    }

    @KeepForSdk
    public void setModelHash(@NonNull String str) {
        this.zzf = str;
    }

    @NonNull
    public String toString() {
        zzq zzb2 = zzr.zzb("RemoteModel");
        zzb2.zza("modelName", this.zzc);
        zzb2.zza("baseModel", this.zzd);
        zzb2.zza("modelType", this.zze);
        return zzb2.toString();
    }
}
