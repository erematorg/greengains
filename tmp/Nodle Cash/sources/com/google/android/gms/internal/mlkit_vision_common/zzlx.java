package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import androidx.collection.SieveCacheKt;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class zzlx implements Closeable {
    private static final Map zza = new HashMap();
    private final String zzb;
    private int zzc;
    private double zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private long zzh;

    private zzlx(String str) {
        this.zzg = SieveCacheKt.NodeLinkMask;
        this.zzh = SieveCacheKt.NodeMetaAndPreviousMask;
        this.zzb = str;
    }

    private final void zza() {
        this.zzc = 0;
        this.zzd = 0.0d;
        this.zze = 0;
        this.zzg = SieveCacheKt.NodeLinkMask;
        this.zzh = SieveCacheKt.NodeMetaAndPreviousMask;
    }

    public static zzlx zze(String str) {
        zzmw.zza();
        if (!zzmw.zzb()) {
            return zzlv.zza;
        }
        Map map = zza;
        if (map.get("detectorTaskWithResource#run") == null) {
            map.put("detectorTaskWithResource#run", new zzlx("detectorTaskWithResource#run"));
        }
        return (zzlx) map.get("detectorTaskWithResource#run");
    }

    public void close() {
        long j2 = this.zze;
        if (j2 != 0) {
            zzd(j2);
            return;
        }
        throw new IllegalStateException("Did you forget to call start()?");
    }

    public zzlx zzb() {
        this.zze = SystemClock.elapsedRealtimeNanos() / 1000;
        return this;
    }

    public void zzc(long j2) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
        long j3 = this.zzf;
        if (j3 != 0 && elapsedRealtimeNanos - j3 >= 1000000) {
            zza();
        }
        this.zzf = elapsedRealtimeNanos;
        this.zzc++;
        this.zzd += (double) j2;
        this.zzg = Math.min(this.zzg, j2);
        this.zzh = Math.max(this.zzh, j2);
        if (this.zzc % 50 == 0) {
            Locale locale = Locale.US;
            zzmw.zza();
        }
        if (this.zzc % 500 == 0) {
            zza();
        }
    }

    public void zzd(long j2) {
        zzc((SystemClock.elapsedRealtimeNanos() / 1000) - j2);
    }
}
