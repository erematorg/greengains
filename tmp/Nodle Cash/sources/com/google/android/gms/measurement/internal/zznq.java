package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amplitude.api.Constants;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzrw;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.HashMap;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.checkerframework.dataflow.qual.Pure;

public final class zznq extends zzno {
    public zznq(zznv zznv) {
        super(zznv);
    }

    public final /* bridge */ /* synthetic */ zzol g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzv zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzam zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzhg zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzms zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zznq zzo() {
        return super.zzo();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    private final String zzb(String str) {
        String zzf = zzm().zzf(str);
        if (TextUtils.isEmpty(zzf)) {
            return zzbj.zzq.zza(null);
        }
        Uri parse = Uri.parse(zzbj.zzq.zza(null));
        Uri.Builder buildUpon = parse.buildUpon();
        String authority = parse.getAuthority();
        buildUpon.authority(zzf + JwtUtilsKt.JWT_DELIMITER + authority);
        return buildUpon.build().toString();
    }

    public final zzns zza(String str) {
        zzh zze;
        if (zzrw.zza() && zze().zza(zzbj.zzbw)) {
            zzq();
            if (zzop.zzf(str)) {
                zzj().zzp().zza("sgtm feature flag enabled.");
                zzh zze2 = zzh().zze(str);
                if (zze2 == null) {
                    return new zzns(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                String zzad = zze2.zzad();
                zzfx.zzd zzc = zzm().zzc(str);
                if (zzc == null || (zze = zzh().zze(str)) == null || ((!zzc.zzr() || zzc.zzh().zza() != 100) && !zzq().zzd(str, zze.zzam()) && (TextUtils.isEmpty(zzad) || zzad.hashCode() % 100 >= zzc.zzh().zza()))) {
                    return new zzns(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                zzns zzns = null;
                if (zze2.zzat()) {
                    zzj().zzp().zza("sgtm upload enabled in manifest.");
                    zzfx.zzd zzc2 = zzm().zzc(zze2.zzac());
                    if (zzc2 != null && zzc2.zzr()) {
                        String zze3 = zzc2.zzh().zze();
                        if (!TextUtils.isEmpty(zze3)) {
                            String zzd = zzc2.zzh().zzd();
                            zzj().zzp().zza("sgtm configured with upload_url, server_info", zze3, TextUtils.isEmpty(zzd) ? "Y" : "N");
                            if (TextUtils.isEmpty(zzd)) {
                                zzns = new zzns(zze3, zznt.SGTM);
                            } else {
                                HashMap hashMap = new HashMap();
                                hashMap.put("x-sgtm-server-info", zzd);
                                if (!TextUtils.isEmpty(zze2.zzam())) {
                                    hashMap.put("x-gtm-server-preview", zze2.zzam());
                                }
                                zzns = new zzns(zze3, hashMap, zznt.SGTM);
                            }
                        }
                    }
                }
                if (zzns != null) {
                    return zzns;
                }
            }
        }
        return new zzns(zzb(str), zznt.GOOGLE_ANALYTICS);
    }

    public final String zza(zzh zzh) {
        Uri.Builder builder = new Uri.Builder();
        String zzah = zzh.zzah();
        if (TextUtils.isEmpty(zzah)) {
            zzah = zzh.zzaa();
        }
        Uri.Builder encodedAuthority = builder.scheme(zzbj.zze.zza(null)).encodedAuthority(zzbj.zzf.zza(null));
        encodedAuthority.path("config/app/" + zzah).appendQueryParameter(Constants.AMP_TRACKING_OPTION_PLATFORM, DeviceInfo.OS_NAME).appendQueryParameter("gmp_version", "102001").appendQueryParameter("runtime_version", SchemaSymbols.ATTVAL_FALSE_0);
        return builder.build().toString();
    }
}
