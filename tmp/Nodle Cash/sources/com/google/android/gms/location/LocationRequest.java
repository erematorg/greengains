package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.work.impl.a;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.checkerframework.dataflow.qual.Pure;

@SafeParcelable.Class(creator = "LocationRequestCreator")
@SafeParcelable.Reserved({4, 5, 14, 1000})
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzaf();
    @Deprecated
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    @Deprecated
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    @Deprecated
    public static final int PRIORITY_LOW_POWER = 104;
    @Deprecated
    public static final int PRIORITY_NO_POWER = 105;
    @SafeParcelable.Field(defaultValueUnchecked = "Priority.PRIORITY_BALANCED_POWER_ACCURACY", getter = "getPriority", id = 1)
    private int zza;
    @SafeParcelable.Field(defaultValue = "3600000", getter = "getIntervalMillis", id = 2)
    private long zzb;
    @SafeParcelable.Field(defaultValue = "600000", getter = "getMinUpdateIntervalMillis", id = 3)
    private long zzc;
    @SafeParcelable.Field(defaultValue = "0", getter = "getMaxUpdateDelayMillis", id = 8)
    private long zzd;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getDurationMillis", id = 10)
    private long zze;
    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MAX_VALUE", getter = "getMaxUpdates", id = 6)
    private int zzf;
    @SafeParcelable.Field(defaultValue = "0", getter = "getMinUpdateDistanceMeters", id = 7)
    private float zzg;
    @SafeParcelable.Field(defaultValue = "false", getter = "isWaitForAccurateLocation", id = 9)
    private boolean zzh;
    @SafeParcelable.Field(defaultValueUnchecked = "-1", getter = "getMaxUpdateAgeMillis", id = 11)
    private long zzi;
    @SafeParcelable.Field(defaultValueUnchecked = "Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 12)
    private final int zzj;
    @SafeParcelable.Field(defaultValueUnchecked = "ThrottleBehavior.THROTTLE_BACKGROUND", getter = "getThrottleBehavior", id = 13)
    private final int zzk;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 15)
    private final boolean zzl;
    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.WorkSource()", getter = "getWorkSource", id = 16)
    private final WorkSource zzm;
    @SafeParcelable.Field(getter = "getImpersonation", id = 17)
    @Nullable
    private final zze zzn;

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocationRequest() {
        /*
            r22 = this;
            r0 = r22
            android.os.WorkSource r1 = new android.os.WorkSource
            r20 = r1
            r1.<init>()
            r21 = 0
            r1 = 102(0x66, float:1.43E-43)
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            r4 = 600000(0x927c0, double:2.964394E-318)
            r6 = 0
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r8 = r10
            r12 = 2147483647(0x7fffffff, float:NaN)
            r13 = 0
            r14 = 1
            r15 = 3600000(0x36ee80, double:1.7786363E-317)
            r17 = 0
            r18 = 0
            r19 = 0
            r0.<init>(r1, r2, r4, r6, r8, r10, r12, r13, r14, r15, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationRequest.<init>():void");
    }

    @NonNull
    @Deprecated
    public static LocationRequest create() {
        WorkSource workSource = r1;
        WorkSource workSource2 = new WorkSource();
        return new LocationRequest(102, DateUtils.MILLIS_PER_HOUR, 600000, 0, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, true, DateUtils.MILLIS_PER_HOUR, 0, 0, false, workSource, (zze) null);
    }

    private static String zze(long j2) {
        return j2 == Long.MAX_VALUE ? "∞" : zzeo.zzb(j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) obj;
            return this.zza == locationRequest.zza && (isPassive() || this.zzb == locationRequest.zzb) && this.zzc == locationRequest.zzc && isBatched() == locationRequest.isBatched() && ((!isBatched() || this.zzd == locationRequest.zzd) && this.zze == locationRequest.zze && this.zzf == locationRequest.zzf && this.zzg == locationRequest.zzg && this.zzh == locationRequest.zzh && this.zzj == locationRequest.zzj && this.zzk == locationRequest.zzk && this.zzl == locationRequest.zzl && this.zzm.equals(locationRequest.zzm) && Objects.equal(this.zzn, locationRequest.zzn));
        }
    }

    @Pure
    public long getDurationMillis() {
        return this.zze;
    }

    @Deprecated
    @Pure
    public long getExpirationTime() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.zze;
        long j3 = elapsedRealtime + j2;
        if (((elapsedRealtime ^ j3) & (j2 ^ j3)) < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    @Deprecated
    @Pure
    public long getFastestInterval() {
        return getMinUpdateIntervalMillis();
    }

    @Pure
    public int getGranularity() {
        return this.zzj;
    }

    @Deprecated
    @Pure
    public long getInterval() {
        return getIntervalMillis();
    }

    @Pure
    public long getIntervalMillis() {
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.zzi;
    }

    @Pure
    public long getMaxUpdateDelayMillis() {
        return this.zzd;
    }

    @Pure
    public int getMaxUpdates() {
        return this.zzf;
    }

    @Deprecated
    @Pure
    public long getMaxWaitTime() {
        return Math.max(this.zzd, this.zzb);
    }

    @Pure
    public float getMinUpdateDistanceMeters() {
        return this.zzg;
    }

    @Pure
    public long getMinUpdateIntervalMillis() {
        return this.zzc;
    }

    @Deprecated
    @Pure
    public int getNumUpdates() {
        return getMaxUpdates();
    }

    @Pure
    public int getPriority() {
        return this.zza;
    }

    @Deprecated
    @Pure
    public float getSmallestDisplacement() {
        return getMinUpdateDistanceMeters();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Long.valueOf(this.zzb), Long.valueOf(this.zzc), this.zzm);
    }

    @Pure
    public boolean isBatched() {
        long j2 = this.zzd;
        return j2 > 0 && (j2 >> 1) >= this.zzb;
    }

    @Deprecated
    @Pure
    public boolean isFastestIntervalExplicitlySet() {
        return true;
    }

    @Pure
    public boolean isPassive() {
        return this.zza == 105;
    }

    public boolean isWaitForAccurateLocation() {
        return this.zzh;
    }

    @NonNull
    @Deprecated
    public LocationRequest setExpirationDuration(long j2) {
        Preconditions.checkArgument(j2 > 0, "durationMillis must be greater than 0");
        this.zze = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setExpirationTime(long j2) {
        this.zze = Math.max(1, j2 - SystemClock.elapsedRealtime());
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setFastestInterval(long j2) {
        Preconditions.checkArgument(j2 >= 0, "illegal fastest interval: %d", Long.valueOf(j2));
        this.zzc = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setInterval(long j2) {
        Preconditions.checkArgument(j2 >= 0, "intervalMillis must be greater than or equal to 0");
        long j3 = this.zzc;
        long j4 = this.zzb;
        if (j3 == j4 / 6) {
            this.zzc = j2 / 6;
        }
        if (this.zzi == j4) {
            this.zzi = j2;
        }
        this.zzb = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setMaxWaitTime(long j2) {
        Preconditions.checkArgument(j2 >= 0, "illegal max wait time: %d", Long.valueOf(j2));
        this.zzd = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setNumUpdates(int i3) {
        if (i3 > 0) {
            this.zzf = i3;
            return this;
        }
        throw new IllegalArgumentException(a.u(new StringBuilder(String.valueOf(i3).length() + 20), "invalid numUpdates: ", i3));
    }

    @NonNull
    @Deprecated
    public LocationRequest setPriority(int i3) {
        zzan.zza(i3);
        this.zza = i3;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setSmallestDisplacement(float f2) {
        if (f2 >= 0.0f) {
            this.zzg = f2;
            return this;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(f2).length() + 22);
        sb.append("invalid displacement: ");
        sb.append(f2);
        throw new IllegalArgumentException(sb.toString());
    }

    @NonNull
    @Deprecated
    public LocationRequest setWaitForAccurateLocation(boolean z2) {
        this.zzh = z2;
        return this;
    }

    @NonNull
    public String toString() {
        StringBuilder p2 = A.a.p("Request[");
        if (isPassive()) {
            p2.append(zzan.zzb(this.zza));
            if (this.zzd > 0) {
                p2.append("/");
                zzeo.zzc(this.zzd, p2);
            }
        } else {
            p2.append("@");
            if (isBatched()) {
                zzeo.zzc(this.zzb, p2);
                p2.append("/");
                zzeo.zzc(this.zzd, p2);
            } else {
                zzeo.zzc(this.zzb, p2);
            }
            p2.append(StringUtils.SPACE);
            p2.append(zzan.zzb(this.zza));
        }
        if (isPassive() || this.zzc != this.zzb) {
            p2.append(", minUpdateInterval=");
            p2.append(zze(this.zzc));
        }
        if (((double) this.zzg) > 0.0d) {
            p2.append(", minUpdateDistance=");
            p2.append(this.zzg);
        }
        if (!isPassive() ? this.zzi != this.zzb : this.zzi != Long.MAX_VALUE) {
            p2.append(", maxUpdateAge=");
            p2.append(zze(this.zzi));
        }
        if (this.zze != Long.MAX_VALUE) {
            p2.append(", duration=");
            zzeo.zzc(this.zze, p2);
        }
        if (this.zzf != Integer.MAX_VALUE) {
            p2.append(", maxUpdates=");
            p2.append(this.zzf);
        }
        if (this.zzk != 0) {
            p2.append(", ");
            p2.append(zzar.zzb(this.zzk));
        }
        if (this.zzj != 0) {
            p2.append(", ");
            p2.append(zzq.zzb(this.zzj));
        }
        if (this.zzh) {
            p2.append(", waitForAccurateLocation");
        }
        if (this.zzl) {
            p2.append(", bypass");
        }
        if (!WorkSourceUtil.isEmpty(this.zzm)) {
            p2.append(", ");
            p2.append(this.zzm);
        }
        if (this.zzn != null) {
            p2.append(", impersonation=");
            p2.append(this.zzn);
        }
        p2.append(AbstractJsonLexerKt.END_LIST);
        return p2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getPriority());
        SafeParcelWriter.writeLong(parcel, 2, getIntervalMillis());
        SafeParcelWriter.writeLong(parcel, 3, getMinUpdateIntervalMillis());
        SafeParcelWriter.writeInt(parcel, 6, getMaxUpdates());
        SafeParcelWriter.writeFloat(parcel, 7, getMinUpdateDistanceMeters());
        SafeParcelWriter.writeLong(parcel, 8, getMaxUpdateDelayMillis());
        SafeParcelWriter.writeBoolean(parcel, 9, isWaitForAccurateLocation());
        SafeParcelWriter.writeLong(parcel, 10, getDurationMillis());
        SafeParcelWriter.writeLong(parcel, 11, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 12, getGranularity());
        SafeParcelWriter.writeInt(parcel, 13, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 15, this.zzl);
        SafeParcelWriter.writeParcelable(parcel, 16, this.zzm, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.zzn, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final int zza() {
        return this.zzk;
    }

    @Pure
    public final boolean zzb() {
        return this.zzl;
    }

    @NonNull
    @Pure
    public final WorkSource zzc() {
        return this.zzm;
    }

    @Nullable
    @Pure
    public final zze zzd() {
        return this.zzn;
    }

    public static final class Builder {
        public static final long IMPLICIT_MAX_UPDATE_AGE = -1;
        public static final long IMPLICIT_MIN_UPDATE_INTERVAL = -1;
        private int zza;
        private long zzb;
        private long zzc;
        private long zzd;
        private long zze;
        private int zzf;
        private float zzg;
        private boolean zzh;
        private long zzi;
        private int zzj;
        private int zzk;
        private boolean zzl;
        @Nullable
        private WorkSource zzm;
        @Nullable
        private zze zzn;

        public Builder(int i3, long j2) {
            this(j2);
            setPriority(i3);
        }

        @NonNull
        public LocationRequest build() {
            int i3 = this.zza;
            long j2 = this.zzb;
            long j3 = this.zzc;
            if (j3 == -1) {
                j3 = j2;
            } else if (i3 != 105) {
                j3 = Math.min(j3, j2);
            }
            long max = Math.max(this.zzd, this.zzb);
            long j4 = this.zze;
            int i4 = this.zzf;
            float f2 = this.zzg;
            boolean z2 = this.zzh;
            long j5 = this.zzi;
            long j6 = j5 == -1 ? this.zzb : j5;
            int i5 = this.zzj;
            int i6 = this.zzk;
            boolean z3 = this.zzl;
            WorkSource workSource = r7;
            WorkSource workSource2 = new WorkSource(this.zzm);
            return new LocationRequest(i3, j2, j3, max, Long.MAX_VALUE, j4, i4, f2, z2, j6, i5, i6, z3, workSource, this.zzn);
        }

        @NonNull
        public Builder setDurationMillis(long j2) {
            Preconditions.checkArgument(j2 > 0, "durationMillis must be greater than 0");
            this.zze = j2;
            return this;
        }

        @NonNull
        public Builder setGranularity(int i3) {
            zzq.zza(i3);
            this.zzj = i3;
            return this;
        }

        @NonNull
        public Builder setIntervalMillis(long j2) {
            Preconditions.checkArgument(j2 >= 0, "intervalMillis must be greater than or equal to 0");
            this.zzb = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j2) {
            boolean z2 = true;
            if (j2 != -1 && j2 < 0) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "maxUpdateAgeMillis must be greater than or equal to 0, or IMPLICIT_MAX_UPDATE_AGE");
            this.zzi = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateDelayMillis(long j2) {
            Preconditions.checkArgument(j2 >= 0, "maxUpdateDelayMillis must be greater than or equal to 0");
            this.zzd = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdates(int i3) {
            Preconditions.checkArgument(i3 > 0, "maxUpdates must be greater than 0");
            this.zzf = i3;
            return this;
        }

        @NonNull
        public Builder setMinUpdateDistanceMeters(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f, "minUpdateDistanceMeters must be greater than or equal to 0");
            this.zzg = f2;
            return this;
        }

        @NonNull
        public Builder setMinUpdateIntervalMillis(long j2) {
            boolean z2 = true;
            if (j2 != -1 && j2 < 0) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "minUpdateIntervalMillis must be greater than or equal to 0, or IMPLICIT_MIN_UPDATE_INTERVAL");
            this.zzc = j2;
            return this;
        }

        @NonNull
        public Builder setPriority(int i3) {
            zzan.zza(i3);
            this.zza = i3;
            return this;
        }

        @NonNull
        public Builder setWaitForAccurateLocation(boolean z2) {
            this.zzh = z2;
            return this;
        }

        @NonNull
        public final Builder zza(int i3) {
            zzar.zza(i3);
            this.zzk = i3;
            return this;
        }

        @RequiresPermission(anyOf = {"android.permission.WRITE_SECURE_SETTINGS", "android.permission.LOCATION_BYPASS"})
        @NonNull
        public final Builder zzb(boolean z2) {
            this.zzl = z2;
            return this;
        }

        @RequiresPermission("android.permission.UPDATE_DEVICE_STATS")
        @NonNull
        public final Builder zzc(@Nullable WorkSource workSource) {
            this.zzm = workSource;
            return this;
        }

        public Builder(long j2) {
            this.zza = 102;
            this.zzc = -1;
            this.zzd = 0;
            this.zze = Long.MAX_VALUE;
            this.zzf = Integer.MAX_VALUE;
            this.zzg = 0.0f;
            this.zzh = true;
            this.zzi = -1;
            this.zzj = 0;
            this.zzk = 0;
            this.zzl = false;
            this.zzm = null;
            this.zzn = null;
            setIntervalMillis(j2);
        }

        public Builder(@NonNull LocationRequest locationRequest) {
            this(locationRequest.getPriority(), locationRequest.getIntervalMillis());
            setMinUpdateIntervalMillis(locationRequest.getMinUpdateIntervalMillis());
            setMaxUpdateDelayMillis(locationRequest.getMaxUpdateDelayMillis());
            setDurationMillis(locationRequest.getDurationMillis());
            setMaxUpdates(locationRequest.getMaxUpdates());
            setMinUpdateDistanceMeters(locationRequest.getMinUpdateDistanceMeters());
            setWaitForAccurateLocation(locationRequest.isWaitForAccurateLocation());
            setMaxUpdateAgeMillis(locationRequest.getMaxUpdateAgeMillis());
            setGranularity(locationRequest.getGranularity());
            int zza2 = locationRequest.zza();
            zzar.zza(zza2);
            this.zzk = zza2;
            this.zzl = locationRequest.zzb();
            this.zzm = locationRequest.zzc();
            zze zzd2 = locationRequest.zzd();
            boolean z2 = true;
            if (zzd2 != null && zzd2.zza()) {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            this.zzn = zzd2;
        }
    }

    @SafeParcelable.Constructor
    public LocationRequest(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 8) long j4, @SafeParcelable.RemovedParam(defaultValueUnchecked = "Long.MAX_VALUE", id = 5) long j5, @SafeParcelable.Param(id = 10) long j6, @SafeParcelable.Param(id = 6) int i4, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 11) long j7, @SafeParcelable.Param(id = 12) int i5, @SafeParcelable.Param(id = 13) int i6, @SafeParcelable.Param(id = 15) boolean z3, @SafeParcelable.Param(id = 16) WorkSource workSource, @SafeParcelable.Param(id = 17) @Nullable zze zze2) {
        long j8;
        long j9;
        int i7 = i3;
        this.zza = i7;
        if (i7 == 105) {
            this.zzb = Long.MAX_VALUE;
            j8 = j2;
        } else {
            j8 = j2;
            this.zzb = j8;
        }
        this.zzc = j3;
        this.zzd = j4;
        if (j5 == Long.MAX_VALUE) {
            j9 = j6;
        } else {
            j9 = Math.min(Math.max(1, j5 - SystemClock.elapsedRealtime()), j6);
        }
        this.zze = j9;
        this.zzf = i4;
        this.zzg = f2;
        this.zzh = z2;
        this.zzi = j7 != -1 ? j7 : j8;
        this.zzj = i5;
        this.zzk = i6;
        this.zzl = z3;
        this.zzm = workSource;
        this.zzn = zze2;
    }
}
