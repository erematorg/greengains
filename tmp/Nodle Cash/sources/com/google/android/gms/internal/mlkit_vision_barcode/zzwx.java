package com.google.android.gms.internal.mlkit_vision_barcode;

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

public final class zzwx implements zzwf {
    @Nullable
    private Provider zza;
    private final Provider zzb;
    private final zzwh zzc;

    public zzwx(Context context, zzwh zzwh) {
        this.zzc = zzwh;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        TransportFactory newFactory = TransportRuntime.getInstance().newFactory((Destination) cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new zzwu(newFactory));
        }
        this.zzb = new Lazy(new zzwv(newFactory));
    }

    @VisibleForTesting
    public static Event zzb(zzwh zzwh, zzwe zzwe) {
        int zza2 = zzwh.zza();
        return zzwe.zza() != 0 ? Event.ofData(zzwe.zze(zza2, false)) : Event.ofTelemetry(zzwe.zze(zza2, false));
    }

    public final void zza(zzwe zzwe) {
        if (this.zzc.zza() == 0) {
            Provider provider = this.zza;
            if (provider != null) {
                ((Transport) provider.get()).send(zzb(this.zzc, zzwe));
                return;
            }
            return;
        }
        ((Transport) this.zzb.get()).send(zzb(this.zzc, zzwe));
    }
}
