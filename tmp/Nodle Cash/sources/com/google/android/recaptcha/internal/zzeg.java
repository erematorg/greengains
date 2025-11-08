package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.UInt;
import org.jetbrains.annotations.NotNull;

public final class zzeg implements zzee {
    @NotNull
    private final zzef zza;
    @NotNull
    private final zzed zzb;

    public zzeg(@NotNull zzef zzef, @NotNull zzed zzed) {
        this.zza = zzef;
        this.zzb = zzed;
    }

    private final zzpf zzb(String str, List list) throws zzae {
        if (str.length() != 0) {
            try {
                zzec zzec = new zzec(this.zza.zza(CollectionsKt___CollectionsKt.toLongArray(list)), 255, zzec.zzb);
                StringBuilder sb = new StringBuilder(str.length());
                for (int i3 = 0; i3 < str.length(); i3++) {
                    sb.append((char) UInt.m9074constructorimpl(UInt.m9074constructorimpl(str.charAt(i3)) ^ UInt.m9074constructorimpl((int) zzec.zza())));
                }
                return zzpf.zzg(zzfy.zzh().zzj(sb.toString()));
            } catch (Exception e3) {
                throw new zzae(3, 18, e3);
            }
        } else {
            throw new zzae(3, 17, (Throwable) null);
        }
    }

    @NotNull
    public final zzpf zza(@NotNull zzpn zzpn) throws zzae {
        zzfh zzb2 = zzfh.zzb();
        zzpf zzb3 = zzb(zzpn.zzi(), zzpn.zzj());
        zzb2.zzf();
        long zza2 = zzb2.zza(TimeUnit.MICROSECONDS);
        zzv zzv = zzv.zza;
        zzv.zza(zzx.zzm.zza(), zza2);
        return zzb3;
    }
}
