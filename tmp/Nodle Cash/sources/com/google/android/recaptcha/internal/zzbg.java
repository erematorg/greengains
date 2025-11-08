package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzbg {
    @NotNull
    private final String zza;
    @NotNull
    private final Context zzb;
    @NotNull
    private final zzab zzc;
    @NotNull
    private final zzbh zzd;
    @NotNull
    private final HashMap zze = new HashMap();
    @NotNull
    private final zzt zzf;

    public zzbg(@NotNull String str, @NotNull Context context, @NotNull zzab zzab, @NotNull zzt zzt, @NotNull zzbh zzbh) {
        this.zza = str;
        this.zzb = context;
        this.zzc = zzab;
        this.zzf = zzt;
        this.zzd = zzbh;
    }

    public final void zza(@NotNull zzbb zzbb) {
        zze(zzbb, 3, (zzmr) null);
    }

    public final void zzb(@NotNull zzbb zzbb, @NotNull zzp zzp, @Nullable String str) {
        String valueOf = String.valueOf(zzp.zzb().zza());
        int zza2 = zzp.zza().zza();
        String zzd2 = zzp.zzd();
        zzmq zzg = zzmr.zzg();
        zzg.zzp(valueOf);
        zzg.zzd(zza2);
        if (zzd2 != null) {
            zzg.zze(zzd2);
        }
        zze(zzbb, 4, (zzmr) zzg.zzj());
    }

    public final void zzd(@NotNull zzpd zzpd) {
        this.zzd.zza(zzpd);
    }

    public final void zze(@NotNull zzbb zzbb, @NotNull int i3, @Nullable zzmr zzmr) {
        zzx zzx;
        zzbf zzbf = (zzbf) this.zze.get(zzbb);
        if (zzbf != null) {
            zznf zza2 = zzbf.zza(i3, zzmr, this.zzb);
            zzpc zzi = zzpd.zzi();
            zzi.zzd(zza2);
            zzpd zzpd = (zzpd) zzi.zzj();
            zzv zzv = zzv.zza;
            zzne zza3 = zzbb.zza();
            long zzf2 = zza2.zzf() * 1000;
            zzne zzne = zzne.UNKNOWN;
            int ordinal = zza3.ordinal();
            if (ordinal == 1) {
                zzx = zzx.zzd;
            } else if (ordinal == 2) {
                zzx = zzx.zze;
            } else if (ordinal == 5) {
                zzx = zzx.zzf;
            } else if (ordinal == 6) {
                zzx = zzx.zzg;
            } else if (ordinal != 24) {
                switch (ordinal) {
                    case 12:
                        zzx = zzx.zzh;
                        break;
                    case 13:
                        zzx = zzx.zzi;
                        break;
                    case 14:
                        zzx = zzx.zzj;
                        break;
                    default:
                        zzx = zzx.zzb;
                        break;
                }
            } else {
                zzx = zzx.zzk;
            }
            zzv.zza(zzx.zza(), zzf2);
            this.zzd.zza(zzpd);
            zzbf zzbf2 = (zzbf) this.zze.remove(zzbb);
        }
    }
}
