package com.google.android.gms.internal.auth;

final class zzhb extends zzgz {
    public final /* bridge */ /* synthetic */ Object zza(Object obj) {
        zzev zzev = (zzev) obj;
        zzha zzha = zzev.zzc;
        if (zzha != zzha.zza()) {
            return zzha;
        }
        zzha zzd = zzha.zzd();
        zzev.zzc = zzd;
        return zzd;
    }

    public final /* synthetic */ Object zzb(Object obj) {
        return ((zzev) obj).zzc;
    }

    public final /* bridge */ /* synthetic */ Object zzc(Object obj, Object obj2) {
        if (zzha.zza().equals(obj2)) {
            return obj;
        }
        if (zzha.zza().equals(obj)) {
            return zzha.zzc((zzha) obj, (zzha) obj2);
        }
        ((zzha) obj).zzb((zzha) obj2);
        return obj;
    }

    public final /* bridge */ /* synthetic */ void zzd(Object obj, int i3, long j2) {
        ((zzha) obj).zzh(i3 << 3, Long.valueOf(j2));
    }

    public final void zze(Object obj) {
        ((zzev) obj).zzc.zzf();
    }

    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        ((zzev) obj).zzc = (zzha) obj2;
    }
}
