package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzjg;
import com.google.android.gms.measurement.internal.zzjj;
import com.google.android.gms.measurement.internal.zzky;
import java.util.List;
import java.util.Map;

final class zzc extends AppMeasurement.zza {
    private final zzky zza;

    public zzc(zzky zzky) {
        super();
        Preconditions.checkNotNull(zzky);
        this.zza = zzky;
    }

    public final int zza(String str) {
        return this.zza.zza(str);
    }

    public final Boolean zzb() {
        return (Boolean) this.zza.zza(4);
    }

    public final Double zzc() {
        return (Double) this.zza.zza(2);
    }

    public final Integer zzd() {
        return (Integer) this.zza.zza(3);
    }

    public final Long zze() {
        return (Long) this.zza.zza(1);
    }

    public final String zzf() {
        return this.zza.zzf();
    }

    public final String zzg() {
        return this.zza.zzg();
    }

    public final String zzh() {
        return this.zza.zzh();
    }

    public final String zzi() {
        return this.zza.zzi();
    }

    public final String zzj() {
        return (String) this.zza.zza(0);
    }

    public final long zza() {
        return this.zza.zza();
    }

    public final void zzb(String str) {
        this.zza.zzb(str);
    }

    public final void zzc(String str) {
        this.zza.zzc(str);
    }

    public final Object zza(int i3) {
        return this.zza.zza(i3);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        this.zza.zzb(str, str2, bundle);
    }

    public final List<Bundle> zza(String str, String str2) {
        return this.zza.zza(str, str2);
    }

    public final void zzb(zzjj zzjj) {
        this.zza.zzb(zzjj);
    }

    public final Map<String, Object> zza(boolean z2) {
        return this.zza.zza((String) null, (String) null, z2);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z2) {
        return this.zza.zza(str, str2, z2);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        this.zza.zza(str, str2, bundle);
    }

    public final void zza(String str, String str2, Bundle bundle, long j2) {
        this.zza.zza(str, str2, bundle, j2);
    }

    public final void zza(zzjj zzjj) {
        this.zza.zza(zzjj);
    }

    public final void zza(Bundle bundle) {
        this.zza.zza(bundle);
    }

    public final void zza(zzjg zzjg) {
        this.zza.zza(zzjg);
    }
}
