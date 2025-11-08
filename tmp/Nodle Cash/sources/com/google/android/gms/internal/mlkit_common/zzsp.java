package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.Destination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

public final class zzsp implements zzrz {
    @Nullable
    private Provider zza;
    private final Provider zzb;
    private final zzsb zzc;

    public zzsp(Context context, zzsb zzsb) {
        this.zzc = zzsb;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        TransportFactory newFactory = TransportRuntime.getInstance().newFactory((Destination) cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new zzsm(newFactory));
        }
        this.zzb = new Lazy(new zzsn(newFactory));
    }

    @VisibleForTesting
    public static Event zzb(zzsb zzsb, zzry zzry) {
        return Event.ofTelemetry(zzry.zze(zzsb.zza(), false));
    }

    public final void zza(zzry zzry) {
        if (this.zzc.zza() == 0) {
            Provider provider = this.zza;
            if (provider != null) {
                ((Transport) provider.get()).send(zzb(this.zzc, zzry));
                return;
            }
            return;
        }
        ((Transport) this.zzb.get()).send(zzb(this.zzc, zzry));
    }
}
