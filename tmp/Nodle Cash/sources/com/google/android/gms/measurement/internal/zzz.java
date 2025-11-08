package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import java.util.ArrayList;
import java.util.List;

final class zzz {
    private zzgn.zzf zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzv zzd;

    public final zzgn.zzf zza(String str, zzgn.zzf zzf) {
        Object obj;
        String zzg = zzf.zzg();
        List<zzgn.zzh> zzh = zzf.zzh();
        this.zzd.g_();
        Long l2 = (Long) zzol.zzb(zzf, "_eid");
        boolean z2 = l2 != null;
        if (z2 && zzg.equals("_ep")) {
            Preconditions.checkNotNull(l2);
            this.zzd.g_();
            zzg = (String) zzol.zzb(zzf, "_en");
            if (TextUtils.isEmpty(zzg)) {
                this.zzd.zzj().zzm().zza("Extra parameter without an event name. eventId", l2);
                return null;
            }
            if (this.zza == null || this.zzb == null || l2.longValue() != this.zzb.longValue()) {
                Pair<zzgn.zzf, Long> zza2 = this.zzd.zzh().zza(str, l2);
                if (zza2 == null || (obj = zza2.first) == null) {
                    this.zzd.zzj().zzm().zza("Extra parameter without existing main event. eventName, eventId", zzg, l2);
                    return null;
                }
                this.zza = (zzgn.zzf) obj;
                this.zzc = ((Long) zza2.second).longValue();
                this.zzd.g_();
                this.zzb = (Long) zzol.zzb(this.zza, "_eid");
            }
            long j2 = this.zzc - 1;
            this.zzc = j2;
            if (j2 <= 0) {
                zzam zzh2 = this.zzd.zzh();
                zzh2.zzt();
                zzh2.zzj().zzp().zza("Clearing complex main event info. appId", str);
                try {
                    zzh2.e_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e3) {
                    zzh2.zzj().zzg().zza("Error clearing complex main event", e3);
                }
            } else {
                this.zzd.zzh().zza(str, l2, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzgn.zzh next : this.zza.zzh()) {
                this.zzd.g_();
                if (zzol.zza(zzf, next.zzg()) == null) {
                    arrayList.add(next);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zzh);
                zzh = arrayList;
            } else {
                this.zzd.zzj().zzm().zza("No unique parameters in main event. eventName", zzg);
            }
        } else if (z2) {
            this.zzb = l2;
            this.zza = zzf;
            this.zzd.g_();
            long longValue = ((Long) zzol.zza(zzf, "_epc", (Object) 0L)).longValue();
            this.zzc = longValue;
            if (longValue <= 0) {
                this.zzd.zzj().zzm().zza("Complex event with zero extra param count. eventName", zzg);
            } else {
                this.zzd.zzh().zza(str, (Long) Preconditions.checkNotNull(l2), this.zzc, zzf);
            }
        }
        return (zzgn.zzf) ((zzlc) ((zzgn.zzf.zza) zzf.zzcd()).zza(zzg).zzd().zza((Iterable<? extends zzgn.zzh>) zzh).zzai());
    }

    private zzz(zzv zzv) {
        this.zzd = zzv;
    }
}
