package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Map;

final class zzdu extends zzdt {
    public final void zza(Object obj) {
        ((zzed) obj).zzb.zzg();
    }

    public final void zzb(zzhh zzhh, Map.Entry entry) throws IOException {
        zzee zzee = (zzee) entry.getKey();
        zzhf zzhf = zzhf.DOUBLE;
        switch (zzee.zzb.ordinal()) {
            case 0:
                zzhh.zzf(zzee.zza, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzhh.zzo(zzee.zza, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzhh.zzt(zzee.zza, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzhh.zzK(zzee.zza, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzhh.zzr(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzhh.zzm(zzee.zza, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzhh.zzk(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzhh.zzb(zzee.zza, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzhh.zzG(zzee.zza, (String) entry.getValue());
                return;
            case 9:
                zzhh.zzq(zzee.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzhh.zzv(zzee.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzhh.zzd(zzee.zza, (zzdf) entry.getValue());
                return;
            case 12:
                zzhh.zzI(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzhh.zzr(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzhh.zzx(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzhh.zzz(zzee.zza, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzhh.zzB(zzee.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzhh.zzD(zzee.zza, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }
}
