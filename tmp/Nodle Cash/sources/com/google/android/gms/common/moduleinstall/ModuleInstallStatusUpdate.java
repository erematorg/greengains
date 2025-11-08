package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class(creator = "ModuleInstallStatusUpdateCreator")
public class ModuleInstallStatusUpdate extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ModuleInstallStatusUpdate> CREATOR = new zae();
    @SafeParcelable.Field(getter = "getSessionId", id = 1)
    private final int zaa;
    @SafeParcelable.Field(getter = "getInstallState", id = 2)
    @InstallState
    private final int zab;
    @SafeParcelable.Field(getter = "getBytesDownloaded", id = 3)
    @Nullable
    private final Long zac;
    @SafeParcelable.Field(getter = "getTotalBytesToDownload", id = 4)
    @Nullable
    private final Long zad;
    @SafeParcelable.Field(getter = "getErrorCode", id = 5)
    private final int zae;
    @Nullable
    private final ProgressInfo zaf;

    @Retention(RetentionPolicy.CLASS)
    public @interface InstallState {
        public static final int STATE_CANCELED = 3;
        public static final int STATE_COMPLETED = 4;
        public static final int STATE_DOWNLOADING = 2;
        public static final int STATE_DOWNLOAD_PAUSED = 7;
        public static final int STATE_FAILED = 5;
        public static final int STATE_INSTALLING = 6;
        public static final int STATE_PENDING = 1;
        public static final int STATE_UNKNOWN = 0;
    }

    public static class ProgressInfo {
        private final long zaa;
        private final long zab;

        public ProgressInfo(long j2, long j3) {
            Preconditions.checkNotZero(j3);
            this.zaa = j2;
            this.zab = j3;
        }

        public long getBytesDownloaded() {
            return this.zaa;
        }

        public long getTotalBytesToDownload() {
            return this.zab;
        }
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleInstallStatusUpdate(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @InstallState int i4, @SafeParcelable.Param(id = 3) @Nullable Long l2, @SafeParcelable.Param(id = 4) @Nullable Long l3, @SafeParcelable.Param(id = 5) int i5) {
        this.zaa = i3;
        this.zab = i4;
        this.zac = l2;
        this.zad = l3;
        this.zae = i5;
        this.zaf = (l2 == null || l3 == null || l3.longValue() == 0) ? null : new ProgressInfo(l2.longValue(), l3.longValue());
    }

    public int getErrorCode() {
        return this.zae;
    }

    @InstallState
    public int getInstallState() {
        return this.zab;
    }

    @Nullable
    public ProgressInfo getProgressInfo() {
        return this.zaf;
    }

    public int getSessionId() {
        return this.zaa;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getSessionId());
        SafeParcelWriter.writeInt(parcel, 2, getInstallState());
        SafeParcelWriter.writeLongObject(parcel, 3, this.zac, false);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zad, false);
        SafeParcelWriter.writeInt(parcel, 5, getErrorCode());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
