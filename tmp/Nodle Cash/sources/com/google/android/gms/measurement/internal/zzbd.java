package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzbd {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    public zzbd(String str, String str2, long j2, long j3, long j4, long j5, Long l2, Long l3, Long l4, Boolean bool) {
        this(str, str2, 0, 0, 0, j4, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
    }

    public final zzbd zza(Long l2, Long l3, Boolean bool) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l2, l3, (bool == null || bool.booleanValue()) ? bool : null);
    }

    public zzbd(String str, String str2, long j2, long j3, long j4, long j5, long j6, Long l2, Long l3, Long l4, Boolean bool) {
        long j7 = j2;
        long j8 = j3;
        long j9 = j4;
        long j10 = j6;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z2 = false;
        Preconditions.checkArgument(j7 >= 0);
        Preconditions.checkArgument(j8 >= 0);
        Preconditions.checkArgument(j9 >= 0);
        Preconditions.checkArgument(j10 >= 0 ? true : z2);
        this.zza = str;
        this.zzb = str2;
        this.zzc = j7;
        this.zzd = j8;
        this.zze = j9;
        this.zzf = j5;
        this.zzg = j10;
        this.zzh = l2;
        this.zzi = l3;
        this.zzj = l4;
        this.zzk = bool;
    }

    public final zzbd zza(long j2, long j3) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, j2, Long.valueOf(j3), this.zzi, this.zzj, this.zzk);
    }

    public final zzbd zza(long j2) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, j2, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }
}
