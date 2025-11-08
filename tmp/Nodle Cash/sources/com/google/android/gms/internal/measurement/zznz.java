package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zznz extends zznx<zznw, zznw> {
    public final boolean zza(zzna zzna) {
        return false;
    }

    public final /* synthetic */ int zzb(Object obj) {
        return ((zznw) obj).zzb();
    }

    public final /* synthetic */ Object zzc(Object obj) {
        zznw zznw = ((zzlc) obj).zzb;
        if (zznw != zznw.zzc()) {
            return zznw;
        }
        zznw zzd = zznw.zzd();
        zza(obj, zzd);
        return zzd;
    }

    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzlc) obj).zzb;
    }

    public final /* synthetic */ Object zze(Object obj) {
        zznw zznw = (zznw) obj;
        zznw.zze();
        return zznw;
    }

    public final void zzf(Object obj) {
        ((zzlc) obj).zzb.zze();
    }

    public final /* synthetic */ int zza(Object obj) {
        return ((zznw) obj).zza();
    }

    public final /* synthetic */ void zzb(Object obj, int i3, long j2) {
        ((zznw) obj).zza(i3 << 3, (Object) Long.valueOf(j2));
    }

    public final /* synthetic */ Object zza(Object obj, Object obj2) {
        zznw zznw = (zznw) obj;
        zznw zznw2 = (zznw) obj2;
        if (zznw.zzc().equals(zznw2)) {
            return zznw;
        }
        if (zznw.zzc().equals(zznw)) {
            return zznw.zza(zznw, zznw2);
        }
        return zznw.zza(zznw2);
    }

    public final /* synthetic */ void zzb(Object obj, Object obj2) {
        zza(obj, (zznw) obj2);
    }

    public final /* synthetic */ void zzc(Object obj, Object obj2) {
        zza(obj, (zznw) obj2);
    }

    public final /* synthetic */ void zzb(Object obj, zzos zzos) throws IOException {
        ((zznw) obj).zzb(zzos);
    }

    public final /* synthetic */ Object zza() {
        return zznw.zzd();
    }

    public final /* synthetic */ void zza(Object obj, int i3, int i4) {
        ((zznw) obj).zza((i3 << 3) | 5, (Object) Integer.valueOf(i4));
    }

    public final /* synthetic */ void zza(Object obj, int i3, long j2) {
        ((zznw) obj).zza((i3 << 3) | 1, (Object) Long.valueOf(j2));
    }

    public final /* synthetic */ void zza(Object obj, int i3, Object obj2) {
        ((zznw) obj).zza((i3 << 3) | 3, (Object) (zznw) obj2);
    }

    public final /* synthetic */ void zza(Object obj, int i3, zzjs zzjs) {
        ((zznw) obj).zza((i3 << 3) | 2, (Object) zzjs);
    }

    private static void zza(Object obj, zznw zznw) {
        ((zzlc) obj).zzb = zznw;
    }

    public final /* synthetic */ void zza(Object obj, zzos zzos) throws IOException {
        ((zznw) obj).zza(zzos);
    }
}
