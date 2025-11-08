package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Map;

final class zzig extends zzif {
    public final int zza(Map.Entry entry) {
        return ((zziq) entry.getKey()).zza;
    }

    public final zzij zzb(Object obj) {
        return ((zzip) obj).zzb;
    }

    public final zzij zzc(Object obj) {
        return ((zzip) obj).zzi();
    }

    public final Object zzd(zzie zzie, zzke zzke, int i3) {
        return zzie.zza(zzke, i3);
    }

    public final Object zze(Object obj, zzkq zzkq, Object obj2, zzie zzie, zzij zzij, Object obj3, zzll zzll) throws IOException {
        Object zze;
        zzir zzir = (zzir) obj2;
        zzmb zzmb = zzir.zzb.zzb;
        Object obj4 = null;
        if (zzmb != zzmb.ENUM) {
            switch (zzmb.ordinal()) {
                case 0:
                    obj4 = Double.valueOf(zzkq.zza());
                    break;
                case 1:
                    obj4 = Float.valueOf(zzkq.zzb());
                    break;
                case 2:
                    obj4 = Long.valueOf(zzkq.zzl());
                    break;
                case 3:
                    obj4 = Long.valueOf(zzkq.zzo());
                    break;
                case 4:
                    obj4 = Integer.valueOf(zzkq.zzg());
                    break;
                case 5:
                    obj4 = Long.valueOf(zzkq.zzk());
                    break;
                case 6:
                    obj4 = Integer.valueOf(zzkq.zzf());
                    break;
                case 7:
                    obj4 = Boolean.valueOf(zzkq.zzN());
                    break;
                case 8:
                    obj4 = zzkq.zzr();
                    break;
                case 9:
                    Object zze2 = zzij.zze(zzir.zzb);
                    if (zze2 instanceof zzit) {
                        zzkr zzb = zzkn.zza().zzb(zze2.getClass());
                        if (!((zzit) zze2).zzG()) {
                            Object zze3 = zzb.zze();
                            zzb.zzg(zze3, zze2);
                            zzij.zzi(zzir.zzb, zze3);
                            zze2 = zze3;
                        }
                        zzkq.zzt(zze2, zzb, zzie);
                        return obj3;
                    }
                    throw null;
                case 10:
                    Object zze4 = zzij.zze(zzir.zzb);
                    if (zze4 instanceof zzit) {
                        zzkr zzb2 = zzkn.zza().zzb(zze4.getClass());
                        if (!((zzit) zze4).zzG()) {
                            Object zze5 = zzb2.zze();
                            zzb2.zzg(zze5, zze4);
                            zzij.zzi(zzir.zzb, zze5);
                            zze4 = zze5;
                        }
                        zzkq.zzu(zze4, zzb2, zzie);
                        return obj3;
                    }
                    throw null;
                case 11:
                    obj4 = zzkq.zzp();
                    break;
                case 12:
                    obj4 = Integer.valueOf(zzkq.zzj());
                    break;
                case 13:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 14:
                    obj4 = Integer.valueOf(zzkq.zzh());
                    break;
                case 15:
                    obj4 = Long.valueOf(zzkq.zzm());
                    break;
                case 16:
                    obj4 = Integer.valueOf(zzkq.zzi());
                    break;
                case 17:
                    obj4 = Long.valueOf(zzkq.zzn());
                    break;
            }
            int ordinal = zzir.zzb.zzb.ordinal();
            if ((ordinal == 9 || ordinal == 10) && (zze = zzij.zze(zzir.zzb)) != null) {
                byte[] bArr = zzjc.zzd;
                obj4 = ((zzke) zze).zzX().zzc((zzke) obj4).zzk();
            }
            zzij.zzi(zzir.zzb, obj4);
            return obj3;
        }
        zzkq.zzg();
        throw null;
    }

    public final void zzf(Object obj) {
        ((zzip) obj).zzb.zzg();
    }

    public final void zzg(zzkq zzkq, Object obj, zzie zzie, zzij zzij) throws IOException {
        throw null;
    }

    public final void zzh(zzgw zzgw, Object obj, zzie zzie, zzij zzij) throws IOException {
        throw null;
    }

    public final void zzi(zzmd zzmd, Map.Entry entry) throws IOException {
        zziq zziq = (zziq) entry.getKey();
        zzmb zzmb = zzmb.DOUBLE;
        switch (zziq.zzb.ordinal()) {
            case 0:
                zzmd.zzf(zziq.zza, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzmd.zzo(zziq.zza, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzmd.zzt(zziq.zza, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzmd.zzK(zziq.zza, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzmd.zzr(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzmd.zzm(zziq.zza, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzmd.zzk(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzmd.zzb(zziq.zza, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzmd.zzG(zziq.zza, (String) entry.getValue());
                return;
            case 9:
                zzmd.zzq(zziq.zza, entry.getValue(), zzkn.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzmd.zzv(zziq.zza, entry.getValue(), zzkn.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzmd.zzd(zziq.zza, (zzgw) entry.getValue());
                return;
            case 12:
                zzmd.zzI(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzmd.zzr(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzmd.zzx(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzmd.zzz(zziq.zza, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzmd.zzB(zziq.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzmd.zzD(zziq.zza, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    public final boolean zzj(zzke zzke) {
        return zzke instanceof zzip;
    }
}
