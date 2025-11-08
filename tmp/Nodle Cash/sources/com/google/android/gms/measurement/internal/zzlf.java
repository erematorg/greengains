package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import androidx.collection.SieveCacheKt;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzqn;
import com.google.android.gms.internal.measurement.zzrq;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzlf extends zznr {
    public zzlf(zznv zznv) {
        super(zznv);
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    public final boolean zzc() {
        return false;
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzbh zzbh, @Size(min = 1) String str) {
        zzom zzom;
        zzgn.zzj.zza zza;
        zzgn.zzk.zza zza2;
        byte[] bArr;
        zzh zzh;
        Bundle bundle;
        zzbd zzbd;
        long j2;
        zzbh zzbh2 = zzbh;
        String str2 = str;
        zzt();
        this.zzu.zzy();
        Preconditions.checkNotNull(zzbh);
        Preconditions.checkNotEmpty(str);
        if (!zze().zze(str2, zzbj.zzbk)) {
            zzj().zzc().zza("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzbh2.zza) || "_iapx".equals(zzbh2.zza)) {
            zzgn.zzj.zza zzb = zzgn.zzj.zzb();
            zzh().zzp();
            zzh zze = zzh().zze(str2);
            if (zze == null) {
                zzj().zzc().zza("Log and bundle not available. package_name", str2);
                byte[] bArr2 = new byte[0];
                zzh().zzu();
                return bArr2;
            } else if (!zze.zzar()) {
                zzj().zzc().zza("Log and bundle disabled. package_name", str2);
                byte[] bArr3 = new byte[0];
                zzh().zzu();
                return bArr3;
            } else {
                zzgn.zzk.zza zzp = zzgn.zzk.zzw().zzh(1).zzp(DeviceInfo.OS_NAME);
                if (!TextUtils.isEmpty(zze.zzac())) {
                    zzp.zzb(zze.zzac());
                }
                if (!TextUtils.isEmpty(zze.zzae())) {
                    zzp.zzd((String) Preconditions.checkNotNull(zze.zzae()));
                }
                if (!TextUtils.isEmpty(zze.zzaf())) {
                    zzp.zze((String) Preconditions.checkNotNull(zze.zzaf()));
                }
                if (zze.zze() != SieveCacheKt.NodeMetaAndPreviousMask) {
                    zzp.zze((int) zze.zze());
                }
                zzp.zzf(zze.zzq()).zzd(zze.zzo());
                String zzah = zze.zzah();
                String zzaa = zze.zzaa();
                if (!TextUtils.isEmpty(zzah)) {
                    zzp.zzm(zzah);
                } else if (!TextUtils.isEmpty(zzaa)) {
                    zzp.zza(zzaa);
                }
                zzp.zzj(zze.zzw());
                zzjc zzb2 = this.zzg.zzb(str2);
                zzp.zzc(zze.zzn());
                if (this.zzu.zzac() && zze().zzj(zzp.zzt()) && zzb2.zzi() && !TextUtils.isEmpty((CharSequence) null)) {
                    zzp.zzj((String) null);
                }
                zzp.zzg(zzb2.zzg());
                if (zzb2.zzi() && zze.zzaq()) {
                    Pair<String, Boolean> zza3 = zzn().zza(zze.zzac(), zzb2);
                    if (zze.zzaq() && zza3 != null && !TextUtils.isEmpty((CharSequence) zza3.first)) {
                        try {
                            zzp.zzq(zza((String) zza3.first, Long.toString(zzbh2.zzd)));
                            Object obj = zza3.second;
                            if (obj != null) {
                                zzp.zzc(((Boolean) obj).booleanValue());
                            }
                        } catch (SecurityException e3) {
                            zzj().zzc().zza("Resettable device id encryption failed", e3.getMessage());
                            return new byte[0];
                        } finally {
                            zzh().zzu();
                        }
                    }
                }
                zzf().zzac();
                zzgn.zzk.zza zzi = zzp.zzi(Build.MODEL);
                zzf().zzac();
                zzi.zzo(Build.VERSION.RELEASE).zzj((int) zzf().zzg()).zzs(zzf().zzh());
                try {
                    if (zzb2.zzj() && zze.zzad() != null) {
                        zzp.zzc(zza((String) Preconditions.checkNotNull(zze.zzad()), Long.toString(zzbh2.zzd)));
                    }
                    if (!TextUtils.isEmpty(zze.zzag())) {
                        zzp.zzl((String) Preconditions.checkNotNull(zze.zzag()));
                    }
                    String zzac = zze.zzac();
                    List<zzom> zzl = zzh().zzl(zzac);
                    Iterator<zzom> it = zzl.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzom = null;
                            break;
                        }
                        zzom = it.next();
                        if ("_lte".equals(zzom.zzc)) {
                            break;
                        }
                    }
                    if (zzom == null || zzom.zze == null) {
                        zzom zzom2 = new zzom(zzac, "auto", "_lte", zzb().currentTimeMillis(), 0L);
                        zzl.add(zzom2);
                        zzh().zza(zzom2);
                    }
                    zzgn.zzo[] zzoArr = new zzgn.zzo[zzl.size()];
                    for (int i3 = 0; i3 < zzl.size(); i3++) {
                        zzgn.zzo.zza zzb3 = zzgn.zzo.zze().zza(zzl.get(i3).zzc).zzb(zzl.get(i3).zzd);
                        g_().zza(zzb3, zzl.get(i3).zze);
                        zzoArr[i3] = (zzgn.zzo) ((zzlc) zzb3.zzai());
                    }
                    zzp.zze((Iterable<? extends zzgn.zzo>) Arrays.asList(zzoArr));
                    g_().zza(zzp);
                    this.zzg.zza(zze, zzp);
                    if (zzqn.zza() && zze().zza(zzbj.zzct)) {
                        this.zzg.zzb(zze, zzp);
                    }
                    zzgm zza4 = zzgm.zza(zzbh);
                    zzq().zza(zza4.zzd, zzh().zzd(str2));
                    zzq().zza(zza4, zze().zzb(str2));
                    Bundle bundle2 = zza4.zzd;
                    bundle2.putLong("_c", 1);
                    zzj().zzc().zza("Marking in-app purchase as real-time");
                    bundle2.putLong("_r", 1);
                    bundle2.putString("_o", zzbh2.zzc);
                    if (zzq().zzd(zzp.zzt(), zze.zzam())) {
                        zzq().zza(bundle2, "_dbg", (Object) 1L);
                        zzq().zza(bundle2, "_r", (Object) 1L);
                    }
                    zzbd zzd = zzh().zzd(str2, zzbh2.zza);
                    if (zzd == null) {
                        bundle = bundle2;
                        zza2 = zzp;
                        zza = zzb;
                        zzh = zze;
                        bArr = null;
                        zzbd = new zzbd(str, zzbh2.zza, 0, 0, zzbh2.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j2 = 0;
                    } else {
                        bundle = bundle2;
                        zza2 = zzp;
                        zza = zzb;
                        zzh = zze;
                        bArr = null;
                        j2 = zzd.zzf;
                        zzbd = zzd.zza(zzbh2.zzd);
                    }
                    zzh().zza(zzbd);
                    zzba zzba = new zzba(this.zzu, zzbh2.zzc, str, zzbh2.zza, zzbh2.zzd, j2, bundle);
                    zzgn.zzf.zza zza5 = zzgn.zzf.zze().zzb(zzba.zzd).zza(zzba.zzb).zza(zzba.zze);
                    Iterator<String> it2 = zzba.zzf.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        zzgn.zzh.zza zza6 = zzgn.zzh.zze().zza(next);
                        Object zzc = zzba.zzf.zzc(next);
                        if (zzc != null) {
                            g_().zza(zza6, zzc);
                            zza5.zza(zza6);
                        }
                    }
                    zzgn.zzk.zza zza7 = zza2;
                    zza7.zza(zza5).zza(zzgn.zzl.zza().zza(zzgn.zzg.zza().zza(zzbd.zzc).zza(zzbh2.zza)));
                    zza7.zza((Iterable<? extends zzgn.zzd>) zzg().zza(zzh.zzac(), Collections.emptyList(), zza7.zzab(), Long.valueOf(zza5.zzc()), Long.valueOf(zza5.zzc())));
                    if (zza5.zzg()) {
                        zza7.zzi(zza5.zzc()).zze(zza5.zzc());
                    }
                    long zzs = zzh.zzs();
                    int i4 = (zzs > 0 ? 1 : (zzs == 0 ? 0 : -1));
                    if (i4 != 0) {
                        zza7.zzg(zzs);
                    }
                    long zzu = zzh.zzu();
                    if (zzu != 0) {
                        zza7.zzh(zzu);
                    } else if (i4 != 0) {
                        zza7.zzh(zzs);
                    }
                    String zzal = zzh.zzal();
                    if (zzrq.zza()) {
                        if (zze().zze(str, zzbj.zzbv) && zzal != null) {
                            zza7.zzr(zzal);
                        }
                    } else {
                        String str3 = str;
                    }
                    zzh.zzap();
                    zza7.zzf((int) zzh.zzt()).zzl(102001).zzk(zzb().currentTimeMillis()).zzd(true);
                    this.zzg.zza(zza7.zzt(), zza7);
                    zzgn.zzj.zza zza8 = zza;
                    zza8.zza(zza7);
                    zzh zzh2 = zzh;
                    zzh2.zzr(zza7.zzf());
                    zzh2.zzp(zza7.zze());
                    zzh().zza(zzh2, false, false);
                    zzh().zzw();
                    try {
                        return g_().zzb(((zzgn.zzj) ((zzlc) zza8.zzai())).zzca());
                    } catch (IOException e4) {
                        zzj().zzg().zza("Data loss. Failed to bundle and serialize. appId", zzgi.zza(str), e4);
                        return bArr;
                    }
                } catch (SecurityException e5) {
                    zzj().zzc().zza("app instance id encryption failed", e5.getMessage());
                    byte[] bArr4 = new byte[0];
                    zzh().zzu();
                    return bArr4;
                }
            }
        } else {
            zzj().zzc().zza("Generating a payload for this event is not available. package_name, event_name", str2, zzbh2.zza);
            return null;
        }
    }
}
