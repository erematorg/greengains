package com.google.android.recaptcha.internal;

import java.io.IOException;

final class zzln extends zzll {
    public final /* synthetic */ int zza(Object obj) {
        return ((zzlm) obj).zza();
    }

    public final /* synthetic */ int zzb(Object obj) {
        return ((zzlm) obj).zzb();
    }

    public final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzit zzit = (zzit) obj;
        zzlm zzlm = zzit.zzc;
        if (zzlm != zzlm.zzc()) {
            return zzlm;
        }
        zzlm zzf = zzlm.zzf();
        zzit.zzc = zzf;
        return zzf;
    }

    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzit) obj).zzc;
    }

    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzlm.zzc().equals(obj2)) {
            return obj;
        }
        if (zzlm.zzc().equals(obj)) {
            return zzlm.zze((zzlm) obj, (zzlm) obj2);
        }
        ((zzlm) obj).zzd((zzlm) obj2);
        return obj;
    }

    public final /* synthetic */ Object zzf() {
        return zzlm.zzf();
    }

    public final /* synthetic */ Object zzg(Object obj) {
        ((zzlm) obj).zzh();
        return obj;
    }

    public final /* bridge */ /* synthetic */ void zzh(Object obj, int i3, int i4) {
        ((zzlm) obj).zzj((i3 << 3) | 5, Integer.valueOf(i4));
    }

    public final /* bridge */ /* synthetic */ void zzi(Object obj, int i3, long j2) {
        ((zzlm) obj).zzj((i3 << 3) | 1, Long.valueOf(j2));
    }

    public final /* bridge */ /* synthetic */ void zzj(Object obj, int i3, Object obj2) {
        ((zzlm) obj).zzj((i3 << 3) | 3, obj2);
    }

    public final /* bridge */ /* synthetic */ void zzk(Object obj, int i3, zzgw zzgw) {
        ((zzlm) obj).zzj((i3 << 3) | 2, zzgw);
    }

    public final /* bridge */ /* synthetic */ void zzl(Object obj, int i3, long j2) {
        ((zzlm) obj).zzj(i3 << 3, Long.valueOf(j2));
    }

    public final void zzm(Object obj) {
        ((zzit) obj).zzc.zzh();
    }

    public final /* synthetic */ void zzn(Object obj, Object obj2) {
        ((zzit) obj).zzc = (zzlm) obj2;
    }

    public final /* synthetic */ void zzo(Object obj, Object obj2) {
        ((zzit) obj).zzc = (zzlm) obj2;
    }

    public final /* synthetic */ void zzp(Object obj, zzmd zzmd) throws IOException {
        ((zzlm) obj).zzk(zzmd);
    }

    public final /* synthetic */ void zzq(Object obj, zzmd zzmd) throws IOException {
        ((zzlm) obj).zzl(zzmd);
    }

    public final boolean zzs(zzkq zzkq) {
        return false;
    }
}
