package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzwr {
    private final TelemetryLoggingClient zza;
    private final AtomicLong zzb = new AtomicLong(-1);

    @VisibleForTesting
    public zzwr(Context context, String str) {
        this.zza = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("mlkit:vision").build());
    }

    public static zzwr zza(Context context) {
        return new zzwr(context, "mlkit:vision");
    }

    public final /* synthetic */ void zzb(long j2, Exception exc) {
        this.zzb.set(j2);
    }

    public final synchronized void zzc(int i3, int i4, long j2, long j3) {
        synchronized (this) {
            AtomicLong atomicLong = this.zzb;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (atomicLong.get() != -1) {
                if (elapsedRealtime - this.zzb.get() <= TimeUnit.MINUTES.toMillis(30)) {
                    return;
                }
            }
            this.zza.log(new TelemetryData(0, Arrays.asList(new MethodInvocation[]{new MethodInvocation(i3, i4, 0, j2, j3, (String) null, (String) null, 0, -1)}))).addOnFailureListener(new zzwq(this, elapsedRealtime));
        }
    }
}
